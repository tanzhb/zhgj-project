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

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
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

import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.core.util.ExcelReader;
import com.congmai.zhgj.core.util.ExcelReader.RowHandler;
import com.congmai.zhgj.core.util.ExcelUtil;
import com.congmai.zhgj.web.model.JsonTreeData;
import com.congmai.zhgj.web.model.Warehouse;
import com.congmai.zhgj.web.model.WarehouseExample;
import com.congmai.zhgj.web.model.WarehouseExample.Criteria;
import com.congmai.zhgj.web.service.WarehouseService;


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
    		System.out.println(e.getMessage());
    	}
		return new ResponseEntity<Warehouse>(warehouse, HttpStatus.OK);
    }
  
    @RequestMapping(value = "/getWarehouseList", method = RequestMethod.GET)
    public ResponseEntity<Map> getWarehouseList(HttpServletRequest request) {
		List<Warehouse> warehouses = warehouseService.selectList();
		// 封装datatables数据返回到前台
		Map pageMap = new HashMap();
		pageMap.put("draw", 1);
		pageMap.put("recordsTotal", warehouses==null?0:warehouses.size());
		pageMap.put("recordsFiltered", warehouses==null?0:warehouses.size());
		pageMap.put("data", warehouses);
		return new ResponseEntity<Map>(pageMap, HttpStatus.OK);
	}
    /**
     * @Description (查看)
     * @param request
     * @return
     */
    @RequestMapping(value = "/viewWarehouseDetail", method = RequestMethod.POST)
    public ResponseEntity<Warehouse> viewWarehouseDetail(Map<String, Object> map, @RequestBody String  serialNum) {
    	Warehouse warehouse=warehouseService.selectOne(serialNum);
    	return new ResponseEntity<Warehouse>(warehouse, HttpStatus.OK);
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
    public void exportWarehouse(Map<String, Object> map,HttpServletRequest request,HttpServletResponse response) {
    		Map<String, Object> dataMap = new HashMap<String, Object>();
    		List<Warehouse> warehouseList = warehouseService.selectList();
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
			excelReader.readExcelContent(new RowHandler() {
				@Override
				public void handle(List<Object> row,int i) throws Exception {
					if(!CollectionUtils.isEmpty(row)){
						try{
							Warehouse  warehouse = new Warehouse();
							warehouse.setSerialNum(ApplicationUtils.random32UUID());
							warehouse.setWarehouseNum(row.get(0).toString());
							warehouse.setWarehouseName(row.get(1).toString());
							warehouse.setWarehouseType(row.get(2).toString());
							warehouse.setWarehouseCategory(row.get(3).toString());
							warehouse.setOwner(row.get(4).toString());
							warehouse.setAddress(row.get(5).toString());
							warehouse.setArea(row.get(6).toString());
							warehouse.setAdmin(row.get(7).toString());
							warehouse.setTel(row.get(8).toString());
							warehouse.setEmail(row.get(9).toString());
							warehouse.setFax(row.get(10).toString());
							warehouse.setRemark(row.get(11).toString());
							Subject currentUser = SecurityUtils.getSubject();
							String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
							warehouse.setCreateTime(new Date());
							warehouse.setCreator(currenLoginName);
							warehouse.setUpdateTime(new Date());
							warehouse.setUpdater(currenLoginName);
							warehouseService.insert(warehouse);
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
