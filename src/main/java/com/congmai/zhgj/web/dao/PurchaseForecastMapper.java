package com.congmai.zhgj.web.dao;

import java.util.List;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.web.model.DemandPlanMateriel;

/**
 * 
 * @ClassName 采购预测Dao接口
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author czw
 */
public interface PurchaseForecastMapper extends GenericDao<DemandPlanMateriel, String> {
    
	/*//添加合同
    int insertContract(ContractVO record);*/

    //查询采购预测列表
    List<DemandPlanMateriel> queryPurchaseForecastList(String userId);
    
    //删除采购预测
    public void deletePurchaseForecast(List<String> ids);
    
    /* //查询合同对象
    public ContractVO selectContractById(String id);
    
    //更新合同
    public void updateContract(ContractVO record);*/
}