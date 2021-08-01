package spring.cloud.base.core.bean;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import spring.cloud.base.core.annotation.UnUseResult;
import spring.cloud.base.core.result.Result;

import java.lang.reflect.AnnotatedElement;
import java.net.URI;

/**
 * @author haozai
 * @date 2021/7/27  9:45
 */
@Component
@AllArgsConstructor
@RestControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice<Object> {

    private final ObjectMapper objectMapper;

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        AnnotatedElement annotatedElement = methodParameter.getAnnotatedElement();
        UnUseResult cancelResult = AnnotationUtils.findAnnotation(annotatedElement, UnUseResult.class);
        return cancelResult == null;
    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        URI uri = serverHttpRequest.getURI();
       // System.out.println(uri.getPath().replace()));
        if(serverHttpRequest.getURI().getPath().contains("v2/api-docs")){
            return o;
        }else if(o instanceof String){
            return objectMapper.writeValueAsString(Result.ok(o));
        }else if(o instanceof Result){
            return o;
        }
        return Result.ok(o);
    }
}
