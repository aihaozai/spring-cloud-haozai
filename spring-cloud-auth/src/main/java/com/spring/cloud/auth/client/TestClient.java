package com.spring.cloud.auth.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import spring.cloud.base.core.result.Result;

/**
 * @author haozai
 * @description spring-cloud-haozai
 * @date 2021/9/25  18:53
 */
@Component
@FeignClient(value = "cloud-fund", path = "fund/test")
public interface TestClient {
    @GetMapping("/test1")
    Result test1();

    @GetMapping("/test2")
    Result test2();
}
