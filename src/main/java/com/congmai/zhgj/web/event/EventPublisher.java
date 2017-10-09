package com.congmai.zhgj.web.event;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class EventPublisher implements  ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}
	

	
	public void publicSendMessageEvent(SendMessageEvent sendMessageEvent){
		applicationContext.publishEvent(sendMessageEvent);
	}

}
