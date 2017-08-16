package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.DemandPlanMateriel;

public interface DemandPlanMaterielService extends GenericService<DemandPlanMateriel, String>{

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
