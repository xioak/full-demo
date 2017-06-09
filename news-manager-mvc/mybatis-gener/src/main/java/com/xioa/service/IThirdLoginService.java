package com.xioa.service;

import com.xioa.model.ThirdLogin;

public interface IThirdLoginService {
    int addThirdLogin(ThirdLogin recode);

    int updateThirdLogin(ThirdLogin recode);

    ThirdLogin findThirdLogin(Integer id);
}