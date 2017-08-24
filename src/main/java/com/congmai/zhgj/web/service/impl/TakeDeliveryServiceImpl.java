package com.congmai.zhgj.web.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.congmai.zhgj.core.feature.orm.mybatis.Page;
import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.web.dao.Delivery2Mapper;
import com.congmai.zhgj.web.dao.TakeDeliveryMapper;
import com.congmai.zhgj.web.model.Delivery;
import com.congmai.zhgj.web.model.TakeDelivery;
import com.congmai.zhgj.web.model.TakeDeliveryParams;
import com.congmai.zhgj.web.service.TakeDeliveryService;

@Service
public class TakeDeliveryServiceImpl extends GenericServiceImpl<TakeDelivery,String>
		implements TakeDeliveryService {

	@Resource
	private TakeDeliveryMapper takeDeliveryMapper;
	
	@Resource
	private Delivery2Mapper deliveryMapper;
	
	@Override
	public GenericDao<TakeDelivery, String> getDao() {
	
		return this.takeDeliveryMapper;
	}

	@Override
	public Page<Delivery> selectByPage(Delivery takeDelivery) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertTakeDelivery(TakeDeliveryParams takeDeliveryParams,
			String currenLoginName) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Delivery selectByTakeDeliveryPrimaryKey(String serialNum) {
		
		return this.deliveryMapper.selectByTakeDeliveryPrimaryKey(serialNum);
	}
	

}
