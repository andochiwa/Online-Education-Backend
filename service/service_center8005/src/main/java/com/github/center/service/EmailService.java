package com.github.center.service;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.center.entity.UcenterMember;
import com.github.center.mapper.EmailMapper;
import com.github.center.thirdparty.Email;
import com.github.utils.ResultCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/4/15
 */
@Service
public class EmailService extends ServiceImpl<EmailMapper, Email> {

    @Autowired
    private UcenterMemberService ucenterMemberService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private MailSender mailSender;

    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;

    @Autowired
    private JavaMailSenderImpl sender;

    @PostConstruct
    public void init() {
        QueryWrapper<Email> wrapper = new QueryWrapper<>();
        wrapper.eq("name", "email");
        Email email = super.getOne(wrapper);
        sender.setPassword(email.getPassword());
        sender.setUsername(email.getUsername());
    }

    /**
     * 发送邮箱
     * @param email 邮箱地址
     */
    public ResultCommon sendEmail(String email) {
        // 判断邮箱是否已存在
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("email", email);
        if (ucenterMemberService.count(wrapper) > 0) {
            return ResultCommon.fail().setMessage("邮箱已存在");
        }

        // 生成随机验证码
        String code = RandomUtil.randomNumbers(6);
        // 发送邮箱
        threadPoolExecutor.submit(() -> {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(email);
            msg.setSubject("Verification code");
            msg.setText("Your Verification code is: " + code);
            // 存入redis，五分钟有效
            redisTemplate.opsForValue().set(email, code, 5, TimeUnit.MINUTES);
            mailSender.send(msg);
        });
        return ResultCommon.success().setData("code", code);
    }
}
