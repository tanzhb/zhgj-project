package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.feature.orm.mybatis.Page;
import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.Delivery;
import com.congmai.zhgj.web.model.DeliveryMateriel;
import com.congmai.zhgj.web.model.DeliveryTransport;
import com.congmai.zhgj.web.model.StockInOutRecord;
import com.congmai.zhgj.web.model.TakeDelivery;
import com.congmai.zhgj.web.model.TakeDeliveryParams;
import com.congmai.zhgj.web.model.TakeDeliveryVO;

public interface TakeDeliveryService extends GenericService<TakeDelivery, String>{
	
	Delivery selectByTakeDeliveryPrimaryKey(String serialNum);

	Page<Delivery> selectByPage(Delivery takeDelivery);

	void insertTakeDelivery(Delivery delivery,TakeDelivery takeDelivery,DeliveryTransport deliveryTransport,List<DeliveryMateriel> deliveryMateriels,
			String currenLoginName);

	void deleteBatch(List<String> serialNumArray);

	void updateTakeDelivery(Delivery delivery,TakeDelivery takeDelivery,DeliveryTransport deliveryTransport,List<DeliveryMateriel> deliveryMateriels,
			String currenLoginName);

	void insertStockInData(StockInOutRecord record,List<DeliveryMateriel> deliveryMateriels,String currenLoginName,String type);

	Page<Delivery> selectStockInListByPage(StockInOutRecord record);
	
	StockInOutRecord selectStockInOutRecordByPrimayKey(String serialNum,String type);

	void deleteStockInInfo(List<String> serialNumArray);

	void updateStockInData(StockInOutRecord record,List<DeliveryMateriel> deliveryMateriels,String currenLoginName,String type);
	
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
	void confirmTakeDelivery(TakeDelivery takeDelivery,List<DeliveryMateriel> deliveryMateriels,
			String currenLoginName)throws Exception;

	void updateStockOutData(StockInOutRecord record,
			List<DeliveryMateriel> deliveryMateriels, String currenLoginName,
			String string);

	
	
		

}
