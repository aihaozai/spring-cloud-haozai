package com.spring.cloud.auth.entity;

import spring.cloud.base.core.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author haozai
 * @description 角色表
 * @date 2021/3/24  22:40
 */
@Data
@TableName("role")
public class Role extends BaseEntity implements GrantedAuthority {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
