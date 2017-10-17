package com.congmai.zhgj.web.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.log.annotation.OperationLog;
import com.congmai.zhgj.web.dao.InvoiceBillingRecordMapper;
import com.congmai.zhgj.web.dao.InvoiceMapper;
import com.congmai.zhgj.web.dao.OrderInfoMapper;
import com.congmai.zhgj.web.dao.OrderMaterielMapper;
import com.congmai.zhgj.web.enums.StaticConst;
import com.congmai.zhgj.web.model.Invoice;
import com.congmai.zhgj.web.model.InvoiceBillingRecord;
import com.congmai.zhgj.web.model.InvoiceBillingRecordExample;
import com.congmai.zhgj.web.model.InvoiceExample;
import com.congmai.zhgj.web.model.InvoiceExample.Criteria;
import com.congmai.zhgj.web.model.OrderInfo;
import com.congmai.zhgj.web.model.OrderMateriel;
import com.congmai.zhgj.web.service.InvoiceService;

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
				OrderInfo orderInfo=orderInfoMapper.selectByPrimaryKey(orderMateriel.getOrderSerial());
				if(orderInfo.getOrderType().equalsIgnoreCase(StaticConst.getInfo("orderTypeIn"))){
					price=price.add(new BigDecimal(orderInfo.getRate()).multiply(new BigDecimal(orderMateriel.getOrderUnitPrice())));
				}else{
					price=new BigDecimal(orderMateriel.getOrderUnitPrice());
				}
				billOrReceiptMoney=billOrReceiptMoney.add(new BigDecimal(i.getBillCount()).multiply(price));
			}
		}
		invoice.setBillOrReceiptMoney(billOrReceiptMoney.toString());
		
		return invoice;
	}

	@Override
	@OperationLog(operateType = "add" ,operationDesc = "收票" ,objectSerial= "{serialNum}")
	public void confirmInvoiceIn(Invoice in,OrderInfo o) {//进项票确认
		// TODO Auto-generated method stub
		o.setBillStatus(OrderInfo.RECIVEBILL);
		o.setSerialNum(in.getOrderSerial());
		orderInfoMapper.updateByPrimaryKey(o);
		
		
	}

	@Override
	@OperationLog(operateType = "add" ,operationDesc = "开票" ,objectSerial= "{serialNum}")
	public void confirmInvoiceOut(Invoice out,OrderInfo o) {//销项票确认
		// TODO Auto-generated method stub
		o.setBillStatus(OrderInfo.BILL);
		o.setSerialNum(out.getOrderSerial());
		orderInfoMapper.updateByPrimaryKey(o);
	}
	}
	
	
	  
	    

   

