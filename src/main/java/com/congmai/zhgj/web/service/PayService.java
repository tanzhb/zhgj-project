package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.ClauseSettlement;
import com.congmai.zhgj.web.model.ClauseSettlementDetail;
import com.congmai.zhgj.web.model.MaterielFile;
import com.congmai.zhgj.web.model.PaymentFile;
import com.congmai.zhgj.web.model.PaymentPlan;
import com.congmai.zhgj.web.model.PaymentRecord;
import com.congmai.zhgj.web.model.Vacation;

/**
 * 
 * @ClassName PayService
 * @Description  付款 业务 接口
 * @author czw
 */
public interface PayService extends GenericService<PaymentRecord, String> {
	
	/**
	 * 
	 * @Description 启动应付款流程
	 * @param paymentRecord
	 * @return
	 * @throws Exception
	 */
	public int apply(PaymentRecord paymentRecord) throws Exception;
    
    /**
     * 
     * @Description 批量删除 付款
     * @param ids
     * @return
     */
	public void delPaymentRecord(String ids);
	
	
	/**
	 * 根据id查询付款对象
	 * @param id
	 * @return
	 */
	public PaymentRecord selectPayById(String serialNum);
	
	

	/**
	 * 查询采购合同的结算条款列表
	 * @param orderId （采购订单id）
	 * @return
	 */
	public List<ClauseSettlementDetail> selectClauseSettlementDetailList(String orderId);
	
	/**
	 * 添加应付款
	 * @param contractVO
	 */
    public void insertPaymentRecord(PaymentRecord record);
    
    
    /**
	 * 添加应付款计划
	 * @param contractVO
	 */
    public void insertPaymentPlan(PaymentPlan record);
    
    
    /**
     * 查询结算条款详情
     * @param serialNum
     * @return
     */
    public ClauseSettlement selectClauseDetail(String serialNum);
    
    
    /**
     * 查询付款列表
     * @param userId
     * @return
     */
    public List<PaymentRecord> selectAllPaymentRecordList(String userId);
    
    
    /**
     * 查询收款列表
     * @param userId
     * @return
     */
    public List<PaymentRecord> findAllGatheringMoneyRecord(String userId);
    
    
    /**
	 * 更新应付款
	 * @param contractVO
	 */
    public void updatePaymentPlan(PaymentPlan record);
    
    
    /**
	 * 更新应付款
	 * @param contractVO
	 */
    public void updatePaymentRecord(PaymentRecord record);
    
    
    
    public void betchInsertPaymentFiles(List<PaymentFile> paymentFiles);
    
    
    
    public String selectPaiedMoney(String serialNum);
    
    
    
    public String selectBilledMoney(String serialNum);
    
    
    
    public List<PaymentFile> selectFileList(String serialNum);
    
    
    
    public void deleteFileOld(String paySerialNum);
}
