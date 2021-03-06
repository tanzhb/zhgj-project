package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.LadderPrice;
import com.congmai.zhgj.web.model.PriceList;

public interface LadderPriceService extends GenericService<LadderPrice, String>{


	
	PriceList selectOne(String priceSerial);
	LadderPrice selectLadderPrice(String serialNum);//获取阶梯价格信息根据流水
	void insertOrUpdateLadderPrices(List<LadderPrice> ladderPrices);
	void insertLadderPrices(List<LadderPrice> ladderPrices,String userName);
//	void updateLadderPrices(List<LadderPrice> ladderPrices);
	List<LadderPrice> selectListByPriceSerial(String PriceSerial);//根据价格流水查找阶梯价格信息

	void deleteByPriceSerial(LadderPrice record);//删除多个通过价格流水
	void deleteBySerialNum(String serialNum);//删除单个通过流水
}
