package com.spring.cloud.fund.fundReal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spring.cloud.fund.fundReal.entity.FundReal;

import java.util.List;

/**
 * @author haozai
 * @date 2021/5/28  15:13
 */
public interface IFundRealService  extends IService<FundReal> {
    /**
     * 批量插入
     * @param fundRealList
     */
    void insertBatch(List<FundReal> fundRealList);
}
