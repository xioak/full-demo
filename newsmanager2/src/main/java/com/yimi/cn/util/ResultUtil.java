package com.yimi.cn.util;

import com.alibaba.fastjson.JSONObject;
import com.yimi.cn.constant.ConstantsEnum;

import java.util.Date;
import java.util.Map;

/**
 * Created by  on 2016/5/17.
 */
public class ResultUtil {

    private static Map resultMap;

    public static ReslutData JsonRsult(int code, String message, Object object) {
        ReslutData data = new ReslutData();
        data.setCode(code);
        data.setResultData(object == null ? new JSONObject() : object);
        data.setMessage(message);
        data.setServerTime(new Date());
        return data;
    }


    public static ReslutData SUCCESS(Object obj) {

        JSONObject.toJSONString(null);

        return ResultUtil.JsonRsult(ConstantsEnum.API_SUCCESS.getIndex(), ConstantsEnum.API_SUCCESS.getName(), obj);

    }

    public static ReslutData SUCCESS(ConstantsEnum constantsEnum, Object obj) {
        return ResultUtil.JsonRsult(constantsEnum.getIndex(), constantsEnum.getName(), obj);
    }

    public static ReslutData SUCCESS(ConstantsEnum constantsEnum) {

        return ResultUtil.JsonRsult(constantsEnum.getIndex(), constantsEnum.getName(), null);
    }

    public static ReslutData ERRO(int code, String message) {

        return ResultUtil.JsonRsult(code, message, null);
    }

    public static ReslutData ERRO(ConstantsEnum constantsEnum) {

        return ResultUtil.JsonRsult(constantsEnum.getIndex(), constantsEnum.getName(), null);
    }

    public static ReslutData ERRO(ConstantsEnum constantsEnum, Object data) {

        return ResultUtil.JsonRsult(constantsEnum.getIndex(), constantsEnum.getName(), data);
    }

    /**
     * 参数错误
     * @param constantsEnum
     * @param pname
     * @return
     */
    public static ReslutData PARAM_MISS(ConstantsEnum constantsEnum,String pname) {

        return ResultUtil.JsonRsult(constantsEnum.getIndex(), constantsEnum.getName()+":"+pname, null);
    }


}
