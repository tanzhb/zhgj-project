package com.congmai.zhgj.web.service;

import org.springframework.stereotype.Service;

import com.congmai.zhgj.web.model.Message;

public interface MessageProcessor {
	public void process(Message messageVO);
	
}