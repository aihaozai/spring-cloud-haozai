package com.spring.cloud.auth.authorize.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author haozai
 * @description 授权权限
 * @date 2021/8/25 12:40
 */
@Data
@ApiModel(description = "授权权限")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthorizeDTO {

    @NotNull(message = "角色id不能为空！")
    @ApiModelProperty(value = "角色id",name = "roleId")
    private Long roleId;

    @ApiModelProperty(value = "菜单",name = "menuList")
    private List<Long> menuList;

    @ApiModelProperty(value = "权限",name = "authorityList")
    private List<Long> authorityList;
}
