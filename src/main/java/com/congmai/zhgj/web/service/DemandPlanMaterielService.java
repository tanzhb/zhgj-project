package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.feature.orm.mybatis.Page;
import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.CompanyQualification;
import com.congmai.zhgj.web.model.DeliveryMateriel;
import com.congmai.zhgj.web.model.DemandPlan;
import com.congmai.zhgj.web.model.DemandPlanMateriel;
import com.congmai.zhgj.web.model.SupplyMateriel;

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
	
	String selectSupplyName(String materielSerial);

	/**
	 * 
	 * @Description (根据条件搜索需求计划物料)
	 * @param search
	 */
	public List<DemandPlanMateriel> searchDemandPlanMateriels(DemandPlan search);

	public List<DemandPlanMateriel> selectListByIds(String ids);
	
	void insertAllDemandPlanMateriel(List<DemandPlanMateriel> insertList,String userId);
	
}
