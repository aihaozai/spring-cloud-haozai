package spring.cloud.base.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author haozai
 * @description spring-cloud-haozai
 * @date 2021/6/2  21:57
 */
@Data
@ConfigurationProperties(prefix = "xxl.job")
public class XxlJobProperties {

    private String adminAddresses;

    private String appName;

    private String ip;

    private int port;

    private String accessToken;

    private String logPath;

    private int logRetentionDays;
}
