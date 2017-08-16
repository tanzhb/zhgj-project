package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.SupplyMateriel;
import com.congmai.zhgj.web.model.SupplyMaterielExample;

/**
 * 
 * @ClassName SupplyMaterielService
 * @Description 供应商物料Service
 * @author qfzhao
 * @Date 2017年7月28日 下午3:04:17
 * @version 1.0.0
 */
public interface SupplyMaterielService extends GenericService<SupplyMateriel, String> {
	/**
     * 查询多个对象
     *
     * @return 对象集合
     */
    List<SupplyMateriel> selectList(SupplyMaterielExample m);

	public void deleteSupplyMateriels(String ids);

	void betchInsertSupplyMateriels(List<SupplyMateriel> supplyMateriels);


}
