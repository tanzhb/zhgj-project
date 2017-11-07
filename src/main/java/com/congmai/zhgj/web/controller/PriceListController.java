package com.congmai.zhgj.web.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.log4j.Logger;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import com.alibaba.fastjson.JSON;
import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.core.util.BeanUtils;
import com.congmai.zhgj.core.util.ExcelReader;
import com.congmai.zhgj.core.util.UserUtil;
import com.congmai.zhgj.core.util.UserUtil;
import com.congmai.zhgj.core.util.ExcelReader.RowHandler;
import com.congmai.zhgj.core.util.ExcelUtil;
import com.congmai.zhgj.web.enums.ComType;
import com.congmai.zhgj.web.enums.StaticConst;
import com.congmai.zhgj.web.model.BaseVO;
import com.congmai.zhgj.web.model.CommentVO;
import com.congmai.zhgj.web.model.LadderPrice;
import com.congmai.zhgj.web.model.Materiel;
import com.congmai.zhgj.web.model.OrderInfo;
import com.congmai.zhgj.web.model.PriceList;
import com.congmai.zhgj.web.model.PriceListExample;
import com.congmai.zhgj.web.model.StockInOutCheck;
import com.congmai.zhgj.web.model.User;
import com.congmai.zhgj.web.service.CompanyService;
import com.congmai.zhgj.web.service.IProcessService;
import com.congmai.zhgj.web.service.LadderPriceService;
import com.congmai.zhgj.web.service.MaterielService;
import com.congmai.zhgj.web.service.PriceListService;
import com.congmai.zhgj.web.service.ProcessBaseService;
import com.congmai.zhgj.web.service.UserCompanyService;


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
	private static final Logger logger = Logger.getLogger(PriceListController.class);
    @Resource
    private PriceListService  priceListService;
    @Autowired
	private LadderPriceService  ladderPriceService;
    @Autowired
	private MaterielService  materielService;
    @Autowired
   	private CompanyService  companyService;
    @Autowired
    private UserCompanyService userCompanyService;
    @Autowired
    protected TaskService taskService;
	@Autowired
	private IProcessService processService;
	 @Resource
	 private ProcessBaseService processBaseService;
	 @Autowired
	 protected RuntimeService runtimeService;
    
    
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
	public ResponseEntity<PriceList> savePriceDetail(@RequestBody String params,UriComponentsBuilder ucBuilder){//
    	Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
		PriceList priceList=JSON.parseObject(params, PriceList.class);
    	try{
    		if(StringUtils.isEmpty(priceList.getSerialNum())){
    			priceList.setSerialNum(ApplicationUtils.random32UUID());
    			priceList.setPriceId(ApplicationUtils.random32UUID());
    			priceList.setVersionNO("1");
    			priceList.setIsLatestVersion("1");
    			priceList.setStatus("0");//待审批
    			priceListService.insert(priceList);
    		}else{
    			PriceList latest=priceListService.getPriceListInfoByPriceId(priceList.getPriceId());//获取最新版本
    			priceListService.updateVersion(priceList);//将之前版本更新为老版本
    			List<LadderPrice>ladderPrices=ladderPriceService.selectListByPriceSerial(priceList.getSerialNum());
    			String serialNum=ApplicationUtils.random32UUID();
    			priceList.setSerialNum(serialNum);
    			for(LadderPrice ladderPrice:ladderPrices ){
    				ladderPrice.setPriceSerial(serialNum);
    			}
    			if("buyPrice".equals(priceList.getPriceType())){
    				priceList.setBuyComId(null);
    			}else{
    				priceList.setSupplyComId(null);
    			}
    			priceList.setVersionNO(Integer.parseInt(latest.getVersionNO())+1+"");
    			priceList.setIsLatestVersion("1");
    			priceList.setStatus("0");//待审批
    			priceListService.insert(priceList);
    			ladderPriceService.insertLadderPrices(ladderPrices,currenLoginName);
    		}
    	}catch(Exception e){
    		System.out.println(e.getMessage());
    	}
    	if("buyPrice".equals(priceList.getPriceType())){
    		priceList.setSupplyComName(companyService.selectOne(priceList.getSupplyComId()).getComName());
    	}else{
    		priceList.setBuyComName(companyService.selectOne(priceList.getBuyComId()).getComName());
    	}
		return new ResponseEntity<PriceList>(priceList, HttpStatus.OK);
    }
  
    @RequestMapping(value = "/getPriceList")
    public ResponseEntity<Map> getPriceList(HttpServletRequest request,String  buyOrSale) {
    	User user = UserUtil.getUserFromSession();
    	List<String> comIds = null;
    	if(user!=null){
			comIds = userCompanyService.getComIdsByUserId(String.valueOf(user.getUserId()));
		}
		List<PriceList> priceLists = priceListService.selectPriceList(buyOrSale,comIds);
		if (priceLists==null||priceLists.size()==0) {
//			return new ResponseEntity<Map>(HttpStatus.NO_CONTENT);//判断是否为空,为空返回NO_CONTENT
		}else{
		for(PriceList priceList:priceLists){
			if(StringUtils.isEmpty(priceList.getBuyComId())){
				priceList.setSupplyComName(companyService.selectOne(priceList.getSupplyComId()).getComName());
			}else{
				priceList.setSupplyComName(companyService.selectOne(priceList.getBuyComId()).getComName());
			}
			Materiel m=materielService.getMaterielInfoByMaterielId(materielService.selectById(priceList.getMaterielSerial()).getMaterielId());
			if(m!=null){
			priceList.setMaterielName(m.getMaterielName());
			priceList.setMaterielNum(m.getMaterielNum());
			priceList.setUnit(m.getUnit());
			priceList.setSpecifications(m.getSpecifications());
			priceList.setPriceNum(priceList.getPriceNum()+"-V"+priceList.getVersionNO());
			}
		}
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
    	if(serialNum.length()>32){
    	PriceList priceList=priceListService.selectById(serialNum.substring(0, 32));
    	if(priceList!=null){
    	Materiel m=materielService.selectById(priceList.getMaterielSerial());
    	priceList.setMaterielNum(m.getMaterielNum());
    	priceList.setMaterielName(m.getMaterielName());
    	priceList.setSpecifications(m.getSpecifications());
    	priceList.setUnit(m.getUnit());
    	if("buyPrice".equals(priceList.getPriceType())){
    		priceList.setSupplyComName(companyService.selectOne(priceList.getSupplyComId()).getComName());
    	}else{
    		priceList.setBuyComName(companyService.selectOne(priceList.getBuyComId()).getComName());
    	}
    	map.put("priceList", priceList);
    	map.put("ladderPrices", ladderPriceService.selectListByPriceSerial(serialNum));
    	map.put("priceLists", priceListService.getAllPriceListInfoByPriceIdOrPriceType(priceList.getPriceId(), null));//价格历史版本信息
    	map.put("buyList", priceListService.getAllPriceListInfoByPriceIdOrPriceType(priceList.getPriceId(), "salePrice"));//获取历史价格中使用的采购商
    }
    	}
    	map.put("supplyCom", companyService.selectCompanyByComType(ComType.SUPPLIER.getValue(), null));
    	map.put("buyCom", companyService.selectCompanyByComType(ComType.BUYER.getValue(), null));
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
		/**
	     * @Description (导出仓库信息)
	     * @param request
	     * @return
	     */
	    @RequestMapping("exportPriceList")
	    public void exportWarehouse(Map<String, Object> map,HttpServletRequest request,HttpServletResponse response,String buyOrSale) {
	    		Map<String, Object> dataMap = new HashMap<String, Object>();
	    		User user = UserUtil.getUserFromSession();
	        	List<String> comIds = null;
	        	if(user!=null){
	    			comIds = userCompanyService.getComIdsByUserId(String.valueOf(user.getUserId()));
	    		}
	    		List<PriceList> priceListList= priceListService.selectPriceList(buyOrSale,comIds);
	    		for(PriceList p:priceListList){
	    			if("buyPrice".equals(p.getPriceType())){
	    				p.setComName(companyService.selectOne(p.getSupplyComId()).getComName());
	    				p.setPriceType(StaticConst.getInfo("buyPrice"));
	    			}else{
	    				p.setComName(companyService.selectOne(p.getBuyComId()).getComName());
	    				p.setPriceType(StaticConst.getInfo("salePrice"));
	    			}
	    			Materiel m=materielService.selectById(p.getMaterielSerial());
	    			p.setMaterielNum(m.getMaterielNum());
	    			p.setMaterielName(m.getMaterielName());
	    			p.setSpecifications(m.getSpecifications());
	    			p.setUnit(m.getUnit());
	    		}
	    		dataMap.put("priceListList",priceListList);
	    		ExcelUtil.export(request, response, dataMap, "priceList", "价格信息");
	    }
	    
	    /**
	     * @Description (下载导入模板)
	     * @param request
	     * @return
	     */
	    @RequestMapping("downloadImportTemp")
	    public void downloadWarehouseTemp(Map<String, Object> map,HttpServletRequest request,HttpServletResponse response) {
	    	ExcelUtil.importTempDownLoad(request, response, "priceList");
	    }
	    
	    /**
	     * @Description (仓库信息导入)
	     * @param request
	     * @return
	     */
	    @RequestMapping("priceListImport")
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
	    private PriceList  json2PriceList(String params) {
			params = params.replace("\\", "");
			ObjectMapper objectMapper = new ObjectMapper();  
			PriceList priceList = new PriceList();
			try {
				priceList = objectMapper.readValue(params, PriceList.class);
			}catch (Exception e1) {
				e1.printStackTrace();
			}
			return priceList;
		}

	    /**
	     * 
	     * @Description (启动价格流程)
	     * @param orderInfo
	     * @return
	     */
	    @RequestMapping(value = "/startPriceProcess", method = RequestMethod.POST)
	    @ResponseBody
		private String startPriceProcess(@RequestBody String params) {
	    	String flag = "0"; //默认失败
	    	PriceList priceList = json2PriceList(params);
	    //	priceListService.update(priceList);//更新备注
	    	
			//启动订单审批测试流程-start
			User user = UserUtil.getUserFromSession();
			priceList.setUserId(user.getUserId());
			priceList.setUser_name(user.getUserName());
			if("buyPrice".equals(priceList.getPriceType())){
				priceList.setTitle(user.getUserName()+" 的采购价格申请");
				priceList.setBusinessType(BaseVO.BUYPRICE); 	
			}else{
				priceList.setTitle(user.getUserName()+" 的销售价格申请");
				priceList.setBusinessType(BaseVO.SALEPRICE); 	
			}
			priceList.setStatus(BaseVO.PENDING);					//审批中
			priceList.setApplyDate(new Date());
			priceList.setReason(priceList.getRemark());
	    	processBaseService.insert(priceList);
			String businessKey = priceList.getSerialNum().toString();
			priceList.setBusinessKey(businessKey);
			try {
				String processInstanceId = this.processService.startPriceList(priceList);
//	                message.setStatus(Boolean.TRUE);
//	    			message.setMessage("请假流程已启动，流程ID：" + processInstanceId);
			    logger.info("processInstanceId: "+processInstanceId);
			    flag = "1";
			} catch (ActivitiException e) {
//	            	message.setStatus(Boolean.FALSE);
			    if (e.getMessage().indexOf("no processes deployed with key") != -1) {
			        logger.warn("没有部署流程!", e);
//	        			message.setMessage("没有部署流程，请联系系统管理员，在[流程定义]中部署相应流程文件！");
			    } else {
			        logger.error("启动价格流程失败：", e);
//	                    message.setMessage("启动请假流程失败，系统内部错误！");
			    }
			    throw e;
			} catch (Exception e) {
			    logger.error("启动价格流程失败：", e);
//	                message.setStatus(Boolean.FALSE);
//	                message.setMessage("启动请假流程失败，系统内部错误！");
			    throw e;
			}
	        //启动订单审批测试流程-end
			return flag;
		}
	    
	    
	    
	    
	    /**
	     * 审批价格流程
	     * @param taskId
	     * @param model
	     * @return
	     * @throws NumberFormatException
	     * @throws Exception
	     */
//		@RequiresPermissions("user:order:toApproval") 	//*代表 经理、总监、人力
	    @RequestMapping(value = "/toApproval/{taskId}", method = RequestMethod.POST, produces = "application/json")
	    public @ResponseBody Map<String, Object> toApproval(@PathVariable("taskId") String taskId) throws NumberFormatException, Exception{
	    	Task task = this.taskService.createTaskQuery().taskId(taskId).singleResult();
			// 根据任务查询流程实例
	    	String processInstanceId = task.getProcessInstanceId();
			ProcessInstance pi = this.runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
			PriceList priceList = (PriceList) this.runtimeService.getVariable(pi.getId(), "entity");
			priceList.setTask(task);
			priceList.setProcessInstanceId(processInstanceId);
			List<CommentVO> commentList = this.processService.getComments(processInstanceId);
			String taskDefinitionKey = task.getTaskDefinitionKey();
			logger.info("taskDefinitionKey: "+taskDefinitionKey);
			String result = null;
			if("modifyApply".equals(taskDefinitionKey)){
				result = "modify";
			}else{
				result = "audit";
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("actionType", result);
			map.put("priceList", priceList);
			map.put("commentList", commentList);
	    	return map;
	    }
	    
	    
	    /**
	     * 完成任务
	     * @param content
	     * @param completeFlag
	     * @param taskId
	     * @param redirectAttributes
	     * @param session
	     * @return
	     * @throws Exception
	     */
//		@RequiresPermissions("user:order:complate")  //数据库中权限字符串为user:*:complate， 通配符*匹配到order所以有权限操作 
	    @RequestMapping(value = "/complate/{taskId}", method = RequestMethod.POST, produces = "application/text;charset=UTF-8")
		@ResponseBody
	    public String complate(
	    		@RequestParam("priceId") String priceId,
	    		@RequestParam("content") String content,
	    		@RequestParam("processInstanceId") String processInstanceId,
	    		@RequestParam("completeFlag") Boolean completeFlag,
	    		@PathVariable("taskId") String taskId, 
	    		@RequestParam("priceType") String priceType,
	    		RedirectAttributes redirectAttributes) throws Exception{
	    	User user = UserUtil.getUserFromSession();
	    	String result = "";
	    	try {
	    		PriceList priceList = this.priceListService.selectById(priceId);
	    		PriceList basePriceList = (PriceList) this.runtimeService.getVariable(processInstanceId, "entity");
	    		Map<String, Object> variables = new HashMap<String, Object>();
	    		variables.put("isPass", completeFlag);
	    		if(!completeFlag){
	    			if("buy".equals(priceType)){
	    				basePriceList.setTitle(basePriceList.getUser_name()+" 的采购价格申请失败,需修改后重新提交！");
	    			}else{
	    				basePriceList.setTitle(basePriceList.getUser_name()+" 的销售价格申请失败,需修改后重新提交！");
	    			}
	    			priceList.setStatus(BaseVO.APPROVAL_FAILED);
	    			variables.put("entity", basePriceList);
	    		}else{
	    			priceList.setStatus(BaseVO.PENDING);					//审批中
	    		}
	    		// 完成任务
	    		this.processService.complete(taskId, content, user.getUserId().toString(), variables);
	    		
	    		if(completeFlag){
	    			//此处需要修改，不能根据人来判断审批是否结束。应该根据流程实例id(processInstanceId)来判定。
	    			//判断指定ID的实例是否存在，如果结果为空，则代表流程结束，实例已被删除(移到历史库中)
	    			ProcessInstance pi = this.runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
	    			if(BeanUtils.isBlank(pi)){
	    				priceList.setStatus(BaseVO.APPROVAL_SUCCESS);
	    			}
	    		}
	    		
	    		this.processBaseService.update(priceList);
	    		
	    		if(BaseVO.APPROVAL_SUCCESS.equals(priceList.getStatus())){//订单完成，需更新状态为1(订单待接收)
	    			PriceList priceListori = new PriceList();
	    			priceListori.setSerialNum(priceList.getSerialNum());
	    			priceListori.setStatus("1");
	    			this.priceListService.update(priceListori);
	    		}
	    		
	    		result = "任务办理完成！";
			} catch (ActivitiObjectNotFoundException e) {
				result = "此任务不存在，请联系管理员！";
				throw e;
			} catch (ActivitiException e) {
				result = "此任务正在协办，您不能办理此任务！";
				throw e;
			} catch (Exception e) {
				result = "任务办理失败，请联系管理员！";
				throw e;
			}
			return result;
	    }
		
	    
	    /**
		 * 调整请假申请
		 * @param vacation
		 * @param taskId
		 * @param processInstanceId
		 * @param reApply
		 * @param session
		 * @return
		 * @throws Exception
		 */
//		@RequiresPermissions("user:vacation:modify")
		@RequestMapping(value = "/modifyPrice/{taskId}", method = RequestMethod.POST, produces = "application/text;charset=UTF-8")
		@ResponseBody
		public String modifyPrice(
				@PathVariable("taskId") String taskId,
				@RequestParam("processInstanceId") String processInstanceId,
				@RequestParam("reApply") Boolean reApply,
				@RequestParam("priceId") String priceId,
				@RequestParam("priceType") String priceType,
				@RequestParam("reason") String reason) throws Exception{
			String result = "";
			User user = UserUtil.getUserFromSession();

			PriceList priceList = new PriceList();
			priceList.setSerialNum(priceId);
			
	        Map<String, Object> variables = new HashMap<String, Object>();
	        priceList.setUserId(user.getUserId());
	        priceList.setUser_name(user.getUserName());
	        priceList.setApplyDate(new Date());
	        priceList.setBusinessKey(priceId);
	        priceList.setProcessInstanceId(processInstanceId);
	        String content = "";
	        if(reApply){
	        	//修改价格申请
	        	if("buy".equals(priceType)){
	        		priceList.setTitle(user.getUserName()+" 的采购价格申请！");
	        		 priceList.setBusinessType(BaseVO.BUYPRICE);
	        		 content = "重新申请采购价格";
	 		        result = "任务办理完成，采购价格申请已重新提交！";
    			}else{
    				priceList.setTitle(user.getUserName()+" 的销售价格申请！");
    				priceList.setBusinessType(BaseVO.SALEPRICE);
	        		 content = "重新申请销售价格";
	 		        result = "任务办理完成，销售价格申请已重新提交！";
    			}
	        	priceList.setStatus(BaseVO.PENDING);
		       
	        }else{
	        	if("buy".equals(priceType)){
	        		priceList.setTitle(user.getUserName()+" 的采购价格申请已取消！");
	        		priceList.setBusinessType(BaseVO.BUYPRICE);
	        		 content = "取消申请采购价格";
	 		        result = "任务办理完成，已经取消您的采购价格申请！";
    			}else{
    				priceList.setTitle(user.getUserName()+" 的销售价格申请！");
    				priceList.setBusinessType(BaseVO.SALEPRICE);
	        		 content = "取消申请销售价格";
	 		        result = "任务办理完成，已经取消您的销售价格申请！";
    			}
	        	priceList.setStatus(BaseVO.APPROVAL_FAILED);
	        }
	        try {
	    		this.processBaseService.update(priceList);
				variables.put("entity", priceList);
				variables.put("reApply", reApply);
				this.processService.complete(taskId, content, user.getUserId().toString(), variables);
				
			} catch (ActivitiObjectNotFoundException e) {
//				message.setStatus(Boolean.FALSE);
//				message.setMessage("此任务不存在，请联系管理员！");
				result = "此任务不存在，请联系管理员！";
				throw e;
			} catch (ActivitiException e) {
//				message.setStatus(Boolean.FALSE);
//				message.setMessage("此任务正在协办，您不能办理此任务！");
				result = "此任务正在协办，您不能办理此任务！";
				throw e;
			} catch (Exception e) {
//				message.setStatus(Boolean.FALSE);
//				message.setMessage("任务办理失败，请联系管理员！");
				result = "任务办理失败，请联系管理员！";
				throw e;
			}
			
	    	return result;
	    }
}
