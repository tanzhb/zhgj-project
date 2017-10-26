package com.congmai.zhgj.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.core.util.UserUtil;
import com.congmai.zhgj.web.dao.Delivery2Mapper;
import com.congmai.zhgj.web.enums.ComType;
import com.congmai.zhgj.web.model.CustomsForm;
import com.congmai.zhgj.web.model.Delivery;
import com.congmai.zhgj.web.model.DeliveryVO;
import com.congmai.zhgj.web.model.Invoice;
import com.congmai.zhgj.web.model.Materiel;
import com.congmai.zhgj.web.model.OrderFile;
import com.congmai.zhgj.web.model.OrderInfo;
import com.congmai.zhgj.web.model.PriceList;
import com.congmai.zhgj.web.model.RelationFile;
import com.congmai.zhgj.web.model.User;
import com.congmai.zhgj.web.service.ContractService;
import com.congmai.zhgj.web.service.CustomsFormService;
import com.congmai.zhgj.web.service.DeliveryService;
import com.congmai.zhgj.web.service.OrderService;


/**
 * 
 * @ClassName CustomsFormControlller
 * @Description 报关单或清关单
 * @author zhaichao
 * @Date 2017年10月20日 下午2:57:52
 * @version 1.0.0
 */
@Controller
@RequestMapping("/customsForm")
public class CustomsFormControlller {
	private static final Logger logger = Logger.getLogger(CustomsFormControlller.class);
	
	@Resource
	private CustomsFormService customsFormService;
	
	@Resource
	private OrderService orderService;
	
	@Resource
	private DeliveryService deliveryService;
	
	   /**
     * 报关列表展示
     * 
     * @param session
     * @return
     */
    @RequestMapping(value = "/customsDeclarationForm")
    public String viewCustomsDeclarationForm(HttpServletRequest request) {
        return "customsForm/customsDeclarationFormList";
    }
    /**
     * 清关列表展示
     * 
     * @param session
     * @return
     */
    @RequestMapping(value = "/customsClearanceForm")
    public String viewCustomsClearanceForm(HttpServletRequest request) {
        return "customsForm/customsClearanceFormList";
    }
    /**
     * 	新建编辑报关单或清关单信息
     */
    @RequestMapping(value = "/addCustomsForm")
    public String addOrEditCheckInOutInfo( ) {
        return "customsForm/addCustomsForm";
    }
    /**
     * 报关单或清关单详情展示
     * 
     * @param session
     * @return
     */
    @RequestMapping(value = "/viewCustomsForm")
    public String viewCustomsForm(HttpServletRequest request) {
        return "customsForm/viewCustomsForm";
    }
    /**
     * 保存报关单或清关单
     */
    @RequestMapping(value = "/saveCustomsForm", method = RequestMethod.POST)
    @ResponseBody
    public CustomsForm saveCustomsForm(@RequestBody String params) {
    	
    	Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
		CustomsForm  customsForm=JSON.parseObject(params, CustomsForm.class);
    	
    	if(customsForm.getSerialNum()==null||customsForm.getSerialNum().isEmpty()){//新增
    		customsForm.setStatus("0");
    		customsForm.setSerialNum(ApplicationUtils.random32UUID());
    		customsForm.setCreator(currenLoginName);
    		customsForm.setCreateTime(new Date());
    		customsFormService.insert(customsForm);
    		
    	}else{//更新
    		customsForm.setUpdater(currenLoginName);
    		customsForm.setUpdateTime(new Date());
    		customsFormService.update(customsForm);
    	}
    	
		return customsForm;
    }

    @RequestMapping(value = "/getCustomsFormList")
    public ResponseEntity<Map<String,Object>> getPriceList(HttpServletRequest request,String  type) {//显示清关单/报关单列表
    	User user = UserUtil.getUserFromSession();
		List<CustomsForm> customsForms = customsFormService.selectCustomsFormList(type);
		if (CollectionUtils.isNotEmpty(customsForms)) {
		for(CustomsForm customsForm:customsForms){
			 List<RelationFile>files=deliveryService.getAttachFileInfo(customsForm.getSerialNum());
			 if(CollectionUtils.isNotEmpty(files)){
				 customsForm.setFileCount(files.size()+"");
			 }else{
				 customsForm.setFileCount("0");
			 }
			if(!StringUtils.isEmpty(customsForm.getDeliverSerial())){
				DeliveryVO d=deliveryService.selectDetailById(customsForm.getDeliverSerial());
				customsForm.setDeliverNum(d==null?"":d.getDeliverNum());
			}else if(StringUtils.isEmpty(customsForm.getOrderSerial())){
				OrderInfo o=orderService.selectById(customsForm.getOrderSerial());
				customsForm.setBuyOrderNum(o==null?"":o.getOrderNum());
			}
			
			}
		}
		// 封装datatables数据返回到前台
		Map<String,Object> pageMap = new HashMap<String,Object>();
		pageMap.put("draw", 1);
		pageMap.put("recordsTotal", customsForms==null?0:customsForms.size());
		pageMap.put("recordsFiltered", customsForms==null?0:customsForms.size());
		pageMap.put("data", customsForms);
		return new ResponseEntity<Map<String,Object>>(pageMap, HttpStatus.OK);
	}
    
    @RequestMapping(value = "/viewCustomsForm", method = RequestMethod.POST)
    public ResponseEntity<Map> viewCustomsFormDetail(HttpServletRequest request, @RequestBody String  serialNum) {
    	Map<String, Object> map = new HashMap<String, Object>();
    CustomsForm customsForm=customsFormService.selectById(serialNum);
    DeliveryVO d=deliveryService.selectDetailById(customsForm.getDeliverSerial());
    List<RelationFile>files=deliveryService.getAttachFileInfo(serialNum);
	customsForm.setDeliverNum(d==null?"":d.getDeliverNum());
	OrderInfo o=orderService.selectById(customsForm.getOrderSerial());
	customsForm.setBuyOrderNum(o==null?"":o.getOrderNum());
    map.put("customsForm", customsForm);
    map.put("file", files);
    	return new ResponseEntity<Map>(map, HttpStatus.OK);
    }
    /**
	 * 
	 * @Description 批量删除清关单/报关单列表
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/deleteCustomsForm", method = RequestMethod.POST)
	public ResponseEntity<Void> deleteCustomsForm(@RequestBody String ids) {
		if ("".equals(ids) || ids == null) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		customsFormService.deleteCustomsForm(ids);
		return new ResponseEntity<Void>(HttpStatus.OK);
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
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, RelationFile.class);  
        List<RelationFile> file;
		try {
			file = objectMapper.readValue(params, javaType);
	    	if(!CollectionUtils.isEmpty(file)){
	    		Subject currentUser = SecurityUtils.getSubject();
	    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
		    	for(RelationFile f:file){
		    		f.setSerialNum(ApplicationUtils.random32UUID());
		    		f.setUploader(currenLoginName);
		    		f.setCreator(currenLoginName);
	    			f.setUpdater(currenLoginName);
	    			f.setUploadDate(new Date());
	    			f.setCreateTime(new Date());
	    			f.setUpdateTime(new Date());
	    		
		    	}
		    	//填充File******↑↑↑↑↑↑********
		    	deliveryService.insertAttachFiles(file);
		    	//数据插入******↑↑↑↑↑↑********
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
    
}
