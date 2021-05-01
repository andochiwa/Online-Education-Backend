package com.github.acl.service;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.acl.mapper.UserMapper;
import com.github.security.entity.User;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author HAN
 * @since 2021-04-22
 */
@Service
@Transactional
public class UserService extends ServiceImpl<UserMapper, User> {

    @Autowired
    private UserRoleService userRoleService;

    /**
     * 根据用户名查询
     * @param username 用户名
     */
    public User getByUsername(String username) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        return super.getOne(wrapper);
    }

    /**
     * 分页条件查询用户信息
     * @param current 第几条开始
     * @param limit 要几条
     * @param name 条件查询用户名
     * @return 用户信息
     */
    public Map<String, Object> getPageConditionList(Long current, Long limit, String name) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (StringUtils.hasText(name)) {
            wrapper.like("username", name);
        }
        Page<User> page = new Page<>(current, limit);
        super.page(page, wrapper);

        HashedMap<String, Object> map = new HashedMap<>();
        map.put("list", page.getRecords());
        map.put("total", page.getTotal());

        return map;
    }

    /**
     * 保存用户
     * @param user 用户信息
     */
    public void saveUser(User user) {
        // 对密码MD5加密
        user.setPassword(SecureUtil.md5(user.getPassword()));
        super.save(user);
        // 默认给一个普通管理员角色
        userRoleService.saveDefaultRoleByUserId(user.getId());
    }

    /**
     * 根据用户id删除
     * @param userId 用户id
     */
    public void removeByUserId(Long userId) {
        super.removeById(userId);
        userRoleService.removeByUserId(userId);

    }

    /**
     * 更新用户
     * @param user 用户信息
     */
    public void updateUserById(User user) {
        if (user.getPassword().length() != 32) {
            user.setPassword(SecureUtil.md5(user.getPassword()));
        }
        super.updateById(user);
    }
}
