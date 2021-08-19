package com.spring.cloud.auth.menu.service;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spring.cloud.auth.menu.entity.Menu;
import com.spring.cloud.auth.menu.mapper.MenuMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author haozai
 * @date 2021/8/18  14:31
 */
@Service
@AllArgsConstructor
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    private final MenuMapper menuMapper;

    @Override
    public List<Map> select() {
        return menuMapper.select();
    }
}
