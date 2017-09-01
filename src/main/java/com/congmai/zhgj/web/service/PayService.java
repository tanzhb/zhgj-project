package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.ClauseSettlement;
import com.congmai.zhgj.web.model.ClauseSettlementDetail;
import com.congmai.zhgj.web.model.PaymentPlan;
import com.congmai.zhgj.web.model.PaymentRecord;

/**
 * 
 * @ClassName ContractService
 * @Description  合同 业务 接口
 * @author czw
 */
public interface PayService extends GenericService<PaymentRecord, String> {
	
    /**
     * 查询用户合同
     * @param userId（用户id）
     * @return
     */
   /* public List<ContractVO> queryContractList(String userId);*/
    
    
    /**
     * 
     * @Description 批量删除 合同
     * @param ids
     * @return
     */
	public void delPaymentRecord(String ids);
	
	
	/**
	 * 根据id查询合同对象
	 * @param id
	 * @return
	 */
	public PaymentRecord selectPayById(String serialNum);
	
	
	/**
	 * 更新用户对象
	 * @param contractVO
	 */
	/*public void updateContract(ContractVO contractVO);*/
	
	
	public List<ClauseSettlementDetail> selectClauseSettlementDetailList(String orderId);
	
	/**
	 * 添加应付款
	 * @param contractVO
	 */
    public void insertPaymentRecord(PaymentRecord record);
    
    
    /**
	 * 添加应付款
	 * @param contractVO
	 */
    public void insertPaymentPlan(PaymentPlan record);
    
    
    public ClauseSettlement selectClauseDetail(String serialNum);
    
    
    
    public List<PaymentRecord> selectAllPaymentRecordList(String userId);
}
