package com.congmai.zhgj.web.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EventExample {
	private static ApplicationContext testContext;
	static{
		testContext = new ClassPathXmlApplicationContext("classpath:event.xml");
	}
	
	public static EventPublisher getEventPublisher(){
		return testContext.getBean(EventPublisher.class);
	}

}
