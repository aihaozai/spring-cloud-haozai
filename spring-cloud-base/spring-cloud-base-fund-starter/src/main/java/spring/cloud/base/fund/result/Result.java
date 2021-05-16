package spring.cloud.base.fund.result;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author haozai
 * @date 2021/4/30  14:07
 */
@Setter
@Getter
@Accessors(chain = true)
public class Result<T> implements Serializable {
    /**
     * 是否成功
     */
    private boolean success;
    /**
     * 返回码
     */
    private Integer code;
    /**
     * 返回信息
     */
    private String message;
    /**
     * 返回数据
     */
    private T data;
}
