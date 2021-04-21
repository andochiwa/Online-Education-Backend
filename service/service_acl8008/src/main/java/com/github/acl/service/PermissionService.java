package com.github.acl.service;

import com.github.acl.entity.Permission;
import com.github.acl.mapper.PermissionMapper;
import com.github.acl.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限 服务实现类
 * </p>
 *
 * @author HAN
 * @since 2021-04-22
 */
@Service
public class PermissionService extends ServiceImpl<PermissionMapper, Permission> {

}
