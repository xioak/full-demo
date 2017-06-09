package com.yimi.cn.service;

import com.yimi.cn.model.ThirdLogin;

public interface IThirdLoginService {
    int addThirdLogin(ThirdLogin recode);

    int updateThirdLogin(ThirdLogin recode);

    ThirdLogin findThirdLogin(Integer id);
}