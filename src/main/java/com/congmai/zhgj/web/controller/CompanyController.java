package com.congmai.zhgj.web.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.congmai.zhgj.core.feature.orm.mybatis.Page;
import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.core.util.ExcelReader;
import com.congmai.zhgj.core.util.StringUtil;
import com.congmai.zhgj.core.util.UserUtil;
import com.congmai.zhgj.core.util.ExcelReader.RowHandler;
import com.congmai.zhgj.core.util.ExcelUtil;
import com.congmai.zhgj.web.enums.ComType;
import com.congmai.zhgj.web.enums.StaticConst;
import com.congmai.zhgj.web.event.EventExample;
import com.congmai.zhgj.web.event.SendMessageEvent;
import com.congmai.zhgj.web.model.Company;
import com.congmai.zhgj.web.model.CompanyAddress;
import com.congmai.zhgj.web.model.CompanyContact;
import com.congmai.zhgj.web.model.CompanyExample;
import com.congmai.zhgj.web.model.CompanyExample.Criteria;
import com.congmai.zhgj.web.model.Category;
import com.congmai.zhgj.web.model.ClauseAdvance;
import com.congmai.zhgj.web.model.ClauseAfterSales;
import com.congmai.zhgj.web.model.ClauseCheckAccept;
import com.congmai.zhgj.web.model.ClauseDelivery;
import com.congmai.zhgj.web.model.ClauseFramework;
import com.congmai.zhgj.web.model.ClauseFrameworkExample;
import com.congmai.zhgj.web.model.ClauseSettlement;
import com.congmai.zhgj.web.model.CompanyFinance;
import com.congmai.zhgj.web.model.CompanyFinanceExample;
import com.congmai.zhgj.web.model.CompanyManage;
import com.congmai.zhgj.web.model.CompanyQualification;
import com.congmai.zhgj.web.model.ContractVO;
import com.congmai.zhgj.web.model.DataTablesParams;
import com.congmai.zhgj.web.model.Materiel;
import com.congmai.zhgj.web.model.MaterielSelectExample;
import com.congmai.zhgj.web.model.OrderFile;
import com.congmai.zhgj.web.model.OrderFileExample;
import com.congmai.zhgj.web.model.OrderInfo;
import com.congmai.zhgj.web.model.OrderMateriel;
import com.congmai.zhgj.web.model.OrderMaterielExample;
import com.congmai.zhgj.web.model.SupplyBuyVO;
import com.congmai.zhgj.web.model.SupplyMateriel;
import com.congmai.zhgj.web.model.SupplyMaterielExample;
import com.congmai.zhgj.web.model.User;
import com.congmai.zhgj.web.model.Warehouse;
import com.congmai.zhgj.web.model.WarehouseExample;
import com.congmai.zhgj.web.service.CompanyAddressService;
import com.congmai.zhgj.web.service.CompanyContactService;
import com.congmai.zhgj.web.service.CompanyFinanceService;
import com.congmai.zhgj.web.service.CompanyManageService;
import com.congmai.zhgj.web.service.CompanyQualificationService;
import com.congmai.zhgj.web.service.CompanyService;
import com.congmai.zhgj.web.service.OrderService;
import com.congmai.zhgj.web.service.UserCompanyService;
import com.congmai.zhgj.web.service.UserService;
import com.congmai.zhgj.web.service.WarehouseService;


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
	@Autowired
	private UserCompanyService userCompanyService;
	@Autowired
	private CompanyAddressService companyAddressService;
	@Autowired
	private UserService userService;
	@Autowired
	private CompanyManageService companyManageService;
	@Autowired
	private WarehouseService warehouseService;
	@Autowired
	private OrderService orderService;
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
    @RequestMapping(value="companyList",method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String,Object>> companyList(Map<String, Object> map,HttpServletRequest request,String params,Company company) {
    	//远程分页代码
    	/*try {
    		params = URLDecoder.decode(params, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	 ObjectMapper objectMapper = new ObjectMapper();
    	 objectMapper.configure(org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  	   		DataTablesParams  dataTablesParams = null;
		   try {
			   JSONObject a = JSONObject.fromObject(params);
			   dataTablesParams = objectMapper.readValue(params,DataTablesParams.class);
			} catch (JsonParseException e) {
				//20180110 qhzhao System.out.println(this.getClass()+"---------"+ e.getMessage());
			} catch (JsonMappingException e) {
				//20180110 qhzhao System.out.println(this.getClass()+"---------"+ e.getMessage());
			} catch (IOException e) {
				//20180110 qhzhao System.out.println(this.getClass()+"---------"+ e.getMessage());
			} catch (Exception e) {
		    	//20180110 qhzhao System.out.println(this.getClass()+"---------"+ e.getMessage());
			}
		 company.setPageIndex(dataTablesParams.getStart());
		 company.setPageSize(dataTablesParams.getLength());*/
    	
		 company.setPageIndex(0);
		 company.setPageSize(-1);
		 //获取session中的user
		User user = UserUtil.getUserFromSession();
		String comId = userCompanyService.getUserComId(String.valueOf(user.getUserId()));
		Page<Company> companys = new Page<Company>();
		if(user !=null){
			company.setComIds(userCompanyService.getComIdsByUserId(String.valueOf(user.getUserId())));//获取用户的企业ID
			company.setComId(comId);
			if(!StringUtils.isEmpty(comId)){
				Company com=companyService.selectById(comId);
				if("1".equals(com.getComType())){
					company.setComType("1");
				}else if("2".equals(com.getComType())){
					company.setComType("2");
				}
			}else{
				if("buy".equals(params)){
					company.setComType("buy");
				}else if("supply".equals(params)){
					company.setComType("supply");
				}else if("other".equals(params)){
					company.setComType("other");
				}
			}
			companys = companyService.selectByPage(company);
	    	//List<Company> companys = companyService.selectByPage(company).getResult();
			// 封装datatables数据返回到前台
		}
    	
		Map<String,Object> pageMap = new HashMap<String,Object>();
		pageMap.put("draw", 1);
		pageMap.put("recordsTotal", company==null?0:companys.getTotalCount());
		pageMap.put("recordsFiltered", company==null?0:companys.getTotalCount());
		pageMap.put("data", companys.getResult());
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
    	Company company =  companyService.selectOne(comId);
    	map.put("company", company);
    	map.put("companyFinances", companyFinanceService.selectListByComId(comId));
     	map.put("companyQualifications", companyQualificationService.selectListByComId(comId));
    	map.put("companyContacts", companyContactService.selectListByComId(comId));
    	map.put("companyAddresses", companyAddressService.selectListByComId(comId));
    	map.put("comManagers", null);
    	map.put("companyManage", CollectionUtils.isEmpty(companyManageService.selectListByComId(comId))?null:companyManageService.selectListByComId(comId).get(0));
    	company.setPageIndex(0);
		 company.setPageSize(-1);
		 if("1".equals(company.getComType())){
				map.put("buyComs", companyService.selectByPage(company).getResult());
				map.put("supplies",null);
		 }else if("2".equals(company.getComType())){
			 map.put("supplies", companyService.selectByPage(company).getResult());
				map.put("buyComs",null);
		 }
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
    	map.put("companyAddresses", companyAddressService.selectListByComId(comId));
    	map.put("companyManage", CollectionUtils.isEmpty(companyManageService.selectListByComId(comId))?null:companyManageService.selectListByComId(comId).get(0));
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
    	Subject currentUser = SecurityUtils.getSubject();
    	User user = UserUtil.getUserFromSession();
    	String comId = userCompanyService.getUserComId(String.valueOf(user.getUserId()));
    	String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名

        	try{
        		String isExist = checkComNumIsExist(company.getComId(),company.getComNum());
        		String isExist1 = checkComNameIsExist(company.getComId(),company.getComName());
        		if("2".equals(isExist)){
        			company.setComNum("isExist");
        			return company;
        		}
        		if("2".equals(isExist1)){
        			company.setComName("isExist");
        			return company;
        		}
        		if(StringUtils.isEmpty(company.getComId())){
        			String newComId=UUID.randomUUID().toString().replace("-","");
        			company.setComId(newComId);
        			company.setCreateTime(new Date());
        			company.setCreator(currenLoginName);
        			company.setUpdateTime(new Date());
        			company.setUpdater(currenLoginName);
        			companyService.insert(company);
        			//生成业务关系
        			SupplyBuyVO vo=new SupplyBuyVO();
        			vo.setSerialNum(UUID.randomUUID().toString().replace("-",""));
        			vo.setCreateId(comId);
        			vo.setDelFlg("0");
        			if(!StringUtils.isEmpty(comId)){
        				Company com=companyService.selectOne(comId);
        				if("1".equals(com.getComType())){//采购商
        					vo.setSupplyId(newComId);
        				}else if("2".equals(com.getComType())){//供应商
        					vo.setBuyId(newComId);
        				}
        			}
        			companyService.insertSupplyBuy(vo);
        		}else{
        			company.setUpdateTime(new Date());
        			company.setUpdater(currenLoginName);
        			companyService.update(company);
        		}
        		
        		EventExample.getEventPublisher().publicSendMessageEvent(new SendMessageEvent(company,"saveCompany"));
        		
        		flag = "1";
        	}catch(Exception e){
        		//20180110 qhzhao System.out.println(e.getMessage());
        		return null;
        	}
    	return company;
    }
    
    /**
     * @Description (检查编号唯一性)
     * @param comNum
     * @param comId
     * @return
     */
    @RequestMapping("checkComNumIsExist")
    @ResponseBody
    public String checkComNumIsExist(String comId,String comNum){
    	String flag  = "0"; //默认失败
    	try {
    		CompanyExample example = new CompanyExample();
        	Criteria criteria = example.createCriteria();
        	criteria.andDelFlgEqualTo("0");
        	criteria.andComNumEqualTo(comNum);
        	if(StringUtils.isNotEmpty(comId)){
        		criteria.andComIdNotEqualTo(comId);
        	}
        	int count = companyService.countCompanybySelective(example);
        	if(count > 0){
        				flag = "2"; //存在重复
        	}else{
        				flag = "1"; //合法
        	}
		} catch (Exception e) {
			//20180110 qhzhao System.out.println(this.getClass()+"----------"+e.getMessage());
		}
    	
    	return flag;
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
    		companyService.deleteCompany(comId);
    		flag = "1";
    	}catch(Exception e){
    		//20180110 qhzhao System.out.println(e.getMessage());
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
    		//20180110 qhzhao System.out.println(e.getMessage());
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
            	//companyQualificationService.deleteByComId(companyQualifications.get(0).getComId());
        		companyQualificationService.insertBatch(companyQualifications,currenLoginName);
            }
            
    		flag = "1";
    	}catch(Exception e){
    		//20180110 qhzhao System.out.println(e.getMessage());
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
    		//20180110 qhzhao System.out.println(e.getMessage());
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
    		//20180110 qhzhao System.out.println(e.getMessage());
    	}
    	return flag;
    }
    
    /**
     * @Description (保存联系地址)
     * @param request
     * @return
     */
    @RequestMapping(value="saveCompanyAddress",method=RequestMethod.POST)
    @ResponseBody
    public List<CompanyAddress> saveCompanyAddress(Map<String, Object> map,@RequestBody CompanyAddress companyAddress,HttpServletRequest request) {
    	String flag ="0"; //默认失败
    	List<CompanyAddress> companyAddresses = null;
    	try{
    		Subject currentUser = SecurityUtils.getSubject();
    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
    		if(StringUtils.isEmpty(companyAddress.getSerialNum())){
    			companyAddress.setSerialNum(ApplicationUtils.random32UUID());
    			companyAddress.setDelFlg("0");
    			companyAddress.setCreateTime(new Date());
    			companyAddress.setCreator(currenLoginName);
    			companyAddress.setUpdateTime(new Date());
    			companyAddress.setUpdater(currenLoginName);
    			companyAddressService.insert(companyAddress);
    		}else{
    			companyAddress.setUpdateTime(new Date());
    			companyAddress.setUpdater(currenLoginName);
    			companyAddressService.update(companyAddress);
    		}
    		
    		companyAddresses = companyAddressService.selectListByComId(companyAddress.getComId());
    		
    		flag = "1";
    	}catch(Exception e){
    		//20180110 qhzhao System.out.println(e.getMessage());
    		return null;
    	}
    	return companyAddresses;
    }
    
    /**
     * @Description (删除联系地址)
     * @param request
     * @return
     */
    @RequestMapping("deleteCompanyAddress")
    @ResponseBody
    public String deleteCompanyAddress(Map<String, Object> map,String serialNum) {
    	String flag ="0"; //默认失败
    	
    	try{
    		
    		companyAddressService.delete(serialNum);
    		
    		
    		flag = "1";
    	}catch(Exception e){
    		//20180110 qhzhao System.out.println(e.getMessage());
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
    		//20180110 qhzhao System.out.println(e.getMessage());
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
    		//20180110 qhzhao System.out.println(e.getMessage());
    	}
    	return flag;
    }
    
    /**
     * @Description (导出企业信息)
     * @param request
     * @return
     */
    @RequestMapping("exportCompany")
    public void exportCompany(Map<String, Object> map,HttpServletRequest request,HttpServletResponse response,String type,String serialNums) {
    		Map<String, Object> dataMap = new HashMap<String, Object>();
    		List<Company> companyList =new ArrayList<Company>();
    		if(StringUtil.isEmpty(serialNums)){
    			Company company = new Company();
        		company.setPageSize(-1);
        		company.setComType(type);
        		companyList = companyService.selectByPage(company).getResult();
    			}else{
    				List<String> idList = ApplicationUtils.getIdList(serialNums);
        			for(String id:idList){
        				companyList.add(companyService.selectOne(id));
        			}
    			}
    		
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
			final List<Company> companyList = new ArrayList<Company>(); 
			excelReader.readExcelContent(new RowHandler() {
				@SuppressWarnings({ "serial", "unused" })
				class MyException extends Exception{
				    public MyException(){
				        super();
				    }
				    public MyException(String msg){
				        super(msg);
				    }
				}
				@Override
				public void handle(List<Object> row,int i) throws Exception {
					if(!CollectionUtils.isEmpty(row)){
						try{
							Company company = new Company();
							company.setComId(ApplicationUtils.random32UUID());
							company.setComNum(StringUtil.rowCell2String(row,0));
							if (StringUtils.isNotEmpty(company.getComNum())) {
								if(orderService.isExist("company",company.getComNum(),null)){
									throw new MyException("企业编号已存在！");
								}
							}else {
								throw new MyException("企业编号不能为空！");
							}
							company.setComName(StringUtil.rowCell2String(row,1));
							String comTypeName = ComType.getValueByInfo(StringUtil.rowCell2String(row,2).trim());
							if(comTypeName==null){
								throw new MyException("企业类型不存在");
							}
							company.setComType(ComType.getValueByInfo(StringUtil.rowCell2String(row,2).trim()));
							company.setAbbreviation(StringUtil.rowCell2String(row,3));
							company.setBusinessNature(StringUtil.rowCell2String(row,4));
							company.setComNature(StringUtil.rowCell2String(row,5));
							company.setBusinessType(StringUtil.rowCell2String(row,6));
							company.setRegisteredCapital(StringUtil.rowCell2String(row,7));
							company.setLegalPerson(StringUtil.rowCell2String(row,8));
							company.setAddress(StringUtil.rowCell2String(row,9));
							company.setTaxpayeNumber(StringUtil.rowCell2String(row,10));
							company.setTel(StringUtil.rowCell2String(row,11));
							company.setContact(StringUtil.rowCell2String(row,12));
							company.setRemark(StringUtil.rowCell2String(row,13));
							Subject currentUser = SecurityUtils.getSubject();
							String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
							company.setCreateTime(new Date());
							company.setCreator(currenLoginName);
							company.setUpdateTime(new Date());
							company.setUpdater(currenLoginName);
							companyList.add(company);
						}catch(MyException  e){
							throw new Exception("第"+(i+1)+"行数据异常请检查，数据内容："+e.getMessage());
						}catch(Exception  e){
							throw new Exception("第"+(i+1)+"行数据转换错误！");
						}
						
					}
					
				}
			}, 2);
				
			companyService.insertBatch(companyList);
			
			map.put("data", "success");
		} catch (Exception e1) {
			map.put("data", e1.getMessage());
		}
    	
         return map;
    }
    
    

    /**
     * @Description (获取采购商)
     * @param request
     * @return
     */
    @RequestMapping("getCustomers")
    @ResponseBody
    public List<Company> getCustomers(HttpServletRequest request,String searchKey) {
    	
    	return companyService.selectCompanyByComType(ComType.BUYER.getValue(), searchKey);
    }
	
    /**
     * @Description (获取供应商)
     * @param request
     * @return
     */
    @RequestMapping("getSuppliers")
    @ResponseBody
    public List<Company> getSuppliers(HttpServletRequest request,String searchKey) {
    	
    	return companyService.selectCompanyByComType(ComType.SUPPLIER.getValue(), searchKey);
    }
    
    /**
     * @Description (获取当前登录人信息)
     * @param request
     * @return
     */
    @RequestMapping("getCurrentUser")
    @ResponseBody
    public User getCurrentUser(HttpServletRequest request) {
    		User user =	UserUtil.getUserFromSession();
    		
    		return user;
    }
    /**
     * @Description (获取当前登录公司仓库信息)
     * @param request
     * @return
     */
    @RequestMapping("getCurrentWarehouse")
    @ResponseBody
    public List<Warehouse> getCurrentWarehouse(HttpServletRequest request) {
    		User user =	UserUtil.getUserFromSession();
    		List<String> comIds = null;
        	String  comId = null;
        	if(user!=null){
    			comIds = userCompanyService.getComIdsByUserId(String.valueOf(user.getUserId()));
    		}
        	if(comIds!=null){
        		comId =comIds.get(0);
        	}
        	WarehouseExample we=new WarehouseExample();
        	com.congmai.zhgj.web.model.WarehouseExample.Criteria c=we.createCriteria();
        	c.andDelFlgEqualTo("0").andOwnerEqualTo(comId);
        	List<Warehouse>warehouseList=warehouseService.selectWarehouseList(we);
    		return warehouseList;
    }
    
    /**
     * @Description (获取订单编号)
     * @param request
     * @return
     */
    @RequestMapping("getOrderNum")
    @ResponseBody
    public Map<String, Object> getOrderNum(HttpServletRequest request) {
    	Map<String, Object> map = new HashMap<String, Object>();
    	String orderNum = "OR"+ApplicationUtils.getFromNumber();
    	map.put("orderNum", orderNum);
    	return map;
    }
    /**
     * @Description (获取所有公司)
     * @param request
     * @return
     */
    @RequestMapping("getAllComs")
    @ResponseBody
    public List<Company> getAllComs(HttpServletRequest request) {
    	
    	return companyService.selectAll();
    }
    /**
     * @Description (检查企业名称唯一性)
     * @param comNum
     * @param comId
     * @return
     */
    @RequestMapping("checkComNameIsExist")
    @ResponseBody
    public String checkComNameIsExist(String comId,String comName){
    	String flag  = "0"; //默认失败
    	try {
    		CompanyExample example = new CompanyExample();
        	Criteria criteria = example.createCriteria();
        	criteria.andDelFlgEqualTo("0");
        	criteria.andComNameEqualTo(comName);
        	if(StringUtils.isNotEmpty(comId)){
        		criteria.andComIdNotEqualTo(comId);
        	}
        	int count = companyService.countCompanybySelective(example);
        	if(count > 0){
        				flag = "2"; //存在重复
        	}else{
        				flag = "1"; //合法
        	}
		} catch (Exception e) {
			//20180110 qhzhao System.out.println(this.getClass()+"----------"+e.getMessage());
		}
    	
    	return flag;
    }
    /**
     * 
     * @Description 查询用户
     * @param type(采购商/供应商)
     * @return
     */
    @RequestMapping("/findUserList")
    @ResponseBody
    public ResponseEntity<Map> findUserList(String type) {
    	List<User>userList=userService.selectUserList(type);
		Map pageMap = new HashMap();
		pageMap.put("draw", 1);
		pageMap.put("recordsTotal", userList==null?0:userList.size());
		pageMap.put("recordsFiltered", userList==null?0:userList.size());
		pageMap.put("data", userList);
		return new ResponseEntity<Map>(pageMap, HttpStatus.OK);

    }
    /**
     * @Description (保存管理信息)
     * @param request
     * @return
     */
    @RequestMapping(value="saveCompanyManage",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object>  saveCompanyManage(@RequestBody CompanyManage companyManage,HttpServletRequest request) {
    	String flag ="0"; //默认失败
    	Subject currentUser = SecurityUtils.getSubject();
    	String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
    	Map<String, Object> map=new HashMap<String, Object>();
        	try{
        		if(org.springframework.util.StringUtils.isEmpty(companyManage.getSerialNum())){
        			companyManage.setSerialNum(UUID.randomUUID().toString().replace("-",""));
        			companyManage.setCreator(currenLoginName);
        			companyManageService.insert(companyManage);
        		}else{
        			companyManage.setUpdater(currenLoginName);
        			companyManageService.update(companyManage);
        		}
        		List<CompanyManage>list=companyManageService.selectListByComId(companyManage.getComId());
        		map.put("companyManage", list.get(0));
        		flag = "1";
        		
        	}catch(Exception e){
        		//20180110 qhzhao System.out.println(e.getMessage());
        		return null;
        	}
        	map.put("flag", flag);
    	return map;
    }
    /**
     * 
     * @Description 保存企业业务关系
     * @param params
     * @return 
     */
    @RequestMapping(value = "/saveCompanyRelation", method = RequestMethod.POST)
    @ResponseBody
    public List<Company> saveCompanyRelation(@RequestBody String params) {
    	params = params.replace("\\", "");
        List<Company> comList = null;
		try {
			comList = JSON.parseArray(params, Company.class);
	    	if(!CollectionUtils.isEmpty(comList)){
	    		Subject currentUser = SecurityUtils.getSubject();
	    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
	    		String comId=comList.get(0).getCreator();
		    	companyService.deleteSupplyBuy(comId);//先删除所有业务关系
		    	Company com=companyService.selectById(comList.get(0).getCreator());
		    	String comType=com.getComType();
	    		for(Company f:comList){
	    			SupplyBuyVO svo=new SupplyBuyVO();
	    			svo.setSerialNum(ApplicationUtils.random32UUID());
	    			if("1".equals(comType)){
	    				svo.setBuyId(null);
	    				svo.setSupplyId(f.getComId());
	    			}else if("2".equals(comType)){
	    				svo.setBuyId(f.getComId());
	    				svo.setSupplyId(null);
	    			}
	    			svo.setCreateId(comId);
	    			svo.setCreator(currenLoginName);
	    			svo.setUpdater(currenLoginName);
	    			svo.setCreateTime(new Date());
	    			svo.setUpdateTime(new Date());
	    			companyService.insertSupplyBuy(svo);
		    	}
	        }else{
	        	/*String createComId=	comList.get(0).getCreator();
	        	supplybuy*/
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
		
    	return comList;
    	
    }
    
    /**
     * @Description (获取平台公司仓库)
     * @param request
     * @return
     */
    @RequestMapping("initPtWarehouseAddress")
    @ResponseBody
    public List<Warehouse> getPtWarehouseAddress(HttpServletRequest request) {
    	WarehouseExample we=new WarehouseExample();
    	com.congmai.zhgj.web.model.WarehouseExample.Criteria c=we.createCriteria();
    	c.andDelFlgEqualTo("0").andOwnerEqualTo("pingtai");
    	return warehouseService.selectWarehouseList(we);
    }
    /**
	 * 
	 * @Description 获取订单信息
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/getComFinances",method = RequestMethod.POST)
	@ResponseBody
	public List<CompanyFinance> getComFinances(@RequestBody String comId) {
		List<CompanyFinance>list=new ArrayList<CompanyFinance>();
		if(!StringUtil.isEmpty(comId)){
			list=companyFinanceService.selectListByComId(comId);
		}
    	return list;
	}
	/**
     * @Description (获取采购商联系地址信息)
     * @param request
     * @return
     */
	@RequestMapping(value = "/initBuyComAddress",method = RequestMethod.POST)
    @ResponseBody
    public List<CompanyAddress> getBuyComAddress(@RequestBody String comId) {
		List<CompanyAddress>list=new ArrayList<CompanyAddress>();
		if(!StringUtil.isEmpty(comId)){
			list=companyAddressService.selectListByComId(comId);
		}
    	return list;
    }
}

