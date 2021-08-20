package com.spring.cloud.auth.menu.service;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spring.cloud.auth.menu.entity.Menu;
import com.spring.cloud.auth.menu.mapper.MenuMapper;
import com.spring.cloud.auth.menu.model.MenuTree;
import com.spring.cloud.auth.util.MenuTreeUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import spring.cloud.base.core.dto.SelectDTO;

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
    public List<SelectDTO> select() {
        return menuMapper.select();
}

    @Override
    public Page treePage(Page<Menu> page, QueryWrapper predicate) {
        Page menuPage = super.page(page,predicate);
        List<Menu> menuList = menuPage.getRecords();
        menuList.addAll(super.list(new LambdaQueryWrapper<Menu>().ne(Menu::getPid,MenuTreeUtil.PID)));
        List<MenuTree> treeList = MenuTreeUtil.buildMenuTree(MenuTreeUtil.PID,menuList);
        menuPage.setRecords(treeList);
        return menuPage;
    }
}
