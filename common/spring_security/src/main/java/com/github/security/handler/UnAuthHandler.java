package com.github.security.handler;

import com.github.utils.ResponseUtil;
import com.github.utils.ResultCommon;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 无权限访问
 * @author HAN
 * @version 1.0
 * @create 04-24-0:55
 */
@Component
public class UnAuthHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ResponseUtil.out(response, ResultCommon.fail());
    }
}
