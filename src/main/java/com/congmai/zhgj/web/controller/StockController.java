package com.congmai.zhgj.web.controller;

import java.io.IOException;
import java.util.ArrayList;
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
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.congmai.zhgj.core.feature.orm.mybatis.Page;
import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.core.util.ExcelReader;
import com.congmai.zhgj.core.util.UserUtil;
import com.congmai.zhgj.core.util.ExcelReader.RowHandler;
import com.congmai.zhgj.core.util.ExcelUtil;
import com.congmai.zhgj.web.enums.ComType;
import com.congmai.zhgj.web.model.Company;
import com.congmai.zhgj.web.model.DeliveryMateriel;
import com.congmai.zhgj.web.model.LadderPrice;
import com.congmai.zhgj.web.model.Materiel;
import com.congmai.zhgj.web.model.OrderInfo;
import com.congmai.zhgj.web.model.PriceList;
import com.congmai.zhgj.web.model.PriceListExample;
import com.congmai.zhgj.web.model.Stock;
import com.congmai.zhgj.web.model.StockExample;
import com.congmai.zhgj.web.model.StockInBatch;
import com.congmai.zhgj.web.model.StockInOutRecord;
import com.congmai.zhgj.web.model.User;
import com.congmai.zhgj.web.model.StockExample.Criteria;
import com.congmai.zhgj.web.service.CompanyService;
import com.congmai.zhgj.web.service.LadderPriceService;
import com.congmai.zhgj.web.service.MaterielService;
import com.congmai.zhgj.web.service.OrderService;
import com.congmai.zhgj.web.service.PriceListService;
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
    		System.out.println(e.getMessage());
    	}
    	Materiel m=materielService.selectById(stock.getMaterielSerial());
		stock.setMaterielName(m.getMaterielName());
		stock.setMaterielNum(m.getMaterielNum());
		stock.setSpecifications(m.getSpecifications());
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
	     */
	    @RequestMapping("exportStock")
	    public void exportStock(Map<String, Object> map,HttpServletRequest request,HttpServletResponse response) {
	    		Map<String, Object> dataMap = new HashMap<String, Object>();
	    		/*List<PriceList> priceListList= priceListService.selectPriceList(new PriceListExample());
	    		for(PriceList p:priceListList){
	    			if("buyPrice".equals(p.getPriceType())){
	    				p.setComName(companyService.selectOne(p.getSupplyComId()).getComName());
	    			}else{
	    				p.setComName(companyService.selectOne(p.getBuyComId()).getComName());
	    			}
	    			Materiel m=materielService.selectById(p.getMaterielSerial());
	    			p.setMaterielNum(m.getMaterielNum());
	    			p.setMaterielName(m.getMaterielName());
	    			p.setSpecifications(m.getSpecifications());
	    			p.setUnit(m.getUnit());
	    		}
	    		dataMap.put("priceListList",priceListList);*/
	    		ExcelUtil.export(request, response, dataMap, "stockList", "库存信息");
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
		  * @Description (获取入库批次)
		  * @param request
		  * @return
		  */
		 @RequestMapping(value="stockInBatchList",method=RequestMethod.GET)
		 public ResponseEntity<Map<String,Object>> stockInBatchList(String serialNum,String orderSerial) {
			 //借用DeliveryMateriel bean
			 List<DeliveryMateriel> stockInBatchList=new  ArrayList<DeliveryMateriel>();
			 if(StringUtils.isEmpty(orderSerial)){//查询入库批次记录
				 stockInBatchList=stockService.getStockInBatchList(serialNum);
			 }else{//出库选择入库批次记录(根据物权方及基本物料)
				 stockInBatchList=stockService.getStockInBatchListByMaterielOwn(serialNum,orderSerial);
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
		 public ResponseEntity<Map<String,Object>> stockOutList( String serialNum) {
			 Stock stock=stockService.selectById(serialNum);
			 List<String>deliverSerialList=stockService.getRelationDeliverSerialList(stock);
			 List<DeliveryMateriel>stockOutList=new  ArrayList<DeliveryMateriel>();
			 if(!CollectionUtils.isEmpty(deliverSerialList)){
				 stockOutList=stockService.getDeliverMaterialListForOut(deliverSerialList,stock);
			 }
				 // 封装datatables数据返回到前台
				 Map<String,Object> pageMap = new HashMap<String,Object>();
				 pageMap.put("draw", 1);
				 pageMap.put("recordsTotal", stockOutList==null?0:stockOutList.size());
				 pageMap.put("recordsFiltered", stockOutList==null?0:stockOutList.size());
				 pageMap.put("data", stockOutList);
				 return new ResponseEntity<Map<String,Object>>(pageMap, HttpStatus.OK);
		 }
}
