package com.yimi.cn.controller;

import com.yimi.cn.constant.ConstantsEnum;
import com.yimi.cn.model.Admin;
import com.yimi.cn.model.CustomerMessage;
import com.yimi.cn.model.NewsContent;
import com.yimi.cn.service.IAdminService;
import com.yimi.cn.service.IBaseInfoService;
import com.yimi.cn.util.PagerUtil;
import com.yimi.cn.util.ReslutData;
import com.yimi.cn.util.ResultUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by  on 2017/4/15.
 */


@Controller
public class MessageController {

    @Autowired
    IBaseInfoService baseInfoServiceImpl;


    @RequestMapping("/message/add")
    @ResponseBody
    public ReslutData addCustomeMessage(CustomerMessage message, HttpServletRequest request){


        if(message!=null && message.getId()!=0){

            message.setAnswerTime(new Date());

            int r = baseInfoServiceImpl.updateCustomeMessage(message);

        }else{

            message.setAddTime(new Date());

            int r = baseInfoServiceImpl.addCustomeMessage(message);
        }

        return ResultUtil.SUCCESS(ConstantsEnum.API_SUCCESS);
    }

    @RequestMapping("/message/get")
    @ResponseBody
    public ReslutData getCustomeMessage(CustomerMessage message, HttpServletRequest request){
        CustomerMessage messagec= baseInfoServiceImpl.getCustomeMessage(message.getId());
        return ResultUtil.SUCCESS(messagec);
    }

    @RequestMapping("/message/list")
    @ResponseBody
    public ReslutData customeMessageList(CustomerMessage message,String page,String pagesize, HttpServletRequest request){

        List<CustomerMessage> messageList = baseInfoServiceImpl.customeMessageList(message,page,pagesize);
        int totalpage = baseInfoServiceImpl.getaTotalPage(message,page,pagesize);
        Map pager = PagerUtil.getPager(page, pagesize, totalpage + "");
        Map datas = new HashMap();
        datas.put("page", pager);
        datas.put("totalpage", totalpage);
        datas.put("datas", messageList);
        return ResultUtil.SUCCESS(datas);
    }

    @RequestMapping("/message/delete")
    @ResponseBody
    public ReslutData deleteCustomeMessage(CustomerMessage message, HttpServletRequest request){
        int r = baseInfoServiceImpl.deleteCustomeMessage(message);
        return ResultUtil.SUCCESS(ConstantsEnum.API_SUCCESS);
    }
}
