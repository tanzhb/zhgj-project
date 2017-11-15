package com.congmai.zhgj.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;



import com.congmai.zhgj.web.model.StockInBatch;
import com.congmai.zhgj.web.dao.StockInBatchMapper;
import com.congmai.zhgj.web.service.StockInBatchService;

/**
 * 
 * @ClassName StockInBatchServiceImpl
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author qfzhao
 * @Date 2017年11月13日 下午2:28:21
 * @version 1.0.0
 */
@Service
public class StockInBatchServiceImpl  implements
		StockInBatchService {

	@Resource
	private StockInBatchMapper StockInBatchMapper;

	@Override
	public int insert(StockInBatch model) {
		return StockInBatchMapper.insert(model);
	}

	@Override
	public int update(StockInBatch model) {
		return StockInBatchMapper.updateByPrimaryKeySelective(model);
	}

	
	@Override
	public int delete(String serialNum) {
		return StockInBatchMapper.deleteByPrimaryKey(serialNum);
	}

	@Override
	public StockInBatch selectById(String serialNum) {
		return StockInBatchMapper.selectByPrimaryKey(serialNum);
	}

	@Override
	public StockInBatch selectOne() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StockInBatch> selectList() {
		return StockInBatchMapper.selectByExample(null);
	}

	

}