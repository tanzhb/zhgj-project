package com.congmai.zhgj.web.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

import com.congmai.zhgj.core.util.ApplicationContextHelper;
import com.congmai.zhgj.web.model.Message;
import com.congmai.zhgj.web.service.MessageProcessor;
import com.congmai.zhgj.web.service.impl.WebSocketProcessor;


public class SendMessageListener implements  ApplicationListener<SendMessageEvent> {

	@Autowired
	private MessageProcessor messageProcessor = null;

	@Override
	public void onApplicationEvent(SendMessageEvent event) {
		if("saveCompany".equals(event.getAction())){//供应商新增产品审核
			saveComanyNewMessage(event);
		}
		
	}
	private void saveComanyNewMessage(SendMessageEvent event) {
		messageProcessor = ApplicationContextHelper.getBean(WebSocketProcessor.class);
		Message messageVO = new Message();
		messageVO.setActionName(event.getAction());
		messageVO.setReadFlg("0");
		messageVO.setDelFlg("0");
		messageProcessor.process(null);
		/*if(merchant!=null){
			messageVO.setCreator(merchant.getUpdater());
			messageVO.setObjectSerial(merchant.getComId());
			Properties properties = new Properties();

			if(merchant.getComName()!=null){
				properties.put("paramer_a", merchant.getComName());
			}else{
				properties.put("paramer_a", "");
			}
			messageVO.setProperties(properties);
			
			messageVO.setMessageType("平台商户");
			messageVO.setTempleteType("32");
			List<SysUserVO> sysUserList = businessUserService.findALLComUser();
			for(SysUserVO user : sysUserList){
				messageVO.setReceiverId(user.getUserId());
				messageService.releaseNewMessage(messageVO);
			}
		}*/
	}

}