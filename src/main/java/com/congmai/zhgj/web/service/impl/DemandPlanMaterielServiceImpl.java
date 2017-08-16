package com.congmai.zhgj.web.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.core.util.DateUtil;
import com.congmai.zhgj.web.dao.DemandPlanMaterielMapper;
import com.congmai.zhgj.web.model.DemandPlanMateriel;
import com.congmai.zhgj.web.model.DemandPlanMaterielExample;
import com.congmai.zhgj.web.service.DemandPlanMaterielService;

@Service
public class DemandPlanMaterielServiceImpl extends GenericServiceImpl<DemandPlanMateriel,String>
		implements DemandPlanMaterielService {


	
	@Resource
	private DemandPlanMaterielMapper demandPlanMaterielMapper;

	@Override
	public GenericDao<DemandPlanMateriel, String> getDao() {
		
		return this.demandPlanMaterielMapper;
	}

	@Override
	public void deleteBatch(List<String> serialNumArray) {
		DemandPlanMateriel demandPlanMateriel = new DemandPlanMateriel();
		demandPlanMateriel.setDelFlg("1");
		DemandPlanMaterielExample example = new DemandPlanMaterielExample();
		example.createCriteria().andSerialNumIn(serialNumArray);
		demandPlanMaterielMapper.updateByExampleSelective(demandPlanMateriel, example);
	}

	@Override
	public List<DemandPlanMateriel> selectListByDemandPlanSerial(
			String serialNum) {
		DemandPlanMaterielExample example = new DemandPlanMaterielExample();
		example.createCriteria().andDemandPlanSerialEqualTo(serialNum).andDelFlgEqualTo("0");
		example.setOrderByClause("createTime desc");
		return this.listHandle(demandPlanMaterielMapper.selectByExample(example));
	}
	
	protected List<DemandPlanMateriel> listHandle(List<DemandPlanMateriel> list){
		if(CollectionUtils.isNotEmpty(list)){
			for(DemandPlanMateriel vo : list){
				int remainTime = 0;
				try {
					remainTime = DateUtil.daysBetween(new Date(), vo.getDeliveryDate());
				} catch (Exception e) {
					System.out.println("距离交付日期出错"+e.getMessage()+e.getCause());
				}
				vo.setRemainTime(String.valueOf(remainTime<0?0:remainTime));
			}
		}
		return list;
	}
	


}
