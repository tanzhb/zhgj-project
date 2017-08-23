package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.PriceList;
import com.congmai.zhgj.web.model.PriceListExample;
import com.congmai.zhgj.web.model.Stock;
import com.congmai.zhgj.web.model.StockExample;

public interface StockService extends GenericService<Stock, String>{

	void deleteStock(String serialNums);
	 List<Stock> selectStockList();

	
	
}
