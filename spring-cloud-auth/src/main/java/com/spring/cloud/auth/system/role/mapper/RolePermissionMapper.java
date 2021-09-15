package com.spring.cloud.auth.system.role.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spring.cloud.auth.constant.AuthorizeConstant;
import com.spring.cloud.auth.system.role.entity.RolePermission;
import org.apache.ibatis.annotations.Select;
import spring.cloud.base.core.model.AuthAuthority;

import java.util.List;

/**
 * @author haozai
 * @date 2021/9/8 22:40
 */
public interface RolePermissionMapper extends BaseMapper<RolePermission> {
    /**
     *
     * 查找角色
     * @param userId 用户id
     * @return java.lang.String
     */
    @Select("SELECT CONCAT( GROUP_CONCAT(DISTINCT r.code), ',' , GROUP_CONCAT(DISTINCT au.code) ) AS code FROM role r left join authorize a ON r.id = a.role_id" +
            " left join authority au ON au.id = a.type_id left join role_permission p ON r.id = p.role_id" +
            " WHERE p.user_id = #{userId} AND a.type = '" + AuthorizeConstant.AUTHORITY +"'")
    String findRoleByUserId(Long userId);

    /**
     *
     * 查找权限
     * @param userId 用户id
     * @return java.util.List<spring.cloud.base.core.entity.AuthAuthority>
     */
    @Select("SELECT DISTINCT au.id, au.menu_id, au.code, au.name FROM role r left join authorize a ON r.id = a.role_id" +
            " left join authority au ON au.id = a.type_id left join role_permission p ON r.id = p.role_id" +
            " WHERE p.user_id = #{userId} AND a.type = '" + AuthorizeConstant.AUTHORITY +"'")
    List<AuthAuthority> findAuthorityByUserId(Long userId);
}