package spring.cloud.base.fund.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

/**
 * @author haozai
 * @description 自动配置
 * @date 2021/5/13  23:37
 */
@Configuration
@ComponentScan(basePackages = {"spring.cloud.base.fund.util","spring.cloud.base.fund.service"})
@EnableFeignClients("spring.cloud.base.fund.client")
public class BeanConfig {
    private final static int TIME_OUT = 10000;
    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(TIME_OUT);
        requestFactory.setReadTimeout(TIME_OUT);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        restTemplate.getMessageConverters().set(1,new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return restTemplate;
    }
}
