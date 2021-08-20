package com.spring.cloud.auth.util;

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

    public static List<MenuTree> buildMenuTree(Long Pid, List<Menu> menuList){
        List<MenuTree> menuTreeList = new ArrayList<>();
        for (Menu menu : menuList){
            if(menu.getPid().equals(Pid)) {
                MenuTree newMenuTree = getMenu(menu);
                List<Menu> ml = new ArrayList<>(menuList);
                ml.remove(menu);
                newMenuTree.setChildren(buildMenuTree(newMenuTree.getId(),ml));
                menuTreeList.add(newMenuTree);
            }
        }
        return menuTreeList;
    }

    private static MenuTree getMenu(Menu menu){
        MenuTree menuTree = new MenuTree();
        menuTree.setId(menu.getId());
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
