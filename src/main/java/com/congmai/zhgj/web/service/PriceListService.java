package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.feature.orm.mybatis.Page;
import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.Company;
import com.congmai.zhgj.web.model.PriceList;

public interface PriceListService extends GenericService<PriceList, String>{

	

	void deletePriceList(String serialNumList);
	
}
