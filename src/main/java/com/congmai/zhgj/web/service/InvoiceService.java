package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.Invoice;
import com.congmai.zhgj.web.model.InvoiceBillingRecord;
import com.congmai.zhgj.web.model.OrderInfo;
import com.congmai.zhgj.web.model.VerificationRecord;
public interface InvoiceService extends GenericService<Invoice, String>{

	void deleteInvoice(String serialNumList);//删除发票
	List<Invoice>getAllInvoice(String  InOut,String serialNum);//获取发票记录
	 Invoice  getDetailInfo(Invoice invoice);
	void confirmInvoiceIn(Invoice in);//进项票确认
	void confirmInvoiceOut(Invoice out);//销项票确认
	
	void insertInvoce(Invoice invoice);//新增发票
	
	public  Boolean  insertAllInvoiceBillingRecordInfo(List<InvoiceBillingRecord>list,String currenLoginName,String serialNum);//保存收开票记录  serialNum 发票流水
}
