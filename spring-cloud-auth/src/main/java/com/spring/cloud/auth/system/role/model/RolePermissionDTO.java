package com.spring.cloud.auth.system.role.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotNull;

/**
 * @author haozai
 * @description 角色权限
 * @date 2021/9/12 8:58
 */
@Data
@ApiModel(description = "角色权限")
@JsonIgnoreProperties(ignoreUnknown = true)
public class RolePermissionDTO {

    private Long id;


    @NotNull(message = "用户id不能为空！")
    @ApiModelProperty(value = "用户id",name = "userId")
    private Long userId;

    @NotNull(message = "角色id不能为空！")
    @ApiModelProperty(value = "角色id",name = "roleId")
    private Long roleId;
}
