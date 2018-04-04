package com.congmai.zhgj.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.print.DocFlavor.STRING;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import net.sf.json.JSONObject;
import net.sf.json.util.NewBeanInstanceStrategy;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
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
import org.springframework.web.util.UriComponentsBuilder;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.core.util.BeanUtils;
import com.congmai.zhgj.core.util.ExcelUtil;
import com.congmai.zhgj.core.util.MessageConstants;
import com.congmai.zhgj.core.util.StringUtil;
import com.congmai.zhgj.core.util.UserUtil;
import com.congmai.zhgj.web.enums.StaticConst;
import com.congmai.zhgj.web.event.EventExample;
import com.congmai.zhgj.web.event.SendMessageEvent;
import com.congmai.zhgj.web.model.BaseVO;
import com.congmai.zhgj.web.model.ClauseSettlement;
import com.congmai.zhgj.web.model.ClauseSettlementDetail;
import com.congmai.zhgj.web.model.CommentVO;
import com.congmai.zhgj.web.model.CompanyContact;
import com.congmai.zhgj.web.model.CompanyFinance;
import com.congmai.zhgj.web.model.ContractVO;
import com.congmai.zhgj.web.model.CustomsForm;
import com.congmai.zhgj.web.model.DeliveryMateriel;
import com.congmai.zhgj.web.model.DeliveryVO;
import com.congmai.zhgj.web.model.MemoRecord;
import com.congmai.zhgj.web.model.OrderInfo;
import com.congmai.zhgj.web.model.OrderMaterielExample;
import com.congmai.zhgj.web.model.PaymentFile;
import com.congmai.zhgj.web.model.PaymentRecord;
import com.congmai.zhgj.web.model.RelationFile;
import com.congmai.zhgj.web.model.StockOutBatch;
import com.congmai.zhgj.web.model.TakeDeliveryParams;
import com.congmai.zhgj.web.model.TakeDeliveryVO;
import com.congmai.zhgj.web.model.User;
import com.congmai.zhgj.web.model.Vacation;
import com.congmai.zhgj.web.model.VerificationRecord;
import com.congmai.zhgj.web.service.CompanyContactService;
import com.congmai.zhgj.web.service.CompanyFinanceService;
import com.congmai.zhgj.web.service.CompanyService;
import com.congmai.zhgj.web.service.ContractService;
import com.congmai.zhgj.web.service.CustomsFormService;
import com.congmai.zhgj.web.service.IProcessService;
import com.congmai.zhgj.web.service.OrderService;
import com.congmai.zhgj.web.service.PayService;

/**
 * 付款管理controller
 * 
 * @author czw
 *
 */
@Component
@Scope("prototype")
@Controller
@RequestMapping("/pay")
public class PayController {
	private static final Logger logger = Logger.getLogger(PayController.class);

	/**
	 * 合同管理service
	 */
	@Resource
	private ContractService contractService;

	/**
	 * 订单service
	 */
	@Resource
	private OrderService orderService;

	/**
	 * 付款service
	 */
	@Resource
	private PayService payService;

	@Autowired
	protected RuntimeService runtimeService;

	@Autowired
	protected IdentityService identityService;

	@Autowired
	protected TaskService taskService;

	@Autowired
	private IProcessService processService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private CompanyFinanceService companyFinanceService;
	@Autowired
	private CustomsFormService customsFormService;
	@Autowired
	private CompanyContactService companyContactService;
	@Autowired  
	Environment env;
	/**
	 * 
	 * @Description 启动应付款流程审批
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/apply", method = RequestMethod.POST)
	@ResponseBody
	public String apply(@RequestBody JSONObject entity) throws Exception {
		User user = UserUtil.getUserFromSession();
		Map map = JSONObject.fromObject(entity);
		String reason = (String) map.get("reason");
		String serialNum = (String) map.get("serialNum");

		PaymentRecord paymentRecord = payService.selectPayById(serialNum);
		paymentRecord.setUserId(user.getUserId());
		paymentRecord.setUser_name(user.getUserName());
		paymentRecord.setTitle(user.getUserName() + " 的应付款申请");
		paymentRecord.setBusinessType(BaseVO.ACCOUNTPAYABLE);
		paymentRecord.setStatus(BaseVO.PENDING);
		paymentRecord.setApplyDate(new Date());
		paymentRecord.setBusinessKey(serialNum);
		paymentRecord.setReason(reason);

		try {
			String processInstanceId = this.processService
					.startAccountPayable(paymentRecord);
			// message.setStatus(Boolean.TRUE);
			// message.setMessage("请假流程已启动，流程ID：" + processInstanceId);
			logger.info("processInstanceId: " + processInstanceId);
		} catch (ActivitiException e) {
			// message.setStatus(Boolean.FALSE);
			if (e.getMessage().indexOf("no processes deployed with key") != -1) {
				logger.warn("没有部署流程!", e);
				// message.setMessage("没有部署流程，请联系系统管理员，在[流程定义]中部署相应流程文件！");
			} else {
				logger.error("启动请假流程失败：", e);
				// message.setMessage("启动请假流程失败，系统内部错误！");
			}
			throw e;
		} catch (Exception e) {
			logger.error("启动请假流程失败：", e);
			// message.setStatus(Boolean.FALSE);
			// message.setMessage("启动请假流程失败，系统内部错误！");
			throw e;
		}
		return null;
	}

	/**
	 * 审批请假流程
	 * 
	 * @param taskId
	 * @param model
	 * @return
	 * @throws NumberFormatException
	 * @throws Exception
	 */
	@RequestMapping(value = "/toApproval/{taskId}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody Map<String, Object> toApproval(
			@PathVariable("taskId") String taskId)
			throws NumberFormatException, Exception {
		Task task = this.taskService.createTaskQuery().taskId(taskId)
				.singleResult();
		// 根据任务查询流程实例
		String processInstanceId = task.getProcessInstanceId();
		ProcessInstance pi = this.runtimeService.createProcessInstanceQuery()
				.processInstanceId(processInstanceId).singleResult();
		PaymentRecord paymentRecord = (PaymentRecord) this.runtimeService
				.getVariable(pi.getId(), "entity");
		paymentRecord.setTask(task);
		paymentRecord.setProcessInstanceId(processInstanceId);
		List<CommentVO> commentList = this.processService
				.getComments(processInstanceId);
		String taskDefinitionKey = task.getTaskDefinitionKey();
		logger.info("taskDefinitionKey: " + taskDefinitionKey);
		String result = null;
		if ("modifyApply".equals(taskDefinitionKey)) {
			result = "modify";
		} else {
			result = "audit";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("actionType", result);
		map.put("paymentRecord", paymentRecord);
		map.put("commentList", commentList);
		return map;
	}

	@RequestMapping(value = "/complete", method = RequestMethod.POST, produces = "application/text;charset=UTF-8")
	@ResponseBody
	public String complete(@RequestParam("serialNum") String serialNum,
			@RequestParam("content") String content,
			@RequestParam("isPass") Boolean completeFlag,
			@RequestParam("taskId") String taskId,
			RedirectAttributes redirectAttributes) throws Exception {
		User user = UserUtil.getUserFromSession();
		String result = "";
		try {
			PaymentRecord paymentRecord = this.payService
					.selectPayById(serialNum);
			PaymentRecord basePaymentRecord = (PaymentRecord) this.runtimeService
					.getVariable(paymentRecord.getProcessInstanceId(), "entity");
			Map<String, Object> variables = new HashMap<String, Object>();
			variables.put("isPass", completeFlag);
			if (!completeFlag) {
				basePaymentRecord.setTitle(basePaymentRecord.getUser_name()
						+ " 的应付款申请失败,需修改后重新提交！");
				paymentRecord.setStatus(BaseVO.APPROVAL_FAILED);
				variables.put("entity", basePaymentRecord);
			}
			// 完成任务
			this.processService.complete(taskId, content, user.getUserId()
					.toString(), variables);

			if (completeFlag) {
				// 此处需要修改，不能根据人来判断审批是否结束。应该根据流程实例id(processInstanceId)来判定。
				// 判断指定ID的实例是否存在，如果结果为空，则代表流程结束，实例已被删除(移到历史库中)
				ProcessInstance pi = this.runtimeService
						.createProcessInstanceQuery()
						.processInstanceId(paymentRecord.getProcessInstanceId())
						.singleResult();
				if (BeanUtils.isBlank(pi)) {
					paymentRecord.setStatus(BaseVO.APPROVAL_SUCCESS);
					//修改订单状态
					this.payService.updateOrderStatus(paymentRecord);
					//发消息给付款制单人通知审批通过
					 EventExample.getEventPublisher().publicSendMessageEvent(new SendMessageEvent(paymentRecord,MessageConstants.BE_CONFIRM_PAY));
				}
			}

			this.payService.updatePaymentRecord(paymentRecord);

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
	 * 
	 * @Description 查找所有用户付款信息
	 * @return
	 */
	@RequestMapping(value = "/findAllPaymentRecord", method = RequestMethod.GET)
	public ResponseEntity<Map> findAllUserContract(HttpServletRequest request) {

		Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();// 获取当前登录用户名
		List<PaymentRecord> paymentRecordlist = payService
				.selectAllPaymentRecordList(currenLoginName);
		

		// 封装datatables数据返回到前台
		Map pageMap = new HashMap();
		pageMap.put("draw", 1);
		pageMap.put("recordsTotal", paymentRecordlist == null ? 0
				: paymentRecordlist.size());
		pageMap.put("recordsFiltered", paymentRecordlist == null ? 0
				: paymentRecordlist.size());
		pageMap.put("data", paymentRecordlist);
		return new ResponseEntity<Map>(pageMap, HttpStatus.OK);
	}

	/**/
	/**
	 * 
	 * @Description 查找所有用户收款信息
	 * @return
	 */
	@RequestMapping(value = "/findAllGatheringMoneyRecord", method = RequestMethod.GET)
	public ResponseEntity<Map> findAllGatheringMoneyRecord(
			HttpServletRequest request) {

		Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();// 获取当前登录用户名
		List<PaymentRecord> paymentRecordlist = payService
				.findAllGatheringMoneyRecord(currenLoginName);

		// 封装datatables数据返回到前台
		Map pageMap = new HashMap();
		pageMap.put("draw", 1);
		pageMap.put("recordsTotal", paymentRecordlist == null ? 0
				: paymentRecordlist.size());
		pageMap.put("recordsFiltered", paymentRecordlist == null ? 0
				: paymentRecordlist.size());
		pageMap.put("data", paymentRecordlist);
		return new ResponseEntity<Map>(pageMap, HttpStatus.OK);
	}

	/**
	 * 
	 * @Description 获取采购/销售订单信息
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/getSaleOrderInfo")
	@ResponseBody
	public Map getSaleOrderInfo(String serialNum, OrderInfo orderInfo) {
		orderInfo = orderService.selectById(serialNum);
		Boolean createBG=StaticConst.getInfo("waimao").equals(orderInfo.getTradeType())&&StringUtils.isEmpty(orderInfo.getSupplyComId());//该订单是否需要报关
		Boolean createQG=StaticConst.getInfo("waimao").equals(orderInfo.getTradeType())&&!StringUtils.isEmpty(orderInfo.getSupplyComId());//该订单是否需要清关
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("createBG", createBG);
		map.put("createQG", createQG);
		if(createBG){
			
		}
		//获取平台收付款信息
		List<CompanyFinance>comFinances=new ArrayList<CompanyFinance>(); 
		if(orderInfo.getOrderType().indexOf(StaticConst.getInfo("xiaoshou"))>-1){
			String comId=companyService.selectComIdByComName(StaticConst.getInfo("comName"));
			comFinances=companyFinanceService.selectListByComId(comId);
		}else{
			comFinances=companyFinanceService.selectListByComId(orderInfo.getSupplyComId());
		}
		map.put("comFinances", comFinances);
		List<CompanyContact>comContacts=null;
		if(StringUtil.isEmpty(orderInfo.getBuyComId())){
			comContacts=companyContactService.selectListByComId(orderInfo.getSupplyComId());
			map.put("comContacts", comContacts);
		}else{
			comContacts=companyContactService.selectListByComId(orderInfo.getBuyComId());
			map.put("comContacts", comContacts);
		}
		
		String paiedMoney = payService.selectPaiedMoney(serialNum);
		orderInfo.setPaiedMoney(paiedMoney);
		String billedMoney = payService.selectBilledMoney(serialNum);
		orderInfo.setBilledMoney(billedMoney);
		map.put("orderInfo", orderInfo);

		OrderMaterielExample m = new OrderMaterielExample();
		// and 条件1
		com.congmai.zhgj.web.model.OrderMaterielExample.Criteria criteria = m
				.createCriteria();
		criteria.andDelFlgEqualTo("0");
		criteria.andOrderSerialEqualTo(serialNum);
		/*
		 * List<DeliveryMaterielVO> orderMateriel =
		 * deliveryService.selectList(serialNum);
		 */
		List<ClauseSettlementDetail> clauList = payService
				.selectClauseSettlementDetailList(serialNum);
		map.put("clauList", clauList);
		
		List<ClauseSettlementDetail> clauseSettlementDetail = payService
				.selectClauseSettlementDetailList2(serialNum);
		//获取所有已经新建的该订单的应收/应付款节点信息
		List<PaymentRecord>paymentRecords=payService.findPaymentRecordList(serialNum);
		if(org.apache.commons.collections.CollectionUtils.isNotEmpty(paymentRecords)){
			for(PaymentRecord p:paymentRecords){
				for(ClauseSettlementDetail csd:clauseSettlementDetail){
					if(p.getPaymentNode().equals(csd.getDeliveryNode())){
						clauseSettlementDetail.remove(csd);
						break;
					}
				}
			}
		}
		map.put("clauseSettlementDetail", clauseSettlementDetail);
		//获取当前订单已建的收付款信息
		List<PaymentRecord>list=payService.findPaymentRecordList(serialNum);
		BigDecimal total=BigDecimal.ZERO;
		for(PaymentRecord p:list){
			total=total.add(new BigDecimal(p.getDeliveryRate()));
		}
		map.put("currentTotalRate", total);
		return map;
	}
	
	
	/**
	 * 
	 * @Description 调整应付款申请
	 * @param taskId
	 * @param reApply
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/modifyApplyAp", method = RequestMethod.POST, produces = "application/text;charset=UTF-8")
	@ResponseBody
	public String modifyApplyAp(
			@RequestParam("taskId") String taskId,
			@RequestParam("reApply") Boolean reApply,
			@Valid PaymentRecord record) throws Exception{
		
		String result = "";
		User user = UserUtil.getUserFromSession();		
		record.setUserId(user.getUserId());
		record.setUser_name(user.getUserName());
		record.setBusinessType(BaseVO.ACCOUNTPAYABLE);
		record.setApplyDate(new Date());
		record.setBusinessKey(record.getSerialNum());
		record.setProcessInstanceId(record.getProcessInstanceId());
        Map<String, Object> variables = new HashMap<String, Object>();
        String content = "";
        if(reApply){
        	//修改请假申请
        	record.setTitle(user.getUserName()+" 的应付款申请！");
        	record.setStatus(BaseVO.PENDING);
	        content = "重新申请";
	        //由userTask自动分配审批权限
//	        if(vacation.getDays() <= 3){
//            	variables.put("auditGroup", "manager");
//            }else{
//            	variables.put("auditGroup", "director");
//            }
	        result = "任务办理完成，应付款申请已重新提交！";
        }else{
        	record.setTitle(user.getUserName()+" 的应付款申请已取消！");
        	record.setStatus(BaseVO.APPROVAL_FAILED);
        	content = "取消申请";
        	result = "任务办理完成，已经取消您的应付款申请！";
        }
        try {
			this.payService.updatePaymentRecord(record);
			variables.put("entity", record);
			variables.put("reApply", reApply);
			this.processService.complete(taskId, content, user.getUserId().toString(), variables);
			
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
	 * 保存用户付款信息
	 * 
	 * @param record
	 *            （付款对象）
	 * @param request
	 *            （http 请求对象）
	 * @param ucBuilder
	 * @return 操作结果
	 */
	@RequestMapping(value = "/savePaymentRecord", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<PaymentRecord> savePaymentRecord(
			@Valid PaymentRecord record, HttpServletRequest request,
			UriComponentsBuilder ucBuilder, MultipartFile file) {
		Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();// 获取当前登录用户名

		String paymentVoucher = null;

		if (file != null) {
			paymentVoucher = uploadFile(file);
			record.setPaymentVoucher(paymentVoucher);
		}
			// 如果id为空执行保存
			if (StringUtils.isEmpty(record.getSerialNum())) {

				String serialNum = ApplicationUtils.random32UUID();
				record.setSerialNum(serialNum);
				record.setCreator(currenLoginName);
				payService.insertPaymentRecord(record);
				record = payService.selectPayById(record.getSerialNum());
				/*if(!StringUtils.isEmpty(record.getSupplyComId())){
					//新增付款后给供应商发送收款消息
					EventExample.getEventPublisher().publicSendMessageEvent(new SendMessageEvent(record,MessageConstants.SHOUKUAN));
				}
				
				if(!StringUtils.isEmpty(record.getBuyComId())){
					//新增收款后给采购商发送收款消息
					EventExample.getEventPublisher().publicSendMessageEvent(new SendMessageEvent(record,MessageConstants.FUKUAN));
				}*/
				
			} else {
				// 如果id不为空执行更新

				record.setUpdater(currenLoginName);
				// 更新付款记录
				payService.updatePaymentRecord(record);
			}

			record = payService.selectPayById(record.getSerialNum());
			String orderSerial = record.getOrderSerial();
			/*OrderInfo o=orderService.selectById(orderSerial);
			record.setOrderDate(o.getOrderDate());*/
			String paiedMoney = payService.selectPaiedMoney(orderSerial);
			String billedMoney = payService.selectBilledMoney(orderSerial);
			record.setPaiedMoney(paiedMoney);
			record.setBilledMoney(billedMoney);
			if(StringUtil.isNotEmpty(record.getCustomsFormSerial())){
				CustomsForm customsForm =customsFormService.selectById(record.getCustomsFormSerial());
				record.setQgOrBgNum(customsForm.getCustomsFormNum());
				record.setAddedTax(customsForm.getAddedTax());
				record.setCustomsAmount(customsForm.getCustomsAmount());
				record.setRate(customsForm.getOrderInfo().getRate());
			}
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/paymentRecordC")
					.buildAndExpand(record.getSerialNum()).toUri());
		return new ResponseEntity<PaymentRecord>(record, HttpStatus.CREATED);

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
		JavaType javaType = objectMapper.getTypeFactory()
				.constructParametricType(List.class, PaymentFile.class);
		List<PaymentFile> file;
		try {
			file = objectMapper.readValue(params, javaType);
			if (!CollectionUtils.isEmpty(file)) {
				Subject currentUser = SecurityUtils.getSubject();
				String currenLoginName = currentUser.getPrincipal().toString();// 获取当前登录用户名
				for (PaymentFile f : file) {
					f.setSerialNum(ApplicationUtils.random32UUID());
					f.setUploader(currenLoginName);
					f.setCreator(currenLoginName);
					f.setUpdater(currenLoginName);
				}
				// 填充File******↑↑↑↑↑↑********
				payService.betchInsertPaymentFiles(file);
				// 数据插入******↑↑↑↑↑↑********
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @Description 更新附件
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/updateFile", method = RequestMethod.POST)
	@ResponseBody
	public void updateFile(@RequestBody String params) {
		params = params.replace("\\", "");
		ObjectMapper objectMapper = new ObjectMapper();
		JavaType javaType = objectMapper.getTypeFactory()
				.constructParametricType(List.class, PaymentFile.class);
		List<PaymentFile> file;
		try {
			file = objectMapper.readValue(params, javaType);
			if(file.size()>0){
				String paySerialNum = file.get(0).getPaymentSerial();
				payService.deleteFileOld(paySerialNum);	
			}
			if (!CollectionUtils.isEmpty(file)) {
				Subject currentUser = SecurityUtils.getSubject();
				String currenLoginName = currentUser.getPrincipal().toString();// 获取当前登录用户名
				for (PaymentFile f : file) {
					f.setSerialNum(ApplicationUtils.random32UUID());
					f.setUploader(currenLoginName);
					f.setCreator(currenLoginName);
					f.setUpdater(currenLoginName);
				}
				// 填充File******↑↑↑↑↑↑********
				payService.betchInsertPaymentFiles(file);
				// 数据插入******↑↑↑↑↑↑********
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 查询结算条款详情
	 * 
	 * @param clauseSettlement
	 *            （结算条款对象）
	 * @param serialNum
	 *            （结算条款的id）
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "selectClauseDetail")
	@ResponseBody
	public ResponseEntity<ClauseSettlement> selectClauseDetail(
			@Valid ClauseSettlement clauseSettlement, String serialNum,
			HttpServletRequest request) {
		try {
			clauseSettlement = payService.selectClauseDetail(clauseSettlement
					.getSerialNum());
		} catch (Exception e) {
			//20180110 qhzhao System.out.println(e.getMessage());
			return null;
		}
		return new ResponseEntity<ClauseSettlement>(clauseSettlement,
				HttpStatus.CREATED);
	}

	/**
	 * @Description (导出付款信息)
	 * @param request
	 * @return
	 */
	@RequestMapping("exportPay")
	public void exportPay(Map<String, Object> map, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();// 获取当前登录用户名
		List<PaymentRecord> paymentRecordlist = payService
				.selectAllPaymentRecordList(currenLoginName);
		for (PaymentRecord paymentRecord : paymentRecordlist) {
			if ("1".equals(paymentRecord.getBillStyle())) {
				paymentRecord.setBillStyle(PaymentRecord.XPHK);
			} else {
				paymentRecord.setBillStyle(PaymentRecord.XKHP);
			}
			if ("1".equals(paymentRecord.getIsBill())) {
				paymentRecord.setIsBill("是");
			} else {
				paymentRecord.setIsBill("否");
			}
			if ("0".equals(paymentRecord.getStatus())) {
				paymentRecord.setStatus("未审批");
			}else if("1".equals(paymentRecord.getStatus())) {
				paymentRecord.setStatus("部分核销");
			}else if("2".equals(paymentRecord.getStatus())) {
				paymentRecord.setStatus("已完成");
			}else if("PENDING".equals(paymentRecord.getStatus())){
				paymentRecord.setStatus("审批中");
			}else if("WAITING_FOR_APPROVAL".equals(paymentRecord.getStatus())){
				paymentRecord.setStatus("待审批");
			}else if("APPROVAL_SUCCESS".equals(paymentRecord.getStatus())){
				paymentRecord.setStatus("审批成功");
			}else if("APPROVAL_FAILED".equals(paymentRecord.getStatus())){
				paymentRecord.setStatus("审批失败");
			}

		}
		dataMap.put("paymentRecordlist", paymentRecordlist);
		ExcelUtil.export(request, response, dataMap, "pay", "付款信息");
	}

	/**
	 * @Description (导出收款信息)
	 * @param request
	 * @return
	 */
	@RequestMapping("exportGatheringMoney")
	public void exportGatheringMoney(Map<String, Object> map,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();// 获取当前登录用户名
		List<PaymentRecord> paymentRecordlist = payService
				.findAllGatheringMoneyRecord(currenLoginName);
		for (PaymentRecord paymentRecord : paymentRecordlist) {
			if ("1".equals(paymentRecord.getBillStyle())) {
				paymentRecord.setBillStyle(PaymentRecord.XPHK);
			} else {
				paymentRecord.setBillStyle(PaymentRecord.XKHP);
			}
			if ("1".equals(paymentRecord.getIsBill())) {
				paymentRecord.setIsBill("是");
			} else {
				paymentRecord.setIsBill("否");
			}
			if ("0".equals(paymentRecord.getStatus())) {
				paymentRecord.setStatus("初始");
			}else if("1".equals(paymentRecord.getStatus())) {
				paymentRecord.setStatus("部分核销");
			}else if("2".equals(paymentRecord.getStatus())) {
				paymentRecord.setStatus("已完成");
			}

		}
		dataMap.put("paymentRecordlist", paymentRecordlist);
		ExcelUtil.export(request, response, dataMap, "gatheringMoney", "收款信息");
	}

	/**
	 * 上传执行
	 * 
	 * @param file
	 *            （上传的文件）
	 * @return
	 */
	public String uploadFile(MultipartFile file) {
		String filePath = getClasspath() + "zhgj/upload";
		String randomName = UUID.randomUUID().toString().toUpperCase()
				.replaceAll("-", "");
		String fileName = fileUp(file, filePath, randomName);
		//20180110 qhzhao System.out.println(fileName);
		return fileName;
	}

	/**
	 * 复制文件
	 * 
	 * @param file
	 *            (文件对象）
	 * @param filePath
	 *            （文件路径）
	 * @param fileName
	 *            （文件名）
	 * @return
	 */
	public String fileUp(MultipartFile file, String filePath, String fileName) {
		String extName = ""; // 扩展名格式：
		try {

			if (file.getOriginalFilename().lastIndexOf(".") >= 0) {
				extName = file.getOriginalFilename().substring(
						file.getOriginalFilename().lastIndexOf("."));
			}

			copyFile(file.getInputStream(), filePath, fileName + extName)
					.replaceAll("-", "");

		} catch (IOException e) {
			//20180110 qhzhao System.out.println(e);
		}
		return fileName + extName;
	}

	/**
	 * 写文件到当前目录的upload目录中
	 * 
	 * @param in
	 * @param fileName
	 * @throws IOException
	 */
	private String copyFile(InputStream in, String dir, String realName)
			throws IOException {
		File file = mkdirsmy(dir, realName);
		FileUtils.copyInputStreamToFile(in, file);
		return realName;
	}

	/**
	 * 判断路径是否存在，否：创建此路径
	 * 
	 * @param dir
	 *            文件路径
	 * @param realName
	 *            文件名
	 * @throws IOException
	 */
	public File mkdirsmy(String dir, String realName) throws IOException {
		File file = new File(dir, realName);
		if (!file.exists()) {
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			file.createNewFile();
		}
		return file;
	}

	/**
	 * 获取classpath1
	 * 
	 * @return
	 */
	public String getClasspath() {
		String path = (String.valueOf(Thread.currentThread()
				.getContextClassLoader().getResource("")) + "../../")
				.replaceAll("file:/", "").replaceAll("%20", " ").trim();
		if (path.indexOf(":") != 1) {
			path = File.separator + path;
		}
		return path;
	}

	/**
	 * 删除用户付款
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/delPaymentRecord", method = RequestMethod.POST)
	public ResponseEntity<Void> delPaymentRecord(@RequestBody String ids) {
		if ("".equals(ids) || ids == null) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		payService.delPaymentRecord(ids);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
	
	/**
	 * 确认收款
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/confirmGatheringMoney", method = RequestMethod.POST)
	public ResponseEntity<Void> confirmGatheringMoney(@RequestBody String serialNum) {
		if ("".equals(serialNum) || serialNum == null) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		
		Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();// 获取当前登录用户名

		Map<String,Object> map=new HashMap<String,Object>();
		map.put("updater", currenLoginName);
		map.put("serialNum", serialNum);
		payService.confirmGatheringMoney(map);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	/**
	 * 通过id查询付款详情
	 * 
	 * @param ids
	 *            （前台所传递的id）
	 * @return
	 */
	@RequestMapping(value = "/selectPayById", method = RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> selectPayById(String serialNum) {
		Map<String,Object>map=new HashMap<String,Object>();
		PaymentRecord c = payService.selectPayById(serialNum);
		String orderSerial = c.getOrderSerial();
		//String paiedMoney = payService.selectPaiedMoney(orderSerial);
		String paiedMoney=c.getPaymentAmount()==null?"0":c.getPaymentAmount();
		String billedMoney = payService.selectBilledMoney(orderSerial);
		c.setPaiedMoney(paiedMoney);
		c.setBilledMoney(billedMoney);
		List<PaymentFile> list = payService.selectFileList(serialNum);
		c.setFileList(list);
		
		List<ClauseSettlementDetail> clauseSettlementDetail = payService
				.selectClauseSettlementDetailList2(c.getOrderSerial());
		
		c.setClauseSettList(clauseSettlementDetail);
		//获取平台收付款信息
				String comId=companyService.selectComIdByComName(StaticConst.getInfo("comName"));
				List<CompanyFinance>comFinances=companyFinanceService.selectListByComId(comId);
				c.setComFinances(comFinances);
				if(StringUtil.isNotEmpty(c.getCustomsFormSerial())){
					CustomsForm customsForm =customsFormService.selectById(c.getCustomsFormSerial());
					c.setQgOrBgNum(customsForm.getCustomsFormNum());
					c.setAddedTax(customsForm.getAddedTax());
					c.setCustomsAmount(customsForm.getCustomsAmount());
					OrderInfo o=orderService.selectById(c.getOrderSerial());
					c.setRate(o.getRate());
				}
				map.put("paymentRecord",c);
				//获取核销记录
				List<VerificationRecord>rvList=payService.findVerificationRecordByPaymentRecordSerial(serialNum);
				map.put("rvList", rvList);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	
	/**
	 * 当支付节点是“合同签订”时，查询日期
	 * @param serialNum
	 * @return
	 */
	@RequestMapping(value = "/selectDateTypeContract", method = RequestMethod.GET)
	public ResponseEntity<ContractVO> selectDateTypeContract(String serialNum){
		ContractVO c=payService.selectDateTypeContract(serialNum);
		return new ResponseEntity<ContractVO>(c, HttpStatus.OK);
	}
	
	
	/**
	 * 当支付节点是“提货前”时，查询日期
	 * @param serialNum
	 * @return
	 */
	@RequestMapping(value = "/selectDateTypeDelivery", method = RequestMethod.GET)
	public ResponseEntity<DeliveryVO> selectDateTypeDelivery(String serialNum){
		DeliveryVO c=payService.selectDateTypeDelivery(serialNum);
		return new ResponseEntity<DeliveryVO>(c, HttpStatus.OK);
	}
	
	
	/**
	 * 当支付节点是“到货后”时，查询日期
	 * @param serialNum
	 * @return
	 */
	@RequestMapping(value = "/selectDateTypeTakeDelivery", method = RequestMethod.GET)
	public ResponseEntity<TakeDeliveryVO> selectDateTypeTakeDelivery(String serialNum){
		TakeDeliveryVO c=payService.selectDateTypeTakeDelivery(serialNum);
		return new ResponseEntity<TakeDeliveryVO>(c, HttpStatus.OK);
	}

	
	@RequestMapping(value = "/resourceDownload", method = RequestMethod.POST)
	// 匹配的是href中的download请求
	public void download(@RequestParam("project_id") Integer projectId,
			HttpServletResponse response) throws IOException {

	}
	
	/**
	 * 
	 * @Description 查找所有收款水单信息
	 * @return
	 */
	@RequestMapping(value = "/findReceiveMemoRecord", method = RequestMethod.GET)
	public ResponseEntity<Map> findReceiveMemoRecord(
			HttpServletRequest request) {
		Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();// 获取当前登录用户名
		List<MemoRecord> memoRecordlist = payService
				.findReceiveMemoRecord(currenLoginName);
		for(MemoRecord memoRecord:memoRecordlist){
			memoRecord.setComName(companyService.selectOne(memoRecord.getBuyComId()).getComName());
		}
		// 封装datatables数据返回到前台
		Map pageMap = new HashMap();
		pageMap.put("draw", 1);
		pageMap.put("recordsTotal", memoRecordlist == null ? 0
				: memoRecordlist.size());
		pageMap.put("recordsFiltered", memoRecordlist == null ? 0
				: memoRecordlist.size());
		pageMap.put("data", memoRecordlist);
		return new ResponseEntity<Map>(pageMap, HttpStatus.OK);
	}
	/**
	 * 
	 * @Description 查找所有付款水单信息
	 * @return
	 */
	@RequestMapping(value = "/findPayMemoRecord", method = RequestMethod.GET)
	public ResponseEntity<Map> findPayMemoRecord(
			HttpServletRequest request) {
		Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();// 获取当前登录用户名
		List<MemoRecord> memoRecordlist = payService
				.findPayMemoRecord(currenLoginName);
		for(MemoRecord memoRecord:memoRecordlist){
			memoRecord.setComName(companyService.selectOne(memoRecord.getSupplyComId()).getComName());
		}
		// 封装datatables数据返回到前台
		Map pageMap = new HashMap();
		pageMap.put("draw", 1);
		pageMap.put("recordsTotal", memoRecordlist == null ? 0
				: memoRecordlist.size());
		pageMap.put("recordsFiltered", memoRecordlist == null ? 0
				: memoRecordlist.size());
		pageMap.put("data", memoRecordlist);
		return new ResponseEntity<Map>(pageMap, HttpStatus.OK);
	}
	/**
	 * 保存收款水单
	 */
	@RequestMapping(value = "/saveReceiveMemo", method = RequestMethod.POST)
	public ResponseEntity<MemoRecord> saveReceiveMemo(@Valid MemoRecord memoRecord, HttpServletRequest request,
			UriComponentsBuilder ucBuilder,@RequestParam(value = "files")MultipartFile files[]) {
		Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名 
		String paymentVoucher=null;
		//只有在文件组不为空时上传文件
		if(files.length>0){
			if(files[0]!=null){
				paymentVoucher=uploadFile1(files[0]);   
			}
		}
		//如果id为空执行保存
		if(StringUtils.isEmpty(memoRecord.getSerialNum())){
			String serialNum=ApplicationUtils.random32UUID(); 
			memoRecord.setSerialNum(serialNum);
			//给各自的文件字段赋值文件名
			memoRecord.setPaymentVoucher(paymentVoucher);
			memoRecord.setCreator(currenLoginName);
			memoRecord.setDelFlg("0");
			memoRecord.setStatus("0");
			payService.insertMemoRecord(memoRecord);
		}else{
			//如果id不为空执行更新
			memoRecord.setUpdater(currenLoginName);
			//给各自的文件字段赋值文件名
			memoRecord.setPaymentVoucher(paymentVoucher);
			payService.updateMemoRecord(memoRecord);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/userContract").buildAndExpand(memoRecord.getSerialNum()).toUri());
		if(StringUtil.isEmpty(memoRecord.getBuyComId())){
			memoRecord.setComName(companyService.selectOne(memoRecord.getSupplyComId()).getComName());
		}else{
			memoRecord.setComName(companyService.selectOne(memoRecord.getBuyComId()).getComName());
		}
		return new ResponseEntity<MemoRecord>(memoRecord, HttpStatus.CREATED);
	}
	/**
	 * 上传执行
	 * @param file（上传的文件）
	 * @return
	 */
	public String uploadFile1(MultipartFile file){
		String filename = "";
		try {
			String path = env.getProperty("upload_path");
			String fileName = file.getOriginalFilename();
			/*String prefix = "." + fileName.substring(fileName.lastIndexOf(".") + 1);*/
			filename = ApplicationUtils.random32UUID() +"_"+ fileName;
			File dst = null;
			File uploadDir = new File(path); // 创建上传目录
			if (!uploadDir.exists()) {
				uploadDir.mkdirs(); // 如果不存在则创建upload目录
			}
			dst = new File(uploadDir,filename); // 创建一个指向upload目录下的文件对象，文件名随机生成
			file.transferTo(dst); // 创建文件并将上传文件复制过去
			//20180110 qhzhao System.out.println("上传文件----------"+filename);
		} catch (Exception e) {
			//20180110 qhzhao System.out.println("文件上传失败----------"+filename+"-------Exception:"+e.getMessage());
			filename="";
		}
		return filename;
	}
	/**
	 * 删除收款水单
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/delReceiveMemo", method = RequestMethod.POST)
	public ResponseEntity<Void> delReceiveMemo(@RequestBody String ids) {
		if ("".equals(ids) || ids == null) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		payService.delMemoRecord(ids);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	/**
	 * 通过id查询收付款水单详情
	 */
	@RequestMapping(value = "/selectReceiveMemo", method = RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> selectReceiveMemo(String serialNum) {
		Map<String,Object>map=new  HashMap<String,Object>();
		MemoRecord memoRecord=payService.selectMemoRecordById(serialNum);
		if(StringUtil.isEmpty(memoRecord.getBuyComId())){
			memoRecord.setComName(companyService.selectOne(memoRecord.getSupplyComId()).getComName());
		}else{
			memoRecord.setComName(companyService.selectOne(memoRecord.getBuyComId()).getComName());
		}
		map.put("memoRecord", memoRecord);
		//获取核销记录
		List<VerificationRecord>rvList=payService.findVerificationRecord(serialNum);
		map.put("rvList", rvList);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	 /**
	  * @Description (获取收付款水单对应的应收/付账单)
	 * @param comId 公司id
	 * @param type 收付款
	  * @return
	  */
	 @RequestMapping(value="paymentRecordList",method=RequestMethod.GET)
	 public ResponseEntity<Map<String,Object>> paymentRecordList(String comId,String type) {
		 List<PaymentRecord> paymentRecordList=payService.findPaymentRecord(comId, type);
		 // 封装datatables数据返回到前台
		 Map<String,Object> pageMap = new HashMap<String,Object>();
		 pageMap.put("draw", 1);
		 pageMap.put("recordsTotal", paymentRecordList==null?0:paymentRecordList.size());
		 pageMap.put("recordsFiltered", paymentRecordList==null?0:paymentRecordList.size());
		 pageMap.put("data", paymentRecordList);
		 return new ResponseEntity<Map<String,Object>>(pageMap, HttpStatus.OK);
	 }
	 /**
	 * @Description (获取应收/付账单对应的收付款水单)
	 * @param comId 公司id
	 * @param type 收付款
	 * @return
	 */
	@RequestMapping(value="memoRecordList",method=RequestMethod.GET)
	 public ResponseEntity<Map<String,Object>> memoRecordList(String comId,String type) {
		 List<MemoRecord> memoRecordList=payService.findMemoRecord(comId, type);
		 // 封装datatables数据返回到前台
		 Map<String,Object> pageMap = new HashMap<String,Object>();
		 pageMap.put("draw", 1);
		 pageMap.put("recordsTotal", memoRecordList==null?0:memoRecordList.size());
		 pageMap.put("recordsFiltered", memoRecordList==null?0:memoRecordList.size());
		 pageMap.put("data", memoRecordList);
		 return new ResponseEntity<Map<String,Object>>(pageMap, HttpStatus.OK);
	 }
    /**
     * @Description (保存核销记录)
     * @param request
     * @return
     */
    @RequestMapping(value="saveVerificateData",method=RequestMethod.POST)
    @ResponseBody
    public String saveVerificateData(Map<String, Object> map,@RequestBody String params,HttpServletRequest request) {
    	String flag ="0"; //默认失败
  	   	  	TakeDeliveryParams  takeDeliveryParams = null;
			takeDeliveryParams = JSON.parseObject(params, TakeDeliveryParams.class);
        	try{
        	Subject currentUser = SecurityUtils.getSubject();
        		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
        		List<VerificationRecord>verList=takeDeliveryParams.getVerificationRecords();
        		Boolean falg=payService.insertVerificateData(verList, currenLoginName,verList.get(0).getReceiveMemoSerial());
        		if(falg){
        			flag = "1";
        		}
        	}catch(Exception e){
        		logger.warn(e.getMessage(), e);
        		return null;
        	}
    	return flag;
    }
    
	/**
	 * @Description (导出收款水单/付款水单信息)
	 * @param request
	 * @return
	 */
	@RequestMapping("exportPayReceiveMemo")
	public void exportPayReceiveMemo(Map<String, Object> map,
			HttpServletRequest request, HttpServletResponse response,
			String type) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();// 获取当前登录用户名
		List<MemoRecord> memoRecordlist = new ArrayList<MemoRecord>();
		if ("receive".equals(type)) {
			memoRecordlist = payService.findReceiveMemoRecord(currenLoginName);
		} else if ("pay".equals(type)) {
			memoRecordlist = payService.findPayMemoRecord(currenLoginName);
		}
		for (MemoRecord memoRecord : memoRecordlist) {
			if ("0".equals(memoRecord.getStatus())) {
				memoRecord.setStatus("待核销");
			} else if ("1".equals(memoRecord.getStatus())) {
				memoRecord.setStatus("部分核销");
			} else if ("2".equals(memoRecord.getStatus())) {
				memoRecord.setStatus("已完成");
			}

			if (StringUtil.isEmpty(memoRecord.getSupplyComId())) {
				memoRecord.setComName(companyService.selectOne(
						memoRecord.getBuyComId()).getComName());
			} else if (StringUtil.isEmpty(memoRecord.getBuyComId())) {
				memoRecord.setComName(companyService.selectOne(
						memoRecord.getSupplyComId()).getComName());
			}
			memoRecord.setRemainMoneyAmount((new BigDecimal(memoRecord
					.getMoneyAmount()).subtract(new BigDecimal(memoRecord
					.getVerificationMoneyAmount()))).toString());
		}

		dataMap.put("memoRecord", memoRecordlist);
		if ("receive".equals(type)) {
			ExcelUtil.export(request, response, dataMap, "receiveMemo",
					"收款水单信息");
		} else if ("pay".equals(type)) {
			ExcelUtil.export(request, response, dataMap, "payMemo", "付款水单信息");
		}

	}
}
