package spring.cloud.base.redis.starter.utils;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import java.util.concurrent.TimeUnit;

/**
 * @author haozai
 * @date 2021/6/7 11:06
 * @description Redis工具类
 */
@AllArgsConstructor
public class RedisUtil {


    private final RedisTemplate<String, Object> redisTemplate;

    /**
     *
     * 获取指定key的value
     * @param key
     * @return java.lang.Object
     */
    public Object get(String key) {

        return redisTemplate.opsForValue().get(key);
    }


    /**
     *
     * 获取指定key的value
     * @param key
     * @return java.lang.String
     */
    public Object getString(String key) {

        return redisTemplate.opsForValue().get(key).toString();
    }

    /**
     *
     * 获取指定key的value
     * @param var1
     * @return java.lang.String
     */
    public byte[] getByte(byte[] var1) {

        return (byte[]) redisTemplate.opsForValue().get(var1);
    }
    /**
     *
     * 获取指定key的value
     * @param key
     * @return java.lang.String
     */
    public Object getByte1(String key) {

        return redisTemplate.opsForValue().get(key);
    }
    /**
     * 指定失效时间
     * @param key  键
     * @param time 时间(秒)
     * @return
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
