package com.congmai.zhgj.web.service;

import com.congmai.zhgj.web.model.Message;

public interface WebSocketService {
	public void sendMessageToUser(Message messageVO);
	
	public void sendMessageToUsers(Message messageVO);
	
	
	
}