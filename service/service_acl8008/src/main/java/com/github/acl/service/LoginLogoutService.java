package com.github.acl.service;

import com.github.acl.entity.Permission;
import com.github.acl.entity.Role;
import com.github.security.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author HAN
 * @version 1.0
 * @create 04-26-16:17
 */
@Service
@Transactional
public class LoginLogoutService {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 获取user数据
     *
     * @param username 用户名
     * @return 封装好的user数据
     */
    public Map<String, Object> getUserInfo(String username) {
        HashMap<String, Object> map = new HashMap<>();
        List<String> permissionValues = null;
        // 获取用户信息
        User user = userService.getByUsername(username);
        // 获取权限列表
        List<Role> roles = roleService.getRoleByUserId(user.getId());
        List<String> roleNames = roles.stream()
                .map(Role::getRoleName)
                .collect(Collectors.toList());
        // 如果为空，返回一个空值，否则前端报错
        if (CollectionUtils.isEmpty(roles)) {
            roleNames.add("");
        } else {
            // 获取权限操作值
            permissionValues = permissionService
                    .getPermissionsByRoleIds(roles.stream()
                            .map(Role::getId)
                            .collect(Collectors.toList()))
                    .stream()
                    .filter(item -> item.getType() == 2) // 过滤出所有为按钮的数据
                    .map(Permission::getPermissionValue)
                    .collect(Collectors.toList());
        }
        redisTemplate.opsForValue().set(username, permissionValues);

        map.put("username", user.getUsername());
        map.put("roleNames", roleNames);
        map.put("avatar", user.getAvatar());
        map.put("permissionValues", permissionValues);
        return map;
    }

    /**
     * 获得可访问菜单列表
     *
     * @param username 用户名
     * @return 菜单列表
     */
    public Map<String, Object> getMenu(String username) {
        // 获得用户信息
        User user = userService.getByUsername(username);
        // 获得权限信息
        List<Role> roles = roleService.getRoleByUserId(user.getId());
        // 获得菜单列表,
        List<Permission> permissions = permissionService.
                getPermissionsByRoleIds(roles.stream()
                        .map(Role::getId)
                        .collect(Collectors.toList()));
        // 把name作为key返回给前端判断
        HashMap<String, Object> map = new HashMap<>();
        permissions.forEach(item -> map.put(item.getName(), item.getPath()));
        // 放入
        return map;
    }
}

