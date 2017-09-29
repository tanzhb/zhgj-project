package com.congmai.zhgj.core.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SimpleMailSender {
	// 用于给用户发送邮件的邮箱
	private static String from = "89731678@qq.com";

	// 授权码 从QQ上获得
	private static String password = "wxsrxurssepvbjbf";


	/*
	 * 重写run方法的实现，在run方法中发送邮件给指定的用户
	 * 
	 * @see java.lang.Thread#run()
	 */
	public static void main(String[] args) {
		sendqqmail("876351271@qq.com","hellow","qq右键啊");
	}

	public static MimeMessage createMimeMessage(Session session,
			String sendMail, String receiveMail,String title,String content) throws Exception {
		// 1. 创建一封邮件
		MimeMessage message = new MimeMessage(session);

		// 2. From: 发件人
		message.setFrom(new InternetAddress(sendMail, "中航国际", "UTF-8"));

		// 3. To: 收件人（可以增加多个收件人、抄送、密送）
		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(
				receiveMail, "用户名", "UTF-8"));

		// 4. Subject: 邮件主题
		message.setSubject(title, "UTF-8");

		// 5. Content: 邮件正文（可以使用html标签）
		message.setContent(content, "text/html;charset=UTF-8");

		// 6. 设置发件时间
		message.setSentDate(new Date());

		// 7. 保存设置
		message.saveChanges();

		return message;
	}

	public static void sendqqmail(String to,String title,String content) {
		try {

			Properties prop = new Properties();

			prop.setProperty("mail.transport.protocol", "smtp");

			prop.setProperty("mail.smtp.host", "smtp.qq.com");

			prop.setProperty("mail.smtp.auth", "true");

			final String smtpPort = "465";

			prop.setProperty("mail.smtp.port", smtpPort);

			prop.setProperty("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");

			prop.setProperty("mail.smtp.socketFactory.fallback", "false");

			prop.setProperty("mail.smtp.socketFactory.port", smtpPort);

			prop.setProperty("mail.debug", "true");

			Session session = Session.getDefaultInstance(prop);

			session.setDebug(true);

			MimeMessage message = createMimeMessage(session, from, to,title,content);

			Transport transport = session.getTransport();

			transport.connect(from, password);

			transport.sendMessage(message, message.getAllRecipients());

			transport.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
