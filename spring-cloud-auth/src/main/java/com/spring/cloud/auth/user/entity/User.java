package com.spring.cloud.auth.user.entity;

import com.spring.cloud.auth.entity.Role;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;
import spring.cloud.base.datasource.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * @author haozai
 * @description 用户表
 * @date 2021/3/24  22:40
 */
@Data
@TableName("user")
public class User extends BaseEntity implements UserDetails, Serializable{
    private static final long serialVersionUID = 1L;

    private String id;

    private String username;

    private String password;

    private List<Role> authorities;

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
