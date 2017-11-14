package com.congmai.zhgj.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.congmai.zhgj.web.dao.StockInbatchMapper;
import com.congmai.zhgj.web.model.StockInbatch;
import com.congmai.zhgj.web.service.StockInbatchService;

/**
 * 
 * @ClassName StockInbatchServiceImpl
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author qfzhao
 * @Date 2017年11月13日 下午2:28:21
 * @version 1.0.0
 */
@Service
public class StockInbatchServiceImpl  implements
		StockInbatchService {

	@Resource
	private StockInbatchMapper StockInbatchMapper;

	@Override
	public int insert(StockInbatch model) {
		return StockInbatchMapper.insert(model);
	}

	@Override
	public int update(StockInbatch model) {
		return StockInbatchMapper.updateByPrimaryKeySelective(model);
	}

	
	@Override
	public int delete(String serialNum) {
		return StockInbatchMapper.deleteByPrimaryKey(serialNum);
	}

	@Override
	public StockInbatch selectById(String serialNum) {
		return StockInbatchMapper.selectByPrimaryKey(serialNum);
	}

	@Override
	public StockInbatch selectOne() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StockInbatch> selectList() {
		return StockInbatchMapper.selectByExample(null);
	}

	

}