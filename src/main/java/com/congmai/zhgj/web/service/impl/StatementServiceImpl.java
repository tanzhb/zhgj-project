package com.congmai.zhgj.web.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.hql.internal.ast.tree.OrderByClause;
import org.springframework.stereotype.Service;

import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.core.util.DateUtil;
import com.congmai.zhgj.core.util.Handle;
import com.congmai.zhgj.core.util.StringUtil;
import com.congmai.zhgj.web.dao.InvoiceMapper;
import com.congmai.zhgj.web.dao.MemoRecordMapper;
import com.congmai.zhgj.web.dao.OrderInfoMapper;
import com.congmai.zhgj.web.dao.OrderMaterielMapper;
import com.congmai.zhgj.web.dao.PaymentPlanViewMapper;
import com.congmai.zhgj.web.dao.PaymentRecordMapper;
import com.congmai.zhgj.web.dao.StatementMapper;
import com.congmai.zhgj.web.model.Invoice;
import com.congmai.zhgj.web.model.InvoiceExample;
import com.congmai.zhgj.web.model.MemoRecord;
import com.congmai.zhgj.web.model.MemoRecordExample;
import com.congmai.zhgj.web.model.OrderInfo;
import com.congmai.zhgj.web.model.OrderInfoExample;
import com.congmai.zhgj.web.model.OrderMateriel;
import com.congmai.zhgj.web.model.OrderMaterielExample;
import com.congmai.zhgj.web.model.PaymentPlanExample;
import com.congmai.zhgj.web.model.PaymentPlanView;
import com.congmai.zhgj.web.model.PaymentRecord;
import com.congmai.zhgj.web.model.PaymentRecordExample;
import com.congmai.zhgj.web.model.PaymentRecordExample.Criteria;
import com.congmai.zhgj.web.model.Statement;
import com.congmai.zhgj.web.model.StatementExample;
import com.congmai.zhgj.web.model.StatementOrderInfo;
import com.congmai.zhgj.web.model.StatementPaymentInfo;
import com.congmai.zhgj.web.service.OrderService;
import com.congmai.zhgj.web.service.PaymentRecordSerive;
import com.congmai.zhgj.web.service.StatementService;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

/**
 * 
 * @ClassName StatementServiceImpl
 * @Description 对账单ServiceImpl
 * @author qfzhao
 * @Date 2017年7月28日 下午3:06:52
 * @version 1.0.0
 */
@Service
public class StatementServiceImpl implements StatementService {
	
	Logger logger = Logger.getLogger(StatementServiceImpl.class);
	
    @Resource
    private StatementMapper statementMapper;
    
    @Resource
    private OrderInfoMapper orderInfoMapper;
   
    @Resource
    private OrderMaterielMapper orderMaterielMapper;
    
    @Resource
    private PaymentRecordMapper paymentRecordMapper;
    
    @Resource
    private PaymentPlanViewMapper paymentPlanViewMapper;
    
    @Resource
    private InvoiceMapper invoiceMapper;
    
    @Resource
    private MemoRecordMapper memoRecordMapper;
    
    @Resource
    private PaymentRecordSerive paymentRecordSerive;
    
    
	@Override
	public int insert(Statement model) {
		return statementMapper.insert(model);
	}

	@Override
	public int update(Statement model) {
		return statementMapper.updateByPrimaryKeySelective(model);
	}

	
	@Override
	public int delete(String serialNum) {
		return statementMapper.deleteByPrimaryKey(serialNum);
	}

	@Override
	public Statement selectById(String serialNum) {
		return statementMapper.selectByPrimaryKey(serialNum);
	}

	@Override
	public Statement selectOne() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Statement> selectList() {
		return statementMapper.selectByExample(null);
	}

	@Override
	public List<Statement> selectList(StatementExample m) {
		return statementMapper.selectByExample(m);
	}

	@Override
	public void deleteStatements(String ids) {
		List<String> idList = ApplicationUtils.getIdList(ids);
		for(String id : idList){
			Statement m = new Statement();
			m.setSerialNum(id);
			m.setDelFlg("1");
			m.setUpdateTime(new Date());
			statementMapper.updateByPrimaryKeySelective(m);
		}

	}

	@Override
	public Map<String, Object> getOrderAndPaymentRecords(String supplyComId,String buyComId,
			String statementDate){
		Map<String,Object> map = new HashMap<String, Object>();
		
		List<StatementOrderInfo> list = new ArrayList<StatementOrderInfo>(); //账单信息
		List<StatementPaymentInfo> shouldPaymentList = new ArrayList<StatementPaymentInfo>(); //本月应收付
		List<MemoRecord> alreadyPaymentList = new ArrayList<MemoRecord>(); //本月收付款水单
		try{
					PaymentRecordExample paymentRecordExample = new PaymentRecordExample(); 
					Criteria criteria = paymentRecordExample.createCriteria();
					if(StringUtils.isNotEmpty(supplyComId)){ //供应商对账单
						if(StringUtils.isEmpty(buyComId)){
							criteria.andBuyComIdIsNull();
						}else{
							criteria.andBuyComIdEqualTo(buyComId);
						}
						criteria.andSupplyComIdEqualTo(supplyComId)
						.andPlayPaymentDateGreaterThanOrEqualTo(DateUtil.getMonthFirstDay(DateUtil.parse("yyyy-MM-dd", statementDate)))
						.andPlayPaymentDateLessThanOrEqualTo(DateUtil.getMonthLastDay(DateUtil.parse("yyyy-MM-dd", statementDate)));
					}else{ //客户对账单
						if(StringUtils.isEmpty(supplyComId)){
							criteria.andSupplyComIdIsNull();
						}else{
							criteria.andSupplyComIdEqualTo(supplyComId);
						}
						criteria.andBuyComIdEqualTo(buyComId)
						.andPlayPaymentDateGreaterThanOrEqualTo(DateUtil.getMonthFirstDay(DateUtil.parse("yyyy-MM-dd", statementDate)))
						.andPlayPaymentDateLessThanOrEqualTo(DateUtil.getMonthLastDay(DateUtil.parse("yyyy-MM-dd", statementDate)));
					}
					List<String> statusList = new ArrayList<String>();
					statusList.add("1");
					statusList.add("2");
					statusList.add("APPROVAL_SUCCESS");
					criteria.andDelFlgEqualTo("0").andStatusIn(statusList);
					Map<String,Map<String,Double>> map2 = new HashMap<String, Map<String,Double>>();
 					Set<OrderInfo> set = new HashSet<OrderInfo>();
 					
 					//计算期初
 					calcStatementBegin(supplyComId,buyComId,statementDate,map);
 					
					List<PaymentRecord> paymentRecords = paymentRecordMapper.selectByExample(paymentRecordExample);
					if(CollectionUtils.isNotEmpty(paymentRecords)){
						//获取本期应付/应收/扣款信息
						getShouldAndAlreadyPaymentInfo(paymentRecords,set,shouldPaymentList,/*alreadyPaymentList,*/map2);
						/*for(PaymentRecord record :paymentRecords){
							StatementPaymentInfo alreadyPaymentInfo = new StatementPaymentInfo(); //已付信息
						//	StatementPaymentInfo shouldPaymentInfo = new StatementPaymentInfo();	//应付信息
							//获取本期应付信息
							getShouldAndAlreadyPaymentInfo(paymentRecords,set,shouldPaymentList,alreadyPaymentList,map2);
//							getAlreadyPaymentInfo(paymentRecords,set,shouldPaymentList,map2);
							if(record != null){

								//获取订单信息
								OrderInfo order = orderInfoMapper.selectByPrimaryKey(record.getOrderSerial());
								double paymentAmount = Handle.stringToDouble(record.getPaymentAmount());//获取实际付款金额
								if(order!=null){
									if(map2.containsKey(order.getSerialNum())){
										//累加已付款金额
										map2.get(order.getSerialNum()).put("totalPaymentAmount",map2.get(order.getSerialNum()).get("totalPaymentAmount")+ paymentAmount);
									}
									alreadyPaymentInfo.setPaymentNum(record.getPaymentNum());//付款计划编号
									alreadyPaymentInfo.setPaymentPlanDate(DateUtil.format("yyyy-MM-dd", record.getPlayPaymentDate()));//计划付款日期
									alreadyPaymentInfo.setPaymentNode(record.getPaymentNode());//付款节点
									alreadyPaymentInfo.setPaymentStatus(record.getStatus());//付款状态
									alreadyPaymentInfo.setPaymentAmount(String.valueOf(paymentAmount));//付款金额
									alreadyPaymentInfo.setOrderNum(order.getOrderNum());//订单编号
									alreadyPaymentInfo.setIsBill(record.getIsBill());//是否开票
									//paymentSerial
									
									//开票信息
									InvoiceExample invoiceExample = new InvoiceExample();
									invoiceExample.createCriteria().andPaymentSerialEqualTo(record.getSerialNum()).andDelFlgEqualTo("0");
									List<Invoice> invoices = invoiceMapper.selectByExample(invoiceExample);
									
									List<String> invoiceNums = new ArrayList<String>();
									List<String> billingDates = new ArrayList<String>();
									
									if(CollectionUtils.isNotEmpty(invoices)){
										for(Invoice invoice : invoices){
											invoice.getInvoiceNum(); //开票编号
											invoice.getBillingDate();//开票日期
											invoiceNums.add(invoice.getInvoiceNum());
											billingDates.add(DateUtil.format("yyyy-MM-dd",invoice.getBillingDate()));
										}
										alreadyPaymentInfo.setBillNum(StringUtils.join(invoiceNums,","));//开票编号
										alreadyPaymentInfo.setBillDate(StringUtils.join(billingDates,","));//开票日期
									}else{
										alreadyPaymentInfo.setBillNum("");//开票编号
										alreadyPaymentInfo.setBillDate("");//开票日期
									}
									
									
									alreadyPaymentInfo.setPeriod("1");//账期
									alreadyPaymentInfo.setInterest("0.00");//利息
									alreadyPaymentList.add(alreadyPaymentInfo);
								}
							
							}
						}*/
						getOrderInfo(set,map2,list);
						
					}
					
					MemoRecordExample mre=new MemoRecordExample();
					com.congmai.zhgj.web.model.MemoRecordExample.Criteria c=mre.createCriteria();
					c.andDelFlgEqualTo("0");
					if(StringUtils.isNotEmpty(supplyComId)){ //供应商对账单
						if(StringUtils.isEmpty(buyComId)){
							c.andBuyComIdIsNull();
						}else{
							c.andBuyComIdEqualTo(buyComId);
						}
						c.andSupplyComIdEqualTo(supplyComId)
						.andPaymentDateGreaterThanOrEqualTo(DateUtil.getMonthFirstDay(DateUtil.parse("yyyy-MM-dd", statementDate)))
						.andPaymentDateLessThanOrEqualTo(DateUtil.getMonthLastDay(DateUtil.parse("yyyy-MM-dd", statementDate)));
					}else{ //客户对账单
						if(StringUtils.isEmpty(supplyComId)){
							c.andSupplyComIdIsNull();
						}else{
							c.andSupplyComIdEqualTo(supplyComId);
						}
						c.andBuyComIdEqualTo(buyComId)
						.andPaymentDateGreaterThanOrEqualTo(DateUtil.getMonthFirstDay(DateUtil.parse("yyyy-MM-dd", statementDate)))
						.andPaymentDateLessThanOrEqualTo(DateUtil.getMonthLastDay(DateUtil.parse("yyyy-MM-dd", statementDate)));
					}
					
					alreadyPaymentList =  memoRecordMapper.selectByExample(mre);
							
				} catch (Exception e) {
					logger.warn(e.getMessage(), e);
				}
						
						
				/*	}
				}*/
				map.put("orderList", list);
				map.put("paymentList", shouldPaymentList);
				map.put("alreadyPaymentList", alreadyPaymentList);
				return map;
	}
	
	/**
	 * 
	 * @Description (计算期初)
	 * @param supplyComId
	 * @param buyComId
	 * @param statementDate
	 * @param map
	 * @throws ParseException
	 */
	private void calcStatementBegin(String supplyComId,String buyComId,String statementDate,Map<String, Object> map) throws ParseException {
			StatementExample statement_example = new StatementExample();
			if(StringUtils.isNotEmpty(supplyComId)){
				statement_example.createCriteria().andDelFlgEqualTo("0").andSupplyComIdEqualTo(supplyComId).andBuyComIdIsNull()
	 			.andStatementDateLessThan(DateUtil.parse("yyyy-MM-dd",statementDate));
			}else{
				statement_example.createCriteria().andDelFlgEqualTo("0").andBuyComIdEqualTo(buyComId).andSupplyComIdIsNull()
	 			.andStatementDateLessThan(DateUtil.parse("yyyy-MM-dd",statementDate));
			}
			statement_example.setOrderByClause("statementDate desc");
			List<Statement> statementList = statementMapper.selectByExample(statement_example);
			if(CollectionUtils.isNotEmpty(statementList)){
				map.put("endShouldPay", statementList.get(0).getEndShouldPay());
			}else{
				map.put("endShouldPay", 0);
			}
		
	}
	

	
	/**
	 * 
	 * @Description (TODO获取本期应付和应收以及扣款信息)
	 * @param paymentPlanViews
	 * @param set
	 * @param paymentList
	 * @param moneyCountMap
	 */
	private void getShouldAndAlreadyPaymentInfo(List<PaymentRecord> paymentRecords,Set<OrderInfo> set,List<StatementPaymentInfo> shouldPaymentList,/*List<StatementPaymentInfo> alreadyPaymentList,*/Map<String,Map<String, Double>> moneyCountMap){
		
		for(PaymentRecord paymentRecord :paymentRecords){
			OrderInfoExample orderExample = new OrderInfoExample();
			orderExample.createCriteria().andSerialNumEqualTo(paymentRecord.getOrderSerial());
			OrderInfo order = orderInfoMapper.selectByPrimaryKey(paymentRecord.getOrderSerial());
			set.add(order);//将关联订单合并
			
			StatementPaymentInfo sti=new StatementPaymentInfo();
			sti.setPaymentNode(paymentRecord.getPaymentNode());
			sti.setPaymentAmount(paymentRecord.getPaymentAmount());
			sti.setApplyPaymentAmount(paymentRecord.getApplyPaymentAmount());
			sti.setPaymentStatus(paymentRecord.getStatus());
			sti.setBillNum("施工中");//发票金额
			sti.setBillDate("施工中");//发票日期
			sti.setIsBill("施工中");//是否开票
			sti.setOrderNum(order.getOrderNum());
			sti.setPaymentNum(paymentRecord.getPaymentNum());
			sti.setPaymentPlanDate(DateUtil.format("yyyy-MM-dd", paymentRecord.getPlayPaymentDate()));
			sti.setPeriod(paymentRecord.getAccountPeriod());
			sti.setInterest("施工中");
			
			if(StringUtil.isNotEmpty(paymentRecord.getPaymentAmount())){//所有应收应付，alreadyPaymentList已收付款需从收付款水单查询
//				alreadyPaymentList.add(sti);
			}else{//放入(本期应付)
				sti.setPaymentAmount("0");
//				shouldPaymentList.add(sti);
			}
			shouldPaymentList.add(sti);
			//超期金额.服务扣除金额暂且未加上
			if(moneyCountMap.containsKey(order.getSerialNum())){
				if(StringUtil.isNotEmpty(paymentRecord.getPaymentAmount())){
					moneyCountMap.get(order.getSerialNum()).put("totalPaymentAmount",moneyCountMap.get(order.getSerialNum()).get("totalPaymentAmount")+  Handle.stringToDouble(paymentRecord.getPaymentAmount()));//已付
				}
				moneyCountMap.get(order.getSerialNum()).put("totalShouldPaymentAmount",moneyCountMap.get(order.getSerialNum()).get("totalShouldPaymentAmount")+  Handle.stringToDouble(paymentRecord.getApplyPaymentAmount()));//应付
			}else{
				Map<String,Double> _map = new HashMap<String, Double>();
				String billedMoney=paymentRecordSerive.selectBilledMoney(order.getSerialNum());//获取订单已开票金额
				_map.put("totalReadyAmount", StringUtil.isEmpty(billedMoney)?0D:Handle.stringToDouble(billedMoney));//获取已开票金额
				_map.put("totalPaymentAmount", StringUtil.isEmpty(paymentRecord.getPaymentAmount())?0D:Handle.stringToDouble(paymentRecord.getPaymentAmount()));
				_map.put("totalShouldPaymentAmount", Handle.stringToDouble(paymentRecord.getApplyPaymentAmount()));//应付金额
				moneyCountMap.put(order.getSerialNum(),_map);
			}
		}
		/*for(PaymentRecord record :paymentRecord){
			
			StatementPaymentInfo shouldPaymentInfo = new StatementPaymentInfo();
			
			//获取付款计划关联订单信息，去重放入set,统计数据
			OrderInfoExample orderExample = new OrderInfoExample();
			orderExample.createCriteria().andSerialNumEqualTo(record.getOrderSerial());
			OrderInfo order = orderInfoMapper.selectByPrimaryKey(record.getOrderSerial());
			set.add(order);
			

			//PaymentRecord record = null;
			double applyPaymentAmount = Handle.stringToDouble(record.getApplyPaymentAmount()); //获取应付金额
			
			//需要在发票记录中计算
			double readyAmount = Handle.stringToDouble("0"); //获取已开金额
			double unReadyAmount = Handle.stringToDouble("0"); //获取未开金额
			
				shouldPaymentInfo.setPaymentNum(record.getPaymentNum());//付款计划编号
				shouldPaymentInfo.setPaymentPlanDate(DateUtil.format("yyyy-MM-dd", record.getPlayPaymentDate()));//计划付款日期
				shouldPaymentInfo.setPaymentNode(record.getPaymentNode());//支付节点
				shouldPaymentInfo.setPaymentStatus(record.getStatus());//支付状态
				shouldPaymentInfo.setPaymentAmount(String.valueOf(applyPaymentAmount));//应付金额
				shouldPaymentInfo.setOrderNum(order.getOrderNum());//订单编号
				if(record!=null){
					shouldPaymentInfo.setIsBill(record.getIsBill());//是否开票
					shouldPaymentInfo.setBillNum("施工中");//开票金额
					shouldPaymentInfo.setBillDate("施工中");//开票日期
				}
				shouldPaymentInfo.setPeriod("1");//账期
				shouldPaymentInfo.setInterest("施工中");//利息
				paymentList.add(shouldPaymentInfo);
				
				
				if(moneyCountMap.containsKey(order.getSerialNum())){
					moneyCountMap.get(order.getSerialNum()).put("totalPaymentAmount",0D);
					moneyCountMap.get(order.getSerialNum()).put("totalReadyAmount",moneyCountMap.get(order.getSerialNum()).get("totalReadyAmount")+ readyAmount);//累计已开票金额
					moneyCountMap.get(order.getSerialNum()).put("totalUnReadyAmount",moneyCountMap.get(order.getSerialNum()).get("totalUnReadyAmount")+ unReadyAmount);//累计未开票金额
				}else{
					Map<String,Double> _map = new HashMap<String, Double>();
					_map.put("totalReadyAmount", readyAmount);
					_map.put("totalUnReadyAmount", unReadyAmount);
					_map.put("totalPaymentAmount", 0D);
					moneyCountMap.put(order.getSerialNum(),_map);
				}
			
		}*/
	}

	private void getOrderInfo(Set<OrderInfo> set,Map<String,Map<String,Double>> map2,List<StatementOrderInfo> list){
		List<OrderInfo> _orderList = new ArrayList<OrderInfo>(set);
		
		if(CollectionUtils.isNotEmpty(_orderList)){
			for(OrderInfo o : _orderList){
				StatementOrderInfo statementOrderInfo = new StatementOrderInfo();
				double totalPaymentAmount = map2.get(o.getSerialNum()).get("totalPaymentAmount"); //实付金额
				double totalShouldPaymentAmount =  map2.get(o.getSerialNum()).get("totalShouldPaymentAmount"); //应付金额
				double totalServiceMoney = 0;  //服务扣除费用
				double overDueMoney = 0;  //超期金额
				double totalReadyAmount =  map2.get(o.getSerialNum()).get("totalReadyAmount"); //已开票金额
				try {
					/*OrderMaterielExample example2 = new OrderMaterielExample();
					example2.createCriteria().andOrderSerialEqualTo(o.getSerialNum());
					List<OrderMateriel> orderMateriels = orderMaterielMapper.selectByExample(example2);
					if(CollectionUtils.isNotEmpty(orderMateriels)){
						for(OrderMateriel m : orderMateriels){
							
							double money = Handle.stringToDouble(m.getMoney());
							totalMoney += money;
						//	double serviceRate = Handle.stringToDouble(m.getServiceRate());
						//	double serviceMoney  = money*serviceRate;
							
						//	totalServiceMoney += serviceMoney; 
						}
					}*/
					
					statementOrderInfo.setOrderNum(o.getOrderNum());
					statementOrderInfo.setOrderDate(DateUtil.format("yyyy-MM-dd",o.getOrderDate()));
					statementOrderInfo.setOrderStatus(o.getStatus());
					statementOrderInfo.setContractNum(o.getContractSerial());
					statementOrderInfo.setTotalMoney(o.getOrderAmount());//订单金额
					statementOrderInfo.setPaymentMoney(String.valueOf(totalShouldPaymentAmount));//应付金额
					statementOrderInfo.setTotalPaymentAmount(String.valueOf(totalPaymentAmount));//已付金额
					statementOrderInfo.setTotalUnPaymentMoney(String.valueOf(totalShouldPaymentAmount-totalPaymentAmount));//未付金额
					statementOrderInfo.setTotalServiceMoney(String.valueOf(totalServiceMoney));
					statementOrderInfo.setTotalReadyAmount(String.valueOf(totalReadyAmount));
					statementOrderInfo.setOverDueMoney(String.valueOf(overDueMoney));
					statementOrderInfo.setTotalUnReadyAmount(String.valueOf(totalShouldPaymentAmount-totalReadyAmount));
					list.add(statementOrderInfo);
				} catch (Exception e) {
					logger.warn(e.getMessage(), e);
				}
			
				
			}
		}			
	}

	@Override
	public boolean isExist(String supplyComId,String buyComId,String statementDate) {
		StatementExample example = new StatementExample();
		try {
			if(StringUtils.isNotEmpty(supplyComId)){
				example.createCriteria().andDelFlgEqualTo("0").andSupplyComIdEqualTo(supplyComId).andBuyComIdIsNull()
	 			.andStatementDateGreaterThanOrEqualTo(DateUtil.parse("yyyy-MM-dd",statementDate));
			}else{
				example.createCriteria().andDelFlgEqualTo("0").andSupplyComIdIsNull().andBuyComIdEqualTo(buyComId)
	 			.andStatementDateGreaterThanOrEqualTo(DateUtil.parse("yyyy-MM-dd",statementDate));
			}
			int count = statementMapper.countByExample(example);
			if(count==0){
				return false;
			}
		} catch (ParseException e) {
			logger.warn(e.getMessage(), e);
			return true;
		}
		return true;
	}
	
}
