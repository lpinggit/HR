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
			// �����˵����������(�Ѻ���)
			String myEmailAccount = "happyimagemeyou@163.com";
		    String myEmailPassword = "123456h";
		    String myEmailSMTPHost = "smtp.163.com";
		    
			//�����ʼ��Ự��Ҳ����Properties props = System.getProperties(); 
			Properties props=new Properties();		
			//�����ʼ��Ự
			props.setProperty("mail.transport.protocol", "smtp");   // ʹ�õ�Э�飨JavaMail�淶Ҫ��
	        props.setProperty("mail.smtp.host", "smtp.163.com");   // �����˵������ SMTP ��������ַ
	        props.setProperty("mail.smtp.auth", "true");            // ��Ҫ������֤
	        // 2. �������ô����Ự����, ���ں��ʼ�����������
	        Session session=Session.getInstance(props);
	        session.setDebug(true);	
				// 3. ����һ���ʼ�
		        MimeMessage message = createMimeMessage(session, myEmailAccount, m,r,e,d);
		        // 4. ���� Session ��ȡ�ʼ��������
		        Transport transport = session.getTransport();
		        // 5. ʹ�� �����˺� �� ���� �����ʼ�������
		        transport.connect(myEmailSMTPHost, myEmailAccount, myEmailPassword);
		        // 6. �����ʼ�, �������е��ռ���ַ
		        transport.sendMessage(message, message.getAllRecipients());
		        // 7. �ر�����
		        transport.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail,String recoName,String empFullName,String desc) throws Exception {
	        // 1. ����һ���ʼ�
	        MimeMessage message = new MimeMessage(session);

	        // 2. From: �����ˣ��ǳ��й�����ɣ����ⱻ�ʼ�����������Ϊ���ķ������������ʧ�ܣ����޸��ǳƣ�
	        message.setFrom(new InternetAddress(sendMail));

	        // 3. To: �ռ��ˣ��������Ӷ���ռ��ˡ����͡����ͣ�
	        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail));

	        // 4. Subject: �ʼ����⣨�����й�����ɣ����ⱻ�ʼ�����������Ϊ���ķ������������ʧ�ܣ����޸ı��⣩
	        message.setSubject("���µ����������Ϣ�����λԱ����ʱ�鿴��");

	        String str_content = null;
			if("����ʧ�ܣ���Ǹ�����������Ϊ���ǹ�˾�Ƽ��˲š�".equals(desc)){
				 str_content="�𾴵�"+empFullName+"���������ã�\n\t���Ƽ��ġ�"+recoName+"��"+desc+
				"��������" +
				"<a href=\"http://localhost:8080/HR/userlogin.do\">��¼ϵͳ</a>"+"�鿴������Ϣ";
			}
			else{
				str_content="�𾴵�"+empFullName+"���������ã�\n\t��ϲ���Ƽ��ġ�"+recoName+"��"+desc+
				"��������" +
				"<a href=\"http://localhost:8080/HR/userlogin.do\">��¼ϵͳ</a>"+"�鿴������Ϣ";
			}
	        // 5. Content: �ʼ����ģ�����ʹ��html��ǩ���������й�����ɣ����ⱻ�ʼ�����������Ϊ���ķ������������ʧ�ܣ����޸ķ������ݣ�
	        message.setContent(str_content, "text/html;charset=UTF-8");

	        // 6. ���÷���ʱ��
	        message.setSentDate(new Date());

	        // 7. ��������
	        message.saveChanges();

	        return message;
	    }
}
