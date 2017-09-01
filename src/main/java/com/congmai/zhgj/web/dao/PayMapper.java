package com.congmai.zhgj.web.dao;

import java.util.List;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.web.model.ClauseSettlement;
import com.congmai.zhgj.web.model.ClauseSettlementDetail;
import com.congmai.zhgj.web.model.ContractVO;
import com.congmai.zhgj.web.model.PaymentPlan;
import com.congmai.zhgj.web.model.PaymentRecord;

/**
 * 
 * @ClassName 合同Dao接口
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author czw
 */
public interface PayMapper extends GenericDao<PaymentRecord, String> {
    
	public List<ClauseSettlementDetail> selectClauseSettlementDetailList(String orderId);
			
	//添加合同
	public void insertPaymentRecord(PaymentRecord record);
	
	//添加付款计划
	public void insertPaymentPlan(PaymentPlan record);
	
	
	public ClauseSettlement selectClauseDetail(String serialNum);
	
	
	public List<PaymentRecord> selectAllPaymentRecordList(String userId);
	
	
	//删除合同
    public void delPaymentRecord(List<String> ids);
    
    public PaymentRecord selectPayById(String serialNum);

    //查询合同列表
    /*List<ContractVO> queryContractList(String userId);
    
    //查询合同对象
  
    
    
    //更新合同
    public void updateContract(ContractVO record);*/
}