package com.github.acl.controller;

import com.github.acl.service.LoginLogoutService;
import com.github.utils.ResultCommon;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author HAN
 * @version 1.0
 * @create 04-26-16:13
 */
@RestController
@RequestMapping("acl/index")
@Api("security控制器")
public class LoginLogoutController {

    @Autowired
    private LoginLogoutService loginLogoutService;

    /**
     * 获取user数据
     */
    @GetMapping("info")
    @ApiOperation("获取用户数据")
    public ResultCommon getUserInfo() {
        // 获取登陆的用户
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Map<String, Object> map = loginLogoutService.getUserInfo(username);
        return ResultCommon.success().setData(map);
    }

    /**
     * 获得可访问菜单列表
     */
    @GetMapping("menu")
    @ApiOperation("获得菜单列表")
    public ResultCommon getMenu() {
        // 获取登陆的用户
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Map<String, Object> permissions = loginLogoutService.getMenu(username);
        System.out.println(permissions);
        return ResultCommon.success().setData("items", permissions);
    }

    /**
     * 登出
     */
    @GetMapping("logout")
    @ApiOperation("登出")
    public ResultCommon logout() {
        return ResultCommon.success();
    }
}
