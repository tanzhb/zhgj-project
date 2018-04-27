package com.congmai.zhgj.web.controller;

import java.math.BigDecimal;
import java.net.URLDecoder;
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

import org.activiti.engine.ActivitiException;
import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.core.util.BeanUtils;
import com.congmai.zhgj.core.util.DateUtil;
import com.congmai.zhgj.core.util.ExcelReader;
import com.congmai.zhgj.core.util.MessageConstants;
import com.congmai.zhgj.core.util.SendEmail;
import com.congmai.zhgj.core.util.StringUtil;
import com.congmai.zhgj.core.util.ExcelReader.RowHandler;
import com.congmai.zhgj.core.util.ExcelUtil;
import com.congmai.zhgj.core.util.UserUtil;
import com.congmai.zhgj.log.annotation.OperationLog;
import com.congmai.zhgj.web.enums.ComType;
import com.congmai.zhgj.web.enums.StaticConst;
import com.congmai.zhgj.web.event.EventExample;
import com.congmai.zhgj.web.event.SendMessageEvent;
import com.congmai.zhgj.web.model.BOMMateriel;
import com.congmai.zhgj.web.model.BOMMaterielExample;
import com.congmai.zhgj.web.model.BaseVO;
import com.congmai.zhgj.web.model.Category;
import com.congmai.zhgj.web.model.ClauseAdvance;
import com.congmai.zhgj.web.model.ClauseAfterSales;
import com.congmai.zhgj.web.model.ClauseCheckAccept;
import com.congmai.zhgj.web.model.ClauseDelivery;
import com.congmai.zhgj.web.model.ClauseSettlement;
import com.congmai.zhgj.web.model.ClauseSettlementDetail;
import com.congmai.zhgj.web.model.CommentVO;
import com.congmai.zhgj.web.model.Company;
import com.congmai.zhgj.web.model.CompanyContact;
import com.congmai.zhgj.web.model.ContractVO;
import com.congmai.zhgj.web.model.DataTablesParams;
import com.congmai.zhgj.web.model.DeliveryMaterielVO;
import com.congmai.zhgj.web.model.DeliveryVO;
import com.congmai.zhgj.web.model.DemandMateriel;
import com.congmai.zhgj.web.model.DemandMaterielExample;
import com.congmai.zhgj.web.model.HistoricTaskVO;
import com.congmai.zhgj.web.model.Materiel;
import com.congmai.zhgj.web.model.MaterielSelectExample;
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
import com.congmai.zhgj.web.service.ProcessBaseService;
import com.congmai.zhgj.web.service.ProcurementPlanMaterielService;
import com.congmai.zhgj.web.service.ProcurementPlanService;
import com.congmai.zhgj.web.service.BOMMaterielService;
import com.congmai.zhgj.web.service.UserCompanyService;


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
    @Resource
    private ProcessBaseService processBaseService;
    @Autowired
	protected RuntimeService runtimeService;
	@Autowired
    protected TaskService taskService;
	@Autowired
	private IProcessService processService;
	@Autowired
	private UserCompanyService userCompanyService;
    

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
		procurementPlanInfo.setIsFromForcast("0");//不是来自采购预测
		
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
	    			f.setStatus("0");
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
     * @Description (更新需求物料信息)
     * @param request
     * @return
     */
    @RequestMapping(value="updateDemandMateriels",method=RequestMethod.POST)
    @ResponseBody
    public String updateDemandMateriels(Map<String, Object> map,@RequestBody String serialNums,HttpServletRequest request) {
    	String flag = "0"; //默认失败
    	try{
    		if(StringUtils.isNotEmpty(serialNums)){
    			procurementPlanMaterielService.updateDemandMateriels(serialNums);
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
    	DemandMaterielExample dme=new DemandMaterielExample();
    	com.congmai.zhgj.web.model.DemandMaterielExample.Criteria cd=dme.createCriteria();
    	cd.andDelFlgEqualTo("0");
    	cd.andProcurementPlanSerialEqualTo(serialNum);
    	List<DemandMateriel> demandMateriels=procurementPlanMaterielService.selecDemandtList(dme);
    	for(DemandMateriel demandMateriel:demandMateriels){
    		DemandMateriel demandMateriel1=new DemandMateriel();
    		demandMateriel1.setSerialNum(demandMateriel.getSerialNum());
    		demandMateriel1.setStatus("1");
    		procurementPlanMaterielService.updateDemandMateriel(demandMateriel1);
    		
    	}
    	
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
    			//更新采购计划中需求物料和采购清单物料状态
    			ProcurementPlanMateriel procurementPlanMateriel1=new ProcurementPlanMateriel();
    			procurementPlanMateriel1.setStatus("1");
    			procurementPlanMateriel1.setSerialNum(o.getSerialNum());
    			procurementPlanMaterielService.updateProcurementPlanMateriel(procurementPlanMateriel1);
    			
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
    		updateProcurementPlan.setStatus("3");//已完成
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
	//发布采购清单物料
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
		        			ProcurementPlanMateriel   procurementPlanMateriel1=new ProcurementPlanMateriel();
			    			procurementPlanMateriel1.setStatus("1");//已发布
			    			procurementPlanMateriel1.setUpdateTime(new Date());
			    			procurementPlanMateriel1.setSerialNum(o.getSerialNum());
			    			procurementPlanMaterielService.updateProcurementPlanMateriel(procurementPlanMateriel1);
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
	 
	    @RequestMapping("/findProcurementPlanMateriel")
	    @ResponseBody
	    public ResponseEntity<Map> findProcurementPlanMateriel(HttpServletRequest request) {
	    	Map pageMap = new HashMap();
	    	String comId = null;
	    	Company company = null;
	    	User user = UserUtil.getUserFromSession();
	    	if(user!=null){
				comId = userCompanyService.getUserComId(String.valueOf(user.getUserId()));//获取供应商comId
			}
	    	ProcurementPlanMaterielExample ppe =new ProcurementPlanMaterielExample();
	    	com.congmai.zhgj.web.model.ProcurementPlanMaterielExample.Criteria  c=ppe.createCriteria();
	    	c.andDelFlgEqualTo("0").andSupplyComIdEqualTo(comId);
	    	List<ProcurementPlanMateriel>list=procurementPlanMaterielService.selectSaleForestList(comId);
			   	 pageMap.put("draw", 1);
				 pageMap.put("recordsTotal", list==null?0:list.size());
				 pageMap.put("recordsFiltered", list==null?0:list.size());
				 pageMap.put("data", list);
			
			return new ResponseEntity<Map>(pageMap, HttpStatus.OK);

	    }
	  /**
	     * 
	     * @Description (启动采购计划流程)
	     * @param params
	     * @return
	     */
	    @RequestMapping(value = "/startProcurementPlanProcess", method = RequestMethod.POST)
	    @ResponseBody
	    public String startProcurementPlanProcess(@RequestBody String params) {
	    	String flag = "0"; //默认失败
	    	ProcurementPlan procurementPlan = json2ProcurementPlan(params);
			//启动订单审批测试流程-start
			User user = UserUtil.getUserFromSession();
			procurementPlan.setUserId(user.getUserId());
			procurementPlan.setUser_name(user.getUserName());
			procurementPlan.setTitle(user.getUserName()+" 的采购计划申请");
			procurementPlan.setStatus(BaseVO.PENDING);					//审批中
			procurementPlan.setApplyDate(new Date());
			procurementPlan.setBusinessType(BaseVO.BUYAPPLY);
			procurementPlan.setReason(procurementPlan.getRemark());
	    	processBaseService.insert(procurementPlan);
			String businessKey = procurementPlan.getSerialNum().toString();
			procurementPlan.setBusinessKey(businessKey);
			
			try {
				String processInstanceId = this.processService.startProcurementPlanInfo(procurementPlan);
				
				//申请加入流程已办
				HistoricTaskVO historicTaskVO = new HistoricTaskVO();
				historicTaskVO.setTaskId(ApplicationUtils.random32UUID());
				historicTaskVO.setProcessInstanceId(processInstanceId);
				historicTaskVO.setStartTime(new Date());
				historicTaskVO.setEndTime(new Date());
				historicTaskVO.setProcessDefId(procurementPlan.getBusinessKey());
				historicTaskVO.setUserId(user.getUserId().toString());
				
				processBaseService.insertHistoricTask(historicTaskVO);
//	                message.setStatus(Boolean.TRUE);
//	    			message.setMessage("订单流程已启动，流程ID：" + processInstanceId);
			    logger.info("processInstanceId: "+processInstanceId);
			    EventExample.getEventPublisher().publicSendMessageEvent(new SendMessageEvent(procurementPlan,MessageConstants.APPLY_BUY_APPLY));
			    flag = "1";
			} catch (ActivitiException e) {
//	            	message.setStatus(Boolean.FALSE);
			    if (e.getMessage().indexOf("no processes deployed with key") != -1) {
			        logger.warn("没有部署流程!", e);
//	        			message.setMessage("没有部署流程，请联系系统管理员，在[流程定义]中部署相应流程文件！");
			    } else {
			        logger.error("启动订单流程失败：", e);
//	                    message.setMessage("启动订单流程失败，系统内部错误！");
			    }
			    throw e;
			} catch (Exception e) {
			    logger.error("启动订单流程失败：", e);
//	                message.setStatus(Boolean.FALSE);
//	                message.setMessage("启动订单流程失败，系统内部错误！");
			    throw e;
			}
//	        启动订单审批测试流程-end
			return flag;
		}
	    
	    
	    /**
	     * 审批订单流程
	     * @param taskId
	     * @param model
	     * @return
	     * @throws NumberFormatException
	     * @throws Exception
	     */
//		@RequiresPermissions("user:order:toApproval") 	//*代表 经理、总监、人力
	    @RequestMapping(value = "/toApproval/{taskId}", method = RequestMethod.POST, produces = "application/json")
	    public @ResponseBody Map<String, Object> toApproval(@PathVariable("taskId") String taskId) throws NumberFormatException, Exception{
	    	Task task = this.taskService.createTaskQuery().taskId(taskId).singleResult();
			// 根据任务查询流程实例
	    	ProcurementPlan procurementPlan = new ProcurementPlan();
	    	List<CommentVO> commentList = new ArrayList<CommentVO>();
	    	String result = null;
	    	if(task!=null){
	    		String processInstanceId = task.getProcessInstanceId();
	    		ProcessInstance pi = this.runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
	    		procurementPlan = (ProcurementPlan) this.runtimeService.getVariable(pi.getId(), "entity");
	    		procurementPlan.setTask(task);
	    		procurementPlan.setProcessInstanceId(processInstanceId);
	    		commentList = this.processService.getComments(processInstanceId);
	    		String taskDefinitionKey = task.getTaskDefinitionKey();
	    		logger.info("taskDefinitionKey: "+taskDefinitionKey);
	    		
	    		if("modifyApply".equals(taskDefinitionKey)){
	    			result = "modify";
	    		}else{
	    			result = "audit";
	    		}
	    	}
	    	
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("actionType", result);
			map.put("procurementPlan", procurementPlan);
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
//		@RequiresPermissions("user:order:complate")  //数据库中权限字符串为user:*:complate， 通配符*匹配到order所以有权限操作 
	    @RequestMapping(value = "/complate/{taskId}", method = RequestMethod.POST, produces = "application/text;charset=UTF-8")
		@ResponseBody
	    public String complate(
	    		@RequestParam("procurementPlanId") String procurementPlanId,
	    		@RequestParam("content") String content,
	    		@RequestParam("processInstanceId") String processInstanceId,
	    		@RequestParam("completeFlag") Boolean completeFlag,
	    		@PathVariable("taskId") String taskId, 
	    		RedirectAttributes redirectAttributes) throws Exception{
	    	User user = UserUtil.getUserFromSession();
	    	String result = "";
	    	try {
	    		ProcurementPlan procurementPlan = this.procurementPlanService.selectById(procurementPlanId);
	    		ProcurementPlan baseProcurementPlanInfo = (ProcurementPlan) this.runtimeService.getVariable(processInstanceId, "entity");
	    		Map<String, Object> variables = new HashMap<String, Object>();
	    		variables.put("isPass", completeFlag);
	    		if(!completeFlag){
	    			baseProcurementPlanInfo.setTitle(baseProcurementPlanInfo.getUser_name()+" 的采购计划申请失败,需修改后重新提交！");
	    			procurementPlan.setStatus(BaseVO.APPROVAL_FAILED);
	    			variables.put("entity", baseProcurementPlanInfo);
	    		}else{
	    			procurementPlan.setStatus(BaseVO.PENDING);					//审批中
	    		}
	    		// 完成任务，返回当前节点定义
	    		String taskDefinitionKey = this.processService.complete(taskId, content, user.getUserId().toString(), variables);
	    		
	    		ProcessInstance pi = null;
	    		if(completeFlag){
	    			//此处需要修改，不能根据人来判断审批是否结束。应该根据流程实例id(processInstanceId)来判定。
	    			//判断指定ID的实例是否存在，如果结果为空，则代表流程结束，实例已被删除(移到历史库中)
	    			pi = this.runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
	    			if(BeanUtils.isBlank(pi)){
	    				procurementPlan.setStatus(BaseVO.APPROVAL_SUCCESS);
	    			}
	    		}
	    		
	    		this.processBaseService.update(procurementPlan);
	    		
	    		if(BaseVO.APPROVAL_SUCCESS.equals(procurementPlan.getStatus())){//审批完成，需更新状态为2(采购计划待发布采购)
	    			ProcurementPlan oi = new ProcurementPlan();
	    			oi.setSerialNum(procurementPlan.getSerialNum());
	    			oi.setStatus("2");
	    			oi.setUpdateTime(new Date());
	    			this.procurementPlanService.updateProcurementPlan(oi);
	    		}
	    		procurementPlan.setProcessInstanceId(processInstanceId);
	    		//发送消息
	    		if(completeFlag){//发送消息给采购计划制单人
				
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
		 * 调整订单申请
		 * @param vacation
		 * @param taskId
		 * @param processInstanceId
		 * @param reApply
		 * @param session
		 * @return
		 * @throws Exception
		 */
//		@RequiresPermissions("user:vacation:modify")
		@RequestMapping(value = "/modifyProcurementPlan/{taskId}", method = RequestMethod.POST, produces = "application/text;charset=UTF-8")
		@ResponseBody
		public String modifyProcurementPlan(
				@PathVariable("taskId") String taskId,
				@RequestParam("processInstanceId") String processInstanceId,
				@RequestParam("reApply") Boolean reApply,
				@RequestParam("procurementPlanId") String procurementPlanId,
				@RequestParam("reason") String reason,
				@RequestParam("orderType") String orderType) throws Exception{
			String result = "";
			User user = UserUtil.getUserFromSession();
			ProcurementPlan procurementPlan = new ProcurementPlan();
			procurementPlan.setSerialNum(procurementPlanId);
	        Map<String, Object> variables = new HashMap<String, Object>();
	        procurementPlan.setUserId(user.getUserId());
	        procurementPlan.setUser_name(user.getUserName());
	        procurementPlan.setApplyDate(new Date());
	        procurementPlan.setBusinessKey(procurementPlanId);
	        procurementPlan.setProcessInstanceId(processInstanceId);
	        String content = "";
	        if(reApply){
	        	//修改订单申请
	        	procurementPlan.setTitle(user.getUserName()+" 的采购计划申请！");
	        	procurementPlan.setStatus(BaseVO.PENDING);
		        content = "重新申请";
		        result = "任务办理完成，采购计划申请已重新提交！";
		        
		      
	        }else{
	        	procurementPlan.setTitle(user.getUserName()+" 的采购计划申请已取消！");
	        	procurementPlan.setStatus(BaseVO.APPROVAL_FAILED);
	        	content = "取消申请";
	        	result = "任务办理完成，已经取消您的采购计划申请！";
	        }
	        try {
	    		this.processBaseService.update(procurementPlan);
				variables.put("entity", procurementPlan);
				variables.put("reApply", reApply);
				this.processService.complete(taskId, content, user.getUserId().toString(), variables);
				
				//发送消息
				if(reApply){
				        //申请消息
					procurementPlan = procurementPlanService.selectById(procurementPlan.getSerialNum());
					   EventExample.getEventPublisher().publicSendMessageEvent(new SendMessageEvent(procurementPlan,MessageConstants.APPLY_BUY_APPLY));
				}else{
					cancelApply(procurementPlan.getSerialNum(),taskId);
				}
			} catch (ActivitiObjectNotFoundException e) {
//				message.setStatus(Boolean.FALSE);
//				message.setMessage("此任务不存在，请联系管理员！");
				result = "此任务不存在，请联系管理员！";
				throw e;
			} catch (ActivitiException e) {
//				message.setStatus(Boolean.FALSE);
//				message.setMessage("此任务正在协办，您不能办理此任务！");
				result = "此任务正在协办，您不能办理此任务！";
				throw e;
			} catch (Exception e) {
//				message.setStatus(Boolean.FALSE);
//				message.setMessage("任务办理失败，请联系管理员！");
				result = "任务办理失败，请联系管理员！";
				throw e;
			}
			
	    	return result;
	    }
		 /**
	     * 用户取消采购计划申请
	     */
	    @RequestMapping(value = "/userCancelProcurementPlanApply/{taskId}/{processInstanceId}", method = RequestMethod.POST, produces = "application/text;charset=UTF-8")
		@ResponseBody
	    public void userCancelProcurementPlanApply(@PathVariable("taskId") String taskId, @PathVariable("processInstanceId") String processInstanceId) {
	    	ProcessInstance pi = this.runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
	    	ProcurementPlan procurementPlan = (ProcurementPlan) this.runtimeService.getVariable(pi.getId(), "entity");
			cancelApply(procurementPlan.getSerialNum(),taskId);
	    }
		/**
		 * 
		 * @Description (取消申请)
		 * @param serialNum
		 */
		private void cancelApply(String serialNum,String taskId) {
			this.processBaseService.delete(serialNum);//取消申请删除审批记录，才开重新审批
			//更新已办tab里面的deleteReason 更新为'取消申请'
			HistoricTaskVO historicTaskVO = new HistoricTaskVO();
			historicTaskVO.setTaskId(taskId);
			historicTaskVO.setDeleteReason(StaticConst.getInfo("quxiaoApply"));//'取消申请'		
			processBaseService.updateHistoricTask(historicTaskVO);
			ProcurementPlan m = new ProcurementPlan();
			m.setStatus("0");//取消申请采购计划回到审批前状态
			m.setSerialNum(serialNum);
			m.setUpdateTime(new Date());
			Subject currentUser = SecurityUtils.getSubject();
			String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
			m.setUpdater(currenLoginName);
			procurementPlanService.updateProcurementPlan(m);
			
		}
		
}
