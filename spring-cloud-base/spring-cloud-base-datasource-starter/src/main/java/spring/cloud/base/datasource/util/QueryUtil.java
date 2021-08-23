package spring.cloud.base.datasource.util;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import spring.cloud.base.datasource.annotation.Query;
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

    @SneakyThrows
    public static <R, Q> QueryWrapper<R> getPredicate(QueryWrapper<R> queryWrapper, Q query) {
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
                if (ObjectUtils.isEmpty(o) || StringUtils.EMPTY.equals(o)) {
                    continue;
                }
                String name = StringUtils.isNotBlank(q.nickname()) ? q.nickname() : field.getName();

                name = humpToUnderline(name);
                switch (q.type()) {
                    case EQUAL:
                        queryWrapper.eq(name, o);
                        break;
                    case LIKE:
                        queryWrapper.like(name, o);
                        break;
                    default:
                        break;
                }
            }
            field.setAccessible(accessible);
        }

        return queryWrapper;
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
