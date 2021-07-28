package com.spring.cloud.fund.funddetail.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spring.cloud.fund.funddetail.entity.FundDetail;
import com.spring.cloud.fund.funddetail.service.IFundDetailService;
import com.spring.cloud.fund.handler.SearchFundJobHandler;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import spring.cloud.base.datasource.request.QueryPage;


/**
 * @author haozai
 * @date 2021/6/10  15:13
 */
@Slf4j
@Api("基金详细控制器")
@RequestMapping("/fundDetail")
@RestController
@AllArgsConstructor
public class FundDetailController {

    private final IFundDetailService iFundDetailService;

    private final SearchFundJobHandler searchFundJobHandler;

    @PostMapping("/page")
    public Page<FundDetail> page(@RequestBody QueryPage queryPage) {
        Page<FundDetail> page = iFundDetailService.page(new Page<>(queryPage.getCurrent(), queryPage.getCurrent()),queryPage.getQueryWrapper());
        return page;
    }

    @GetMapping("/addFundDetailData")
    public void addFundDetailData() {
        searchFundJobHandler.searchFundDetailData();
    }
}
