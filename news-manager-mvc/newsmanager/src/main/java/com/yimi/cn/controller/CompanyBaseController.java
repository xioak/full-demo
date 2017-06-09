package com.yimi.cn.controller;

import com.alibaba.fastjson.JSON;
import com.yimi.cn.conf.SysConfig;
import com.yimi.cn.constant.ConstantsEnum;
import com.yimi.cn.model.BaseDto;
import com.yimi.cn.model.BaseInfo;
import com.yimi.cn.model.NewsContent;
import com.yimi.cn.service.IBaseInfoService;
import com.yimi.cn.util.ReslutData;
import com.yimi.cn.util.ResultUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by xions on 2016/5/16.
 */

@Controller
@RequestMapping("cpbase")
public class CompanyBaseController {


    @Autowired
    IBaseInfoService baseInfoServiceImpl;

    @Autowired
    SysConfig config;


    /**
     * 公司简介
     *
     * @param info
     * @param request
     * @param response
     * @param view
     * @return
     */
    @RequestMapping("base/add")
    @ResponseBody
    public ReslutData baseAdd(BaseInfo info, HttpServletRequest request, HttpServletResponse response, ModelMap view) {


        Map data = new HashMap();
        data.put("cont", info.getContent());

        if (info.getImgurl().startsWith("http")) {
            data.put("img", info.getImgurl());
        } else {
            data.put("img", "http://" + config.getImghost() + info.getImgurl());
        }

        info.setContent(JSON.toJSONString(data));

        if (info.getId() == 0) {
            baseInfoServiceImpl.addCpBase(info);
        } else {
            baseInfoServiceImpl.updateCpBase(info);
        }

        BaseInfo baseInfo = baseInfoServiceImpl.findBaseInfoByType(101);

        return ResultUtil.SUCCESS(baseInfo);
    }


    @RequestMapping("base/get")
    @ResponseBody
    public ReslutData baseCpInfo(BaseInfo info, HttpServletRequest request) {
        BaseInfo baseInfo = baseInfoServiceImpl.findBaseInfoByType(info.getType());
        Map data = new HashMap();
        String content = baseInfo.getContent();
        data.put("content", JSON.parse(content));
        data.put("id", baseInfo.getId());
        return ResultUtil.SUCCESS(data);
    }

    @RequestMapping("base/host")
    @ResponseBody
    public ReslutData baseHost(BaseInfo info, HttpServletRequest request) {

        return ResultUtil.SUCCESS(config.getImghost());
    }


    @RequestMapping("base/team")
    @ResponseBody
    public ReslutData teams(NewsContent content, HttpServletRequest request) {
        content.setCreatTime(new Date());
        content.setTitle("团队介绍");
        content.setType(1);

        if (content.getId() == 0) {
            baseInfoServiceImpl.addTeamsInfo(content);
        } else {
            baseInfoServiceImpl.updateTeamsInfo(content);
        }
        return ResultUtil.SUCCESS(config.getImghost());
    }

    @RequestMapping("base/team/get")
    @ResponseBody
    public ReslutData teamsInfo(NewsContent content, HttpServletRequest request) {

        if (content == null) {
            content = new NewsContent();
            content.setType(1);
        }

        try {
            NewsContent contentnew = baseInfoServiceImpl.getTeamsInfoByType(content.getType());
            return ResultUtil.SUCCESS(contentnew);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResultUtil.ERRO(1, "系統錯誤");

    }

    @RequestMapping("base/qywm/add")
    @ResponseBody
    public ReslutData qywhAdd(BaseDto content, String cpname, HttpServletRequest request) {


        Map datamap = new HashMap();

        String url = content.getImgurl();

        if (url != null && !url.startsWith("http://")) {
            url = "http://" + config.getImghost() + url;
        }

        datamap.put("title", content.getTitle());
        datamap.put("imgurl", url);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        datamap.put("creattime", sdf.format(new Date()));


        if(content.getSubtitle()!=null){
            String[] subtitle = content.getSubtitle().split(",");
            if (subtitle.length == 1) {
                subtitle = content.getSubtitle().split("，");
            }

            datamap.put("subtitle", JSON.toJSON(subtitle));
        }

        BaseInfo info = new BaseInfo();
        info.setType(content.getType());
        info.setContent(JSON.toJSONString(datamap));
        info.setImgurl(url);
        if (content.getCpId() != 0) {
            info.setCpId(content.getCpId());
        }

        if (content.getId() != 0) {
            info.setId(content.getId());
            baseInfoServiceImpl.updateBaseInfo(info);
        } else {

            baseInfoServiceImpl.addBaseInfo(info);
        }


        return ResultUtil.SUCCESS(ConstantsEnum.API_SUCCESS);
    }

    @RequestMapping("base/qywm/list")
    @ResponseBody
    public ReslutData qywhList(BaseDto content, HttpServletRequest request) {

        List<BaseInfo> baseInfoList = baseInfoServiceImpl.findBaseInfoListByType(content.getType());
        return ResultUtil.SUCCESS(this.getBaseFormatJosn(baseInfoList));
    }

    @RequestMapping("base/cptd/list")
    @ResponseBody
    public ReslutData cptdList(BaseDto content, HttpServletRequest request) {

        List<BaseInfo> baseInfoList = baseInfoServiceImpl.findBaseInfoList(content.getType(), content.getCpId());
        return ResultUtil.SUCCESS(this.getBaseFormatJosn(baseInfoList));
    }

    private List getBaseFormatJosn(List<BaseInfo> baseinfos) {
        List dataList = new ArrayList();
        for (BaseInfo info : baseinfos) {
            Map mdata = new HashMap();
            mdata.put("data", JSON.parse(info.getContent()));
            mdata.put("id", info.getId());
            mdata.put("imgurl", info.getImgurl());
            dataList.add(mdata);
        }
        return dataList;
    }

    @RequestMapping("base/address/list")
    @ResponseBody
    public ReslutData addressList(BaseDto content, HttpServletRequest request) {

        return qywhList(content, request);
    }

    @RequestMapping("base/address/add")
    @ResponseBody
    public ReslutData addressadd(BaseDto content, HttpServletRequest request) {

        BaseInfo info = new BaseInfo();
        info.setType(content.getType());
        Map dmap = new HashMap();
        dmap.put("name", content.getName());
        dmap.put("address", content.getAddress());
        dmap.put("phone", content.getPhone());
        dmap.put("code", content.getCode());
        dmap.put("email", content.getEmail());
        info.setContent(JSON.toJSONString(dmap));

        if (content.getId() != 0) {
            info.setId(content.getId());
            baseInfoServiceImpl.updateBaseInfo(info);

        } else {
            baseInfoServiceImpl.addBaseInfo(info);
        }


        return ResultUtil.SUCCESS(info);
    }

    @RequestMapping("base/cpname/list")
    @ResponseBody
    public ReslutData getCpNameList(BaseDto content, HttpServletRequest request) {
        List<BaseInfo> baseInfo = baseInfoServiceImpl.findBaseInfoListByType(content.getType());
        List datas = new ArrayList();
        for (BaseInfo info : baseInfo) {
            String contentstr = info.getContent();
            Object cpname = JSON.parseObject(contentstr).get("cpname");
            if (cpname != null) {
                Map jmap = new HashMap();
                jmap.put("cpname", cpname.toString());
                jmap.put("id", info.getId());
                datas.add(jmap);
            }

        }
        return ResultUtil.SUCCESS(datas);
    }

    @RequestMapping("base/cptd/get")
    @ResponseBody
    public ReslutData getCpTdData(BaseDto content, HttpServletRequest request) {

        BaseInfo info = baseInfoServiceImpl.findBaseInfoType(content.getId(), content.getType());
        Map data = new HashMap();
        data.put("info", info);
        data.put("content", JSON.parse(info.getContent()));
        return ResultUtil.SUCCESS(data);

    }

    @RequestMapping("base/cptd/delete")
    @ResponseBody
    public ReslutData updatCpTdData(BaseDto content, HttpServletRequest request) {

        int row = baseInfoServiceImpl.deleteBaseInfo(content.getId());

        return ResultUtil.SUCCESS(ConstantsEnum.API_SUCCESS);

    }


}
