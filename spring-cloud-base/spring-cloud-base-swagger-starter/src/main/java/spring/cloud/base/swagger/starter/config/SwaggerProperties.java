package spring.cloud.base.swagger.starter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author haozai
 * @description Swagger属性
 * @date 2021/4/17  15:06
 */
@Data
@ConfigurationProperties(prefix = "swagger.doc")
public class SwaggerProperties {

    private Boolean enable;

    private String basePackage;

    private String title;

    private String description;

    private String termsOfServiceUrl;

    private String version;
}
