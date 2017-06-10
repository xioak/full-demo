package com.demo.dubbo.service;

import com.alibaba.dubbo.config.annotation.Service;

/**
 * Created by xioamac on 2017/6/10.
 */

@Service(version = "1.0.0")
public class DubboServiceImpl implements DubboService {

    @Override
    public void sayHello(String hello) {
        System.out.println(hello);
    }
}
