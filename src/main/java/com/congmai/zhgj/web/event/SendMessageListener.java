package com.congmai.zhgj.web.event;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.congmai.zhgj.core.util.ApplicationContextHelper;
import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.core.util.Constants;
import com.congmai.zhgj.core.util.MessageConstants;
import com.congmai.zhgj.core.util.UserUtil;
import com.congmai.zhgj.web.model.AlertVO;
import com.congmai.zhgj.web.model.Company;
import com.congmai.zhgj.web.model.CompanyAddress;
import com.congmai.zhgj.web.model.Delivery;
import com.congmai.zhgj.web.model.Message;
import com.congmai.zhgj.web.model.OrderInfo;
import com.congmai.zhgj.web.model.TakeDelivery;
import com.congmai.zhgj.web.model.TakeDeliveryParams;
import com.congmai.zhgj.web.model.User;
import com.congmai.zhgj.web.model.UserCompanyKey;
import com.congmai.zhgj.web.service.ActRuTaskService;
import com.congmai.zhgj.web.service.CompanyService;
import com.congmai.zhgj.web.service.GroupService;
import com.congmai.zhgj.web.service.MessageProcessor;
import com.congmai.zhgj.web.service.MessageService;
import com.congmai.zhgj.web.service.OrderService;
import com.congmai.zhgj.web.service.UserCompanyService;
import com.congmai.zhgj.web.service.UserService;
import com.congmai.zhgj.web.service.impl.WebSocketProcessor;
/**
 * 
 * @ClassName SendMessageListener
 * @Description TODO(消息监听器)
 * @author likt
 * @Date 2017年10月12日 下午2:53:19
 * @version 1.0.0
 */
@Component
public class SendMessageListener implements  ApplicationListener<SendMessageEvent> {

	
	private MessageProcessor messageProcessor = null;
	
	private MessageService messageService = null;
	
	private ActRuTaskService actRuTaskService = null;
	
	private UserCompanyService userCompanyService = null;
	
	private CompanyService companyService = null;
	
	private UserService userService = null;
	
	private GroupService groupService = null;
	
	private OrderService orderService = null;
	
	private void initService(){
		messageProcessor = ApplicationContextHelper.getBean(WebSocketProcessor.class);
		messageService =  ApplicationContextHelper.getBean(MessageService.class);
		actRuTaskService = ApplicationContextHelper.getBean(ActRuTaskService.class);
		userCompanyService = ApplicationContextHelper.getBean(UserCompanyService.class);
		companyService = ApplicationContextHelper.getBean(CompanyService.class);
		userService =  ApplicationContextHelper.getBean(UserService.class);
		groupService =  ApplicationContextHelper.getBean(GroupService.class);
		orderService =  ApplicationContextHelper.getBean(OrderService.class);
	}

	@Override
	public void onApplicationEvent(SendMessageEvent event) {
		
		if(MessageConstants.APPLY_BUY_ORDER.equals(event.getAction())){ //采购订单申请消息
			applyBuyOrderMessage(event);
		}else if(MessageConstants.REFUSE_BUY_ORDER.equals(event.getAction())){ //采购订单被驳回消息
			refuseBuyOrderMessage(event);
		}else if(MessageConstants.AGREE_BUY_ORDER.equals(event.getAction())){ //采购订单审核通过消息
			agreeBuyOrderMessage(event);
		}else if(MessageConstants.CONFIRM_BUY_ORDER.equals(event.getAction())){ //采购订单待确认
			confirmBuyOrderMessage(event);
		}else if(MessageConstants.BE_CONFIRM_BUY_ORDER.equals(event.getAction())){ //采购订单被确认
			beConfirmBuyOrderMessage(event);
		}else if(MessageConstants.SINGLE_AGREE_BUY_ORDER.equals(event.getAction())){ //采购订单单个审批通过
			singleAgreeBuyOrderMessage(event);
		}else if(MessageConstants.NO_TAKE_DELIVERY.equals(event.getAction())){ //采购订单单个审批通过
			noTakeDeliveryMessage(event);
		}else if(MessageConstants.TAKE_DELIVERY.equals(event.getAction())){ //收货完成
			takeDeliveryMessage(event);
		}else if(MessageConstants.DELIVERY.equals(event.getAction())){ //发货完成
			deliveryMessage(event);
		}else if(MessageConstants.OUT_TO_BUY.equals(event.getAction())){ //出库完成（to采购）
			outToBuyMessage(event);
		}else if(MessageConstants.OUT_TO_SALE.equals(event.getAction())){ //出库完成（to供应）
			outToSaleMessage(event);
		}else if(MessageConstants.OUT_CHECK_TO_BUY.equals(event.getAction())){ //出库检验完成（to采购）
			outCheckToBuyMessage(event);
		}else if(MessageConstants.OUT_CHECK_TO_SALE.equals(event.getAction())){ //出库检验完成（to供应）
			outCheckToSaleMessage(event);
		}else if(MessageConstants.IN_CHECK_TO_BUY.equals(event.getAction())){ //入库检验完成（to采购）
			inCheckToBuyMessage(event);
		}else if(MessageConstants.IN_CHECK_TO_SALE.equals(event.getAction())){ //入库检验完成（to供应）
			inCheckToSaleMessage(event);
		}else if(MessageConstants.IN_TO_BUY.equals(event.getAction())){ //入库完成（to采购）
			inToBuyMessage(event);
		}else if(MessageConstants.IN_TO_SALE.equals(event.getAction())){ //入库完成（to供应）
			inToSaleMessage(event);
		}
		
	}
	

	/**
	 * 
	 * @Description (采购订单申请消息)
	 * @param event
	 */
	private void applyBuyOrderMessage(SendMessageEvent event) {
		try {
			initService();
			User user = UserUtil.getUserFromSession();
			if(user != null){
				OrderInfo order = (OrderInfo) event.getSource();
				
				if(order.getMaker()!=null){
					user = userService.selectByUsername(order.getMaker());
				}
				Message messageVO = this.createMessage(event,user);
				messageVO.setMessageType(MessageConstants.BUSSINESS_MESSAGE);
				messageVO.setTempleteType(MessageConstants.TEMP_APPLY_BUY_ORDER); //采购订单申请消息
				messageVO.setObjectSerial(order.getSerialNum());
				
				
				Properties properties = new Properties();
				User auditUser = actRuTaskService.getAuditUserByProcessInstanceId(order.getProcessInstanceId());
				if( auditUser!=null ){
					messageVO.setReceiverId(auditUser.getUserId().toString());
					properties.put("paramer_a", auditUser.getUserName());
				}else{
					throw new Exception("没有找到消息接受者！");
				}
				properties.put("paramer_b", user.getUserName());
				properties.put("paramer_c", order.getOrderNum());
				properties.put("paramer_d", MessageConstants.URL_APPLY_BUY_ORDER);
				properties.put("paramer_f", messageVO.getSerialNum());
				if(StringUtils.isNotBlank(order.getReason())){
					properties.put("paramer_e", order.getReason());
				}else{
					properties.put("paramer_e", "无");
				}
				messageVO.setProperties(properties);
				messageProcessor.sendMessageToUser(messageVO);
				messageService.insert(messageVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * 
	 * @Description (采购订单驳回消息)
	 * @param event
	 */
	private void refuseBuyOrderMessage(SendMessageEvent event) {
		try {
			initService();
			User user = UserUtil.getUserFromSession();
			if(user != null){
				Message messageVO = this.createMessage(event,user);
				
				messageVO.setMessageType(MessageConstants.SYSTEM_MESSAGE);
				messageVO.setTempleteType(MessageConstants.TEMP_REFUSE_BUY_ORDER); //采购订单申请消息
				OrderInfo order = (OrderInfo) event.getSource();
				messageVO.setObjectSerial(order.getSerialNum());
				
				
				Properties properties = new Properties();
				User auditUser = actRuTaskService.getAuditUserByProcessInstanceId(order.getProcessInstanceId());
				if( auditUser!=null ){
					messageVO.setReceiverId(auditUser.getUserId().toString());
					properties.put("paramer_a", auditUser.getUserName());
				}else{
					throw new Exception("没有找到消息接受者！");
				}
				properties.put("paramer_b", order.getOrderNum());
				properties.put("paramer_c", user.getUserName());
				properties.put("paramer_d", MessageConstants.URL_REFUSE_BUY_ORDER);
				properties.put("paramer_e", messageVO.getSerialNum());
				
				properties.put("paramer_f", "无"); //这里得去找审批评论
				
				messageVO.setProperties(properties);
				messageProcessor.sendMessageToUser(messageVO);
				messageService.insert(messageVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @Description (采购订单通过消息)
	 * @param event
	 */
	private void agreeBuyOrderMessage(SendMessageEvent event) {
		try {
			initService();
			User user = UserUtil.getUserFromSession();
			if(user != null){
				Message messageVO = this.createMessage(event,user);
				
				messageVO.setMessageType(MessageConstants.SYSTEM_MESSAGE);
				messageVO.setTempleteType(MessageConstants.TEMP_AGREE_BUY_ORDER); //采购订单申请消息
				OrderInfo order = (OrderInfo) event.getSource();
				messageVO.setObjectSerial(order.getSerialNum());
				
				
				Properties properties = new Properties();
				User maker = userService.selectByUsername(order.getMaker());
				if( maker!=null ){
					messageVO.setReceiverId(maker.getUserId().toString());
					properties.put("paramer_a", maker.getUserName());
				}else{
					throw new Exception("没有找到消息接受者！");
				}
				properties.put("paramer_b", order.getOrderNum());
				properties.put("paramer_c", user.getUserName());
				properties.put("paramer_d", MessageConstants.URL_AGREE_BUY_ORDER);
				properties.put("paramer_e", messageVO.getSerialNum());
				
				properties.put("paramer_f", "无"); //这里得去找审批评论
				
				messageVO.setProperties(properties);
				messageProcessor.sendMessageToUser(messageVO);
				messageService.insert(messageVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @Description (采购订单待确认消息)
	 * @param event
	 */
	private void confirmBuyOrderMessage(SendMessageEvent event) {
		try {
			initService();
			User user = UserUtil.getUserFromSession();
			if(user != null){
				Message messageVO = this.createMessage(event,user);
				
				messageVO.setMessageType(MessageConstants.BUSSINESS_MESSAGE);
				messageVO.setTempleteType(MessageConstants.TEMP_CONFIRM_BUY_ORDER); //采购订单申请消息
				OrderInfo order = (OrderInfo) event.getSource();
				messageVO.setObjectSerial(order.getSerialNum());
				
				
				Properties properties = new Properties();
				Company company = companyService.selectById(order.getSupplyComId());
				
				List<UserCompanyKey> users = userCompanyService.getUsersByComId(order.getSupplyComId());
				List<String> userIds = new ArrayList<String>();
				
				for(UserCompanyKey uc : users){
					userIds.add(uc.getUser_id());
				}
				
				User maker = userService.selectByUsername(order.getMaker()); //制单人
				
				if(company!=null ){
					messageVO.setReceiverIds(userIds);
					properties.put("paramer_a", company.getComName());
				}else{
					throw new Exception("没有找到消息接受者！");
				}
				properties.put("paramer_b", maker.getUserName());
				properties.put("paramer_c", order.getOrderNum());
				properties.put("paramer_d", MessageConstants.URL_CONFIRM_BUY_ORDER);
				properties.put("paramer_e", messageVO.getSerialNum());
				
				properties.put("paramer_f", "无"); //这里得去找审批评论
				
				messageVO.setProperties(properties);
				messageProcessor.sendMessageToUsers(messageVO);
				messageService.insertBatch(messageVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @Description (采购订单被确认消息)
	 * @param event
	 */
	private void beConfirmBuyOrderMessage(SendMessageEvent event) {
		try {
			initService();
			User user = UserUtil.getUserFromSession();
			if(user != null){
				Message messageVO = this.createMessage(event,user);
				
				messageVO.setMessageType(MessageConstants.SYSTEM_MESSAGE);
				messageVO.setTempleteType(MessageConstants.TEMP_BE_CONFIRM_BUY_ORDER); //采购订单申请消息
				OrderInfo order = (OrderInfo) event.getSource();
				messageVO.setObjectSerial(order.getSerialNum());
				
				
				Properties properties = new Properties();
				User maker = userService.selectByUsername(order.getMaker());
				if( maker!=null ){
					messageVO.setReceiverId(maker.getUserId().toString());
					properties.put("paramer_a", maker.getUserName());
				}else{
					throw new Exception("没有找到消息接受者！");
				}
				properties.put("paramer_b", userCompanyService.getCompany(user.getUserId().toString()).getComName());
				properties.put("paramer_c", order.getOrderNum());
				properties.put("paramer_d", MessageConstants.URL_BE_CONFIRM_BUY_ORDER);
				properties.put("paramer_e", messageVO.getSerialNum());
				
				properties.put("paramer_f", "无"); //这里得去找审批评论
				
				messageVO.setProperties(properties);
				messageProcessor.sendMessageToUser(messageVO);
				messageService.insert(messageVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @Description (采购订单单个节点通过消息)
	 * @param event
	 */
	private void singleAgreeBuyOrderMessage(SendMessageEvent event) {
		try {
			initService();
			User user = UserUtil.getUserFromSession();
			if(user != null){
				Message messageVO = this.createMessage(event,user);
				
				messageVO.setMessageType(MessageConstants.SYSTEM_MESSAGE);
				messageVO.setTempleteType(MessageConstants.TEMP_SINGLE_AGREE_BUY_ORDER); //采购订单申请消息
				OrderInfo order = (OrderInfo) event.getSource();
				messageVO.setObjectSerial(order.getSerialNum());
				
				
				Properties properties = new Properties();
				User maker = userService.selectByUsername(order.getMaker());
				if( maker!=null ){
					messageVO.setReceiverId(maker.getUserId().toString());
					properties.put("paramer_a", maker.getUserName());
				}else{
					throw new Exception("没有找到消息接受者！");
				}
				properties.put("paramer_b", order.getOrderNum());
				properties.put("paramer_c", user.getUserName());
				properties.put("paramer_d", MessageConstants.URL_AGREE_BUY_ORDER);
				properties.put("paramer_e", messageVO.getSerialNum());
				
				properties.put("paramer_f", "无"); //这里得去找审批评论
				
				messageVO.setProperties(properties);
				messageProcessor.sendMessageToUser(messageVO);
				messageService.insert(messageVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @Description (收货消息)
	 * @param event
	 */
	private void takeDeliveryMessage(SendMessageEvent event) {
		try {
			initService();
			User user = UserUtil.getUserFromSession();
			if(user != null){
				Message messageVO = this.createMessage(event,user);
				
				messageVO.setMessageType(MessageConstants.SYSTEM_MESSAGE);
				messageVO.setTempleteType(MessageConstants.TEMP_TAKE_DELIVERY); //收货消息
				TakeDeliveryParams params = (TakeDeliveryParams) event.getSource();
				messageVO.setObjectSerial(params.getTakeDelivery().getSerialNum());
				
				
				Properties properties = new Properties();
				Company company = companyService.selectById(params.getDelivery().getSupplyComId());
				
				List<UserCompanyKey> users = userCompanyService.getUsersByComId(params.getDelivery().getSupplyComId());
				List<String> userIds = new ArrayList<String>();
				
				for(UserCompanyKey uc : users){
					userIds.add(uc.getUser_id());
				}
				
				messageVO.setReceiverIds(userIds);
				
				properties.put("paramer_a", company.getComName());
				properties.put("paramer_b", user.getUserName());
				properties.put("paramer_c", params.getDelivery().getDeliverNum());
				properties.put("paramer_d", MessageConstants.URL_TAKE_DELIVERY);
				properties.put("paramer_e", messageVO.getSerialNum());
				
				messageVO.setProperties(properties);
				messageProcessor.sendMessageToUsers(messageVO);
				messageService.insertBatch(messageVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 
	 * @Description (逾期未收货)
	 * @param event
	 */
	private void noTakeDeliveryMessage(SendMessageEvent event) {
		
		
	}
	
	
	
	/**
	 * 
	 * @Description (发货消息)
	 * @param event
	 */
	private void deliveryMessage(SendMessageEvent event) {
		
		/*try {
			initService();
			User user = UserUtil.getUserFromSession();
			if(user != null){
				Message messageVO = this.createMessage(event,user);
				
				messageVO.setMessageType(MessageConstants.SYSTEM_MESSAGE);
				messageVO.setTempleteType(MessageConstants.TEMP_TAKE_DELIVERY); //收货消息
				Delivery delivery = (Delivery) event.getSource();
				messageVO.setObjectSerial(delivery.getSerialNum());
				
				Properties properties = new Properties();
				
				List<String> userIds = groupService.selectUserIdsByGroupType(Constants.SALES);
				
				messageVO.setReceiverIds(userIds);
				
				properties.put("paramer_a", company.getComName());
				properties.put("paramer_b", user.getUserName());
				properties.put("paramer_c", params.getDelivery().getDeliverNum());
				properties.put("paramer_d", MessageConstants.URL_TAKE_DELIVERY);
				properties.put("paramer_e", messageVO.getSerialNum());
				
				messageVO.setProperties(properties);
				messageProcessor.sendMessageToUsers(messageVO);
				messageService.insertBatch(messageVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}

	private void outToBuyMessage(SendMessageEvent event) {
		
		
	}

	private void outToSaleMessage(SendMessageEvent event) {
		
		
	}

	private void outCheckToBuyMessage(SendMessageEvent event) {
		
		
	}

	private void outCheckToSaleMessage(SendMessageEvent event) {
		
		
	}

	private void inCheckToBuyMessage(SendMessageEvent event) {
		
		
	}

	private void inCheckToSaleMessage(SendMessageEvent event) {
		
		
	}

	private void inToBuyMessage(SendMessageEvent event) {
		
		
	}

	private void inToSaleMessage(SendMessageEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	
	/**
	 * 
	 * @Description (构建消息对象)
	 * @param event
	 * @param user
	 * @return
	 */
	private Message createMessage(SendMessageEvent event,User user){
		Message messageVO = new Message();
		messageVO.setSerialNum(ApplicationUtils.random32UUID());
		messageVO.setActionName(event.getAction());
		messageVO.setReadFlg(Constants.READ_FLAG_DEFUALT);
		messageVO.setDelFlg(Constants.DEL_FLAG_DEFUALT);
		messageVO.setCreator(user.getUserName());
		messageVO.setCreateTime(new Date());
		messageVO.setUpdater(user.getUserName());
		messageVO.setUpdateTime(new Date());
		return messageVO;
	}

}