package com.yimi.cn.service;

import com.yimi.cn.model.Role;

public interface IRoleService {
    int addRole(Role recode);

    int updateRole(Role recode);

    Role findRole(Integer id);
}