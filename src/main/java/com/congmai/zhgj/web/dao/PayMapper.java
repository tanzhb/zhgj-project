package com.congmai.zhgj.web.dao;

import java.util.List;
import java.util.Map;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.web.model.ClauseSettlement;
import com.congmai.zhgj.web.model.ClauseSettlementDetail;
import com.congmai.zhgj.web.model.ContractVO;
import com.congmai.zhgj.web.model.DeliveryVO;
import com.congmai.zhgj.web.model.MemoRecord;
import com.congmai.zhgj.web.model.PaymentFile;
import com.congmai.zhgj.web.model.PaymentPlan;
import com.congmai.zhgj.web.model.PaymentRecord;
import com.congmai.zhgj.web.model.TakeDeliveryVO;

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
    
    
    
    /**
     * 批量添加收付款附件
     * @param record
     */
    public void betchInsertPaymentFiles(PaymentFile Files);
    
    
    
    /**
     * 查询订单已付金额
     * @param record
     */
    public String selectPaiedMoney(String serialNum);
    
    
    
    /**
     * 查询订单已开票金额
     * @param record
     */
    public String selectBilledMoney(String serialNum);
    
    
    
    /**
     * 查询收付款对象附件集合
     * @param record
     */
    public List<PaymentFile> selectFileList(String serialNum);
    
    
    /**
     * 删除旧的附件
     * @param record
     */
    public void deleteFileOld(String paySerialNum);
    
    
    /**
     * 确认收款
     * @param map
     */
    public void confirmGatheringMoney(Map<String, Object> map);
    
    
    
    public List<ClauseSettlementDetail> selectClauseSettlementDetailList2(String serialNum);
    
    
    /**
	 * 当支付节点是“合同签订”时，查询日期
	 * @param serialNum
	 * @return
	 */
    public ContractVO selectDateTypeContract(String serialNum);
    
    /**
	 * 当支付节点是“提货前”时，查询日期
	 * @param serialNum
	 * @return
	 */
    public DeliveryVO selectDateTypeDelivery(String serialNum);
    
    /**
	 * 当支付节点是“到货后”时，查询日期
	 * @param serialNum
	 * @return
	 */
    public TakeDeliveryVO selectDateTypeTakeDelivery(String serialNum);
    
    public List<PaymentRecord> findPaymentRecord(Map<String,Object>map);//查找水单对应的应收付款账单
	 public List<MemoRecord> findMemoRecord(Map<String,Object>map);//查找应收付款账单对应的收付款水单
}