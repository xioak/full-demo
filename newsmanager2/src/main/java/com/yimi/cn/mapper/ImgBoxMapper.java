package com.yimi.cn.mapper;

import com.yimi.cn.model.ImgBox;

public interface ImgBoxMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ImgBox record);

    int insertSelective(ImgBox record);

    ImgBox selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ImgBox record);

    int updateByPrimaryKey(ImgBox record);
}