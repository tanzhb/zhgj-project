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
import org.hibernate.hql.internal.ast.tree.OrderByClause;
import org.springframework.stereotype.Service;

import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.core.util.DateUtil;
import com.congmai.zhgj.core.util.Handle;
import com.congmai.zhgj.web.dao.InvoiceMapper;
import com.congmai.zhgj.web.dao.OrderInfoMapper;
import com.congmai.zhgj.web.dao.OrderMaterielMapper;
import com.congmai.zhgj.web.dao.PaymentPlanViewMapper;
import com.congmai.zhgj.web.dao.PaymentRecordMapper;
import com.congmai.zhgj.web.dao.StatementMapper;
import com.congmai.zhgj.web.model.Invoice;
import com.congmai.zhgj.web.model.InvoiceExample;
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

/*	@Override
	public Map<String, Object> getOrderAndPaymentRecords(String supplyComId,
			String statementDate) {
		Map<String,Object> map = new HashMap<String, Object>();
		OrderInfoExample example = new OrderInfoExample();
		example.createCriteria().andSupplyComIdEqualTo(supplyComId).andBuyComIdIsNull().andDelFlgEqualTo("0");
		List<OrderInfo> orderList = orderInfoMapper.selectByExample(example);
		
		List<StatementOrderInfo> list = new ArrayList<StatementOrderInfo>(); //账单信息
		List<StatementPaymentInfo> paymentList = new ArrayList<StatementPaymentInfo>(); //本月应付
		List<StatementPaymentInfo> alreadyPaymentList = new ArrayList<StatementPaymentInfo>(); //本月已付
		if(CollectionUtils.isNotEmpty(orderList)){
			for(OrderInfo o : orderList){
				StatementOrderInfo statementOrderInfo = new StatementOrderInfo();
				double totalPaymentAmount = 0; //实付金额
				double totalMoney = 0; //应付金额
				double totalServiceMoney = 0;  //服务费
				double totalReadyAmount = 0; //已开金额
				double totalUnReadyAmount = 0; //未开金额
				try {
					OrderMaterielExample example2 = new OrderMaterielExample();
					example2.createCriteria().andOrderSerialEqualTo(o.getSerialNum());
					List<OrderMateriel> orderMateriels = orderMaterielMapper.selectByExample(example2);
					if(CollectionUtils.isNotEmpty(orderMateriels)){
						for(OrderMateriel m : orderMateriels){
							
							double money = Handle.stringToDouble(m.getMoney());
							totalMoney += money;
							double serviceRate = Handle.stringToDouble(m.getServiceRate());
							double serviceMoney  = money*serviceRate;
							
							totalServiceMoney += serviceMoney; 
						}
					}
					
					PaymentPlanExample example3 = new PaymentPlanExample();
					example3.createCriteria().andOrderSerialEqualTo(o.getSerialNum()).andDelFlgEqualTo("0");
					List<PaymentPlanView> paymentPlanViews = paymentPlanViewMapper.selectByExample(example3);
					if(CollectionUtils.isNotEmpty(paymentPlanViews)){
						for(PaymentPlanView plan :paymentPlanViews){
							StatementPaymentInfo statementPaymentInfo = new StatementPaymentInfo();
							
							PaymentRecordExample example4 = new PaymentRecordExample();
							example4.createCriteria().andDelFlgEqualTo("0").andPaymentPlanSerialEqualTo(plan.getSerialNum());
							List<PaymentRecord> records = paymentRecordMapper.selectByExample(example4);
							//PaymentRecord record = null;
							double paymentAmount = Handle.stringToDouble(plan.getPaymentAmount());
							double readyAmount = Handle.stringToDouble(plan.getReadyAmount());
							double unReadyAmount = Handle.stringToDouble(plan.getUnreadyAmount());
							if(CollectionUtils.isNotEmpty(records)){
								PaymentRecord record = records.get(0);
								statementPaymentInfo.setPaymentPlanNum(plan.getPaymentPlanNum());
								statementPaymentInfo.setPaymentPlanDate(DateUtil.format("yyyy-MM-dd", plan.getPlayPaymentDate()));
								statementPaymentInfo.setPaymentNode(plan.getPaymentNode());
								statementPaymentInfo.setPaymentStatus(plan.getStatus());
								statementPaymentInfo.setPaymentAmount(String.valueOf(paymentAmount));
								statementPaymentInfo.setOrderNum(o.getOrderNum());
								statementPaymentInfo.setIsBill(record.getIsBill());
								statementPaymentInfo.setBillNum("敬请期待");
								statementPaymentInfo.setBillDate("敬请期待");
								statementPaymentInfo.setPeriod("1");
								statementPaymentInfo.setInterest("额。。");
								
								alreadyPaymentList.add(statementPaymentInfo);
								paymentList.add(statementPaymentInfo);
								totalPaymentAmount += paymentAmount;
								totalReadyAmount += readyAmount;
								totalUnReadyAmount += unReadyAmount;
							}else{
								statementPaymentInfo.setPaymentPlanNum(plan.getPaymentPlanNum());
								statementPaymentInfo.setPaymentPlanDate(DateUtil.format("yyyy-MM-dd", plan.getPlayPaymentDate()));
								statementPaymentInfo.setPaymentNode(plan.getPaymentNode());
								statementPaymentInfo.setPaymentStatus(plan.getStatus());
								statementPaymentInfo.setPaymentAmount(String.valueOf(paymentAmount));
								statementPaymentInfo.setOrderNum(o.getOrderNum());
								statementPaymentInfo.setBillNum("敬请期待");
								statementPaymentInfo.setBillDate("敬请期待");
								statementPaymentInfo.setPeriod("1");
								statementPaymentInfo.setInterest("额。。");
								paymentList.add(statementPaymentInfo);
							}
							
						}
					}
					statementOrderInfo.setOrderNum(o.getOrderNum());
					statementOrderInfo.setOrderDate(DateUtil.format("yyyy-MM-dd",o.getOrderDate()));
					statementOrderInfo.setOrderStatus(o.getStatus());
					statementOrderInfo.setContractNum(o.getContractSerial());
					statementOrderInfo.setTotalMoney(String.valueOf(totalMoney));
					statementOrderInfo.setPaymentMoney(String.valueOf(totalMoney));
					statementOrderInfo.setTotalPaymentAmount(String.valueOf(totalPaymentAmount));
					statementOrderInfo.setTotalUnPaymentMoney(String.valueOf(totalMoney-totalPaymentAmount));
					statementOrderInfo.setTotalServiceMoney(String.valueOf(totalServiceMoney));
					statementOrderInfo.setTotalReadyAmount(String.valueOf(totalReadyAmount));
					statementOrderInfo.setTotalUnReadyAmount(String.valueOf(totalUnReadyAmount));
					list.add(statementOrderInfo);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println(this.getClass()+"-------->"+e.getMessage()+":"+e.getStackTrace());
				}
				
				
			}
		}
		map.put("orderList", list);
		map.put("paymentList", paymentList);
		map.put("alreadyPaymentList", alreadyPaymentList);
		return map;
	}*/
	
	@Override
	public Map<String, Object> getOrderAndPaymentRecords(String supplyComId,String buyComId,
			String statementDate){
		Map<String,Object> map = new HashMap<String, Object>();
		
		List<StatementOrderInfo> list = new ArrayList<StatementOrderInfo>(); //账单信息
		List<StatementPaymentInfo> shouldPaymentList = new ArrayList<StatementPaymentInfo>(); //本月应付
		List<StatementPaymentInfo> alreadyPaymentList = new ArrayList<StatementPaymentInfo>(); //本月已付
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
					Map<String,Map<String,Double>> map2 = new HashMap<String, Map<String,Double>>();
 					Set<OrderInfo> set = new HashSet<OrderInfo>();
 					
 					//计算期初
 					calcStatementBegin(supplyComId,buyComId,statementDate,map);
 					
					List<PaymentRecord> paymentRecords = paymentRecordMapper.selectByExample(paymentRecordExample);
					if(CollectionUtils.isNotEmpty(paymentRecords)){
						for(PaymentRecord record :paymentRecords){
							StatementPaymentInfo alreadyPaymentInfo = new StatementPaymentInfo(); //已付信息
							StatementPaymentInfo shouldPaymentInfo = new StatementPaymentInfo();	//应付信息
							//获取本期应付信息
							getShouldPaymentInfo2(paymentRecords,set,shouldPaymentList,map2);
							
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
									if(CollectionUtils.isNotEmpty(invoices)){
										for(Invoice invoice : invoices){
											invoice.getInvoiceNum(); //开票编号
											invoice.getBillingDate();//开票日期
										}
									}
									
									alreadyPaymentInfo.setBillNum("施工中");//开票编号
									alreadyPaymentInfo.setBillDate("施工中");//开票日期
									alreadyPaymentInfo.setPeriod("1");//账期
									alreadyPaymentInfo.setInterest("施工中");//利息
									alreadyPaymentList.add(alreadyPaymentInfo);
								}
							
							}
						}
						getOrderInfo(set,map2,list);
					}
							
				} catch (Exception e) {
							e.printStackTrace();
							System.out.println(this.getClass()+"-------->"+e.getMessage()+":"+e.getStackTrace());
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
	 			.andStatementDateGreaterThan(DateUtil.parse("yyyy-MM-dd",statementDate));
			}else{
				statement_example.createCriteria().andDelFlgEqualTo("0").andBuyComIdEqualTo(buyComId).andSupplyComIdIsNull()
	 			.andStatementDateGreaterThan(DateUtil.parse("yyyy-MM-dd",statementDate));
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
	 * @Description (TODO获取本期应付信息)
	 * @param paymentPlanViews
	 * @param set
	 * @param paymentList
	 * @param map2
	 */
	private void getShouldPaymentInfo(List<PaymentPlanView> paymentPlanViews,Set<OrderInfo> set,List<StatementPaymentInfo> paymentList,Map<String,Map<String, Double>> map2){
		for(PaymentPlanView plan :paymentPlanViews){
			
			StatementPaymentInfo statementPaymentInfo = new StatementPaymentInfo();
			
			//获取付款计划关联订单信息，去重放入set,统计数据
			OrderInfoExample orderExample = new OrderInfoExample();
			orderExample.createCriteria().andSerialNumEqualTo(plan.getOrderSerial());
			OrderInfo order = orderInfoMapper.selectByPrimaryKey(plan.getOrderSerial());
			set.add(orderInfoMapper.selectByPrimaryKey(plan.getOrderSerial()));
			
			//构造付款计划查询条件
			PaymentRecordExample example4 = new PaymentRecordExample();
			example4.createCriteria().andDelFlgEqualTo("0").andPaymentPlanSerialEqualTo(plan.getSerialNum());
			//付款计划列表
			List<PaymentRecord> records = paymentRecordMapper.selectByExample(example4);
			PaymentRecord record = null;
			double paymentAmount = Handle.stringToDouble(plan.getPaymentAmount()); //获取应付金额
			double readyAmount = Handle.stringToDouble(plan.getReadyAmount()); //获取已开金额
			double unReadyAmount = Handle.stringToDouble(plan.getUnreadyAmount()); //获取未开金额
				if(CollectionUtils.isNotEmpty(records)){
					record = records.get(0);
				}
				//statementPaymentInfo.setPaymentPlanNum(plan.getPaymentPlanNum());//付款计划编号
				statementPaymentInfo.setPaymentPlanDate(DateUtil.format("yyyy-MM-dd", plan.getPlayPaymentDate()));//计划付款日期
				statementPaymentInfo.setPaymentNode(plan.getPaymentNode());//支付节点
				statementPaymentInfo.setPaymentStatus(plan.getStatus());//支付状态
				statementPaymentInfo.setPaymentAmount(String.valueOf(paymentAmount));//应付金额
				statementPaymentInfo.setOrderNum(order.getOrderNum());//订单编号
				if(record!=null){
					statementPaymentInfo.setIsBill(record.getIsBill());//是否开票
					statementPaymentInfo.setBillNum("施工中");//开票金额
					statementPaymentInfo.setBillDate("施工中");//开票日期
				}
				statementPaymentInfo.setPeriod("1");//账期
				statementPaymentInfo.setInterest("施工中");//利息
				paymentList.add(statementPaymentInfo);
				
				
				if(map2.containsKey(order.getSerialNum())){
					map2.get(order.getSerialNum()).put("totalPaymentAmount",0D);
					map2.get(order.getSerialNum()).put("totalReadyAmount",map2.get(order.getSerialNum()).get("totalReadyAmount")+ readyAmount);//累计已开票金额
					map2.get(order.getSerialNum()).put("totalUnReadyAmount",map2.get(order.getSerialNum()).get("totalUnReadyAmount")+ unReadyAmount);//累计未开票金额
				}else{
					Map<String,Double> _map = new HashMap<String, Double>();
					_map.put("totalReadyAmount", readyAmount);
					_map.put("totalUnReadyAmount", unReadyAmount);
					map2.put(order.getSerialNum(),_map);
				}
			
		}
	}
	
	/**
	 * 
	 * @Description (TODO获取本期应付信息)
	 * @param paymentPlanViews
	 * @param set
	 * @param paymentList
	 * @param moneyCountMap
	 */
	private void getShouldPaymentInfo2(List<PaymentRecord> paymentRecord,Set<OrderInfo> set,List<StatementPaymentInfo> paymentList,Map<String,Map<String, Double>> moneyCountMap){
		for(PaymentRecord record :paymentRecord){
			
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
					moneyCountMap.put(order.getSerialNum(),_map);
				}
			
		}
	}

	private void getOrderInfo(Set<OrderInfo> set,Map<String,Map<String,Double>> map2,List<StatementOrderInfo> list){
		List<OrderInfo> _orderList = new ArrayList<OrderInfo>(set);
		
		if(CollectionUtils.isNotEmpty(_orderList)){
			for(OrderInfo o : _orderList){
				StatementOrderInfo statementOrderInfo = new StatementOrderInfo();
				double totalPaymentAmount = map2.get(o.getSerialNum()).get("totalPaymentAmount"); //实付金额
				double totalMoney = 0; //应付金额
				double totalServiceMoney = 0;  //服务费
				double totalReadyAmount =  map2.get(o.getSerialNum()).get("totalReadyAmount"); //已开金额
				double totalUnReadyAmount =  map2.get(o.getSerialNum()).get("totalUnReadyAmount"); //未开金额
				try {
					OrderMaterielExample example2 = new OrderMaterielExample();
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
					}
					
					statementOrderInfo.setOrderNum(o.getOrderNum());
					statementOrderInfo.setOrderDate(DateUtil.format("yyyy-MM-dd",o.getOrderDate()));
					statementOrderInfo.setOrderStatus(o.getStatus());
					statementOrderInfo.setContractNum(o.getContractSerial());
					statementOrderInfo.setTotalMoney(String.valueOf(totalMoney));
					statementOrderInfo.setPaymentMoney(String.valueOf(totalMoney));
					statementOrderInfo.setTotalPaymentAmount(String.valueOf(totalPaymentAmount));
					statementOrderInfo.setTotalUnPaymentMoney(String.valueOf(totalMoney-totalPaymentAmount));
					//statementOrderInfo.setTotalServiceMoney(String.valueOf(totalServiceMoney));
					statementOrderInfo.setTotalReadyAmount(String.valueOf(totalReadyAmount));
					statementOrderInfo.setTotalUnReadyAmount(String.valueOf(totalUnReadyAmount));
					list.add(statementOrderInfo);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println(this.getClass()+"-------->"+e.getMessage()+":"+e.getStackTrace());
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
			e.printStackTrace();
			return true;
		}
		return true;
	}
	
}
