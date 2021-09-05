package com.spring.cloud.auth.role.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import spring.cloud.base.datasource.entity.BaseEntity;

/**
 * @author haozai
 * @description 角色表
 * @date 2021/8/24 22:40
 */

@Data
@TableName("role")
@ApiModel(description = "角色表")
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseEntity {

    @ApiModelProperty(value = "角色名称",name = "name")
    private String name;

    @ApiModelProperty(value = "角色编码",name = "code")
    private String code;

}
