package com.yimi.cn.mapper;

import com.yimi.cn.model.CustomerMessage;

public interface CustomerMessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CustomerMessage record);

    int insertSelective(CustomerMessage record);

    CustomerMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CustomerMessage record);

    int updateByPrimaryKey(CustomerMessage record);
}