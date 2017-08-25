package com.congmai.zhgj.web.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.congmai.zhgj.core.feature.orm.mybatis.Page;
import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.web.dao.Delivery2Mapper;
import com.congmai.zhgj.web.dao.DeliveryMaterielMapper;
import com.congmai.zhgj.web.dao.DeliveryTransportMapper;
import com.congmai.zhgj.web.dao.TakeDeliveryMapper;
import com.congmai.zhgj.web.model.Delivery;
import com.congmai.zhgj.web.model.DeliveryExample;
import com.congmai.zhgj.web.model.DeliveryMateriel;
import com.congmai.zhgj.web.model.DeliveryMaterielExample;
import com.congmai.zhgj.web.model.DeliverySelectExample;
import com.congmai.zhgj.web.model.DeliveryTransportExample;
import com.congmai.zhgj.web.model.TakeDelivery;
import com.congmai.zhgj.web.model.TakeDeliveryExample;
import com.congmai.zhgj.web.model.TakeDeliveryParams;
import com.congmai.zhgj.web.model.TakeDeliverySelectExample;
import com.congmai.zhgj.web.service.TakeDeliveryService;

@Service
public class TakeDeliveryServiceImpl extends GenericServiceImpl<TakeDelivery,String>
		implements TakeDeliveryService {

	@Resource
	private TakeDeliveryMapper takeDeliveryMapper;
	
	@Resource
	private Delivery2Mapper delivery2Mapper;
	
	@Resource
	private DeliveryTransportMapper deliveryTransportMapper;
	
	@Resource
	private DeliveryMaterielMapper deliveryMaterielMapper;
	
	@Override
	public GenericDao<TakeDelivery, String> getDao() {
	
		return this.takeDeliveryMapper;
	}

	@Override
	public Page<Delivery> selectByPage(Delivery takeDelivery) {
		TakeDeliverySelectExample example = new TakeDeliverySelectExample();
		example.setPageIndex(0);
		example.setPageSize(-1);
		example.createCriteria().andDelFlgEqualTo("0").andDeliverStatusNotEqualTo("0");
		Page<Delivery> page = new Page<Delivery>();
		page.setResult(takeDeliveryMapper.selectListByExample(example));
		page.setTotalCount(takeDeliveryMapper.countListByExample(example));
		
		return page;
	}

	@Override
	public void insertTakeDelivery(TakeDeliveryParams takeDeliveryParams,
			String currenLoginName) {
		takeDeliveryParams = getTakeDeliveryData(takeDeliveryParams,currenLoginName);
		delivery2Mapper.insert(takeDeliveryParams.getDelivery());
		deliveryTransportMapper.insert(takeDeliveryParams.getDeliveryTransport());
		takeDeliveryMapper.insert(takeDeliveryParams.getTakeDelivery());
		for(DeliveryMateriel materiel : takeDeliveryParams.getDeliveryMateriels()){
			deliveryMaterielMapper.insert(materiel);
		}
	}
	
	private TakeDeliveryParams getTakeDeliveryData(TakeDeliveryParams takeDeliveryParams,String currenLoginName){
		String deliverySerial = ApplicationUtils.random32UUID();
		Date now = new Date();
		if(StringUtils.isEmpty(takeDeliveryParams.getDelivery().getSerialNum())){
			takeDeliveryParams.getDelivery().setSerialNum(deliverySerial);
			takeDeliveryParams.getDelivery().setCreator(currenLoginName);
			takeDeliveryParams.getDelivery().setCreateTime(now);
		}
		takeDeliveryParams.getDelivery().setUpdater(currenLoginName);
		takeDeliveryParams.getDelivery().setUpdateTime(now);
		takeDeliveryParams.getDelivery().setDelFlg("0");
		takeDeliveryParams.getDelivery().setStatus("2"); //已发货
		
		if(StringUtils.isEmpty(takeDeliveryParams.getDeliveryTransport().getSerialNum())){
			takeDeliveryParams.getDeliveryTransport().setSerialNum(ApplicationUtils.random32UUID());
			takeDeliveryParams.getDeliveryTransport().setDeliverSerial(deliverySerial);
			takeDeliveryParams.getDeliveryTransport().setCreator(currenLoginName);
			takeDeliveryParams.getDeliveryTransport().setCreateTime(now);
		}
		takeDeliveryParams.getDeliveryTransport().setUpdater(currenLoginName);
		takeDeliveryParams.getDeliveryTransport().setUpdateTime(now);
		takeDeliveryParams.getDeliveryTransport().setDelFlg("0");
		
		if(StringUtils.isEmpty(takeDeliveryParams.getTakeDelivery().getSerialNum())){
			takeDeliveryParams.getTakeDelivery().setSerialNum(ApplicationUtils.random32UUID());
			takeDeliveryParams.getTakeDelivery().setDeliverSerial(deliverySerial);
			takeDeliveryParams.getTakeDelivery().setCreator(currenLoginName);
			takeDeliveryParams.getTakeDelivery().setCreateTime(now);
		}
		takeDeliveryParams.getTakeDelivery().setUpdater(currenLoginName);
		takeDeliveryParams.getTakeDelivery().setUpdateTime(now);
		takeDeliveryParams.getTakeDelivery().setDelFlg("0");
		
		for(DeliveryMateriel materiel : takeDeliveryParams.getDeliveryMateriels()){
			materiel.setSerialNum(ApplicationUtils.random32UUID());
			materiel.setDeliverSerial(takeDeliveryParams.getDelivery().getSerialNum());
			materiel.setCreator(currenLoginName);
			materiel.setCreateTime(now);
			materiel.setUpdater(currenLoginName);
			materiel.setUpdateTime(now);
			materiel.setDelFlg("0");
		}
		return takeDeliveryParams;
	}
	@Override
	public Delivery selectByTakeDeliveryPrimaryKey(String serialNum) {
		
		return this.takeDeliveryMapper.selectByTakeDeliveryPrimaryKey(serialNum);
	}

	@Override
	public void deleteBatch(List<String> serialNumArray) {
		TakeDeliveryExample example = new TakeDeliveryExample();
		TakeDelivery record = new TakeDelivery();
		record.setDelFlg("1");
		example.createCriteria().andSerialNumIn(serialNumArray);
		takeDeliveryMapper.updateByExampleSelective(record, example);
	}

	@Override
	public void updateTakeDelivery(TakeDeliveryParams takeDeliveryParams,
			String currenLoginName) {
		takeDeliveryParams = getTakeDeliveryData(takeDeliveryParams,currenLoginName);
		
		DeliveryExample d_example = new DeliveryExample();
		d_example.createCriteria().andSerialNumEqualTo(takeDeliveryParams.getDelivery().getSerialNum());
		delivery2Mapper.updateByExampleSelective(takeDeliveryParams.getDelivery(),d_example);
		
		DeliveryTransportExample dt_example = new DeliveryTransportExample();
		dt_example.createCriteria().andSerialNumEqualTo(takeDeliveryParams.getDeliveryTransport().getSerialNum());
		deliveryTransportMapper.updateByExampleSelective(takeDeliveryParams.getDeliveryTransport(),dt_example);
		
		TakeDeliveryExample td_example = new TakeDeliveryExample();
		td_example.createCriteria().andSerialNumEqualTo(takeDeliveryParams.getTakeDelivery().getSerialNum());
		takeDeliveryMapper.updateByExampleSelective(takeDeliveryParams.getTakeDelivery(),td_example);
		
		//if(StringUtils.isEmpty(takeDeliveryParams.getDeliveryMateriels().get(0).getSerialNum())){
			DeliveryMateriel dm = new DeliveryMateriel();
			dm.setDelFlg("1");
			DeliveryMaterielExample dmExample = new DeliveryMaterielExample();
			dmExample.createCriteria().andDeliverSerialEqualTo(takeDeliveryParams.getDelivery().getSerialNum());
			deliveryMaterielMapper.updateByExampleSelective(dm,dmExample);
		//}
		
		for(DeliveryMateriel materiel : takeDeliveryParams.getDeliveryMateriels()){
			/*if(StringUtils.isNotEmpty(materiel.getSerialNum())){
				DeliveryMaterielExample dm_example = new DeliveryMaterielExample();
				dm_example.createCriteria().andSerialNumEqualTo(materiel.getSerialNum());
				deliveryMaterielMapper.updateByExampleSelective(materiel,dm_example);
			}else{*/
				deliveryMaterielMapper.insert(materiel);
			/*}*/
			
		}
	}
	

}
