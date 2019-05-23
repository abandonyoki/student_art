package com.xn.Constants;

import java.io.UnsupportedEncodingException;

public class MailConstants {
    public static final String PROTOCOL = "mail.transport.protocol";
    public static final String PROTOCOL_NAME = "smtp";
    public static final String HOST = "mail.smtp.host";
    public static final String HOST_FOR_QQ = "smtp.qq.com";
    public static final String HOST_FOR_NETEASE = "smtp.163.com";
    public static final String PORT = "mail.smtp.port";
    public static final String AUTH = "mail.smtp.auth";
    public static final String SLL_ENABLE = "mail.smtp.ssl.enable";
    public static final String MAIL_DUBUG = "mail.debug";
    public static final String MAIL_USERNAME = "2628751126@qq.com";
    public static final String MAIL_PASSWORD = "kedtvbripywidjaf";
    public static final String SENDER_NAME = "艺术专业课程作品管理系统";
    public  static String getSenderName(){
        String nick="";
        try{
            nick=javax.mail.internet.MimeUtility.encodeText(SENDER_NAME);
        }catch(Exception e){
            e.printStackTrace();
        }
        return  nick;
    }

}
