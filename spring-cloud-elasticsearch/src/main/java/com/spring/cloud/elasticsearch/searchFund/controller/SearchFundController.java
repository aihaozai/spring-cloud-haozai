package com.spring.cloud.elasticsearch.searchFund.controller;

import com.spring.cloud.elasticsearch.searchFund.service.ISearchFundService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.cloud.base.core.result.Result;
import spring.cloud.base.fund.client.FundClient;
import spring.cloud.base.fund.dto.FundDto;

import java.io.IOException;
import java.util.List;


/**
 * @author haozai
 * @date 2021/5/06  15:13
 */
@Slf4j
@RequestMapping("/searchFund")
@RestController
@AllArgsConstructor
public class SearchFundController {

    @Value("${search.fundCode}")
    private List<String> fundCodeList;

    private final FundClient fundClient;

    private final ISearchFundService iSearchFundService;

    @GetMapping("/searchFundRealData")
    public Result searchFundRealData() throws IOException {
        List<FundDto> fundList = fundClient.selectFund().getData();
        iSearchFundService.searchFundRealData(fundList);
        return Result.ok();
    }

    @GetMapping("/createFundIndex")
    public Result createFundIndex(){
        List<FundDto> fundList = fundClient.selectFund().getData();
        iSearchFundService.createFundIndex(fundList);
        return Result.ok();
    }

}
