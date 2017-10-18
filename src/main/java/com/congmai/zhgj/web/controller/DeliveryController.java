package com.congmai.zhgj.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import net.sf.json.JSONObject;
import net.sf.json.JSONArray;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.io.FileUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
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
import com.congmai.zhgj.core.util.ExcelReader;
import com.congmai.zhgj.core.util.MessageConstants;
import com.congmai.zhgj.core.util.UserUtil;
import com.congmai.zhgj.core.util.ExcelReader.RowHandler;
import com.congmai.zhgj.core.util.ExcelUtil;
import com.congmai.zhgj.web.event.EventExample;
import com.congmai.zhgj.web.event.SendMessageEvent;
import com.congmai.zhgj.web.model.BaseVO;
import com.congmai.zhgj.web.model.CommentVO;
import com.congmai.zhgj.web.model.Company;
import com.congmai.zhgj.web.model.ContractVO;
import com.congmai.zhgj.web.model.DeliveryMaterielVO;
import com.congmai.zhgj.web.model.DeliveryTransportVO;
import com.congmai.zhgj.web.model.DeliveryVO;
import com.congmai.zhgj.web.model.Materiel;
import com.congmai.zhgj.web.model.OrderInfo;
import com.congmai.zhgj.web.model.OrderMateriel;
import com.congmai.zhgj.web.model.OrderMaterielExample;
import com.congmai.zhgj.web.model.PaymentRecord;
import com.congmai.zhgj.web.model.RelationFile;
import com.congmai.zhgj.web.model.StockInOutCheck;
import com.congmai.zhgj.web.model.TakeDeliveryVO;
import com.congmai.zhgj.web.model.User;
import com.congmai.zhgj.web.model.Warehouse;
import com.congmai.zhgj.web.service.ContractService;
import com.congmai.zhgj.web.service.DeliveryService;
import com.congmai.zhgj.web.service.IProcessService;
import com.congmai.zhgj.web.service.OrderMaterielService;
import com.congmai.zhgj.web.service.OrderService;
import com.congmai.zhgj.web.service.StockInOutCheckService;
import com.congmai.zhgj.web.service.UserCompanyService;
import com.congmai.zhgj.web.service.WarehouseService;


/**
 * 发货管理controller
 * @author czw
 *
 */
@Component  
@Scope("prototype")  
@Controller
@RequestMapping("/delivery")
public class DeliveryController {
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
	 * 仓库service
	 */
	@Resource
    private WarehouseService  warehouseService;
	
	/**
	 * 订单物料service
	 */
	@Resource
    private OrderMaterielService orderMaterielService;
	
	/**
	 * 发货service
	 */
	@Resource
    private DeliveryService deliveryService;
	/**
	 * 用户企业关系service
	 */
	@Resource
	private UserCompanyService userCompanyService;
	
	
	@Autowired
	private IProcessService processService;
	
	@Autowired
	protected RuntimeService runtimeService;
	
	
	@Autowired
	protected TaskService taskService;
	
	
	@Resource
	private StockInOutCheckService  stockInOutCheckService;

	 /**
     * @Description (查询仓库列表)
     * @param request
     * @return
     */
    @RequestMapping(value = "/getWarehouseList", method = RequestMethod.GET)
    public ResponseEntity<List<Warehouse>>  getWarehouseList(HttpServletRequest request) {
		List<Warehouse> warehouses = warehouseService.selectList();
		return new ResponseEntity<List<Warehouse>>(warehouses, HttpStatus.OK);
	}
    
    
    /**
     * @Description (查询订单物料)
     * @param request
     * @return
     */
    @RequestMapping(value = "/getSaleOrderMaterielList", method = RequestMethod.GET)
    public ResponseEntity<List<OrderMateriel>>  getSaleOrderMaterielList(HttpServletRequest request,String serialNum) {
    	OrderMaterielExample m =new OrderMaterielExample();
    	//and 条件1
    	com.congmai.zhgj.web.model.OrderMaterielExample.Criteria criteria =  m.createCriteria();
    	criteria.andDelFlgEqualTo("0");
    	criteria.andOrderSerialEqualTo(serialNum);
    	List<OrderMateriel> orderMateriel = orderMaterielService.selectList(m);
		
		return new ResponseEntity<List<OrderMateriel>>(orderMateriel, HttpStatus.OK);
	}
    
    
    /**
     * 查询发货列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/findAllDeliveryList", method = RequestMethod.GET)
    public ResponseEntity<Map> findAllDeliveryList(HttpServletRequest request) {

		Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名 
		//List<DeliveryVO> contractList=deliveryService.findAllDeliveryList(currenLoginName);
		User user = UserUtil.getUserFromSession();
    	List<String> comIds = new ArrayList<String>();
    	if(user!=null){
			comIds = userCompanyService.getComIdsByUserId(String.valueOf(user.getUserId()));
		}
		DeliveryVO query = new DeliveryVO();
		query.setCreator(currenLoginName);
		query.setSupplyComIds(comIds);
		List<DeliveryVO> contractList=deliveryService.findAllDeliveryList(query);

		//封装datatables数据返回到前台
		Map pageMap = new HashMap();
		pageMap.put("draw", 1);
		pageMap.put("recordsTotal",contractList==null?0:contractList.size());
		pageMap.put("recordsFiltered",contractList==null?0:contractList.size());
		pageMap.put("data", contractList);
		return new ResponseEntity<Map>(pageMap, HttpStatus.OK);
	}
    


	/**
	 * 确认发货
	 * @param request（http 请求对象）
	 * @param ucBuilder
	 * @return 操作结果
	 */
	@RequestMapping(value = "/goDelivery", method = RequestMethod.GET)
	public ResponseEntity<Void> goDelivery(String serialNum, HttpServletRequest request,UriComponentsBuilder ucBuilder) {
		Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名 

		Map<String,Object> map=new HashMap<String,Object>();
		map.put("serialNum", serialNum);
		map.put("updater", currenLoginName);
		
		DeliveryVO delivery=deliveryService.selectDetailById(serialNum);
		String orderSerial=delivery.getOrderSerial();
		map.put("orderSerial", orderSerial);
		
		deliveryService.updateOrderWhenDeliveryComlete(map);
		
		deliveryService.goDelivery(map);
		
		StockInOutCheck stockInOutCheck=new StockInOutCheck();
		stockInOutCheck.setSerialNum(ApplicationUtils.random32UUID());
		stockInOutCheck.setDeliverSerial(serialNum);
		stockInOutCheck.setTakeDeliverSerial("checkout");
		//stockInOutCheck.setTakeDeliverSerial(delivery.getTakeDeliverSerialNum());
		stockInOutCheck.setChecker(currenLoginName);
		stockInOutCheck.setCreator(currenLoginName);
		stockInOutCheck.setCreateTime(new Date());
		stockInOutCheck.setUpdater(currenLoginName);
		stockInOutCheck.setUpdateTime(new Date());
		stockInOutCheck.setStatus("0");//待检验
		stockInOutCheck.setCheckDate(new Date());
		stockInOutCheck.setDelFlg("0");
		stockInOutCheckService.insert(stockInOutCheck);
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/delivery").buildAndExpand(serialNum).toUri());
		
		//发货消息
		EventExample.getEventPublisher().publicSendMessageEvent(new SendMessageEvent(delivery,MessageConstants.DELIVERY));
		
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
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
    	List<DeliveryMaterielVO> orderMateriel = deliveryService.selectList(serialNum);
    	map.put("orderMateriel", orderMateriel);
    	return map;
	}
	
	
	
	/**
	 * 
	 * @Description 获取附件信息
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/getAttachFileInfo")
	@ResponseBody
	public Map<String, Object> getAttachFileInfo(String serialNum) {
		Map<String, Object> map = new HashMap<String, Object>();
    	
    	List<RelationFile> fileList=deliveryService.getAttachFileInfo(serialNum);
    	map.put("fileList", fileList);
    	return map;
	}
	
	
	/**
	 * 批量获取物料信息
	 * @return
	 */
	@RequestMapping(value = "/batchGetMaterielInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> batchGetMaterielInfo(@RequestBody String ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Materiel> list=deliveryService.batchGetMaterielInfo(ids);
		map.put("materielList", list);
		return map;
	}
	
    
    /**
     * @Description (保存订单物料信息)
     * @param request
     * @return
     */
    @RequestMapping(value="saveDeliveryMateriel",method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<DeliveryMaterielVO> saveDeliveryMateriel(Map<String, Object> map,DeliveryMaterielVO deliveryMateriel,HttpServletRequest request) {
        	try{
        		Subject currentUser = SecurityUtils.getSubject();
        		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
        		if(StringUtils.isEmpty(deliveryMateriel.getSerialNum())){
        			deliveryMateriel.setSerialNum(ApplicationUtils.random32UUID());
        			deliveryMateriel.setCreator(currenLoginName);
        			deliveryMateriel.setUpdater(currenLoginName);
        			deliveryService.insertDeliveryMateriel(deliveryMateriel);
        		}
        		
        		//附件
        		List<RelationFile> files=new ArrayList<RelationFile>();
        		
        		//如果附件不为空时执行添加操作
        		if(!StringUtils.isEmpty(deliveryMateriel.getAttachFile())){
        			String attachFile[]=deliveryMateriel.getAttachFile().split("&");
        			for(String detail:attachFile){
        				RelationFile item=new RelationFile();
        				String attachFileDetail[]=detail.split(",");
        				String file=attachFileDetail[0];
        				
        				//描述不为空时添加描述
        				String describe=null;
        				if(attachFileDetail.length>1){
        					if(attachFileDetail[1]!=null){
               				 describe=attachFileDetail[1];	
               				}	
        				}
        				
        				
        				item.setSerialNum(ApplicationUtils.random32UUID());
        				item.setRelationSerial(deliveryMateriel.getSerialNum());
        				item.setFileType("delivery");
        				item.setFileDescribe(describe);
        				item.setFile(file);
        				item.setUploader(currenLoginName);
        				item.setCreator(currenLoginName);
        				item.setUpdater(currenLoginName);
        				files.add(item);
        			}
        			
        			//批量添加附件
        			deliveryService.insertAttachFiles(files);
        		}
        		
        	}catch(Exception e){
        		System.out.println(e.getMessage());
        		return null;
        	}
        	deliveryMateriel =deliveryService.selectDeliveryMaterielById(deliveryMateriel.getSerialNum());
        	
        	if(StringUtils.isEmpty(deliveryMateriel.getMaterielNum())){
        		deliveryMateriel =deliveryService.selectDeliveryMaterielById2(deliveryMateriel.getSerialNum());	
        	}
        	
    	return new ResponseEntity<DeliveryMaterielVO>(deliveryMateriel, HttpStatus.CREATED);
    }
    
    
    /**
     * @Description (编辑订单物料信息)
     * @param request
     * @return
     */
    @RequestMapping(value="editDeliveryMateriel",method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<DeliveryMaterielVO>> editDeliveryMateriel(Map<String, Object> map,HttpServletRequest request,String params) {
    	JSONArray array = JSONArray.fromObject(params); 
    	List<DeliveryMaterielVO> list = JSONArray.toList(array, DeliveryMaterielVO.class);//
    	String deliverSerial=null;
    	if(list.size()>0){
    		deliverSerial=list.get(0).getDeliverSerial();
    		deliveryService.deleteOldDeliveryMateriel2(deliverSerial);
    	}

    	Subject currentUser = SecurityUtils.getSubject();
    	String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
    	for(DeliveryMaterielVO deliveryMaterielVO:list){
    		deliveryMaterielVO.setSerialNum(ApplicationUtils.random32UUID());
    		deliveryMaterielVO.setCreator(currenLoginName);
    		deliveryMaterielVO.setUpdater(currenLoginName);
    		deliveryService.insertDeliveryMateriel(deliveryMaterielVO);

    		//附件
    		List<RelationFile> files=new ArrayList<RelationFile>();

    		//如果附件不为空时执行添加操作
    		if(!StringUtils.isEmpty(deliveryMaterielVO.getAttachFile())){
    			String attachFile[]=deliveryMaterielVO.getAttachFile().split("&");
    			for(String detail:attachFile){
    				RelationFile item=new RelationFile();
    				String attachFileDetail[]=detail.split(",");
    				String file=attachFileDetail[0];

    				//描述不为空时添加描述
    				String describe=null;
    				if(attachFileDetail.length>1){
    					if(attachFileDetail[1]!=null){
    						describe=attachFileDetail[1];	
    					}	
    				}


    				item.setSerialNum(ApplicationUtils.random32UUID());
    				item.setRelationSerial(deliveryMaterielVO.getSerialNum());
    				item.setFileType("delivery");
    				item.setFileDescribe(describe);
    				item.setFile(file);
    				item.setUploader(currenLoginName);
    				item.setCreator(currenLoginName);
    				item.setUpdater(currenLoginName);
    				files.add(item);
    			}

    			//批量添加附件
    			deliveryService.insertAttachFiles(files);
    		}
    	}

    	List<DeliveryMaterielVO> deliveryMateriels=null;
    	deliveryMateriels = deliveryService.selectListForDetail(deliverSerial);
    	if(deliveryMateriels.size()>0){
    		String materielNum=deliveryMateriels.get(0).getMaterielNum();
    		if(StringUtils.isEmpty(materielNum)){
    			deliveryMateriels = deliveryService.selectListForDetail2(deliverSerial);	
    		}	
    	}

    	return new ResponseEntity<List<DeliveryMaterielVO>>(deliveryMateriels, HttpStatus.CREATED);
    }
    
    
    
    
    /**
     * 查询仓库地址
     * @param warehouseSerial
     * @return
     */
    @RequestMapping(value="selectAddress",method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Warehouse> selectAddress(String warehouseSerial){
    	
    	Warehouse warehouse=deliveryService.queryAddressById(warehouseSerial);
    	
    	return new ResponseEntity<Warehouse>(warehouse, HttpStatus.OK);
    }
    
    
    /**
     * 保存基本信息
     * @param delivery
     * @param deliveryTransport
     * @param takeDeliveryVO
     * @return
     */
    @RequestMapping(value="saveBasicInfo",method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<DeliveryVO>  saveBasicInfo(DeliveryVO delivery,DeliveryTransportVO deliveryTransport,TakeDeliveryVO takeDeliveryVO){
    	//保存基本信息第一部分
    	delivery.setSerialNum(ApplicationUtils.random32UUID());
    	Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
		
		User user = UserUtil.getUserFromSession();
    	String comId = null;
		comId = userCompanyService.getUserComId(String.valueOf(user.getUserId()));
    	if(StringUtils.isEmpty(comId)){
    		delivery.setSupplyComId(null);
    		delivery.setShipper(null);
    	}else{
    		Company company=deliveryService.selectCompanyInfo(comId);
       		delivery.setSupplyComId(comId);
    	}
		
		delivery.setCreator(currenLoginName);
    	deliveryService.insertBasicInfo(delivery);
    	
    	//保存基本信息第二部分
    	deliveryTransport.setSerialNum(ApplicationUtils.random32UUID());
    	deliveryTransport.setCreator(currenLoginName);
    	deliveryTransport.setDeliverSerial(delivery.getSerialNum());
    	deliveryService.insertBasicInfoPartII(deliveryTransport);
    	
    	//保存基本信息第三部分
    	takeDeliveryVO.setSerialNum(ApplicationUtils.random32UUID());
    	takeDeliveryVO.setDeliverSerial(delivery.getSerialNum());
    	takeDeliveryVO.setCreator(currenLoginName);
    	deliveryService.insertBasicInfoPartIII(takeDeliveryVO);
    	
    	//保存之后查询
    	delivery=deliveryService.selectDetailById(delivery.getSerialNum());
    	if(StringUtils.isEmpty(delivery.getOrderNum())){
    	delivery=deliveryService.selectDetailById2(delivery.getSerialNum());	
    	}
    	return new ResponseEntity<DeliveryVO>(delivery, HttpStatus.OK);
    }
    
    
    /**
     * 编辑基本信息
     * @param delivery
     * @param deliveryTransport
     * @param takeDeliveryVO
     * @return
     */
    @RequestMapping(value="editBasicInfo",method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<DeliveryVO>  editBasicInfo(DeliveryVO delivery,DeliveryTransportVO deliveryTransport,TakeDeliveryVO takeDeliveryVO){
    	//保存基本信息第一部分
    	String transportserialNum=delivery.getTransportserialNum();
    	String takeDeliverSerialNum=delivery.getTakeDeliverSerialNum();
    	
    	Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
		delivery.setUpdater(currenLoginName);
		delivery.setStatus("0");
    	deliveryService.updateBasicInfo(delivery);
    	
    	
    	//保存基本信息第二部分
    	deliveryTransport.setSerialNum(transportserialNum);
    	deliveryTransport.setUpdater(currenLoginName);
    	deliveryService.updateBasicInfoPartII(deliveryTransport);
    	
    	//保存基本信息第三部分
    	takeDeliveryVO.setSerialNum(takeDeliverSerialNum);
    	takeDeliveryVO.setUpdater(currenLoginName);
    	deliveryService.updateBasicInfoPartIII(takeDeliveryVO);
    	
    	//更新之后查询
    	delivery=deliveryService.selectDetailById(delivery.getSerialNum());
    	if(StringUtils.isEmpty(delivery.getOrderNum())){
    	delivery=deliveryService.selectDetailById2(delivery.getSerialNum());	
    	}
    	return new ResponseEntity<DeliveryVO>(delivery, HttpStatus.OK);
    }
    
    
    /**
	 * 
	 * @Description 调整发货申请
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
			@Valid DeliveryVO record) throws Exception{
		
		String result = "";
		User user = UserUtil.getUserFromSession();		
		record.setUserId(user.getUserId());
		record.setUser_name(user.getUserName());
		record.setBusinessType(BaseVO.ACCOUNTDELIVERY);
		record.setApplyDate(new Date());
		record.setBusinessKey(record.getSerialNum());
		record.setProcessInstanceId(record.getProcessInstanceId());
        Map<String, Object> variables = new HashMap<String, Object>();
        String content = "";
        if(reApply){
        	//修改请假申请
        	record.setTitle(user.getUserName()+" 的发货申请！");
        	record.setStatus(BaseVO.PENDING);
	        content = "重新申请";
	        //由userTask自动分配审批权限
//	        if(vacation.getDays() <= 3){
//            	variables.put("auditGroup", "manager");
//            }else{
//            	variables.put("auditGroup", "director");
//            }
	        result = "任务办理完成，发货申请已重新提交！";
        }else{
        	record.setTitle(user.getUserName()+" 的应付款申请已取消！");
        	record.setStatus(BaseVO.APPROVAL_FAILED);
        	content = "取消申请";
        	result = "任务办理完成，已经取消您的发货申请！";
        }
        try {
        	//保存基本信息第一部分
        	String transportserialNum=record.getTransportserialNum();
        	String takeDeliverSerialNum=record.getTakeDeliverSerialNum();
        	
        	Subject currentUser = SecurityUtils.getSubject();
    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
    		record.setUpdater(currenLoginName);
        	deliveryService.updateBasicInfo(record);
        	
        	
        	/*//保存基本信息第二部分
        	deliveryTransport.setSerialNum(transportserialNum);
        	deliveryTransport.setUpdater(currenLoginName);
        	deliveryService.updateBasicInfoPartII(deliveryTransport);
        	
        	//保存基本信息第三部分
        	takeDeliveryVO.setSerialNum(takeDeliverSerialNum);
        	takeDeliveryVO.setUpdater(currenLoginName);
        	deliveryService.updateBasicInfoPartIII(takeDeliveryVO);*/
        	
        	//保存之后查询
        	record=deliveryService.selectDetailById(record.getSerialNum());
        	
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
   	 * 
   	 * @Description 获取发货详情信息
   	 * @param ids
   	 * @return
   	 */
	@RequestMapping(value = "/getDeliveryInfo")
	@ResponseBody
	public Map<String, Object> getDeliveryInfo(String serialNum) {
		DeliveryVO delivery=null;
		delivery=deliveryService.selectDetailById(serialNum);
		if(StringUtils.isEmpty(delivery.getOrderNum())){
			delivery=deliveryService.selectDetailById2(delivery.getSerialNum());	
		}
		Map<String, Object> map = new HashMap<String, Object>();
		String suppluComId=delivery.getSupplyComId();
		Company company=deliveryService.selectCompanyInfo(suppluComId);
		if(company!=null){
			delivery.setSupplyComId(company.getComName());
			delivery.setShipper(company.getComName());
		}
		map.put("delivery", delivery);

		List<DeliveryMaterielVO> deliveryMateriels=null;
		deliveryMateriels = deliveryService.selectListForDetail(serialNum);
		if(deliveryMateriels.size()>0){
			String materielNum=deliveryMateriels.get(0).getMaterielNum();
			if(StringUtils.isEmpty(materielNum)){
			deliveryMateriels = deliveryService.selectListForDetail2(serialNum);	
			}	
		}
		
		for(DeliveryMaterielVO deliveryMaterielVO:deliveryMateriels){
			String attachFile="";
			List<RelationFile> files=deliveryService.getAttachFileInfo(deliveryMaterielVO.getSerialNum());
			for(RelationFile relationFile:files){
				String file=relationFile.getFile();
				String remark=relationFile.getRemark();
				attachFile=attachFile+file+","+remark+"&";
			}
			deliveryMaterielVO.setAttachFile(attachFile);
			deliveryMaterielVO.setFiles(files);
		}
		
		map.put("deliveryMateriels", deliveryMateriels);
		return map;
	}
   	
   	
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
		DeliveryVO delivery=deliveryService.selectDetailById(serialNum);
		delivery.setUserId(user.getUserId());
		delivery.setUser_name(user.getUserName());
		delivery.setUpdater(user.getUserName());
		delivery.setTitle(user.getUserName()+"的发货申请");
		delivery.setBusinessType(BaseVO.ACCOUNTDELIVERY);
		delivery.setStatus(BaseVO.PENDING);
		delivery.setApplyDate(new Date());
		delivery.setBusinessKey(serialNum);
		delivery.setReason(reason);

		try {
			String processInstanceId = this.processService
					.startAccountDeliveryable(delivery);
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
			/*PaymentRecord paymentRecord = this.payService
					.selectPayById(serialNum);
			PaymentRecord basePaymentRecord = (PaymentRecord) this.runtimeService
					.getVariable(paymentRecord.getProcessInstanceId(), "entity");*/
			
			DeliveryVO delivery=deliveryService.selectDetailById(serialNum);
			
			String orderSerial=delivery.getOrderSerial();
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("serialNum", serialNum);
			map.put("updater", user.getUserId());
			map.put("orderSerial", orderSerial);
			deliveryService.updateOrderWhenDeliveryComlete(map);
			DeliveryVO baseDelivery = (DeliveryVO) this.runtimeService.getVariable(delivery.getProcessInstanceId(), "entity");
					
			
			Map<String, Object> variables = new HashMap<String, Object>();
			variables.put("isPass", completeFlag);
			if (!completeFlag) {
				baseDelivery.setTitle(baseDelivery.getUser_name()
						+ " 的发货申请失败,需修改后重新提交！");
				delivery.setStatus(BaseVO.APPROVAL_FAILED);
				variables.put("entity", baseDelivery);
			}
			// 完成任务
			this.processService.complete(taskId, content, user.getUserId()
					.toString(), variables);

			if (completeFlag) {
				// 此处需要修改，不能根据人来判断审批是否结束。应该根据流程实例id(processInstanceId)来判定。
				// 判断指定ID的实例是否存在，如果结果为空，则代表流程结束，实例已被删除(移到历史库中)
				ProcessInstance pi = this.runtimeService
						.createProcessInstanceQuery()
						.processInstanceId(delivery.getProcessInstanceId())
						.singleResult();
				if (BeanUtils.isBlank(pi)) {
					delivery.setStatus("3");
				}
			}

			delivery.setReason(content);
			this.deliveryService.updateBasicInfo(delivery);

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
	 * @Description (导出发货信息)
	 * @param request
	 * @return
	 */
	@RequestMapping("exportDelivery")
	public void exportDelivery(Map<String, Object> map,HttpServletRequest request,HttpServletResponse response) {
/*		Map<String, Object> dataMap = new HashMap<String, Object>();
		Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名 
		List<DeliveryVO> deliveryList=deliveryService.findAllDeliveryList(currenLoginName);*/
		Map<String, Object> dataMap = new HashMap<String, Object>();
		Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名 
		//List<DeliveryVO> contractList=deliveryService.findAllDeliveryList(currenLoginName);
		User user = UserUtil.getUserFromSession();
    	List<String> comIds = new ArrayList<String>();
    	if(user!=null){
			comIds = userCompanyService.getComIdsByUserId(String.valueOf(user.getUserId()));
		}
		DeliveryVO query = new DeliveryVO();
		query.setCreator(currenLoginName);
		query.setSupplyComIds(comIds);
		List<DeliveryVO> contractList=deliveryService.findAllDeliveryList(query);

		dataMap.put("deliveryList",contractList);
		ExcelUtil.export(request, response, dataMap, "delivery", "发货信息");
	}
	
	
	@RequestMapping("/viewDelivery")
    public String viewDelivery() {
        return "delivery/viewDelivery";
    }
	
	
	@RequestMapping("/applyDelivery")
    public String applyDelivery() {
        return "delivery/applyDelivery";
    }
	
	
	@RequestMapping("/auditDelivery")
    public String auditDelivery() {
        return "delivery/auditDelivery";
    }


	 /**
     * 添加发货页面
     * @return 添加发货页面url
     */
    @RequestMapping("/addDelivery")
    public String addDelivery() {
    	User user = UserUtil.getUserFromSession();
    	String comId = null;
		comId = userCompanyService.getUserComId(String.valueOf(user.getUserId()));
        return "delivery/addDelivery";
    }
    
    /**
     * 查询仓库地址
     * @param warehouseSerial
     * @return
     */
    @RequestMapping(value="getSupplyComId",method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Company> getSupplyComId(String warehouseSerial){
    	
    	User user = UserUtil.getUserFromSession();
    	String comId = null;
		comId = userCompanyService.getUserComId(String.valueOf(user.getUserId()));
		
		
		Company company=deliveryService.selectCompanyInfo(comId);
    	
    	return new ResponseEntity<Company>(company, HttpStatus.OK);
    }
    
    
    

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
		DeliveryVO deliveryVO = (DeliveryVO) this.runtimeService
				.getVariable(pi.getId(), "entity");
		deliveryVO.setTask(task);
		deliveryVO.setProcessInstanceId(processInstanceId);
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
		map.put("deliveryVO", deliveryVO);
		map.put("commentList", commentList);
		return map;
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
	 * 删除发货
	 * @param contractVO
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/deleteDeliveryS", method = RequestMethod.POST)
	public ResponseEntity<Void> deleteDeliveryS(@RequestBody String ids) {
		if ("".equals(ids) || ids == null) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		deliveryService.deleteDeliveryS(ids); 
		return new ResponseEntity<Void>(HttpStatus.OK);
	}


	/**
	 * 通过id查询合同详情
	 * @param ids（前台所传递的id）
	 * @return
	 */
	@RequestMapping(value = "/selectConbtractById", method = RequestMethod.GET)
	public ResponseEntity<ContractVO> selectConbtractById(String ids) {

		ContractVO c=contractService.selectConbtractById(ids);
		return new ResponseEntity<ContractVO>(c, HttpStatus.OK);
	}

	/**
	 * 跳转到编辑页面
	 * @return
	 */
	@RequestMapping(value = "/editDeliveryPage")
	public String editDeliveryPage(String id,String view) {
		return "delivery/editDeliveryPage";
	}
	
	
	/**
	 * 跳转到调整页面
	 * @return
	 */
	@RequestMapping(value = "/editAuditDelivery")
	public String editAuditDelivery(String id,String view) {
		return "delivery/editAuditDelivery";
	}
	
	
	 /**
     * @Description (下载导入模板)
     * @param request
     * @return
     */
    @RequestMapping("downloadImportTemp")
    public void downloadContractTemp(Map<String, Object> map,HttpServletRequest request,HttpServletResponse response) {
    	ExcelUtil.importTempDownLoad(request, response, "contractImport");
    }
    
    
    /**
     * @Description (合同信息导入)
     * @param request
     * @return
     */
    @RequestMapping("contractImport")
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
    }



	@RequestMapping(value="/resourceDownload",method=RequestMethod.POST) //匹配的是href中的download请求
	public void download(@RequestParam("project_id") Integer projectId, HttpServletResponse response) throws IOException {



	}
}
