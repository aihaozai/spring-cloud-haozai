package com.spring.cloud.auth.menu.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author haozai
 * @description 菜单
 * @date 2021/8/19 16:25
 */
@Data
public class MenuDTO {

    private Long id;

    @NotNull(message = "上级菜单不能为空！")
    @Max(value = 9999 ,message = "不能大于9999")
    @ApiModelProperty(value = "上级菜单id",name = "pid")
    private Long pid;

    @NotBlank(message = "菜单名称不能为空！")
    @Length(min=1 ,max= 16 ,message = "长度应在1-16位之间")
    @ApiModelProperty(value = "菜单名称",name = "menuName")
    private String menuName;

    @Length(max= 30 ,message = "长度应在0-30位之间")
    @ApiModelProperty(value = "访问菜单路径",name = "menuUrl")
    private String menuUrl;

    @Length(max= 15 ,message = "长度应在0-15位之间")
    @ApiModelProperty(value = "菜单图标",name = "menuIcon")
    private String menuIcon;

    @Max(value= 9999 ,message = "不能大于9999")
    @ApiModelProperty(value = "排序",name = "sort",example = "1")
    private Integer sort;
}
