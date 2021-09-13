package com.spring.cloud.auth.system.authorize.service;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spring.cloud.auth.system.authorize.entity.Authorize;
import com.spring.cloud.auth.system.authorize.mapper.AuthorizeMapper;
import com.spring.cloud.auth.system.authorize.model.AuthorizeDTO;
import com.spring.cloud.auth.system.authorize.model.AuthorizeQueryCriteria;
import com.spring.cloud.auth.constant.AuthorizeConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.cloud.base.datasource.util.QueryUtil;
import java.util.ArrayList;
import java.util.List;

/**
 * @author haozai
 * @date 2021/9/5 19:29
 */
@Service
@RequiredArgsConstructor
public class AuthorizeServiceImpl extends ServiceImpl<AuthorizeMapper, Authorize> implements IAuthorizeService {


    @Override
    public void add(AuthorizeDTO authorizeDTO) {
        super.remove(new LambdaQueryWrapper<Authorize>().eq(Authorize::getRoleId,authorizeDTO.getRoleId()));
        List<Authorize> addList = new ArrayList<>();
        if(CollectionUtil.isNotEmpty(authorizeDTO.getMenuList())){
            authorizeDTO.getMenuList().forEach(a-> addList.add(Authorize.builder().type(AuthorizeConstant.MENU).roleId(authorizeDTO.getRoleId()).typeId(a).build()));
        }
        if(CollectionUtil.isNotEmpty(authorizeDTO.getAuthorityList())){
            authorizeDTO.getAuthorityList().forEach(a-> addList.add(Authorize.builder().type(AuthorizeConstant.AUTHORITY).roleId(authorizeDTO.getRoleId()).typeId(a).build()));
        }
        if(CollectionUtil.isNotEmpty(addList)){
            super.saveBatch(addList);
        }
    }

    @Override
    public AuthorizeDTO roleList(AuthorizeQueryCriteria queryCriteria) {
        List<Authorize> authorizeList = super.list(QueryUtil.getPredicate(new QueryWrapper<>(),queryCriteria));
        List<Long> menuList = new ArrayList<>();
        List<Long> authorityList = new ArrayList<>();
        authorizeList.forEach(a->{
            if(AuthorizeConstant.MENU.equals(a.getType())){
                menuList.add(a.getTypeId());
            }else if(AuthorizeConstant.AUTHORITY.equals(a.getType())){
                authorityList.add(a.getTypeId());
            }
        });
        return AuthorizeDTO.builder().roleId(queryCriteria.getRoleId()).menuList(menuList).authorityList(authorityList).build();
    }
}
