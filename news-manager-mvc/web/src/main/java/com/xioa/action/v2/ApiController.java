package com.xioa.action.v2;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by xions on 2016/5/16.
 */

@Controller
public class ApiController {


    @RequestMapping("api")
    public String login(String page, HttpServletRequest request, HttpServletResponse response, ModelMap view) {


        if (StringUtils.isBlank(page)) {
            return "index";
        }

        if (StringUtils.endsWith(page, "1")) {
            return "nav_1";
        }
        if (StringUtils.endsWith(page, "2")) {
            return "nav_2";
        }
        if (StringUtils.endsWith(page, "3")) {
            return "nav_3";
        }


//        return "index";

        return "socket";


    }

    @RequestMapping("/")
    public String home(String page, HttpServletRequest request, HttpServletResponse response, ModelMap view) {

        return "nav_1";
    }
}
