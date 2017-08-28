package com.congmai.zhgj.web.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.core.util.ExcelReader;
import com.congmai.zhgj.core.util.ExcelReader.RowHandler;
import com.congmai.zhgj.core.util.ExcelUtil;
import com.congmai.zhgj.web.model.Delivery;
import com.congmai.zhgj.web.model.DeliveryMaterielVO;
import com.congmai.zhgj.web.model.DeliveryVO;
import com.congmai.zhgj.web.model.StockInOutCheck;
import com.congmai.zhgj.web.service.DeliveryService;
import com.congmai.zhgj.web.service.StockInOutCheckService;
import com.congmai.zhgj.web.service.TakeDeliveryService;


/**
 * @ClassName StockInOutController
 * @Description TODO(出入库控制器(出入库检验))
 * @author zhaichao
 * @Date 2017年8月23日 上午10:14:41
 * @version 1.0.0
 */
@Controller
@RequestMapping(value = "/stockInOut")
public class StockInOutController {

    @Resource
    private StockInOutCheckService  stockInOutCheckService;
    @Resource
    private DeliveryService  deliveryService;
    @Autowired
	private TakeDeliveryService takeDeliveryService;
    
    
    
    /**
     * 出入库检验列表展示
     * 
     * @param session
     * @return
     */
    @RequestMapping(value = "/viewStockInOutCheckList")
    public String viewStockInOutCheckList(HttpServletRequest request) {
        return "stockInOutCheck/stockInOutCheckList";
    }
    
    /**
     * 	编辑出入库检验信息详情/新增出入库检验信息
     */
    @RequestMapping(value = "/addOrEditStockInOutCheck")
    public String addOrEditCheckInOutInfo( ) {
        return "stockInOutCheck/addOrEditStockInOutCheckInfo";
    }
    /**
     * 出入库详情展示
     * 
     * @param session
     * @return
     */
    @RequestMapping(value = "/viewStockInOutCheck")
    public String viewStockInOutCheck(HttpServletRequest request) {
        return "stockInOutCheck/viewStockInOutCheckDetailInfo";
    }
    /**
     * 保存出入库检验信息
     * 
     */
    @RequestMapping(value = "/saveStockInOutCheckInfo", method = RequestMethod.POST)
	public ResponseEntity<StockInOutCheck> saveStockInOutCheckInfo(@RequestBody StockInOutCheck  stockInOutCheck,UriComponentsBuilder ucBuilder){//
    	Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
    	try{
    		if(StringUtils.isEmpty(stockInOutCheck.getSerialNum())){
    			stockInOutCheck.setSerialNum(ApplicationUtils.random32UUID());
    			stockInOutCheck.setStatus("0");//待审批
    			stockInOutCheckService.insert(stockInOutCheck);
    		}else{
    			
    		}
    	}catch(Exception e){
    		System.out.println(e.getMessage());
    	}
		return new ResponseEntity<StockInOutCheck>(stockInOutCheck, HttpStatus.OK);
    }
    /**
     * 判断出入库检验信息是否已经存在
     * 
     */
    @RequestMapping(value = "/stockInOutCheckIsExist", method = RequestMethod.POST)
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
    }
    /**
     * 获取出入库检验列表
     * 
     */
    @RequestMapping(value = "/getStockInOutCheckList")
    public ResponseEntity<Map> getStockInOutCheckList(HttpServletRequest request,String  inOrOut) {
    	
		List<StockInOutCheck> stockInOutChecks = stockInOutCheckService.getAllStockInOutCheck(inOrOut,null);
		if (stockInOutChecks.size() != 0) {
			for (StockInOutCheck stockInOutCheck : stockInOutChecks) {
				
			}
		}
		// 封装datatables数据返回到前台
		Map<String,Object> pageMap = new HashMap<String,Object>();
		pageMap.put("draw", 1);
		pageMap.put("recordsTotal",  stockInOutChecks==null?0:stockInOutChecks.size());
		pageMap.put("recordsFiltered",  stockInOutChecks==null?0:stockInOutChecks.size());
		pageMap.put("data", stockInOutChecks);
		return new ResponseEntity<Map>(pageMap, HttpStatus.OK);
	}
    /**
     * @Description 根据serialNum获取出入库检验详情
     * @param request
     * @return
     */
  
    @RequestMapping(value = "/stockInOutCheckView", method = RequestMethod.POST)
    public ResponseEntity<Map> viewStockInOutCheck(HttpServletRequest request, @RequestBody String  serialNum) {
    	Map<String, Object> map = new HashMap<String, Object>();
    	StockInOutCheck stockInOutCheck=stockInOutCheckService.selectById(serialNum.substring(0, 32));
    	if(stockInOutCheck!=null){
    	map.put("stockInOutCheck",stockInOutCheck);
    	if(serialNum.indexOf("in")>-1){//入库
    		Delivery delivery= takeDeliveryService.selectByTakeDeliveryPrimaryKey(stockInOutCheck.getTakeDeliverSerial());
    		map.put("materials", delivery.getDeliveryMateriels());
    	}else if (serialNum.indexOf("out")>-1){//出库
    	 	List<DeliveryMaterielVO> deliveryMateriels = deliveryService.selectListForDetail(stockInOutCheck.getDeliverSerial());
    	 	map.put("materials", deliveryMateriels);
    	}
    }
    	
    	return new ResponseEntity<Map>(map, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/getMaterialBySerialNum", method = RequestMethod.POST)
    public ResponseEntity<Map> getMaterialBySerialNum(HttpServletRequest request, @RequestBody String  serialNum) {
    	Map<String, Object> map = new HashMap<String, Object>();
    	if(serialNum.indexOf("in")>-1){//入库
    		Delivery delivery= takeDeliveryService.selectByTakeDeliveryPrimaryKey(serialNum.substring(0, 32));
    		map.put("materials", delivery.getDeliveryMateriels());
    	}else if (serialNum.indexOf("out")>-1){//出库
    	 	List<DeliveryMaterielVO> deliveryMateriels = deliveryService.selectListForDetail(serialNum.substring(0, 32));
    	 	map.put("materials", deliveryMateriels);
    	}
    	
    	return new ResponseEntity<Map>(map, HttpStatus.OK);
    }
    /**
	 * 
	 * @Description 批量删除出入库检验信息
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/deleteStockInOutCheck", method = RequestMethod.POST)
	public ResponseEntity<Void> deleteStockInOutCheck(@RequestBody String serialNums) {
		if ("".equals(serialNums) || serialNums == null) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		stockInOutCheckService.deleteStockInOutCheck(serialNums);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
		/**
	     * @Description (导出出入库检验信息)
	     * @param request
	     * @return
	     */
	    @RequestMapping("exportStockInOutCheck")
	    public void exportStockInOutCheck(Map<String, Object> map,HttpServletRequest request,HttpServletResponse response,@RequestBody String  inOrOut) {
	    		Map<String, Object> dataMap = new HashMap<String, Object>();
	    		List<StockInOutCheck> stockInOutCheckList= stockInOutCheckService.getAllStockInOutCheck(inOrOut,null);
	    		for(StockInOutCheck s:stockInOutCheckList){
	    			
	    		}
	    		dataMap.put("stockInOutRecordList",stockInOutCheckList);
	    		ExcelUtil.export(request, response, dataMap, "stockInOutRecord", "出入库信息");
	    }
	    /**
	     * @Description (下载导入模板)
	     * @param request
	     * @return
	     */
	    @RequestMapping("downloadImportTemp")
	    public void downloadStockInOutCheckTemp(Map<String, Object> map,HttpServletRequest request,HttpServletResponse response) {
	    	ExcelUtil.importTempDownLoad(request, response, "stockInOutCheck");
	    }
	    
	    /**
	     * @Description (仓库信息导入)
	     * @param request
	     * @return
	     */
	    @RequestMapping("stockInOutCheckImport")
	    @ResponseBody
	    public Map<String,String> companyImport(@RequestParam(value = "excelFile") MultipartFile excelFile,HttpServletRequest request,HttpServletResponse response) {
	    	Map<String,String> map = new HashMap<String, String>();
	    	 try {
			     
				ExcelReader excelReader = new ExcelReader(excelFile.getInputStream());
				excelReader.readExcelContent(new RowHandler() {
					@Override
					public void handle(List<Object> row,int i) throws Exception {
						/*if(!CollectionUtils.isEmpty(row)){
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
							
						}*/
						
					}
				}, 1);
				map.put("data", "success");
			} catch (Exception e1) {
				map.put("data", e1.getMessage());
			}
	    	
	         return map;
	    }
}
