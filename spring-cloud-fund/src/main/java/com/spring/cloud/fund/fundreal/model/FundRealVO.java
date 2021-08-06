package com.spring.cloud.fund.fundreal.model;

import com.spring.cloud.fund.fundreal.entity.FundReal;
import lombok.Data;

import java.util.List;

/**
 * @author haozai
 * @date 2021/8/6
 */
@Data
public class FundRealVO {

    private String fundCode;

    private String fundName;

    private List<FundReal> fundRealList;

    private List<String> yAxis;
}
