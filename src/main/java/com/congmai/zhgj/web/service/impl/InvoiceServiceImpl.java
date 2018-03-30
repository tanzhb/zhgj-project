package com.congmai.zhgj.web.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.core.util.StringUtil;
import com.congmai.zhgj.log.annotation.OperationLog;
import com.congmai.zhgj.web.dao.InvoiceBillingRecordMapper;
import com.congmai.zhgj.web.dao.InvoiceMapper;
import com.congmai.zhgj.web.dao.OrderInfoMapper;
import com.congmai.zhgj.web.dao.OrderMaterielMapper;
import com.congmai.zhgj.web.enums.StaticConst;
import com.congmai.zhgj.web.model.ClauseSettlementDetail;
import com.congmai.zhgj.web.model.Invoice;
import com.congmai.zhgj.web.model.InvoiceBillingRecord;
import com.congmai.zhgj.web.model.InvoiceBillingRecordExample;
import com.congmai.zhgj.web.model.InvoiceExample;
import com.congmai.zhgj.web.model.PaymentRecord;
import com.congmai.zhgj.web.model.VerificationRecord;
import com.congmai.zhgj.web.model.InvoiceExample.Criteria;
import com.congmai.zhgj.web.model.OrderInfo;
import com.congmai.zhgj.web.model.OrderMateriel;
import com.congmai.zhgj.web.service.ClauseSettlementService;
import com.congmai.zhgj.web.service.InvoiceService;
import com.congmai.zhgj.web.service.OrderService;
import com.congmai.zhgj.web.service.PayService;

/**
 * 
 * @ClassName StockInOutServiceImpl
 * @Descripzhaichao
 * @Date 2017年8月23日 上午11:56:29
 * @version 1.0.0
 */
@Service
public class InvoiceServiceImpl extends GenericServiceImpl<Invoice, String> implements InvoiceService {

	 @Resource
	    private InvoiceMapper  invoiceMapper;
	 @Resource
	    private OrderMaterielMapper  orderMaterielMapper ;
	 @Resource
	    private InvoiceBillingRecordMapper   invoiceBillingRecordMapper ;
	 @Resource
	    private OrderInfoMapper  orderInfoMapper ;
	@Resource
	private OrderService orderService;
	@Resource 
	private PayService payService;

	@Override
	public GenericDao<Invoice, String> getDao() {
		return invoiceMapper;
	}

	@Override
	public void deleteInvoice(String serialNumList) {
		List<String> idList = ApplicationUtils.getIdList(serialNumList);
		invoiceMapper.deleteInvoice(idList);
	}
	@Override
	public List<Invoice> getAllInvoice(String InOut, String serialNum) {
		InvoiceExample ie=new InvoiceExample();
		Criteria criteria=ie.createCriteria();
    	criteria.andDelFlgEqualTo("0");
    	if(InOut.indexOf("in")>-1){
    		criteria.andSupplyComIdIsNotNull();
    		criteria.andBuyComIdIsNull();
    	}else{
    		criteria.andSupplyComIdIsNull();
    		criteria.andBuyComIdIsNotNull();
    	}
		return invoiceMapper.selectByExample(ie);
	}

	@Override
	public Invoice getDetailInfo(Invoice invoice) {
		InvoiceBillingRecordExample ibe=new InvoiceBillingRecordExample();
		com.congmai.zhgj.web.model.InvoiceBillingRecordExample.Criteria c=ibe.createCriteria();
		c.andInvoiceSerialEqualTo(invoice.getSerialNum());
		List<InvoiceBillingRecord>list=invoiceBillingRecordMapper.selectByExample(ibe);
		BigDecimal billOrReceiptMoney=BigDecimal.ZERO;
		BigDecimal price=BigDecimal.ZERO;
		if(list!=null&&list.size()>0){
			for(InvoiceBillingRecord i:list){
				OrderMateriel orderMateriel=orderMaterielMapper.selectByPrimaryKey(i.getOrderMaterielSerial());
				/*OrderInfo orderInfo=orderInfoMapper.selectByPrimaryKey(orderMateriel.getOrderSerial());
				if(StaticConst.getInfo("orderTypeIn").equalsIgnoreCase(orderInfo.getOrderType())){
					price=price.add(new BigDecimal(orderInfo.getRate()).multiply(new BigDecimal(orderMateriel.getOrderUnitPrice())));
				}else{
					price=new BigDecimal(orderMateriel.getOrderUnitPrice());
				}*/
				price=new BigDecimal(orderMateriel.getOrderRateUnit());
				billOrReceiptMoney=billOrReceiptMoney.add(new BigDecimal(i.getBillCount()==null?"0":i.getBillCount()).multiply(price));
			}
		}
		invoice.setBillOrReceiptMoney(billOrReceiptMoney.toString());
		
		return invoice;
	}

	@Override
	@OperationLog(operateType = "add" ,operationDesc = "收票" ,objectSerial= "{serialNum}")
	public void confirmInvoiceIn(Invoice in) {//进项票确认
		// TODO Auto-generated method stub
		OrderInfo orderInfo=new OrderInfo();
		orderInfo.setPayStatus(OrderInfo.RECIVEBILL);
		orderInfo.setSerialNum(in.getOrderSerial());
		orderInfoMapper.updateByPrimaryKeySelective(orderInfo);
		
		if("1".equals(in.getBillWay())){
			createPayRecordAndUpdateOrder(in);
		}
	}
	
	/**
	 * @Description 生成付款更新订单状态
	 * @param orderInfo
	 * @param f
	 */
	public void createPayRecordAndUpdateOrder(Invoice i) {
		Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();// 获取当前登录用户名
		
		PaymentRecord record = new PaymentRecord();
		
		String serialNum = ApplicationUtils.random32UUID();
		record.setSerialNum(serialNum);
		if(i.getSupplyComId()==null){//新建收款
			record.setPaymentNum(orderService.getNumCode("IM"));
		}else{//新建付款
			record.setPaymentNum(orderService.getNumCode("OM"));
		}
		
		record.setSupplyComId(i.getSupplyComId());
		record.setBuyComId(i.getBuyComId());
		record.setOrderSerial(i.getOrderSerial());
		record.setApplyPaymentAmount(i.getInvoiceAmount());

		record.setBillStyle(PaymentRecord.XPHK);

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
	@OperationLog(operateType = "add" ,operationDesc = "开票" ,objectSerial= "{serialNum}")
	public void confirmInvoiceOut(Invoice out) {//销项票确认
		// TODO Auto-generated method stub
		OrderInfo orderInfo=new OrderInfo();
		orderInfo.setPayStatus(OrderInfo.BILL);
		orderInfo.setSerialNum(out.getOrderSerial());
		orderInfoMapper.updateByPrimaryKeySelective(orderInfo);
		
		if("1".equals(out.getBillWay())){
			createPayRecordAndUpdateOrder(out);
		}
	}
	

	@Override
	public void insertInvoce(Invoice invoice) {
		// TODO Auto-generated method stub
		if(invoice.getInvoiceNum().indexOf("OT")>-1){
			insertInvoiceForIn(invoice);//进项票
		}else{
			insertInvoiceForOut(invoice);//销项票
		}
		
	}
	
	@OperationLog(operateType = "add" ,operationDesc = "收票" ,objectSerial= "{serialNum}")
	public void insertInvoiceForIn(Invoice in) {//进项票新增
		// TODO Auto-generated method stub
		invoiceMapper.insert(in);
		
		
	}
	@OperationLog(operateType = "add" ,operationDesc = "开票" ,objectSerial= "{serialNum}")
	public void insertInvoiceForOut(Invoice in) {//销项票新增
		// TODO Auto-generated method stub
		invoiceMapper.insert(in);
		
	}

	@Override
	public Boolean insertAllInvoiceBillingRecordInfo(
			List<InvoiceBillingRecord> list, String currenLoginName,
			String serialNum) {
		if(!CollectionUtils.isEmpty(list)){
			InvoiceBillingRecordExample ire=new  InvoiceBillingRecordExample();
			com.congmai.zhgj.web.model.InvoiceBillingRecordExample.Criteria  c=ire.createCriteria();
			c.andInvoiceSerialEqualTo(list.get(0).getInvoiceSerial());
			invoiceBillingRecordMapper.deleteByExample(ire);
			for(InvoiceBillingRecord v:list){
				v.setSerialNum(ApplicationUtils.random32UUID());
				v.setCreateTime(new Date());
				v.setDelFlg("0");
				v.setCreator(currenLoginName);
				invoiceBillingRecordMapper.insert(v);//保存收开票记录
				/*if(StringUtil.isEmpty(v.getSerialNum())||"null".equals(v.getSerialNum())){
					v.setSerialNum(ApplicationUtils.random32UUID());
					v.setCreateTime(new Date());
					v.setDelFlg("0");
					v.setCreator(currenLoginName);
					invoiceBillingRecordMapper.insert(v);//保存收开票记录
				}else{
					v.setUpdateTime(new Date());
					v.setUpdater(currenLoginName);
					invoiceBillingRecordMapper.updateByPrimaryKey(v);//更新收开票记录
				}*/
			}
		}
		return true;
	}
	}
	
	
	  
	    

   

