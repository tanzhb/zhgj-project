package com.congmai.zhgj.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import net.sf.json.JSONObject;
import net.sf.json.JSONArray;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.io.FileUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.core.util.BeanUtils;
import com.congmai.zhgj.core.util.ExcelReader;
import com.congmai.zhgj.core.util.MessageConstants;
import com.congmai.zhgj.core.util.StringUtil;
import com.congmai.zhgj.core.util.UserUtil;
import com.congmai.zhgj.core.util.ExcelReader.RowHandler;
import com.congmai.zhgj.core.util.ExcelUtil;
import com.congmai.zhgj.log.annotation.OperationLog;
import com.congmai.zhgj.web.dao.Delivery2Mapper;
import com.congmai.zhgj.web.dao.DeliveryMapper;
import com.congmai.zhgj.web.dao.DeliveryMaterielMapper;
import com.congmai.zhgj.web.dao.OrderInfoMapper;
import com.congmai.zhgj.web.dao.RelationFileMapper;
import com.congmai.zhgj.web.dao.StockInOutCheckMapper;
import com.congmai.zhgj.web.dao.StockInOutRecordMapper;
import com.congmai.zhgj.web.dao.TakeDeliveryMapper;
import com.congmai.zhgj.web.enums.StaticConst;
import com.congmai.zhgj.web.event.EventExample;
import com.congmai.zhgj.web.event.SendMessageEvent;
import com.congmai.zhgj.web.model.BaseVO;
import com.congmai.zhgj.web.model.ClauseDelivery;
import com.congmai.zhgj.web.model.CommentVO;
import com.congmai.zhgj.web.model.Company;
import com.congmai.zhgj.web.model.CompanyAddress;
import com.congmai.zhgj.web.model.CompanyExample;
import com.congmai.zhgj.web.model.CompanyQualification;
import com.congmai.zhgj.web.model.ContractVO;
import com.congmai.zhgj.web.model.Delivery;
import com.congmai.zhgj.web.model.DeliveryMateriel;
import com.congmai.zhgj.web.model.DeliveryMaterielExample;
import com.congmai.zhgj.web.model.DeliveryMaterielVO;
import com.congmai.zhgj.web.model.DeliveryTransportVO;
import com.congmai.zhgj.web.model.DeliveryVO;
import com.congmai.zhgj.web.model.HistoricTaskVO;
import com.congmai.zhgj.web.model.Materiel;
import com.congmai.zhgj.web.model.OrderInfo;
import com.congmai.zhgj.web.model.OrderMateriel;
import com.congmai.zhgj.web.model.OrderMaterielExample;
import com.congmai.zhgj.web.model.PaymentRecord;
import com.congmai.zhgj.web.model.RelationFile;
import com.congmai.zhgj.web.model.RelationFileExample;
import com.congmai.zhgj.web.model.StockInOutCheck;
import com.congmai.zhgj.web.model.StockInOutRecord;
import com.congmai.zhgj.web.model.TakeDelivery;
import com.congmai.zhgj.web.model.TakeDeliverySelectExample;
import com.congmai.zhgj.web.model.TakeDeliveryVO;
import com.congmai.zhgj.web.model.User;
import com.congmai.zhgj.web.model.Warehouse;
import com.congmai.zhgj.web.model.WarehouseExample;
import com.congmai.zhgj.web.model.OrderMaterielExample.Criteria;
import com.congmai.zhgj.web.service.ClauseDeliveryService;
import com.congmai.zhgj.web.service.CompanyAddressService;
import com.congmai.zhgj.web.service.CompanyService;
import com.congmai.zhgj.web.service.ContractService;
import com.congmai.zhgj.web.service.DeliveryService;
import com.congmai.zhgj.web.service.IProcessService;
import com.congmai.zhgj.web.service.MaterielService;
import com.congmai.zhgj.web.service.OrderMaterielService;
import com.congmai.zhgj.web.service.OrderService;
import com.congmai.zhgj.web.service.ProcessBaseService;
import com.congmai.zhgj.web.service.StockInOutCheckService;
import com.congmai.zhgj.web.service.StockService;
import com.congmai.zhgj.web.service.TakeDeliveryService;
import com.congmai.zhgj.web.service.UserCompanyService;
import com.congmai.zhgj.web.service.WarehouseService;
import com.sun.org.apache.bcel.internal.generic.GETSTATIC;


/**
 * 发货管理controller
 * @author czw
 *
 */
@Component  
@Scope("prototype")  
@Controller
@RequestMapping("/delivery")
public class DeliveryController {
	private static final Logger logger = Logger.getLogger(PayController.class);

	/**
	 * 合同管理service
	 */
	@Resource
	private ContractService contractService;
	
	/**
	 * 订单service
	 */
	@Resource
    private OrderService orderService;
	
	/**
	 * 仓库service
	 */
	@Resource
    private WarehouseService  warehouseService;
	
	/**
	 * 订单物料service
	 */
	@Resource
    private OrderMaterielService orderMaterielService;
	
	/**
	 * 发货service
	 */
	@Resource
    private DeliveryService deliveryService;
	/**
	 * 用户企业关系service
	 */
	@Resource
	private UserCompanyService userCompanyService;
	
	
	@Autowired
	private IProcessService processService;
	
	@Autowired
	protected RuntimeService runtimeService;
	
	
	@Autowired
	protected TaskService taskService;
	
	
	@Resource
	private StockInOutCheckService  stockInOutCheckService;
	
	@Resource
	private StockInOutRecordMapper  stockInOutRecordMapper;
	
	@Resource
	private OrderInfoMapper   orderInfoMapper;
	
	@Resource
	private Delivery2Mapper   delivery2Mapper;
	
	@Resource
	private RelationFileMapper   relationFileMapper;
	
	@Resource
	private StockInOutCheckMapper stockInOutCheckMapper;
	
	@Resource
	private TakeDeliveryMapper takeDeliveryMapper;

	@Resource
	private TakeDeliveryService  takeDeliveryService;
	
	@Resource
	private DeliveryMaterielMapper deliveryMaterielMapper;
	
	@Resource
	private CompanyService  companyservice;
	
	@Resource
	private CompanyAddressService  companyAddressService;
	  @Resource
	    private ClauseDeliveryService clauseDeliveryService;
	  @Resource
	    private ProcessBaseService processBaseService;
	  @Resource
	    private MaterielService materielService;
	  @Resource
	    private StockService stockService;
	 
	
	 /**
     * @Description (查询仓库列表)
     * @param request
     * @return
     */
    @RequestMapping(value = "/getWarehouseList", method = RequestMethod.POST)
    public ResponseEntity<List<Warehouse>>  getWarehouseList(HttpServletRequest request, String  judgeString,String  comId) {
    	WarehouseExample we=new WarehouseExample();
    		com.congmai.zhgj.web.model.WarehouseExample.Criteria  c=	we.createCriteria();
    		c.andDelFlgEqualTo("0");
    		User user = UserUtil.getUserFromSession();
    		String	comId1 = userCompanyService.getUserComId(String.valueOf(user.getUserId()));//查询当前登录人所属企业类型
    		if("pt".equals(judgeString)){
    			if(StringUtils.isEmpty(comId)){
    				if(StringUtils.isEmpty(comId1)){
    					c.andOwnerEqualTo("pingtai");
    				}else{
    					c.andOwnerEqualTo(comId1);
    				}
    			}else{
    				c.andOwnerEqualTo(comId);
    			}
    		}else if("pts".equals(judgeString)){//
    			if(StringUtils.isEmpty(comId)){
    				c.andOwnerEqualTo("pingtai");
    			}else{
    				c.andOwnerEqualTo(comId);
    			}
    		}else if("buys".equals(judgeString)){//采购商收货仓库列表
    			c.andOwnerEqualTo(comId1);
    		}
    	List<Warehouse> warehouses = warehouseService.selectWarehouseList(we);
    	
		return new ResponseEntity<List<Warehouse>>(warehouses, HttpStatus.OK);
	}
    
    
    /**
     * @Description (查询订单物料)
     * @param request
     * @return
     */
    @RequestMapping(value = "/getSaleOrderMaterielList", method = RequestMethod.GET)
    public ResponseEntity<List<OrderMateriel>>  getSaleOrderMaterielList(HttpServletRequest request,String serialNum) {
    	OrderMaterielExample m =new OrderMaterielExample();
    	//and 条件1
    	com.congmai.zhgj.web.model.OrderMaterielExample.Criteria criteria =  m.createCriteria();
    	criteria.andDelFlgEqualTo("0");
    	criteria.andOrderSerialEqualTo(serialNum);
    	List<OrderMateriel> orderMateriel = orderMaterielService.selectList(m);
		
		return new ResponseEntity<List<OrderMateriel>>(orderMateriel, HttpStatus.OK);
	}
    
    
    /**
     * 查询发货列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/findAllDeliveryList", method = RequestMethod.GET)
    public ResponseEntity<Map<String,Object>> findAllDeliveryList(HttpServletRequest request,String customsFormType,String noInit) {

		Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名 
		//List<DeliveryVO> contractList=deliveryService.findAllDeliveryList(currenLoginName);
		User user = UserUtil.getUserFromSession();
//    	List<String> comIds = new ArrayList<String>();
//    	if(user!=null){
//			comIds = userCompanyService.getComIdsByUserId(String.valueOf(user.getUserId()));
//		}
		DeliveryVO query = new DeliveryVO();
		//query.setCreator(currenLoginName);
		String	comId = userCompanyService.getUserComId(String.valueOf(user.getUserId()));//查询当前登录人所属企业类型
	//采购商和供应商查看
		if(comId!=null){
			Company com =companyservice.selectById(comId);
			if("1".equals(com.getComType())){//采购商
				query.setBuyComId(comId );
			}else{//供应商
				query.setSupplyComId(comId);
//				query.setSupplyComIds(comIds);
			}
		}else{//平台查看
			if(StringUtils.isEmpty(noInit)){//平台销售订单发货列表
				query.setDeliverType("mhq");//贸易发货其他发货
			}
		}
		if("1".equals(noInit)){//不查询初始化状态的发货
			query.setStatus("noInit");
		}
		List<DeliveryVO> deliveryList=deliveryService.findAllDeliveryList(query);
		for(DeliveryVO d:deliveryList){
			if("00".equals(d.getStatus())){//可能需要申请取对应订单已申请数量
				if(d.getOrderSerial()!=null){
					OrderInfo o=orderService.selectById(d.getOrderSerial());
					if(o.getApplyCount()==null){
						d.setOrderApplyCount("0");
					}else{
						d.setOrderApplyCount(o.getApplyCount());
					}
				}
			}
		}
		List<DeliveryVO> now=new ArrayList<DeliveryVO>();
		if("clearance".equals(customsFormType)){
			for(DeliveryVO deliveryVO:deliveryList){
				OrderInfo o=orderService.selectById(deliveryVO.getOrderSerial());
				if(StaticConst.getInfo("waimao").equals(o.getTradeType())&&o.getOrderType().indexOf(StaticConst.getInfo("caigou"))>-1){
					now.add(deliveryVO);
				}
				
			}
			deliveryList=now;
		}else if("declaration".equals(customsFormType)){
			for(DeliveryVO deliveryVO:deliveryList){
				OrderInfo o=orderService.selectById(deliveryVO.getOrderSerial());
				if(StaticConst.getInfo("waimao").equals(o.getTradeType())&&o.getOrderType().indexOf(StaticConst.getInfo("xiaoshou"))>-1){
					now.add(deliveryVO);
				}
				
			}
			deliveryList=now;
		}

		//封装datatables数据返回到前台
		Map<String,Object> pageMap = new HashMap<String,Object>();
		pageMap.put("draw", 1);
		pageMap.put("recordsTotal",deliveryList==null?0:deliveryList.size());
		pageMap.put("recordsFiltered",deliveryList==null?0:deliveryList.size());
		pageMap.put("data", deliveryList);
		return new ResponseEntity<Map<String,Object>>(pageMap, HttpStatus.OK);
	}
    


	/**
	 * 确认发货
	 * @param request（http 请求对象）
	 * @param ucBuilder
	 * @return 操作结果
	 * @throws Exception 
	 */
	@RequestMapping(value = "/goDelivery", method = RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> goDelivery(String serialNum, HttpServletRequest request,UriComponentsBuilder ucBuilder) throws Exception {
		//先判断现在的发货数量与未发数量符合条件才可确认发货,否则提示用户修改
		Map<String,Object> map=new HashMap<String,Object>();
		Boolean  isDel=false;//是否删除当前发货单
		 Boolean flag=false;//还可以发
		DeliveryVO delivery=deliveryService.selectDetailById(serialNum);
		String orderSerial=delivery.getOrderSerial();
		List<DeliveryMaterielVO> orderMateriels = deliveryService.selectList(orderSerial);//取最新的数据判断
		OrderInfo o=orderService.selectById(orderSerial);
		String  deliveryCount=o.getDeliveryCount();//订单已发数量
		String  materielCount=o.getMaterielCount();//订单总数量
		  BigDecimal deliveryCount1=new BigDecimal(deliveryCount);
		  BigDecimal materielCount1=new BigDecimal(materielCount);
		  if(deliveryCount1.compareTo(materielCount1)>=0){
			  flag=true;
			  isDel=true;//提示删除当前发货单
			  map.put("isDel", isDel);
			  map.put("flag", flag);
			  return new ResponseEntity<Map<String,Object>>(map, HttpStatus.CREATED);
		  }else{
			  map.put("isDel", isDel);
		  }
		 
		List<DeliveryMaterielVO> deliveryMateriels1=null;//查出当前发货信息
		deliveryMateriels1= deliveryService.selectListForDetail(serialNum);
		if(deliveryMateriels1!=null&&deliveryMateriels1.size()>0){
			String materielNum=deliveryMateriels1.get(0).getMaterielNum();
			if(StringUtils.isEmpty(materielNum)){
			deliveryMateriels1 = deliveryService.selectListForDetail2(serialNum);	
			}	
		}
		  for(DeliveryMaterielVO deliveryMateriel: deliveryMateriels1){
			  for(DeliveryMaterielVO vo:orderMateriels){
				  if(deliveryMateriel.getOrderMaterielSerialNum().equals(vo.getOrderMaterielSerialNum())){//两个订单物料流水相同时
					  BigDecimal deliverCount=new BigDecimal(deliveryMateriel.getDeliverCount());
					  BigDecimal canDeliverCount=new BigDecimal(vo.getAmount()).subtract(new BigDecimal(vo.getDeliveredCount()==null?"0":vo.getDeliveredCount()));//可发数量=物料数量-已发数量
					  if(deliverCount.compareTo(canDeliverCount)>0){
						  flag=true;
						  map.put("flag", flag);
						  return new ResponseEntity<Map<String,Object>>(map, HttpStatus.CREATED);
					  }
				  }
			  }
		  }
		Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名 

		map.put("serialNum", serialNum);
		map.put("updater", currenLoginName);
		
	
		TakeDelivery takeDelivery = new TakeDelivery();
		takeDelivery = takeDeliveryMapper.selectTakeDeliveryByDeliveryId(serialNum);
		
		
		map.put("orderSerial", orderSerial);
		map.put("orderInfo", o);
		Boolean createQG=StaticConst.getInfo("waimao").equals(o.getTradeType())&&!StringUtils.isEmpty(o.getSupplyComId());//供应商发货/平台发货是否产生清关单
		deliveryService.updateOrderWhenDeliveryComlete(map);
		map.put("createQG", createQG);
		deliveryService.goDelivery(map);//外贸供应商发货
		if(!createQG){//不产生清关单(供应商发货/平台发货)
			OrderInfo orderInfo=new OrderInfo();
			orderInfo.setSerialNum(orderSerial);
			orderInfo.setDeliverStatus(OrderInfo.DELIVER);
			//设置订单发货数量，用于更新
			orderInfo.setDeliveryCount(o.getDeliveryCount());
			List<DeliveryMaterielVO> deliveryMateriels=null;
			deliveryMateriels = deliveryService.selectListForDetail(delivery.getSerialNum());
			if(deliveryMateriels!=null&&deliveryMateriels.size()>0){
				for(DeliveryMaterielVO deliveryMaterielVO:deliveryMateriels){
					orderInfo.setDeliveryCount(StringUtil.sum(orderInfo.getDeliveryCount(),deliveryMaterielVO.getDeliverCount()));
				}
			}
			
			Delivery delivery1=new  Delivery();
			delivery1.setSerialNum(delivery.getSerialNum());
			delivery1.setStatus(DeliveryVO.COMPLETE);
			if("1".equals(o.getContractContent().substring(4, 5))){//有验收条款
					if(StringUtils.isEmpty(o.getSupplyComId())){//平台发货 产生检验单
						//平台发货-->  需要检验 --> 生成出库检验单
						StockInOutCheck stockInOutCheck=new StockInOutCheck();
						stockInOutCheck.setSerialNum(ApplicationUtils.random32UUID());
						stockInOutCheck.setDeliverSerial(serialNum);
						stockInOutCheck.setCheckNum(orderService.getNumCode("QU"));
						stockInOutCheck.setTakeDeliverSerial("checkout");
						stockInOutCheck.setChecker(currenLoginName);
						stockInOutCheck.setCreator(currenLoginName);
						stockInOutCheck.setCreateTime(new Date());
						stockInOutCheck.setUpdater(currenLoginName);
						stockInOutCheck.setUpdateTime(new Date());
						stockInOutCheck.setStatus("0");//待检验
						stockInOutCheck.setCheckDate(new Date());
						stockInOutCheck.setDelFlg("0");
						stockInOutCheckService.insert(stockInOutCheck);
						//更新订单状态至出库待检验
						orderInfo.setDeliverStatus(orderInfo.WAIT_OUT_CHECK);
						delivery1.setStatus(DeliveryVO.WAIT_CHECK);
						takeDelivery.setStatus(TakeDelivery.APPLY_COMPLETE);
					}else{
						//供应商发货--> 不走清关 --> 不需收货 --> 需要检验 --> 生成入库检验单
						
						if(takeDelivery!=null){
							takeDelivery.setStatus(TakeDelivery.APPLY_COMPLETE); //待检验
							this.createStockInCheckRecord(takeDelivery,currenLoginName);
							orderInfo.setDeliverStatus(orderInfo.WAIT_IN_CHECK);//已收货待检验
							delivery1.setStatus(DeliveryVO.WAIT_CHECK);
						}
						
					}
			}else{//没有验收条款
				if(StringUtils.isEmpty(o.getSupplyComId())){//平台发货 产生出库单
					//平台发货-->  不需要检验 --> 生成出库单
					
					orderInfo.setDeliverStatus(orderInfo.WAIT_OUTRECORD);//待出库
					StockInOutRecord stockInOutRecord=new StockInOutRecord();
					stockInOutRecord.setInOutNum(orderService.getNumCode("OU"));
					stockInOutRecord.setSerialNum(ApplicationUtils.random32UUID());
					stockInOutRecord.setDelFlg("0");
					stockInOutRecord.setStatus("0");
					stockInOutRecord.setDeliverSerial(serialNum);
					stockInOutRecord.setTakeDeliverSerial("");
					stockInOutRecord.setCreator(currenLoginName);
					stockInOutRecord.setCreateTime(new Date());
					stockInOutRecord.setUpdater(currenLoginName);
					stockInOutRecord.setUpdateTime(new Date());
					stockInOutRecordMapper.insert(stockInOutRecord);
					//出库消息通知仓储人员
			    	EventExample.getEventPublisher().publicSendMessageEvent(new SendMessageEvent(stockInOutRecord,MessageConstants.IN_TO_STOCK));
					//更新订单状态至待出库
					orderInfo.setDeliverStatus(orderInfo.WAIT_OUTRECORD);
					delivery1.setStatus(DeliveryVO.WAITRECORD);
					takeDelivery.setStatus(TakeDelivery.CHECK_COMPLETE);
				}else{//供应商发货--> 不走清关 --> 不需收货 --> 不需要检验 --> 生成入库单
					takeDelivery.setStatus(TakeDelivery.CHECK_COMPLETE); //已完成
					orderInfo.setDeliverStatus(orderInfo.WAIT_INRECORD);//待入库
					delivery1.setStatus(DeliveryVO.WAIT_IN_RECORD);
					//生成入库单
					StockInOutRecord stockInOutRecord=new StockInOutRecord();
					stockInOutRecord.setSerialNum(ApplicationUtils.random32UUID());
					stockInOutRecord.setTakeDeliverSerial(takeDelivery.getSerialNum());
					stockInOutRecord.setDeliverSerial("");
					stockInOutRecord.setInOutNum(orderService.getNumCode("IN"));
					stockInOutRecord.setDelFlg("0");
					stockInOutRecord.setStatus("0");
					stockInOutRecord.setCreator(currenLoginName);
					stockInOutRecord.setCreateTime(new Date());
					stockInOutRecord.setUpdater(currenLoginName);
					stockInOutRecord.setUpdateTime(new Date());
					stockInOutRecordMapper.insert(stockInOutRecord);
					//入库消息通知仓储人员
			    	EventExample.getEventPublisher().publicSendMessageEvent(new SendMessageEvent(stockInOutRecord,MessageConstants.IN_TO_STOCK));
					
				}
				
			}
			orderInfoMapper.updateByPrimaryKeySelective(orderInfo);//更新订单状态
			delivery2Mapper.updateByPrimaryKeySelective(delivery1);//更新发货单状态
			takeDeliveryMapper.updateByPrimaryKeySelective(takeDelivery);//更新收货单状态
		}
		
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/delivery").buildAndExpand(serialNum).toUri());
		
		//发货消息
		EventExample.getEventPublisher().publicSendMessageEvent(new SendMessageEvent(delivery,MessageConstants.DELIVERY));
		 map.put("flag", flag);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.CREATED);
	}
    
    
	/**
	 * 生成入库检验单
	 */
	public void createStockInCheckRecord(TakeDelivery takeDelivery,
			String currenLoginName) throws Exception {
		StockInOutCheck check = new StockInOutCheck();
		check.setSerialNum(ApplicationUtils.random32UUID());
		check.setTakeDeliverSerial(takeDelivery.getSerialNum());
		check.setDeliverSerial("checkin");
		check.setCheckNum(orderService.getNumCode("QU"));
		check.setStatus("0");
		check.setDelFlg("0");
		check.setCreator(currenLoginName);
		check.setCreateTime(new Date());
		check.setUpdater(currenLoginName);
		check.setUpdateTime(new Date());
		stockInOutCheckMapper.insert(check);
		
		/*//更改订单 
		OrderInfo orderInfo = new OrderInfo();
		Delivery delivery = delivery2Mapper.selectByPrimaryKey(takeDelivery.getDeliverSerial());
		if(delivery!=null){
			orderInfo.setSerialNum(delivery.getOrderSerial());
			orderInfo.setDeliverStatus(OrderInfo.TAKEDELIVER);//待检验
			orderInfo.setUpdateTime(new Date());
			orderInfo.setUpdater(currenLoginName);
			orderInfoMapper.updateByPrimaryKeySelective(orderInfo);
			
			//更新发货状态
			Delivery _delivery = new Delivery();
			_delivery.setSerialNum(delivery.getSerialNum());
			_delivery.setStatus("4");//状态:已收货
			_delivery.setUpdateTime(new Date());
			_delivery.setUpdater(currenLoginName);
			delivery2Mapper.updateByPrimaryKeySelective(_delivery);
		}else{
			throw new Exception("没有找到发货单,发货id"+takeDelivery.getDeliverSerial());
		}*/
		
	}
    /**
	 * 
	 * @Description 获取销售订单信息
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/getSaleOrderInfo")
	@ResponseBody
	public Map getSaleOrderInfo(String serialNum,OrderInfo orderInfo) {
		orderInfo = orderService.selectById(serialNum);
		Map<String, Object> map = new HashMap<String, Object>();
		ContractVO contract = null;
		if(org.apache.commons.lang3.StringUtils.isNotEmpty(orderInfo.getContractSerial())){
    		contract=contractService.selectConbtractById(orderInfo.getContractSerial());
    	}
		if(contract!=null&&org.apache.commons.lang3.StringUtils.isNotEmpty(contract.getId())){
			ClauseDelivery clauseDelivery = clauseDeliveryService.selectByContractId(contract.getId());
    		map.put("clauseDelivery", clauseDelivery);
		}else{
			map.put("clauseDelivery", null);
		}
    	map.put("orderInfo", orderInfo);
    	Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名 
    	OrderMaterielExample m =new OrderMaterielExample();
    	//and 条件1
    	com.congmai.zhgj.web.model.OrderMaterielExample.Criteria criteria =  m.createCriteria();
    	criteria.andDelFlgEqualTo("0");
    	criteria.andOrderSerialEqualTo(serialNum);
    	List<DeliveryMaterielVO> orderMateriel = deliveryService.selectList(serialNum);
    	map.put("orderMateriel", orderMateriel);
    	map.put("currenLoginName", currenLoginName);
    	//  	map.put("deliverNum", "DE"+ApplicationUtils.getFromNumber());
    	return map;
	}
	
	
	
	/**
	 * 
	 * @Description 获取附件信息
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/getAttachFileInfo")
	@ResponseBody
	public Map<String, Object> getAttachFileInfo(String serialNum) {
		Map<String, Object> map = new HashMap<String, Object>();
    	
    	List<RelationFile> fileList=deliveryService.getAttachFileInfo(serialNum);
    	map.put("fileList", fileList);
    	return map;
	}
	
	
	/**
	 * 批量获取物料信息
	 * @return
	 */
	@RequestMapping(value = "/batchGetMaterielInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> batchGetMaterielInfo(@RequestBody String ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Materiel> list=deliveryService.batchGetMaterielInfo(ids);
		map.put("materielList", list);
		return map;
	}
	
    
  /*  *//**
     * @Description (保存订单物料信息单个保存)
     * @param request
     * @return
     *//*
    @RequestMapping(value="saveDeliveryMateriel",method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<DeliveryMaterielVO> saveDeliveryMateriel(Map<String, Object> map,DeliveryMaterielVO deliveryMateriel,HttpServletRequest request) {
        	try{
        		Subject currentUser = SecurityUtils.getSubject();
        		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
        		if(StringUtils.isEmpty(deliveryMateriel.getSerialNum())){
        			deliveryMateriel.setSerialNum(ApplicationUtils.random32UUID());
        			deliveryMateriel.setCreator(currenLoginName);
        			deliveryMateriel.setUpdater(currenLoginName);
        			deliveryService.insertDeliveryMateriel(deliveryMateriel);
        		}
        		
        		//附件
        		List<RelationFile> files=new ArrayList<RelationFile>();
        		
        		//如果附件不为空时执行添加操作
        		if(!StringUtils.isEmpty(deliveryMateriel.getAttachFile())){
        			String attachFile[]=deliveryMateriel.getAttachFile().split("&");
        			for(String detail:attachFile){
        				RelationFile item=new RelationFile();
        				String attachFileDetail[]=detail.split(",");
        				String file=attachFileDetail[0];
        				
        				//描述不为空时添加描述
        				String describe=null;
        				if(attachFileDetail.length>1){
        					if(attachFileDetail[1]!=null){
               				 describe=attachFileDetail[1];	
               				}	
        				}
        				
        				
        				item.setSerialNum(ApplicationUtils.random32UUID());
        				item.setRelationSerial(deliveryMateriel.getSerialNum());
        				item.setFileType("delivery");
        				item.setFileDescribe(describe);
        				item.setFile(file);
        				item.setUploader(currenLoginName);
        				item.setCreator(currenLoginName);
        				item.setUpdater(currenLoginName);
        				files.add(item);
        			}
        			
        			//批量添加附件
        			deliveryService.insertAttachFiles(files);
        		}
        		
        	}catch(Exception e){
        		//20180110 qhzhao System.out.println(e.getMessage());
        		return null;
        	}
        	deliveryMateriel =deliveryService.selectDeliveryMaterielById(deliveryMateriel.getSerialNum());
        	
        	if(StringUtils.isEmpty(deliveryMateriel.getMaterielNum())){
        		deliveryMateriel =deliveryService.selectDeliveryMaterielById2(deliveryMateriel.getSerialNum());	
        	}
        	
    	return new ResponseEntity<DeliveryMaterielVO>(deliveryMateriel, HttpStatus.CREATED);
    }*/
    
	
    private static BeanCopier beanCopier = BeanCopier.create(DeliveryMaterielVO.class,DeliveryMateriel.class,false);
    
	/**
     * @Description (保存订单物料信息)
     * @param request
     * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
     */
   @RequestMapping(value="saveAllDeliveryMateriel",method=RequestMethod.POST)
   @ResponseBody
   public Map<String,Object> saveDeliveryMateriel(@RequestBody  String params) throws JsonParseException, JsonMappingException, IOException {
	   
	   params = params.replace("\\", "");
	   List<DeliveryMaterielVO> deliveryMateriels;
	   Map<String,Object> map =new HashMap<String,Object>();
	   /*ObjectMapper objectMapper = new ObjectMapper();  
       JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, DeliveryMaterielVO.class);  
       deliveryMateriels = objectMapper.readValue(params, javaType);*/
	  deliveryMateriels = JSON.parseArray(params, DeliveryMaterielVO.class);
	  List<DeliveryMaterielVO> orderMateriels = deliveryService.selectList(deliveryMateriels.get(0).getOrderSerial());//取最新的数据判断
	  Boolean flag=false;//还可以发
	  Boolean isDel=false;//是否删除发货单
		OrderInfo o=orderService.selectById(deliveryMateriels.get(0).getOrderSerial());
		String  deliveryCount=o.getDeliveryCount();//订单已发数量
		String  materielCount=o.getMaterielCount();//订单总数量
		  BigDecimal deliveryCount1=new BigDecimal(deliveryCount);
		  BigDecimal materielCount1=new BigDecimal(materielCount);
		  if(deliveryCount1.compareTo(materielCount1)>=0){
			  flag=true;
			  isDel=true;//提示删除当前发货单
			  map.put("isDel", isDel);
			  map.put("flag", flag);
			  return map;
		  }else{
			  map.put("isDel", isDel);
		  }
	  for(DeliveryMaterielVO deliveryMateriel: deliveryMateriels){
		  for(DeliveryMaterielVO vo:orderMateriels){
			  if(deliveryMateriel.getOrderMaterielSerialNum().equals(vo.getOrderMaterielSerialNum())){//两个订单物料流水相同时
				  BigDecimal deliverCount=new BigDecimal(deliveryMateriel.getDeliverCount());
				  BigDecimal canDeliverCount=new BigDecimal(vo.getAmount()).subtract(new BigDecimal(vo.getDeliveredCount()==null?"0":vo.getDeliveredCount()));//可发数量=物料数量-已发数量
				  if(deliverCount.compareTo(canDeliverCount)>0){
					  flag=true;
					  deliveryMateriel.setDeliveredCount(vo.getDeliveredCount());//更新成最新的已发数量
					  deliveryMateriel.setDeliverCount("0");//发货数量置为0
					  map.put("deliveryMateriels", deliveryMateriels);//重新将最新的发货数据传到前台
					  map.put("flag", flag);
					  return map;
				  }
			  }
		  }
	  }
	  
	   try{
   		Subject currentUser = SecurityUtils.getSubject();
   		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
   		RelationFile  rf=new RelationFile();
   		rf.setDelFlg("1");
   		rf.setUpdateTime(new Date());
   		rf.setUpdater(currenLoginName);
   		
   		if(deliveryMateriels!=null&&deliveryMateriels.size()>0){
   			TakeDelivery takeDelivery = new TakeDelivery();
   			takeDelivery = takeDeliveryMapper.selectTakeDeliveryByDeliveryId(deliveryMateriels.get(0).getDeliverSerial());
   			DeliveryMaterielExample example = new 	DeliveryMaterielExample();
   			com.congmai.zhgj.web.model.DeliveryMaterielExample.Criteria cc = example.createCriteria();
   			cc.andDeliverSerialEqualTo(takeDelivery.getSerialNum()) ;
   			deliveryMaterielMapper.deleteByExample(example);
   			
   			
   	   		for(DeliveryMaterielVO deliveryMateriel:deliveryMateriels){
   	   		if(StringUtils.isEmpty(deliveryMateriel.getSerialNum())){
   	   			deliveryMateriel.setSerialNum(ApplicationUtils.random32UUID());
   	   			deliveryMateriel.setCreator(currenLoginName);
   	   			deliveryMateriel.setUpdater(currenLoginName);
   	   			deliveryMateriel.setOrderMaterielSerial(deliveryMateriel.getOrderMaterielSerialNum());
   	   			deliveryService.insertDeliveryMateriel(deliveryMateriel);
   	   			insertRelationFile(deliveryMateriel,currenLoginName);//保存附件
   	   			
   	   		}else{//修改发货物料
   	   			deliveryMateriel.setUpdateTime(new Date());
   	   			deliveryMateriel.setUpdater(currenLoginName);
   	   			deliveryService.updateDeliveryMateriel(deliveryMateriel);
   	   			//先删除原来的附件
   	   			RelationFileExample rfe=new RelationFileExample();
   	   			com.congmai.zhgj.web.model.RelationFileExample.Criteria c=rfe.createCriteria();
   	   			c.andRelationSerialEqualTo(deliveryMateriel.getSerialNum());
   	   			relationFileMapper.updateByExampleSelective(rf, rfe);
   	   			insertRelationFile(deliveryMateriel,currenLoginName);//保存附件
   	   		}
   	   		
   			DeliveryMateriel materiel = new DeliveryMateriel();
   			beanCopier.copy(deliveryMateriel, materiel, null);
   			materiel.setSerialNum(ApplicationUtils.random32UUID());
   			materiel.setDeliverSerial(takeDelivery.getSerialNum());
   			materiel.setDelFlg("0");
   			materiel.setAcceptCount(deliveryMateriel.getDeliverCount());
   			deliveryMaterielMapper.insert(materiel);
   	   		}
   		}      
   		
   		
   	}catch(Exception e){
   		//20180110 qhzhao System.out.println(e.getMessage());
   		
   	}
	   
	   String  serialNum=deliveryMateriels.get(0).getDeliverSerial();
   	deliveryMateriels = deliveryService.selectListForDetail(serialNum);
	if(deliveryMateriels.size()>0){
		String materielNum=deliveryMateriels.get(0).getMaterielNum();
		if(StringUtils.isEmpty(materielNum)){
		deliveryMateriels = deliveryService.selectListForDetail2(serialNum);	
		}	
	}
	   map.put("deliveryMateriels", deliveryMateriels);
	   map.put("flag", flag);
	   
	   return map;
   }
   
   public  void  insertRelationFile(DeliveryMaterielVO deliveryMateriel,String currenLoginName ){
	 //附件
	   		List<RelationFile> files=new ArrayList<RelationFile>();
	   		//如果附件不为空时执行添加操作
	   		if(!StringUtils.isEmpty(deliveryMateriel.getAttachFile())){
	   			String attachFile[]=deliveryMateriel.getAttachFile().split("&");
	   			for(String detail:attachFile){
	   				RelationFile item=new RelationFile();
	   				String attachFileDetail[]=detail.split(",");
	   				String file=attachFileDetail[0];
	   				
	   				//描述不为空时添加描述
	   				String describe=null;
	   				if(attachFileDetail.length>1){
	   					if(attachFileDetail[1]!=null){
	          				 describe=attachFileDetail[1];	
	          				}	
	   				}
	   				
	   				
	   				item.setSerialNum(ApplicationUtils.random32UUID());
	   				item.setRelationSerial(deliveryMateriel.getSerialNum());
	   				item.setFileType("delivery");
	   				item.setFileDescribe(describe);
	   				item.setFile(file);
	   				item.setUploader(currenLoginName);
	   				item.setCreator(currenLoginName);
	   				item.setUpdater(currenLoginName);
	   				files.add(item);
	   			}
	   			
	   			//批量添加附件
	   			deliveryService.insertAttachFiles(files);
	   		}
	   
   }
    
    /**
     * @Description (编辑订单物料信息)
     * @param request
     * @return
     */
    @RequestMapping(value="editDeliveryMateriel",method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<DeliveryMaterielVO>> editDeliveryMateriel(Map<String, Object> map,HttpServletRequest request,String params) {
    	JSONArray array = JSONArray.fromObject(params); 
    	List<DeliveryMaterielVO> list = JSONArray.toList(array, DeliveryMaterielVO.class);//
    	String deliverSerial=null;
    	if(list.size()>0){
    		deliverSerial=list.get(0).getDeliverSerial();
    		deliveryService.deleteOldDeliveryMateriel2(deliverSerial);
    	}

    	Subject currentUser = SecurityUtils.getSubject();
    	String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
    	for(DeliveryMaterielVO deliveryMaterielVO:list){
    		deliveryMaterielVO.setSerialNum(ApplicationUtils.random32UUID());
    		deliveryMaterielVO.setCreator(currenLoginName);
    		deliveryMaterielVO.setUpdater(currenLoginName);
    		deliveryService.insertDeliveryMateriel(deliveryMaterielVO);

    		//附件
    		List<RelationFile> files=new ArrayList<RelationFile>();

    		//如果附件不为空时执行添加操作
    		if(!StringUtils.isEmpty(deliveryMaterielVO.getAttachFile())){
    			String attachFile[]=deliveryMaterielVO.getAttachFile().split("&");
    			for(String detail:attachFile){
    				RelationFile item=new RelationFile();
    				String attachFileDetail[]=detail.split(",");
    				String file=attachFileDetail[0];

    				//描述不为空时添加描述
    				String describe=null;
    				if(attachFileDetail.length>1){
    					if(attachFileDetail[1]!=null){
    						describe=attachFileDetail[1];	
    					}	
    				}


    				item.setSerialNum(ApplicationUtils.random32UUID());
    				item.setRelationSerial(deliveryMaterielVO.getSerialNum());
    				item.setFileType("delivery");
    				item.setFileDescribe(describe);
    				item.setFile(file);
    				item.setUploader(currenLoginName);
    				item.setCreator(currenLoginName);
    				item.setUpdater(currenLoginName);
    				files.add(item);
    			}

    			//批量添加附件
    			deliveryService.insertAttachFiles(files);
    		}
    	}

    	List<DeliveryMaterielVO> deliveryMateriels=null;
    	deliveryMateriels = deliveryService.selectListForDetail(deliverSerial);
    	if(deliveryMateriels.size()>0){
    		String materielNum=deliveryMateriels.get(0).getMaterielNum();
    		if(StringUtils.isEmpty(materielNum)){
    			deliveryMateriels = deliveryService.selectListForDetail2(deliverSerial);	
    		}	
    	}

    	return new ResponseEntity<List<DeliveryMaterielVO>>(deliveryMateriels, HttpStatus.CREATED);
    }
    
    
    
    
    /**
     * 查询仓库地址
     * @param warehouseSerial
     * @return
     */
    @RequestMapping(value="selectAddress",method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Warehouse> selectAddress(String warehouseSerial){
    	
    	Warehouse warehouse=deliveryService.queryAddressById(warehouseSerial);
    	
    	return new ResponseEntity<Warehouse>(warehouse, HttpStatus.OK);
    }
    
    
    /**
     * 保存基本信息
     * @param delivery
     * @param deliveryTransport
     * @param takeDeliveryVO
     * @return
     */
    @RequestMapping(value="saveBasicInfo",method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<DeliveryVO>  saveBasicInfo(DeliveryVO delivery,DeliveryTransportVO deliveryTransport,TakeDeliveryVO takeDeliveryVO){
    	
    	//保存前判断发货数量是否满足条件
		Boolean  isDel=false;//是否删除当前发货单
		 Boolean flag=false;//还可以发
    	String orderSerial=delivery.getOrderSerial();
    	DeliveryVO deliveryVO=new  DeliveryVO();
		List<DeliveryMaterielVO> orderMateriels = deliveryService.selectList(orderSerial);//取最新的数据判断
		OrderInfo orderInfo=orderService.selectById(orderSerial);
		String  deliveryCount=orderInfo.getDeliveryCount();//订单已发数量
		String  materielCount=orderInfo.getMaterielCount();//订单总数量
		  BigDecimal deliveryCount1=new BigDecimal(deliveryCount);
		  BigDecimal materielCount1=new BigDecimal(materielCount);
		  if(deliveryCount1.compareTo(materielCount1)>=0){
			  flag=true;
			  isDel=true;//提示删除当前发货单
			  deliveryVO.setFlag(flag);
			  deliveryVO.setIsDel(isDel);
			  return new ResponseEntity<DeliveryVO>(deliveryVO, HttpStatus.CREATED);
		  }else{
			  deliveryVO.setIsDel(isDel);
		  }
		 
		List<DeliveryMaterielVO> deliveryMateriels1=null;//查出当前发货信息
		deliveryMateriels1= deliveryService.selectListForDetail(delivery.getSerialNum());
		if(deliveryMateriels1!=null&&deliveryMateriels1.size()>0){
			String materielNum=deliveryMateriels1.get(0).getMaterielNum();
			if(StringUtils.isEmpty(materielNum)){
			deliveryMateriels1 = deliveryService.selectListForDetail2(delivery.getSerialNum());	
			}	
		}
		  for(DeliveryMaterielVO deliveryMateriel: deliveryMateriels1){
			  for(DeliveryMaterielVO vo:orderMateriels){
				  if(deliveryMateriel.getOrderMaterielSerialNum().equals(vo.getOrderMaterielSerialNum())){//两个订单物料流水相同时
					  BigDecimal deliverCount=new BigDecimal(deliveryMateriel.getDeliverCount());
					  BigDecimal canDeliverCount=new BigDecimal(vo.getAmount()).subtract(new BigDecimal(vo.getDeliveredCount()==null?"0":vo.getDeliveredCount()));//可发数量=物料数量-已发数量
					  if(deliverCount.compareTo(canDeliverCount)>0){
						  flag=true;
						  deliveryVO.setFlag(flag);
						  deliveryVO.setDeliveryMateriels(deliveryMateriels1);
						  return new ResponseEntity<DeliveryVO>(deliveryVO, HttpStatus.CREATED);
					  }
				  }
			  }
		  }
    	//保存基本信息第一部分
    	Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
		String transportserialNum=deliveryTransport.getDeliveryTransportSerialNum();
    	String takeDeliverSerialNum=takeDeliveryVO.getTakeDeliveryVOSerialNum();
    	String deliverySerialNum=delivery.getSerialNum();
    	User user = UserUtil.getUserFromSession();
    	String comId = null;
    	if("deliveryInfo".equals(delivery.getType())){
    		OrderInfo o = orderService.selectById(delivery.getOrderSerial());
    	if(StringUtils.isEmpty(delivery.getSerialNum())){
    	delivery.setSerialNum(ApplicationUtils.random32UUID());
    	delivery.setStatus("0");
    	deliverySerialNum=delivery.getSerialNum();
		comId = userCompanyService.getUserComId(String.valueOf(user.getUserId()));
    	if(StringUtils.isEmpty(comId)){
    		delivery.setSupplyComId(null);
    		delivery.setShipper(null);
    		//delivery.setStatus("00");//待申请
    	}else{
//    		Company company=deliveryService.selectCompanyInfo(comId);
       		delivery.setSupplyComId(comId);
    	}
		delivery.setCreator(currenLoginName);
		if(o!=null){
			delivery.setBuyComId(o.getBuyComId());
		}
    	deliveryService.insertBasicInfo(delivery);
    	}else{
    		if(o!=null){
    			delivery.setBuyComId(o.getBuyComId());
    		}
    		deliveryService.updateBasicInfo(delivery);
    	}
    	
    	}
    	//保存基本信息第二部分
    	if("deliveryInfo".equals(delivery.getType())){
    	if(StringUtils.isEmpty(transportserialNum)){
    	deliveryTransport.setSerialNum(ApplicationUtils.random32UUID());
    	deliveryTransport.setCreator(currenLoginName);
    	deliveryTransport.setDeliverSerial(deliverySerialNum);
    	deliveryService.insertBasicInfoPartII(deliveryTransport);
    	}else{
    		deliveryTransport.setSerialNum(transportserialNum);
    		deliveryTransport.setUpdater(currenLoginName);
    		deliveryService.updateBasicInfoPartII(deliveryTransport);
    	}}
    	//保存基本信息第三部分
    	if("deliveryInfo".equals(delivery.getType())){
    	if(StringUtils.isEmpty(takeDeliverSerialNum)){
    	takeDeliveryVO.setSerialNum(ApplicationUtils.random32UUID());
    	takeDeliveryVO.setCreator(currenLoginName);
    	takeDeliveryVO.setDeliverSerial(deliverySerialNum);
    	takeDeliveryVO.setTakeDeliverNum(orderService.getNumCode("RE"));
    	deliveryService.insertBasicInfoPartIII(takeDeliveryVO);
    	}else{
    		takeDeliveryVO.setSerialNum(takeDeliverSerialNum);
    		takeDeliveryVO.setUpdater(currenLoginName);
    		deliveryService.updateBasicInfoPartIII(takeDeliveryVO);
    	}}
    	//保存之后查询
    	delivery=deliveryService.selectDetailById(delivery.getSerialNum());
    	if(StringUtils.isEmpty(delivery.getOrderNum())){
    	delivery=deliveryService.selectDetailById2(delivery.getSerialNum());	
    	}
    	delivery.setSerialNum(deliverySerialNum);
    	return new ResponseEntity<DeliveryVO>(delivery, HttpStatus.OK);
    }
    
    
    /**
     * 编辑基本信息
     * @param delivery
     * @param deliveryTransport
     * @param takeDeliveryVO
     * @return
     */
    @RequestMapping(value="editBasicInfo",method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<DeliveryVO>  editBasicInfo(DeliveryVO delivery,DeliveryTransportVO deliveryTransport,TakeDeliveryVO takeDeliveryVO){
    	//保存基本信息第一部分
    	String transportserialNum=delivery.getTransportserialNum();
    	String takeDeliverSerialNum=delivery.getTakeDeliverSerialNum();
    	
    	Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
		delivery.setUpdater(currenLoginName);
		delivery.setStatus("0");
    	deliveryService.updateBasicInfo(delivery);
    	
    	
    	//保存基本信息第二部分
    	deliveryTransport.setSerialNum(transportserialNum);
    	deliveryTransport.setUpdater(currenLoginName);
    	deliveryService.updateBasicInfoPartII(deliveryTransport);
    	
    	//保存基本信息第三部分
    	takeDeliveryVO.setSerialNum(takeDeliverSerialNum);
    	takeDeliveryVO.setUpdater(currenLoginName);
    	deliveryService.updateBasicInfoPartIII(takeDeliveryVO);
    	
    	//更新之后查询
    	delivery=deliveryService.selectDetailById(delivery.getSerialNum());
    	if(StringUtils.isEmpty(delivery.getOrderNum())){
    	delivery=deliveryService.selectDetailById2(delivery.getSerialNum());	
    	}
    	return new ResponseEntity<DeliveryVO>(delivery, HttpStatus.OK);
    }
    
    
    /**
	 * 
	 * @Description 调整发货申请
	 * @param taskId
	 * @param reApply
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/modifyDeliveryPlanApply/{taskId}", method = RequestMethod.POST, produces = "application/text;charset=UTF-8")
	@ResponseBody
	public String modifyDeliveryPlanApply(@PathVariable("taskId") String taskId,
			@RequestParam("processInstanceId") String processInstanceId,
			@RequestParam("reApply") Boolean reApply,
			@RequestParam("serialNum") String serialNum,
			@RequestParam("reason") String reason) throws Exception{
		
		String result = "";
		User user = UserUtil.getUserFromSession();	
		DeliveryVO record=new DeliveryVO();
		record.setUserId(user.getUserId());
		record.setUser_name(user.getUserName());
		record.setBusinessType(BaseVO.DELIVERY);
		record.setApplyDate(new Date());
		record.setBusinessKey(serialNum);
		record.setProcessInstanceId(processInstanceId);
        Map<String, Object> variables = new HashMap<String, Object>();
        String content = "";
        if(reApply){
        	//修改请假申请
        	record.setTitle(user.getUserName()+" 的发货申请！");
        	record.setStatus(BaseVO.PENDING);
	        content = "重新申请";
	        result = "任务办理完成，发货申请已重新提交！";
        }else{
        	record.setTitle(user.getUserName()+" 的发货申请已取消！");
        	record.setStatus(BaseVO.APPROVAL_FAILED);
        	content = "取消申请";
        	result = "任务办理完成，已经取消您的发货申请！";
        }
        try {
        	//保存之后查询
        	Delivery d=new Delivery();
        	d.setSerialNum(serialNum);
        	d.setStatus(record.getStatus());
        	 deliveryService.updateDelivery(d);//更新状态
        	/*record=deliveryService.selectDetailById(serialNum);*/
        	
			variables.put("entity", record);
			variables.put("reApply", reApply);
			this.processService.complete(taskId, content, user.getUserId().toString(), variables);
			
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
   	 * 
   	 * @Description 获取发货详情信息
   	 * @param ids
   	 * @return
   	 */
	@RequestMapping(value = "/getDeliveryInfo")
	@ResponseBody
	public Map<String, Object> getDeliveryInfo(String serialNum) {
		DeliveryVO delivery=null;
		Map<String, Object> map = new HashMap<String, Object>();
		delivery=deliveryService.selectDetailById(serialNum);
		if(StringUtils.isEmpty(delivery.getOrderNum())){
			delivery=deliveryService.selectDetailById2(delivery.getSerialNum());
		}else{
			OrderInfo o=orderService.selectById(delivery.getOrderSerial());
			if("1".equals(o.getContractContent().substring(4, 5))){//	有验收条款
				map.put("hasCheckData", true);
			}
		
		}
		String suppluComId=delivery.getSupplyComId();
		Company company=deliveryService.selectCompanyInfo(suppluComId);
		if(company!=null){
			delivery.setSupplyComId(company.getComName());
			delivery.setShipper(company.getComName());
		}
		if(!StringUtils.isEmpty(delivery.getWarehouseSerial())){
			Warehouse w=warehouseService.selectOne(delivery.getWarehouseSerial());
		}
		StockInOutRecord sir=deliveryService.selectStockInOutRecordByDeliveryId(delivery.getSerialNum(),StringUtil.isEmpty(delivery.getSupplyComId())?"out":"in");//查找关联的已出库出库单信息
		map.put("showTransport", true);
		if(!StringUtil.isEmpty(delivery.getSupplyComId())){
			map.put("showTransport", true);
		}
		if(sir!=null&&StringUtil.isEmpty(delivery.getSupplyComId())){
			delivery.setTransportType(sir.getTransportType());
			delivery.setTransport(sir.getTransport());
			delivery.setShipNumber(sir.getShipNumber());//transportContact  transportContactNum  transportRemark
			delivery.setTransportContact(sir.getTransportContact());
			delivery.setTransportContactNum(sir.getTransportContactNum());
			delivery.setTransportRemark(sir.getTransportRemark());
			map.put("hasOutData", true);
		}else if(sir!=null&&!StringUtil.isEmpty(delivery.getSupplyComId())){//采购发货
			map.put("hasInData", true);
		}
		
		map.put("delivery", delivery);

		List<DeliveryMaterielVO> deliveryMateriels=null;
		deliveryMateriels = deliveryService.selectListForDetail(serialNum);
		if(deliveryMateriels!=null&&deliveryMateriels.size()>0){
			String materielNum=deliveryMateriels.get(0).getMaterielNum();
			if(StringUtils.isEmpty(materielNum)){
			deliveryMateriels = deliveryService.selectListForDetail2(serialNum);	
			}	
		}
		
		for(DeliveryMaterielVO deliveryMaterielVO:deliveryMateriels){
			String attachFile="";
			List<RelationFile> files=deliveryService.getAttachFileInfo(deliveryMaterielVO.getSerialNum());
			for(RelationFile relationFile:files){
				String file=relationFile.getFile();
				String remark=relationFile.getRemark();
				attachFile=attachFile+file+","+remark+"&";
			}
			deliveryMaterielVO.setAttachFile(attachFile);
			deliveryMaterielVO.setFiles(files);
		}
		
		map.put("deliveryMateriels", deliveryMateriels);
		return map;
	}
   	
   	
   	/**
	 * 
	 * @Description 启动发货流程审批
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/startDeliveryPlanProcess", method = RequestMethod.POST)
	@ResponseBody
	public String startDeliveryPlanProcess(@RequestBody String params) throws Exception {
		String flag = "0"; //默认失败
    	params = params.replace("\\", "");
		ObjectMapper objectMapper = new ObjectMapper();  
		Delivery delivery = new Delivery();
		try {
			delivery = objectMapper.readValue(params, Delivery.class);
		} catch (Exception e1) {
			return flag;
		} 
		delivery.setStatus(BaseVO.PENDING);
		
		//启动订单审批测试流程-start
		User user = UserUtil.getUserFromSession();
		DeliveryVO deliveryVO = new DeliveryVO();
		deliveryVO.setSerialNum(delivery.getSerialNum());
		deliveryVO.setUserId(user.getUserId());
		deliveryVO.setUser_name(user.getUserName());
		deliveryVO.setTitle(user.getUserName()+" 的发货计划申请");
		deliveryVO.setBusinessType(BaseVO.DELIVERY); 			//业务类型：发货计划
		deliveryVO.setStatus(BaseVO.PENDING);					//审批中
		deliveryVO.setApplyDate(new Date());
    	processBaseService.insert(deliveryVO);
		String businessKey = deliveryVO.getSerialNum().toString();
		deliveryVO.setBusinessKey(businessKey);
		try {
			String processInstanceId = this.processService.startDeliveryPlanProcess(deliveryVO);
//                message.setStatus(Boolean.TRUE);
//    			message.setMessage("订单流程已启动，流程ID：" + processInstanceId);
			
			//申请加入流程已办
			HistoricTaskVO historicTaskVO = new HistoricTaskVO();
			historicTaskVO.setTaskId(ApplicationUtils.random32UUID());
			historicTaskVO.setProcessInstanceId(processInstanceId);
			historicTaskVO.setStartTime(new Date());
			historicTaskVO.setEndTime(new Date());
			historicTaskVO.setProcessDefId(deliveryVO.getBusinessKey());
			historicTaskVO.setUserId(user.getUserId().toString());
			
			processBaseService.insertHistoricTask(historicTaskVO);
		    logger.info("processInstanceId: "+processInstanceId);
		    
		    flag = "1";
		    delivery.setProcessInstanceId(processInstanceId);
		    deliveryService.updateDelivery(delivery);//更新申请原因和状态
		    List<DeliveryMaterielVO> list= deliveryService.selectListForDetail(delivery.getSerialNum());
		  DeliveryVO   d=deliveryService.selectDetailById(delivery.getSerialNum());
		    OrderInfo o=orderService.selectById(d.getOrderSerial());
		    if(list!=null&&list.size()>0){
				for(DeliveryMaterielVO deliveryMaterielVO:list){
					OrderMateriel om=orderMaterielService.selectById(deliveryMaterielVO.getOrderMaterielSerialNum());
					om.setApplyCount(StringUtil.sum(om.getApplyCount(),deliveryMaterielVO.getDeliverCount()));
					orderMaterielService.update(om);
					o.setApplyCount(StringUtil.sum(o.getApplyCount(),deliveryMaterielVO.getDeliverCount()));
				}
			}
		    orderService.update(o);
		} catch (ActivitiException e) {
//            	message.setStatus(Boolean.FALSE);
		    if (e.getMessage().indexOf("no processes deployed with key") != -1) {
		        logger.warn("没有部署流程!", e);
//        			message.setMessage("没有部署流程，请联系系统管理员，在[流程定义]中部署相应流程文件！");
		    } else {
		        logger.error("启动流程失败：", e);
//                    message.setMessage("启动订单流程失败，系统内部错误！");
		    }
		    throw e;
		} catch (Exception e) {
		    logger.error("启动流程失败：", e);
//                message.setStatus(Boolean.FALSE);
//                message.setMessage("启动订单流程失败，系统内部错误！");
		    throw e;
		}
        //启动订单审批测试流程-end
		return flag;
	}
	@RequestMapping(value = "/complate/{taskId}", method = RequestMethod.POST, produces = "application/text;charset=UTF-8")
	@ResponseBody
	public String complete(@RequestParam("serialNum") String serialNum,
			@RequestParam("content") String content,
			@RequestParam("processInstanceId") String processInstanceId,
			@RequestParam("completeFlag") Boolean completeFlag,
			@PathVariable("taskId") String taskId,
			RedirectAttributes redirectAttributes) throws Exception {
		User user = UserUtil.getUserFromSession();
		String result = "";
		try {
			
			DeliveryVO delivery=deliveryService.selectDetailById(serialNum);
			
			String orderSerial=delivery.getOrderSerial();
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("serialNum", serialNum);
			map.put("updater", user.getUserId());
			map.put("orderSerial", orderSerial);
			deliveryService.updateOrderWhenDeliveryComlete(map);
			DeliveryVO baseDelivery = (DeliveryVO) this.runtimeService.getVariable(delivery.getProcessInstanceId(), "entity");
					
			Map<String, Object> variables = new HashMap<String, Object>();
			variables.put("isPass", completeFlag);
			if (!completeFlag) {
				baseDelivery.setTitle(baseDelivery.getUser_name()
						+ " 的发货计划申请失败,需修改后重新提交！");
				delivery.setStatus(BaseVO.APPROVAL_FAILED);
				variables.put("entity", baseDelivery);
			}
			// 完成任务
			this.processService.complete(taskId, content, user.getUserId()
					.toString(), variables);

			if (completeFlag) {
				// 此处需要修改，不能根据人来判断审批是否结束。应该根据流程实例id(processInstanceId)来判定。
				// 判断指定ID的实例是否存在，如果结果为空，则代表流程结束，实例已被删除(移到历史库中)
				ProcessInstance pi = this.runtimeService
						.createProcessInstanceQuery()
						.processInstanceId(delivery.getProcessInstanceId())
						.singleResult();
				if (BeanUtils.isBlank(pi)) {
					delivery.setStatus("0");//审批完成更新为已确认发货
					delivery.setReason(content);
					result = "任务办理完成！";
					this.deliveryService.updateBasicInfo(delivery);
					 goDeliveryWithoutConfirm(serialNum);//直接确认发货
				}
			}else{
				delivery.setReason(content);
				this.deliveryService.updateBasicInfo(delivery);
				result = "任务办理完成！";
			}
			

			/*Map<String, Object> map1 = goDeliveryWithoutConfirm(serialNum);// 直接确认发货,获取map
			Boolean flag = (Boolean) map1.get("flag");
			Boolean isDel = (Boolean) map1.get("isDel");
			if (flag) {// 提示删除或修改发货单
				if (isDel) {
					result = StaticConst.getInfo("delPlease");
				} else {
					result = StaticConst.getInfo("changePlease");
				}
			} else {
				this.deliveryService.updateBasicInfo(delivery);
				result = "任务办理完成！";
			}*/
			
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
	 * @Description (导出发货信息)
	 * @param request
	 * @return
	 */
	@RequestMapping("exportDelivery")
	public void exportDelivery(Map<String, Object> map,HttpServletRequest request,HttpServletResponse response) {
/*		Map<String, Object> dataMap = new HashMap<String, Object>();
		Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名 
		List<DeliveryVO> deliveryList=deliveryService.findAllDeliveryList(currenLoginName);*/
		Map<String, Object> dataMap = new HashMap<String, Object>();
		Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名 
		//List<DeliveryVO> contractList=deliveryService.findAllDeliveryList(currenLoginName);
		User user = UserUtil.getUserFromSession();
    	List<String> comIds = new ArrayList<String>();
    	if(user!=null){
			comIds = userCompanyService.getComIdsByUserId(String.valueOf(user.getUserId()));
		}
		DeliveryVO query = new DeliveryVO();
		query.setCreator(currenLoginName);
		query.setSupplyComIds(comIds);
		List<DeliveryVO> contractList=deliveryService.findAllDeliveryList(query);

		dataMap.put("deliveryList",contractList);
		ExcelUtil.export(request, response, dataMap, "delivery", "发货信息");
	}
	
	
	@RequestMapping("/viewDelivery")
    public String viewDelivery() {
        return "delivery/viewDelivery";
    }
	
	
	@RequestMapping("/applyDelivery")
    public String applyDelivery() {
        return "delivery/applyDelivery";
    }
	
	
	@RequestMapping("/auditDelivery")
    public String auditDelivery() {
        return "delivery/auditDelivery";
    }


	 /**
     * 添加发货页面
     * @return 添加发货页面url
     */
    @RequestMapping("/addDelivery")
    public String addDelivery() {
    	User user = UserUtil.getUserFromSession();
    	String comId = null;
		comId = userCompanyService.getUserComId(String.valueOf(user.getUserId()));
        return "delivery/addDelivery";
    }
    
    /**
     * 查询仓库地址
     * @param warehouseSerial
     * @return
     */
    @RequestMapping(value="getSupplyComId",method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Company> getSupplyComId(String warehouseSerial){
    	
    	User user = UserUtil.getUserFromSession();
    	String comId = null;
		comId = userCompanyService.getUserComId(String.valueOf(user.getUserId()));
		
		
		Company company=deliveryService.selectCompanyInfo(comId);
    	
    	return new ResponseEntity<Company>(company, HttpStatus.OK);
    }
    
    
    

	@RequestMapping(value = "/toApproval/{taskId}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody Map<String, Object> toApproval(
			@PathVariable("taskId") String taskId)
			throws NumberFormatException, Exception {
		Task task = this.taskService.createTaskQuery().taskId(taskId)
				.singleResult();
		// 根据任务查询流程实例
		String processInstanceId = task.getProcessInstanceId();
		ProcessInstance pi = this.runtimeService.createProcessInstanceQuery()
				.processInstanceId(processInstanceId).singleResult();
		DeliveryVO deliveryVO = (DeliveryVO) this.runtimeService
				.getVariable(pi.getId(), "entity");
		deliveryVO.setTask(task);
		deliveryVO.setProcessInstanceId(processInstanceId);
		List<CommentVO> commentList = this.processService
				.getComments(processInstanceId);
		String taskDefinitionKey = task.getTaskDefinitionKey();
		logger.info("taskDefinitionKey: " + taskDefinitionKey);
		String result = null;
		if ("modifyApply".equals(taskDefinitionKey)) {
			result = "modify";
		} else {
			result = "audit";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("actionType", result);
		map.put("deliveryVO", deliveryVO);
		map.put("commentList", commentList);
		return map;
	}
	
	/**
	 * 上传执行
	 * @param file（上传的文件）
	 * @return
	 */
	public String uploadFile(MultipartFile file){
		String filePath = getClasspath()+"uploadAttachFiles/";
		String randomName=UUID.randomUUID().toString().toUpperCase().replaceAll("-", ""); 
		String fileName = fileUp(file, filePath,randomName);
		//20180110 qhzhao System.out.println(fileName);
		return fileName;
	}


	/**
	 * 复制文件
	 * @param file (文件对象）
	 * @param filePath （文件路径）
	 * @param fileName   （文件名）
	 * @return
	 */
	public  String fileUp(MultipartFile file, String filePath, String fileName){
		String extName = ""; // 扩展名格式：
		try {

			if (file.getOriginalFilename().lastIndexOf(".") >= 0){
				extName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			}

			copyFile(file.getInputStream(), filePath, fileName+extName).replaceAll("-", "");

		} catch (IOException e) {
			//20180110 qhzhao System.out.println(e);
		}
		return fileName+extName;
	}


	/**
	 * 写文件到当前目录的upload目录中
	 * @param in
	 * @param fileName
	 * @throws IOException
	 */
	private  String copyFile(InputStream in, String dir, String realName)
			throws IOException {
		File file = mkdirsmy(dir,realName);
		FileUtils.copyInputStreamToFile(in, file);
		return realName;
	}


	/**判断路径是否存在，否：创建此路径
	 * @param dir  文件路径
	 * @param realName  文件名
	 * @throws IOException 
	 */
	public  File mkdirsmy(String dir, String realName) throws IOException{
		File file = new File(dir, realName);
		if (!file.exists()) {
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			file.createNewFile();
		}
		return file;
	}




	/**获取classpath1
	 * @return
	 */
	public  String getClasspath(){
		String path = (String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))+"../../").replaceAll("file:/", "").replaceAll("%20", " ").trim();	
		if(path.indexOf(":") != 1){
			path = File.separator + path;
		}
		return path;
	}


	/**
	 * 删除发货
	 * @param contractVO
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/deleteDeliveryS", method = RequestMethod.POST)
	public ResponseEntity<Void> deleteDeliveryS(@RequestBody String ids) {
		if ("".equals(ids) || ids == null) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		deliveryService.deleteDeliveryS(ids); 
		return new ResponseEntity<Void>(HttpStatus.OK);
	}


	/**
	 * 通过id查询合同详情
	 * @param ids（前台所传递的id）
	 * @return
	 */
	@RequestMapping(value = "/selectConbtractById", method = RequestMethod.GET)
	public ResponseEntity<ContractVO> selectConbtractById(String ids) {

		ContractVO c=contractService.selectConbtractById(ids);
		return new ResponseEntity<ContractVO>(c, HttpStatus.OK);
	}

	/**
	 * 跳转到编辑页面
	 * @return
	 */
	@RequestMapping(value = "/editDeliveryPage")
	public String editDeliveryPage(String id,String view) {
		return "delivery/editDeliveryPage";
	}
	
	
	/**
	 * 跳转到调整页面
	 * @return
	 */
	@RequestMapping(value = "/editAuditDelivery")
	public String editAuditDelivery(String id,String view) {
		return "delivery/editAuditDelivery";
	}
	
	
	 /**
     * @Description (下载导入模板)
     * @param request
     * @return
     */
    @RequestMapping("downloadImportTemp")
    public void downloadContractTemp(Map<String, Object> map,HttpServletRequest request,HttpServletResponse response) {
    	ExcelUtil.importTempDownLoad(request, response, "contractImport");
    }
    
    
    /**
     * @Description (合同信息导入)
     * @param request
     * @return
     */
    @RequestMapping("contractImport")
    @ResponseBody
    public Map<String,String> contractImport(@RequestParam(value = "excelFile") MultipartFile excelFile,HttpServletRequest request,HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String, String>();
    	 try {
		     
			ExcelReader excelReader = new ExcelReader(excelFile.getInputStream());
			excelReader.readExcelContent(new RowHandler() {
				@Override
				public void handle(List<Object> row,int i) throws Exception {
					if(!CollectionUtils.isEmpty(row)){
						try{
							ContractVO contract = new ContractVO();
							contract.setId(ApplicationUtils.random32UUID());
							contract.setContractNum(row.get(0).toString());
							contract.setComId(row.get(1).toString());
							contract.setContractType(row.get(2).toString());
							contract.setServiceModel(row.get(3).toString());
							
							SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd");
							contract.setSignDate(sdf.parse(row.get(4).toString()));
							contract.setSigner(row.get(5).toString());
							contract.setStartDate(sdf.parse(row.get(6).toString()));
							contract.setEndDate(sdf.parse(row.get(7).toString()));
							contract.setSettlementClause(row.get(8).toString());
							Subject currentUser = SecurityUtils.getSubject();
							String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
							contract.setCreator(currenLoginName);
							contractService.insertContract(contract);
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



	@RequestMapping(value="/resourceDownload",method=RequestMethod.POST) //匹配的是href中的download请求
	public void download(@RequestParam("project_id") Integer projectId, HttpServletResponse response) throws IOException {



	}
	
	/**
	 * 确认收货
	 * @param request（http 请求对象）
	 * @param ucBuilder
	 * @return 操作结果
	 * @throws Exception 
	 */
	@RequestMapping(value = "/goTakeDelivery", method = RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> goTakeDelivery(String serialNum, HttpServletRequest request,UriComponentsBuilder ucBuilder) throws Exception {
		Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名 
		String flags1="0";
		Map<String,Object>map=new HashMap<String,Object>();
		try{
    		if(org.apache.commons.lang3.StringUtils.isNotEmpty(serialNum)){
    			DeliveryVO delivery1=deliveryService.selectDetailById(serialNum);
    			Delivery delivery=new Delivery();
    			delivery.setStatus(DeliveryVO.TAKEDELIVER_DELIVERY);//已收货
    			delivery.setSerialNum(serialNum);
    			delivery.setUpdater(currenLoginName);
    			deliveryService.updateDelivery(delivery);
    			OrderInfo o=new OrderInfo();
    			o.setSerialNum(delivery1.getOrderSerial());
    			o.setDeliverStatus(OrderInfo.TAKEDELIVER);//已收货
    			o.setUpdater(currenLoginName);
    			orderService.updateStatus(o);
    		/*TakeDelivery takeDelivery = takeDeliveryMapper.selectTakeDeliveryByDeliveryId(serialNum);
    		TakeDelivery takeDelivery1=new TakeDelivery();
    		takeDelivery1.setSerialNum(takeDelivery.getSerialNum());
    		takeDelivery1.setStatus(TakeDelivery.COMPLETE_TAKEDELIVERY);//已收货
    			takeDeliveryService.updateTakeDelivery(takeDelivery1);*/
    		}
    	}catch(Exception e){
    		logger.warn(e.getMessage(), e);
    		flags1 = "1";
    	}
		//收货消息
		//EventExample.getEventPublisher().publicSendMessageEvent(new SendMessageEvent(delivery,MessageConstants.DELIVERY));
		 map.put("flag", flags1);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.CREATED);
	}
	
	 /**
     * @Description (根据企业id找联系地址列表)
     * @param request
     * @return
     */
    @RequestMapping(value = "/getCompanyaddress", method = RequestMethod.POST)
    public ResponseEntity<List<CompanyAddress>>  getCompanyaddressList(HttpServletRequest request,String  comId) {
    	List<CompanyAddress> companyAddresss=null;
    if("pt".equals(comId)){
    	 comId=companyservice.selectComIdByComName(StaticConst.getInfo("comName"));
    	 companyAddresss = companyAddressService.selectListByComId(comId);
 		return new ResponseEntity<List<CompanyAddress>>(companyAddresss, HttpStatus.OK);
    }
    if(StringUtil.isEmpty(comId)){//供应商发货时
    	User user = UserUtil.getUserFromSession();
		comId = userCompanyService.getUserComId(String.valueOf(user.getUserId()));//查询当前登录人所属企业类型
		companyAddresss = companyAddressService.selectListByComId(comId);
 		return new ResponseEntity<List<CompanyAddress>>(companyAddresss, HttpStatus.OK);
   }else{
	   companyAddresss = companyAddressService.selectListByComId(comId);
		return new ResponseEntity<List<CompanyAddress>>(companyAddresss, HttpStatus.OK);
   }
   
    
	}
    
    /**
     * @Description (查找进行中发货单)
     * @param orderSerialNum
     * @return
     */
    @RequestMapping(value="getDoingDelivery",method=RequestMethod.POST)
    @ResponseBody
    public DeliveryVO getDoingDelivery(@RequestBody String orderSerialNum) {

		DeliveryVO query = new DeliveryVO();
		query.setOrderSerial(orderSerialNum);
		query.setStatus("isInit");
		List<DeliveryVO> deliveryList=deliveryService.findAllDeliveryList(query);
		if(!CollectionUtils.isEmpty(deliveryList)){
			return deliveryList.get(0);
		}else{
			return null;
		}
    	
    }
    
    public Map<String,Object> goDeliveryWithoutConfirm(String serialNum) throws Exception {
		//先判断现在的发货数量与未发数量符合条件才可确认发货,否则提示用户修改
		Map<String,Object> map=new HashMap<String,Object>();
		Boolean  isDel=false;//是否删除当前发货单
		 Boolean flag=false;//还可以发
		DeliveryVO delivery=deliveryService.selectDetailById(serialNum);
		String orderSerial=delivery.getOrderSerial();
		List<DeliveryMaterielVO> orderMateriels = deliveryService.selectList(orderSerial);//取最新的数据判断
		OrderInfo o=orderService.selectById(orderSerial);
		String  deliveryCount=o.getDeliveryCount();//订单已发数量
		String  materielCount=o.getMaterielCount();//订单总数量
		  BigDecimal deliveryCount1=new BigDecimal(deliveryCount);
		  BigDecimal materielCount1=new BigDecimal(materielCount);
		  if(deliveryCount1.compareTo(materielCount1)>=0){
			  flag=true;
			  isDel=true;//提示删除当前发货单
			  map.put("isDel", isDel);
			  map.put("flag", flag);
			  return map;
		  }else{
			  map.put("isDel", isDel);
		  }
		 
		List<DeliveryMaterielVO> deliveryMateriels1=null;//查出当前发货信息
		deliveryMateriels1= deliveryService.selectListForDetail(serialNum);
		if(deliveryMateriels1!=null&&deliveryMateriels1.size()>0){
			String materielNum=deliveryMateriels1.get(0).getMaterielNum();
			if(StringUtils.isEmpty(materielNum)){
			deliveryMateriels1 = deliveryService.selectListForDetail2(serialNum);	
			}	
		}
		  for(DeliveryMaterielVO deliveryMateriel: deliveryMateriels1){
			  for(DeliveryMaterielVO vo:orderMateriels){
				  if(deliveryMateriel.getOrderMaterielSerialNum().equals(vo.getOrderMaterielSerialNum())){//两个订单物料流水相同时
					  BigDecimal deliverCount=new BigDecimal(deliveryMateriel.getDeliverCount());
					  BigDecimal canDeliverCount=new BigDecimal(vo.getAmount()).subtract(new BigDecimal(vo.getDeliveredCount()==null?"0":vo.getDeliveredCount()));//可发数量=物料数量-已发数量
					  if(deliverCount.compareTo(canDeliverCount)>0){
						  flag=true;
						  map.put("flag", flag);
						  return map;
					  }
				  }
			  }
		  }
		Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名 

		map.put("serialNum", serialNum);
		map.put("updater", currenLoginName);
		
	
		TakeDelivery takeDelivery = new TakeDelivery();
		takeDelivery = takeDeliveryMapper.selectTakeDeliveryByDeliveryId(serialNum);
		
		
		map.put("orderSerial", orderSerial);
		map.put("orderInfo", o);
		Boolean createQG=StaticConst.getInfo("waimao").equals(o.getTradeType())&&!StringUtils.isEmpty(o.getSupplyComId());//供应商发货/平台发货是否产生清关单
		deliveryService.updateOrderWhenDeliveryComlete(map);
		map.put("createQG", createQG);
		deliveryService.goDelivery(map);//外贸供应商发货
		if(!createQG){//不产生清关单(供应商发货/平台发货)
			OrderInfo orderInfo=new OrderInfo();
			orderInfo.setSerialNum(orderSerial);
			orderInfo.setDeliverStatus(OrderInfo.DELIVER);
			//设置订单发货数量，用于更新
			orderInfo.setDeliveryCount(o.getDeliveryCount());
			List<DeliveryMaterielVO> deliveryMateriels=null;
			deliveryMateriels = deliveryService.selectListForDetail(delivery.getSerialNum());
			if(deliveryMateriels!=null&&deliveryMateriels.size()>0){
				for(DeliveryMaterielVO deliveryMaterielVO:deliveryMateriels){
					orderInfo.setDeliveryCount(StringUtil.sum(orderInfo.getDeliveryCount(),deliveryMaterielVO.getDeliverCount()));
				}
			}
			
			Delivery delivery1=new  Delivery();
			delivery1.setSerialNum(delivery.getSerialNum());
			delivery1.setStatus(DeliveryVO.COMPLETE);
			if("1".equals(o.getContractContent().substring(4, 5))){//有验收条款
					if(StringUtils.isEmpty(o.getSupplyComId())){//平台发货 产生检验单
						//平台发货-->  需要检验 --> 生成出库检验单
						StockInOutCheck stockInOutCheck=new StockInOutCheck();
						stockInOutCheck.setSerialNum(ApplicationUtils.random32UUID());
						stockInOutCheck.setDeliverSerial(serialNum);
						stockInOutCheck.setCheckNum(orderService.getNumCode("QU"));
						stockInOutCheck.setTakeDeliverSerial("checkout");
						stockInOutCheck.setChecker(currenLoginName);
						stockInOutCheck.setCreator(currenLoginName);
						stockInOutCheck.setCreateTime(new Date());
						stockInOutCheck.setUpdater(currenLoginName);
						stockInOutCheck.setUpdateTime(new Date());
						stockInOutCheck.setStatus("0");//待检验
						stockInOutCheck.setCheckDate(new Date());
						stockInOutCheck.setDelFlg("0");
						stockInOutCheckService.insert(stockInOutCheck);
						//更新订单状态至出库待检验
						orderInfo.setDeliverStatus(orderInfo.WAIT_OUT_CHECK);
						delivery1.setStatus(DeliveryVO.WAIT_CHECK);
						takeDelivery.setStatus(TakeDelivery.APPLY_COMPLETE);
						//出库检验通知仓储人员/采购
				    	EventExample.getEventPublisher().publicSendMessageEvent(new SendMessageEvent(stockInOutCheck,MessageConstants.IN_TO_WAITCHECK));
					}else{
						//供应商发货--> 不走清关 --> 不需收货 --> 需要检验 --> 生成入库检验单
						
						if(takeDelivery!=null){
							takeDelivery.setStatus(TakeDelivery.APPLY_COMPLETE); //待检验
							this.createStockInCheckRecord(takeDelivery,currenLoginName);
							orderInfo.setDeliverStatus(orderInfo.WAIT_IN_CHECK);//已收货待检验
							delivery1.setStatus(DeliveryVO.WAIT_CHECK);
						}
						
					}
			}else{//没有验收条款
				if(StringUtils.isEmpty(o.getSupplyComId())){//平台发货 产生出库单
					//平台发货-->  不需要检验 --> 生成出库单
					
					orderInfo.setDeliverStatus(orderInfo.WAIT_OUTRECORD);//待出库
					StockInOutRecord stockInOutRecord=new StockInOutRecord();
					stockInOutRecord.setInOutNum(orderService.getNumCode("OU"));
					stockInOutRecord.setSerialNum(ApplicationUtils.random32UUID());
					stockInOutRecord.setDelFlg("0");
					stockInOutRecord.setStatus("0");
					stockInOutRecord.setDeliverSerial(serialNum);
					stockInOutRecord.setTakeDeliverSerial("");
					stockInOutRecord.setCreator(currenLoginName);
					stockInOutRecord.setCreateTime(new Date());
					stockInOutRecord.setUpdater(currenLoginName);
					stockInOutRecord.setUpdateTime(new Date());
					stockInOutRecordMapper.insert(stockInOutRecord);
					//更新订单状态至待出库
					orderInfo.setDeliverStatus(orderInfo.WAIT_OUTRECORD);
					delivery1.setStatus(DeliveryVO.WAITRECORD);
					takeDelivery.setStatus(TakeDelivery.CHECK_COMPLETE);
					//出库通知消息通知仓储人员
			    	EventExample.getEventPublisher().publicSendMessageEvent(new SendMessageEvent(stockInOutRecord,MessageConstants.IN_TO_STOCK));
				}else{//供应商发货--> 不走清关 --> 不需收货 --> 不需要检验 --> 生成入库单
					takeDelivery.setStatus(TakeDelivery.CHECK_COMPLETE); //已完成
					orderInfo.setDeliverStatus(orderInfo.WAIT_INRECORD);//待入库
					delivery1.setStatus(DeliveryVO.WAIT_IN_RECORD);
					//生成入库单
					StockInOutRecord stockInOutRecord=new StockInOutRecord();
					stockInOutRecord.setSerialNum(ApplicationUtils.random32UUID());
					stockInOutRecord.setTakeDeliverSerial(takeDelivery.getSerialNum());
					stockInOutRecord.setDeliverSerial("");
					stockInOutRecord.setInOutNum(orderService.getNumCode("IN"));
					stockInOutRecord.setDelFlg("0");
					stockInOutRecord.setStatus("0");
					stockInOutRecord.setCreator(currenLoginName);
					stockInOutRecord.setCreateTime(new Date());
					stockInOutRecord.setUpdater(currenLoginName);
					stockInOutRecord.setUpdateTime(new Date());
					stockInOutRecordMapper.insert(stockInOutRecord);
					//出库通知消息通知仓储人员
			    	EventExample.getEventPublisher().publicSendMessageEvent(new SendMessageEvent(stockInOutRecord,MessageConstants.IN_TO_STOCK));
					
				}
				
			}
			orderInfoMapper.updateByPrimaryKeySelective(orderInfo);//更新订单状态
			delivery2Mapper.updateByPrimaryKeySelective(delivery1);//更新发货单状态
			takeDeliveryMapper.updateByPrimaryKeySelective(takeDelivery);//更新收货单状态
		}
		
		//发货消息
		EventExample.getEventPublisher().publicSendMessageEvent(new SendMessageEvent(delivery,MessageConstants.DELIVERY));
		 map.put("flag", flag);
		return map;
	}
//申请前判断数量
    @RequestMapping(value = "/goApplyDelivery", method = RequestMethod.GET)
	public ResponseEntity<Map<String,Object>>goApplyDelivery(String serialNum) {
    	//先判断现在的发货数量与未发数量符合条件才可确认发货,否则提示用户修改
    			Map<String,Object> map=new HashMap<String,Object>();
    			Boolean  isDel=false;//是否删除当前发货单
    			 Boolean flag=false;//还可以发
    			DeliveryVO delivery=deliveryService.selectDetailById(serialNum);
    			String orderSerial=delivery.getOrderSerial();
    			List<DeliveryMaterielVO> orderMateriels = deliveryService.selectList(orderSerial);//取最新的数据判断
    			OrderInfo o=orderService.selectById(orderSerial);
    			String  applyCount=o.getApplyCount();//订单已申请数量
    			String  materielCount=o.getMaterielCount();//订单总数量
    			  BigDecimal applyCount1=new BigDecimal(applyCount==null?"0":applyCount);
    			  BigDecimal materielCount1=new BigDecimal(materielCount);
    			  if(applyCount1.compareTo(materielCount1)>=0){
    				  flag=true;
    				  isDel=true;//提示删除当前发货单
    				  map.put("isDel", isDel);
    				  map.put("flag", flag);
    				  return  new ResponseEntity<Map<String,Object>>(map, HttpStatus.CREATED);
    			  }else{
    				  map.put("isDel", isDel);
    			  }
    			 
    			List<DeliveryMaterielVO> deliveryMateriels1=null;//查出当前发货信息
    			deliveryMateriels1= deliveryService.selectListForDetail(serialNum);
    			if(deliveryMateriels1!=null&&deliveryMateriels1.size()>0){
    				String materielNum=deliveryMateriels1.get(0).getMaterielNum();
    				if(StringUtils.isEmpty(materielNum)){
    				deliveryMateriels1 = deliveryService.selectListForDetail2(serialNum);	
    				}	
    			}
    			  for(DeliveryMaterielVO deliveryMateriel: deliveryMateriels1){
    				  for(DeliveryMaterielVO vo:orderMateriels){
    					  if(deliveryMateriel.getOrderMaterielSerialNum().equals(vo.getOrderMaterielSerialNum())){//两个订单物料流水相同时
    						  BigDecimal deliverCount=new BigDecimal(deliveryMateriel.getDeliverCount());
    						  OrderMateriel om=orderMaterielService.selectById(vo.getOrderMaterielSerialNum());
    						  BigDecimal canApplyCount=new BigDecimal(om.getAmount()).subtract(new BigDecimal(om.getApplyCount()==null?"0":om.getApplyCount()));//可申请数量=物料数量-已申请数量
    						  BigDecimal currentCount=new BigDecimal(materielService.getCurrentCount(om.getMaterielSerial()));//该物料实时自建库存数量
    						  if(deliverCount.compareTo(canApplyCount)>0||deliverCount.compareTo(currentCount)>0){
    							  flag=true;
    							  map.put("flag", flag);
    							  return new ResponseEntity<Map<String,Object>>(map, HttpStatus.CREATED);
    						  }
    					  }
    				  }
    			  }
    			  map.put("flag", flag);  
    			  return  new ResponseEntity<Map<String,Object>>(map, HttpStatus.CREATED);
    }
    /**
	 * 判断订单物料是否均无库存
	 * 
	 */
	@RequestMapping(value = "/judgeIsStock", method = RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> judgeIsStock(String serialNum, HttpServletRequest request,UriComponentsBuilder ucBuilder) throws Exception {
		Boolean  flag=true;
		Map<String,Object>map=new HashMap<String,Object>();
		try{
			OrderMaterielExample m =new OrderMaterielExample();
	    	//and 条件1
	    	com.congmai.zhgj.web.model.OrderMaterielExample.Criteria criteria =  m.createCriteria();
	    	criteria.andDelFlgEqualTo("0");
	    	criteria.andOrderSerialEqualTo(serialNum);
			List<OrderMateriel>omList=orderMaterielService.selectList(m);
			if(org.apache.commons.collections.CollectionUtils.isNotEmpty(omList)){
				for(OrderMateriel om:omList){
					String inCountString = stockService.getCountInAmountForZijian(om.getMaterielSerial());
    				String outCountString = stockService.getCountOutAmountForZijian(om.getMaterielSerial());
    				if(new BigDecimal(inCountString==null?"0":inCountString).compareTo(new BigDecimal(outCountString==null?"0":outCountString))>0){//存在有物料有库存
    					flag=false;
    					map.put("flag", flag);
    					return new ResponseEntity<Map<String,Object>>(map, HttpStatus.CREATED);
    				}
				}
			}
			
    	}catch(Exception e){
    		logger.warn(e.getMessage(), e);
    	}
		map.put("flag", flag);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.CREATED);
	}
}
