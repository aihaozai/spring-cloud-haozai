package com.spring.cloud.fund.handler;

import com.spring.cloud.fund.fund.entity.Fund;
import com.spring.cloud.fund.fund.service.IFundService;
import com.spring.cloud.fund.funddetail.entity.FundDetail;
import com.spring.cloud.fund.funddetail.service.IFundDetailService;
import com.spring.cloud.fund.fundreal.entity.FundReal;
import com.spring.cloud.fund.fundreal.service.IFundRealService;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;
import spring.cloud.base.fund.service.IBaseSearchFundService;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author haozai
 * @description 基金爬取定时器
 * @date 2021/5/21  17:11
 */
@Slf4j
@AllArgsConstructor
@Component
public class SearchFundJobHandler {

    private final IFundService iFundService;

    private final IFundRealService iFundRealService;

    private final IBaseSearchFundService baseSearchFundService;

    private final IFundDetailService iFundDetailService;

    /**
     * 爬取实时数据
     * @throws IOException
     */
    @XxlJob("searchFundRealData")
    public void searchFundRealData(){
        List<String> fundList = iFundService.list().stream().map(Fund::getFundCode).collect(Collectors.toList());
        List<FundReal> searchResult = baseSearchFundService.searchFundRealData(fundList, FundReal.class);
        long begin=System.currentTimeMillis();
        searchResult = searchResult.stream().filter(ObjectUtils::isNotEmpty).collect(Collectors.toList());
        iFundRealService.insertBatch(searchResult);
        long end=System.currentTimeMillis();
        log.info("基金实时数据更新完毕，耗时：{}",end-begin);
    }

    /**
     * 爬取基金详细信息
     */
    @XxlJob("searchFundDetailData")
    public void searchFundDetailData() {
        List<Fund> fundList = iFundService.list();
        List<FundDetail> fundDetailList = iFundDetailService.searchFundDetailData(fundList);
        long begin=System.currentTimeMillis();
        iFundDetailService.insertBatch(fundDetailList);
        long end=System.currentTimeMillis();
        log.info("基金详细数据更新完毕，耗时：{}",end-begin);
    }

    /**
     * 删除某日期之前基金实时信息
     */
    @XxlJob("deleteFundDetailData")
    public void deleteFundDetailData() {
        String delDate = DateUtil.format(DateUtil.addDays(new Date(),-3),"yyyy-MM-dd");
        iFundRealService.deleteByDate(delDate);
    }
}
