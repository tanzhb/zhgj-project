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
import com.congmai.zhgj.core.util.UserUtil;
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
import com.congmai.zhgj.web.model.User;
import com.congmai.zhgj.web.model.StockInOutRecordSelectExample.Criteria;
import com.congmai.zhgj.web.model.SupplyMateriel;
import com.congmai.zhgj.web.model.SupplyMaterielExample;
import com.congmai.zhgj.web.service.DeliveryMaterielService;
import com.congmai.zhgj.web.service.DemandPlanService;
import com.congmai.zhgj.web.service.UserCompanyService;
import com.sun.org.apache.regexp.internal.recompile;

@Service
public class DeliveryMaterielServiceImpl extends GenericServiceImpl<DeliveryMateriel,String>
		implements DeliveryMaterielService {

	@Resource
	private DeliveryMaterielMapper deliveryMaterielMapper;
	@Resource
	private UserCompanyService userCompanyService;
	
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
	public Page<DeliveryMateriel> selectListByExample(StockInOutRecord record,String type,String  status) {
		StockInOutRecordSelectExample example = new StockInOutRecordSelectExample();
		example.setPageIndex(0);
		example.setPageSize(-1);
		
		String comId = null;
    	User user = UserUtil.getUserFromSession();
    	if(user!=null){
			comId = userCompanyService.getUserComId(String.valueOf(user.getUserId()));
		}
    	example.setComId(comId);
    	example.setOrderSerial(record.getOrderSerial());
    	if(!StringUtils.isEmpty(status)){
    		example.setStatus(status);
    	}else{
    		example.setStatus(null);
    	}
		Page<DeliveryMateriel> page = new Page<DeliveryMateriel>();
		Criteria c = example.createCriteria();
		c.andDelFlgEqualTo("0");
		
		if(!CollectionUtils.isEmpty(record.getIdList())){
			c.andSerialNumIn(record.getIdList());
		}
		if("in".equals(type)){
			
			c.andDeliverSerialEqualTo("");
			//c.andInOutFlagEqualTo("1");//入库
			Criteria c2 = example.or();
			if(StringUtils.isEmpty(record.getBuyComId())){
				c.andBuyComIdIsNull();
				c2.andBuyComIdIsNull();
			}else{
				c.andBuyComIdEqualTo(record.getBuyComId());
				c2.andBuyComIdEqualTo(record.getBuyComId());
			}
			c2.andDeliverSerialEqualTo(""); 
			//c2.andInOutFlagIsNull();//出库;
			c2.andDelFlgEqualTo("0");
			if(!CollectionUtils.isEmpty(record.getIdList())){
				c2.andSerialNumIn(record.getIdList());
			}
			page.setResult(deliveryMaterielMapper.selectListByExampleForStockIn(example));
			page.setTotalCount(deliveryMaterielMapper.countListByExampleForStockIn(example));
		}else{
			c.andTakeDeliverSerialEqualTo(""); 
//			c.andInOutFlagEqualTo("0");//出库
			Criteria c2 = example.or();
			if(StringUtils.isEmpty(record.getSupplyComId())){
				c.andSupplyComIdIsNull();
				c2.andSupplyComIdIsNull();
			}else{
				c.andSupplyComIdEqualTo(record.getSupplyComId());
				c2.andSupplyComIdEqualTo(record.getSupplyComId());
			}
			c2.andTakeDeliverSerialEqualTo(""); 
//			c2.andInOutFlagIsNull();//出库
			c2.andDelFlgEqualTo("0");
			if(!CollectionUtils.isEmpty(record.getIdList())){
				c2.andSerialNumIn(record.getIdList());
			}
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
