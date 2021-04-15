package com.github.center.service;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.center.entity.UcenterMember;
import com.github.center.mapper.UcenterMemberMapper;
import com.github.utils.JwtUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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
}
