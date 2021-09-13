package com.spring.cloud.auth.system.authorize.model;


import lombok.Data;
import spring.cloud.base.datasource.annotation.Query;

/**
 * @author haozai
 * @date 2021/9/6 23:36
 */
@Data
public class AuthorizeQueryCriteria {

    @Query(type = Query.Type.EQUAL)
    private Long roleId;
}
