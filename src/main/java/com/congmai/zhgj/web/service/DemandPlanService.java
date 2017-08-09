package com.congmai.zhgj.web.service;

import com.congmai.zhgj.core.feature.orm.mybatis.Page;
import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.DemandPlan;

public interface DemandPlanService extends GenericService<DemandPlan, String>{
	
		public Page<DemandPlan> getListByCondition(DemandPlan demandPlan,int start,int limit);
}
