package com.congmai.zhgj.web.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.core.util.MessageConstants;
import com.congmai.zhgj.log.annotation.OperationLog;
import com.congmai.zhgj.web.dao.DeliveryMapper;
import com.congmai.zhgj.web.dao.OrderInfoMapper;
import com.congmai.zhgj.web.dao.StockInOutCheckMapper;
import com.congmai.zhgj.web.dao.StockInOutRecordMapper;
import com.congmai.zhgj.web.dao.StockMapper;
import com.congmai.zhgj.web.dao.TakeDeliveryMapper;
import com.congmai.zhgj.web.event.EventExample;
import com.congmai.zhgj.web.event.SendMessageEvent;
import com.congmai.zhgj.web.model.ClauseSettlementDetail;
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
import com.congmai.zhgj.web.service.ContractService;
import com.congmai.zhgj.web.service.OrderService;
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
	 @Resource
	 private OrderService orderService;
	 @Resource
	 private ContractService contractService;

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
	@OperationLog(operateType = "add" ,operationDesc = "检验" ,objectSerial= "{serialNum}")
	public void updateStockInOutCheckStatus(String serialNum,String serialNum1,String userName) {
		stockInOutCheckMapper.updateStockInOutCheckStatus(serialNum);
		StockInOutCheck stockInOutCheck=stockInOutCheckMapper.selectByPrimaryKey(serialNum);
		if(serialNum1.indexOf("checkin")>-1){
			createStockInOutRecord("checkin",stockInOutCheck.getTakeDeliverSerial(),userName);//产生入库记录
			TakeDelivery td=takeDeliveryMapper.selectByPrimaryKey(stockInOutCheck.getTakeDeliverSerial());
			td.setStatus(TakeDelivery.CHECK_COMPLETE);
			takeDeliveryMapper.updateByPrimaryKey(td);
			DeliveryVO d=deliveryMapper.selectDetailById(td.getDeliverSerial());
			
			OrderInfo o = new OrderInfo();
			o.setSerialNum(d.getOrderSerial());
			o.setDeliverStatus(OrderInfo.CHECK);
			orderInfoMapper.updateByPrimaryKeySelective(o);
			
			//入库检验消息  to 采购
			//EventExample.getEventPublisher().publicSendMessageEvent(new SendMessageEvent(stockInOutCheck,MessageConstants.IN_CHECK_TO_BUY));
			//入库检验消息  to 供应
			//EventExample.getEventPublisher().publicSendMessageEvent(new SendMessageEvent(stockInOutCheck,MessageConstants.IN_CHECK_TO_SALE));
			
			//按结算条款中的签订合同节点生成付款
			String orderString = d.getOrderSerial();
			String nodeString = ClauseSettlementDetail.YSH;
			contractService.findPaymentNode(orderString, nodeString);
			
		}else if(serialNum1.indexOf("checkout")>-1){
			createStockInOutRecord("checkout",stockInOutCheck.getDeliverSerial(),userName);//产生出库记录
			//String orderSerial=td.getDeliverSerial();
			/*Delivery d=deliveryMapper.
			d.setStatus("");
			deliveryMapper.updateByPrimaryKey(d);*/
			
			DeliveryVO d=deliveryMapper.selectDetailById(stockInOutCheck.getDeliverSerial());
			OrderInfo o = new OrderInfo();
			o.setSerialNum(d.getOrderSerial());
			o.setDeliverStatus(OrderInfo.CHECK);
			orderInfoMapper.updateByPrimaryKeySelective(o);
			
			//按结算条款中的签订合同节点生成付款
			String orderString = d.getOrderSerial();
			String nodeString = ClauseSettlementDetail.YSH;
			contractService.findPaymentNode(orderString, nodeString);
			//出库检验消息  to 采购
			//EventExample.getEventPublisher().publicSendMessageEvent(new SendMessageEvent(stockInOutCheck,MessageConstants.OUT_CHECK_TO_BUY));
			//出库检验消息  to 供应
			//EventExample.getEventPublisher().publicSendMessageEvent(new SendMessageEvent(stockInOutCheck,MessageConstants.OUT_CHECK_TO_SALE));
		}
		
	}

	public void	createStockInOutRecord(String judgeString,String  serial,String userName){
		StockInOutRecord stockInOutRecord=new StockInOutRecord();
		stockInOutRecord.setSerialNum(ApplicationUtils.random32UUID());
		if("checkin".equals(judgeString)){
			stockInOutRecord.setTakeDeliverSerial(serial);
			stockInOutRecord.setDeliverSerial("");
			stockInOutRecord.setInOutNum(orderService.getNumCode("IN"));
		}else{
			stockInOutRecord.setDeliverSerial(serial);
			stockInOutRecord.setTakeDeliverSerial("");
			stockInOutRecord.setInOutNum(orderService.getNumCode("OU"));
		}
		stockInOutRecord.setDelFlg("0");
		stockInOutRecord.setStatus("0");
		stockInOutRecord.setCreator(userName);
		stockInOutRecord.setCreateTime(new Date());
		stockInOutRecord.setUpdater(userName);
		stockInOutRecord.setUpdateTime(new Date());
		stockInOutRecordMapper.insert(stockInOutRecord);
		
	}

	@Override
	public StockInOutCheck getStockInOutCheck(String serialNum) {
		// TODO Auto-generated method stub
		return stockInOutCheckMapper.selectByPrimaryKey(serialNum);
	}

	@Override
	public StockInOutRecord getStockInOutRecord(String serialNum) {
		// TODO Auto-generated method stub
		return stockInOutRecordMapper.selectByPrimaryKey(serialNum);
	}

	
	
	}
	
	
	  
	    

   

