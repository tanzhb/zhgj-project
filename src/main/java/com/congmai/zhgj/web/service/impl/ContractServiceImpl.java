package com.congmai.zhgj.web.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.log.annotation.OperationLog;
import com.congmai.zhgj.web.dao.ContractMapper;
import com.congmai.zhgj.web.dao.UserMapper;
import com.congmai.zhgj.web.model.ClauseAdvance;
import com.congmai.zhgj.web.model.ClauseAfterSales;
import com.congmai.zhgj.web.model.ClauseCheckAccept;
import com.congmai.zhgj.web.model.ClauseDelivery;
import com.congmai.zhgj.web.model.ClauseSettlement;
import com.congmai.zhgj.web.model.ClauseSettlementDetail;
import com.congmai.zhgj.web.model.ContractVO;
import com.congmai.zhgj.web.model.OrderInfo;
import com.congmai.zhgj.web.model.PaymentRecord;
import com.congmai.zhgj.web.model.User;
import com.congmai.zhgj.web.model.UserExample;
import com.congmai.zhgj.web.service.ClauseSettlementService;
import com.congmai.zhgj.web.service.ContractService;
import com.congmai.zhgj.web.service.OrderService;
import com.congmai.zhgj.web.service.PayService;
import com.congmai.zhgj.web.service.UserService;

/**
 * 
 * @ClassName ContractServiceImpl
 * @Description 合同Service实现类
 * @author czw
 */
@Service
public class ContractServiceImpl extends GenericServiceImpl<ContractVO, String> implements
		ContractService {

	//合同的dao
	@Resource
	private ContractMapper contractMapper;

	@Resource
	private OrderService orderService;
	
	@Resource
	private ClauseSettlementService clauseSettlementService;
	
	@Resource 
	private PayService payService;
	
	@Override
	public GenericDao<ContractVO, String> getDao() {
		return contractMapper;
	}

	
	/**
	 * 添加用户合同
	 * @param contractVO
	 */
 @Override
	public void insertContract(ContractVO contractVO) {
		// TODO Auto-generated method stub
	 contractMapper.insertContract(contractVO);
	}
	 
	 /**
	  * 查询用户合同
	  * @param userId（用户id）
	  * @return
	  */
	public List<ContractVO> queryContractList(String userId) {
		// TODO Auto-generated method stub
		
		return contractMapper.queryContractList(userId);
	}

	
	
	@Override
	public List<ContractVO> querySaleContractList(String userId) {
		// TODO Auto-generated method stub
		return contractMapper.querySaleContractList(userId);
	}

	

	@Override
	public List<ContractVO> queryBuyContractList(String userId) {
		// TODO Auto-generated method stub
		return contractMapper.queryBuyContractList(userId);
	}


	@Override
	public List<OrderInfo> queryOrderInfo(String contractSerial) {
		// TODO Auto-generated method stub
		return contractMapper.queryOrderInfo(contractSerial);
	}


	/**
     * 
     * @Description 批量删除 合同
     * @param ids
     * @return
     */
	@Override
	public void deleteUserContractS(String ids) {
		List<String> idList = ApplicationUtils.getIdList(ids);
		contractMapper.deleteUserContractS(idList);
	}
	

	/**
	 * 根据id查询合同对象
	 * @param id
	 * @return
	 */
	@Override
	public ContractVO selectConbtractById(String id) {
		// TODO Auto-generated method stub
		
		return contractMapper.selectContractById(id);
	}

	
	/**
	 * 更新用户合同
	 * @param contractVO
	 */
	@Override
	public void updateContract(ContractVO contractVO) {
		// TODO Auto-generated method stub
		contractMapper.updateContract(contractVO);
	}


	/**
	 * 签订销售合同
	 * @param contractVO
	 */
	@Override
	public void signSaleContract(ContractVO contractVO) {
		// TODO Auto-generated method stub
		contractMapper.signSaleContract(contractVO);
	}


	/**
	 * 签订销售合同后更新订单
	 * @param contractVO
	 */
	@Override
	@OperationLog(operateType = "update" ,operationDesc = "签订合同" ,objectSerial= "{serialNum}")
	public void updateOrderAfterSign(Map<String,Object> map) {
		// TODO Auto-generated method stub
		contractMapper.updateOrderAfterSign(map);
		
		//按结算条款中的签订合同节点生成付款
		String orderString = (String) map.get("serialNum");
		String nodeString = ClauseSettlementDetail.HTQD;
		findPaymentNode(orderString, nodeString);
		
	}


	/**
	 * @Description 查找付款节点
	 * @param orderString
	 * @param nodeString
	 */
	@Override
	public void findPaymentNode(String orderString, String nodeString) {
		OrderInfo orderInfo = orderService.selectById(orderString);
		//获取合同信息
    	ContractVO contract = null;
		if(StringUtils.isNotEmpty(orderInfo.getContractSerial())){
    		contract=selectConbtractById(orderInfo.getContractSerial());
    	}
		if(contract!=null&&StringUtils.isNotEmpty(contract.getId())){
    		//获取合同条款信息
    		ClauseSettlement clauseSettlement = clauseSettlementService.selectByContractId(contract.getId());
    		if(clauseSettlement!=null){
        		List<ClauseSettlementDetail> clauseSettlementDetail = clauseSettlement.getClauseSettlementDetails();
				if(!CollectionUtils.isEmpty(clauseSettlementDetail)){
			    	for(ClauseSettlementDetail f:clauseSettlementDetail){
			    		//为当前节点而且不是先票后款，生成付款单
			    		if(nodeString.equals(f.getDeliveryNode())&&!PaymentRecord.XPHK.equals(f.getBillingMethod())){
			    			createPayRecordAndUpdateOrder(orderInfo, f);
			    		}
			    	}
		        }
    		}
    	}
	}


	/**
	 * @Description 生成付款更新订单状态
	 * @param orderInfo
	 * @param f
	 */
	public void createPayRecordAndUpdateOrder(OrderInfo orderInfo,
			ClauseSettlementDetail f) {
		Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();// 获取当前登录用户名
		
		PaymentRecord record = new PaymentRecord();
		
		String serialNum = ApplicationUtils.random32UUID();
		record.setSerialNum(serialNum);
		if(orderInfo.getSupplyComId()==null){//新建收款
			record.setPaymentNum(orderService.getNumCode("IM"));
		}else{//新建付款
			record.setPaymentNum(orderService.getNumCode("OM"));
		}
		
		record.setSupplyComId(orderInfo.getSupplyComId());
		record.setBuyComId(orderInfo.getBuyComId());
		record.setPayType(f.getPaymentType());
		record.setOrderSerial(orderInfo.getSerialNum());
		record.setApplyPaymentAmount(f.getDeliveryAmount());
		record.setApplyCurrency("人民币");
		record.setPaymentNode(f.getDeliveryNode());
		record.setBillStyle(PaymentRecord.XKHP);

		record.setCreator(currenLoginName);
		record.setUpdater(currenLoginName);
		record.setCreateTime(new Date());
		record.setUpdateTime(new Date());
		record.setStatus("0");
		payService.insertPaymentRecord(record);
		
		//设置订单状态
		OrderInfo oi = new OrderInfo();
		oi.setSerialNum(record.getOrderSerial());
		oi.setPayStatus(OrderInfo.PAYING);//付款中
		oi.setUpdateTime(new Date());
		orderService.updateStatus(oi);
	}


	@Override
	public List<ContractVO> selectList(ContractVO parm) {
		return contractMapper.selectList(parm);
	}
}