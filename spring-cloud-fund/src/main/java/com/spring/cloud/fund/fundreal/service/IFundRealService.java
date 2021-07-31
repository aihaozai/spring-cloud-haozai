package com.spring.cloud.fund.fundreal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spring.cloud.fund.fund.entity.Fund;
import com.spring.cloud.fund.fundreal.entity.FundReal;
import java.util.List;

/**
 * @author haozai
 * @date 2021/5/28  15:13
 */
public interface IFundRealService  extends IService<FundReal> {
    /**
     * 批量插入
     * @param fundRealList 实时数据
     */
    void insertBatch(List<FundReal> fundRealList);

    /**
     * 查询最新的基金日期
     * @return string
     */
    String queryLastDate();

    /**
     * 获取实时基金数据
     * @return List<Fund>
     */
    List<Fund> getFundRealData();

    /**
     * 删除某日期之前基金实时信息
     * @param delDate 日期
     */
    void deleteByDate(String delDate);
}
