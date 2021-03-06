package com.spring.cloud.auth.system.authority.model;


import lombok.Data;
import spring.cloud.base.datasource.annotation.Query;

/**
 * @author haozai
 * @date 2021/8/25 12:40
 */
@Data
public class AuthorityQueryCriteria {

    @Query(type = Query.Type.EQUAL)
    private String name;

    @Query(type = Query.Type.EQUAL)
    private String menuId;
}
