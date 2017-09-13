package com.congmai.zhgj.web.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.core.util.ExcelUtil;
import com.congmai.zhgj.web.model.Invoice;
import com.congmai.zhgj.web.model.InvoiceBillingRecord;
import com.congmai.zhgj.web.model.Materiel;
import com.congmai.zhgj.web.model.OrderInfo;
import com.congmai.zhgj.web.service.ClauseSettlementDetailService;
import com.congmai.zhgj.web.service.ClauseSettlementService;
import com.congmai.zhgj.web.service.CompanyService;
import com.congmai.zhgj.web.service.ContractService;
import com.congmai.zhgj.web.service.DeliveryMaterielService;
import com.congmai.zhgj.web.service.InvoiceBillingRecordService;
import com.congmai.zhgj.web.service.InvoiceService;
import com.congmai.zhgj.web.service.MaterielService;
import com.congmai.zhgj.web.service.OrderService;



/**
 * @ClassName InvoiceController
 * @Description TODO(发票)
 * @author zhaichao
 * @Date 2017年8月30日 下午3:06:43
 * @version 1.0.0
 */
@Controller
@RequestMapping(value = "/invoice")
public class InvoiceController {

    @Resource
    private InvoiceService  invoiceService;
    @Autowired
   	private OrderService orderService;
    @Autowired
   	private DeliveryMaterielService  deliveryMaterielService;
    @Resource
	private ContractService contractService;
    @Resource
    private ClauseSettlementService clauseSettlementService;
    @Resource
    private ClauseSettlementDetailService clauseSettlementDetailService;
    @Resource
    private CompanyService  companyService;
    @Resource
    private MaterielService  materielService;
    @Resource
    private InvoiceBillingRecordService invoiceBillingRecordService;
    
    /**
     * 发票列表展示
     * 
     * @param session
     * @return
     */
    @RequestMapping(value = "/viewInvoiceList")
    public String viewStockInOutCheckList(HttpServletRequest request) {
        return "invoice/invoiceList";
    }
    
    /**
     * 	编辑发票信息详情/新增发票信息
     */
    @RequestMapping(value = "/addOrEditInvoice")
    public String addOrEditCheckInOutInfo( ) {
        return "invoice/addOrEditInvoiceInfo";
    }
    /**
     * 发票详情展示
     * 
     * @param session
     * @return
     */
    @RequestMapping(value = "/viewInvoice")
    public String viewStockInOutCheck(HttpServletRequest request) {
        return "invoice/viewInvoiceInfo";
    }
    /**
     * 保存发票信息
     * 
     */
    @RequestMapping(value = "/saveInvoiceInfo", method = RequestMethod.POST)
	public ResponseEntity<Invoice> saveInvoiceInfo(@RequestBody Invoice  invoice,UriComponentsBuilder ucBuilder){//
    	Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
    	try{
    		if(StringUtils.isEmpty(invoice.getSerialNum())){
    			invoice.setSerialNum(ApplicationUtils.random32UUID());
    			invoiceService.insert(invoice);
    		}else{
    			invoiceService.update(invoice);
    		}
    		
    	}catch(Exception e){
    		System.out.println(e.getMessage());
    	}
		return new ResponseEntity<Invoice>(invoice, HttpStatus.OK);
    }
   /* *//**
     * 判断发票检验信息是否已经存在
     * 
     *//*
    @RequestMapping(value = "/InvoiceIsExist", method = RequestMethod.POST)
	public ResponseEntity<String> stockInOutCheckIsExist(@RequestBody String  serialNum,UriComponentsBuilder ucBuilder){//
    String flag="0";
    List<StockInOutCheck>stockInOutChecks=null;
    	try{
    		if(serialNum.indexOf("in")>-1){
    			stockInOutChecks=stockInOutCheckService.getAllStockInOutCheck("in", serialNum.substring(0, 32));
    		}else{
    			stockInOutChecks=stockInOutCheckService.getAllStockInOutCheck("out", serialNum.substring(0, 32));
    		}
    		if(stockInOutChecks.size()>0){
    			 flag="1";
    		}
    	}catch(Exception e){
    		System.out.println(e.getMessage());
    	}
		return new ResponseEntity<String>(flag, HttpStatus.OK);
    }*/
    /**
     * 获取发票检验列表
     * 
     */
    @RequestMapping(value = "/getInvoiceList")
    public ResponseEntity<Map> getInvoiceList(HttpServletRequest request,String  inOrOut) {
    	
		List<Invoice> invoices = invoiceService.getAllInvoice(inOrOut,null);
		for(Invoice invoice:invoices){
			OrderInfo order=orderService.selectById(invoice.getOrderSerial());
			invoice.setRelationBuyOrSaleNum(order.getOrderNum());
		}
       
		// 封装datatables数据返回到前台
		Map<String,Object> pageMap = new HashMap<String,Object>();
		pageMap.put("draw", 1);
		pageMap.put("recordsTotal",  invoices==null?0:invoices.size());
		pageMap.put("recordsFiltered",  invoices==null?0:invoices.size());
		pageMap.put("data", invoices);
		return new ResponseEntity<Map>(pageMap, HttpStatus.OK);
	}
    /**
     * @Description 根据serialNum获取发票详情
     * @param request
     * @return
     */
  
    @RequestMapping(value = "/invoiceView")
    @ResponseBody
    public Map viewInvoice(HttpServletRequest request, @RequestBody String  serialNum) {
    	Map<String, Object> map = new HashMap<String, Object>();
    	Invoice invoice=invoiceService.selectById(serialNum.substring(0, 32));
    	if(invoice!=null){
    	OrderInfo orderInfo=orderService.selectById(invoice.getOrderSerial());
    		invoice.setRelationBuyOrSaleNum(orderInfo.getOrderNum());
    		invoice.setOrderAmount(orderInfo.getOrderAmount());
    		invoice.setComName(invoice.getSupplyComId()==null?invoice.getBuyComId():invoice.getSupplyComId());
    		map.put("invoice",invoice);
    	
    }
    	return map;
    }
    
    /**
	 * 
	 * @Description 批量删除发票信息
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/deleteInvoice", method = RequestMethod.POST)
	public ResponseEntity<Void> deleteInvoice(@RequestBody String serialNums) {
		if ("".equals(serialNums) || serialNums == null) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		invoiceService.deleteInvoice(serialNums);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
		/**
	     * @Description (导出发票检验信息)
	     * @param request
	     * @return
	     */
	    @RequestMapping("exportInvoice")
	    @ResponseBody
	    public void exportStockInOutCheck(Map<String, Object> map,HttpServletRequest request,HttpServletResponse response,String  inOrOut) {
	    		Map<String, Object> dataMap = new HashMap<String, Object>();
	    		List<Invoice> invoices = invoiceService.getAllInvoice(inOrOut,null);
	    		for(Invoice invoice:invoices){
	    			OrderInfo order=orderService.selectById(invoice.getOrderSerial());
	    			invoice.setRelationBuyOrSaleNum(order.getOrderNum());
	    		}
	    		dataMap.put("invoiceList",invoices);
	    		if("in".equals(inOrOut)){
		    		ExcelUtil.export(request, response, dataMap, "invoiceIn", "进项票信息");
		    		}else{
		    			ExcelUtil.export(request, response, dataMap, "invoiceOut", "销项票信息");
		    		}
	    }
	@RequestMapping(value = "/getOrderInfoBySerialNum",method = RequestMethod.POST)
	@ResponseBody
	public Map getOrderMaterielInfo( @RequestBody String serialNum) {
		OrderInfo orderInfo =null;
		if(serialNum.indexOf("in")>-1){
			orderInfo = orderService.selectById(serialNum.substring(0, 32));
		}else if(serialNum.indexOf("out")>-1){
			orderInfo = orderService.selectById(serialNum.substring(0, 32));
		}else{
			orderInfo= orderService.selectById(serialNum);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderInfo", orderInfo);
    	//获取订单物料信息
		List<Materiel>orderMateriels=materielService.selectMaterielByOrderSerial(serialNum.substring(0, 32),null);
		map.put("orderMateriels", orderMateriels);
    	return map;
	}
	/**
     * 获取发票物料列表
     * 
     */
    @RequestMapping(value = "/getMaterielList")
    public ResponseEntity<Map> getMaterielList(HttpServletRequest request,String  orderSerial) {
    	
		List<Materiel> materiels = materielService.selectMaterielByOrderSerial(orderSerial.substring(0, 32),orderSerial);
		OrderInfo orderInfo=orderService.selectById(orderSerial.substring(0, 32));
		for(Materiel materiel:materiels){
			materiel.setMoney(new BigDecimal(materiel.getOrderUnitPrice()).multiply(new BigDecimal(materiel.getAmount()).setScale(2,BigDecimal.ROUND_HALF_UP )).toString());
			
		}
		// 封装datatables数据返回到前台
		Map<String,Object> pageMap = new HashMap<String,Object>();
		pageMap.put("draw", 1);
		pageMap.put("recordsTotal",  materiels==null?0:materiels.size());
		pageMap.put("recordsFiltered",  materiels==null?0:materiels.size());
		pageMap.put("data", materiels);
		return new ResponseEntity<Map>(pageMap, HttpStatus.OK);
	}
    @RequestMapping(value = "/saveInvoiceBillingRecordInfo", method = RequestMethod.POST)
   	public ResponseEntity<InvoiceBillingRecord> saveInvoiceBillingRecordInfo(@RequestBody InvoiceBillingRecord  invoiceBillingRecord,UriComponentsBuilder ucBuilder){//
       	Subject currentUser = SecurityUtils.getSubject();
   		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
   		invoiceBillingRecord.setCreator(currenLoginName);
       	try{
       		if(StringUtils.isEmpty(invoiceBillingRecord.getSerialNum())){
       			invoiceBillingRecord.setSerialNum(ApplicationUtils.random32UUID());
       			invoiceBillingRecordService.insert(invoiceBillingRecord);
       		}else{
       			invoiceBillingRecordService.update(invoiceBillingRecord);
       		}
       		
       	}catch(Exception e){
       		System.out.println(e.getMessage());
       	}
   		return new ResponseEntity<InvoiceBillingRecord>(invoiceBillingRecord, HttpStatus.OK);
       }
    
}
