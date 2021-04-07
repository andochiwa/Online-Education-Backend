package com.github.eduservice.controller;

import com.github.utils.ResultCommon;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/4/6
 */
@RestController
@RequestMapping("/eduservice/user")
public class EduLoginController {

    /**
     * login
     */
    @PostMapping("login")
    public ResultCommon login() {
        return ResultCommon.success().setData("token", "admin");
    }

    /**
     * info
     */
    @GetMapping("info")
    public ResultCommon info() {
        return ResultCommon.success().setData("name", "admin").setData("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

}
