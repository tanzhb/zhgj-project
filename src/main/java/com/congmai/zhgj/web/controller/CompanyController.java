package com.congmai.zhgj.web.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.congmai.zhgj.core.feature.orm.mybatis.Page;
import com.congmai.zhgj.web.model.Company;
import com.congmai.zhgj.web.model.CompanyQualification;
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
     * @Description (企业信息首页)
     * @param request
     * @return
     */
    @RequestMapping("companyAdd")
    public String companyAdd(HttpServletRequest request,Company company,Map<String,Object> map) {
    	if(company !=null){
    		map.put("company", company);
    	}
        return "company/companyAdd";
    }

    /**
     * @Description (查看)
     * @param request
     * @return
     */
    @RequestMapping("viewCompany")
    @ResponseBody
    public Company viewCompany(Map<String, Object> map,String comId) {
    	//map.put("company", companyService.selectOne(serialNum));
    	return companyService.selectOne(comId);
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
    public String deleteCompany(Map<String, Object> map,String id) {
    	String flag ="0"; //默认失败
    	try{
    		companyService.delete(id);
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
    

	
	

}
