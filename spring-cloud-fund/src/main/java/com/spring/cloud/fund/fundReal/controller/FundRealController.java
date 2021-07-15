package com.spring.cloud.fund.fundReal.controller;

import com.spring.cloud.fund.fundReal.service.IFundRealService;
import com.spring.cloud.fund.handler.SearchFundJobHandler;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.cloud.base.core.result.Result;


/**
 * @author haozai
 * @date 2021/5/28  15:13
 */
@Slf4j
@Api("基金实时控制器")
@RequestMapping("/fundReal")
@RestController
@AllArgsConstructor
public class FundRealController{

    private final SearchFundJobHandler searchFundJobHandler;

    private final IFundRealService iFundRealService;

    @GetMapping("/addFundRealData")
    public Result addFundRealData() throws Exception {
        searchFundJobHandler.searchFundRealData();
        return Result.ok();
    }

    @GetMapping("/getFundRealData")
    public Result getFundRealData(){
        return Result.ok(iFundRealService.getFundRealData());
    }
}
