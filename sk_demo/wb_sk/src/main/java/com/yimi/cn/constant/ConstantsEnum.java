package com.yimi.cn.constant;

public enum ConstantsEnum {

    USER_SESSION_NAME(1,"user_session_info"),
    API_SUCCESS(0, "SUCCESS"),
    PARM_ERRO_OR_MISS(102, "参数不正确"),
    PARM_ERRO_OR_EMPTY(104, "参数不能为空"),
    PARM_ERRO_OR_SIGNATUR(105, "签名错误"),


    FILE_MAXSIZE_ERRO(106,"图片大小超过限制"),
    LOGIN_ERRO(107,"登录错误"),
    UNAME_ERRO(108,"用户名不存在"),
    PASSWORD_ERRO(109,"密码错误" ),
    CONTENT_ADD_ERRO(110,"添加新闻内容错误"),
    CONTENT_ID_ERRO(112, "未知的新闻编号");






    private int index;
    private String name;
    private String comment;


    ConstantsEnum(int index, String name) {
        this.index = index;
        this.name = name;
    }

    ConstantsEnum(int index, String name, String comment) {
        this.index = index;
        this.comment = comment;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    public class USER_SESSION_NAME {
    }
}
