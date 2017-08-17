package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.PriceList;
import com.congmai.zhgj.web.model.PriceListExample;

public interface PriceListService extends GenericService<PriceList, String>{

	

	void deletePriceList(String serialNumList);
	 List<PriceList> selectPriceList(PriceListExample ple);
	 //List<Materiel> selectList(MaterielExample m);
	
}
