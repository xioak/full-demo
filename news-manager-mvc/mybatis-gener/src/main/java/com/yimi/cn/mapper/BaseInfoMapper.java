package com.yimi.cn.mapper;

import com.yimi.cn.model.BaseInfo;

public interface BaseInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseInfo record);

    int insertSelective(BaseInfo record);

    BaseInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseInfo record);

    int updateByPrimaryKey(BaseInfo record);
}