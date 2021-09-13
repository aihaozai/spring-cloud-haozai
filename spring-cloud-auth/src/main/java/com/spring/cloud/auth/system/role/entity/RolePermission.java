package com.spring.cloud.auth.system.role.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import spring.cloud.base.datasource.entity.BaseEntity;

/**
 * @author haozai
 * @description 角色表
 * @date 2021/9/8 22:40
 */

@Data
@TableName("role_permission")
@ApiModel(description = "角色权限表")
@EqualsAndHashCode(callSuper = true)
public class RolePermission extends BaseEntity {

    @ApiModelProperty(value = "用户id",name = "userId")
    private Long userId;

    @ApiModelProperty(value = "角色id",name = "roleId")
    private Long roleId;

}
