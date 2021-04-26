package com.github.acl.service;

import com.alibaba.fastjson.JSONObject;
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
        map.put("permissionValues", permissionValues);
        return map;
    }

    /**
     * 获得可访问菜单列表
     *
     * @param username 用户名
     * @return 菜单列表
     */
    public List<JSONObject> getMenu(String username) {
        // 获得用户信息
        User user = userService.getByUsername(username);
        // 获得权限信息
        List<Role> roles = roleService.getRoleByUserId(user.getId());
        // 获得菜单列表
        List<Permission> permissions = permissionService.
                getPermissionsByRoleIds(roles.stream()
                        .map(Role::getId)
                        .collect(Collectors.toList()))
                .stream().filter(item -> item.getType() == 1) // 过滤出所有为菜单选项
                .collect(Collectors.toList());
        // 构建菜单
        buildMenu(permissions, null);
        // 构建菜单路由
        return buildMenuRouter(permissions);
    }

    private List<Permission> buildMenu(List<Permission> permissions, Permission permission) {
        return permissions.stream()
                .filter(item -> permission == null ? item.getId() == 0
                        : Objects.equals(item.getPid(), permission.getId()))
                .peek(item -> {
                    if (permission == null) {
                        item.setLevel(1);
                    } else {
                        item.setLevel(permission.getLevel() + 1);
                    }
                    item.setChildren(buildMenu(permissions, item));
                })
                .collect(Collectors.toList());
    }

    private List<JSONObject> buildMenuRouter(List<Permission> permissions) {
        List<JSONObject> menus = new ArrayList<>();
        // 如果不是1说明没有数据，也就是没有任何权限, 则直接返回
        if (permissions.size() == 1) {
            permissions.get(0).getChildren().forEach(levelOneData -> {
                // 第一层
                JSONObject levelOneMenu = new JSONObject();
                levelOneMenu.put("path", levelOneData.getPath());
                levelOneMenu.put("component", levelOneData.getComponent());
                levelOneMenu.put("redirect", "noredirect");
                levelOneMenu.put("name", "name_"+levelOneData.getId());
                levelOneMenu.put("hidden", false);

                JSONObject levelOneMeta = new JSONObject();
                levelOneMeta.put("title", levelOneData.getName());
                levelOneMeta.put("icon", levelOneData.getIcon());
                levelOneMenu.put("meta", levelOneMeta);

                // 第二层列表
                JSONObject levelTwoMenu = new JSONObject();
                levelOneData.getChildren().forEach(levelTwoData -> {
                    levelTwoMenu.put("path", levelTwoData.getPath());
                    levelTwoMenu.put("component", levelTwoData.getComponent());
                    levelTwoMenu.put("redirect", "noredirect");
                    levelTwoMenu.put("name", "name_"+levelTwoData.getId());
                    levelTwoMenu.put("hidden", false);

                    JSONObject levelTwoMeta = new JSONObject();
                    levelTwoMeta.put("title", levelTwoData.getName());
                    levelTwoMeta.put("icon", levelTwoData.getIcon());
                    levelTwoMenu.put("meta", levelTwoMeta);
                });
                // 放入返回数据中
                levelOneMenu.put("children", levelTwoMenu);
                menus.add(levelOneMenu);
            });
        }
        return menus;
    }
}
