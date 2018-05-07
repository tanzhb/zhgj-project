package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.DeliveryMateriel;
import com.congmai.zhgj.web.model.LadderPrice;
import com.congmai.zhgj.web.model.Stock;
import com.congmai.zhgj.web.model.StockSupplyRecord;
import com.congmai.zhgj.web.model.Warehouse;
import com.congmai.zhgj.web.model.Warehouseposition;


/**
 * @ClassName StockSupplyRecordService
 * @Description TODO(供应商库存)
 * @author zhaichao
 * @Date 2018年4月24日 下午5:30:38
 * @version 1.0.0
 */
public interface StockSupplyRecordService extends GenericService<StockSupplyRecord, String> {
   
	void deleteStockSupply(String serialNums);
	List<StockSupplyRecord>getSupplyListForIn(String serialNum);//根据库存流水号获取供应商库存入库记录
	 List<StockSupplyRecord>getSupplyListForOut(String serialNum);//根据库存流水号获取供应商库存出库记录
	 List<StockSupplyRecord> getStockSupplyRecordBySupplyComId(String comId);//通过供应商comid查供应商库存
	 Stock   judgeSupplyStockIsExist(String materielSerial,String supplyComId);
	 String    currentAmountForSupply(String serialNums);
	 String   countInAmountForSupply(String serialNums);
	 String   countOutAmountForSupply(String serialNums);
	 String   getLastOutDateForSupply(String serialNums);
	 String   getLastInDateForSupply(String serialNums);
}
