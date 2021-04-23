package com.github.security.config;

import com.github.security.filter.AuthFilter;
import com.github.security.filter.LoginFilter;
import com.github.security.handler.DefaultPasswordEncoder;
import com.github.security.handler.TokenLogoutHandler;
import com.github.security.handler.UnAuthHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author HAN
 * @version 1.0
 * @create 04-24-2:46
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private DefaultPasswordEncoder defaultPasswordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling()
                .authenticationEntryPoint(new UnAuthHandler()) // 无权访问设置
                .and() // 关闭csrf
                .csrf().disable()
                .authorizeRequests().anyRequest().authenticated()
                .and() // 设置登出
                .logout().logoutUrl("admin/acl/logout")
                .addLogoutHandler(new TokenLogoutHandler(redisTemplate))
                .and()// 不进行认证路径
                .authorizeRequests().antMatchers("/api/**").permitAll()
                .and() // 设置过滤器
                .addFilter(new LoginFilter(redisTemplate, authenticationManager()))
                .addFilter(new AuthFilter(authenticationManager(), redisTemplate))
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 设置密码处理器
        auth.userDetailsService(userDetailsService).passwordEncoder(defaultPasswordEncoder);
    }
}
