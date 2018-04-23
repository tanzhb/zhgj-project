package com.congmai.zhgj.web.service.impl;

import org.springframework.stereotype.Service;

import com.congmai.zhgj.core.util.ApplicationContextHelper;
import com.congmai.zhgj.core.util.SendEmail;
import com.congmai.zhgj.web.model.Message;
import com.congmai.zhgj.web.model.MessageTemplate;
import com.congmai.zhgj.web.service.SendMailService;
import com.congmai.zhgj.web.service.UserService;

@Service
public class MailProcessor implements SendMailService {
	
	private static final String DEFAULT_MSG_TEMPLATE = "Mail";

	private UserService userService = null;
	
	//自主采购订单审批通过发送给相关人员
	private static String message22 = "尊敬的${paramer_a}，您好！</br>采购订单&nbsp;${paramer_b}&nbsp;通过审批并发布成功。"+
					" </br>祝您工作愉快！";
	
	static{
		MessageTemplate.register("22", DEFAULT_MSG_TEMPLATE, null, message22);
	}
	
	
	private void initService(){
		userService = (UserService) ApplicationContextHelper.getBean(UserService.class);
	}


	@Override
	public void sendMessageToUser(Message messageVO) {
		initService();
//		messageVO.setTitle(MessageTemplate.getTitle(messageVO.getTempleteType(), DEFAULT_MSG_TEMPLATE, messageVO.getProperties()));
		messageVO.setContext(MessageTemplate.getContent(messageVO.getTempleteType(), DEFAULT_MSG_TEMPLATE, messageVO.getProperties()));

		try {
			SendEmail.sendHtmlMail(userService.getUserInfo(Integer.parseInt(messageVO.getReceiverId())).getEmail(), "中航国际业务提醒", "<div>"+messageVO.getContext()+
					"</div><div>如果此邮件是误发给您，请谅解，并删除！</div><div>（本邮件由系统自动发出，请勿回复。）</div>");

		} catch (Exception e) {
			messageVO.setContext(messageVO.getContext()+"==>:发送失败");

			e.printStackTrace();
		}
	}

	
}
