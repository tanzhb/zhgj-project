package com.congmai.zhgj.web.service;

import com.congmai.zhgj.web.model.Message;

public interface MessageProcessor {
	public void sendMessageToUser(Message messageVO);
	
	public void sendMessageToUsers(Message messageVO);
	
	
	
}