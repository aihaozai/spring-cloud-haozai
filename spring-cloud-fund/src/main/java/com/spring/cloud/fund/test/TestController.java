package com.spring.cloud.fund.test;

import com.spring.cloud.fund.mq.client.StreamClient;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author haozai
 * @description spring-cloud-haozai
 * @date 2021/9/25  18:58
 */
@RequestMapping("/test")
@RestController
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;
    private final StreamClient streamClient;

    @GetMapping("/test1")
    public String test1(){
        System.out.println(111);
        testService.test1();
        return "dfdsfsda";
    }

    @GetMapping("/test2")
    public String test2(){
//        System.out.println(2222);
//        testService.test2();
        streamClient.delayOutput().send(MessageBuilder.withPayload("Hello World...").build());

        return "dfdsfsda";
    }
}
