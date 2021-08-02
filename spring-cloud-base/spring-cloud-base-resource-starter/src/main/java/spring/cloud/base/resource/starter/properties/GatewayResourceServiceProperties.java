package spring.cloud.base.resource.starter.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author haozai
 * @date 2021/8/2  22:28
 */
@Data
@ConfigurationProperties(prefix = "gateway.resource")
public class GatewayResourceServiceProperties {

    private String resourceId;

    private List<String> permitAll;

}
