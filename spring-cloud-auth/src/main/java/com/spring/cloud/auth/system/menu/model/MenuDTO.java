package com.spring.cloud.auth.system.menu.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author haozai
 * @description 菜单
 * @date 2021/8/19 16:25
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MenuDTO {

    private Long id;

    @NotNull(message = "上级菜单不能为空！")
    @ApiModelProperty(value = "上级菜单id",name = "pid")
    private Long pid;

    @NotBlank(message = "菜单名称不能为空！")
    @Length(max = 16 ,message = "长度应在1-16位之间")
    @ApiModelProperty(value = "菜单名称",name = "name")
    private String name;

    @Length(max = 10 ,message = "长度应在0-10位之间")
    @ApiModelProperty(value = "访问菜单路径",name = "path")
    private String path;

    @Length(max = 60 ,message = "长度应在0-60位之间")
    @ApiModelProperty(value = "菜单部件",name = "component")
    private String component;

    @Length(max = 15 ,message = "长度应在0-15位之间")
    @ApiModelProperty(value = "菜单图标",name = "icon")
    private String icon;

    @Max(value = 9999 ,message = "不能大于9999")
    @ApiModelProperty(value = "排序",name = "sort",example = "1")
    private Integer sort;
}
