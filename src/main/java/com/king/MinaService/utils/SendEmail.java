package com.king.MinaService.utils;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class SendEmail {
    /**public static void main(String[] args) {
     //boolean flag=sendQQmail("2847017157@qq.com", "第二次测试", "哈哈哈哈");
     boolean flag=send163Email("yongxin3344520@163.com",
     "注册消息",
     "恭喜您注册成功！<a href='http://127.0.0.1:8080/clothesBuy/customer/index'>点击这里</a>进入主页面");
     if(flag){
     System.out.println("发送成功");
     }else{
     System.out.println("发送失败");
     }
     }*/
    /**
     * 163邮件发送方法  （需要在邮箱的POP3设置里面勾选 POP3/SMTP ,IMAP/SMTP 选项）
     * @param sendTo 邮件接收人   需要两个包 activation.jar ,mail.jar
     * @param title 邮件的标题
     * @param content 邮件的内容
     * @return true 表示成功，false 表示失败
     */
    public static boolean send163Email(String sendTo,String title,String content){
        //1：创建 Session
        Properties pro=new Properties();
        //邮件协议
        pro.put("mail.transport.protocol","smtp");
        //邮件端口
        pro.put("mail.host","smtp.163.com");
        //邮件发送人
        pro.put("mail.from","viphwq@163.com");
        //相当于的客户端与邮件服务器的连接对象
        Session session=Session.getDefaultInstance(pro);
        try{
            //2:获取邮件发送对象
            Transport transport=session.getTransport();
            // 设置账号 ，授权码
            transport.connect("viphwq@163.com","Hwqlxf1314");
            //3:创建邮件消息体
            MimeMessage msg=new MimeMessage(session);
            //邮件标题
            msg.setSubject(title);
            //邮件内容和页面编码
            msg.setContent(content,"text/html;charset=UTF-8");
            //4:发送
            transport.sendMessage(msg,InternetAddress.parse(sendTo));
            //发送成功的返回值
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    /**
     * QQ 邮件发送方法 （需要在QQ邮箱的邮箱设置-账户-POP3/SMTP 服务开启）
     * @param sendTo 邮件接收人   需要两个包 activation.jar ,mail.jar
     * @param title 邮件的标题
     * @param content 邮件的内容
     * @return true 表示成功，false 表示失败
     */
    public  static boolean sendQQmail(String  sendTo,String title,String content){
        boolean bret = false;
        try
        {
            final Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.host", "smtp.qq.com");
            //你自己的邮箱                                   后面是邮箱的账号
            props.put("mail.user", "191160217@qq.com");
            //你开启pop3/smtp时的验证码             后面是邮箱授权码
            props.put("mail.password", "hwq6610085");
            props.put("mail.smtp.port", "25");
            props.put("mail.smtp.starttls.enable", "true");
            Authenticator authenticator = new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    String userName = props.getProperty("mail.user");
                    String password = props.getProperty("mail.password");
                    return new PasswordAuthentication(userName, password);
                }
            };
            // 使用环境属性和授权信息，创建邮件会话
            Session mailSession = Session.getInstance(props, authenticator);
            // 创建邮件消息
            MimeMessage message = new MimeMessage(mailSession);
            // 设置发件人
            String username = props.getProperty("mail.user");  //这里可以自己定义发件人
            InternetAddress form = new InternetAddress(username);
            message.setFrom(form);

            InternetAddress to = new InternetAddress(sendTo);
            message.setRecipient(RecipientType.TO, to);
            // 设置邮件标题
            message.setSubject(title);
            // 设置邮件的内容体
            message.setContent(content, "text/html;charset=UTF-8");
            // 发送邮件
            Transport.send(message);
            //发送成功
            bret = true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        //发送失败
        return bret;
    }


}

