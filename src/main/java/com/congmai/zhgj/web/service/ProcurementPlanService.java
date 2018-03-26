package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.ProcurementPlan;
import com.congmai.zhgj.web.model.ProcurementPlanExample;

/**
 * 
 * @ClassName Service
 * @Description 采购计划Service
 * @author qfzhao
 * @Date 2017年7月28日 下午3:04:17
 * @version 1.0.0
 */
public  interface ProcurementPlanService extends GenericService<ProcurementPlan, String> {
	/**
     * 查询多个对象
     *
     * @return 对象集合
     */
    List<ProcurementPlan> selectList(ProcurementPlanExample m);

	public void deleteProcurementPlans(String ids);
	
	public void updateProcurementPlan(ProcurementPlan p);


}
