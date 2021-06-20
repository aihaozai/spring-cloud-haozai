package com.spring.cloud.auth.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.spring.cloud.auth.core.entity.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity implements Serializable{
    private static final long serialVersionUID = 1L;

    private String id;

    private String username;

    private String password;

    @TableField(exist = false)
    private List<Role> authorities;
}
