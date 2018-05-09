package com.bluedot.service.impl;

import java.util.Date;
import java.util.List;
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

import com.bluedot.dao.RecruitDao;
import com.bluedot.dao.impl.RecruitDaoImpl;
import com.bluedot.po.RecruitInfo;
import com.bluedot.service.RecruitService;
import com.bluedot.util.SplitPage;

public class RecruitServiceImpl implements RecruitService {

	RecruitDao recruitDao;

	public RecruitServiceImpl() {
		recruitDao = new RecruitDaoImpl();
	}

	public boolean saveRecruitInfo(RecruitInfo recruit) {

		boolean isSuccess = recruitDao.saveRecruitInfo(recruit);
		if (isSuccess && "yes".equals(recruit.getIsUrgent())) {

			System.out.println("发送邮件················");
			try {
				
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

				//查询所有的邮箱号码
				List<String> mailBox = recruitDao.getAllmailBox();
				for(String mail:mailBox){
					
					// 3. 创建一封邮件
			        MimeMessage message = createMimeMessage(session, myEmailAccount, mail);
			        // 4. 根据 Session 获取邮件传输对象
			        Transport transport = session.getTransport();
			        // 5. 使用 邮箱账号 和 密码 连接邮件服务器
			        transport.connect(myEmailSMTPHost, myEmailAccount, myEmailPassword);
			        // 6. 发送邮件, 发到所有的收件地址
			        transport.sendMessage(message, message.getAllRecipients());
			        // 7. 关闭连接
			        transport.close();
				}
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	
	/**
     * 创建一封只包含文本的简单邮件
     *
     * @param session 和服务器交互的会话
     * @param sendMail 发件人邮箱
     * @param receiveMail 收件人邮箱
     * @return
     * @throws Exception
     */
    public MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail) throws Exception {
        // 1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);

        // 2. From: 发件人（昵称有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改昵称）
        message.setFrom(new InternetAddress(sendMail));

        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail));

        // 4. Subject: 邮件主题（标题有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改标题）
        message.setSubject("有新的招聘信息，请各位员工及时查看！", "UTF-8");

        String str_content="各位员工大家好：\n\t刚刚发布了新的招聘信息，" +
				"请查看，有合适的麻烦推荐一下，详情点击" +
				"<a href=\"http://localhost:8080/HR/userlogin.do\">招聘信息</a>";
        // 5. Content: 邮件正文（可以使用html标签）（内容有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改发送内容）
        message.setContent(str_content, "text/html;charset=UTF-8");

        // 6. 设置发件时间
        message.setSentDate(new Date());

        // 7. 保存设置
        message.saveChanges();

        return message;
    }

	public List<RecruitInfo> viewRecruitInfo() {
		return recruitDao.viewRecruitInfo();
	}

	public List getRecruitInfo() {
		return recruitDao.getRecruitInfo();
	}

//	public int getRecruitIdFromSequence() {
//		return recruitDao.getRecruitIdFromSequence();
//	}

	public RecruitInfo viewRecruitById(int recruitId) {
		return recruitDao.viewRecruitById(recruitId);
	}

	public boolean deleteRecruitInfo(String[] recruitId) {
		return recruitDao.deleteRecruitInfo(recruitId);
	}

	public List<RecruitInfo> viewRecruitInfoByRole(int empId) {
		return recruitDao.viewRecruitInfoByRole(empId);
	}
	List<String> getAllmailBox(){
		return recruitDao.getAllmailBox();
	}

	public boolean updateRecruitInfo(RecruitInfo recruit) {
		return recruitDao.updateRecruitInfo(recruit);
	}

	public boolean modifyMark() {
		return recruitDao.modifyMark();
	}

	public SplitPage getAllRecruit(int curentPage) {
		return recruitDao.getAllRecruit(curentPage);
	}
}