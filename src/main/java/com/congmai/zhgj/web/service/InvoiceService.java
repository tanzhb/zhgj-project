package com.congmai.zhgj.web.service;

import java.util.List;
import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.Invoice;
public interface InvoiceService extends GenericService<Invoice, String>{

	void deleteInvoice(String serialNumList);//删除发票
	List<Invoice>getAllInvoice(String  InOut,String serialNum);//获取发票记录
	 Invoice  getDetailInfo(Invoice invoice);
	
	
	
}
