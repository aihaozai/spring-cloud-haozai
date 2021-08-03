package com.spring.cloud.fund.fund.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spring.cloud.fund.fund.entity.Fund;
import com.spring.cloud.fund.fund.mapper.FundMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author haozai
 * @date 2021/5/06  15:13
 */
@Slf4j
@Service
@AllArgsConstructor
public class FundServiceImpl extends ServiceImpl<FundMapper, Fund> implements IFundService {

    private final FundMapper fundMapper;

    @Override
    public List<Fund> findList(QueryWrapper queryWrapper) {
        return fundMapper.findList(queryWrapper);
    }
}
