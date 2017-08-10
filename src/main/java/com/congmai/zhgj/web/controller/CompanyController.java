package com.congmai.zhgj.web.controller;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.core.util.ExcelReader;
import com.congmai.zhgj.core.util.ExcelReader.RowHandler;
import com.congmai.zhgj.core.util.ExcelUtil;
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
        return "company/companyManage";
    }
    
    /**
     * @Description (获取列表数据)
     * @param request
     * @return
     */
  /*  @RequestMapping("companyList")
    @ResponseBody
    public Page<Company> companyList(Map<String, Object> map,HttpServletRequest request,Company company) {
    	company.setFirstResult((company.getPageIndex()-1)*company.getPageSize());
    	company.setPageSize(company.getPageSize());
    	return companyService.selectByPage(company);
    }
    */
    /**
     * @Description (获取列表数据)
     * @param request
     * @return
     */
    @RequestMapping("companyList")
    public ResponseEntity<Map<String,Object>> companyList(Map<String, Object> map,HttpServletRequest request,Company company) {
    	
    	List<Company> companys = companyService.selectByPage(new Company()).getResult();

		if (companys.isEmpty()) {
			return new ResponseEntity<Map<String,Object>>(HttpStatus.NO_CONTENT);// You many
																	// decide to
																	// return
																	// HttpStatus.NOT_FOUND
		}
		// 封装datatables数据返回到前台
		Map<String,Object> pageMap = new HashMap<String,Object>();
		pageMap.put("draw", 1);
		pageMap.put("recordsTotal", companys.size());
		pageMap.put("recordsFiltered", companys.size());
		pageMap.put("data", companys);
		return new ResponseEntity<Map<String,Object>>(pageMap, HttpStatus.OK);
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
    @RequestMapping(value="saveCompany",method=RequestMethod.POST)
    @ResponseBody
    public Company saveCompany(Map<String, Object> map,@RequestBody Company company,HttpServletRequest request) {
    	String flag ="0"; //默认失败

        	try{
        		Subject currentUser = SecurityUtils.getSubject();
        		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
        		if(StringUtils.isEmpty(company.getComId())){
        			company.setComId(UUID.randomUUID().toString().replace("-",""));
        			company.setCreateTime(new Date());
        			company.setCreator(currenLoginName);
        			company.setUpdateTime(new Date());
        			company.setUpdater(currenLoginName);
        			companyService.insert(company);
        		}else{
        			company.setUpdateTime(new Date());
        			company.setUpdater(currenLoginName);
        			companyService.update(company);
        		}
        		
        		flag = "1";
        	}catch(Exception e){
        		System.out.println(e.getMessage());
        		return null;
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
    @RequestMapping(value="deleteCompanyBatch",method=RequestMethod.POST)
    @ResponseBody
    public String deleteCompanyBatch(Map<String, Object> map,@RequestBody String comIds) {
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
    @RequestMapping(value="saveCompanyQualification",method=RequestMethod.POST)
    @ResponseBody
    public List<CompanyQualification> saveCompanyQualification(Map<String, Object> map,@RequestBody String params,HttpServletRequest request) {
    	//List<CompanyQualification> companyQualificationArrays =Arrays.asList(companyQualifications);
    	String flag ="0"; //默认失败
    	List<CompanyQualification> companyQualifications = null;
	   	try{
	   		Subject currentUser = SecurityUtils.getSubject();
    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
    		params = params.replace("\\", "");
    		ObjectMapper objectMapper = new ObjectMapper();  
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, CompanyQualification.class);  
            companyQualifications = objectMapper.readValue(params, javaType);
            if(!CollectionUtils.isEmpty(companyQualifications)){
            	companyQualificationService.deleteByComId(companyQualifications.get(0).getComId());
        		companyQualificationService.insertBatch(companyQualifications,currenLoginName);
            }
            
    		flag = "1";
    	}catch(Exception e){
    		System.out.println(e.getMessage());
    		return null;
    	}
 
    	return companyQualifications;
    }
    
    /**
     * @Description (保存联系人)
     * @param request
     * @return
     */
    @RequestMapping(value="saveCompanyContact",method=RequestMethod.POST)
    @ResponseBody
    public List<CompanyContact> saveCompanyContact(Map<String, Object> map,@RequestBody CompanyContact companyContact,HttpServletRequest request) {
    	String flag ="0"; //默认失败
    	List<CompanyContact> companyContacts = null;
	   	try{
	   		Subject currentUser = SecurityUtils.getSubject();
    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
    		if(StringUtils.isEmpty(companyContact.getSerialNum())){
    			companyContact.setSerialNum(UUID.randomUUID().toString().replace("-",""));
    			companyContact.setSerialNum(UUID.randomUUID().toString().replace("-",""));
    			companyContact.setCreateTime(new Date());
    			companyContact.setCreator(currenLoginName);
    			companyContact.setUpdateTime(new Date());
    			companyContact.setUpdater(currenLoginName);
    			companyContactService.insert(companyContact);
    		}else{
    			companyContact.setUpdateTime(new Date());
    			companyContact.setUpdater(currenLoginName);
    			companyContactService.update(companyContact);
    		}
    		
    		companyContacts = companyContactService.selectListByComId(companyContact.getComId());
    		
    		flag = "1";
    	}catch(Exception e){
    		System.out.println(e.getMessage());
    		return null;
    	}
     	return companyContacts;
    }
    
    /**
     * @Description (删除联系人)
     * @param request
     * @return
     */
    @RequestMapping("deleteCompanyContact")
    @ResponseBody
    public String deleteCompanyContact(Map<String, Object> map,String serialNum) {
    	String flag ="0"; //默认失败
    
    	try{
    		
    		companyContactService.delete(serialNum);
    		
    		
    		flag = "1";
    	}catch(Exception e){
    		System.out.println(e.getMessage());
    	}
    	return flag;
    }

    /**
     * @Description (保存财务信息)
     * @param request
     * @return
     */
    @RequestMapping(value="saveCompanyFinance",method=RequestMethod.POST)
    @ResponseBody
    public List<CompanyFinance> saveCompanyFinance(Map<String, Object> map,@RequestBody CompanyFinance companyFinance,HttpServletRequest request) {
    	String flag ="0"; //默认失败
    	List<CompanyFinance> companyFinances = null;
    	Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
	   	try{
    		if(StringUtils.isEmpty(companyFinance.getSerialNum())){
    			companyFinance.setSerialNum(UUID.randomUUID().toString().replace("-",""));
    			companyFinance.setCreateTime(new Date());
    			companyFinance.setCreator(currenLoginName);
    			companyFinance.setUpdateTime(new Date());
    			companyFinance.setUpdater(currenLoginName);
    			companyFinanceService.insert(companyFinance);
    		}else{
    			companyFinance.setUpdateTime(new Date());
    			companyFinance.setUpdater(currenLoginName);
    			companyFinanceService.update(companyFinance);
    		}
    		
    		companyFinances = companyFinanceService.selectListByComId(companyFinance.getComId());
    		
    		flag = "1";
    	}catch(Exception e){
    		System.out.println(e.getMessage());
    		return null;
    	}
    	return companyFinances;
    }
    
    /**
     * @Description (删除财务信息)
     * @param request
     * @return
     */
    @RequestMapping("deleteCompanyFinance")
    @ResponseBody
    public String deleteCompanyFinance(Map<String, Object> map,String serialNum) {
    	String flag ="0"; //默认失败
    	
    	try{
    		
    		companyFinanceService.delete(serialNum);
    		
    		
    		flag = "1";
    	}catch(Exception e){
    		System.out.println(e.getMessage());
    	}
    	return flag;
    }
    
    /**
     * @Description (导出企业信息)
     * @param request
     * @return
     */
    @RequestMapping("exportCompany")
    public void exportCompany(Map<String, Object> map,HttpServletRequest request,HttpServletResponse response) {
    		Map<String, Object> dataMap = new HashMap<String, Object>();
    		List<Company> companyList = companyService.selectByPage(new Company()).getResult();
    		dataMap.put("companyList",companyList);
    		ExcelUtil.export(request, response, dataMap, "company", "企业信息");
    }
    
    /**
     * @Description (下载导入模板)
     * @param request
     * @return
     */
    @RequestMapping("downloadImportTemp")
    public void downloadCompanyTemp(Map<String, Object> map,HttpServletRequest request,HttpServletResponse response) {
    	ExcelUtil.importTempDownLoad(request, response, "company");
    }
    
    /**
     * @Description (企业信息导入)
     * @param request
     * @return
     */
    @RequestMapping("companyImport")
    @ResponseBody
    public Map<String,String> companyImport(@RequestParam(value = "excelFile") MultipartFile excelFile,HttpServletRequest request,HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String, String>();
    	 try {
		     
			ExcelReader excelReader = new ExcelReader(excelFile.getInputStream());
			excelReader.readExcelContent(new RowHandler() {
				@Override
				public void handle(List<Object> row,int i) throws Exception {
					if(!CollectionUtils.isEmpty(row)){
						try{
							Company company = new Company();
							company.setComId(ApplicationUtils.random32UUID());
							company.setComNum(row.get(0).toString());
							company.setComName(row.get(1).toString());
							company.setComType(row.get(2).toString());
							company.setAbbreviation(row.get(3).toString());
							company.setBusinessNature(row.get(4).toString());
							company.setComNature(row.get(5).toString());
							company.setBusinessType(row.get(6).toString());
							company.setRegisteredCapital(row.get(7).toString());
							company.setLegalPerson(row.get(8).toString());
							company.setAddress(row.get(9).toString());
							company.setTaxpayeNumber(row.get(10).toString());
							company.setTel(row.get(11).toString());
							company.setContact(row.get(12).toString());
							company.setRemark(row.get(13).toString());
							Subject currentUser = SecurityUtils.getSubject();
							String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
							company.setCreateTime(new Date());
							company.setCreator(currenLoginName);
							company.setUpdateTime(new Date());
							company.setUpdater(currenLoginName);
							companyService.insert(company);
						}catch(Exception  e){
							throw new Exception("第"+i+"行数据异常请检查，数据内容："+row.toString());
						}
						
					}
					
				}
			}, 1);
			map.put("data", "success");
		} catch (Exception e1) {
			map.put("data", e1.getMessage());
		}
    	
         return map;
    }
    
    

	
	

}