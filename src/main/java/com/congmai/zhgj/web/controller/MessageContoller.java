package com.congmai.zhgj.web.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.congmai.zhgj.core.feature.orm.mybatis.Page;
import com.congmai.zhgj.core.util.MessageConstants;
import com.congmai.zhgj.core.util.UserUtil;
import com.congmai.zhgj.web.model.BaseVO;
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
   /* @RequestMapping(value = "/endTask/{businessType}")
    @ResponseBody
    public ResponseEntity<Map<String,Object>> findFinishedTaskInstances(@PathVariable("businessType") String businessType) throws Exception {
    	User user = UserUtil.getUserFromSession();
    	List<BaseVO> taskList = this.processService.findFinishedTaskInstances(user);
    	List<Object> jsonList=new ArrayList<Object>(); 
    	for(BaseVO base : taskList){
    		Map<String, Object> map = new HashMap<String, Object>();
    		map.put("businessType", base.getBusinessType());
    		map.put("userName", base.getUser_name());
    		map.put("title", base.getTitle());
    		map.put("taskId", base.getHistoricTaskInstance().getId());
    		map.put("processInstanceId", base.getHistoricTaskInstance().getProcessInstanceId());
    		map.put("startTime", base.getHistoricTaskInstance().getStartTime());
    		map.put("claimTime", base.getHistoricTaskInstance().getClaimTime());
    		map.put("endTime", base.getHistoricTaskInstance().getEndTime());
    		map.put("deleteReason", base.getHistoricTaskInstance().getDeleteReason());
    		map.put("version", base.getProcessDefinition().getVersion());
    		if(!"All".equals(businessType)){
				if(businessType.equals(base.getBusinessType())){//根据流程类型添加到已办事项
					jsonList.add(map);
				}
			}else jsonList.add(map);//进入首页时，所有流程类型添加到已办事项
    	}
    	
    	Map<String,Object> pageMap = new HashMap<String,Object>();
		pageMap.put("draw", 1);
		pageMap.put("recordsTotal", jsonList==null?0:jsonList.size());
		pageMap.put("recordsFiltered", jsonList==null?0:jsonList.size());
		pageMap.put("data", jsonList);
    	
		return new ResponseEntity<Map<String,Object>>(pageMap, HttpStatus.OK);
    }*/
   /* @RequestMapping(value = "/endTask/{businessType}")
    @ResponseBody
    public ResponseEntity<Map<String,Object>> findFinishedTaskInstances(@PathVariable("businessType") String businessType) throws Exception {
    	User user = UserUtil.getUserFromSession();
    	List<BaseVO> taskList = this.processService.findFinishedTaskInstances(user);
    	List<Object> jsonList=new ArrayList<Object>(); 
    	for(BaseVO base : taskList){
    		Map<String, Object> map = new HashMap<String, Object>();
    		map.put("businessType", base.getBusinessType());
    		map.put("userName", base.getUser_name());
    		map.put("title", base.getTitle());
    		map.put("taskId", base.getHistoricTaskInstance().getId());
    		map.put("processInstanceId", base.getHistoricTaskInstance().getProcessInstanceId());
    		map.put("startTime", base.getHistoricTaskInstance().getStartTime());
    		map.put("claimTime", base.getHistoricTaskInstance().getClaimTime());
    		map.put("endTime", base.getHistoricTaskInstance().getEndTime());
    		map.put("deleteReason", base.getHistoricTaskInstance().getDeleteReason());
    		map.put("version", base.getProcessDefinition().getVersion());
    		if(!"All".equals(businessType)){
				if(businessType.equals(base.getBusinessType())){//根据流程类型添加到已办事项
					jsonList.add(map);
				}
			}else jsonList.add(map);//进入首页时，所有流程类型添加到已办事项
    	}
    	
    	Map<String,Object> pageMap = new HashMap<String,Object>();
		pageMap.put("draw", 1);
		pageMap.put("recordsTotal", jsonList==null?0:jsonList.size());
		pageMap.put("recordsFiltered", jsonList==null?0:jsonList.size());
		pageMap.put("data", jsonList);
    	
		return new ResponseEntity<Map<String,Object>>(pageMap, HttpStatus.OK);
    }*/
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
    		logger.warn(e.getMessage(), e);
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
    		logger.warn(e.getMessage(), e);
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
    		logger.warn(e.getMessage(), e);
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
    		logger.warn(e.getMessage(), e);
    	}
    	return String.valueOf(flag);
    }
    
    
    /**
     * @Description (新消息个数)
     * @param request
     * @return
     */
    @RequestMapping(value="newMessageCount")
    @ResponseBody
    public String newMessageCount(Map<String, Object> map,HttpServletRequest request) {
    	int flag = 0; //默认失败
    	try{
    		User user = UserUtil.getUserFromSession();
    		if(user != null){
    			flag = messageService.messageSize(user.getUserId(),null);
    		}
    	}catch(Exception e){
    		logger.warn(e.getMessage(), e);
    	}
    	return String.valueOf(flag);
    }
    /**
     * @Description (修改消息已读未读状态)
     * @param request
     * @return
     */
    @RequestMapping(value="changeReadFlg",method=RequestMethod.POST)
    @ResponseBody
    public String changeReadFlg(Map<String, Object> map,@RequestBody String serialNum,HttpServletRequest request) {
    	String flag = "0"; //默认失败
    	try{
    		if(StringUtils.isNotEmpty(serialNum)){
    			messageService.changeReadFlg(serialNum,UserUtil.getUserFromSession().getUserName());
    		}
    	}catch(Exception e){
    		logger.warn(e.getMessage(), e);
    		flag = "1";
    	}
    	return flag;
    }

}
