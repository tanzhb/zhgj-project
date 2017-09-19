package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.DeliveryMateriel;
import com.congmai.zhgj.web.model.StockInOutCheck;
public interface StockInOutCheckService extends GenericService<StockInOutCheck, String>{

	void deleteStockInOutCheck(String serialNumList);//删除出入库检验
	List<StockInOutCheck>getAllStockInOutCheck(String  InOut,String serialNum);//获取出入库检验记录
	void updateStockInOutCheckStatus(String serialNum,String serialNum1,String userName);//更新状态
	
	
	
}
