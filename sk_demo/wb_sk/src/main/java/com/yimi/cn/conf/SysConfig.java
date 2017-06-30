package com.yimi.cn.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Created by kangxs on 2016/6/23.
 */

@Component
public class SysConfig {

    @Value("${img.host.perfix}")
    public String imghost;


    public String getImghost() {
        return imghost;
    }







    /**
     * 生出随机字符串
     * @param length
     * @return
     */
    public String getRadomStr(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
