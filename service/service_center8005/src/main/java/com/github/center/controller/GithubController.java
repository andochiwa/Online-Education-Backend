package com.github.center.controller;

import cn.hutool.core.util.IdUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.center.entity.UcenterMember;
import com.github.center.service.GithubService;
import com.github.center.service.UcenterMemberService;
import com.github.center.thirdparty.Github;
import com.github.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @author HAN
 * @version 1.0
 * @create 04-16-21:57
 */
@Controller
//@CrossOrigin
@RequestMapping("/login/github")
public class GithubController {

    @Autowired
    private GithubService githubService;

    @Autowired
    private UcenterMemberService ucenterMemberService;

    /**
     * 登入github准备登陆
     */
    @GetMapping
    public String loginUser() {
        String baseUrl = "https://github.com/login/oauth/authorize" +
                "?client_id=%s" +
                "&state=%s";

        // 从数据库中获取clientId等信息
        QueryWrapper<Github> wrapper = new QueryWrapper<>();
        wrapper.eq("name", "github");
        Github github = githubService.getOne(wrapper);

        String url = String.format(baseUrl,
                github.getId(),
                IdUtil.simpleUUID().substring(0, 10));
        return "redirect:" + url;
    }

    /**
     * 成功后的回调函数
     */
    @GetMapping("callback")
    public String callback(String code) {
        String baseTokenUrl = "https://github.com/login/oauth/access_token" +
                "?client_id=%s" +
                "&client_secret=%s" +
                "&code=%s";

        // 从数据库中获取clientId等信息
        QueryWrapper<Github> wrapper = new QueryWrapper<>();
        wrapper.eq("name", "github");
        Github github = githubService.getOne(wrapper);

        String accessTokenUrl = String.format(baseTokenUrl,
                github.getId(),
                github.getSecret(),
                code);
        // 发送http请求
        String tokenInfo = HttpUtil.get(accessTokenUrl);
        // 解析字符串, 因为只需要token所以只拿第一个参数
        String accessToken = tokenInfo.split("&")[0].split("=")[1];
        // 再次发送http请求获得用户信息
        String userString = HttpRequest.get("https://api.github.com/user")
                .header("Authorization", "token " + accessToken)
                .execute().body();
        Map<String, Object> userMap = JSONObject.parseObject(userString);
        // 存入数据库
        UcenterMember ucenterMember = ucenterMemberService.saveUser(userMap);

        String token = JwtUtils.getJwtToken(ucenterMember.getId(), ucenterMember.getNickname());
        return "redirect:http://andochiwa.top:3000?token=" + token;
    }
}
