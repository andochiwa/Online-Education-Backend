package com.github.acl.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.acl.entity.UserRole;
import com.github.acl.mapper.UserRoleMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;
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
public class UserRoleService extends ServiceImpl<UserRoleMapper, UserRole> {

    /**
     * 通过用户id获取角色id
     * @param userId 用户id
     * @return 包含了角色id的列表
     */
    public List<UserRole> getRoleId(Long userId) {
        QueryWrapper<UserRole> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId).select("role_id");
        return super.list(wrapper);
    }

    /**
     * 保存或删除用户的角色
     * @param userId 用户id
     * @param oldRoleIds 角色旧id
     * @param newRoleIds 角色新id
     */
    public void saveOrRemoveRole(Long userId, Set<Long> oldRoleIds, Set<Long> newRoleIds) {
        // 如果旧数据为空，直接存入新数据
        if (CollectionUtils.isEmpty(oldRoleIds)) {
            if (!CollectionUtils.isEmpty(newRoleIds)) {
                List<UserRole> userRoles = newRoleIds.parallelStream()
                        .map(item -> {
                            UserRole userRole = new UserRole();
                            userRole.setUserId(userId);
                            userRole.setRoleId(item);
                            return userRole;
                        })
                        .collect(Collectors.toList());
                super.saveBatch(userRoles);
            }
            return;
        }
        // 如果新数据为空，则直接删除所有用户的角色
        if (CollectionUtils.isEmpty(newRoleIds)) {
            if (!CollectionUtils.isEmpty(oldRoleIds)) {
                QueryWrapper<UserRole> wrapper = new QueryWrapper<>();
                wrapper.eq("user_id", userId);
                super.remove(wrapper);
            }
            return;
        }

        // 删除数据
        oldRoleIds.stream()
                .filter(item -> !newRoleIds.contains(item))
                .forEach(item -> {
                    QueryWrapper<UserRole> wrapper = new QueryWrapper<>();
                    wrapper.eq("user_id", userId).eq("role_id", item);
                    super.remove(wrapper);
                });
        // 添加数据
        List<UserRole> userRoles = newRoleIds.stream()
                .filter(item -> !oldRoleIds.contains(item))
                .map(item -> {
                    UserRole userRole = new UserRole();
                    userRole.setUserId(userId);
                    userRole.setRoleId(item);
                    return userRole;
                })
                .collect(Collectors.toList());
        super.saveBatch(userRoles);
    }

    public void removeByUserId(Long userId) {
        QueryWrapper<UserRole> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        super.remove(wrapper);
    }
}
