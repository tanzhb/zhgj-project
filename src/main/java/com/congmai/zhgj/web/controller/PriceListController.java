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
import com.congmai.zhgj.web.model.PriceList;
import com.congmai.zhgj.web.model.User;
import com.congmai.zhgj.web.model.Warehouse;
import com.congmai.zhgj.web.model.WarehouseExample;
import com.congmai.zhgj.web.model.Warehouseposition;
import com.congmai.zhgj.web.model.WarehouseExample.Criteria;
import com.congmai.zhgj.web.security.PermissionSign;
import com.congmai.zhgj.web.security.RoleSign;
import com.congmai.zhgj.web.service.PriceListService;
import com.congmai.zhgj.web.service.UserService;
import com.congmai.zhgj.web.service.WarehouseService;


/**
 * @ClassName WareHouseController
 * @Description TODO   价格控制器
 * @author zhaichao
 * @Date 2017年8月8日 下午3:31:35
 * @version 1.0.0
 */
@Controller
@RequestMapping(value = "/price")
public class PriceListController {

    @Resource
    private PriceListService  priceListService;
    
    
    /**
     * 价格信息列表展示
     * 
     * @param session
     * @return
     */
    @RequestMapping(value = "/viewPriceList")
    public String viewPriceList(HttpServletRequest request) {
        return "price/priceList";
    }
    
    /**
     * 	编辑价格信息详情/新增价格信息
     * 
     * @param session
     * @return
     */
    @RequestMapping(value = "/addOrEditPriceInfo")
    public String addOrEditWarehouseDetail( ) {
        return "price/addOrEditPriceInfo";
    }
    /**
     * 保存价格信息
     * 
     * @param session
     * @return
     */
    @RequestMapping(value = "/savePriceInfo", method = RequestMethod.POST)
	public ResponseEntity<Void> savePriceDetail(@RequestBody  PriceList priceList,UriComponentsBuilder ucBuilder) {
    	try{
    		if(StringUtils.isEmpty(priceList.getSerialNum())){
    			priceList.setSerialNum(UUID.randomUUID().toString().replace("-",""));
    			priceListService.insert(priceList);
    		}else{
    			priceListService.update(priceList);
    		}
    	}catch(Exception e){
    		System.out.println(e.getMessage());
    	}
    	HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
  
    @RequestMapping(value = "/getPriceList", method = RequestMethod.GET)
    public ResponseEntity<Map> getWarehouseList(HttpServletRequest request) {
		List<PriceList> priceLists = priceListService.selectList();
		if (priceLists==null||priceLists.isEmpty()) {
			return new ResponseEntity<Map>(HttpStatus.NO_CONTENT);//判断是否为空,为空返回NO_CONTENT
		}
		// 封装datatables数据返回到前台
		Map pageMap = new HashMap();
		pageMap.put("draw", 1);
		pageMap.put("recordsTotal", priceLists.size());
		pageMap.put("recordsFiltered", priceLists.size());
		pageMap.put("data", priceLists);
		return new ResponseEntity<Map>(pageMap, HttpStatus.OK);
	}
    /**
     * @Description 根据serialNum获取价格
     * @param request
     * @return
     */
    @RequestMapping(value = "/viewPriceDetail", method = RequestMethod.POST)
    public ResponseEntity<PriceList> viewWarehouseDetail(Map<String, Object> map, @RequestBody String  serialNum) {
    	PriceList priceList=priceListService.selectById(serialNum);
    	return new ResponseEntity<PriceList>(priceList, HttpStatus.OK);
    }
	
    /**
	 * 
	 * @Description 批量删除价格
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/deletePrice", method = RequestMethod.POST)
	public ResponseEntity<Void> deletePriceList(@RequestBody String ids) {
		if ("".equals(ids) || ids == null) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		priceListService.deletePriceList(ids);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	  
}
