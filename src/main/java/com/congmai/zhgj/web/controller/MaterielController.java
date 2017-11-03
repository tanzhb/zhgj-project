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
import com.congmai.zhgj.core.util.UserUtil;
import com.congmai.zhgj.core.util.ExcelReader.RowHandler;
import com.congmai.zhgj.web.enums.ComType;
import com.congmai.zhgj.web.model.BOMMateriel;
import com.congmai.zhgj.web.model.BOMMaterielExample;
import com.congmai.zhgj.web.model.BuyMateriel;
import com.congmai.zhgj.web.model.BuyMaterielExample;
import com.congmai.zhgj.web.model.Category;
import com.congmai.zhgj.web.model.Company;
import com.congmai.zhgj.web.model.CompanyCode;
import com.congmai.zhgj.web.model.JsonTreeData;
import com.congmai.zhgj.web.model.Materiel;
import com.congmai.zhgj.web.model.MaterielExample;
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
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, BOMMateriel.class);  
        List<BOMMateriel> BOM;
		try {
			BOM = objectMapper.readValue(params, javaType);
			Materiel materiel = null;
	    	if(!CollectionUtils.isEmpty(BOM)){
	    		materiel = materielService.selectById(BOM.get(0).getBomMaterielSerial());
	    		if(materiel!=null){
		    		createNewVersion(materiel);
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
		    	for(SupplyMateriel f:supplyMateriel){
		    		f.setSerialNum(ApplicationUtils.random32UUID());
		    		f.setCreator(currenLoginName);
	    			f.setUpdater(currenLoginName);
	    			f.setCreateTime(new Date());
	    			f.setUpdateTime(new Date());
		    	}
		    	//填充物料供应商******↑↑↑↑↑↑********
		    	supplyMaterielService.betchInsertSupplyMateriels(supplyMateriel);
		    	//数据插入******↑↑↑↑↑↑********
		    	
		    	SupplyMaterielExample m2 =new SupplyMaterielExample();
		    	com.congmai.zhgj.web.model.SupplyMaterielExample.Criteria criteria2 =  m2.createCriteria();
		    	criteria2.andMaterielIdEqualTo(supplyMateriel.get(0).getMaterielId());
		    	criteria2.andDelFlgEqualTo("0");
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
		    	for(BuyMateriel f:buyMateriel){
		    		f.setSerialNum(ApplicationUtils.random32UUID());
		    		f.setCreator(currenLoginName);
	    			f.setUpdater(currenLoginName);
	    			f.setCreateTime(new Date());
	    			f.setUpdateTime(new Date());
		    	}
		    	//填充物料供应商******↑↑↑↑↑↑********
		    	buyMaterielService.betchInsertBuyMateriels(buyMateriel);
		    	//数据插入******↑↑↑↑↑↑********
		    	
		    	BuyMaterielExample m2 =new BuyMaterielExample();
		    	com.congmai.zhgj.web.model.BuyMaterielExample.Criteria criteria2 =  m2.createCriteria();
		    	criteria2.andMaterielIdEqualTo(buyMateriel.get(0).getMaterielId());
		    	criteria2.andDelFlgEqualTo("0");
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
	 * @Description 构造新版本物料
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
		}
		materiel.setStatus("1");
	}
	/**
	 * 
	 * @Description 新增物料
	 * @param materiel
	 */
	private void insertNew(Materiel materiel) {
		materiel.setSerialNum(ApplicationUtils.random32UUID());
		materiel.setMaterielId(ApplicationUtils.random32UUID());
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
     * @param type
     * @return
     */
    @RequestMapping("/findMaterielList")
    @ResponseBody
    public ResponseEntity<Map> findMaterielList(String parent,String isLatestVersion,String type) {
    	//MaterielExample m =new MaterielExample();
    	MaterielSelectExample m =new MaterielSelectExample();
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
    	
    	if(parent==null||parent.isEmpty()){//查询全部物料
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
        	m.setOrderByClause("updateTime DESC");
        	if(company!=null&&ComType.SUPPLIER.getValue().equals(company.getComType())){
        		criteria.andSupplyComIdIn(comIds);
        	}else if(company!=null&&ComType.BUYER.getValue().equals(company.getComType())){
        		criteria.andBuyComIdIn(comIds);
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
        	m.setOrderByClause("updateTime DESC");
        	if(company!=null&&ComType.SUPPLIER.getValue().equals(company.getComType())){
        		criteria.andSupplyComIdIn(comIds);
        	}else if(company!=null&&ComType.BUYER.getValue().equals(company.getComType())){
        		criteria.andBuyComIdIn(comIds);
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
		pageMap.put("draw", 1);
		pageMap.put("recordsTotal", materielList==null?0:materielList.size());
		pageMap.put("recordsFiltered", materielList==null?0:materielList.size());
		pageMap.put("data", materielList);
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
    	
    	return map;
	}
    
    
	/**
     * @Description (导出物料信息)
     * @param request
     * @return
     */
    @RequestMapping("exportMateriel")
    public void exportMateriel(Map<String, Object> map,HttpServletRequest request,HttpServletResponse response) {
    		Map<String, Object> dataMap = new HashMap<String, Object>();
        	List<Materiel> materielList = new ArrayList<Materiel>();
        	//查询全部物料
        	MaterielExample m =new MaterielExample();
    		//and 条件1
        	Criteria criteria =  m.createCriteria();
        	criteria.andIsLatestVersionEqualTo("1");
        	criteria.andDelFlgEqualTo("0");
    		//and 条件2,未发布可编辑的物料
        	Criteria criteria2 =  m.createCriteria();
        	criteria2.andStatusEqualTo("0");
        	criteria2.andDelFlgEqualTo("0");
        	//or 条件
        	m.or(criteria2);
        	//排序字段
        	m.setOrderByClause("updateTime DESC");
        	materielList = materielService.selectList(m);
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
			excelReader.readExcelContent(new RowHandler() {
				@Override
				public void handle(List<Object> row,int i) throws Exception {
					if(!CollectionUtils.isEmpty(row)){
						try{
							Materiel materiel = new Materiel();

							materiel.setMaterielNum(row.get(0).toString());
							materiel.setMnemonicCode(row.get(1).toString());
							materiel.setType(row.get(2).toString());
							materiel.setMaterielName(row.get(3).toString());
							materiel.setCategory1(row.get(4).toString());
							materiel.setUnit(row.get(5).toString());
							materiel.setSpecifications(row.get(6).toString());
							materiel.setProductionPlace(row.get(7).toString());
							materiel.setStockUnit(row.get(8).toString());
							/*materiel.setparentMateriel(row.get(9).toString());*/
							materiel.setBrand(row.get(10).toString());
							materiel.setOriginCountry(row.get(11).toString());
							materiel.setLength(row.get(12).toString());
							materiel.setWidth(row.get(13).toString());
							materiel.setHeight(row.get(14).toString());
							materiel.setCurrency(row.get(15).toString());
							materiel.setUnitPrice(row.get(16).toString());
							materiel.setFilingItemNo(row.get(17).toString());
							materiel.setVolume(row.get(18).toString());
							materiel.setCustomsSupervisionConditions(row.get(19).toString());
							materiel.setWeight(row.get(20).toString());
							materiel.setQualityDate(row.get(21).toString());
							materiel.setPalletSpecification(row.get(22).toString());
							materiel.setManufactureDate(StringUtils.isEmpty(row.get(23).toString())?null:(Date) row.get(23));
							materiel.setPalletWeight(row.get(24).toString());
							materiel.setRemark(row.get(25).toString());

							insertNew(materiel);
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
    
    
    /**
     * 
     * @Description (选择的供应物料)
     * @param ids
     * @return
     */
    @RequestMapping(value="chooseMateriel",method=RequestMethod.POST)
    @ResponseBody
    public List<SupplyMateriel> chooseMateriel(@RequestBody String ids){
    	List<SupplyMateriel> list = null;
    	try {
    		list = supplyMaterielService.chooseMateriel(ids);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    	
    	return list;
    }
    
    
    /**
     * 
     * @Description (选择的标准物料)
     * @param ids
     * @return
     */
    @RequestMapping(value="chooseBasicMateriels",method=RequestMethod.POST)
    @ResponseBody
    public List<Materiel> chooseBasicMateriels(@RequestBody String ids){
    	List<Materiel> list = null;
    	try {
    		list = materielService.chooseMateriel(ids);
		} catch (Exception e) {
			System.out.println(e.getMessage());
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