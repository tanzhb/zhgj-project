package com.congmai.zhgj.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.web.model.BOMMateriel;
import com.congmai.zhgj.web.model.BOMMaterielExample;
import com.congmai.zhgj.web.model.CompanyQualification;
import com.congmai.zhgj.web.model.JsonTreeData;
import com.congmai.zhgj.web.model.Materiel;
import com.congmai.zhgj.web.model.MaterielExample;
import com.congmai.zhgj.web.model.MaterielExample.Criteria;
import com.congmai.zhgj.web.model.User;
import com.congmai.zhgj.web.service.MaterielService;
import com.congmai.zhgj.web.service.UserService;

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

    /**
     * 保存物料
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Materiel save(@RequestBody Materiel materiel, HttpServletRequest request) {
    	if(materiel.getSerialNum()==null||materiel.getSerialNum().isEmpty()){//新增
    		insertNew(materiel, request);
    	}else{//编辑升级
    		updateVersion(materiel, request);
    	}
    	return materiel;
    }
    
    
    @RequestMapping(value = "/saveBOM", method = RequestMethod.POST)
    @ResponseBody
    public Map saveBOM(@RequestBody String params, HttpServletRequest request) {
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
		    		createNewVersion(materiel, request);
		    	}
	    		//生成新版本物料******↑↑↑↑↑↑********
		    	User user = (User) request.getSession().getAttribute("userInfo");
		    	for(BOMMateriel b:BOM){
		    		b.setSerialNum(ApplicationUtils.random32UUID());
		    		b.setBomMaterielSerial(materiel.getSerialNum());
		    		if(user!=null){
		    			b.setCreator(user.getId().toString());
		    			b.setUpdater(user.getId().toString());
		    		}
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
	 * @Description 升级版本
	 * @param materiel
	 * @param request
	 */
	private void updateVersion(Materiel materiel, HttpServletRequest request) {
		createNewVersion(materiel, request);
		
		materielService.updateVersion(materiel);
	}


	/**
	 * @Description 构造新版本物料
	 * @param materiel
	 * @param request
	 */
	private void createNewVersion(Materiel materiel, HttpServletRequest request) {
		materiel.setSerialNum(ApplicationUtils.random32UUID());
		User user = (User) request.getSession().getAttribute("userInfo");
		if(user!=null){
			materiel.setCreator(user.getId().toString());
			materiel.setUpdater(user.getId().toString());
		}
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
	 * @param request
	 */
	private void insertNew(Materiel materiel, HttpServletRequest request) {
		materiel.setSerialNum(ApplicationUtils.random32UUID());
		materiel.setMaterielId(ApplicationUtils.random32UUID());
		User user = (User) request.getSession().getAttribute("userInfo");
		if(user!=null){
			materiel.setCreator(user.getId().toString());
			materiel.setUpdater(user.getId().toString());
		}
		materiel.setCreateTime(new Date());
		materiel.setUpdateTime(new Date());
		materiel.setIsLatestVersion("1");
		materiel.setVersionNO("1");
		materiel.setStatus("1");
		
/*		if("true".equals(materiel.getIsBOM())){
			materiel.setIsBOM("1");
		}else{
			materiel.setIsBOM("0");
		}*/
		
		materielService.insert(materiel);
	}

    /**
     * 
     * @Description 查询物料列表//全部查询，或根据父节点查询
     * @param parent(若有值，则查询它及上级物料是它的物料)
     * @param isLatestVersion(若有值为1，则查询所以已发布的正式物料)
     * @return
     */
    @RequestMapping("/findMaterielList")
    @ResponseBody
    public ResponseEntity<Map> findMaterielList(String parent,String isLatestVersion) {
    	MaterielExample m =new MaterielExample();
    	List<Materiel> materielList = new ArrayList<Materiel>();
    	if(parent==null||parent.isEmpty()){//查询全部物料
    		//and 条件1
        	Criteria criteria =  m.createCriteria();
        	criteria.andIsLatestVersionEqualTo("1");
        	criteria.andDelFlgEqualTo("0");
        	if("1".equals(isLatestVersion)){
        	}else{
        		//and 条件2,未发布可编辑的物料
            	Criteria criteria2 =  m.createCriteria();
            	criteria2.andStatusEqualTo("0");
            	criteria2.andDelFlgEqualTo("0");
            	//or 条件
            	m.or(criteria2);
        	}
        	//排序字段
        	m.setOrderByClause("updateTime DESC");
        	materielList = materielService.selectList(m);
    	}else{//根据父节点查询
    		//and 条件1
        	Criteria criteria =  m.createCriteria();
        	criteria.andIsLatestVersionEqualTo("1");
        	criteria.andDelFlgEqualTo("0");
        	criteria.andSerialNumEqualTo(parent);
        	//and 条件2
        	Criteria criteria2 =  m.createCriteria();
        	criteria2.andStatusEqualTo("0");
        	criteria2.andDelFlgEqualTo("0");
        	criteria2.andSerialNumEqualTo(parent);
        	//or 条件
        	m.or(criteria2);
        	//排序字段
        	m.setOrderByClause("updateTime DESC");
        	materielList = materielService.selectList(m);
        	
        	//查询下级物料
        	findChildList(parent,materielList);
    	}
    	
    	if (materielList==null||materielList.isEmpty()) {
			return new ResponseEntity<Map>(HttpStatus.NO_CONTENT);
		}
    	//封装datatables数据返回到前台
		Map pageMap = new HashMap();
		pageMap.put("draw", 1);
		pageMap.put("recordsTotal", materielList.size());
		pageMap.put("recordsFiltered", materielList.size());
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
	 * @Description 批量删除物料
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/deleteMateriels", method = RequestMethod.POST)
	public ResponseEntity<Void> deleteMateriels(@RequestBody String ids, HttpServletRequest request) {
		if ("".equals(ids) || ids == null) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		List<String> idList = ApplicationUtils.getIdList(ids);
		for(String id : idList){
			Materiel m = new Materiel();
			m.setSerialNum(id);
			m.setDelFlg("1");
			m.setUpdateTime(new Date());
			User user = (User) request.getSession().getAttribute("userInfo");
	    	if(user!=null){
	    		m.setUpdater(user.getId().toString());
	    	}
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
    	
    	return map;
	}
    
	
    
    
    
}