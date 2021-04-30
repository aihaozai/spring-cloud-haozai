package com.spring.cloud.auth.core.controller;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

/**
 * @author haozai
 * @date 2021/3/15  21:51
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/keyPair")
public class KeyPairController {

    @Qualifier("rsaKeyPair")
    private final KeyPair keyPair;

    @GetMapping("/getPublicKey")
    public Map<String, Object> getPublicKey() {
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAKey key =new RSAKey.Builder(publicKey).build();
        return new JWKSet(key).toJSONObject();
    }

}
