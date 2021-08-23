package com.spring.cloud.auth.util;

import cn.hutool.core.collection.CollectionUtil;
import com.spring.cloud.auth.menu.entity.Menu;
import com.spring.cloud.auth.menu.model.MenuTree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author haozai
 * @date 2021/8/20 14:25
 * @description 树形菜单工具类
 */
public class MenuTreeUtil {

    public final static Long PID = 0L;

    protected MenuTreeUtil() {
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> buildMenuTree(Long pid, List<Menu> menuList){
        List<T> menuTreeList = new ArrayList<>();
        for (Menu menu : menuList){
            if(menu.getPid().equals(pid)) {
                MenuTree newMenuTree = getMenu(menu);
                List<Menu> ml = new ArrayList<>(menuList);
                ml.remove(menu);
                newMenuTree.setChildren(buildMenuTree(newMenuTree.getId(),ml));
                if(CollectionUtil.isEmpty(newMenuTree.getChildren())){
                    menu.setKey(menu.getId());
                    menuTreeList.add((T) menu);
                }else {
                    menuTreeList.add((T) newMenuTree);
                }
            }
        }
        return menuTreeList;
    }

    private static MenuTree getMenu(Menu menu){
        MenuTree menuTree = new MenuTree();
        menuTree.setId(menu.getId());
        menuTree.setKey(menu.getId());
        menuTree.setPid(menu.getPid());
        menuTree.setName(menu.getName());
        menuTree.setPath(menu.getPath());
        menuTree.setComponent(menu.getComponent());
        menuTree.setIcon(menu.getIcon());
        menuTree.setSort(menu.getSort());
        menuTree.setCreateTime(menu.getCreateTime());
        menuTree.setUpdateTime(menu.getUpdateTime());
        return  menuTree;
    }

}
