package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.StockInOutCheck;
public interface StockInOutCheckService extends GenericService<StockInOutCheck, String>{

	void deleteStockInOutCheck(String serialNumList);//删除出入库检验
	List<StockInOutCheck>getAllStockInOutCheck(String  InOut);//获取出入库检验记录
	
}
