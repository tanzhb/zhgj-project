package com.congmai.zhgj.web.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.congmai.zhgj.core.feature.orm.mybatis.Page;
import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.web.model.Company;
import com.congmai.zhgj.web.model.DemandPlan;
import com.congmai.zhgj.web.model.DemandPlanMateriel;
import com.congmai.zhgj.web.model.Materiel;
import com.congmai.zhgj.web.service.DemandPlanMaterielService;
import com.congmai.zhgj.web.service.DemandPlanService;

/**
 * 
 * @ClassName DemandPlanController
 * @Description TODO(需求计划控制器)
 * @author likt
 * @Date 2017年8月9日 下午2:55:36
 * @version 1.0.0
 */
@Controller
@RequestMapping("demandPlan")
public class DemandPlanController {
	
	@Autowired
	private DemandPlanService demandPlanService;
	
	@Autowired
	private DemandPlanMaterielService demandPlanMaterielService;
	
	
	@RequestMapping("demandPlanManage")
	public String demandPlanManage() {
		
		return "demandPlan/demandPlanManage";
	}
	
    /**
     * @Description (获取列表数据)
     * @param request
     * @return
     */
    @RequestMapping("demandPlanList")
    @ResponseBody
    public Page<DemandPlan> companyList(Map<String, Object> map,HttpServletRequest request,DemandPlan demandPlan) {
    	
    	Page<DemandPlan> demandPlans = demandPlanService.getListByCondition(demandPlan, 1, 10);
    	System.out.println("dddddddddddddd");
		return demandPlans;
    }
    
	  /**
     * @Description (需求计划新增页面)
     * @param request
     * @return
     */
    @RequestMapping("demandPlanAdd")
    public String demandPlanAdd(HttpServletRequest request) {
        return "demandPlan/demandPlanAdd";
    }

    
    /**
     * @Description (保存)
     * @param request
     * @return
     */
    @RequestMapping(value="saveDemandPlan",method=RequestMethod.POST)
    @ResponseBody
    public DemandPlan saveDemandPlan(Map<String, Object> map,@RequestBody DemandPlan demandPlan,HttpServletRequest request) {
    	String flag ="0"; //默认失败

        	try{
        		Subject currentUser = SecurityUtils.getSubject();
        		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
        		if(StringUtils.isEmpty(demandPlan.getSerialNum())){
        			demandPlan.setSerialNum(ApplicationUtils.random32UUID());
        			demandPlan.setCreateTime(new Date());
        			demandPlan.setCreator(currenLoginName);
        			demandPlan.setUpdateTime(new Date());
        			demandPlan.setUpdater(currenLoginName);
        			demandPlanService.insert(demandPlan);
        		}else{
        			demandPlan.setUpdateTime(new Date());
        			demandPlan.setUpdater(currenLoginName);
        			demandPlanService.update(demandPlan);
        		}
        		
        		flag = "1";
        	}catch(Exception e){
        		System.out.println(e.getMessage());
        		return null;
        	}
    	return demandPlan;
    }
    
    
    /**
     * @Description (查看需求计划)
     * @param request
     * @return
     */
    @RequestMapping(value="viewDemandPlan",method=RequestMethod.POST)
    @ResponseBody
    public DemandPlan viewDemandPlan(Map<String, Object> map,@RequestBody String serialNum,HttpServletRequest request) {
    	
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
    
    /**
     * @Description (查看需求计划)
     * @param request
     * @return
     */
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
    		}
    		map.put("demandPlan", demandPlan);
    		map.put("demandPlanMateriels", demandPlanMateriels);
    	}catch(Exception e){
    		System.out.println(e.getMessage());
    		return null;
    	}
    	return map;
    }

    /**
     * @Description (删除需求计划信息)
     * @param request
     * @return
     */
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
    
    /**
     * 
     * @Description (选择物料)
     * @param ids
     * @return
     */
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
    
    
    /**
     * @Description (保存续期计划物料信息)
     * @param request
     * @return
     */
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
        		
        		flag = "1";
        	}catch(Exception e){
        		System.out.println(e.getMessage());
        		return null;
        	}
    	return materiel;
    }
    
    /**
     * @Description (查看需求计划物料信息)
     * @param request
     * @return
     */
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

    /**
     * @Description (删除需求计划物料信息)
     * @param request
     * @return
     */
    @RequestMapping(value="deleteDemandPlanMateriel",method=RequestMethod.POST)
    @ResponseBody
    public String deleteDemandPlanMateriel(Map<String, Object> map,@RequestBody String serialNums,HttpServletRequest request) {
    	String flag = "0"; //默认失败
    	try{
    		if(StringUtils.isNotEmpty(serialNums)){
    			List<String> serialNumArray  = Arrays.asList(serialNums.split(","));
    			demandPlanMaterielService.deleteBatch(serialNumArray);
    		}
    	}catch(Exception e){
    		System.out.println(e.getMessage());
    		flag = "1";
    	}
    	return flag;
    }
    
    

}
