package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.PriceList;
import com.congmai.zhgj.web.model.PriceListExample;

public interface PriceListService extends GenericService<PriceList, String>{

	

	void deletePriceList(String serialNumList);
	 List<PriceList> selectPriceList(PriceListExample ple);
	 //List<Materiel> selectList(MaterielExample m);
	 PriceList getPriceListInfoByPriceId(String priceId);//获取最新价格
	 void updateVersion(PriceList priceList);//将之前是否最新版本值置为0
	 List<PriceList> getAllPriceListInfoByPriceIdOrPriceType(String priceId,String priceType);//获取所有价格
	
}
