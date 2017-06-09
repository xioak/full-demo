package com.xioa.dao;

import com.xioa.model.Pril;

import java.util.Map;

public interface PrilMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Pril record);

    int insertSelective(Pril record);

    Pril selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Pril record);

    int updateByPrimaryKey(Pril record);

    void getByProc(Map param);
}