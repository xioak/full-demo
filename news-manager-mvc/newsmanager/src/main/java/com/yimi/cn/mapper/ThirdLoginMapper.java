package com.yimi.cn.mapper;

import com.yimi.cn.model.ThirdLogin;
import com.yimi.cn.model.ThirdLoginKey;

public interface ThirdLoginMapper {

    int deleteByPrimaryKey(ThirdLoginKey key);

    int insert(ThirdLogin record);

    int insertSelective(ThirdLogin record);

    ThirdLogin selectByPrimaryKey(ThirdLoginKey key);

    int updateByPrimaryKeySelective(ThirdLogin record);

    int updateByPrimaryKey(ThirdLogin record);
}