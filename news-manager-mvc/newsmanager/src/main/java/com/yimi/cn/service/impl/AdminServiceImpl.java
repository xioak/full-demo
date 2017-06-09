package com.yimi.cn.service.impl;

import com.yimi.cn.mapper.AdminMapper;
import com.yimi.cn.model.Admin;
import com.yimi.cn.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AdminServiceImpl implements IAdminService{

    @Autowired
    AdminMapper adminMapper;

    @Override
    public int addAdmin(Admin recode) {
        return adminMapper.insertSelective(recode);
    }

    @Override
    public int updateAdmin(Admin recode) {
        return adminMapper.updateByPrimaryKeySelective(recode);
    }

    @Override
    public Admin findAdmin(Integer id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    @Override
    public Admin findAdminByNmae(Admin admin) {
        return adminMapper.findAdminByNmae(admin);
    }

    @Override
    public List getAdminList() {
        return adminMapper.getAdminList();
    }

    @Override
    public int deleteAdmin(Integer id) {
        return adminMapper.deleteByPrimaryKey(id);
    }
}