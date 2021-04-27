package com.github.acl.controller;


import com.github.acl.service.UserRoleService;
import com.github.acl.service.UserService;
import com.github.security.entity.User;
import com.github.utils.ResultCommon;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author HAN
 * @since 2021-04-22
 */
@RestController
@RequestMapping("/acl/user")
@Api("用户管理")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    /**
     * 分页条件查询用户信息
     * @param current 第几条开始
     * @param limit 要几条
     * @param name 条件查询用户名
     */
    @GetMapping("{current}/{limit}")
    @ApiOperation("分页条件查询用户信息")
    public ResultCommon getPageConditionList(@PathVariable("current") Long current,
                                             @PathVariable("limit") Long limit,
                                             @RequestParam(required = false) String name) {
        Map<String, Object> map = userService.getPageConditionList(current, limit, name);
        return ResultCommon.success().setData(map);
    }

    /**
     * 保存用户
     * @param user 用户信息
     */
    @PostMapping
    @ApiOperation("保存用户")
    public ResultCommon saveUser(@RequestBody User user) {
        userService.saveUser(user);
        return ResultCommon.success();
    }

    /**
     * 更新用户
     * @param user 用户信息
     */
    @PutMapping
    @ApiOperation("更新用户")
    public ResultCommon updateUser(@RequestBody User user) {
        userService.updateUserById(user);
        return ResultCommon.success();
    }

    /**
     * 根据id删除用户
     * @param userId 用户id
     */
    @DeleteMapping("{id}")
    @ApiOperation("根据id删除用户")
    public ResultCommon deleteUser(@PathVariable("id") Long userId) {
        userService.removeByUserId(userId);
        return ResultCommon.success();
    }

    /**
     * 保存或删除用户的角色
     * @param id 用户id
     * @param oldRoleIds 角色旧id
     * @param newRoleIds 角色新id
     */
    @PostMapping("role/{userId}")
    @ApiOperation("保存或删除用户的角色")
    public ResultCommon saveOrRemoveRole(@PathVariable("userId") Long id,
                                 @RequestParam(required = false) Set<Long> oldRoleIds,
                                 @RequestParam(required = false) Set<Long> newRoleIds) {
        userRoleService.saveOrRemoveRole(id, oldRoleIds, newRoleIds);
        return ResultCommon.success();
    }

}

