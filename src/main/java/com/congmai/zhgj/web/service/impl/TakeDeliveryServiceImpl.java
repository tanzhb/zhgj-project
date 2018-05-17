package com.congmai.zhgj.web.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.cmd.GetSubTasksCmd;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.congmai.zhgj.core.feature.orm.mybatis.Page;
import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.core.util.HttpClientUtil;
import com.congmai.zhgj.core.util.MessageConstants;
import com.congmai.zhgj.core.util.SendEmail;
import com.congmai.zhgj.core.util.StringUtil;
import com.congmai.zhgj.log.annotation.OperationLog;
import com.congmai.zhgj.core.util.Constants;
import com.congmai.zhgj.web.dao.CustomsFormMapper;
import com.congmai.zhgj.web.dao.Delivery2Mapper;
import com.congmai.zhgj.web.dao.DeliveryMapper;
import com.congmai.zhgj.web.dao.DeliveryMaterielMapper;
import com.congmai.zhgj.web.dao.DeliveryTransportMapper;
import com.congmai.zhgj.web.dao.MaterielMapper;
import com.congmai.zhgj.web.dao.OrderInfoMapper;
import com.congmai.zhgj.web.dao.OrderMaterielMapper;
import com.congmai.zhgj.web.dao.RelationFileMapper;
import com.congmai.zhgj.web.dao.StockInBatchMapper;
import com.congmai.zhgj.web.dao.StockInOutCheckMapper;
import com.congmai.zhgj.web.dao.StockInOutRecordMapper;
import com.congmai.zhgj.web.dao.StockMapper;
import com.congmai.zhgj.web.dao.StockOutBatchMapper;
import com.congmai.zhgj.web.dao.TakeDeliveryMapper;
import com.congmai.zhgj.web.enums.StaticConst;
import com.congmai.zhgj.web.event.EventExample;
import com.congmai.zhgj.web.event.SendMessageEvent;
import com.congmai.zhgj.web.model.ClauseSettlementDetail;
import com.congmai.zhgj.web.model.Company;
import com.congmai.zhgj.web.model.CompanyContact;
import com.congmai.zhgj.web.model.CustomsForm;
import com.congmai.zhgj.web.model.Delivery;
import com.congmai.zhgj.web.model.DeliveryExample;
import com.congmai.zhgj.web.model.DeliveryMateriel;
import com.congmai.zhgj.web.model.DeliveryMaterielExample;
import com.congmai.zhgj.web.model.DeliveryMaterielVO;
import com.congmai.zhgj.web.model.DeliveryTransport;
import com.congmai.zhgj.web.model.DeliveryTransportExample;
import com.congmai.zhgj.web.model.DeliveryVO;
import com.congmai.zhgj.web.model.Materiel;
import com.congmai.zhgj.web.model.OrderInfo;
import com.congmai.zhgj.web.model.OrderMateriel;
import com.congmai.zhgj.web.model.Stock;
import com.congmai.zhgj.web.model.StockExample;
import com.congmai.zhgj.web.model.StockInBatch;
import com.congmai.zhgj.web.model.StockInBatchExample;
import com.congmai.zhgj.web.model.StockInOutCheck;
import com.congmai.zhgj.web.model.StockInOutRecord;
import com.congmai.zhgj.web.model.StockInOutRecordExample;
import com.congmai.zhgj.web.model.StockOutBatch;
import com.congmai.zhgj.web.model.StockOutBatchExample;
import com.congmai.zhgj.web.model.TakeDelivery;
import com.congmai.zhgj.web.model.TakeDeliveryExample;
import com.congmai.zhgj.web.model.TakeDeliveryParams;
import com.congmai.zhgj.web.model.TakeDeliverySelectExample;
import com.congmai.zhgj.web.model.Warehouse;
import com.congmai.zhgj.web.model.TakeDeliverySelectExample.Criteria;
import com.congmai.zhgj.web.model.TakeDeliveryVO;
import com.congmai.zhgj.web.service.ClauseDeliveryService;
import com.congmai.zhgj.web.service.CompanyAddressService;
import com.congmai.zhgj.web.service.CompanyContactService;
import com.congmai.zhgj.web.service.CompanyService;
import com.congmai.zhgj.web.service.ContractService;
import com.congmai.zhgj.web.service.DeliverFileService;
import com.congmai.zhgj.web.service.DeliveryService;
import com.congmai.zhgj.web.service.IProcessService;
import com.congmai.zhgj.web.service.MaterielService;
import com.congmai.zhgj.web.service.OrderMaterielService;
import com.congmai.zhgj.web.service.OrderService;
import com.congmai.zhgj.web.service.ProcessBaseService;
import com.congmai.zhgj.web.service.StockInBatchService;
import com.congmai.zhgj.web.service.StockInOutCheckService;
import com.congmai.zhgj.web.service.TakeDeliveryService;
import com.congmai.zhgj.web.service.UserCompanyService;
import com.congmai.zhgj.web.service.WarehouseService;
import com.congmai.zhgj.wms.model.ArriveBill;
import com.congmai.zhgj.wms.model.ArriveData;
import com.congmai.zhgj.wms.model.Product;
import com.congmai.zhgj.wms.model.Supplier;
import com.congmai.zhgj.wms.model.Unit;
import com.congmai.zhgj.wms.model.WmsReturn;
import com.congmai.zhgj.wms.model.WmsReturnData;

@Service
@PropertySource("classpath:/wmsAPI.properties")
public class TakeDeliveryServiceImpl extends GenericServiceImpl<TakeDelivery,String>
		implements TakeDeliveryService {

	@Autowired
	Environment env;
	
	@Resource
	private TakeDeliveryMapper takeDeliveryMapper;
	
	@Resource
	private Delivery2Mapper delivery2Mapper;
	
	@Resource
	private DeliveryTransportMapper deliveryTransportMapper;
	
	@Resource
	private DeliveryMaterielMapper deliveryMaterielMapper;
	
	@Resource
	private StockInOutRecordMapper stockInOutRecordMapper;
	
	@Resource
	private StockInOutCheckMapper stockInOutCheckMapper;
	
	@Resource
	private OrderInfoMapper orderInfoMapper;
	
	@Resource
	private StockMapper stockMapper;
	
	@Resource
	private OrderMaterielMapper orderMaterielMapper;
	
	@Resource
	private StockOutBatchMapper stockOutBatchMapper;
	
	@Resource
	private StockInBatchMapper stockInBatchMapper;
	
	@Resource
	private MaterielMapper materielMapper;
	
	@Resource
	private DeliveryMapper deliveryMapper;
	
	@Resource
	private CustomsFormMapper customsFormMapper;
	
	@Resource
    private DeliveryService deliveryService;
	
	@Resource
    private OrderService  orderService;
	@Resource
	private ContractService contractService;
	@Resource
	private CompanyContactService companyContactService;

	/**
	 * 仓库service
	 */
	@Resource
    private WarehouseService  warehouseService;
	
	@Resource
	private CompanyService  companyservice;
	
	@Resource
	private MaterielService materielService;
	@Override
	public GenericDao<TakeDelivery, String> getDao() {
	
		return this.takeDeliveryMapper;
	}

	@Override
	public Page<Delivery> selectByPage(Delivery takeDelivery) {
		TakeDeliverySelectExample example = new TakeDeliverySelectExample();
		example.setPageIndex(0);
		example.setPageSize(-1);
		Criteria c =  example.createCriteria();
		c.andDelFlgEqualTo("0");
		if(StringUtils.isNotBlank(takeDelivery.getStatus())&&!"noInit".equals(takeDelivery.getStatus())){
			c.andStatusEqualTo(takeDelivery.getStatus());
		}
		if("noInit".equals(takeDelivery.getStatus())){
			c.andStatusNotEqualTo("0");//状态不为初始
		}
		
		if(StringUtils.isNotEmpty(takeDelivery.getBuyComId())){ //根据用户所在企业筛选收货单
//			c.andDeliverBuyComIdEqualTo(takeDelivery.getBuyComId());
			c.andDeliverBuyComIdEqualTo(takeDelivery.getBuyComId());
			c.andStatusNotEqualTo("0");//状态不为初始
		Criteria c2 = example.or();
		c2.andStatusEqualTo("0");//状态为初始
//		c2.andDeliverBuyComIdEqualTo(takeDelivery.getBuyComId());
		c2.andDeliverBuyComIdEqualTo(takeDelivery.getBuyComId());
		c2.andDeliverTypeEqualTo("采购发货");
	
		}else{
			c.andDeliverBuyComIdIsNull();
			if("noInit".equals(takeDelivery.getStatus())){//不看查询初始状态的收货（或者说入库计划）
				c.andStatusNotEqualTo("0");//状态不为初始
				c.andTakeDeliverSeriaIsNotNull();
//				c.andDeliverBuyComIdEqualTo(takeDelivery.getSupplyComId());
				if(StringUtils.isNotEmpty(takeDelivery.getSupplyComId())){
					c.andDeliverSupplyComIdEqualTo(takeDelivery.getSupplyComId());
				}
			}else{
				//平台可查状态为初始的代发货
//				c.andStatusNotEqualTo("0");//状态不为初始
				c.andTakeDeliverSeriaIsNotNull();
				Criteria c2 = example.or();
				c2.andStatusEqualTo("0");//状态为初始，只能是代发货
				c2.andDeliverTypeEqualTo("代发货");
				if(StringUtils.isNotBlank(takeDelivery.getStatus())){
					c2.andStatusEqualTo(takeDelivery.getStatus());
				}
				c2.andDelFlgEqualTo("0");
				c2.andDeliverBuyComIdIsNull();
			}
			
		}

		Page<Delivery> page = new Page<Delivery>();
		page.setResult(takeDeliveryMapper.selectListByExample(example));
		page.setTotalCount(takeDeliveryMapper.selectListByExample(example).size());
//		page.setTotalCount(takeDeliveryMapper.countListByExample(example));
		
		return page;
	}
    private static BeanCopier beanCopier = BeanCopier.create(DeliveryMateriel.class,DeliveryMateriel.class,false);
	@Override
	@OperationLog(operateType = "add" ,operationDesc = "代发货" ,objectSerial= "{serialNum}")
	public void insertTakeDelivery(Delivery delivery,TakeDelivery takeDelivery,DeliveryTransport deliveryTransport,List<DeliveryMateriel> deliveryMateriels,
			String currenLoginName) {
		//takeDeliveryParams = getTakeDeliveryData(takeDeliveryParams,currenLoginName);
		if("11".equals(delivery.getStatus())){
//			delivery.setStatus("0");
			EventExample.getEventPublisher().publicSendMessageEvent(new SendMessageEvent(delivery,MessageConstants.NOTICESUPPLY));//通知供应商修改
		}
		delivery2Mapper.insert(delivery);
		if(deliveryTransport==null){
			deliveryTransport=new DeliveryTransport();
			deliveryTransport.setSerialNum(ApplicationUtils.random32UUID());
			deliveryTransport.setDeliverSerial(delivery.getSerialNum());
		}
		deliveryTransportMapper.insert(deliveryTransport);
		takeDelivery.setTakeDeliverNum(orderService.getNumCode("RE"));
		takeDeliveryMapper.insert(takeDelivery);
		for(DeliveryMateriel materiel : deliveryMateriels){
			deliveryMaterielMapper.insert(materiel);
			
			DeliveryMateriel tmateriel = new DeliveryMateriel();
   			beanCopier.copy(materiel, tmateriel, null);
   			tmateriel.setSerialNum(ApplicationUtils.random32UUID());
   			tmateriel.setDeliverSerial(takeDelivery.getSerialNum());
   			tmateriel.setDelFlg("0");
   			tmateriel.setAcceptCount(materiel.getDeliverCount());
   			deliveryMaterielMapper.insert(tmateriel);
		}
		/*if(!StaticConst.getInfo("buygetmerchant").equals(delivery.getDeliverType())){//非采购发货
		confirmDelivery(delivery, takeDelivery, currenLoginName);//确认发货操作
		}*/
		confirmDelivery(delivery, takeDelivery, currenLoginName);//确认发货操作
	}
	
	@Override
	public Delivery selectByTakeDeliveryPrimaryKey(String serialNum) {
		Delivery delivery = null;
		TakeDelivery takeDelivery = takeDeliveryMapper.selectByPrimaryKey(serialNum);
		if(takeDelivery!=null){
		      delivery = delivery2Mapper.selectByPrimaryKey(takeDelivery.getDeliverSerial());
		  if(StringUtils.isNotEmpty(delivery.getOrderSerial())){
			  delivery = this.takeDeliveryMapper.selectByTakeDeliveryPrimaryKey(serialNum);
		  }else{
			  delivery = this.takeDeliveryMapper.selectByTakeDeliveryPrimaryKeyForOtherType(serialNum);
		  }
		}
		//Delivery delivery = this.takeDeliveryMapper.selectByTakeDeliveryPrimaryKey(serialNum);
		//DeliveryMaterielExample example = new DeliveryMaterielExample();
		//example.createCriteria().andDelFlgEqualTo("0").andDeliverSerialEqualTo(delivery.getTakeDelivery().getSerialNum());
		//return this.takeDeliveryMapper.selectByTakeDeliveryPrimaryKey(serialNum);
		return delivery;
	}

	@Override
	public void deleteBatch(List<String> serialNumArray) {
		TakeDeliveryExample example = new TakeDeliveryExample();
		TakeDelivery record = new TakeDelivery();
		record.setDelFlg("1");
		example.createCriteria().andSerialNumIn(serialNumArray);
		takeDeliveryMapper.updateByExampleSelective(record, example);
	}

	@Override
	public void updateTakeDelivery(Delivery delivery,TakeDelivery takeDelivery,DeliveryTransport deliveryTransport,List<DeliveryMateriel> deliveryMateriels,
			String currenLoginName) {
		//takeDeliveryParams = getTakeDeliveryData(takeDeliveryParams,currenLoginName);
		
		DeliveryExample d_example = new DeliveryExample();
		d_example.createCriteria().andSerialNumEqualTo(delivery.getSerialNum());
		delivery2Mapper.updateByExampleSelective(delivery,d_example);
		if("11".equals(delivery.getStatus())){//平台通知供应商发货
		/*	OrderInfo o=new OrderInfo();
			o.setSerialNum(delivery.getOrderSerial());
			o.setStatus(OrderInfo.WAIT_SUPPLY_CONFIRMED);
			orderInfoMapper.updateByPrimaryKeySelective(o);*/
			EventExample.getEventPublisher().publicSendMessageEvent(new SendMessageEvent(delivery,MessageConstants.NOTICESUPPLY));//通知供应商修改
			
		}
		if(deliveryTransport!=null){
			DeliveryTransportExample dt_example = new DeliveryTransportExample();
			dt_example.createCriteria().andSerialNumEqualTo(deliveryTransport.getSerialNum());
			deliveryTransportMapper.updateByExampleSelective(deliveryTransport,dt_example);
		}
		
		TakeDeliveryExample td_example = new TakeDeliveryExample();
		td_example.createCriteria().andSerialNumEqualTo(takeDelivery.getSerialNum());
		takeDeliveryMapper.updateByExampleSelective(takeDelivery,td_example);
		
		DeliveryMateriel dm = new DeliveryMateriel();
		dm.setDelFlg("1");
		DeliveryMaterielExample dmExample = new DeliveryMaterielExample();
		dmExample.createCriteria().andDeliverSerialEqualTo(delivery.getSerialNum());
		deliveryMaterielMapper.updateByExampleSelective(dm,dmExample);
		
		DeliveryMaterielExample example = new 	DeliveryMaterielExample();
			com.congmai.zhgj.web.model.DeliveryMaterielExample.Criteria cc = example.createCriteria();
			cc.andDeliverSerialEqualTo(takeDelivery.getSerialNum()) ;
			deliveryMaterielMapper.deleteByExample(example);
		for(DeliveryMateriel materiel : deliveryMateriels){
				deliveryMaterielMapper.insert(materiel);
				
				DeliveryMateriel tmateriel = new DeliveryMateriel();
	   			beanCopier.copy(materiel, tmateriel, null);
	   			tmateriel.setSerialNum(ApplicationUtils.random32UUID());
	   			tmateriel.setDeliverSerial(takeDelivery.getSerialNum());
	   			tmateriel.setDelFlg("0");
	   			tmateriel.setAcceptCount(materiel.getDeliverCount());
	   			deliveryMaterielMapper.insert(tmateriel);
		}
		/*if(!StaticConst.getInfo("buygetmerchant").equals(delivery.getDeliverType())){//非采购发货
			confirmDelivery(delivery, takeDelivery, currenLoginName);//确认发货操作
		}*/
		confirmDelivery(delivery, takeDelivery, currenLoginName);//确认发货操作
	}
	@Override
	public  void  confirmDelivery(Delivery delivery,
			TakeDelivery takeDelivery, String currenLoginName) {
		if("1".equals(delivery.getStatus())){//平台确定代发货
			OrderInfo orderInfonew=orderInfoMapper.selectByPrimaryKey(delivery.getOrderSerial());
//			Boolean createQG=StaticConst.getInfo("waimao").equals(orderInfonew.getTradeType());//是否产生清关单
			Boolean createQG=false;
			OrderInfo orderInfo=new OrderInfo();
			Delivery delivery1=new  Delivery();
			delivery1.setSerialNum(delivery.getSerialNum());
			orderInfo.setSerialNum(delivery.getOrderSerial());
			//设置订单发货数量，用于更新
			OrderInfo o=orderService.selectById(delivery.getOrderSerial());
			orderInfo.setDeliveryCount(o.getDeliveryCount());
			List<DeliveryMaterielVO> deliveryMateriels=null;
			deliveryMateriels = deliveryService.selectListForDetail(delivery.getSerialNum());
			if(deliveryMateriels!=null&&deliveryMateriels.size()>0){
				for(DeliveryMaterielVO deliveryMaterielVO:deliveryMateriels){
					orderInfo.setDeliveryCount(StringUtil.sum(orderInfo.getDeliveryCount(),deliveryMaterielVO.getDeliverCount()));
				}
			}
			//发完订单数量将其它未发货的发货单更新为已失效
			if(new BigDecimal(o.getMaterielCount()).compareTo(new BigDecimal(orderInfo.getDeliveryCount()))==0){//发完订单数量
				Map<String,Object>map=new HashMap<String,Object>();
				map.put("serialNum", delivery.getSerialNum());
				map.put("orderSerial",o.getSerialNum());
				takeDeliveryMapper.updateOtherTakeDeliveryStatus(map);
				deliveryMapper.updateOtherDeliveryStatus(map);
				
			}
			if(createQG){
				Map<String,Object>map=new HashMap<String,Object>();
				map.put("serialNum", delivery.getSerialNum());
				map.put("currenLoginName", currenLoginName);
				map.put("orderSerial", delivery.getOrderSerial());
				deliveryService.createCustomsClearanceForm(map);//内有清关提醒消息
				orderInfo.setDeliverStatus(OrderInfo.CLEARANCE);
				
				delivery1.setStatus(DeliveryVO.WAIT_OUT);//待清关
				takeDelivery.setStatus(TakeDelivery.WAIT_Cearance);//收货单更新为待清关
				orderInfoMapper.updateByPrimaryKeySelective(orderInfo);//更新订单状态
				delivery2Mapper.updateByPrimaryKeySelective(delivery1);//更新发货单状态
			}else{
				if(orderInfonew.getContractContent()!=null&&"1".equals(orderInfonew.getContractContent().substring(4, 5))){//有验收条款
					//供应商发货--> 不走清关 --> 不需收货 --> 需要检验 --> 生成入库检验单
					if(takeDelivery!=null){
						takeDelivery.setStatus(TakeDelivery.APPLY_COMPLETE); //待检验
						this.createStockInCheckRecord(takeDelivery,currenLoginName);//内有检验提醒
						orderInfo.setDeliverStatus(orderInfo.WAIT_IN_CHECK);//已收货待检验
						delivery1.setStatus(DeliveryVO.WAIT_CHECK);
						
					}
				}else{//供应商发货--> 不走清关 --> 不需收货 --> 不需要检验 --> 生成入库单
					takeDelivery.setStatus(TakeDelivery.CHECK_COMPLETE); //已完成
					orderInfo.setDeliverStatus(orderInfo.WAIT_INRECORD);//待入库
					delivery1.setStatus(DeliveryVO.WAIT_IN_RECORD);
					//生成入库单
					StockInOutRecord stockInOutRecord=new StockInOutRecord();
					
					//为wms生成到货通知,并设置返回的id
					stockInOutRecord.setWmsDeliveryId(createWmsArriveBill(delivery,takeDelivery));
			    	
					
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
			
			//按结算条款中的签订合同节点生成付款
			String orderString = delivery.getOrderSerial();
			String nodeString = ClauseSettlementDetail.FHH;
			contractService.findPaymentNode(orderString, nodeString);
		}
	}

	@Override
	public void insertStockInData(StockInOutRecord record,List<DeliveryMateriel> deliveryMateriels,
			String currenLoginName,String type) {
		
		//StockInOutRecord record = takeDeliveryParams.getRecord();
		String recordSerial = ApplicationUtils.random32UUID();
		record.setSerialNum(recordSerial);
		record.setCreator(currenLoginName);
		record.setCreateTime(new Date());
		record.setUpdater(currenLoginName);
		record.setUpdateTime(new Date());
		if(StringUtils.isEmpty(record.getTakeDeliverSerial())&&StringUtils.isEmpty(record.getDeliverSerial())){
			if("in".equals(type)){
				record.setInOutFlag("1"); //入库
			}else if("out".equals(type)){
				record.setInOutFlag("0"); //出库
			}
		}
		record.setStatus("1");
		record.setDelFlg("0");
		stockInOutRecordMapper.insert(record);
		
		List<DeliveryMateriel> materiels = deliveryMateriels; //这里是入库的物料信息
		for(DeliveryMateriel materiel : materiels){
			if(StringUtils.isEmpty(materiel.getSerialNum())){
				materiel.setSerialNum(ApplicationUtils.random32UUID());
				materiel.setStockInOutRecordSerial(recordSerial);
				materiel.setDeliverSerial("");
				materiel.setUpdater(currenLoginName);
				materiel.setUpdateTime(new Date());
				materiel.setCreator(currenLoginName);
				materiel.setCreateTime(new Date());
				materiel.setDelFlg("0");
				deliveryMaterielMapper.insert(materiel);
			}else{
				DeliveryMaterielExample example = new DeliveryMaterielExample();
				example.createCriteria().andSerialNumEqualTo(materiel.getSerialNum());
				materiel.setUpdater(currenLoginName);
				materiel.setUpdateTime(new Date());
				deliveryMaterielMapper.updateByExampleSelective(materiel, example);
			}
			//materiel.setSerialNum(ApplicationUtils.random32UUID());
			//materiel.setDeliverSerial(record.getTakeDeliverSerial());
			//deliveryMaterielMapper.insert(materiel);
//			createStock(materiel,new StockExample(),currenLoginName);
		}
		
	}

	private void createStock(DeliveryMateriel deliveryMateriel,StockExample stockExample,String currenLoginName) {//生成自建库存
		OrderMateriel orderMateriel=orderMaterielMapper.selectByPrimaryKey(deliveryMateriel.getOrderMaterielSerial());//获取订单物料信息
		OrderInfo orderInfo=orderInfoMapper.selectByPrimaryKey(orderMateriel.getOrderSerial());
		OrderInfo saleOrderInfo=orderInfoMapper.selectByOrderNum(orderInfo.getOrderSerial());
		com.congmai.zhgj.web.model.StockExample.Criteria criteria=stockExample.createCriteria();
		
		Boolean isStockZijian=true;//默认是自建库存
		//StaticConst.getInfo("dailiBuy").equals(orderInfo.getOrderType())||StaticConst.getInfo("dailiSale").equals(orderInfo.getOrderType())
		if(!isStockZijian){//代理销售/代理采购
			criteria.andMaterielSerialEqualTo(orderMateriel.getMaterielSerial()).andManageTypeEqualTo("2").andDelFlgEqualTo("0");
			if(saleOrderInfo!=null){
				criteria.andMaterielOwnerEqualTo(saleOrderInfo.getBuyComId());//代管库存有唯一的物权方
			}
			isStockZijian=false;
		}else{
			criteria.andMaterielSerialEqualTo(orderMateriel.getMaterielSerial()).andManageTypeEqualTo("1").andDelFlgEqualTo("0");//自建库存
		}
		
		List<Stock>  stocks=stockMapper.selectByExample(stockExample);
		if(CollectionUtils.isEmpty(stocks)){//不存在此物料的库存,就直接新建一条库存
			Stock stock=new Stock();
			stock.setStockNum(orderService.getNumCode("IV"));
			stock.setSerialNum(ApplicationUtils.random32UUID());
			stock.setCurrentAmount(deliveryMateriel.getStockCount());
			stock.setDelFlg("0");
			stock.setCreator(currenLoginName);
			stock.setCreateTime(new Date());
			stock.setUpdater(currenLoginName);
			stock.setUpdateTime(new Date());
			stock.setMaterielSerial(orderMateriel.getMateriel().getSerialNum());
			if (isStockZijian) {
				stock.setManageType("1");// 自建库存
				stock.setMaterielOwner("");//StaticConst.getInfo("comName")
				stock.setServiceParty("");//默认中航国际
			}else{
				stock.setManageType("2");// 代管库存
				if(saleOrderInfo!=null){
					stock.setMaterielOwner(saleOrderInfo.getBuyComId());
				}
				stock.setServiceParty("");
				//stock.setServiceParty(StaticConst.getInfo("comName"));
			}
			stockMapper.insert(stock);
			stockMapper.updateStockNumUsed(stock.getStockNum());
			
		}/*else{//已存在此物料的库存,更新库存数量
//		Integer currenAmount=Integer.parseInt(stocks.get(0).getCountInAmount()==null?"0":stocks.get(0).getCountInAmount())-Integer.parseInt(stocks.get(0).getCountOutAmount()==null?"0":stocks.get(0).getCountOutAmount());//获取当前库存数
//		if(isStockZijian){//自建库存
//		Stock stock=new Stock();
//		stock.setSerialNum(stocks.get(0).getSerialNum());
//		stock.setCurrentAmount(currenAmount-Integer.parseInt(deliveryMateriel.getStockCount())+"");
		//stockMapper.updateByPrimaryKey(stock);
		}else{
			//判断物权方是否相同,相同更新代管库存,不同新建代管库存
		}*/
	//}
		
	}

	@Override
	public Page<Delivery> selectStockInListByPage(StockInOutRecord record) {
		StockInOutRecordExample example = new StockInOutRecordExample();
		example.setPageIndex(0);
		example.setPageSize(-1);
		example.createCriteria().andDelFlgEqualTo("0");
		Page<Delivery> page = new Page<Delivery>();
		page.setResult(stockInOutRecordMapper.selectListByExample(example));
		page.setTotalCount(stockInOutRecordMapper.countByExample(example));
		return page;
	}

	@Override
	public StockInOutRecord selectStockInOutRecordByPrimayKey(String serialNum,String type) {
	    if("in".equals(type)){
	    	return stockInOutRecordMapper.selectStockInInfoByPrimaryKey(serialNum);
	    }else{
	    	return stockInOutRecordMapper.selectStockOutInfoByPrimaryKey(serialNum);
	    }
		
	}

	@Override
	public void deleteStockInInfo(List<String> serialNumArray) {
		StockInOutRecordExample example = new StockInOutRecordExample();
		StockInOutRecord record = new StockInOutRecord();
		record.setDelFlg("1");
		example.createCriteria().andSerialNumIn(serialNumArray);
		stockInOutRecordMapper.updateByExampleSelective(record, example);
		for(String serialNum : this.removeDuplicate(serialNumArray)){
			StockInOutRecord delete_record = stockInOutRecordMapper.selectStockInInfoByPrimaryKey(serialNum);
			if(delete_record!=null){
				if(delete_record.getDelivery()==null){
				//	clearStockInInfoFormMateriels(delete_record.getDelivery().getDeliveryMateriels());
					List<DeliveryMateriel> deliveryMateriels = deliveryMaterielMapper.getListByStockSerial(delete_record.getSerialNum());
					if(CollectionUtils.isNotEmpty(deliveryMateriels)){
						clearStockInInfoFormMateriels(deliveryMateriels);
					}
				}else{
					clearStockInInfoFormMateriels(delete_record.getDelivery().getDeliveryMateriels());
				}
			}
		}
		
	}

	@Override
	@OperationLog(operateType = "add" ,operationDesc = "入库" ,objectSerial= "{serialNum}")
	public void updateStockInData(StockInOutRecord record,List<DeliveryMateriel> deliveryMateriels,
			String currenLoginName,String type) {
		//StockInOutRecord record = takeDeliveryParams.getRecord();
		
		
		Delivery old_delivery = null;
		if("in".equals(type)){
			//获取更新前的收货id
			StockInOutRecord stockInOutRecord = stockInOutRecordMapper.selectByPrimaryKey(record.getSerialNum());
			String takeDeliverySerial = stockInOutRecord.getTakeDeliverSerial();
			old_delivery = takeDeliveryMapper.selectByTakeDeliveryPrimaryKey(takeDeliverySerial); //之前的收货id
			//clearStockInInfoFormMateriels(old_delivery.getDeliveryMateriels());//清除之前的出入库物料信息
			
			//更新入库记录
			record.setUpdater(currenLoginName);
			record.setUpdateTime(new Date());
			StockInOutRecordExample example = new StockInOutRecordExample();
			example.createCriteria().andSerialNumEqualTo(record.getSerialNum());
//			record.setInOutNum(null);//不更新编号，否则影响sql执行效率
//			stockInOutRecordMapper.updateByExampleSelective(record, example);
		if("1".equals(record.getStatus())){
			//入库完成状态处理
			stockInEndHandle(old_delivery.getOrderSerial(),takeDeliverySerial,currenLoginName);		
			
			//按结算条款中的签订合同节点生成付款
			String orderString = old_delivery.getOrderSerial();
			String nodeString = ClauseSettlementDetail.RKH;
			contractService.findPaymentNode(orderString, nodeString);	
		}else{
			stockInOutRecordMapper.updateByExampleSelective(record, example);
		}	
		}else{
			//获取更新前的发货id
			StockInOutRecord stockInOutRecord = stockInOutRecordMapper.selectByPrimaryKey(record.getSerialNum());
			String deliverySerial = stockInOutRecord.getDeliverSerial();
			old_delivery = delivery2Mapper.selectByDeliveryPrimaryKey(deliverySerial);
//			clearStockOutInfoFormMateriels(old_delivery.getDeliveryMateriels());//清除之前的出入库物料信息
			
			//更新入库记录
			record.setUpdater(currenLoginName);
			record.setStatus("1");//入库完成
			record.setUpdateTime(new Date());
			StockInOutRecordExample example = new StockInOutRecordExample();
			example.createCriteria().andSerialNumEqualTo(record.getSerialNum());
			stockInOutRecordMapper.updateByExampleSelective(record, example);
			
			//出库完成状态处理
			stockOutEndHandle(old_delivery.getOrderSerial(),deliverySerial,currenLoginName);
			
		}
		
		
		List<DeliveryMateriel> materiels = deliveryMateriels; //这里是出入库的物料信息
		BigDecimal  totalOutCount=BigDecimal.ZERO;
		for(DeliveryMateriel materiel : materiels){
			DeliveryMaterielExample example2 = new DeliveryMaterielExample();
			example2.createCriteria().andSerialNumEqualTo(materiel.getSerialNum());
			deliveryMaterielMapper.updateByExampleSelective(materiel, example2);
			totalOutCount=totalOutCount.add(new  BigDecimal(materiel.getStockCount()) );
			/*if(!CollectionUtils.isEmpty(materiel.getStockInBatchs())){
				//先删除原来的入库批次信息
				StockInBatchExample se=new StockInBatchExample();
				com.congmai.zhgj.web.model.StockInBatchExample.Criteria c=se.createCriteria();
				c.andStockInMaterielSerialEqualTo(materiel.getSerialNum());
				stockInBatchMapper.deleteByExample(se);
				for(StockInBatch s : materiel.getStockInBatchs()){
					s.setStockInMaterielSerial(materiel.getSerialNum());
					s.setSerialNum(ApplicationUtils.random32UUID());
					s.setUpdater(currenLoginName);
					s.setUpdateTime(new Date());
					s.setCreator(currenLoginName);
					s.setCreateTime(new Date());
					stockInBatchMapper.insert(s);
				}
			}*/
			if("1".equals(record.getStatus())){
			createStock(materiel,new StockExample(),currenLoginName);
			String  orderSerial=old_delivery.getOrderSerial();//取订单流水
			OrderInfo order=orderInfoMapper.selectByPrimaryKey(orderSerial);
				// 入库计划入库成功通过发邮件通知供应商
				if (StringUtil.isNotEmpty(order.getSupplyComId())) {
					List<CompanyContact> companyContacts = companyContactService
							.selectListByComId(order.getSupplyComId());// 获取企业联系人
					if (org.apache.commons.collections.CollectionUtils
							.isNotEmpty(companyContacts)) {
						for (CompanyContact companyContact : companyContacts) {
							if (StringUtil.isNotEmpty(companyContact
									.getContactEmail())) {
								SendEmail.sendHtmlMail(
										companyContact.getContactEmail(),
										"平台收货计划入库成功",
										"平台入库成功,"+
										"关联发货计划单号" + old_delivery.getDeliverNum());
							}
						}
					}
				}
			}
		}
		//更新入库数量(订单)
		if("1".equals(record.getStatus())){
			OrderInfo o=orderService.selectById(old_delivery.getOrderSerial());
			if(StringUtil.isNotEmpty(o.getReceiveCount())){
				totalOutCount=totalOutCount.add(new BigDecimal(o.getReceiveCount()));
			}
			o.setReceiveCount(totalOutCount.toString());
			orderService.update(o);
			//更新入库单实际入库数量
			StockInOutRecordExample example = new StockInOutRecordExample();
			example.createCriteria().andSerialNumEqualTo(record.getSerialNum());
			record.setRealCount(totalOutCount.toString());//实际入库数量
			stockInOutRecordMapper.updateByExampleSelective(record, example);
			}
		
		
	}
	
	
	@Override
	@OperationLog(operateType = "add" ,operationDesc = "出库" ,objectSerial= "{serialNum}")
	public void updateStockOutData(StockInOutRecord record,
			List<DeliveryMateriel> deliveryMateriels,List<StockOutBatch> stockOutMateriels, String currenLoginName,
			String string) {
		//StockInOutRecord record = takeDeliveryParams.getRecord();
		
		
		Delivery old_delivery = null;

		//获取更新前的发货id
		StockInOutRecord stockInOutRecord = stockInOutRecordMapper.selectByPrimaryKey(record.getSerialNum());
		String deliverySerial = stockInOutRecord.getDeliverSerial();
		old_delivery = delivery2Mapper.selectByDeliveryPrimaryKey(deliverySerial);
		//clearStockOutInfoFormMateriels(old_delivery.getDeliveryMateriels());//清除之前的出入库物料信息
		BigDecimal totalOutCount=BigDecimal.ZERO;
		if("1".equals(record.getStatus())){
		//自动生成报关单
		OrderInfo o=orderInfoMapper.selectByPrimaryKey(old_delivery.getOrderSerial());
		OrderInfo orderInfo=new OrderInfo();
		orderInfo.setSerialNum(o.getSerialNum());
		Delivery d=new Delivery();
		d.setSerialNum(old_delivery.getSerialNum());
		TakeDelivery td=new TakeDelivery();
		td.setSerialNum(old_delivery.getTakeDeliverSerial());
		if(StaticConst.getInfo("waimao").equals(o.getTradeType())&&StringUtils.isEmpty(o.getSupplyComId())){//外贸
			List<DeliveryMateriel> materiels = deliveryMateriels; //这里是出入库的物料信息
			for(DeliveryMateriel materiel : materiels){
				DeliveryMaterielExample example2 = new DeliveryMaterielExample();
				example2.createCriteria().andSerialNumEqualTo(materiel.getSerialNum());
				deliveryMaterielMapper.updateByExampleSelective(materiel, example2);
				totalOutCount=totalOutCount.add(new BigDecimal(materiel.getStockCount()));
			}
		createCustomsDeclarationForm(deliverySerial,currenLoginName);
		//更新订单状态待报关
		orderInfo.setDeliverStatus(orderInfo.DECLARATION);
		d.setStatus(DeliveryVO.DECLARATION);
		td.setStatus(TakeDelivery.WAIT_Declaration);
		}else{
			//orderInfo.setDeliverStatus(orderInfo.OUTRECORD);//已出库
			orderInfo.setDeliverStatus(orderInfo.WAIT_TAKEDELIVER);//待收货
			//d.setStatus(DeliveryVO.COMPLETE);//发货完成
			d.setStatus(DeliveryVO.WAIT_TAKEDELIVER_DELIVERY);//待收货
			//td.setStatus(TakeDelivery.COMPLETE);
			td.setStatus(TakeDelivery.WAITING);//待收货
			List<DeliveryMateriel> materiels = deliveryMateriels; //这里是出入库的物料信息
			for(DeliveryMateriel materiel : materiels){
				DeliveryMaterielExample example2 = new DeliveryMaterielExample();
				example2.createCriteria().andSerialNumEqualTo(materiel.getSerialNum());
				deliveryMaterielMapper.updateByExampleSelective(materiel, example2);
				totalOutCount=totalOutCount.add(new BigDecimal(materiel.getStockCount()));
				/*createStock(materiel,new StockExample(),currenLoginName);*/
			}
		}
		OrderInfo order=orderService.selectById(old_delivery.getOrderSerial());
		if(StringUtil.isNotEmpty(order.getReceiveCount())){
			totalOutCount=totalOutCount.add(new BigDecimal(order.getReceiveCount()));
		}
		orderInfo.setReceiveCount(totalOutCount.toString());
		orderInfoMapper.updateByPrimaryKeySelective(orderInfo);
		delivery2Mapper.updateByPrimaryKeySelective(d);
		takeDeliveryMapper.updateByPrimaryKeySelective(td);
		//按结算条款中的签订合同节点生成付款
		String orderString = old_delivery.getOrderSerial();
		String nodeString = ClauseSettlementDetail.CKH;
		contractService.findPaymentNode(orderString, nodeString);	
		
		//更新出库单实际出库数量
		StockInOutRecordExample example = new StockInOutRecordExample();
		example.createCriteria().andSerialNumEqualTo(record.getSerialNum());
		record.setRealCount(totalOutCount.toString());//实际出库数量
		stockInOutRecordMapper.updateByExampleSelective(record, example);
			// 出库计划出库成功通过发邮件通知采购商
			if (StringUtil.isNotEmpty(order.getBuyComId())) {
				List<CompanyContact> companyContacts = companyContactService
						.selectListByComId(order.getBuyComId());// 获取企业联系人
				if (org.apache.commons.collections.CollectionUtils
						.isNotEmpty(companyContacts)) {
					for (CompanyContact companyContact : companyContacts) {
						if (StringUtil.isNotEmpty(companyContact
								.getContactEmail())) {
							SendEmail.sendHtmlMail(
									companyContact.getContactEmail(),
									"平台发货计划出库成功",
									"平台出库成功,"+
									"关联收货计划单号" + old_delivery.getDeliverNum());
						}
					}
				}
			}
		}else{
			List<DeliveryMateriel> materiels = deliveryMateriels; //这里是出入库的物料信息
			for(DeliveryMateriel materiel : materiels){
				DeliveryMaterielExample example2 = new DeliveryMaterielExample();
				example2.createCriteria().andSerialNumEqualTo(materiel.getSerialNum());
				deliveryMaterielMapper.updateByExampleSelective(materiel, example2);
			}
		}
		//更新入库记录
		record.setUpdater(currenLoginName);
		record.setUpdateTime(new Date());
		StockInOutRecordExample example = new StockInOutRecordExample();
		example.createCriteria().andSerialNumEqualTo(record.getSerialNum());
		stockInOutRecordMapper.updateByExampleSelective(record, example);
		
		//出库完成状态处理
		//stockOutEndHandle(old_delivery.getOrderSerial(),deliverySerial,currenLoginName);
		
		//保存出库来源批次
		if(stockOutMateriels.size()!=0){
			List<StockOutBatch> stockOutMaterielsNew = stockOutMateriels; //这里是入库的物料信息
			StockOutBatchExample  se=new  StockOutBatchExample();
			com.congmai.zhgj.web.model.StockOutBatchExample.Criteria c=se.createCriteria();
			c.andStockOutSerialEqualTo(stockOutMateriels.get(0).getStockOutSerial());
			stockOutBatchMapper.deleteByExample(se);//删除原来出库单关联的出库批次 
			for (StockOutBatch stockOutBatch : stockOutMaterielsNew) {
				if (StringUtils.isEmpty(stockOutBatch.getSerialNum())) {
					stockOutBatch.setSerialNum(ApplicationUtils.random32UUID());
					stockOutBatch.setUpdater(currenLoginName);
					stockOutBatch.setUpdateTime(new Date());
					stockOutBatch.setCreator(currenLoginName);
					stockOutBatch.setCreateTime(new Date());
					stockOutBatch.setDelFlg("0");
					stockOutBatchMapper.insert(stockOutBatch);
				}
			}
		}
			
		
	}
	
	//自动生成报关单
		public void createCustomsDeclarationForm(String deliverySerial ,String currenLoginName){
			DeliveryVO d=deliveryMapper.selectDetailById(deliverySerial);
			String orderSerial =d.getOrderSerial();//获取订单流水
			Map<String,String>map=new HashMap<String,String>();
			map.put("orderSerial", orderSerial);
			map.put("invoiceSerial", null);
			map.put("deliverySerial", deliverySerial);
			map.put("isClearance", "0");//是否清关单
			List<Materiel> materiels = materielMapper.selectMaterielByOrderSerial(map);
			DeliveryTransportExample de=new DeliveryTransportExample();
			com.congmai.zhgj.web.model.DeliveryTransportExample.Criteria  c=de.createCriteria();
			c.andDelFlgEqualTo("0");
			c.andDeliverSerialEqualTo(deliverySerial);
			List<DeliveryTransport>ds =deliveryTransportMapper.selectByExample(de);
			DeliveryTransport deliveryTransport=null;
			if(CollectionUtils.isNotEmpty(ds)){
				deliveryTransport=ds.get(0);
			}
			BigDecimal deliverAmount=BigDecimal.ZERO;//deliverAmount发货金额
			BigDecimal addedTax=BigDecimal.ZERO;//addedTax增值税
			BigDecimal customsAmount=BigDecimal.ZERO;//customsAmount关税额
			for(Materiel materiel:materiels){
				materiel.setMoney(new BigDecimal(materiel.getOrderUnitPrice()).multiply(new BigDecimal(materiel.getBillAmount()).setScale(2,BigDecimal.ROUND_HALF_UP )).toString());
				if(!StringUtils.isEmpty(materiel.getRate())){//	税额
					materiel.setRateMoney(new BigDecimal(materiel.getRate()).multiply(new BigDecimal(materiel.getOrderUnitPrice())).multiply(new BigDecimal(materiel.getDeliverCount())).divide(new BigDecimal("100")).setScale(2,BigDecimal.ROUND_HALF_UP ).toString());
					addedTax=addedTax.add(new BigDecimal(materiel.getRateMoney()));
				}
				if(!StringUtils.isEmpty(materiel.getCustomsRate())){//关税额
					materiel.setCustomRateMoney(new BigDecimal(materiel.getCustomsRate()).multiply(new BigDecimal(materiel.getOrderUnitPrice())).multiply(new BigDecimal(materiel.getDeliverCount())).divide(new BigDecimal("100")).setScale(2,BigDecimal.ROUND_HALF_UP ).toString());
					customsAmount=customsAmount.add(new BigDecimal(materiel.getCustomRateMoney()));
				}
				materiel.setMaterielMoney(new BigDecimal(materiel.getOrderUnitPrice()).multiply(new BigDecimal(materiel.getDeliverCount())).setScale(2,BigDecimal.ROUND_HALF_UP ).toString());
				deliverAmount=deliverAmount.add(new BigDecimal(materiel.getMaterielMoney()));
			}
			CustomsForm customsForm=new  CustomsForm();
			customsForm.setStatus("0");
			customsForm.setDelFlg("0");
			customsForm.setCustomsFormNum(orderService.getNumCode("BG"));
    		customsForm.setSerialNum(ApplicationUtils.random32UUID());
    		customsForm.setCustomsFormType("declaration");
    		customsForm.setCreator(currenLoginName);
    		customsForm.setCreateTime(new Date());
    		customsForm.setDeliverSerial(deliverySerial);
    		customsForm.setOrderSerial(orderSerial);
    		customsForm.setDeliverAmount(deliverAmount.toString());
    		customsForm.setAddedTax(addedTax.toString());
    		customsForm.setCustomsAmount(customsAmount.toString());
    		customsForm.setShipNumber(deliveryTransport==null?"":deliveryTransport.getShipNumber());
    		customsForm.setPlayArrivalDate(deliveryTransport==null?null:deliveryTransport.getPlayArrivalDate());
    		customsForm.setPort(deliveryTransport==null?null:deliveryTransport.getPort());
    		customsFormMapper.insert(customsForm);
    		//报关消息通知销售订单制单人
        	EventExample.getEventPublisher().publicSendMessageEvent(new SendMessageEvent(customsForm,MessageConstants.IN_TO_CUSTOMSFORM));
		}
	public  List<String> removeDuplicate(List<String> list) 

	{        

	HashSet<String> h = new  HashSet<String>(list);        
    
	List<String> list2 = new ArrayList<String>();
	list2.addAll(h);        

	return list2;     

	}  
	
	private void clearStockInInfoFormMateriels(List<DeliveryMateriel> deliveryMateriels){
		for(DeliveryMateriel materiel : deliveryMateriels){
			deliveryMaterielMapper.deleteByPrimaryKey(materiel.getSerialNum());
		}
	}
	
	private void clearStockOutInfoFormMateriels(List<DeliveryMateriel> deliveryMateriels){
		for(DeliveryMateriel materiel : deliveryMateriels){
			DeliveryMaterielExample example2 = new DeliveryMaterielExample();
			materiel.setStockCount("");
			materiel.setUnstockCount("");
			materiel.setPositionSerial("");
			materiel.setWarehouseSerial("");
			materiel.setStockRemark("");
			example2.createCriteria().andSerialNumEqualTo(materiel.getSerialNum());
			deliveryMaterielMapper.updateByExampleSelective(materiel, example2);
		}
	}

	@Override
	public void createStockInRecord(String takeDeliverySerial,String currenLoginName) {
		StockInOutRecord record = new StockInOutRecord();
		record.setSerialNum(ApplicationUtils.random32UUID());
		record.setTakeDeliverSerial(takeDeliverySerial);
		record.setDeliverSerial("");
		record.setStatus("0");
		record.setDelFlg("0");
		record.setCreator(currenLoginName);
		record.setCreateTime(new Date());
		record.setUpdater(currenLoginName);
		record.setUpdateTime(new Date());
		stockInOutRecordMapper.insert(record);
	}

	@Override
	public void createStockOutRecord(String deliverySerial,String currenLoginName) {
		StockInOutRecord record = new StockInOutRecord();
		record.setSerialNum(ApplicationUtils.random32UUID());
		record.setDeliverSerial(deliverySerial);
		record.setTakeDeliverSerial("");
		record.setStatus("0");
		record.setDelFlg("0");
		record.setCreator(currenLoginName);
		record.setCreateTime(new Date());
		record.setUpdater(currenLoginName);
		record.setUpdateTime(new Date());
		stockInOutRecordMapper.insert(record);
	}

	@Override
	@OperationLog(operateType = "add" ,operationDesc = "收货" ,objectSerial= "{serialNum}")
	public void confirmTakeDelivery(TakeDelivery takeDelivery,List<DeliveryMateriel> deliveryMateriels,
			String currenLoginName) throws Exception {
		//takeDeliveryParams = getConfirmTakeDeliveryData(takeDeliveryParams,currenLoginName);
		//(takeDeliveryParams.getTakeDelivery());
		//TakeDelivery takeDelivery = new TakeDelivery();
		
		//takeDelivery.setSerialNum(takeDeliveryParams.getTakeDelivery().getSerialNum());
		//takeDelivery.set(takeDeliveryParams.getTakeDelivery().getSerialNum());
		//takeDelivery.setSerialNum(takeDeliveryParams.getTakeDelivery().getSerialNum());
		//takeDelivery.setSerialNum(takeDeliveryParams.getTakeDelivery().getSerialNum());
		Delivery delivery=delivery2Mapper.selectByDeliveryPrimaryKey(takeDelivery.getDeliverSerial());
		OrderInfo o=orderInfoMapper.selectByPrimaryKey(delivery.getOrderSerial());
		OrderInfo orderInfo=new OrderInfo();
		orderInfo.setSerialNum(delivery.getOrderSerial());
		Delivery deliverynew=new  Delivery();
		deliverynew.setSerialNum(takeDelivery.getDeliverSerial());
		if("1".equals(o.getContractContent().substring(4, 5))){//验收条款有效
			takeDelivery.setStatus(TakeDelivery.APPLY_COMPLETE); //待检验
			this.createStockInCheckRecord(takeDelivery,currenLoginName);
			orderInfo.setDeliverStatus(orderInfo.WAIT_IN_CHECK);//已收货待检验
			deliverynew.setStatus(DeliveryVO.WAIT_CHECK);
		}else{
			takeDelivery.setStatus(TakeDelivery.CHECK_COMPLETE); //已完成
			orderInfo.setDeliverStatus(orderInfo.WAIT_INRECORD);//待入库
			deliverynew.setStatus(DeliveryVO.WAIT_IN_RECORD);
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
			
		}

		//更新订单状态待清关
		
		orderInfoMapper.updateByPrimaryKeySelective(orderInfo);
		takeDeliveryMapper.updateByPrimaryKeySelective(takeDelivery);
		
		//没有审批时，直接收货
		TakeDelivery _takeDelivery = takeDeliveryMapper.selectByPrimaryKey(takeDelivery.getSerialNum());
		takeDelivery.setDeliverSerial(_takeDelivery.getDeliverSerial());
		
		delivery2Mapper.updateByPrimaryKeySelective(deliverynew);
		
		
		
		//删除已保存的收货物料
		DeliveryMaterielExample example = new DeliveryMaterielExample();
		example.createCriteria().andDeliverSerialEqualTo(takeDelivery.getSerialNum());
		deliveryMaterielMapper.deleteByExample(example);
		
	   for(DeliveryMateriel materiel : deliveryMateriels){
			materiel.setSerialNum(ApplicationUtils.random32UUID());
			materiel.setDeliverSerial(takeDelivery.getSerialNum());
			materiel.setDelFlg("0");
			materiel.setCreator(currenLoginName);
			materiel.setCreateTime(new Date());
			materiel.setUpdater(currenLoginName);
			materiel.setUpdateTime(new Date());
			deliveryMaterielMapper.insert(materiel);
		}
		
	}


	@Override
	public TakeDelivery selectByPrimaryKey(String serialNum) {
		
		return takeDeliveryMapper.selectByPrimaryKey(serialNum);
	}

	@Override
	public int updateByPrimaryKeySelective(TakeDelivery record) {
		
		return takeDeliveryMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 生成入库检验单
	 */
	@Override
	/*@OperationLog(operateType = "add" ,operationDesc = "收货" ,objectSerial= "{serialNum}")*/
	public void createStockInCheckRecord(TakeDelivery takeDelivery,
			String currenLoginName){
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
		//入库检验通知仓储人员/采购
    	EventExample.getEventPublisher().publicSendMessageEvent(new SendMessageEvent(check,MessageConstants.IN_TO_WAITCHECK));
		
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
	 * 生成出库检验单
	 */
	@Override
	public void createStockOutCheckRecord(String deliverySerial,
			String currenLoginName) {
		StockInOutCheck check = new StockInOutCheck();
		check.setSerialNum(ApplicationUtils.random32UUID());
		check.setDeliverSerial(deliverySerial);
		check.setTakeDeliverSerial("checkout");
		check.setCheckNum(orderService.getNumCode("QU"));
		check.setStatus("0");
		check.setDelFlg("0");
		check.setCreator(currenLoginName);
		check.setCreateTime(new Date());
		check.setUpdater(currenLoginName);
		check.setUpdateTime(new Date());
		stockInOutCheckMapper.insert(check);
		//出库检验通知仓储人员/采购
    	EventExample.getEventPublisher().publicSendMessageEvent(new SendMessageEvent(check,MessageConstants.IN_TO_WAITCHECK));
	}
	
	/**
	 * 
	 * @Description (入库完成状态处理)
	 * @param orderSerial
	 * @param takeDelvierySerial
	 * @param currenLoginName
	 */
	private void stockInEndHandle(String orderSerial,String takeDelvierySerial,String currenLoginName){
		//更新订单状态
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setSerialNum(orderSerial);
		orderInfo.setDeliverStatus(OrderInfo.INRECORD);//入库完成/待收票
		orderInfo.setUpdateTime(new Date());
		orderInfo.setUpdater(currenLoginName);
		orderInfoMapper.updateByPrimaryKeySelective(orderInfo);
		//更新收货状态
		TakeDelivery takeDelivery = new TakeDelivery();
		takeDelivery.setSerialNum(takeDelvierySerial);
		takeDelivery.setStatus("4");//收货完成
		takeDelivery.setUpdateTime(new Date());
		takeDelivery.setUpdater(currenLoginName);
		takeDeliveryMapper.updateByPrimaryKeySelective(takeDelivery);
	}
	
	/**
	 * 
	 * @Description (出库完成状态处理)
	 * @param orderSerial
	 * @param takeDelvierySerial
	 * @param currenLoginName
	 */
	private void stockOutEndHandle(String orderSerial,String deliverySerial,String currenLoginName){
		
		//更新订单状态
	/*	OrderInfo orderInfo = new OrderInfo();
		orderInfo.setSerialNum(orderSerial);
		orderInfo.setDeliverStatus(OrderInfo.OUTRECORD);//入库完成/待收票
		orderInfo.setUpdateTime(new Date());
		orderInfo.setUpdater(currenLoginName);
		orderInfoMapper.updateByPrimaryKeySelective(orderInfo);*/
		//更新发货状态
		//Delivery delivery = new Delivery();
		//delivery.setSerialNum(deliverySerial);
		//delivery.setStatus("4");//状态未定
		//delivery.setUpdateTime(new Date());
		//delivery.setUpdater(currenLoginName);
		//delivery2Mapper.updateByPrimaryKeySelective(delivery);
	}

	@Override
	public StockInOutRecord findStockInSerialNum(String serialNum) {
		StockInOutRecordExample example = new StockInOutRecordExample();
		com.congmai.zhgj.web.model.StockInOutRecordExample.Criteria c =  example.createCriteria();
		c.andDelFlgEqualTo("0");
		c.andTakeDeliverSerialEqualTo(serialNum);
		List<StockInOutRecord> list = stockInOutRecordMapper.selectByExample(example);
		if(!CollectionUtils.isEmpty(list)){
			return list.get(0);
		}else{
			return null;
		}
		
	}

	@Override
	public StockInOutRecord findStockOutSerialNum(String serialNum) {
		StockInOutRecordExample example = new StockInOutRecordExample();
		com.congmai.zhgj.web.model.StockInOutRecordExample.Criteria c =  example.createCriteria();
		c.andDelFlgEqualTo("0");
		c.andDeliverSerialEqualTo(serialNum);
		List<StockInOutRecord> list = stockInOutRecordMapper.selectByExample(example);
		if(!CollectionUtils.isEmpty(list)){
			return list.get(0);
		}else{
			return null;
		}
	}

	@Override
	public void updateTakeDelivery(TakeDelivery takeDelivery) {
		takeDeliveryMapper.updateByPrimaryKeySelective(takeDelivery);//更新收货单状态
		
	}

	/**
	 * 
	 * @Description (生成wms到货单，并返回数据记录id)
	 * @param delivery
	 * @param deliveryMateriels1
	 * @param takeDelivery
	 * @return
	 */
	private String createWmsArriveBill(Delivery delivery,TakeDelivery takeDelivery) {
		try {
			if(StringUtil.isNotEmpty(takeDelivery.getWarehouseSerial())){//判断收货仓库是否是WMS仓库
				Warehouse w =warehouseService.selectOne(takeDelivery.getWarehouseSerial());
				if(w!=null&&StringUtil.isNotEmpty(w.getWmsWarehouseId())){
					
					List<DeliveryMaterielVO> deliveryMateriels1=null;//查出当前发货信息
					deliveryMateriels1= deliveryService.selectListForDetail(delivery.getSerialNum());
					if(deliveryMateriels1!=null&&deliveryMateriels1.size()>0){
						String materielNum=deliveryMateriels1.get(0).getMaterielNum();
						if(StringUtils.isEmpty(materielNum)){
						deliveryMateriels1 = deliveryService.selectListForDetail2(delivery.getSerialNum());	
						}	
					}
					
					ArriveBill arriveBill = new ArriveBill();
					
					arriveBill.setStorageType(env.getProperty("storageType"));//设置入库方式
					arriveBill.setArriveWarehouse(w.getWmsWarehouseId());//设置 仓库
					
					//设置 供应商
					Company com = companyservice.selectById(delivery.getSupplyComId());
					if(StringUtil.isNotEmpty(com.getWmsComId())){//已关联供应商
						arriveBill.setSupplierManage(com.getWmsComId());
					}else {//未关联供应商
						//为wms生成供应商
						Supplier supplier = new Supplier();
						supplier.setEnterpriseName(com.getComName());// 供应商名称 
						supplier.setEnterpriseShortname(com.getAbbreviation());// 供应商简称
						supplier.setIsForbit(Integer.parseInt(env.getProperty("isForbit")));
						
						String data = HttpClientUtil.sendHttpPost(env.getProperty("addSupplier"),JSON.toJSONString(supplier));
						
						if (StringUtil.isNotEmpty(data)) {//获取返回的供应商id
							WmsReturn wmsReturn = JSON.parseObject(data,WmsReturn.class); 
							WmsReturnData wmsReturnData = JSON.parseObject(wmsReturn.getData(),WmsReturnData.class); 
							if("1".equals(wmsReturnData.getFlag())){
								arriveBill.setSupplierManage(wmsReturnData.getId());
								
								//更新中航供应商绑定wms供应商
								Company updateCompany = new Company();
								updateCompany.setWmsComId(wmsReturnData.getId());
								updateCompany.setComId(delivery.getSupplyComId());
								companyservice.update(updateCompany);
							}
						}
					}
	
					arriveBill.setShipDate(delivery.getDeliverDate());// 发货日期
					arriveBill.setState(env.getProperty("arriveState"));// 到货状态
					
					//设置到货物料
					List<ArriveData> goodsList = new ArrayList<ArriveData>();
					
					List<Unit> unitList = new ArrayList<Unit>();
				  for(DeliveryMaterielVO deliveryMateriel: deliveryMateriels1){//循环发货物料构造数据生成wms到货计划物料
					  ArriveData arriveData = new ArriveData();	
					  
					  Materiel materiel = materielService.getMaterielInfoByMaterielSerial(deliveryMateriel.getMaterielSerial());
					  
					  arriveData.setProductFlag("0");
					  arriveData.setArriveCount(Float.parseFloat(deliveryMateriel.getDeliverCount()));
					  if(StringUtil.isNotEmpty(materiel.getWmsMaterielId())){
						  arriveData.setProductId(materiel.getWmsMaterielId());
						  Product product= new Product();
						  product.setId(materiel.getWmsMaterielId());
						  arriveData.setProduct(product);
					  }else{
						//为wms生成产品
						  	Product product= new Product();
						  	product.setProductchname(materiel.getMaterielName());// 产品名称
						  	product.setTypeid(env.getProperty("productTypeId"));// 分类，写死，默认
						  	product.setState(env.getProperty("productState"));//  状态，默认
						  	
						  	//默认未找到单位id
							String matchFlag = "0";
						  	if(StringUtil.isNotEmpty(materiel.getUnit())){
						  		if(unitList.size()>0){
									for (Unit unit : unitList){
										if(materiel.getUnit().equals(unit.getUnitname())){
											product.setUnitid(unit.getId());// 单位id
											matchFlag = "1";
										}
									}
							  	}else{
							  	//获取单位id
								  	String unitData = HttpClientUtil.sendHttpGet(env.getProperty("getUnitList"));
								  	if (StringUtil.isNotEmpty(unitData)) {//获取返回的产品id
										WmsReturn wmsReturn = JSON.parseObject(unitData,WmsReturn.class); 
										WmsReturnData wmsReturnData = JSON.parseObject(wmsReturn.getData(),WmsReturnData.class); 
										if(Integer.parseInt(wmsReturnData.getTotal())>0){
											List<String> unitStringList = wmsReturnData.getItems();
											if (!CollectionUtils.isEmpty(unitStringList)) {
												for (String string : unitStringList) {
													Unit unit = JSON.parseObject(string,Unit.class);
													if(materiel.getUnit().equals(unit.getUnitname())){
														product.setUnitid(unit.getId());// 单位id
														matchFlag = "1";
													}
													unitList.add(unit);
												}
											}
										}
									}
								  	
							  	}
						  		if (matchFlag=="0") {//未找到对应id，需向WMS中新增单位
									Unit unit = new Unit();
									unit.setUnitname(materiel.getUnit());
									unit.setIsforbit(Integer.parseInt(env.getProperty("isForbit")));
									
									String data = HttpClientUtil.sendHttpPost(env.getProperty("addUnit"),JSON.toJSONString(unit));
									
									if (StringUtil.isNotEmpty(data)) {//获取返回的产品id
										WmsReturn wmsReturn2 = JSON.parseObject(data,WmsReturn.class); 
										WmsReturnData wmsReturnData2 = JSON.parseObject(wmsReturn2.getData(),WmsReturnData.class);
										if("1".equals(wmsReturnData2.getFlag())){
											product.setUnitid(wmsReturnData2.getId());
											unit.setId(wmsReturnData2.getId());
											unitList.add(unit);
										}
									}
								}
						  	}
						  	
							
							String data = HttpClientUtil.sendHttpPost(env.getProperty("addProduct"),JSON.toJSONString(product));
							
							if (StringUtil.isNotEmpty(data)) {//获取返回的产品id
								WmsReturn wmsReturn = JSON.parseObject(data,WmsReturn.class); 
								WmsReturnData wmsReturnData = JSON.parseObject(wmsReturn.getData(),WmsReturnData.class); 
								if("1".equals(wmsReturnData.getFlag())){
									arriveData.setProductId(wmsReturnData.getId());
									  Product product2= new Product();
									  product2.setId(wmsReturnData.getId());
									  arriveData.setProduct(product2);
									//更新中航物料绑定wms产品
									Materiel updateMateriel = new Materiel();
									updateMateriel.setWmsMaterielId(wmsReturnData.getId());
									updateMateriel.setSerialNum(deliveryMateriel.getMaterielSerial());
									materielService.update(updateMateriel);
								}
							}
					  }
					  goodsList.add(arriveData);
					}
					  
				  	arriveBill.setGoodsList(goodsList);  
					
					//新增wms到货通知
					String data = HttpClientUtil.sendHttpPost(env.getProperty("addArrive"),JSON.toJSONString(arriveBill));
					if (StringUtil.isNotEmpty(data)) {//获取返回的到货通知id
						WmsReturn wmsReturn = JSON.parseObject(data,WmsReturn.class); 
						WmsReturnData wmsReturnData = JSON.parseObject(wmsReturn.getData(),WmsReturnData.class);
						if("1".equals(wmsReturnData.getFlag())){
							return wmsReturnData.getId();
							
						}
					}
	
				}
			}
			
		} catch (Exception e) {
			return null;
		}
		return null;
	}


	

}
