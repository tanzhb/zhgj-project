package com.congmai.zhgj.web.event;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.congmai.zhgj.web.model.DeliveryMateriel;
import com.congmai.zhgj.web.model.DeliveryMaterielExample;
import com.congmai.zhgj.web.model.DeliveryVO;
import com.congmai.zhgj.web.model.Message;
import com.congmai.zhgj.web.model.OrderInfo;
import com.congmai.zhgj.web.model.PaymentRecord;
import com.congmai.zhgj.web.model.ProcurementPlan;
import com.congmai.zhgj.web.model.StockInOutCheck;
import com.congmai.zhgj.web.model.StockInOutRecord;
import com.congmai.zhgj.web.model.TakeDelivery;
import com.congmai.zhgj.web.model.TakeDeliveryParams;
import com.congmai.zhgj.web.model.User;
import com.congmai.zhgj.web.model.UserCompanyKey;
import com.congmai.zhgj.web.service.ActRuTaskService;
import com.congmai.zhgj.web.service.CompanyService;
import com.congmai.zhgj.web.service.DeliveryMaterielService;
import com.congmai.zhgj.web.service.DeliveryService;
import com.congmai.zhgj.web.service.GroupService;
import com.congmai.zhgj.web.service.MessageProcessor;
import com.congmai.zhgj.web.service.MessageService;
import com.congmai.zhgj.web.service.OrderService;
import com.congmai.zhgj.web.service.PayService;
import com.congmai.zhgj.web.service.StockInOutCheckService;
import com.congmai.zhgj.web.service.TakeDeliveryService;
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

//	private Logger logger = LoggerFactory.getLogger(SendMessageListener.class);

	private MessageProcessor messageProcessor = null;

	private MessageService messageService = null;

	private ActRuTaskService actRuTaskService = null;

	private UserCompanyService userCompanyService = null;

	private CompanyService companyService = null;

	private UserService userService = null;

	private GroupService groupService = null;

	private OrderService orderService = null;

	private DeliveryService deliveryService = null;

	private TakeDeliveryService takeDeliveryService = null;
	
	private PayService payService = null;

	private DeliveryMaterielService deliveryMaterielService = null;

	private StockInOutCheckService stockInOutCheckService = null;

	private void initService(){
		messageProcessor = ApplicationContextHelper.getBean(WebSocketProcessor.class);
		messageService =  ApplicationContextHelper.getBean(MessageService.class);
		actRuTaskService = ApplicationContextHelper.getBean(ActRuTaskService.class);
		userCompanyService = ApplicationContextHelper.getBean(UserCompanyService.class);
		companyService = ApplicationContextHelper.getBean(CompanyService.class);
		userService =  ApplicationContextHelper.getBean(UserService.class);
		groupService =  ApplicationContextHelper.getBean(GroupService.class);
		orderService =  ApplicationContextHelper.getBean(OrderService.class);
		deliveryService =  ApplicationContextHelper.getBean(DeliveryService.class);
		takeDeliveryService =  ApplicationContextHelper.getBean(TakeDeliveryService.class);
		payService =  ApplicationContextHelper.getBean(PayService.class);
		stockInOutCheckService =  ApplicationContextHelper.getBean(StockInOutCheckService.class);
		deliveryMaterielService =  ApplicationContextHelper.getBean(DeliveryMaterielService.class);
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
		}else if(MessageConstants.IN_TO_BUY_TO_SALE.equals(event.getAction())){
			inToBuyToSaleMessage(event);
		}else if(MessageConstants.SHOUKUAN.equals(event.getAction())){ //收款（to供应）
			shoukuanMessage(event);
		}else if(MessageConstants.FUKUAN.equals(event.getAction())){ //付款（to采购）
			fukuanMessage(event);
		}else if(MessageConstants.SALE_TO_PALN.equals(event.getAction())){ 
			sale2palnMessage(event);
		}else if(MessageConstants.PALN_TO_BUY.equals(event.getAction())){ 
			paln2buyMessage(event);
		}else if(MessageConstants.IN_TO_STOCK.equals(event.getAction())){ 
			in2stockMessage(event);
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
//			logger.warn(e.getMessage(), e);
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
//			logger.warn(e.getMessage(), e);
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
//			logger.warn(e.getMessage(), e);
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
//			logger.warn(e.getMessage(), e);
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
				try {
					properties.put("paramer_b", userCompanyService.getCompany(user.getUserId().toString()).getComName());
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				properties.put("paramer_c", order.getOrderNum());
				properties.put("paramer_d", MessageConstants.URL_BE_CONFIRM_BUY_ORDER);
				properties.put("paramer_e", messageVO.getSerialNum());

				properties.put("paramer_f", "无"); //这里得去找审批评论

				messageVO.setProperties(properties);
				messageProcessor.sendMessageToUser(messageVO);
				messageService.insert(messageVO);
			}
		} catch (Exception e) {
//			logger.warn(e.getMessage(), e);
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
//			logger.warn(e.getMessage(), e);
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

				List<String> userIds = new ArrayList<String>();
				Properties properties = new Properties();


				if(StringUtils.isEmpty(params.getDelivery().getSupplyComId())){ //销售方为平台
					List<User> users = groupService.selectUserIdsByGroupType(Constants.SALES);
					for(User uc : users){
						userIds.add(uc.getUserId().toString());
					}
					properties.put("paramer_a", MessageConstants.PLATFORM_NAME);
				}else{
					Company company = companyService.selectById(params.getDelivery().getSupplyComId());
					List<UserCompanyKey> users = userCompanyService.getUsersByComId(params.getDelivery().getSupplyComId());
					for(UserCompanyKey uc : users){
						userIds.add(uc.getUser_id());
					}
					properties.put("paramer_a", company.getComName());
				}


				messageVO.setReceiverIds(userIds);


				properties.put("paramer_b", user.getUserName());
				properties.put("paramer_c", params.getDelivery().getDeliverNum());
				properties.put("paramer_d", MessageConstants.URL_TAKE_DELIVERY);
				properties.put("paramer_e", messageVO.getSerialNum());

				messageVO.setProperties(properties);
				messageProcessor.sendMessageToUsers(messageVO);
				messageService.insertBatch(messageVO);
			}
		} catch (Exception e) {
//			logger.warn(e.getMessage(), e);
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

		try {
			initService();
			User user = UserUtil.getUserFromSession();
			if(user != null){
				DeliveryVO delivery = (DeliveryVO) event.getSource();
				OrderInfo order = orderService.selectById(delivery.getOrderSerial());
				List<User> users = null;
				
				if(StringUtils.isEmpty(order.getBuyComId())){ //发给采购
					users = groupService.selectUserIdsByGroupType(Constants.PURCHASE);
					if(CollectionUtils.isNotEmpty(users)){
						for(User u : users){
							Message messageVO = this.createMessage(event,user);
							Properties properties = new Properties();
							messageVO.setMessageType(MessageConstants.SYSTEM_MESSAGE);
							messageVO.setTempleteType(MessageConstants.TEMP_DELIVERY); //收货消息

							messageVO.setObjectSerial(delivery.getSerialNum());


							messageVO.setReceiverId(u.getUserId().toString());
							properties.put("paramer_a", u.getUserName());
							properties.put("paramer_b", order.getOrderNum());
							properties.put("paramer_c", delivery.getDeliverNum());
							properties.put("paramer_d", MessageConstants.URL_DELIVERY);
							properties.put("paramer_e", messageVO.getSerialNum());

							messageVO.setProperties(properties);
							messageProcessor.sendMessageToUser(messageVO);
							messageService.insert(messageVO);
						}
					}
				}else{   //发给客户
					users = new ArrayList<User>();
					Company company = companyService.selectById(order.getBuyComId());
					Properties properties = new Properties();
					Message messageVO = this.createMessage(event,user);
					List<UserCompanyKey> userCompanyKeys = userCompanyService.getUsersByComId(order.getBuyComId());
					List<String> userIds = new ArrayList<String>();
					for(UserCompanyKey uc : userCompanyKeys){
						userIds.add(uc.getUser_id());
					}
					messageVO.setMessageType(MessageConstants.SYSTEM_MESSAGE);
					messageVO.setTempleteType(MessageConstants.TEMP_DELIVERY); //收货消息

					messageVO.setObjectSerial(delivery.getSerialNum());
					messageVO.setReceiverIds(userIds);

					properties.put("paramer_a", company.getComName());
					properties.put("paramer_b", order.getOrderNum());
					properties.put("paramer_c", delivery.getDeliverNum());
					properties.put("paramer_d", MessageConstants.URL_DELIVERY);
					properties.put("paramer_e", messageVO.getSerialNum());

					messageVO.setProperties(properties);

					messageVO.setProperties(properties);
					messageProcessor.sendMessageToUsers(messageVO);
					messageService.insertBatch(messageVO);
				}
				
				//发给仓储
				users = null;
				users = groupService.selectUserIdsByGroupType(Constants.STORAGE);
				if(CollectionUtils.isNotEmpty(users)){
					for(User u : users){
						Properties properties = new Properties();
						Message messageVO = this.createMessage(event,user);
						messageVO.setMessageType(MessageConstants.BUSSINESS_MESSAGE);
						messageVO.setTempleteType(MessageConstants.TEMP_DELIVERY); 
						messageVO.setActionName(MessageConstants.OUT_TO_SALE);
						messageVO.setObjectSerial(delivery.getSerialNum());
						messageVO.setReceiverId(u.getUserId().toString());
						properties.put("paramer_a", u.getUserName());
						properties.put("paramer_b", order.getOrderNum());
						properties.put("paramer_c", delivery.getDeliverNum());
						properties.put("paramer_d", MessageConstants.URL_IN_TO_SALE);
						properties.put("paramer_e", messageVO.getSerialNum());

						messageVO.setProperties(properties);
						messageProcessor.sendMessageToUser(messageVO);
						messageService.insert(messageVO);
					}
				}


			}
		} catch (Exception e) {
//			logger.warn(e.getMessage(), e);
		}
	}

	/**
	 * 
	 * @Description (出库消息to采购)
	 * @param event
	 */
	private void outToBuyMessage(SendMessageEvent event) {
		try {
			initService();
			User user = UserUtil.getUserFromSession();
			if(user != null){
				TakeDeliveryParams params = (TakeDeliveryParams) event.getSource();

				Properties properties = new Properties();
				DeliveryVO delivery = deliveryService.selectDetailById(params.getRecord().getDeliverSerial());
				OrderInfo order = orderService.selectById(delivery.getOrderSerial());
				Integer count = 0;
				for(DeliveryMateriel dm : params.getDeliveryMateriels()){
					count += Integer.parseInt(dm.getStockCount());
				}
				List<User> users = null;
				if(StringUtils.isEmpty(order.getBuyComId())){ //发给采购
					users = groupService.selectUserIdsByGroupType(Constants.PURCHASE);
					if(CollectionUtils.isNotEmpty(users)){
						for(User u : users){
							Message messageVO = this.createMessage(event,user);

							messageVO.setMessageType(MessageConstants.SYSTEM_MESSAGE);
							messageVO.setTempleteType(MessageConstants.TEMP_OUT_TO_BUY); //收货消息
							messageVO.setObjectSerial(params.getRecord().getSerialNum());
							messageVO.setReceiverId(u.getUserId().toString());
							properties.put("paramer_a", u.getUserName());
							properties.put("paramer_b", delivery.getDeliverNum());
							properties.put("paramer_c", count);
							properties.put("paramer_d", order.getOrderNum());
							properties.put("paramer_e", MessageConstants.URL_OUT_TO_BUY);
							properties.put("paramer_f", messageVO.getSerialNum());

							messageVO.setProperties(properties);
							messageProcessor.sendMessageToUser(messageVO);
							messageService.insert(messageVO);
						}
					}
				}else{   //发给客户
					users = new ArrayList<User>();
					Company company = companyService.selectById(order.getBuyComId());
					Message messageVO = this.createMessage(event,user);
					List<UserCompanyKey> userCompanyKeys = userCompanyService.getUsersByComId(order.getBuyComId());
					List<String> userIds = new ArrayList<String>();
					for(UserCompanyKey uc : userCompanyKeys){
						userIds.add(uc.getUser_id());
					}
					messageVO.setMessageType(MessageConstants.SYSTEM_MESSAGE);
					messageVO.setTempleteType(MessageConstants.TEMP_OUT_TO_BUY); //收货消息
					messageVO.setObjectSerial(params.getRecord().getSerialNum());
					messageVO.setReceiverIds(userIds);

					properties.put("paramer_a", company.getComName());
					properties.put("paramer_b", delivery.getDeliverNum());
					properties.put("paramer_c", count);
					properties.put("paramer_d", order.getOrderNum());
					properties.put("paramer_e", MessageConstants.URL_OUT_TO_BUY);
					properties.put("paramer_f", messageVO.getSerialNum());

					messageVO.setProperties(properties);
					messageProcessor.sendMessageToUsers(messageVO);
					messageService.insertBatch(messageVO);
				}


			}
		} catch (Exception e) {
//			logger.warn(e.getMessage(), e);
		}
	}

	/**
	 * 
	 * @Description (出库消息to供应)
	 * @param event
	 */
	private void outToSaleMessage(SendMessageEvent event) {
		try {
			initService();
			User user = UserUtil.getUserFromSession();
			if(user != null){

				TakeDeliveryParams params = (TakeDeliveryParams) event.getSource();

				Properties properties = new Properties();
				DeliveryVO delivery = deliveryService.selectDetailById(params.getRecord().getDeliverSerial());
				OrderInfo order = orderService.selectById(delivery.getOrderSerial());
				Integer count = 0;
				for(DeliveryMateriel dm : params.getDeliveryMateriels()){
					count += Integer.parseInt(dm.getStockCount());
				}
				List<User> users = null;
				if(StringUtils.isEmpty(order.getBuyComId())){ //发给采购
					users = groupService.selectUserIdsByGroupType(Constants.PURCHASE);
					if(CollectionUtils.isNotEmpty(users)){
						for(User u : users){
							Message messageVO = this.createMessage(event,user);

							messageVO.setMessageType(MessageConstants.SYSTEM_MESSAGE);
							messageVO.setTempleteType(MessageConstants.TEMP_OUT_TO_SALE); //收货消息
							messageVO.setObjectSerial(params.getRecord().getSerialNum());
							messageVO.setReceiverId(u.getUserId().toString());
							properties.put("paramer_a", u.getUserName());
							properties.put("paramer_b", delivery.getDeliverNum());
							properties.put("paramer_c", count);
							properties.put("paramer_d", order.getOrderNum());
							properties.put("paramer_e", MessageConstants.URL_OUT_TO_SALE);
							properties.put("paramer_f", messageVO.getSerialNum());

							messageVO.setProperties(properties);
							messageProcessor.sendMessageToUser(messageVO);
							messageService.insert(messageVO);
						}
					}
				}else{   //发给客户
					users = new ArrayList<User>();
					Company company = companyService.selectById(order.getBuyComId());
					Message messageVO = this.createMessage(event,user);
					List<UserCompanyKey> userCompanyKeys = userCompanyService.getUsersByComId(order.getBuyComId());
					List<String> userIds = new ArrayList<String>();
					for(UserCompanyKey uc : userCompanyKeys){
						userIds.add(uc.getUser_id());
					}
					messageVO.setMessageType(MessageConstants.SYSTEM_MESSAGE);
					messageVO.setTempleteType(MessageConstants.TEMP_OUT_TO_SALE); //收货消息
					messageVO.setObjectSerial(params.getRecord().getSerialNum());
					messageVO.setReceiverIds(userIds);

					properties.put("paramer_a", company.getComName());
					properties.put("paramer_b", delivery.getDeliverNum());
					properties.put("paramer_c", count);
					properties.put("paramer_d", order.getOrderNum());
					properties.put("paramer_e", MessageConstants.URL_OUT_TO_SALE);
					properties.put("paramer_f", messageVO.getSerialNum());

					messageVO.setProperties(properties);
					messageProcessor.sendMessageToUsers(messageVO);
					messageService.insertBatch(messageVO);
				}
			}
		} catch (Exception e) {
//			logger.warn(e.getMessage(), e);
		}
	}

	/**
	 * 
	 * @Description (出库检验消息  to 采购)
	 * @param event
	 */
	private void outCheckToBuyMessage(SendMessageEvent event) {
		try {
			initService();
			User user = UserUtil.getUserFromSession();
			if(user != null){

				StockInOutCheck check = (StockInOutCheck) event.getSource();

				Properties properties = new Properties();
				DeliveryVO delivery = deliveryService.selectDetailById(check.getDeliverSerial());


				OrderInfo order = orderService.selectById(delivery.getOrderSerial());
				Integer qualifiedCount = 0;
				Integer unQualifiedCount = 0;
				//stockInOutCheckService.
				List<DeliveryMateriel> deliveryMateriels = getDeliveryMateriels(delivery.getSerialNum());

				for(DeliveryMateriel dm : deliveryMateriels){
					qualifiedCount += Integer.parseInt(dm.getQualifiedCount());
					unQualifiedCount += Integer.parseInt(dm.getUnqualifiedCount());
				}
				List<User> users = null;
				if(StringUtils.isEmpty(order.getBuyComId())){ //发给采购
					users = groupService.selectUserIdsByGroupType(Constants.PURCHASE);
					if(CollectionUtils.isNotEmpty(users)){
						for(User u : users){
							Message messageVO = this.createMessage(event,user);
							messageVO.setMessageType(MessageConstants.SYSTEM_MESSAGE);
							messageVO.setTempleteType(MessageConstants.TEMP_OUT_CHECK_TO_BUY); //收货消息
							messageVO.setObjectSerial(check.getSerialNum());
							messageVO.setReceiverId(u.getUserId().toString());
							properties.put("paramer_a", u.getUserName());
							properties.put("paramer_b", delivery.getDeliverNum());
							properties.put("paramer_c", qualifiedCount+unQualifiedCount);
							properties.put("paramer_d", unQualifiedCount);
							properties.put("paramer_e", order.getOrderNum());
							properties.put("paramer_f", MessageConstants.URL_OUT_CHECK_TO_BUY);
							properties.put("paramer_g", messageVO.getSerialNum());

							messageVO.setProperties(properties);
							messageProcessor.sendMessageToUser(messageVO);
							messageService.insert(messageVO);
						}
					}
				}else{   //发给客户
					users = new ArrayList<User>();
					Company company = companyService.selectById(order.getBuyComId());
					Message messageVO = this.createMessage(event,user);
					List<UserCompanyKey> userCompanyKeys = userCompanyService.getUsersByComId(order.getBuyComId());
					List<String> userIds = new ArrayList<String>();
					for(UserCompanyKey uc : userCompanyKeys){
						userIds.add(uc.getUser_id());
					}
					messageVO.setMessageType(MessageConstants.SYSTEM_MESSAGE);
					messageVO.setTempleteType(MessageConstants.TEMP_OUT_CHECK_TO_BUY); //收货消息
					messageVO.setObjectSerial(check.getSerialNum());
					messageVO.setReceiverIds(userIds);

					properties.put("paramer_a", company.getComName());
					properties.put("paramer_b", delivery.getDeliverNum());
					properties.put("paramer_c", qualifiedCount+unQualifiedCount);
					properties.put("paramer_d", unQualifiedCount);
					properties.put("paramer_e", order.getOrderNum());
					properties.put("paramer_f", MessageConstants.URL_OUT_CHECK_TO_BUY);
					properties.put("paramer_g", messageVO.getSerialNum());

					messageVO.setProperties(properties);
					messageProcessor.sendMessageToUsers(messageVO);
					messageService.insertBatch(messageVO);
				}
			}
		} catch (Exception e) {
//			logger.warn(e.getMessage(), e);
		}

	}

	/**
	 * 
	 * @Description (出库检验消息  to 供应)
	 * @param event
	 */
	private void outCheckToSaleMessage(SendMessageEvent event) {
		try {
			initService();
			User user = UserUtil.getUserFromSession();
			if(user != null){

				StockInOutCheck check = (StockInOutCheck) event.getSource();

				Properties properties = new Properties();
				DeliveryVO delivery = deliveryService.selectDetailById(check.getDeliverSerial());
				OrderInfo order = orderService.selectById(delivery.getOrderSerial());
				Integer qualifiedCount = 0;
				Integer unQualifiedCount = 0;

				List<DeliveryMateriel> deliveryMateriels = getDeliveryMateriels(delivery.getSerialNum());

				for(DeliveryMateriel dm : deliveryMateriels){
					qualifiedCount += Integer.parseInt(dm.getQualifiedCount());
					unQualifiedCount += Integer.parseInt(dm.getUnqualifiedCount());
				}
				List<User> users = null;
				if(StringUtils.isEmpty(order.getSupplyComId())){ //发给销售
					users = groupService.selectUserIdsByGroupType(Constants.SALES);
					if(CollectionUtils.isNotEmpty(users)){
						for(User u : users){
							Message messageVO = this.createMessage(event,user);
							messageVO.setMessageType(MessageConstants.SYSTEM_MESSAGE);
							messageVO.setTempleteType(MessageConstants.TEMP_OUT_CHECK_TO_SALE); //收货消息
							messageVO.setObjectSerial(check.getSerialNum());
							messageVO.setReceiverId(u.getUserId().toString());
							properties.put("paramer_a", u.getUserName());
							properties.put("paramer_b", delivery.getDeliverNum());
							properties.put("paramer_c", qualifiedCount+unQualifiedCount);
							properties.put("paramer_d", MessageConstants.URL_OUT_CHECK_TO_SALE);
							properties.put("paramer_e", messageVO.getSerialNum());

							messageVO.setProperties(properties);
							messageProcessor.sendMessageToUser(messageVO);
							messageService.insert(messageVO);
						}
					}
				}else{   //发给供应商
					users = new ArrayList<User>();
					Company company = companyService.selectById(order.getSupplyComId());
					Message messageVO = this.createMessage(event,user);
					List<UserCompanyKey> userCompanyKeys = userCompanyService.getUsersByComId(order.getSupplyComId());
					List<String> userIds = new ArrayList<String>();
					for(UserCompanyKey uc : userCompanyKeys){
						userIds.add(uc.getUser_id());
					}
					messageVO.setMessageType(MessageConstants.SYSTEM_MESSAGE);
					messageVO.setTempleteType(MessageConstants.TEMP_OUT_CHECK_TO_SALE); //收货消息
					messageVO.setObjectSerial(check.getSerialNum());
					messageVO.setReceiverIds(userIds);

					properties.put("paramer_a", company.getComName());
					properties.put("paramer_b", delivery.getDeliverNum());
					properties.put("paramer_c", qualifiedCount+unQualifiedCount);
					properties.put("paramer_d", MessageConstants.URL_OUT_CHECK_TO_SALE);
					properties.put("paramer_e", messageVO.getSerialNum());

					messageVO.setProperties(properties);
					messageProcessor.sendMessageToUsers(messageVO);
					messageService.insertBatch(messageVO);
				}
			}
		} catch (Exception e) {
//			logger.warn(e.getMessage(), e);
		}
	}

	/**
	 * 
	 * @Description (TODO入库检验 to 采购)
	 * @param event
	 */
	private void inCheckToBuyMessage(SendMessageEvent event) {
		try {
			initService();
			User user = UserUtil.getUserFromSession();
			if(user != null){

				StockInOutCheck check = (StockInOutCheck) event.getSource();

				Properties properties = new Properties();
				TakeDelivery takeDelivery = takeDeliveryService.selectById(check.getTakeDeliverSerial());
				DeliveryVO delivery = deliveryService.selectDetailById(takeDelivery.getDeliverSerial());
				OrderInfo order = orderService.selectById(delivery.getOrderSerial());
				Integer qualifiedCount = 0;
				Integer unQualifiedCount = 0;

				List<DeliveryMateriel> deliveryMateriels = getDeliveryMateriels(takeDelivery.getSerialNum());

				for(DeliveryMateriel dm : deliveryMateriels){
					qualifiedCount += Integer.parseInt(dm.getQualifiedCount());
					unQualifiedCount += Integer.parseInt(dm.getUnqualifiedCount());
				}
				List<User> users = null;
				if(StringUtils.isEmpty(order.getBuyComId())){ //发给采购
					users = groupService.selectUserIdsByGroupType(Constants.PURCHASE);
					if(CollectionUtils.isNotEmpty(users)){
						for(User u : users){
							Message messageVO = this.createMessage(event,user);

							messageVO.setMessageType(MessageConstants.SYSTEM_MESSAGE);
							messageVO.setTempleteType(MessageConstants.TEMP_IN_CHECK_TO_BUY); //收货消息
							messageVO.setObjectSerial(check.getSerialNum());
							messageVO.setReceiverId(u.getUserId().toString());
							properties.put("paramer_a", u.getUserName());
							properties.put("paramer_b", takeDelivery.getTakeDeliverNum());
							properties.put("paramer_c", qualifiedCount+unQualifiedCount);
							properties.put("paramer_d", MessageConstants.URL_IN_CHECK_TO_BUY);
							properties.put("paramer_e", messageVO.getSerialNum());

							messageVO.setProperties(properties);
							messageProcessor.sendMessageToUser(messageVO);
							messageService.insert(messageVO);
						}
					}
				}else{   //发给客户
					users = new ArrayList<User>();
					Company company = companyService.selectById(order.getBuyComId());
					Message messageVO = this.createMessage(event,user);
					List<UserCompanyKey> userCompanyKeys = userCompanyService.getUsersByComId(order.getBuyComId());
					List<String> userIds = new ArrayList<String>();
					for(UserCompanyKey uc : userCompanyKeys){
						userIds.add(uc.getUser_id());
					}
					messageVO.setMessageType(MessageConstants.SYSTEM_MESSAGE);
					messageVO.setTempleteType(MessageConstants.TEMP_IN_CHECK_TO_BUY); //收货消息
					messageVO.setObjectSerial(check.getSerialNum());
					messageVO.setReceiverIds(userIds);

					properties.put("paramer_a", company.getComName());
					properties.put("paramer_b", takeDelivery.getTakeDeliverNum());
					properties.put("paramer_c", qualifiedCount+unQualifiedCount);
					properties.put("paramer_d", MessageConstants.URL_IN_CHECK_TO_BUY);
					properties.put("paramer_e", messageVO.getSerialNum());

					messageVO.setProperties(properties);
					messageProcessor.sendMessageToUsers(messageVO);
					messageService.insertBatch(messageVO);
				}
			}
		} catch (Exception e) {
//			logger.warn(e.getMessage(), e);
		}
	}

	/**
	 * 
	 * @Description (入库检验 to 供应)
	 * @param event
	 */
	private void inCheckToSaleMessage(SendMessageEvent event) {
		try {
			initService();
			User user = UserUtil.getUserFromSession();
			if(user != null){
				StockInOutCheck check = (StockInOutCheck) event.getSource();

				Properties properties = new Properties();
				TakeDelivery takeDelivery = takeDeliveryService.selectById(check.getTakeDeliverSerial());
				DeliveryVO delivery = deliveryService.selectDetailById(takeDelivery.getDeliverSerial());
				OrderInfo order = orderService.selectById(delivery.getOrderSerial());
				Integer qualifiedCount = 0;
				Integer unQualifiedCount = 0;

				List<DeliveryMateriel> deliveryMateriels = getDeliveryMateriels(takeDelivery.getSerialNum());
				for(DeliveryMateriel dm : deliveryMateriels){
					qualifiedCount += Integer.parseInt(dm.getQualifiedCount());
					unQualifiedCount += Integer.parseInt(dm.getUnqualifiedCount());
				}
				List<User> users = null;
				if(StringUtils.isEmpty(order.getSupplyComId())){ //发给销售
					users = groupService.selectUserIdsByGroupType(Constants.SALES);
					if(CollectionUtils.isNotEmpty(users)){
						for(User u : users){
							Message messageVO = this.createMessage(event,user);

							messageVO.setMessageType(MessageConstants.SYSTEM_MESSAGE);
							messageVO.setTempleteType(MessageConstants.TEMP_IN_CHECK_TO_SALE); //收货消息
							messageVO.setObjectSerial(check.getSerialNum());
							messageVO.setReceiverId(u.getUserId().toString());
							properties.put("paramer_a", u.getUserName());
							properties.put("paramer_b", delivery.getDeliverNum());
							properties.put("paramer_c", qualifiedCount+unQualifiedCount);
							properties.put("paramer_d", unQualifiedCount);
							properties.put("paramer_e", order.getOrderNum());
							properties.put("paramer_f", MessageConstants.URL_IN_CHECK_TO_SALE);
							properties.put("paramer_g", messageVO.getSerialNum());

							messageVO.setProperties(properties);
							messageProcessor.sendMessageToUser(messageVO);
							messageService.insert(messageVO);
						}
					}
				}else{   //发给供应商
					users = new ArrayList<User>();
					Company company = companyService.selectById(order.getSupplyComId());
					Message messageVO = this.createMessage(event,user);
					List<UserCompanyKey> userCompanyKeys = userCompanyService.getUsersByComId(order.getSupplyComId());
					List<String> userIds = new ArrayList<String>();
					for(UserCompanyKey uc : userCompanyKeys){
						userIds.add(uc.getUser_id());
					}
					messageVO.setMessageType(MessageConstants.SYSTEM_MESSAGE);
					messageVO.setTempleteType(MessageConstants.TEMP_IN_CHECK_TO_SALE); //收货消息
					messageVO.setObjectSerial(check.getSerialNum());
					messageVO.setReceiverIds(userIds);

					properties.put("paramer_a", company.getComName());
					properties.put("paramer_b", delivery.getDeliverNum());
					properties.put("paramer_c", qualifiedCount+unQualifiedCount);
					properties.put("paramer_d", unQualifiedCount);
					properties.put("paramer_e", order.getOrderNum());
					properties.put("paramer_f", MessageConstants.URL_IN_CHECK_TO_SALE);
					properties.put("paramer_g", messageVO.getSerialNum());

					messageVO.setProperties(properties);
					messageProcessor.sendMessageToUsers(messageVO);
					messageService.insertBatch(messageVO);
				}
			}
		} catch (Exception e) {
//			logger.warn(e.getMessage(), e);
		}

	}

	/**
	 * 
	 * @Description (入库 to 采购)
	 * @param event
	 */
	private void inToBuyMessage(SendMessageEvent event) {
		try {
			initService();
			User user = UserUtil.getUserFromSession();
			if(user != null){
				TakeDeliveryParams params = (TakeDeliveryParams) event.getSource();

				Properties properties = new Properties();
				TakeDelivery takeDelivery = takeDeliveryService.selectById(params.getRecord().getTakeDeliverSerial());
				DeliveryVO delivery = deliveryService.selectDetailById(takeDelivery.getDeliverSerial());
				OrderInfo order = orderService.selectById(delivery.getOrderSerial());
				Integer count = 0;
				for(DeliveryMateriel dm : params.getDeliveryMateriels()){
					count += Integer.parseInt(dm.getStockCount());
				}
				List<User> users = null;
				if(StringUtils.isEmpty(order.getBuyComId())){ //发给采购
					users = groupService.selectUserIdsByGroupType(Constants.PURCHASE);
					if(CollectionUtils.isNotEmpty(users)){
						for(User u : users){
							Message messageVO = this.createMessage(event,user);

							messageVO.setMessageType(MessageConstants.SYSTEM_MESSAGE);
							messageVO.setTempleteType(MessageConstants.TEMP_IN_TO_BUY); //收货消息
							messageVO.setObjectSerial(params.getRecord().getSerialNum());
							messageVO.setReceiverId(u.getUserId().toString());
							properties.put("paramer_a", u.getUserName());
							properties.put("paramer_b", takeDelivery.getTakeDeliverNum());
							properties.put("paramer_c", count);
							properties.put("paramer_d", order.getOrderNum());
							properties.put("paramer_e", MessageConstants.URL_IN_TO_BUY);
							properties.put("paramer_f", messageVO.getSerialNum());

							messageVO.setProperties(properties);
							messageProcessor.sendMessageToUser(messageVO);
							messageService.insert(messageVO);
						}
					}
				}else{   //发给客户
					users = new ArrayList<User>();
					Company company = companyService.selectById(order.getBuyComId());
					Message messageVO = this.createMessage(event,user);
					List<UserCompanyKey> userCompanyKeys = userCompanyService.getUsersByComId(order.getBuyComId());
					List<String> userIds = new ArrayList<String>();
					for(UserCompanyKey uc : userCompanyKeys){
						userIds.add(uc.getUser_id());
					}
					messageVO.setMessageType(MessageConstants.SYSTEM_MESSAGE);
					messageVO.setTempleteType(MessageConstants.TEMP_IN_TO_BUY); //收货消息
					messageVO.setObjectSerial(params.getRecord().getSerialNum());
					messageVO.setReceiverIds(userIds);

					properties.put("paramer_a", company.getComName());
					properties.put("paramer_b", takeDelivery.getTakeDeliverNum());
					properties.put("paramer_c", count);
					properties.put("paramer_d", order.getOrderNum());
					properties.put("paramer_e", MessageConstants.URL_IN_TO_BUY);
					properties.put("paramer_f", messageVO.getSerialNum());

					messageVO.setProperties(properties);
					messageProcessor.sendMessageToUsers(messageVO);
					messageService.insertBatch(messageVO);
				}
			}
		} catch (Exception e) {
//			logger.warn(e.getMessage(), e);
		}

	}

	
	/**
	 * 
	 * @Description (入库 to 采购通知关联的销售订单制单人)
	 * @param event
	 */
	private void inToBuyToSaleMessage(SendMessageEvent event) {
		try {
			initService();
			User user = UserUtil.getUserFromSession();
			if(user != null){
				TakeDeliveryParams params = (TakeDeliveryParams) event.getSource();
				TakeDelivery takeDelivery = takeDeliveryService.selectById(params.getRecord().getTakeDeliverSerial());
				DeliveryVO delivery = deliveryService.selectDetailById(takeDelivery.getDeliverSerial());
				OrderInfo order = orderService.selectById(delivery.getOrderSerial());
				
				if(StringUtils.isNotEmpty(order.getOrderSerial())){ //发给采购对应销售订单
					Integer count = 0;
					for(DeliveryMateriel dm : params.getDeliveryMateriels()){
						count += Integer.parseInt(dm.getStockCount());
					}
					
					//发给采购人员
					List<User> users = null;
					users = groupService.selectUserIdsByGroupType(Constants.PURCHASE);
					if(CollectionUtils.isNotEmpty(users)){
						for(User u : users){
							Properties properties = new Properties();
							Message messageVO = this.createMessage(event,user);
							messageVO.setMessageType(MessageConstants.BUSSINESS_MESSAGE);
							messageVO.setTempleteType(MessageConstants.TEMP_IN_TO_BUY_TO_SALE); //收货消息
							messageVO.setObjectSerial(params.getRecord().getSerialNum());
							messageVO.setReceiverId(u.getUserId().toString());
							messageVO.setActionName(MessageConstants.PALN_TO_BUY);
							properties.put("paramer_a", u.getUserName());
							properties.put("paramer_b", takeDelivery.getTakeDeliverNum());
							properties.put("paramer_c", count);
							properties.put("paramer_d", order.getOrderNum());
							properties.put("paramer_e", MessageConstants.URL_SALE_ORDER);
							properties.put("paramer_f", messageVO.getSerialNum());
							properties.put("paramer_g", order.getOrderSerial());

							messageVO.setProperties(properties);
							messageProcessor.sendMessageToUser(messageVO);
							messageService.insert(messageVO);
						}
					}
					
					//发给销售人员
					users = null;
					users = groupService.selectUserIdsByGroupType(Constants.SALES);
					if(CollectionUtils.isNotEmpty(users)){
						for(User u : users){
							Properties properties = new Properties();
							Message messageVO = this.createMessage(event,user);
							messageVO.setMessageType(MessageConstants.BUSSINESS_MESSAGE);
							messageVO.setTempleteType(MessageConstants.TEMP_IN_TO_BUY_TO_SALE); //收货消息
							messageVO.setObjectSerial(params.getRecord().getSerialNum());
							messageVO.setReceiverId(u.getUserId().toString());
							properties.put("paramer_a", u.getUserName());
							properties.put("paramer_b", takeDelivery.getTakeDeliverNum());
							properties.put("paramer_c", count);
							properties.put("paramer_d", order.getOrderNum());
							properties.put("paramer_e", MessageConstants.URL_BE_CONFIRM_BUY_ORDER);
							properties.put("paramer_f", messageVO.getSerialNum());
							properties.put("paramer_g", order.getOrderSerial());

							messageVO.setProperties(properties);
							messageProcessor.sendMessageToUser(messageVO);
							messageService.insert(messageVO);
						}
					}
					
					//发给供应链管理组（产品总监）
					users = null;
					users = groupService.selectUserIdsByGroupType(Constants.MANAGER);
					if(CollectionUtils.isNotEmpty(users)){
						for(User u : users){
							Properties properties = new Properties();
							Message messageVO = this.createMessage(event,user);
							messageVO.setMessageType(MessageConstants.BUSSINESS_MESSAGE);
							messageVO.setTempleteType(MessageConstants.TEMP_IN_TO_BUY_TO_SALE); //收货消息
							messageVO.setObjectSerial(params.getRecord().getSerialNum());
							messageVO.setReceiverId(u.getUserId().toString());
							properties.put("paramer_a", u.getUserName());
							properties.put("paramer_b", takeDelivery.getTakeDeliverNum());
							properties.put("paramer_c", count);
							properties.put("paramer_d", order.getOrderNum());
							properties.put("paramer_e", MessageConstants.URL_SALE_ORDER);
							properties.put("paramer_f", messageVO.getSerialNum());
							properties.put("paramer_g", order.getOrderSerial());

							messageVO.setProperties(properties);
							messageProcessor.sendMessageToUser(messageVO);
							messageService.insert(messageVO);
						}
					}
				}
			}
		} catch (Exception e) {
//			logger.warn(e.getMessage(), e);
		}

	}
	
	private void inToSaleMessage(SendMessageEvent event) {
		try {
			initService();
			User user = UserUtil.getUserFromSession();
			if(user != null){

				TakeDeliveryParams params = (TakeDeliveryParams) event.getSource();

				Properties properties = new Properties();
				TakeDelivery takeDelivery = takeDeliveryService.selectById(params.getRecord().getTakeDeliverSerial());
				DeliveryVO delivery = deliveryService.selectDetailById(takeDelivery.getDeliverSerial());
				OrderInfo order = orderService.selectById(delivery.getOrderSerial());
				Integer count = 0;
				for(DeliveryMateriel dm : params.getDeliveryMateriels()){
					count += Integer.parseInt(dm.getStockCount());
				}
				List<User> users = null;
				if(StringUtils.isEmpty(order.getSupplyComId())){ //发给销售
					users = groupService.selectUserIdsByGroupType(Constants.SALES);
					if(CollectionUtils.isNotEmpty(users)){
						for(User u : users){
							Message messageVO = this.createMessage(event,user);

							messageVO.setMessageType(MessageConstants.SYSTEM_MESSAGE);
							messageVO.setTempleteType(MessageConstants.TEMP_IN_TO_SALE); //收货消息
							messageVO.setObjectSerial(params.getRecord().getSerialNum());
							messageVO.setReceiverId(u.getUserId().toString());
							properties.put("paramer_a", u.getUserName());
							properties.put("paramer_b", delivery.getDeliverNum());
							properties.put("paramer_c", count);
							properties.put("paramer_d", order.getOrderNum());
							properties.put("paramer_e", MessageConstants.URL_IN_TO_SALE);
							properties.put("paramer_f", messageVO.getSerialNum());

							messageVO.setProperties(properties);
							messageProcessor.sendMessageToUser(messageVO);
							messageService.insert(messageVO);
						}
					}
				}else{   //发给供应商
					users = new ArrayList<User>();
					Company company = companyService.selectById(order.getSupplyComId());
					Message messageVO = this.createMessage(event,user);
					List<UserCompanyKey> userCompanyKeys = userCompanyService.getUsersByComId(order.getSupplyComId());
					List<String> userIds = new ArrayList<String>();
					for(UserCompanyKey uc : userCompanyKeys){
						userIds.add(uc.getUser_id());
					}
					messageVO.setMessageType(MessageConstants.SYSTEM_MESSAGE);
					messageVO.setTempleteType(MessageConstants.TEMP_IN_TO_SALE); //收货消息
					messageVO.setObjectSerial(params.getRecord().getSerialNum());
					messageVO.setReceiverIds(userIds);

					properties.put("paramer_a", company.getComName());
					properties.put("paramer_b", delivery.getDeliverNum());
					properties.put("paramer_c", count);
					properties.put("paramer_d", order.getOrderNum());
					properties.put("paramer_e", MessageConstants.URL_IN_TO_SALE);
					properties.put("paramer_f", messageVO.getSerialNum());

					messageVO.setProperties(properties);
					messageProcessor.sendMessageToUsers(messageVO);
					messageService.insertBatch(messageVO);
				}
			}
		} catch (Exception e) {
//			logger.warn(e.getMessage(), e);
		}

	}


	/**
	 * 
	 * @Description (收款 to 供应)
	 * @param event
	 */
	private void shoukuanMessage(SendMessageEvent event) {
		try {
			initService();
			User user = UserUtil.getUserFromSession();
			if(user != null){
				PaymentRecord record=(PaymentRecord)event.getSource();
				record = payService.selectPayById(record.getSerialNum());

				Properties properties = new Properties();
				//发给供应商
				Company company = companyService.selectById(record.getSupplyComId());
				Message messageVO = this.createMessage(event,user);
				List<UserCompanyKey> userCompanyKeys = userCompanyService.getUsersByComId(record.getSupplyComId());
				List<String> userIds = new ArrayList<String>();
				for(UserCompanyKey uc : userCompanyKeys){
					userIds.add(uc.getUser_id());
				}
				messageVO.setMessageType(MessageConstants.SYSTEM_MESSAGE);
				messageVO.setTempleteType(MessageConstants.TEMP_SHOUKUAN); //收货消息
				messageVO.setObjectSerial(record.getSerialNum());
				messageVO.setReceiverIds(userIds);

				properties.put("paramer_a", company.getComName());
				properties.put("paramer_b", record.getOrderNum());
				properties.put("paramer_c", MessageConstants.URL_SHOUKUAN);
				properties.put("paramer_d", messageVO.getSerialNum());

				messageVO.setProperties(properties);
				messageProcessor.sendMessageToUsers(messageVO);
				messageService.insertBatch(messageVO);
				}
		} catch (Exception e) {
//			logger.warn(e.getMessage(), e);
		}

	}
	
	
	/**
	 * 
	 * @Description (付款to 供应)
	 * @param event
	 */
	private void fukuanMessage(SendMessageEvent event) {
		try {
			initService();
			User user = UserUtil.getUserFromSession();
			if(user != null){
				PaymentRecord record=(PaymentRecord)event.getSource();
				record = payService.selectPayById(record.getSerialNum());

				Properties properties = new Properties();
				//发给供应商
				Company company = companyService.selectById(record.getBuyComId());
				Message messageVO = this.createMessage(event,user);
				List<UserCompanyKey> userCompanyKeys = userCompanyService.getUsersByComId(record.getBuyComId());
				List<String> userIds = new ArrayList<String>();
				for(UserCompanyKey uc : userCompanyKeys){
					userIds.add(uc.getUser_id());
				}
				messageVO.setMessageType(MessageConstants.SYSTEM_MESSAGE);
				messageVO.setTempleteType(MessageConstants.TEMP_FUKUAN); //收货消息
				messageVO.setObjectSerial(record.getSerialNum());
				messageVO.setReceiverIds(userIds);

				properties.put("paramer_a", company.getComName());
				properties.put("paramer_b", record.getOrderNum());
				properties.put("paramer_c", MessageConstants.URL_FUKUAN);
				properties.put("paramer_d", messageVO.getSerialNum());

				messageVO.setProperties(properties);
				messageProcessor.sendMessageToUsers(messageVO);
				messageService.insertBatch(messageVO);
				}
		} catch (Exception e) {
//			logger.warn(e.getMessage(), e);
		}

	}
	
	/**
	 * 
	 * @Description (销售分解为计划to 计划发布人员)
	 * @param event
	 */
	private void sale2palnMessage(SendMessageEvent event) {
		try {
			initService();
			User user = UserUtil.getUserFromSession();
			if(user != null){
				ProcurementPlan record=(ProcurementPlan)event.getSource();
				OrderInfo order = orderService.selectById(record.getSaleOrderSerial());
				List<User> users = null;
					users = groupService.selectUserIdsByGroupType(Constants.MANAGER);
					if(CollectionUtils.isNotEmpty(users)){
						for(User u : users){
							Properties properties = new Properties();
							Message messageVO = this.createMessage(event,user);
							messageVO.setMessageType(MessageConstants.BUSSINESS_MESSAGE);
							messageVO.setTempleteType(MessageConstants.SALE2PLAN); //收货消息
							messageVO.setObjectSerial(record.getSerialNum());
							messageVO.setReceiverId(u.getUserId().toString());
							properties.put("paramer_a", u.getUserName());
							properties.put("paramer_b", order.getOrderNum());
							properties.put("paramer_c", MessageConstants.URL_TO_BUYPLAN);
							properties.put("paramer_d", messageVO.getSerialNum());

							messageVO.setProperties(properties);
							messageProcessor.sendMessageToUser(messageVO);
							messageService.insert(messageVO);
						}
					}
				}
		} catch (Exception e) {
//			logger.warn(e.getMessage(), e);
		}

	}
	
	
	/**
	 * 
	 * @Description (采购计划发布to 采购人员)
	 * @param event
	 */
	private void paln2buyMessage(SendMessageEvent event) {
		try {
			initService();
			User user = UserUtil.getUserFromSession();
			if(user != null){
				ProcurementPlan record=(ProcurementPlan)event.getSource();
				List<User> users = null;
					users = groupService.selectUserIdsByGroupType(Constants.PURCHASE);
					if(CollectionUtils.isNotEmpty(users)){
						for(User u : users){
							Properties properties = new Properties();
							Message messageVO = this.createMessage(event,user);
							messageVO.setMessageType(MessageConstants.BUSSINESS_MESSAGE);
							messageVO.setTempleteType(MessageConstants.PLAN2BUY); //收货消息
							messageVO.setObjectSerial(record.getSerialNum());
							messageVO.setReceiverId(u.getUserId().toString());
							properties.put("paramer_a", u.getUserName());
							properties.put("paramer_b", record.getProcurementPlanNum());
							properties.put("paramer_c", MessageConstants.URL_BE_CONFIRM_BUY_ORDER);
							properties.put("paramer_d", messageVO.getSerialNum());

							messageVO.setProperties(properties);
							messageProcessor.sendMessageToUser(messageVO);
							messageService.insert(messageVO);
						}
					}
				}
		} catch (Exception e) {
//			logger.warn(e.getMessage(), e);
		}

	}
	
	
	/**
	 * 
	 * @Description (发货to 仓储)
	 * @param event
	 */
	private void in2stockMessage(SendMessageEvent event) {
		try {
			initService();
			User user = UserUtil.getUserFromSession();
			if(user != null){
				StockInOutRecord record=(StockInOutRecord)event.getSource();
				List<User> users = null;
					users = groupService.selectUserIdsByGroupType(Constants.STORAGE);
					if(CollectionUtils.isNotEmpty(users)){
						for(User u : users){
							Properties properties = new Properties();
							Message messageVO = this.createMessage(event,user);
							messageVO.setMessageType(MessageConstants.BUSSINESS_MESSAGE);
							messageVO.setTempleteType(MessageConstants.IN2STOCK); //收货消息
							messageVO.setObjectSerial(record.getSerialNum());
							messageVO.setReceiverId(u.getUserId().toString());
							properties.put("paramer_a", u.getUserName());
							properties.put("paramer_b", record.getInOutNum());
							properties.put("paramer_c", MessageConstants.URL_IN_TO_BUY);
							properties.put("paramer_d", messageVO.getSerialNum());

							messageVO.setProperties(properties);
							messageProcessor.sendMessageToUser(messageVO);
							messageService.insert(messageVO);
						}
					}
				}
		} catch (Exception e) {
//			logger.warn(e.getMessage(), e);
		}

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


	private List<DeliveryMateriel> getDeliveryMateriels(String deliverSerial){
		DeliveryMaterielExample example = new DeliveryMaterielExample();
		example.createCriteria().andDelFlgEqualTo(Constants.DEL_FLAG_DEFUALT).andDeliverSerialEqualTo(deliverSerial);
		return deliveryMaterielService.selectByExample(example);
	}

}