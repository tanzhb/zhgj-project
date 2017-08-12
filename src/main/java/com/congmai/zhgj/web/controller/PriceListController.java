package com.congmai.zhgj.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
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
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.congmai.zhgj.core.feature.orm.mybatis.Page;
import com.congmai.zhgj.web.model.CompanyQualification;
import com.congmai.zhgj.web.model.JsonTreeData;
import com.congmai.zhgj.web.model.LadderPrice;
import com.congmai.zhgj.web.model.Materiel;
import com.congmai.zhgj.web.model.MaterielExample;
import com.congmai.zhgj.web.model.PriceList;
import com.congmai.zhgj.web.model.PriceListExample;
import com.congmai.zhgj.web.model.User;
import com.congmai.zhgj.web.model.Warehouse;
import com.congmai.zhgj.web.model.WarehouseExample;
import com.congmai.zhgj.web.model.Warehouseposition;
import com.congmai.zhgj.web.model.WarehouseExample.Criteria;
import com.congmai.zhgj.web.security.PermissionSign;
import com.congmai.zhgj.web.security.RoleSign;
import com.congmai.zhgj.web.service.CompanyQualificationService;
import com.congmai.zhgj.web.service.LadderPriceService;
import com.congmai.zhgj.web.service.PriceListService;
import com.congmai.zhgj.web.service.UserService;
import com.congmai.zhgj.web.service.WarehouseService;


/**
 * @ClassName PriceListController
 * @Description TODO   价格控制器
 * @author zhaichao
 * @Date 2017年8月8日 下午3:31:35
 * @version 1.0.0
 */
@Controller
@RequestMapping(value = "/priceList")
public class PriceListController {

    @Resource
    private PriceListService  priceListService;
    @Autowired
	private LadderPriceService  ladderPriceService;
    
    
    /**
     * 价格信息列表展示
     * 
     * @param session
     * @return
     */
    @RequestMapping(value = "/viewPriceList")
    public String viewPriceList(HttpServletRequest request) {
        return "priceList/priceList";
    }
    
    /**
     * 	编辑价格信息详情/新增价格信息
     * 
     * @param session
     * @return
     */
    @RequestMapping(value = "/addOrEditPriceListInfo")
    public String addOrEditWarehouseDetail( ) {
        return "priceList/addOrEditPriceListInfo";
    }
    /**
     * 保存价格信息
     * 
     * @param session
     * @return
     * @throws IOException 
     * @throws JsonMappingException 
     * @throws JsonParseException 
     */
    @RequestMapping(value = "/savePriceListInfo", method = RequestMethod.POST)
	public ResponseEntity<PriceList> savePriceDetail(@RequestBody PriceList priceList  ,UriComponentsBuilder ucBuilder){//
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
		return new ResponseEntity<PriceList>(priceList, HttpStatus.OK);
    }
  
    @RequestMapping(value = "/getPriceList", method = RequestMethod.GET)
    public ResponseEntity<Map> getWarehouseList(HttpServletRequest request) {
		List<PriceList> priceLists = priceListService.selectPriceList(new  PriceListExample());
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
    @RequestMapping(value = "/viewPriceListDetail", method = RequestMethod.POST)
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
	@RequestMapping(value = "/deletePriceList", method = RequestMethod.POST)
	public ResponseEntity<Void> deletePriceList(@RequestBody String ids) {
		if ("".equals(ids) || ids == null) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		priceListService.deletePriceList(ids);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	/**
     * @Description (保存或修改阶梯价格信息)
     * @param request
     * @return
     */
    @RequestMapping(value="saveLadderPrices",method=RequestMethod.POST)
    @ResponseBody
    public List<LadderPrice> saveLadderPrices(Map<String, Object> map,@RequestBody String params,HttpServletRequest request) {
    	String flag ="0"; //默认失败
    	List<LadderPrice> ladderPrices = null;
	   	try{
	   		Subject currentUser = SecurityUtils.getSubject();
    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
    		params = params.replace("\\", "");
    		ObjectMapper objectMapper = new ObjectMapper();  
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, LadderPrice.class);  
            ladderPrices = objectMapper.readValue(params, javaType);
            if(!CollectionUtils.isEmpty(ladderPrices)){
            	ladderPriceService.deleteByPriceSerial(ladderPrices.get(0));
            	ladderPriceService.insertLadderPrices(ladderPrices);
            }
            
    		flag = "1";
    	}catch(Exception e){
    		System.out.println(e.getMessage());
    		return null;
    	}
 
    	return ladderPrices;
    }
    /**
	 * 
	 * @Description 删除一条阶梯价格
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/deleteOneLadderPrice", method = RequestMethod.POST)
	public ResponseEntity<Void> deleteOneLadderPrice(@RequestBody String id) {
		if ("".equals(id) || id== null) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		ladderPriceService.deleteBySerialNum(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	} 
	
	 /**
		 * 
		 * @Description 获取阶梯价格信息
		 * @param ids
		 * @return
		 */
		@RequestMapping(value = "/getLadderPriceList", method = RequestMethod.POST)
		public List <LadderPrice>  getLadderPriceList(@RequestBody String id) {
			if ("".equals(id) || id== null) {
				return new ArrayList<LadderPrice>();
			}
			LadderPrice ladderPrice=new LadderPrice();
			ladderPrice.setPriceSerial(id);
			return ladderPriceService.selectListByPriceSerial(ladderPrice);
		} 
}
