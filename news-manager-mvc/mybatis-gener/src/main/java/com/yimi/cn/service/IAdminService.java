package com.yimi.cn.service;

import com.yimi.cn.model.Admin;

public interface IAdminService {
    int addAdmin(Admin recode);

    int updateAdmin(Admin recode);

    Admin findAdmin(Integer id);
}