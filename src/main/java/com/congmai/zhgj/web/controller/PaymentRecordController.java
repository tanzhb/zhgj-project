package com.congmai.zhgj.web.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.codehaus.jackson.map.ObjectMapper;
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
import com.congmai.zhgj.core.util.ExcelUtil;
import com.congmai.zhgj.web.model.Delivery;
import com.congmai.zhgj.web.model.DeliveryMateriel;
import com.congmai.zhgj.web.model.DeliveryMaterielExample;
import com.congmai.zhgj.web.model.OrderInfo;
import com.congmai.zhgj.web.model.OrderInfoExample;
import com.congmai.zhgj.web.model.OrderMateriel;
import com.congmai.zhgj.web.model.OrderMaterielExample;
import com.congmai.zhgj.web.model.PaymentRecord;
import com.congmai.zhgj.web.model.TakeDeliveryExample;
import com.congmai.zhgj.web.model.TakeDeliveryParams;
import com.congmai.zhgj.web.model.Warehouse;
import com.congmai.zhgj.web.model.WarehouseExample;
import com.congmai.zhgj.web.model.Warehouseposition;
import com.congmai.zhgj.web.model.WarehousepositionExample;
import com.congmai.zhgj.web.service.DeliveryMaterielService;
import com.congmai.zhgj.web.service.OrderMaterielService;
import com.congmai.zhgj.web.service.OrderService;
import com.congmai.zhgj.web.service.PaymentRecordSerive;
import com.congmai.zhgj.web.service.TakeDeliveryService;
import com.congmai.zhgj.web.service.WarehouseService;
import com.congmai.zhgj.web.service.WarehousepositionService;

/**
 * @ClassName CollectionAndPaymentController
 * @Description TODO(收付款记录控制器)
 * @author likt
 * @Date 2017年8月29日 下午3:20:52
 * @version 1.0.0
 */
@Controller
@RequestMapping("paymentRecord")
public class PaymentRecordController {
	
	
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
	private DeliveryMaterielService deliveryMaterielService;
	
	@Autowired
	private PaymentRecordSerive paymentRecordSerive;
	
	
	@RequestMapping("paymentRecordManage")
	public String paymentRecordManage() {
		
		return "paymentRecord/paymentRecordManage";
	}
	
	   /**
     * @Description (获取收款记录数据)
     * @param request
     * @return
     */
    @RequestMapping(value="collectionRecordList",method=RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> collectionRecordList(Map<String, Object> map,HttpServletRequest request,@RequestBody String params,PaymentRecord collectionRecord) {
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
    	//takeDelivery.setPageIndex(0);
    	//takeDelivery.setPageSize(-1);
    	collectionRecord.setSupplyComId("");
    	Page<PaymentRecord> collectionRecords = paymentRecordSerive.selectByPage(collectionRecord);
    	//Page<PaymentRecord> acpRecords = null;
		// 封装datatables数据返回到前台
		Map<String,Object> pageMap = new HashMap<String,Object>();
		pageMap.put("draw", 1);
		pageMap.put("recordsTotal", collectionRecord==null?0:collectionRecords.getTotalCount());
		pageMap.put("recordsFiltered", collectionRecord==null?0:collectionRecords.getTotalCount());
		pageMap.put("data", collectionRecords.getResult());
		return new ResponseEntity<Map<String,Object>>(pageMap, HttpStatus.OK);
    }
    
    /**
     * @Description (获取付款记录数据)
     * @param request
     * @return
     */
    @RequestMapping(value="paymentRecordList",method=RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> paymentRecordList(Map<String, Object> map,HttpServletRequest request,@RequestBody String params,PaymentRecord paymentRecord) {
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
    	//takeDelivery.setPageIndex(0);
    	//takeDelivery.setPageSize(-1);
    	paymentRecord.setBuyComId("");
    	Page<PaymentRecord> paymentRecords = paymentRecordSerive.selectByPage(paymentRecord);
    	// 封装datatables数据返回到前台
    	Map<String,Object> pageMap = new HashMap<String,Object>();
    	pageMap.put("draw", 1);
    	pageMap.put("recordsTotal", paymentRecord==null?0:paymentRecords.getTotalCount());
    	pageMap.put("recordsFiltered", paymentRecord==null?0:paymentRecords.getTotalCount());
    	pageMap.put("data", paymentRecords.getResult());
    	return new ResponseEntity<Map<String,Object>>(pageMap, HttpStatus.OK);
    }
    
	 /**
     * @Description (收款记录新增页面)
     * @param request
     * @return
     */
    @RequestMapping("collectionRecordAdd")
    public String collectionRecordAdd(HttpServletRequest request) {
        return "paymentRecord/collectionRecordAdd";
    }
    
    /**
     * @Description (付款记录新增页面)
     * @param request
     * @return
     */
    @RequestMapping("paymentRecordAdd")
    public String paymentRecordAdd(HttpServletRequest request) {
    	return "paymentRecord/paymentRecordAdd";
    }
    

    

	 /**
     * @Description (收款查看页面)
     * @param request
     * @return
     */
    @RequestMapping("collectionRecordView")
    public String collectionRecordView(HttpServletRequest request) {
        return "paymentRecord/collectionRecordView";
    }
    
    /**
     * @Description (付款查看页面)
     * @param request
     * @return
     */
    @RequestMapping("paymentRecordView")
    public String paymentRecordView(HttpServletRequest request) {
    	return "paymentRecord/paymentRecordView";
    }

    /**
     * @Description (获取收款数据)
     * @param request
     * @return
     */
    @RequestMapping("getCollectionRecordInfo")
    @ResponseBody
    public Object getCollectionRecordInfo(HttpServletRequest request,String serialNum) {
    	
    	return null;
    }
    
    /**
     * @Description (获取付款数据)
     * @param request
     * @return
     */
    @RequestMapping("getPaymentRecordInfo")
    @ResponseBody
    public Object getPaymentRecordInfo(HttpServletRequest request,String serialNum) {
    	
    	return null;
    }
    
	    
    /**
     * @Description (保存收款记录)
     * @param request
     * @return
     */
    @RequestMapping(value="saveCollectionRecord",method=RequestMethod.POST)
    @ResponseBody
    public String saveCollectionRecord(Map<String, Object> map,@RequestBody String params,HttpServletRequest request) {
    	String flag ="0"; //默认失败

    	 ObjectMapper objectMapper = new ObjectMapper();
    	 objectMapper.configure(org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  	   		TakeDeliveryParams  takeDeliveryParams = null;
		   try {
			   //JSONObject a = JSONObject.fromObject(params);
			  // takeDeliveryParams = objectMapper.readValue(params,TakeDeliveryParams.class);
			   takeDeliveryParams = JSON.parseObject(params, TakeDeliveryParams.class);
			} catch (Exception e) {
		    	//20180110 qhzhao System.out.println(this.getClass()+"---------"+ e.getMessage());
			}
        	try{
        		Subject currentUser = SecurityUtils.getSubject();
        		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
        		if(StringUtils.isNotEmpty(takeDeliveryParams.getTakeDelivery().getSerialNum())){
        			//takeDeliveryService.updateTakeDelivery(takeDeliveryParams,currenLoginName);
        		}else{
        			//takeDeliveryService.insertTakeDelivery(takeDeliveryParams,currenLoginName);
        		}
        		flag = "1";
        	}catch(Exception e){
        		//20180110 qhzhao System.out.println(e.getMessage());
        		return null;
        	}
    	return flag;
    }
    
    /**
     * @Description (保存付款记录)
     * @param request
     * @return
     */
    @RequestMapping(value="savePaymentRecord",method=RequestMethod.POST)
    @ResponseBody
    public String savePaymentRecord(Map<String, Object> map,@RequestBody String params,HttpServletRequest request) {
    	String flag ="0"; //默认失败
    	
    	ObjectMapper objectMapper = new ObjectMapper();
    	objectMapper.configure(org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    	TakeDeliveryParams  takeDeliveryParams = null;
    	try {
    		//JSONObject a = JSONObject.fromObject(params);
    		// takeDeliveryParams = objectMapper.readValue(params,TakeDeliveryParams.class);
    		takeDeliveryParams = JSON.parseObject(params, TakeDeliveryParams.class);
    	} catch (Exception e) {
    		//20180110 qhzhao System.out.println(this.getClass()+"---------"+ e.getMessage());
    	}
    	try{
    		Subject currentUser = SecurityUtils.getSubject();
    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
    		if(StringUtils.isNotEmpty(takeDeliveryParams.getTakeDelivery().getSerialNum())){
    			//takeDeliveryService.updateTakeDelivery(takeDeliveryParams,currenLoginName);
    		}else{
    			//takeDeliveryService.insertTakeDelivery(takeDeliveryParams,currenLoginName);
    		}
    		flag = "1";
    	}catch(Exception e){
    		//20180110 qhzhao System.out.println(e.getMessage());
    		return null;
    	}
    	return flag;
    }
    
    
    /**
     * @Description (删除收款记录)
     * @param request
     * @return
     */
    @RequestMapping(value="deleteCollectionRecord",method=RequestMethod.POST)
    @ResponseBody
    public String deleteCollectionRecord(Map<String, Object> map,@RequestBody String serialNums,HttpServletRequest request) {
    	String flag = "0"; //默认失败
    	try{
    		if(StringUtils.isNotEmpty(serialNums)){
    			List<String> serialNumArray  = Arrays.asList(serialNums.split(","));
    			//takeDeliveryService.deleteBatch(serialNumArray);
    		}
    	}catch(Exception e){
    		//20180110 qhzhao System.out.println(e.getMessage());
    		flag = "1";
    	}
    	return flag;
    }
    

    /**
     * @Description (删除付款记录)
     * @param request
     * @return
     */
    @RequestMapping(value="deletePaymentRecord",method=RequestMethod.POST)
    @ResponseBody
    public String deletePaymentRecord(Map<String, Object> map,@RequestBody String serialNums,HttpServletRequest request) {
    	String flag = "0"; //默认失败
    	try{
    		if(StringUtils.isNotEmpty(serialNums)){
    			List<String> serialNumArray  = Arrays.asList(serialNums.split(","));
    			//takeDeliveryService.deleteBatch(serialNumArray);
    		}
    	}catch(Exception e){
    		//20180110 qhzhao System.out.println(e.getMessage());
    		flag = "1";
    	}
    	return flag;
    }
    
    
 
    
    /**
     * @Description (导出收款记录)
     * @param request
     * @return
     */
    @RequestMapping("exportCollectionRecord")
    public void exportCollectionRecord(Map<String, Object> map,HttpServletRequest request,HttpServletResponse response) {
    		Map<String, Object> dataMap = new HashMap<String, Object>();
    		Delivery takeDelivery = new Delivery();
    		takeDelivery.setPageIndex(0);
        	takeDelivery.setPageSize(-1);
    		List<Delivery> takeDeliveryList = takeDeliveryService.selectByPage(takeDelivery).getResult();
    		dataMap.put("takeDeliveryList",takeDeliveryList);
    		ExcelUtil.export(request, response, dataMap, "takeDelivery", "收货计划");
    }
    
    /**
     * @Description (导出付款记录)
     * @param request
     * @return
     */
    @RequestMapping("exportPaymentRecord")
    public void exportPaymentRecord(Map<String, Object> map,HttpServletRequest request,HttpServletResponse response) {
    	Map<String, Object> dataMap = new HashMap<String, Object>();
    	Delivery takeDelivery = new Delivery();
    	takeDelivery.setPageIndex(0);
    	takeDelivery.setPageSize(-1);
    	List<Delivery> takeDeliveryList = takeDeliveryService.selectByPage(takeDelivery).getResult();
    	dataMap.put("takeDeliveryList",takeDeliveryList);
    	ExcelUtil.export(request, response, dataMap, "takeDelivery", "收货计划");
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
}
