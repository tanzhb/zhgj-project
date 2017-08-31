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
import com.congmai.zhgj.web.model.OrderInfo;
import com.congmai.zhgj.web.model.OrderInfoExample;
import com.congmai.zhgj.web.model.OrderMateriel;
import com.congmai.zhgj.web.model.OrderMaterielExample;
import com.congmai.zhgj.web.model.TakeDeliveryParams;
import com.congmai.zhgj.web.model.Warehouse;
import com.congmai.zhgj.web.model.WarehouseExample;
import com.congmai.zhgj.web.service.DeliveryMaterielService;
import com.congmai.zhgj.web.service.DeliveryTransportService;
import com.congmai.zhgj.web.service.OrderMaterielService;
import com.congmai.zhgj.web.service.OrderService;
import com.congmai.zhgj.web.service.TakeDeliveryService;
import com.congmai.zhgj.web.service.WarehouseService;


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
    
    @RequestMapping("getOrders")
    @ResponseBody
    public List<OrderInfo> getOrders(){
    	OrderInfoExample example = new OrderInfoExample();
    	example.createCriteria().andDelFlgEqualTo("0");
    	return orderService.selectList(example);
    }
    
    
    @RequestMapping("initWarehouse")
    @ResponseBody
    public List<Warehouse> initWarehouse(){
    	WarehouseExample example = new WarehouseExample();
    	example.createCriteria().andDelFlgEqualTo("0");
    	return warehouseService.selectWarehouseList(example);
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
		    	System.out.println(this.getClass()+"---------"+ e.getMessage());
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
    /*
    
    *//**
     * @Description (跳转至查看需求计划页面)
     * @param request
     * @return
     *//*
    @RequestMapping(value="demandPlanView")
    public String demandPlanView(Map<String, Object> map,String serialNum,HttpServletRequest request) {
    	
    	DemandPlan demandPlan = null;
        	try{
        		if(StringUtils.isNotEmpty(serialNum)){
        			demandPlan = demandPlanService.selectById(serialNum);
        		}
        		
        		
        	}catch(Exception e){
        		System.out.println(e.getMessage());
        		return null;
        	}
    	return "demandPlan/demandPlanView";
    }
    
    *//**
     * @Description (查看需求计划)
     * @param request
     * @return
     *//*
    @RequestMapping(value="viewDemandPlan")
    @ResponseBody
    public DemandPlan viewDemandPlan(Map<String, Object> map,String serialNum,HttpServletRequest request) {
    	
    	DemandPlan demandPlan = null;
    	try{
    		if(StringUtils.isNotEmpty(serialNum)){
    			demandPlan = demandPlanService.selectById(serialNum);
    		}
    		
    		
    	}catch(Exception e){
    		System.out.println(e.getMessage());
    		return null;
    	}
    	return demandPlan;
    }
    
    *//**
     * @Description (查看需求计划)
     * @param request
     * @return
     *//*
    @RequestMapping(value="demandPlanInfo",method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> demandPlanInfo(@RequestBody String serialNum,HttpServletRequest request) {
    	 Map<String,Object> map = new HashMap<String, Object>(); 
    	DemandPlan demandPlan = null;
    	List<DemandPlanMateriel> demandPlanMateriels = null;
    	try{
    		if(StringUtils.isNotEmpty(serialNum)){
    			demandPlan = demandPlanService.selectById(serialNum);
    			demandPlanMateriels = demandPlanMaterielService.selectListByDemandPlanSerial(serialNum);
    			for(DemandPlanMateriel materiel:demandPlanMateriels){
    				if(materiel.getMateriel()!=null){
    					materiel.setMaterielName(materiel.getMateriel().getMaterielName());
        				materiel.setMaterielNum(materiel.getMateriel().getMaterielNum());
        				materiel.setSpecifications(materiel.getMateriel().getSpecifications());
        				materiel.setUnit(materiel.getMateriel().getUnit());
        				materiel.setMaterielSerial(materiel.getMateriel().getSerialNum());
        				materiel.setSupplyMateriels(materiel.getMateriel().getSupplyMateriels());
        				
    				}
    			}
    		}
    		map.put("demandPlan", demandPlan);
    		map.put("demandPlanMateriels", demandPlanMateriels);
    	}catch(Exception e){
    		System.out.println("获取需求计划出错"+e.getMessage());
    		return null;
    	}
    	return map;
    }

    *//**
     * @Description (删除需求计划信息)
     * @param request
     * @return
     *//*
    @RequestMapping(value="deleteDemandPlan",method=RequestMethod.POST)
    @ResponseBody
    public String deleteDemandPlan(Map<String, Object> map,@RequestBody String serialNums,HttpServletRequest request) {
    	String flag = "0"; //默认失败
    	try{
    		if(StringUtils.isNotEmpty(serialNums)){
    			List<String> serialNumArray  = Arrays.asList(serialNums.split(","));
    			demandPlanService.deleteBatch(serialNumArray);
    		}
    	}catch(Exception e){
    		System.out.println(e.getMessage());
    		flag = "1";
    	}
    	return flag;
    }
    
    *//**
     * 
     * @Description (选择物料)
     * @param ids
     * @return
     *//*
    @RequestMapping(value="chooseMateriel",method=RequestMethod.POST)
    @ResponseBody
    public List<Materiel> chooseMateriel(@RequestBody String ids){
    	List<Materiel> list = null;
    	try {
    		list = demandPlanService.chooseMateriel(ids);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    	
    	return list;
    }
    
    
    
    *//**
     * @Description (获取列表数据)
     * @param request
     * @return
     *//*
    @RequestMapping(value="demandPlanMaterialList",method=RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> demandPlanMaterialList(Map<String, Object> map,HttpServletRequest request,@RequestBody String params) {
    	try {
    		params = URLDecoder.decode(params, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	   ObjectMapper objectMapper = new ObjectMapper();
    	   DemandPlanMateriel  demandPlanMateriel = null;
		   try {
			   demandPlanMateriel = objectMapper.readValue(params.substring(7),DemandPlanMateriel.class);
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				System.out.println(this.getClass()+"---------"+ e.getMessage());
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				System.out.println(this.getClass()+"---------"+ e.getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(this.getClass()+"---------"+ e.getMessage());
			} catch (Exception e) {
		    	// TODO Auto-generated catch block
		    	System.out.println(this.getClass()+"---------"+ e.getMessage());
			}

    	List<DemandPlanMateriel> demandPlanMateriels = demandPlanMaterielService.getListByCondition(demandPlanMateriel,1, 99999999).getResult();
		// 封装datatables数据返回到前台
		Map<String,Object> pageMap = new HashMap<String,Object>();
		pageMap.put("draw", 1);
		pageMap.put("recordsTotal", demandPlanMateriel==null?0:demandPlanMateriels.size());
		pageMap.put("recordsFiltered", demandPlanMateriel==null?0:demandPlanMateriels.size());
		pageMap.put("data", demandPlanMateriels);
		return new ResponseEntity<Map<String,Object>>(pageMap, HttpStatus.OK);
    }
    
    *//**
     * @Description (保存续期计划物料信息)
     * @param request
     * @return
     *//*
    @RequestMapping(value="saveDemandPlanMateriel",method=RequestMethod.POST)
    @ResponseBody
    public DemandPlanMateriel saveDemandPlanMateriel(Map<String, Object> map,@RequestBody DemandPlanMateriel materiel,HttpServletRequest request) {
    	String flag ="0"; //默认失败

        	try{
        		Subject currentUser = SecurityUtils.getSubject();
        		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
        		if(StringUtils.isEmpty(materiel.getSerialNum())){
        			materiel.setSerialNum(ApplicationUtils.random32UUID());
        			materiel.setCreateTime(new Date());
        			materiel.setCreator(currenLoginName);
        			materiel.setUpdateTime(new Date());
        			materiel.setUpdater(currenLoginName);
        			demandPlanMaterielService.insert(materiel);
        		}else{
        			materiel.setUpdateTime(new Date());
        			materiel.setUpdater(currenLoginName);
        			demandPlanMaterielService.update(materiel);
        		}
        		materiel.setSupplyName(demandPlanMaterielService.selectSupplyName(materiel.getSupplyMaterielSerial()));
        		int remainTime = 0;
				try {
					remainTime = DateUtil.daysBetween(new Date(), materiel.getDeliveryDate());
				} catch (Exception e) {
					System.out.println("deliverDate----"+e.getMessage());
				}
				materiel.setRemainTime(String.valueOf(remainTime<0?0:remainTime));
        		
        		flag = "1";
        	}catch(Exception e){
        		System.out.println(e.getMessage());
        		return null;
        	}
    	return materiel;
    }
    
    *//**
     * @Description (查看需求计划物料信息)
     * @param request
     * @return
     *//*
    @RequestMapping(value="viewDemandPlanMateriel",method=RequestMethod.POST)
    @ResponseBody
    public DemandPlanMateriel viewDemandPlanMateriel(Map<String, Object> map,@RequestBody String serialNum,HttpServletRequest request) {
    	
    	DemandPlanMateriel materiel = null;
        	try{
        		if(StringUtils.isNotEmpty(serialNum)){
        			materiel = demandPlanMaterielService.selectById(serialNum);
        		}
        		
        		
        	}catch(Exception e){
        		System.out.println(e.getMessage());
        		return null;
        	}
    	return materiel;
    }

    *//**
     * @Description (删除需求计划物料信息)
     * @param request
     * @return
     *//*
    @RequestMapping(value="deleteDemandPlanMateriel",method=RequestMethod.POST)
    @ResponseBody
    public String deleteDemandPlanMateriel(Map<String, Object> map,@RequestBody String serialNums,HttpServletRequest request) {
    	String flag = "0"; //默认失败
    	try{
    		if(StringUtils.isNotEmpty(serialNums)){
    			List<String> serialNumArray  = Arrays.asList(serialNums.split(","));
    			demandPlanMaterielService.deleteBatch(serialNumArray);
    		}
    		flag = "1";
    	}catch(Exception e){
    		System.out.println(e.getMessage());
    		
    	}
    	return flag;
    }
    
    *//**
     * @Description (下载导入模板)
     * @param request
     * @return
     *//*
    @RequestMapping("downloadImportTemp")
    public void downloadCompanyTemp(Map<String, Object> map,HttpServletRequest request,HttpServletResponse response) {
    	ExcelUtil.importTempDownLoad(request, response, "demandPlan");
    }
    
    *//**
     * @Description (需求计划导入)
     * @param request
     * @return
     *//*
    @RequestMapping("demandPlanImport")
    @ResponseBody
    public Map<String,String> demandPlanImport(@RequestParam(value = "excelFile") MultipartFile excelFile,HttpServletRequest request,HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String, String>();
    	 try {
		     
			ExcelReader excelReader = new ExcelReader(excelFile.getInputStream());
			//final List<Company> companyList = new ArrayList<Company>(); 
			Subject currentUser = SecurityUtils.getSubject();
    		final String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
			final DemandPlan demandPlan = new DemandPlan();
			final String demandPlanSerial = ApplicationUtils.random32UUID();
			final List<DemandPlanMateriel> demandPlanMateriels = new ArrayList<DemandPlanMateriel>();
			excelReader.readExcelContent(new RowHandler() {
				@Override
				public void handle(List<Object> row,int i) throws Exception {
					if(!CollectionUtils.isEmpty(row)){
						try{
							if(i==1){
								demandPlan.setDemandPlanNum(row.get(1).toString());
								demandPlan.setBuyComId(companyService.selectComIdByComName(row.get(4).toString()));
							}else if(i==2){
								demandPlan.setReleaseDate((Date)row.get(1));
								demandPlan.setRemark(row.get(4).toString());
							}else if(i==3){
								
							}else{
								DemandPlanMateriel materiel = new DemandPlanMateriel();
								materiel.setSerialNum(ApplicationUtils.random32UUID());
								materiel.setDemandPlanSerial(demandPlanSerial);
								String serialNum = demandPlanMaterielService.selectMaterielSerialByMaterielNum(row.get(1).toString());
								materiel.setMaterielSerial(serialNum);
								materiel.setSupplyMaterielSerial(serialNum);
								materiel.setAmount(row.get(2).toString());
								materiel.setDeliveryDate((Date)row.get(3));
								materiel.setDeliveryAddress(row.get(4).toString());
								materiel.setCreator(currenLoginName);
								materiel.setCreateTime(new Date());
								materiel.setUpdater(currenLoginName);
								demandPlanMateriels.add(materiel);
							}
						}catch(Exception  e){
							throw new Exception("第"+(i+1)+"行数据异常请检查，数据内容："+row.toString());
						}
						
					}
					
				}
			}, 1);
			demandPlan.setSerialNum(demandPlanSerial);
			demandPlan.setCreator(currenLoginName);
			demandPlan.setCreateTime(new Date());
			demandPlan.setUpdater(currenLoginName);
			demandPlan.setUpdateTime(new Date());
				//companyService.insertBatch(companyList);
			demandPlanService.insertDemandPlanInfo(demandPlan,demandPlanMateriels);
			map.put("data", "success");
		} catch (Exception e1) {
			map.put("data", e1.getMessage());
		}
    	
         return map;
    }
    
    */
    /**
     * @Description (导出需求计划)
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

    

}
