package com.xioa.action.v2;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by xioa on 2016/6/11.
 */
@Controller
public class FileUpController {

    @RequestMapping("/fileup")
    @ResponseBody
    public String fileUp(@RequestParam("file") CommonsMultipartFile[] files, HttpServletRequest request) throws IOException {

        System.out.println(files.length);
//        boolean flag =true;
//        Progress status = (Progress) request.getSession().getAttribute("status");
//        while (flag){
//            System.out.println(JSON.toJSONString(status));
//            if(status.getpContentLength()==status.getpBytesRead()){
//                flag =false;
//            }
//            try {
//                Thread.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

        List pathlist = new ArrayList(10);
        for(CommonsMultipartFile file:files ){
            if(file.getSize()<=0){
                System.out.println("空");
                continue;
            }
            int pre = (int) System.currentTimeMillis();
//            String path = "D:\\Desktop\\"+new Date().getTime()+"_" + file.getOriginalFilename();
            String path = "/Users/xioamac/Pictures/" +new Date().getTime()+"_" + file.getOriginalFilename();
            pathlist.add(path);
            File localFile = new File(path);
            file.transferTo(localFile);
            int finaltime = (int) System.currentTimeMillis();
            System.out.println((finaltime-pre)+"  "+file.getOriginalFilename());
        }

        String script = "<script>" +
                "var pathstr=" +JSON.toJSONString(pathlist)+
                ";alert(pathstr)"+
//                " ;   function path() {" +
//                "    alert(pathstr)    " +
//                "    }()" +
                "</script>";

        return script;






//        return "nav_1";

//        return "redirect:/api.html?page=1";




//
//        //创建一个通用的多部分解析器
//        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
//        //判断 request 是否有文件上传,即多部分请求
//        if (multipartResolver.isMultipart(request)) {
//            //转换成多部分request
//            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
//            //取得request中的所有文件名
//            Iterator<String> iter = multiRequest.getFileNames();
//            while (iter.hasNext()) {
//                //记录上传过程起始时的时间，用来计算上传时间
//                int pre = (int) System.currentTimeMillis();
//                //取得上传文件
//                MultipartFile file = multiRequest.getFile(iter.next());
//                if (file != null) {
//                    //取得当前上传文件的文件名称
//                    String myFileName = file.getOriginalFilename();
//                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在
//                    if (myFileName.trim() != "") {
//                        System.out.println(myFileName);
//                        //重命名上传后的文件名
//                        String fileName = file.getOriginalFilename();
//                        //定义上传路径
//                        String path = "D:\\Desktop\\" + fileName;
//                        File localFile = new File(path);
//                        file.transferTo(localFile);
//                    }
//                }
//                //记录上传该文件后的时间
//                int finaltime = (int) System.currentTimeMillis();
//                System.out.println(finaltime - pre);
//            }
//
//        }


//        return "nav_1";
    }
}
