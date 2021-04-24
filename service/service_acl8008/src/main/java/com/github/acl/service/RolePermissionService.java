package com.github.acl.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.acl.entity.RolePermission;
import com.github.acl.mapper.RolePermissionMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色权限 服务实现类
 * </p>
 *
 * @author HAN
 * @since 2021-04-22
 */
@Service
public class RolePermissionService extends ServiceImpl<RolePermissionMapper, RolePermission> {

    /**
     * 根据角色id删除关联数据
     * @param roleId 角色id
     */
    public void removeByRoleId(Long roleId) {
        QueryWrapper<RolePermission> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id", roleId);
        super.remove(wrapper);
    }
}
