package com.xioa.action.v2;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.xioa.util.HeaderUtil;
import com.xioa.util.MatrixToImageWriter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by xions on 2016/5/16.
 */

@Controller
public class CodeZxingController {


    @RequestMapping("/zxing")
    public  void Zxing(HttpServletRequest request, HttpServletResponse response){



        String text = "http://blog.csdn.net/gao36951";
        int width = 300;
        int height = 300;
        // 二维码的图片格式
        String format = "png";
        /**
         * 设置二维码的参数
         */
        HashMap hints = new HashMap();
        // 内容所使用编码
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");

        try {

            System.out.println(request.getSession().getServletContext().getRealPath("/"));

            String path = request.getSession().getServletContext().getRealPath("/");

            File file = new File(path+"code/qr_img/");

            if(!file.exists() && !file.isDirectory()){
                file.mkdir();
            }
            file  = new File(path+"code/qr_img/code.png");

//            String str = "http://192.168.1.100:8080/login?username=xioa&time"+new Date().getTime();// 二维码内容  192.168.1.100
            String str="扫一扫扫一扫扫一扫扫一扫扫一扫扫一扫扫一扫扫一扫";
            BitMatrix bitMatrix = new MultiFormatWriter().encode(str,BarcodeFormat.QR_CODE,width,height,hints);
            OutputStream os = null;
            HeaderUtil.setResponseHeaders(response);
            try {
                os = response.getOutputStream();
                MatrixToImageWriter.writeToStream(bitMatrix,format,os);
//                MatrixToImageWriter.writeToFile(bitMatrix,format,file);

            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    os.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
