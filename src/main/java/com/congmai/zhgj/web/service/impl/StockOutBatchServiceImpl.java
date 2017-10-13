package com.congmai.zhgj.web.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.web.dao.StockOutBatchMapper;
import com.congmai.zhgj.web.model.StockOutBatch;
import com.congmai.zhgj.web.model.StockOutBatchExample;
import com.congmai.zhgj.web.model.StockOutBatchExample.Criteria;
import com.congmai.zhgj.web.service.StockOutBatchService;

/**
 * 
 * @ClassName WarehouseServiceImpl
 * @Descripzhaichao
 * @Date 2017年7月28日 下午4:56:29
 * @version 1.0.0
 */
@Service
public class StockOutBatchServiceImpl extends GenericServiceImpl<StockOutBatch, String> implements  StockOutBatchService {
    @Resource
    private  StockOutBatchMapper  stockOutBatchMapper;
	@Override
	public GenericDao<StockOutBatch, String> getDao() {
		// TODO Auto-generated method stub
		return  stockOutBatchMapper;
	}

	@Override
	public List<StockOutBatch> selectList(String deliverMaterielSerial) {
		StockOutBatchExample stockOutBatchExample=new  StockOutBatchExample();
		Criteria criteria= stockOutBatchExample.createCriteria();
		criteria.andDelFlgEqualTo("0");
		return stockOutBatchMapper.selectByExample(stockOutBatchExample);
	}

}
