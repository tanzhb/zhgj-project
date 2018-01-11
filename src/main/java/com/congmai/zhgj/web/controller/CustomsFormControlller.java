package com.congmai.zhgj.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.core.util.ExcelReader;
import com.congmai.zhgj.core.util.ExcelUtil;
import com.congmai.zhgj.core.util.UserUtil;
import com.congmai.zhgj.core.util.ExcelReader.RowHandler;
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
import com.congmai.zhgj.web.model.Warehouse;
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
	private static final Logger logger = Logger
			.getLogger(CustomsFormControlller.class);

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
	 * 新建编辑报关单或清关单信息
	 */
	@RequestMapping(value = "/addCustomsForm")
	public String addOrEditCheckInOutInfo() {
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
		String currenLoginName = currentUser.getPrincipal().toString();// 获取当前登录用户名
		CustomsForm customsForm = JSON.parseObject(params, CustomsForm.class);

		if (customsForm.getSerialNum() == null
				|| customsForm.getSerialNum().isEmpty()) {// 新增
			customsForm.setStatus("0");
			customsForm.setSerialNum(ApplicationUtils.random32UUID());
			customsForm.setCreator(currenLoginName);
			customsForm.setCreateTime(new Date());
			customsFormService.insert(customsForm);

		} else {// 更新
			customsForm.setUpdater(currenLoginName);
			customsForm.setUpdateTime(new Date());
			customsFormService.update(customsForm);
			if ("1".equals(customsForm.getStatus())) {// 确认清关/报关时
				CustomsForm temp = new CustomsForm();
				temp.setSerialNum(customsForm.getSerialNum());
				temp.setStatus("1");
				temp.setCustomsFormType(customsForm.getCustomsFormType());
				customsFormService.updateStatus(customsForm);
			}
		}

		return customsForm;
	}

	@RequestMapping(value = "/getCustomsFormList")
	public ResponseEntity<Map<String, Object>> getPriceList(
			HttpServletRequest request, String type, String orderSerial) {// 显示清关单/报关单列表
		User user = UserUtil.getUserFromSession();
		List<CustomsForm> customsForms = new ArrayList<CustomsForm>();
		if (StringUtils.isEmpty(orderSerial)) {
			customsForms = customsFormService.selectCustomsFormList(type);
			if (CollectionUtils.isNotEmpty(customsForms)) {
				for (CustomsForm customsForm : customsForms) {
					List<RelationFile> files = deliveryService
							.getAttachFileInfo(customsForm.getSerialNum());
					if (CollectionUtils.isNotEmpty(files)) {
						customsForm.setFileCount(files.size() + "");
					} else {
						customsForm.setFileCount("0");
					}
					if (!StringUtils.isEmpty(customsForm.getDeliverSerial())) {
						DeliveryVO d = deliveryService
								.selectDetailById(customsForm
										.getDeliverSerial());
						customsForm.setDeliverNum(d == null ? "" : d.getDeliverNum());
						OrderInfo o = orderService.selectById(customsForm.getOrderSerial());
						customsForm.setBuyOrderNum(o.getOrderNum());
					} /*else if (StringUtils
							.isEmpty(customsForm.getOrderSerial())) {
						OrderInfo o = orderService.selectById(customsForm
								.getOrderSerial());
						customsForm.setBuyOrderNum(o == null ? "" : o
								.getOrderNum());
					}
*/
				}
			}
		} else {
			customsForms = customsFormService.selectCustomsFormList(type,orderSerial);
		}
		// 封装datatables数据返回到前台
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("draw", 1);
		pageMap.put("recordsTotal",
				customsForms == null ? 0 : customsForms.size());
		pageMap.put("recordsFiltered",
				customsForms == null ? 0 : customsForms.size());
		pageMap.put("data", customsForms);
		return new ResponseEntity<Map<String, Object>>(pageMap, HttpStatus.OK);
	}

	@RequestMapping(value = "/viewCustomsForm", method = RequestMethod.POST)
	public ResponseEntity<Map> viewCustomsFormDetail(
			HttpServletRequest request, @RequestBody String serialNum) {
		Map<String, Object> map = new HashMap<String, Object>();
		CustomsForm customsForm = customsFormService.selectById(serialNum);
		DeliveryVO d = deliveryService.selectDetailById(customsForm
				.getDeliverSerial());
		List<RelationFile> files = deliveryService.getAttachFileInfo(serialNum);
		customsForm.setDeliverNum(d == null ? "" : d.getDeliverNum());
		OrderInfo o = orderService.selectById(customsForm.getOrderSerial());
		customsForm.setBuyOrderNum(o == null ? "" : o.getOrderNum());
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
		JavaType javaType = objectMapper.getTypeFactory()
				.constructParametricType(List.class, RelationFile.class);
		List<RelationFile> file;
		try {
			file = objectMapper.readValue(params, javaType);
			if (!CollectionUtils.isEmpty(file)) {
				Subject currentUser = SecurityUtils.getSubject();
				String currenLoginName = currentUser.getPrincipal().toString();// 获取当前登录用户名
				for (RelationFile f : file) {
					f.setSerialNum(ApplicationUtils.random32UUID());
					f.setUploader(currenLoginName);
					f.setCreator(currenLoginName);
					f.setUpdater(currenLoginName);
					f.setUploadDate(new Date());
					f.setCreateTime(new Date());
					f.setUpdateTime(new Date());

				}
				// 填充File******↑↑↑↑↑↑********
				deliveryService.insertAttachFiles(file);
				// 数据插入******↑↑↑↑↑↑********
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @Description (导出报关单/清关单信息)
	 * @param request
	 * @return
	 */
	@RequestMapping("exportCustomsForm")
	public void exportWarehouse(Map<String, Object> map,
			HttpServletRequest request, HttpServletResponse response,
			String type) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		List<CustomsForm> customsFormList = customsFormService
				.selectCustomsFormList(type);
		for (CustomsForm customsForm : customsFormList) {
			DeliveryVO d = deliveryService.selectDetailById(customsForm
					.getDeliverSerial());
			List<RelationFile> files = deliveryService
					.getAttachFileInfo(customsForm.getSerialNum());
			customsForm.setDeliverNum(d == null ? "" : d.getDeliverNum());
			if (CollectionUtils.isNotEmpty(files)) {
				customsForm.setFileCount(files.size() + "");
			} else {
				customsForm.setFileCount("0");
			}
			if ("1".equals(customsForm.getStatus())) {
				customsForm.setStatus("已确认");
			} else {
				customsForm.setStatus("待确认");
			}
		}
		dataMap.put("customsFormList", customsFormList);
		if ("clearance".equals(type)) {
			ExcelUtil.export(request, response, dataMap, "customsform" + type,
					"清关单信息");
		} else if ("declaration".equals(type)) {
			ExcelUtil.export(request, response, dataMap, "customsform" + type,
					"报关单信息");
		}

	}

	/**
	 * @Description (下载导入模板)
	 * @param request
	 * @return
	 */
	@RequestMapping("downloadImportTemp")
	public void downloadCustomsFormTemp(Map<String, Object> map,
			HttpServletRequest request, HttpServletResponse response,
			String type) {
		ExcelUtil.importTempDownLoad(request, response, "customsForm" + type);
	}

	/**
	 * @Description (信息导入)
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "customsFormImport", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> companyImport(
			@RequestParam(value = "excelFile") MultipartFile excelFile,
			HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "type") String type) {
		Map<String, String> map = new HashMap<String, String>();
		try {

			/*
			 * ExcelReader excelReader = new
			 * ExcelReader(excelFile.getInputStream());
			 * excelReader.readExcelContent(new RowHandler() {
			 * 
			 * @Override public void handle(List<Object> row,int i) throws
			 * Exception { if(!CollectionUtils.isEmpty(row)){ try{ Warehouse
			 * warehouse = new Warehouse();
			 * warehouse.setSerialNum(ApplicationUtils.random32UUID());
			 * warehouse.setWarehouseNum(row.get(0).toString());
			 * warehouse.setWarehouseName(row.get(1).toString());
			 * warehouse.setWarehouseType(row.get(2).toString());
			 * warehouse.setWarehouseCategory(row.get(3).toString());
			 * warehouse.setOwner(row.get(4).toString());
			 * warehouse.setAddress(row.get(5).toString());
			 * warehouse.setArea(row.get(6).toString());
			 * warehouse.setAdmin(row.get(7).toString());
			 * warehouse.setTel(row.get(8).toString());
			 * warehouse.setEmail(row.get(9).toString());
			 * warehouse.setFax(row.get(10).toString());
			 * warehouse.setRemark(row.get(11).toString()); Subject currentUser
			 * = SecurityUtils.getSubject(); String currenLoginName =
			 * currentUser.getPrincipal().toString();//获取当前登录用户名
			 * warehouse.setCreateTime(new Date());
			 * warehouse.setCreator(currenLoginName);
			 * warehouse.setUpdateTime(new Date());
			 * warehouse.setUpdater(currenLoginName);
			 * warehouseService.insert(warehouse); }catch(Exception e){ throw
			 * new Exception("第"+i+"行数据异常请检查，数据内容："+row.toString()); }
			 * 
			 * }
			 * 
			 * } }, 1);
			 */
			map.put("data", "success");
		} catch (Exception e1) {
			map.put("data", e1.getMessage());
		}

		return map;
	}
}
