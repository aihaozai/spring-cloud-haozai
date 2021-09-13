package com.spring.cloud.auth.system.menu.model;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhenjh
 * @date 2021/8/20
 * @description 菜单权限树
 */
@Setter
@Getter
public class MenuAuthorityTree {

    private String label;

    private Long value;

    private List<MenuAuthorityTree> children = new ArrayList<>();

    private List<Authority> authority = new ArrayList<>();

    @Setter
    @Getter
    public static class Authority{

        private String label;

        private Long value;
    }
}
