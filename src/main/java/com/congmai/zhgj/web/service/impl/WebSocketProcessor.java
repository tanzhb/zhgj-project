package com.congmai.zhgj.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;

import com.alibaba.fastjson.JSONObject;
import com.congmai.zhgj.web.model.Message;
import com.congmai.zhgj.web.model.MessageTemplate;
import com.congmai.zhgj.web.service.MessageProcessor;
import com.congmai.zhgj.web.websocket.SystemWebSocketHandler;

@Service
public class WebSocketProcessor implements MessageProcessor {
	
	private static final String DEFAULT_MSG_TEMPLATE = "WebSocket";
	
	@Autowired
	private SystemWebSocketHandler handler;
	
	//订单申请审批提示
	private static String message1 = "尊敬的${paramer_a}，您好！</br>${paramer_b}新建采购订单&nbsp;${paramer_c}&nbsp;等待您的审批。<a href='javascript:;' ui-sref=${paramer_d} onclick=readAndClose('${paramer_f}')>马上处理</a> </br>备注：${paramer_e}。</br>祝您工作愉快！";
	
	static{
		MessageTemplate.register("1", DEFAULT_MSG_TEMPLATE, null, message1);

	}
	
	@Override
	public void process(Message messageVO) {
		
		messageVO.setContext(MessageTemplate.getContent(messageVO.getTempleteType(), DEFAULT_MSG_TEMPLATE, messageVO.getProperties()));
		TextMessage message2 = new TextMessage(JSONObject.toJSON(messageVO).toString());
		handler.sendMessageToUser(messageVO.getReceiverId(), message2);
		
	}

}
