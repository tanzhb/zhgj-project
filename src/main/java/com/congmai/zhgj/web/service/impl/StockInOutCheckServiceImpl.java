package com.congmai.zhgj.web.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.web.dao.DeliveryMapper;
import com.congmai.zhgj.web.dao.OrderInfoMapper;
import com.congmai.zhgj.web.dao.StockInOutCheckMapper;
import com.congmai.zhgj.web.dao.StockInOutRecordMapper;
import com.congmai.zhgj.web.dao.StockMapper;
import com.congmai.zhgj.web.dao.TakeDeliveryMapper;
import com.congmai.zhgj.web.model.Delivery;
import com.congmai.zhgj.web.model.DeliveryMateriel;
import com.congmai.zhgj.web.model.DeliveryVO;
import com.congmai.zhgj.web.model.OrderInfo;
import com.congmai.zhgj.web.model.Stock;
import com.congmai.zhgj.web.model.StockExample;
import com.congmai.zhgj.web.model.StockInOutCheck;
import com.congmai.zhgj.web.model.StockInOutCheckExample;
import com.congmai.zhgj.web.model.StockInOutCheckExample.Criteria;
import com.congmai.zhgj.web.model.StockInOutRecord;
import com.congmai.zhgj.web.model.StockInOutRecordExample;
import com.congmai.zhgj.web.model.TakeDelivery;
import com.congmai.zhgj.web.service.StockInOutCheckService;
import com.congmai.zhgj.web.service.StockService;

/**
 * 
 * @ClassName StockInOutServiceImpl
 * @Descripzhaichao
 * @Date 2017年8月23日 上午11:56:29
 * @version 1.0.0
 */
@Service
public class StockInOutCheckServiceImpl extends GenericServiceImpl<StockInOutCheck, String> implements StockInOutCheckService {

	 @Resource
	    private StockInOutCheckMapper  stockInOutCheckMapper;
	 @Resource
	    private StockInOutRecordMapper  stockInOutRecordMapper;
	 @Resource
	 private TakeDeliveryMapper  takeDeliveryMapper;
	 @Resource
	 private DeliveryMapper deliveryMapper;
	 @Resource
	 private OrderInfoMapper orderInfoMapper;

	@Override
	public GenericDao<StockInOutCheck, String> getDao() {
		return stockInOutCheckMapper;
	}

	@Override
	public void deleteStockInOutCheck(String serialNumList) {
		List<String> idList = ApplicationUtils.getIdList(serialNumList);
		stockInOutCheckMapper.deleteStockInOutCheck(idList);
		
	}

	@Override
	public List<StockInOutCheck> getAllStockInOutCheck(String  InOut,String serialNum) {
		StockInOutCheckExample sore=new  StockInOutCheckExample();
    	Criteria criteria=sore.createCriteria();
    	criteria.andDelFlgEqualTo("0");
    	if("in".equals(InOut)){//入库
    		criteria.andDeliverSerialLike("checkin");
    		criteria.andTakeDeliverSerialIsNotNull();
    		if(!StringUtils.isEmpty(serialNum)){
    			criteria.andTakeDeliverSerialEqualTo(serialNum);
    		}
    	}else if("out".equals(InOut)){//出库
    		criteria.andTakeDeliverSerialLike("checkout");
    		criteria.andDeliverSerialIsNotNull();
    		if(!StringUtils.isEmpty(serialNum)){
    			criteria.andDeliverSerialEqualTo(serialNum);
    		}
    	}
		return stockInOutCheckMapper.selectByExample(sore);
	}

	@Override
	public void updateStockInOutCheckStatus(String serialNum,String serialNum1,String userName) {
		stockInOutCheckMapper.updateStockInOutCheckStatus(serialNum);
		StockInOutCheck stockInOutCheck=stockInOutCheckMapper.selectByPrimaryKey(serialNum);
		if(serialNum1.indexOf("checkin")>-1){
			createStockInOutRecord("checkin",stockInOutCheck.getTakeDeliverSerial(),userName);//产生入库记录
			TakeDelivery td=takeDeliveryMapper.selectByPrimaryKey(stockInOutCheck.getTakeDeliverSerial());
			td.setStatus(TakeDelivery.CHECK_COMPLETE);
			takeDeliveryMapper.updateByPrimaryKey(td);
			DeliveryVO d=deliveryMapper.selectDetailById(td.getDeliverSerial());
			OrderInfo o =orderInfoMapper.selectByPrimaryKey(d.getOrderSerial());
			o.setStatus("6");
			orderInfoMapper.updateByPrimaryKey(o);
		}else if(serialNum1.indexOf("checkout")>-1){
			createStockInOutRecord("checkout",stockInOutCheck.getDeliverSerial(),userName);//产生出库记录
			//String orderSerial=td.getDeliverSerial();
			/*Delivery d=deliveryMapper.
			d.setStatus("");
			deliveryMapper.updateByPrimaryKey(d);*/
		}
		
	}

	public void	createStockInOutRecord(String judgeString,String  serial,String userName){
		StockInOutRecord stockInOutRecord=new StockInOutRecord();
		stockInOutRecord.setSerialNum(ApplicationUtils.random32UUID());
		if("checkin".equals(judgeString)){
			stockInOutRecord.setTakeDeliverSerial(serial);
			stockInOutRecord.setDeliverSerial("");
		}else{
			stockInOutRecord.setDeliverSerial(serial);
			stockInOutRecord.setTakeDeliverSerial("");
		}
		stockInOutRecord.setInOutNum("RK"+ApplicationUtils.getFromNumber());
		stockInOutRecord.setDelFlg("0");
		stockInOutRecord.setStatus("0");
		stockInOutRecord.setCreator(userName);
		stockInOutRecord.setCreateTime(new Date());
		stockInOutRecord.setUpdater(userName);
		stockInOutRecord.setUpdateTime(new Date());
		stockInOutRecordMapper.insert(stockInOutRecord);
		
	}

	
	
	}
	
	
	  
	    

   

