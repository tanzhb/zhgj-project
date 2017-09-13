package com.congmai.zhgj.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.web.dao.InvoiceBillingRecordMapper;
import com.congmai.zhgj.web.dao.InvoiceMapper;
import com.congmai.zhgj.web.model.Invoice;
import com.congmai.zhgj.web.model.InvoiceBillingRecord;
import com.congmai.zhgj.web.model.InvoiceExample;
import com.congmai.zhgj.web.model.InvoiceExample.Criteria;
import com.congmai.zhgj.web.service.InvoiceBillingRecordService;
import com.congmai.zhgj.web.service.InvoiceService;

/**
 * 
 * @ClassName StockInOutServiceImpl
 * @Descripzhaichao
 * @Date 2017年9月12s日 上午11:56:29
 * @version 1.0.0
 */
@Service
public class InvoiceBillingRecordServiceImpl extends GenericServiceImpl<InvoiceBillingRecord, String> implements InvoiceBillingRecordService {

	 @Resource
	    private InvoiceBillingRecordMapper  invoiceBillingRecordMapper;

	@Override
	public GenericDao<InvoiceBillingRecord, String> getDao() {
		return invoiceBillingRecordMapper;
	}

	
	}
	
	
	  
	    

   

