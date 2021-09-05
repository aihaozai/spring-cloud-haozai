package com.spring.cloud.auth.authority.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import spring.cloud.base.datasource.entity.BaseEntity;

/**
 * @author haozai
 * @description 操作权限表
 * @date 2021/8/25 12:40
 */

@Data
@TableName("authority")
@ApiModel(description = "操作权限表")
@EqualsAndHashCode(callSuper = true)
public class Authority extends BaseEntity implements GrantedAuthority {

    @ApiModelProperty(value = "菜单id",name = "menuId")
    private Long menuId;

    @ApiModelProperty(value = "权限名称",name = "name")
    private String name;

    @ApiModelProperty(value = "权限编码",name = "code")
    private String code;

    @Override
    public String getAuthority() {
        return code;
    }
}
