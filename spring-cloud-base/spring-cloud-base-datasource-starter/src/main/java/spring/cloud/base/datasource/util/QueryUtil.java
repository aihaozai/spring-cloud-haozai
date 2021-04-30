package spring.cloud.base.datasource.util;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.ObjectUtils;
import spring.cloud.base.datasource.request.QueryEnum;

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
    public static void setQuery(JSONObject queryObject, QueryWrapper queryWrapper){
        if(ObjectUtils.isNotEmpty(queryObject)){
            queryObject.forEach((key,val) -> {
                if(ObjectUtils.isNotEmpty(key)){
                    if(key.contains(QueryEnum.LIKE.getKeyword())){
                        queryWrapper.like(key,val);
                    }else{
                        queryWrapper.eq(key,val);
                    }
                }
            });
        }
    }
}
