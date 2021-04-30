package spring.cloud.base.datasource.request;


import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import spring.cloud.base.datasource.util.QueryUtil;

/**
 * @author zhenjh
 * @date 2021/4/30
 * @description <p></p>
 */
@Setter
@Getter
@Accessors(chain = true)
public class QueryPage {
    private long size;

    private long current = 10L;

    private JSONObject queryObject;

    private QueryWrapper queryWrapper;

    public void setQueryObject(JSONObject queryObject) {
        this.queryObject = queryObject;
        QueryUtil.setQuery(queryObject,queryWrapper);
    }
}
