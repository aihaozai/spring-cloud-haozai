package com.spring.cloud.fund.fundsubscribe.controller;

import com.spring.cloud.fund.fundsubscribe.service.IFundSubscribeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author haozai
 * @date 2021/7/31  15:13
 */
@Slf4j
@Api("基金订阅控制器")
@RequestMapping("/fundSubscribe")
@RestController
@AllArgsConstructor
public class FundSubscribeController{

    private final IFundSubscribeService fundSubscribeService;

    @ApiOperation("订阅基金")
    @ApiParam(required = true, name = "基金代码")
    @PutMapping("/subscribe")
    public void subscribe(@RequestParam String fundCode) {
        fundSubscribeService.subscribe(fundCode);
    }

    @ApiOperation("取消订阅基金")
    @ApiParam(required = true, name = "订阅id")
    @DeleteMapping("/unSubscribe")
    public void unSubscribe(@RequestParam String subscribeId) {
        fundSubscribeService.removeById(subscribeId);
    }

}
