package com.github.acl.service;

import com.github.acl.entity.User;
import com.github.acl.mapper.UserMapper;
import com.github.acl.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author HAN
 * @since 2021-04-22
 */
@Service
public class UserService extends ServiceImpl<UserMapper, User> {

}
