package com.github.acl.security;

import com.github.acl.service.PermissionService;
import com.github.acl.service.UserService;
import com.github.security.entity.SecurityUser;
import com.github.security.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author HAN
 * @version 1.0
 * @create 04-24-3:30
 */
@Component("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据用户名查询数据
        User user = userService.getByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        // 根据用户id查询权限表
        List<String> permissionList = permissionService.getPermissionByUserId(user.getId());
        return new SecurityUser(user, permissionList);
    }
}
