package com.yimi.cn.mapper;

import com.yimi.cn.model.ThirdLogin;

public interface ThirdLoginMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ThirdLogin record);

    int insertSelective(ThirdLogin record);

    ThirdLogin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ThirdLogin record);

    int updateByPrimaryKey(ThirdLogin record);
}