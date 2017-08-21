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

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.core.util.DateUtil;
import com.congmai.zhgj.core.util.ExcelUtil;
import com.congmai.zhgj.web.model.BOMMateriel;
import com.congmai.zhgj.web.model.BOMMaterielExample;
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
import com.congmai.zhgj.web.model.OrderInfoExample.Criteria;
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
	
	
	
	
    
}