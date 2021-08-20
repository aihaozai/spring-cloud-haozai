package com.spring.cloud.auth.menu.model;


import lombok.Data;
import spring.cloud.base.datasource.annotation.Query;

/**
 * @author haozai
 * @date 2021/8/18  14:31
 */
@Data
public class MenuQueryCriteria {

    @Query(type = Query.Type.EQUAL)
    private Long pid;
}
