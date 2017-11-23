//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.yimi.cn.controller;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class UploaderUeditor {
    private String url = "";
    private String fileName = "";
    private String state = "";
    private String type = "";
    private String originalName = "";
    private long size = 0L;
    private HttpServletRequest request = null;
    private String title = "";
    private String savePath = "upload";
    private String[] allowFiles = new String[]{".rar", ".doc", ".docx", ".zip", ".pdf", ".txt", ".swf", ".wmv", ".gif", ".png", ".jpg", ".jpeg", ".bmp"};
    private int maxSize = 10000;
    private HashMap<String, String> errorInfo = new HashMap();
    private String fileUrl = "";

    public UploaderUeditor(HttpServletRequest request) {
        this.request = request;
        HashMap<String, String> tmp = this.errorInfo;
        tmp.put("SUCCESS", "SUCCESS");
        tmp.put("NOFILE", "未包含文件上传域");
        tmp.put("TYPE", "不允许的文件格式");
        tmp.put("SIZE", "文件大小超出限制");
        tmp.put("ENTYPE", "请求类型ENTYPE错误");
        tmp.put("REQUEST", "上传请求异常");
        tmp.put("IO", "IO异常");
        tmp.put("DIR", "目录创建失败");
        tmp.put("UNKNOWN", "未知错误");
    }

    public void upload(CommonsMultipartFile[] files) throws Exception {
        boolean isMultipart = ServletFileUpload.isMultipartContent(this.request);
        if (!isMultipart) {
            this.state = (String) this.errorInfo.get("NOFILE");
        }
        try {
            if (files == null || files.length == 0) {
                this.state = (String) this.errorInfo.get("NOFILE");
            } else {
                String savePath = this.getFolder(this.savePath);
                for (CommonsMultipartFile fis : files) {


                    this.originalName = fis.getOriginalFilename();
                    if (this.checkFileType(this.originalName)) {
                        this.fileName = this.getName(this.originalName);
                        this.type = this.getFileExt(this.fileName);
//                        this.url = savePath + File.separator + this.fileName;
                        this.url = this.fileUrl + "/" + this.fileName;
                        String path = this.request.getSession().getServletContext().getRealPath(savePath);
                        File localFile = new File(path);
                        if (!localFile.exists()) {
                            localFile.mkdirs();
                        }

                        fis.transferTo(new File(localFile + File.separator + this.fileName));
                        this.state = (String) this.errorInfo.get("SUCCESS");
                        this.size = fis.getSize();
                        return;
                    } else {
                        String fname = fis.getFileItem().getName();
                        if (fname.equals("pictitle")) {
                            BufferedInputStream in = new BufferedInputStream(fis.getInputStream());
                            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                            StringBuffer result = new StringBuffer();

                            while (reader.ready()) {
                                result.append((char) reader.read());
                            }

                            this.title = new String(result.toString().getBytes(), "utf-8");
                            reader.close();
                        }
                    }
                }

                return;
            }

        } catch (Exception var14) {
            this.state = (String) this.errorInfo.get("UNKNOWN");
            var14.printStackTrace();
        }

    }


    public void uploadBase64(String fieldName) {
        String savePath = this.getFolder(this.savePath);
        String base64Data = this.request.getParameter(fieldName);
        this.fileName = this.getName("test.png");
        this.url = this.fileUrl + "/" + this.fileName;
        BASE64Decoder decoder = new BASE64Decoder();

        try {
            File outFile = new File(this.getPhysicalPath(this.url));
            OutputStream ro = new FileOutputStream(outFile);
            byte[] b = decoder.decodeBuffer(base64Data);

            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] = (byte) (b[i] + 256);
                }
            }

            ro.write(b);
            ro.flush();
            ro.close();
            this.state = (String) this.errorInfo.get("SUCCESS");
        } catch (Exception var9) {
            this.state = (String) this.errorInfo.get("IO");
        }

    }

    private boolean checkFileType(String fileName) {
        Iterator type = Arrays.asList(this.allowFiles).iterator();

        while (type.hasNext()) {
            String ext = (String) type.next();
            if (fileName.toLowerCase().endsWith(ext)) {
                return true;
            }
        }

        return false;
    }

    private String getFileExt(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    private String getName(String fileName) {
        Random random = new Random();
        return this.fileName = "" + random.nextInt(10000) + System.currentTimeMillis() + this.getFileExt(fileName);
    }

    private String getFolder(String path) {
        SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");

        String datastr = formater.format(new Date());
        this.fileUrl = path + "/" + datastr;
        path = path + File.separator + datastr;
        File dir = new File(this.getPhysicalPath(path));
        if (!dir.exists()) {
            try {
                dir.mkdirs();
            } catch (Exception var5) {
                this.state = (String) this.errorInfo.get("DIR");
                return "";
            }
        }

        return path;
    }

    private String getPhysicalPath(String path) {
        String servletPath = this.request.getServletPath();
        String realPath = this.request.getSession().getServletContext().getRealPath(servletPath);
//        return (new File(realPath)).getParent() + File.separator + path;
        return realPath + File.separator + path;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public void setAllowFiles(String[] allowFiles) {
        this.allowFiles = allowFiles;
    }

    public void setMaxSize(int size) {
        this.maxSize = size;
    }

    public long getSize() {
        return this.size;
    }

    public String getUrl() {
        return this.url;
    }

    public String getFileName() {
        return this.fileName;
    }

    public String getState() {
        return this.state;
    }

    public String getTitle() {
        return this.title;
    }

    public String getType() {
        return this.type;
    }

    public String getOriginalName() {
        return this.originalName;
    }
}
