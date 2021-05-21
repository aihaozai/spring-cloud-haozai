package spring.cloud.base.datasource.util;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.ObjectUtils;
import spring.cloud.base.datasource.request.QueryEnum;

import java.util.List;

/**
 * @author haozai
 * @date 2021/4/30  16:07
 */
public class QueryUtil {

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
}
