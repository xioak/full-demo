package com.yimi.cn.util;


import com.yimi.cn.constant.ConstantsEnum;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by kangxs on 2016/6/6.
 */
public class RequestUtil {

    /**
     * 校验参数是否缺失
     * @param request
     * @param params
     * @return
     */
    public static ReslutData CheckMissParam(HttpServletRequest request, String ...params){

        return RequestUtil.CheckMissParam(request.getParameterMap(),params);
    }

    public static ReslutData CheckMissParam(Map<String,String[]> map, String ...params){

        for(String param :params){

            if(!map.containsKey(param)){
                return ResultUtil.PARAM_MISS(ConstantsEnum.PARM_ERRO_OR_MISS,param);
            }

            if(StringUtils.isBlank(map.get(param)[0])){
                return ResultUtil.PARAM_MISS(ConstantsEnum.PARM_ERRO_OR_EMPTY,param);
            }
        }
        return null;
    }

    /**
     * 排序key
     * @param keys
     * @return
     */
    public static List<String> sortKeys(Enumeration<String> keys) {
        List<String> keyList = new ArrayList<String>();
        while ( keys.hasMoreElements()) {
            String key = keys.nextElement();
            keyList.add(key);
        }
        Collections.sort(keyList);
        return keyList;
    }


}
