package com.spring.cloud.auth.menu.model;

import com.spring.cloud.auth.authority.entity.Authority;
import com.spring.cloud.auth.menu.entity.Menu;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhenjh
 * @date 2021/8/20
 * @description 菜单树
 */
@Setter
@Getter
public class MenuTree extends Menu {

    private List<MenuTree> children = new ArrayList<>();
}
