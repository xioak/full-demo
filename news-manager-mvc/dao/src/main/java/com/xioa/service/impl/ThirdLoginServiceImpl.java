package com.xioa.service.impl;

import com.xioa.dao.ThirdLoginMapper;
import com.xioa.model.ThirdLogin;
import com.xioa.service.IThirdLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ThirdLoginServiceImpl implements IThirdLoginService{


    @Autowired
    ThirdLoginMapper thirdLoginMapper;


    @Override
    public int addThirdLogin(ThirdLogin recode) {
        return thirdLoginMapper.insert(recode);
    }

    @Override
    public int updateThirdLogin(ThirdLogin recode) {
        return 0;
    }

    @Override
    public ThirdLogin findThirdLogin(Integer id) {
        return null;
    }

    @Override
    public List<ThirdLogin> findThirdLoginAll() {
        return thirdLoginMapper.findThirdLoginAll();
    }
}