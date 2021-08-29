package com.spring.cloud.auth.authority.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author haozai
 * @description 菜单
 * @date 2021/8/25 12:40
 */
@Data
@ApiModel(description = "操作权限表")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthorityDTO {

    private Long id;

    @NotNull(message = "菜单id不能为空！")
    @ApiModelProperty(value = "菜单id",name = "menuId")
    private Long menuId;

    @NotBlank(message = "权限名称不能为空！")
    @Length(max = 32 ,message = "长度应在1-32位之间")
    @ApiModelProperty(value = "权限名称",name = "name")
    private String name;

    @NotBlank(message = "权限编码不能为空！")
    @Length(max = 15 ,message = "长度应在1-16位之间")
    @ApiModelProperty(value = "权限编码",name = "code")
    private String code;
}
