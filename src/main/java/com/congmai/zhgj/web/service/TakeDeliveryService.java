package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.feature.orm.mybatis.Page;
import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.Delivery;
import com.congmai.zhgj.web.model.StockInOutRecord;
import com.congmai.zhgj.web.model.TakeDelivery;
import com.congmai.zhgj.web.model.TakeDeliveryParams;
import com.congmai.zhgj.web.model.TakeDeliveryVO;

public interface TakeDeliveryService extends GenericService<TakeDelivery, String>{
	
	Delivery selectByTakeDeliveryPrimaryKey(String serialNum);

	Page<Delivery> selectByPage(Delivery takeDelivery);

	void insertTakeDelivery(TakeDeliveryParams takeDeliveryParams,
			String currenLoginName);

	void deleteBatch(List<String> serialNumArray);

	void updateTakeDelivery(TakeDeliveryParams takeDeliveryParams,
			String currenLoginName);

	void insertStockInData(TakeDeliveryParams takeDeliveryParams,
			String currenLoginName);

	Page<Delivery> selectStockInListByPage(StockInOutRecord record);
	
	StockInOutRecord selectStockInOutRecordByPrimayKey(String serialNum,String type);

	void deleteStockInInfo(List<String> serialNumArray);

	void updateStockInData(TakeDeliveryParams takeDeliveryParams,
			String currenLoginName,String type);
	
	TakeDelivery selectByPrimaryKey(String serialNum);
	
	int updateByPrimaryKeySelective(TakeDelivery record);
	
	/**
	 * 
	 * @Description (TODO生成入库单)
	 */
	void createStockInRecord(String takeDeliverySerial,String currenLoginName);
	
	/**
	 * 
	 * @Description (TODO生成出库单)
	 */
	void createStockOutRecord(String deliverySerial,String currenLoginName);
	
	/**
	 * 
	 * @Description (TODO生成入库检验单)
	 */
	void createStockInCheckRecord(TakeDelivery takeDelivery,String currenLoginName) throws Exception ;
	
	/**
	 * 
	 * @Description (TODO生成出库检验单)
	 */
	void createStockOutCheckRecord(String deliverySerial,String currenLoginName);

	/**
	 * 
	 * @Description (TODO发货)
	 * @param takeDeliveryParams
	 * @param currenLoginName
	 */
	void confirmTakeDelivery(TakeDeliveryParams takeDeliveryParams,
			String currenLoginName);

	TakeDeliveryVO selectDetailById(String deliverSerial);
	
		

}
