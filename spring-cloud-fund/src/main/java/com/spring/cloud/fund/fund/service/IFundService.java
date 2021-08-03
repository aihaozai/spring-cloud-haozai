package com.spring.cloud.fund.fund.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spring.cloud.fund.fund.entity.Fund;

import java.util.List;
import java.util.Map;

/**
 * @author haozai
 * @date 2021/5/06  15:13
 */
public interface IFundService extends IService<Fund> {

    /**
     * 自定义查找list
     * @param queryWrapper 查询构造器
     * @return  返回数据类型
     */
    List<Fund> findList(QueryWrapper queryWrapper);
}
