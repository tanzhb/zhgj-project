package com.congmai.zhgj.web.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.IdentityService;
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
import org.springframework.beans.factory.annotation.Autowired;
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

import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.core.util.BeanUtils;
import com.congmai.zhgj.core.util.ExcelReader;
import com.congmai.zhgj.core.util.ExcelUtil;
import com.congmai.zhgj.core.util.UserUtil;
import com.congmai.zhgj.core.util.ExcelReader.RowHandler;
import com.congmai.zhgj.web.enums.StaticConst;
import com.congmai.zhgj.web.model.BOMMateriel;
import com.congmai.zhgj.web.model.BOMMaterielExample;
import com.congmai.zhgj.web.model.BaseVO;
import com.congmai.zhgj.web.model.ClauseFramework;
import com.congmai.zhgj.web.model.ClauseAdvance;
import com.congmai.zhgj.web.model.ClauseAfterSales;
import com.congmai.zhgj.web.model.ClauseCheckAccept;
import com.congmai.zhgj.web.model.ClauseDelivery;
import com.congmai.zhgj.web.model.ClauseFrameworkExample;
import com.congmai.zhgj.web.model.ClauseSettlement;
import com.congmai.zhgj.web.model.ClauseSettlementDetail;
import com.congmai.zhgj.web.model.CommentVO;
import com.congmai.zhgj.web.model.ContractVO;
import com.congmai.zhgj.web.model.MaterielExample;
import com.congmai.zhgj.web.model.OrderFile;
import com.congmai.zhgj.web.model.OrderFileExample;
import com.congmai.zhgj.web.model.OrderMateriel;
import com.congmai.zhgj.web.model.Materiel;
import com.congmai.zhgj.web.model.OrderInfo;
import com.congmai.zhgj.web.model.OrderInfoExample;
import com.congmai.zhgj.web.model.OrderMaterielExample;
import com.congmai.zhgj.web.model.User;
import com.congmai.zhgj.web.model.Vacation;
import com.congmai.zhgj.web.model.OrderInfoExample.Criteria;
import com.congmai.zhgj.web.service.ClauseAdvanceService;
import com.congmai.zhgj.web.service.ClauseAfterSalesService;
import com.congmai.zhgj.web.service.ClauseCheckAcceptService;
import com.congmai.zhgj.web.service.ClauseDeliveryService;
import com.congmai.zhgj.web.service.ClauseFrameworkService;
import com.congmai.zhgj.web.service.ClauseSettlementDetailService;
import com.congmai.zhgj.web.service.ClauseSettlementService;
import com.congmai.zhgj.web.service.ContractService;
import com.congmai.zhgj.web.service.IProcessService;
import com.congmai.zhgj.web.service.OrderFileService;
import com.congmai.zhgj.web.service.OrderMaterielService;
import com.congmai.zhgj.web.service.OrderService;
import com.congmai.zhgj.web.service.UserCompanyService;
import com.congmai.zhgj.web.service.ProcessBaseService;


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
    private ClauseFrameworkService clauseFrameworkService;
    @Resource
    private UserCompanyService userCompanyService;
    @Resource
    private ProcessBaseService processBaseService;
    
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
    		orderInfo.setSerialNum(ApplicationUtils.random32UUID());
    		Subject currentUser = SecurityUtils.getSubject();
    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
    		orderInfo.setCreator(currenLoginName);
    		orderInfo.setUpdater(currenLoginName);
    		orderInfo.setCreateTime(new Date());
    		orderInfo.setUpdateTime(new Date());
    		orderInfo.setStatus("0");
    		
    		orderService.insert(orderInfo);
    		
    	}else{//更新
    		Subject currentUser = SecurityUtils.getSubject();
    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
    		orderInfo.setUpdater(currenLoginName);
    		orderInfo.setUpdateTime(new Date());
    		orderService.update(orderInfo);
    	}
    	
    	/*if("1".equals(orderInfo.getStatus())){
    		//启动订单审批测试流程-start
    		startOrderProcess(orderInfo);
    		//启动订单审批测试流程-end
    	}*/
		
		return orderInfo;
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
     * @param orderInfo
     * @return
     */
    @RequestMapping(value = "/startBuyOrderProcess", method = RequestMethod.POST)
    @ResponseBody
	private String startBuyOrderProcess(@RequestBody String params) {
    	String flag = "0"; //默认失败
    	OrderInfo orderInfo = json2Order(params);
    	orderInfo.setUpdateTime(new Date());
    	orderService.update(orderInfo);//更新备注
    	
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
			String processInstanceId = this.processService.startBuyOrderInfo(orderInfo);
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
     * 
     * @Description (启动销售订单流程)
     * @param orderInfo
     * @return
     */
    @RequestMapping(value = "/startSaleOrderProcess", method = RequestMethod.POST)
    @ResponseBody
	private String startSaleOrderProcess(@RequestBody String params) {
    	String flag = "0"; //默认失败
    	OrderInfo orderInfo = json2Order(params);
    	
    	orderService.update(orderInfo);//更新备注
    	
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
    	String processInstanceId = task.getProcessInstanceId();
		ProcessInstance pi = this.runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		OrderInfo orderInfo = (OrderInfo) this.runtimeService.getVariable(pi.getId(), "entity");
		orderInfo.setTask(task);
		orderInfo.setProcessInstanceId(processInstanceId);
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
    		// 完成任务
    		this.processService.complete(taskId, content, user.getUserId().toString(), variables);
    		
    		if(completeFlag){
    			//此处需要修改，不能根据人来判断审批是否结束。应该根据流程实例id(processInstanceId)来判定。
    			//判断指定ID的实例是否存在，如果结果为空，则代表流程结束，实例已被删除(移到历史库中)
    			ProcessInstance pi = this.runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
    			if(BeanUtils.isBlank(pi)){
    				order.setStatus(BaseVO.APPROVAL_SUCCESS);
    			}
    		}
    		
    		this.processBaseService.update(order);
    		
    		if(BaseVO.APPROVAL_SUCCESS.equals(order.getStatus())){//订单完成，需更新状态为1(订单待接收)
    			OrderInfo oi = new OrderInfo();
    			oi.setSerialNum(order.getSerialNum());
    			oi.setStatus("1");
    			this.orderService.update(oi);
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
     * @Description 查询订单列表
     * @param parent(若有值，则查询它及上级物料是它的物料)
     * @return
     */
    @RequestMapping("/findOrderList")
    @ResponseBody
    public ResponseEntity<Map> findOrderList(String type,String selectFor,String fram) {
    	List<OrderInfo> orderInfoList = new ArrayList<OrderInfo>();
//    	OrderInfoExample m =new OrderInfoExample();
//    	//and 条件1
//    	Criteria criteria =  m.createCriteria();
//    	criteria.andDelFlgEqualTo("0");
//    	if("sale".equals(type)){//平台销售订单供应商为空
//    		criteria.andSupplyComIdIsNull();
//    	}else if("buy".equals(type)){//平台采购订单采购商为空
//    		criteria.andBuyComIdIsNull();
//    	}
//    	/*//and 条件2,未发布可编辑的物料
//    	Criteria criteria2 =  m.createCriteria();
//    	criteria2.andStatusEqualTo("0");
//    	criteria2.andDelFlgEqualTo("0");
//    	//or 条件
//    	m.or(criteria2);*/
//    	//排序字段
//    	m.setOrderByClause("updateTime DESC");
    	String comId = null;
    	User user = UserUtil.getUserFromSession();
    	if(user!=null){
			comId = userCompanyService.getUserComId(String.valueOf(user.getUserId()));
			if(comId==null){
				comId = "null";
			}
		}
    	
    	OrderInfo parm =new OrderInfo();
    	if("sale".equals(type)){//平台销售订单供应商为空
    		parm.setSupplyComId(comId);
    	}else if("buy".equals(type)){//平台采购订单采购商为空
    		parm.setBuyComId(comId);
    		if("delivery".equals(selectFor)){
    			parm.setStatus("2");
    		}
    	}else if("supply".equals(type)){//供应商订单(状态不为0)
    		parm.setSupplyComId(comId);
    		parm.setStatus("000");
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
	 * @Description 批量删除订单
	 * @param ids
	 * @return
	 */
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
			orderService.update(m);
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
    	
    	OrderMaterielExample m =new OrderMaterielExample();
    	//and 条件1
    	com.congmai.zhgj.web.model.OrderMaterielExample.Criteria criteria =  m.createCriteria();
    	criteria.andDelFlgEqualTo("0");
    	criteria.andOrderSerialEqualTo(serialNum);
    	List<OrderMateriel> orderMateriel = orderMaterielService.selectList(m);
    	map.put("orderMateriel", orderMateriel);
    	
    	//获取合同信息
    	ContractVO contract = null;
		if(StringUtils.isNotEmpty(orderInfo.getContractSerial())){
    		contract=contractService.selectConbtractById(orderInfo.getContractSerial());
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
        		}else{
        			orderMateriel.setUpdateTime(new Date());
        			orderMateriel.setUpdater(currenLoginName);
        			orderMaterielService.update(orderMateriel);
        		}
        		flag = "1";
        	}catch(Exception e){
        		System.out.println(e.getMessage());
        		return null;
        	}
        	orderMateriel = orderMaterielService.selectById(orderMateriel.getSerialNum());
    	return orderMateriel;
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
        		System.out.println(e.getMessage());
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
    		System.out.println(e.getMessage());
    		
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
	public ContractVO saveContract(@RequestBody ContractVO contract, HttpServletRequest request) {
		if(contract.getId()==null||contract.getId().isEmpty()){//新增
			contract.setId(ApplicationUtils.random32UUID());
    		Subject currentUser = SecurityUtils.getSubject();
    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
    		contract.setCreator(currenLoginName);
    		contract.setUpdater(currenLoginName);
    		contract.setCreateTime(new Date());
    		contract.setUpdateTime(new Date());

    		orderService.insertContract(contract);
    	}else{//更新
    		Subject currentUser = SecurityUtils.getSubject();
    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
    		contract.setUpdater(currenLoginName);
    		contract.setUpdateTime(new Date());
    		orderService.updateContract(contract);
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
				    		orderInfo.setStatus("1");
				    		
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
				    		orderInfo.setStatus("1");
				    		
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
}
