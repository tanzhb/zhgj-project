package com.congmai.zhgj.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.impl.cmd.GetNextIdBlockCmd;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
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
import org.springframework.web.util.UriComponentsBuilder;

import com.alibaba.fastjson.JSON;
import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.core.util.ExcelReader;
import com.congmai.zhgj.core.util.StringUtil;
import com.congmai.zhgj.core.util.UserUtil;
import com.congmai.zhgj.core.util.ExcelReader.RowHandler;
import com.congmai.zhgj.core.util.ExcelUtil;
import com.congmai.zhgj.web.enums.StaticConst;
import com.congmai.zhgj.web.model.Company;
import com.congmai.zhgj.web.model.JsonTreeData;
import com.congmai.zhgj.web.model.LadderPrice;
import com.congmai.zhgj.web.model.OrderMateriel;
import com.congmai.zhgj.web.model.PriceList;
import com.congmai.zhgj.web.model.User;
import com.congmai.zhgj.web.model.Warehouse;
import com.congmai.zhgj.web.model.WarehouseExample;
import com.congmai.zhgj.web.model.WarehouseExample.Criteria;
import com.congmai.zhgj.web.model.Warehouseposition;
import com.congmai.zhgj.web.service.CompanyService;
import com.congmai.zhgj.web.service.OrderService;
import com.congmai.zhgj.web.service.UserCompanyService;
import com.congmai.zhgj.web.service.WarehouseService;
import com.congmai.zhgj.web.service.WarehousepositionService;


/**
 * @ClassName WareHouseController
 * @Description TODO   仓库控制器
 * @author zhaichao
 * @Date 2017年7月28日 下午3:31:35
 * @version 1.0.0
 */
@Controller
@RequestMapping(value = "/warehouse")
public class WareHouseController {

    @Resource
    private WarehouseService  warehouseService;
    @Resource
    private WarehousepositionService  warehousepositionService;
    @Resource
    private CompanyService  companyService;
    @Resource
    private UserCompanyService   userCompanyService;
    @Resource
    private OrderService   orderService;
    
    
    /**
     * 仓库信息列表展示
     * 
     * @param session
     * @return
     */
    @RequestMapping(value = "/viewWarehouseList")
    public String viewWareHouseList(HttpServletRequest request) {
        return "warehouse/wareHouseList";
    }
    
    /**
     * 仓库信息详情展示
     * 
     * @param session
     * @return
     */
    @RequestMapping(value = "/viewWarehouseInfo",method = RequestMethod.POST)
    public String wareHouseDetail( @RequestBody String serial) {
       //Warehouse warehouse=warehouseService.selectBySerialNum(serial);
        return "warehouse/wareHouseDetail";
    }
    /**
     * 	编辑仓库信息详情/新增仓库信息
     * 
     * @param session
     * @return
     */
    @RequestMapping(value = "/addOrEditWarehouseInfo")
    public String addOrEditWarehouseDetail( ) {
        return "warehouse/addOrEditWarehouseInfo";
    }
    /**
     * 保存仓库信息
     * 
     * @param session
     * @return
     */
    @RequestMapping(value = "/saveWarehouseInfo", method = RequestMethod.POST)
	public ResponseEntity<Warehouse> saveWarehouseDetail(@RequestBody  Warehouse warehouse,UriComponentsBuilder ucBuilder) {
    	try{
    		if(StringUtils.isEmpty(warehouse.getSerialNum())){
    			warehouse.setSerialNum(UUID.randomUUID().toString().replace("-",""));
    			warehouseService.insert(warehouse);
    		}else{
    			warehouseService.update(warehouse);
    		}
    	}catch(Exception e){
    		//20180110 qhzhao System.out.println(e.getMessage());
    	}
    	Company com =companyService.selectOne(warehouse.getOwner());
    	if(com!=null){
    		warehouse.setOwnerName(com.getComName());
    	}
		return new ResponseEntity<Warehouse>(warehouse, HttpStatus.OK);
    }
  
    
    /**
     * @Description (查看列表)
     * @param request
     * @return
     */
    @RequestMapping(value = "/getWarehouseList", method = RequestMethod.GET)
    public ResponseEntity<Map> getWarehouseList(HttpServletRequest request) {
    	Subject currentUser = SecurityUtils.getSubject();
    	User user = UserUtil.getUserFromSession();
    	String comId = null;
		comId = userCompanyService.getUserComId(String.valueOf(user.getUserId()));
		WarehouseExample  we=new WarehouseExample ();
		com.congmai.zhgj.web.model.WarehouseExample.Criteria c=we.createCriteria();
		if(comId!=null){
		 c.andOwnerEqualTo(comId);
		 c.andDelFlgEqualTo("0");
		}else{
			 c.andDelFlgEqualTo("0");
		}
		List<Warehouse> warehouses = warehouseService.selectWarehouseList(we);
		// 封装datatables数据返回到前台
		Map pageMap = new HashMap();
		pageMap.put("draw", 1);
		pageMap.put("recordsTotal", warehouses==null?0:warehouses.size());
		pageMap.put("recordsFiltered", warehouses==null?0:warehouses.size());
		pageMap.put("data", warehouses);
		for(Warehouse w:warehouses){
			Company com =companyService.selectOne(w.getOwner());
	    	if(com!=null){
	    		w.setOwnerName(com.getComName());
	    	}
		}
		return new ResponseEntity<Map>(pageMap, HttpStatus.OK);
	}
    /**
     * @Description (查看)
     * @param request
     * @return
     */
    @RequestMapping(value = "/viewWarehouseDetail", method = RequestMethod.POST)
    public ResponseEntity<Map> viewWarehouseDetail(@RequestBody String  serialNum) {
    	Map<String,Object> map = new HashMap<String,Object>();
    	Warehouse warehouse=warehouseService.selectOne(serialNum);
    	Company com =companyService.selectOne(warehouse.getOwner());
    	if(com!=null){
    		warehouse.setOwnerName(com.getComName());
    	}
    	map.put("warehouse", warehouse);
    	List<Warehouseposition>warehousepositionList=warehousepositionService.selectList(serialNum);
    	map.put("warehousepositions", warehousepositionList);
    	return new ResponseEntity<Map>(map, HttpStatus.OK);
    }
	
    /**
	 * 
	 * @Description 批量删除仓库
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/deleteWarehouse", method = RequestMethod.POST)
	public ResponseEntity<Void> deleteWarehouse(@RequestBody String ids) {
		if ("".equals(ids) || ids == null) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		warehouseService.deleteWarehouse(ids);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	   /**
     * @param oredCriteria 
     * @Description 查询仓库树
     * @param materiel
     * @return
     */
    @RequestMapping("/getWarehouseTree")
    @ResponseBody
    public List<JsonTreeData> getWarehouseTree(String parent) {
    	WarehouseExample we =new WarehouseExample();
    	//and 条件1
  Criteria criteria =  we.createCriteria();
    	criteria.andDelFlgEqualTo("0");
    	if(!StringUtils.isEmpty(parent)){
    	if(parent.indexOf("仓库")>-1){
    		criteria.andWarehouseTypeEqualTo(parent);
    	}else if(parent.indexOf("#")<0) {
    		criteria.andSerialNumEqualTo(parent);
    	}
    	}
    	//排序字段
    	/*we.setOrderByClause("updateTime DESC");*/
    	List<Warehouse> warehouselList = warehouseService.selectWarehouseList(we);
    	if (warehouselList.isEmpty()) {
			return new ArrayList<JsonTreeData>();
		}
    	List<JsonTreeData> treeDataList = new ArrayList<JsonTreeData>();
      for (Warehouse warehouse : warehouselList) {
           JsonTreeData treeData = new JsonTreeData();
           treeData.setId(warehouse.getSerialNum());
           treeData.setPid(warehouse.getWarehouseType());
           treeData.setText(warehouse.getWarehouseType());
           treeData.setChildren(false);
           treeData.setState("open");
           treeDataList.add(treeData);
       }
/*       //最后得到结果集,经过FirstJSON转换后就可得所需的json格式
       List<JsonTreeData> newTreeDataList = TreeNodeUtil.getfatherNode(treeDataList);*/
       return treeDataList;
    }
    
    /**
     * @Description (导出仓库信息)
     * @param request
     * @return
     */
    @RequestMapping("exportWarehouse")
    public void exportWarehouse(Map<String, Object> map,HttpServletRequest request,HttpServletResponse response,String serialNums) {
    		Map<String, Object> dataMap = new HashMap<String, Object>();
    		List<Warehouse> warehouseList = new ArrayList<Warehouse>();
    				warehouseService.selectList();
    		if(StringUtils.isEmpty(serialNums)){
    			warehouseList=warehouseService.selectList();
    		}else{
    			List<String> idList = ApplicationUtils.getIdList(serialNums);
    			for(String id:idList){
    				warehouseList	.add(warehouseService.selectOne(id));
    			}
    		}
    		
//    		List<Warehouse> warehouseList = warehouseService.selectList();
    		for(Warehouse w:warehouseList){
    		w.setOwner("pingtai".equals(w.getOwner())?StaticConst.getInfo("comName"):companyService.selectById(w.getOwner()).getComName());	
    		}
    		dataMap.put("warehouseList",warehouseList);
    		ExcelUtil.export(request, response, dataMap, "warehouse", "仓库信息");
    }
    
    /**
     * @Description (下载导入模板)
     * @param request
     * @return
     */
    @RequestMapping("downloadImportTemp")
    public void downloadWarehouseTemp(Map<String, Object> map,HttpServletRequest request,HttpServletResponse response) {
    	ExcelUtil.importTempDownLoad(request, response, "warehouse");
    }
    
    /**
     * @Description (仓库信息导入)
     * @param request
     * @return
     */
    @RequestMapping("warehouseImport")
    @ResponseBody
    public Map<String,String> companyImport(@RequestParam(value = "excelFile") MultipartFile excelFile,HttpServletRequest request,HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String, String>();
    	 try {
		     
			ExcelReader excelReader = new ExcelReader(excelFile.getInputStream());
			List<Warehouse> warehouseList = new ArrayList<Warehouse>(); 
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
							Warehouse  warehouse = new Warehouse();
							warehouse.setSerialNum(ApplicationUtils.random32UUID());
							warehouse.setWarehouseNum(StringUtil.rowCell2String(row,0));//
							if (!StringUtils.isEmpty(warehouse.getWarehouseNum())) {
								if(orderService.isExist("warehouse",warehouse.getWarehouseNum(),null)){
									throw new MyException("仓库编号已存在！");
								}
							}else {
								throw new MyException("仓库编号不能为空！");
							}
							warehouse.setWarehouseName(StringUtil.rowCell2String(row,1));
							warehouse.setWarehouseType(StringUtil.rowCell2String(row,2));
							warehouse.setWarehouseCategory(StringUtil.rowCell2String(row,3));
//							warehouse.setOwner(StringUtil.rowCell2String(row,4));
							if(StringUtils.isEmpty(StringUtil.rowCell2String(row,4))){
								warehouse.setOwner("pingtai");//默认平台为所有者
							}else{
								String comId=companyService.selectComIdByComName(StringUtil.rowCell2String(row,4));
								if(StringUtils.isEmpty(comId)){
									//创建该公司
									Company com=new Company();
									com.setComId(ApplicationUtils.random32UUID());
									com.setComNum(orderService.getNumCode("CO"));
									com.setComType("9");
									com.setComName(StringUtil.rowCell2String(row,4));
									companyService.insert(com);
									warehouse.setOwner(com.getComId());
								}else{
									warehouse.setOwner(comId);
								}
							}
//							warehouse.setOwner(StringUtil.rowCell2String(row,4));
							warehouse.setAddress(StringUtil.rowCell2String(row,5));
							warehouse.setArea(StringUtil.rowCell2String(row,6));
							warehouse.setAdmin(StringUtil.rowCell2String(row,7));
							warehouse.setTel(StringUtil.rowCell2String(row,8));
							warehouse.setEmail(StringUtil.rowCell2String(row,9));
							warehouse.setFax(StringUtil.rowCell2String(row,10));
							warehouse.setRemark(StringUtil.rowCell2String(row,11));
							Subject currentUser = SecurityUtils.getSubject();
							String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
							warehouse.setCreateTime(new Date());
							warehouse.setCreator(currenLoginName);
							warehouse.setUpdateTime(new Date());
							warehouse.setUpdater(currenLoginName);
							warehouseList.add(warehouse);
						}catch(MyException  e){
							throw new Exception("第"+(i+1)+"行数据异常请检查，数据内容："+e.getMessage());
						}catch(Exception  e){
							throw new Exception("第"+(i+1)+"行数据转换错误！");
						}
						
					}
					
				}
			}, 2);
			for (Warehouse warehouse : warehouseList) {
				warehouseService.insert(warehouse);
			}

			map.put("data", "success");
		} catch (Exception e1) {
			map.put("data", e1.getMessage());
		}
    	
         return map;
    }
    
    /**
     * @Description (保存库区信息)
     * @param request
     * @return
     */
   
    @RequestMapping(value = "/saveWarehousePositionInfo", method = RequestMethod.POST)
	public ResponseEntity<List> saveWarehousePositionInfo(@RequestBody  Warehouseposition warehouseposition,UriComponentsBuilder ucBuilder) {
    	try{
    		if(StringUtils.isEmpty(warehouseposition.getSerialNum())){
    			warehouseposition.setSerialNum(UUID.randomUUID().toString().replace("-",""));
    			warehousepositionService.insert(warehouseposition);
    		}else{
    			warehousepositionService.update(warehouseposition);
    		}
    	}catch(Exception e){
    		//20180110 qhzhao System.out.println(e.getMessage());
    	}
    	List <Warehouseposition>warehousepositions=warehousepositionService.selectList(warehouseposition.getWarehouseSerial());
		return new ResponseEntity<List>(warehousepositions, HttpStatus.OK);
    }
    
    /**
     * @Description (保存库区信息)
     * @param request
     * @return
     */
   
    @RequestMapping(value = "/saveAllWarehousePositionInfo", method = RequestMethod.POST)
	public ResponseEntity<List> saveWarehousePositionInfo(@RequestBody  String params,UriComponentsBuilder ucBuilder) {
    	params = params.replace("\\", "");
    	        List<Warehouseposition> warehousepositions=new ArrayList<Warehouseposition>();
    	try{
    		warehousepositions=JSON.parseArray(params, Warehouseposition.class);
    		for( Warehouseposition  warehouseposition :  warehousepositions){
    			if(StringUtils.isEmpty(warehouseposition.getSerialNum())){
        			warehouseposition.setSerialNum(UUID.randomUUID().toString().replace("-",""));
        			warehousepositionService.insert(warehouseposition);
        		}else{
        			warehousepositionService.update(warehouseposition);
        		}
    		}
    		
    	}catch(Exception e){
    		System.out.println(e.getMessage());
    	}
//    	warehousepositions=warehousepositionService.selectList(warehousepositions.get(0).getWarehouseSerial());
		return new ResponseEntity<List>(warehousepositions, HttpStatus.OK);
    }
    /**
   	 * 
   	 * @Description 删除仓库区位
   	 * @param ids
   	 * @return
   	 */
   	@RequestMapping(value = "/deleteWarehousePosition", method = RequestMethod.POST)
   	public ResponseEntity<String> deleteWarehousePosition(@RequestBody String id) {
   		if ("".equals(id) || id == null) {
   			return new ResponseEntity<String>("0",HttpStatus.CONFLICT);
   		}
   		warehousepositionService.delete(id);
   		return new ResponseEntity<String>("1",HttpStatus.OK);
   	}
}
