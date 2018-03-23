package com.congmai.zhgj.web.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.core.util.DateUtil;
import com.congmai.zhgj.core.util.ExcelReader;
import com.congmai.zhgj.core.util.MessageConstants;
import com.congmai.zhgj.core.util.StringUtil;
import com.congmai.zhgj.core.util.ExcelReader.RowHandler;
import com.congmai.zhgj.core.util.ExcelUtil;
import com.congmai.zhgj.core.util.UserUtil;
import com.congmai.zhgj.log.annotation.OperationLog;
import com.congmai.zhgj.web.enums.StaticConst;
import com.congmai.zhgj.web.event.EventExample;
import com.congmai.zhgj.web.event.SendMessageEvent;
import com.congmai.zhgj.web.model.BOMMateriel;
import com.congmai.zhgj.web.model.BOMMaterielExample;
import com.congmai.zhgj.web.model.ClauseAdvance;
import com.congmai.zhgj.web.model.ClauseAfterSales;
import com.congmai.zhgj.web.model.ClauseCheckAccept;
import com.congmai.zhgj.web.model.ClauseDelivery;
import com.congmai.zhgj.web.model.ClauseSettlement;
import com.congmai.zhgj.web.model.ClauseSettlementDetail;
import com.congmai.zhgj.web.model.Company;
import com.congmai.zhgj.web.model.ContractVO;
import com.congmai.zhgj.web.model.DeliveryMaterielVO;
import com.congmai.zhgj.web.model.DeliveryVO;
import com.congmai.zhgj.web.model.DemandMateriel;
import com.congmai.zhgj.web.model.DemandMaterielExample;
import com.congmai.zhgj.web.model.Materiel;
import com.congmai.zhgj.web.model.OperateLog;
import com.congmai.zhgj.web.model.OrderFile;
import com.congmai.zhgj.web.model.OrderFileExample;
import com.congmai.zhgj.web.model.OrderInfo;
import com.congmai.zhgj.web.model.OrderMateriel;
import com.congmai.zhgj.web.model.OrderMaterielExample;
import com.congmai.zhgj.web.model.ProcurementPlan;
import com.congmai.zhgj.web.model.ProcurementPlanExample;
import com.congmai.zhgj.web.model.ProcurementPlanMateriel;
import com.congmai.zhgj.web.model.ProcurementPlanMaterielExample;
import com.congmai.zhgj.web.model.RelationFile;
import com.congmai.zhgj.web.model.StockInOutRecord;
import com.congmai.zhgj.web.model.SupplyMateriel;
import com.congmai.zhgj.web.model.SupplyMaterielExample;
import com.congmai.zhgj.web.model.User;
import com.congmai.zhgj.web.model.Warehouse;
import com.congmai.zhgj.web.service.CompanyService;
import com.congmai.zhgj.web.service.ContractService;
import com.congmai.zhgj.web.service.IProcessService;
import com.congmai.zhgj.web.service.OrderMaterielService;
import com.congmai.zhgj.web.service.OrderService;
import com.congmai.zhgj.web.service.ProcurementPlanMaterielService;
import com.congmai.zhgj.web.service.ProcurementPlanService;
import com.congmai.zhgj.web.service.BOMMaterielService;


/**
 * 
 * @ClassName ProcurementPlanController
 * @Description 物料信息业务处理
 * @author qfzhao
 * @Date 2017年7月28日 下午2:57:52
 * @version 1.0.0
 */
@Controller
@RequestMapping("/procurementPlan")
public class ProcurementPlanController {
	private static final Logger logger = Logger.getLogger(ProcurementPlanController.class);
    @Resource
    private ProcurementPlanService procurementPlanService;
    @Resource
    private ProcurementPlanMaterielService procurementPlanMaterielService;
    @Resource
    private com.congmai.zhgj.web.service.BOMMaterielService BOMMaterielService;
    @Resource
    private com.congmai.zhgj.web.service.MaterielService materielService;
    @Resource
    private com.congmai.zhgj.web.service.SupplyMaterielService supplyMaterielService;
    

    /**
     * 保存订单
     */
    @RequestMapping(value = "/saveProcurementPlan", method = RequestMethod.POST)
    @ResponseBody
    public ProcurementPlan saveProcurementPlan(@RequestBody String params) {
    	ProcurementPlan procurementPlanInfo = json2ProcurementPlan(params);
            
    	if(procurementPlanInfo.getSerialNum()==null||procurementPlanInfo.getSerialNum().isEmpty()){//新增
    		insetProcurementPlan(procurementPlanInfo);
    		
    	}else{//更新
    		updateProcurementPlan(procurementPlanInfo);
    	}
    	/*if("1".equals(procurementPlanInfo.getStatus())){
    		//启动订单审批测试流程-start
    		startProcurementPlanProcess(procurementPlanInfo);
    		//启动订单审批测试流程-end
    	}*/
    	procurementPlanInfo = procurementPlanService.selectById(procurementPlanInfo.getSerialNum());
		return procurementPlanInfo;
    }
   
    
	private void insetProcurementPlan(ProcurementPlan procurementPlanInfo) {
		procurementPlanInfo.setSerialNum(ApplicationUtils.random32UUID());
		Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
		procurementPlanInfo.setCreator(currenLoginName);
		procurementPlanInfo.setUpdater(currenLoginName);
		procurementPlanInfo.setCreateTime(new Date());
		procurementPlanInfo.setUpdateTime(new Date());
		procurementPlanInfo.setBuyDate(new Date());
		procurementPlanInfo.setMaker(currenLoginName);
		procurementPlanInfo.setStatus("0");
		
		procurementPlanService.insert(procurementPlanInfo);
	}
	
	private void updateProcurementPlan(ProcurementPlan procurementPlanInfo) {
		Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
		procurementPlanInfo.setUpdater(currenLoginName);
		procurementPlanInfo.setUpdateTime(new Date());
		procurementPlanService.update(procurementPlanInfo);
	}

	private ProcurementPlan json2ProcurementPlan(String params) {
		params = params.replace("\\", "");
		ObjectMapper objectMapper = new ObjectMapper();  
        ProcurementPlan procurementPlanInfo = new ProcurementPlan();
		try {
			procurementPlanInfo = objectMapper.readValue(params, ProcurementPlan.class);
		}catch (Exception e1) {
			e1.printStackTrace();
		}
		return procurementPlanInfo;
	}


    
    /**
     * 
     * @Description (各类订单列表查询)
     * @param type（只分为销售：sale，采购:buy两种）
     * @param selectFor(自定义参数值，用于控制生成查询sql)
     * @param fram（是否框架合同1是0否）
     * @return
     */
    @RequestMapping("/findProcurementPlanList")
    @ResponseBody
    public ResponseEntity<Map> findProcurementPlanList(String type,String selectFor,String fram,@RequestParam(required=false)String demandPlanSerial) {
    	List<ProcurementPlan> procurementPlanInfoList = new ArrayList<ProcurementPlan>();

    	
    	ProcurementPlanExample m =new ProcurementPlanExample();
    	//and 条件1
    	com.congmai.zhgj.web.model.ProcurementPlanExample.Criteria criteria =  m.createCriteria();
    	criteria.andDelFlgEqualTo("0");

    	procurementPlanInfoList = procurementPlanService.selectList(m);
    	
    	//封装datatables数据返回到前台
		Map pageMap = new HashMap();
		pageMap.put("draw", 1);
		pageMap.put("recordsTotal", procurementPlanInfoList==null?0:procurementPlanInfoList.size());
		pageMap.put("recordsFiltered", procurementPlanInfoList==null?0:procurementPlanInfoList.size());
		pageMap.put("data", procurementPlanInfoList);
		return new ResponseEntity<Map>(pageMap, HttpStatus.OK);

    }

   
    /**
	 * 
	 * @Description 批量删除订单
	 * @param ids
	 * @return
	 */
    @OperationLog(operateType = "update" ,operationDesc = "订单删除")
	@RequestMapping(value = "/deleteProcurementPlans", method = RequestMethod.POST)
	public ResponseEntity<Void> deleteProcurementPlans(@RequestBody String ids) {
		if ("".equals(ids) || ids == null) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		List<String> idList = ApplicationUtils.getIdList(ids);
		for(String id : idList){
			ProcurementPlan m = new ProcurementPlan();
			m.setSerialNum(id);
			m.setDelFlg("1");
			m.setUpdateTime(new Date());
			Subject currentUser = SecurityUtils.getSubject();
			String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
	    	m.setUpdater(currenLoginName);
			procurementPlanService.update(m);
		}

		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
	/**
	 * 
	 * @Description 获取订单信息
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/getProcurementPlanInfo")
	@ResponseBody
	public Map getProcurementPlan(String serialNum,ProcurementPlan procurementPlan) {
		procurementPlan = procurementPlanService.selectById(serialNum);
		Map<String, Object> map = new HashMap<String, Object>();
    	map.put("procurementPlan", procurementPlan);
    	List<ProcurementPlanMateriel> procurementPlanMateriel=null;
    	
    	ProcurementPlanMaterielExample m =new ProcurementPlanMaterielExample();
    	//and 条件1
    	com.congmai.zhgj.web.model.ProcurementPlanMaterielExample.Criteria criteria =  m.createCriteria();
    	criteria.andDelFlgEqualTo("0");
    	criteria.andProcurementPlanSerialEqualTo(serialNum);
    	m.setOrderByClause(" sort asc");
    	procurementPlanMateriel = procurementPlanMaterielService.selectList(m);

    	map.put("procurementPlanMateriel", procurementPlanMateriel);
    	
    	List<DemandMateriel> demandMateriels=null;
    	DemandMaterielExample dme =new DemandMaterielExample();
    	//and 条件1
    	com.congmai.zhgj.web.model.DemandMaterielExample.Criteria criteria1 =  dme.createCriteria();
    	criteria1.andDelFlgEqualTo("0");
    	criteria1.andProcurementPlanSerialEqualTo(serialNum);
    	m.setOrderByClause(" sort asc");
    	demandMateriels = procurementPlanMaterielService.selecDemandtList(dme);
    	map.put("demandMateriel", demandMateriels);
    	return map;
	}
	
	
	
	
	/**
     * @Description (保存采购清单物料信息)
     * @param request
     * @return
     */
    @RequestMapping(value="saveProcurementPlanMateriel",method=RequestMethod.POST)
    @ResponseBody
    public ProcurementPlanMateriel saveProcurementPlanMateriel(Map<String, Object> map,@RequestBody ProcurementPlanMateriel procurementPlanMateriel,HttpServletRequest request) {
    	String flag ="0"; //默认失败
        	try{
        		Subject currentUser = SecurityUtils.getSubject();
        		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
        		if(StringUtils.isEmpty(procurementPlanMateriel.getSerialNum())){
        			procurementPlanMateriel.setSerialNum(ApplicationUtils.random32UUID());
        			procurementPlanMateriel.setCreateTime(new Date());
        			procurementPlanMateriel.setCreator(currenLoginName);
        			procurementPlanMateriel.setUpdateTime(new Date());
        			procurementPlanMateriel.setUpdater(currenLoginName);
        			procurementPlanMaterielService.insert(procurementPlanMateriel);
        			
        		}else{
        			procurementPlanMateriel.setUpdateTime(new Date());
        			procurementPlanMateriel.setUpdater(currenLoginName);
        			procurementPlanMaterielService.update(procurementPlanMateriel);
        		}
        		flag = "1";
        	}catch(Exception e){
        		//20180110 qhzhao System.out.println(e.getMessage());
        		return null;
        	}
        	procurementPlanMateriel = procurementPlanMaterielService.selectById(procurementPlanMateriel.getSerialNum());
    	return procurementPlanMateriel;
    }
    
    
    /**
     * 
     * @Description 保存所有采购清单物料
     * @param params
     * @return
     */
    @RequestMapping(value = "/saveAllProcurementPlanMateriel", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> saveAllProcurementPlanMateriel(@RequestBody String params) {
    	params = params.replace("\\", "");
/*		ObjectMapper objectMapper = new ObjectMapper();  
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, ProcurementPlanMateriel.class);  */
    	Map<String,Object>map=new HashMap<String,Object>();
        List<ProcurementPlanMateriel> procurementPlanMateriel;
		try {
			procurementPlanMateriel = JSON.parseArray(params, ProcurementPlanMateriel.class);
	    	if(!CollectionUtils.isEmpty(procurementPlanMateriel)){
	    		Subject currentUser = SecurityUtils.getSubject();
	    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
		    	for(ProcurementPlanMateriel f:procurementPlanMateriel){
		    		f.setSerialNum(ApplicationUtils.random32UUID());
		    		f.setCreator(currenLoginName);
	    			f.setUpdater(currenLoginName);
	    			f.setCreateTime(new Date());
	    			f.setUpdateTime(new Date());
	    			f.setDelFlg("0");
		    	}
		    	//填充File******↑↑↑↑↑↑********
		    	procurementPlanMaterielService.betchInsertProcurementPlanMateriel(procurementPlanMateriel);
		    	//数据插入******↑↑↑↑↑↑********
	        }
	    	map.put("procurementPlanMateriel", procurementPlanMateriel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
    }
    
    /**
     * @Description (保存采购清单物料信息)
     * @param request
     * @return
     */
    @RequestMapping(value="saveDemandMateriel",method=RequestMethod.POST)
    @ResponseBody
    public DemandMateriel saveDemandMateriel(Map<String, Object> map,@RequestBody DemandMateriel demandMateriel,HttpServletRequest request) {
    	String flag ="0"; //默认失败
        	try{
        		Subject currentUser = SecurityUtils.getSubject();
        		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
        		if(StringUtils.isEmpty(demandMateriel.getSerialNum())){
        			demandMateriel.setSerialNum(ApplicationUtils.random32UUID());
        			demandMateriel.setCreateTime(new Date());
        			demandMateriel.setCreator(currenLoginName);
        			demandMateriel.setUpdateTime(new Date());
        			demandMateriel.setUpdater(currenLoginName);
        			List<DemandMateriel>demandMateriels=new ArrayList<DemandMateriel>();
        			demandMateriels.add(demandMateriel);
        			procurementPlanMaterielService.betchInsertDemandMateriel(demandMateriels);
        			
        		}else{
        			demandMateriel.setUpdateTime(new Date());
        			demandMateriel.setUpdater(currenLoginName);
        			procurementPlanMaterielService.updateDemandMateriel(demandMateriel);
        		}
        		flag = "1";
        	}catch(Exception e){
        		//20180110 qhzhao System.out.println(e.getMessage());
        		return null;
        	}
    	return demandMateriel;
    }
    /**
     * 
     * @Description 保存所有需求物料
     * @param params
     * @return
     */
    @RequestMapping(value = "/saveAllDemandMateriel", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> saveAllDemandMateriel(@RequestBody String params) {
    	params = params.replace("\\", "");
        List<DemandMateriel> demandMateriels;
        Map<String,Object>map=new HashMap<String,Object>();
		try {
			demandMateriels = JSON.parseArray(params, DemandMateriel.class);
	    	if(!CollectionUtils.isEmpty(demandMateriels)){
	    		Subject currentUser = SecurityUtils.getSubject();
	    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
	    		BigDecimal totalDemandCount=BigDecimal.ZERO;
		    	for(DemandMateriel f:demandMateriels){
		    		f.setSerialNum(ApplicationUtils.random32UUID());
		    		f.setCreator(currenLoginName);
	    			f.setUpdater(currenLoginName);
	    			f.setCreateTime(new Date());
	    			f.setUpdateTime(new Date());
	    			f.setDelFlg("0");
	    			totalDemandCount=totalDemandCount.add(new  BigDecimal(f.getPlanCount()));
		    	}
		    	procurementPlanMaterielService.betchInsertDemandMateriel(demandMateriels);
		    	ProcurementPlan procurementPlan=new ProcurementPlan();
		    	procurementPlan.setSerialNum(demandMateriels.get(0).getProcurementPlanSerial());
		    	procurementPlan.setBuyCount(totalDemandCount.toString());
		    	procurementPlanService.update(procurementPlan);
		    	//数据插入******↑↑↑↑↑↑********
		    	map.put("demandMateriel", demandMateriels);
		    	map.put("totalDemandCount", totalDemandCount.toString());
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
    }
    /**
     * @Description (查看订单物料信息)
     * @param request
     * @return
     */
    @RequestMapping(value="viewProcurementPlanMateriel",method=RequestMethod.POST)
    @ResponseBody
    public ProcurementPlanMateriel viewProcurementPlanMateriel(Map<String, Object> map,@RequestBody String serialNum,HttpServletRequest request) {
    	
    	ProcurementPlanMateriel materiel = null;
        	try{
        		if(StringUtils.isNotEmpty(serialNum)){
        			materiel = procurementPlanMaterielService.selectById(serialNum);
        		}
        		
        	}catch(Exception e){
        		//20180110 qhzhao System.out.println(e.getMessage());
        		return null;
        	}
    	return materiel;
    }

    /**
     * @Description (删除采购清单物料信息)
     * @param request
     * @return
     */
    @RequestMapping(value="deleteProcurementPlanMateriel",method=RequestMethod.POST)
    @ResponseBody
    public String deleteProcurementPlanMateriel(Map<String, Object> map,@RequestBody String serialNums,HttpServletRequest request) {
    	String flag = "0"; //默认失败
    	try{
    		if(StringUtils.isNotEmpty(serialNums)){
    			procurementPlanMaterielService.deleteProcurementPlanMateriels(serialNums);
    		}
    		flag = "1";
    	}catch(Exception e){
    		//20180110 qhzhao System.out.println(e.getMessage());
    		
    	}
    	return flag;
    }
    /**
     * @Description (删除需求物料信息)
     * @param request
     * @return
     */
    @RequestMapping(value="deleteDemandMateriel",method=RequestMethod.POST)
    @ResponseBody
    public String deleteDemandMateriel(Map<String, Object> map,@RequestBody String serialNums,HttpServletRequest request) {
    	String flag = "0"; //默认失败
    	try{
    		if(StringUtils.isNotEmpty(serialNums)){
    			procurementPlanMaterielService.deleteDemandMateriel(serialNums);
    		}
    		flag = "1";
    	}catch(Exception e){
    		//20180110 qhzhao System.out.println(e.getMessage());
    		
    	}
    	return flag;
    }
    
    /**
     * @Description (导出订单信息)
     * @param request
     * @return
     */
    @RequestMapping("exportProcurementPlan")
    public void exportProcurementPlan(String type,String fram,Map<String, Object> map,HttpServletRequest request,HttpServletResponse response) {
    		Map<String, Object> dataMap = new HashMap<String, Object>();
    		List<ProcurementPlan> procurementPlanInfoList = new ArrayList<ProcurementPlan>();
        	
    		dataMap.put("procurementPlanInfoList",procurementPlanInfoList);
    		if("sale".equals(type)){
    			ExcelUtil.export(request, response, dataMap, "procurementPlanInfo", "订单信息");
        	}else if("buy".equals(type)){
        		ExcelUtil.export(request, response, dataMap, "buyProcurementPlan", "订单信息");
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
    		ExcelUtil.importTempDownLoad(request, response, "procurementPlanInfo");
    	}else if("buy".equals(type)){
    		ExcelUtil.importTempDownLoad(request, response, "buyProcurementPlan");
    	}
    	
    }
    
    /**
     * @Description (采购订单信息导入)
     * @param request
     * @return
     */
    @RequestMapping("buyProcurementPlanImport")
    @ResponseBody
    public Map<String,String> buyProcurementPlanImport(@RequestParam(value = "excelFile") MultipartFile excelFile,HttpServletRequest request,HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String, String>();
    	 try {
			ExcelReader excelReader = new ExcelReader(excelFile.getInputStream());
			excelReader.readExcelContent(new RowHandler() {
				@Override
				public void handle(List<Object> row,int i) throws Exception {
					if(!CollectionUtils.isEmpty(row)){
						try{
							ProcurementPlan procurementPlanInfo = new ProcurementPlan();

							

							procurementPlanInfo.setSerialNum(ApplicationUtils.random32UUID());
				    		Subject currentUser = SecurityUtils.getSubject();
				    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
				    		procurementPlanInfo.setCreator(currenLoginName);
				    		procurementPlanInfo.setUpdater(currenLoginName);
				    		procurementPlanInfo.setCreateTime(new Date());
				    		procurementPlanInfo.setUpdateTime(new Date());
				    		procurementPlanInfo.setStatus("0");
				    		
				    		procurementPlanService.insert(procurementPlanInfo);
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
    @RequestMapping("procurementPlanImport")
    @ResponseBody
    public Map<String,String> procurementPlanImport(@RequestParam(value = "excelFile") MultipartFile excelFile,String type,HttpServletRequest request,HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String, String>();
    	 try {
			ExcelReader excelReader = new ExcelReader(excelFile.getInputStream());
			excelReader.readExcelContent(new RowHandler() {
				@Override
				public void handle(List<Object> row,int i) throws Exception {
					if(!CollectionUtils.isEmpty(row)){
						try{
							ProcurementPlan procurementPlanInfo = new ProcurementPlan();

							

							procurementPlanInfo.setSerialNum(ApplicationUtils.random32UUID());
				    		Subject currentUser = SecurityUtils.getSubject();
				    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
				    		procurementPlanInfo.setCreator(currenLoginName);
				    		procurementPlanInfo.setUpdater(currenLoginName);
				    		procurementPlanInfo.setCreateTime(new Date());
				    		procurementPlanInfo.setUpdateTime(new Date());
				    		procurementPlanInfo.setStatus("0");
				    		
				    		procurementPlanService.insert(procurementPlanInfo);
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
    
    
    
    public void ListSort(List<OperateLog> list) {
    	if(list!=null){
    		Collections.sort(list, new Comparator<OperateLog>() {
    			@Override
    			public int compare(OperateLog o1, OperateLog o2) {
    				try {
    					Date dt1 = o1.getOperationTime();
    					Date dt2 = o2.getOperationTime();
    					if (dt1.getTime() > dt2.getTime()) {
    						return 1;
    					} else if (dt1.getTime() < dt2.getTime()) {
    						return -1;
    					} else {
    						return 0;
    					}
    				} catch (Exception e) {
    					e.printStackTrace();
    				}
    				return 0;
    			}
    		});
    	}
	}
   
    
    @Resource
    private OrderService orderService;
    @Resource
    private OrderMaterielService orderMaterielService;
    @Resource
    private ContractService contractService;
    @Resource
    private CompanyService companyService;
    
    /**
	 * 
	 * @Description 采购计划生成采购订单
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/procurementPlanGenerateBuy")
	@ResponseBody
	public ProcurementPlan procurementPlanGenerateBuy(String serialNum) {
		Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
		
		ProcurementPlan procurementPlan = procurementPlanService.selectById(serialNum);
		Map<String, Object> map = new HashMap<String, Object>();
    	map.put("procurementPlan", procurementPlan);
    	List<ProcurementPlanMateriel> procurementPlanMateriel=null;
    	
    	ProcurementPlanMaterielExample m =new ProcurementPlanMaterielExample();
    	//and 条件1
    	com.congmai.zhgj.web.model.ProcurementPlanMaterielExample.Criteria criteria =  m.createCriteria();
    	criteria.andDelFlgEqualTo("0");
    	criteria.andProcurementPlanSerialEqualTo(serialNum);
    	m.setOrderByClause(" sort asc");
    	procurementPlanMateriel = procurementPlanMaterielService.selectList(m);
    	
    	Set<String> supplySet = new HashSet<String>();//存入所有物料供应商，不会重复
    	if(procurementPlanMateriel!=null){//循环供应物料，集合所有的供应商
    		for(ProcurementPlanMateriel o:procurementPlanMateriel){
    			if(o.getSupplyComId()!=null){
    				supplySet.add(o.getSupplyComId());
    			}
    		}
    	}
    	if(supplySet.size()>0){
    		for(String supplyComId : supplySet){//对于某一供应商生成采购订单
    			String newSerialNum = ApplicationUtils.random32UUID();//生成新的采购订单流水号
    			String newContractSerialNum = ApplicationUtils.random32UUID();//生成新的采购合同流水号
    			
    			OrderInfo newOrderInfo = new OrderInfo();//生成新的采购订单
    			
    
    			newOrderInfo.setSerialNum(newSerialNum);//设置新的流水号
    			String temp = orderService.getNumCode("PO");
    			newOrderInfo.setOrderNum(temp);
    			newOrderInfo.setSupplyComId(supplyComId);//设置新的供应商
    			newOrderInfo.setContractSerial(newContractSerialNum);
    			newOrderInfo.setOrderSerial(null);//
    			newOrderInfo.setDemandPlanSerial(procurementPlan.getProcurementPlanNum());
    			newOrderInfo.setBuyComId(null);//表示采购商为平台，即采购订单
    			newOrderInfo.setOrderType(StaticConst.getInfo("zizhuBuy"));//设置为自主采购
    			
    			Company supply =  companyService.selectOne(supplyComId);
    			if(supply!=null&&StaticConst.getInfo("waimao").equals(supply.getTradeType())){
    				newOrderInfo.setTradeType(StaticConst.getInfo("waimao"));//设置为外贸
    			}else {
    				newOrderInfo.setTradeType(StaticConst.getInfo("neimao"));//设置为内贸
				}
    			
    			newOrderInfo.setSeller(StaticConst.getInfo("comName"));
    			newOrderInfo.setOrderDate(new Date());
    			newOrderInfo.setRate("17");
    			newOrderInfo.setCurrency("人民币");
    			
    			newOrderInfo.setCreator(currenLoginName);
    			newOrderInfo.setUpdater(currenLoginName);
    			newOrderInfo.setMaker(currenLoginName);
    			newOrderInfo.setCreateTime(new Date());
    			newOrderInfo.setUpdateTime(new Date());
    			newOrderInfo.setMakeDate(new Date());
    			newOrderInfo.setStatus("0");
    			orderService.insert(newOrderInfo);
    			
    			ContractVO newcontract = new ContractVO();
				newcontract.setId(newContractSerialNum);
				newcontract.setComId(supplyComId);
				newcontract.setContractNum(orderService.getNumCode("CA"));
				newcontract.setContractType(StaticConst.getInfo("buyContract"));//设置合同类型为采购合同
				newcontract.setCreator(currenLoginName);
				newcontract.setUpdater(currenLoginName);
				newcontract.setCreateTime(new Date());
				newcontract.setUpdateTime(new Date());
				newcontract.setStatus(ContractVO.WAIT_SIGN);
				
				contractService.insertContract(newcontract);
				
    			List<OrderMateriel> newMaterielList = new ArrayList<OrderMateriel>();//生成新的采购订单物料
    			Double materielCount = 0D;
    			for(ProcurementPlanMateriel o:procurementPlanMateriel){
        			if(o.getSupplyComId()!=null){
        				if(supplyComId.equals(o.getSupplyComId())){
        					OrderMateriel orderMateriel = new OrderMateriel();
        					orderMateriel.setSerialNum(ApplicationUtils.random32UUID());
        					orderMateriel.setSupplyMaterielSerial(null);//采购订单物料为标准物料
        					orderMateriel.setOrderSerial(newSerialNum);
        					orderMateriel.setCreator(currenLoginName);
        					orderMateriel.setUpdater(currenLoginName);
        					orderMateriel.setCreateTime(new Date());
        					orderMateriel.setUpdateTime(new Date());
        					orderMateriel.setMaterielSerial(o.getMaterielSerial());
        					orderMateriel.setAmount(o.getBuyCount());
        					orderMateriel.setDeliveryAddress(o.getDeliveryAddress());
        					orderMateriel.setDeliveryDate(o.getDeliveryDate());
        					orderMateriel.setLastDeliveryDate(o.getLastDeliveryDate());
        					newMaterielList.add(orderMateriel);
        					
        					materielCount = materielCount + Double.parseDouble(o.getBuyCount());
        				}
        			}
        		}
    			orderMaterielService.betchInsertOrderMateriel(newMaterielList);//插入新的订单物料
    			
    			OrderInfo updateOrderMaterielCount = new OrderInfo();//修改销售订单的关联采购订单号
    			updateOrderMaterielCount.setSerialNum(newSerialNum);
    			updateOrderMaterielCount.setMaterielCount(materielCount.toString());
        		orderService.updateStatus(updateOrderMaterielCount);
    			
    	    	
    		}
    		
    		
    		ProcurementPlan updateProcurementPlan = new ProcurementPlan();
    		updateProcurementPlan.setSerialNum(serialNum);
    		updateProcurementPlan.setStatus("1");
    		updateProcurementPlan.setBuyDate(new Date());
    		procurementPlanService.update(updateProcurementPlan);
    	}
    	
    	procurementPlan = procurementPlanService.selectById(serialNum);
    	
    	//采购计划发布2采购人员
    	EventExample.getEventPublisher().publicSendMessageEvent(new SendMessageEvent(procurementPlan,MessageConstants.PALN_TO_BUY));
		return procurementPlan;
	}
	@RequestMapping(value = "/getProcurementPlanMateriels")
	@ResponseBody
	public Map<String, Object> getProcurementPlanMateriels(String serialNum) {//将选中bom物料分解
		Map<String, Object> map = new HashMap<String, Object>();
		List<ProcurementPlanMateriel>procurementPlanMateriels=new ArrayList<ProcurementPlanMateriel>();
		List<BOMMateriel>allBomMateriels=new ArrayList<BOMMateriel>();
		List<String> demandMaterielSerialNums = java.util.Arrays.asList(serialNum.split(","));
		if(org.apache.commons.collections.CollectionUtils.isNotEmpty(demandMaterielSerialNums)){//
			for(String demandMaterielSerialNum:demandMaterielSerialNums){
				BOMMaterielExample m1 =new BOMMaterielExample();
		    	com.congmai.zhgj.web.model.BOMMaterielExample.Criteria criteria1 =  m1.createCriteria();
		    	criteria1.andBomMaterielSerialEqualTo(demandMaterielSerialNum);
		    	List<BOMMateriel> bomMateriels = BOMMaterielService.selectList(m1);
		    	allBomMateriels.addAll(bomMateriels);
			}
		}
		if(org.apache.commons.collections.CollectionUtils.isNotEmpty(allBomMateriels)){
			for(BOMMateriel bomMateriel:allBomMateriels){
				ProcurementPlanMateriel  pMateriel = new ProcurementPlanMateriel();
    			pMateriel.setSerialNum(ApplicationUtils.random32UUID());
    			pMateriel.setMaterielSerial(bomMateriel.getMaterielSerial());
//    			pMateriel.setDemandMaterielSerial(bomMateriel.getBomMaterielSerial());
    			pMateriel.setSingleDose(bomMateriel.getSingleDose());
    			pMateriel.setMateriel(bomMateriel.getMateriel());
    			/*String materielId=bomMateriel.getMateriel().getMaterielId();//获取基本物料id
    			SupplyMaterielExample m2 =new SupplyMaterielExample();
		    	com.congmai.zhgj.web.model.SupplyMaterielExample.Criteria criteria2 =  m2.createCriteria();
		    	criteria2.andMaterielIdEqualTo(materielId);
		    	criteria2.andDelFlgEqualTo("0");
		    	List<SupplyMateriel> supplyMateriels = supplyMaterielService.selectList(m2);
		    	if(org.apache.commons.collections.CollectionUtils.isNotEmpty(supplyMateriels)){
		    		pMateriel.setSupplyMaterielSerial(supplyMateriels.get(0).getSerialNum());
		    	}*/
		    	procurementPlanMateriels.add(pMateriel);
    			/*pMateriel.setProcurementPlanSerial(newSerialNum);
    			pMateriel.setPlanCount(o.getAmount());
    			pMateriel.setBuyCount(o.getAmount());
    			pMateriel.setDeliveryAddress(o.getDeliveryAddress());
    			pMateriel.setDeliveryDate(DateUtil.addDay(o.getDeliveryDate(), -10));//采购计划中交付日期提前10天
    			pMateriel.setLastDeliveryDate(o.getLastDeliveryDate());*/
			}
			
		}
		map.put("procurementPlanMateriels", procurementPlanMateriels);
		return map;
	}
	 @RequestMapping(value = "/releaseProcurementPlanMateriel", method = RequestMethod.POST)
	    @ResponseBody
	    public void releaseProcurementPlanMateriel(@RequestBody String params) {
	    	params = params.replace("\\", "");
	        List<ProcurementPlanMateriel> procurementPlanMateriel;
	        ProcurementPlan procurementPlan=null;
			try {
				procurementPlanMateriel = JSON.parseArray(params, ProcurementPlanMateriel.class);
		    	if(!CollectionUtils.isEmpty(procurementPlanMateriel)){
		    		Subject currentUser = SecurityUtils.getSubject();
		    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
		    		Set<String> supplySet = new HashSet<String>();//存入所有物料供应商，不会重复
		    		if(procurementPlan==null){
		    			procurementPlan=procurementPlanService.selectById(procurementPlanMateriel.get(0).getProcurementPlanSerial());//获取采购计划
		    		}
		    		if(procurementPlanMateriel!=null){//循环供应物料，集合所有的供应商
		        		for(ProcurementPlanMateriel o:procurementPlanMateriel){
		        			if(o.getSupplyComId()!=null){
		        				supplySet.add(o.getSupplyComId());
		        			}
		        			/*ProcurementPlanMateriel ppm=new ProcurementPlanMateriel();
		        			ppm.setSerialNum(o.getSerialNum());
		        			ppm.setStatus("1");//
*/		        		}
		        	}
		        	if(supplySet.size()>0){
		        		for(String supplyComId : supplySet){//对于某一供应商生成采购订单
		        			String newSerialNum = ApplicationUtils.random32UUID();//生成新的采购订单流水号
		        			String newContractSerialNum = ApplicationUtils.random32UUID();//生成新的采购合同流水号
		        			
		        			OrderInfo newOrderInfo = new OrderInfo();//生成新的采购订单
		        			
		        
		        			newOrderInfo.setSerialNum(newSerialNum);//设置新的流水号
		        			String temp = orderService.getNumCode("PO");
		        			newOrderInfo.setOrderNum(temp);
		        			newOrderInfo.setSupplyComId(supplyComId);//设置新的供应商
		        			newOrderInfo.setContractSerial(newContractSerialNum);
		        		//	newOrderInfo.setOrderSerial(procurementPlan.getSaleOrder().getOrderNum());现在采购计划与销售无关
		        			newOrderInfo.setDemandPlanSerial(procurementPlan.getProcurementPlanNum());
		        			newOrderInfo.setBuyComId(null);//表示采购商为平台，即采购订单
		        			newOrderInfo.setOrderType(StaticConst.getInfo("zizhuBuy"));//设置为自主采购
		        			
		        			Company supply =  companyService.selectOne(supplyComId);
		        			if(supply!=null&&StaticConst.getInfo("waimao").equals(supply.getTradeType())){
		        				newOrderInfo.setTradeType(StaticConst.getInfo("waimao"));//设置为外贸
		        			}else {
		        				newOrderInfo.setTradeType(StaticConst.getInfo("neimao"));//设置为内贸
		    				}
		        			
		        			newOrderInfo.setSeller(StaticConst.getInfo("comName"));
		        			newOrderInfo.setOrderDate(new Date());
		        			newOrderInfo.setRate("17");
		        			newOrderInfo.setCurrency("人民币");
		        			
		        			newOrderInfo.setCreator(currenLoginName);
		        			newOrderInfo.setUpdater(currenLoginName);
		        			newOrderInfo.setMaker(currenLoginName);
		        			newOrderInfo.setCreateTime(new Date());
		        			newOrderInfo.setUpdateTime(new Date());
		        			newOrderInfo.setMakeDate(new Date());
		        			newOrderInfo.setStatus("0");
		        			orderService.insert(newOrderInfo);
		        			
		        			ContractVO newcontract = new ContractVO();
		    				newcontract.setId(newContractSerialNum);
		    				newcontract.setComId(supplyComId);
		    				newcontract.setContractNum(orderService.getNumCode("CA"));
		    				newcontract.setContractType(StaticConst.getInfo("buyContract"));//设置合同类型为采购合同
		    				newcontract.setCreator(currenLoginName);
		    				newcontract.setUpdater(currenLoginName);
		    				newcontract.setCreateTime(new Date());
		    				newcontract.setUpdateTime(new Date());
		    				newcontract.setStatus(ContractVO.WAIT_SIGN);
		    				
		    				contractService.insertContract(newcontract);
		    				
		        			List<OrderMateriel> newMaterielList = new ArrayList<OrderMateriel>();//生成新的采购订单物料
		        			Double materielCount = 0D;
		        			for(ProcurementPlanMateriel o:procurementPlanMateriel){
		            			if(o.getSupplyComId()!=null){
		            				if(supplyComId.equals(o.getSupplyComId())){
		            					OrderMateriel orderMateriel = new OrderMateriel();
		            					orderMateriel.setSerialNum(ApplicationUtils.random32UUID());
		            					orderMateriel.setSupplyMaterielSerial(null);//采购订单物料为标准物料
		            					orderMateriel.setOrderSerial(newSerialNum);
		            					orderMateriel.setCreator(currenLoginName);
		            					orderMateriel.setUpdater(currenLoginName);
		            					orderMateriel.setCreateTime(new Date());
		            					orderMateriel.setUpdateTime(new Date());
		            					orderMateriel.setMaterielSerial(o.getMaterielSerial());
		            					orderMateriel.setAmount(o.getBuyCount());
		            					orderMateriel.setDeliveryAddress(o.getDeliveryAddress());
		            					orderMateriel.setDeliveryDate(o.getDeliveryDate());
		            					orderMateriel.setLastDeliveryDate(DateUtil.addDay(o.getDeliveryDate(), -10));
		            					newMaterielList.add(orderMateriel);
		            					
		            					materielCount = materielCount + Double.parseDouble(o.getBuyCount());
		            				}
		            			}
		            		}
		        			orderMaterielService.betchInsertOrderMateriel(newMaterielList);//插入新的订单物料
		        			
		        			OrderInfo updateOrderMaterielCount = new OrderInfo();//修改销售订单的关联采购订单号
		        			updateOrderMaterielCount.setSerialNum(newSerialNum);
		        			updateOrderMaterielCount.setMaterielCount(materielCount.toString());
		            		orderService.updateStatus(updateOrderMaterielCount);
		        			
		        	    	
		        		}
		        		
		        		
		        		ProcurementPlan updateProcurementPlan = new ProcurementPlan();
		        		updateProcurementPlan.setSerialNum(procurementPlan.getSerialNum());
		        		updateProcurementPlan.setStatus("1");
		        		updateProcurementPlan.setBuyDate(new Date());
		        		procurementPlanService.update(updateProcurementPlan);
		        	}
			    	
		        }
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	
	    }
	
}
