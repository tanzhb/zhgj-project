package com.congmai.zhgj.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
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

import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.core.util.ExcelReader;
import com.congmai.zhgj.core.util.ExcelUtil;
import com.congmai.zhgj.core.util.ExcelReader.RowHandler;
import com.congmai.zhgj.web.model.BOMMateriel;
import com.congmai.zhgj.web.model.BOMMaterielExample;
import com.congmai.zhgj.web.model.ClauseFramework;
import com.congmai.zhgj.web.model.ClauseAdvance;
import com.congmai.zhgj.web.model.ClauseAfterSales;
import com.congmai.zhgj.web.model.ClauseCheckAccept;
import com.congmai.zhgj.web.model.ClauseDelivery;
import com.congmai.zhgj.web.model.ClauseFrameworkExample;
import com.congmai.zhgj.web.model.ClauseSettlement;
import com.congmai.zhgj.web.model.ClauseSettlementDetail;
import com.congmai.zhgj.web.model.ContractVO;
import com.congmai.zhgj.web.model.MaterielExample;
import com.congmai.zhgj.web.model.OrderFile;
import com.congmai.zhgj.web.model.OrderFileExample;
import com.congmai.zhgj.web.model.OrderMateriel;
import com.congmai.zhgj.web.model.Materiel;
import com.congmai.zhgj.web.model.OrderInfo;
import com.congmai.zhgj.web.model.OrderInfoExample;
import com.congmai.zhgj.web.model.OrderMaterielExample;
import com.congmai.zhgj.web.model.OrderInfoExample.Criteria;
import com.congmai.zhgj.web.service.ClauseAdvanceService;
import com.congmai.zhgj.web.service.ClauseAfterSalesService;
import com.congmai.zhgj.web.service.ClauseCheckAcceptService;
import com.congmai.zhgj.web.service.ClauseDeliveryService;
import com.congmai.zhgj.web.service.ClauseFrameworkService;
import com.congmai.zhgj.web.service.ClauseSettlementDetailService;
import com.congmai.zhgj.web.service.ClauseSettlementService;
import com.congmai.zhgj.web.service.ContractService;
import com.congmai.zhgj.web.service.OrderFileService;
import com.congmai.zhgj.web.service.OrderMaterielService;
import com.congmai.zhgj.web.service.OrderService;

/**
 * 
 * @ClassName OrderController
 * @Description 物料信息业务处理
 * @author qfzhao
 * @Date 2017年7月28日 下午2:57:52
 * @version 1.0.0
 */
@Controller
@RequestMapping("/order")
public class OrderController {
	
    @Resource
    private OrderService orderService;
    @Resource
    private OrderMaterielService orderMaterielService;
    @Resource
    private ClauseAfterSalesService clauseAfterSalesService;
    @Resource
    private ClauseAdvanceService clauseAdvanceService;
    @Resource
    private ClauseCheckAcceptService clauseCheckAcceptService;
    @Resource
    private ClauseDeliveryService clauseDeliveryService;
    @Resource
    private ClauseSettlementService clauseSettlementService;
    @Resource
    private ClauseSettlementDetailService clauseSettlementDetailService;
    @Resource
    private OrderFileService orderFileService;
    @Resource
    private ClauseFrameworkService clauseFrameworkService;
    
	/**
	 * 合同管理service
	 */
	@Resource
	private ContractService contractService;
    
    /**
     * 保存销售订单
     */
    @RequestMapping(value = "/saveOrder", method = RequestMethod.POST)
    @ResponseBody
    public OrderInfo saveOrder(@RequestBody OrderInfo orderInfo) {
    	
    	if(orderInfo.getSerialNum()==null||orderInfo.getSerialNum().isEmpty()){//新增
    		orderInfo.setSerialNum(ApplicationUtils.random32UUID());
    		Subject currentUser = SecurityUtils.getSubject();
    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
    		orderInfo.setCreator(currenLoginName);
    		orderInfo.setUpdater(currenLoginName);
    		orderInfo.setCreateTime(new Date());
    		orderInfo.setUpdateTime(new Date());
    		orderInfo.setStatus("1");
    		
    		orderService.insert(orderInfo);
    	}else{//更新
    		Subject currentUser = SecurityUtils.getSubject();
    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
    		orderInfo.setUpdater(currenLoginName);
    		orderInfo.setUpdateTime(new Date());
    		orderService.update(orderInfo);
    	}
    	
		return orderInfo;
    }
    
    
    /**
     * 
     * @Description 查询订单列表
     * @param parent(若有值，则查询它及上级物料是它的物料)
     * @return
     */
    @RequestMapping("/findOrderList")
    @ResponseBody
    public ResponseEntity<Map> findOrderList() {
    	OrderInfoExample m =new OrderInfoExample();
    	List<OrderInfo> orderInfoList = new ArrayList<OrderInfo>();
    	//and 条件1
    	Criteria criteria =  m.createCriteria();
    	criteria.andDelFlgEqualTo("0");
    	//and 条件2,未发布可编辑的物料
    	Criteria criteria2 =  m.createCriteria();
    	criteria2.andStatusEqualTo("0");
    	criteria2.andDelFlgEqualTo("0");
    	//or 条件
    	m.or(criteria2);
    	//排序字段
    	m.setOrderByClause("updateTime DESC");
    	orderInfoList = orderService.selectList(m);
    	
    	//封装datatables数据返回到前台
		Map pageMap = new HashMap();
		pageMap.put("draw", 1);
		pageMap.put("recordsTotal", orderInfoList==null?0:orderInfoList.size());
		pageMap.put("recordsFiltered", orderInfoList==null?0:orderInfoList.size());
		pageMap.put("data", orderInfoList);
		return new ResponseEntity<Map>(pageMap, HttpStatus.OK);

    }

    /**
	 * 
	 * @Description 批量删除订单
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/deleteOrders", method = RequestMethod.POST)
	public ResponseEntity<Void> deleteOrders(@RequestBody String ids) {
		if ("".equals(ids) || ids == null) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		List<String> idList = ApplicationUtils.getIdList(ids);
		for(String id : idList){
			OrderInfo m = new OrderInfo();
			m.setSerialNum(id);
			m.setDelFlg("1");
			m.setUpdateTime(new Date());
			Subject currentUser = SecurityUtils.getSubject();
			String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
	    	m.setUpdater(currenLoginName);
			orderService.update(m);
		}

		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
	/**
	 * 
	 * @Description 获取销售订单信息
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/getOrderInfo")
	@ResponseBody
	public Map getOrderInfo(String serialNum,OrderInfo orderInfo) {
		orderInfo = orderService.selectById(serialNum);
		Map<String, Object> map = new HashMap<String, Object>();
    	map.put("orderInfo", orderInfo);
    	
    	OrderMaterielExample m =new OrderMaterielExample();
    	//and 条件1
    	com.congmai.zhgj.web.model.OrderMaterielExample.Criteria criteria =  m.createCriteria();
    	criteria.andDelFlgEqualTo("0");
    	criteria.andOrderSerialEqualTo(serialNum);
    	List<OrderMateriel> orderMateriel = orderMaterielService.selectList(m);
    	map.put("orderMateriel", orderMateriel);
    	
    	//获取合同信息
    	ContractVO contract = null;
		if(StringUtils.isNotEmpty(orderInfo.getContractSerial())){
    		contract=contractService.selectConbtractById(orderInfo.getContractSerial());
    	}
    	map.put("contract", contract);
    	if(contract!=null&&StringUtils.isNotEmpty(contract.getId())){
    		//获取合同条款信息
    		ClauseAfterSales clauseAfterSales = clauseAfterSalesService.selectByContractId(contract.getId());
    		map.put("clauseAfterSales", clauseAfterSales);
    		ClauseAdvance clauseAdvance = clauseAdvanceService.selectByContractId(contract.getId());
    		map.put("clauseAdvance", clauseAdvance);
    		ClauseCheckAccept clauseCheckAccept = clauseCheckAcceptService.selectByContractId(contract.getId());
    		map.put("clauseCheckAccept", clauseCheckAccept);
    		ClauseDelivery clauseDelivery = clauseDeliveryService.selectByContractId(contract.getId());
    		map.put("clauseDelivery", clauseDelivery);
    		ClauseSettlement clauseSettlement = clauseSettlementService.selectByContractId(contract.getId());
    		map.put("clauseSettlement", clauseSettlement);
    		
        	//查询框架合同
        	if("框架合同".equals(contract.getContractType())){//如果是框架合同
        		ClauseFrameworkExample mf =new ClauseFrameworkExample();
    	    	com.congmai.zhgj.web.model.ClauseFrameworkExample.Criteria criteriaf =  mf.createCriteria();
    	    	criteriaf.andContractSerialEqualTo(contract.getId());
    	    	criteriaf.andDelFlgEqualTo("0");
    	    	List<ClauseFramework> ClauseFramework = clauseFrameworkService.selectList(mf);
    	    	map.put("ClauseFramework", ClauseFramework);
        	}
    	}
    	
    	//获取订单附件
    	OrderFileExample om =new OrderFileExample();
    	com.congmai.zhgj.web.model.OrderFileExample.Criteria criteria2 =  om.createCriteria();
    	criteria2.andOrderSerialEqualTo(serialNum);
    	criteria2.andDelFlgEqualTo("0");
    	List<OrderFile> file = orderFileService.selectList(om);
    	map.put("file", file);
    	
    	
    	
    	return map;
	}
	
	
	/**
     * @Description (保存订单物料信息)
     * @param request
     * @return
     */
    @RequestMapping(value="saveOrderMateriel",method=RequestMethod.POST)
    @ResponseBody
    public OrderMateriel saveOrderMateriel(Map<String, Object> map,@RequestBody OrderMateriel orderMateriel,HttpServletRequest request) {
    	String flag ="0"; //默认失败
        	try{
        		Subject currentUser = SecurityUtils.getSubject();
        		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
        		if(StringUtils.isEmpty(orderMateriel.getSerialNum())){
        			orderMateriel.setSerialNum(ApplicationUtils.random32UUID());
        			orderMateriel.setCreateTime(new Date());
        			orderMateriel.setCreator(currenLoginName);
        			orderMateriel.setUpdateTime(new Date());
        			orderMateriel.setUpdater(currenLoginName);
        			orderMaterielService.insert(orderMateriel);
        		}else{
        			orderMateriel.setUpdateTime(new Date());
        			orderMateriel.setUpdater(currenLoginName);
        			orderMaterielService.update(orderMateriel);
        		}
        		flag = "1";
        	}catch(Exception e){
        		System.out.println(e.getMessage());
        		return null;
        	}
        	orderMateriel = orderMaterielService.selectById(orderMateriel.getSerialNum());
    	return orderMateriel;
    }
    
    /**
     * @Description (查看订单物料信息)
     * @param request
     * @return
     */
    @RequestMapping(value="viewOrderMateriel",method=RequestMethod.POST)
    @ResponseBody
    public OrderMateriel viewOrderMateriel(Map<String, Object> map,@RequestBody String serialNum,HttpServletRequest request) {
    	
    	OrderMateriel materiel = null;
        	try{
        		if(StringUtils.isNotEmpty(serialNum)){
        			materiel = orderMaterielService.selectById(serialNum);
        		}
        		
        	}catch(Exception e){
        		System.out.println(e.getMessage());
        		return null;
        	}
    	return materiel;
    }

    /**
     * @Description (删除订单物料信息)
     * @param request
     * @return
     */
    @RequestMapping(value="deleteOrderMateriel",method=RequestMethod.POST)
    @ResponseBody
    public String deleteOrderMateriel(Map<String, Object> map,@RequestBody String serialNums,HttpServletRequest request) {
    	String flag = "0"; //默认失败
    	try{
    		if(StringUtils.isNotEmpty(serialNums)){
    			orderMaterielService.deleteOrderMateriels(serialNums);
    		}
    		flag = "1";
    	}catch(Exception e){
    		System.out.println(e.getMessage());
    		
    	}
    	return flag;
    }
	
    /**
	 * 订单保存合同
	 * @param contract（合同对象）
	 * @param request（http 请求对象）
	 * @return 操作结果
	 */
	@RequestMapping(value = "/saveContract", method = RequestMethod.POST)
	@ResponseBody
	public ContractVO saveContract(@RequestBody ContractVO contract, HttpServletRequest request) {
		if(contract.getId()==null||contract.getId().isEmpty()){//新增
			contract.setId(ApplicationUtils.random32UUID());
    		Subject currentUser = SecurityUtils.getSubject();
    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
    		contract.setCreator(currenLoginName);
    		contract.setUpdater(currenLoginName);
    		contract.setCreateTime(new Date());
    		contract.setUpdateTime(new Date());

    		orderService.insertContract(contract);
    	}else{//更新
    		Subject currentUser = SecurityUtils.getSubject();
    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
    		contract.setUpdater(currenLoginName);
    		contract.setUpdateTime(new Date());
    		orderService.updateContract(contract);
    	}
		return contract;
	}
    
	
    /**
	 * 订单保存合同售后条款
	 * @param clauseAfterSales（售后条款）
	 * @param request（http 请求对象）
	 * @return 操作结果
	 */
	@RequestMapping(value = "/saveClauseAfterSales", method = RequestMethod.POST)
	@ResponseBody
	public ClauseAfterSales saveClauseAfterSales(@RequestBody ClauseAfterSales clauseAfterSales, HttpServletRequest request) {
		if(clauseAfterSales.getSerialNum()==null||clauseAfterSales.getSerialNum().isEmpty()){//新增
			clauseAfterSales.setSerialNum(ApplicationUtils.random32UUID());
    		Subject currentUser = SecurityUtils.getSubject();
    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
    		clauseAfterSales.setCreator(currenLoginName);
    		clauseAfterSales.setUpdater(currenLoginName);
    		clauseAfterSales.setCreateTime(new Date());
    		clauseAfterSales.setUpdateTime(new Date());

    		clauseAfterSalesService.insert(clauseAfterSales);
    	}else{//更新
    		Subject currentUser = SecurityUtils.getSubject();
    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
    		clauseAfterSales.setUpdater(currenLoginName);
    		clauseAfterSales.setUpdateTime(new Date());
    		clauseAfterSalesService.update(clauseAfterSales);
    	}
		return clauseAfterSales;
	}
	
	/**
	 * 订单保存合同垫资条款
	 * @param clauseAdvance（垫资条款）
	 * @param request（http 请求对象）
	 * @return 操作结果
	 */
	@RequestMapping(value = "/saveClauseAdvance", method = RequestMethod.POST)
	@ResponseBody
	public ClauseAdvance saveClauseAdvance(@RequestBody ClauseAdvance clauseAdvance, HttpServletRequest request) {
		if(clauseAdvance.getSerialNum()==null||clauseAdvance.getSerialNum().isEmpty()){//新增
			clauseAdvance.setSerialNum(ApplicationUtils.random32UUID());
    		Subject currentUser = SecurityUtils.getSubject();
    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
    		clauseAdvance.setCreator(currenLoginName);
    		clauseAdvance.setUpdater(currenLoginName);
    		clauseAdvance.setCreateTime(new Date());
    		clauseAdvance.setUpdateTime(new Date());

    		clauseAdvanceService.insert(clauseAdvance);
    	}else{//更新
    		Subject currentUser = SecurityUtils.getSubject();
    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
    		clauseAdvance.setUpdater(currenLoginName);
    		clauseAdvance.setUpdateTime(new Date());
    		clauseAdvanceService.update(clauseAdvance);
    	}
		return clauseAdvance;
	}
	
	/**
	 * 订单保存合同验收条款
	 * @param clauseCheckAccept（验收条款）
	 * @param request（http 请求对象）
	 * @return 操作结果
	 */
	@RequestMapping(value = "/saveClauseCheckAccept", method = RequestMethod.POST)
	@ResponseBody
	public ClauseCheckAccept saveClauseCheckAccept(@RequestBody ClauseCheckAccept clauseCheckAccept, HttpServletRequest request) {
		if(clauseCheckAccept.getSerialNum()==null||clauseCheckAccept.getSerialNum().isEmpty()){//新增
			clauseCheckAccept.setSerialNum(ApplicationUtils.random32UUID());
    		Subject currentUser = SecurityUtils.getSubject();
    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
    		clauseCheckAccept.setCreator(currenLoginName);
    		clauseCheckAccept.setUpdater(currenLoginName);
    		clauseCheckAccept.setCreateTime(new Date());
    		clauseCheckAccept.setUpdateTime(new Date());

    		clauseCheckAcceptService.insert(clauseCheckAccept);
    	}else{//更新
    		Subject currentUser = SecurityUtils.getSubject();
    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
    		clauseCheckAccept.setUpdater(currenLoginName);
    		clauseCheckAccept.setUpdateTime(new Date());
    		clauseCheckAcceptService.update(clauseCheckAccept);
    	}
		return clauseCheckAccept;
	}
	
	/**
	 * 订单保存合同交付条款
	 * @param clauseDelivery（交付条款）
	 * @param request（http 请求对象）
	 * @return 操作结果
	 */
	@RequestMapping(value = "/saveClauseDelivery", method = RequestMethod.POST)
	@ResponseBody
	public ClauseDelivery saveClauseDelivery(@RequestBody ClauseDelivery clauseDelivery, HttpServletRequest request) {
		if(clauseDelivery.getSerialNum()==null||clauseDelivery.getSerialNum().isEmpty()){//新增
			clauseDelivery.setSerialNum(ApplicationUtils.random32UUID());
    		Subject currentUser = SecurityUtils.getSubject();
    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
    		clauseDelivery.setCreator(currenLoginName);
    		clauseDelivery.setUpdater(currenLoginName);
    		clauseDelivery.setCreateTime(new Date());
    		clauseDelivery.setUpdateTime(new Date());

    		clauseDeliveryService.insert(clauseDelivery);
    	}else{//更新
    		Subject currentUser = SecurityUtils.getSubject();
    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
    		clauseDelivery.setUpdater(currenLoginName);
    		clauseDelivery.setUpdateTime(new Date());
    		clauseDeliveryService.update(clauseDelivery);
    	}
		return clauseDelivery;
	}
	
	
	/**
	 * 订单保存合同结算条款
	 * @param clauseSettlement（结算条款）
	 * @param request（http 请求对象）
	 * @return 操作结果
	 */
	@RequestMapping(value = "/saveClauseSettlement", method = RequestMethod.POST)
	@ResponseBody
	public ClauseSettlement saveClauseSettlement(@RequestBody ClauseSettlement clauseSettlement, HttpServletRequest request) {
		if(clauseSettlement.getSerialNum()==null||clauseSettlement.getSerialNum().isEmpty()){//新增
			clauseSettlement.setSerialNum(ApplicationUtils.random32UUID());
    		Subject currentUser = SecurityUtils.getSubject();
    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
    		clauseSettlement.setCreator(currenLoginName);
    		clauseSettlement.setUpdater(currenLoginName);
    		clauseSettlement.setCreateTime(new Date());
    		clauseSettlement.setUpdateTime(new Date());

    		clauseSettlementService.insert(clauseSettlement);
    	}else{//更新
    		Subject currentUser = SecurityUtils.getSubject();
    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
    		clauseSettlement.setUpdater(currenLoginName);
    		clauseSettlement.setUpdateTime(new Date());
    		clauseSettlementService.update(clauseSettlement);
    	}
		return clauseSettlement;
	}
	
	/**
	 * 订单保存合同结算条款明细
	 * @param params（结算条款）明细
	 * @param request（http 请求对象）
	 * @return 操作结果
	 */
	@RequestMapping(value = "/saveClauseSettlementDetail", method = RequestMethod.POST)
	@ResponseBody
	public List<ClauseSettlementDetail> saveClauseSettlementDetail(@RequestBody String params, HttpServletRequest request) {
    	params = params.replace("\\", "");
		ObjectMapper objectMapper = new ObjectMapper();  
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, ClauseSettlementDetail.class);  
        List<ClauseSettlementDetail> clauseSettlementDetail = null;
		try {
			clauseSettlementDetail = objectMapper.readValue(params, javaType);
			if(!CollectionUtils.isEmpty(clauseSettlementDetail)){
	    		Subject currentUser = SecurityUtils.getSubject();
	    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
		    	for(ClauseSettlementDetail f:clauseSettlementDetail){
		    		f.setSerialNum(ApplicationUtils.random32UUID());
		    		f.setCreator(currenLoginName);
	    			f.setUpdater(currenLoginName);
	    			f.setCreateTime(new Date());
	    			f.setUpdateTime(new Date());
		    	}
		    	//填充File******↑↑↑↑↑↑********
		    	clauseSettlementDetailService.betchInsertClauseSettlementDetails(clauseSettlementDetail);
		    	//数据插入******↑↑↑↑↑↑********
	        }

		} catch (Exception e) {
			e.printStackTrace();
		}
		return clauseSettlementDetail;
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
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, OrderFile.class);  
        List<OrderFile> file;
		try {
			file = objectMapper.readValue(params, javaType);
	    	if(!CollectionUtils.isEmpty(file)){
	    		Subject currentUser = SecurityUtils.getSubject();
	    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
		    	for(OrderFile f:file){
		    		f.setSerialNum(ApplicationUtils.random32UUID());
		    		f.setUploader(currenLoginName);
		    		f.setCreator(currenLoginName);
	    			f.setUpdater(currenLoginName);
	    			f.setUploadDate(new Date());
	    			f.setCreateTime(new Date());
	    			f.setUpdateTime(new Date());
		    	}
		    	//填充File******↑↑↑↑↑↑********
		    	orderFileService.betchInsertOrderFiles(file);
		    	//数据插入******↑↑↑↑↑↑********
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
    
    /**
     * 
     * @Description 保存框架条款
     * @param params
     * @return
     */
    @RequestMapping(value = "/saveClauseFramework", method = RequestMethod.POST)
    @ResponseBody
    public List<ClauseFramework> saveClauseFramework(@RequestBody String params) {
    	params = params.replace("\\", "");
		ObjectMapper objectMapper = new ObjectMapper();  
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, ClauseFramework.class);  
        List<ClauseFramework> clauseFramework;
		try {
			clauseFramework = objectMapper.readValue(params, javaType);
	    	if(!CollectionUtils.isEmpty(clauseFramework)){
	    		Subject currentUser = SecurityUtils.getSubject();
	    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
		    	for(ClauseFramework b:clauseFramework){
		    		b.setSerialNum(ApplicationUtils.random32UUID());
	    			b.setCreator(currenLoginName);
	    			b.setUpdater(currenLoginName);
	    			b.setCreateTime(new Date());
	    			b.setUpdateTime(new Date());
		    	}
		    	//填充框架条款M******↑↑↑↑↑↑********
		    	clauseFrameworkService.betchInsertBOM(clauseFramework);
		    	//数据插入******↑↑↑↑↑↑********
	        }
	    	
	    	//返回数据******↑↑↑↑↑↑********
	    	return clauseFramework;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    	
    }
    
    /**
     * @Description (导出订单信息)
     * @param request
     * @return
     */
    @RequestMapping("exportOrder")
    public void exportOrder(Map<String, Object> map,HttpServletRequest request,HttpServletResponse response) {
    		Map<String, Object> dataMap = new HashMap<String, Object>();
    		OrderInfoExample m =new OrderInfoExample();
        	List<OrderInfo> orderInfoList = new ArrayList<OrderInfo>();
        	//and 条件1
        	Criteria criteria =  m.createCriteria();
        	criteria.andDelFlgEqualTo("0");
        	//and 条件2,未发布可编辑的物料
        	Criteria criteria2 =  m.createCriteria();
        	criteria2.andStatusEqualTo("0");
        	criteria2.andDelFlgEqualTo("0");
        	//or 条件
        	m.or(criteria2);
        	//排序字段
        	m.setOrderByClause("updateTime DESC");
        	orderInfoList = orderService.selectList(m);
    		dataMap.put("orderInfoList",orderInfoList);
    		ExcelUtil.export(request, response, dataMap, "orderInfo", "订单信息");
    }
    
    /**
     * @Description (下载导入模板)
     * @param request
     * @return
     */
    @RequestMapping("downloadImportTemp")
    public void downloadCompanyTemp(Map<String, Object> map,HttpServletRequest request,HttpServletResponse response) {
    	ExcelUtil.importTempDownLoad(request, response, "orderInfo");
    }
    
    /**
     * @Description (订单信息导入)
     * @param request
     * @return
     */
    @RequestMapping("orderImport")
    @ResponseBody
    public Map<String,String> orderImport(@RequestParam(value = "excelFile") MultipartFile excelFile,HttpServletRequest request,HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String, String>();
    	 try {
			ExcelReader excelReader = new ExcelReader(excelFile.getInputStream());
			excelReader.readExcelContent(new RowHandler() {
				@Override
				public void handle(List<Object> row,int i) throws Exception {
					if(!CollectionUtils.isEmpty(row)){
						try{
							OrderInfo orderInfo = new OrderInfo();

							orderInfo.setOrderNum(row.get(0).toString());
							orderInfo.setOrderType(row.get(1).toString());
							orderInfo.setBuyComId(row.get(2).toString());
							orderInfo.setSeller(row.get(3).toString());
							orderInfo.setEntrustParty(row.get(4).toString());
							orderInfo.setServiceModel(row.get(5).toString());
							orderInfo.setSettlementClause(row.get(6).toString());
							orderInfo.setDeliveryMode(row.get(7).toString());
							orderInfo.setRate(row.get(8).toString());
							orderInfo.setCurrency(row.get(9).toString());
							orderInfo.setExchangeRate(row.get(10).toString());
							orderInfo.setDemandPlanSerial(row.get(11).toString());
							orderInfo.setSaleApplySerial(row.get(12).toString());
							orderInfo.setOrderSerial(row.get(13).toString());
							orderInfo.setMaker(row.get(14).toString());
							orderInfo.setOrderDate(StringUtils.isEmpty(row.get(15).toString())?null:(Date) row.get(15));

							orderInfo.setSerialNum(ApplicationUtils.random32UUID());
				    		Subject currentUser = SecurityUtils.getSubject();
				    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
				    		orderInfo.setCreator(currenLoginName);
				    		orderInfo.setUpdater(currenLoginName);
				    		orderInfo.setCreateTime(new Date());
				    		orderInfo.setUpdateTime(new Date());
				    		orderInfo.setStatus("1");
				    		
				    		orderService.insert(orderInfo);
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
}