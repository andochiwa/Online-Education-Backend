package com.github.acl.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.acl.entity.Permission;
import com.github.acl.entity.RolePermission;
import com.github.acl.mapper.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 权限 服务实现类
 * </p>
 *
 * @author HAN
 * @since 2021-04-22
 */
@Service
@Transactional
public class PermissionService extends ServiceImpl<PermissionMapper, Permission> {

    @Autowired
    private RolePermissionService rolePermissionService;

    /**
     * 查询所有菜单
     *
     * @return 带层次的菜单
     */
    public List<Permission> getList() {
        List<Permission> permissions = super.list();
        return permissions.stream()
                .filter(item -> item.getId() == 1)
                .peek(item -> {
                    item.setLevel(0);
                    item.setChildren(recursionHelper(item, permissions));
                })
                .collect(Collectors.toList());

    }

    /**
     * 查询菜单递归用方法
     */
    private List<Permission> recursionHelper(Permission permission, List<Permission> permissions) {
        return permissions.stream()
                .filter(item -> Objects.equals(permission.getId(), item.getPid()))
                .peek(item -> {
                    item.setLevel(permission.getLevel() + 1);
                    item.setChildren(recursionHelper(item, permissions));
                })
                .collect(Collectors.toList());
    }

    /**
     * 递归删除菜单
     * @param id 菜单id
     */
    public void deleteList(Long id) {
        super.removeById(id);
        QueryWrapper<Permission> wrapper = new QueryWrapper<>();

        deleteHelper(id, wrapper);
    }

    /**
     * 递归删除菜单用方法
     * @param pid 菜单pid
     * @param wrapper 删除条件
     */
    private void deleteHelper(Long pid, QueryWrapper<Permission> wrapper) {
        wrapper.clear();
        wrapper.eq("pid", pid).select("id");

        // 先查出所有pid的id
        List<Long> ids = super.list(wrapper).stream()
                .map(Permission::getId)
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(ids)) {
            // 适当剪枝
            return;
        }
        // 删除
        super.removeByIds(ids);
        // 递归删除
        ids.forEach(item -> deleteHelper(item, wrapper));
    }

    /**
     * 给角色分配权限
     * @param roleId 角色id
     * @param permissionIds 菜单id
     */
    public void saveRolePermission(Long roleId, Long[] permissionIds) {
        // 获取RolePermission对象
        List<RolePermission> rolePermissions = Arrays.stream(permissionIds)
                .map(permissionId -> {
                    RolePermission rolePermission = new RolePermission();
                    rolePermission.setPermissionId(permissionId);
                    rolePermission.setRoleId(roleId);
                    return rolePermission;
                })
                .collect(Collectors.toList());
        rolePermissionService.saveBatch(rolePermissions);
    }

    /**
     * 根据用户id查询权限
     * @param id 用户id
     * @return 权限列表
     */
    public List<String> getPermissionByUserId(Long id) {
        return super.baseMapper.getPermissionByUserId(id);
    }


    /**
     * 根据roleId获取权限值
     * @param roleIds 角色id列表
     * @return 所有权限值
     */
    public List<Permission> getPermissionsByRoleIds(List<Long> roleIds) {
        // 获取权限id
        List<Long> permissionIds = rolePermissionService.getPermissionIdByRoleIds(roleIds);
        // 根据权限id查询权限值
        QueryWrapper<Permission> wrapper = new QueryWrapper<>();
        wrapper.in("id", permissionIds)
                .select("permission_value", "id", "pid", "type", "path", "component", "icon", "status", "name");
        return super.list(wrapper);
    }

    /**
     * 角色id获取权限列表
     * @param roleId 角色id
     * @return 权限列表
     */
    public List<Permission> getPermissionsByRoleId(Long roleId) {
        // 获取权限id
        List<Long> permissionIds = rolePermissionService.getPermissionIdByRoleId(roleId);
        return CollectionUtils.isEmpty(permissionIds) ? null : super.listByIds(permissionIds);
    }
}
