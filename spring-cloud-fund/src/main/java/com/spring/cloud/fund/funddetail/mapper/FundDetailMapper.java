package com.spring.cloud.fund.funddetail.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spring.cloud.fund.funddetail.entity.FundDetail;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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

    /**
     * 基金订阅列表
     * @param page
     * @param userId
     * @param predicate
     * @return
     */
    @Select(" select f.id, f.fund_code, f.fund_name, f.fund_type, f.company_code, f.company_name, f.one_n, f.six_y, f.three_y, f.one_y, s.subscribeId " +
            " from fund_detail f left join ( select id as subscribeId, fund_code from fund_subscribe where user_id =#{userId} ) s on f.fund_code = s.fund_code  ${ew.customSqlSegment} ")
    Page<FundDetail> subscribePage(Page page, Long userId, @Param(Constants.WRAPPER) QueryWrapper predicate);
}
