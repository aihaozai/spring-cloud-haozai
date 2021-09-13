package com.spring.cloud.auth.system.user.model;


import lombok.Data;
import spring.cloud.base.datasource.annotation.Query;

/**
 * @author haozai
 * @date 2021/8/17 9:41
 */
@Data
public class UserQueryCriteria {

    @Query(type = Query.Type.EQUAL)
    private String username;

    @Query(nickname = "account", type = Query.Type.LIKE)
    private String accountLike;

    @Query(nickname = "username", type = Query.Type.LIKE)
    private String usernameLike;
}
