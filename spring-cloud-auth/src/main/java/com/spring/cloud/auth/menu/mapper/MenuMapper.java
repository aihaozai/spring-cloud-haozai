package com.spring.cloud.auth.menu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spring.cloud.auth.menu.entity.Menu;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author haozai
 * @date 2021/8/18  14:31
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 下拉选择
     * @return java.util.List<java.util.Map>
     */
    @Select("SELECT id AS code, name AS label FROM menu")
    List<Map> select();
}