package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.feature.orm.mybatis.Page;
import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.DemandPlan;
import com.congmai.zhgj.web.model.Materiel;

public interface DemandPlanService extends GenericService<DemandPlan, String>{
	
		public Page<DemandPlan> getListByCondition(DemandPlan demandPlan,int start,int limit);

		public List<Materiel> chooseMateriel(String ids);

		public void deleteBatch(List<String> serialNumArray);
}
