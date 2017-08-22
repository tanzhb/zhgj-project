package com.congmai.zhgj.web.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.core.util.DateUtil;
import com.congmai.zhgj.core.util.ExcelUtil;
import com.congmai.zhgj.web.model.BOMMateriel;
import com.congmai.zhgj.web.model.BOMMaterielExample;
import com.congmai.zhgj.web.model.ClauseAdvance;
import com.congmai.zhgj.web.model.ClauseAfterSales;
import com.congmai.zhgj.web.model.ClauseCheckAccept;
import com.congmai.zhgj.web.model.ClauseDelivery;
import com.congmai.zhgj.web.model.ContractVO;
import com.congmai.zhgj.web.model.OrderMateriel;
import com.congmai.zhgj.web.model.Materiel;
import com.congmai.zhgj.web.model.MaterielFile;
import com.congmai.zhgj.web.model.MaterielFileExample;
import com.congmai.zhgj.web.model.OrderInfo;
import com.congmai.zhgj.web.model.OrderInfoExample;
import com.congmai.zhgj.web.model.OrderMateriel;
import com.congmai.zhgj.web.model.OrderMaterielExample;
import com.congmai.zhgj.web.model.SupplyMateriel;
import com.congmai.zhgj.web.model.SupplyMaterielExample;
import com.congmai.zhgj.web.model.User;
import com.congmai.zhgj.web.model.OrderInfoExample.Criteria;
import com.congmai.zhgj.web.service.ClauseAdvanceService;
import com.congmai.zhgj.web.service.ClauseAfterSalesService;
import com.congmai.zhgj.web.service.ClauseCheckAcceptService;
import com.congmai.zhgj.web.service.ClauseDeliveryService;
import com.congmai.zhgj.web.service.ContractService;
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
    
	/**
	 * 合同管理service
	 */
	@Resource
	private ContractService contractService;
    
    /**
     * 保存销售订单
     */
    @RequestMapping(value = "/saveSaleOrder", method = RequestMethod.POST)
    @ResponseBody
    public OrderInfo saveSaleOrder(@RequestBody OrderInfo orderInfo) {
    	
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
    @RequestMapping("/findSaleOrderList")
    @ResponseBody
    public ResponseEntity<Map> findSaleOrderList() {
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
     * @Description (下载导入模板)
     * @param request
     * @return
     */
    @RequestMapping("downloadImportTemp")
    public void downloadCompanyTemp(Map<String, Object> map,HttpServletRequest request,HttpServletResponse response) {
    	ExcelUtil.importTempDownLoad(request, response, "orderInfo");
    }
    
    /**
	 * 
	 * @Description 批量删除订单
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/deleteSaleOrders", method = RequestMethod.POST)
	public ResponseEntity<Void> deleteSaleOrders(@RequestBody String ids) {
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
    	List<OrderMateriel> orderMateriel = orderMaterielService.selectList(m);
    	map.put("orderMateriel", orderMateriel);
    	
    	//获取合同信息
    	ContractVO contract = null;
		if(StringUtils.isNotEmpty(orderInfo.getContractSerial())){
    		contract=contractService.selectConbtractById(orderInfo.getContractSerial());
    	}
    	map.put("contract", contract);
    	if(contract!=null&&StringUtils.isNotEmpty(contract.getId())){
    		ClauseAfterSales clauseAfterSales = clauseAfterSalesService.selectByContractId(contract.getId());
    		map.put("clauseAfterSales", clauseAfterSales);
    		ClauseAdvance clauseAdvance = clauseAdvanceService.selectByContractId(contract.getId());
    		map.put("clauseAdvance", clauseAdvance);
    		ClauseCheckAccept clauseCheckAccept = clauseCheckAcceptService.selectByContractId(contract.getId());
    		map.put("clauseCheckAccept", clauseCheckAccept);
    		ClauseDelivery clauseDelivery = clauseDeliveryService.selectByContractId(contract.getId());
    		map.put("clauseDelivery", clauseDelivery);
    	}
    	
    	
    	
    	//获取合同条款信息
    	
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
    
}