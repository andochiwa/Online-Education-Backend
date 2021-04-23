package com.github.security.handler;

import cn.hutool.crypto.SecureUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author HAN
 * @version 1.0
 * @create 04-24-0:05
 */
@Component
public class DefaultPasswordEncoder implements PasswordEncoder {
    public DefaultPasswordEncoder() {
        this(-1);
    }

    public DefaultPasswordEncoder(int length) {

    }

    /**
     * 对密码进行md5加密
     * @param rawPassword 密码
     * @return 加密后的密码
     */
    @Override
    public String encode(CharSequence rawPassword) {
        return SecureUtil.md5(rawPassword.toString());
    }

    /**
     * 进行密码比对
     * @param rawPassword 传入的密码
     * @param encodedPassword 加密后的密码
     * @return true 密码一致
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return Objects.equals(SecureUtil.md5(rawPassword.toString()), encodedPassword);
    }
}
