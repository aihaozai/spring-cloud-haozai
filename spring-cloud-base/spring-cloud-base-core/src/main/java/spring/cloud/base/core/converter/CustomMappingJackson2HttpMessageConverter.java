package spring.cloud.base.core.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.lang.reflect.Type;

/**
 * @author haozai
 * @description 自定义的json转换器
 * @date 2021/8/20  23:29
 */
public class CustomMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {

    private final static Logger logger = LoggerFactory.getLogger(CustomMappingJackson2HttpMessageConverter.class);

    /**
     * 判断该转换器是否能将请求内容转换成 Java 对象
     */
    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType) {
        // 不需要反序列化
        return false;
    }

    /**
     * 判断该转换器是否能将请求内容转换成 Java 对象
     */
    @Override
    public boolean canRead(Type type, Class<?> contextClass, MediaType mediaType) {
        // 不需要反序列化
        return false;
    }

    /**
     * 判断该转换器是否可以将 Java 对象转换成返回内容.
     * 可以匹配web api  此处不进行匹配，默认所有
     */
    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        return true;
    }

}