package com.congmai.zhgj.web.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.congmai.zhgj.core.feature.orm.mybatis.Page;
import com.congmai.zhgj.web.model.Company;
import com.congmai.zhgj.web.model.CompanyContact;
import com.congmai.zhgj.web.model.CompanyFinance;
import com.congmai.zhgj.web.model.CompanyQualification;
import com.congmai.zhgj.web.service.CompanyContactService;
import com.congmai.zhgj.web.service.CompanyFinanceService;
import com.congmai.zhgj.web.service.CompanyQualificationService;
import com.congmai.zhgj.web.service.CompanyService;

/**
 * 
 * @ClassName CompanyController
 * @Description TODO(企业信息控制器)
 * @author likt
 * @Date 2017年7月31日 下午1:22:08
 * @version 1.0.0
 */
@Controller
@RequestMapping("company")
public class CompanyController {
	
	@Autowired
	private CompanyService companyService;
	@Autowired
	private CompanyQualificationService companyQualificationService;
	@Autowired
	private CompanyContactService companyContactService;
	@Autowired
	private CompanyFinanceService companyFinanceService;
	
	  /**
     * @Description (企业信息首页)
     * @param request
     * @return
     */
    @RequestMapping("companyManage")
    public String companyIndex(Map<String, Object> map,HttpServletRequest request,Company company) {
    	//company.setPageIndex(0);
    	//company.setPageSize(10);
    	//map.put("list", companyService.selectByPage(company));
        return "company/companyManage";
    }
    
    /**
     * @Description (获取列表数据)
     * @param request
     * @return
     */
    @RequestMapping("companyList")
    @ResponseBody
    public Page<Company> companyList(Map<String, Object> map,HttpServletRequest request,Company company) {
    	company.setFirstResult((company.getPageIndex()-1)*company.getPageSize());
    	company.setPageSize(company.getPageSize());
    	return companyService.selectByPage(company);
    }
    
    
    /**
     * @Description (获取企业全部信息)
     * @param request
     * @return
     */
    @RequestMapping("getCompanyInfo")
    @ResponseBody
    public Map<String,Object> getCompanyInfo(HttpServletRequest request,String comId) {
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("company", companyService.selectById(comId));
    	map.put("companyFinances", companyFinanceService.selectListByComId(comId));
     	map.put("companyQualifications", companyQualificationService.selectListByComId(comId));
    	map.put("companyContacts", companyContactService.selectListByComId(comId));
    	return map;
    }
    
    
	  /**
     * @Description (企业信息首页)
     * @param request
     * @return
     */
    @RequestMapping("companyAdd")
    public String companyAdd(HttpServletRequest request,String comId,Map<String,Object> map) {
    	/*if(company !=null){
    		map.put("company", company);
    	}*/
        return "company/companyAdd";
    }

    /**
     * @Description (查看)
     * @param request
     * @return
     */
    @RequestMapping("companyView")
    public String companyView(HttpServletRequest request,String comId,Map<String,Object> map) {
    	//map.put("company", companyService.selectOne(serialNum));
    	map.put("company", companyService.selectById(comId));
    	map.put("companyFinances", companyFinanceService.selectListByComId(comId));
     	map.put("companyQualifications", companyQualificationService.selectListByComId(comId));
    	map.put("companyContacts", companyContactService.selectListByComId(comId));
    	return "company/companyView";
    }
    
    /**
     * @Description (保存)
     * @param request
     * @return
     */
    @RequestMapping("saveCompany")
    @ResponseBody
    public Company saveCompany(Map<String, Object> map,Company company) {
    	String flag ="0"; //默认失败
    	try{
    		if(StringUtils.isEmpty(company.getComId())){
    			company.setComId(UUID.randomUUID().toString().replace("-",""));
    			companyService.insert(company);
    		}else{
    			companyService.update(company);
    		}
    		
    		flag = "1";
    	}catch(Exception e){
    		System.out.println(e.getMessage());
    	}
    	return company;
    }
    
    /**
     * @Description (删除)
     * @param request
     * @return
     */
    @RequestMapping("deleteCompany")
    @ResponseBody
    public String deleteCompany(Map<String, Object> map,String comId) {
    	String flag ="0"; //默认失败
    	try{
    		companyService.delete(comId);
    		flag = "1";
    	}catch(Exception e){
    		System.out.println(e.getMessage());
    	}
    	return flag;
    }
    
    /**
     * @Description (删除)
     * @param request
     * @return
     */
    @RequestMapping("deleteCompanyBatch")
    @ResponseBody
    public String deleteCompanyBatch(Map<String, Object> map,String comIds) {
    	String flag ="0"; //默认失败
    	String[] comIdArray = null;
    	List<String> comIdList = null;
    	if(!StringUtils.isEmpty(comIds)){
    		comIdArray = comIds.split(",");
    		comIdList = Arrays.asList(comIdArray);
    	}
    	try{
    		companyService.deleteBatch(comIdList);
    		flag = "1";
    	}catch(Exception e){
    		System.out.println(e.getMessage());
    	}
    	return flag;
    }
    
    
    
    /**
     * @Description (保存企业资质信息)
     * @param request
     * @return
     */
    @RequestMapping("saveCompanyQualification")
    @ResponseBody
    public List<CompanyQualification> saveCompanyQualification(Map<String, Object> map,@RequestBody String params) {
    	//List<CompanyQualification> companyQualificationArrays =Arrays.asList(companyQualifications);
    	String flag ="0"; //默认失败
    	List<CompanyQualification> companyQualifications = null;
    	try{
    		params = params.replace("\\", "");
    		ObjectMapper objectMapper = new ObjectMapper();  
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, CompanyQualification.class);  
            companyQualifications = objectMapper.readValue(params, javaType); 
    		companyQualificationService.insertOrUpdateBatch(companyQualifications);
    		flag = "1";
    	}catch(Exception e){
    		System.out.println(e.getMessage());
    	}
    	return companyQualifications;
    }
    
    /**
     * @Description (保存联系人)
     * @param request
     * @return
     */
    @RequestMapping("saveCompanyContact")
    @ResponseBody
    public List<CompanyContact> saveCompanyContact(Map<String, Object> map,CompanyContact companyContact) {
    	String flag ="0"; //默认失败
    	List<CompanyContact> companyContacts = null;
    	try{
    		if(StringUtils.isEmpty(companyContact.getSerialNum())){
    			companyContact.setSerialNum(UUID.randomUUID().toString().replace("-",""));
    			companyContactService.insert(companyContact);
    		}else{
    			companyContactService.update(companyContact);
    		}
    		
    		companyContacts = companyContactService.selectListByComId(companyContact.getComId());
    		
    		flag = "1";
    	}catch(Exception e){
    		System.out.println(e.getMessage());
    	}
    	return companyContacts;
    }

    /**
     * @Description (保存财务信息)
     * @param request
     * @return
     */
    @RequestMapping("saveCompanyFinance")
    @ResponseBody
    public List<CompanyFinance> saveCompanyFinance(Map<String, Object> map,CompanyFinance companyFinance) {
    	String flag ="0"; //默认失败
    	List<CompanyFinance> companyFinances = null;
    	try{
    		if(StringUtils.isEmpty(companyFinance.getSerialNum())){
    			companyFinance.setSerialNum(UUID.randomUUID().toString().replace("-",""));
    			companyFinanceService.insert(companyFinance);
    		}else{
    			companyFinanceService.update(companyFinance);
    		}
    		
    		companyFinances = companyFinanceService.selectListByComId(companyFinance.getComId());
    		
    		flag = "1";
    	}catch(Exception e){
    		System.out.println(e.getMessage());
    	}
    	return companyFinances;
    }
    
    

	
	

}
