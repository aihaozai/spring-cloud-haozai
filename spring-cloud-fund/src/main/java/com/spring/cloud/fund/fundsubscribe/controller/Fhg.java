package com.spring.cloud.fund.fundsubscribe.controller;

import com.spring.cloud.fund.fundsubscribe.mapper.GoodMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author haozai
 * @description spring-cloud-haozai
 * @date 2021/8/4  23:15
 */
@RequestMapping("/f")
@RestController
public class Fhg {
    @Resource
    private  GoodMapper mapper;
}
