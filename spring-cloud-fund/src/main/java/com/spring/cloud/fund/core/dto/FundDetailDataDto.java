package com.spring.cloud.fund.core.dto;

import com.alibaba.fastjson.JSONArray;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author haozai
 * @date 2021/5/7 14:58
 * @description 基金详细数据
 */
@Data
@Accessors(chain = true)
public class FundDetailDataDto {

    /**
     * 基金名称
     */
    private String fS_name;

    /**
     * 基金编号
     */
    private String fS_code;

    /**
     * 原费率
     */
    private String fund_sourceRate;

    /**
     * 现费率
     */
    private String fund_Rate;

    /**
     * 最小申购金额
     */
    private String fund_minsg;

    /**
     * 收益率
     */
    private String zqCodesNew;

    /**
     *近一年收益率
     */
    private String syl_1n;

    /**
     *近6月收益率
     */
    private String syl_6y;

    /**
     *近三月收益率
     */
    private String syl_3y;

    /**
     *近一月收益率
     */
    private String syl_1y;

    /**
     * 单位净值走势
     */
    private JSONArray data_netWorthTrend;

    /**
     * 累计净值走势
     */
    private JSONArray data_ACWorthTrend;
}
