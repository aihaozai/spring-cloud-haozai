package com.spring.cloud.auth.menu.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spring.cloud.auth.menu.entity.Menu;
import com.spring.cloud.auth.menu.model.MenuTree;
import spring.cloud.base.core.dto.SelectDTO;

import java.util.List;
import java.util.Map;

/**
 * @author haozai
 * @date 2021/8/18  14:31
 */
public interface IMenuService extends IService<Menu> {

    /**
     *
     * 下拉选择
     * @return java.util.List<spring.cloud.base.core.dto.SelectDTO>
     */
    List<SelectDTO> select();

    /**
     * 树形分页
     * @param page  分页
     * @param predicate 查询构造器
     * @return com.baomidou.mybatisplus.extension.plugins.pagination.Page<com.spring.cloud.auth.menu.entity.Menu>
     */
    Page treePage(Page<Menu> page, QueryWrapper<Menu> predicate);

    /**
     *
     * 菜单授权树
     * @return java.util.List
     */
    List authorityTree();
}
