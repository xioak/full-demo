package com.xioa.action.v2;

import com.github.bingoohuang.patchca.background.SingleColorBackgroundFactory;
import com.github.bingoohuang.patchca.color.ColorFactory;
import com.github.bingoohuang.patchca.custom.ConfigurableCaptchaService;
import com.github.bingoohuang.patchca.filter.predefined.DoubleRippleFilterFactory;
import com.github.bingoohuang.patchca.text.renderer.BestFitTextRenderer;
import com.github.bingoohuang.patchca.utils.encoder.EncoderHelper;
import com.github.bingoohuang.patchca.word.RandomWordFactory;
import com.xioa.conf.UserConf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * Created by xions on 2016/5/21.
 */

@Controller
public class VerifyCodeController {



    private static ConfigurableCaptchaService ccs = null;
    private static Random random = new Random();

    @Autowired
    private UserConf userConf;

    @RequestMapping("img/code2")
    public void ImageCode(HttpServletRequest request, HttpServletResponse response)  {
        if(ccs==null){
            ccs = new ConfigurableCaptchaService();

            //设置随机颜色
            ccs.setColorFactory(
                    new ColorFactory() {
                        @Override
                        public Color getColor(int p) {
                            int[] c = new int[3];
                            int i = random.nextInt(c.length);
                            for (int fi = 0; fi < c.length; fi++) {
                                if (fi == i) {
                                    c[fi] = random.nextInt(72);
                                } else {
                                    c[fi] = random.nextInt(256);
                                }
                            }
                            return new Color(c[0], c[1], c[2]);
                        }
                    }
            );

            //设置随机字符
            RandomWordFactory wf = new RandomWordFactory();
            wf.setCharacters("23456789abcdefghigkmnpqrstuvwxyzABCDEFGHIGKLMNPQRSTUVWXYZ");
            wf.setMaxLength(4);
            wf.setMinLength(4);
            ccs.setWordFactory(wf);

            //设置图片大小
            ccs.setWidth(160);
            ccs.setHeight(80);

            ccs.setTextRenderer(new BestFitTextRenderer());
            ccs.setBackgroundFactory(new SingleColorBackgroundFactory(new Color(192, 192, 136)));
            ccs.setFilterFactory(new DoubleRippleFilterFactory());
        }


        System.out.println(userConf.getAge());


        this.setResponseHeaders(response);
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            String captcha = EncoderHelper.getChallangeAndWriteImage(ccs, "png", os);
            System.out.println(captcha);
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

    }

    protected void setResponseHeaders(HttpServletResponse response) {
        response.setContentType("image/png");
        response.setHeader("Cache-Control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        long time = System.currentTimeMillis();
        response.setDateHeader("Last-Modified", time);
        response.setDateHeader("Date", time);
        response.setDateHeader("Expires", time);
    }

}




