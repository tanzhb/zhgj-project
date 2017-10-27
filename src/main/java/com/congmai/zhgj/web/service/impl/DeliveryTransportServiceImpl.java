package com.congmai.zhgj.web.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.congmai.zhgj.core.feature.orm.mybatis.Page;
import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.core.util.DateUtil;
import com.congmai.zhgj.web.dao.DeliveryTransportMapper;
import com.congmai.zhgj.web.dao.DemandPlanMapper;
import com.congmai.zhgj.web.dao.DemandPlanMaterielMapper;
import com.congmai.zhgj.web.dao.MaterielMapper;
import com.congmai.zhgj.web.dao.SupplyMaterielMapper;
import com.congmai.zhgj.web.model.Delivery;
import com.congmai.zhgj.web.model.DeliveryTransport;
import com.congmai.zhgj.web.model.DeliveryTransportExample;
import com.congmai.zhgj.web.model.DemandPlan;
import com.congmai.zhgj.web.model.DemandPlanExample;
import com.congmai.zhgj.web.model.DemandPlanMateriel;
import com.congmai.zhgj.web.model.DemandPlanMaterielExample;
import com.congmai.zhgj.web.model.Materiel;
import com.congmai.zhgj.web.model.MaterielExample;
import com.congmai.zhgj.web.model.SupplyMateriel;
import com.congmai.zhgj.web.model.SupplyMaterielExample;
import com.congmai.zhgj.web.service.DeliveryTransportService;
import com.congmai.zhgj.web.service.DemandPlanService;

@Service
public class DeliveryTransportServiceImpl extends GenericServiceImpl<DeliveryTransport,String>
		implements DeliveryTransportService {

	@Resource
	private DeliveryTransportMapper deliveryTransportMapper;
	
	@Override
	public GenericDao<DeliveryTransport, String> getDao() {
		
		return this.deliveryTransportMapper;
	}

	@Override
	public DeliveryTransport getDeliveryTransport(String  deliverSerial) {
		// TODO Auto-generated method stub
		DeliveryTransportExample de=new DeliveryTransportExample();
		com.congmai.zhgj.web.model.DeliveryTransportExample.Criteria  c=de.createCriteria();
		c.andDelFlgEqualTo("0");
		c.andDeliverSerialEqualTo(deliverSerial);
		List<DeliveryTransport>ds =deliveryTransportMapper.selectByExample(de);
		DeliveryTransport d=null;
		if(CollectionUtils.isNotEmpty(ds)){
			d=ds.get(0);
		}
		
		return  d;
	}

	


}
