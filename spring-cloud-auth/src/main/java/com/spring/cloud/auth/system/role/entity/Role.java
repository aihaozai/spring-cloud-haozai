package com.spring.cloud.auth.system.role.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
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
public class Role extends BaseEntity implements GrantedAuthority {

    @ApiModelProperty(value = "角色名称",name = "name")
    private String name;

    @ApiModelProperty(value = "角色编码",name = "code")
    private String code;

    @Override
    public String getAuthority() {
        return "ROLE" + code;
    }
}
