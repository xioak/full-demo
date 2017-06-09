package com.xioa.dao;

import com.xioa.model.ThirdLogin;

import java.util.List;

public interface ThirdLoginMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ThirdLogin record);

    int insertSelective(ThirdLogin record);

    ThirdLogin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ThirdLogin record);

    int updateByPrimaryKey(ThirdLogin record);

    List<ThirdLogin> findThirdLoginAll();
}