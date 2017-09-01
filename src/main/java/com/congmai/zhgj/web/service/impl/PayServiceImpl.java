package com.congmai.zhgj.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.web.dao.PayMapper;
import com.congmai.zhgj.web.model.ClauseSettlement;
import com.congmai.zhgj.web.model.ClauseSettlementDetail;
import com.congmai.zhgj.web.model.PaymentPlan;
import com.congmai.zhgj.web.model.PaymentRecord;
import com.congmai.zhgj.web.service.PayService;

/**
 * 
 * @ClassName ContractServiceImpl
 * @Description 合同Service实现类
 * @author czw
 */
@Service
public class PayServiceImpl extends GenericServiceImpl<PaymentRecord, String> implements
		PayService {

	//合同的dao
	@Resource
	private PayMapper payMapper;

	@Override
	public GenericDao<PaymentRecord,String> getDao() {
		return payMapper;
	}

	@Override
	public List<ClauseSettlementDetail> selectClauseSettlementDetailList(
			String orderId) {
		// TODO Auto-generated method stub
		
		return payMapper.selectClauseSettlementDetailList(orderId);
	}

	
	/**
	 * 添加应付款
	 * @param contractVO
	 */
 @Override
	public void insertPaymentRecord(PaymentRecord record) {
		// TODO Auto-generated method stub
	 payMapper.insertPaymentRecord(record);
	}

 
	@Override
	public void insertPaymentPlan(PaymentPlan record) {
		// TODO Auto-generated method stub
		 payMapper.insertPaymentPlan(record);
	}

	@Override
	public ClauseSettlement selectClauseDetail(String serialNum) {
		// TODO Auto-generated method stub
		
		return payMapper.selectClauseDetail(serialNum);
	}

	@Override
	public List<PaymentRecord> selectAllPaymentRecordList(String userId) {
		// TODO Auto-generated method stub
		
		return payMapper.selectAllPaymentRecordList(userId);
	}
 
	 /**
	  * 查询用户合同
	  * @param userId（用户id）
	  * @return
	  */
	/*public List<ContractVO> queryContractList(String userId) {
		// TODO Auto-generated method stub
		
		return contractMapper.queryContractList(userId);
	}*/

	
	/**
     * 
     * @Description 批量删除 合同
     * @param ids
     * @return
     */
	@Override
	public void delPaymentRecord(String ids) {
		List<String> idList = ApplicationUtils.getIdList(ids);
		payMapper.delPaymentRecord(idList);
	}
	

	/**
	 * 根据id查询合同对象
	 * @param id
	 * @return
	 */
	@Override
	public PaymentRecord selectPayById(String serialNum) {
		// TODO Auto-generated method stub
		
		return payMapper.selectPayById(serialNum);
	}

	
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