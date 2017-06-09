package com.yimi.cn.service;

import com.yimi.cn.model.CustomerMessage;

public interface ICustomerMessageService {
    int addCustomerMessage(CustomerMessage recode);

    int updateCustomerMessage(CustomerMessage recode);

    CustomerMessage findCustomerMessage(Integer id);
}