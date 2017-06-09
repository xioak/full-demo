package com.yimi.cn.mapper;

import com.yimi.cn.model.CustomerMessage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerMessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CustomerMessage record);

    int insertSelective(CustomerMessage record);

    CustomerMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CustomerMessage record);

    int updateByPrimaryKey(CustomerMessage record);

    int getaTotalPage();

    List<CustomerMessage> customeMessageList(@Param("start") int start, @Param("end") int end);
}