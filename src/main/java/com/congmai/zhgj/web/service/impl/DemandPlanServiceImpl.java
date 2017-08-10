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
import com.congmai.zhgj.web.service.DemandPlanService;

@Service
public class DemandPlanServiceImpl extends GenericServiceImpl<DemandPlan,String>
		implements DemandPlanService {

	@Resource
	private DemandPlanMapper demandPlanMapper;
	
	@Resource
	private MaterielMapper materielMapper;
	
	@Resource
	private DemandPlanMaterielMapper demandPlanMaterielMapper;
	
	@Override
	public GenericDao<DemandPlan, String> getDao() {
		return this.demandPlanMapper;
	}

	@Override
	public Page<DemandPlan> getListByCondition(DemandPlan demandPlan,int start,int limit) {
		DemandPlanExample example = new DemandPlanExample();
		example.setOrderByClause("updateTime desc");
		example.setStart(start);
		example.setLimit(limit);
		List<DemandPlan> list = demandPlanMapper.selectByExample(example);
		if(!CollectionUtils.isEmpty(list)){
			for(DemandPlan vo : list){
				DemandPlanMaterielExample example2 = new DemandPlanMaterielExample();
				example2.createCriteria().andDemandPlanSerialEqualTo(vo.getSerialNum());
				List<DemandPlanMateriel> mList = demandPlanMaterielMapper.selectByExample(example2);
				vo.setMateriels(mList);
			}
		}
		int count = demandPlanMapper.countByExample(example);
		Page<DemandPlan> page = new Page<DemandPlan>(start, limit);
		page.setResult(list);
		page.setTotalCount(count);
		return page;
	}

	@Override
	public List<Materiel> chooseMateriel(String ids) {
		
		if(StringUtils.isNotEmpty(ids)){
			List<String> idList = Arrays.asList(ids.split(","));
			MaterielExample example = new MaterielExample();
			example.createCriteria().andSerialNumIn(idList).andDelFlgEqualTo("0");
			return materielMapper.selectByExample(example);
		}
		return null;
	}

}
