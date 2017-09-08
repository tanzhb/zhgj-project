package com.congmai.zhgj.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.alibaba.druid.util.StringUtils;
import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.core.util.ExcelUtil;
import com.congmai.zhgj.web.model.ClauseSettlement;
import com.congmai.zhgj.web.model.ClauseSettlementDetail;
import com.congmai.zhgj.web.model.ContractVO;
import com.congmai.zhgj.web.model.DeliveryMaterielVO;
import com.congmai.zhgj.web.model.OrderInfo;
import com.congmai.zhgj.web.model.OrderMaterielExample;
import com.congmai.zhgj.web.model.PaymentPlan;
import com.congmai.zhgj.web.model.PaymentRecord;
import com.congmai.zhgj.web.service.ContractService;
import com.congmai.zhgj.web.service.OrderService;
import com.congmai.zhgj.web.service.PayService;


/**
 * 付款管理controller
 * @author czw
 *
 */
@Component  
@Scope("prototype")  
@Controller
@RequestMapping("/pay")
public class PayController {

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

	/**
	 * 
	 * @Description 查找所有用户付款信息
	 * @return
	 */
	@RequestMapping(value = "/findAllPaymentRecord", method = RequestMethod.GET)
	public ResponseEntity<Map> findAllUserContract(HttpServletRequest request) {

		Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名 
		List<PaymentRecord> paymentRecordlist=payService.selectAllPaymentRecordList(currenLoginName);

		//封装datatables数据返回到前台
		Map pageMap = new HashMap();
		pageMap.put("draw", 1);
		pageMap.put("recordsTotal",paymentRecordlist==null?0:paymentRecordlist.size());
		pageMap.put("recordsFiltered",paymentRecordlist==null?0:paymentRecordlist.size());
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
	public ResponseEntity<Map> findAllGatheringMoneyRecord(HttpServletRequest request) {

		Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名 
		List<PaymentRecord> paymentRecordlist=payService.findAllGatheringMoneyRecord(currenLoginName);

		//封装datatables数据返回到前台
		Map pageMap = new HashMap();
		pageMap.put("draw", 1);
		pageMap.put("recordsTotal",paymentRecordlist==null?0:paymentRecordlist.size());
		pageMap.put("recordsFiltered",paymentRecordlist==null?0:paymentRecordlist.size());
		pageMap.put("data", paymentRecordlist);
		return new ResponseEntity<Map>(pageMap, HttpStatus.OK);
	}
	
	/**
	 * 
	 * @Description 获取采购订单信息
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/getSaleOrderInfo")
	@ResponseBody
	public Map getSaleOrderInfo(String serialNum,OrderInfo orderInfo) {
		orderInfo = orderService.selectById(serialNum);
		Map<String, Object> map = new HashMap<String, Object>();
    	map.put("orderInfo", orderInfo);
    	
    	OrderMaterielExample m =new OrderMaterielExample();
    	//and 条件1
    	com.congmai.zhgj.web.model.OrderMaterielExample.Criteria criteria =  m.createCriteria();
    	criteria.andDelFlgEqualTo("0");
    	criteria.andOrderSerialEqualTo(serialNum);
    	/*List<DeliveryMaterielVO> orderMateriel = deliveryService.selectList(serialNum);*/
    	List<ClauseSettlementDetail> clauList=payService.selectClauseSettlementDetailList(serialNum);
    	map.put("clauList", clauList);
    	return map;
	}


	/**
	 * 保存用户付款信息
	 * @param record（付款对象）
	 * @param request（http 请求对象）
	 * @param ucBuilder
	 * @return 操作结果
	 */
	@RequestMapping(value = "/savePaymentRecord", method = RequestMethod.POST)
	public ResponseEntity<Void> saveUserContract(@Valid PaymentRecord record, HttpServletRequest request,
			UriComponentsBuilder ucBuilder,MultipartFile file) {
		Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名 


		String paymentVoucher=null;


			if(file!=null){
				paymentVoucher=uploadFile(file); 
				record.setPaymentVoucher(paymentVoucher);
			}
		//如果id为空执行保存
		if(StringUtils.isEmpty(record.getSerialNum())){
			PaymentPlan plan=new PaymentPlan();
			plan.setSerialNum(ApplicationUtils.random32UUID());
			plan.setOrderSerial(record.getOrderSerial());
			plan.setPaymentPlanNum(record.getPaymentPlanNum());
			plan.setPaymentStyle(record.getPaymentStyle());
			plan.setSupplyComId(record.getSupplyComId());
			plan.setBuyComId(record.getBuyComId());
			plan.setPaymentAmount(record.getPaymentAmount());
			plan.setCreator(currenLoginName);
			plan.setClauseSettlementSerial(record.getClauseSettlementSerial());
			//添加付款计划
			payService.insertPaymentPlan(plan);
			
			
			String serialNum=ApplicationUtils.random32UUID(); 
			record.setSerialNum(serialNum);
			record.setCreator(currenLoginName);
			record.setPaymentPlanSerial(plan.getSerialNum());
			//添加付款记录
			payService.insertPaymentRecord(record);
		}else{
			//如果id不为空执行更新
			PaymentPlan plan=new PaymentPlan();
			plan.setSerialNum(record.getPaymentPlanSerial());
			plan.setOrderSerial(record.getOrderSerial());
			plan.setPaymentPlanNum(record.getPaymentPlanNum());
			plan.setPaymentStyle(record.getPaymentStyle());
			plan.setSupplyComId(record.getSupplyComId());
			plan.setBuyComId(null);
			plan.setPaymentAmount(record.getPaymentAmount());
			plan.setClauseSettlementSerial(record.getClauseSettlementSerial());
			plan.setUpdater(currenLoginName);
			//更新付款计划
			payService.updatePaymentPlan(plan);
			
			record.setUpdater(currenLoginName);
			//更新付款记录
			payService.updatePaymentRecord(record);
		}


		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/paymentRecordC").buildAndExpand(record.getSerialNum()).toUri());

		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	
	/**
	 * 查询结算条款详情
	 * @param clauseSettlement （结算条款对象）
	 * @param serialNum （结算条款的id）
	 * @param request
	 * @return
	 */
	@RequestMapping(value="selectClauseDetail")
    @ResponseBody
    public ResponseEntity<ClauseSettlement> selectClauseDetail(@Valid ClauseSettlement clauseSettlement,String serialNum,HttpServletRequest request) {
        	try{
        		 clauseSettlement=payService.selectClauseDetail(clauseSettlement.getSerialNum());
        	}catch(Exception e){
        		System.out.println(e.getMessage());
        		return null;
        	}
    	return new ResponseEntity<ClauseSettlement>(clauseSettlement, HttpStatus.CREATED);
    }

	/**
	 * @Description (导出付款信息)
	 * @param request
	 * @return
	 */
	@RequestMapping("exportPay")
	public void exportPay(Map<String, Object> map,HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名 
		List<PaymentRecord> paymentRecordlist=payService.selectAllPaymentRecordList(currenLoginName);
        for(PaymentRecord paymentRecord:paymentRecordlist){
        	if("1".equals(paymentRecord.getBillStyle())){
        		paymentRecord.setBillStyle("先票后款");
        	}else{
        		paymentRecord.setBillStyle("先款后票");
        	}
        	
        	if("0".equals(paymentRecord.getStatus())){
        		paymentRecord.setStatus("初始");
        	}
        	
        }
		dataMap.put("paymentRecordlist",paymentRecordlist);
		ExcelUtil.export(request, response, dataMap, "pay", "付款信息");
	}
	
	
	/**
	 * @Description (导出收款信息)
	 * @param request
	 * @return
	 */
	@RequestMapping("exportGatheringMoney")
	public void exportGatheringMoney(Map<String, Object> map,HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名 
		List<PaymentRecord> paymentRecordlist=payService.findAllGatheringMoneyRecord(currenLoginName);
        for(PaymentRecord paymentRecord:paymentRecordlist){
        	if("1".equals(paymentRecord.getBillStyle())){
        		paymentRecord.setBillStyle("先票后款");
        	}else{
        		paymentRecord.setBillStyle("先款后票");
        	}
        	
        	if("0".equals(paymentRecord.getStatus())){
        		paymentRecord.setStatus("初始");
        	}
        	
        }
		dataMap.put("paymentRecordlist",paymentRecordlist);
		ExcelUtil.export(request, response, dataMap, "gatheringMoney", "收款信息");
	}


	/**
	 * 上传执行
	 * @param file（上传的文件）
	 * @return
	 */
	public String uploadFile(MultipartFile file){
		String filePath = getClasspath()+"zhgj/upload";
		String randomName=UUID.randomUUID().toString().toUpperCase().replaceAll("-", ""); 
		String fileName = fileUp(file, filePath,randomName);
		System.out.println(fileName);
		return fileName;
	}


	/**
	 * 复制文件
	 * @param file (文件对象）
	 * @param filePath （文件路径）
	 * @param fileName   （文件名）
	 * @return
	 */
	public  String fileUp(MultipartFile file, String filePath, String fileName){
		String extName = ""; // 扩展名格式：
		try {

			if (file.getOriginalFilename().lastIndexOf(".") >= 0){
				extName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			}

			copyFile(file.getInputStream(), filePath, fileName+extName).replaceAll("-", "");

		} catch (IOException e) {
			System.out.println(e);
		}
		return fileName+extName;
	}


	/**
	 * 写文件到当前目录的upload目录中
	 * @param in
	 * @param fileName
	 * @throws IOException
	 */
	private  String copyFile(InputStream in, String dir, String realName)
			throws IOException {
		File file = mkdirsmy(dir,realName);
		FileUtils.copyInputStreamToFile(in, file);
		return realName;
	}


	/**判断路径是否存在，否：创建此路径
	 * @param dir  文件路径
	 * @param realName  文件名
	 * @throws IOException 
	 */
	public  File mkdirsmy(String dir, String realName) throws IOException{
		File file = new File(dir, realName);
		if (!file.exists()) {
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			file.createNewFile();
		}
		return file;
	}




	/**获取classpath1
	 * @return
	 */
	public  String getClasspath(){
		String path = (String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))+"../../").replaceAll("file:/", "").replaceAll("%20", " ").trim();	
		if(path.indexOf(":") != 1){
			path = File.separator + path;
		}
		return path;
	}


	/**
	 * 删除用户付款
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
	 * 通过id查询付款详情
	 * @param ids（前台所传递的id）
	 * @return
	 */
	@RequestMapping(value = "/selectPayById", method = RequestMethod.GET)
	public ResponseEntity<PaymentRecord> selectPayById(String serialNum) {

		PaymentRecord c=payService.selectPayById(serialNum);
		List<ClauseSettlementDetail> clauList=payService.selectClauseSettlementDetailList(c.getOrderSerial());
		c.setClauseSettList(clauList);
		return new ResponseEntity<PaymentRecord>(c, HttpStatus.OK);
	}


	@RequestMapping(value="/resourceDownload",method=RequestMethod.POST) //匹配的是href中的download请求
	public void download(@RequestParam("project_id") Integer projectId, HttpServletResponse response) throws IOException {

	}
}
