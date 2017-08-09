package com.congmai.zhgj.web.controller;

import java.io.PrintWriter;
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
import com.congmai.zhgj.web.model.User;
import com.congmai.zhgj.web.model.Warehouse;
import com.congmai.zhgj.web.model.Warehouseposition;
import com.congmai.zhgj.web.security.PermissionSign;
import com.congmai.zhgj.web.security.RoleSign;
import com.congmai.zhgj.web.service.UserService;
import com.congmai.zhgj.web.service.WarehouseService;
import com.congmai.zhgj.web.service.WarehousepositionService;


/**
 * @ClassName WareHousepositionController
 * @Description TODO   仓库库位控制器
 * @author zhaichao
 * @Date 2017年8月2日 下午3:31:35
 * @version 1.0.0
 */
@Controller
@RequestMapping(value = "/warehouseposition")
public class WareHousepositionController {

    @Resource
    private WarehousepositionService  warehousepositionService;
    
    
    /**
     * 查询仓库库位信息列表
     * 
     * @param session
     * @return
     */
    @RequestMapping(value = "/viewWarehousepositionList")
    public List<Warehouseposition> viewWareHouseList(@RequestBody String warehouseSerial) {
    	List<Warehouseposition> wp=warehousepositionService.selectList(warehouseSerial);
        return wp;
    }
    
    
    /**
     * 保存仓库信息
     * 
     * @param session
     * @return
     */
    @RequestMapping(value = "/saveWarehousepositionInfo", method = RequestMethod.POST)
	public ResponseEntity<Void> saveWarehousepositionDetail(@RequestBody  Warehouseposition warehouseposition,UriComponentsBuilder ucBuilder) {
    	try{
    		if(StringUtils.isEmpty(warehouseposition.getSerialNum())){
    			warehouseposition.setSerialNum(UUID.randomUUID().toString().replace("-",""));
    			warehousepositionService.insert(warehouseposition);
    		}else{
    			warehousepositionService.update(warehouseposition);
    		}
    	}catch(Exception e){
    		System.out.println(e.getMessage());
    	}
    	HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/warehouseposition/{id}")
				.buildAndExpand(warehouseposition.getSerialNum()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
  
    /**
	 * 
	 * @Description 删除仓库位置信息
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/deleteWarehouseposition", method = RequestMethod.POST)
	public ResponseEntity<Void> deleteWarehouse(@RequestBody String ids) {
		if ("".equals(ids) || ids == null) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		warehousepositionService.deleteWarehouseposition(ids);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
