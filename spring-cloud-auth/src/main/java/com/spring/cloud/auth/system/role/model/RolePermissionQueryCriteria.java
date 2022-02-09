package com.spring.cloud.auth.system.role.model;


import lombok.Data;
import spring.cloud.base.datasource.annotation.Query;

/**
 * @author haozai
 * @date 2021/9/12 8:58
 */
@Data
public class RolePermissionQueryCriteria {

    @Query(type = Query.Type.EQUAL)
    private String name;

    @Query(type = Query.Type.EQUAL)
    private String userId;
}
