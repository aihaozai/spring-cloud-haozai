package com.spring.cloud.elasticsearch.searchFund.controller;

import com.alibaba.fastjson.JSONObject;
import com.spring.cloud.elasticsearch.handler.SearchFundJobHandler;
import com.spring.cloud.elasticsearch.searchFund.service.ISearchFundService;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.cloud.base.core.result.Result;
import spring.cloud.base.fund.client.FundClient;
import spring.cloud.base.fund.dto.FundDto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author haozai
 * @date 2021/5/06  15:13
 */
@Slf4j
@RequestMapping("/searchFund")
@RestController
@RefreshScope
@AllArgsConstructor
public class SearchFundController {

    @Value("${search.fundCode}")
    private ArrayList<String> fundCodeList;

    private final FundClient fundClient;

    private final ISearchFundService iSearchFundService;

    private final SearchFundJobHandler searchFundJobHandler;

    @GetMapping("/searchFundRealData")
    public Result searchFundRealData() throws IOException {
        searchFundJobHandler.fundRealDataJobHandler();
        return Result.ok();
    }

    @GetMapping("/createFundIndex")
    public Result createFundIndex(){
        searchFundJobHandler.createFundIndexJobHandler();
        return Result.ok();
    }

    @GetMapping("/getFundRealData")
    public Result getFundRealData() throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("fund_code@In",fundCodeList);
        List<FundDto> fundList = fundClient.selectFund(jsonObject).getData();
        iSearchFundService.getFundRealData(fundList);
        return Result.ok();
    }
}
