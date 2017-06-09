package com.xioa.action.v2;

import com.xioa.model.ThirdLogin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by xions on 2016/5/16.
 */

@Controller
public class UserController {


    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response, ModelMap view){
        String name = request.getParameter("username");
        System.out.println("222222");
       // view.setViewName("index");
        ThirdLogin index = new ThirdLogin();
//        index.setIcon("http://baidu.com");
        view.put("username","asdasdffeeeeeeeeeee");
        view.put("thirdLogin",index);
        return "login";


    }
}
