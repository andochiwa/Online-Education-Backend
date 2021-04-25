package com.github.acl.controller;


import com.github.acl.entity.Role;
import com.github.acl.service.RolePermissionService;
import com.github.acl.service.RoleService;
import com.github.utils.ResultCommon;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author HAN
 * @since 2021-04-22
 */
@RestController
@RequestMapping("/acl/role")
@Api("角色控制")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RolePermissionService rolePermissionService;

    /**
     * 分页条件查询获取所有角色
     * @param current 第几条开始
     * @param limit 要几条
     * @param name 查询名字
     */
    @GetMapping("{current}/{limit}")
    @ApiOperation("分页条件查询获取所有角色")
    public ResultCommon getPageConditionList(@PathVariable("current") long current,
                                             @PathVariable("limit") long limit,
                                             @RequestParam(required = false) String name) {
        Map<String, Object> map = roleService.getPageConditionList(current, limit, name);
        return ResultCommon.success().setData(map);
    }

    /**
     * 保存角色
     * @param role 角色对象
     */
    @PostMapping
    @ApiOperation("保存角色")
    public ResultCommon saveRole(@RequestBody Role role) {
        roleService.save(role);
        return ResultCommon.success();
    }

    /**
     * 删除角色以及关联表的id
     * @param roleId 角色id
     */
    @DeleteMapping("{id}")
    @ApiOperation("删除角色")
    public ResultCommon removeRole(@PathVariable("id") Long roleId) {
        roleService.deleteById(roleId);
        return ResultCommon.success();
    }

    /**
     * 更新角色
     * @param role 角色对象
     */
    @PutMapping
    @ApiOperation("更新角色")
    public ResultCommon updateRole(@RequestBody Role role) {
        roleService.saveOrUpdate(role);
        return ResultCommon.success();
    }

    /**
     * 获取权限列表的id
     * @param roleId 角色id
     */
    @GetMapping("auth/{id}")
    @ApiOperation("获取权限列表的id")
    public ResultCommon getPermissionIdByRoleId(@PathVariable("id") Long roleId) {
        List<String> permissionIds = rolePermissionService.getPermissionIdByRoleId(roleId);
        return ResultCommon.success().setData("items", permissionIds);
    }

    /**
     * 保存或删除权限列表
     * @param roleId 角色id
     * @param oldPermissionIds 已有权限id
     * @param newPermissionIds 更新后的权限id
     */
    @PostMapping("auth/{id}")
    @ApiOperation("保存或删除权限列表")
    public ResultCommon removeOrSavePermission(@PathVariable("id") Long roleId,
                                               @RequestParam(required = false) Set<Long> oldPermissionIds,
                                               @RequestParam(required = false) Set<Long> newPermissionIds) {
        rolePermissionService.removeOrSavePermission(roleId, oldPermissionIds, newPermissionIds);
        return ResultCommon.success();
    }

    /**
     * 查询所有角色
     */
    @GetMapping("user")
    @ApiOperation("查询所有角色的id和name")
    public ResultCommon getAllRole() {
        List<Role> role = roleService.getAllRole();
        return ResultCommon.success().setData("items", role);
    }

    /**
     * 根据用户id查询角色
     * @param id 用户id
     */
    @GetMapping("user/{userId}")
    @ApiOperation("根据用户id查询角色")
    public ResultCommon getRoleByUserId(@PathVariable("userId") Long id) {
        List<Role> role = roleService.getRoleByUserId(id);
        return ResultCommon.success().setData("items", role);
    }

}

