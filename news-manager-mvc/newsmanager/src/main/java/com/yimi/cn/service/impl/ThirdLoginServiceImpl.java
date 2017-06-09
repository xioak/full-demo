package com.yimi.cn.service.impl;

import com.yimi.cn.mapper.ThirdLoginMapper;
import com.yimi.cn.model.ThirdLogin;
import com.yimi.cn.service.IThirdLoginService;
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