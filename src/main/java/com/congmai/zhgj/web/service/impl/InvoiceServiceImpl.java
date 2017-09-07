package com.congmai.zhgj.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.web.dao.InvoiceMapper;
import com.congmai.zhgj.web.model.Invoice;
import com.congmai.zhgj.web.model.InvoiceExample;
import com.congmai.zhgj.web.model.InvoiceExample.Criteria;
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
	}
	
	
	  
	    

   

