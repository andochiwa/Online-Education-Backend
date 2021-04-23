package com.github.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.security.entity.SecurityUser;
import com.github.security.entity.User;
import com.github.utils.JwtUtils;
import com.github.utils.ResponseUtil;
import com.github.utils.ResultCommon;
import lombok.SneakyThrows;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author HAN
 * @version 1.0
 * @create 04-24-0:58
 */
public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    private RedisTemplate<String, Object> redisTemplate;

    private AuthenticationManager authenticationManager;

    public LoginFilter(RedisTemplate<String, Object> redisTemplate, AuthenticationManager authenticationManager) {
        this.redisTemplate = redisTemplate;
        this.authenticationManager = authenticationManager;
        super.setPostOnly(false);
        super.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/admin/acl/login"));
    }

    // 获取表单提交的用户名与密码
    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        // 获取表单提交的数据
        User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),
                user.getPassword(),
                new ArrayList<>()));
    }

    // 认证成功调用
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {
        // 认证成功后获取用户信息
        SecurityUser user = (SecurityUser) authResult.getPrincipal();
        // 生成token
        String token = JwtUtils.getJwtToken(user.getUser().getUsername(), user.getUser().getPassword());
        // 把用户名和权限列表放入redis
        redisTemplate.opsForValue().set(user.getUser().getUsername(), user.getPermissionList());
        // 返回token
        ResponseUtil.out(response, ResultCommon.success().setData("token", token));
    }

    // 认证失败调用
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        ResponseUtil.out(response, ResultCommon.fail());
    }
}
