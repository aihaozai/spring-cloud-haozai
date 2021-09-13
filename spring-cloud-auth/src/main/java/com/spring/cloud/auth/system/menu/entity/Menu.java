package com.spring.cloud.auth.system.menu.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import spring.cloud.base.datasource.entity.BaseEntity;

import java.util.Date;

/**
 * @author haozai
 * @description 菜单表
 * @date 2021/8/18 14:25
 */
@Data
@TableName("menu")
@EqualsAndHashCode(callSuper = true)
public class Menu extends BaseEntity {

    @ApiModelProperty(value = "上级菜单id",name = "pid")
    private Long pid;

    @ApiModelProperty(value = "菜单名称",name = "name")
    private String name;

    @ApiModelProperty(value = "访问菜单路径",name = "path")
    private String path;

    @ApiModelProperty(value = "菜单部件",name = "component")
    private String component;

    @ApiModelProperty(value = "菜单图标",name = "icon")
    private String icon;

    @ApiModelProperty(value = "排序",name = "sort")
    private Integer sort;

    @ApiModelProperty(value = "创建时间",name = "createTime")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间",name = "updateTime")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "树形表格专用",name = "key")
    @TableField(exist = false)
    private Long key;
}
