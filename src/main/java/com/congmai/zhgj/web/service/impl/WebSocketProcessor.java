package com.congmai.zhgj.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;

import com.congmai.zhgj.web.model.Message;
import com.congmai.zhgj.web.model.MessageCode;
import com.congmai.zhgj.web.model.MessageTemplate;
import com.congmai.zhgj.web.service.MessageProcessor;
import com.congmai.zhgj.web.websocket.SystemWebSocketHandler;

@Service
public class WebSocketProcessor implements MessageProcessor {
	
	private static final String DEFAULT_MSG_TEMPLATE = "WebSocket";
	
	@Autowired
	private SystemWebSocketHandler handler;
	
	private static String message1 = "${paramer_a}邀请您成为他的客户";
	private static String message2 = "${paramer_a}邀请您成为他的供应商";
	private static String message3 = "${paramer_a}已${paramer_b}您的邀请";
	private static String message4 = "${paramer_d}被${paramer_a}绑定为您的业务联系人";
	private static String message5 = "您已绑定成为${paramer_b}的业务员";
	private static String message6 = "您被${paramer_a}邀请为业务员";
	private static String message7 = "${paramer_a}邀请${paramer_c}成为业务联系人";
	private static String message8 = "${paramer_a}向您发布了一条询价请求，询价单号${paramer_b}";
	private static String message9 = "${paramer_a}向${paramer_c}发布了一条询价单，询价单号${paramer_b}";
	private static String message10 = "${paramer_c}收到了${paramer_a}一条询价请求，询价单号${paramer_b}";
	private static String message11 = "${paramer_a}发布了一条询价待审核";
	private static String message12 = "您的询报价${paramer_a}${paramer_b}通过审核";
	private static String message13 = "您的询报价${paramer_a}收到了${paramer_b}一条报价单";
	private static String message14 = "${paramer_c}收到了${paramer_b}一条报价单，询价单号${paramer_a}";
	private static String message15 = "${paramer_a}${paramer_b}了您的报价单，询价单号${paramer_c}";
	private static String message16 = "${paramer_a}${paramer_b}了${paramer_d}的报价单，询价单号${paramer_c}";
	private static String message17 = "${paramer_a}向${paramer_b}发布了一条订单，订单号${paramer_c}";
	private static String message18 = "${paramer_a}向您发起了订单，订单号${paramer_c}";
	private static String message19 = "${paramer_a}${paramer_b}了${paramer_c}的订单号${paramer_d}";
	private static String message20 = "${paramer_a}${paramer_b}了您的订单${paramer_d}";
	private static String message21 = "${paramer_a}对订单号${paramer_b}添加了发货，发货单号${paramer_c}";
	private static String message22 = "${paramer_a}对发货单${paramer_c}确认了收货";
	private static String message23 = "${paramer_a}向您发起了退货单，退货单号${paramer_c}";
	private static String message24 = "${paramer_a}${paramer_b}了${paramer_c}的退货单${paramer_d}";
	private static String message25 = "${paramer_a}${paramer_b}了您的退货单${paramer_d}";
	private static String message26 = "${paramer_a}向您发起了结算，结算单号${paramer_d}";
	private static String message27 = "${paramer_a}${paramer_b}了您的结算单${paramer_d}";
	private static String message28 = "${paramer_a}向您发起了付款确认，付款单号${paramer_d}";
	private static String message29 = "${paramer_a}${paramer_b}了您的付款确认，付款单号${paramer_d}";
	private static String message30 = "${paramer_a}新建产品${paramer_d}提交审核";
	private static String message31 = "您新建的产品${paramer_d}被平台${paramer_b}申请";
	private static String message32 = "${paramer_a}提交审核材料";
	private static String message33= "您的授信额度为${paramer_a},已用额度为${paramer_b},剩余额度占比为${paramer_c}";//提醒二级业务员剩余额度信息
	private static String message34= "您的授信额度为${paramer_a},已用额度为${paramer_b},额度不够";//提醒一级业务员剩余额度信息
	
	static{
		MessageTemplate.register("1", "WebSocket", null, message1);
		MessageTemplate.register("2", "WebSocket", null, message2);
		MessageTemplate.register("3", "WebSocket", null, message3);
		MessageTemplate.register("4", "WebSocket", null, message4);
		MessageTemplate.register("5", "WebSocket", null, message5);
		MessageTemplate.register("6", "WebSocket", null, message6);
		MessageTemplate.register("7", "WebSocket", null, message7);
		MessageTemplate.register("8", "WebSocket", null, message8);
		MessageTemplate.register("9", "WebSocket", null, message9);
		MessageTemplate.register("10", "WebSocket", null, message10);
		MessageTemplate.register("11", "WebSocket", null, message11);
		MessageTemplate.register("12", "WebSocket", null, message12);
		MessageTemplate.register("13", "WebSocket", null, message13);
		MessageTemplate.register("14", "WebSocket", null, message14);
		MessageTemplate.register("15", "WebSocket", null, message15);
		MessageTemplate.register("16", "WebSocket", null, message16);
		MessageTemplate.register("17", "WebSocket", null, message17);
		MessageTemplate.register("18", "WebSocket", null, message18);
		MessageTemplate.register("19", "WebSocket", null, message19);
		MessageTemplate.register("20", "WebSocket", null, message20);
		MessageTemplate.register("21", "WebSocket", null, message21);
		MessageTemplate.register("22", "WebSocket", null, message22);
		MessageTemplate.register("23", "WebSocket", null, message23);
		MessageTemplate.register("24", "WebSocket", null, message24);
		MessageTemplate.register("25", "WebSocket", null, message25);
		MessageTemplate.register("26", "WebSocket", null, message26);
		MessageTemplate.register("27", "WebSocket", null, message27);
		MessageTemplate.register("28", "WebSocket", null, message28);
		MessageTemplate.register("29", "WebSocket", null, message29);
		MessageTemplate.register("30", "WebSocket", null, message30);
		MessageTemplate.register("31", "WebSocket", null, message31);
		MessageTemplate.register("32", "WebSocket", null, message32);
		MessageTemplate.register("33", "WebSocket", null, message33);
		MessageTemplate.register("34", "WebSocket", null, message34);

	}
	
	@Override
	public void process(Message messageVO) {
		//messageVO.setContext(MessageTemplate.getContent(messageVO.getTempleteType(), DEFAULT_MSG_TEMPLATE, messageVO.getProperties()));
		//messageVO.setSerialNum(GUID.generateGUID());
		//WebSocketTest ws = new WebSocketTest();
		MessageCode message = new MessageCode();
		message.setContent("测试收到消息没");
		TextMessage message2 = new TextMessage("测试收到消息没");
		message.setContent("测试收到消息没");
		handler.sendMessageToUser("37", message2);
		
	}

}
