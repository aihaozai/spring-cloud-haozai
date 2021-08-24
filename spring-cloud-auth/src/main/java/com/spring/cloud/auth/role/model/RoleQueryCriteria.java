package com.spring.cloud.auth.role.model;


import lombok.Data;
import spring.cloud.base.datasource.annotation.Query;

/**
 * @author haozai
 * @date 2021/8/24 22:40
 */
@Data
public class RoleQueryCriteria {

    @Query(type = Query.Type.EQUAL)
    private String name;
}
