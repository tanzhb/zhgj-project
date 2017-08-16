package com.congmai.zhgj.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.web.dao.PurchaseForecastMapper;
import com.congmai.zhgj.web.model.DemandPlanMateriel;
import com.congmai.zhgj.web.service.PurchaseForecastService;

/**
 * 
 * @ClassName ContractServiceImpl
 * @Description 采购预测Service实现类
 * @author czw
 */
@Service
public class PurchaseForecastServiceImpl extends GenericServiceImpl<DemandPlanMateriel, String> implements
PurchaseForecastService {

	//合同的dao
	@Resource
	private PurchaseForecastMapper purchaseForecastMapper;

	@Override
	public GenericDao<DemandPlanMateriel, String> getDao() {
		return purchaseForecastMapper;
	}

	
	/**
	 * 添加用户合同
	 * @param contractVO
	 */
 /*@Override
	public void insertContract(ContractVO contractVO) {
		// TODO Auto-generated method stub
	 contractMapper.insertContract(contractVO);
	}*/
	 
	 /**
	  * 查询采购预测
	  * @param userId（用户id）
	  * @return
	  */
	public List<DemandPlanMateriel> queryPurchaseForecastList(String userId) {
		// TODO Auto-generated method stub
		
		return purchaseForecastMapper.queryPurchaseForecastList(userId);
	}

	
	/**
     * 
     * @Description 批量删除 采购预测
     * @param ids
     * @return
     */
	@Override
	public void deletePurchaseForecast(String ids) {
		List<String> idList = ApplicationUtils.getIdList(ids);
		purchaseForecastMapper.deletePurchaseForecast(idList);
	}
	

	/**
	 * 根据id查询合同对象
	 * @param id
	 * @return
	 */
	/*@Override
	public ContractVO selectConbtractById(String id) {
		// TODO Auto-generated method stub
		
		return contractMapper.selectContractById(id);
	}*/

	
	/**
	 * 更新用户对象
	 * @param contractVO
	 */
	/*@Override
	public void updateContract(ContractVO contractVO) {
		// TODO Auto-generated method stub
		contractMapper.updateContract(contractVO);
	}*/
}