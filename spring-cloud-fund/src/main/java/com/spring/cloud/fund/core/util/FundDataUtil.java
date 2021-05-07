package com.spring.cloud.fund.core.util;

import com.alibaba.fastjson.JSONObject;
import com.spring.cloud.fund.core.dto.FundDataDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

/**
 * @author haozai
 * @date 2021/5/7 13:59
 */
@Slf4j
public class FundDataUtil {

    /**
     * 基金
     */
    private final static String FUND_URL = "http://fundgz.1234567.com.cn/js/%s.js";

    private final static RestTemplate restTemplate = new RestTemplate();

    public static FundDataDto getFundList(String fundCode){
        restTemplate.getMessageConverters().set(1,new StringHttpMessageConverter(StandardCharsets.UTF_8));
        ResponseEntity<String> response = restTemplate.getForEntity(String.format(FUND_URL,fundCode), String.class );
        String data = response.getBody().replace("jsonpgz(","").replace(");","");
        System.out.println(data);
        FundDataDto fundDataDto = JSONObject.parseObject(data,FundDataDto.class);
        return fundDataDto;

    }

    public static void main(String[] args) {
        String[] arr = new String[]{"519674","005693","320007","008087","001717"};
        for (String code :arr){
            getFundList(code);
        }
    }
}
