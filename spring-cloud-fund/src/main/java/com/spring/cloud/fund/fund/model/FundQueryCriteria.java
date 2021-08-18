package com.spring.cloud.fund.fund.model;


import lombok.Data;
import spring.cloud.base.datasource.annotation.Query;

/**
 * @author haozai
 * @date 2021/7/28 14:54
 */
@Data
public class FundQueryCriteria {

    @Query(type = Query.Type.EQUAL)
    private String fundCode;
}
