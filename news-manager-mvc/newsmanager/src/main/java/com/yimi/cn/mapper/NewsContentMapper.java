package com.yimi.cn.mapper;

import com.yimi.cn.model.NewsContent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NewsContentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NewsContent record);

    int insertSelective(NewsContent record);

    NewsContent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NewsContent record);

    int updateByPrimaryKeyWithBLOBs(NewsContent record);

    int updateByPrimaryKey(NewsContent record);

    List<NewsContent> findAllNewContent(int type);

    NewsContent getTeamsInfoByType(Integer type);

    List<NewsContent> findAllNewContentByPage(@Param("type") int type, @Param("start")int start, @Param("end")int end);

    int getTotalRows(int type);

    List getContentNameOnly(Integer type);
}