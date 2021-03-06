package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.DeliveryMateriel;
import com.congmai.zhgj.web.model.PriceList;
import com.congmai.zhgj.web.model.PriceListExample;
import com.congmai.zhgj.web.model.Stock;
import com.congmai.zhgj.web.model.StockExample;
import com.congmai.zhgj.web.model.StockInBatch;
import com.congmai.zhgj.web.model.StockOutBatch;
import com.congmai.zhgj.web.model.StockSupplyRecord;

public interface StockService extends GenericService<Stock, String>{

	void deleteStock(String serialNums);
	 List<Stock> selectStockList(String  manageType);
	 
	 List<String> getRelationDeliverSerialList(Stock   stock);//获取库存相关发货流水
	 List<String> getRelationTakeDeliverSerialList(Stock   stock);//获取库存相关收获流水
	 List<DeliveryMateriel>getDeliverMaterialListForIn(List<String>takeDeliverSerialList,String buyComId,Stock   stock);//获取库存入库记录
	 List<DeliveryMateriel>getDeliverMaterialListForOut(List<String>deliverSerialList,Stock   stock);//获取库存出库记录
	 List<Stock> selectStockListByMaterielSerial(String  materielSerial);//通过基本物料流水获取库存信息
	 
	String  getCountInAmountForZijian(String serialNum);//自建库存根据基本物料流水查入库总数
	String  getCountOutAmountForZijian(String serialNum);//自建库存根据基本物料流水查出库总数
	String  getCountInAmountForDaiguan(String serialNum, String comId);//代管库存根据基本物料流水查入库总数
	String  getCountOutAmountForDaiguan(String serialNum, String comId);//代管库存根据基本物料流水查出库总数
	List<Stock> selectStockListByComId(String manageType, String comId, String serialNums);
	List<DeliveryMateriel> getStockInBatchList(String serialNum);
	List<DeliveryMateriel> getStockInBatchListByMaterielOwn(String serialNum,
			String orderSerial);
	List<StockOutBatch> getStockOutBatchListByDmSerialNum(String serialNum);//通过发货物料查关连出库批次
	
	
	
}
