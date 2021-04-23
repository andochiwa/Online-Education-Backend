package com.github.security.filter;

import com.github.utils.JwtUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.ObjectUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author HAN
 * @version 1.0
 * @create 04-24-2:18
 */
public class AuthFilter extends BasicAuthenticationFilter {

    private RedisTemplate<String, Object> redisTemplate;

    public AuthFilter(AuthenticationManager authenticationManager, RedisTemplate<String, Object> redisTemplate) {
        super(authenticationManager);
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 获得当前认证成功用户权限信息
        UsernamePasswordAuthenticationToken authRequest = getAuthenticationToken(request);
        if (authRequest != null) {
            SecurityContextHolder.getContext().setAuthentication(authRequest);
        }
        // 跳转页面||到下一个链
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request) {
        // 从token中获取用户名
        String username = JwtUtils.getMemberIdByJwtToken(request);
        if (username != null) {
            // 从redis中获取对应的权限列表
            @SuppressWarnings("unchecked")
            List<String> permissions = (List<String>) redisTemplate.opsForValue().get(username);
            ArrayList<GrantedAuthority> authorities = new ArrayList<>();

            permissions.forEach(item -> {
                if (ObjectUtils.isEmpty(item)) {
                    return;
                }
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(item);
                authorities.add(authority);
            });


            return new UsernamePasswordAuthenticationToken(username, request.getHeader("token"), authorities);
        }
        return null;
    }
}
