package spring.cloud.base.core.result;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.http.HttpStatus;

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
     * 返回数据
     */
    private T data;

    public static <T> Result<T> ok() {
        Result<T> result = new Result<>();
        result.setSuccess(ResultCodeEnum.SUCCESS.getSuccess())
        .setCode(ResultCodeEnum.SUCCESS.getCode());
        return result;
    }

    public static <T> Result<T> ok(T data) {
        Result<T> result = new Result<>();
        result.setSuccess(ResultCodeEnum.SUCCESS.getSuccess())
        .setCode(ResultCodeEnum.SUCCESS.getCode())
        .setData(data);
        return result;
    }

    public static <T> Result<T> ok(T data,Integer code) {
        Result<T> result = new Result<>();
        result.setSuccess(HttpStatus.SC_OK==code?ResultCodeEnum.SUCCESS.getSuccess():ResultCodeEnum.FAIL.getSuccess())
                .setCode(code)
                .setData(data);
        return result;
    }

    public static <T> Result<T> fail(T data) {
        Result<T> result = new Result<>();
        result.setSuccess(ResultCodeEnum.FAIL.getSuccess())
                .setCode(ResultCodeEnum.FAIL.getCode())
                .setData(data);
        return result;
    }
}
