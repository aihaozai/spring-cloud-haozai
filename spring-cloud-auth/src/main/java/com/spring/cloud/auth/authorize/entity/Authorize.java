package com.spring.cloud.auth.authorize.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import spring.cloud.base.datasource.entity.BaseEntity;

/**
 * @author haozai
 * @description 授权权限表
 * @date 2021/9/5 19:29
 */
@Data
@Builder
@TableName("authorize")
@ApiModel(description = "授权权限表")
@EqualsAndHashCode(callSuper = true)
public class Authorize extends BaseEntity {

    @ApiModelProperty(value = "授权类型",name = "type")
    private String type;

    @ApiModelProperty(value = "类型id",name = "typeId")
    private Long typeId;

    @ApiModelProperty(value = "角色id",name = "roleId")
    private Long roleId;

}
