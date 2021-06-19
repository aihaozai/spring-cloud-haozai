package com.spring.cloud.auth.core.service;

import com.spring.cloud.auth.user.entity.User;
import com.spring.cloud.auth.user.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;
import java.util.Objects;

/**
 * @author haozai
 * @description OAUTH2身份验证服务
 * @date 2021/3/24  22:40
 */
@Service
@AllArgsConstructor
public class UserServiceDetailImpl implements UserDetailsService {

    private final MessageSource message;

    private final IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUserByUsername(username.trim());
        Objects.requireNonNull(user,this.message.getMessage("AuthenticationProvider.badUser",  null, Locale.getDefault()));
        //DaoAuthenticationProvider
        //            Collection<GrantedAuthority> authorities = new HashSet<>();
//            authorities.add(new SimpleGrantedAuthority("admin"));
        return user;
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
        String pwd = bcryptPasswordEncoder.encode("123456");
        System.out.println(pwd);
    }
}