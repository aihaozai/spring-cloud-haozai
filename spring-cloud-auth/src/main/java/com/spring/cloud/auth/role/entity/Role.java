package com.spring.cloud.auth.role.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import spring.cloud.base.datasource.entity.BaseEntity;

/**
 * @author haozai
 * @description 角色表
 * @date 2021/8/24 22:40
 */
@Data
@TableName("role")
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseEntity implements GrantedAuthority {

    private static final long serialVersionUID = 1L;

    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
