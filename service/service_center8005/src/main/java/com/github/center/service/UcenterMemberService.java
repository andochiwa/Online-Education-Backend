package com.github.center.service;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.center.aspect.RegisterAspect;
import com.github.center.entity.UcenterMember;
import com.github.center.mapper.UcenterMemberMapper;
import com.github.center.vo.UserRegister;
import com.github.utils.JwtUtils;
import com.github.utils.ResultCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author HAN
 * @since 2021-04-15
 */
@Service
public class UcenterMemberService extends ServiceImpl<UcenterMemberMapper, UcenterMember> {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private RegisterAspect registerAspect;

    /**
     * 登录
     * @param member 登录信息
     * @return token值
     */
    public String login(UcenterMember member) {

        String password = member.getPassword();
        password = SecureUtil.md5(password);
        String email = member.getEmail();

        // 判断邮箱和密码是否为空
        if (ObjectUtils.isEmpty(password) || ObjectUtils.isEmpty(email)) {
            throw new RuntimeException("邮箱或密码不能为空");
        }

        // 根据用户名查询数据库
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("email", member.getEmail());
        UcenterMember ucenterMember = super.getOne(wrapper);
        // 判断用户是否存在以及密码是否正确
        if (ObjectUtils.isEmpty(ucenterMember) || !Objects.equals(ucenterMember.getPassword(), password)) {
            throw new RuntimeException("邮箱或密码不正确");
        }

        // 判断用户是否被禁用
        if (ucenterMember.getIsDisabled()) {
            throw new RuntimeException("用户被禁用");
        }

        return JwtUtils.getJwtToken(ucenterMember.getId(), ucenterMember.getNickname());
    }

    /**
     * 注册
     * @param userRegister 注册信息
     */
    public ResultCommon register(UserRegister userRegister) {
        String nickName = userRegister.getNickName();
        String password = userRegister.getPassword();
        String email = userRegister.getEmail();
        String code = userRegister.getCode();

        Object redisCode = redisTemplate.opsForValue().get(email);
        if (!Objects.equals(code, redisCode)) {
            return ResultCommon.fail().setMessage("验证码错误");
        }

        UcenterMember ucenterMember = new UcenterMember();
        ucenterMember.setNickname(nickName);
        ucenterMember.setPassword(SecureUtil.md5(password));
        ucenterMember.setEmail(email);
        super.save(ucenterMember);
        return ResultCommon.success().setData("items", ucenterMember);
    }

    /**
     * 从github中得到的用户信息放入数据库
     * @param userMap 用户信息
     */
    public UcenterMember saveUser(Map<String, Object> userMap) {
        // 查看是否已存在用户
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("openid", userMap.get("node_id"));
        UcenterMember ucenterMember = super.getOne(wrapper);
        if (ucenterMember == null) {
            ucenterMember = new UcenterMember();
            ucenterMember.setNickname((String) userMap.get("name"));
            ucenterMember.setOpenid((String) userMap.get("node_id"));
            ucenterMember.setAvatar((String) userMap.get("avatar_url"));
            super.save(ucenterMember);
            // 统计注册人数+1
            registerAspect.countRegister();
        }
        return ucenterMember;

    }

    /**
     * 查询某一天的注册人数
     * @param date 哪一天
     * @return 注册人数
     */
    public int statRegister(String date) {
        int count = baseMapper.countRegister(date);
        return count;
    }

    /**
     * 更新用户信息
     * @param ucenterMember 更新用户信息
     */
    public void updateByUserId(UcenterMember ucenterMember) {
        if (!ObjectUtils.isEmpty(ucenterMember.getPassword()) && ucenterMember.getPassword().length() != 32) {
            ucenterMember.setPassword(SecureUtil.md5(ucenterMember.getPassword()));
        }
        super.updateById(ucenterMember);
    }
}
