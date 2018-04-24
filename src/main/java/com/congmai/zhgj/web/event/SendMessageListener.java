package com.congmai.zhgj.web.event;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.congmai.zhgj.core.util.ApplicationContextHelper;
import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.core.util.Constants;
import com.congmai.zhgj.core.util.MessageConstants;
import com.congmai.zhgj.core.util.StringUtil;
import com.congmai.zhgj.core.util.UserUtil;
import com.congmai.zhgj.web.enums.StaticConst;
import com.congmai.zhgj.web.model.Company;
import com.congmai.zhgj.web.model.CustomsForm;
import com.congmai.zhgj.web.model.Delivery;
import com.congmai.zhgj.web.model.DeliveryMateriel;
import com.congmai.zhgj.web.model.DeliveryMaterielExample;
import com.congmai.zhgj.web.model.DeliveryVO;
import com.congmai.zhgj.web.model.DemandPlan;
import com.congmai.zhgj.web.model.Message;
import com.congmai.zhgj.web.model.OrderInfo;
import com.congmai.zhgj.web.model.OrderMateriel;
import com.congmai.zhgj.web.model.OrderMaterielExample;
import com.congmai.zhgj.web.model.PaymentRecord;
import com.congmai.zhgj.web.model.ProcurementPlan;
import com.congmai.zhgj.web.model.ProcurementPlanMateriel;
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
import com.congmai.zhgj.web.service.SendMailService;
import com.congmai.zhgj.web.service.MaterielService;
import com.congmai.zhgj.web.service.WebSocketService;
import com.congmai.zhgj.web.service.MessageService;
import com.congmai.zhgj.web.service.OrderMaterielService;
import com.congmai.zhgj.web.service.OrderService;
import com.congmai.zhgj.web.service.PayService;
import com.congmai.zhgj.web.service.ProcurementPlanService;
import com.congmai.zhgj.web.service.StockInOutCheckService;
import com.congmai.zhgj.web.service.TakeDeliveryService;
import com.congmai.zhgj.web.service.UserCompanyService;
import com.congmai.zhgj.web.service.UserService;
import com.congmai.zhgj.web.service.impl.MailProcessor;
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

	private Logger logger = LoggerFactory.getLogger(SendMessageListener.class);

	private WebSocketService webSocketProcessor = null;
	
	private SendMailService mailProcessor = null;

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
	
	private ProcurementPlanService procurementPlanService = null;
	
	private OrderMaterielService orderMaterielService = null;
	
	private MaterielService materielService = null;

	private void initService(){
		webSocketProcessor = (WebSocketProcessor)ApplicationContextHelper.getBean(WebSocketService.class);
		mailProcessor = (MailProcessor)ApplicationContextHelper.getBean(SendMailService.class);		
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
		procurementPlanService=  ApplicationContextHelper.getBean(ProcurementPlanService.class);
		orderMaterielService=  ApplicationContextHelper.getBean(OrderMaterielService.class);
		materielService=  ApplicationContextHelper.getBean(MaterielService.class);
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
		}else if(MessageConstants.APPLY_SALE_ORDER.equals(event.getAction())){ //销售订单申请消息
			applySaleOrderMessage(event);
		}else if(MessageConstants.REFUSE_SALE_ORDER.equals(event.getAction())){ //销售订单审批不通过
			//refuseSaleOrderMessage(event);
		}else if(MessageConstants.AGREE_SALE_ORDER.equals(event.getAction())){ //销售订单审批通过
			confirmSaleOrderMessage(event);
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
		}else if(MessageConstants.IN_TO_STOCK.equals(event.getAction())){// 确认代收货/发货生成出入库通知单提醒消息
			in2stockMessage(event);
		}else if(MessageConstants.OUT_TO_SALE_GROUP.equals(event.getAction())){ //出库(to 销售组)
			outToSaleGroupMessage(event);
		}else if(MessageConstants.IN_TO_WAITCHECK.equals(event.getAction())){ //确认代收货/发货生成入库检验单提醒消息
			inToWaitCheckMessage(event);
		}else if(MessageConstants.IN_TO_CUSTOMSFORM.equals(event.getAction())){ //确认代收货/发货生成清报关单提醒消息
			inToWaitCustomsform(event);
		}else if(MessageConstants.NOTICESUPPLY.equals(event.getAction())){ //平台代发货通知供应商修改消息
			inToSupply(event);
		}else if(MessageConstants.BE_CONFIRM_PAY.equals(event.getAction())){ //付款申请被确认(审批通过)
			inToPayOwner(event);
		}else if(MessageConstants.BE_RECEIVE_SALE_ORDER.equals(event.getAction())){ //采购商发布采购订单通知平台接收(销售订单)并分解采购
			buy2SaleGroupMessage(event);
		}else if(MessageConstants.BE_CONFIRM_APPLY_BUY_ORDER.equals(event.getAction())){ //采购商发布采购订单通知平台接收(销售订单)并分解采购
			sale2BuyGroupMessage(event);
		}else if(MessageConstants.DEMANDPLAN_TO_PROMANAGER.equals(event.getAction())){ //提交需求计划后通知产品经理
			demand2ProManagerMessage(event);
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
				webSocketProcessor.sendMessageToUser(messageVO);
				messageService.insert(messageVO);
			}
		} catch (Exception e) {
			logger.warn(e.getMessage(), e);
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
				webSocketProcessor.sendMessageToUser(messageVO);
				messageService.insert(messageVO);
			}
		} catch (Exception e) {
//			logger.warn(e.getMessage(), e);
		}
	}
	/**
	 * 
	 * @Description (付款申请通过消息发给付款制单人)
	 * @param event
	 */
	private void inToPayOwner(SendMessageEvent event) {
		try {
			initService();
			User user = UserUtil.getUserFromSession();
			if(user != null){
				Message messageVO = this.createMessage(event,user);

				messageVO.setMessageType(MessageConstants.BUSSINESS_MESSAGE);
				messageVO.setTempleteType(MessageConstants.TEMP_AGREE_PAYMENTRECORD); // 付款申请通过通知付款单制单人消息模板
				PaymentRecord  paymentRecord = (PaymentRecord) event.getSource();
				messageVO.setObjectSerial(paymentRecord.getSerialNum());

				User maker = userService.selectByUsername(paymentRecord.getCreator());
				Properties properties = new Properties();
				if( maker!=null ){
					messageVO.setReceiverId(maker.getUserId().toString());
					properties.put("paramer_a", maker.getUserName());
				}else{
					throw new Exception("没有找到消息接受者！");
				}
				properties.put("paramer_b", paymentRecord.getPaymentNum());
				properties.put("paramer_c", MessageConstants.URL_AGREE_PAYMENTRECORD);
				properties.put("paramer_d", messageVO.getSerialNum());
				messageVO.setProperties(properties);
				webSocketProcessor.sendMessageToUser(messageVO);
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
				webSocketProcessor.sendMessageToUsers(messageVO);
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
				webSocketProcessor.sendMessageToUser(messageVO);
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
				webSocketProcessor.sendMessageToUser(messageVO);
				messageService.insert(messageVO);
			}
		} catch (Exception e) {
//			logger.warn(e.getMessage(), e);
		}
	}
	/**
	 * 
	 * @Description (销售订单申请消息)
	 * @param event
	 */
	private void applySaleOrderMessage(SendMessageEvent event) {
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
				messageVO.setTempleteType(MessageConstants.TEMP_APPLY_SALE_ORDER); //销售订单申请消息
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
				properties.put("paramer_d", MessageConstants.URL_APPLY_SALE_ORDER);
				properties.put("paramer_f", messageVO.getSerialNum());
				if(StringUtils.isNotBlank(order.getReason())){
					properties.put("paramer_e", order.getReason());
				}else{
					properties.put("paramer_e", "无");
				}
				messageVO.setProperties(properties);
				webSocketProcessor.sendMessageToUser(messageVO);
				messageService.insert(messageVO);
			}
		} catch (Exception e) {
//			logger.warn(e.getMessage(), e);
		}


	}

	
	/**
	 * 
	 * @Description (销售订单审批通过消息)
	 * @param event
	 */
	private void confirmSaleOrderMessage(SendMessageEvent event) {
		try {
			initService();
			User user = UserUtil.getUserFromSession();
			if(user != null){
				Message messageVO = this.createMessage(event,user);

				messageVO.setMessageType(MessageConstants.SYSTEM_MESSAGE);
				messageVO.setTempleteType(MessageConstants.TEMP_AGREE_SALE_ORDER); //销售订单审批通过通知制单人
				OrderInfo order = (OrderInfo) event.getSource();
				messageVO.setObjectSerial(order.getSerialNum());
				Properties properties = new Properties();
				User maker = userService.selectByUsername(order.getMaker()); //制单人
				messageVO.setReceiverId(maker.getUserId()+"");
				properties.put("paramer_a", maker.getUserName());
				properties.put("paramer_b", order.getOrderNum());
				properties.put("paramer_c", MessageConstants.URL_CONFIRM_SALE_ORDER);
				properties.put("paramer_d", messageVO.getSerialNum());
				messageVO.setProperties(properties);
				webSocketProcessor.sendMessageToUser(messageVO);
				messageService.insert(messageVO);
//				if(StaticConst.getInfo("zizhuSale").equals(order.getOrderType())){//如果是自主销售订单
					List<User> users =new ArrayList<User>();
					List<User> users1 = groupService.selectUserIdsByGroupType(Constants.PRODUCT_MANAGER);
					List<User> users2 = groupService.selectUserIdsByGroupType(Constants.FULL_DEPARTMENT_LEADER);
					List<User> users3 = groupService.selectUserIdsByGroupType(Constants.FINANCIAL_LEADER);
					users.addAll(users1);
					/*users.addAll(users2);
					users.addAll(users3);*/
					for(User u : users){
						Message messageVO1 = this.createMessage(event,user);
						messageVO1.setMessageType(MessageConstants.SYSTEM_MESSAGE);
						messageVO1.setTempleteType(MessageConstants.TEMP_AGREE_SALE_ORDER); //采购计划组
						messageVO1.setObjectSerial(order.getSerialNum());
						messageVO1.setReceiverId(u.getUserId().toString());
						properties.put("paramer_a", u.getUserName());
						properties.put("paramer_b", maker.getUserName());
						properties.put("paramer_c", order.getOrderNum());
						properties.put("paramer_d", MessageConstants.URL_CONFIRM_SALE_ORDER);
						properties.put("paramer_e", messageVO1.getSerialNum());
						messageVO1.setProperties(properties);
						webSocketProcessor.sendMessageToUser(messageVO1);
						messageService.insert(messageVO1);
					}
					//个别用户发送邮件
					users =new ArrayList<User>();
					users.addAll(users2);
					users.addAll(users3);
					for(User u : users){
						Message messageVO1 = this.createMessage(event,user);
						messageVO1.setMessageType(MessageConstants.SYSTEM_MESSAGE);
						messageVO1.setTempleteType(MessageConstants.TEMP_AGREE_SALE_ORDER); //采购计划组
						messageVO1.setObjectSerial(order.getSerialNum());
						messageVO1.setReceiverId(u.getUserId().toString());
						properties.put("paramer_a", u.getUserName());
						properties.put("paramer_b", maker.getUserName());
						properties.put("paramer_c", order.getOrderNum());
						properties.put("paramer_d", MessageConstants.URL_CONFIRM_SALE_ORDER);
						properties.put("paramer_e", messageVO1.getSerialNum());
						messageVO1.setProperties(properties);
						webSocketProcessor.sendMessageToUser(messageVO1);
						mailProcessor.sendMessageToUser(messageVO1);
						messageService.insert(messageVO1);
					}
				}
				/*//再发给采购计划组
				List<User> users = groupService.selectUserIdsByGroupType(Constants.MANAGER);
				if(CollectionUtils.isNotEmpty(users)){
					//Message messageVO1= this.createMessage(event,user);
					for(User u : users){
						Message messageVO1 = this.createMessage(event,user);
						messageVO1.setMessageType(MessageConstants.BUSSINESS_MESSAGE);
						messageVO1.setTempleteType(MessageConstants.TEMP_AGREE_SALE_ORDER1); //采购计划组
						messageVO1.setObjectSerial(order.getSerialNum());
						messageVO1.setReceiverId(u.getUserId().toString());
						properties.put("paramer_a", u.getUserName());
						properties.put("paramer_b", maker.getUserName());
						properties.put("paramer_c", order.getOrderNum());
						properties.put("paramer_d", MessageConstants.URL_CONFIRM_SALE_ORDER);
						properties.put("paramer_e", messageVO1.getSerialNum());
						messageVO1.setProperties(properties);
						webSocketProcessor.sendMessageToUser(messageVO1);
						messageService.insert(messageVO1);
					}
				}*/
//			}
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
				webSocketProcessor.sendMessageToUsers(messageVO);
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
							webSocketProcessor.sendMessageToUser(messageVO);
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
					webSocketProcessor.sendMessageToUsers(messageVO);
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
						webSocketProcessor.sendMessageToUser(messageVO);
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
							webSocketProcessor.sendMessageToUser(messageVO);
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
					webSocketProcessor.sendMessageToUsers(messageVO);
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
							webSocketProcessor.sendMessageToUser(messageVO);
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
					webSocketProcessor.sendMessageToUsers(messageVO);
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
							webSocketProcessor.sendMessageToUser(messageVO);
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
					webSocketProcessor.sendMessageToUsers(messageVO);
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
							webSocketProcessor.sendMessageToUser(messageVO);
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
					webSocketProcessor.sendMessageToUsers(messageVO);
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
							webSocketProcessor.sendMessageToUser(messageVO);
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
					webSocketProcessor.sendMessageToUsers(messageVO);
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
							webSocketProcessor.sendMessageToUser(messageVO);
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
					webSocketProcessor.sendMessageToUsers(messageVO);
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
							webSocketProcessor.sendMessageToUser(messageVO);
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
					webSocketProcessor.sendMessageToUsers(messageVO);
					messageService.insertBatch(messageVO);
				}
			}
		} catch (Exception e) {
//			logger.warn(e.getMessage(), e);
		}

	}

	
	/**
	 * 
	 * @Description (入库 to 采购通知关联的销售订单制单人以及采购计划)
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
				Integer count = 0;
				for(DeliveryMateriel dm : params.getDeliveryMateriels()){
					count += Integer.parseInt(dm.getStockCount());
				}
				
//				if(StringUtils.isNotEmpty(order.getOrderSerial())){ //发给采购对应销售订单制单人
//					OrderInfo saleOrder = orderService.selectByOrderNum(order.getOrderSerial());//获取销售订单
//					User maker = userService.selectByUsername(saleOrder.getMaker()); //制单人
//					Properties properties = new Properties();
//					//通知采购订单的制单人
//					Message messageVO = this.createMessage(event,user);
//					messageVO.setMessageType(MessageConstants.SYSTEM_MESSAGE);
//					messageVO.setTempleteType(MessageConstants.TEMP_IN_TO_BUY_TO_SALE);
//					messageVO.setObjectSerial(params.getRecord().getSerialNum());
//					messageVO.setReceiverId(maker.getUserId().toString());
//					messageVO.setActionName(MessageConstants.IN_TO_INSTOCK);
//					properties.put("paramer_a", maker.getUserName());
//					properties.put("paramer_b", takeDelivery.getTakeDeliverNum());
//					properties.put("paramer_c", count);
//					properties.put("paramer_d", order.getOrderNum());
//					properties.put("paramer_e", saleOrder.getOrderNum());
//					properties.put("paramer_f", MessageConstants.URL_IN_TO_BUY);
//					properties.put("paramer_g", messageVO.getSerialNum());
//
//					messageVO.setProperties(properties);
//					webSocketProcessor.sendMessageToUser(messageVO);
//					messageService.insert(messageVO);
//				}
				//通知采购订单的制单人
				User maker = userService.selectByUsername(order.getMaker()); //制单人
				Properties properties = new Properties();
				Message messageVO = this.createMessage(event,user);
				messageVO.setMessageType(MessageConstants.SYSTEM_MESSAGE);
				messageVO.setTempleteType(MessageConstants.TEMP_IN_TO_BUY_TO_BUY);
				messageVO.setObjectSerial(params.getRecord().getSerialNum());
				messageVO.setReceiverId(maker.getUserId().toString());
				properties.put("paramer_a", maker.getUserName());
				properties.put("paramer_b", takeDelivery.getTakeDeliverNum());
				properties.put("paramer_c", count);
				properties.put("paramer_d", order.getOrderNum());
				properties.put("paramer_f", MessageConstants.URL_IN_TO_BUY);
				properties.put("paramer_g", messageVO.getSerialNum());
				messageVO.setProperties(properties);
				webSocketProcessor.sendMessageToUser(messageVO);
				messageService.insert(messageVO);
				
				List<User> users =new ArrayList<User>();
				List<User> users1 = groupService.selectUserIdsByGroupType(Constants.PRODUCT_MANAGER);//产品总监
				List<User> users2 = groupService.selectUserIdsByGroupType(Constants.FULL_DEPARTMENT_LEADER);//财务负责人（组）
				List<UserCompanyKey> users3 = userCompanyService.getUsersByComId(order.getSupplyComId());//获取供应商联系人
				if(CollectionUtils.isNotEmpty(users3)){
					for(UserCompanyKey uck:users3){
						User u=userService.getUserInfo(Integer.parseInt(uck.getUser_id()));
						users.add(u);
					}
				}
				if(StaticConst.getInfo("neimao").equals(order.getTradeType())){
					users=groupService.selectUserIdsByGroupType(Constants.PURCHASE);
				}else{
					users=groupService.selectUserIdsByGroupType(Constants.INTERNATIONALPURCHASE);
				}
				users.addAll(users1);
				users.addAll(users2);
				for(User u : users){
					Message messageVO1 = this.createMessage(event,user);
					messageVO1.setMessageType(MessageConstants.SYSTEM_MESSAGE);
					messageVO1.setTempleteType(MessageConstants.TEMP_IN_TO_BUY_TO_BUY); 
					messageVO1.setObjectSerial(order.getSerialNum());
					messageVO1.setReceiverId(u.getUserId().toString());
					properties.put("paramer_a", u.getUserName());
					properties.put("paramer_b", takeDelivery.getTakeDeliverNum());
					properties.put("paramer_c", count);
					properties.put("paramer_d", order.getOrderNum());
					properties.put("paramer_f", MessageConstants.URL_IN_TO_BUY);
					properties.put("paramer_g", messageVO.getSerialNum());
					messageVO1.setProperties(properties);
					webSocketProcessor.sendMessageToUser(messageVO1);
					messageService.insert(messageVO1);
				}
				//通知采购计划订单的制单人
//				if(StringUtils.isNotEmpty(order.getDemandPlanSerial())){ 
//					ProcurementPlanExample ppe=new  ProcurementPlanExample();
//					com.congmai.zhgj.web.model.ProcurementPlanExample.Criteria c=ppe.createCriteria();
//					c.andProcurementPlanNumEqualTo(order.getDemandPlanSerial());
//					List<ProcurementPlan> list=procurementPlanService.selectList(ppe);
//					User maker1 = userService.selectByUsername(list.get(0).getCreator()); //采购计划制单人
//					Properties properties1 = new Properties();
//					Message messageVO1 = this.createMessage(event,user);
//					messageVO1.setMessageType(MessageConstants.SYSTEM_MESSAGE);
//					messageVO1.setTempleteType(MessageConstants.TEMP_IN_TO_BUY_TO_PLAN);
//					messageVO1.setObjectSerial(params.getRecord().getSerialNum());
//					messageVO1.setReceiverId(maker1.getUserId().toString());
//					messageVO1.setActionName(MessageConstants.IN_TO_INSTOCK);
//					properties1.put("paramer_a", maker1.getUserName());
//					properties1.put("paramer_b", takeDelivery.getTakeDeliverNum());
//					properties.put("paramer_c", count);
//					properties.put("paramer_d", order.getOrderNum());
//					properties.put("paramer_e", order.getDemandPlanSerial());
//					properties.put("paramer_f", MessageConstants.URL_IN_TO_BUY);
//					properties.put("paramer_g", messageVO1.getSerialNum());
//					messageVO1.setProperties(properties);
//					webSocketProcessor.sendMessageToUser(messageVO1);
//					messageService.insert(messageVO1);
//				}
				
					/*//发给采购人员
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
							webSocketProcessor.sendMessageToUser(messageVO);
							messageService.insert(messageVO);
						}
					}*/
					
				/*	//发给销售人员
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
							webSocketProcessor.sendMessageToUser(messageVO);
							messageService.insert(messageVO);
						}
					}
					*/
					//发给供应链管理组（产品总监）
				/*	users = null;
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
							webSocketProcessor.sendMessageToUser(messageVO);
							messageService.insert(messageVO);
						}
					}*/
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
							webSocketProcessor.sendMessageToUser(messageVO);
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
					webSocketProcessor.sendMessageToUsers(messageVO);
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
				webSocketProcessor.sendMessageToUsers(messageVO);
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
				webSocketProcessor.sendMessageToUsers(messageVO);
				messageService.insertBatch(messageVO);
				}
		} catch (Exception e) {
//			logger.warn(e.getMessage(), e);
		}

	}
	
	/**
	 * 
	 * @Description (平台代发货通知供应商确认消息)
	 * @param event
	 */
	private void inToSupply(SendMessageEvent event) {
		try {
			initService();
			User user = UserUtil.getUserFromSession();
			if(user != null){
				Delivery record=(Delivery)event.getSource();
				List<User>list=userService.selectUserListByComId(record.getSupplyComId());//通知对应人员
				if(CollectionUtils.isNotEmpty(list)){
					//再通知通知供应商
					for(User u:list){
						Message messageVO = this.createMessage(event,user);
						Properties properties = new Properties();
						messageVO.setMessageType(MessageConstants.BUSSINESS_MESSAGE);
						messageVO.setTempleteType(MessageConstants.TEMP_NOTICESUPPLY); //发给通知供应商
						messageVO.setObjectSerial(record.getSerialNum());
						messageVO.setReceiverId(u.getUserId()+"");
						properties.put("paramer_a", u.getUserName());
						properties.put("paramer_b", record.getDeliverNum());
						properties.put("paramer_c", MessageConstants.URL_DELIVERY_TO_SUPPLY);
						properties.put("paramer_d", messageVO.getSerialNum());
						messageVO.setProperties(properties);
						webSocketProcessor.sendMessageToUser(messageVO);
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
							webSocketProcessor.sendMessageToUser(messageVO);
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
	 * @Description (发货to 仓储/采购)
	 * @param event
	 */
	private void inToWaitCheckMessage(SendMessageEvent event) {
		try {
			initService();
			User user = UserUtil.getUserFromSession();
			if(user != null){
				StockInOutCheck record=(StockInOutCheck)event.getSource();
				List<User> users = null;
					users = groupService.selectUserIdsByGroupType(Constants.STORAGE);
					if(CollectionUtils.isNotEmpty(users)){
						for(User u : users){
							Properties properties = new Properties();
							Message messageVO = this.createMessage(event,user);
							messageVO.setMessageType(MessageConstants.BUSSINESS_MESSAGE);
							if(StringUtil.isEmpty(record.getDeliverSerial())){//入库检验单
								messageVO.setTempleteType(MessageConstants.IN2SWAITCHECK); //入库检验消息
							}else{
								messageVO.setTempleteType(MessageConstants.OUT2SWAITCHECK); //出库检验消息
							}
							messageVO.setObjectSerial(record.getSerialNum());
							messageVO.setReceiverId(u.getUserId().toString());
							properties.put("paramer_a", u.getUserName());
							properties.put("paramer_b", record.getCheckNum());
							properties.put("paramer_c", MessageConstants.URL_IN_TO_CHECK);
							properties.put("paramer_d", messageVO.getSerialNum());

							messageVO.setProperties(properties);
							webSocketProcessor.sendMessageToUser(messageVO);
							messageService.insert(messageVO);
						}
					}
					//发给采购
					 users = null;
					users = groupService.selectUserIdsByGroupType(Constants.PURCHASE);
					if(CollectionUtils.isNotEmpty(users)){
						for(User u : users){
							Properties properties = new Properties();
							Message messageVO = this.createMessage(event,user);
							messageVO.setMessageType(MessageConstants.BUSSINESS_MESSAGE);
							if(StringUtil.isEmpty(record.getDeliverSerial())){//入库检验单
								messageVO.setTempleteType(MessageConstants.IN2SWAITCHECK); //入库检验消息
							}else{
								messageVO.setTempleteType(MessageConstants.OUT2SWAITCHECK); //出库检验消息
							}
							messageVO.setObjectSerial(record.getSerialNum());
							messageVO.setReceiverId(u.getUserId().toString());
							properties.put("paramer_a", u.getUserName());
							properties.put("paramer_b", record.getCheckNum());
							properties.put("paramer_c", MessageConstants.URL_IN_TO_CHECK);
							properties.put("paramer_d", messageVO.getSerialNum());

							messageVO.setProperties(properties);
							webSocketProcessor.sendMessageToUser(messageVO);
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
	 * @Description (发货to 仓储/采购)
	 * @param event
	 */
	private void inToWaitCustomsform(SendMessageEvent event) {
		try {
			initService();
			User user = UserUtil.getUserFromSession();
			if(user != null){
				CustomsForm  record=(CustomsForm)event.getSource();
				List<User> users = null;
					users = groupService.selectUserIdsByGroupType(Constants.STORAGE);
					if(CollectionUtils.isNotEmpty(users)){
						for(User u : users){
							Properties properties = new Properties();
							Message messageVO = this.createMessage(event,user);
							messageVO.setMessageType(MessageConstants.BUSSINESS_MESSAGE);
							if("clearance".equals(record.getCustomsFormType())){//清关单
								messageVO.setTempleteType(MessageConstants.IN2CLEARANCE); //清关单消息
								properties.put("paramer_c", MessageConstants.URL_IN_TO_CLEARANCE);
								messageVO.setActionName(MessageConstants.URL_IN_TO_CLEARANCE);
							}else{
								messageVO.setTempleteType(MessageConstants.IN2DECLARATION); //报关单消息
								properties.put("paramer_c", MessageConstants.URL_IN_TO_DECLARATION);
								messageVO.setActionName(MessageConstants.URL_IN_TO_DECLARATION);
							}
							messageVO.setObjectSerial(record.getSerialNum());
							messageVO.setReceiverId(u.getUserId().toString());
							properties.put("paramer_a", u.getUserName());
							properties.put("paramer_b", record.getCustomsFormNum());
							properties.put("paramer_d", messageVO.getSerialNum());

							messageVO.setProperties(properties);
							webSocketProcessor.sendMessageToUser(messageVO);
							messageService.insert(messageVO);
						}
					}
					//发给采购
					 users = null;
					users = groupService.selectUserIdsByGroupType(Constants.PURCHASE);
					if(CollectionUtils.isNotEmpty(users)){
						for(User u : users){
							Properties properties = new Properties();
							Message messageVO = this.createMessage(event,user);
							messageVO.setMessageType(MessageConstants.BUSSINESS_MESSAGE);
							if("clearance".equals(record.getCustomsFormType())){//清关单
								messageVO.setTempleteType(MessageConstants.IN2CLEARANCE); //清关单消息
								properties.put("paramer_c", MessageConstants.URL_IN_TO_CLEARANCE);
								messageVO.setActionName(MessageConstants.URL_IN_TO_CLEARANCE);
							}else{
								messageVO.setTempleteType(MessageConstants.IN2DECLARATION); //报关单消息
								properties.put("paramer_c", MessageConstants.URL_IN_TO_DECLARATION);
								messageVO.setActionName(MessageConstants.URL_IN_TO_DECLARATION);
							}
							messageVO.setObjectSerial(record.getSerialNum());
							messageVO.setReceiverId(u.getUserId().toString());
							properties.put("paramer_a", u.getUserName());
							properties.put("paramer_b", record.getCustomsFormNum());
							properties.put("paramer_d", messageVO.getSerialNum());

							messageVO.setProperties(properties);
							webSocketProcessor.sendMessageToUser(messageVO);
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
	 * @Description (发货to 仓储/采购)
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
							if(StringUtil.isEmpty(record.getDeliverSerial())){//入库通知单
								messageVO.setTempleteType(MessageConstants.IN2STOCK); //入库通知单消息
								messageVO.setActionName(MessageConstants.URL_OUT_TO_SALE);
							}else{
								messageVO.setTempleteType(MessageConstants.OUT2STOCK); //出库通知单消息
								messageVO.setActionName(MessageConstants.URL_IN_TO_BUY);
							}
							messageVO.setObjectSerial(record.getSerialNum());
							messageVO.setReceiverId(u.getUserId().toString());
							properties.put("paramer_a", u.getUserName());
							properties.put("paramer_b", record.getInOutNum());
							properties.put("paramer_c", MessageConstants.URL_IN_TO_BUY);
							properties.put("paramer_d", messageVO.getSerialNum());

							messageVO.setProperties(properties);
							webSocketProcessor.sendMessageToUser(messageVO);
							messageService.insert(messageVO);
						}
					}
					//发给采购
					 users = null;
					users = groupService.selectUserIdsByGroupType(Constants.PURCHASE);
					if(CollectionUtils.isNotEmpty(users)){
						for(User u : users){
							Properties properties = new Properties();
							Message messageVO = this.createMessage(event,user);
							messageVO.setMessageType(MessageConstants.SYSTEM_MESSAGE);
							if(StringUtil.isEmpty(record.getDeliverSerial())){//入库通知单
								messageVO.setTempleteType(MessageConstants.IN2STOCK); //入库通知单消息
							}else{
								messageVO.setTempleteType(MessageConstants.OUT2STOCK); //出库通知单消息
							}
							messageVO.setObjectSerial(record.getSerialNum());
							messageVO.setReceiverId(u.getUserId().toString());
							properties.put("paramer_a", u.getUserName());
							properties.put("paramer_b", record.getInOutNum());
							properties.put("paramer_c", MessageConstants.URL_IN_TO_BUY);
							properties.put("paramer_d", messageVO.getSerialNum());

							messageVO.setProperties(properties);
							webSocketProcessor.sendMessageToUser(messageVO);
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
	 * @Description (确认出库消息  to 销售组)
	 * @param event
	 */
	private void outToSaleGroupMessage(SendMessageEvent event) {
		try {
			initService();
			User user = UserUtil.getUserFromSession();
			if(user != null){
				TakeDeliveryParams takeDeliveryParams = (TakeDeliveryParams) event.getSource();

				Properties properties = new Properties();
				DeliveryVO delivery=deliveryService.selectDetailById(takeDeliveryParams.getRecord().getDeliverSerial());
				Integer count = 0;
				for(DeliveryMateriel dm : takeDeliveryParams.getDeliveryMateriels()){
					count += Integer.parseInt(dm.getStockCount()==null?"0":dm.getStockCount());
				}
				List<User> users = groupService.selectUserIdsByGroupType(Constants.SALES);
					if(CollectionUtils.isNotEmpty(users)){
						for(User u : users){
							Message messageVO = this.createMessage(event,user);
							messageVO.setMessageType(MessageConstants.SYSTEM_MESSAGE);
							messageVO.setTempleteType(MessageConstants.TEMP_OUT_TO_SALE_GROUP); //出库
							messageVO.setObjectSerial(takeDeliveryParams.getRecord().getSerialNum());
							messageVO.setReceiverId(u.getUserId().toString());
							properties.put("paramer_a", u.getUserName());
							properties.put("paramer_b", delivery.getDeliverNum());
							properties.put("paramer_c", count);
							properties.put("paramer_d", delivery.getOrderNum());
							properties.put("paramer_e", MessageConstants.URL_OUT_TO_SALE_GROUP);
							properties.put("paramer_f", messageVO.getSerialNum());

							messageVO.setProperties(properties);
							webSocketProcessor.sendMessageToUser(messageVO);
							messageService.insert(messageVO);
						}
					}
			}
		} catch (Exception e) {
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
				Properties properties = new Properties();
					users = groupService.selectUserIdsByGroupType(Constants.MANAGER);
					if(CollectionUtils.isNotEmpty(users)){
						for(User u : users){
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
							webSocketProcessor.sendMessageToUser(messageVO);
							messageService.insert(messageVO);
						}
					}
					//再通知销售订单制单人
					Message messageVO = this.createMessage(event,user);
					messageVO.setMessageType(MessageConstants.SYSTEM_MESSAGE);
					messageVO.setTempleteType(MessageConstants.SALE2PLANOWNER); //发给销售订单制单人
					messageVO.setObjectSerial(order.getSerialNum());
					User maker = userService.selectByUsername(order.getMaker()); //制单人
					messageVO.setReceiverId(maker.getUserId()+"");
					properties.put("paramer_a", maker.getUserName());
					properties.put("paramer_b", order.getOrderNum());
					properties.put("paramer_c", MessageConstants.URL_CONFIRM_SALE_ORDER);
					properties.put("paramer_d", messageVO.getSerialNum());
					messageVO.setProperties(properties);
					webSocketProcessor.sendMessageToUser(messageVO);
					messageService.insert(messageVO);
					//再通知采购物料关联的采购订单物料(未完成的采购订单)中关联的采购订单制单人
					List<ProcurementPlanMateriel> materielList = null;
					materielList = record.getMaterielList();
					OrderMaterielExample m=new  OrderMaterielExample();
					com.congmai.zhgj.web.model.OrderMaterielExample.Criteria c=m.createCriteria();
						if(CollectionUtils.isNotEmpty(materielList)){
							for( ProcurementPlanMateriel pm:materielList){
								if(StringUtil.isNotEmpty(pm.getSupplyComId())){//取物料供应商id
									//获取供应商comid为pm.getSupplyMaterielSerial()的未完成的采购订单
									List<OrderInfo>list=orderService.selectUnfinishedBuyOrderList(pm.getSupplyComId());
									if(CollectionUtils.isNotEmpty(list)){
										for(OrderInfo orderInfo:list){
											c.andOrderSerialEqualTo(orderInfo.getSerialNum()).andDelFlgEqualTo("0");
											List<OrderMateriel>omList=orderMaterielService.selectList(m);
											for(OrderMateriel om:omList){
												if(pm.getMaterielSerial().equals(om.getMaterielSerial())){
													Message messageVO1 = this.createMessage(event,user);
													Properties properties1 = new Properties();
													messageVO1.setMessageType(MessageConstants.BUSSINESS_MESSAGE);
													messageVO1.setTempleteType(MessageConstants.SALE2BUYORDEROWNER); //发给采购订单制单人
													messageVO1.setObjectSerial(orderInfo.getSerialNum());
													messageVO1.setActionName(MessageConstants.URL_BE_CONFIRM_BUY_ORDER);
													User maker1 = userService.selectByUsername(orderInfo.getMaker()); //制单人
													messageVO1.setReceiverId(maker1.getUserId()+"");
													properties1.put("paramer_a", maker1.getUserName());
													properties1.put("paramer_b", materielService.selectById(om.getMaterielSerial()).getMaterielName());
													properties1.put("paramer_c", pm.getDeliveryDate()==null?"":new SimpleDateFormat("yyyy-MM-dd").format(pm.getDeliveryDate())); //    
													properties1.put("paramer_d", pm.getPlanCount());
													properties1.put("paramer_e", orderInfo.getSupplyName());
													properties1.put("paramer_f", MessageConstants.URL_CONFIRM_SALE_ORDER);
													properties1.put("paramer_g", messageVO1.getSerialNum());
													messageVO1.setProperties(properties1);
													webSocketProcessor.sendMessageToUser(messageVO1);
													messageService.insert(messageVO1);
													break;
												}
											}
											
										}
										
									}
									
								}
								
							}
							
						}
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
				webSocketProcessor.sendMessageToUser(messageVO);
				messageService.insert(messageVO);
//				if(StaticConst.getInfo("zizhuBuy").equals(order.getOrderType())){//自主采购订单除制单人还需发给采购经理、产品经理、财务负责人（组）、综管负责人（组）
					List<User> users =new ArrayList<User>();
					List<User> users1 = groupService.selectUserIdsByGroupType(Constants.PRODUCT_MANAGER);//产品经理
					List<User> users2 = groupService.selectUserIdsByGroupType(Constants.FULL_DEPARTMENT_LEADER);//财务负责人（组）
					List<User> users3 = groupService.selectUserIdsByGroupType(Constants.FINANCIAL_LEADER);//综管负责人（组）
					List<User> users4 = groupService.selectUserIdsByGroupType(Constants.FINANCIAL);//财务部（huhui）
					if(StaticConst.getInfo("neimao").equals(order.getTradeType())){
						users=groupService.selectUserIdsByGroupType(Constants.PURCHASE);
					}else{
						users=groupService.selectUserIdsByGroupType(Constants.INTERNATIONALPURCHASE);
					}
					users.addAll(users1);
					users.addAll(users3);
					
					for(User u : users){
						Message messageVO1 = this.createMessage(event,user);
						messageVO1.setMessageType(MessageConstants.SYSTEM_MESSAGE);
						messageVO1.setTempleteType(MessageConstants.TEMP_AGREE_ZZ_BUY_ORDER); 
						messageVO1.setObjectSerial(order.getSerialNum());
						messageVO1.setReceiverId(u.getUserId().toString());
						properties.put("paramer_a", u.getUserName());
						properties.put("paramer_b", order.getOrderNum());
						properties.put("paramer_c", MessageConstants.URL_AGREE_BUY_ORDER);
						properties.put("paramer_d", messageVO1.getSerialNum());
						messageVO1.setProperties(properties);
						webSocketProcessor.sendMessageToUser(messageVO1);
						messageService.insert(messageVO1);
					}
					
					//个别用户发送邮件
					users =new ArrayList<User>();
					users.addAll(users2);
					users.addAll(users4);
					for(User u : users){
						Message messageVO1 = this.createMessage(event,user);
						messageVO1.setMessageType(MessageConstants.SYSTEM_MESSAGE);
						messageVO1.setTempleteType(MessageConstants.TEMP_AGREE_ZZ_BUY_ORDER); 
						messageVO1.setObjectSerial(order.getSerialNum());
						messageVO1.setReceiverId(u.getUserId().toString());
						properties.put("paramer_a", u.getUserName());
						properties.put("paramer_b", order.getOrderNum());
						properties.put("paramer_c", MessageConstants.URL_AGREE_BUY_ORDER);
						properties.put("paramer_d", messageVO1.getSerialNum());
						messageVO1.setProperties(properties);
						webSocketProcessor.sendMessageToUser(messageVO1);
						mailProcessor.sendMessageToUser(messageVO1);
						messageService.insert(messageVO1);
					}
					
//				}
				/*//向采购订单关联销售订单制单人发消息
				if(StringUtil.isNotEmpty(order.getOrderSerial())){//获取采购订单关联销售订单
					OrderInfo o=orderService.selectByOrderNum(order.getOrderSerial());
					User u= userService.selectByUsername(o.getMaker());
					Message messageVO1 = this.createMessage(event,user);
					messageVO1.setMessageType(MessageConstants.SYSTEM_MESSAGE);
					messageVO1.setTempleteType(MessageConstants.TEMP_AGREE_BUY_SALEORDER); //
					messageVO1.setObjectSerial(order.getSerialNum());
					properties.put("paramer_a", u.getUserName());
					properties.put("paramer_b", o.getOrderNum());
					properties.put("paramer_c", order.getOrderNum());
					properties.put("paramer_d", MessageConstants.URL_AGREE_BUY_ORDER);
					properties.put("paramer_e", messageVO.getSerialNum());
					messageVO1.setProperties(properties);
					webSocketProcessor.sendMessageToUser(messageVO1);
					messageService.insert(messageVO1);		
				}*/
			}
		} catch (Exception e) {
//			logger.warn(e.getMessage(), e);
		}
	}
	/**
	 * 
	 * @Description (采购商发布采购订单通知  to 销售组接收分解销售订单)
	 * @param event
	 */
	private void buy2SaleGroupMessage(SendMessageEvent event) {
		try {
			initService();
			User user = UserUtil.getUserFromSession();
			if(user != null){
				OrderInfo  order = (OrderInfo) event.getSource();
				OrderInfo orderInfo=orderService.selectById(order.getSerialNum());//取订单编号
				Company buyCom=companyService.selectById(order.getBuyComId());//获取订单采购商
				Properties properties = new Properties();
				List<User> users = groupService.selectUserIdsByGroupType(Constants.SALES);
					if(CollectionUtils.isNotEmpty(users)){
						for(User u : users){
							Message messageVO = this.createMessage(event,user);
							messageVO.setMessageType(MessageConstants.BUSSINESS_MESSAGE);
							messageVO.setTempleteType(MessageConstants.TEMP_BE_RECEIVE_SALE_ORDER); //采购商发布采购订单通知
							messageVO.setObjectSerial(order.getSerialNum());
							messageVO.setReceiverId(u.getUserId().toString());
							properties.put("paramer_a", u.getUserName());
							properties.put("paramer_b", buyCom==null?"":buyCom.getComName());
							properties.put("paramer_c", orderInfo.getOrderNum());
							properties.put("paramer_d", MessageConstants.URL_BE_RECEIVE_SALE_ORDER);
							properties.put("paramer_e", messageVO.getSerialNum());
							messageVO.setProperties(properties);
							webSocketProcessor.sendMessageToUser(messageVO);
							messageService.insert(messageVO);
						}
					}
			}
		} catch (Exception e) {
			logger.warn(e.getMessage(), e);
		}
	}
	
	/**
	 * 
	 * @Description (平台委托销售订单分解成功提示  to 采购组进行采购操作)
	 * @param event
	 */
	private void sale2BuyGroupMessage(SendMessageEvent event) {
		try {
			initService();
			User user = UserUtil.getUserFromSession();
			if(user != null){
				OrderInfo  order = (OrderInfo) event.getSource();
				Properties properties = new Properties();
				List<User> users =new ArrayList<User>();
				if(StaticConst.getInfo("waimao").equals(order.getTradeType())){//取国际采购组
					users = groupService.selectUserIdsByGroupType(Constants.INTERNATIONALPURCHASE);
				}else if(StaticConst.getInfo("neimao").equals(order.getTradeType())){//取国内采购组
					users= groupService.selectUserIdsByGroupType(Constants.PURCHASE);
				}
					if(CollectionUtils.isNotEmpty(users)){
						for(User u : users){
							Message messageVO = this.createMessage(event,user);
							messageVO.setMessageType(MessageConstants.BUSSINESS_MESSAGE);
							messageVO.setTempleteType(MessageConstants.TEMP_BE_CONFIRM_APPLY_BUY_ORDER); //平台委托销售订单分解成功提示  to 采购组进行采购通知
							messageVO.setObjectSerial(order.getSerialNum());
							messageVO.setReceiverId(u.getUserId().toString());
							properties.put("paramer_a", u.getUserName());
							properties.put("paramer_b", order.getUpdater());
							properties.put("paramer_c", order.getOrderNum());
							properties.put("paramer_d", MessageConstants.URL_BE_CONFIRM_APPLY_BUY_ORDER);
							properties.put("paramer_e", messageVO.getSerialNum());
							messageVO.setProperties(properties);
							webSocketProcessor.sendMessageToUser(messageVO);
							messageService.insert(messageVO);
						}
					}
			}
		} catch (Exception e) {
			logger.warn(e.getMessage(), e);
		}
	}
	
	/**
	 * 
	 * @Description (采购订单申请消息)
	 * @param event
	 */
	private void demand2ProManagerMessage(SendMessageEvent event) {
		try {
			initService();
			User user = UserUtil.getUserFromSession();
			if(user != null){
				DemandPlan  demandPlan = (DemandPlan) event.getSource();

				if(demandPlan.getCreator()!=null){
					user = userService.selectByUsername(demandPlan.getCreator());
				}
				Message messageVO = this.createMessage(event,user);
				messageVO.setMessageType(MessageConstants.BUSSINESS_MESSAGE);
				messageVO.setTempleteType(MessageConstants.TEMP_DEMANDPLAN_TO_PROMANAGER); //需求计划通知产品经理
				messageVO.setObjectSerial(demandPlan.getSerialNum());

				Properties properties = new Properties();
				List<User> users=groupService.selectUserIdsByGroupType(Constants.PRODUCT_MANAGER);
				if(CollectionUtils.isNotEmpty(users) ){
					messageVO.setReceiverId(users.get(0).getUserId().toString());
					properties.put("paramer_a", users.get(0).getUserName());
				}else{
					throw new Exception("没有找到消息接受者！");
				}
				properties.put("paramer_b", demandPlan.getDemandPlanNum());
				properties.put("paramer_c", MessageConstants.URL_DEMANDPLAN_TO_PROMANAGER);
				properties.put("paramer_d", messageVO.getSerialNum());
				messageVO.setProperties(properties);
				webSocketProcessor.sendMessageToUser(messageVO);
				messageService.insert(messageVO);
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