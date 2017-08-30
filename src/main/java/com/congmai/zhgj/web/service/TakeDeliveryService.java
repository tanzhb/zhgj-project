package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.feature.orm.mybatis.Page;
import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.Delivery;
import com.congmai.zhgj.web.model.StockInOutRecord;
import com.congmai.zhgj.web.model.TakeDelivery;
import com.congmai.zhgj.web.model.TakeDeliveryParams;

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
	
	StockInOutRecord selectStockInOutRecordByPrimayKey(String serialNum);

	void deleteStockInInfo(List<String> serialNumArray);

	void updateStockInData(TakeDeliveryParams takeDeliveryParams,
			String currenLoginName);
	
		

}
