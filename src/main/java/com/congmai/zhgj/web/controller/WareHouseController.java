package com.congmai.zhgj.web.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.congmai.zhgj.core.feature.orm.mybatis.Page;
import com.congmai.zhgj.web.model.JsonTreeData;
import com.congmai.zhgj.web.model.Materiel;
import com.congmai.zhgj.web.model.MaterielExample;
import com.congmai.zhgj.web.model.User;
import com.congmai.zhgj.web.model.Warehouse;
import com.congmai.zhgj.web.model.WarehouseExample;
import com.congmai.zhgj.web.model.Warehouseposition;
import com.congmai.zhgj.web.model.WarehouseExample.Criteria;
import com.congmai.zhgj.web.security.PermissionSign;
import com.congmai.zhgj.web.security.RoleSign;
import com.congmai.zhgj.web.service.UserService;
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
	public ResponseEntity<Void> saveWarehouseDetail(@RequestBody  Warehouse warehouse,UriComponentsBuilder ucBuilder) {
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
    	HttpHeaders headers = new HttpHeaders();
		/*headers.setLocation(ucBuilder.path("/warehouse/{id}")
				.buildAndExpand(warehouse.getSerialNum()).toUri());*/
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
  
    @RequestMapping(value = "/getWarehouseList", method = RequestMethod.GET)
    public ResponseEntity<Map> getWarehouseList(HttpServletRequest request) {
		List<Warehouse> warehouses = warehouseService.selectList();
		if (warehouses.isEmpty()) {
			return new ResponseEntity<Map>(HttpStatus.NO_CONTENT);//判断是否为空,为空返回NO_CONTENT
		}
		// 封装datatables数据返回到前台
		Map pageMap = new HashMap();
		pageMap.put("draw", 1);
		pageMap.put("recordsTotal", warehouses.size());
		pageMap.put("recordsFiltered", warehouses.size());
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
}
