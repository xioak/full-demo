package com.yimi.cn.controller;

import com.yimi.cn.constant.ConstantsEnum;
import com.yimi.cn.model.Admin;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by xions on 2016/5/16.
 */


public class LoginInterCeptor extends HandlerInterceptorAdapter {


    private static final String[] IGNORE_URI = {"/", "/admin","/login/index","css","js","login.html"};

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();



        for (String url :IGNORE_URI){
            if(requestURI.endsWith(url)){
                return true;
            }
        }

        System.out.println(requestURI);

        if(requestURI.indexOf("images")>0 || requestURI.indexOf("upload")>0){
            return true;
        }

        if(requestURI.endsWith("list")||requestURI.endsWith("get")){
            return true;
        }


        if(requestURI.indexOf("homepages")>0){
            return true;
        }

        Admin admin = (Admin) request.getSession().getAttribute(ConstantsEnum.USER_SESSION_NAME.getName());
        if (admin == null) {

            if(requestURI.startsWith("login.html") ){
                return true;
            }

            request.getRequestDispatcher("/").forward(request, response);
            return false;
        } else {
            return true;
        }

    }


}
