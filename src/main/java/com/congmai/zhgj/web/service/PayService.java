package com.congmai.zhgj.web.service;

import java.util.List;
import java.util.Map;

import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.ClauseSettlement;
import com.congmai.zhgj.web.model.ClauseSettlementDetail;
import com.congmai.zhgj.web.model.ContractVO;
import com.congmai.zhgj.web.model.DeliveryVO;
import com.congmai.zhgj.web.model.MaterielFile;
import com.congmai.zhgj.web.model.MemoRecord;
import com.congmai.zhgj.web.model.PaymentFile;
import com.congmai.zhgj.web.model.PaymentPlan;
import com.congmai.zhgj.web.model.PaymentRecord;
import com.congmai.zhgj.web.model.TakeDeliveryVO;
import com.congmai.zhgj.web.model.Vacation;
import com.congmai.zhgj.web.model.VerificationRecord;

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
     * 
     * @Description 确认收款
     * @param ids
     * @return
     */
	public void confirmGatheringMoney(Map<String,Object> map);
	
	/**
	 * 根据id查询付款对象
	 * @param id
	 * @return
	 */
	public PaymentRecord selectPayById(String serialNum);
	
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

	/**
	 * 查询采购合同的结算条款列表
	 * @param orderId （采购订单id）
	 * @return
	 */
	public List<ClauseSettlementDetail> selectClauseSettlementDetailList(String orderId);
	
	
	
	/**
	 * 查询采购合同的结算条款列表
	 * @param orderId （采购订单id）
	 * @return
	 */
	public List<ClauseSettlementDetail> selectClauseSettlementDetailList2(String serialNum);
	
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
    
    
    /**
     * 批量添加收付款附件
     * @param paymentFiles
     */
    public void betchInsertPaymentFiles(List<PaymentFile> paymentFiles);
    
    
    /**
     * 查询订单已付金额
     * @param serialNum
     * @return
     */
    public String selectPaiedMoney(String serialNum);
    
    
    /**
     * 查询订单已开票金额
     * @param serialNum
     * @return
     */
    public String selectBilledMoney(String serialNum);
    
    
    /**
     * 查询收付款对象附件集合
     * @param serialNum
     * @return
     */
    public List<PaymentFile> selectFileList(String serialNum);
    
    
    /**
     * 删除旧的附件
     * @param serialNum
     * @return
     */
    public void deleteFileOld(String paySerialNum);

	public void updateOrderStatus(PaymentRecord paymentRecord);
	
	 /**
     * 查询收款水单列表
     * @param userId
     * @return
     */
    public List<MemoRecord>findReceiveMemoRecord(String userId);
    
    /**
     * 查询付款水单列表
     * @param userId
     * @return
     */
    public List<MemoRecord>findPayMemoRecord(String userId);
    
    /**
     * 查询收款/付款水单对应的核销记录(通过收款/付款水单流水)
     * @param userId
     * @return
     */
    public List< VerificationRecord>findVerificationRecord(String serialNum);
    
    /**
     * 
     * @Description 批量删除 收/付款水单
     * @param ids
     * @return
     */
	public void delMemoRecord(String ids);
	
	public void insertMemoRecord(MemoRecord ids);
	public void updateMemoRecord(MemoRecord ids);
	public MemoRecord selectMemoRecordById(String serialNum);
	 public List<PaymentRecord> findPaymentRecord(String comId,String type);//查找水单对应的应收付款账单
	 public List<MemoRecord> findMemoRecord(String comId,String type);//查找应收付款账单对应的收付款水单
	 public  Map<String,Object>  insertVerificateData(List<VerificationRecord>list,String currenLoginName,String serialNum);//保存核销记录  serialNum 水单流水
	 /**
	     * 查询应收款/付款单对应的核销记录(通过应收款/付款单流水)
	     * @param userId
	     * @return
	     */
	    public List< VerificationRecord>findVerificationRecordByPaymentRecordSerial(String serialNum);
	    
	    public List<PaymentRecord> findPaymentRecordList(String  orderSerial);//根据订单流水查找该订单已建的收付款信息
	    
	
}
