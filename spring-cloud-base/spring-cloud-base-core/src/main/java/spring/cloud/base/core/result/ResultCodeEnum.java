package spring.cloud.base.core.result;

import lombok.Getter;
import org.apache.http.HttpStatus;

/**
 * @author haozai
 * @date 2021/4/30  14:07
 */
@Getter
public enum ResultCodeEnum {
    /**
     * 成功
     */
    SUCCESS(Boolean.TRUE, HttpStatus.SC_OK,"成功");


    private Boolean success;

    private Integer code;

    private String message;

    ResultCodeEnum(Boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }
}
