package com.github.acl.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.acl.entity.RolePermission;
import com.github.acl.mapper.RolePermissionMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    /**
     * 根据角色id获取权限列表id
     * @param roleId 角色id
     * @return 所有权限列表id的list
     */
    public List<String> getPermissionIdByRoleId(Long roleId) {
        QueryWrapper<RolePermission> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id", roleId).select("permission_id");
        return super.list(wrapper).stream()
                .map(item -> String.valueOf(item.getPermissionId()))
                .collect(Collectors.toList());
    }

    /**
     * 保存或删除权限列表
     * @param roleId 角色id
     * @param oldPermissionIds 已有权限id
     * @param newPermissionIds 更新后的权限id
     */
    public void removeOrSavePermission(Long roleId, Set<Long> oldPermissionIds, Set<Long> newPermissionIds) {
        // 如果旧数据为空直接保存新数据
        if (CollectionUtils.isEmpty(oldPermissionIds)) {
            if (!CollectionUtils.isEmpty(newPermissionIds)) {
                super.saveBatch(newPermissionIds.parallelStream()
                        .map(item -> {
                            RolePermission rolePermission = new RolePermission();
                            rolePermission.setRoleId(roleId);
                            rolePermission.setPermissionId(item);
                            return rolePermission;
                        })
                        .collect(Collectors.toList()));
            }
            return;
        }
        // 如果新数据为空直接删除所有旧数据
        if (CollectionUtils.isEmpty(newPermissionIds)) {
            if (!CollectionUtils.isEmpty(oldPermissionIds)) {
                QueryWrapper<RolePermission> wrapper = new QueryWrapper<>();
                wrapper.eq("role_id", roleId);
                super.remove(wrapper);
            }
            return;
        }

        // 删除数据
        List<Long> removeList = oldPermissionIds.stream()
                .filter(item -> !newPermissionIds.contains(item)) // 如果新数据中有id就不需要删除
                .collect(Collectors.toList());
        removeList.forEach(item -> {
            QueryWrapper<RolePermission> wrapper = new QueryWrapper<>();
            wrapper.eq("role_id", roleId).eq("permission_id", item);
            super.remove(wrapper);
        });

        // 新增数据
        List<RolePermission> saveList = newPermissionIds.stream()
                .filter(item -> !oldPermissionIds.contains(item)) // 如果旧数据中有key就不需要新增
                .map(item -> {
                    RolePermission rolePermission = new RolePermission();
                    rolePermission.setRoleId(roleId);
                    rolePermission.setPermissionId(item);
                    return rolePermission;
                })
                .collect(Collectors.toList());
        super.saveBatch(saveList);
    }
}
