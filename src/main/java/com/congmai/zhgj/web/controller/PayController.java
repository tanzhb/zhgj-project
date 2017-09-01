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
 * 合同管理controller
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
	
	@Resource
	private PayService payService;

	/**
	 * 
	 * @Description 查找所有用户合同信息
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
	
	
	/**
	 * 
	 * @Description 获取销售订单信息
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
	 * 保存用户合同
	 * @param contractVO（合同对象）
	 * @param request（http 请求对象）
	 * @param ucBuilder
	 * @return 操作结果
	 */
	@RequestMapping(value = "/savePaymentRecord", method = RequestMethod.POST)
	public ResponseEntity<Void> saveUserContract(@Valid PaymentRecord record, HttpServletRequest request,
			UriComponentsBuilder ucBuilder,@RequestParam(value = "file")MultipartFile file) {
		Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名 


		String paymentVoucher=null;


			if(file!=null){
				paymentVoucher=uploadFile(file); 
			}
		//如果id为空执行保存
		if(StringUtils.isEmpty(record.getSerialNum())){
			PaymentPlan plan=new PaymentPlan();
			plan.setSerialNum(ApplicationUtils.random32UUID());
			plan.setOrderSerial(record.getOrderSerial());
			plan.setPaymentPlanNum(record.getPaymentPlanNum());
			plan.setPaymentStyle(record.getPaymentStyle());
			plan.setSupplyComId(record.getSupplyComId());
			plan.setBuyComId(null);
			plan.setPaymentAmount(record.getPaymentAmount());
			plan.setCreator(currenLoginName);
			plan.setClauseSettlementSerial(record.getClauseSettlementSerial());
			payService.insertPaymentPlan(plan);
			
			
			String serialNum=ApplicationUtils.random32UUID(); 
			record.setSerialNum(serialNum);
			record.setCreator(currenLoginName);
			record.setPaymentVoucher(paymentVoucher);
			record.setPaymentPlanSerial(plan.getSerialNum());
			record.setBuyComId(null);
			payService.insertPaymentRecord(record);
			
			
			
		}/*else{
			//如果id不为空执行更新
			User user1=(User) request.getSession().getAttribute("userInfo");
			contractVO.setUpdater(currenLoginName);

			//给各自的文件字段赋值文件名
			contractVO.setElectronicContract(electronicContract);
			contractVO.setSignContract(signContract);

			contractService.updateContract(contractVO);
		}*/


		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/userContract").buildAndExpand(record.getSerialNum()).toUri());

		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	/**/
	
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
	 * @Description (导出合同信息)
	 * @param request
	 * @return
	 */
	/*@RequestMapping("exportContract")
	public void exportContract(Map<String, Object> map,HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名 
		List<ContractVO> contractList=contractService.queryContractList(currenLoginName);

		dataMap.put("contractList",contractList);
		ExcelUtil.export(request, response, dataMap, "contract", "合同信息");
	}*/


	/** 
	 * 获取时间 
	 *  
	 * @return返回短时间格式 yyyy-MM-dd 
	 */  
	public  Date getNowDateShort(Date date) {  
		Date sqlDate = new java.sql.Date(date.getTime());  
		return sqlDate;  
	}


	/**
	 * 上传执行
	 * @param file（上传的文件）
	 * @return
	 */
	public String uploadFile(MultipartFile file){
		String filePath = getClasspath()+"uploadAttachFiles/";
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
	 * 删除用户合同
	 * @param contractVO
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
	 * 通过id查询合同详情
	 * @param ids（前台所传递的id）
	 * @return
	 */
	@RequestMapping(value = "/selectPayById", method = RequestMethod.GET)
	public ResponseEntity<PaymentRecord> selectPayById(String serialNum) {

		PaymentRecord c=payService.selectPayById(serialNum);
		return new ResponseEntity<PaymentRecord>(c, HttpStatus.OK);
	}

	/**
	 * 跳转到编辑页面
	 * @return
	 */
	/*@RequestMapping(value = "/editUserContractPage")
	public String editUserContractPage(String id,String view) {
		return "contract/editUserContractPage";
	}*/
	
	
	 /**
     * @Description (下载导入模板)
     * @param request
     * @return
     */
   /* @RequestMapping("downloadImportTemp")
    public void downloadContractTemp(Map<String, Object> map,HttpServletRequest request,HttpServletResponse response) {
    	ExcelUtil.importTempDownLoad(request, response, "contractImport");
    }*/
    
    
    /**
     * @Description (合同信息导入)
     * @param request
     * @return
     */
    /*@RequestMapping("contractImport")
    @ResponseBody
    public Map<String,String> contractImport(@RequestParam(value = "excelFile") MultipartFile excelFile,HttpServletRequest request,HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String, String>();
    	 try {
		     
			ExcelReader excelReader = new ExcelReader(excelFile.getInputStream());
			excelReader.readExcelContent(new RowHandler() {
				@Override
				public void handle(List<Object> row,int i) throws Exception {
					if(!CollectionUtils.isEmpty(row)){
						try{
							ContractVO contract = new ContractVO();
							contract.setId(ApplicationUtils.random32UUID());
							contract.setContractNum(row.get(0).toString());
							contract.setComId(row.get(1).toString());
							contract.setContractType(row.get(2).toString());
							contract.setServiceModel(row.get(3).toString());
							
							SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd");
							contract.setSignDate(sdf.parse(row.get(4).toString()));
							contract.setSigner(row.get(5).toString());
							contract.setStartDate(sdf.parse(row.get(6).toString()));
							contract.setEndDate(sdf.parse(row.get(7).toString()));
							contract.setSettlementClause(row.get(8).toString());
							Subject currentUser = SecurityUtils.getSubject();
							String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
							contract.setCreator(currenLoginName);
							contractService.insertContract(contract);
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
    }*/

	@RequestMapping(value="/resourceDownload",method=RequestMethod.POST) //匹配的是href中的download请求
	public void download(@RequestParam("project_id") Integer projectId, HttpServletResponse response) throws IOException {

	}
}
