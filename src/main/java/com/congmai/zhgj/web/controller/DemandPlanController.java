package com.congmai.zhgj.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.congmai.zhgj.core.feature.orm.mybatis.Page;
import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.web.model.Company;
import com.congmai.zhgj.web.model.DemandPlan;
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
    	
    	Page<DemandPlan> demandPlans = demandPlanService.getListByCondition(demandPlan, 0, 10);

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
    

}
