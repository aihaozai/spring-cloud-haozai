package com.spring.cloud.auth.menu.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import spring.cloud.base.datasource.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;

/**
 * @author haozai
 * @description 菜单表
 * @date 2021/8/18 14:25
 */
@Data
@TableName("user")
@EqualsAndHashCode(callSuper = true)
public class Menu extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "上级菜单id",name = "pid")
    private Long pid;

    @ApiModelProperty(value = "菜单id",name = "menuId")
    private String menuId;

    @ApiModelProperty(value = "菜单名称",name = "menuName")
    private String menuName;

    @ApiModelProperty(value = "访问菜单路径",name = "menuUrl")
    private String menuUrl;

    @ApiModelProperty(value = "菜单图标",name = "menuIcon")
    private String menuIcon;

    @ApiModelProperty(value = "排序",name = "sort",example = "1")
    private Integer sort;

    @ApiModelProperty(value = "创建时间",name = "createTime")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ApiModelProperty(value = "更新时间",name = "updateTime")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
