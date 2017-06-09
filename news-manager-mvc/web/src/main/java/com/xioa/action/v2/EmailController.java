package com.xioa.action.v2;

import com.xioa.conf.EmailConf;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xions on 2016/5/16.
 */

@Controller
public class
EmailController {


    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private VelocityEngine velocityEnginet;

//    @Autowired
//    private EmailConf emailConf;




    @RequestMapping("/email")
    @ResponseBody
    public  void Email() throws MessagingException {

        MimeMessage msg = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true,"utf-8");
//        helper.setFrom(emailConf.getUsername());
        helper.setTo("475914010@qq.com");
        helper.setSubject("来自JAVA的问候");


        Map<String, Object> model = new HashMap();
        model.put("user", "用户VIP");
        model.put("content", "nihao");
        String  html =VelocityEngineUtils.mergeTemplateIntoString(velocityEnginet, "/email/email.vm","utf-8",model);

        //第二个参数true，表示text的内容为html，然后注意<img/>标签，src='cid:file'，'cid'是contentId的缩写，'file'是一个标记，需要在后面的代码中调用MimeMessageHelper的addInline方法替代成文件
        helper.setText(html,true);

        mailSender.send(msg);

    }
}
