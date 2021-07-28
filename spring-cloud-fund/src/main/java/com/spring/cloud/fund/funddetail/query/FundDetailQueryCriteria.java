package com.spring.cloud.fund.funddetail.query;


import lombok.Data;
import spring.cloud.base.datasource.annotation.Query;

/**
 * @author haozai
 * @date 2021/7/28 14:54
 */
@Data
public class FundDetailQueryCriteria {

    @Query(type = Query.Type.EQUAL)
    private String fundCode;
}
