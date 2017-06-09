package com.xioa.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by xions on 2016/5/21.
 */


@Component()
@Scope("singleton")
public class UserConf {


    public static String userName;

    @Value("${test.user.age}")
    private String age;

    public String getAge() {
        return age;
    }
}
