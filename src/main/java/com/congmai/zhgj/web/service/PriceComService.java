package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.PriceCom;
public interface PriceComService extends GenericService<PriceCom, String>{


	
	List<PriceCom> insertPriceComs(List<PriceCom>priceComs,String userName);//插入价格关联企业信息
	List<PriceCom> selectListByPriceSerial(String PriceSerial);//根据价格流水查找采供应商列表
	void deleteByPriceSerial(String PriceSerial);//删除多个通过价格流水

}
