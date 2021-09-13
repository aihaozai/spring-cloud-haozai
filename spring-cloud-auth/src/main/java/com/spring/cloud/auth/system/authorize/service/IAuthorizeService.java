package com.spring.cloud.auth.system.authorize.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spring.cloud.auth.system.authorize.entity.Authorize;
import com.spring.cloud.auth.system.authorize.model.AuthorizeDTO;
import com.spring.cloud.auth.system.authorize.model.AuthorizeQueryCriteria;

/**
 * @author haozai
 * @date 2021/9/5 19:29
 */
public interface IAuthorizeService extends IService<Authorize> {


    /**
     *
     * 新增
     * @param authorizeDTO 授权权限
     */
    void add(AuthorizeDTO authorizeDTO);

    /**
     *
     * 获取授权的权限
     * @param queryCriteria 查询参数
     * @return com.spring.cloud.auth.system.authorize.model.AuthorizeDTO
     */
    AuthorizeDTO roleList(AuthorizeQueryCriteria queryCriteria);
}
