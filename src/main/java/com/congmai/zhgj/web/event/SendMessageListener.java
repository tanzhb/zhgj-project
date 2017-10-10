package com.congmai.zhgj.web.event;

import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.congmai.zhgj.core.util.ApplicationContextHelper;
import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.core.util.MessageConstants;
import com.congmai.zhgj.core.util.UserUtil;
import com.congmai.zhgj.web.model.AlertVO;
import com.congmai.zhgj.web.model.Company;
import com.congmai.zhgj.web.model.CompanyAddress;
import com.congmai.zhgj.web.model.Message;
import com.congmai.zhgj.web.model.OrderInfo;
import com.congmai.zhgj.web.model.User;
import com.congmai.zhgj.web.service.MessageProcessor;
import com.congmai.zhgj.web.service.MessageService;
import com.congmai.zhgj.web.service.OrderService;
import com.congmai.zhgj.web.service.impl.WebSocketProcessor;

@Component
public class SendMessageListener implements  ApplicationListener<SendMessageEvent> {

	@Autowired
	private MessageProcessor messageProcessor;
	
	@Autowired
	private MessageService messageService;

	@Override
	public void onApplicationEvent(SendMessageEvent event) {
		
		if(MessageConstants.APPLY_BUY_ORDER.equals(event.getAction())){ //采购订单申请消息
			applyBuyOrderMessage(event);
		}
		
	}
	
	/**
	 * 
	 * @Description (采购订单申请消息)
	 * @param event
	 */
	private void applyBuyOrderMessage(SendMessageEvent event) {
		try {
			messageProcessor = ApplicationContextHelper.getBean(WebSocketProcessor.class);
			messageService =  ApplicationContextHelper.getBean(MessageService.class);
			User user = UserUtil.getUserFromSession();
			if(user != null){
				Message messageVO = new Message();
				messageVO.setSerialNum(ApplicationUtils.random32UUID());
				messageVO.setActionName(event.getAction());
				messageVO.setMessageType(MessageConstants.BUSSINESS_MESSAGE);
				messageVO.setTempleteType("1"); //采购订单申请消息
				messageVO.setReadFlg("0");
				messageVO.setDelFlg("0");
				OrderInfo order = (OrderInfo) event.getSource();
				messageVO.setObjectSerial(order.getSerialNum());
				messageVO.setReceiverId(order.getUserId().toString());
				Properties properties = new Properties();
				
				properties.put("paramer_a", order.getUser_name());
				properties.put("paramer_b", user.getUserName());
				properties.put("paramer_c", order.getOrderNum());
				properties.put("paramer_d", MessageConstants.APPLY_BUY_ORDER_URL);
				if(StringUtils.isNotBlank(order.getReason())){
					properties.put("paramer_e", order.getReason());
				}else{
					properties.put("paramer_e", "无");
				}
				
				messageVO.setProperties(properties);
				messageVO.setCreator(user.getUserName());
				messageVO.setCreateTime(new Date());
				messageVO.setUpdater(user.getUserName());
				messageVO.setUpdateTime(new Date());
				messageProcessor.process(messageVO);
				messageService.insert(messageVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}