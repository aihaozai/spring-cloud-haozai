package com.spring.cloud.auth.system.menu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spring.cloud.auth.constant.AuthorizeConstant;
import com.spring.cloud.auth.system.menu.entity.Menu;
import org.apache.ibatis.annotations.Select;
import spring.cloud.base.core.dto.SelectDTO;

import java.util.List;

/**
 * @author haozai
 * @date 2021/8/18  14:31
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     *
     * 下拉选择
     * @return java.util.List<spring.cloud.base.core.dto.SelectDTO>
     */
    @Select("SELECT id AS code, name AS label FROM menu")
    List<SelectDTO> select();

    /**
     *
     * 查找该用户的菜单
     * @param userId 用户id
     * @return java.util.List<com.spring.cloud.auth.system.menu.model.MenuTree>
     */
    @Select("SELECT DISTINCT m.id, m.pid, m.name, m.path, m.component, m.icon, m.sort  FROM role r left join authorize a ON r.id = a.role_id" +
            " left join menu m ON m.id = a.type_id left join role_permission p ON r.id = p.role_id" +
            " WHERE p.user_id = #{userId} AND a.type = '" + AuthorizeConstant.MENU +"'")
    List<Menu> findMenuByUserId(Long userId);
}