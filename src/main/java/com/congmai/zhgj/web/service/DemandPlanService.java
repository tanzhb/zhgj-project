package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.feature.orm.mybatis.Page;
import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.DemandPlan;
import com.congmai.zhgj.web.model.DemandPlanMateriel;
import com.congmai.zhgj.web.model.Materiel;

public interface DemandPlanService extends GenericService<DemandPlan, String>{
	
		/**
		 * @Description (分页查询需求计划)
		 * @param demandPlan
		 * @param start
		 * @param limit
		 * @return
		 */
		public Page<DemandPlan> getListByCondition(DemandPlan demandPlan,int start,int limit);

		/**
		 * @Description (选择物料)
		 * @param ids
		 * @return
		 */
		public List<Materiel> chooseMateriel(String ids);

		/**
		 * @Description (批量删除需求计划)
		 * @param serialNumArray
		 */
		public void deleteBatch(List<String> serialNumArray);

		/**
		 * @Description (保存需求计划信息)
		 * @param demandPlan
		 * @param demandPlanMateriels
		 */
		public void insertDemandPlanInfo(DemandPlan demandPlan,List<DemandPlanMateriel> demandPlanMateriels);
}
