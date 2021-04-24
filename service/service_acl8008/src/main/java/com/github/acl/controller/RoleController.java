package com.github.acl.controller;


import com.github.acl.entity.Role;
import com.github.acl.service.RoleService;
import com.github.utils.ResultCommon;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
                                             String name) {
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
     * 更细角色
     * @param role 角色对象
     */
    @PutMapping
    @ApiOperation("更新角色")
    public ResultCommon updateRole(@RequestBody Role role) {
        roleService.saveOrUpdate(role);
        return ResultCommon.success();
    }

}

