package com.yimi.cn.mapper;

import com.yimi.cn.model.BaseInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseInfo record);

    int insertSelective(BaseInfo record);

    BaseInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseInfo record);

    int updateByPrimaryKey(BaseInfo record);

    BaseInfo selectByType(int type);

    List<BaseInfo> findBaseInfoListByType(int type);

    List<BaseInfo> findBaseInfoList(@Param("id") Integer id, @Param("type") Integer type);

    BaseInfo findBaseInfoOnType(@Param("id") Integer id, @Param("type") Integer type);
}