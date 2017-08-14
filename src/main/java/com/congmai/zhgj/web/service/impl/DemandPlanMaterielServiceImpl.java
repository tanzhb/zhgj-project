package com.congmai.zhgj.web.service.impl;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.congmai.zhgj.core.feature.orm.mybatis.Page;
import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.web.dao.DemandPlanMapper;
import com.congmai.zhgj.web.dao.DemandPlanMaterielMapper;
import com.congmai.zhgj.web.dao.MaterielMapper;
import com.congmai.zhgj.web.model.DemandPlan;
import com.congmai.zhgj.web.model.DemandPlanExample;
import com.congmai.zhgj.web.model.DemandPlanMateriel;
import com.congmai.zhgj.web.model.DemandPlanMaterielExample;
import com.congmai.zhgj.web.model.Materiel;
import com.congmai.zhgj.web.model.MaterielExample;
import com.congmai.zhgj.web.service.DemandPlanMaterielService;
import com.congmai.zhgj.web.service.DemandPlanService;

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
		return demandPlanMaterielMapper.selectByExample(example);
	}
	


}
