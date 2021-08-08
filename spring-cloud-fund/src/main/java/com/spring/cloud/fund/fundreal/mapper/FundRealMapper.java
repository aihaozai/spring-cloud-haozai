package com.spring.cloud.fund.fundreal.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.spring.cloud.fund.fundreal.entity.FundReal;
import com.spring.cloud.fund.fundreal.model.FundRealVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author haozai
 * @date 2021/5/28  15:13
 */
@Repository
public interface FundRealMapper extends BaseMapper<FundReal> {
    /**
     * 批量插入
     * @param fundRealList 爬取数据结果
     */
    void insertBatch(List<FundReal> fundRealList);

    /**
     * 查询最新的基金日期
     * @return
     */
    String queryLastDate();

    /**
     * 删除某日期之前基金实时信息
     * @param delDate 日期
     */
    void deleteByDate(String delDate);

    /**
     * 添加索引
     */
    void addIndex();

    /**
     * 删除索引
     */
    void delIndex();

    /**
     * 基金基本信息
     * @param fundQueryWrapper 构造器
     * @return List<FundRealVO>
     */
    @Select(" select fund_code, fund_name from fund  ${ew.customSqlSegment} ")
    List<FundRealVO> fundList(@Param(Constants.WRAPPER) QueryWrapper fundQueryWrapper);
}
