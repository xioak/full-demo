package com.xioa.service;

import com.xioa.model.Role;

public interface IRoleService {
    int addRole(Role recode);

    int updateRole(Role recode);

    Role findRole(Integer id);
}