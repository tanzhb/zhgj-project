package com.congmai.zhgj.web.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.core.util.ExcelReader;
import com.congmai.zhgj.core.util.ExcelUtil;
import com.congmai.zhgj.core.util.StringUtil;
import com.congmai.zhgj.core.util.UserUtil;
import com.congmai.zhgj.core.util.ExcelReader.RowHandler;
import com.congmai.zhgj.web.enums.ComType;
import com.congmai.zhgj.web.model.BOMMateriel;
import com.congmai.zhgj.web.model.BOMMaterielExample;
import com.congmai.zhgj.web.model.BuyMateriel;
import com.congmai.zhgj.web.model.BuyMaterielExample;
import com.congmai.zhgj.web.model.Category;
import com.congmai.zhgj.web.model.CategoryExample;
import com.congmai.zhgj.web.model.Company;
import com.congmai.zhgj.web.model.CompanyCode;
import com.congmai.zhgj.web.model.DataTablesParams;
import com.congmai.zhgj.web.model.JsonTreeData;
import com.congmai.zhgj.web.model.Materiel;
import com.congmai.zhgj.web.model.MaterielExample;
import com.congmai.zhgj.web.model.OrderMateriel;
import com.congmai.zhgj.web.model.PriceList;
import com.congmai.zhgj.web.model.Search;
import com.congmai.zhgj.web.model.User;
import com.congmai.zhgj.web.model.MaterielExample.Criteria;
import com.congmai.zhgj.web.model.MaterielFile;
import com.congmai.zhgj.web.model.MaterielFileExample;
import com.congmai.zhgj.web.model.MaterielSelectExample;
import com.congmai.zhgj.web.model.SupplyMateriel;
import com.congmai.zhgj.web.model.SupplyMaterielExample;
import com.congmai.zhgj.web.service.BuyMaterielService;
import com.congmai.zhgj.web.service.CategoryService;
import com.congmai.zhgj.web.service.CompanyService;
import com.congmai.zhgj.web.service.MaterielFileService;
import com.congmai.zhgj.web.service.MaterielService;
import com.congmai.zhgj.web.service.OrderService;
import com.congmai.zhgj.web.service.PriceListService;
import com.congmai.zhgj.web.service.StockService;
import com.congmai.zhgj.web.service.SupplyMaterielService;
import com.congmai.zhgj.web.service.UserCompanyService;

/**
 * 
 * @ClassName MaterielController
 * @Description 物料信息业务处理
 * @author qfzhao
 * @Date 2017年7月28日 下午2:57:52
 * @version 1.0.0
 */
@Controller
@RequestMapping("/materiel")
public class MaterielController {
	
    @Resource
    private MaterielService materielService;
    
    @Resource
    private com.congmai.zhgj.web.service.BOMMaterielService BOMMaterielService;
    
    @Resource
    private MaterielFileService materielFileService;
    
    @Resource
    private SupplyMaterielService supplyMaterielService;
    
    @Resource
    private BuyMaterielService buyMaterielService;
    
    @Resource
    private UserCompanyService userCompanyService;
    
    @Resource
    private CategoryService categoryService;
    
    @Resource
    private CompanyService companyService;
    
    @Resource
    private StockService stockService;
    
    @Resource
    private OrderService orderService;
    
    @Resource
    private PriceListService priceListService;
    
    
    /**
     * 保存物料
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Materiel save(@RequestBody Materiel materiel) {
    	if(materiel.getSerialNum()==null||materiel.getSerialNum().isEmpty()){//新增
    		insertNew(materiel);
    	}else{//编辑升级
    		updateVersion(materiel);
    	}
    	return materiel;
    }
    
    /**
     * 
     * @Description 保存BOM
     * @param params
     * @return
     */
    @RequestMapping(value = "/saveBOM", method = RequestMethod.POST)
    @ResponseBody
    public Map saveBOM(@RequestBody String params) {
    	params = params.replace("\\", "");
		ObjectMapper objectMapper = new ObjectMapper();  
//        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, BOMMateriel.class);  
        List<BOMMateriel> BOM;
		try {
			BOM = JSON.parseArray(params, BOMMateriel.class);
//			objectMapper.readValue(params, javaType);
			Materiel materiel = null;
	    	if(!CollectionUtils.isEmpty(BOM)){
	    		materiel = materielService.selectById(BOM.get(0).getBomMaterielSerial());
	    		if(materiel!=null){
//		    		createNewVersion(materiel);
	    			materiel.setSerialNum(ApplicationUtils.random32UUID());
	    			Subject currentUser = SecurityUtils.getSubject();
	    			String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
	    			materiel.setCreator(currenLoginName);
	    			materiel.setUpdater(currenLoginName);
	    			materiel.setCreateTime(new Date());
	    			materiel.setUpdateTime(new Date());
	    			materiel.setIsLatestVersion("1");
	    			materiel.setStatus("1");
		    	}
	    		//生成新版本物料******↑↑↑↑↑↑********
	    		Subject currentUser = SecurityUtils.getSubject();
	    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
		    	for(BOMMateriel b:BOM){
		    		b.setSerialNum(ApplicationUtils.random32UUID());
		    		b.setBomMaterielSerial(materiel.getSerialNum());
	    			b.setCreator(currenLoginName);
	    			b.setUpdater(currenLoginName);
	    			b.setCreateTime(new Date());
	    			b.setUpdateTime(new Date());
		    	}
		    	//填充BOM******↑↑↑↑↑↑********
		    	BOMMaterielService.betchInsertBOM(materiel,BOM);
		    	//数据插入******↑↑↑↑↑↑********
	        }
	    	
	    	Map<String, Object> map = new HashMap<String, Object>();
	    	map.put("materiel", materiel);
	    	map.put("BOM", BOM);
	    	//返回数据******↑↑↑↑↑↑********
	    	return getMaterielInfo(materiel.getSerialNum(),materiel);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    	
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
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, MaterielFile.class);  
        List<MaterielFile> file;
		try {
			file = objectMapper.readValue(params, javaType);
	    	if(!CollectionUtils.isEmpty(file)){
	    		Subject currentUser = SecurityUtils.getSubject();
	    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
		    	for(MaterielFile f:file){
		    		f.setSerialNum(ApplicationUtils.random32UUID());
		    		f.setUploader(currenLoginName);
		    		f.setCreator(currenLoginName);
	    			f.setUpdater(currenLoginName);
	    			f.setUploadDate(new Date());
	    			f.setCreateTime(new Date());
	    			f.setUpdateTime(new Date());
		    	}
		    	//填充File******↑↑↑↑↑↑********
		    	materielFileService.betchInsertMaterielFiles(file);
		    	//数据插入******↑↑↑↑↑↑********
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
    /**
     * 
     * @Description 保存物料供应商
     * @param params
     * @return 
     */
    @RequestMapping(value = "/saveSupplyMateriel", method = RequestMethod.POST)
    @ResponseBody
    public List<SupplyMateriel> saveSupplyMateriel(@RequestBody String params) {
    	params = params.replace("\\", "");
/*		ObjectMapper objectMapper = new ObjectMapper();  
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, SupplyMateriel.class); */ 
        List<SupplyMateriel> supplyMateriel = null;
		try {
			supplyMateriel = JSON.parseArray(params, SupplyMateriel.class);
	    	if(!CollectionUtils.isEmpty(supplyMateriel)){
	    		Subject currentUser = SecurityUtils.getSubject();
	    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
		    	int i = 0;
	    		for(SupplyMateriel f:supplyMateriel){
		    		f.setSerialNum(ApplicationUtils.random32UUID());
		    		f.setCreator(currenLoginName);
	    			f.setUpdater(currenLoginName);
	    			f.setCreateTime(new Date());
	    			f.setUpdateTime(new Date());
	    			i++;
					f.setSort(i);
		    	}
		    	//填充物料供应商******↑↑↑↑↑↑********
		    	supplyMaterielService.betchInsertSupplyMateriels(supplyMateriel);
		    	//数据插入******↑↑↑↑↑↑********
		    	
		    	SupplyMaterielExample m2 =new SupplyMaterielExample();
		    	com.congmai.zhgj.web.model.SupplyMaterielExample.Criteria criteria2 =  m2.createCriteria();
		    	criteria2.andMaterielIdEqualTo(supplyMateriel.get(0).getMaterielId());
		    	criteria2.andDelFlgEqualTo("0");
		    	m2.setOrderByClause(" sort asc");
		    	supplyMateriel = supplyMaterielService.selectList(m2);
		    	//查询数据返回******↑↑↑↑↑↑********
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
		
    	return supplyMateriel;
    	
    }
    
    
    /**
     * 
     * @Description 保存物料采购商
     * @param params
     * @return 
     */
    @RequestMapping(value = "/saveBuyMateriel", method = RequestMethod.POST)
    @ResponseBody
    public List<BuyMateriel> saveBuyMateriel(@RequestBody String params) {
    	params = params.replace("\\", "");
/*		ObjectMapper objectMapper = new ObjectMapper();  
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, BuyMateriel.class); */ 
        List<BuyMateriel> buyMateriel = null;
		try {
			buyMateriel = JSON.parseArray(params, BuyMateriel.class);
	    	if(!CollectionUtils.isEmpty(buyMateriel)){
	    		Subject currentUser = SecurityUtils.getSubject();
	    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
	    		int i = 0;
	    		for(BuyMateriel f:buyMateriel){
		    		f.setSerialNum(ApplicationUtils.random32UUID());
		    		f.setCreator(currenLoginName);
	    			f.setUpdater(currenLoginName);
	    			f.setCreateTime(new Date());
	    			f.setUpdateTime(new Date());
	    			i++;
					f.setSort(i);
		    	}
		    	//填充物料供应商******↑↑↑↑↑↑********
		    	buyMaterielService.betchInsertBuyMateriels(buyMateriel);
		    	//数据插入******↑↑↑↑↑↑********
		    	
		    	BuyMaterielExample m2 =new BuyMaterielExample();
		    	com.congmai.zhgj.web.model.BuyMaterielExample.Criteria criteria2 =  m2.createCriteria();
		    	criteria2.andMaterielIdEqualTo(buyMateriel.get(0).getMaterielId());
		    	criteria2.andDelFlgEqualTo("0");
		    	m2.setOrderByClause(" sort asc");
		    	buyMateriel = buyMaterielService.selectList(m2);
		    	//查询数据返回******↑↑↑↑↑↑********
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
		
    	return buyMateriel;
    	
    }
    
    
	/**
	 * 
	 * @Description 升级版本
	 * @param materiel
	 */
	private void updateVersion(Materiel materiel) {
		createNewVersion(materiel);
		materielService.updateVersion(materiel);
	}


	/**
	 * @Description 构造新版本物料(将上一版本下级物料带入版本号为(最新版本+1)的物料中)
	 * @param materiel
	 */
	private void createNewVersion(Materiel materiel) {
		materiel.setSerialNum(ApplicationUtils.random32UUID());
		Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
		materiel.setCreator(currenLoginName);
		materiel.setUpdater(currenLoginName);
		materiel.setCreateTime(new Date());
		materiel.setUpdateTime(new Date());
		materiel.setIsLatestVersion("1");
		
		//根据物料id获取最新版本物料
		Materiel lastmateriel = materielService.getMaterielInfoByMaterielId(materiel.getMaterielId());
		//生成当前版本号为最新版本+1
		if(lastmateriel==null){
			materiel.setVersionNO("1");
		}else{
			materiel.setVersionNO(String.valueOf((Integer.parseInt(lastmateriel.getVersionNO())+1)));
			//获取最新版本下级物料带入最新版本
			if("1".equals(materiel.getIsBOM())){//只有最新(最新版本+1)的物料是bom物料
				BOMMaterielExample m =new BOMMaterielExample();
		    	com.congmai.zhgj.web.model.BOMMaterielExample.Criteria criteria =  m.createCriteria();
		    	criteria.andBomMaterielSerialEqualTo(lastmateriel.getSerialNum());
		    	List<BOMMateriel> bomList = BOMMaterielService.selectList(m);
		    	for(BOMMateriel bom:bomList){
		    		bom.setSerialNum(ApplicationUtils.random32UUID());
		    		bom.setBomMaterielSerial(materiel.getSerialNum());
		    		bom.setCreateTime(new Date());
		    		bom.setCreator(currenLoginName);
		    		BOMMaterielService.insert(bom);
		    	}
			}
		}
		materiel.setStatus("1");
	}
	/**
	 * 
	 * @Description 新增物料
	 * @param materiel
	 */
	private void insertNew(Materiel materiel) {
		if (StringUtil.isEmpty(materiel.getSerialNum())) {
			materiel.setSerialNum(ApplicationUtils.random32UUID());
		}
		if (StringUtil.isEmpty(materiel.getMaterielId())) {
			materiel.setMaterielId(ApplicationUtils.random32UUID());
		}
		
		Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
		materiel.setCreator(currenLoginName);
		materiel.setUpdater(currenLoginName);
		materiel.setCreateTime(new Date());
		materiel.setUpdateTime(new Date());
		materiel.setIsLatestVersion("1");
		materiel.setVersionNO("1");
		materiel.setStatus("1");
		
		materielService.insert(materiel);
		
		Company company = getCompany();
    	if(company!=null&&ComType.SUPPLIER.getValue().equals(company.getComType())){
    		SupplyMateriel sm = new SupplyMateriel();
    		sm.setSerialNum(ApplicationUtils.random32UUID());
    		sm.setMaterielId(materiel.getMaterielId());
    		sm.setSupplyComId(company.getComId());
    		sm.setCreator(currenLoginName);
    		sm.setUpdater(currenLoginName);
    		sm.setCreateTime(new Date());
    		sm.setUpdateTime(new Date());
    		supplyMaterielService.insert(sm);
    	}else if(company!=null&&ComType.BUYER.getValue().equals(company.getComType())){
    		BuyMateriel bm = new BuyMateriel();
    		bm.setSerialNum(ApplicationUtils.random32UUID());
    		bm.setMaterielId(materiel.getMaterielId());
    		bm.setBuyComId(company.getComId());
    		bm.setCreator(currenLoginName);
    		bm.setUpdater(currenLoginName);
    		bm.setCreateTime(new Date());
    		bm.setUpdateTime(new Date());
    		buyMaterielService.insert(bm);
    	}
		
	}

	private Company getCompany() {
		User user = UserUtil.getUserFromSession();
    	List<String> comIds = null;
    	Company company = null;
    	if(user!=null){
			comIds = userCompanyService.getComIdsByUserId(String.valueOf(user.getUserId()));
		}
    	if(comIds!=null){
    		company = companyService.selectById(comIds.get(0));
    	}
		return company;
	}

	//销售订单
	public static final String SALEORDER = "sale";
	//采购订单
	public static final String BUYORDER = "buy";
    /**
     * 
     * @Description 查询物料列表//全部查询，或根据父节点查询
     * @param parent(若有值，则查询该分类下的物料)
     * @param isLatestVersion(若有值为1，则查询所以已发布的正式物料)
     * @param type 销售订单选择物料，筛选有供应商的物料
     * @param supplyComId 物料关联供应商
     * @param 分页查询参数
     * @return
     */
    @RequestMapping("/findMaterielList")
    @ResponseBody
    public ResponseEntity<Map> findMaterielList(HttpServletRequest request,String params,String parent,String isLatestVersion,String type,String supplyComId,String buyComId) {
    	//MaterielExample m =new MaterielExample();
    	MaterielSelectExample m =new MaterielSelectExample();
    	List<Materiel> materielList = new ArrayList<Materiel>();
    	
    	String recordsTotal = null;
    	
    	User user = UserUtil.getUserFromSession();
    	List<String> comIds = null;
    	Company company = null;
    	if(user!=null){
			comIds = userCompanyService.getComIdsByUserId(String.valueOf(user.getUserId()));
		}
    	if(comIds!=null){
    		company = companyService.selectById(comIds.get(0));
    	}
    	DataTablesParams  dataTablesParams = null;
    	if(parent==null||parent.isEmpty()||"null".equals(parent.toLowerCase())){//查询全部物料
    		//and 条件1
        	com.congmai.zhgj.web.model.MaterielSelectExample.Criteria criteria =  m.createCriteria();
        	criteria.andIsLatestVersionEqualTo("1");
        	criteria.andDelFlgEqualTo("0");
        	if("1".equals(isLatestVersion)){
        	}else{
        		//and 条件2,未发布可编辑的物料
            	com.congmai.zhgj.web.model.MaterielSelectExample.Criteria criteria2 =  m.createCriteria();
            	criteria2.andStatusEqualTo("0");
            	criteria2.andDelFlgEqualTo("0");
            	//or 条件
            	m.or(criteria2);
        	}
        	//排序字段
        	m.setOrderByClause("materielNum DESC");
        	if(StringUtils.isNotEmpty(supplyComId)){
        		comIds = new ArrayList<String>();
        		comIds.add(supplyComId);
        		criteria.andSupplyComIdIn(comIds);
        	}else if(StringUtils.isNotEmpty(buyComId)){
        		comIds = new ArrayList<String>();
        		comIds.add(buyComId);
        		criteria.andBuyComIdIn(comIds);
        	}else if(company!=null&&ComType.SUPPLIER.getValue().equals(company.getComType())){
        		criteria.andSupplyComIdIn(comIds);
        	}else if(company!=null&&ComType.BUYER.getValue().equals(company.getComType())){
        		criteria.andBuyComIdIn(comIds);
        	}else if(company!=null&&ComType.TRAFFICKER.getValue().equals(company.getComType())){
        		criteria.andBuyComIdIn(comIds);
        		com.congmai.zhgj.web.model.MaterielSelectExample.Criteria criteria3 =  m.createCriteria();
        		criteria3.andIsLatestVersionEqualTo("1");
        		criteria3.andDelFlgEqualTo("0");
        		criteria3.andSupplyComIdIn(comIds);
        		m.or(criteria3);
        	}
        	
        	if(params!=null){
	        	//TODO分页查询
	        	ObjectMapper objectMapper = new ObjectMapper();
	        	try {
	        		
	        		params = URLDecoder.decode(params, "UTF-8");
					dataTablesParams = objectMapper.readValue(params,DataTablesParams.class);
					m.setStart(dataTablesParams.getStart());
					m.setLength(dataTablesParams.getLength());
					m.setSearchStr(dataTablesParams.getSearch().getValue());
					
					recordsTotal = materielService.selectListCount(m);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        	materielList = materielService.selectList(m);
    	}else{//根据父节点查询
    		
    		Category category = this.categoryService.selectById(parent);
    		
    		//and 条件1
        	com.congmai.zhgj.web.model.MaterielSelectExample.Criteria criteria =  m.createCriteria();
        	criteria.andIsLatestVersionEqualTo("1");
        	criteria.andDelFlgEqualTo("0");
        	if(category!=null){
        		if("1".equals(category.getLevel())){
        			criteria.andTypeEqualTo(parent);
        		}else if("2".equals(category.getLevel())){
        			criteria.andCategory1EqualTo(parent);
        		}else if("3".equals(category.getLevel())){
        			criteria.andCategory2EqualTo(parent);
        		}else if("4".equals(category.getLevel())){
        			criteria.andCategory3EqualTo(parent);
        		}
        	}
        	//and 条件2
        	com.congmai.zhgj.web.model.MaterielSelectExample.Criteria criteria2 =  m.createCriteria();
        	criteria2.andStatusEqualTo("0");
        	criteria2.andDelFlgEqualTo("0");
        	if(category!=null){
        		if("1".equals(category.getLevel())){
        			criteria2.andTypeEqualTo(parent);
        		}else if("2".equals(category.getLevel())){
        			criteria2.andCategory1EqualTo(parent);
        		}else if("3".equals(category.getLevel())){
        			criteria2.andCategory2EqualTo(parent);
        		}else if("4".equals(category.getLevel())){
        			criteria2.andCategory3EqualTo(parent);
        		}
        	}
        	//or 条件
        	m.or(criteria2);
        	//排序字段
        	m.setOrderByClause("materielNum DESC");
        	if(company!=null&&ComType.SUPPLIER.getValue().equals(company.getComType())){
        		criteria.andSupplyComIdIn(comIds);
        	}else if(company!=null&&ComType.BUYER.getValue().equals(company.getComType())){
        		criteria.andBuyComIdIn(comIds);
        	}
        	//TODO分页查询
        	ObjectMapper objectMapper = new ObjectMapper();
        	if(params!=null){
	        	try {
	        		
	        		
	        		
	        		params = URLDecoder.decode(params, "UTF-8");
					dataTablesParams = objectMapper.readValue(params,DataTablesParams.class);
					m.setStart(dataTablesParams.getStart());
					m.setLength(dataTablesParams.getLength());
					
					m.setSearchStr(dataTablesParams.getSearch().getValue());
					
					recordsTotal = materielService.selectListCount(m);		
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        	
        	materielList = materielService.selectList(m);
        	
        	//查询下级物料
        	/*findChildList(parent,materielList);*/
    	}
    	//封装datatables数据返回到前台
    	
    	if(SALEORDER.equals(type)&&materielList!=null&&materielList.size()>1){
    		for(int i=0;i<materielList.size();i++){
    			if(materielList.get(i).getSupplyMateriels()==null||materielList.get(i).getSupplyMateriels().size()<1){
    				materielList.remove(i);
    				i--;
    			}
    		}
    	}
    	Map pageMap = new HashMap();
    	if(dataTablesParams==null){
    		pageMap.put("draw", 1);
    		pageMap.put("recordsTotal", materielList==null?0:materielList.size());
    		pageMap.put("recordsFiltered", materielList==null?0:materielList.size());
    		pageMap.put("data", materielList);
    	}else{
    		pageMap.put("draw", dataTablesParams.getDraw());
    		pageMap.put("recordsTotal", recordsTotal==null?(materielList==null?0:materielList.size()):recordsTotal);
    		pageMap.put("recordsFiltered", recordsTotal==null?(materielList==null?0:materielList.size()):recordsTotal);
    		pageMap.put("data", materielList);
    	}
		
		return new ResponseEntity<Map>(pageMap, HttpStatus.OK);

    }
    //显示采购物料下级物料
    @RequestMapping("/findUnderMaterielList")
    @ResponseBody
    public ResponseEntity<Map> findUnderMaterielList(HttpServletRequest request,String params,String serialNum) {
		Map<String, Object> pageMap = new HashMap<String, Object>();
	    	//查询bom
		    	BOMMaterielExample m =new BOMMaterielExample();
		    	com.congmai.zhgj.web.model.BOMMaterielExample.Criteria criteria =  m.createCriteria();
		    	criteria.andBomMaterielSerialEqualTo(serialNum);
		    	List<BOMMateriel> BOM = BOMMaterielService.selectList(m);
		    	pageMap.put("draw", 1);
				pageMap.put("recordsTotal", BOM==null?0:BOM.size());
				pageMap.put("recordsFiltered", BOM==null?0:BOM.size());
				pageMap.put("data", BOM);
    	return new ResponseEntity<Map>(pageMap, HttpStatus.OK);
    } 	
	private void findChildList(String parent,List<Materiel> allMaterielList) {
		List<Materiel> materielList = new ArrayList<Materiel>();
		MaterielExample m =new MaterielExample();
		//and 条件1
		Criteria criteria =  m.createCriteria();
		criteria.andIsLatestVersionEqualTo("1");
		criteria.andDelFlgEqualTo("0");
		criteria.andParentMaterielSerialEqualTo(parent);
		//and 条件2
		Criteria criteria2 =  m.createCriteria();
		criteria2.andStatusEqualTo("0");
		criteria2.andDelFlgEqualTo("0");
		criteria2.andParentMaterielSerialEqualTo(parent);
		//or 条件
		m.or(criteria2);
		//排序字段
		m.setOrderByClause("updateTime DESC");
		materielList = materielService.selectList(m);
		if(materielList.size()>0){
			allMaterielList.addAll(materielList);
			for(Materiel materiel:materielList){
				findChildList(materiel.getSerialNum(),allMaterielList);
			}
		}
	}
    
    
    
    /**
     * @param oredCriteria 
     * @Description 查询物料列表
     * @param materiel
     * @return
     */
    @RequestMapping("/findMaterielTree")
    @ResponseBody
    public List<JsonTreeData> findMaterielTree(String parent) {
    	MaterielExample m =new MaterielExample();
    	//and 条件1
    	Criteria criteria =  m.createCriteria();
    	criteria.andIsLatestVersionEqualTo("1");
    	criteria.andDelFlgEqualTo("0");
    	if("#".equals(parent)){
    		criteria.andParentMaterielSerialIsNull();
    	}else{
    		criteria.andParentMaterielSerialEqualTo(parent);
    	}
    	//and 条件2
    	Criteria criteria2 =  m.createCriteria();
    	criteria2.andStatusEqualTo("0");
    	criteria2.andDelFlgEqualTo("0");
    	if("#".equals(parent)){
    		criteria2.andParentMaterielSerialIsNull();
    	}else{
    		criteria2.andParentMaterielSerialEqualTo(parent);
    	}
    	
    	//or 条件
    	m.or(criteria2);
    	//排序字段
    	m.setOrderByClause("updateTime DESC");
    	List<Materiel> materielList = materielService.selectList(m);
    	
    	if (materielList.isEmpty()) {
			return new ArrayList<JsonTreeData>();
		}
    	
    	List<JsonTreeData> treeDataList = new ArrayList<JsonTreeData>();
        /*为了整理成公用的方法，所以将查询结果进行二次转换。 */
       for (Materiel materiel : materielList) {
           JsonTreeData treeData = new JsonTreeData();
           treeData.setId(materiel.getSerialNum());
           treeData.setPid(materiel.getParentMaterielSerial());
           treeData.setText(materiel.getMaterielName());
           treeData.setChildren(true);
           treeDataList.add(treeData);
       }
/*       //最后得到结果集,经过FirstJSON转换后就可得所需的json格式
       List<JsonTreeData> newTreeDataList = TreeNodeUtil.getfatherNode(treeDataList);*/
       return treeDataList;
    }
    
    
    /**
     * 
     * @Description 按父id查询子分类
     * @param parent
     * @return
     */
    @RequestMapping("/findMaterielCategoryTree")
    @ResponseBody
    public List<JsonTreeData> findMaterielCategoryTree(String parent) {
    	if("#".equals(parent)){
    		parent = "0";
    	}
    	List<Category> list = this.categoryService.queryCategoryListByParent(parent);
    	
    	if (list.isEmpty()) {
			return new ArrayList<JsonTreeData>();
		}
    	
    	List<JsonTreeData> treeDataList = new ArrayList<JsonTreeData>();
        /*为了整理成公用的方法，所以将查询结果进行二次转换。 */
       for (Category c : list) {
           JsonTreeData treeData = new JsonTreeData();
           treeData.setId(c.getCategoryId());
           treeData.setPid(c.getParentId());
           treeData.setText(c.getCategoryName());
           treeData.setChildren(true);
           treeDataList.add(treeData);
       }
/*       //最后得到结果集,经过FirstJSON转换后就可得所需的json格式
       List<JsonTreeData> newTreeDataList = TreeNodeUtil.getfatherNode(treeDataList);*/
       return treeDataList;
    }
	/**
	 * 
	 * @Description 批量删除物料
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/deleteMateriels", method = RequestMethod.POST)
	public ResponseEntity<Void> deleteMateriels(@RequestBody String ids) {
		if ("".equals(ids) || ids == null) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		List<String> idList = ApplicationUtils.getIdList(ids);
		for(String id : idList){
			Materiel m = new Materiel();
			m.setSerialNum(id);
			m.setDelFlg("1");
			m.setUpdateTime(new Date());
			Subject currentUser = SecurityUtils.getSubject();
			String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
	    	m.setUpdater(currenLoginName);
			materielService.update(m);
		}

		return new ResponseEntity<Void>(HttpStatus.OK);
	}
    
	
	/**
	 * 
	 * @Description 获取物料信息
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/getMaterielInfo")
	@ResponseBody
	public Map getMaterielInfo(String serialNum,Materiel materiel) {
		materiel = materielService.selectById(serialNum);
		Map<String, Object> map = new HashMap<String, Object>();
    	map.put("materiel", materiel);
    	if(materiel!=null){
	    	//查询bom
	    	if("1".equals(materiel.getIsBOM())){//如果是bom物料
		    	BOMMaterielExample m =new BOMMaterielExample();
		    	com.congmai.zhgj.web.model.BOMMaterielExample.Criteria criteria =  m.createCriteria();
		    	criteria.andBomMaterielSerialEqualTo(serialNum);
		    	List<BOMMateriel> BOM = BOMMaterielService.selectList(m);
		    	map.put("BOM", BOM);
	    	}
	    	
	    	MaterielFileExample m =new MaterielFileExample();
	    	com.congmai.zhgj.web.model.MaterielFileExample.Criteria criteria =  m.createCriteria();
	    	criteria.andMaterielIdEqualTo(materiel.getMaterielId());
	    	criteria.andDelFlgEqualTo("0");
	    	List<MaterielFile> file = materielFileService.selectList(m);
	    	map.put("file", file);
	    	
	    	
	    	SupplyMaterielExample m2 =new SupplyMaterielExample();
	    	com.congmai.zhgj.web.model.SupplyMaterielExample.Criteria criteria2 =  m2.createCriteria();
	    	criteria2.andMaterielIdEqualTo(materiel.getMaterielId());
	    	criteria2.andDelFlgEqualTo("0");
	    	List<SupplyMateriel> supplyMateriel = supplyMaterielService.selectList(m2);
	    	map.put("supplyMateriel", supplyMateriel);
	    	
	    	
	    	BuyMaterielExample m3 =new BuyMaterielExample();
	    	com.congmai.zhgj.web.model.BuyMaterielExample.Criteria criteria3 =  m3.createCriteria();
	    	criteria3.andMaterielIdEqualTo(materiel.getMaterielId());
	    	criteria3.andDelFlgEqualTo("0");
	    	List<BuyMateriel> buyMateriel = buyMaterielService.selectList(m3);
	    	map.put("buyMateriel", buyMateriel);
	    	
	    	//转换物料功能分类
	    	if(materiel.getMaterielAttribute()!=null){
	    		String[] attributes = materiel.getMaterielAttribute().split(",");
	    		CategoryExample ex = new CategoryExample();
	    		com.congmai.zhgj.web.model.CategoryExample.Criteria criteria1 =  ex.createCriteria();
	    		criteria1.andCategoryIdIn(Arrays.asList(attributes));
	        	List<Category> list = categoryService.selectList(ex);
	        	if(list!=null){
	        		for (int i = 0; i < list.size(); i++) {
	        			if(i==0){
	        				materiel.setMaterielAttributeName(list.get(i).getCategoryName());
	        			}else{
	        				materiel.setMaterielAttributeName(materiel.getMaterielAttributeName()+','+list.get(i).getCategoryName());
	        			}
					}
	        	}
	    	}
	    	
    	}
    	return map;
	}
    
    
	/**
     * @Description (导出物料信息)
     * @param request
     * @return
     */
    @RequestMapping("exportMateriel")
    public void exportMateriel(Map<String, Object> map,HttpServletRequest request,HttpServletResponse response,String params,String parent,String isLatestVersion,String serialNums) {
    		Map<String, Object> dataMap = new HashMap<String, Object>();
        	List<Materiel> materielList = new ArrayList<Materiel>();
        	
        	User user = UserUtil.getUserFromSession();
        	List<String> comIds = null;
        	Company company = null;
        	if(user!=null){
    			comIds = userCompanyService.getComIdsByUserId(String.valueOf(user.getUserId()));
    		}
        	if(comIds!=null){
        		company = companyService.selectById(comIds.get(0));
        	}
        	DataTablesParams  dataTablesParams = null;
        	MaterielSelectExample m =new MaterielSelectExample();
        	if(StringUtil.isEmpty(serialNums)){
        		if(parent==null||parent.isEmpty()||"null".equals(parent.toLowerCase())){//查询全部物料
            		//and 条件1
                	com.congmai.zhgj.web.model.MaterielSelectExample.Criteria criteria =  m.createCriteria();
                	criteria.andIsLatestVersionEqualTo("1");
                	criteria.andDelFlgEqualTo("0");
                	if("1".equals(isLatestVersion)){
                	}else{
                		//and 条件2,未发布可编辑的物料
                    	com.congmai.zhgj.web.model.MaterielSelectExample.Criteria criteria2 =  m.createCriteria();
                    	criteria2.andStatusEqualTo("0");
                    	criteria2.andDelFlgEqualTo("0");
                    	//or 条件
                    	m.or(criteria2);
                	}
                	//排序字段
                	m.setOrderByClause("materielNum DESC");
                	if(company!=null&&ComType.SUPPLIER.getValue().equals(company.getComType())){
                		criteria.andSupplyComIdIn(comIds);
                	}else if(company!=null&&ComType.BUYER.getValue().equals(company.getComType())){
                		criteria.andBuyComIdIn(comIds);
                	}else if(company!=null&&ComType.TRAFFICKER.getValue().equals(company.getComType())){
                		criteria.andBuyComIdIn(comIds);
                		com.congmai.zhgj.web.model.MaterielSelectExample.Criteria criteria3 =  m.createCriteria();
                		criteria3.andIsLatestVersionEqualTo("1");
                		criteria3.andDelFlgEqualTo("0");
                		criteria3.andSupplyComIdIn(comIds);
                		m.or(criteria3);
                	}
                	
                	if(params!=null&&!"null".equals(params)){
                		m.setSearchStr(params);
                	}
                	materielList = materielService.selectList4Export(m);
                	
                	//拼接供应商采购商名称
                	if(!CollectionUtils.isEmpty(materielList)){
                		for (Materiel materiel : materielList) {
                			if(!CollectionUtils.isEmpty(materiel.getSupplyMateriels())){
                	    		for (SupplyMateriel supplyMateriel2 : materiel.getSupplyMateriels()) {
                	    			materiel.setSupplyNamesString((StringUtil.isEmpty(materiel.getSupplyNamesString())?"":(materiel.getSupplyNamesString()+","))+supplyMateriel2.getSupply().getComName());
        						}
                			}
                			
                	    	if(!CollectionUtils.isEmpty(materiel.getBuyMateriels())){
                	    		for (BuyMateriel buyMateriel2 : materiel.getBuyMateriels()) {
                	    			materiel.setBuyNamesString((StringUtil.isEmpty(materiel.getBuyNamesString())?"":(materiel.getBuyNamesString()+","))+buyMateriel2.getBuy().getComName());
        						}
                	    	}
        				}
                	}
            	}else{//根据父节点查询
            		
            		Category category = this.categoryService.selectById(parent);
            		
            		//and 条件1
                	com.congmai.zhgj.web.model.MaterielSelectExample.Criteria criteria =  m.createCriteria();
                	criteria.andIsLatestVersionEqualTo("1");
                	criteria.andDelFlgEqualTo("0");
                	if(category!=null){
                		if("1".equals(category.getLevel())){
                			criteria.andTypeEqualTo(parent);
                		}else if("2".equals(category.getLevel())){
                			criteria.andCategory1EqualTo(parent);
                		}else if("3".equals(category.getLevel())){
                			criteria.andCategory2EqualTo(parent);
                		}else if("4".equals(category.getLevel())){
                			criteria.andCategory3EqualTo(parent);
                		}
                	}
                	//and 条件2
                	com.congmai.zhgj.web.model.MaterielSelectExample.Criteria criteria2 =  m.createCriteria();
                	criteria2.andStatusEqualTo("0");
                	criteria2.andDelFlgEqualTo("0");
                	if(category!=null){
                		if("1".equals(category.getLevel())){
                			criteria2.andTypeEqualTo(parent);
                		}else if("2".equals(category.getLevel())){
                			criteria2.andCategory1EqualTo(parent);
                		}else if("3".equals(category.getLevel())){
                			criteria2.andCategory2EqualTo(parent);
                		}else if("4".equals(category.getLevel())){
                			criteria2.andCategory3EqualTo(parent);
                		}
                	}
                	//or 条件
                	m.or(criteria2);
                	//排序字段
                	m.setOrderByClause("materielNum DESC");
                	if(company!=null&&ComType.SUPPLIER.getValue().equals(company.getComType())){
                		criteria.andSupplyComIdIn(comIds);
                	}else if(company!=null&&ComType.BUYER.getValue().equals(company.getComType())){
                		criteria.andBuyComIdIn(comIds);
                	}else if(company!=null&&ComType.TRAFFICKER.getValue().equals(company.getComType())){
                		criteria.andBuyComIdIn(comIds);
                		com.congmai.zhgj.web.model.MaterielSelectExample.Criteria criteria3 =  m.createCriteria();
                		criteria3.andIsLatestVersionEqualTo("1");
                		criteria3.andDelFlgEqualTo("0");
                		criteria3.andSupplyComIdIn(comIds);
                		m.or(criteria3);
                	}
                	
                	if(params!=null&&!"null".equals(params)){
                		m.setSearchStr(params);
                	}
                	
                	materielList = materielService.selectList4Export(m);
                	
                	//拼接供应商采购商名称
                	if(!CollectionUtils.isEmpty(materielList)){
                		for (Materiel materiel : materielList) {
                			if(!CollectionUtils.isEmpty(materiel.getSupplyMateriels())){
                	    		for (SupplyMateriel supplyMateriel2 : materiel.getSupplyMateriels()) {
                	    			materiel.setSupplyNamesString((StringUtil.isEmpty(materiel.getSupplyNamesString())?"":(materiel.getSupplyNamesString()+","))+supplyMateriel2.getSupply().getComName());
        						}
                			}
                			
                	    	if(!CollectionUtils.isEmpty(materiel.getBuyMateriels())){
                	    		for (BuyMateriel buyMateriel2 : materiel.getBuyMateriels()) {
                	    			materiel.setBuyNamesString((StringUtil.isEmpty(materiel.getBuyNamesString())?"":(materiel.getBuyNamesString()+","))+buyMateriel2.getBuy().getComName());
        						}
                	    	}
        				}
                	}
                	//查询下级物料
                	/*findChildList(parent,materielList);*/
            	}
			}else{
				List<String> idList = ApplicationUtils.getIdList(serialNums);
    			for(String id:idList){
    				materielList.add(materielService.selectById(id));
    			}
    			
    			//按选择项的导出,拼接供应商采购商名称
    			if(!CollectionUtils.isEmpty(materielList)){
            		for (Materiel materiel : materielList) {
            			if(!CollectionUtils.isEmpty(materiel.getSupplyMateriels())){
            	    		for (SupplyMateriel supplyMateriel2 : materiel.getSupplyMateriels()) {
            	    			materiel.setSupplyNamesString((StringUtil.isEmpty(materiel.getSupplyNamesString())?"":(materiel.getSupplyNamesString()+","))+supplyMateriel2.getSupply().getComName());
    						}
            			}
            			
            			
            			BuyMaterielExample m3 =new BuyMaterielExample();
            	    	com.congmai.zhgj.web.model.BuyMaterielExample.Criteria criteria3 =  m3.createCriteria();
            	    	criteria3.andMaterielIdEqualTo(materiel.getMaterielId());
            	    	criteria3.andDelFlgEqualTo("0");
            	    	List<BuyMateriel> buyMateriel = buyMaterielService.selectList(m3);
            	    	if(!CollectionUtils.isEmpty(buyMateriel)){
            	    		for (BuyMateriel buyMateriel2 : buyMateriel) {
            	    			materiel.setBuyNamesString((StringUtil.isEmpty(materiel.getBuyNamesString())?"":(materiel.getBuyNamesString()+","))+buyMateriel2.getBuy().getComName());
    						}
            	    	}
    				}
            	}
			}
        	
        	
        	

    		dataMap.put("materielList",materielList);
    		ExcelUtil.export(request, response, dataMap, "materiel", "物料信息");
    }
    
    /**
     * @Description (下载导入模板)
     * @param request
     * @return
     */
    @RequestMapping("downloadImportTemp")
    public void downloadCompanyTemp(Map<String, Object> map,HttpServletRequest request,HttpServletResponse response) {
    	ExcelUtil.importTempDownLoad(request, response, "materiel");
    }
    
    /**
     * @Description (物料信息导入)
     * @param request
     * @return
     */
    @RequestMapping("materielImport")
    @ResponseBody
    public Map<String,String> materielImport(@RequestParam(value = "excelFile") MultipartFile excelFile,HttpServletRequest request,HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String, String>();
    	 try {
			ExcelReader excelReader = new ExcelReader(excelFile.getInputStream());
			List<Materiel> materielList = new ArrayList<Materiel>(); 
			
			List<SupplyMateriel> supplyMaterielList = new ArrayList<SupplyMateriel>(); 
			List<BuyMateriel> buyMaterielList = new ArrayList<BuyMateriel>(); 
			
			String comNumCodeString = orderService.getNumCode("C");
			
//			Set<String> newSupplyString = new HashSet<String>(); //新增的供应商名称集合
			
			List<Company> newSupply = new ArrayList<Company>(); //新增的供应商集合
			
			List<Company> oldSupply = companyService.selectCompanyByComType(ComType.SUPPLIER.getValue(), null); //已有的供应商集合
			
//			Set<String> newBuyString = new HashSet<String>(); //新增的采购商名称集合
			
			List<Company> newBuy = new ArrayList<Company>(); //新增的采购商集合
			
			List<Company> oldBuy = companyService.selectCompanyByComType(ComType.BUYER.getValue(), null); //已有的采购商集合
			
			List<Company> updateCompanyList = new ArrayList<Company>(); //需要修改为贸易商的公司
					
			excelReader.readExcelContent(new RowHandler() {
				@SuppressWarnings("serial")
				class MyException extends Exception{
				    @SuppressWarnings("unused")
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
							Materiel materiel = new Materiel();
							
							materiel.setSerialNum(ApplicationUtils.random32UUID());
							materiel.setMaterielId(ApplicationUtils.random32UUID());
							materiel.setMaterielNum(StringUtil.rowCell2String(row,0));
							if (StringUtils.isNotEmpty(materiel.getMaterielNum())) {
								if(orderService.isExist("materiel",materiel.getMaterielNum(),null)){
									throw new MyException("物料编号已存在！");
								}
							}else {
								throw new MyException("物料编号不能为空！");
							}
							materiel.setMnemonicCode(StringUtil.rowCell2String(row,1));
							materiel.setMaterielName(StringUtil.rowCell2String(row,2));
							materiel.setSpecifications(StringUtil.rowCell2String(row,3));
							materiel.setType(StringUtil.rowCell2String(row,4));
							materiel.setCategory1(StringUtil.rowCell2String(row,5));
							materiel.setCategory2(StringUtil.rowCell2String(row,6));
							materiel.setCategory3(StringUtil.rowCell2String(row,7));
							//分类为空，设置为其他分类，如果没有其他分类，新增其他分类并设置
							if(StringUtil.isEmpty(materiel.getType())){
								materiel.setType(setOrCreateCategoryId("0","其他","1"));
//								List<Category> fristCategoryList = categoryService.queryCategoryListByParent("0");
//								//查找分类名是否已在分类中，存在返回分类
//								Category category = nameInCategory("其他",fristCategoryList);
//								if(category!=null){
//									materiel.setType(category.getCategoryId());
//								}else {
//									category = new Category();
//									category.setCategoryName("其他");
//									category.setLevel("1");
//									category.setParentId("0");
//									category = saveCategory(category);
//									materiel.setType(category.getCategoryId());
//								}
							}else {
								materiel.setType(setOrCreateCategoryId("0",materiel.getType(),"1"));
							}

							if(StringUtil.isEmpty(materiel.getCategory1())){
								materiel.setCategory1(setOrCreateCategoryId(materiel.getType(),"其他","2"));
							}else {
								materiel.setCategory1(setOrCreateCategoryId(materiel.getType(),materiel.getCategory1(),"2"));
							}
							if(StringUtil.isEmpty(materiel.getCategory2())){
								materiel.setCategory2(setOrCreateCategoryId(materiel.getCategory1(),"其他","3"));
							}else {
								materiel.setCategory2(setOrCreateCategoryId(materiel.getCategory1(),materiel.getCategory2(),"3"));
							}
							if(StringUtil.isEmpty(materiel.getCategory3())){
								materiel.setCategory3(setOrCreateCategoryId(materiel.getCategory2(),"其他","4"));
							}else {
								materiel.setCategory3(setOrCreateCategoryId(materiel.getCategory2(),materiel.getCategory3(),"4"));
							}
							
							materiel.setUnit(StringUtil.rowCell2String(row,9));
							if (StringUtils.isEmpty(materiel.getUnit())) {
								throw new MyException("物料单位不能为空！");
							}
							materiel.setBrand(StringUtil.rowCell2String(row,10));
							materiel.setOriginCountry(StringUtil.rowCell2String(row,11));
							materiel.setLength(StringUtil.rowCell2String(row,12));
							materiel.setWidth(StringUtil.rowCell2String(row,13));
							materiel.setHeight(StringUtil.rowCell2String(row,14));
							materiel.setCurrency(StringUtil.rowCell2String(row,15));
							materiel.setUnitPrice(StringUtil.rowCell2String(row,16));
							materiel.setFilingItemNo(StringUtil.rowCell2String(row,17));
							materiel.setVolume(StringUtil.rowCell2String(row,18));
							materiel.setWeight(StringUtil.rowCell2String(row,19));
							materiel.setPalletSpecification(StringUtil.rowCell2String(row,20));
							materiel.setPalletWeight(StringUtil.rowCell2String(row,21));
							materiel.setCustomsSupervisionConditions(StringUtil.rowCell2String(row,22));
							materiel.setCustomsCode(StringUtil.rowCell2String(row,23));
							materiel.setQualityDate(StringUtil.rowCell2String(row,24));
							materiel.setDeliveryCycle(StringUtil.rowCell2String(row,25));
							materiel.setRemark(StringUtil.rowCell2String(row,26));
							
							materiel.setSupplyNamesString(StringUtil.rowCell2String(row,27));
							materiel.setBuyNamesString(StringUtil.rowCell2String(row,28));
							
							//构造供应商物料
							if (StringUtil.isNotEmpty(materiel.getSupplyNamesString())) {
								String[] nameList = materiel.getSupplyNamesString().split(",");
								if (nameList!=null&&nameList.length>0) {
									for (String name : nameList) {
										//查找公司名是否已在供应商中，存在返回供应商
										Company company = nameInOldSupply(name,oldSupply);
										if (company!=null) {
											//构造供应商物料
											SupplyMateriel supplyMateriel = createSupplyMateriel(materiel, company);
											supplyMaterielList.add(supplyMateriel);
										}else {
											//查找公司名是否已在采购商中，存在返回采购商，并设置公司类型为贸易商，加入需修改list
											company = nameInOldBuy(name,oldBuy);
											if (company!=null) {
												company.setComType(ComType.TRAFFICKER.getValue());
												updateCompanyList.add(company);
												//构造供应商物料
												SupplyMateriel supplyMateriel = createSupplyMateriel(materiel, company);
												supplyMaterielList.add(supplyMateriel);
											}else{
												//查找公司名是否已在新的供应商名中，存在返回新的供应商
												company = nameInNewSupply(name,newSupply);
												if (company!=null) {
													//构造供应商物料
													SupplyMateriel supplyMateriel = createSupplyMateriel(materiel, company);
													supplyMaterielList.add(supplyMateriel);
												}else {
													//查找公司名是否已在新的采购商名中，存在返回新的采购商，设置公司类型为贸易商
													company = nameInNewBuy(name,newBuy);
													if (company!=null) {
														company.setComType(ComType.TRAFFICKER.getValue());
														//构造供应商物料
														SupplyMateriel supplyMateriel = createSupplyMateriel(materiel, company);
														supplyMaterielList.add(supplyMateriel);
													}else {
														//不存在新增供应商到新的供应商中
														company = new Company();
														company.setComId(ApplicationUtils.random32UUID());
														company.setComName(name);
														company.setComNum(comNumCodeString+((newSupply.size()+newBuy.size()==0)?"":(newSupply.size()+newBuy.size())));
														company.setComType(ComType.SUPPLIER.getValue());
														
														newSupply.add(company);
														//构造供应商物料
														SupplyMateriel supplyMateriel = createSupplyMateriel(materiel, company);
														supplyMaterielList.add(supplyMateriel);
													}
												}
											}
											
										}
									}
								}
							}
							
							//构造采购商物料
							if (StringUtil.isNotEmpty(materiel.getBuyNamesString())) {
								String[] nameList = materiel.getBuyNamesString().split(",");
								if (nameList!=null&&nameList.length>0) {
									for (String name : nameList) {
										//查找公司名是否已在采购商中，存在返回采购商
										Company company = nameInOldBuy(name,oldBuy);
										if (company!=null) {
											//构造采购商物料
											BuyMateriel BuyMateriel = createBuyMateriel(materiel, company);
											buyMaterielList.add(BuyMateriel);
										}else {
											//查找公司名是否已在供应商中，存在返回供应商，并设置公司类型为贸易商，加入需修改list
											company = nameInOldSupply(name,oldSupply);
											if (company!=null) {
												company.setComType(ComType.TRAFFICKER.getValue());
												updateCompanyList.add(company);
												//构造采购商物料
												BuyMateriel BuyMateriel = createBuyMateriel(materiel, company);
												buyMaterielList.add(BuyMateriel);
											}else{
												//查找公司名是否已在新的采购商名中，存在返回新的采购商
												company = nameInNewBuy(name,newBuy);
												if (company!=null) {
													//构造采购商物料
													BuyMateriel BuyMateriel = createBuyMateriel(materiel, company);
													buyMaterielList.add(BuyMateriel);
												}else {
													//查找公司名是否已在新的供应商名中，存在返回新的供应商，设置公司类型为贸易商
													company = nameInNewSupply(name,newSupply);
													if (company!=null) {
														company.setComType(ComType.TRAFFICKER.getValue());
														//构造采购商物料
														BuyMateriel BuyMateriel = createBuyMateriel(materiel, company);
														buyMaterielList.add(BuyMateriel);
													}else {
														//不存在新增采购商到新的采购商中
														company = new Company();
														company.setComId(ApplicationUtils.random32UUID());
														company.setComNum(comNumCodeString+((newSupply.size()+newBuy.size()==0)?"":(newSupply.size()+newBuy.size())));
														company.setComName(name);
														company.setComType(ComType.BUYER.getValue());
														
														newBuy.add(company);
														//构造采购商物料
														BuyMateriel BuyMateriel = createBuyMateriel(materiel, company);
														buyMaterielList.add(BuyMateriel);
													}
												}
											}
										}
									}
								}
							}

							/*insertNew(materiel);*/
							materielList.add(materiel);
						}catch(MyException  e){
							throw new Exception("第"+(i+1)+"行数据异常请检查，数据内容："+e.getMessage());
						}catch(Exception  e){
							throw new Exception("第"+(i+1)+"行数据转换错误！");
						}
						
					}
					
				}

				private SupplyMateriel createSupplyMateriel(Materiel materiel,
						Company company) {
					SupplyMateriel supplyMateriel = new SupplyMateriel();
					supplyMateriel.setSerialNum(ApplicationUtils.random32UUID());
					supplyMateriel.setSerialNum(ApplicationUtils.random32UUID());
					supplyMateriel.setMaterielId(materiel.getMaterielId());
					supplyMateriel.setSupplyComId(company.getComId());
					supplyMateriel.setSupplyMaterielNum(materiel.getMaterielNum());
					supplyMateriel.setMaterielName(materiel.getMaterielName());
					supplyMateriel.setSpecifications(materiel.getSpecifications());
					supplyMateriel.setUnit(materiel.getUnit());
					supplyMateriel.setType(materiel.getType());
					supplyMateriel.setCategory1(materiel.getCategory1());
					supplyMateriel.setCategory2(materiel.getCategory2());
					supplyMateriel.setCategory3(materiel.getCategory3());
					Subject currentUser = SecurityUtils.getSubject();
					String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
					supplyMateriel.setCreator(currenLoginName);
					supplyMateriel.setUpdater(currenLoginName);
					supplyMateriel.setCreateTime(new Date());
					supplyMateriel.setUpdateTime(new Date());
					return supplyMateriel;
				}
				
				private BuyMateriel createBuyMateriel(Materiel materiel,
						Company company) {
					BuyMateriel buyMateriel = new BuyMateriel();
					buyMateriel.setSerialNum(ApplicationUtils.random32UUID());
					buyMateriel.setSerialNum(ApplicationUtils.random32UUID());
					buyMateriel.setMaterielId(materiel.getMaterielId());
					buyMateriel.setBuyComId(company.getComId());
					buyMateriel.setBuyMaterielNum(materiel.getMaterielNum());
					buyMateriel.setMaterielName(materiel.getMaterielName());
					buyMateriel.setSpecifications(materiel.getSpecifications());
					buyMateriel.setUnit(materiel.getUnit());
					buyMateriel.setType(materiel.getType());
					buyMateriel.setCategory1(materiel.getCategory1());
					buyMateriel.setCategory2(materiel.getCategory2());
					buyMateriel.setCategory3(materiel.getCategory3());
					Subject currentUser = SecurityUtils.getSubject();
					String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
					buyMateriel.setCreator(currenLoginName);
					buyMateriel.setUpdater(currenLoginName);
					buyMateriel.setCreateTime(new Date());
					buyMateriel.setUpdateTime(new Date());
					return buyMateriel;
				}
			}, 2);
			
			//插入物料信息
			for (Materiel materiel : materielList) {
				insertNew(materiel);
			}
			
			//插入新增的企业信息
			for (Company com : newSupply) {
				companyService.insert(com);
			}
			
			//插入新增的企业信息
			for (Company com : newBuy) {
				companyService.insert(com);
			}
			
			//更新企业信息
			for (Company com : updateCompanyList) {
				companyService.update(com);
			}
			
			//插入新增的供应商物料
			for (SupplyMateriel supplyMateriel : supplyMaterielList) {
				supplyMaterielService.insert(supplyMateriel);
			}
			
			//插入新增的采购商物料
			for (BuyMateriel buyMateriel : buyMaterielList) {
				buyMaterielService.insert(buyMateriel);
			}
			
			map.put("data", "success");
		} catch (Exception e1) {
			map.put("data", e1.getMessage());
		}
    	
         return map;
    }
    
    
    protected Company nameInNewSupply(String name, List<Company> newSupply) {
    	if(CollectionUtils.isEmpty(newSupply)){
			return null;
		}else {
			for (Company company : newSupply) {
				if(name.equals(company.getComName())){
					return company;
				}
			}
		}
		return null;
	}
    

    protected Company nameInOldSupply(String name,
			List<Company> oldSupply) {
		if(CollectionUtils.isEmpty(oldSupply)){
			return null;
		}else {
			for (Company company : oldSupply) {
				if(name.equals(company.getComName())){
					return company;
				}
			}
		}
		return null;
	}
    
    
    protected Company nameInNewBuy(String name, List<Company> newBuy) {
    	if(CollectionUtils.isEmpty(newBuy)){
			return null;
		}else {
			for (Company company : newBuy) {
				if(name.equals(company.getComName())){
					return company;
				}
			}
		}
		return null;
	}

    protected Company nameInOldBuy(String name,
			List<Company> oldBuy) {
		if(CollectionUtils.isEmpty(oldBuy)){
			return null;
		}else {
			for (Company company : oldBuy) {
				if(name.equals(company.getComName())){
					return company;
				}
			}
		}
		return null;
	}
    /**
     * 
     * @Description (物料类型名称转换为类型id，类型已存在直接返回，不存在新建后返回)
     * @param materiel
     * @param parent
     * @param name
     * @param level
     */
    private String setOrCreateCategoryId(String parent,String name,String level) {
		List<Category> categoryList = categoryService.queryCategoryListByParent(parent);
		//查找分类名是否已在分类中，存在返回分类
		Category category = nameInCategory(name,categoryList);
		if(category!=null){
			return category.getCategoryId();
		}else {
			category = new Category();
			category.setCategoryName(name);
			category.setLevel(level);
			category.setParentId(parent);
			category = saveCategory(category);
			return category.getCategoryId();
		}
	}
	
	private Category nameInCategory(String name,
			List<Category> categoryList) {
		if(CollectionUtils.isEmpty(categoryList)){
			return null;
		}else {
			for (Category category : categoryList) {
				if(name.equals(category.getCategoryName())){
					return category;
				}
			}
		}
		return null;
	}
    /**
     * 
     * @Description (选择的供应物料)
     * @param ids
     * @return
     */
    @RequestMapping(value="chooseMateriel",method=RequestMethod.POST)
    @ResponseBody
    public List<SupplyMateriel> chooseMateriel(@RequestBody String ids,String comId,String comType){
    	List<SupplyMateriel> list = null;
    	try {
    		list = supplyMaterielService.chooseMateriel(ids);
    		if(list!=null){
    			for (int i = 0; i < list.size(); i++) {//设置所选基本物料的自建库存数量
    				if(list.get(i).getMateriel()!=null){
    					Materiel m = list.get(i).getMateriel();
    					String inCountString = stockService.getCountInAmountForZijian(m.getSerialNum());
        				String outCountString = stockService.getCountOutAmountForZijian(m.getSerialNum());
        				m.setStockCount(
        						(Integer.parseInt(inCountString==null?"0":inCountString)
        								-Integer.parseInt(outCountString==null?"0":outCountString))+"");
        				
        				if(StringUtil.isNotEmpty(comId)&&StringUtil.isNotEmpty(comType)){
        					//首先查询当前有效的价格目录
            				List<PriceList> priceLists = priceListService.selectCurrentPriceList(m,comId,comType);
            				if (!CollectionUtils.isEmpty(priceLists)) {
            					m.setUnitPrice(priceLists.get(0).getInclusivePrice());
    						}
        				}
    					
    				}
    				
					
				}
    		}
		} catch (Exception e) {
			//20180110 qhzhao System.out.println(e.getMessage());
		}
    	
    	return list;
    }
    
    
    /**
     * 
     * @Description (选择的标准物料)
     * @param ids
     * @return
     */
    @RequestMapping(value="chooseBasicMateriels")
    @ResponseBody
    public List<Materiel> chooseBasicMateriels(String ids,String comId,String comType){
    	List<Materiel> list = null;
    	try {
    		list = materielService.chooseMateriel(ids);
    		if(list!=null){
    			for (int i = 0; i < list.size(); i++) {//设置所选物料的自建库存数量
    				String inCountString = stockService.getCountInAmountForZijian(list.get(i).getSerialNum());
    				String outCountString = stockService.getCountOutAmountForZijian(list.get(i).getSerialNum());
    				list.get(i).setStockCount(
    						(Integer.parseInt(inCountString==null?"0":inCountString)
    								-Integer.parseInt(outCountString==null?"0":outCountString))+"");
    				
    				if(StringUtil.isNotEmpty(comId)&&StringUtil.isNotEmpty(comType)){
    					//首先查询当前有效的价格目录
        				List<PriceList> priceLists = priceListService.selectCurrentPriceList(list.get(i),comId,comType);
        				if (!CollectionUtils.isEmpty(priceLists)) {
        					list.get(i).setUnitPrice(priceLists.get(0).getInclusivePrice());
						}
    				}
					
				}
    		}
    		
    		
		} catch (Exception e) {
			//20180110 qhzhao System.out.println(e.getMessage());
		}
    	
    	return list;
    }
    
    
    
	/**
	 * 
	 * @Description 初始化物料分类
	 * @return
	 */
	@RequestMapping(value = "/getCategoryList")
	@ResponseBody
	public Map getCategoryList() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Category> fristCategoryList = this.categoryService.queryCategoryListByParent("0");
		map.put("fristCategoryList", fristCategoryList);
		if(fristCategoryList!=null&&fristCategoryList.size()>0){
			List<Category> secondCategoryList = this.categoryService.queryCategoryListByParent(fristCategoryList.get(0).getCategoryId());
			map.put("secondCategoryList", secondCategoryList);
			if(secondCategoryList!=null&&secondCategoryList.size()>0){
				List<Category> thirdCategoryList = this.categoryService.queryCategoryListByParent(secondCategoryList.get(0).getCategoryId());
				map.put("thirdCategoryList", thirdCategoryList);
				if(thirdCategoryList!=null&&thirdCategoryList.size()>0){
					List<Category> fourthCategoryList = this.categoryService.queryCategoryListByParent(thirdCategoryList.get(0).getCategoryId());
					map.put("fourthCategoryList", fourthCategoryList);
				}
			}
		}
		/*List<CompanyCode> functionTypeList = sysMyTagService.selectTypeList("functionType");*/
		return map;
	}
	
	/**
	 * 
	 * @return 
	 * @Description 查询下级分类
	 * @return
	 */
	@RequestMapping(value = "/queryCategoryListByParent")
	@ResponseBody
	public List<Category> queryCategoryListByParent(String parentId) {
		List<Category> list = this.categoryService.queryCategoryListByParent(parentId);
		return list;
	}
	
	
	@RequestMapping(value = "/saveCategory", method = RequestMethod.POST)
    @ResponseBody
	public Category saveCategory(@RequestBody Category category) {
		Integer list = (Integer)this.categoryService.selectMaxSortByParentId(category.getParentId());
		if(list==null||list==0){
			category.setSort(1);
		}else{
			category.setSort(list+1);
		}
		category.setCategoryId(ApplicationUtils.random32UUID());
		category.setLevel(category.getLevel());
		category.setParentId(category.getParentId());
		category.setCategoryName(category.getCategoryName());
		
		Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
		category.setCreater(currenLoginName);
		category.setUpdater(currenLoginName);
		category.setCreateTime(new Date());
		category.setUpdateTime(new Date());
		this.categoryService.insert(category);
		return category;
	}
	@RequestMapping(value = "/deleteCategory", method = RequestMethod.POST)
    @ResponseBody
	public Category deleteCategory(@RequestBody Category category) {
		Category c = new Category();
		c.setCategoryId(category.getCategoryId());
		c.setDelFlg("1");
		c.setUpdateTime(new Date());
		Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
    	c.setUpdater(currenLoginName);
    	categoryService.update(c);
    	return category;
		
	}
}