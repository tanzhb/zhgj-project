package com.congmai.zhgj.web.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.hibernate.id.GUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.congmai.zhgj.core.feature.orm.mybatis.Page;
import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.core.util.BeanUtils;
import com.congmai.zhgj.core.util.DateUtil;
import com.congmai.zhgj.core.util.ExcelReader;
import com.congmai.zhgj.core.util.ExcelReader.RowHandler;
import com.congmai.zhgj.core.util.ExcelUtil;
import com.congmai.zhgj.core.util.MessageConstants;
import com.congmai.zhgj.core.util.UserUtil;
import com.congmai.zhgj.log.annotation.OperationLog;
import com.congmai.zhgj.web.enums.StaticConst;
import com.congmai.zhgj.web.event.EventExample;
import com.congmai.zhgj.web.event.SendMessageEvent;
import com.congmai.zhgj.web.model.BOMMateriel;
import com.congmai.zhgj.web.model.BOMMaterielExample;
import com.congmai.zhgj.web.model.BaseVO;
import com.congmai.zhgj.web.model.ClauseAdvance;
import com.congmai.zhgj.web.model.ClauseAfterSales;
import com.congmai.zhgj.web.model.ClauseCheckAccept;
import com.congmai.zhgj.web.model.ClauseDelivery;
import com.congmai.zhgj.web.model.ClauseFramework;
import com.congmai.zhgj.web.model.ClauseFrameworkExample;
import com.congmai.zhgj.web.model.ClauseSettlement;
import com.congmai.zhgj.web.model.ClauseSettlementDetail;
import com.congmai.zhgj.web.model.CommentVO;
import com.congmai.zhgj.web.model.Company;
import com.congmai.zhgj.web.model.ContractFile;
import com.congmai.zhgj.web.model.ContractFileExample;
import com.congmai.zhgj.web.model.ContractVO;
import com.congmai.zhgj.web.model.Delivery;
import com.congmai.zhgj.web.model.DeliveryMateriel;
import com.congmai.zhgj.web.model.DeliveryVO;
import com.congmai.zhgj.web.model.DemandPlanMateriel;
import com.congmai.zhgj.web.model.HistoricTaskVO;
import com.congmai.zhgj.web.model.Invoice;
import com.congmai.zhgj.web.model.LadderPrice;
import com.congmai.zhgj.web.model.Materiel;
import com.congmai.zhgj.web.model.MemoRecord;
import com.congmai.zhgj.web.model.OperateLog;
import com.congmai.zhgj.web.model.OperateLogExample;
import com.congmai.zhgj.web.model.OrderFile;
import com.congmai.zhgj.web.model.OrderFileExample;
import com.congmai.zhgj.web.model.OrderInfo;
import com.congmai.zhgj.web.model.OrderMateriel;
import com.congmai.zhgj.web.model.OrderMaterielExample;
import com.congmai.zhgj.web.model.PriceList;
import com.congmai.zhgj.web.model.ProcurementPlan;
import com.congmai.zhgj.web.model.ProcurementPlanMateriel;
import com.congmai.zhgj.web.model.StockInOutRecord;
import com.congmai.zhgj.web.model.SupplyMateriel;
import com.congmai.zhgj.web.model.User;
import com.congmai.zhgj.web.service.ClauseAdvanceService;
import com.congmai.zhgj.web.service.ClauseAfterSalesService;
import com.congmai.zhgj.web.service.ClauseCheckAcceptService;
import com.congmai.zhgj.web.service.ClauseDeliveryService;
import com.congmai.zhgj.web.service.ClauseFrameworkService;
import com.congmai.zhgj.web.service.ClauseSettlementDetailService;
import com.congmai.zhgj.web.service.ClauseSettlementService;
import com.congmai.zhgj.web.service.CompanyService;
import com.congmai.zhgj.web.service.ContractFileService;
import com.congmai.zhgj.web.service.ContractService;
import com.congmai.zhgj.web.service.DeliveryMaterielService;
import com.congmai.zhgj.web.service.DeliveryService;
import com.congmai.zhgj.web.service.DemandPlanMaterielService;
import com.congmai.zhgj.web.service.IProcessService;
import com.congmai.zhgj.web.service.InvoiceService;
import com.congmai.zhgj.web.service.LadderPriceService;
import com.congmai.zhgj.web.service.OperateLogService;
import com.congmai.zhgj.web.service.OrderFileService;
import com.congmai.zhgj.web.service.OrderMaterielService;
import com.congmai.zhgj.web.service.OrderService;
import com.congmai.zhgj.web.service.PriceListService;
import com.congmai.zhgj.web.service.ProcessBaseService;
import com.congmai.zhgj.web.service.ProcurementPlanMaterielService;
import com.congmai.zhgj.web.service.ProcurementPlanService;
import com.congmai.zhgj.web.service.StockService;
import com.congmai.zhgj.web.service.UserCompanyService;


/**
 * 
 * @ClassName OrderController
 * @Description 物料信息业务处理
 * @author qfzhao
 * @Date 2017年7月28日 下午2:57:52
 * @version 1.0.0
 */
@Controller
@RequestMapping("/order")
public class OrderController {
	private static final Logger logger = Logger.getLogger(OrderController.class);
	@Autowired
	protected RuntimeService runtimeService;
	@Autowired
    protected TaskService taskService;
	@Autowired
	private IProcessService processService;
    @Resource
    private OrderService orderService;
    @Resource
    private OrderMaterielService orderMaterielService;
    @Resource
    private ClauseAfterSalesService clauseAfterSalesService;
    @Resource
    private ClauseAdvanceService clauseAdvanceService;
    @Resource
    private ClauseCheckAcceptService clauseCheckAcceptService;
    @Resource
    private ClauseDeliveryService clauseDeliveryService;
    @Resource
    private ClauseSettlementService clauseSettlementService;
    @Resource
    private ClauseSettlementDetailService clauseSettlementDetailService;
    @Resource
    private OrderFileService orderFileService;
    @Resource
    private ContractFileService contractFileService;
    @Resource
    private ClauseFrameworkService clauseFrameworkService;
    @Resource
    private UserCompanyService userCompanyService;
    @Resource
    private ProcessBaseService processBaseService;
    @Resource
    private OperateLogService operateLogService;
    @Resource
    private DemandPlanMaterielService demandPlanMaterielService;
    @Resource
    private CompanyService companyService;
    @Resource
    private StockService stockService;
    @Resource
    private PriceListService  priceListService;
    @Resource
    private LadderPriceService  ladderPriceService;
    @Resource
    private InvoiceService  invoiceService;
    @Autowired
	private DeliveryMaterielService deliveryMaterielService;
    @Autowired
	private DeliveryService deliveryService;
    
    
	//销售订单
	public static final String SALEORDER = "sale";
	
	//采购订单
	public static final String BUYORDER = "buy";
	
/*	//供应商订单（因为是平台方创建，显示需限制为已发布）
	public static final String SUPPLYORDER = "supply";*/
	/**
	 * 合同管理service
	 */
	@Resource
	private ContractService contractService;
    
    /**
     * 保存订单
     */
    @RequestMapping(value = "/saveOrder", method = RequestMethod.POST)
    @ResponseBody
    public OrderInfo saveOrder(@RequestBody String params) {
    	OrderInfo orderInfo = json2Order(params);
            
    	if(orderInfo.getSerialNum()==null||orderInfo.getSerialNum().isEmpty()){//新增
    		insetOrder(orderInfo);
    		
    	}else{//更新
    		updateOrder(orderInfo);
    	}
    	/*if("1".equals(orderInfo.getStatus())){
    		//启动订单审批测试流程-start
    		startOrderProcess(orderInfo);
    		//启动订单审批测试流程-end
    	}*/
    	orderInfo = orderService.selectById(orderInfo.getSerialNum());
		return orderInfo;
    }
    
    /**
     * 客户提交订单
     */
    @RequestMapping(value = "/submitOrder", method = RequestMethod.POST)
    @ResponseBody
    public OrderInfo submitOrder(@RequestBody String params) {
    	OrderInfo orderInfo = json2Order(params);
    	Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
		orderInfo.setUpdater(currenLoginName);
		orderInfo.setUpdateTime(new Date());
		orderService.submitOrder(orderInfo);
		return orderInfo;
    }
    
    /**
     * 接受客户订单
     */
    @RequestMapping(value = "/acceptSubmit", method = RequestMethod.POST)
    @ResponseBody
    public OrderInfo acceptSubmit(@RequestBody String params) {
    	OrderInfo orderInfo = json2Order(params);
    	Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
		orderInfo.setUpdater(currenLoginName);
		orderInfo.setUpdateTime(new Date());
		orderService.acceptSubmit(orderInfo);
		return orderInfo;
    }
    
    
    /**
     * 供应商提交订单
     */
    @RequestMapping(value = "/supplyConfirmed", method = RequestMethod.POST)
    @ResponseBody
    public OrderInfo supplyConfirmed(@RequestBody String params) {
    	OrderInfo orderInfo = json2Order(params);
    	Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
		orderInfo.setUpdater(currenLoginName);
		orderInfo.setUpdateTime(new Date());
		orderService.supplyConfirmed(orderInfo);
		return orderInfo;
    }
    
    
    /**
     * 平台提交订单
     */
    @RequestMapping(value = "/pingTaiSubmit", method = RequestMethod.POST)
    @ResponseBody
    public OrderInfo pingTaiSubmit(@RequestBody String params) {
    	OrderInfo orderInfo = json2Order(params);
    	Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
		orderInfo.setUpdater(currenLoginName);
		orderInfo.setUpdateTime(new Date());
		orderService.pingTaiSubmit(orderInfo);
		return orderInfo;
    }
    
    
    /**
     * 保存订单
     */
    @RequestMapping(value = "/checkNum", method = RequestMethod.POST)
    @ResponseBody
    public String checkNum(@RequestBody String params) {
    	OrderInfo orderInfo = json2Order(params);
    	String flag = "0";
    	flag = orderService.checkNum(orderInfo);
		return flag;
    }
    
    

    /**
     * 平台接收订单
     */
    @RequestMapping(value = "/reciveOrder", method = RequestMethod.POST)
    @ResponseBody
    public OrderInfo reciveOrder(@RequestBody String params) {
    	OrderInfo orderInfo = json2Order(params);
    	Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
		orderInfo.setUpdater(currenLoginName);
		orderInfo.setUpdateTime(new Date());
		orderService.reciveOrder(orderInfo);
		
		orderInfo = orderService.selectById(orderInfo.getSerialNum());
		EventExample.getEventPublisher().publicSendMessageEvent(new SendMessageEvent(orderInfo,MessageConstants.BE_CONFIRM_BUY_ORDER));
		return orderInfo;
    }
    
	private void updateOrder(OrderInfo orderInfo) {
		Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
		orderInfo.setUpdater(currenLoginName);
		orderInfo.setUpdateTime(new Date());
		if(StringUtils.isNotEmpty(orderInfo.getStatus())){
			orderService.updateStatus(orderInfo);
		}else{
			orderService.update(orderInfo);
		}
		
	}
    
	private void insetOrder(OrderInfo orderInfo) {
		orderInfo.setSerialNum(ApplicationUtils.random32UUID());
		Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
		orderInfo.setCreator(currenLoginName);
		orderInfo.setUpdater(currenLoginName);
		orderInfo.setMaker(currenLoginName);
		orderInfo.setCreateTime(new Date());
		orderInfo.setUpdateTime(new Date());
		orderInfo.setMakeDate(new Date());
		orderInfo.setStatus("0");
		
		String comId = null;
    	User user = UserUtil.getUserFromSession();
    	if(user!=null){
			comId = userCompanyService.getUserComId(String.valueOf(user.getUserId()));
			if(comId!=null){
				orderInfo.setBuyComId(comId);
				orderInfo.setStatus(OrderInfo.CUSTOMER);
			}
		}
    	
		orderService.insert(orderInfo);
	}

	private OrderInfo json2Order(String params) {
		params = params.replace("\\", "");
		ObjectMapper objectMapper = new ObjectMapper();  
        OrderInfo orderInfo = new OrderInfo();
		try {
			orderInfo = objectMapper.readValue(params, OrderInfo.class);
		}catch (Exception e1) {
			e1.printStackTrace();
		}
		return orderInfo;
	}


    /**
     * 
     * @Description (启动采购订单流程)
     * @param params
     * @return
     */
    @RequestMapping(value = "/startBuyOrderProcess", method = RequestMethod.POST)
    @ResponseBody
    public String startBuyOrderProcess(@RequestBody String params) {
    	String flag = "0"; //默认失败
    	OrderInfo orderInfo = json2Order(params);
    	orderInfo.setUpdateTime(new Date());
    	orderInfo.setStatus("1");//在未确认状态提交申请的订单，设置为已确认
    	orderService.updateStatus(orderInfo);//更新备注
    	
		//启动订单审批测试流程-start
		User user = UserUtil.getUserFromSession();
		orderInfo.setUserId(user.getUserId());
		orderInfo.setUser_name(user.getUserName());
		orderInfo.setTitle(user.getUserName()+" 的订单申请");
		orderInfo.setBusinessType(BaseVO.BUYORDER); 			//业务类型：订单审核
		orderInfo.setStatus(BaseVO.PENDING);					//审批中
    	orderInfo.setApplyDate(new Date());
    	orderInfo.setReason(orderInfo.getRemark());
    	processBaseService.insert(orderInfo);
		String businessKey = orderInfo.getSerialNum().toString();
		orderInfo.setBusinessKey(businessKey);
		try {
			OrderInfo orderInfo2 = orderService.selectById(orderInfo.getSerialNum());
			orderInfo.setSupplyComId(orderInfo2.getSupplyComId());
			String processInstanceId = this.processService.startBuyOrderInfo(orderInfo);
			
			//申请加入流程已办
			HistoricTaskVO historicTaskVO = new HistoricTaskVO();
			historicTaskVO.setTaskId(ApplicationUtils.random32UUID());
			historicTaskVO.setProcessInstanceId(processInstanceId);
			historicTaskVO.setStartTime(new Date());
			historicTaskVO.setEndTime(new Date());
			historicTaskVO.setProcessDefId(orderInfo.getBusinessKey());
			historicTaskVO.setUserId(user.getUserId().toString());
			
			processBaseService.insertHistoricTask(historicTaskVO);
//                message.setStatus(Boolean.TRUE);
//    			message.setMessage("订单流程已启动，流程ID：" + processInstanceId);
		    logger.info("processInstanceId: "+processInstanceId);
		    
		    EventExample.getEventPublisher().publicSendMessageEvent(new SendMessageEvent(orderInfo,MessageConstants.APPLY_BUY_ORDER));
		    
		    flag = "1";
		} catch (ActivitiException e) {
//            	message.setStatus(Boolean.FALSE);
		    if (e.getMessage().indexOf("no processes deployed with key") != -1) {
		        logger.warn("没有部署流程!", e);
//        			message.setMessage("没有部署流程，请联系系统管理员，在[流程定义]中部署相应流程文件！");
		    } else {
		        logger.error("启动订单流程失败：", e);
//                    message.setMessage("启动订单流程失败，系统内部错误！");
		    }
		    throw e;
		} catch (Exception e) {
		    logger.error("启动订单流程失败：", e);
//                message.setStatus(Boolean.FALSE);
//                message.setMessage("启动订单流程失败，系统内部错误！");
		    throw e;
		}
        //启动订单审批测试流程-end
		return flag;
	}
    
    
    /**
     * 
     * @Description (启动销售订单流程)
     * @param params
     * @return
     */
    @RequestMapping(value = "/startSaleOrderProcess", method = RequestMethod.POST)
    @ResponseBody
    public String startSaleOrderProcess(@RequestBody String params) {
    	String flag = "0"; //默认失败
    	OrderInfo orderInfo = json2Order(params);
    	orderInfo.setUpdateTime(new Date());
    	orderService.updateStatus(orderInfo);//更新备注
    	
		//启动订单审批测试流程-start
		User user = UserUtil.getUserFromSession();
		orderInfo.setUserId(user.getUserId());
		orderInfo.setUser_name(user.getUserName());
		orderInfo.setTitle(user.getUserName()+" 的订单申请");
		orderInfo.setBusinessType(BaseVO.SALEORDER); 			//业务类型：采购订单
		orderInfo.setStatus(BaseVO.PENDING);					//审批中
    	orderInfo.setApplyDate(new Date());
    	orderInfo.setReason(orderInfo.getRemark());
    	processBaseService.insert(orderInfo);
		String businessKey = orderInfo.getSerialNum().toString();
		orderInfo.setBusinessKey(businessKey);
		try {
			String processInstanceId = this.processService.startSaleOrderInfo(orderInfo);
			
			//申请加入流程已办
			HistoricTaskVO historicTaskVO = new HistoricTaskVO();
			historicTaskVO.setTaskId(ApplicationUtils.random32UUID());
			historicTaskVO.setProcessInstanceId(processInstanceId);
			historicTaskVO.setStartTime(new Date());
			historicTaskVO.setEndTime(new Date());
			historicTaskVO.setProcessDefId(orderInfo.getBusinessKey());
			historicTaskVO.setUserId(user.getUserId().toString());
			
			processBaseService.insertHistoricTask(historicTaskVO);
//                message.setStatus(Boolean.TRUE);
//    			message.setMessage("订单流程已启动，流程ID：" + processInstanceId);
		    logger.info("processInstanceId: "+processInstanceId);
		    flag = "1";
		} catch (ActivitiException e) {
//            	message.setStatus(Boolean.FALSE);
		    if (e.getMessage().indexOf("no processes deployed with key") != -1) {
		        logger.warn("没有部署流程!", e);
//        			message.setMessage("没有部署流程，请联系系统管理员，在[流程定义]中部署相应流程文件！");
		    } else {
		        logger.error("启动订单流程失败：", e);
//                    message.setMessage("启动订单流程失败，系统内部错误！");
		    }
		    throw e;
		} catch (Exception e) {
		    logger.error("启动订单流程失败：", e);
//                message.setStatus(Boolean.FALSE);
//                message.setMessage("启动订单流程失败，系统内部错误！");
		    throw e;
		}
        //启动订单审批测试流程-end
		return flag;
	}
    
    
    /**
     * 审批订单流程
     * @param taskId
     * @param model
     * @return
     * @throws NumberFormatException
     * @throws Exception
     */
//	@RequiresPermissions("user:order:toApproval") 	//*代表 经理、总监、人力
    @RequestMapping(value = "/toApproval/{taskId}", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody Map<String, Object> toApproval(@PathVariable("taskId") String taskId) throws NumberFormatException, Exception{
    	Task task = this.taskService.createTaskQuery().taskId(taskId).singleResult();
		// 根据任务查询流程实例
    	OrderInfo orderInfo = new OrderInfo();
    	List<CommentVO> commentList = new ArrayList<CommentVO>();
    	String result = null;
    	if(task!=null){
    		String processInstanceId = task.getProcessInstanceId();
    		ProcessInstance pi = this.runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
    		orderInfo = (OrderInfo) this.runtimeService.getVariable(pi.getId(), "entity");
    		orderInfo.setTask(task);
    		orderInfo.setProcessInstanceId(processInstanceId);
    		commentList = this.processService.getComments(processInstanceId);
    		String taskDefinitionKey = task.getTaskDefinitionKey();
    		logger.info("taskDefinitionKey: "+taskDefinitionKey);
    		
    		if("modifyApply".equals(taskDefinitionKey)){
    			result = "modify";
    		}else{
    			result = "audit";
    		}
    	}
    	
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("actionType", result);
		map.put("orderInfo", orderInfo);
		map.put("commentList", commentList);
    	return map;
    }
    
    
    /**
     * 完成任务
     * @param content
     * @param completeFlag
     * @param taskId
     * @param redirectAttributes
     * @param session
     * @return
     * @throws Exception
     */
//	@RequiresPermissions("user:order:complate")  //数据库中权限字符串为user:*:complate， 通配符*匹配到order所以有权限操作 
    @RequestMapping(value = "/complate/{taskId}", method = RequestMethod.POST, produces = "application/text;charset=UTF-8")
	@ResponseBody
    public String complate(
    		@RequestParam("orderId") String orderId,
    		@RequestParam("content") String content,
    		@RequestParam("processInstanceId") String processInstanceId,
    		@RequestParam("completeFlag") Boolean completeFlag,
    		@PathVariable("taskId") String taskId, 
    		RedirectAttributes redirectAttributes) throws Exception{
    	User user = UserUtil.getUserFromSession();
    	String result = "";
    	try {
    		OrderInfo order = this.orderService.selectById(orderId);
            OrderInfo baseOrderInfo = (OrderInfo) this.runtimeService.getVariable(processInstanceId, "entity");
    		Map<String, Object> variables = new HashMap<String, Object>();
    		variables.put("isPass", completeFlag);
    		if(!completeFlag){
    			baseOrderInfo.setTitle(baseOrderInfo.getUser_name()+" 的订单申请失败,需修改后重新提交！");
    			order.setStatus(BaseVO.APPROVAL_FAILED);
    			variables.put("entity", baseOrderInfo);
    			
    			
    	
    		}else{
    			order.setStatus(BaseVO.PENDING);					//审批中
    		}
    		// 完成任务，返回当前节点定义
    		String taskDefinitionKey = this.processService.complete(taskId, content, user.getUserId().toString(), variables);
    		
    		ProcessInstance pi = null;
    		if(completeFlag){
    			//此处需要修改，不能根据人来判断审批是否结束。应该根据流程实例id(processInstanceId)来判定。
    			//判断指定ID的实例是否存在，如果结果为空，则代表流程结束，实例已被删除(移到历史库中)
    			pi = this.runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
    			/*if(BeanUtils.isBlank(pi)){
    				order.setStatus(BaseVO.APPROVAL_SUCCESS);
    			}*/
    			if("managerAudit4".equals(taskDefinitionKey)){//副总审批节点，流程结束
    				order.setStatus(BaseVO.APPROVAL_SUCCESS);
    			}
    		}
    		
    		this.processBaseService.update(order);
    		
    		if(BaseVO.APPROVAL_SUCCESS.equals(order.getStatus())){//订单完成，需更新状态为1(订单待接收)
    			OrderInfo oi = new OrderInfo();
    			oi.setSerialNum(order.getSerialNum());
    			
    			//获取合同信息
    	    	ContractVO contract = null;
    			if(StringUtils.isNotEmpty(order.getContractSerial())){
    	    		contract=contractService.selectConbtractById(order.getContractSerial());
    	    	}
    	    	if(contract!=null){
    	    		if(StaticConst.getInfo("buyOrder").equals(contract.getContractType())){//采购订单
    	    			oi.setStatus("2");//跳过合同签订
    	    		}else if(StaticConst.getInfo("saleOrder").equals(contract.getContractType())){//销售订单
    	    			oi.setStatus("2");//跳过合同签订
    	    		}else if(StaticConst.getInfo("buyContract").equals(contract.getContractType())){//采购合同
    	    			oi.setStatus("2");//不在合同签订了
    	    		}else if(StaticConst.getInfo("saleContract").equals(contract.getContractType())){//销售合同
    	    			oi.setStatus("2");//不在合同签订了
    	    		}
    	    	}
    			
    			this.orderService.updateStatus(oi);
    			//自主销售订单审批完成，分解BOM
    			if(StaticConst.getInfo("zizhuSale").equals(order.getOrderType())){//自主销售
    				saleGenerateProcurementPlan(order.getSerialNum());
	    		}
        		
    		}
    		
    		
    		order.setProcessInstanceId(processInstanceId);
    		//发送消息
    		if(!completeFlag){
    			//采购订单驳回消息
    		    EventExample.getEventPublisher().publicSendMessageEvent(new SendMessageEvent(order,MessageConstants.REFUSE_BUY_ORDER));
    		}else{
    			//给中间审批人发消息
				EventExample.getEventPublisher().publicSendMessageEvent(new SendMessageEvent(order,MessageConstants.APPLY_BUY_ORDER));
				//给制单人发送消息
				EventExample.getEventPublisher().publicSendMessageEvent(new SendMessageEvent(order,MessageConstants.SINGLE_AGREE_BUY_ORDER));
    			/*if(BeanUtils.isBlank(pi)){*/
				if("managerAudit4".equals(taskDefinitionKey)){//副总审批节点，流程结束
    				//采购订单审核通过消息
        		    EventExample.getEventPublisher().publicSendMessageEvent(new SendMessageEvent(order,MessageConstants.AGREE_BUY_ORDER));
        		    //采购订单待确认（发给供应商）
        		    EventExample.getEventPublisher().publicSendMessageEvent(new SendMessageEvent(order,MessageConstants.CONFIRM_BUY_ORDER));
    			}
    		}
    		result = "任务办理完成！";
		} catch (ActivitiObjectNotFoundException e) {
			result = "此任务不存在，请联系管理员！";
			throw e;
		} catch (ActivitiException e) {
			result = "此任务正在协办，您不能办理此任务！";
			throw e;
		} catch (Exception e) {
			result = "任务办理失败，请联系管理员！";
			throw e;
		}
		return result;
    }
	
    
    /**
	 * 调整订单申请
	 * @param vacation
	 * @param taskId
	 * @param processInstanceId
	 * @param reApply
	 * @param session
	 * @return
	 * @throws Exception
	 */
//	@RequiresPermissions("user:vacation:modify")
	@RequestMapping(value = "/modifyOrder/{taskId}", method = RequestMethod.POST, produces = "application/text;charset=UTF-8")
	@ResponseBody
	public String modifyOrder(
			@PathVariable("taskId") String taskId,
			@RequestParam("processInstanceId") String processInstanceId,
			@RequestParam("reApply") Boolean reApply,
			@RequestParam("orderId") String orderId,
			@RequestParam("reason") String reason,
			@RequestParam("orderType") String orderType) throws Exception{
		String result = "";
		User user = UserUtil.getUserFromSession();

		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setSerialNum(orderId);
		
        Map<String, Object> variables = new HashMap<String, Object>();
        orderInfo.setUserId(user.getUserId());
        orderInfo.setUser_name(user.getUserName());
        if(BaseVO.SALEORDER.equals(orderType)){
        	orderInfo.setBusinessType(BaseVO.SALEORDER);
        }else{
        	orderInfo.setBusinessType(BaseVO.BUYORDER);
        }
        
        orderInfo.setApplyDate(new Date());
        orderInfo.setBusinessKey(orderId);
        orderInfo.setProcessInstanceId(processInstanceId);
        String content = "";
        if(reApply){
        	//修改订单申请
        	orderInfo.setTitle(user.getUserName()+" 的订单申请！");
        	orderInfo.setStatus(BaseVO.PENDING);
	        content = "重新申请";
	        result = "任务办理完成，订单申请已重新提交！";
	        
	      
        }else{
        	orderInfo.setTitle(user.getUserName()+" 的订单申请已取消！");
        	orderInfo.setStatus(BaseVO.APPROVAL_FAILED);
        	content = "取消申请";
        	result = "任务办理完成，已经取消您的订单申请！";
        }
        try {
    		this.processBaseService.update(orderInfo);
			variables.put("entity", orderInfo);
			variables.put("reApply", reApply);
			this.processService.complete(taskId, content, user.getUserId().toString(), variables);
			
			//发送消息
			if(reApply){
			        //申请消息
				   orderInfo = orderService.selectById(orderInfo.getSerialNum());
				   EventExample.getEventPublisher().publicSendMessageEvent(new SendMessageEvent(orderInfo,MessageConstants.APPLY_BUY_ORDER));
			}else{
				cancelApply(orderInfo.getSerialNum(),taskId);
			}
		} catch (ActivitiObjectNotFoundException e) {
//			message.setStatus(Boolean.FALSE);
//			message.setMessage("此任务不存在，请联系管理员！");
			result = "此任务不存在，请联系管理员！";
			throw e;
		} catch (ActivitiException e) {
//			message.setStatus(Boolean.FALSE);
//			message.setMessage("此任务正在协办，您不能办理此任务！");
			result = "此任务正在协办，您不能办理此任务！";
			throw e;
		} catch (Exception e) {
//			message.setStatus(Boolean.FALSE);
//			message.setMessage("任务办理失败，请联系管理员！");
			result = "任务办理失败，请联系管理员！";
			throw e;
		}
		
    	return result;
    }

	/**
	 * 
	 * @Description (取消申请)
	 * @param serialNum
	 */
	private void cancelApply(String serialNum,String taskId) {
		this.processBaseService.delete(serialNum);//取消申请删除审批记录，才开重新审批
		//更新已办tab里面的deleteReason 更新为'取消申请'
		HistoricTaskVO historicTaskVO = new HistoricTaskVO();
		historicTaskVO.setTaskId(taskId);
		historicTaskVO.setDeleteReason(StaticConst.getInfo("quxiaoApply"));//'取消申请'		
		processBaseService.updateHistoricTask(historicTaskVO);
		OrderInfo m = new OrderInfo();
		m.setStatus("0");//取消申请订单回到审批前状态
		m.setSerialNum(serialNum);
		m.setUpdateTime(new Date());
		Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
		m.setUpdater(currenLoginName);
		orderService.updateStatus(m);
		
	}
	
	
	/**
	 * 
	 * @Description (取消框架申请)
	 * @param serialNum
	 */
	private void cancelFrameApply(String id,String taskId) {
		this.processBaseService.delete(id);//取消申请删除审批记录，才开重新审批
		//更新已办tab里面的deleteReason 更新为'取消申请'
				HistoricTaskVO historicTaskVO = new HistoricTaskVO();
				historicTaskVO.setTaskId(taskId);
				historicTaskVO.setDeleteReason(StaticConst.getInfo("quxiaoApply"));//'取消申请'		
				processBaseService.updateHistoricTask(historicTaskVO);
		ContractVO m = new ContractVO();
		m.setStatus("0");//取消申请框架回到审批前状态
		m.setId(id);
		m.setUpdateTime(new Date());
		Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
		m.setUpdater(currenLoginName);
		contractService.update(m);
	}
	/**
	 * 
	 * @Description (取消价格申请)
	 * @param serialNum
	 */
	private void cancelPriceApply(String id,String taskId) {
		this.processBaseService.delete(id);//取消申请删除审批记录，才开重新审批
		//更新已办tab里面的deleteReason 更新为'取消申请'
				HistoricTaskVO historicTaskVO = new HistoricTaskVO();
				historicTaskVO.setTaskId(taskId);
				historicTaskVO.setDeleteReason(StaticConst.getInfo("quxiaoApply"));//'取消申请'		
				processBaseService.updateHistoricTask(historicTaskVO);
		PriceList p = new PriceList();
		p.setStatus("0");//取消申请价格回到审批前状态
		p.setSerialNum(id);
		p.setUpdateTime(new Date());
		Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
		p.setUpdater(currenLoginName);
		priceListService.update(p);
	}
	/**
	 * 
	 * @Description (取消发票)
	 * @param serialNum
	 */
	private void cancelInvoiceApply(String id,String taskId) {
		this.processBaseService.delete(id);//取消申请删除审批记录，才开重新审批
		//更新已办tab里面的deleteReason 更新为'取消申请'
				HistoricTaskVO historicTaskVO = new HistoricTaskVO();
				historicTaskVO.setTaskId(taskId);
				historicTaskVO.setDeleteReason(StaticConst.getInfo("quxiaoApply"));//'取消申请'		
				processBaseService.updateHistoricTask(historicTaskVO);
				Invoice i= new Invoice();
		i .setStatus("0");//取消申请价格回到审批前状态
		i.setSerialNum(id);
		i.setUpdateTime(new Date());
		Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
		i.setUpdater(currenLoginName);
		invoiceService.update(i);
	}
	/**
	 * 
	 * @Description (取消发货)
	 * @param serialNum
	 */
	private void cancelDeliveryApply(String id,String taskId) {
		this.processBaseService.delete(id);//取消申请删除审批记录，才开重新审批
		//更新已办tab里面的deleteReason 更新为'取消申请'
				HistoricTaskVO historicTaskVO = new HistoricTaskVO();
				historicTaskVO.setTaskId(taskId);
				historicTaskVO.setDeleteReason(StaticConst.getInfo("quxiaoApply"));//'取消申请'		
				processBaseService.updateHistoricTask(historicTaskVO);
				Delivery d=new Delivery();
	        	d.setSerialNum(id);
	        	d.setStatus("00");
		Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
		d.setUpdater(currenLoginName);
		deliveryService.updateDelivery(d);//更新状态
	}
    /**
     * 
     * @Description (各类订单列表查询)
     * @param type（只分为销售：sale，采购:buy两种）
     * @param selectFor(自定义参数值，用于控制生成查询sql)
     * @param fram（是否框架合同1是0否）
     * @return
     */
    @RequestMapping("/findOrderList")
    @ResponseBody
    public ResponseEntity<Map> findOrderList(String type,String selectFor,String fram,@RequestParam(required=false)String demandPlanSerial) {
    	List<OrderInfo> orderInfoList = new ArrayList<OrderInfo>();
    	String comId = null;
    	User user = UserUtil.getUserFromSession();
    	if(user!=null){
			comId = userCompanyService.getUserComId(String.valueOf(user.getUserId()));
			if(comId==null){
				comId = "null";//null此处可看做是平台方的公司id
			}
		}
    	/******以上看做每个用户都有自己的公司id******/
    	
    	OrderInfo parm =new OrderInfo();
    	if(SALEORDER.equals(type)){//查找公司销售订单
    		parm.setSupplyComId(comId);
    		if("delivery".equals(selectFor)){//为发货查找自己公司待发货的销售订单
    			parm.setStatus("2");
    		}
    		if("supplyOrder".equals(selectFor)){//供应商订单(状态不为0，本公司销售订单)
        		parm.setStatus("000");
        	}
    		if("platformOrder".equals(selectFor)){//平台销售订单(状态不为44，平台销售订单不能包括客户端的初始化订单)
        		parm.setStatus("111");
        	}
    		//历史销售订单条件
        	if(StringUtils.isNotEmpty(demandPlanSerial)){
        		parm.setDemandPlanSerial(demandPlanSerial);
        	}
    	}else if(BUYORDER.equals(type)){//查找公司采购订单
    		parm.setBuyComId(comId);
    		if("delivery".equals(selectFor)){
    			parm.setStatus("2");
    		}
    	}
    	
    	if("1".equals(fram)){
    		orderInfoList = orderService.selectFramList(parm);
    	}else{
    		orderInfoList = orderService.selectCommenList(parm);
    	}
    	

    	
    	//封装datatables数据返回到前台
		Map pageMap = new HashMap();
		pageMap.put("draw", 1);
		pageMap.put("recordsTotal", orderInfoList==null?0:orderInfoList.size());
		pageMap.put("recordsFiltered", orderInfoList==null?0:orderInfoList.size());
		pageMap.put("data", orderInfoList);
		return new ResponseEntity<Map>(pageMap, HttpStatus.OK);

    }

    /**
     * 
     * @Description (各类框架列表查询)
     * @param type（只分为销售：sale，采购:buy两种）
     * @param selectFor(自定义参数值，用于控制生成查询sql)
     * @return
     */
    @RequestMapping("/findFrameList")
    @ResponseBody
    public ResponseEntity<Map> findFrameList(String type,String selectFor,String comId) {
    	List<ContractVO> contractList = new ArrayList<ContractVO>();
    	
    	ContractVO parm =new ContractVO();
    	if(SALEORDER.equals(type)){//查找公司销售订单
    		parm.setContractType(StaticConst.CONTRACT_TYPE_SALEFRAME.getInfo());
    	}else if(BUYORDER.equals(type)){//查找公司采购订单
    		parm.setContractType(StaticConst.CONTRACT_TYPE_BUYFRAME.getInfo());
    	}
    	
    	parm.setStatus(selectFor);
    	if(comId==null){
    		User user = UserUtil.getUserFromSession();
        	if(user!=null){
    			comId = userCompanyService.getUserComId(String.valueOf(user.getUserId()));
    		}
    	}
    	parm.setComId(comId);
    	contractList = contractService.selectList(parm);
    	
    	//封装datatables数据返回到前台
		Map pageMap = new HashMap();
		pageMap.put("draw", 1);
		pageMap.put("recordsTotal", contractList==null?0:contractList.size());
		pageMap.put("recordsFiltered", contractList==null?0:contractList.size());
		pageMap.put("data", contractList);
		return new ResponseEntity<Map>(pageMap, HttpStatus.OK);

    }
    
    /**
     * 
     * @Description (各类框架列表查询)
     * @param type（只分为销售：sale，采购:buy两种）
     * @param selectFor(自定义参数值，用于控制生成查询sql)
     * @return
     */
    @RequestMapping("/findDefaultFrame")
    @ResponseBody
    public ContractVO findDefaultFrame(String type,String selectFor,String comId) {
    	List<ContractVO> contractList = new ArrayList<ContractVO>();
    	
    	ContractVO parm =new ContractVO();
    	if(SALEORDER.equals(type)){//查找公司销售订单
    		parm.setContractType(StaticConst.CONTRACT_TYPE_SALEFRAME.getInfo());
    	}else if(BUYORDER.equals(type)){//查找公司采购订单
    		parm.setContractType(StaticConst.CONTRACT_TYPE_BUYFRAME.getInfo());
    	}
    	
    	parm.setStatus(selectFor);
    	if(comId==null){
    		User user = UserUtil.getUserFromSession();
        	if(user!=null){
    			comId = userCompanyService.getUserComId(String.valueOf(user.getUserId()));
    		}
    	}
    	parm.setComId(comId);
    	contractList = contractService.selectList(parm);
    	if(CollectionUtils.isEmpty(contractList)){
    		return null;
    	}else{
    		return contractList.get(0);
    	}
    	
		
    }
    /**
	 * 
	 * @Description 批量删除订单
	 * @param ids
	 * @return
	 */
    @OperationLog(operateType = "update" ,operationDesc = "订单删除")
	@RequestMapping(value = "/deleteOrders", method = RequestMethod.POST)
	public ResponseEntity<Void> deleteOrders(@RequestBody String ids) {
		if ("".equals(ids) || ids == null) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		List<String> idList = ApplicationUtils.getIdList(ids);
		for(String id : idList){
			OrderInfo m = new OrderInfo();
			m.setSerialNum(id);
			m.setDelFlg("1");
			m.setUpdateTime(new Date());
			Subject currentUser = SecurityUtils.getSubject();
			String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
	    	m.setUpdater(currenLoginName);
			orderService.updateStatus(m);
		}

		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
	/**
	 * 
	 * @Description 获取订单信息
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/getOrderInfo")
	@ResponseBody
	public Map getOrderInfo(String serialNum,OrderInfo orderInfo) {
		orderInfo = orderService.selectById(serialNum);
		Map<String, Object> map = new HashMap<String, Object>();
    	map.put("orderInfo", orderInfo);
    	List<OrderMateriel> orderMateriel=null;
    	
   	OrderMaterielExample m =new OrderMaterielExample();
    	//and 条件1
    	com.congmai.zhgj.web.model.OrderMaterielExample.Criteria criteria =  m.createCriteria();
    	criteria.andDelFlgEqualTo("0");
    	criteria.andOrderSerialEqualTo(serialNum);
    	m.setOrderByClause(" sort asc");
    	orderMateriel = orderMaterielService.selectList(m);
    	
    	if(orderMateriel!=null){
			for (int i = 0; i < orderMateriel.size(); i++) {//设置所选基本物料的自建库存数量
				if(orderMateriel.get(i).getMateriel()!=null){
					Materiel m1 = orderMateriel.get(i).getMateriel();
					String inCountString = stockService.getCountInAmountForZijian(m1.getSerialNum());
    				String outCountString = stockService.getCountOutAmountForZijian(m1.getSerialNum());
    				m1.setStockCount(
    						(Integer.parseInt(inCountString==null?"0":inCountString)
    								-Integer.parseInt(outCountString==null?"0":outCountString))+"");
				}
				
				
			}
		}
    	map.put("orderMateriel", orderMateriel);
    	
    	//获取合同信息
    	ContractVO contract = null;
		if(StringUtils.isNotEmpty(orderInfo.getContractSerial())){
    		contract=contractService.selectConbtractById(orderInfo.getContractSerial());
    	}else{
    		map.put("clauseDelivery", null);
    	}
    	map.put("contract", contract);
    	if(contract!=null&&StringUtils.isNotEmpty(contract.getId())){
    		//获取合同条款信息
    		ClauseAfterSales clauseAfterSales = clauseAfterSalesService.selectByContractId(contract.getId());
    		map.put("clauseAfterSales", clauseAfterSales);
    		ClauseAdvance clauseAdvance = clauseAdvanceService.selectByContractId(contract.getId());
    		map.put("clauseAdvance", clauseAdvance);
    		ClauseCheckAccept clauseCheckAccept = clauseCheckAcceptService.selectByContractId(contract.getId());
    		map.put("clauseCheckAccept", clauseCheckAccept);
    		ClauseDelivery clauseDelivery = clauseDeliveryService.selectByContractId(contract.getId());
    		map.put("clauseDelivery", clauseDelivery);
    		ClauseSettlement clauseSettlement = clauseSettlementService.selectByContractId(contract.getId());
    		map.put("clauseSettlement", clauseSettlement);
    		
        	//查询框架合同
        	if(StaticConst.getInfo("framContract").equals(contract.getContractType())){//如果是框架合同
        		ClauseFrameworkExample mf =new ClauseFrameworkExample();
    	    	com.congmai.zhgj.web.model.ClauseFrameworkExample.Criteria criteriaf =  mf.createCriteria();
    	    	criteriaf.andContractSerialEqualTo(contract.getId());
    	    	criteriaf.andDelFlgEqualTo("0");
    	    	List<ClauseFramework> ClauseFramework = clauseFrameworkService.selectList(mf);
    	    	map.put("ClauseFramework", ClauseFramework);
        	}
    	}
    	
    	//获取订单附件
    	OrderFileExample om =new OrderFileExample();
    	com.congmai.zhgj.web.model.OrderFileExample.Criteria criteria2 =  om.createCriteria();
    	criteria2.andOrderSerialEqualTo(serialNum);
    	criteria2.andDelFlgEqualTo("0");
    	List<OrderFile> file = orderFileService.selectList(om);
    	map.put("file", file);
    	
    	
    	
    	return map;
	}
	
	
	/**
	 * 
	 * @Description 获取框架协议信息
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/getFrameInfo")
	@ResponseBody
	public Map getFrameInfo(String serialNum) {
		Map<String, Object> map = new HashMap<String, Object>();
    	//获取合同信息
    	ContractVO contract=contractService.selectConbtractById(serialNum);
    	map.put("contract", contract);
    	if(contract!=null&&StringUtils.isNotEmpty(contract.getId())){
    		//获取合同条款信息
    		ClauseAfterSales clauseAfterSales = clauseAfterSalesService.selectByContractId(contract.getId());
    		map.put("clauseAfterSales", clauseAfterSales);
    		ClauseAdvance clauseAdvance = clauseAdvanceService.selectByContractId(contract.getId());
    		map.put("clauseAdvance", clauseAdvance);
    		ClauseCheckAccept clauseCheckAccept = clauseCheckAcceptService.selectByContractId(contract.getId());
    		map.put("clauseCheckAccept", clauseCheckAccept);
    		ClauseDelivery clauseDelivery = clauseDeliveryService.selectByContractId(contract.getId());
    		map.put("clauseDelivery", clauseDelivery);
    		ClauseSettlement clauseSettlement = clauseSettlementService.selectByContractId(contract.getId());
    		map.put("clauseSettlement", clauseSettlement);
    		
        	//查询框架合同
//        	if(StaticConst.getInfo("framContract").equals(contract.getContractType())){//如果是框架合同
//        		ClauseFrameworkExample mf =new ClauseFrameworkExample();
//    	    	com.congmai.zhgj.web.model.ClauseFrameworkExample.Criteria criteriaf =  mf.createCriteria();
//    	    	criteriaf.andContractSerialEqualTo(contract.getId());
//    	    	criteriaf.andDelFlgEqualTo("0");
//    	    	List<ClauseFramework> ClauseFramework = clauseFrameworkService.selectList(mf);
//    	    	map.put("ClauseFramework", ClauseFramework);
//        	}
    	}
    	
//    	获取合同附件
    	ContractFileExample om =new ContractFileExample();
    	com.congmai.zhgj.web.model.ContractFileExample.Criteria criteria2 =  om.createCriteria();
    	criteria2.andContractSerialEqualTo(serialNum);
    	criteria2.andDelFlgEqualTo("0");
    	List<ContractFile> file = contractFileService.selectList(om);
    	map.put("file", file);
    	
    	return map;
	}
	
	/**
     * @Description (保存订单物料信息)
     * @param request
     * @return
     */
    @RequestMapping(value="saveOrderMateriel",method=RequestMethod.POST)
    @ResponseBody
    public OrderMateriel saveOrderMateriel(Map<String, Object> map,@RequestBody OrderMateriel orderMateriel,HttpServletRequest request) {
    	String flag ="0"; //默认失败
        	try{
        		Subject currentUser = SecurityUtils.getSubject();
        		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
        		if(StringUtils.isEmpty(orderMateriel.getSerialNum())){
        			orderMateriel.setSerialNum(ApplicationUtils.random32UUID());
        			orderMateriel.setCreateTime(new Date());
        			orderMateriel.setCreator(currenLoginName);
        			orderMateriel.setUpdateTime(new Date());
        			orderMateriel.setUpdater(currenLoginName);
        			orderMaterielService.insert(orderMateriel);
        			
        			//需求计划物料加入订单流水
        			updateDemandPlanMateriel(orderMateriel.getOrderSerial(),orderMateriel.getDemandPlanMaterielSerial());
        		}else{
        			orderMateriel.setUpdateTime(new Date());
        			orderMateriel.setUpdater(currenLoginName);
        			orderMaterielService.update(orderMateriel);
        		}
        		flag = "1";
        	}catch(Exception e){
        		//20180110 qhzhao System.out.println(e.getMessage());
        		return null;
        	}
        	orderMateriel = orderMaterielService.selectById(orderMateriel.getSerialNum());
    	return orderMateriel;
    }
    
    
    /**
     * 
     * @Description 保存所有订单物料
     * @param params
     * @return
     */
    @RequestMapping(value = "/saveAllOrderMateriel", method = RequestMethod.POST)
    @ResponseBody
    public void saveAllOrderMateriel(@RequestBody String params) {
    	params = params.replace("\\", "");
/*		ObjectMapper objectMapper = new ObjectMapper();  
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, OrderMateriel.class);  */
        List<OrderMateriel> orderMateriel;
		try {
			orderMateriel = JSON.parseArray(params, OrderMateriel.class);
	    	if(!CollectionUtils.isEmpty(orderMateriel)){
	    		Subject currentUser = SecurityUtils.getSubject();
	    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
		    	for(OrderMateriel f:orderMateriel){
		    		f.setSerialNum(ApplicationUtils.random32UUID());
		    		f.setCreator(currenLoginName);
	    			f.setUpdater(currenLoginName);
	    			f.setCreateTime(new Date());
	    			f.setUpdateTime(new Date());
		    	}
		    	//填充File******↑↑↑↑↑↑********
		    	orderMaterielService.betchInsertOrderMateriel(orderMateriel);
		    	//数据插入******↑↑↑↑↑↑********
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
    
    /**
     * @Description (查看订单物料信息)
     * @param request
     * @return
     */
    @RequestMapping(value="viewOrderMateriel",method=RequestMethod.POST)
    @ResponseBody
    public OrderMateriel viewOrderMateriel(Map<String, Object> map,@RequestBody String serialNum,HttpServletRequest request) {
    	
    	OrderMateriel materiel = null;
        	try{
        		if(StringUtils.isNotEmpty(serialNum)){
        			materiel = orderMaterielService.selectById(serialNum);
        		}
        		
        	}catch(Exception e){
        		//20180110 qhzhao System.out.println(e.getMessage());
        		return null;
        	}
    	return materiel;
    }

    /**
     * @Description (删除订单物料信息)
     * @param request
     * @return
     */
    @RequestMapping(value="deleteOrderMateriel",method=RequestMethod.POST)
    @ResponseBody
    public String deleteOrderMateriel(Map<String, Object> map,@RequestBody String serialNums,HttpServletRequest request) {
    	String flag = "0"; //默认失败
    	try{
    		if(StringUtils.isNotEmpty(serialNums)){
    			orderMaterielService.deleteOrderMateriels(serialNums);
    		}
    		flag = "1";
    	}catch(Exception e){
    		//20180110 qhzhao System.out.println(e.getMessage());
    		
    	}
    	return flag;
    }
	
    /**
	 * 订单保存合同
	 * @param contract（合同对象）
	 * @param request（http 请求对象）
	 * @return 操作结果
	 */
	@RequestMapping(value = "/saveContract", method = RequestMethod.POST)
	@ResponseBody
	public ContractVO saveContract(@RequestBody String params, HttpServletRequest request) {
		params = params.replace("\\", "");
		ObjectMapper objectMapper = new ObjectMapper();  
		ContractVO contract = new ContractVO();
		try {
			contract = objectMapper.readValue(params, ContractVO.class);
			if(contract.getId()==null||contract.getId().isEmpty()){//新增
				contract.setId(ApplicationUtils.random32UUID());
	    		Subject currentUser = SecurityUtils.getSubject();
	    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
	    		contract.setCreator(currenLoginName);
	    		contract.setUpdater(currenLoginName);
	    		contract.setCreateTime(new Date());
	    		contract.setUpdateTime(new Date());
	    		contract.setStatus(ContractVO.WAIT_SIGN);

	    		orderService.insertContract(contract);
	    	}else{//更新
	    		Subject currentUser = SecurityUtils.getSubject();
	    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
	    		contract.setUpdater(currenLoginName);
	    		contract.setUpdateTime(new Date());
	    		orderService.updateContract(contract);
	    	}
			contract = contractService.selectConbtractById(contract.getId());
		}catch (Exception e1) {
			e1.printStackTrace();
		}
		
		return contract;
	}
	
	
	/**
	 * 保存框架
	 * @param contract（框架对象）
	 * @param request（http 请求对象）
	 * @return 操作结果
	 */
	@RequestMapping(value = "/saveFrame", method = RequestMethod.POST)
	@ResponseBody
	public ContractVO saveFrame(@RequestBody String params, HttpServletRequest request) {
		params = params.replace("\\", "");
		ObjectMapper objectMapper = new ObjectMapper();  
		ContractVO contract = new ContractVO();
		try {
			contract = objectMapper.readValue(params, ContractVO.class);
			if(contract.getId()==null||contract.getId().isEmpty()){//新增
				contract.setId(ApplicationUtils.random32UUID());
	    		Subject currentUser = SecurityUtils.getSubject();
	    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
	    		contract.setSigner(currenLoginName);
	    		contract.setCreator(currenLoginName);
	    		contract.setUpdater(currenLoginName);
	    		contract.setCreateTime(new Date());
	    		contract.setUpdateTime(new Date());
	    		contract.setStatus(ContractVO.INIT);
	    		orderService.insertContract(contract);
	    	}else{//更新
	    		Subject currentUser = SecurityUtils.getSubject();
	    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
	    		contract.setUpdater(currenLoginName);
	    		contract.setUpdateTime(new Date());
	    		contractService.update(contract);
	    	}
			contract = contractService.selectConbtractById(contract.getId());
		}catch (Exception e1) {
			e1.printStackTrace();
		}
		
		return contract;
	}
    
	
    /**
	 * 订单保存合同售后条款
	 * @param clauseAfterSales（售后条款）
	 * @param request（http 请求对象）
	 * @return 操作结果
	 */
	@RequestMapping(value = "/saveClauseAfterSales", method = RequestMethod.POST)
	@ResponseBody
	public ClauseAfterSales saveClauseAfterSales(@RequestBody ClauseAfterSales clauseAfterSales, HttpServletRequest request) {
		if(clauseAfterSales.getSerialNum()==null||clauseAfterSales.getSerialNum().isEmpty()){//新增
			clauseAfterSales.setSerialNum(ApplicationUtils.random32UUID());
    		Subject currentUser = SecurityUtils.getSubject();
    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
    		clauseAfterSales.setCreator(currenLoginName);
    		clauseAfterSales.setUpdater(currenLoginName);
    		clauseAfterSales.setCreateTime(new Date());
    		clauseAfterSales.setUpdateTime(new Date());

    		clauseAfterSalesService.insert(clauseAfterSales);
    	}else{//更新
    		Subject currentUser = SecurityUtils.getSubject();
    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
    		clauseAfterSales.setUpdater(currenLoginName);
    		clauseAfterSales.setUpdateTime(new Date());
    		clauseAfterSalesService.update(clauseAfterSales);
    	}
		return clauseAfterSales;
	}
	
	/**
	 * 订单保存合同垫资条款
	 * @param clauseAdvance（垫资条款）
	 * @param request（http 请求对象）
	 * @return 操作结果
	 */
	@RequestMapping(value = "/saveClauseAdvance", method = RequestMethod.POST)
	@ResponseBody
	public ClauseAdvance saveClauseAdvance(@RequestBody ClauseAdvance clauseAdvance, HttpServletRequest request) {
		if(clauseAdvance.getSerialNum()==null||clauseAdvance.getSerialNum().isEmpty()){//新增
			clauseAdvance.setSerialNum(ApplicationUtils.random32UUID());
    		Subject currentUser = SecurityUtils.getSubject();
    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
    		clauseAdvance.setCreator(currenLoginName);
    		clauseAdvance.setUpdater(currenLoginName);
    		clauseAdvance.setCreateTime(new Date());
    		clauseAdvance.setUpdateTime(new Date());

    		clauseAdvanceService.insert(clauseAdvance);
    	}else{//更新
    		Subject currentUser = SecurityUtils.getSubject();
    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
    		clauseAdvance.setUpdater(currenLoginName);
    		clauseAdvance.setUpdateTime(new Date());
    		clauseAdvanceService.update(clauseAdvance);
    	}
		return clauseAdvance;
	}
	
	/**
	 * 订单保存合同验收条款
	 * @param clauseCheckAccept（验收条款）
	 * @param request（http 请求对象）
	 * @return 操作结果
	 */
	@RequestMapping(value = "/saveClauseCheckAccept", method = RequestMethod.POST)
	@ResponseBody
	public ClauseCheckAccept saveClauseCheckAccept(@RequestBody ClauseCheckAccept clauseCheckAccept, HttpServletRequest request) {
		if(clauseCheckAccept.getSerialNum()==null||clauseCheckAccept.getSerialNum().isEmpty()){//新增
			clauseCheckAccept.setSerialNum(ApplicationUtils.random32UUID());
    		Subject currentUser = SecurityUtils.getSubject();
    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
    		clauseCheckAccept.setCreator(currenLoginName);
    		clauseCheckAccept.setUpdater(currenLoginName);
    		clauseCheckAccept.setCreateTime(new Date());
    		clauseCheckAccept.setUpdateTime(new Date());

    		clauseCheckAcceptService.insert(clauseCheckAccept);
    	}else{//更新
    		Subject currentUser = SecurityUtils.getSubject();
    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
    		clauseCheckAccept.setUpdater(currenLoginName);
    		clauseCheckAccept.setUpdateTime(new Date());
    		clauseCheckAcceptService.update(clauseCheckAccept);
    	}
		return clauseCheckAccept;
	}
	
	/**
	 * 订单保存合同交付条款
	 * @param clauseDelivery（交付条款）
	 * @param request（http 请求对象）
	 * @return 操作结果
	 */
	@RequestMapping(value = "/saveClauseDelivery", method = RequestMethod.POST)
	@ResponseBody
	public ClauseDelivery saveClauseDelivery(@RequestBody ClauseDelivery clauseDelivery, HttpServletRequest request) {
		if(clauseDelivery.getSerialNum()==null||clauseDelivery.getSerialNum().isEmpty()){//新增
			clauseDelivery.setSerialNum(ApplicationUtils.random32UUID());
    		Subject currentUser = SecurityUtils.getSubject();
    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
    		clauseDelivery.setCreator(currenLoginName);
    		clauseDelivery.setUpdater(currenLoginName);
    		clauseDelivery.setCreateTime(new Date());
    		clauseDelivery.setUpdateTime(new Date());

    		clauseDeliveryService.insert(clauseDelivery);
    	}else{//更新
    		Subject currentUser = SecurityUtils.getSubject();
    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
    		clauseDelivery.setUpdater(currenLoginName);
    		clauseDelivery.setUpdateTime(new Date());
    		clauseDeliveryService.update(clauseDelivery);
    	}
		return clauseDelivery;
	}
	
	
	/**
	 * 订单保存合同结算条款
	 * @param clauseSettlement（结算条款）
	 * @param request（http 请求对象）
	 * @return 操作结果
	 */
	@RequestMapping(value = "/saveClauseSettlement", method = RequestMethod.POST)
	@ResponseBody
	public ClauseSettlement saveClauseSettlement(@RequestBody ClauseSettlement clauseSettlement, HttpServletRequest request) {
		if(clauseSettlement.getSerialNum()==null||clauseSettlement.getSerialNum().isEmpty()){//新增
			clauseSettlement.setSerialNum(ApplicationUtils.random32UUID());
    		Subject currentUser = SecurityUtils.getSubject();
    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
    		clauseSettlement.setCreator(currenLoginName);
    		clauseSettlement.setUpdater(currenLoginName);
    		clauseSettlement.setCreateTime(new Date());
    		clauseSettlement.setUpdateTime(new Date());

    		clauseSettlementService.insert(clauseSettlement);
    	}else{//更新
    		Subject currentUser = SecurityUtils.getSubject();
    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
    		clauseSettlement.setUpdater(currenLoginName);
    		clauseSettlement.setUpdateTime(new Date());
    		clauseSettlementService.update(clauseSettlement);
    	}
		return clauseSettlement;
	}
	
	/**
	 * 订单保存合同结算条款明细
	 * @param params（结算条款）明细
	 * @param request（http 请求对象）
	 * @return 操作结果
	 */
	@RequestMapping(value = "/saveClauseSettlementDetail", method = RequestMethod.POST)
	@ResponseBody
	public List<ClauseSettlementDetail> saveClauseSettlementDetail(@RequestBody String params, HttpServletRequest request) {
    	params = params.replace("\\", "");
		ObjectMapper objectMapper = new ObjectMapper();  
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, ClauseSettlementDetail.class);  
        List<ClauseSettlementDetail> clauseSettlementDetail = null;
		try {
			clauseSettlementDetail = objectMapper.readValue(params, javaType);
			if(!CollectionUtils.isEmpty(clauseSettlementDetail)){
	    		Subject currentUser = SecurityUtils.getSubject();
	    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
		    	for(ClauseSettlementDetail f:clauseSettlementDetail){
		    		f.setSerialNum(ApplicationUtils.random32UUID());
		    		f.setCreator(currenLoginName);
	    			f.setUpdater(currenLoginName);
	    			f.setCreateTime(new Date());
	    			f.setUpdateTime(new Date());
		    	}
		    	//填充File******↑↑↑↑↑↑********
		    	clauseSettlementDetailService.betchInsertClauseSettlementDetails(clauseSettlementDetail);
		    	//数据插入******↑↑↑↑↑↑********
	        }

		} catch (Exception e) {
			e.printStackTrace();
		}
		return clauseSettlementDetail;
	}
    
	/**
     * 
     * @Description 保存附件
     * @param params
     * @return
     */
    @RequestMapping(value = "/saveFile", method = RequestMethod.POST)
    @ResponseBody
    public void saveFile(@RequestBody String params) {
    	params = params.replace("\\", "");
		ObjectMapper objectMapper = new ObjectMapper();  
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, OrderFile.class);  
        List<OrderFile> file;
		try {
			file = objectMapper.readValue(params, javaType);
	    	if(!CollectionUtils.isEmpty(file)){
	    		Subject currentUser = SecurityUtils.getSubject();
	    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
		    	for(OrderFile f:file){
		    		f.setSerialNum(ApplicationUtils.random32UUID());
		    		f.setUploader(currenLoginName);
		    		f.setCreator(currenLoginName);
	    			f.setUpdater(currenLoginName);
	    			f.setUploadDate(new Date());
	    			f.setCreateTime(new Date());
	    			f.setUpdateTime(new Date());
		    	}
		    	//填充File******↑↑↑↑↑↑********
		    	orderFileService.betchInsertOrderFiles(file);
		    	//数据插入******↑↑↑↑↑↑********
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
    
    /**
     * 
     * @Description 保存合同附件
     * @param params
     * @return
     */
    @RequestMapping(value = "/saveFrameFile", method = RequestMethod.POST)
    @ResponseBody
    public void saveFrameFile(@RequestBody String params) {
    	params = params.replace("\\", "");
		ObjectMapper objectMapper = new ObjectMapper();  
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, ContractFile.class);  
        List<ContractFile> file;
		try {
			file = objectMapper.readValue(params, javaType);
	    	if(!CollectionUtils.isEmpty(file)){
	    		Subject currentUser = SecurityUtils.getSubject();
	    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
		    	for(ContractFile f:file){
		    		f.setSerialNum(ApplicationUtils.random32UUID());
		    		f.setUploader(currenLoginName);
		    		f.setCreator(currenLoginName);
	    			f.setUpdater(currenLoginName);
	    			f.setUploadDate(new Date());
	    			f.setCreateTime(new Date());
	    			f.setUpdateTime(new Date());
		    	}
		    	//填充File******↑↑↑↑↑↑********
		    	contractFileService.betchInsertContractFiles(file);
		    	//数据插入******↑↑↑↑↑↑********
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
    
    /**
     * 
     * @Description 保存框架条款
     * @param params
     * @return
     */
    @RequestMapping(value = "/saveClauseFramework", method = RequestMethod.POST)
    @ResponseBody
    public List<ClauseFramework> saveClauseFramework(@RequestBody String params) {
    	params = params.replace("\\", "");
		ObjectMapper objectMapper = new ObjectMapper();  
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, ClauseFramework.class);  
        List<ClauseFramework> clauseFramework;
		try {
			clauseFramework = objectMapper.readValue(params, javaType);
	    	if(!CollectionUtils.isEmpty(clauseFramework)){
	    		Subject currentUser = SecurityUtils.getSubject();
	    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
		    	for(ClauseFramework b:clauseFramework){
		    		b.setSerialNum(ApplicationUtils.random32UUID());
	    			b.setCreator(currenLoginName);
	    			b.setUpdater(currenLoginName);
	    			b.setCreateTime(new Date());
	    			b.setUpdateTime(new Date());
		    	}
		    	//填充框架条款M******↑↑↑↑↑↑********
		    	clauseFrameworkService.betchInsertBOM(clauseFramework);
		    	//数据插入******↑↑↑↑↑↑********
	        }
	    	
	    	//返回数据******↑↑↑↑↑↑********
	    	return clauseFramework;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    	
    }
    
    /**
     * @Description (导出订单信息)
     * @param request
     * @return
     */
    @RequestMapping("exportOrder")
    public void exportOrder(String type,String fram,Map<String, Object> map,HttpServletRequest request,HttpServletResponse response) {
    		Map<String, Object> dataMap = new HashMap<String, Object>();
    		List<OrderInfo> orderInfoList = new ArrayList<OrderInfo>();
//    		OrderInfoExample m =new OrderInfoExample();
//        	//and 条件1
//        	Criteria criteria =  m.createCriteria();
//        	criteria.andDelFlgEqualTo("0");
//        	if("sale".equals(type)){//平台销售订单供应商为空
//        		criteria.andSupplyComIdIsNull();
//        	}else if("buy".equals(type)){//平台采购订单采购商为空
//        		criteria.andBuyComIdIsNull();
//        	}
//        	/*//and 条件2,未发布可编辑的物料
//        	Criteria criteria2 =  m.createCriteria();
//        	criteria2.andStatusEqualTo("0");
//        	criteria2.andDelFlgEqualTo("0");
//        	//or 条件
//        	m.or(criteria2);*/
//        	//排序字段
//        	m.setOrderByClause("updateTime DESC");
//        	orderInfoList = orderService.selectList(m);
        	
        	
        	OrderInfo parm =new OrderInfo();
        	if("sale".equals(type)){//平台销售订单供应商为空
        		parm.setSupplyComId("null");
        	}else if("buy".equals(type)){//平台采购订单采购商为空
        		parm.setBuyComId("null");
        	}
        	if("1".equals(fram)){
        		orderInfoList = orderService.selectFramList(parm);
        	}else{
        		orderInfoList = orderService.selectCommenList(parm);
        	}
        	
        	
    		dataMap.put("orderInfoList",orderInfoList);
    		if("sale".equals(type)){
    			ExcelUtil.export(request, response, dataMap, "orderInfo", "订单信息");
        	}else if("buy".equals(type)){
        		ExcelUtil.export(request, response, dataMap, "buyOrderInfo", "订单信息");
        	}
    		
    }
    
    /**
     * @Description (下载导入模板)
     * @param request
     * @return
     */
    @RequestMapping("downloadImportTemp")
    public void downloadCompanyTemp(String type,Map<String, Object> map,HttpServletRequest request,HttpServletResponse response) {
    	if("sale".equals(type)){
    		ExcelUtil.importTempDownLoad(request, response, "orderInfo");
    	}else if("buy".equals(type)){
    		ExcelUtil.importTempDownLoad(request, response, "buyOrderInfo");
    	}
    	
    }
    
    /**
     * @Description (采购订单信息导入)
     * @param request
     * @return
     */
    @RequestMapping("buyOrderImport")
    @ResponseBody
    public Map<String,String> buyOrderImport(@RequestParam(value = "excelFile") MultipartFile excelFile,HttpServletRequest request,HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String, String>();
    	 try {
			ExcelReader excelReader = new ExcelReader(excelFile.getInputStream());
			excelReader.readExcelContent(new RowHandler() {
				@Override
				public void handle(List<Object> row,int i) throws Exception {
					if(!CollectionUtils.isEmpty(row)){
						try{
							OrderInfo orderInfo = new OrderInfo();

							orderInfo.setOrderNum(row.get(0).toString());
							orderInfo.setOrderType(row.get(1).toString());
							orderInfo.setSupplyComId(row.get(2).toString());
							orderInfo.setSeller(row.get(3).toString());
							orderInfo.setEntrustParty(row.get(4).toString());
							orderInfo.setServiceModel(row.get(5).toString());
							orderInfo.setSettlementClause(row.get(6).toString());
							orderInfo.setDeliveryMode(row.get(7).toString());
							orderInfo.setRate(row.get(8).toString());
							orderInfo.setCurrency(row.get(9).toString());
							orderInfo.setExchangeRate(row.get(10).toString());
							orderInfo.setDemandPlanSerial(row.get(11).toString());
							orderInfo.setOrderSerial(row.get(12).toString());
							orderInfo.setMaker(row.get(13).toString());
							orderInfo.setOrderDate(StringUtils.isEmpty(row.get(14).toString())?null:(Date) row.get(14));

							orderInfo.setSerialNum(ApplicationUtils.random32UUID());
				    		Subject currentUser = SecurityUtils.getSubject();
				    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
				    		orderInfo.setCreator(currenLoginName);
				    		orderInfo.setUpdater(currenLoginName);
				    		orderInfo.setCreateTime(new Date());
				    		orderInfo.setUpdateTime(new Date());
				    		orderInfo.setStatus("0");
				    		
				    		orderService.insert(orderInfo);
						}catch(Exception  e){
							throw new Exception("第"+i+"行数据异常请检查，数据内容："+row.toString());
						}
						
					}
					
				}
			}, 2);
			map.put("data", "success");
		} catch (Exception e1) {
			map.put("data", e1.getMessage());
		}
    	
         return map;
    }
    
    
    /**
     * @Description (销售订单信息导入)
     * @param request
     * @return
     */
    @RequestMapping("orderImport")
    @ResponseBody
    public Map<String,String> orderImport(@RequestParam(value = "excelFile") MultipartFile excelFile,String type,HttpServletRequest request,HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String, String>();
    	 try {
			ExcelReader excelReader = new ExcelReader(excelFile.getInputStream());
			excelReader.readExcelContent(new RowHandler() {
				@Override
				public void handle(List<Object> row,int i) throws Exception {
					if(!CollectionUtils.isEmpty(row)){
						try{
							OrderInfo orderInfo = new OrderInfo();

							orderInfo.setOrderNum(row.get(0).toString());
							orderInfo.setOrderType(row.get(1).toString());
							orderInfo.setBuyComId(row.get(2).toString());
							orderInfo.setSeller(row.get(3).toString());
							orderInfo.setEntrustParty(row.get(4).toString());
							orderInfo.setServiceModel(row.get(5).toString());
							orderInfo.setSettlementClause(row.get(6).toString());
							orderInfo.setDeliveryMode(row.get(7).toString());
							orderInfo.setRate(row.get(8).toString());
							orderInfo.setCurrency(row.get(9).toString());
							orderInfo.setExchangeRate(row.get(10).toString());
							orderInfo.setDemandPlanSerial(row.get(11).toString());
							orderInfo.setSaleApplySerial(row.get(12).toString());
							orderInfo.setOrderSerial(row.get(13).toString());
							orderInfo.setMaker(row.get(14).toString());
							orderInfo.setOrderDate(StringUtils.isEmpty(row.get(15).toString())?null:(Date) row.get(15));

							orderInfo.setSerialNum(ApplicationUtils.random32UUID());
				    		Subject currentUser = SecurityUtils.getSubject();
				    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
				    		orderInfo.setCreator(currenLoginName);
				    		orderInfo.setUpdater(currenLoginName);
				    		orderInfo.setCreateTime(new Date());
				    		orderInfo.setUpdateTime(new Date());
				    		orderInfo.setStatus("0");
				    		
				    		orderService.insert(orderInfo);
						}catch(Exception  e){
							throw new Exception("第"+i+"行数据异常请检查，数据内容："+row.toString());
						}
						
					}
					
				}
			}, 2);
			map.put("data", "success");
		} catch (Exception e1) {
			map.put("data", e1.getMessage());
		}
    	
         return map;
    }
    
    
    /**
     * @Description (查询订单操作日志)
     * @param serialNum
     * @return
     */

    @RequestMapping("/findOrderLog")
    @ResponseBody
    public ResponseEntity<Map> findOrderLog(String serialNum) {
    	OperateLogExample m =new OperateLogExample();
    	List<OperateLog> operateLogList = new ArrayList<OperateLog>();
    	
		//and 条件1
    	com.congmai.zhgj.web.model.OperateLogExample.Criteria criteria =  m.createCriteria();
    	criteria.andObjectSerialEqualTo(serialNum);
    	operateLogList = operateLogService.selectList(m);
    	
    	OrderInfo orderInfo = orderService.selectById(serialNum);
    	List<CommentVO> commentList = null;
    	try {
    		if(orderInfo.getProcessBase()!=null){
    			commentList = this.processService.getComments(orderInfo.getProcessBase().getProcessInstanceId());
    		}
    		
		} catch (Exception e) {
			e.printStackTrace();
		}
    	if(commentList!=null){
    		for(CommentVO c:commentList ){
        		OperateLog o = new OperateLog();
        		o.setOperationDesc("审批");
        		o.setRemark(c.getContent());
        		o.setOperator(c.getUserName());
        		o.setOperationTime(c.getTime());
        		operateLogList.add(o);
        	}
    	}
    	
    	ListSort(operateLogList);
    	//封装datatables数据返回到前台
		Map pageMap = new HashMap();
		pageMap.put("draw", 1);
		pageMap.put("recordsTotal", operateLogList==null?0:operateLogList.size());
		pageMap.put("recordsFiltered", operateLogList==null?0:operateLogList.size());
		pageMap.put("data", operateLogList);
		return new ResponseEntity<Map>(pageMap, HttpStatus.OK);

    }
    
    
    
    /**
     * @Description (查询订单（收货发货检验出库入库）操作日志)
     * @param serialNum
     * @return
     */

    @RequestMapping("/findDeliverLog")
    @ResponseBody
    public ResponseEntity<Map> findDeliverLog(String serialNum) {
    	List<OperateLog> operateLogList = new ArrayList<OperateLog>();
    	operateLogList = operateLogService.findDeliverLogByOrderSerialNum(serialNum);
    	ListSort(operateLogList);
    	//封装datatables数据返回到前台
		Map pageMap = new HashMap();
		pageMap.put("draw", 1);
		pageMap.put("recordsTotal", operateLogList==null?0:operateLogList.size());
		pageMap.put("recordsFiltered", operateLogList==null?0:operateLogList.size());
		pageMap.put("data", operateLogList);
		return new ResponseEntity<Map>(pageMap, HttpStatus.OK);

    }
    
    /**
     * @Description (查询订单（收付款）操作日志)
     * @param serialNum
     * @return
     */

    @RequestMapping("/findPayLog")
    @ResponseBody
    public ResponseEntity<Map> findPayLog(String serialNum) {
    	List<OperateLog> operateLogList = new ArrayList<OperateLog>();
    	operateLogList = operateLogService.findPayLogByOrderSerialNum(serialNum);
    	ListSort(operateLogList);
    	//封装datatables数据返回到前台
		Map pageMap = new HashMap();
		pageMap.put("draw", 1);
		pageMap.put("recordsTotal", operateLogList==null?0:operateLogList.size());
		pageMap.put("recordsFiltered", operateLogList==null?0:operateLogList.size());
		pageMap.put("data", operateLogList);
		return new ResponseEntity<Map>(pageMap, HttpStatus.OK);

    }
    
    
    public void ListSort(List<OperateLog> list) {
    	if(list!=null){
    		Collections.sort(list, new Comparator<OperateLog>() {
    			@Override
    			public int compare(OperateLog o1, OperateLog o2) {
    				try {
    					Date dt1 = o1.getOperationTime();
    					Date dt2 = o2.getOperationTime();
    					if (dt1.getTime() > dt2.getTime()) {
    						return 1;
    					} else if (dt1.getTime() < dt2.getTime()) {
    						return -1;
    					} else {
    						return 0;
    					}
    				} catch (Exception e) {
    					e.printStackTrace();
    				}
    				return 0;
    			}
    		});
    	}
	}
   
    private void updateDemandPlanMateriel(String orderSerial,String serialNum){
    	DemandPlanMateriel materiel = new DemandPlanMateriel();
    	materiel.setSerialNum(serialNum);
    	materiel.setOrderSerial(orderSerial);
    	demandPlanMaterielService.update(materiel);
    };
    
    /**
     * @Description 生成表单编号
     * @param request
     * @return
     */
    @RequestMapping(value="getNumCode")
    @ResponseBody
    public String getNumCode(String codeType) {
    	String numCode = orderService.getNumCode(codeType);
    	return numCode;
    }
    /**
     * @Description 判断是否存在
     * @param request
     * @return
     */
    @RequestMapping(value="judgeIsObjExist")
    @ResponseBody
    public Boolean isObjExist (String codeType,String num,String serialNum) {
    	
    	Boolean  falg = orderService.isExist(codeType,num,serialNum);
    	
    	return falg;
    }
    
    private static BeanCopier beanCopier = BeanCopier.create(OrderInfo.class,OrderInfo.class,false);
    private static BeanCopier beanContractVOCopier = BeanCopier.create(ContractVO.class,ContractVO.class,false);
    /**
	 * 
	 * @Description 销售订单生成采购订单
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/saleGenerateBuy")
	@ResponseBody
	public OrderInfo saleGenerateBuy(String serialNum) {
		Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
		
		OrderInfo orderInfo = orderService.selectById(serialNum);
    	
    	OrderMaterielExample m =new OrderMaterielExample();
    	//and 条件1
    	com.congmai.zhgj.web.model.OrderMaterielExample.Criteria criteria =  m.createCriteria();
    	criteria.andDelFlgEqualTo("0");
    	criteria.andOrderSerialEqualTo(serialNum);
    	m.setOrderByClause(" sort asc");
    	List<OrderMateriel> orderMateriel = orderMaterielService.selectList(m);
    	
    	Set<String> supplySet = new HashSet<String>();//存入所有物料供应商，不会重复
    	if(orderMateriel!=null){//循环供应物料，集合所有的供应商
    		for(OrderMateriel o:orderMateriel){
    			if(o.getSupplyMateriel()!=null){
    				supplySet.add(o.getSupplyMateriel().getSupplyComId());
    			}
    		}
    	}
    	String numCode = "";//生成的采购单号
    	if(supplySet.size()>0){
    		for(String supplyComId : supplySet){//对于某一供应商生成采购订单
    			String newSerialNum = ApplicationUtils.random32UUID();//生成新的采购订单流水号
    			String newContractSerialNum = ApplicationUtils.random32UUID();//生成新的采购合同流水号
    			OrderInfo newOrderInfo = new OrderInfo();//生成新的采购订单
    			beanCopier.copy(orderInfo, newOrderInfo, null);
    			newOrderInfo.setSerialNum(newSerialNum);//设置新的流水号
    			String temp = getNumCode("PO");
    			newOrderInfo.setOrderNum(temp);
    			numCode = numCode + temp + " ";
    			newOrderInfo.setContractSerial(newContractSerialNum);
    			newOrderInfo.setSupplyComId(supplyComId);//设置新的供应商
    			newOrderInfo.setOrderSerial(orderInfo.getOrderNum());//设置关联销售订单号
    			newOrderInfo.setBuyComId(null);//表示采购商为平台，即采购订单
    			newOrderInfo.setOrderType(StaticConst.getInfo("dailiBuy"));//设置为代理采购
    			newOrderInfo.setTradeType(StaticConst.getInfo("neimao"));//设置为内贸
    			newOrderInfo.setSeller(StaticConst.getInfo("comName"));
    			
    			if(supplySet.size()>1){
    				newOrderInfo.setMaterielCount(null);
    				newOrderInfo.setMaterielAmount(null);
    				newOrderInfo.setRateAmount(null);
    				newOrderInfo.setRateAndAmount(null);
    				newOrderInfo.setOtherAmount(null);
    				newOrderInfo.setOrderAmount(null);
    			}
    			
    			Company company =  companyService.selectOne(orderInfo.getBuyComId());
    			if(company!=null){
    				newOrderInfo.setEntrustParty(company.getComName());
    			}
    			
    			newOrderInfo.setCreator(currenLoginName);
    			newOrderInfo.setUpdater(currenLoginName);
    			newOrderInfo.setMaker(currenLoginName);
    			newOrderInfo.setCreateTime(new Date());
    			newOrderInfo.setUpdateTime(new Date());
    			newOrderInfo.setMakeDate(new Date());
    			newOrderInfo.setStatus("0");
    			orderService.insert(newOrderInfo);
    			List<OrderMateriel> newMaterielList = new ArrayList<OrderMateriel>();//生成新的采购订单物料
    			Double materielCount = 0D;
    			for(OrderMateriel o:orderMateriel){
        			if(o.getSupplyMateriel()!=null){
        				if(supplyComId.equals(o.getSupplyMateriel().getSupplyComId())){
        					o.setSerialNum(ApplicationUtils.random32UUID());
        					o.setSupplyMaterielSerial(null);//采购订单物料为标准物料
        					o.setOrderSerial(newSerialNum);
        		    		o.setCreator(currenLoginName);
        	    			o.setUpdater(currenLoginName);
        	    			o.setCreateTime(new Date());
        	    			o.setUpdateTime(new Date());
        					newMaterielList.add(o);
        					
        					materielCount = materielCount + Double.parseDouble(o.getAmount());
        				}
        			}
        		}
    			orderMaterielService.betchInsertOrderMateriel(newMaterielList);//插入新的订单物料
    			if(supplySet.size()>1){
	    			OrderInfo updateOrderMaterielCount = new OrderInfo();//修改销售订单的关联采购订单号
	    			updateOrderMaterielCount.setSerialNum(newSerialNum);
	    			updateOrderMaterielCount.setMaterielCount(materielCount.toString());
	        		orderService.updateStatus(updateOrderMaterielCount);
    			}
    			//获取合同信息
    	    	ContractVO contract = null;
    			if(StringUtils.isNotEmpty(orderInfo.getContractSerial())){
    	    		contract=contractService.selectConbtractById(orderInfo.getContractSerial());
    	    	}
    			if(contract!=null){
    				ContractVO newcontract = new ContractVO();
    				beanContractVOCopier.copy(contract, newcontract, null);
    				newcontract.setId(newContractSerialNum);
    				newcontract.setComId(supplyComId);
    				newcontract.setContractNum(getNumCode("CA"));
    				newcontract.setContractType(StaticConst.getInfo("buyContract"));//设置合同类型为采购合同
    				newcontract.setCreator(currenLoginName);
    				newcontract.setUpdater(currenLoginName);
    				newcontract.setCreateTime(new Date());
    				newcontract.setUpdateTime(new Date());
    				newcontract.setStatus(ContractVO.WAIT_SIGN);
    				
    				contractService.insertContract(newcontract);
    			}

    	    	if(contract!=null&&StringUtils.isNotEmpty(contract.getId())){
    	    		//获取合同条款信息
    	    		ClauseAfterSales clauseAfterSales = clauseAfterSalesService.selectByContractId(contract.getId());
    	    		if(clauseAfterSales!=null){
    	    			clauseAfterSales.setSerialNum(ApplicationUtils.random32UUID());
    	    			clauseAfterSales.setContractSerial(newContractSerialNum);
    	        		clauseAfterSales.setCreator(currenLoginName);
    	        		clauseAfterSales.setUpdater(currenLoginName);
    	        		clauseAfterSales.setCreateTime(new Date());
    	        		clauseAfterSales.setUpdateTime(new Date());
    	        		clauseAfterSalesService.insert(clauseAfterSales);
    	    		}
    	    			
    	    		ClauseAdvance clauseAdvance = clauseAdvanceService.selectByContractId(contract.getId());
    	    		if(clauseAdvance!=null){
    	    			clauseAdvance.setSerialNum(ApplicationUtils.random32UUID());
    	    			clauseAdvance.setContractSerial(newContractSerialNum);
    	        		clauseAdvance.setCreator(currenLoginName);
    	        		clauseAdvance.setUpdater(currenLoginName);
    	        		clauseAdvance.setCreateTime(new Date());
    	        		clauseAdvance.setUpdateTime(new Date());
    	        		clauseAdvanceService.insert(clauseAdvance);
    	    		}
    	    		ClauseCheckAccept clauseCheckAccept = clauseCheckAcceptService.selectByContractId(contract.getId());
    	    		if(clauseCheckAccept!=null){
    	    			clauseCheckAccept.setSerialNum(ApplicationUtils.random32UUID());
    	    			clauseCheckAccept.setContractSerial(newContractSerialNum);
    	        		clauseCheckAccept.setCreator(currenLoginName);
    	        		clauseCheckAccept.setUpdater(currenLoginName);
    	        		clauseCheckAccept.setCreateTime(new Date());
    	        		clauseCheckAccept.setUpdateTime(new Date());
    	        		clauseCheckAcceptService.insert(clauseCheckAccept);
    	    		}
    	    		ClauseDelivery clauseDelivery = clauseDeliveryService.selectByContractId(contract.getId());
    	    		if(clauseDelivery!=null){
    	    			clauseDelivery.setSerialNum(ApplicationUtils.random32UUID());
    	    			clauseDelivery.setContractSerial(newContractSerialNum);
    	        		clauseDelivery.setCreator(currenLoginName);
    	        		clauseDelivery.setUpdater(currenLoginName);
    	        		clauseDelivery.setCreateTime(new Date());
    	        		clauseDelivery.setUpdateTime(new Date());

    	        		clauseDeliveryService.insert(clauseDelivery);
    	    		}
    	    		
    	    		ClauseSettlement clauseSettlement = clauseSettlementService.selectByContractId(contract.getId());
    	    		if(clauseSettlement!=null){
    	    			clauseSettlement.setSerialNum(ApplicationUtils.random32UUID());
    	    			clauseSettlement.setContractSerial(newContractSerialNum);
    	        		clauseSettlement.setCreator(currenLoginName);
    	        		clauseSettlement.setUpdater(currenLoginName);
    	        		clauseSettlement.setCreateTime(new Date());
    	        		clauseSettlement.setUpdateTime(new Date());

    	        		if(supplySet.size()>1){
    	        			clauseSettlement.setMaterielAmount(null);
    	        			clauseSettlement.setRateAmount(null);
    	        			clauseSettlement.setRateAndAmount(null);
    	        			clauseSettlement.setOtherAmount(null);
    	        			clauseSettlement.setOrderAmount(null);
            			}
    	        		clauseSettlementService.insert(clauseSettlement);
    	        		
    	        		List<ClauseSettlementDetail> clauseSettlementDetail = clauseSettlement.getClauseSettlementDetails();
        				if(!CollectionUtils.isEmpty(clauseSettlementDetail)){
        			    	for(ClauseSettlementDetail f:clauseSettlementDetail){
        			    		f.setSerialNum(ApplicationUtils.random32UUID());
        			    		f.setClauseSettlementSerial(clauseSettlement.getSerialNum());
        			    		f.setCreator(currenLoginName);
        		    			f.setUpdater(currenLoginName);
        		    			f.setCreateTime(new Date());
        		    			f.setUpdateTime(new Date());
        			    	}
        			    	clauseSettlementDetailService.betchInsertClauseSettlementDetails(clauseSettlementDetail);
        			    	//数据插入******↑↑↑↑↑↑********
        		        }
    	    		}
    	    	}
    	    	if(supplySet.size()==1){
    	    		//获取订单附件
        	    	OrderFileExample om =new OrderFileExample();
        	    	com.congmai.zhgj.web.model.OrderFileExample.Criteria criteria2 =  om.createCriteria();
        	    	criteria2.andOrderSerialEqualTo(serialNum);
        	    	criteria2.andDelFlgEqualTo("0");
        	    	List<OrderFile> file = orderFileService.selectList(om);
        	    	
        	    	if(!CollectionUtils.isEmpty(file)){
        	    		for(OrderFile f:file){
        		    		f.setSerialNum(ApplicationUtils.random32UUID());
        		    		f.setOrderSerial(newSerialNum);
        		    		f.setUploader(currenLoginName);
        		    		f.setCreator(currenLoginName);
        	    			f.setUpdater(currenLoginName);
        	    			f.setUploadDate(new Date());
        	    			f.setCreateTime(new Date());
        	    			f.setUpdateTime(new Date());
        		    	}
        		    	//填充File******↑↑↑↑↑↑********
        		    	orderFileService.betchInsertOrderFiles(file);
        	    	}
        	    	
    			}
    	    	
    		}
    		
    		
    		OrderInfo updateOrderInfo = new OrderInfo();//修改销售订单的关联采购订单号
    		updateOrderInfo.setSerialNum(serialNum);
    		updateOrderInfo.setOrderSerial(numCode);
    		orderService.updateOrderRelation(updateOrderInfo);
    	}
    	
    	orderInfo = orderService.selectById(orderInfo.getSerialNum());
		return orderInfo;
	}
	
	
	@Resource
    private ProcurementPlanService procurementPlanService;
    @Resource
    private ProcurementPlanMaterielService procurementPlanMaterielService;
    
    @Resource
    private com.congmai.zhgj.web.service.BOMMaterielService BOMMaterielService;
	/**
	 * 
	 * @Description 销售订单生成采购计划
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/saleGenerateProcurementPlan")
	@ResponseBody
	public OrderInfo saleGenerateProcurementPlan(String serialNum) {
		Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
		
		OrderInfo orderInfo = orderService.selectById(serialNum);
    	
    	OrderMaterielExample m =new OrderMaterielExample();
    	//and 条件1
    	com.congmai.zhgj.web.model.OrderMaterielExample.Criteria criteria =  m.createCriteria();
    	criteria.andDelFlgEqualTo("0");
    	criteria.andOrderSerialEqualTo(serialNum);
    	m.setOrderByClause(" sort asc");
    	List<OrderMateriel> orderMateriel = orderMaterielService.selectList(m);
    	
    	String numCode = getNumCode("PL");//生成的采购单号
    	
		String newSerialNum = ApplicationUtils.random32UUID();//生成新的采购计划流水号
		ProcurementPlan newProcurementPlan = new ProcurementPlan();
		newProcurementPlan.setSerialNum(newSerialNum);//设置新的流水号
		
		newProcurementPlan.setProcurementPlanNum(numCode);
		newProcurementPlan.setSaleOrderSerial(serialNum);
		newProcurementPlan.setCreator(currenLoginName);
		newProcurementPlan.setUpdater(currenLoginName);
		newProcurementPlan.setCreateTime(new Date());
		newProcurementPlan.setUpdateTime(new Date());
		newProcurementPlan.setStatus("0");
		
		List<ProcurementPlanMateriel> newMaterielList = new ArrayList<ProcurementPlanMateriel>();//生成新的采购订单物料
		Double materielCount = 0D;
		for(OrderMateriel o:orderMateriel){
			
			if("1".equals(o.getMateriel().getIsBOM())){//如果是bom物料
		    	BOMMaterielExample m1 =new BOMMaterielExample();
		    	com.congmai.zhgj.web.model.BOMMaterielExample.Criteria criteria1 =  m1.createCriteria();
		    	criteria1.andBomMaterielSerialEqualTo(o.getMateriel().getSerialNum());
		    	List<BOMMateriel> BOM = BOMMaterielService.selectList(m1);
		    	if(CollectionUtils.isEmpty(BOM)){
		    		ProcurementPlanMateriel pMateriel = new ProcurementPlanMateriel();
	    			pMateriel.setSerialNum(ApplicationUtils.random32UUID());
	    			pMateriel.setMaterielSerial(o.getMaterielSerial());//采购订单物料为标准物料
	    			pMateriel.setProcurementPlanSerial(newSerialNum);
	    			pMateriel.setPlanCount(o.getAmount());
	    			pMateriel.setBuyCount(o.getAmount());
	    			materielCount = materielCount + Double.parseDouble(pMateriel.getBuyCount());
	    			
	    			
	    			pMateriel.setDeliveryAddress(o.getDeliveryAddress());
	    			pMateriel.setDeliveryDate(DateUtil.addDay(o.getDeliveryDate(), -10));//采购计划中交付日期提前10天
	    			pMateriel.setLastDeliveryDate(o.getLastDeliveryDate());
	    			pMateriel.setCreator(currenLoginName);
	    			pMateriel.setUpdater(currenLoginName);
	    			pMateriel.setCreateTime(new Date());
	    			pMateriel.setUpdateTime(new Date());
	    			
	    			if(o.getMateriel()!=null&& !CollectionUtils.isEmpty(o.getMateriel().getSupplyMateriels())){
	    				pMateriel.setSupplyMaterielSerial((o.getMateriel().getSupplyMateriels()).get(0).getSupplyComId());//设置供应商id
	    			}
	    			
	    			newMaterielList.add(pMateriel);
		    	}else{
		    		
		    		for(BOMMateriel b:BOM){
		    			ProcurementPlanMateriel pMateriel = new ProcurementPlanMateriel();
		    			pMateriel.setSerialNum(ApplicationUtils.random32UUID());
		    			pMateriel.setMaterielSerial(b.getMaterielSerial());//采购订单物料为标准物料
		    			pMateriel.setProcurementPlanSerial(newSerialNum);
		    			pMateriel.setPlanCount(
		    					String.valueOf(Double.parseDouble(o.getAmount()==null?"0":o.getAmount())
		    							*Double.parseDouble(b.getSingleDose()==null?"0":b.getSingleDose())));
		    			pMateriel.setBuyCount(
		    					String.valueOf(Double.parseDouble(o.getAmount()==null?"0":o.getAmount())
		    							*Double.parseDouble(b.getSingleDose()==null?"0":b.getSingleDose())));
		    			materielCount = materielCount + Double.parseDouble(pMateriel.getBuyCount());
		    			pMateriel.setDeliveryAddress(o.getDeliveryAddress());
		    			pMateriel.setDeliveryDate(DateUtil.addDay(o.getDeliveryDate(), -10));//采购计划中交付日期提前10天
		    			pMateriel.setLastDeliveryDate(o.getLastDeliveryDate());
		    			pMateriel.setCreator(currenLoginName);
		    			pMateriel.setUpdater(currenLoginName);
		    			pMateriel.setCreateTime(new Date());
		    			pMateriel.setUpdateTime(new Date());
		    			
		    			if(b.getMateriel()!=null&& !CollectionUtils.isEmpty(b.getMateriel().getSupplyMateriels())){
		    				pMateriel.setSupplyMaterielSerial((b.getMateriel().getSupplyMateriels()).get(0).getSupplyComId());//设置供应商id
		    			}
		    			newMaterielList.add(pMateriel);
		    		}
		    	}
	    	}else{
	    		ProcurementPlanMateriel pMateriel = new ProcurementPlanMateriel();
    			pMateriel.setSerialNum(ApplicationUtils.random32UUID());
    			pMateriel.setMaterielSerial(o.getMaterielSerial());//采购订单物料为标准物料
    			pMateriel.setProcurementPlanSerial(newSerialNum);
    			pMateriel.setPlanCount(o.getAmount());
    			pMateriel.setBuyCount(o.getAmount());
    			materielCount = materielCount + Double.parseDouble(pMateriel.getBuyCount());
    			pMateriel.setDeliveryAddress(o.getDeliveryAddress());
    			pMateriel.setDeliveryDate(DateUtil.addDay(o.getDeliveryDate(), -10));//采购计划中交付日期提前10天
    			pMateriel.setLastDeliveryDate(o.getLastDeliveryDate());
    			pMateriel.setCreator(currenLoginName);
    			pMateriel.setUpdater(currenLoginName);
    			pMateriel.setCreateTime(new Date());
    			pMateriel.setUpdateTime(new Date());
    			if(o.getMateriel()!=null&& !CollectionUtils.isEmpty(o.getMateriel().getSupplyMateriels())){
    				pMateriel.setSupplyMaterielSerial((o.getMateriel().getSupplyMateriels()).get(0).getSupplyComId());//设置供应商id
    			}
    			newMaterielList.add(pMateriel);
	    	}
			
			
		}
		newProcurementPlan.setBuyCount(String.valueOf(materielCount));
		procurementPlanService.insert(newProcurementPlan);
		procurementPlanMaterielService.betchInsertProcurementPlanMateriel(newMaterielList);//插入新的订单物料
	
    		
		OrderInfo updateOrderInfo = new OrderInfo();//修改销售订单的关联采购订单号
		updateOrderInfo.setSerialNum(serialNum);
		updateOrderInfo.setOrderSerial(numCode);
		orderService.updateOrderRelation(updateOrderInfo);
    	
    	orderInfo = orderService.selectById(orderInfo.getSerialNum());
    	
    	//分解订单发送消息至采购计划操作人
    	EventExample.getEventPublisher().publicSendMessageEvent(new SendMessageEvent(newProcurementPlan,MessageConstants.SALE_TO_PALN));
		return orderInfo;
	}
	/**
	 * 
	 * @Description 复制订单
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/copyOrderInfo")
	@ResponseBody
	public void copyOrderInfo(String serialNum) {
		Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
		
		OrderInfo orderInfo = orderService.selectById(serialNum);
    	
    	OrderMaterielExample m =new OrderMaterielExample();
    	//and 条件1
    	com.congmai.zhgj.web.model.OrderMaterielExample.Criteria criteria =  m.createCriteria();
    	criteria.andDelFlgEqualTo("0");
    	criteria.andOrderSerialEqualTo(serialNum);
    	m.setOrderByClause(" sort asc");
    	List<OrderMateriel> orderMateriel = orderMaterielService.selectList(m);
    	
		String newSerialNum = ApplicationUtils.random32UUID();//生成新的采购订单流水号
		String newContractSerialNum = ApplicationUtils.random32UUID();//生成新的采购合同流水号
		OrderInfo newOrderInfo = new OrderInfo();//生成新的采购订单
		beanCopier.copy(orderInfo, newOrderInfo, null);
		newOrderInfo.setSerialNum(newSerialNum);//设置新的流水号
		newOrderInfo.setStatus("0");
		String comId = null;
    	User user = UserUtil.getUserFromSession();
    	if(user!=null){
			comId = userCompanyService.getUserComId(String.valueOf(user.getUserId()));
			if(comId!=null){//采购商新建订单
				newOrderInfo.setStatus(OrderInfo.CUSTOMER);
			}
		}
    	String temp = getNumCode("SO");
    	if(newOrderInfo.getSupplyComId()!=null){
    		temp = getNumCode("PO");
    	}
		
		newOrderInfo.setOrderNum(temp);
		newOrderInfo.setContractSerial(newContractSerialNum);
		newOrderInfo.setCreator(currenLoginName);
		newOrderInfo.setUpdater(currenLoginName);
		newOrderInfo.setMaker(currenLoginName);
		newOrderInfo.setCreateTime(new Date());
		newOrderInfo.setUpdateTime(new Date());
		newOrderInfo.setMakeDate(new Date());
		newOrderInfo.setDeliveryCount(null);
		newOrderInfo.setPayAmount(null);
		newOrderInfo.setDemandPlanSerial(null);
		newOrderInfo.setSaleApplySerial(null);
		newOrderInfo.setOrderSerial(null);
		newOrderInfo.setDeliverStatus(null);
		newOrderInfo.setPayStatus(null);
		newOrderInfo.setBillStatus(null);
		
		orderService.insert(newOrderInfo);
		List<OrderMateriel> newMaterielList = new ArrayList<OrderMateriel>();//生成新的采购订单物料
		for(OrderMateriel o:orderMateriel){
			o.setSerialNum(ApplicationUtils.random32UUID());
			o.setOrderSerial(newSerialNum);
    		o.setCreator(currenLoginName);
			o.setUpdater(currenLoginName);
			o.setCreateTime(new Date());
			o.setUpdateTime(new Date());
			o.setDemandPlanMaterielSerial(null);
			newMaterielList.add(o);
		}
		orderMaterielService.betchInsertOrderMateriel(newMaterielList);//插入新的订单物料

		//获取合同信息
    	ContractVO contract = null;
		if(StringUtils.isNotEmpty(orderInfo.getContractSerial())){
    		contract=contractService.selectConbtractById(orderInfo.getContractSerial());
    	}
		if(contract!=null){
			ContractVO newcontract = new ContractVO();
			beanContractVOCopier.copy(contract, newcontract, null);
			newcontract.setId(newContractSerialNum);
//			newcontract.setComId(supplyComId);
			newcontract.setContractNum(getNumCode("CA"));
//			newcontract.setContractType(StaticConst.getInfo("buyContract"));//设置合同类型为采购合同
			newcontract.setCreator(currenLoginName);
			newcontract.setUpdater(currenLoginName);
			newcontract.setCreateTime(new Date());
			newcontract.setUpdateTime(new Date());
			newcontract.setStatus(ContractVO.WAIT_SIGN);
			
			contractService.insertContract(newcontract);
		}

    	if(contract!=null&&StringUtils.isNotEmpty(contract.getId())){
    		//获取合同条款信息
    		ClauseAfterSales clauseAfterSales = clauseAfterSalesService.selectByContractId(contract.getId());
    		if(clauseAfterSales!=null){
    			clauseAfterSales.setSerialNum(ApplicationUtils.random32UUID());
    			clauseAfterSales.setContractSerial(newContractSerialNum);
        		clauseAfterSales.setCreator(currenLoginName);
        		clauseAfterSales.setUpdater(currenLoginName);
        		clauseAfterSales.setCreateTime(new Date());
        		clauseAfterSales.setUpdateTime(new Date());
        		clauseAfterSalesService.insert(clauseAfterSales);
    		}
    			
    		ClauseAdvance clauseAdvance = clauseAdvanceService.selectByContractId(contract.getId());
    		if(clauseAdvance!=null){
    			clauseAdvance.setSerialNum(ApplicationUtils.random32UUID());
    			clauseAdvance.setContractSerial(newContractSerialNum);
        		clauseAdvance.setCreator(currenLoginName);
        		clauseAdvance.setUpdater(currenLoginName);
        		clauseAdvance.setCreateTime(new Date());
        		clauseAdvance.setUpdateTime(new Date());
        		clauseAdvanceService.insert(clauseAdvance);
    		}
    		ClauseCheckAccept clauseCheckAccept = clauseCheckAcceptService.selectByContractId(contract.getId());
    		if(clauseCheckAccept!=null){
    			clauseCheckAccept.setSerialNum(ApplicationUtils.random32UUID());
    			clauseCheckAccept.setContractSerial(newContractSerialNum);
        		clauseCheckAccept.setCreator(currenLoginName);
        		clauseCheckAccept.setUpdater(currenLoginName);
        		clauseCheckAccept.setCreateTime(new Date());
        		clauseCheckAccept.setUpdateTime(new Date());
        		clauseCheckAcceptService.insert(clauseCheckAccept);
    		}
    		ClauseDelivery clauseDelivery = clauseDeliveryService.selectByContractId(contract.getId());
    		if(clauseDelivery!=null){
    			clauseDelivery.setSerialNum(ApplicationUtils.random32UUID());
    			clauseDelivery.setContractSerial(newContractSerialNum);
        		clauseDelivery.setCreator(currenLoginName);
        		clauseDelivery.setUpdater(currenLoginName);
        		clauseDelivery.setCreateTime(new Date());
        		clauseDelivery.setUpdateTime(new Date());

        		clauseDeliveryService.insert(clauseDelivery);
    		}
    		
    		ClauseSettlement clauseSettlement = clauseSettlementService.selectByContractId(contract.getId());
    		if(clauseSettlement!=null){
    			clauseSettlement.setSerialNum(ApplicationUtils.random32UUID());
    			clauseSettlement.setContractSerial(newContractSerialNum);
        		clauseSettlement.setCreator(currenLoginName);
        		clauseSettlement.setUpdater(currenLoginName);
        		clauseSettlement.setCreateTime(new Date());
        		clauseSettlement.setUpdateTime(new Date());

        		clauseSettlementService.insert(clauseSettlement);
        		
        		List<ClauseSettlementDetail> clauseSettlementDetail = clauseSettlement.getClauseSettlementDetails();
				if(!CollectionUtils.isEmpty(clauseSettlementDetail)){
			    	for(ClauseSettlementDetail f:clauseSettlementDetail){
			    		f.setSerialNum(ApplicationUtils.random32UUID());
			    		f.setClauseSettlementSerial(clauseSettlement.getSerialNum());
			    		f.setCreator(currenLoginName);
		    			f.setUpdater(currenLoginName);
		    			f.setCreateTime(new Date());
		    			f.setUpdateTime(new Date());
			    	}
			    	clauseSettlementDetailService.betchInsertClauseSettlementDetails(clauseSettlementDetail);
			    	//数据插入******↑↑↑↑↑↑********
		        }
    		}
    	}
	}
	
	
	/**
     * 
     * @Description (启动采购框架流程)
     * @param params
     * @return
     */
    @RequestMapping(value = "/startBuyFrameProcess", method = RequestMethod.POST)
    @ResponseBody
    public String startBuyFrameProcess(@RequestBody String params) {
    	String flag = "0"; //默认失败
    	params = params.replace("\\", "");
		ObjectMapper objectMapper = new ObjectMapper();  
		ContractVO contract = new ContractVO();
		try {
			contract = objectMapper.readValue(params, ContractVO.class);
		} catch (Exception e1) {
			return flag;
		} 
		String remarkString = contract.getRemark();
		contract.setRemark(null);
    	contract.setStatus(contract.APPING);//在未确认状态提交申请的订单，设置为已确认
    	contractService.update(contract);
    	
		//启动订单审批测试流程-start
		User user = UserUtil.getUserFromSession();
		contract.setUserId(user.getUserId());
		contract.setUser_name(user.getUserName());
		contract.setTitle(user.getUserName()+" 的采购框架申请");
		contract.setBusinessType(BaseVO.BUYFRAME); 			//业务类型：采购框架
		contract.setStatus(BaseVO.PENDING);					//审批中
		contract.setApplyDate(new Date());
		contract.setReason(remarkString);
		contract.setSerialNum(contract.getId());
    	processBaseService.insert(contract);
    	
    	
		String businessKey = contract.getId().toString();
		contract.setBusinessKey(businessKey);
		try {
			String processInstanceId = this.processService.startBuyFramerProcess(contract);
//                message.setStatus(Boolean.TRUE);
//    			message.setMessage("订单流程已启动，流程ID：" + processInstanceId);
			
			//申请加入流程已办
			HistoricTaskVO historicTaskVO = new HistoricTaskVO();
			historicTaskVO.setTaskId(ApplicationUtils.random32UUID());
			historicTaskVO.setProcessInstanceId(processInstanceId);
			historicTaskVO.setStartTime(new Date());
			historicTaskVO.setEndTime(new Date());
			historicTaskVO.setProcessDefId(contract.getBusinessKey());
			historicTaskVO.setUserId(user.getUserId().toString());
			
			processBaseService.insertHistoricTask(historicTaskVO);
		    logger.info("processInstanceId: "+processInstanceId);
		    
		    flag = "1";
		} catch (ActivitiException e) {
//            	message.setStatus(Boolean.FALSE);
		    if (e.getMessage().indexOf("no processes deployed with key") != -1) {
		        logger.warn("没有部署流程!", e);
//        			message.setMessage("没有部署流程，请联系系统管理员，在[流程定义]中部署相应流程文件！");
		    } else {
		        logger.error("启动流程失败：", e);
//                    message.setMessage("启动订单流程失败，系统内部错误！");
		    }
		    throw e;
		} catch (Exception e) {
		    logger.error("启动流程失败：", e);
//                message.setStatus(Boolean.FALSE);
//                message.setMessage("启动订单流程失败，系统内部错误！");
		    throw e;
		}
        //启动订单审批测试流程-end
		return flag;
	}
    
    
    /**
     * 
     * @Description (启动销售框架流程)
     * @param params
     * @return
     */
    @RequestMapping(value = "/startSaleFrameProcess", method = RequestMethod.POST)
    @ResponseBody
    public String startSaleFrameProcess(@RequestBody String params) {
    	String flag = "0"; //默认失败
    	params = params.replace("\\", "");
		ObjectMapper objectMapper = new ObjectMapper();  
		ContractVO contract = new ContractVO();
		try {
			contract = objectMapper.readValue(params, ContractVO.class);
		} catch (Exception e1) {
			return flag;
		}
		
		String remarkString = contract.getRemark();
		contract.setRemark(null);
    	contract.setStatus(contract.APPING);//在未确认状态提交申请的订单，设置为已确认
    	contractService.update(contract);
    	
		//启动订单审批测试流程-start
		User user = UserUtil.getUserFromSession();
		contract.setUserId(user.getUserId());
		contract.setUser_name(user.getUserName());
		contract.setTitle(user.getUserName()+" 的销售框架申请");
		contract.setBusinessType(BaseVO.SALEFRAME); 			//业务类型：销售框架
		contract.setStatus(BaseVO.PENDING);					//审批中
		contract.setApplyDate(new Date());
		contract.setReason(remarkString);
		contract.setSerialNum(contract.getId());
    	processBaseService.insert(contract);
		String businessKey = contract.getId().toString();
		contract.setBusinessKey(businessKey);
		try {
			String processInstanceId = this.processService.startSaleFramerProcess(contract);
			
			//申请加入流程已办
			HistoricTaskVO historicTaskVO = new HistoricTaskVO();
			historicTaskVO.setTaskId(ApplicationUtils.random32UUID());
			historicTaskVO.setProcessInstanceId(processInstanceId);
			historicTaskVO.setStartTime(new Date());
			historicTaskVO.setEndTime(new Date());
			historicTaskVO.setProcessDefId(contract.getBusinessKey());
			historicTaskVO.setUserId(user.getUserId().toString());
			
			processBaseService.insertHistoricTask(historicTaskVO);
//                message.setStatus(Boolean.TRUE);
//    			message.setMessage("订单流程已启动，流程ID：" + processInstanceId);
		    logger.info("processInstanceId: "+processInstanceId);
		    flag = "1";
		} catch (ActivitiException e) {
//            	message.setStatus(Boolean.FALSE);
		    if (e.getMessage().indexOf("no processes deployed with key") != -1) {
		        logger.warn("没有部署流程!", e);
//        			message.setMessage("没有部署流程，请联系系统管理员，在[流程定义]中部署相应流程文件！");
		    } else {
		        logger.error("启动流程失败：", e);
//                    message.setMessage("启动订单流程失败，系统内部错误！");
		    }
		    throw e;
		} catch (Exception e) {
		    logger.error("启动流程失败：", e);
//                message.setStatus(Boolean.FALSE);
//                message.setMessage("启动订单流程失败，系统内部错误！");
		    throw e;
		}
        //启动订单审批测试流程-end
		return flag;
	}
    
    
    /**
     * 审批框架协议流程
     * @param taskId
     * @param model
     * @return
     * @throws NumberFormatException
     * @throws Exception
     */
//	@RequiresPermissions("user:order:toApproval") 	//*代表 经理、总监、人力
    @RequestMapping(value = "/toFrameApproval/{taskId}", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody Map<String, Object> toFrameApproval(@PathVariable("taskId") String taskId) throws NumberFormatException, Exception{
    	Task task = this.taskService.createTaskQuery().taskId(taskId).singleResult();
		// 根据任务查询流程实例
    	String processInstanceId = task.getProcessInstanceId();
		ProcessInstance pi = this.runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		ContractVO contract = (ContractVO) this.runtimeService.getVariable(pi.getId(), "entity");
		contract.setTask(task);
		contract.setProcessInstanceId(processInstanceId);
		List<CommentVO> commentList = this.processService.getComments(processInstanceId);
		String taskDefinitionKey = task.getTaskDefinitionKey();
		logger.info("taskDefinitionKey: "+taskDefinitionKey);
		String result = null;
		if("modifyApply".equals(taskDefinitionKey)){
			result = "modify";
		}else{
			result = "audit";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("actionType", result);
		map.put("contract", contract);
		map.put("commentList", commentList);
    	return map;
    }
    
    
    /**
     * 完成任务
     * @param content
     * @param completeFlag
     * @param taskId
     * @param redirectAttributes
     * @param session
     * @return
     * @throws Exception
     */
//	@RequiresPermissions("user:order:complate")  //数据库中权限字符串为user:*:complate， 通配符*匹配到order所以有权限操作 
    @RequestMapping(value = "/complateFrame/{taskId}", method = RequestMethod.POST, produces = "application/text;charset=UTF-8")
	@ResponseBody
    public String complateFrame(
    		@RequestParam("frameId") String id,
    		@RequestParam("content") String content,
    		@RequestParam("processInstanceId") String processInstanceId,
    		@RequestParam("completeFlag") Boolean completeFlag,
    		@PathVariable("taskId") String taskId, 
    		RedirectAttributes redirectAttributes) throws Exception{
    	User user = UserUtil.getUserFromSession();
    	String result = "";
    	try {
    		ContractVO contract = this.contractService.selectConbtractById(id);
    		ContractVO baseContract = (ContractVO) this.runtimeService.getVariable(processInstanceId, "entity");
    		Map<String, Object> variables = new HashMap<String, Object>();
    		variables.put("isPass", completeFlag);
    		if(!completeFlag){
    			baseContract.setTitle(baseContract.getUser_name()+" 的订单申请失败,需修改后重新提交！");
    			contract.setStatus(BaseVO.APPROVAL_FAILED);
    			variables.put("entity", baseContract);
    			
    			
    	
    		}else{
    			contract.setStatus(BaseVO.PENDING);					//审批中
    		}
    		// 完成任务
    		this.processService.complete(taskId, content, user.getUserId().toString(), variables);
    		
    		
    		ProcessInstance pi = null;
    		if(completeFlag){
    			//此处需要修改，不能根据人来判断审批是否结束。应该根据流程实例id(processInstanceId)来判定。
    			//判断指定ID的实例是否存在，如果结果为空，则代表流程结束，实例已被删除(移到历史库中)
    			pi = this.runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
    			if(BeanUtils.isBlank(pi)){
    				contract.setStatus(BaseVO.APPROVAL_SUCCESS);
    			}
    		}
    		contract.setSerialNum(contract.getId());
    		this.processBaseService.update(contract);
    		
    		if(BaseVO.APPROVAL_SUCCESS.equals(contract.getStatus())){//订单完成，需更新状态为1(订单待接收)
    			ContractVO c = new ContractVO();
    			c.setId(contract.getId());
    			/*if(StaticConst.getInfo("saleFrame").equals(c.getContractType())){//销售框架
    				c.setStatus("1");
    			}else if(StaticConst.getInfo("buyFrame").equals(c.getContractType())){//采购订单
    				c.setStatus("3");
    			}*/
    			c.setStatus("3");
    			this.contractService.update(c);
    		}
    		
    		contract.setProcessInstanceId(processInstanceId);
    		//发送消息

    		result = "任务办理完成！";
		} catch (ActivitiObjectNotFoundException e) {
			result = "此任务不存在，请联系管理员！";
			throw e;
		} catch (ActivitiException e) {
			result = "此任务正在协办，您不能办理此任务！";
			throw e;
		} catch (Exception e) {
			result = "任务办理失败，请联系管理员！";
			throw e;
		}
		return result;
    }
	
    
    /**
	 * 调整订单申请
	 * @param vacation
	 * @param taskId
	 * @param processInstanceId
	 * @param reApply
	 * @param session
	 * @return
	 * @throws Exception
	 */
//	@RequiresPermissions("user:vacation:modify")
	@RequestMapping(value = "/modifyFrame/{taskId}", method = RequestMethod.POST, produces = "application/text;charset=UTF-8")
	@ResponseBody
	public String modifyFrame(
			@PathVariable("taskId") String taskId,
			@RequestParam("processInstanceId") String processInstanceId,
			@RequestParam("reApply") Boolean reApply,
			@RequestParam("frameId") String id,
			@RequestParam("reason") String reason,
			@RequestParam("frameType") String frameType) throws Exception{
		String result = "";
		User user = UserUtil.getUserFromSession();

		ContractVO contract = new ContractVO();
		contract.setId(id);
		
        Map<String, Object> variables = new HashMap<String, Object>();
        contract.setUserId(user.getUserId());
        contract.setUser_name(user.getUserName());
        if(BaseVO.SALEFRAME.equals(frameType)){
        	contract.setBusinessType(BaseVO.SALEFRAME);
        }else{
        	contract.setBusinessType(BaseVO.BUYFRAME);
        }
        
        contract.setApplyDate(new Date());
        contract.setBusinessKey(id);
        contract.setProcessInstanceId(processInstanceId);
        String content = "";
        if(reApply){
        	//修改订单申请
        	if(BaseVO.SALEFRAME.equals(frameType)){
        		contract.setTitle(user.getUserName()+" 的销售框架申请！");
            }else{
            	contract.setTitle(user.getUserName()+" 的采购框架申请！");
            }
        	
        	contract.setStatus(BaseVO.PENDING);
	        content = "重新申请";
	        result = "任务办理完成，订单申请已重新提交！";
	        
	      
        }else{
        	contract.setTitle(user.getUserName()+" 的订单申请已取消！");
        	contract.setStatus(BaseVO.APPROVAL_FAILED);
        	content = "取消申请";
        	result = "任务办理完成，已经取消您的框架协议申请！";
        }
        try {
        	contract.setSerialNum(contract.getId());
    		this.processBaseService.update(contract);
			variables.put("entity", contract);
			variables.put("reApply", reApply);
			this.processService.complete(taskId, content, user.getUserId().toString(), variables);
			
			if(reApply){
		      
	        }else{
	        	cancelFrameApply(contract.getId(),taskId);
	        }
		} catch (ActivitiObjectNotFoundException e) {
//			message.setStatus(Boolean.FALSE);
//			message.setMessage("此任务不存在，请联系管理员！");
			result = "此任务不存在，请联系管理员！";
			throw e;
		} catch (ActivitiException e) {
//			message.setStatus(Boolean.FALSE);
//			message.setMessage("此任务正在协办，您不能办理此任务！");
			result = "此任务正在协办，您不能办理此任务！";
			throw e;
		} catch (Exception e) {
//			message.setStatus(Boolean.FALSE);
//			message.setMessage("任务办理失败，请联系管理员！");
			result = "任务办理失败，请联系管理员！";
			throw e;
		}
		
    	return result;
    }
	
    
    /**
     * 供应商确认框架
     */
    @RequestMapping(value = "/supplyConfirmedFrame", method = RequestMethod.POST)
    @ResponseBody
    public ContractVO supplyConfirmedFrame(@RequestBody String params) {
    	ContractVO contract = jaon2Contract(params);
    	Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
		contract.setUpdater(currenLoginName);
		contract.setUpdateTime(new Date());
		contractService.update(contract);
		return contract;
    }

	private ContractVO jaon2Contract(String params){
		params = params.replace("\\", "");
		ObjectMapper objectMapper = new ObjectMapper();  
		ContractVO contract = new ContractVO();
		try {
			contract = objectMapper.readValue(params, ContractVO.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return contract;
	}
    
    
    /**
     * 平台提交框架
     */
    @RequestMapping(value = "/pingTaiSubmitFrame", method = RequestMethod.POST)
    @ResponseBody
    public ContractVO pingTaiSubmitFrame(@RequestBody String params) {
    	ContractVO contract = jaon2Contract(params);
    	Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
		contract.setUpdater(currenLoginName);
		contract.setUpdateTime(new Date());
		contractService.update(contract);
		return contract;
    }
    
    /**
     * 平台接收框架协议
     */
    @RequestMapping(value = "/reciveFrame", method = RequestMethod.POST)
    @ResponseBody
    public ContractVO reciveFrame(@RequestBody String params) {
    	ContractVO contract = jaon2Contract(params);
    	Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
		contract.setUpdater(currenLoginName);
		contract.setUpdateTime(new Date());
		contractService.update(contract);
		
		return contract;
    }
    
    
    
    /**
     * 用户取消订单申请
     */
    @RequestMapping(value = "/userCancelOrderApply/{taskId}/{processInstanceId}", method = RequestMethod.POST, produces = "application/text;charset=UTF-8")
	@ResponseBody
    public void userCancelOrderApply(@PathVariable("taskId") String taskId, @PathVariable("processInstanceId") String processInstanceId) {
    	ProcessInstance pi = this.runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		OrderInfo orderInfo = (OrderInfo) this.runtimeService.getVariable(pi.getId(), "entity");
		cancelApply(orderInfo.getSerialNum(),taskId);
    }
    
    /**
     * 用户取消框架申请
     */
    @RequestMapping(value = "/userCancelFrameApply/{taskId}/{processInstanceId}", method = RequestMethod.POST, produces = "application/text;charset=UTF-8")
   	@ResponseBody
    public void userCancelFrameApply(@PathVariable("taskId") String taskId, @PathVariable("processInstanceId") String processInstanceId) {
    	ProcessInstance pi = this.runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
    	ContractVO contractVO = (ContractVO) this.runtimeService.getVariable(pi.getId(), "entity");
		cancelFrameApply(contractVO.getSerialNum(),taskId);
    }
    /**
     * 用户取消价格申请
     */
    @RequestMapping(value = "/userCancelPriceApply/{taskId}/{processInstanceId}", method = RequestMethod.POST, produces = "application/text;charset=UTF-8")
   	@ResponseBody
    public void userCancelPriceApply(@PathVariable("taskId") String taskId, @PathVariable("processInstanceId") String processInstanceId) {
    	ProcessInstance pi = this.runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
    	PriceList priceList = (PriceList) this.runtimeService.getVariable(pi.getId(), "entity");
		cancelPriceApply(priceList.getSerialNum(),taskId);
    }
    /**
     * 用户取消发票(销项票)申请
     */
    @RequestMapping(value = "/userCancelInvoiceApply/{taskId}/{processInstanceId}", method = RequestMethod.POST, produces = "application/text;charset=UTF-8")
   	@ResponseBody
    public void userCancelInvoiceApply(@PathVariable("taskId") String taskId, @PathVariable("processInstanceId") String processInstanceId) {
    	ProcessInstance pi = this.runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
    	Invoice invoice = (Invoice) this.runtimeService.getVariable(pi.getId(), "entity");
		cancelInvoiceApply(invoice.getSerialNum(),taskId);
    }
    /**
     * 用户取消发货申请
     */
    @RequestMapping(value = "/userCancelDeliveryApply/{taskId}/{processInstanceId}", method = RequestMethod.POST, produces = "application/text;charset=UTF-8")
	@ResponseBody
    public void userCancelDeliveryApply(@PathVariable("taskId") String taskId, @PathVariable("processInstanceId") String processInstanceId) {
    	ProcessInstance pi = this.runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
    	DeliveryVO deliveryVO = (DeliveryVO) this.runtimeService.getVariable(pi.getId(), "entity");
		cancelDeliveryApply(deliveryVO.getSerialNum(),taskId);
    }
    /**
     * 获取采购/销售订单物料价格
     */
	@RequestMapping(value = "/getUnitPrice", method = RequestMethod.POST)
	@ResponseBody
	public String getUnitPrice(@RequestBody String params) {
		PriceList priceList = json2PriceList(params);
		PriceList priceList1 = null;
		String price = "";
		if (!StringUtils.isEmpty(priceList.getBuyComId())) {
			priceList1 = priceListService.getPriceListInfo(
					priceList.getBuyComId(), priceList.getMaterielSerial(),
					"salePrice");
		} else if (!StringUtils.isEmpty(priceList.getSupplyComId())) {
			priceList1 = priceListService.getPriceListInfo(
					priceList.getSupplyComId(), priceList.getMaterielSerial(),
					"buyPrice");
		}
		if (priceList1 != null) {
			price = priceList1.getPrice();
			if ("1".equals(priceList1.getIsLadderPrice())) {
				List<LadderPrice> list = ladderPriceService
						.selectListByPriceSerial(priceList1.getSerialNum());// 获取阶梯价格
				if (!CollectionUtils.isEmpty(list)) {
					for (LadderPrice lp : list) {
						if (new BigDecimal(priceList1.getNumber())
								.compareTo(new BigDecimal(lp.getCountStart())) > -1
								&& new BigDecimal(priceList1.getNumber())
										.compareTo(new BigDecimal(lp
												.getCountStart())) <= 0) {
							price = lp.getPrice();
							break;
						}

					}

				}

			}
		}
		return price;
	}
	private PriceList json2PriceList(String params) {
		params = params.replace("\\", "");
		ObjectMapper objectMapper = new ObjectMapper();  
		PriceList priceList = new PriceList();
		try {
			priceList = objectMapper.readValue(params, PriceList.class);
		}catch (Exception e1) {
			e1.printStackTrace();
		}
		return priceList;
	}
	 /**
		 * @Description (获取采购订单/销售订单出入库记录)
		 * @param serialNum 订单流水
		 * @param type 采购/销售
		 * @return
		 */
		@RequestMapping(value="/getRecordList",method=RequestMethod.GET)
		 public ResponseEntity<Map<String,Object>> getRecordList(String serialNum,String type) {
			 User user = UserUtil.getUserFromSession();
			 StockInOutRecord record=new StockInOutRecord();
			 record.setPageIndex(0);
			 record.setPageSize(-1);
			 record.setOrderSerial(serialNum);
			 Page<DeliveryMateriel> takeDeliverys=null;
			 if(user != null&&"sale".equals(type)){
				 String comId = userCompanyService.getUserComId(user.getUserId().toString());
				 record.setSupplyComId(comId);
				 takeDeliverys= deliveryMaterielService.selectListByExample(record,"out","1");
			 }else{
				 takeDeliverys=deliveryMaterielService.selectListByExample(record,"in","1");
			 }
			 // 封装datatables数据返回到前台
			 Map<String,Object> pageMap = new HashMap<String,Object>();
			 pageMap.put("draw", 1);
			 pageMap.put("recordsTotal", takeDeliverys==null?0:takeDeliverys.getTotalCount());
			 pageMap.put("recordsFiltered", takeDeliverys==null?0:takeDeliverys.getTotalCount());
			 pageMap.put("data", takeDeliverys.getResult());
			 return new ResponseEntity<Map<String,Object>>(pageMap, HttpStatus.OK);
		 }
}
