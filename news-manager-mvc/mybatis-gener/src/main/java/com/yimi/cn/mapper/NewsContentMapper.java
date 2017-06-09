package com.yimi.cn.mapper;

import com.yimi.cn.model.NewsContent;

public interface NewsContentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NewsContent record);

    int insertSelective(NewsContent record);

    NewsContent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NewsContent record);

    int updateByPrimaryKeyWithBLOBs(NewsContent record);

    int updateByPrimaryKey(NewsContent record);
}