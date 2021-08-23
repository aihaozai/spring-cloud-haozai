package spring.cloud.base.core.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import com.ulisesbocchio.jasyptspringboot.annotation.EncryptablePropertySource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import java.math.BigInteger;

/**
 * @author haozai
 * @description 自动配置
 * @date 2021/5/13  23:37
 */

@Configuration
@EnableEncryptableProperties
@EncryptablePropertySource( name  =  "EncryptedProperties" , value  =  "classpath:/bootstrap.yml" )
@ComponentScan(basePackages = {"spring.cloud.base.core.bean"})
public class BeanConfig {

    /**
     *
     * 解决前端js处理大数字丢失精度问题，将Long和BigInteger转换成string
     * @return org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
     */
    @Bean
    @ConditionalOnBean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(BigInteger.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        objectMapper.registerModule(simpleModule);
        jackson2HttpMessageConverter.setObjectMapper(objectMapper);
        return jackson2HttpMessageConverter;
    }
}
