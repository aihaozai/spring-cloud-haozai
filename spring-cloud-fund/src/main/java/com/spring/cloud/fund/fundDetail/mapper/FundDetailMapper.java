package com.spring.cloud.fund.fundDetail.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spring.cloud.fund.fundDetail.entity.FundDetail;
import java.util.List;

/**
 * @author haozai
 * @date 2021/6/10  15:13
 */
public interface FundDetailMapper extends BaseMapper<FundDetail> {

    /**
     * 批量插入
     * @param fundDetailList
     */
    void insertBatch(List<FundDetail> fundDetailList);

}
