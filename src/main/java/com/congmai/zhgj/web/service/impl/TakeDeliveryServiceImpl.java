package com.congmai.zhgj.web.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.web.dao.TakeDeliveryMapper;
import com.congmai.zhgj.web.model.TakeDelivery;
import com.congmai.zhgj.web.service.TakeDeliveryService;

@Service
public class TakeDeliveryServiceImpl extends GenericServiceImpl<TakeDelivery,String>
		implements TakeDeliveryService {

	@Resource
	private TakeDeliveryMapper takeDeliveryMapper;
	
	@Override
	public GenericDao<TakeDelivery, String> getDao() {
	
		return this.takeDeliveryMapper;
	}
	

}
