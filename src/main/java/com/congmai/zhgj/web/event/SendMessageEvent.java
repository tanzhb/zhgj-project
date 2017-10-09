package com.congmai.zhgj.web.event;

import java.util.Date;

import org.springframework.context.ApplicationEvent;

import com.congmai.zhgj.web.model.AlertVO;

public class SendMessageEvent extends ApplicationEvent{

	private AlertVO alertVO;
	private Date eventDate = new Date();
	/*supply 供应商，
	product 	产品
	enquiry 询价
	quotation 报价
	order 订单
	deliver 交付
	statement 对账
	*/
	private String action; 
	
	public SendMessageEvent(Object source) {
		super(source);
		if(source instanceof AlertVO){
			this.alertVO = (AlertVO) source;
		}
	}
	
	public SendMessageEvent(Object source, String action) {
		this(source);
		this.action = action;
	}
	
	public AlertVO getAlertVO() {
		return alertVO;
	}

	public void setAlertVO(AlertVO alertVO) {
		this.alertVO = alertVO;
	}

	public Date getEventDate() {
		return eventDate;
	}


	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 5081960755039449257L;

}
