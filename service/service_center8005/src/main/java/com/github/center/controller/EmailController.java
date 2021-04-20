package com.github.center.controller;

import com.github.center.service.EmailService;
import com.github.utils.ResultCommon;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/4/15
 */
@RestController
@RequestMapping("/edu-center/email")
//@CrossOrigin
@Api("发送邮箱")
public class EmailController {

    @Autowired
    private EmailService emailService;

    /**
     * 发送邮箱
     * @param email 邮箱地址
     * @return 返回成功与否信息
     */
    @GetMapping("{email}")
    @ApiOperation("发送邮箱")
    public ResultCommon sendEmail(@PathVariable("email") String email) {
        return emailService.sendEmail(email);
    }
}
