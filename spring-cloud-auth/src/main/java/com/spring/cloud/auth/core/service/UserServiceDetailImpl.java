package com.spring.cloud.auth.core.service;

import com.spring.cloud.auth.system.role.service.IRolePermissionService;
import com.spring.cloud.auth.system.user.entity.User;
import com.spring.cloud.auth.system.user.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import spring.cloud.base.core.entity.AuthAuthority;
import spring.cloud.base.core.entity.AuthUser;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 * @author haozai
 * @description OAUTH2身份验证服务
 * @date 2021/3/24  22:40
 */
@Service
@RequiredArgsConstructor
public class UserServiceDetailImpl implements UserDetailsService {

    private final MessageSource message;

    private final IUserService userService;

    private final IRolePermissionService rolePermissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUserByUsername(username.trim());
        Objects.requireNonNull(user,this.message.getMessage("AuthenticationProvider.badUser",  null, Locale.getDefault()));
        List<AuthAuthority> authorityList = rolePermissionService.findAuthorityByUserId(user.getId());
        AuthUser authUser = new AuthUser(user.getUsername(), user.getPassword(), user.isEnabled(), user.isAccountNonExpired(), user.isCredentialsNonExpired(), user.isAccountNonLocked(), authorityList);
        authUser.setId(user.getId()).setAccount(user.getAccount());
        return authUser;
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
        String pwd = bcryptPasswordEncoder.encode("123456");
        System.out.println(pwd);
    }
}