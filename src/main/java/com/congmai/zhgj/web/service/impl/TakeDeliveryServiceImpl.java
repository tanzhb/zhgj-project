package com.congmai.zhgj.web.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.congmai.zhgj.core.feature.orm.mybatis.Page;
import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.log.annotation.OperationLog;
import com.congmai.zhgj.core.util.Constants;
import com.congmai.zhgj.web.dao.Delivery2Mapper;
import com.congmai.zhgj.web.dao.DeliveryMaterielMapper;
import com.congmai.zhgj.web.dao.DeliveryTransportMapper;
import com.congmai.zhgj.web.dao.OrderInfoMapper;
import com.congmai.zhgj.web.dao.OrderMaterielMapper;
import com.congmai.zhgj.web.dao.StockInOutCheckMapper;
import com.congmai.zhgj.web.dao.StockInOutRecordMapper;
import com.congmai.zhgj.web.dao.StockMapper;
import com.congmai.zhgj.web.dao.TakeDeliveryMapper;
import com.congmai.zhgj.web.enums.StaticConst;
import com.congmai.zhgj.web.model.Delivery;
import com.congmai.zhgj.web.model.DeliveryExample;
import com.congmai.zhgj.web.model.DeliveryMateriel;
import com.congmai.zhgj.web.model.DeliveryMaterielExample;
import com.congmai.zhgj.web.model.DeliveryTransport;
import com.congmai.zhgj.web.model.DeliveryTransportExample;
import com.congmai.zhgj.web.model.OrderInfo;
import com.congmai.zhgj.web.model.OrderMateriel;
import com.congmai.zhgj.web.model.Stock;
import com.congmai.zhgj.web.model.StockExample;
import com.congmai.zhgj.web.model.StockInOutCheck;
import com.congmai.zhgj.web.model.StockInOutRecord;
import com.congmai.zhgj.web.model.StockInOutRecordExample;
import com.congmai.zhgj.web.model.TakeDelivery;
import com.congmai.zhgj.web.model.TakeDeliveryExample;
import com.congmai.zhgj.web.model.TakeDeliveryParams;
import com.congmai.zhgj.web.model.TakeDeliverySelectExample;
import com.congmai.zhgj.web.model.TakeDeliverySelectExample.Criteria;
import com.congmai.zhgj.web.model.TakeDeliveryVO;
import com.congmai.zhgj.web.service.OrderMaterielService;
import com.congmai.zhgj.web.service.TakeDeliveryService;

@Service
public class TakeDeliveryServiceImpl extends GenericServiceImpl<TakeDelivery,String>
		implements TakeDeliveryService {

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
		if(StringUtils.isNotBlank(takeDelivery.getStatus())){
			c.andStatusEqualTo(takeDelivery.getStatus());
		}
		Page<Delivery> page = new Page<Delivery>();
		page.setResult(takeDeliveryMapper.selectListByExample(example));
		page.setTotalCount(takeDeliveryMapper.countListByExample(example));
		
		return page;
	}

	@Override
	@OperationLog(operateType = "add" ,operationDesc = "代发货" ,objectSerial= "{serialNum}")
	public void insertTakeDelivery(Delivery delivery,TakeDelivery takeDelivery,DeliveryTransport deliveryTransport,List<DeliveryMateriel> deliveryMateriels,
			String currenLoginName) {
		//takeDeliveryParams = getTakeDeliveryData(takeDeliveryParams,currenLoginName);
		delivery2Mapper.insert(delivery);
		
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setSerialNum(delivery.getOrderSerial());
		orderInfo.setDeliverStatus(OrderInfo.DELIVER);//确认发货
		orderInfo.setUpdateTime(new Date());
		orderInfo.setUpdater(currenLoginName);
		orderInfoMapper.updateByPrimaryKeySelective(orderInfo);
		
		deliveryTransportMapper.insert(deliveryTransport);
		takeDeliveryMapper.insert(takeDelivery);
		for(DeliveryMateriel materiel : deliveryMateriels){
			deliveryMaterielMapper.insert(materiel);
		}
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
		
		DeliveryTransportExample dt_example = new DeliveryTransportExample();
		dt_example.createCriteria().andSerialNumEqualTo(deliveryTransport.getSerialNum());
		deliveryTransportMapper.updateByExampleSelective(deliveryTransport,dt_example);
		
		TakeDeliveryExample td_example = new TakeDeliveryExample();
		td_example.createCriteria().andSerialNumEqualTo(takeDelivery.getSerialNum());
		takeDeliveryMapper.updateByExampleSelective(takeDelivery,td_example);
		
		DeliveryMateriel dm = new DeliveryMateriel();
		dm.setDelFlg("1");
		DeliveryMaterielExample dmExample = new DeliveryMaterielExample();
		dmExample.createCriteria().andDeliverSerialEqualTo(delivery.getSerialNum());
		deliveryMaterielMapper.updateByExampleSelective(dm,dmExample);
		
		for(DeliveryMateriel materiel : deliveryMateriels){
				deliveryMaterielMapper.insert(materiel);
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
			createStock(materiel,new StockExample(),currenLoginName);
		}
		
	}

	private void createStock(DeliveryMateriel deliveryMateriel,StockExample stockExample,String currenLoginName) {//生成自建库存
		OrderMateriel orderMateriel=orderMaterielMapper.selectByPrimaryKey(deliveryMateriel.getOrderMaterielSerial());//获取订单物料信息
		OrderInfo orderInfo=orderInfoMapper.selectByPrimaryKey(orderMateriel.getOrderSerial());
		com.congmai.zhgj.web.model.StockExample.Criteria criteria=stockExample.createCriteria();
		Boolean isStockZijian=true;//默认是自建库存
		if(StaticConst.getValueByInfo("dailiBuy").equals(orderInfo.getOrderType())||StaticConst.getValueByInfo("dailiSale").equals(orderInfo.getOrderType())){//代理销售/代理采购
			criteria.andMaterielSerialEqualTo(orderMateriel.getMaterielSerial()).andManageTypeEqualTo("2");//代管库存
			isStockZijian=false;
		}else{
			criteria.andMaterielSerialEqualTo(orderMateriel.getMaterielSerial()).andManageTypeEqualTo("1");//自建库存
		}
		
		List<Stock>  stocks=stockMapper.selectByExample(stockExample);
		if(CollectionUtils.isEmpty(stocks)){//不存在此物料的库存,就直接新建一条库存
			Stock stock=new Stock();
			stock.setStockNum("KC"+ApplicationUtils.getFromNumber());
			stock.setSerialNum(ApplicationUtils.random32UUID());
			stock.setCurrentAmount(deliveryMateriel.getStockCount());
			stock.setDelFlg("0");
			stock.setCreator(currenLoginName);
			stock.setCreateTime(new Date());
			stock.setUpdater(currenLoginName);
			stock.setUpdateTime(new Date());
			if (isStockZijian) {
				stock.setManageType("1");// 自建库存
				stock.setMaterielOwner(StaticConst.getValueByInfo("comName"));
				StaticConst.getValueByInfo("comName");
			}else{
				stock.setManageType("2");// 代管库存
				stock.setMaterielOwner("");
				stock.setServiceParty(StaticConst.getValueByInfo("comName"));
			}
			
			
		}else{//已存在此物料的库存,更新库存数量
		String currenAmount=stocks.get(0).getCurrentAmount();//获取当前库存数
		if(isStockZijian){//自建库存
		Stock stock=new Stock();
		stock.setSerialNum(stocks.get(0).getSerialNum());
		stock.setCurrentAmount(Integer.parseInt(currenAmount)+Integer.parseInt(deliveryMateriel.getStockCount())+"");
		stockMapper.updateByPrimaryKey(stock);
		}else{
			//判断物权方是否相同,相同更新代管库存,不同新建代管库存
		}
	}
		
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
			record.setStatus("1");//入库完成
			record.setUpdateTime(new Date());
			StockInOutRecordExample example = new StockInOutRecordExample();
			example.createCriteria().andSerialNumEqualTo(record.getSerialNum());
			stockInOutRecordMapper.updateByExampleSelective(record, example);
			
		
			//入库完成状态处理
			stockInEndHandle(old_delivery.getOrderSerial(),takeDeliverySerial,currenLoginName);		
				
		}else{
			//获取更新前的发货id
			StockInOutRecord stockInOutRecord = stockInOutRecordMapper.selectByPrimaryKey(record.getSerialNum());
			String deliverySerial = stockInOutRecord.getDeliverSerial();
			old_delivery = delivery2Mapper.selectByDeliveryPrimaryKey(deliverySerial);
			clearStockOutInfoFormMateriels(old_delivery.getDeliveryMateriels());//清除之前的出入库物料信息
			
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
		for(DeliveryMateriel materiel : materiels){
			DeliveryMaterielExample example2 = new DeliveryMaterielExample();
			example2.createCriteria().andSerialNumEqualTo(materiel.getSerialNum());
			deliveryMaterielMapper.updateByExampleSelective(materiel, example2);
		}
		
		
		
	}
	
	
	@Override
	@OperationLog(operateType = "add" ,operationDesc = "出库" ,objectSerial= "{serialNum}")
	public void updateStockOutData(StockInOutRecord record,
			List<DeliveryMateriel> deliveryMateriels, String currenLoginName,
			String string) {
		//StockInOutRecord record = takeDeliveryParams.getRecord();
		
		
		Delivery old_delivery = null;

		//获取更新前的发货id
		StockInOutRecord stockInOutRecord = stockInOutRecordMapper.selectByPrimaryKey(record.getSerialNum());
		String deliverySerial = stockInOutRecord.getDeliverSerial();
		old_delivery = delivery2Mapper.selectByDeliveryPrimaryKey(deliverySerial);
		clearStockOutInfoFormMateriels(old_delivery.getDeliveryMateriels());//清除之前的出入库物料信息
		
		//更新入库记录
		record.setUpdater(currenLoginName);
		record.setStatus("1");//入库完成
		record.setUpdateTime(new Date());
		StockInOutRecordExample example = new StockInOutRecordExample();
		example.createCriteria().andSerialNumEqualTo(record.getSerialNum());
		stockInOutRecordMapper.updateByExampleSelective(record, example);
		
		//出库完成状态处理
		stockOutEndHandle(old_delivery.getOrderSerial(),deliverySerial,currenLoginName);
		
		List<DeliveryMateriel> materiels = deliveryMateriels; //这里是出入库的物料信息
		for(DeliveryMateriel materiel : materiels){
			DeliveryMaterielExample example2 = new DeliveryMaterielExample();
			example2.createCriteria().andSerialNumEqualTo(materiel.getSerialNum());
			deliveryMaterielMapper.updateByExampleSelective(materiel, example2);
		}
		
		
		
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
		
		takeDelivery.setStatus(TakeDelivery.APPLY_COMPLETE); //待检验
		takeDeliveryMapper.updateByPrimaryKeySelective(takeDelivery);
		
		//没有审批时，直接收货
		TakeDelivery _takeDelivery = takeDeliveryMapper.selectByPrimaryKey(takeDelivery.getSerialNum());
		takeDelivery.setDeliverSerial(_takeDelivery.getDeliverSerial());
		
		this.createStockInCheckRecord(takeDelivery,currenLoginName);
		
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
	@OperationLog(operateType = "add" ,operationDesc = "收货" ,objectSerial= "{serialNum}")
	public void createStockInCheckRecord(TakeDelivery takeDelivery,
			String currenLoginName) throws Exception {
		StockInOutCheck check = new StockInOutCheck();
		check.setSerialNum(ApplicationUtils.random32UUID());
		check.setTakeDeliverSerial(takeDelivery.getSerialNum());
		check.setDeliverSerial("checkin");
		check.setCheckNum("RK"+ApplicationUtils.getFromNumber());
		check.setStatus("0");
		check.setDelFlg("0");
		check.setCreator(currenLoginName);
		check.setCreateTime(new Date());
		check.setUpdater(currenLoginName);
		check.setUpdateTime(new Date());
		stockInOutCheckMapper.insert(check);
		
		//更改订单 
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
		}
		
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
		check.setCheckNum("CK"+ApplicationUtils.getFromNumber());
		check.setStatus("0");
		check.setDelFlg("0");
		check.setCreator(currenLoginName);
		check.setCreateTime(new Date());
		check.setUpdater(currenLoginName);
		check.setUpdateTime(new Date());
		stockInOutCheckMapper.insert(check);
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
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setSerialNum(orderSerial);
		orderInfo.setDeliverStatus(OrderInfo.OUTRECORD);//入库完成/待收票
		orderInfo.setUpdateTime(new Date());
		orderInfo.setUpdater(currenLoginName);
		orderInfoMapper.updateByPrimaryKeySelective(orderInfo);
		//更新发货状态
		Delivery delivery = new Delivery();
		delivery.setSerialNum(deliverySerial);
		delivery.setStatus("4");//状态未定
		delivery.setUpdateTime(new Date());
		delivery.setUpdater(currenLoginName);
		//delivery2Mapper.updateByPrimaryKeySelective(delivery);
	}

	


	

}
