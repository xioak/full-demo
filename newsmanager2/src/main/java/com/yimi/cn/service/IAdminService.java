package com.yimi.cn.service;

import com.yimi.cn.model.Admin;

import java.util.List;

public interface IAdminService {
    int addAdmin(Admin recode);

    int updateAdmin(Admin recode);

    Admin findAdmin(Integer id);

    Admin findAdminByNmae(Admin admin);

    List getAdminList();

    int deleteAdmin(Integer id);
}