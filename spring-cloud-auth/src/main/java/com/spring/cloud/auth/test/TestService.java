package com.spring.cloud.auth.test;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.spring.cloud.auth.client.TestClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author haozai
 * @description spring-cloud-haozai
 * @date 2021/9/25  19:59
 */
@Service
@RequiredArgsConstructor
public class TestService {

    private final TestClient testClient;
    @LcnTransaction
    @Transactional(rollbackFor = Exception.class)
    public void test1() {
        testClient.test1();
        testClient.test2();
    }
}
