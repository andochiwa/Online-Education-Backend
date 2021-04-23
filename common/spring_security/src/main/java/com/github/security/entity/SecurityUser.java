package com.github.security.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author HAN
 * @version 1.0
 * @create 04-24-1:25
 */
@Data
public class SecurityUser implements UserDetails {

    private User user;

    // 当前权限
    private List<String> permissionList;

    public SecurityUser() {
    }

    public SecurityUser(User user, List<String> permissionList) {
        this.user = user;
        this.permissionList = permissionList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        permissionList.forEach(item -> {
            if (ObjectUtils.isEmpty(item)) {
                return;
            }
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(item);
            grantedAuthorities.add(authority);

        });
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
