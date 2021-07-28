package spring.cloud.base.datasource.util;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import spring.cloud.base.datasource.annotation.Query;
import spring.cloud.base.datasource.request.QueryEnum;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author haozai
 * @date 2021/4/30  16:07
 */
@Slf4j
public class QueryUtil {

    private static final String UNDER_LINE = "_";

    /**
     * 设置查询参数
     * @param queryObject
     * @param queryWrapper
     */
    @SuppressWarnings("unchecked")
    public static QueryWrapper setQuery(JSONObject queryObject, QueryWrapper queryWrapper){
        if(ObjectUtils.isNotEmpty(queryObject)){
            queryObject.forEach((key,val) -> {
                if(ObjectUtils.isNotEmpty(key)){
                    if(key.contains(QueryEnum.LIKE.getKeyword())){
                        queryWrapper.like(key.replace(QueryEnum.LIKE.getKeyword(),""),val);
                    }
                    else if(key.contains(QueryEnum.IN.getKeyword())){
                        queryWrapper.in(key.replace(QueryEnum.IN.getKeyword(),""),((List)val).toArray());
                    }
                    else{
                        queryWrapper.eq(key,val);
                    }
                }
            });
        }
        return queryWrapper;
    }

    @SneakyThrows
    public static <R, Q> QueryWrapper getPredicate(QueryWrapper<R> queryWrapper, Q query) {
        if (query == null) {
            return queryWrapper;
        }
        List<Field> fields = getAllFields(query.getClass(), new ArrayList<>());
        for (Field field : fields) {
            boolean accessible = field.isAccessible();
            field.setAccessible(true);
            Query q = field.getAnnotation(Query.class);
            if (q != null) {
                Object o = field.get(query);
                if (ObjectUtil.isNull(o) || StringUtils.EMPTY.equals(o)) {
                    continue;
                }
                String name = StringUtils.isNotBlank(q.nickname()) ? q.nickname() : field.getName();

                name = humpToUnderline(name);
                switch (q.type()) {
                    case EQUAL:
                        queryWrapper.eq(name, o);
                        break;
                    default:
                        break;
                }
            }
            field.setAccessible(accessible);
        }

        return queryWrapper;
    }

    @SneakyThrows
    public static <T, Q> LambdaQueryWrapper getPredicate(LambdaQueryWrapper<T> lambdaQueryWrapper, Q query) {
        if (query == null) {
            return lambdaQueryWrapper;
        }
        List<Field> fields = getAllFields(query.getClass(), new ArrayList<>());
        for (Field field : fields) {
            boolean accessible = field.isAccessible();
            field.setAccessible(true);
            Query q = field.getAnnotation(Query.class);
            if (q != null) {
                Object o = field.get(query);
                if (ObjectUtil.isNull(o) || StringUtils.EMPTY.equals(o)) {
                    continue;
                }
                String name = StringUtils.isNotBlank(q.nickname()) ? q.nickname() : field.getName();


                switch (q.type()) {
                    case EQUAL:
                        lambdaQueryWrapper.eq(l -> name, o);
                        break;
                    default:
                        break;
                }
            }
            field.setAccessible(accessible);
        }

        return lambdaQueryWrapper;
    }

    private static List<Field> getAllFields(Class clazz, List<Field> fields) {
        if (clazz != null) {
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            getAllFields(clazz.getSuperclass(), fields);
        }
        return fields;
    }

    public static String humpToUnderline(String para) {
        StringBuilder sb = new StringBuilder(para);
        int temp = 0;
        if (!para.contains(UNDER_LINE)) {
            for (int i = 0; i < para.length(); i++) {
                if (Character.isUpperCase(para.charAt(i))) {
                    sb.insert(i + temp, UNDER_LINE);
                    temp += 1;
                }
            }
        }
        return sb.toString();
    }
}
