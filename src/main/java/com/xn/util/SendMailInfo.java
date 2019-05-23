package com.xn.util;

import com.xn.Constants.MailConstants;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class SendMailInfo extends Authenticator {
    /**
     * 以文本格式发送邮件
     *
     * @param
     */
    public static boolean sendTextMail(String mailContents, String title, String[] recivers) {
        Transport transport;
        Properties properties = new Properties();
        properties.put(MailConstants.PROTOCOL, MailConstants.PROTOCOL_NAME);// 连接协议
        properties.put(MailConstants.HOST, MailConstants.HOST_FOR_QQ);// 主机名
        properties.put(MailConstants.PORT, 465);// 端口号
        properties.put(MailConstants.AUTH, "true");
        properties.put(MailConstants.SLL_ENABLE, "true");// 设置是否使用ssl安全连接 ---一般都使用
        properties.put(MailConstants.MAIL_DUBUG, "true");// 设置是否显示debug信息 true 会在控制台显示相关信息
        // 根据邮件会话属性和密码验证器构造一个发送邮件的 session
        Session sendMailSession = Session.getInstance(properties, new SendMailInfo());
        try {
            // 根据 session 创建一个邮件消息
            Message mailMessage = new MimeMessage(sendMailSession);
            // 创建邮件发送者地址
            Address from = new InternetAddress(MailConstants.getSenderName()+"<"+MailConstants.MAIL_USERNAME+">");
            // 设置邮件消息的发送者
            mailMessage.setFrom(from);
            // 设置邮件消息的主题
            mailMessage.setSubject(title);
            // 设置邮件消息发送的时间
            mailMessage.setSentDate(new Date());
            // 设置邮件消息的主要内容
            mailMessage.setText(mailContents);
            // 创建邮件的接收者地址，并设置到邮件消息中
            transport = sendMailSession.getTransport();
            transport.connect(MailConstants.MAIL_USERNAME, MailConstants.MAIL_PASSWORD);
            for (String reciver:recivers) {
                mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(reciver));
                // 发送邮件
                transport.send(mailMessage);
            }
            transport.close();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        String username = MailConstants.MAIL_USERNAME;
        String pa = MailConstants.MAIL_PASSWORD;
        if (username != null && username.length() > 0 && pa != null && pa.length() > 0) {
            return new PasswordAuthentication(username, pa);
        }
        return null;
    }

}