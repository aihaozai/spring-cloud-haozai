package com.spring.cloud.elasticsearch.searchFund.service;

import spring.cloud.base.fund.dto.FundDto;

import java.io.IOException;
import java.util.List;

/**
 * @author haozai
 * @date 2021/5/06  15:13
 */
public interface ISearchFundService{


    /**
     * 爬取实时基金
     * @param fundList
     * @throws IOException
     */
    void searchFundRealData(List<FundDto> fundList) throws IOException;

    /**
     * 创建基金索引
     * @param fundList
     */
    void createFundIndex(List<FundDto> fundList);
}
