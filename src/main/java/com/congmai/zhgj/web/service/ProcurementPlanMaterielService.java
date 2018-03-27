package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.DemandMateriel;
import com.congmai.zhgj.web.model.DemandMaterielExample;
import com.congmai.zhgj.web.model.ProcurementPlanMateriel;
import com.congmai.zhgj.web.model.ProcurementPlanMaterielExample;
import com.congmai.zhgj.web.model.DemandMaterielExample;

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
	
	void betchInsertDemandMateriel(List<DemandMateriel> demandMateriels);//保存需求物料
	
	public void deleteDemandMateriels(String procurementPlanSerial);//删除需求物料通过采购计划流水
	
	public void deleteAllProcurementPlanMateriels(String procurementPlanSerial);//删除采购清单物料通过采购计划流水
	
	  List<DemandMateriel> selecDemandtList(DemandMaterielExample m);
	  public void updateProcurementPlanMateriel(ProcurementPlanMateriel record);//更新采购计划物料
	  
	  public void deleteDemandMateriel(String ids);//删除需求物料通过需求物料流水
	  
	  public void updateDemandMateriel(DemandMateriel demandMateriel);//删除采购清单物料通过采购计划流水
	  
	  public void updateDemandMateriels(String  ids);//更新需求物料状态


}
