package com.congmai.zhgj.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.web.dao.StockInOutCheckMapper;
import com.congmai.zhgj.web.dao.StockInOutRecordMapper;
import com.congmai.zhgj.web.dao.StockMapper;
import com.congmai.zhgj.web.model.Stock;
import com.congmai.zhgj.web.model.StockExample;
import com.congmai.zhgj.web.model.StockInOutCheck;
import com.congmai.zhgj.web.model.StockInOutCheckExample;
import com.congmai.zhgj.web.model.StockInOutCheckExample.Criteria;
import com.congmai.zhgj.web.model.StockInOutRecord;
import com.congmai.zhgj.web.model.StockInOutRecordExample;
import com.congmai.zhgj.web.service.StockInOutCheckService;
import com.congmai.zhgj.web.service.StockService;

/**
 * 
 * @ClassName StockInOutServiceImpl
 * @Descripzhaichao
 * @Date 2017年8月23日 上午11:56:29
 * @version 1.0.0
 */
@Service
public class StockInOutCheckServiceImpl extends GenericServiceImpl<StockInOutCheck, String> implements StockInOutCheckService {

	 @Resource
	    private StockInOutCheckMapper  stockInOutCheckMapper;

	@Override
	public GenericDao<StockInOutCheck, String> getDao() {
		return stockInOutCheckMapper;
	}

	@Override
	public void deleteStockInOutCheck(String serialNumList) {
		List<String> idList = ApplicationUtils.getIdList(serialNumList);
		stockInOutCheckMapper.deleteStockInOutCheck(idList);
		
	}

	@Override
	public List<StockInOutCheck> getAllStockInOutCheck(String  InOut) {
		StockInOutCheckExample sore=new  StockInOutCheckExample();
    	Criteria criteria=sore.createCriteria();
    	criteria.andDelFlgEqualTo("0");
    	if("in".equals(InOut)){
    		criteria.andDeliverSerialIsNull();
    		criteria.andTakeDeliverSerialIsNotNull();
    	}else if("out".equals(InOut)){
    		criteria.andDeliverSerialIsNotNull();
    		criteria.andTakeDeliverSerialIsNull();
    	}
		return stockInOutCheckMapper.selectByExample(sore);
	}

	
	
	}
	
	
	  
	    

   

