package com.github.security.handler;

import com.github.utils.JwtUtils;
import com.github.utils.ResponseUtil;
import com.github.utils.ResultCommon;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author HAN
 * @version 1.0
 * @create 04-24-0:17
 */
public class TokenLogoutHandler implements LogoutHandler {

    private RedisTemplate<String, Object> redisTemplate;

    public TokenLogoutHandler(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        // 获取会员名
        String token = JwtUtils.getMemberIdByJwtToken(request);
        if (!ObjectUtils.isEmpty(token)) {
            // 这里也可以移除token，不过交给前端了
            // 从redis中移除token
            redisTemplate.delete(token);
        }
        // 返回信息
        ResponseUtil.out(response, ResultCommon.success());
    }
}
