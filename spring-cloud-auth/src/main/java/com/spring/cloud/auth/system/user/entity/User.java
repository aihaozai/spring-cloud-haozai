package com.spring.cloud.auth.system.user.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.spring.cloud.auth.system.role.entity.Role;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import spring.cloud.base.datasource.entity.BaseEntity;

import java.util.Date;
import java.util.List;

/**
 * @author haozai
 * @description 用户表
 * @date 2021/3/24  22:40
 */
@Data
@TableName("user")
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {

    @ApiModelProperty(value = "账号",name = "account")
    private String account;

    @ApiModelProperty(value = "密码",name = "password")
    private String password;

    @ApiModelProperty(value = "用户名称",name = "username")
    private String username;

    @ApiModelProperty(value = "账号没有过期",name = "accountNonExpired")
    private boolean accountNonExpired;

    @ApiModelProperty(value = "账号没有锁定",name = "accountNonLocked")
    private boolean accountNonLocked;

    @ApiModelProperty(value = "证书没有过期",name = "credentialsNonExpired")
    private boolean credentialsNonExpired;

    @ApiModelProperty(value = "是否开启",name = "enabled")
    private boolean enabled;

    @ApiModelProperty(value = "头像",name = "avatar")
    private String avatar;

    @ApiModelProperty(value = "创建时间",name = "createTime")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ApiModelProperty(value = "更新时间",name = "account")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @TableField(exist = false)
    private List<Role> authorities;
}
