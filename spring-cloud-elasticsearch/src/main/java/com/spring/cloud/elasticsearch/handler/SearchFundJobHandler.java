package com.spring.cloud.elasticsearch.handler;

import com.alibaba.fastjson.JSONObject;
import com.spring.cloud.elasticsearch.searchFund.service.ISearchFundService;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import spring.cloud.base.fund.client.FundClient;
import spring.cloud.base.fund.dto.FundDto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author haozai
 * @description 基金爬取定时器
 * @date 2021/5/21  17:11
 */
@AllArgsConstructor
@Component
public class SearchFundJobHandler {
    @Value("${search.fundCode}")
    private ArrayList<String> fundCodeList;

    @Autowired
    private  FundClient fundClient;

    @Autowired
    private  ISearchFundService iSearchFundService;

    @XxlJob("fundRealDataJobHandler")
    public void fundRealDataJobHandler() throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("fund_code@In",fundCodeList);
        List<FundDto> fundList = fundClient.selectFund(jsonObject).getData();
        iSearchFundService.searchFundRealData(fundList);
    }

    @XxlJob("createFundIndexJobHandler")
    public void createFundIndexJobHandler(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("fund_code@In",fundCodeList);
        List<FundDto> fundList = fundClient.selectFund(jsonObject).getData();
        iSearchFundService.createFundIndex(fundList);
    }
}
