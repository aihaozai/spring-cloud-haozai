package com.spring.cloud.elasticsearch.searchFund.controller;

import com.spring.cloud.elasticsearch.handler.SearchFundJobHandler;
import com.spring.cloud.elasticsearch.searchFund.service.ISearchFundService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.cloud.base.core.result.Result;

import java.io.IOException;
import java.util.ArrayList;


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
        iSearchFundService.getFundRealData(fundCodeList);
        return Result.ok();
    }
}
