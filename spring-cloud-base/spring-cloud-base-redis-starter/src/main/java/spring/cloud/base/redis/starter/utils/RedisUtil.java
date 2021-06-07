package spring.cloud.base.redis.starter.utils;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author haozai
 * @date 2021/6/7 11:06
 * @description Redis工具类
 */
@Component
@AllArgsConstructor
public class RedisUtil {


    private final RedisTemplate<String, Object> redisTemplate;

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
