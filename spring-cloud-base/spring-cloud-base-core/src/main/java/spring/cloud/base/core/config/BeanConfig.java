package spring.cloud.base.core.config;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import com.ulisesbocchio.jasyptspringboot.annotation.EncryptablePropertySource;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

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

}
