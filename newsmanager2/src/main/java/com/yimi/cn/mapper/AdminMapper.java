package com.yimi.cn.mapper;

import com.yimi.cn.model.Admin;

import java.util.List;

public interface AdminMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    Admin findAdminByNmae(Admin admin);

    List getAdminList();
}