package com.congmai.zhgj.web.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.log.annotation.OperationLog;
import com.congmai.zhgj.web.dao.OrderInfoMapper;
import com.congmai.zhgj.web.dao.PayMapper;
import com.congmai.zhgj.web.model.ClauseSettlement;
import com.congmai.zhgj.web.model.ClauseSettlementDetail;
import com.congmai.zhgj.web.model.MaterielFile;
import com.congmai.zhgj.web.model.MaterielFileExample;
import com.congmai.zhgj.web.model.OrderInfo;
import com.congmai.zhgj.web.model.PaymentFile;
import com.congmai.zhgj.web.model.PaymentPlan;
import com.congmai.zhgj.web.model.PaymentRecord;
import com.congmai.zhgj.web.model.Vacation;
import com.congmai.zhgj.web.model.MaterielFileExample.Criteria;
import com.congmai.zhgj.web.service.PayService;

/**
 * 
 * @ClassName PayServiceImpl
 * @Description 付款Service实现类
 * @author czw
 */
@Service
public class PayServiceImpl extends GenericServiceImpl<PaymentRecord, String> implements
		PayService {

	//付款的dao
	@Resource
	private PayMapper payMapper;
	@Resource
	private OrderInfoMapper orderInfoMapper;
	

	@Override
	public GenericDao<PaymentRecord,String> getDao() {
		return payMapper;
	}
	
	@Override
	public int apply(PaymentRecord paymentRecord) throws Exception {
		return payMapper.insertSelective(paymentRecord);
	}

	/**
	 * 查询采购合同的结算条款列表
	 * @param orderId （采购订单id）
	 * @return
	 */
	@Override
	public List<ClauseSettlementDetail> selectClauseSettlementDetailList(
			String orderId) {
		// TODO Auto-generated method stub
		
		return payMapper.selectClauseSettlementDetailList(orderId);
	}

	
	/**
	 * 查询采购合同的结算条款列表
	 * @param orderId （采购订单id）
	 * @return
	 */
	@Override
	public List<ClauseSettlementDetail> selectClauseSettlementDetailList2(
			String serialNum) {
		// TODO Auto-generated method stub
		return payMapper.selectClauseSettlementDetailList2(serialNum);
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

 
     /**
	 * 添加应付款计划
	 * @param contractVO
	 */
	@Override
	public void insertPaymentPlan(PaymentPlan record) {
		// TODO Auto-generated method stub
		 payMapper.insertPaymentPlan(record);
	}

	
	/**
     * 查询结算条款详情
     * @param serialNum
     * @return
     */
	@Override
	public ClauseSettlement selectClauseDetail(String serialNum) {
		// TODO Auto-generated method stub
		
		return payMapper.selectClauseDetail(serialNum);
	}

	
	/**
     * 查询付款列表
     * @param userId
     * @return
     */
	@Override
	public List<PaymentRecord> selectAllPaymentRecordList(String userId) {
		// TODO Auto-generated method stub
		
		return payMapper.selectAllPaymentRecordList(userId);
	}
	
	

	/**
     * 查询收款列表
     * @param userId
     * @return
     */
	@Override
	public List<PaymentRecord> findAllGatheringMoneyRecord(String userId) {
		// TODO Auto-generated method stub
		return payMapper.findAllGatheringMoneyRecord(userId);
	}

	/**
     * 
     * @Description 批量删除 付款
     * @param ids
     * @return
     */
	@Override
	public void delPaymentRecord(String ids) {
		List<String> idList = ApplicationUtils.getIdList(ids);
		payMapper.delPaymentRecord(idList);
	}
	

	/**
     * 
     * @Description 确认收款
     * @param ids
     * @return
     */
	@Override
	public void confirmGatheringMoney(Map<String, Object> map) {
		// TODO Auto-generated method stub
		payMapper.confirmGatheringMoney(map);
	}

	/**
	 * 根据id查询付款对象
	 * @param id
	 * @return
	 */
	@Override
	public PaymentRecord selectPayById(String serialNum) {
		// TODO Auto-generated method stub
		
		return payMapper.selectPayById(serialNum);
	}

	/**
	 * 更新应付款
	 * @param contractVO
	 */
	@Override
	public void updatePaymentPlan(PaymentPlan record) {
		// TODO Auto-generated method stub
		payMapper.updatePaymentPlan(record);
	}

	/**
	 * 更新应付款
	 * @param contractVO
	 */
	@Override
	public void updatePaymentRecord(PaymentRecord record) {
		// TODO Auto-generated method stub
		payMapper.updatePaymentRecord(record);
	}
	
	
	/**
     * 批量添加收付款附件
     * @param paymentFiles
     */
	@Override
	public void betchInsertPaymentFiles(List<PaymentFile> Files) {
		if(!CollectionUtils.isEmpty(Files)){
			
			for(PaymentFile b:Files){
				payMapper.betchInsertPaymentFiles(b);
			}
		}
	}

	
	/**
     * 查询订单已付金额
     * @param serialNum
     * @return
     */
	@Override
	public String selectPaiedMoney(String serialNum) {
		// TODO Auto-generated method stub
		
		return payMapper.selectPaiedMoney(serialNum);
	}

	
	/**
     * 查询订单已开票金额
     * @param serialNum
     * @return
     */
	@Override
	public String selectBilledMoney(String serialNum) {
		// TODO Auto-generated method stub
		return payMapper.selectBilledMoney(serialNum);
	}

	
	/**
     * 查询收付款对象附件集合
     * @param serialNum
     * @return
     */
	@Override
	public List<PaymentFile> selectFileList(String serialNum) {
		// TODO Auto-generated method stub
		return payMapper.selectFileList(serialNum);
	}

	
	/**
     * 删除旧的附件
     * @param serialNum
     * @return
     */
	@Override
	public void deleteFileOld(String paySerialNum) {
		// TODO Auto-generated method stub
		payMapper.deleteFileOld(paySerialNum);
	}

	@Override
	@OperationLog(operateType = "add" ,operationDesc = "付款" ,objectSerial= "{serialNum}")
	public void updateOrderStatus(PaymentRecord paymentRecord) {
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setSerialNum(paymentRecord.getOrderSerial());
		orderInfo.setPayStatus(OrderInfo.PAY);//已付款
		orderInfo.setUpdateTime(new Date());
		orderInfoMapper.updateByPrimaryKeySelective(orderInfo);
	}
}