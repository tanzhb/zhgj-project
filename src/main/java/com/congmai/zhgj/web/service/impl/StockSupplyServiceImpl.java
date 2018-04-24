package com.congmai.zhgj.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.core.util.StringUtil;
import com.congmai.zhgj.web.dao.DeliveryMaterielMapper;
import com.congmai.zhgj.web.dao.StockInBatchMapper;
import com.congmai.zhgj.web.dao.StockInOutRecordMapper;
import com.congmai.zhgj.web.dao.StockMapper;
import com.congmai.zhgj.web.dao.StockOutBatchMapper;
import com.congmai.zhgj.web.dao.StockSupplyRecordMapper;
import com.congmai.zhgj.web.dao.WarehouseMapper;
import com.congmai.zhgj.web.dao.WarehousepositionMapper;
import com.congmai.zhgj.web.model.DeliveryMateriel;
import com.congmai.zhgj.web.model.Stock;
import com.congmai.zhgj.web.model.StockExample;
import com.congmai.zhgj.web.model.StockOutBatchExample;
import com.congmai.zhgj.web.model.StockSupplyRecord;
import com.congmai.zhgj.web.model.Warehouse;
import com.congmai.zhgj.web.model.StockExample.Criteria;
import com.congmai.zhgj.web.model.StockInBatch;
import com.congmai.zhgj.web.model.StockOutBatch;
import com.congmai.zhgj.web.service.StockService;
import com.congmai.zhgj.web.service.StockSupplyRecordService;

/**
 * 
 * @ClassName StockServiceImpl
 * @Descripzhaichao
 * @Date 2017年8月21日 下午4:56:29
 * @version 1.0.0
 */
@Service
public class StockSupplyServiceImpl extends GenericServiceImpl<StockSupplyRecord, String> implements StockSupplyRecordService {

	 @Resource
	    private StockMapper  stockMapper;
	
	 @Resource
	    private StockInOutRecordMapper stockInOutRecordMapper;
	 
	 @Resource
	    private DeliveryMaterielMapper deliveryMaterielMapper;
	 
	 @Resource
	    private StockOutBatchMapper stockOutBatchMapper;
	 @Resource
	    private WarehouseMapper  warehouseMapper;
	 @Resource
	    private WarehousepositionMapper  warehousepositionMapper;
	 @Resource
	    private StockInBatchMapper  stockInBatchMapper;
	 @Resource
	    private StockSupplyRecordMapper  stockSupplyRecordMapper;

	

	@Override
	public void deleteStockSupply(String serialNums) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public GenericDao<StockSupplyRecord, String> getDao() {
		// TODO Auto-generated method stub
		return  stockSupplyRecordMapper;
	}


	
	
	}
	
	
	  
	    

   

