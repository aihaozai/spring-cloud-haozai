package com.spring.cloud.auth.system.menu.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spring.cloud.auth.system.menu.entity.Menu;
import com.spring.cloud.auth.system.menu.model.MenuTree;
import spring.cloud.base.core.dto.SelectDTO;

import java.util.List;

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
     * @return com.baomidou.mybatisplus.extension.plugins.pagination.Page<com.spring.cloud.auth.system.menu.entity.Menu>
     */
    Page treePage(Page<Menu> page, QueryWrapper<Menu> predicate);

    /**
     *
     * 菜单授权树
     * @return java.util.List
     */
    List authorityTree();

    /**
     *
     * 查找该用户的菜单
     * @param userId 用户id
     * @return java.util.List<com.spring.cloud.auth.system.menu.model.MenuTree>
     */
    List<MenuTree> findMenuByUserId(Long userId);
}
