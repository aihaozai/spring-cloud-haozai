package com.spring.cloud.auth.util;

import cn.hutool.core.collection.CollectionUtil;
import com.spring.cloud.auth.authority.entity.Authority;
import com.spring.cloud.auth.menu.entity.Menu;
import com.spring.cloud.auth.menu.model.MenuAuthorityTree;
import com.spring.cloud.auth.menu.model.MenuTree;
import org.springframework.util.CollectionUtils;
import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public static <T> List<T> buildMenuTree(@Nonnull Long pid, @Nonnull List<Menu> menuList){
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
    public static List<MenuAuthorityTree> buildAuthority(@Nonnull Long pid, @Nonnull List<Menu> menuList, @Nonnull List<Authority> authorities){
        List<MenuAuthorityTree> menuTreeList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(menuList)&&!CollectionUtils.isEmpty(authorities)){
            for (Menu menu : menuList){
                if(menu.getPid().equals(pid)) {
                    List<Authority> temp = authorities.stream().filter(a->a.getMenuId().equals(menu.getId())).collect(Collectors.toList());
                    MenuAuthorityTree newMenuTree = getMenuAuthority(menu,temp);
                    authorities.removeAll(temp);
                    List<Menu> ml = new ArrayList<>(menuList);
                    ml.remove(menu);
                    newMenuTree.setChildren(buildAuthority(newMenuTree.getValue(),ml,authorities));
                    menuTreeList.add(newMenuTree);
                }
            }
        }
        return menuTreeList;
    }

    private static MenuAuthorityTree getMenuAuthority(Menu menu,List<Authority> temp){
        MenuAuthorityTree menuTree = new MenuAuthorityTree();
        menuTree.setLabel(menu.getName());
        menuTree.setValue(menu.getId());
        if(!CollectionUtils.isEmpty(temp)){
            List<MenuAuthorityTree.Authority> authorityList = new ArrayList<>();
            for (Authority authority : temp){
                MenuAuthorityTree.Authority auth = new MenuAuthorityTree.Authority();
                auth.setLabel(authority.getName());
                auth.setValue(authority.getId());
                authorityList.add(auth);
            }
            menuTree.setAuthority(authorityList);
        }
        return  menuTree;
    }
}
