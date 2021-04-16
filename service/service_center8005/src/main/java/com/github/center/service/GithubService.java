package com.github.center.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.center.mapper.GithubMapper;
import com.github.center.thirdparty.Github;
import org.springframework.stereotype.Service;

/**
 * @author HAN
 * @version 1.0
 * @create 04-16-22:06
 */
@Service
public class GithubService extends ServiceImpl<GithubMapper, Github> {
}
