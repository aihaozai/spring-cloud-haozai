package com.spring.cloud.auth.system.role.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spring.cloud.auth.constant.AuthorizeConstant;
import com.spring.cloud.auth.system.role.entity.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author haozai
 * @date 2021/9/8 22:40
 */
public interface RoleMapper extends BaseMapper<Role> {


    /**
     *
     * 查找角色
     * @param userId 用户id
     * @return java.util.List<com.spring.cloud.auth.system.role.entity.Role>
     */
    @Select("SELECT r.id, r.name, r.code FROM role r LEFT JOIN role_permission p ON r.id = p.role_id WHERE p.user_id = #{userId} ")
    List<Role> findRoleByCurrentUser(Long userId);
}