package com.congmai.zhgj.web.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.congmai.zhgj.core.feature.orm.mybatis.Page;
import com.congmai.zhgj.core.util.Constants;
import com.congmai.zhgj.core.util.ExcelUtil;
import com.congmai.zhgj.web.model.Delivery;
import com.congmai.zhgj.web.model.DeliveryMateriel;
import com.congmai.zhgj.web.model.DeliveryMaterielExample;
import com.congmai.zhgj.web.model.OrderInfo;
import com.congmai.zhgj.web.model.OrderInfoExample;
import com.congmai.zhgj.web.model.OrderMateriel;
import com.congmai.zhgj.web.model.OrderMaterielExample;
import com.congmai.zhgj.web.model.StockInOutRecord;
import com.congmai.zhgj.web.model.StockInOutRecordExample;
import com.congmai.zhgj.web.model.TakeDelivery;
import com.congmai.zhgj.web.model.TakeDeliveryExample;
import com.congmai.zhgj.web.model.TakeDeliveryParams;
import com.congmai.zhgj.web.model.Warehouse;
import com.congmai.zhgj.web.model.WarehouseExample;
import com.congmai.zhgj.web.model.Warehouseposition;import com.congmai.zhgj.web.model.WarehousepositionExample;
import com.congmai.zhgj.web.service.DeliveryMaterielService;
import com.congmai.zhgj.web.service.DeliveryTransportService;
import com.congmai.zhgj.web.service.OrderMaterielService;
import com.congmai.zhgj.web.service.OrderService;
import com.congmai.zhgj.web.service.TakeDeliveryService;
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
	protected RuntimeService runtimeService;
	
    @Autowired
    protected IdentityService identityService;
	
	
	
	@RequestMapping("takeDeliveryManage")
	public String takeDeliveryManage() {
		
		return "takeDelivery/takeDeliveryManage";
	}
	
	   /**
     * @Description (获取列表数据)
     * @param request
     * @return
     */
    @RequestMapping(value="takeDeliveryList",method=RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> takeDeliveryList(Map<String, Object> map,HttpServletRequest request,@RequestBody String params,Delivery takeDelivery) {
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
				System.out.println(this.getClass()+"---------"+ e.getMessage());
			} catch (JsonMappingException e) {
				System.out.println(this.getClass()+"---------"+ e.getMessage());
			} catch (IOException e) {
				System.out.println(this.getClass()+"---------"+ e.getMessage());
			} catch (Exception e) {
		    	System.out.println(this.getClass()+"---------"+ e.getMessage());
			}*/
    	takeDelivery.setPageIndex(0);
    	takeDelivery.setPageSize(-1);
    	Page<Delivery> takeDeliverys = takeDeliveryService.selectByPage(takeDelivery);
    	
    	
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
     * @Description (收货查看页面)
     * @param request
     * @return
     */
    @RequestMapping("getTakeDeliveryInfo")
    @ResponseBody
    public Delivery getTakeDeliveryInfo(HttpServletRequest request,String serialNum) {
    	
    	return takeDeliveryService.selectByTakeDeliveryPrimaryKey(serialNum);
    }
    
	    
    /**
     * @Description (保存)
     * @param request
     * @return
     */
    @RequestMapping(value="saveTakeDelivery",method=RequestMethod.POST)
    @ResponseBody
    public String saveTakeDelivery(Map<String, Object> map,@RequestBody String params,HttpServletRequest request) {
    	String flag ="0"; //默认失败

    	 ObjectMapper objectMapper = new ObjectMapper();
    	 objectMapper.configure(org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  	   		TakeDeliveryParams  takeDeliveryParams = null;
		   try {
			   //JSONObject a = JSONObject.fromObject(params);
			  // takeDeliveryParams = objectMapper.readValue(params,TakeDeliveryParams.class);
			   takeDeliveryParams = JSON.parseObject(params, TakeDeliveryParams.class);
			} catch (Exception e) {
		    	logger.warn(e.getMessage(), e);
			}
        	try{
        		Subject currentUser = SecurityUtils.getSubject();
        		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
        		if(StringUtils.isNotEmpty(takeDeliveryParams.getTakeDelivery().getSerialNum())){
        			takeDeliveryService.updateTakeDelivery(takeDeliveryParams,currenLoginName);
        		}else{
        			takeDeliveryService.insertTakeDelivery(takeDeliveryParams,currenLoginName);
        		}
        		flag = "1";
        	}catch(Exception e){
        		logger.warn(e.getMessage(), e);
        		return null;
        	}
    	return flag;
    }
    
    /**
     * @Description (确认发货)
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
    		System.out.println(this.getClass()+"---------"+ e.getMessage());
    	}
    	try{
    		Subject currentUser = SecurityUtils.getSubject();
    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
    		takeDeliveryService.confirmTakeDelivery(takeDeliveryParams,currenLoginName);
    		flag = "1";
    	}catch(Exception e){
    		System.out.println(e.getMessage());
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
    		System.out.println(e.getMessage());
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
    public void exportTakeDelivery(Map<String, Object> map,HttpServletRequest request,HttpServletResponse response) {
    		Map<String, Object> dataMap = new HashMap<String, Object>();
    		Delivery takeDelivery = new Delivery();
    		takeDelivery.setPageIndex(0);
        	takeDelivery.setPageSize(-1);
    		List<Delivery> takeDeliveryList = takeDeliveryService.selectByPage(takeDelivery).getResult();
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
		    	System.out.println(this.getClass()+"---------"+ e.getMessage());
			}
        	try{
        		Subject currentUser = SecurityUtils.getSubject();
        		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
        		if(StringUtils.isNotEmpty(takeDeliveryParams.getRecord().getSerialNum())){
        			takeDeliveryService.updateStockInData(takeDeliveryParams,currenLoginName,"in");
        		}else{
        			takeDeliveryService.insertStockInData(takeDeliveryParams,currenLoginName);
        		}
        		flag = "1";
        	}catch(Exception e){
        		System.out.println(e.getMessage());
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
		   try {
			   takeDeliveryParams = JSON.parseObject(params, TakeDeliveryParams.class);
			} catch (Exception e) {
		    	System.out.println(this.getClass()+"---------"+ e.getMessage());
			}
        	try{
        		Subject currentUser = SecurityUtils.getSubject();
        		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
        		if(StringUtils.isNotEmpty(takeDeliveryParams.getRecord().getSerialNum())){
        			takeDeliveryService.updateStockInData(takeDeliveryParams,currenLoginName,"out");
        		}else{
        			takeDeliveryService.insertStockInData(takeDeliveryParams,currenLoginName);
        		}
        		flag = "1";
        	}catch(Exception e){
        		System.out.println(e.getMessage());
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
    public StockInOutRecord getStockInInfo(HttpServletRequest request,String serialNum) {
    	
    	return takeDeliveryService.selectStockInOutRecordByPrimayKey(serialNum,"in");
    }
    
    /**
     * @Description (出库查看)
     * @param request
     * @return
     */
    @RequestMapping("getStockOutInfo")
    @ResponseBody
    public StockInOutRecord getStockOutInfo(HttpServletRequest request,String serialNum) {
    	
    	return takeDeliveryService.selectStockInOutRecordByPrimayKey(serialNum,"out");
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
					System.out.println(this.getClass()+"---------"+ e.getMessage());
				} catch (JsonMappingException e) {
					System.out.println(this.getClass()+"---------"+ e.getMessage());
				} catch (IOException e) {
					System.out.println(this.getClass()+"---------"+ e.getMessage());
				} catch (Exception e) {
			    	System.out.println(this.getClass()+"---------"+ e.getMessage());
				}*/
		 record.setPageIndex(0);
		 record.setPageSize(-1);
	 	Page<DeliveryMateriel> takeDeliverys = deliveryMaterielService.selectListByExample(record,"in");
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
					System.out.println(this.getClass()+"---------"+ e.getMessage());
				} catch (JsonMappingException e) {
					System.out.println(this.getClass()+"---------"+ e.getMessage());
				} catch (IOException e) {
					System.out.println(this.getClass()+"---------"+ e.getMessage());
				} catch (Exception e) {
			    	System.out.println(this.getClass()+"---------"+ e.getMessage());
				}*/
		 record.setPageIndex(0);
		 record.setPageSize(-1);
		 Page<DeliveryMateriel> takeDeliverys = deliveryMaterielService.selectListByExample(record,"out");
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
	    		System.out.println(e.getMessage());
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
	    		List<DeliveryMateriel> stockInList = deliveryMaterielService.selectListByExample(record,"in").getResult();
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
	    	List<DeliveryMateriel> stockOutList = deliveryMaterielService.selectListByExample(record,"out").getResult();
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
    public List<DeliveryMateriel> getTakeDeliveryMaterielList(String serialNum){
    	if(StringUtils.isNotBlank(serialNum)){
    		DeliveryMaterielExample example = new DeliveryMaterielExample();
        	example.createCriteria().andDelFlgEqualTo("0").andDeliverSerialEqualTo(serialNum);
        	return deliveryMaterielService.selectByExample(example);
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
        identityService.setAuthenticatedUserId(takeDelivery.getSerialNum().toString());
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
        //takeDelivery.setProcessInstanceId(processInstanceId);
        //this.takeDeliveryService.

        logger.info("processInstanceId: "+processInstanceId);
        //最后要设置null，就是这么做，还没研究为什么
        this.identityService.setAuthenticatedUserId(null);
        return processInstanceId;
    }
    
    
    @RequestMapping(value="applyTakeDelivery",method=RequestMethod.POST)
    @ResponseBody
    public String applyTakeDelivery(Map<String, Object> map,@RequestBody String params,HttpServletRequest request){
    	String flag = saveTakeDelivery(map, params, request);
    	if("1".equals(flag)){
    		 try {
    			 startTakeDelivery(JSON.parseObject(params, TakeDeliveryParams.class).getTakeDelivery());
//    	            message.setStatus(Boolean.TRUE);
//    				message.setMessage("请假流程已启动，流程ID：" + processInstanceId);
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

    

}
