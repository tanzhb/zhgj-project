package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.Materiel;
import com.congmai.zhgj.web.model.BuyMateriel;
import com.congmai.zhgj.web.model.BuyMaterielExample;

/**
 * 
 * @ClassName BuyMaterielService
 * @Description 采购商物料Service
 * @author qfzhao
 * @Date 2017年7月28日 下午3:04:17
 * @version 1.0.0
 */
public interface BuyMaterielService extends GenericService<BuyMateriel, String> {
	/**
     * 查询多个对象
     *
     * @return 对象集合
     */
    List<BuyMateriel> selectList(BuyMaterielExample m);

	public void deleteBuyMateriels(String ids);

	void betchInsertBuyMateriels(List<BuyMateriel> buyMateriels);

	List<BuyMateriel> chooseMateriel(String ids);


}
