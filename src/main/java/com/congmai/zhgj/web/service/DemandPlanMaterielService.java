package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.feature.orm.mybatis.Page;
import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.DemandPlan;
import com.congmai.zhgj.web.model.DemandPlanMateriel;

public interface DemandPlanMaterielService extends GenericService<DemandPlanMateriel, String>{
	
	/**
	 * @Description (分页查询需求计划物料信息)
	 * @param demandPlan
	 * @param start
	 * @param limit
	 * @return
	 */
	public Page<DemandPlanMateriel> getListByCondition(DemandPlanMateriel demandPlanMateriel,int start,int limit);

	/**
	 * @Description (批量删除)
	 * @param serialNumArray
	 */
	void deleteBatch(List<String> serialNumArray);

	/**
	 * @Description (根据需求计划编号查询物料信息)
	 * @param serialNum
	 * @return
	 */
	List<DemandPlanMateriel> selectListByDemandPlanSerial(String serialNum);
	
	
	String selectMaterielSerialByMaterielNum(String materielNum);
	
	
}
