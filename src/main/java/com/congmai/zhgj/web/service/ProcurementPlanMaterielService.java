package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.ProcurementPlanMateriel;
import com.congmai.zhgj.web.model.ProcurementPlanMaterielExample;

/**
 * 
 * @ClassName MaterielService
 * @Description 采购计划物料Service
 * @author qfzhao
 * @Date 2017年7月28日 下午3:04:17
 * @version 1.0.0
 */
public interface ProcurementPlanMaterielService extends GenericService<ProcurementPlanMateriel, String> {
	/**
     * 查询多个对象
     *
     * @return 对象集合
     */
    List<ProcurementPlanMateriel> selectList(ProcurementPlanMaterielExample m);

	public void deleteProcurementPlanMateriels(String ids);

	void betchInsertProcurementPlanMateriel(List<ProcurementPlanMateriel> procurementPlanMateriel);


}
