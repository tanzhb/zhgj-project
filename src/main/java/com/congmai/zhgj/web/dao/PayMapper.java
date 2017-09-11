package com.congmai.zhgj.web.dao;

import java.util.List;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.web.model.ClauseSettlement;
import com.congmai.zhgj.web.model.ClauseSettlementDetail;
import com.congmai.zhgj.web.model.ContractVO;
import com.congmai.zhgj.web.model.PaymentFile;
import com.congmai.zhgj.web.model.PaymentPlan;
import com.congmai.zhgj.web.model.PaymentRecord;

/**
 * 
 * @ClassName 付款Dao接口
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author czw
 */
public interface PayMapper extends GenericDao<PaymentRecord, String> {
    
	/**
	 * 查询采购订单的结算条款列表
	 * @return
	 */
	public List<ClauseSettlementDetail> selectClauseSettlementDetailList(String orderId);
			
	/**
	 * 添加付款记录
	 * @param record
	 */
	public void insertPaymentRecord(PaymentRecord record);
	
	/**
	 * 添加付款计划
	 * @param record
	 */
	public void insertPaymentPlan(PaymentPlan record);
	
	/**
	 * 查询结算条款详情
	 * @param serialNum
	 * @return
	 */
	public ClauseSettlement selectClauseDetail(String serialNum);
	
	
	/**
	 * 查询付款记录列表
	 * @param userId
	 * @return
	 */
	public List<PaymentRecord> selectAllPaymentRecordList(String userId);
	
	
	/**
	 * 查询收款记录列表
	 * @param userId
	 * @return
	 */
	public List<PaymentRecord> findAllGatheringMoneyRecord(String userId);
	
	
	/**
	 * 删除付款
	 * @param ids
	 */
    public void delPaymentRecord(List<String> ids);
    
    
    /**
     * 查询付款详情
     * @param serialNum
     * @return
     */
    public PaymentRecord selectPayById(String serialNum);

    
    /**
     * 更新付款计划
     * @param record
     */
    public void updatePaymentPlan(PaymentPlan record);
    
    
    /**
     * 更新付款
     * @param record
     */
    public void updatePaymentRecord(PaymentRecord record);
    
    
    
    
    public void betchInsertPaymentFiles(PaymentFile Files);
    
    
    
    
    public String selectPaiedMoney(String serialNum);
    
    
    
    
    public String selectBilledMoney(String serialNum);
    
    
    
    
    public List<PaymentFile> selectFileList(String serialNum);
    
    
    
    public void deleteFileOld(String paySerialNum);
}