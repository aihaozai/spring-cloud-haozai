package com.spring.cloud.auth.user.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.spring.cloud.auth.core.entity.Role;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import spring.cloud.base.datasource.entity.BaseEntity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author haozai
 * @description 用户
 * @date 2021/8/17  9:38
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserVO extends BaseEntity implements Serializable{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "账号",name = "account")
    private String account;


    @ApiModelProperty(value = "用户名称",name = "username")
    private String username;

    @ApiModelProperty(value = "账号没有过期",name = "accountNonExpired")
    private Boolean accountNonExpired;

    @ApiModelProperty(value = "账号没有锁定",name = "accountNonLocked")
    private Boolean accountNonLocked;

    @ApiModelProperty(value = "证书没有过期",name = "credentialsNonExpired")
    private Boolean credentialsNonExpired;

    @ApiModelProperty(value = "是否开启",name = "enabled")
    private Boolean enabled;

    @ApiModelProperty(value = "头像",name = "avatar")
    private String avatar;

    @ApiModelProperty(value = "创建时间",name = "createTime")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ApiModelProperty(value = "更新时间",name = "account")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
