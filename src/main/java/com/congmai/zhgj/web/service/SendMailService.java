package com.congmai.zhgj.web.service;

import com.congmai.zhgj.web.model.Message;

public interface SendMailService {
	public void sendMessageToUser(Message messageVO);
}