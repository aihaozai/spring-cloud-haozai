package com.spring.cloud.auth.test;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: springCloud
 * @author: haozai
 * @date: 2020-04-12 14:11
 **/
@Api("testController")
@RefreshScope
@RequestMapping("/res")
@RestController
@RequiredArgsConstructor
public class TestController {
    private final StringEncryptor encryptor;

    private final TestService testService;
    @Value("${me}")
    private String me;
    @ApiOperation(value = "me", httpMethod = "GET")
    @GetMapping("/me")
    public String me(){
        return this.me;
    }

    @GetMapping("/encryptor")
    public String encryptor(String str){
        return encryptor.encrypt(str);
    }

    public static void main(String[] args) {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword("spring-cloud-haozai");
        config.setAlgorithm("PBEWITHHMACSHA512ANDAES_128");
        config.setPoolSize("1");
        encryptor.setConfig(config);
        System.out.println(encryptor.encrypt("cloud_tx666!"));
    }

    @GetMapping("/test1")
    public void test1(){
        testService.test1();
    }
}
