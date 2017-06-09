package com.yimi.cn.service;

import com.yimi.cn.model.BaseInfo;

public interface IBaseInfoService {
    int addBaseInfo(BaseInfo recode);

    int updateBaseInfo(BaseInfo recode);

    BaseInfo findBaseInfo(Integer id);
}