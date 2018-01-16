package com.congmai.zhgj.web.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.congmai.zhgj.core.feature.orm.mybatis.Page;
import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.core.util.MessageConstants;
import com.congmai.zhgj.web.dao.MessageMapper;
import com.congmai.zhgj.web.model.Message;
import com.congmai.zhgj.web.model.MessageExample;
import com.congmai.zhgj.web.model.MessageExample.Criteria;
import com.congmai.zhgj.web.service.MessageService;
@Service
public class MessageServiceImpl extends GenericServiceImpl<Message, String> implements
		MessageService {

	@Autowired
	private MessageMapper messageMapper;
	
	@Override
	public GenericDao<Message, String> getDao() {
		
		return this.messageMapper;
	}

	@Override
	public void deleteBatch(List<String> serialNumArray, String userName) {
		MessageExample messageExample = new MessageExample();
		messageExample.createCriteria().andSerialNumIn(serialNumArray);
		Message message = new Message();
		message.setDelFlg("1"); //删除
		message.setUpdater(userName);
		message.setUpdateTime(new Date());
		messageMapper.updateByExampleSelective(message, messageExample);
		
	}

	@Override
	public Page<Message> selectSystemMessageByPage(Message message,
			String userId) {
		message.setMessageType(MessageConstants.SYSTEM_MESSAGE);
		message.setReceiverId(userId);
		//message.setPageIndex(message.getPageIndex()-1);
		message.setStart((message.getPageIndex()-1)*message.getPageSize());
		List<Message> messages = messageMapper.findMessageList(message);
		int count = messageMapper.countMessageList(message);
		Page<Message> page = new Page<Message>(message.getPageIndex(),message.getPageSize());
		page.setResult(messages);
		page.setTotalCount(count);
		return page;
	}

	@Override
	public Page<Message> selectBusinessMessageByPage(Message message,
			String userId) {
		message.setMessageType(MessageConstants.BUSSINESS_MESSAGE);
		message.setReceiverId(userId);
		message.setStart((message.getPageIndex()-1)*message.getPageSize());
		List<Message> messages = messageMapper.findMessageList(message);
		int count = messageMapper.countMessageList(message);
		Page<Message> page = new Page<Message>(message.getPageIndex(),message.getPageSize());
		page.setResult(messages);
		page.setTotalCount(count);
		return page;
	}

	@Override
	public void readMessage(String serialNum, String userName) {
		Message message = new Message();
		message.setSerialNum(serialNum);
		message.setReadFlg("1");
		message.setUpdater(userName);
		message.setUpdateTime(new Date());
		messageMapper.updateByPrimaryKeySelective(message);
	}

	@Override
	public void insertBatch(Message messageVO) {
		if(CollectionUtils.isNotEmpty(messageVO.getReceiverIds())){
			for(String receiverId : messageVO.getReceiverIds()){
				messageVO.setReceiverId(receiverId);
				messageVO.setSerialNum(ApplicationUtils.random32UUID());
				messageMapper.insert(messageVO);
			}
		}
		
	}

	@Override
	public int messageSize(Integer userId, String messageType) {
		MessageExample example = new MessageExample();
		Criteria c = example.createCriteria();
		if(messageType != null){
			c.andMessageTypeEqualTo(messageType);
		}
		c.andReceiverIdEqualTo(userId.toString()).andReadFlgEqualTo("0");
		return messageMapper.countByExample(example);
	}

	@Override
	public void changeReadFlg(String serialNum, String userName) {
		Message message = messageMapper.selectByPrimaryKey(serialNum);
		if("1".equals(message.getReadFlg())){
			message.setReadFlg("0");
		}else{
			message.setReadFlg("1");
		}
		message.setUpdater(userName);
		message.setUpdateTime(new Date());
		messageMapper.updateByPrimaryKeySelective(message);
		
	}

}
