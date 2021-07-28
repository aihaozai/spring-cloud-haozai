package spring.cloud.base.datasource.request;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author haozai
 * @date 2021/4/30
 */
@Setter
@Getter
@Accessors(chain = true)
public class QueryPage {
    private long size =10L;

    private long current = 1L;
}
