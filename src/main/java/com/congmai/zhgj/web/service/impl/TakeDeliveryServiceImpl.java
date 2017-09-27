package com.congmai.zhgj.web.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.congmai.zhgj.core.feature.orm.mybatis.Page;
import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.log.annotation.OperationLog;
import com.congmai.zhgj.web.dao.Delivery2Mapper;
import com.congmai.zhgj.web.dao.DeliveryMaterielMapper;
import com.congmai.zhgj.web.dao.DeliveryTransportMapper;
import com.congmai.zhgj.web.dao.OrderInfoMapper;
import com.congmai.zhgj.web.dao.StockInOutCheckMapper;
import com.congmai.zhgj.web.dao.StockInOutRecordMapper;
import com.congmai.zhgj.web.dao.TakeDeliveryMapper;
import com.congmai.zhgj.web.model.Delivery;
import com.congmai.zhgj.web.model.DeliveryExample;
import com.congmai.zhgj.web.model.DeliveryMateriel;
import com.congmai.zhgj.web.model.DeliveryMaterielExample;
import com.congmai.zhgj.web.model.DeliveryTransportExample;
import com.congmai.zhgj.web.model.OrderInfo;
import com.congmai.zhgj.web.model.StockInOutCheck;
import com.congmai.zhgj.web.model.StockInOutRecord;
import com.congmai.zhgj.web.model.StockInOutRecordExample;
import com.congmai.zhgj.web.model.TakeDelivery;
import com.congmai.zhgj.web.model.TakeDeliveryExample;
import com.congmai.zhgj.web.model.TakeDeliveryParams;
import com.congmai.zhgj.web.model.TakeDeliverySelectExample;
import com.congmai.zhgj.web.model.TakeDeliverySelectExample.Criteria;
import com.congmai.zhgj.web.model.TakeDeliveryVO;
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
	public void insertTakeDelivery(TakeDeliveryParams takeDeliveryParams,
			String currenLoginName) {
		takeDeliveryParams = getTakeDeliveryData(takeDeliveryParams,currenLoginName);
		delivery2Mapper.insert(takeDeliveryParams.getDelivery());
		
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setSerialNum(takeDeliveryParams.getDelivery().getOrderSerial());
		orderInfo.setDeliverStatus(OrderInfo.DELIVER);//确认发货
		orderInfo.setUpdateTime(new Date());
		orderInfo.setUpdater(currenLoginName);
		orderInfoMapper.updateByPrimaryKeySelective(orderInfo);
		
		deliveryTransportMapper.insert(takeDeliveryParams.getDeliveryTransport());
		takeDeliveryMapper.insert(takeDeliveryParams.getTakeDelivery());
		for(DeliveryMateriel materiel : takeDeliveryParams.getDeliveryMateriels()){
			deliveryMaterielMapper.insert(materiel);
		}
	}
	
	private TakeDeliveryParams getTakeDeliveryData(TakeDeliveryParams takeDeliveryParams,String currenLoginName){
		String deliverySerial = ApplicationUtils.random32UUID();
		String takeDeliverySerial = ApplicationUtils.random32UUID();
		Date now = new Date();
		if(StringUtils.isEmpty(takeDeliveryParams.getTakeDelivery().getSerialNum())){
			takeDeliveryParams.getTakeDelivery().setSerialNum(takeDeliverySerial);
			takeDeliveryParams.getTakeDelivery().setTakeDeliverNum("SH"+ApplicationUtils.getFromNumber());
			takeDeliveryParams.getTakeDelivery().setDeliverSerial(deliverySerial);
			takeDeliveryParams.getTakeDelivery().setStatus("0");
			takeDeliveryParams.getTakeDelivery().setCreator(currenLoginName);
			takeDeliveryParams.getTakeDelivery().setCreateTime(now);
		}
		takeDeliveryParams.getTakeDelivery().setUpdater(currenLoginName);
		takeDeliveryParams.getTakeDelivery().setUpdateTime(now);
		takeDeliveryParams.getTakeDelivery().setDelFlg("0");

		if(takeDeliveryParams.getDelivery()!=null){
			if(StringUtils.isEmpty(takeDeliveryParams.getDelivery().getSerialNum())){
				takeDeliveryParams.getDelivery().setSerialNum(deliverySerial);
				takeDeliveryParams.getDelivery().setTakeDeliverSerial(takeDeliverySerial);
				takeDeliveryParams.getDelivery().setCreator(currenLoginName);
				takeDeliveryParams.getDelivery().setCreateTime(now);
			}
			takeDeliveryParams.getDelivery().setUpdater(currenLoginName);
			takeDeliveryParams.getDelivery().setUpdateTime(now);
			takeDeliveryParams.getDelivery().setDelFlg("0");
			takeDeliveryParams.getDelivery().setStatus("3"); //已发货
		}
		
		if(takeDeliveryParams.getDeliveryTransport()!=null){
			if(StringUtils.isEmpty(takeDeliveryParams.getDeliveryTransport().getSerialNum())){
				takeDeliveryParams.getDeliveryTransport().setSerialNum(ApplicationUtils.random32UUID());
				takeDeliveryParams.getDeliveryTransport().setDeliverSerial(deliverySerial);
				takeDeliveryParams.getDeliveryTransport().setCreator(currenLoginName);
				takeDeliveryParams.getDeliveryTransport().setCreateTime(now);
			}
			takeDeliveryParams.getDeliveryTransport().setUpdater(currenLoginName);
			takeDeliveryParams.getDeliveryTransport().setUpdateTime(now);
			takeDeliveryParams.getDeliveryTransport().setDelFlg("0");
		}
		
		for(DeliveryMateriel materiel : takeDeliveryParams.getDeliveryMateriels()){
			materiel.setSerialNum(ApplicationUtils.random32UUID());
			materiel.setDeliverSerial(takeDeliveryParams.getDelivery().getSerialNum());
			materiel.setCreator(currenLoginName);
			materiel.setCreateTime(now);
			materiel.setUpdater(currenLoginName);
			materiel.setUpdateTime(now);
			materiel.setDelFlg("0");
		}
		
		return takeDeliveryParams;
	}
	@Override
	public Delivery selectByTakeDeliveryPrimaryKey(String serialNum) {
		Delivery delivery = this.takeDeliveryMapper.selectByTakeDeliveryPrimaryKey(serialNum);
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
	public void updateTakeDelivery(TakeDeliveryParams takeDeliveryParams,
			String currenLoginName) {
		takeDeliveryParams = getTakeDeliveryData(takeDeliveryParams,currenLoginName);
		
		DeliveryExample d_example = new DeliveryExample();
		d_example.createCriteria().andSerialNumEqualTo(takeDeliveryParams.getDelivery().getSerialNum());
		delivery2Mapper.updateByExampleSelective(takeDeliveryParams.getDelivery(),d_example);
		
		DeliveryTransportExample dt_example = new DeliveryTransportExample();
		dt_example.createCriteria().andSerialNumEqualTo(takeDeliveryParams.getDeliveryTransport().getSerialNum());
		deliveryTransportMapper.updateByExampleSelective(takeDeliveryParams.getDeliveryTransport(),dt_example);
		
		TakeDeliveryExample td_example = new TakeDeliveryExample();
		td_example.createCriteria().andSerialNumEqualTo(takeDeliveryParams.getTakeDelivery().getSerialNum());
		takeDeliveryMapper.updateByExampleSelective(takeDeliveryParams.getTakeDelivery(),td_example);
		
		DeliveryMateriel dm = new DeliveryMateriel();
		dm.setDelFlg("1");
		DeliveryMaterielExample dmExample = new DeliveryMaterielExample();
		dmExample.createCriteria().andDeliverSerialEqualTo(takeDeliveryParams.getDelivery().getSerialNum());
		deliveryMaterielMapper.updateByExampleSelective(dm,dmExample);
		
		for(DeliveryMateriel materiel : takeDeliveryParams.getDeliveryMateriels()){
				deliveryMaterielMapper.insert(materiel);
		}
	}

	@Override
	public void insertStockInData(TakeDeliveryParams takeDeliveryParams,
			String currenLoginName) {
		
		StockInOutRecord record = takeDeliveryParams.getRecord();
		record.setSerialNum(ApplicationUtils.random32UUID());
		record.setCreator(currenLoginName);
		record.setCreateTime(new Date());
		record.setUpdater(currenLoginName);
		record.setUpdateTime(new Date());
		record.setStatus("0");
		record.setDelFlg("0");
		stockInOutRecordMapper.insert(record);
		
		List<DeliveryMateriel> materiels = takeDeliveryParams.getDeliveryMateriels(); //这里是入库的物料信息
		for(DeliveryMateriel materiel : materiels){
			DeliveryMaterielExample example = new DeliveryMaterielExample();
			example.createCriteria().andSerialNumEqualTo(materiel.getSerialNum());
			deliveryMaterielMapper.updateByExampleSelective(materiel, example);
			//materiel.setSerialNum(ApplicationUtils.random32UUID());
			//materiel.setDeliverSerial(record.getTakeDeliverSerial());
			//deliveryMaterielMapper.insert(materiel);
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
				clearStockInInfoFormMateriels(delete_record.getDelivery().getDeliveryMateriels());
			}
		}
		
	}

	@Override
	@OperationLog(operateType = "add" ,operationDesc = "入库" ,objectSerial= "{serialNum}")
	public void updateStockInData(TakeDeliveryParams takeDeliveryParams,
			String currenLoginName,String type) {
		StockInOutRecord record = takeDeliveryParams.getRecord();
		
		
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
			
			//更新订单状态
			OrderInfo orderInfo = new OrderInfo();
			orderInfo.setSerialNum(old_delivery.getOrderSerial());
			orderInfo.setDeliverStatus(OrderInfo.INRECORD);//入库完成/待收票
			orderInfo.setUpdateTime(new Date());
			orderInfo.setUpdater(currenLoginName);
			orderInfoMapper.updateByPrimaryKeySelective(orderInfo);
			//更新收货状态
			TakeDelivery takeDelivery = new TakeDelivery();
			takeDelivery.setSerialNum(takeDeliverySerial);
			takeDelivery.setStatus("4");//收货完成
			takeDelivery.setUpdateTime(new Date());
			takeDelivery.setUpdater(currenLoginName);
			takeDeliveryMapper.updateByPrimaryKeySelective(takeDelivery);
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
			
			//更新订单状态
			OrderInfo orderInfo = new OrderInfo();
			orderInfo.setSerialNum(old_delivery.getOrderSerial());
			orderInfo.setDeliverStatus(OrderInfo.OUTRECORD);//入库完成/待收票
			orderInfoMapper.updateByPrimaryKeySelective(orderInfo);
			//更新发货状态
			Delivery delivery = new Delivery();
			delivery.setSerialNum(deliverySerial);
			delivery.setStatus("4");//状态未定
			delivery.setUpdateTime(new Date());
			delivery.setUpdater(currenLoginName);
			//delivery2Mapper.updateByPrimaryKeySelective(delivery);
			//跟新发货状态
		}
		
		
		List<DeliveryMateriel> materiels = takeDeliveryParams.getDeliveryMateriels(); //这里是入库的物料信息
		for(DeliveryMateriel materiel : materiels){
			DeliveryMaterielExample example2 = new DeliveryMaterielExample();
			example2.createCriteria().andSerialNumEqualTo(materiel.getSerialNum());
			deliveryMaterielMapper.updateByExampleSelective(materiel, example2);
			//materiel.setSerialNum(ApplicationUtils.random32UUID());
			//materiel.setDeliverSerial(old_delivery.getTakeDelivery().getSerialNum());
			//deliveryMaterielMapper.insert(materiel);
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
			deliveryMaterielMapper.deleteByPrimaryKey(materiel.getStockInSerialNum());
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
	public void confirmTakeDelivery(TakeDeliveryParams takeDeliveryParams,
			String currenLoginName) throws Exception {
		takeDeliveryParams = getConfirmTakeDeliveryData(takeDeliveryParams,currenLoginName);
		//(takeDeliveryParams.getTakeDelivery());
		//TakeDelivery takeDelivery = new TakeDelivery();
		
		//takeDelivery.setSerialNum(takeDeliveryParams.getTakeDelivery().getSerialNum());
		//takeDelivery.set(takeDeliveryParams.getTakeDelivery().getSerialNum());
		//takeDelivery.setSerialNum(takeDeliveryParams.getTakeDelivery().getSerialNum());
		//takeDelivery.setSerialNum(takeDeliveryParams.getTakeDelivery().getSerialNum());
		
		takeDeliveryParams.getTakeDelivery().setStatus(TakeDelivery.APPLY_COMPLETE); //待检验
		takeDeliveryMapper.updateByPrimaryKeySelective(takeDeliveryParams.getTakeDelivery());
		
		//没有审批时，直接收货
		TakeDelivery takeDelivery = takeDeliveryMapper.selectByPrimaryKey(takeDeliveryParams.getTakeDelivery().getSerialNum());
		takeDeliveryParams.getTakeDelivery().setDeliverSerial(takeDelivery.getDeliverSerial());
		
		this.createStockInCheckRecord(takeDeliveryParams.getTakeDelivery(),currenLoginName);
		
		//删除已保存的收货物料
		DeliveryMaterielExample example = new DeliveryMaterielExample();
		example.createCriteria().andDeliverSerialEqualTo(takeDeliveryParams.getTakeDelivery().getSerialNum());
		deliveryMaterielMapper.deleteByExample(example);
		
	   for(DeliveryMateriel materiel : takeDeliveryParams.getDeliveryMateriels()){
			materiel.setSerialNum(ApplicationUtils.random32UUID());
			materiel.setDeliverSerial(takeDeliveryParams.getTakeDelivery().getSerialNum());
			materiel.setDelFlg("0");
			materiel.setCreator(currenLoginName);
			materiel.setCreateTime(new Date());
			materiel.setUpdater(currenLoginName);
			materiel.setUpdateTime(new Date());
			deliveryMaterielMapper.insert(materiel);
		}
		
	}

	private TakeDeliveryParams getConfirmTakeDeliveryData(
			TakeDeliveryParams takeDeliveryParams, String currenLoginName) {
		Date now = new Date();
		
		for(DeliveryMateriel materiel : takeDeliveryParams.getDeliveryMateriels()){
			//materiel.setSerialNum(ApplicationUtils.random32UUID());
			//materiel.setDeliverSerial(takeDeliveryParams.getTakeDelivery().getSerialNum());
			//materiel.setCreator(currenLoginName);
			//materiel.setCreateTime(now);
			materiel.setUpdater(currenLoginName);
			materiel.setUpdateTime(now);
			//materiel.setDelFlg("0");
		}
		
		return takeDeliveryParams;
	}

	@Override
	public TakeDelivery selectByPrimaryKey(String serialNum) {
		
		return takeDeliveryMapper.selectByPrimaryKey(serialNum);
	}

	@Override
	public int updateByPrimaryKeySelective(TakeDelivery record) {
		
		return takeDeliveryMapper.updateByPrimaryKeySelective(record);
	}

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

	

}
