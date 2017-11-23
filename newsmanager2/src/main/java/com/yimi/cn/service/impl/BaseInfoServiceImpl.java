package com.yimi.cn.service.impl;

import com.yimi.cn.mapper.BaseInfoMapper;
import com.yimi.cn.mapper.CustomerMessageMapper;
import com.yimi.cn.mapper.NewsContentMapper;
import com.yimi.cn.model.BaseInfo;
import com.yimi.cn.model.CustomerMessage;
import com.yimi.cn.model.NewsContent;
import com.yimi.cn.service.IBaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseInfoServiceImpl implements IBaseInfoService{


    @Autowired
    private BaseInfoMapper baseInfoMapper;

    @Autowired
    private NewsContentMapper newsContentMapper;

    @Autowired
    private CustomerMessageMapper customerMessageMapper;

    @Override
    public int addBaseInfo(BaseInfo recode) {
        return baseInfoMapper.insertSelective(recode);
    }

    @Override
    public int updateBaseInfo(BaseInfo recode) {
        return baseInfoMapper.updateByPrimaryKeySelective(recode);
    }

    @Override
    public BaseInfo findBaseInfo(Integer id) {
        return null;
    }

    @Override
    public int addCpBase(BaseInfo info) {
        return baseInfoMapper.insertSelective(info);
    }

    @Override
    public int updateCpBase(BaseInfo info) {
        return baseInfoMapper.updateByPrimaryKeySelective(info);
    }

    @Override
    public BaseInfo findBaseInfoByType(int type) {
        return baseInfoMapper.selectByType(type);
    }


    @Override
    public int addTeamsInfo(NewsContent content) {

        return newsContentMapper.insertSelective(content);
    }

    @Override
    public NewsContent getTeamsInfoByType(Integer type) {
        return newsContentMapper.getTeamsInfoByType(type);
    }

    @Override
    public int updateTeamsInfo(NewsContent content) {
        return newsContentMapper.updateByPrimaryKeySelective(content);
    }

    @Override
    public List<BaseInfo> findBaseInfoListByType(int type) {
        return baseInfoMapper.findBaseInfoListByType(type);
    }

    @Override
    public List<BaseInfo> findBaseInfoList(Integer type, Integer id) {
        return baseInfoMapper.findBaseInfoList(id,type);
    }

    @Override
    public BaseInfo findBaseInfoType(Integer id, Integer type) {
        return baseInfoMapper.findBaseInfoOnType(id,type);
    }

    @Override
    public int deleteBaseInfo(Integer id) {
        return baseInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int addCustomeMessage(CustomerMessage message) {
        return customerMessageMapper.insertSelective(message);
    }

    @Override
    public List<CustomerMessage> customeMessageList(CustomerMessage message, String page, String pagesize) {

        int inpage = Integer.parseInt(page);
        int inpagesize = Integer.parseInt(pagesize);
        int start = (inpage-1)*inpagesize;
        int end =inpage*inpagesize;
        return customerMessageMapper.customeMessageList(start,end);
    }

    @Override
    public int getaTotalPage(CustomerMessage message, String page, String pagesize) {

        int total = customerMessageMapper.getaTotalPage();
        int size = Integer.parseInt(pagesize);
        if(total%size!=0){
            return  total/size+1;
        }else{
            return total/size;
        }
    }

    @Override
    public int deleteCustomeMessage(CustomerMessage message) {
        return customerMessageMapper.deleteByPrimaryKey(message.getId());
    }

    @Override
    public int updateCustomeMessage(CustomerMessage message) {
        return customerMessageMapper.updateByPrimaryKeySelective(message);
    }

    @Override
    public CustomerMessage getCustomeMessage(Integer id) {
        return customerMessageMapper.selectByPrimaryKey(id);
    }
}