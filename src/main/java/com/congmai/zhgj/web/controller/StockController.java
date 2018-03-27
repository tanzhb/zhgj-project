package com.congmai.zhgj.web.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
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
import com.congmai.zhgj.core.util.DateUtil;
import com.congmai.zhgj.core.util.ExcelReader;
import com.congmai.zhgj.core.util.ExcelReader.RowHandler;
import com.congmai.zhgj.core.util.ExcelUtil;
import com.congmai.zhgj.core.util.StringUtil;
import com.congmai.zhgj.core.util.UserUtil;
import com.congmai.zhgj.web.dao.StockOutBatchMapper;
import com.congmai.zhgj.web.enums.StaticConst;
import com.congmai.zhgj.web.model.Company;
import com.congmai.zhgj.web.model.DeliveryMateriel;
import com.congmai.zhgj.web.model.Materiel;
import com.congmai.zhgj.web.model.OrderInfo;
import com.congmai.zhgj.web.model.Stock;
import com.congmai.zhgj.web.model.StockOutBatch;
import com.congmai.zhgj.web.model.StockOutBatchExample;
import com.congmai.zhgj.web.model.User;
import com.congmai.zhgj.web.service.CompanyService;
import com.congmai.zhgj.web.service.MaterielService;
import com.congmai.zhgj.web.service.OrderService;
import com.congmai.zhgj.web.service.StockService;
import com.congmai.zhgj.web.service.UserCompanyService;


/**
 * @ClassName StockController
 * @Description TODO   库存控制器
 * @author zhaichao
 * @Date 2017年8月21日 下午5:31:35
 * @version 1.0.0
 */
@Controller
@RequestMapping(value = "/stock")
public class StockController {

    @Resource
    private StockService  stockService;
    @Resource
    private MaterielService  materielService;
    @Resource
    private CompanyService  companyService;
    @Resource
    private OrderService  orderService;
    @Resource
    private UserCompanyService  userCompanyService;
    
    /**
     * 库存信息列表展示
     * 
     * @param session
     * @return
     */
    @RequestMapping(value = "/viewStockList")
    public String viewStockList(HttpServletRequest request) {
        return "stock/stockList";
    }
    /**
     * 库存信息详情展示
     * 
     * @param session
     * @return
     */
    @RequestMapping(value = "/stockView")
    public String stockView(HttpServletRequest request) {
        return "stock/viewStockDetailInfo";
    }
    /**
     * 	编辑库存信息详情/新增库存信息
     * 
     * @param session
     * @return
     */
    @RequestMapping(value = "/addOrEditStock")
    public String addOrEditStockDetail( ) {
        return "stock/addOrEditStockInfo";
    }
    /**
     * 保存库存信息
     * 
     * @param session
     * @return
     * @throws IOException 
     * @throws JsonMappingException 
     * @throws JsonParseException 
     */
    @RequestMapping(value = "/saveStockInfo", method = RequestMethod.POST)
	public ResponseEntity<Stock> saveStockDetail(@RequestBody Stock stock,UriComponentsBuilder ucBuilder){//
    	Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
    	try{
    		if(StringUtils.isEmpty(stock.getSerialNum())){
    			stock.setSerialNum(ApplicationUtils.random32UUID());
    			stockService.insert(stock);
    		}else{
    			stockService.update(stock);
    		}
    	}catch(Exception e){
    		//20180110 qhzhao System.out.println(e.getMessage());
    	}
    	Materiel m=materielService.selectById(stock.getMaterielSerial());
		stock.setMaterielName(m.getMaterielName());
		stock.setMaterielNum(m.getMaterielNum());
		stock.setSpecifications(m.getSpecifications());
		stock.setServiceParty(StaticConst.getInfo("comName"));
		if(StringUtils.isEmpty(stock.getMaterielOwner())){
			stock.setMaterielOwnerName(StaticConst.getInfo("comName"));
		}else{
			stock.setMaterielOwnerName(companyService.selectById(stock.getMaterielOwner()).getComName());
		}
		return new ResponseEntity<Stock>(stock, HttpStatus.OK);
    }
  
    @RequestMapping(value = "/getStockList", method = RequestMethod.GET)//获取库存信息列表
    public ResponseEntity<Map> getStockList(HttpServletRequest request,String manageType) {
    	String comId = null;
    	User user = UserUtil.getUserFromSession();
    	if(user!=null){
			comId = userCompanyService.getUserComId(String.valueOf(user.getUserId()));
		}
    	
    	List<Stock> stocks = stockService.selectStockListByComId(manageType,comId);
		if(stocks.size()!=0){
		for(Stock stock:stocks){
			Materiel m=materielService.selectById(stock.getMaterielSerial());
			stock.setMaterielName(m.getMaterielName());
			stock.setMaterielNum(m.getMaterielNum());
			stock.setSpecifications(m.getSpecifications());
			if("1".equals(manageType)){//自建
				/*if("0e2523c32dd1415cac89999ccad52b83".equals(stock.getMaterielSerial())){
					System.out.println("当前入库数是:"+stock.getCountInAmountZijian());
				}*/
				stock.setCurrentAmount((Integer.parseInt(stock.getCountInAmountZijian()==null?"0":stock.getCountInAmountZijian())-Integer.parseInt(stock.getCountOutAmountZijian()==null?"0":stock.getCountOutAmountZijian()))+"");
			}else if("2".equals(manageType)){//代管
				stock.setCurrentAmount((Integer.parseInt(stock.getCountInAmountDaiguan()==null?"0":stock.getCountInAmountDaiguan())-Integer.parseInt(stock.getCountOutAmountDaiguan()==null?"0":stock.getCountOutAmountDaiguan()))+"");
			}
			stock.setAverrageWhAge("0");
			stock.setPreSaleAmount("0");
			stock.setOnRoadAmount("0");
			stock.setCanSaleAmount("0");
			stock.setStatus("0");
		}
		}
		// 封装datatables数据返回到前台
		Map<String,Object> pageMap = new HashMap<String,Object>();
		pageMap.put("draw", 1);
		pageMap.put("recordsTotal", stocks==null?0:stocks.size());
		pageMap.put("recordsFiltered", stocks==null?0:stocks.size());
		pageMap.put("data", stocks);
		return new ResponseEntity<Map>(pageMap, HttpStatus.OK);
	}
   //获取库存内容
    @RequestMapping(value = "/viewStock", method = RequestMethod.POST)
    public ResponseEntity<Map> viewStock(HttpServletRequest request, @RequestBody String  serialNum) {
    	Map<String, Object> map = new HashMap<String, Object>();
    	Stock stock=stockService.selectById(serialNum.substring(0, 32));
    	if(stock!=null){
    		Materiel m=materielService.selectById(stock.getMaterielSerial());
			stock.setMaterielName(m.getMaterielName());
			stock.setMaterielNum(m.getMaterielNum());
			stock.setSpecifications(m.getSpecifications());
			if(!StringUtils.isEmpty(stock.getMaterielOwner())){
				Company com=companyService.selectOne(stock.getMaterielOwner());
			stock.setMaterielOwnerName(com==null?"":com.getComName());
			}else{
				stock.setMaterielOwnerName(StaticConst.getInfo("comName"));
			}
			stock.setServiceParty(StaticConst.getInfo("comName"));
			map.put("stock", stock);
    }
    	
    	return new ResponseEntity<Map>(map, HttpStatus.OK);
    }
    //获取库存详情页面信息
    @RequestMapping(value = "/viewStockDetail", method = RequestMethod.POST)
    public ResponseEntity<Map> viewStockDetail(HttpServletRequest request, @RequestBody String  serialNum) {
    	Map<String, Object> map = new HashMap<String, Object>();
    	Stock stock=stockService.selectById(serialNum);
    	if(stock!=null){
    		Materiel m=materielService.selectById(stock.getMaterielSerial());
			stock.setMaterielName(m.getMaterielName());
			stock.setMaterielNum(m.getMaterielNum());
			stock.setSpecifications(m.getSpecifications());
			if(!StringUtils.isEmpty(stock.getMaterielOwner())){
				Company com=companyService.selectOne(stock.getMaterielOwner());
				stock.setMaterielOwnerName(com==null?"":com.getComName());
				}else{
					stock.setMaterielOwnerName(StaticConst.getInfo("comName"));
				}
			stock.setServiceParty(StaticConst.getInfo("comName"));
			if(stock.getLastOutDateDaiguan()!=null&&stock.getLastOutDateZijian()!=null){
				Date date1=new Date();
				Date date2=new Date();
				try {
					date1 = new SimpleDateFormat("yyyy-MM-dd").parse(stock.getLastOutDateDaiguan());
					 date2=new SimpleDateFormat("yyyy-MM-dd").parse(stock.getLastOutDateZijian());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(date1.getTime()>=date2.getTime()){
					stock.setLastOutDateZijian(null);
				}else{
					stock.setLastOutDateDaiguan(null);
				}
				
			}
			map.put("stock", stock);
    }
    	//List<Company>comList=companyService.selectList();
    	return new ResponseEntity<Map>(map, HttpStatus.OK);
    }
    /**
	 * 
	 * @Description 批量删除库存
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/deleteStock", method = RequestMethod.POST)
	public ResponseEntity<Void> deleteStock(@RequestBody String ids) {
		if ("".equals(ids) || ids == null) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		stockService.deleteStock(ids);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
	
		/**
	     * @Description (导出库存信息)
	     * @param request
	     * @return
		 * @throws ParseException 
	     */
	    @RequestMapping("exportStock")
	    @ResponseBody
	    public void exportStock(Map<String, Object> map,HttpServletRequest request,HttpServletResponse response,String  type) throws ParseException {
	    		Map<String, Object> dataMap = new HashMap<String, Object>();
	    		String comId = null;
    	    	User user = UserUtil.getUserFromSession();
    	    	if(user!=null){
    				comId = userCompanyService.getUserComId(String.valueOf(user.getUserId()));
    			}
		if ("zijian".equals(type)) {
			List<Stock> stocks = stockService
					.selectStockListByComId("1", comId);
			if (stocks.size() != 0) {
				for (Stock stock : stocks) {
					Materiel m = materielService.selectById(stock
							.getMaterielSerial());
					stock.setMaterielName(m.getMaterielName());
					stock.setMaterielNum(m.getMaterielNum());
					stock.setSpecifications(m.getSpecifications());
					stock.setCurrentAmount((Integer.parseInt(stock
							.getCountInAmountZijian() == null ? "0" : stock
							.getCountInAmountZijian()) - Integer.parseInt(stock
							.getCountOutAmountZijian() == null ? "0" : stock
							.getCountOutAmountZijian()))
							+ "");
					stock.setAverrageWhAge("0");
					stock.setPreSaleAmount("0");
					stock.setOnRoadAmount("0");
					stock.setCanSaleAmount("0");
					if(stock.getFirstInDateZijian()!=null){
						stock.setFirstInDateZijian(DateUtil.daysBetween(new SimpleDateFormat("yyyy-MM-dd").parse(stock.getFirstInDateZijian()), new Date())+"");
					}
					if(!StringUtil.isEmpty(stock.getMinStock())&&new BigDecimal(stock.getCurrentAmount()).compareTo(new BigDecimal(stock.getMinStock()==null?"0":stock.getMinStock()))<=0){
						stock.setStatus(StaticConst.getInfo("queliao"));
					}else{
						stock.setStatus(StaticConst.getInfo("zhengchang"));
					}
					stock.setRiskGrade("");
				}
				dataMap.put("stocks",stocks);
				ExcelUtil.export(request, response, dataMap, "zijianStock",
						"自建库存信息");

			}
		}
	    		
	    }
	    
	    /**
	     * @Description (下载导入模板)
	     * @param request
	     * @return
	     */
	    @RequestMapping("downloadImportTemp")
	    public void downloadWarehouseTemp(Map<String, Object> map,HttpServletRequest request,HttpServletResponse response) {
	    	ExcelUtil.importTempDownLoad(request, response, "stock");
	    }
	    
	    /**
	     * @Description (库存信息导入)
	     * @param request
	     * @return
	     */
	    @RequestMapping("stockImport")
	    @ResponseBody
	    public Map<String,String> companyImport(@RequestParam(value = "excelFile") MultipartFile excelFile,HttpServletRequest request,HttpServletResponse response) {
	    	Map<String,String> map = new HashMap<String, String>();
	    	 try {
			     
				ExcelReader excelReader = new ExcelReader(excelFile.getInputStream());
				excelReader.readExcelContent(new RowHandler() {
					@Override
					public void handle(List<Object> row,int i) throws Exception {
					}
				}, 1);
				map.put("data", "success");
			} catch (Exception e1) {
				map.put("data", e1.getMessage());
			}
	    	
	         return map;
	    }
	    
	    
	    /**
		  * @Description (获取入库记录)
		  * @param request
		  * @return
		  */
		 @RequestMapping(value="stockInList",method=RequestMethod.GET)
		 public ResponseEntity<Map<String,Object>> stockInList(String serialNum,String orderSerial) {
			 Stock stock=stockService.selectById(serialNum);
			 List<String>takeDeliverSerialList=new  ArrayList<String>();
			 List<DeliveryMateriel>stockInList=new  ArrayList<DeliveryMateriel>();
			 if(StringUtils.isEmpty(orderSerial)){//查询入库记录
				 takeDeliverSerialList=stockService.getRelationTakeDeliverSerialList(stock);
				 stockInList=stockService.getDeliverMaterialListForIn(takeDeliverSerialList,null,stock);
			 }else{//出库选择入库记录
				 List<Stock> stockList=stockService.selectStockListByMaterielSerial(serialNum);
				for(Stock stock1:stockList){
					List<String>takeDeliverSerial=stockService.getRelationTakeDeliverSerialList(stock1);
					takeDeliverSerialList.addAll(takeDeliverSerial);
				}
				OrderInfo o=orderService.selectById(orderSerial);
				 stockInList=stockService.getDeliverMaterialListForIn(takeDeliverSerialList,o.getBuyComId()==null?"zhgj":o.getBuyComId(),null);
			 }
			
				 
			 // 封装datatables数据返回到前台
			 Map<String,Object> pageMap = new HashMap<String,Object>();
			 pageMap.put("draw", 1);
			 pageMap.put("recordsTotal", stockInList==null?0:stockInList.size());
			 pageMap.put("recordsFiltered", stockInList==null?0:stockInList.size());
			 pageMap.put("data", stockInList);
			 return new ResponseEntity<Map<String,Object>>(pageMap, HttpStatus.OK);
		 }
	    
	    /**
		  * @Description (获取入库批次/在库数量)
		  * @param request
		  * @return
		  */
		 @RequestMapping(value="stockInBatchList",method=RequestMethod.GET)
		 public ResponseEntity<Map<String,Object>> stockInBatchList(String serialNum,String orderSerial,String  dmSerialNum) {
			 //借用DeliveryMateriel bean
			 Stock stock=stockService.selectById(serialNum);
			 List<DeliveryMateriel> stockInBatchList=new  ArrayList<DeliveryMateriel>();
			 List<String>takeDeliverSerialList=new  ArrayList<String>();
			 List<DeliveryMateriel> stockOutList=new  ArrayList<DeliveryMateriel>();
			 if(StringUtils.isEmpty(orderSerial)){//查询入库批次记录
				 takeDeliverSerialList=stockService.getRelationTakeDeliverSerialList(stock);
				 stockInBatchList=stockService.getDeliverMaterialListForIn(takeDeliverSerialList,null,stock);//查到所有入库记录
				 //查该物料当前出库总数
				 List<String>deliverSerialList=stockService.getRelationDeliverSerialList(stock);
				 stockOutList=stockService.getDeliverMaterialListForOut(deliverSerialList,stock);
				BigDecimal totalOutCount=BigDecimal.ZERO ;
				if(org.apache.commons.collections.CollectionUtils.isNotEmpty(stockOutList)){
					for(DeliveryMateriel dm:stockOutList){
						totalOutCount=totalOutCount.add(new BigDecimal(dm.getStockCount()==null?"0":dm.getStockCount()));
					}
					//根据入库时间先进先出原则为入库记录附上出库数量
					if(totalOutCount.compareTo(BigDecimal.ZERO)>0){
						for(int i=0;i<stockInBatchList.size();i++){
							DeliveryMateriel dm=stockInBatchList.get(i);
							if(totalOutCount.compareTo(new BigDecimal(dm.getStockCount()))>0){
								dm.setSumStockOutCount(dm.getStockCount());
								totalOutCount=totalOutCount.subtract(new BigDecimal(dm.getStockCount()) );
							}else{
								dm.setSumStockOutCount(totalOutCount.toString());
								break;
							}
						}
					}
				
				}
				
				
				 //stockInBatchList=stockService.getStockInBatchList(serialNum);
			 }else{//出库选择入库批次记录(根据物权方及基本物料)
				 stockInBatchList=stockService.getStockInBatchListByMaterielOwn(serialNum,orderSerial);//dmSerialNum发货物料流水
				 if(org.apache.commons.lang3.StringUtils.isNotBlank(dmSerialNum)){//获取出库批次
					 List<StockOutBatch>soblist=stockService.getStockOutBatchListByDmSerialNum(dmSerialNum);
					 for(int i=0;i<soblist.size();i++){
						 String stockInBatchSerial=soblist.get(i).getStockInBatchSerial();
						 for(DeliveryMateriel d:stockInBatchList){
							 if(stockInBatchSerial.equals(d.getSerialNum())){//入库批次流水相同
								 d.setOutCount(soblist.get(i).getOutCount());
							 }
						 }
						 
					 }
				 }
			 }
			
				 
			 // 封装datatables数据返回到前台
			 Map<String,Object> pageMap = new HashMap<String,Object>();
			 pageMap.put("draw", 1);
			 pageMap.put("recordsTotal", stockInBatchList==null?0:stockInBatchList.size());
			 pageMap.put("recordsFiltered", stockInBatchList==null?0:stockInBatchList.size());
			 pageMap.put("data", stockInBatchList);
			 return new ResponseEntity<Map<String,Object>>(pageMap, HttpStatus.OK);
		 }
		  /**
		  * @Description (获取出库记录)
		  * @param request
		  * @return
		  */
		 @RequestMapping(value="stockOutList",method=RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> stockOutList(String serialNum) {
		Stock stock = stockService.selectById(serialNum);
		List<String> deliverSerialList = stockService
				.getRelationDeliverSerialList(stock);
		List<DeliveryMateriel> stockOutList = new ArrayList<DeliveryMateriel>();
		List<String> takeDeliverSerialList = new ArrayList<String>();
		List<DeliveryMateriel> stockInList = new ArrayList<DeliveryMateriel>();
		if (!CollectionUtils.isEmpty(deliverSerialList)) {
			takeDeliverSerialList = stockService
					.getRelationTakeDeliverSerialList(stock);
			stockInList = stockService.getDeliverMaterialListForIn(
					takeDeliverSerialList, null, stock);
			stockOutList = stockService.getDeliverMaterialListForOut(
					deliverSerialList, stock);
			// 查该物料当前出库总数
			BigDecimal totalOutCount = BigDecimal.ZERO;
			if (org.apache.commons.collections.CollectionUtils
					.isNotEmpty(stockOutList)) {
				for (DeliveryMateriel dm : stockOutList) {
					totalOutCount = totalOutCount
							.add(new BigDecimal(
									dm.getStockCount() == null ? "0" : dm
											.getStockCount()));
				}
				// 根据入库时间先进先出原则为出库记录附上出库仓库
				if (totalOutCount.compareTo(BigDecimal.ZERO) > 0) {
					for (int i = 0; i < stockOutList.size(); i++) {
						DeliveryMateriel  dm=stockOutList.get(i);
						BigDecimal stockOutCount=new  BigDecimal(dm.getStockCount());
						for(int ii=0;ii<stockInList.size();ii++){
							DeliveryMateriel dm1 = stockInList.get(ii);
							if("0".equals(dm1.getStockCount())){
								continue;
							}
							if(stockOutCount.compareTo(new BigDecimal(dm1.getStockCount()))>0 &&!"0".equals(stockOutCount.toString())){//出库比入库多
								dm1.setStockCount("0");
								stockOutCount=stockOutCount.subtract(new BigDecimal(dm1.getStockCount()));
								if(StringUtil.isEmpty(dm.getWarehouseName())){
									dm.setWarehouseName(dm1.getWarehouseName());
								}else{
									if(!(dm.getWarehouseName().indexOf(dm1.getWarehouseName())>-1)){//已有该仓库
										dm.setWarehouseName(dm.getWarehouseName().concat(dm1.getWarehouseName()));
									}
								}
							}else if(stockOutCount.compareTo(new BigDecimal(dm1.getStockCount()))<=0 &&!"0".equals(stockOutCount.toString())){//出库比入库少
								dm1.setStockCount(new BigDecimal(dm1.getStockCount()).subtract(stockOutCount).toString());
								stockOutCount=BigDecimal.ZERO;
								if(StringUtil.isEmpty(dm.getWarehouseName())){
									dm.setWarehouseName(dm1.getWarehouseName());
								}else{
									if(!(dm.getWarehouseName().indexOf(dm1.getWarehouseName())>-1)){//已有该仓库
										dm.setWarehouseName(dm.getWarehouseName().concat(dm1.getWarehouseName()));
									}
								}
							}
						}
					
					}
				}

			}

		}
		// 封装datatables数据返回到前台
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("draw", 1);
		pageMap.put("recordsTotal",
				stockOutList == null ? 0 : stockOutList.size());
		pageMap.put("recordsFiltered",
				stockOutList == null ? 0 : stockOutList.size());
		pageMap.put("data", stockOutList);
		return new ResponseEntity<Map<String, Object>>(pageMap, HttpStatus.OK);
	}
}
