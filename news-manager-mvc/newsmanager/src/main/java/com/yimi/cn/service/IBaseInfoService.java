package com.yimi.cn.service;

import com.yimi.cn.model.BaseInfo;
import com.yimi.cn.model.CustomerMessage;
import com.yimi.cn.model.NewsContent;

import java.util.List;

public interface IBaseInfoService {
    int addBaseInfo(BaseInfo recode);

    int updateBaseInfo(BaseInfo recode);

    BaseInfo findBaseInfo(Integer id);

    int addCpBase(BaseInfo info);

    int updateCpBase(BaseInfo info);

    BaseInfo findBaseInfoByType(int i);

    int addTeamsInfo(NewsContent content);

    NewsContent getTeamsInfoByType(Integer type);

    int updateTeamsInfo(NewsContent content);

    List<BaseInfo> findBaseInfoListByType(int type);

    List<BaseInfo> findBaseInfoList(Integer type, Integer id);

    BaseInfo findBaseInfoType(Integer id, Integer type);

    int deleteBaseInfo(Integer id);

    int addCustomeMessage(CustomerMessage message);

    List<CustomerMessage> customeMessageList(CustomerMessage message, String page, String pagesize);

    int getaTotalPage(CustomerMessage message, String page, String pagesize);

    int deleteCustomeMessage(CustomerMessage message);

    int updateCustomeMessage(CustomerMessage message);

    CustomerMessage getCustomeMessage(Integer id);
}