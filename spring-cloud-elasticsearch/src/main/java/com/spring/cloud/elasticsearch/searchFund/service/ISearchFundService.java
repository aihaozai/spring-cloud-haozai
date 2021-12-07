package com.spring.cloud.elasticsearch.searchFund.service;

import java.io.IOException;
import java.util.List;

/**
 * @author haozai
 * @date 2021/5/06  15:13
 */
public interface ISearchFundService{



    /**
     * 创建基金索引
     * @param fundCodeList
     */
    void createFundIndex(List<String> fundCodeList);

    /**
     * 获取基金实时数据
     * @param fundCodeList
     */
    void getFundRealData(List<String> fundCodeList) throws IOException;
}
