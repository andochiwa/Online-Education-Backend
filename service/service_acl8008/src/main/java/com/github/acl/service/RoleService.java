package com.github.acl.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.acl.entity.Role;
import com.github.acl.entity.UserRole;
import com.github.acl.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author HAN
 * @since 2021-04-22
 */
@Service
@Transactional
public class RoleService extends ServiceImpl<RoleMapper, Role> {

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private UserRoleService userRoleService;

    /**
     * 分页条件查询获取所有角色
     * @param current 第几条开始
     * @param limit 要几条
     * @param name 查询名字
     * @return 查询分页好的对象和数量
     */
    public Map<String, Object> getPageConditionList(long current, long limit, String name) {
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        if (StringUtils.hasText(name)) {
            wrapper.like("role_name", name);
        }
        Page<Role> page = new Page<>(current, limit);
        super.page(page, wrapper);

        List<Role> roles = page.getRecords();
        long total = page.getTotal();
        HashMap<String, Object> map = new HashMap<>();
        map.put("items", roles);
        map.put("total", total);
        return map;
    }

    /**
     * 删除角色以及关联表的id
     * @param roleId 角色id
     */
    public void deleteById(Long roleId) {
        super.removeById(roleId);
        rolePermissionService.removeByRoleId(roleId);
    }

    /**
     * 查询所有角色的id的角色名
     */
    public List<Role> getAllRole() {
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        wrapper.select("id", "role_name");
        return super.list(wrapper);
    }

    /**
     * 根据用户id查询角色
     * @param userId 用户id
     * @return 角色列表
     */
    public List<Role> getRoleByUserId(Long userId) {
        // 获取角色id
        List<UserRole> userRoles = userRoleService.getRoleId(userId);
        // 查询相应角色
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        wrapper.in("id", userRoles.stream()
                .map(UserRole::getRoleId)
                .collect(Collectors.toList()));
        return super.list(wrapper);
    }
}
