package com.congmai.zhgj.core.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



/**
 * @ClassName SendEmail
 * @Description TODO(发送邮件)
 * @author zhaichao
 * @Date 2018年2月1日 上午9:34:57
 * @version 1.0.0
 */
public class SendEmail {
//	MailSenderInfo mailInfo = new MailSenderInfo(); 
//	mailInfo.setMailServerHost("121.195.178.51");//邮件服务器ip地址。smtp.163.com,  
//	mailInfo.setMailServerPort("25");//端口  
//	mailInfo.setValidate(true); 
//	mailInfo.setUserName("xiongcheng@126.com");//能够登录126的邮箱  
//	mailInfo.setPassword("*****");//密码  
//	mailInfo.setFromAddress("****@163.com");//显示发送发邮箱地址  
//	mailInfo.setToAddress("****@126.com");//接收邮件地址  
//	mailInfo.setSubject("标题"); 
//	mailInfo.setContent("发送内容");// 这个类主要来发送邮件  
//	SimpleMailSender sms = new SimpleMailSender();// 发送html格式  
//	return sms.sendHtmlMail(mailInfo); 
	private Log log = LogFactory.getLog(SendEmail.class);
	private static String smtphost = "smtp.scmyun.com"; // 发送邮件服务器
	private static String user = "service@scmyun.com"; // 邮件服务器登录用户名
	private static String password = "pass@1234"; // 邮件服务器登录密码
	private static String from = "service@scmyun.com"; //
	
//	private static String smtphost = "smtp.163.com"; // 鍙戦�閭欢鏈嶅姟鍣�
//	private static String user = "easysrmemail@163.com"; // 閭欢鏈嶅姟鍣ㄧ櫥褰曠敤鎴峰悕
//	private static String password = "2014_easysrm"; // 閭欢鏈嶅姟鍣ㄧ櫥褰曞瘑鐮�
//	private static String from = "easysrmemail@163.com"; // 
//	private static String to = "568398116@qq.com"; // 收件人邮件地址
	//private static String subject = "测试"; // 邮件标题
//	private static String content = "测试样例邮箱<a href='http://www.baidu.com'>http://www.baidu.com</a>"; // 邮件内容
	/**
	 * 以HTML格式发送邮件
	 * 
	 * @param tobody 发往的邮箱，subject发送的主题，content发送的内容
	 *            待发送的邮件信息
	 * @throws UnsupportedEncodingException 
	 */ 
	public static void sendHtmlMail(String tobody,String subject,String content) { 
		String emailServerURL=System.getProperty("emailServerURL");
		System.out.print("emailServerURL参数值："+emailServerURL);
		if(emailServerURL != null){
			String location;
			try {
				location = emailServerURL+"sendEmailService/sendEmail.action?userName=SCMyun&&password=Develop"+"&email="+tobody+"&subject="
				    +URLEncoder.encode(URLEncoder.encode(subject, "UTF-8"),"UTF-8")
				    +"&content="+URLEncoder.encode(URLEncoder.encode(content, "UTF-8"),"UTF-8");
				httpURLConectionGET(location);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
	    Properties pro = new Properties();
	    pro.put("mail.smtp.host", smtphost);//发送邮箱服务器
	    pro.put("mail.smtp.auth", "true");//是否需要校验
	    Session sendMailSession = Session.getInstance(pro,null); 
	    try { 
	        // 根据session创建一个邮件消息  
	    	MimeMessage mailMessage = new MimeMessage(sendMailSession); 
	        // 创建邮件发送者地址  
	        InternetAddress fromAddress = new InternetAddress(from);
	        // 设置邮件消息的发送者  
	        mailMessage.setFrom(fromAddress); 
	        // 创建邮件的接收者地址，并设置到邮件消息中  
	        InternetAddress toAddress = new InternetAddress(tobody);
	        // Message.RecipientType.TO属性表示接收者的类型为TO  
	        mailMessage.addRecipient(Message.RecipientType.TO, toAddress);
	        // 设置邮件消息的主题  
	        mailMessage.setSubject(subject,"GBK"); 
	        // 设置邮件消息发送的时间  
	        mailMessage.setSentDate(new Date()); 
	        // MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象  
	        MimeMultipart mainPart = new MimeMultipart(); 
	        // 创建一个包含HTML内容的MimeBodyPart  
	        MimeBodyPart html = new MimeBodyPart(); 
	        // 设置HTML内容  
	        html.setContent(content, "text/html; charset=GBK"); 
	        mainPart.addBodyPart(html); 
	        // 将MiniMultipart对象设置为邮件内容  
	        mailMessage.setContent(mainPart); 
	        // 发送邮件  
	        Transport transport = sendMailSession.getTransport("smtp");
	        transport.connect(smtphost, user, password);
	        transport.sendMessage(mailMessage,mailMessage
	        		.getRecipients(Message.RecipientType.TO)); 
	        transport.close();
	    } catch (MessagingException ex) { 
	        if (ex.getCause() instanceof SendFailedException) { 
	            //发送失败， 更新发送状态为1.  
	            ex.printStackTrace();
	        } 
	        ex.printStackTrace(); 
			//throw new Exception(ex.getMessage(), ex);
	    } 
		}
	} 
    /**
     * 接口调用 GET
     */
    public static void httpURLConectionGET(String GET_URL) {
        try {
            URL url = new URL(GET_URL);    // 把字符串转换为URL请求地址
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();// 打开连接
            connection.connect();// 连接会话
            // 获取输入流
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {// 循环读取流
                sb.append(line);
            }
            br.close();// 关闭流
            connection.disconnect();// 断开连接
            System.out.println(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("失败!");
        }
    }
    
	public static void main(String args[]) throws UnsupportedEncodingException{
		//sendHtmlMail("1923188401@qq.com","邮件测试","看看有没有钓鱼邮件内容");
//		Call webCall;
//		String location =SystemConstants.SERVICES_IP+"services/SCMyunSenEmail.do?wsdl";
//		try {
//			Service webServcie = new Service();
//			webCall = (Call) webServcie.createCall();
//			webCall.setTargetEndpointAddress(new URL(location));
//			webCall.removeAllParameters();
//			webCall.setOperationName("sendEmail");
//			webCall.addParameter("userName", XMLType.XSD_STRING,ParameterMode.IN);
//			webCall.addParameter("password", XMLType.XSD_STRING,ParameterMode.IN);
//			webCall.addParameter("email", XMLType.XSD_STRING,ParameterMode.IN);
//			webCall.addParameter("subject", XMLType.XSD_STRING,ParameterMode.IN);
//			webCall.addParameter("content", XMLType.XSD_STRING,ParameterMode.IN);
//			webCall.setReturnType(XMLType.SOAP_STRING);
//			@SuppressWarnings("unused")
//			String rtnXml = (String) webCall.invoke(new Object[] {"SCMyun" ,"Develop","809065327@qq.com","邮件测试","邮件内容"});
//			System.out.print("++++++++++++++++++++++++++++++++++++++++++++++++发送邮件："+rtnXml);
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.print("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//		}
	}
	
}
