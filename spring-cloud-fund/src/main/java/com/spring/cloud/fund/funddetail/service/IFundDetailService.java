package com.spring.cloud.fund.funddetail.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spring.cloud.fund.fund.entity.Fund;
import com.spring.cloud.fund.funddetail.entity.FundDetail;

import java.util.List;

/**
 * @author haozai
 * @date 2021/6/10  15:13
 */
public interface IFundDetailService extends IService<FundDetail> {

    /**
     * 搜索基金详细信息
     * @param fundList
     * @return
     */
    List<FundDetail> searchFundDetailData(List<Fund> fundList);

    /**
     * 批量插入
     * @param fundDetailList
     */
    void insertBatch(List<FundDetail> fundDetailList);

    /**
     * 基金订阅列表
     * @param page
     * @param predicate
     * @return
     */
    Page<FundDetail> subscribePage(Page page, QueryWrapper predicate);
}
