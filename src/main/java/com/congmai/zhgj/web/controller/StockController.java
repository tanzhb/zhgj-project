package com.congmai.zhgj.web.controller;

import java.io.IOException;
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

import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.core.util.ExcelReader;
import com.congmai.zhgj.core.util.ExcelReader.RowHandler;
import com.congmai.zhgj.core.util.ExcelUtil;
import com.congmai.zhgj.web.enums.ComType;
import com.congmai.zhgj.web.model.Company;
import com.congmai.zhgj.web.model.LadderPrice;
import com.congmai.zhgj.web.model.Materiel;
import com.congmai.zhgj.web.model.PriceList;
import com.congmai.zhgj.web.model.PriceListExample;
import com.congmai.zhgj.web.model.Stock;
import com.congmai.zhgj.web.model.StockExample;
import com.congmai.zhgj.web.model.StockExample.Criteria;
import com.congmai.zhgj.web.service.CompanyService;
import com.congmai.zhgj.web.service.LadderPriceService;
import com.congmai.zhgj.web.service.MaterielService;
import com.congmai.zhgj.web.service.PriceListService;
import com.congmai.zhgj.web.service.StockService;


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
		List<Stock> stocks = stockService.selectStockList(manageType);
		if(stocks.size()!=0){
		for(Stock stock:stocks){
			Materiel m=materielService.selectById(stock.getMaterielSerial());
			stock.setMaterielName(m.getMaterielName());
			stock.setMaterielNum(m.getMaterielNum());
			stock.setSpecifications(m.getSpecifications());
			stock.setBelongWarehouseNum("0");
			stock.setCurrentAmount("0");
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
}
