package com.xioa.util;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by xions on 2016/5/22.
 */
public class HeaderUtil {

    public static void setResponseHeaders(HttpServletResponse response) {
        response.setContentType("image/png");
        response.setHeader("Cache-Control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        long time = System.currentTimeMillis();
        response.setDateHeader("Last-Modified", time);
        response.setDateHeader("Date", time);
        response.setDateHeader("Expires", time);
    }
}
