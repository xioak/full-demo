package com.yimi.cn.util;

import com.alibaba.fastjson.JSON;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xioamac on 2017/5/18.
 */
public class PagerUtil {


    public static Map getPager(String page,String size,String tolpage){

        int totalpage = Integer.parseInt(tolpage);
        int pageNo= Integer.parseInt(page);
        int last =pageNo+3;
        if(last>totalpage){
            last= totalpage;
        }

        StringBuffer buffer = null;
        if(pageNo-3<=0){
            buffer =new StringBuffer("1");
            for(int i= 2;i<=last;i++){
                buffer.append(",");
                buffer.append(i);
            }
        }else{
            buffer =new StringBuffer((pageNo-3)+"");
            for(int i= pageNo-2;i<=last;i++){
                buffer.append(",");
                buffer.append(i);
            }
        }

        Map datas = new HashMap();

        datas.put("total",totalpage);
        datas.put("curre",page);
        datas.put("pagelist", JSON.toJSON(buffer.toString().split(",")));

        if(pageNo==1){
            datas.put("first","0");
        }
        if(pageNo>=totalpage){
            datas.put("last","0");
        }


        return datas;
    }

    public static String getMD5(String str) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public static void main(String[] args) {
        System.out.println(PagerUtil.getMD5("23244"));
    }

}
