package com.yimi.cn.controller;

import com.alibaba.fastjson.JSON;
import com.yimi.cn.constant.ConstantsEnum;
import com.yimi.cn.model.NewsContent;
import com.yimi.cn.service.IAdminService;
import com.yimi.cn.service.INewsContentService;
import com.yimi.cn.util.PagerUtil;
import com.yimi.cn.util.ReslutData;
import com.yimi.cn.util.ResultUtil;
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
@RequestMapping("content")
public class ContentController {


    @Autowired
    INewsContentService newsContentServiceImpl;

    @RequestMapping("/add")
    @ResponseBody
    public ReslutData contentAdd(NewsContent content, HttpServletRequest request) {
        content.setCreatTime(new Date());
        int rows = newsContentServiceImpl.addNewsContent(content);
        if (rows > 0) {
            return ResultUtil.SUCCESS(ConstantsEnum.API_SUCCESS);
        }
        return ResultUtil.SUCCESS(ConstantsEnum.CONTENT_ADD_ERRO);
    }


    @RequestMapping("/list")
    @ResponseBody
    public ReslutData contentList(NewsContent content, String page, String pageSize, HttpServletRequest request) {

        int type = 2;
        if(content!=null){
            type =content.getType();
        }

        List<NewsContent> contents = newsContentServiceImpl.findAllNewContentByPage(type, page, pageSize);
        int totalpage = newsContentServiceImpl.getaTotalPageByType(type, pageSize);
        Map pager = PagerUtil.getPager(page, pageSize, totalpage + "");
        Map datas = new HashMap();
        datas.put("page", pager);
        datas.put("totalpage", totalpage);
        datas.put("newsList", contents);
        return ResultUtil.SUCCESS(ConstantsEnum.API_SUCCESS, datas);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public ReslutData contentDelete(NewsContent content, HttpServletRequest request) {

        if (content == null) {
            return ResultUtil.ERRO(ConstantsEnum.CONTENT_ID_ERRO);
        }

        int rows = newsContentServiceImpl.deletContent(content);

        return ResultUtil.SUCCESS(ConstantsEnum.API_SUCCESS);
    }

    @RequestMapping("/get")
    @ResponseBody
    public ReslutData contentGet(NewsContent content, HttpServletRequest request) {

        if (content == null) {
            return ResultUtil.ERRO(ConstantsEnum.CONTENT_ID_ERRO);
        }

        NewsContent newContent = newsContentServiceImpl.findNewContent(content.getId());

        return ResultUtil.SUCCESS(newContent);
    }

    @RequestMapping("/name/only")
    @ResponseBody
    public ReslutData getNameOnly(NewsContent content, HttpServletRequest request) {

        if (content == null) {
            return ResultUtil.ERRO(ConstantsEnum.CONTENT_ID_ERRO);
        }

        List names = newsContentServiceImpl.getContentNameOnly(content);

        return ResultUtil.SUCCESS(names);
    }

    @RequestMapping("/update")
    @ResponseBody
    public ReslutData contentUpdate(NewsContent content, HttpServletRequest request) {

        if (content == null) {
            return ResultUtil.ERRO(ConstantsEnum.CONTENT_ID_ERRO);
        }

        int i = newsContentServiceImpl.updateNewsContent(content);

        return ResultUtil.SUCCESS(ConstantsEnum.API_SUCCESS);
    }


}
