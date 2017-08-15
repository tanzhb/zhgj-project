package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.DemandPlanMateriel;

/**
 * 
 * @ClassName ContractService
 * @Description  采购预测 接口
 * @author czw
 */
public interface PurchaseForecastService extends GenericService<DemandPlanMateriel, String> {
	
	/**
	 * 添加用户合同
	 * @param contractVO
	 */
   /* public void insertContract(ContractVO contractVO);*/
    
    
    /**
     * 查询采购预测
     * @param userId（用户id）
     * @return
     */
    public List<DemandPlanMateriel> queryPurchaseForecastList(String userId);
    
    
    /**
     * 
     * @Description 批量删除 合同
     * @param ids
     * @return
     */
	public void deletePurchaseForecast(String ids);
	
	
	/**//**
	 * 根据id查询合同对象
	 * @param id
	 * @return
	 *//*
	public ContractVO selectConbtractById(String id);
	
	
	*//**
	 * 更新用户对象
	 * @param contractVO
	 *//*
	public void updateContract(ContractVO contractVO);*/
}
