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
	
	// 采购订单申请消息模板
	private static String message1 = "尊敬的${paramer_a}，您好！</br>${paramer_b}新建采购订单&nbsp;${paramer_c}&nbsp;等待您的审批。"+
									 "<a href='javascript:;' ui-sref=${paramer_d} onclick=readAndClose('${paramer_f}')>马上处理</a> </br>备注：${paramer_e}。</br>祝您工作愉快！";
	// 采购订单驳回消息模板
	private static String message2 = "尊敬的${paramer_a}，您好！</br>您的采购订单&nbsp;${paramer_b}&nbsp;被${paramer_c}驳回审批。"+
			"<a href='javascript:;' ui-sref=${paramer_d} onclick=readAndClose('${paramer_e}')>查看</a> </br>祝您工作愉快！";
	// 采购订单审核通过消息模板
	private static String message3 = "尊敬的${paramer_a}，您好！</br>您的采购订单&nbsp;${paramer_b}&nbsp;被${paramer_c}通过审批并发布成功，等待对方接收。"+
			"<a href='javascript:;' ui-sref=${paramer_d} onclick=readAndClose('${paramer_e}')>查看</a> </br>备注：${paramer_f}。</br>祝您工作愉快！";
	// 采购订单待确认消息模板（发给供应商）
	private static String message4 = "尊敬的${paramer_a}，您好！</br>${paramer_b}新建采购订单&nbsp;${paramer_c}&nbsp;等待您的确认。"+
				"<a href='javascript:;' ui-sref=${paramer_d} onclick=readAndClose('${paramer_e}')>马上处理</a> </br>祝您工作愉快！";
	// 采购订单通过消息模板
	private static String message5 = "尊敬的${paramer_a}，您好！</br>${paramer_b}确认接收采购订单&nbsp;${paramer_c}&nbsp;。"+
			"<a href='javascript:;' ui-sref=${paramer_d} onclick=readAndClose('${paramer_e}')>查看</a> </br>备注：${paramer_f}。</br>祝您工作愉快！";
	
	
	static{
		MessageTemplate.register("1", DEFAULT_MSG_TEMPLATE, null, message1);
		MessageTemplate.register("2", DEFAULT_MSG_TEMPLATE, null, message2);
		MessageTemplate.register("3", DEFAULT_MSG_TEMPLATE, null, message3);
		MessageTemplate.register("4", DEFAULT_MSG_TEMPLATE, null, message4);
		MessageTemplate.register("5", DEFAULT_MSG_TEMPLATE, null, message5);

	}
	
	@Override
	public void sendMessageToUser(Message messageVO) {
		
		messageVO.setContext(MessageTemplate.getContent(messageVO.getTempleteType(), DEFAULT_MSG_TEMPLATE, messageVO.getProperties()));
		TextMessage message2 = new TextMessage(JSONObject.toJSON(messageVO).toString());
		handler.sendMessageToUser(messageVO.getReceiverId(), message2);
		
	}
	
	@Override
	public void sendMessageToUsers(Message messageVO) {
		
		messageVO.setContext(MessageTemplate.getContent(messageVO.getTempleteType(), DEFAULT_MSG_TEMPLATE, messageVO.getProperties()));
		TextMessage message2 = new TextMessage(JSONObject.toJSON(messageVO).toString());
		handler.sendMessageToUsers(messageVO.getReceiverIds(), message2);
		
	}

}
