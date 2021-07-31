package com.spring.cloud.fund.fundsubscribe.controller;

import com.spring.cloud.fund.fundsubscribe.service.IFundSubscribeService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @PutMapping("/subscribe")
    public void subscribe(@RequestParam String fundCode) {
        fundSubscribeService.subscribe(fundCode);
    }
}
