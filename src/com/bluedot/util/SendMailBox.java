package com.bluedot.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMailBox {
	
	public static void SendMailBox(String mailBox,String recoName,String empFullName,String desc){
		
		try {
			String m=mailBox;
			String r=recoName;
			String e=empFullName;
			String d=desc;
			// 发件人的邮箱和密码(搜狐的)
			String myEmailAccount = "happyimagemeyou@163.com";
		    String myEmailPassword = "123456h";
		    String myEmailSMTPHost = "smtp.163.com";
		    
			//建立邮件会话，也可用Properties props = System.getProperties(); 
			Properties props=new Properties();		
			//建立邮件会话
			props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
	        props.setProperty("mail.smtp.host", "smtp.163.com");   // 发件人的邮箱的 SMTP 服务器地址
	        props.setProperty("mail.smtp.auth", "true");            // 需要请求认证
	        // 2. 根据配置创建会话对象, 用于和邮件服务器交互
	        Session session=Session.getInstance(props);
	        session.setDebug(true);	
				// 3. 创建一封邮件
		        MimeMessage message = createMimeMessage(session, myEmailAccount, m,r,e,d);
		        // 4. 根据 Session 获取邮件传输对象
		        Transport transport = session.getTransport();
		        // 5. 使用 邮箱账号 和 密码 连接邮件服务器
		        transport.connect(myEmailSMTPHost, myEmailAccount, myEmailPassword);
		        // 6. 发送邮件, 发到所有的收件地址
		        transport.sendMessage(message, message.getAllRecipients());
		        // 7. 关闭连接
		        transport.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail,String recoName,String empFullName,String desc) throws Exception {
	        // 1. 创建一封邮件
	        MimeMessage message = new MimeMessage(session);

	        // 2. From: 发件人（昵称有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改昵称）
	        message.setFrom(new InternetAddress(sendMail));

	        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
	        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail));

	        // 4. Subject: 邮件主题（标题有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改标题）
	        message.setSubject("有新的面试审核信息，请各位员工及时查看！");

	        String str_content = null;
			if("面试失败，抱歉！！请你继续为我们公司推荐人才。".equals(desc)){
				 str_content="尊敬的"+empFullName+"先生：您好！\n\t你推荐的“"+recoName+"”"+desc+
				"请点击这里" +
				"<a href=\"http://localhost:8080/HR/userlogin.do\">登录系统</a>"+"查看具体信息";
			}
			else{
				str_content="尊敬的"+empFullName+"先生：您好！\n\t恭喜你推荐的“"+recoName+"”"+desc+
				"请点击这里" +
				"<a href=\"http://localhost:8080/HR/userlogin.do\">登录系统</a>"+"查看具体信息";
			}
	        // 5. Content: 邮件正文（可以使用html标签）（内容有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改发送内容）
	        message.setContent(str_content, "text/html;charset=UTF-8");

	        // 6. 设置发件时间
	        message.setSentDate(new Date());

	        // 7. 保存设置
	        message.saveChanges();

	        return message;
	    }
}
