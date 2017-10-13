package com.congmai.zhgj.web.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.congmai.zhgj.core.feature.orm.mybatis.Page;
import com.congmai.zhgj.core.util.MessageConstants;
import com.congmai.zhgj.core.util.UserUtil;
import com.congmai.zhgj.web.model.Message;
import com.congmai.zhgj.web.model.User;
import com.congmai.zhgj.web.service.MessageService;
import com.congmai.zhgj.web.service.UserCompanyService;

@Controller
@RequestMapping("message")
public class MessageContoller {
	
	private Log logger = LogFactory.getLog(MessageContoller.class);
	
	@Autowired
	private MessageService messageService;
	

	 /**
     * @Description (跳转至个人公告页面)
     * @param request
     * @return
     */
    @RequestMapping(value="myMessage")
    public String myMessage(Map<String, Object> map,HttpServletRequest request) {
    	
    	return "message/myMessage";
    }
  
    
    @RequestMapping(value="systemMessageList",method=RequestMethod.POST)
    @ResponseBody
    public Page<Message> systemMessageList(@RequestBody Message message){
    	Page<Message> page = new Page<Message>();
    	try{
    		User user = UserUtil.getUserFromSession();
    		page = this.messageService.selectSystemMessageByPage(message, user.getUserId().toString());
    		if(CollectionUtils.isNotEmpty(page.getResult())){
    			for(Message n : page.getResult()){
    				//n.setRelaseDate(DateUtil.getInterval(DateUtil.format("yyyy-MM-dd HH:mm:ss",n.getUpdateTime())));
    			}
    		}
    	}catch(Exception e){
    		logger.warn(e.getMessage(), e);
    	}
    	return page;
    }
    
    @RequestMapping(value="businessMessageList",method=RequestMethod.POST)
    @ResponseBody
    public Page<Message> businessMessageList(@RequestBody Message message){
    	Page<Message> page = null;
    	try{
    		User user = UserUtil.getUserFromSession();
    		page = this.messageService.selectBusinessMessageByPage(message, user.getUserId().toString());
    		if(CollectionUtils.isNotEmpty(page.getResult())){
    			for(Message n : page.getResult()){
    				//n.setRelaseDate(DateUtil.getInterval(DateUtil.format("yyyy-MM-dd HH:mm:ss",n.getUpdateTime())));
    			}
    		}
    	}catch(Exception e){
    		logger.warn(e.getMessage(), e);
    	}
    	return page;
    }

    /**
     * @Description (删除消息)
     * @param request
     * @return
     */
    @RequestMapping(value="deleteMessageBatch",method=RequestMethod.POST)
    @ResponseBody
    public String deleteMessageBatch(Map<String, Object> map,@RequestBody String serialNums,HttpServletRequest request) {
    	String flag = "0"; //默认失败
    	try{
    		if(StringUtils.isNotEmpty(serialNums)){
    			List<String> serialNumArray  = Arrays.asList(serialNums.split(","));
    			messageService.deleteBatch(serialNumArray,UserUtil.getUserFromSession().getUserName());
    		}
    	}catch(Exception e){
    		System.out.println(e.getMessage());
    		flag = "1";
    	}
    	return flag;
    }
    
    /**
     * @Description (读消息)
     * @param request
     * @return
     */
    @RequestMapping(value="readMessage",method=RequestMethod.POST)
    @ResponseBody
    public String readMyMessage(Map<String, Object> map,@RequestBody String serialNum,HttpServletRequest request) {
    	String flag = "0"; //默认失败
    	try{
    		if(StringUtils.isNotEmpty(serialNum)){
    			if(serialNum.contains("=")){
    				serialNum = serialNum.replace("=", "");
    			}
    			messageService.readMessage(serialNum,UserUtil.getUserFromSession().getUserName());
    		}
    	}catch(Exception e){
    		System.out.println(e.getMessage());
    		flag = "1";
    	}
    	return flag;
    }
    
    
    /**
     * @Description (读消息)
     * @param request
     * @return
     */
    @RequestMapping(value="systemMessageSize")
    @ResponseBody
    public String systemMessageSize(Map<String, Object> map,HttpServletRequest request) {
    	int flag = 0; //默认失败
    	try{
    		User user = UserUtil.getUserFromSession();
    		if(user != null){
    		
    			flag = messageService.messageSize(user.getUserId(),MessageConstants.SYSTEM_MESSAGE);
    		}
    	}catch(Exception e){
    		System.out.println(e.getMessage());
    	}
    	return String.valueOf(flag);
    }
    /**
     * @Description (读消息)
     * @param request
     * @return
     */
    @RequestMapping(value="businessMessageSize")
    @ResponseBody
    public String businessMessageSize(Map<String, Object> map,HttpServletRequest request) {
    	int flag = 0; //默认失败
    	try{
    		User user = UserUtil.getUserFromSession();
    		if(user != null){
    			flag = messageService.messageSize(user.getUserId(),MessageConstants.BUSSINESS_MESSAGE);
    		}
    	}catch(Exception e){
    		System.out.println(e.getMessage());
    	}
    	return String.valueOf(flag);
    }
    
    
}
