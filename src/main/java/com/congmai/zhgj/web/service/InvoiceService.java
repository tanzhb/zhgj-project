package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.Invoice;
import com.congmai.zhgj.web.model.OrderInfo;
public interface InvoiceService extends GenericService<Invoice, String>{

	void deleteInvoice(String serialNumList);//删除发票
	List<Invoice>getAllInvoice(String  InOut,String serialNum);//获取发票记录
	 Invoice  getDetailInfo(Invoice invoice);
	void confirmInvoiceIn(Invoice in,OrderInfo o);//进项票确认
	void confirmInvoiceOut(Invoice out,OrderInfo o);//销项票确认
	
	
}
