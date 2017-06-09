package com.yimi.cn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * Created by xions on 2016/5/16.
 */

@Controller
public class UeditorController{


    private UploaderUeditor uploader =null;

    private CommonsMultipartFile[] imgfiles =null;



    @RequestMapping("ueditor/images/upload2")
    public void updload(HttpServletRequest request, HttpServletResponse response, @RequestParam("upfile") CommonsMultipartFile[] files) {

        imgfiles =files;


    }





    @RequestMapping("ueditor/images/upload")
    public void updload2(HttpServletRequest request, HttpServletResponse response, @RequestParam("upfile") CommonsMultipartFile[] files) {


        try {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            uploader = new UploaderUeditor(request);
            uploader.setSavePath("/upload");
            String[] fileType = {".rar", ".doc", ".docx", ".zip", ".pdf", ".txt", ".swf",
                    ".wmv", ".gif", ".png", ".jpg", ".jpeg", ".bmp"};
            uploader.setAllowFiles(fileType);
            uploader.setMaxSize(10000); //单位KB
            uploader.upload(files);

            String callback = request.getParameter("callback");
            String result = "{\"name\":\""+ uploader.getFileName() +"\", " +
                    "\"originalName\": \""+ uploader.getOriginalName() +"\", \"size\": "
                    + uploader.getSize() +", \"state\": \""+ uploader.getState() +"\", \"type\": \""
                    + uploader.getType() +"\", \"url\": \""+ uploader.getUrl() +"\"}";
            result = result.replaceAll( "\\\\", "\\\\" );

            if( callback == null ){
                response.getWriter().print( result );
            }else{
                response.getWriter().print("<script>"+ callback +"(" + result + ")</script>");
            }


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }



    }


}
