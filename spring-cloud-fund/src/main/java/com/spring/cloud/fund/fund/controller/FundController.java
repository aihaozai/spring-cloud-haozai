package com.spring.cloud.fund.fund.controller;

import com.spring.cloud.fund.fund.service.IFundService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


/**
 * @author haozai
 * @date 2021/5/06  15:13
 */
@Api("基金公司控制器")
@RequestMapping("/fund")
@RestController
@AllArgsConstructor
public class FundController{

    private final IFundService iFundService;

    @GetMapping("/te")
    public void te() throws IOException {


    }

}
