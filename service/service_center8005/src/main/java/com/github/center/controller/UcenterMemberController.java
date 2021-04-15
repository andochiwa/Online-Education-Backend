package com.github.center.controller;


import com.github.center.entity.UcenterMember;
import com.github.center.service.UcenterMemberService;
import com.github.utils.ResultCommon;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@CrossOrigin
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
        String token = ucenterMemberService.login(ucenterMember);

        return ResultCommon.success().setData("token", token);
    }


    // 注册

}

