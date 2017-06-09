package com.xioa.action.v2;

import com.xioa.model.ThirdLogin;
import com.xioa.service.IThirdLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

/**
 * Created by xions on 2016/5/16.
 */

@Controller
public class TsetDbController {


    @Autowired
    private IThirdLoginService thirdLoginService;


    @RequestMapping("/get/data")
    @ResponseBody
    public List login(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {


        List<ThirdLogin> third = thirdLoginService.findThirdLoginAll();

        int type = 1;

        if (third.size() > 0) {

            ThirdLogin thirdLog = third.get(third.size() - 1);

            type = thirdLog.getType() + 1;
        }
        ThirdLogin login = new ThirdLogin();

        login.setId(type);

        login.setType(type);

        login.setTypeName("type_name_"+type);

        login.setToken(UUID.randomUUID().toString().substring(20));

        thirdLoginService.addThirdLogin(login);

        return third;


    }

    @RequestMapping("/get/data/page")
    public String loginPage(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {

        return "third";
    }
}
