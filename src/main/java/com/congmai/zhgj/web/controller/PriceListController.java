package com.congmai.zhgj.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.web.model.LadderPrice;
import com.congmai.zhgj.web.model.Materiel;
import com.congmai.zhgj.web.model.PriceList;
import com.congmai.zhgj.web.model.PriceListExample;
import com.congmai.zhgj.web.service.CompanyService;
import com.congmai.zhgj.web.service.LadderPriceService;
import com.congmai.zhgj.web.service.MaterielService;
import com.congmai.zhgj.web.service.PriceListService;
import com.sun.org.apache.bcel.internal.generic.GETSTATIC;


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
    @Autowired
	private MaterielService  materielService;
    @Autowired
   	private CompanyService  companyService;
    
    
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
	public ResponseEntity<PriceList> savePriceDetail(@RequestBody PriceList priceList,UriComponentsBuilder ucBuilder){//
    	Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
    	try{
    		if(StringUtils.isEmpty(priceList.getSerialNum())){
    			priceList.setSerialNum(ApplicationUtils.random32UUID());
    			priceList.setPriceId(ApplicationUtils.random32UUID());
    			priceList.setVersionNO("1");
    			priceList.setIsLatestVersion("1");
    			priceList.setStatus("0");//待审批
    			priceListService.insert(priceList);
    		}else{
    			priceListService.updateVersion(priceList);//将之前版本更新为老版本
    			List<LadderPrice>ladderPrices=ladderPriceService.selectListByPriceSerial(priceList.getSerialNum());
    			priceList.setSerialNum(ApplicationUtils.random32UUID());
    			priceList.setVersionNO(Integer.parseInt(priceList.getVersionNO())+1+"");
    			priceList.setIsLatestVersion("1");
    			priceList.setStatus("0");//待审批
    			priceListService.insert(priceList);
    			ladderPriceService.insertLadderPrices(ladderPrices,currenLoginName);
    		}
    	}catch(Exception e){
    		System.out.println(e.getMessage());
    	}
		return new ResponseEntity<PriceList>(priceList, HttpStatus.OK);
    }
  
    @RequestMapping(value = "/getPriceList", method = RequestMethod.GET)
    public ResponseEntity<Map> getPriceList(HttpServletRequest request) {
		List<PriceList> priceLists = priceListService.selectPriceList(new  PriceListExample());
		if (priceLists==null||priceLists.isEmpty()) {
			return new ResponseEntity<Map>(HttpStatus.NO_CONTENT);//判断是否为空,为空返回NO_CONTENT
		}
		for(PriceList priceList:priceLists){
			if(StringUtils.isEmpty(priceList.getBuyComId())){
				priceList.setSupplyComName(companyService.selectOne(priceList.getSupplyComId()).getComName());
			}else{
				priceList.setSupplyComName(companyService.selectOne(priceList.getBuyComId()).getComName());
			}
			Materiel m=materielService.getMaterielInfoByMaterielId(materielService.selectById(priceList.getMaterielSerial()).getMaterielId());
			priceList.setMaterielName(m.getMaterielName());
			priceList.setMaterielNum(m.getMaterielNum());
			priceList.setUnit(m.getUnit());
			priceList.setSpecifications(m.getSpecifications());
			priceList.setPriceNum(priceList.getPriceNum()+"-V"+priceList.getVersionNO());
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
   /* @RequestMapping(value = "/viewPriceListDetail", method = RequestMethod.POST)
    public ResponseEntity<PriceList> viewPriceListDetail(HttpServletRequest request, @RequestBody String  serialNum) {
    	Map<String, Object> map = new HashMap<String, Object>();
    	PriceList priceList=priceListService.selectById(serialNum);
    	Materiel m=materielService.selectById(priceList.getMaterielSerial());
    	priceList.setMaterielNum(m.getMaterielNum());
    	priceList.setMaterielName(m.getMaterielName());
    	priceList.setSpecifications(m.getSpecifications());
    	priceList.setUnit(m.getUnit());
    	return new ResponseEntity<PriceList>(priceList, HttpStatus.OK);
    }*/
    @RequestMapping(value = "/viewPriceListDetail", method = RequestMethod.POST)
    public ResponseEntity<Map> viewPriceListDetail(HttpServletRequest request, @RequestBody String  serialNum) {
    	Map<String, Object> map = new HashMap<String, Object>();
    	PriceList priceList=priceListService.selectById(serialNum);
    	if(priceList!=null){
    	Materiel m=materielService.selectById(priceList.getMaterielSerial());
    	priceList.setMaterielNum(m.getMaterielNum());
    	priceList.setMaterielName(m.getMaterielName());
    	priceList.setSpecifications(m.getSpecifications());
    	priceList.setUnit(m.getUnit());
    	if("采购价格".equals(priceList.getPriceType())){
    		priceList.setSupplyComName(companyService.selectOne(priceList.getSupplyComId()).getComName());
    	}else{
    		priceList.setBuyComName(companyService.selectOne(priceList.getBuyComId()).getComName());
    	}
    	map.put("priceList", priceList);
    	map.put("ladderPrices", ladderPriceService.selectListByPriceSerial(serialNum));
    	map.put("priceLists", priceListService.getAllPriceListInfoByPriceIdOrPriceType(priceList.getPriceId(), null));//价格历史版本信息
    	map.put("buyList", priceListService.getAllPriceListInfoByPriceIdOrPriceType(priceList.getPriceId(), "销售价格"));//获取历史价格中使用的采购商
    }
    	map.put("supplyCom", companyService.selectCompanyByComType("供应商", null));
    	map.put("buyCom", companyService.selectCompanyByComType("采购商", null));
    	return new ResponseEntity<Map>(map, HttpStatus.OK);
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
            	ladderPriceService.insertLadderPrices(ladderPrices,currenLoginName);
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
	 /* public ResponseEntity<Map> getWarehouseList(HttpServletRequest request) {
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
		}*/
		/*@RequestMapping(value = "/getLadderPrice", method = RequestMethod.POST)
		public List <LadderPrice>  getLadderPriceList(HttpServletRequest request,@RequestBody String serialNum) {
			Map<String, Object> map = new HashMap<String, Object>();
	    	PriceList priceList=priceListService.selectById(serialNum);
	    	return new ResponseEntity<PriceList>(priceList, HttpStatus.OK);
			if ("".equals(serialNum) || serialNum== null) {
				return new ArrayList<LadderPrice>();
			}
			LadderPrice ladderPrice=new LadderPrice();
			ladderPrice.setPriceSerial(serialNum);
			return ladderPriceService.selectListByPriceSerial(ladderPrice);
		} */
		@RequestMapping(value = "/getLadderPrice", method = RequestMethod.POST)
	    public ResponseEntity<Map> getLadderPriceList(HttpServletRequest request,@RequestBody String serialNum) {
			if ("".equals(serialNum) || serialNum== null) {
				return new ResponseEntity<Map>(HttpStatus.NO_CONTENT);//判断是否为空,为空返回NO_CONTENT
			}
			// 封装datatables数据返回到前台
			Map pageMap = new HashMap();
			pageMap.put("data", ladderPriceService.selectListByPriceSerial(serialNum));
			return new ResponseEntity<Map>(pageMap, HttpStatus.OK);
		}
}
