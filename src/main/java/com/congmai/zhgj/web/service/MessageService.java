package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.feature.orm.mybatis.Page;
import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.Message;
import com.congmai.zhgj.web.model.Notice;

public interface MessageService extends GenericService<Message, String>{

	void deleteBatch(List<String> serialNumArray, String userName);

	Page<Message> selectSystemMessageByPage(Message message, String string);
	
	Page<Message> selectBusinessMessageByPage(Message message, String string);

	void readMessage(String serialNum, String string);

	void insertBatch(Message messageVO);

}
