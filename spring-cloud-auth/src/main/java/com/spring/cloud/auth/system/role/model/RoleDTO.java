package com.spring.cloud.auth.system.role.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author haozai
 * @description 角色
 * @date 2021/8/24 22:40
 */
@Data
@ApiModel(description = "角色")
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleDTO {

    private Long id;

    @NotBlank(message = "角色名称不能为空！")
    @Length(max = 32 ,message = "长度应在1-32位之间")
    @ApiModelProperty(value = "角色名称",name = "name")
    private String name;

    @NotBlank(message = "角色编码不能为空！")
    @Length(max = 15 ,message = "长度应在1-16位之间")
    @ApiModelProperty(value = "角色编码",name = "code")
    private String code;
}
