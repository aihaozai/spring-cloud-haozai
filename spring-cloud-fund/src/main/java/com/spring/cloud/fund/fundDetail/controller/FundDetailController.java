package com.spring.cloud.fund.fundDetail.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spring.cloud.fund.fund.service.IFundService;
import com.spring.cloud.fund.fundDetail.entity.FundDetail;
import com.spring.cloud.fund.fundDetail.service.IFundDetailService;
import com.spring.cloud.fund.handler.SearchFundJobHandler;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import spring.cloud.base.core.result.Result;
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
    public Result<Page<FundDetail>> page(@RequestBody QueryPage queryPage) {
        Page<FundDetail> page = iFundDetailService.page(new Page<>(queryPage.getCurrent(), queryPage.getCurrent()),queryPage.getQueryWrapper());
        return Result.ok(page);
    }


    @GetMapping("/addFundDetailData")
    public Result addFundDetailData() throws Exception {
        searchFundJobHandler.searchFundDetailData();
        return Result.ok();
    }
}
