package com.congmai.zhgj.web.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JsonConfig;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.congmai.zhgj.core.feature.orm.mybatis.Page;
import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.core.util.BeanUtils;
import com.congmai.zhgj.core.util.Constants;
import com.congmai.zhgj.core.util.ExcelUtil;
import com.congmai.zhgj.core.util.MessageConstants;
import com.congmai.zhgj.core.util.SendEmail;
import com.congmai.zhgj.core.util.StringUtil;
import com.congmai.zhgj.core.util.UserUtil;
import com.congmai.zhgj.web.dao.Delivery2Mapper;
import com.congmai.zhgj.web.dao.StockInOutRecordMapper;
import com.congmai.zhgj.web.dao.TakeDeliveryMapper;
import com.congmai.zhgj.web.enums.StaticConst;
import com.congmai.zhgj.web.event.EventExample;
import com.congmai.zhgj.web.event.SendMessageEvent;
import com.congmai.zhgj.web.model.BaseVO;
import com.congmai.zhgj.web.model.CommentVO;
import com.congmai.zhgj.web.model.CompanyContact;
import com.congmai.zhgj.web.model.Delivery;
import com.congmai.zhgj.web.model.DeliveryMateriel;
import com.congmai.zhgj.web.model.DeliveryMaterielExample;
import com.congmai.zhgj.web.model.DeliveryMaterielVO;
import com.congmai.zhgj.web.model.DeliveryVO;
import com.congmai.zhgj.web.model.OrderInfo;
import com.congmai.zhgj.web.model.OrderInfoExample;
import com.congmai.zhgj.web.model.OrderMateriel;
import com.congmai.zhgj.web.model.OrderMaterielExample;
import com.congmai.zhgj.web.model.RelationFile;
import com.congmai.zhgj.web.model.StockExample;
import com.congmai.zhgj.web.model.StockInOutRecord;
import com.congmai.zhgj.web.model.TakeDelivery;
import com.congmai.zhgj.web.model.TakeDeliveryExample;
import com.congmai.zhgj.web.model.TakeDeliveryParams;
import com.congmai.zhgj.web.model.TakeDeliverySelectExample;
import com.congmai.zhgj.web.model.TakeDeliverySelectExample.Criteria;
import com.congmai.zhgj.web.model.User;
import com.congmai.zhgj.web.model.Warehouse;
import com.congmai.zhgj.web.model.WarehouseExample;
import com.congmai.zhgj.web.model.Warehouseposition;
import com.congmai.zhgj.web.model.WarehousepositionExample;
import com.congmai.zhgj.web.service.DeliveryMaterielService;
import com.congmai.zhgj.web.service.DeliveryService;
import com.congmai.zhgj.web.service.DeliveryTransportService;
import com.congmai.zhgj.web.service.IProcessService;
import com.congmai.zhgj.web.service.OrderMaterielService;
import com.congmai.zhgj.web.service.OrderService;
import com.congmai.zhgj.web.service.RelationFileService;
import com.congmai.zhgj.web.service.StockService;
import com.congmai.zhgj.web.service.TakeDeliveryService;
import com.congmai.zhgj.web.service.UserCompanyService;
import com.congmai.zhgj.web.service.WarehouseService;
import com.congmai.zhgj.web.service.WarehousepositionService;


/**
 * @ClassName TakeDeliveryController
 * @Description TODO(收货控制器)
 * @author likt
 * @Date 2017年8月22日 下午2:31:00
 * @version 1.0.0
 */
@Controller
@RequestMapping("takeDelivery")
public class TakeDeliveryController {
	
	 private Logger logger = Logger.getLogger(TakeDeliveryController.class);
	
	@Autowired
	private DeliveryMaterielService deliveryMaterielService;
	
	@Autowired
	private DeliveryTransportService deliveryTransportService;
	
	@Autowired
	private TakeDeliveryService takeDeliveryService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderMaterielService orderMaterielService;
	
	@Autowired
	private WarehouseService warehouseService;
	
	@Autowired
	private WarehousepositionService warehousepositionService; 
	
	@Autowired
	private RelationFileService relationFileService;
	
	@Autowired
	protected RuntimeService runtimeService;
	
    @Autowired
    protected IdentityService identityService;
	@Autowired
	protected TaskService taskService;

	@Autowired
	private IProcessService processService;
	
	@Autowired
	private UserCompanyService userCompanyService;
	
	@Autowired
	private StockService stockService;  
	
	@Autowired
	private Delivery2Mapper delivery2Mapper;  
	
	@Autowired
	private TakeDeliveryMapper takeDeliveryMapper;  
	
	@Autowired
	private DeliveryService  deliveryService;
	@Autowired
	private StockInOutRecordMapper stockInOutRecordMapper;  
	@RequestMapping("takeDeliveryManage")
	public String takeDeliveryManage() {
		
		return "takeDelivery/takeDeliveryManage";
	}
	
	   /**
     * @Description (获取列表数据)
     * @param request
     * @return
     */
    @RequestMapping(value="takeDeliveryList")
    public ResponseEntity<Map<String,Object>> takeDeliveryList(Map<String, Object> map,HttpServletRequest request,@RequestBody String params,Delivery takeDelivery,String status,String noInit) {
    	//远程分页代码
    	/*try {
    		params = URLDecoder.decode(params, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch blockr
			e1.printStackTrace();
		}
    	 ObjectMapper objectMapper = new ObjectMapper();
    	 objectMapper.configure(org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  	   		DataTablesParams  dataTablesParams = null;
		   try {
			   JSONObject a = JSONObject.fromObject(params);
			   dataTablesParams = objectMapper.readValue(params,DataTablesParams.class);
			} catch (JsonParseException e) {
				//20180110 qhzhao System.out.println(this.getClass()+"---------"+ e.getMessage());
			} catch (JsonMappingException e) {
				//20180110 qhzhao System.out.println(this.getClass()+"---------"+ e.getMessage());
			} catch (IOException e) {
				//20180110 qhzhao System.out.println(this.getClass()+"---------"+ e.getMessage());
			} catch (Exception e) {
		    	//20180110 qhzhao System.out.println(this.getClass()+"---------"+ e.getMessage());
			}*/
    	takeDelivery.setPageIndex(0);
    	takeDelivery.setPageSize(-1);
    	if(StringUtils.isNotBlank(status)){
    		takeDelivery.setStatus(status);
    	}
    	if("1".equals(noInit)){//不查询初始化状态的发货
    		takeDelivery.setStatus("noInit");
		}else if("1buy".equals(noInit)){
			//takeDelivery.setDeliverType("采购发货");
			takeDelivery.setStatus("noInit");
		}
    	User user = UserUtil.getUserFromSession();
		String comId = null;
		if(user !=null){
			comId = userCompanyService.getUserComId(String.valueOf(user.getUserId()));//获取用户的企业ID
		}
		/*if(){
			
		}*/
//		takeDelivery.setBuyComId(comId);
		if(StringUtil.isEmpty(comId)){
			takeDelivery.setBuyComId(comId);
		}else{
			takeDelivery.setSupplyComId(comId);
		}
		
	/*	if("1buy".equals(noInit)){
			takeDelivery.setBuyComId(null);
			takeDelivery.setSupplyComId(comId);
		}*/
    	Page<Delivery> takeDeliverys = takeDeliveryService.selectByPage(takeDelivery);
    	if(CollectionUtils.isNotEmpty(takeDeliverys.getResult())){
    		List<Delivery>list=takeDeliverys.getResult();
    		for(Delivery d:list){
    			d.setMaterielTotalCount(deliveryService.getDeliveryTotalCount(d.getSerialNum()));
    		}
    	}
    	//List<Company> companys = companyService.selectByPage(company).getResult();
		// 封装datatables数据返回到前台
		Map<String,Object> pageMap = new HashMap<String,Object>();
		pageMap.put("draw", 1);
		pageMap.put("recordsTotal", takeDelivery==null?0:takeDeliverys.getTotalCount());
		pageMap.put("recordsFiltered", takeDelivery==null?0:takeDeliverys.getTotalCount());
		pageMap.put("data", takeDeliverys.getResult());
		return new ResponseEntity<Map<String,Object>>(pageMap, HttpStatus.OK);
    }
    
	 /**
     * @Description (收货新增页面)
     * @param request
     * @return
     */
    @RequestMapping("takeDeliveryAdd")
    public String takeDeliveryAdd(HttpServletRequest request) {
        return "takeDelivery/takeDeliveryAdd";
    }
    

    

	 /**
     * @Description (收货查看页面)
     * @param request
     * @return
     */
    @RequestMapping("takeDeliveryView")
    public String takeDeliveryView(HttpServletRequest request) {
        return "takeDelivery/takeDeliveryView";
    }
    
    /**
     * @Description (收货页面)
     * @param request
     * @return
     */
    @RequestMapping("takeDelivery")
    public String takeDelivery(HttpServletRequest request) {
    	return "takeDelivery/takeDelivery";
    }
    
    /**
     * @Description (收货审批)
     * @param request
     * @return
     */
    @RequestMapping("takeDeliveryAudit")
    public String takeDeliveryAudit(HttpServletRequest request) {
    	return "takeDelivery/takeDeliveryAudit";
    }
    
    /**
     * @Description (重新申请)
     * @param request
     * @return
     */
    @RequestMapping("takeDeliveryAdjustment")
    public String takeDeliveryAdjustment(HttpServletRequest request) {
    	return "takeDelivery/takeDeliveryAdjustment";
    }

    /**
     * @Description (收货查看页面)
     * @param request
     * @return
     */
    @RequestMapping("getTakeDeliveryInfo")
    @ResponseBody
    public Delivery getTakeDeliveryInfo(HttpServletRequest request,String serialNum) {
    	
    	Delivery delivery = takeDeliveryService.selectByTakeDeliveryPrimaryKey(serialNum);
    	if(delivery!=null&&CollectionUtils.isNotEmpty(delivery.getDeliveryMateriels())){
    		for(DeliveryMateriel deliveryMaterielVO:delivery.getDeliveryMateriels()){
    			String attachFile="";
    			List<RelationFile> files=relationFileService.getAttachFileInfo("takeDelivery",deliveryMaterielVO.getSerialNum());
    			for(RelationFile relationFile:files){
    				String file=relationFile.getFile();
    				String remark=relationFile.getRemark();
    				attachFile=attachFile+file+","+remark+"&";
    			}
    			deliveryMaterielVO.setAttachFile(attachFile);
    			deliveryMaterielVO.setFiles(files);
    			
    			String deliveryAttachFile="";
    			List<RelationFile> deliveryFiles=relationFileService.getAttachFileInfo("delivery",deliveryMaterielVO.getSerialNum());
    			for(RelationFile relationFile:deliveryFiles){
    				String file=relationFile.getFile();
    				String remark=relationFile.getRemark();
    				deliveryAttachFile=deliveryAttachFile+file+","+remark+"&";
    			}
    			deliveryMaterielVO.setDeliveryAttachFile(deliveryAttachFile);
    			deliveryMaterielVO.setDeliveryFiles(deliveryFiles);
    		}
    		StockInOutRecord sr=takeDeliveryService.findStockInSerialNum(serialNum);//根据收货流水查入库单记录
        	if(delivery.getOrderSerial()!=null&&sr!=null){
        		OrderInfo o=orderService.selectById(delivery.getOrderSerial());
        		sr.setOrder(o);
    			if(StringUtil.isNotEmpty(o.getContractContent())&&"1".equals(o.getContractContent().substring(4, 5))){//	有验收条款
    				delivery.setHasCheckData(true);
    			}
        		delivery.setStockInOutRecord(sr);
        	}
    	}
    	
    	return delivery;
    }
    
	    
    /**
     * @Description (保存)
     * @param request
     * @return
     */
    @RequestMapping(value="saveTakeDelivery",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object>  saveTakeDelivery(Map<String, Object> map,@RequestBody String params,HttpServletRequest request) {
    	String flag ="0"; //默认失败

    	 ObjectMapper objectMapper = new ObjectMapper();
    	 objectMapper.configure(org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  	   		TakeDeliveryParams  takeDeliveryParams = null;
  	   	Map<String, Object>map1 =new HashMap<String, Object>();
  	  String  takeDeliverAddress="";
		   try {
			   //JSONObject a = JSONObject.fromObject(params);
			  // takeDeliveryParams = objectMapper.readValue(params,TakeDeliveryParams.class);
			   takeDeliveryParams = JSON.parseObject(params, TakeDeliveryParams.class);
			   takeDeliverAddress=takeDeliveryParams.getTakeDelivery().getTakeDeliverAddress();
			} catch (Exception e) {
		    	logger.warn(e.getMessage(), e);
			}
        	try{
        		Subject currentUser = SecurityUtils.getSubject();
        		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
        		//保存/更新前判断发货数量
        		OrderInfo o=orderService.selectById(takeDeliveryParams.getDelivery().getOrderSerial());
        		  Boolean flags=false;//还可以发
        		  Boolean isDel=false;//是否删除发货单
        			String  deliveryCount=o.getDeliveryCount();//订单已发数量
        			String  materielCount=o.getMaterielCount();//订单总数量
        			  BigDecimal deliveryCount1=new BigDecimal(deliveryCount);
        			  BigDecimal materielCount1=new BigDecimal(materielCount);
        			  if(deliveryCount1.compareTo(materielCount1)>=0){
        				  flags=true;
        				  isDel=true;//提示删除当前发货单
        				  map1.put("isDel", isDel);
        				  map1.put("flag", flags);
        				  return map1;
        			  }else{
        				  map1.put("isDel", isDel);
        			  }
        				OrderMaterielExample m =new OrderMaterielExample();
        		    	//and 条件1
        		    	com.congmai.zhgj.web.model.OrderMaterielExample.Criteria criteria =  m.createCriteria();
        		    	criteria.andDelFlgEqualTo("0");
        		    	criteria.andOrderSerialEqualTo(takeDeliveryParams.getDelivery().getOrderSerial());
        		    	m.setOrderByClause(" sort asc");
        		    	List<OrderMateriel> orderMateriel = orderMaterielService.selectList(m);//获取最新的已发
        		    	List<DeliveryMateriel> deliveryMateriels=takeDeliveryParams.getDeliveryMateriels();//当前需要保存的发货物料
        		  	  for(DeliveryMateriel deliveryMateriel: deliveryMateriels){
        				  for(OrderMateriel vo:orderMateriel){
        					  if(deliveryMateriel.getOrderMaterielSerial().equals(vo.getSerialNum())){//两个订单物料流水相同时
        						  BigDecimal deliverCount=new BigDecimal(deliveryMateriel.getDeliverCount());
        						  BigDecimal canDeliverCount=new BigDecimal(vo.getAmount()).subtract(new BigDecimal(vo.getDeliveredCount()==null?"0":vo.getDeliveredCount()));//可发数量=物料数量-已发数量
        						  if(deliverCount.compareTo(canDeliverCount)>0){
        							  flags=true;
        							  deliveryMateriel.setDeliveredCount(vo.getDeliveredCount());//更新成最新的已发数量
        							  deliveryMateriel.setDeliverCount("0");//发货数量置为0
        							  map1.put("deliveryMateriels", orderMateriel);//重新将最新的发货数据传到前台
        							  map1.put("flag", flags);
        							  return map1;
        						  }
        					  }
        				  }
        			  }
        		if(StringUtils.isNotEmpty(takeDeliveryParams.getTakeDelivery().getSerialNum())){
        			takeDeliveryParams = this.getTakeDeliveryData(takeDeliveryParams,currenLoginName);
        			takeDeliveryService.updateTakeDelivery(takeDeliveryParams.getDelivery(),takeDeliveryParams.getTakeDelivery(),takeDeliveryParams.getDeliveryTransport(),takeDeliveryParams.getDeliveryMateriels(),currenLoginName);
        		}else{
        			takeDeliveryParams = this.getTakeDeliveryData(takeDeliveryParams,currenLoginName);
        			takeDeliveryService.insertTakeDelivery(takeDeliveryParams.getDelivery(),takeDeliveryParams.getTakeDelivery(),takeDeliveryParams.getDeliveryTransport(),takeDeliveryParams.getDeliveryMateriels(),currenLoginName);
        		}
        		flag = "1";
        	}catch(Exception e){
        		logger.warn(e.getMessage(), e);
        		return null;
        	}
        	//deliver.warehouseSerial
        	if(!StringUtils.isEmpty(takeDeliveryParams.getTakeDelivery().getWarehouseSerial())){
        		String takeDeliverWarehouseName=warehouseService.selectOne(takeDeliveryParams.getTakeDelivery().getWarehouseSerial()).getWarehouseName();
        		takeDeliveryParams.getDelivery().setTakeDeliverWarehouseName(takeDeliverWarehouseName);
        	}
        	map1.put("delivery", takeDeliveryParams.getDelivery());
        	takeDeliveryParams.getTakeDelivery().setTakeDeliverAddress(takeDeliverAddress);
        	map1.put("takeDelivery", takeDeliveryParams.getTakeDelivery());
        	map1.put("deliveryTransport", takeDeliveryParams.getDeliveryTransport());
    	return map1;
    }
    
    /**
     * @Description (确认收货)
     * @param request
     * @return
     */
    @RequestMapping(value="confirmTakeDelivery",method=RequestMethod.POST)
    @ResponseBody
    public String confirmTakeDelivery(Map<String, Object> map,@RequestBody String params,HttpServletRequest request) {
    	String flag ="0"; //默认失败
    	
    	ObjectMapper objectMapper = new ObjectMapper();
    	objectMapper.configure(org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    	TakeDeliveryParams  takeDeliveryParams = null;
    	try {
    		takeDeliveryParams = JSON.parseObject(params, TakeDeliveryParams.class);
    	} catch (Exception e) {
    		logger.warn(e.getMessage(), e);
    	}
    	try{
    		Subject currentUser = SecurityUtils.getSubject();
    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
    		takeDeliveryParams = this.getConfirmTakeDeliveryData(takeDeliveryParams,currenLoginName);
    		takeDeliveryService.confirmTakeDelivery(takeDeliveryParams.getTakeDelivery(),takeDeliveryParams.getDeliveryMateriels(),currenLoginName);
    		
    		//收货消息
    		EventExample.getEventPublisher().publicSendMessageEvent(new SendMessageEvent(takeDeliveryParams,MessageConstants.TAKE_DELIVERY));
    		
    		flag = "1";
    	}catch(Exception e){
    		logger.warn(e.getMessage(), e);
    		return null;
    	}
    	return flag;
    }
    
    
    /**
     * @Description (删除收货计划信息)
     * @param request
     * @return
     */
    @RequestMapping(value="deleteTakeDelivery",method=RequestMethod.POST)
    @ResponseBody
    public String deleteTakeDelivery(Map<String, Object> map,@RequestBody String serialNums,HttpServletRequest request) {
    	String flag = "0"; //默认失败
    	try{
    		if(StringUtils.isNotEmpty(serialNums)){
    			List<String> serialNumArray  = Arrays.asList(serialNums.split(","));
    			takeDeliveryService.deleteBatch(serialNumArray);
    		}
    	}catch(Exception e){
    		logger.warn(e.getMessage(), e);
    		flag = "1";
    	}
    	return flag;
    }
    
    
    /**
     * @Description (导出收货计划)
     * @param request
     * @return
     */
    @RequestMapping("exportTakeDelivery")
    public void exportTakeDelivery(Map<String, Object> map,HttpServletRequest request,HttpServletResponse response,String serialNums) {
    		Map<String, Object> dataMap = new HashMap<String, Object>();
    		List<Delivery> takeDeliveryList = new ArrayList<Delivery>();
	if(StringUtils.isEmpty(serialNums)){
		Delivery takeDelivery = new Delivery();
		takeDelivery.setPageIndex(0);
    	takeDelivery.setPageSize(-1);
		 takeDeliveryList = takeDeliveryService.selectByPage(takeDelivery).getResult();
		 for(Delivery d:takeDeliveryList){
 			d.setMaterielTotalCount(deliveryService.getDeliveryTotalCount(d.getSerialNum()));
 		}
	}else{
		List<String> idList = ApplicationUtils.getIdList(serialNums);
		for(String id:idList){
			Delivery vo=takeDeliveryService.selectByTakeDeliveryPrimaryKey(id);
			vo.setMaterielTotalCount(deliveryService.getDeliveryTotalCount(vo.getSerialNum()));
			takeDeliveryList.add(vo);
		}
	}
    		dataMap.put("takeDeliveryList",takeDeliveryList);
    		ExcelUtil.export(request, response, dataMap, "takeDelivery", "收货计划");
    }
    
    
    /**
     * @Description (保存入库记录)
     * @param request
     * @return
     */
    @RequestMapping(value="saveStockInData",method=RequestMethod.POST)
    @ResponseBody
    public String saveStockInData(Map<String, Object> map,@RequestBody String params,HttpServletRequest request) {
    	String flag ="0"; //默认失败

    	
  	   	  TakeDeliveryParams  takeDeliveryParams = null;
		   try {
			   takeDeliveryParams = JSON.parseObject(params, TakeDeliveryParams.class);
			} catch (Exception e) {
		    	//20180110 qhzhao System.out.println(this.getClass()+"---------"+ e.getMessage());
				System.out.println(this.getClass()+"---------"+ e.getMessage());
			}
        	try{
        		Subject currentUser = SecurityUtils.getSubject();
        		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
        		takeDeliveryParams.getRecord().setStockDate(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(takeDeliveryParams.getRecord().getDateStock()));
        		if(StringUtils.isNotEmpty(takeDeliveryParams.getRecord().getSerialNum())){
        			/*takeDeliveryParams = getTakeDeliveryData(takeDeliveryParams, currenLoginName);*/
        			takeDeliveryService.updateStockInData(takeDeliveryParams.getRecord(),takeDeliveryParams.getDeliveryMateriels(),currenLoginName,"in");
        			if("1".equals(takeDeliveryParams.getRecord().getStatus())){
        			//入库消息  to 采购
//        			EventExample.getEventPublisher().publicSendMessageEvent(new SendMessageEvent(takeDeliveryParams,MessageConstants.IN_TO_BUY));
        			//入库消息  to 供应
//        			EventExample.getEventPublisher().publicSendMessageEvent(new SendMessageEvent(takeDeliveryParams,MessageConstants.IN_TO_SALE));
        			//入库完成的采购订单，通知关联的销售订单制单人
        				//1、通知采购订单的制单人
        				//2、通知关联采购计划的制单人
        				//3、通知关联销售订单的制单人
        			EventExample.getEventPublisher().publicSendMessageEvent(new SendMessageEvent(takeDeliveryParams,MessageConstants.IN_TO_BUY_TO_SALE));
        			
        			}
        		}else{
        		/*	takeDeliveryParams = getTakeDeliveryData(takeDeliveryParams, currenLoginName);*/
        			takeDeliveryService.insertStockInData(takeDeliveryParams.getRecord(),takeDeliveryParams.getDeliveryMateriels(),currenLoginName,"in");
        		}
        		flag = "1";
        	}catch(Exception e){
        		logger.warn(e.getMessage(), e);
        		 System.out.println(this.getClass()+"---------"+ e.getMessage());
        		return null;
        	}
    	return flag;
    }
    
    /**
     * @Description (保存出库记录)
     * @param request
     * @return
     */
    @RequestMapping(value="saveStockOutData",method=RequestMethod.POST)
    @ResponseBody
    public String saveStockOutData(Map<String, Object> map,@RequestBody String params,HttpServletRequest request) {
    	String flag ="0"; //默认失败

    	
  	   	  	TakeDeliveryParams  takeDeliveryParams = null;
			takeDeliveryParams = JSON.parseObject(params, TakeDeliveryParams.class);
        	try{
        		Subject currentUser = SecurityUtils.getSubject();
        		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
        		takeDeliveryParams.getRecord().setStockDate(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(takeDeliveryParams.getRecord().getDateStock()));
        		if(StringUtils.isNotEmpty(takeDeliveryParams.getRecord().getSerialNum())){//确认出库
        			//takeDeliveryParams = getTakeDeliveryData(takeDeliveryParams, currenLoginName);
        			takeDeliveryService.updateStockOutData(takeDeliveryParams.getRecord(),takeDeliveryParams.getDeliveryMateriels(),takeDeliveryParams.getStockOutMateriels(),currenLoginName,"out");
        			if("1".equals(takeDeliveryParams.getRecord().getStatus())){
        			//出库消息  to 采购
        			EventExample.getEventPublisher().publicSendMessageEvent(new SendMessageEvent(takeDeliveryParams,MessageConstants.OUT_TO_BUY));
        			//出库消息  to 供应
        			EventExample.getEventPublisher().publicSendMessageEvent(new SendMessageEvent(takeDeliveryParams,MessageConstants.OUT_TO_SALE));
        			//出库消息  to 销售组
        			EventExample.getEventPublisher().publicSendMessageEvent(new SendMessageEvent(takeDeliveryParams,MessageConstants.OUT_TO_SALE_GROUP));
        			}
        		}else{
        			//takeDeliveryParams = getTakeDeliveryData(takeDeliveryParams, currenLoginName);
        			takeDeliveryService.insertStockInData(takeDeliveryParams.getRecord(),takeDeliveryParams.getDeliveryMateriels(),currenLoginName,"out");
        		}
        		flag = "1";
        	}catch(Exception e){
        		logger.warn(e.getMessage(), e);
        		return null;
        	}
    	return flag;
    }


    /**
     * 
     * @Description (TODO新建入庫)
     * @param map
     * @param serialNum
     * @param request
     * @return
     */
    @RequestMapping(value="stockInAdd")
    public String stockInAdd(Map<String, Object> map,String serialNum,HttpServletRequest request) {
    	
    	return "takeDelivery/stockInAdd";
    }
    
    /**
     * 
     * @Description (TODO新建入庫)
     * @param map
     * @param serialNum
     * @param request
     * @return
     */
    @RequestMapping(value="stockIn")
    public String stockIn(Map<String, Object> map,String serialNum,HttpServletRequest request) {
    	
    	return "takeDelivery/stockIn";
    }
    
    /**
     * 
     * @Description (TODO查看入库)
     * @param map
     * @param serialNum
     * @param request
     * @return
     */
    @RequestMapping(value="stockInView")
    public String stockInView(Map<String, Object> map,String serialNum,HttpServletRequest request) {
    	
    	return "takeDelivery/stockInView";
    }
    
    /**
     * 
     * @Description (TODO这新建出库)
     * @param map
     * @param serialNum
     * @param request
     * @return
     */
    @RequestMapping(value="stockOutAdd")
    public String stockOutAdd(Map<String, Object> map,String serialNum,HttpServletRequest request) {
    	
    	return "delivery/stockOutAdd";
    }
    
    /**
     * 
     * @Description (TODO这新建出库)
     * @param map
     * @param serialNum
     * @param request
     * @return
     */
    @RequestMapping(value="stockOut")
    public String stockOut(Map<String, Object> map,String serialNum,HttpServletRequest request) {
    	
    	return "delivery/stockOut";
    }
    
    /**
     * 
     * @Description (TODO查看出库)
     * @param map
     * @param serialNum
     * @param request
     * @return
     */
    @RequestMapping(value="stockOutView")
    public String stockOutView(Map<String, Object> map,String serialNum,HttpServletRequest request) {
    	
    	return "delivery/stockOutView";
    }
    
    /**
     * @Description (入库查看)
     * @param request
     * @return
     */
    @RequestMapping("getStockInInfo")
    @ResponseBody
    public Map<String,Object> getStockInInfo(HttpServletRequest request,String serialNum) {
    	Map<String,Object>map=new HashMap<String,Object>();
    	StockInOutRecord stockInOutRecord= takeDeliveryService.selectStockInOutRecordByPrimayKey(serialNum,"in");
    	Delivery deliver=takeDeliveryService.selectByTakeDeliveryPrimaryKey(stockInOutRecord.getTakeDeliverSerial());
    	List<DeliveryMateriel> deliveryMateriels =deliver.getDeliveryMateriels();//获取发货数量
    	BigDecimal totalDeliveryCount=BigDecimal.ZERO;
    	if(CollectionUtils.isNotEmpty(deliveryMateriels)){
    		for(DeliveryMateriel dm:deliveryMateriels){
    			totalDeliveryCount=totalDeliveryCount.add(new BigDecimal(dm.getDeliverCount()));
    		}
    	}
    	map.put("stockInOutRecord", stockInOutRecord);
    	map.put("deliver", deliver);
    	map.put("totalDeliveryCount", totalDeliveryCount);
    	return map;
    }
    
    
    /**
     * @Description (收货流水查找入库记录流水号)
     * @param serialNum
     * @return
     */
    @RequestMapping(value="findStockInSerialNum",method=RequestMethod.POST)
    @ResponseBody
    public StockInOutRecord findStockInSerialNum(@RequestBody String serialNum) {
    	return takeDeliveryService.findStockInSerialNum(serialNum);
    }
    
    
    /**
     * @Description (查找进行中代发货单)
     * @param orderSerialNum
     * @return
     */
    @RequestMapping(value="getDoingTakeDelivery",method=RequestMethod.POST)
    @ResponseBody
    public Delivery getDoingTakeDelivery(@RequestBody String orderSerialNum) {
    	TakeDeliverySelectExample example = new TakeDeliverySelectExample();
		example.setPageIndex(0);
		example.setPageSize(-1);
		Criteria c2 =  example.createCriteria();
		c2.andStatusEqualTo("0");//状态为初始，只能是代发货
		c2.andDeliverTypeEqualTo("代发货");
		c2.andDelFlgEqualTo("0");
		c2.andDeliverBuyComIdIsNull();
		c2.andOrderEqualTo(orderSerialNum);
		
		List<Delivery> takeDeliverys = takeDeliveryMapper.selectListByExample(example);
		if(CollectionUtils.isNotEmpty(takeDeliverys)){
			return takeDeliverys.get(0);
		}else{
			return null;
		}
    	
    }
    /**
     * @Description (发货流水查找出库记录流水号)
     * @param serialNum
     * @return
     */
    @RequestMapping(value="findStockOutSerialNum",method=RequestMethod.POST)
    @ResponseBody
    public StockInOutRecord findStockOutSerialNum(@RequestBody String serialNum) {
    	return takeDeliveryService.findStockOutSerialNum(serialNum);
    }
    
    /**
     * @Description (出库查看)
     * @param request
     * @return
     */
    @RequestMapping("getStockOutInfo")
    @ResponseBody
    public Map<String,Object> getStockOutInfo(HttpServletRequest request,String serialNum) {
    	Map<String,Object>map=new HashMap<String,Object>();
    	StockInOutRecord stockInOutRecord= takeDeliveryService.selectStockInOutRecordByPrimayKey(serialNum,"out");
    	DeliveryVO delivery=deliveryService.selectDetailById(stockInOutRecord.getDeliverSerial());
		if(StringUtils.isEmpty(delivery.getOrderNum())){
			delivery=deliveryService.selectDetailById2(delivery.getSerialNum());	
		}
		List<DeliveryMaterielVO> deliveryMateriels =deliveryService.selectList(delivery.getSerialNum());//获取发货数量
    	BigDecimal totalDeliveryCount=BigDecimal.ZERO;
    	if(CollectionUtils.isNotEmpty(deliveryMateriels)){
    		for(DeliveryMaterielVO dm:deliveryMateriels){
    			totalDeliveryCount=totalDeliveryCount.add(new BigDecimal(dm.getDeliverCount()));
    		}
    	}
    	String orderSerial=delivery.getOrderSerial();//获取订单流水
    	OrderInfo o=orderService.selectById(orderSerial);
    	stockInOutRecord.setOrder(o);
    	map.put("stockInOutRecord", stockInOutRecord);
    	map.put("totalDeliveryCount", totalDeliveryCount);
    	map.put("deliver", delivery);
    	return map;
    }
    
    
	 /**
	  * @Description (获取列表数据)
	  * @param request
	  * @return
	  */
	 @RequestMapping(value="stockInList",method=RequestMethod.POST)
	 public ResponseEntity<Map<String,Object>> stockInList(Map<String, Object> map,HttpServletRequest request,@RequestBody String params,StockInOutRecord record) {
	 	//远程分页代码
	 	/*try {
	 		params = URLDecoder.decode(params, "UTF-8");
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	 	 ObjectMapper objectMapper = new ObjectMapper();
	 	 objectMapper.configure(org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		   		DataTablesParams  dataTablesParams = null;
			   try {
				   JSONObject a = JSONObject.fromObject(params);
				   dataTablesParams = objectMapper.readValue(params,DataTablesParams.class);
				} catch (JsonParseException e) {
					//20180110 qhzhao System.out.println(this.getClass()+"---------"+ e.getMessage());
				} catch (JsonMappingException e) {
					//20180110 qhzhao System.out.println(this.getClass()+"---------"+ e.getMessage());
				} catch (IOException e) {
					//20180110 qhzhao System.out.println(this.getClass()+"---------"+ e.getMessage());
				} catch (Exception e) {
			    	//20180110 qhzhao System.out.println(this.getClass()+"---------"+ e.getMessage());
				}*/
		 record.setPageIndex(0);
		 record.setPageSize(-1);
	 	Page<DeliveryMateriel> takeDeliverys = deliveryMaterielService.selectListByExample(record,"in","1");
	 	//List<Company> companys = companyService.selectByPage(company).getResult();
			// 封装datatables数据返回到前台
			Map<String,Object> pageMap = new HashMap<String,Object>();
			pageMap.put("draw", 1);
			pageMap.put("recordsTotal", record==null?0:takeDeliverys.getTotalCount());
			pageMap.put("recordsFiltered", record==null?0:takeDeliverys.getTotalCount());
			pageMap.put("data", takeDeliverys.getResult());
			return new ResponseEntity<Map<String,Object>>(pageMap, HttpStatus.OK);
	 }

	 /**
	  * @Description (获取列表数据)
	  * @param request
	  * @return
	  */
	 @RequestMapping(value="stockOutList",method=RequestMethod.POST)
	 public ResponseEntity<Map<String,Object>> stockOutList(Map<String, Object> map,HttpServletRequest request,@RequestBody String params,StockInOutRecord record) {
		 //远程分页代码
		 /*try {
	 		params = URLDecoder.decode(params, "UTF-8");
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	 	 ObjectMapper objectMapper = new ObjectMapper();
	 	 objectMapper.configure(org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		   		DataTablesParams  dataTablesParams = null;
			   try {
				   JSONObject a = JSONObject.fromObject(params);
				   dataTablesParams = objectMapper.readValue(params,DataTablesParams.class);
				} catch (JsonParseException e) {
					//20180110 qhzhao System.out.println(this.getClass()+"---------"+ e.getMessage());
				} catch (JsonMappingException e) {
					//20180110 qhzhao System.out.println(this.getClass()+"---------"+ e.getMessage());
				} catch (IOException e) {
					//20180110 qhzhao System.out.println(this.getClass()+"---------"+ e.getMessage());
				} catch (Exception e) {
			    	//20180110 qhzhao System.out.println(this.getClass()+"---------"+ e.getMessage());
				}*/
		 record.setPageIndex(0);
		 record.setPageSize(-1);
		 
		 User user = UserUtil.getUserFromSession();
		 Map<String,Object> pageMap = new HashMap<String,Object>();
		 if(user != null){
			 String comId = userCompanyService.getUserComId(user.getUserId().toString());
			 record.setSupplyComId(comId);
			 Page<DeliveryMateriel> takeDeliverys = deliveryMaterielService.selectListByExample(record,"out","1");
			 //List<Company> companys = companyService.selectByPage(company).getResult();
			 // 封装datatables数据返回到前台
			 pageMap.put("draw", 1);
			 pageMap.put("recordsTotal", record==null?0:takeDeliverys.getTotalCount());
			 pageMap.put("recordsFiltered", record==null?0:takeDeliverys.getTotalCount());
			 pageMap.put("data", takeDeliverys.getResult());
		 }
		
		 return new ResponseEntity<Map<String,Object>>(pageMap, HttpStatus.OK);
	 }
	 
	 
	  /**
	     * @Description (删除入库记录信息)
	     * @param request
	     * @return
	     */
	  @RequestMapping(value="deleteStockInInfo",method=RequestMethod.POST)
	  @ResponseBody
	  public String deleteStockInInfo(Map<String, Object> map,@RequestBody String serialNums,HttpServletRequest request) {
	    	String flag = "0"; //默认失败
	    	try{
	    		if(StringUtils.isNotEmpty(serialNums)){
	    			List<String> serialNumArray  = Arrays.asList(serialNums.split(","));
	    			takeDeliveryService.deleteStockInInfo(serialNumArray);
	    		}
	    	}catch(Exception e){
	    		logger.warn(e.getMessage(), e);
	    		flag = "1";
	    	}
	    	return flag;
	 }
	  
	  /**
	     * @Description (导出入库记录)
	     * @param request
	     * @return
	     */
	    @RequestMapping("exportStockIn")
	    public void exportStockIn(Map<String, Object> map,HttpServletRequest request,HttpServletResponse response) {
	    		Map<String, Object> dataMap = new HashMap<String, Object>();
	    		StockInOutRecord record = new StockInOutRecord();
	    		record.setPageIndex(0);
	    		record.setPageSize(-1);
	    		List<DeliveryMateriel> stockInList = deliveryMaterielService.selectListByExample(record,"in","1").getResult();
	    		dataMap.put("stockInList",stockInList);
	    		ExcelUtil.export(request, response, dataMap, "stockIn", "入库记录");
	    }
	    
	    /**
	     * @Description (导出出库记录)
	     * @param request
	     * @return
	     */
	    @RequestMapping("exportStockOut")
	    public void exportStockOut(Map<String, Object> map,HttpServletRequest request,HttpServletResponse response) {
	    	Map<String, Object> dataMap = new HashMap<String, Object>();
	    	StockInOutRecord record = new StockInOutRecord();
	    	record.setPageIndex(0);
	    	record.setPageSize(-1);
	    	List<DeliveryMateriel> stockOutList = deliveryMaterielService.selectListByExample(record,"out","1").getResult();
	    	dataMap.put("stockOutList",stockOutList);
	    	ExcelUtil.export(request, response, dataMap, "stockOut", "出库记录");
	    }
    
    @RequestMapping("getOrders")
    @ResponseBody
    public List<OrderInfo> getOrders(){
    	OrderInfoExample example = new OrderInfoExample();
    	example.createCriteria().andDelFlgEqualTo("0");
    	return orderService.selectList(example);
    }
    
    
    @RequestMapping("initTakeDelviery")
    @ResponseBody
    public List<Delivery> initTakeDelviery(){
    	Delivery takeDelivery = new Delivery();
    	takeDelivery.setPageSize(-1);
    	TakeDeliveryExample example = new TakeDeliveryExample();
    	example.createCriteria().andDelFlgEqualTo("0");
    	return takeDeliveryService.selectByPage(takeDelivery).getResult();
    }
    
    @RequestMapping("initWarehouse")
    @ResponseBody
    public List<Warehouse> initWarehouse(){
    	WarehouseExample example = new WarehouseExample();
    	example.createCriteria().andDelFlgEqualTo("0");
    	return warehouseService.selectWarehouseList(example);
    }
    
    @RequestMapping("getPositions")
    @ResponseBody
    public List<Warehouseposition> getPositions(String warehouseSerial){
    	WarehousepositionExample example = new WarehousepositionExample();
    	example.createCriteria().andDelFlgEqualTo("0");
    	return warehousepositionService.selectList(warehouseSerial);
    }
    
    @RequestMapping("getTakeDeliveryMaterielList")
    @ResponseBody
    public List<DeliveryMateriel> getTakeDeliveryMaterielList(String deliverySerial,String stockSerial){
    	if(StringUtils.isNotBlank(deliverySerial)){
    		DeliveryMaterielExample example = new DeliveryMaterielExample();
        	example.createCriteria().andDelFlgEqualTo("0").andDeliverSerialEqualTo(deliverySerial);
        	 List<DeliveryMateriel> deliveryMateriels=deliveryMaterielService.selectByExample(example);
        	 if(!CollectionUtils.isEmpty(deliveryMateriels)){
        	 //先判断该订单是普通采购还是代采购
        	 OrderMateriel orderMateriel =deliveryMateriels.get(0).getOrderMateriel();
     		OrderInfo orderInfo=orderService.selectById(orderMateriel.getOrderSerial());
     		StockExample stockExample =new StockExample();
     		com.congmai.zhgj.web.model.StockExample.Criteria criteria=stockExample.createCriteria();
     		Boolean isStockZijian=true;//默认是自建库存
     		if(StaticConst.getInfo("dailiBuy").equals(orderInfo.getOrderType())||StaticConst.getInfo("dailiSale").equals(orderInfo.getOrderType())){//代理销售/代理采购
     			isStockZijian=false;
     		}
        	 for(DeliveryMateriel d:deliveryMateriels){
        		 String materielSerial=d.getOrderMateriel().getMaterielSerial();//基本物料流水
        		 String countInAmountZijian=stockService.getCountInAmountForZijian(materielSerial);
     			String countOutAmountZijian=stockService.getCountOutAmountForZijian(materielSerial);
     			String countInAmountDaiguan=stockService.getCountInAmountForDaiguan(materielSerial,orderInfo.getBuyComId());
    			String countOutAmountDaiguan=stockService.getCountOutAmountForDaiguan(materielSerial,orderInfo.getBuyComId());
    			d.setCurrentStockAmount(Integer.parseInt(countInAmountZijian==null?"0":countInAmountZijian)-Integer.parseInt(countOutAmountZijian==null?"0":countOutAmountZijian)+
    					Integer.parseInt(countInAmountDaiguan==null?"0":countInAmountDaiguan)-Integer.parseInt(countOutAmountDaiguan==null?"0":countOutAmountDaiguan)+"");
        	/*	if(isStockZijian){
        			String countInAmountZijian=stockService.getCountInAmountForZijian(materielSerial);
        			String countOutAmountZijian=stockService.getCountOutAmountForZijian(materielSerial);
        			d.setCurrentStockAmount(Integer.parseInt(countInAmountZijian==null?"0":countInAmountZijian)-Integer.parseInt(countOutAmountZijian==null?"0":countOutAmountZijian)+"");
        		}else{
        			String countInAmountDaiguan=stockService.getCountInAmountForDaiguan(materielSerial);
        			String countOutAmountZijian=stockService.getCountOutAmountForZijian(materielSerial);
        			d.setCurrentStockAmount(Integer.parseInt(countInAmountDaiguan==null?"0":countInAmountDaiguan)-Integer.parseInt(countOutAmountDaiguan==null?"0":countOutAmountDaiguan)+"");
        		}*/
        		 
        	 }
        	 }
        	return deliveryMateriels;
    	}else if(StringUtils.isNotBlank(stockSerial)){
    		StockInOutRecord sr=stockInOutRecordMapper.selectByPrimaryKey(stockSerial);
    		DeliveryMaterielExample example = new DeliveryMaterielExample();
        	example.createCriteria().andDelFlgEqualTo("0").andDeliverSerialEqualTo(org.springframework.util.StringUtils.isEmpty(sr.getTakeDeliverSerial())?sr.getDeliverSerial():sr.getTakeDeliverSerial());
        	return deliveryMaterielService.selectByExample(example);
    	}
    	return null;
    }
    
    @RequestMapping("getTakeDeliveryMaterielListForStockIn")
    @ResponseBody
    public List<DeliveryMateriel> getTakeDeliveryMaterielListForStockIn(String deliverySerialNum,String stockSerialNum){
    	if(StringUtils.isNotBlank(deliverySerialNum)){
    		DeliveryMaterielExample example = new DeliveryMaterielExample();
    		example.createCriteria().andDelFlgEqualTo("0").andDeliverSerialEqualTo(deliverySerialNum);
    		List<DeliveryMateriel>list=deliveryMaterielService.selectByExampleForStockIn(example);
    		return list;
    	}else if(StringUtils.isNotBlank(stockSerialNum)){
    		DeliveryMaterielExample example = new DeliveryMaterielExample();
    		example.createCriteria().andDelFlgEqualTo("0").andStockInOutRecordSerialEqualTo(stockSerialNum);
    		return deliveryMaterielService.selectByExampleForStockIn(example);
    	}
    	
    	return null;
    }
    
    @RequestMapping("getOrderMaterielList")
    @ResponseBody
    public List<OrderMateriel> getOrderMaterielList(String serialNum){
    	if(StringUtils.isNotBlank(serialNum)){
    		OrderMaterielExample example = new OrderMaterielExample();
    		example.createCriteria().andDelFlgEqualTo("0").andOrderSerialEqualTo(serialNum);
    		return orderMaterielService.selectList(example);
    	}
    	return null;
    }
    
    //**********************************审批**************************************************//
    
    private String startTakeDelivery(TakeDelivery takeDelivery){
    	// 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
        identityService.setAuthenticatedUserId(takeDelivery.getUserId().toString());
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("entity", takeDelivery);
        //由userTask自动分配审批权限
//        if(vacation.getDays() <= 3){
//        	variables.put("auditGroup", "manager");
//        }else{
//        	variables.put("auditGroup", "director");
//        }
        String businessKey = takeDelivery.getBusinessKey();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(Constants.TAKEDELIVERY_KEY, businessKey, variables);
        String processInstanceId = processInstance.getId();
        takeDelivery.setProcessInstanceId(processInstanceId);
        this.takeDeliveryService.updateByPrimaryKeySelective(takeDelivery);
        logger.info("processInstanceId: "+processInstanceId);
        //最后要设置null，就是这么做，还没研究为什么
        this.identityService.setAuthenticatedUserId(null);
        return processInstanceId;
    }
    
    
    @RequestMapping(value="applyTakeDelivery",method=RequestMethod.POST)
    @ResponseBody
    public String applyTakeDelivery(Map<String, Object> map,@RequestBody String params,HttpServletRequest request){
    	String flag = confirmTakeDelivery(map, params, request);
    	if("1".equals(flag)){
    		 try {
    			 TakeDelivery takeDelivery = this.takeDeliveryService.selectByPrimaryKey(JSON.parseObject(params, TakeDeliveryParams.class).getTakeDelivery().getSerialNum());
    			 
    				User user = UserUtil.getUserFromSession();
    				String reason = (String) map.get("reason");
    				String serialNum = (String) map.get("serialNum");

    				takeDelivery.setUserId(user.getUserId());
    				takeDelivery.setUser_name(user.getUserName());
    				takeDelivery.setTitle(user.getUserName() + " 的收货申请");
    				takeDelivery.setBusinessType(Constants.TAKEDELIVERY);
    				takeDelivery.setStatus(BaseVO.PENDING);
    				takeDelivery.setApplyDate(new Date());
    				takeDelivery.setBusinessKey(serialNum);
    				takeDelivery.setReason(reason);
    				
    			 String processInstanceId = startTakeDelivery(takeDelivery);
 //   	            message.setStatus(Boolean.TRUE);
//   				message.setMessage("请假流程已启动，流程ID：" + processInstanceId);
    			 logger.info("processInstanceId: " + processInstanceId);
    	        } catch (ActivitiException e) {
//    	        	message.setStatus(Boolean.FALSE);
    	            if (e.getMessage().indexOf("no processes deployed with key") != -1) {
    	                logger.warn("没有部署流程!", e);
//    	    			message.setMessage("没有部署流程，请联系系统管理员，在[流程定义]中部署相应流程文件！");
    	            } else {
    	                logger.error("启动收货流程失败：", e);
//    	                message.setMessage("启动请假流程失败，系统内部错误！");
    	            }
    	        	flag = "0";
    	          //  throw e;
    	        } catch (Exception e) {
    	            logger.error("启动收货流程失败：", e);
//    	            message.setStatus(Boolean.FALSE);
//    	            message.setMessage("启动请假流程失败，系统内部错误！");
    	        	flag = "0";
    	           // throw e;
    	        }
    	}
    	return flag;
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
		TakeDelivery takeDelivery = (TakeDelivery) this.runtimeService
				.getVariable(pi.getId(), "entity");
		takeDelivery.setTask(task);
		takeDelivery.setProcessInstanceId(processInstanceId);
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
		map.put("takeDelivery", takeDelivery);
		map.put("commentList", commentList);
		return map;
	}

	@RequestMapping(value = "/complete", method = RequestMethod.POST, produces = "application/text;charset=UTF-8")
	@ResponseBody
	public String complete(@RequestParam("serialNum") String serialNum,
			@RequestParam("content") String content,
			@RequestParam("isPass") Boolean completeFlag,
			@RequestBody@RequestParam(value="params",required=false) String params,
			@RequestParam("taskId") String taskId,
			RedirectAttributes redirectAttributes,HttpServletRequest request) throws Exception {
		User user = UserUtil.getUserFromSession();
		String result = "";
		try {
		
			Map<String, Object> variables = new HashMap<String, Object>();
			variables.put("isPass", completeFlag);
		
			
			Task task = this.taskService.createTaskQuery().taskId(taskId)
					.singleResult();
			String taskDefinitionKey = task.getTaskDefinitionKey();
			TakeDelivery takeDelivery = null;
			if ("modifyApply".equals(taskDefinitionKey)) {
				
				
				if(!completeFlag){
					takeDelivery = this.takeDeliveryService.selectByPrimaryKey(serialNum);
					TakeDelivery baseTakeDelivery = (TakeDelivery) this.runtimeService
							.getVariable(takeDelivery.getProcessInstanceId(), "entity");
		        	baseTakeDelivery.setTitle(baseTakeDelivery.getUser_name()
							+ " 的收货申请已取消！");
					takeDelivery.setStatus(TakeDelivery.CANCEL);
		        	content = "取消申请";
		        	//result = "任务办理完成，已经取消您的请假申请！";
		        	variables.put("entity", baseTakeDelivery);
		        }else
		        {
		        	String flag = confirmTakeDelivery(new HashMap<String, Object>(), params, request);
					if(!"1".equals(flag)){
						logger.warn("修改收货信息失败");
						throw new Exception("修改收货信息失败");
					}
					takeDelivery = this.takeDeliveryService.selectByPrimaryKey(serialNum);
					TakeDelivery baseTakeDelivery = (TakeDelivery) this.runtimeService
							.getVariable(takeDelivery.getProcessInstanceId(), "entity");
		        	baseTakeDelivery.setTitle(baseTakeDelivery.getUser_name()
							+ " 的收货重新申请！");
					takeDelivery.setStatus(BaseVO.PENDING);
					variables.put("entity", baseTakeDelivery);
		        	content = "重新申请";
		        }
			}else{
				takeDelivery = this.takeDeliveryService.selectByPrimaryKey(serialNum);
				TakeDelivery baseTakeDelivery = (TakeDelivery) this.runtimeService
						.getVariable(takeDelivery.getProcessInstanceId(), "entity");
				if (!completeFlag) {
					baseTakeDelivery.setTitle(baseTakeDelivery.getUser_name()
							+ " 的收货申请失败,需修改后重新提交！");
					takeDelivery.setStatus(BaseVO.APPROVAL_FAILED);
					variables.put("entity", baseTakeDelivery);
				}
			}
			// 完成任务
			this.processService.complete(taskId, content, user.getUserId()
					.toString(), variables);

			if (completeFlag) {
				// 此处需要修改，不能根据人来判断审批是否结束。应该根据流程实例id(processInstanceId)来判定。
				// 判断指定ID的实例是否存在，如果结果为空，则代表流程结束，实例已被删除(移到历史库中)
				ProcessInstance pi = this.runtimeService
						.createProcessInstanceQuery()
						.processInstanceId(takeDelivery.getProcessInstanceId())
						.singleResult();
				if (BeanUtils.isBlank(pi)) {
					takeDelivery.setStatus(TakeDelivery.APPLY_COMPLETE);
					//任务办理完成后,创建入库检验单
					this.takeDeliveryService.createStockInCheckRecord(takeDelivery, user.getUserName());
				}
			}

			this.takeDeliveryService.updateByPrimaryKeySelective(takeDelivery);

			
			
			result = "任务办理完成！";
		} catch (ActivitiObjectNotFoundException e) {
			result = "此任务不存在，请联系管理员！";
			//throw e;
		} catch (ActivitiException e) {
			result = "此任务正在协办，您不能办理此任务！";
			//throw e;
		} catch (Exception e) {
			result = "任务办理失败，请联系管理员！";
			//throw e;
		}
		return result;
	}
	
	
	
	//********************************以下为数据处理**********************************

	
	/**
	 * 
	 * @Description (封装代发货数据)
	 * @param takeDeliveryParams
	 * @param currenLoginName
	 * @return
	 */
	private TakeDeliveryParams getTakeDeliveryData(TakeDeliveryParams takeDeliveryParams,String currenLoginName){
		String deliverySerial = ApplicationUtils.random32UUID();
		String takeDeliverySerial = ApplicationUtils.random32UUID();
		Date now = new Date();
		if(StringUtils.isEmpty(takeDeliveryParams.getTakeDelivery().getSerialNum())){
			takeDeliveryParams.getTakeDelivery().setSerialNum(takeDeliverySerial);
			//takeDeliveryParams.getTakeDelivery().setTakeDeliverNum("SH"+ApplicationUtils.getFromNumber());
			takeDeliveryParams.getTakeDelivery().setDeliverSerial(deliverySerial);
			takeDeliveryParams.getTakeDelivery().setStatus("0");
			takeDeliveryParams.getTakeDelivery().setCreator(currenLoginName);
			takeDeliveryParams.getTakeDelivery().setCreateTime(now);
		}
		takeDeliveryParams.getTakeDelivery().setUpdater(currenLoginName);
		takeDeliveryParams.getTakeDelivery().setUpdateTime(now);
		takeDeliveryParams.getTakeDelivery().setDelFlg("0");
		if(takeDeliveryParams.getTakeDelivery().getWarehouseSerial()!=null&&warehouseService.selectOne(takeDeliveryParams.getTakeDelivery().getWarehouseSerial())!=null){
			takeDeliveryParams.getTakeDelivery().setTakeDeliverAddress(warehouseService.selectOne(takeDeliveryParams.getTakeDelivery().getWarehouseSerial()).getWarehouseName());
		}
		if(takeDeliveryParams.getDelivery()!=null){
			if(StringUtils.isEmpty(takeDeliveryParams.getDelivery().getSerialNum())){
				takeDeliveryParams.getDelivery().setSerialNum(deliverySerial);
				takeDeliveryParams.getDelivery().setTakeDeliverSerial(takeDeliverySerial);
				takeDeliveryParams.getDelivery().setCreator(currenLoginName);
				takeDeliveryParams.getDelivery().setCreateTime(now);
			}
			takeDeliveryParams.getDelivery().setUpdater(currenLoginName);
			takeDeliveryParams.getDelivery().setUpdateTime(now);
			takeDeliveryParams.getDelivery().setDelFlg("0");
			if(takeDeliveryParams.getDelivery().getWarehouseSerial()!=null&&warehouseService.selectOne(takeDeliveryParams.getDelivery().getWarehouseSerial())!=null){
				takeDeliveryParams.getDelivery().setWarehouseName(warehouseService.selectOne(takeDeliveryParams.getDelivery().getWarehouseSerial()).getWarehouseName());
			}
			//takeDeliveryParams.getDelivery().setStatus("3"); //已发货
		}
		
		if(takeDeliveryParams.getDeliveryTransport()!=null){
			if(StringUtils.isEmpty(takeDeliveryParams.getDeliveryTransport().getSerialNum())){
				takeDeliveryParams.getDeliveryTransport().setSerialNum(ApplicationUtils.random32UUID());
				takeDeliveryParams.getDeliveryTransport().setDeliverSerial(deliverySerial);
				takeDeliveryParams.getDeliveryTransport().setCreator(currenLoginName);
				takeDeliveryParams.getDeliveryTransport().setCreateTime(now);
			}
			takeDeliveryParams.getDeliveryTransport().setUpdater(currenLoginName);
			takeDeliveryParams.getDeliveryTransport().setUpdateTime(now);
			takeDeliveryParams.getDeliveryTransport().setDelFlg("0");
			
		}
		
		for(DeliveryMateriel materiel : takeDeliveryParams.getDeliveryMateriels()){
			//附件
    		List<RelationFile> files=new ArrayList<RelationFile>();
    		
    
			materiel.setSerialNum(ApplicationUtils.random32UUID());
			materiel.setDeliverSerial(takeDeliveryParams.getDelivery().getSerialNum());
			materiel.setCreator(currenLoginName);
			materiel.setCreateTime(now);
			materiel.setUpdater(currenLoginName);
			materiel.setUpdateTime(now);
			materiel.setDelFlg("0");
			
			
			//如果附件不为空时执行添加操作
    		if(!StringUtils.isEmpty(materiel.getAttachFile())){
    			String attachFile[]=materiel.getAttachFile().split("&");
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
    				item.setRelationSerial(materiel.getSerialNum());
    				item.setFileType("delivery");
    				item.setFileDescribe(describe);
    				item.setFile(file);
    				item.setUploader(currenLoginName);
    				item.setCreator(currenLoginName);
    				item.setUpdater(currenLoginName);
    				item.setDelFlg("0");
    				item.setIsReadable("0");
    				files.add(item);
    			}
    			
    			//批量添加附件
    			relationFileService.insertAttachFiles(files);
    		}	
		}
		
		return takeDeliveryParams;
	}
	
	/**
	 * 
	 * @Description (封装收货数据)
	 * @param takeDeliveryParams
	 * @param currenLoginName
	 * @return
	 */
	private TakeDeliveryParams getConfirmTakeDeliveryData(
			TakeDeliveryParams takeDeliveryParams, String currenLoginName) {
		Date now = new Date();
		for(DeliveryMateriel materiel : takeDeliveryParams.getDeliveryMateriels()){
			//附件
    		List<RelationFile> files=new ArrayList<RelationFile>();
    		
    		//如果附件不为空时执行添加操作
    		if(!StringUtils.isEmpty(materiel.getAttachFile())){
    			String attachFile[]=materiel.getAttachFile().split("&");
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
    				item.setRelationSerial(materiel.getSerialNum());
    				item.setFileType("takeDelivery");
    				item.setFileDescribe(describe);
    				item.setFile(file);
    				item.setUploader(currenLoginName);
    				item.setCreator(currenLoginName);
    				item.setUpdater(currenLoginName);
    				item.setDelFlg("0");
    				item.setIsReadable("0");
    				files.add(item);
    			}
    			
    			//批量添加附件
    			relationFileService.insertAttachFiles(files);
    		}
			materiel.setUpdater(currenLoginName);
			materiel.setUpdateTime(now);
		}
		return takeDeliveryParams;
	}
	 /* *//**
     * @Description (确认代发货)
     * @param request
     * @return
     */
  @RequestMapping(value="/confirmDelivery",method=RequestMethod.POST)
  @ResponseBody
  public Map<String, Object> confirmDelivery(Map<String, Object> map,@RequestBody String serialNum,HttpServletRequest request) {
    	String flags1 = "0"; //默认失败
    	Map<String, Object>map1=new HashMap<String, Object>();
    	Delivery  delivery=takeDeliveryMapper.selectByPrimaryDeliveryKey(serialNum);
    	//保存/更新前判断发货数量
		OrderInfo o=orderService.selectById(delivery.getOrderSerial());
		  Boolean flags=false;//还可以发
		  Boolean isDel=false;//是否删除发货单
			String  deliveryCount=o.getDeliveryCount();//订单已发数量
			String  materielCount=o.getMaterielCount();//订单总数量
			  BigDecimal deliveryCount1=new BigDecimal(deliveryCount);
			  BigDecimal materielCount1=new BigDecimal(materielCount);
			  if(deliveryCount1.compareTo(materielCount1)>=0){
				  flags=true;
				  isDel=true;//提示删除当前发货单
				  map1.put("isDel", isDel);
				  map1.put("flag", flags);
				  return map1;
			  }else{
				  map1.put("isDel", isDel);
			  }
				OrderMaterielExample m =new OrderMaterielExample();
		    	//and 条件1
		    	com.congmai.zhgj.web.model.OrderMaterielExample.Criteria criteria =  m.createCriteria();
		    	criteria.andDelFlgEqualTo("0");
		    	criteria.andOrderSerialEqualTo(delivery.getOrderSerial());
		    	m.setOrderByClause(" sort asc");
		    	List<OrderMateriel> orderMateriel = orderMaterielService.selectList(m);//获取最新的已发
		    	List<DeliveryMaterielVO> deliveryMateriels=deliveryService.selectListForDetail(delivery.getSerialNum());
		  	  for(DeliveryMaterielVO deliveryMateriel: deliveryMateriels){
				  for(OrderMateriel vo:orderMateriel){
					  if(deliveryMateriel.getOrderMaterielSerial().equals(vo.getSerialNum())){//两个订单物料流水相同时
						  BigDecimal deliverCount=new BigDecimal(deliveryMateriel.getDeliverCount());
						  BigDecimal canDeliverCount=new BigDecimal(vo.getAmount()).subtract(new BigDecimal(vo.getDeliveredCount()==null?"0":vo.getDeliveredCount()));//可发数量=物料数量-已发数量
						  if(deliverCount.compareTo(canDeliverCount)>0){
							  flags=true;
							  map1.put("flag", flags);
							  return map1;
						  }
					  }
				  }
			  }
    	try{
    		if(StringUtils.isNotEmpty(serialNum)){
    			delivery.setStatus("1");
    			TakeDelivery takeDelivery = new TakeDelivery();
    			takeDelivery = takeDeliveryMapper.selectTakeDeliveryByDeliveryId(serialNum);
    			Subject currentUser = SecurityUtils.getSubject();
        		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
    			takeDeliveryService.confirmDelivery(delivery, takeDelivery, currenLoginName);
    			
    		}
    	}catch(Exception e){
    		logger.warn(e.getMessage(), e);
    		flags1 = "1";
    	}
    	map1.put("flags1", flags1);
    	return map1;
 }

}
