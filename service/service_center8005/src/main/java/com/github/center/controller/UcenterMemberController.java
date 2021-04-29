package com.github.center.controller;


import com.github.center.entity.UcenterMember;
import com.github.center.service.UcenterMemberService;
import com.github.center.vo.UserRegister;
import com.github.utils.JwtUtils;
import com.github.utils.ResultCommon;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author HAN
 * @since 2021-04-15
 */
@RestController
@RequestMapping("/edu-center")
//@CrossOrigin
@Api("登陆注册功能")
public class UcenterMemberController {

    @Autowired
    private UcenterMemberService ucenterMemberService;

    /**
     * 前台登录
     * @param ucenterMember 登录信息
     */
    @PostMapping("login")
    @ApiOperation("前台登录")
    public ResultCommon loginUser(@RequestBody UcenterMember ucenterMember) {
        // 获取生成token值
        String token;
        try {
            token = ucenterMemberService.login(ucenterMember);
        } catch (Exception e) {
            return ResultCommon.fail().setMessage(e.getMessage());
        }

        return ResultCommon.success().setData("token", token);
    }


    /**
     * 注册账户
     * @param userRegister 注册信息
     */
    @PostMapping("register")
    @ApiOperation("注册账户")
    public ResultCommon registerUser(@RequestBody UserRegister userRegister) {

        return ucenterMemberService.register(userRegister);
    }

    /**
     * 根据token获取用户信息
     */
    @GetMapping("user-info")
    @ApiOperation("根据token获取用户信息")
    public ResultCommon InformationUser(HttpServletRequest request) {
        String id = JwtUtils.getUserIdByJwtToken(request);
        UcenterMember ucenterMember = ucenterMemberService.getById(id);

        return ResultCommon.success().setData("items", ucenterMember);
    }

    /**
     * 根据id获取用户信息
     * @param id 用户id
     */
    @GetMapping("user-info/{id}")
    @ApiOperation("根据id获取用户信息")
    public ResultCommon infoUserById(@PathVariable("id") String id) {
        UcenterMember ucenterMember = ucenterMemberService.getById(id);

        return ResultCommon.success().setData("items", ucenterMember);
    }

    /**
     * 统计某一天的注册人数
     * @param date 哪一天
     * @return 人数
     */
    @GetMapping("stat-register/{date}")
    @ApiOperation("统计某一天的注册人数")
    public int statRegister(@PathVariable("date") String date) {
        int count = ucenterMemberService.statRegister(date);

        return count;
    }

}

