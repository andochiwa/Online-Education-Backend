package com.github.acl.controller;


import com.github.acl.entity.Permission;
import com.github.acl.service.PermissionService;
import com.github.utils.ResultCommon;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 权限 前端控制器
 * </p>
 *
 * @author HAN
 * @since 2021-04-22
 */
@RestController
@RequestMapping("/acl/permission")
@Api("权限控制")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    /**
     * 查询所有分层菜单
     */
    @GetMapping
    @ApiOperation("查询所有分层菜单")
    public ResultCommon getListPermission() {

        List<Permission> list = permissionService.getList();

        return ResultCommon.success().setData("list", list);
    }

    /**
     * 删除当前以及子级菜单
     * @param id 菜单id
     */
    @DeleteMapping("{id}")
    @ApiOperation("删除当前以及子级菜单")
    public ResultCommon deleteListPermission(@PathVariable("id") Long id) {
        try {
            permissionService.deleteList(id);
        } catch (Exception e) {
            return ResultCommon.fail().setMessage("未知错误");
        }

        return ResultCommon.success();
    }

    /**
     * 给角色分配权限
     * @param roleId 角色id
     * @param permissionIds 菜单id
     */
    @PostMapping("auth")
    @ApiOperation("给角色分配权限")
    public ResultCommon saveRolePermission(Long roleId, Long[] permissionIds) {
        permissionService.saveRolePermission(roleId, permissionIds);

        return ResultCommon.success();
    }

}

