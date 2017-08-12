package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.feature.orm.mybatis.Page;
import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.Company;
import com.congmai.zhgj.web.model.Materiel;
import com.congmai.zhgj.web.model.MaterielExample;
import com.congmai.zhgj.web.model.PriceList;
import com.congmai.zhgj.web.model.PriceListExample;
import com.congmai.zhgj.web.model.Warehouse;
import com.congmai.zhgj.web.model.WarehouseExample;

public interface PriceListService extends GenericService<PriceList, String>{

	

	void deletePriceList(String serialNumList);
	 List<PriceList> selectPriceList(PriceListExample ple);
	 //List<Materiel> selectList(MaterielExample m);
	
}
