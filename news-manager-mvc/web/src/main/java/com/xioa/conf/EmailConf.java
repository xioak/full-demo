package com.xioa.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by xions on 2016/5/22.
 */

@Component
public class EmailConf {

//    @Value("${mail.username}")

    @Value("${mail.xioa.com.hello.world}")
    private String username;

    public String getUsername() {
        return username;
    }




    public void setUsername(String username) {
        this.username = username;
        System.out.println("value annotoion  run");
    }
}
