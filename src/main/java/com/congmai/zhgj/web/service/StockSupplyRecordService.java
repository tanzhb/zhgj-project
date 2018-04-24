package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.LadderPrice;
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
	
}
