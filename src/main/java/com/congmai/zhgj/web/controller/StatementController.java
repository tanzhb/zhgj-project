package com.congmai.zhgj.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.core.util.DateUtil;
import com.congmai.zhgj.core.util.ExcelReader;
import com.congmai.zhgj.core.util.ExcelUtil;
import com.congmai.zhgj.core.util.UserUtil;
import com.congmai.zhgj.core.util.ExcelReader.RowHandler;
import com.congmai.zhgj.web.model.BOMMateriel;
import com.congmai.zhgj.web.model.BOMMaterielExample;
import com.congmai.zhgj.web.model.ClauseFramework;
import com.congmai.zhgj.web.model.ClauseAdvance;
import com.congmai.zhgj.web.model.ClauseAfterSales;
import com.congmai.zhgj.web.model.ClauseCheckAccept;
import com.congmai.zhgj.web.model.ClauseDelivery;
import com.congmai.zhgj.web.model.ClauseFrameworkExample;
import com.congmai.zhgj.web.model.ClauseSettlement;
import com.congmai.zhgj.web.model.ClauseSettlementDetail;
import com.congmai.zhgj.web.model.ContractVO;
import com.congmai.zhgj.web.model.CustomsForm;
import com.congmai.zhgj.web.model.MaterielExample;
import com.congmai.zhgj.web.model.Materiel;
import com.congmai.zhgj.web.model.Statement;
import com.congmai.zhgj.web.model.StatementExample;
import com.congmai.zhgj.web.model.StatementPaymentInfo;
import com.congmai.zhgj.web.model.StatementExample.Criteria;
import com.congmai.zhgj.web.model.StatementOrderInfo;
import com.congmai.zhgj.web.model.User;
import com.congmai.zhgj.web.service.ClauseAdvanceService;
import com.congmai.zhgj.web.service.ClauseAfterSalesService;
import com.congmai.zhgj.web.service.ClauseCheckAcceptService;
import com.congmai.zhgj.web.service.ClauseDeliveryService;
import com.congmai.zhgj.web.service.ClauseFrameworkService;
import com.congmai.zhgj.web.service.ClauseSettlementDetailService;
import com.congmai.zhgj.web.service.ClauseSettlementService;
import com.congmai.zhgj.web.service.ContractService;
import com.congmai.zhgj.web.service.StatementService;
import com.congmai.zhgj.web.service.UserCompanyService;

/**
 * 
 * @ClassName StatementController
 * @Description 业务处理
 * @author qfzhao
 * @Date 2017年7月28日 下午2:57:52
 * @version 1.0.0
 */
@Controller
@RequestMapping("/statement")
public class StatementController {
	
    @Resource
    private StatementService statementService;
    
    @Resource
    private UserCompanyService userCompanyService;
  
    @RequestMapping("buyStatement")
    public String butStatement() {
    	
		return "statement/buyStatement";
	}
    
    @RequestMapping("supplyStatement")
    public String supplyStatement() {
    	
    	return "statement/supplyStatement";
    }
    
    @RequestMapping("statementBuyAdd")
    public String statementBuyAdd() {
    	
    	return "statement/statementBuyAdd";
    }
    
    @RequestMapping("statementSupplyAdd")
    public String statementSupplyAdd() {
    	
    	return "statement/statementSupplyAdd";
    }
    
    @RequestMapping("statementBuyView")
    public String statementBuyView() {
    	
    	return "statement/statementBuyView";
    }
    
    @RequestMapping("statementSupplyView")
    public String statementSupplyView() {
    	
    	return "statement/statementSupplyView";
    }
    
    /**
     * 保存客户对账单
     */
    @RequestMapping(value = "/saveStatement", method = RequestMethod.POST)
    @ResponseBody
    public Statement saveStatement(@RequestBody Statement statement) {
    	
    	if(statement.getSerialNum()==null||statement.getSerialNum().isEmpty()){//新增
    		statement.setSerialNum(ApplicationUtils.random32UUID());
    		Subject currentUser = SecurityUtils.getSubject();
    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
    		statement.setCreator(currenLoginName);
    		statement.setUpdater(currenLoginName);
    		statement.setCreateTime(new Date());
    		statement.setUpdateTime(new Date());
    		statement.setStatus("1");
    		statement.setDelFlg("0");
    		
    		statementService.insert(statement);
    	}
    	
		return statement;
    }
    
    
    /**
     * 
     * @Description 查询对账单列表
     * @param parent(若有值，则查询它及上级物料是它的物料)
     * @return
     */
    @RequestMapping("/findStatementList")
    @ResponseBody
    public ResponseEntity<Map> findStatementList(String type) {
    	StatementExample m =new StatementExample();
    	List<Statement> statementList = new ArrayList<Statement>();
    	//and 条件1
    	Criteria criteria =  m.createCriteria();
    	criteria.andDelFlgEqualTo("0");
    	User user = UserUtil.getUserFromSession();
    	if(user!=null){
    		List<String> comIds = userCompanyService.getComIdsByUserId(String.valueOf(user.getUserId()));
    		if("buy".equals(type)){
    			if(comIds==null){//平台客户对账单供应商为空
    				criteria.andSupplyComIdIsNull();
    			}else{
    				criteria.andSupplyComIdIn(comIds);
    			}
        	}else if("supply".equals(type)){//平台供应商对账单客户为空
        		if(comIds==null){
            		criteria.andBuyComIdIsNull();
    			}else{
    				criteria.andBuyComIdIn(comIds);
    			}
        	}
    		m.setOrderByClause("updateTime DESC");
        	statementList = statementService.selectList(m);
    	}
    
    	/*//and 条件2,未发布可编辑的物料
    	Criteria criteria2 =  m.createCriteria();
    	criteria2.andStatusEqualTo("0");
    	criteria2.andDelFlgEqualTo("0");
    	//or 条件
    	m.or(criteria2);*/
    	//排序字段
    
    	
    	//封装datatables数据返回到前台
		Map pageMap = new HashMap();
		pageMap.put("draw", 1);
		pageMap.put("recordsTotal", statementList==null?0:statementList.size());
		pageMap.put("recordsFiltered", statementList==null?0:statementList.size());
		pageMap.put("data", statementList);
		return new ResponseEntity<Map>(pageMap, HttpStatus.OK);

    }

    /**
	 * 
	 * @Description 批量删除对账单
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/deleteStatements", method = RequestMethod.POST)
	public ResponseEntity<Void> deleteStatements(@RequestBody String ids) {
		if ("".equals(ids) || ids == null) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		List<String> idList = ApplicationUtils.getIdList(ids);
		for(String id : idList){
			Statement m = new Statement();
			m.setSerialNum(id);
			m.setDelFlg("1");
			m.setUpdateTime(new Date());
			Subject currentUser = SecurityUtils.getSubject();
			String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
	    	m.setUpdater(currenLoginName);
			statementService.update(m);
		}

		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
	/**
	 * 
	 * @Description 获取客户对账单信息
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/getStatement")
	@ResponseBody
	public Map getStatement(String serialNum,Statement statement) {
		statement = statementService.selectById(serialNum);
		//Map<String, Object> map = new HashMap<String, Object>();
    	Map<String,Object> map = statementService.getOrderAndPaymentRecords(statement.getSupplyComId(),statement.getBuyComId(),DateUtil.format("yyyy-MM-dd", statement.getStatementDate()));
    	map.put("statement", statement);
    	return map;
	}
	
	/**
	 * 
	 * @Description 获取订单及付款信息
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/getOrderAndPaymentRecords")
	@ResponseBody
	public Map<String,Object> getOrderAndPaymentRecords(String supplyComId,String buyComId,String statementDate){
		//判断是否已经存在对账单
		boolean flag = statementService.isExist(supplyComId,buyComId,statementDate);
		Map<String,Object> map = null;
		if(flag){
			map = new HashMap<String, Object>();
			map.put("isExist", true);
		}else{
			map = statementService.getOrderAndPaymentRecords(supplyComId,buyComId,statementDate);
		}
		return map;
	}
	
    /**
     * @Description (导出对账单信息)
     * @param request
     * @return
     */
    @RequestMapping("exportStatement")
    public void exportStatement(String type, String serialNums, Map<String, Object> map,HttpServletRequest request,HttpServletResponse response) {
    		Map<String, Object> dataMap = new HashMap<String, Object>();
    		StatementExample m =new StatementExample();
    		List<Statement> statementList = new ArrayList<Statement>();
    		//and 条件1
        	Criteria criteria =  m.createCriteria();
        	criteria.andDelFlgEqualTo("0");
        	if("buy".equals(type)){//平台客户对账单供应商为空
        		criteria.andSupplyComIdIsNull();
        	}else if("supply".equals(type)){//平台供应商对账单客户为空
        		criteria.andBuyComIdIsNull();
        	}
        	/*//and 条件2,未发布可编辑的物料
        	Criteria criteria2 =  m.createCriteria();
        	criteria2.andStatusEqualTo("0");
        	criteria2.andDelFlgEqualTo("0");
        	//or 条件
        	m.or(criteria2);*/
        	//排序字段
        	m.setOrderByClause("updateTime DESC");
        	    if(StringUtils.isEmpty(serialNums)){
        	    	statementList = statementService.selectList(m);
    		}else{
    			List<String> idList = ApplicationUtils.getIdList(serialNums);
    			for(String id:idList){
    				statementList	.add(statementService.selectById(id));
    			}
    		}
        	
        	
    		dataMap.put("statementList",statementList);
    		if("supply".equals(type)){
    			ExcelUtil.export(request, response, dataMap, "supplyStatement", "供应商对账单信息");
        	}else if("buy".equals(type)){
        		ExcelUtil.export(request, response, dataMap, "buyStatement", "客户对账单信息");
        	}
    		
    }
    
    /**
     * @Description (下载导入模板)
     * @param request
     * @return
     */
    @RequestMapping("downloadImportTemp")
    public void downloadCompanyTemp(String type,Map<String, Object> map,HttpServletRequest request,HttpServletResponse response) {
    	if("sale".equals(type)){
    		ExcelUtil.importTempDownLoad(request, response, "statement");
    	}else if("buy".equals(type)){
    		ExcelUtil.importTempDownLoad(request, response, "buyStatement");
    	}
    	
    }
    
    /**
     * @Description (客户对账单信息导入)
     * @param request
     * @return
     */
    @RequestMapping("buyStatementImport")
    @ResponseBody
    public Map<String,String> buyStatementImport(@RequestParam(value = "excelFile") MultipartFile excelFile,HttpServletRequest request,HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String, String>();
    	 try {
			ExcelReader excelReader = new ExcelReader(excelFile.getInputStream());
			excelReader.readExcelContent(new RowHandler() {
				@Override
				public void handle(List<Object> row,int i) throws Exception {
					if(!CollectionUtils.isEmpty(row)){
						try{
							Statement statement = new Statement();



							statement.setSerialNum(ApplicationUtils.random32UUID());
				    		Subject currentUser = SecurityUtils.getSubject();
				    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
				    		statement.setCreator(currenLoginName);
				    		statement.setUpdater(currenLoginName);
				    		statement.setCreateTime(new Date());
				    		statement.setUpdateTime(new Date());
				    		statement.setStatus("1");
				    		
				    		statementService.insert(statement);
						}catch(Exception  e){
							throw new Exception("第"+(i+1)+"行数据异常请检查，数据内容："+row.toString());
						}
						
					}
					
				}
			}, 2);
			map.put("data", "success");
		} catch (Exception e1) {
			map.put("data", e1.getMessage());
		}
    	
         return map;
    }
    
    
    /**
     * @Description (供应商对账单信息导入)
     * @param request
     * @return
     */
    @RequestMapping("statementImport")
    @ResponseBody
    public Map<String,String> statementImport(@RequestParam(value = "excelFile") MultipartFile excelFile,String type,HttpServletRequest request,HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String, String>();
    	 try {
			ExcelReader excelReader = new ExcelReader(excelFile.getInputStream());
			excelReader.readExcelContent(new RowHandler() {
				@Override
				public void handle(List<Object> row,int i) throws Exception {
					if(!CollectionUtils.isEmpty(row)){
						try{
							Statement statement = new Statement();

							statement.setStatementNum(row.get(0).toString());
							
							statement.setStatementDate(StringUtils.isEmpty(row.get(15).toString())?null:(Date) row.get(15));

							statement.setSerialNum(ApplicationUtils.random32UUID());
				    		Subject currentUser = SecurityUtils.getSubject();
				    		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
				    		statement.setCreator(currenLoginName);
				    		statement.setUpdater(currenLoginName);
				    		statement.setCreateTime(new Date());
				    		statement.setUpdateTime(new Date());
				    		statement.setStatus("1");
				    		
				    		statementService.insert(statement);
						}catch(Exception  e){
							throw new Exception("第"+i+"行数据异常请检查，数据内容："+row.toString());
						}
						
					}
					
				}
			}, 2);
			map.put("data", "success");
		} catch (Exception e1) {
			map.put("data", e1.getMessage());
		}
    	
         return map;
    }
}