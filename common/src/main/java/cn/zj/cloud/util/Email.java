package cn.zj.cloud.util;

import javax.mail.internet.MimeMessage;


import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import cn.zj.cloud.constant.Constant;

public class Email {
	
	JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
	
	public Email() {
		senderImpl.setHost("smtp.qq.com");
		senderImpl.setUsername("71885882@qq.com");
		senderImpl.setPassword("adhiouvnrahwbjfa");
		senderImpl.setPort(465);
		senderImpl.setProtocol("smtps");
		
	}

	public void sendMail(String toEmailAddress, String id) {
		String htmlContent = "<html><title>User Resiter Information</title><body><a href='http://localhost:8098/user/{0}'>Click this link to confirm</a></body></html>";
		htmlContent = htmlContent.replaceFirst("{0}", id);
		System.out.println(htmlContent);
		try {
			// create html mail
			MimeMessage mailMessage = senderImpl.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true, "utf-8");
			// set sender address
			messageHelper.setFrom(senderImpl.getUsername());
			// set receiver address
			messageHelper.setTo(toEmailAddress);
			// Set email subject
			messageHelper.setSubject(Constant.CONFIRM_MAIL_SUBJECT);
			// Set email content
			messageHelper.setText(htmlContent, true);

			senderImpl.send(mailMessage);
			
		} catch (Exception e) {
			;
		}

	}
}
