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
	private static String message01 = "尊敬的${paramer_a}，您好！</br>${paramer_b}新建采购订单&nbsp;${paramer_c}&nbsp;等待您的审批。"+
									 "<a href='javascript:;' ui-sref=${paramer_d} onclick=readAndClose('${paramer_f}')>马上处理</a> </br>备注：${paramer_e}。</br>祝您工作愉快！";
	// 采购订单驳回消息模板
	private static String message02 = "尊敬的${paramer_a}，您好！</br>您的采购订单&nbsp;${paramer_b}&nbsp;被${paramer_c}驳回审批。"+
			"<a href='javascript:;' ui-sref=${paramer_d} onclick=readAndClose('${paramer_e}')>查看</a> </br>祝您工作愉快！";
	// 采购订单单个节点审核通过消息模板
	private static String message03 = "尊敬的${paramer_a}，您好！</br>您的采购订单&nbsp;${paramer_b}&nbsp;被${paramer_c}通过审批。"+
			"<a href='javascript:;' ui-sref=${paramer_d} onclick=readAndClose('${paramer_e}')>查看</a> </br>备注：${paramer_f}。</br>祝您工作愉快！";	
	// 采购订单审核通过消息模板
	private static String message04 = "尊敬的${paramer_a}，您好！</br>您的采购订单&nbsp;${paramer_b}&nbsp;被${paramer_c}通过审批并发布成功，等待对方接收。"+
			"<a href='javascript:;' ui-sref=${paramer_d} onclick=readAndClose('${paramer_e}')>查看</a> </br>备注：${paramer_f}。</br>祝您工作愉快！";
	// 采购订单待确认消息模板（发给供应商）
	private static String message05 = "尊敬的${paramer_a}，您好！</br>${paramer_b}新建采购订单&nbsp;${paramer_c}&nbsp;等待您的确认。"+
				"<a href='javascript:;' ui-sref=${paramer_d} onclick=readAndClose('${paramer_e}')>马上处理</a> </br>祝您工作愉快！";
	// 采购订单通过消息模板
	private static String message06 = "尊敬的${paramer_a}，您好！</br>${paramer_b}确认接收采购订单&nbsp;${paramer_c}&nbsp;。"+
			"<a href='javascript:;' ui-sref=${paramer_d} onclick=readAndClose('${paramer_e}')>查看</a> </br>备注：${paramer_f}。</br>祝您工作愉快！";
	
	
	
	//逾期未到货
	private static String message71 = "尊敬的${paramer_a}，您好！</br>采购订单${paramer_b} 逾期未到货 。"+
			"<a href='javascript:;' ui-sref=${paramer_d} onclick=readAndClose('${paramer_e}')>查看</a>";

	//已收货
//	private static String message72 = "尊敬的${paramer_a}，您好！</br>${paramer_b}对采购订单${paramer_c}进行了发货，预计到货时间${paramer_d}，发货单号${paramer_e}。"+
//			"<a href='javascript:;' ui-sref=${paramer_d} onclick=readAndClose('${paramer_e}')>查看</a>";
	private static String message72 = "尊敬的${paramer_a}，您好！</br>${paramer_b}对发货单${paramer_c}进行了正常收货。"+
			"<a href='javascript:;' ui-sref=${paramer_d} onclick=readAndClose('${paramer_e}')>查看</a>";

	
	//已发货
	private static String message81 = "尊敬的${paramer_a}，您好！</br>销售订单号${paramer_b} 已进行部分发货，发货单号${paramer_c}。"+
			"<a href='javascript:;' ui-sref=${paramer_d} onclick=readAndClose('${paramer_e}')>查看</a>";

	//已出库检验（to采购）
	private static String message102 = "尊敬的${paramer_a}，您好！</br>发货单号${paramer_b} 已完成出库检验${paramer_c}件，不合格品 ${paramer_d} 件，关联销售订单号${paramer_e}。"+
			"<a href='javascript:;' ui-sref=${paramer_f} onclick=readAndClose('${paramer_g}')>查看</a>";
	
	//已出库检验(to供应)
	private static String message101 = "尊敬的${paramer_a}，您好！</br>发货单号${paramer_b}已完成出库检验 ${paramer_c} 件，请通知工作人员进行出库下架。"+
			"<a href='javascript:;' ui-sref=${paramer_d} onclick=readAndClose('${paramer_e}')>查看</a>";
	
	//已出库（to采购）
	private static String message93 = "尊敬的${paramer_a}，您好！</br>发货单号${paramer_b}已完成出库${paramer_c} 件，关联销售订单号${paramer_d}。"+
			"<a href='javascript:;' ui-sref=${paramer_e} onclick=readAndClose('${paramer_f}')>查看</a>";
	
	//已出库（to供应）
	private static String message94 = "尊敬的${paramer_a}，您好！</br>发货单号${paramer_b} 对方已完成销售出库${paramer_c} 件，关联采购订单号${paramer_d}。"+
			"<a href='javascript:;' ui-sref=${paramer_e} onclick=readAndClose('${paramer_f}')>查看</a>";
	
	//已入库检验（to采购）
	private static String message103 = "尊敬的${paramer_a}，您好！</br>收货单号${paramer_b}已完成检验 ${paramer_c} 件，请通知通知工作人员进行入库上架。"+
			"<a href='javascript:;' ui-sref=${paramer_d} onclick=readAndClose('${paramer_e}')>查看</a>";
	//已入库检验（to供应）
	private static String message104 = "尊敬的${paramer_a}，您好！</br>发货单号${paramer_b}已完成入库检验${paramer_c} 件，不合格品${paramer_d} 件，关联销售订单号${paramer_e}。"+
			"<a href='javascript:;' ui-sref=${paramer_f} onclick=readAndClose('${paramer_g}')>查看</a>";
	
	//已入库（to采购）
	private static String message91 = "尊敬的${paramer_a}，您好！</br>收货单号${paramer_b} 已完成入库${paramer_c} 件，关联采购订单号${paramer_d}。"+
			"<a href='javascript:;' ui-sref=${paramer_e} onclick=readAndClose('${paramer_f}')>查看</a>";
	
	//已入库（to供应）
	private static String message92 = "尊敬的${paramer_a}，您好！</br>发货单号${paramer_b}对方已完成入库 ${paramer_c} 件，关联销售订单号${paramer_d}。"+
			"<a href='javascript:;' ui-sref=${paramer_e} onclick=readAndClose('${paramer_f}')>查看</a>";

	
	static{
		MessageTemplate.register("01", DEFAULT_MSG_TEMPLATE, null, message01);
		MessageTemplate.register("02", DEFAULT_MSG_TEMPLATE, null, message02);
		MessageTemplate.register("03", DEFAULT_MSG_TEMPLATE, null, message03);
		MessageTemplate.register("04", DEFAULT_MSG_TEMPLATE, null, message04);
		MessageTemplate.register("05", DEFAULT_MSG_TEMPLATE, null, message05);
		MessageTemplate.register("06", DEFAULT_MSG_TEMPLATE, null, message06);
		MessageTemplate.register("71", DEFAULT_MSG_TEMPLATE, null, message71);
		MessageTemplate.register("72", DEFAULT_MSG_TEMPLATE, null, message72);
		
		MessageTemplate.register("81", DEFAULT_MSG_TEMPLATE, null, message81);
		MessageTemplate.register("91", DEFAULT_MSG_TEMPLATE, null, message91);
		MessageTemplate.register("92", DEFAULT_MSG_TEMPLATE, null, message92);
		MessageTemplate.register("93", DEFAULT_MSG_TEMPLATE, null, message93);
		MessageTemplate.register("94", DEFAULT_MSG_TEMPLATE, null, message94);
		MessageTemplate.register("101", DEFAULT_MSG_TEMPLATE, null, message101);
		MessageTemplate.register("102", DEFAULT_MSG_TEMPLATE, null, message102);
		MessageTemplate.register("103", DEFAULT_MSG_TEMPLATE, null, message103);
		MessageTemplate.register("104", DEFAULT_MSG_TEMPLATE, null, message104);

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
