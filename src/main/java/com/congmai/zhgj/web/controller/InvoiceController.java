package com.congmai.zhgj.web.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import com.alibaba.fastjson.JSON;
import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.core.util.BeanUtils;
import com.congmai.zhgj.core.util.ExcelUtil;
import com.congmai.zhgj.core.util.UserUtil;
import com.congmai.zhgj.web.model.BaseVO;
import com.congmai.zhgj.web.model.CommentVO;
import com.congmai.zhgj.web.model.Company;
import com.congmai.zhgj.web.model.CompanyFinance;
import com.congmai.zhgj.web.model.Invoice;
import com.congmai.zhgj.web.model.InvoiceBillingRecord;
import com.congmai.zhgj.web.model.Materiel;
import com.congmai.zhgj.web.model.OrderInfo;
import com.congmai.zhgj.web.model.PriceList;
import com.congmai.zhgj.web.model.ProcessBase;
import com.congmai.zhgj.web.model.User;
import com.congmai.zhgj.web.service.ClauseSettlementDetailService;
import com.congmai.zhgj.web.service.ClauseSettlementService;
import com.congmai.zhgj.web.service.CompanyFinanceService;
import com.congmai.zhgj.web.service.CompanyService;
import com.congmai.zhgj.web.service.ContractService;
import com.congmai.zhgj.web.service.DeliveryMaterielService;
import com.congmai.zhgj.web.service.IProcessService;
import com.congmai.zhgj.web.service.InvoiceBillingRecordService;
import com.congmai.zhgj.web.service.InvoiceService;
import com.congmai.zhgj.web.service.MaterielService;
import com.congmai.zhgj.web.service.OrderService;
import com.congmai.zhgj.web.service.ProcessBaseService;



/**
 * @ClassName InvoiceController
 * @Description TODO(发票)
 * @author zhaichao
 * @Date 2017年8月30日 下午3:06:43
 * @version 1.0.0
 */
@Controller
@RequestMapping(value = "/invoice")
public class InvoiceController {
	private static final Logger logger = Logger.getLogger(InvoiceController.class);
    @Resource
    private InvoiceService  invoiceService;
    @Autowired
   	private OrderService orderService;
    @Autowired
   	private DeliveryMaterielService  deliveryMaterielService;
    @Resource
	private ContractService contractService;
    @Resource
    private ClauseSettlementService clauseSettlementService;
    @Resource
    private ClauseSettlementDetailService clauseSettlementDetailService;
    @Resource
    private CompanyService  companyService;
    @Resource
    private MaterielService  materielService;
    @Resource
    private InvoiceBillingRecordService invoiceBillingRecordService;
    @Autowired
    protected TaskService taskService;
	@Autowired
	private IProcessService processService;
	 @Resource
	 private ProcessBaseService processBaseService;
	 @Autowired
	 protected RuntimeService runtimeService;
	 @Autowired
		private CompanyFinanceService companyFinanceService;
    
    /**
     * 发票列表展示
     * 
     * @param session
     * @return
     */
    @RequestMapping(value = "/viewInvoiceList")
    public String viewStockInOutCheckList(HttpServletRequest request) {
        return "invoice/invoiceList";
    }
    
    /**
     * 	编辑发票信息详情/新增发票信息
     */
    @RequestMapping(value = "/addOrEditInvoice")
    public String addOrEditCheckInOutInfo( ) {
        return "invoice/addOrEditInvoiceInfo";
    }
    /**
     * 发票详情展示
     * 
     * @param session
     * @return
     */
    @RequestMapping(value = "/viewInvoice")
    public String viewStockInOutCheck(HttpServletRequest request) {
        return "invoice/viewInvoiceInfo";
    }
    /**
     * 保存发票信息
     * 
     */
    @RequestMapping(value = "/saveInvoiceInfo", method = RequestMethod.POST)
	public ResponseEntity<Invoice> saveInvoiceInfo(@RequestBody String  params,UriComponentsBuilder ucBuilder){//
    	Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
		Invoice  invoice=JSON.parseObject(params, Invoice.class);
    	try{
    		if(StringUtils.isEmpty(invoice.getSerialNum())){
    			invoice.setSerialNum(ApplicationUtils.random32UUID());
    			invoice.setStatus("0");//待确认
    			invoiceService.insert(invoice);
    		}else{
    			invoiceService.update(invoice);
    			if("2".equals(invoice.getStatus())){
    				OrderInfo orderInfo=new OrderInfo();
    				if(StringUtils.isEmpty(invoice.getSupplyComId())){
    					orderInfo.setBillStatus(OrderInfo.BILL);
    				}else{
    					orderInfo.setBillStatus(OrderInfo.RECIVEBILL);
    				}
    				orderService.updateStatus(orderInfo);
    			}
    		}
    		
    	}catch(Exception e){
    		System.out.println(e.getMessage());
    	}
		return new ResponseEntity<Invoice>(invoice, HttpStatus.OK);
    }
   /* *//**
     * 判断发票检验信息是否已经存在
     * 
     *//*
    @RequestMapping(value = "/InvoiceIsExist", method = RequestMethod.POST)
	public ResponseEntity<String> stockInOutCheckIsExist(@RequestBody String  serialNum,UriComponentsBuilder ucBuilder){//
    String flag="0";
    List<StockInOutCheck>stockInOutChecks=null;
    	try{
    		if(serialNum.indexOf("in")>-1){
    			stockInOutChecks=stockInOutCheckService.getAllStockInOutCheck("in", serialNum.substring(0, 32));
    		}else{
    			stockInOutChecks=stockInOutCheckService.getAllStockInOutCheck("out", serialNum.substring(0, 32));
    		}
    		if(stockInOutChecks.size()>0){
    			 flag="1";
    		}
    	}catch(Exception e){
    		System.out.println(e.getMessage());
    	}
		return new ResponseEntity<String>(flag, HttpStatus.OK);
    }*/
    /**
     * 获取发票检验列表
     * 
     */
    @RequestMapping(value = "/getInvoiceList")
    public ResponseEntity<Map> getInvoiceList(HttpServletRequest request,String  inOrOut) {
    	
		List<Invoice> invoices = invoiceService.getAllInvoice(inOrOut,null);
		for(Invoice invoice:invoices){
			OrderInfo order=orderService.selectById(invoice.getOrderSerial());
			invoice.setRelationBuyOrSaleNum(order.getOrderNum());
		}
       
		// 封装datatables数据返回到前台
		Map<String,Object> pageMap = new HashMap<String,Object>();
		pageMap.put("draw", 1);
		pageMap.put("recordsTotal",  invoices==null?0:invoices.size());
		pageMap.put("recordsFiltered",  invoices==null?0:invoices.size());
		pageMap.put("data", invoices);
		return new ResponseEntity<Map>(pageMap, HttpStatus.OK);
	}
    /**
     * @Description 根据serialNum获取发票详情
     * @param request
     * @return
     */
  
    @RequestMapping(value = "/invoiceView")
    @ResponseBody
    public Map viewInvoice(HttpServletRequest request, @RequestBody String  serialNum) {
    	Map<String, Object> map = new HashMap<String, Object>();
    	Invoice invoice=invoiceService.selectById(serialNum.substring(0, 32));
    	 invoice=invoiceService.getDetailInfo(invoice);//统计数据
    	if(invoice!=null){
    	OrderInfo orderInfo=orderService.selectById(invoice.getOrderSerial());
    		invoice.setRelationBuyOrSaleNum(orderInfo.getOrderNum());
    		invoice.setOrderAmount(orderInfo.getOrderAmount());
    		invoice.setComName(invoice.getSupplyComId()==null?invoice.getBuyComId():invoice.getSupplyComId());
    		map.put("invoice",invoice);
    	
    }
    	return map;
    }
    
    /**
	 * 
	 * @Description 批量删除发票信息
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/deleteInvoice", method = RequestMethod.POST)
	public ResponseEntity<Void> deleteInvoice(@RequestBody String serialNums) {
		if ("".equals(serialNums) || serialNums == null) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		invoiceService.deleteInvoice(serialNums);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
		/**
	     * @Description (导出发票检验信息)
	     * @param request
	     * @return
	     */
	    @RequestMapping("exportInvoice")
	    @ResponseBody
	    public void exportStockInOutCheck(Map<String, Object> map,HttpServletRequest request,HttpServletResponse response,String  inOrOut) {
	    		Map<String, Object> dataMap = new HashMap<String, Object>();
	    		List<Invoice> invoices = invoiceService.getAllInvoice(inOrOut,null);
	    		for(Invoice invoice:invoices){
	    			OrderInfo order=orderService.selectById(invoice.getOrderSerial());
	    			invoice.setRelationBuyOrSaleNum(order.getOrderNum());
	    		}
	    		dataMap.put("invoiceList",invoices);
	    		if("in".equals(inOrOut)){
		    		ExcelUtil.export(request, response, dataMap, "invoiceIn", "进项票信息");
		    		}else{
		    			ExcelUtil.export(request, response, dataMap, "invoiceOut", "销项票信息");
		    		}
	    }
	@RequestMapping(value = "/getOrderInfoBySerialNum",method = RequestMethod.POST)
	@ResponseBody
	public Map getOrderMaterielInfo( @RequestBody String serialNum) {
		OrderInfo orderInfo =null;
		Company company=null;
		List<CompanyFinance> companyFinanceList=null;
		Map<String, Object> map = new HashMap<String, Object>();
		if(serialNum.indexOf("in")>-1){//进项票
			orderInfo = orderService.selectById(serialNum.substring(0, 32));
		}else if(serialNum.indexOf("out")>-1){//销项票
			orderInfo = orderService.selectById(serialNum.substring(0, 32));
			company=companyService.selectById(orderInfo.getBuyComId());
			companyFinanceList=companyFinanceService.selectListByComId(company.getComId());
		}else{
			orderInfo= orderService.selectById(serialNum);
		}
		
		map.put("orderInfo", orderInfo);
		map.put("company",company);
		map.put("companyFinanceList",companyFinanceList);
    	
    	return map;
	}
	/**
     * 获取发票物料列表
     * 
     */
    @RequestMapping(value = "/getMaterielList")
    public ResponseEntity<Map> getMaterielList(HttpServletRequest request, String  orderSerial) {
    	
		List<Materiel> materiels = materielService.selectMaterielByOrderSerial(orderSerial.substring(0, 32),orderSerial);
		OrderInfo orderInfo=orderService.selectById(orderSerial.substring(0, 32));
		for(Materiel materiel:materiels){
			materiel.setMoney(new BigDecimal(materiel.getOrderUnitPrice()).multiply(new BigDecimal(materiel.getBillAmount()).setScale(2,BigDecimal.ROUND_HALF_UP )).toString());
			
		}
		// 封装datatables数据返回到前台
		Map<String,Object> pageMap = new HashMap<String,Object>();
		pageMap.put("draw", 1);
		pageMap.put("recordsTotal",  materiels==null?0:materiels.size());
		pageMap.put("recordsFiltered",  materiels==null?0:materiels.size());
		pageMap.put("data", materiels);
		return new ResponseEntity<Map>(pageMap, HttpStatus.OK);
	}
    @RequestMapping(value = "/saveInvoiceBillingRecordInfo", method = RequestMethod.POST)
   	public ResponseEntity<InvoiceBillingRecord> saveInvoiceBillingRecordInfo(@RequestBody InvoiceBillingRecord  invoiceBillingRecord,UriComponentsBuilder ucBuilder){//
       	Subject currentUser = SecurityUtils.getSubject();
   		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
   		invoiceBillingRecord.setCreator(currenLoginName);
       	try{
       		if("null".equals(invoiceBillingRecord.getSerialNum())){
       			invoiceBillingRecord.setSerialNum(ApplicationUtils.random32UUID());
       			invoiceBillingRecordService.insert(invoiceBillingRecord);
       		}else{
       			invoiceBillingRecordService.update(invoiceBillingRecord);
       		}
       		
       	}catch(Exception e){
       		System.out.println(e.getMessage());
       	}
   		return new ResponseEntity<InvoiceBillingRecord>(invoiceBillingRecord, HttpStatus.OK);
       }
    
    private Invoice  json2Invoice(String params) {
		params = params.replace("\\", "");
		ObjectMapper objectMapper = new ObjectMapper();  
		Invoice invoice = new Invoice();
		try {
			invoice = objectMapper.readValue(params, Invoice.class);
		}catch (Exception e1) {
			e1.printStackTrace();
		}
		return invoice;
	}
    /**
     * 
     * @Description (启动发票流程)
     * @param orderInfo
     * @return
     */
    @RequestMapping(value = "/startInvoiceProcess", method = RequestMethod.POST)
    @ResponseBody
	private String startInvoiceProcess(@RequestBody String params) {
    	String flag = "0"; //默认失败
    	Invoice invoice = json2Invoice(params);
    	invoiceService.update(invoice);//更新理由
    	
		//启动发票审批测试流程-start
		User user = UserUtil.getUserFromSession();
		invoice.setUserId(user.getUserId());
		invoice.setUser_name(user.getUserName());
		invoice.setTitle(user.getUserName()+" 的销项票申请");
		invoice.setBusinessType(BaseVO.OUTINVOICE); 	
		invoice.setStatus(BaseVO.PENDING);					//审批中
		invoice.setApplyDate(new Date());
		invoice.setReason(invoice.getReason());
    	processBaseService.insert(invoice);
		String businessKey = invoice.getSerialNum().toString();
		invoice.setBusinessKey(businessKey);
		try {
			String processInstanceId = this.processService.startInvoice(invoice);
//                message.setStatus(Boolean.TRUE);
//    			message.setMessage("请假流程已启动，流程ID：" + processInstanceId);
		    logger.info("processInstanceId: "+processInstanceId);
		    flag = "1";
		} catch (ActivitiException e) {
//            	message.setStatus(Boolean.FALSE);
		    if (e.getMessage().indexOf("no processes deployed with key") != -1) {
		        logger.warn("没有部署流程!", e);
//        			message.setMessage("没有部署流程，请联系系统管理员，在[流程定义]中部署相应流程文件！");
		    } else {
		        logger.error("启动价格流程失败：", e);
//                    message.setMessage("启动请假流程失败，系统内部错误！");
		    }
		    throw e;
		} catch (Exception e) {
		    logger.error("启动价格流程失败：", e);
//                message.setStatus(Boolean.FALSE);
//                message.setMessage("启动请假流程失败，系统内部错误！");
		    throw e;
		}
        //启动发票审批测试流程-end
		return flag;
	}
    
    
    
    
    /**
     * 审批发票流程
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
		Invoice invoice = (Invoice) this.runtimeService.getVariable(pi.getId(), "entity");
		invoice.setTask(task);
		invoice.setProcessInstanceId(processInstanceId);
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
		map.put("invoice", invoice);
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
    		@RequestParam("invoiceId") String invoiceId,
    		@RequestParam("content") String content,
    		@RequestParam("processInstanceId") String processInstanceId,
    		@RequestParam("completeFlag") Boolean completeFlag,
    		@PathVariable("taskId") String taskId,
    		RedirectAttributes redirectAttributes) throws Exception{
    	User user = UserUtil.getUserFromSession();
    	String result = "";
    	try {
    		Invoice invoice = this.invoiceService.selectById(invoiceId);
    		Invoice baseInvoice = (Invoice) this.runtimeService.getVariable(processInstanceId, "entity");
    		Map<String, Object> variables = new HashMap<String, Object>();
    		variables.put("isPass", completeFlag);
    		if(!completeFlag){
    			baseInvoice.setTitle(baseInvoice.getUser_name()+" 的销项票申请失败,需修改后重新提交！");
    			invoice.setStatus(BaseVO.APPROVAL_FAILED);
    			variables.put("entity", baseInvoice);
    		}else{
    			invoice.setStatus(BaseVO.PENDING);					//审批中
    		}
    		// 完成任务
    		this.processService.complete(taskId, content, user.getUserId().toString(), variables);
    		
    		if(completeFlag){
    			//此处需要修改，不能根据人来判断审批是否结束。应该根据流程实例id(processInstanceId)来判定。
    			//判断指定ID的实例是否存在，如果结果为空，则代表流程结束，实例已被删除(移到历史库中)
    			ProcessInstance pi = this.runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
    			if(BeanUtils.isBlank(pi)){
    				invoice.setStatus(BaseVO.APPROVAL_SUCCESS);
    			}
    		}
    		
    		this.processBaseService.update(invoice);
    		
    		if(BaseVO.APPROVAL_SUCCESS.equals(invoice.getStatus())){//发票完成，需更新状态为1(发票待接收)
    			Invoice invoiceori = new Invoice();
    			invoiceori.setSerialNum(invoice.getSerialNum());
    			invoiceori.setStatus("1");
    			this.invoiceService.update(invoiceori);
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
	 * 调整请假申请
	 * @param vacation
	 * @param taskId
	 * @param processInstanceId
	 * @param reApply
	 * @param session
	 * @return
	 * @throws Exception
	 */
//	@RequiresPermissions("user:vacation:modify")
	@RequestMapping(value = "/modifyInvoice/{taskId}", method = RequestMethod.POST, produces = "application/text;charset=UTF-8")
	@ResponseBody
	public String modifyInvoice(
			@PathVariable("taskId") String taskId,
			@RequestParam("processInstanceId") String processInstanceId,
			@RequestParam("isPass") Boolean isPass,
			@RequestParam("invoiceId") String invoiceId,
			@RequestParam("reason") String reason) throws Exception{
		String result = "";
		User user = UserUtil.getUserFromSession();
		Invoice invoice = new Invoice();
		invoice.setSerialNum(invoiceId);
        Map<String, Object> variables = new HashMap<String, Object>();
        invoice.setUserId(user.getUserId());
        invoice.setUser_name(user.getUserName());
        invoice.setApplyDate(new Date());
        invoice.setBusinessKey(invoiceId);
        invoice.setProcessInstanceId(processInstanceId);
        String content = "";
        if(isPass){
        	//修改销项票申请
        	invoice.setTitle(user.getUserName()+" 的销项票申请！");
        	invoice.setBusinessType(BaseVO.OUTINVOICE);
        	content = "重新申请销项票";
 		     result = "任务办理完成，销项票申请已重新提交！";
        	invoice.setStatus(BaseVO.PENDING);
	       
        }else{
        	invoice.setTitle(user.getUserName()+" 的销项票申请已取消！");
        	invoice.setBusinessType(BaseVO.OUTINVOICE);
        	content = "取消申请销项票";
 		    result = "任务办理完成，已经取消您的销项票申请！";
        	invoice.setStatus(BaseVO.APPROVAL_FAILED);
        }
        try {
    		this.processBaseService.update(invoice);
			variables.put("entity", invoice);
			variables.put("isPass", isPass);
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
	////判断销项票是否审批通过
	@RequestMapping(value = "/judgeIsApproval", method = RequestMethod.POST)
	@ResponseBody
	public String judgeIsApproval( @RequestBody String serialNum ) throws Exception{
		String result = "";
		Invoice invoice = invoiceService.selectById(serialNum.substring(0, 32));
		if (serialNum.indexOf("out") > -1) {// 销项票确认
			if ("2".equals(invoice.getStatus())) {
				result = "0";// 已开票
			} else {
				ProcessBase processBase = processBaseService
						.selectBaseById(serialNum);
				if (processBase == null) {
					result = "1";// 未申请审批
				} else if ("APPROVAL_SUCCESS".equals(processBase.getStatus())) {
					result = "2";// 审批通过
				} else {
					result = "3";// 还需审批
				}
			}
		} else {// 进项票确认
			if ("2".equals(invoice.getStatus())) {
				result = "0";// 已收票
			} else {
				result = "2";// 可以确认收票
			}
		}
    	return result;
    }
}
