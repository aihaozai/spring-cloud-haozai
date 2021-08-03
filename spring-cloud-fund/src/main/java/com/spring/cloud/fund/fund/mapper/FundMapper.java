package com.spring.cloud.fund.fund.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.spring.cloud.fund.fund.entity.Fund;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author haozai
 * @date 2021/5/06  15:13
 */
public interface FundMapper extends BaseMapper<Fund> {

    /**
     * 自定义查找list
     * @param queryWrapper 查询构造器
     * @return T 返回数据类型
     */
    @ResultType(Fund.class)
    @Select(" select f.id, f.fund_code, f.fund_name, f.company_code, f.company_name " +
            " from fund f left join fund_subscribe s on f.fund_code = s.fund_code  ${ew.customSqlSegment} ")
    List<Fund> findList(@Param(Constants.WRAPPER) QueryWrapper queryWrapper);
}
