package com.congmai.zhgj.web.service;

import com.congmai.zhgj.core.feature.orm.mybatis.Page;
import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.Delivery;
import com.congmai.zhgj.web.model.TakeDelivery;
import com.congmai.zhgj.web.model.TakeDeliveryParams;

public interface TakeDeliveryService extends GenericService<TakeDelivery, String>{
	
	Delivery selectByTakeDeliveryPrimaryKey(String serialNum);

	Page<Delivery> selectByPage(Delivery takeDelivery);

	void insertTakeDelivery(TakeDeliveryParams takeDeliveryParams,
			String currenLoginName);
	
		

}
