package com.spring.cloud.auth.menu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spring.cloud.auth.menu.entity.Menu;

import java.util.List;
import java.util.Map;

/**
 * @author haozai
 * @date 2021/8/18  14:31
 */
public interface IMenuService extends IService<Menu> {

    /**
     * 下拉选择
     * @return java.util.List<java.util.Map>
     */
    List<Map> select();
}
