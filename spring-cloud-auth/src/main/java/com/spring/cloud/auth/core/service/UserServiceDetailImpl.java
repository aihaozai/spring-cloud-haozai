package com.spring.cloud.auth.core.service;

import com.spring.cloud.auth.user.entity.User;
import com.spring.cloud.auth.user.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import spring.cloud.base.core.entity.AuthUser;
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
        return new AuthUser(user.getUsername(),user.getPassword(),AuthorityUtils.commaSeparatedStringToAuthorityList("ADMIN"));
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
        String pwd = bcryptPasswordEncoder.encode("123456");
        System.out.println(pwd);
    }
}