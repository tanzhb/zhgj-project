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
import com.congmai.zhgj.web.dao.DeliveryMaterielMapper;
import com.congmai.zhgj.web.dao.DemandPlanMapper;
import com.congmai.zhgj.web.dao.DemandPlanMaterielMapper;
import com.congmai.zhgj.web.dao.MaterielMapper;
import com.congmai.zhgj.web.dao.SupplyMaterielMapper;
import com.congmai.zhgj.web.model.Delivery;
import com.congmai.zhgj.web.model.DeliveryMateriel;
import com.congmai.zhgj.web.model.DeliveryMaterielExample;
import com.congmai.zhgj.web.model.DemandPlan;
import com.congmai.zhgj.web.model.DemandPlanExample;
import com.congmai.zhgj.web.model.DemandPlanMateriel;
import com.congmai.zhgj.web.model.DemandPlanMaterielExample;
import com.congmai.zhgj.web.model.Materiel;
import com.congmai.zhgj.web.model.MaterielExample;
import com.congmai.zhgj.web.model.StockInOutRecord;
import com.congmai.zhgj.web.model.StockInOutRecordExample;
import com.congmai.zhgj.web.model.StockInOutRecordSelectExample;
import com.congmai.zhgj.web.model.StockInOutRecordSelectExample.Criteria;
import com.congmai.zhgj.web.model.SupplyMateriel;
import com.congmai.zhgj.web.model.SupplyMaterielExample;
import com.congmai.zhgj.web.service.DeliveryMaterielService;
import com.congmai.zhgj.web.service.DemandPlanService;
import com.sun.org.apache.regexp.internal.recompile;

@Service
public class DeliveryMaterielServiceImpl extends GenericServiceImpl<DeliveryMateriel,String>
		implements DeliveryMaterielService {

	@Resource
	private DeliveryMaterielMapper deliveryMaterielMapper;
	
	@Override
	public GenericDao<DeliveryMateriel, String> getDao() {
		
		return this.deliveryMaterielMapper;
	}

	@Override
	public List<DeliveryMateriel> selectByExample(
			DeliveryMaterielExample example) {
		
		return deliveryMaterielMapper.selectByExample(example);
		//return deliveryMaterielMapper.selectListByExampleForStockIn(example);
	}
	
	@Override
	public List<DeliveryMateriel> selectByExampleForStockIn(
			DeliveryMaterielExample example) {
		
		return deliveryMaterielMapper.selectByExampleForStockIn(example);
		//return deliveryMaterielMapper.selectListByExampleForStockIn(example);
	}

	@Override
	public Page<DeliveryMateriel> selectListByExample(StockInOutRecord record,String type) {
		StockInOutRecordSelectExample example = new StockInOutRecordSelectExample();
		example.setPageIndex(0);
		example.setPageSize(-1);
		Page<DeliveryMateriel> page = new Page<DeliveryMateriel>();
		Criteria c = example.createCriteria();
		c.andDelFlgEqualTo("0");
		if("in".equals(type)){
			c.andDeliverSerialEqualTo("");
			page.setResult(deliveryMaterielMapper.selectListByExampleForStockIn(example));
			page.setTotalCount(deliveryMaterielMapper.countListByExampleForStockIn(example));
		}else{
			c.andTakeDeliverSerialEqualTo("");
			page.setResult(deliveryMaterielMapper.selectListByExample(example));
			page.setTotalCount(deliveryMaterielMapper.countListByExample(example));
		}
		
		
		
		return page;
	}
@Override
	public int updateDeliveryMateriel(DeliveryMateriel record) {
		deliveryMaterielMapper.updateDeliveryMateriel(record);
		return 1;
	}

	
}
