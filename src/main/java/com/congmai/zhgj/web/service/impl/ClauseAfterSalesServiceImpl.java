package com.congmai.zhgj.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.congmai.zhgj.web.dao.ClauseAfterSalesMapper;
import com.congmai.zhgj.web.model.ClauseAfterSales;
import com.congmai.zhgj.web.model.ClauseAfterSalesExample;
import com.congmai.zhgj.web.model.ClauseAfterSalesExample.Criteria;
import com.congmai.zhgj.web.service.ClauseAfterSalesService;

/**
 * 
 * @ClassName ClauseAfterSalesServiceImpl
 * @Description 售后条款接口实现类
 * @author qfzhao
 * @Date 2017年8月22日 下午4:07:04
 * @version 1.0.0
 */
@Service
public class ClauseAfterSalesServiceImpl  implements
		ClauseAfterSalesService {

	@Resource
	private ClauseAfterSalesMapper clauseAfterSalesMapper;

	@Override
	public int insert(ClauseAfterSales model) {
		return clauseAfterSalesMapper.insert(model);
	}

	@Override
	public int update(ClauseAfterSales model) {
		return clauseAfterSalesMapper.updateByPrimaryKeySelective(model);
	}

	
	@Override
	public int delete(String serialNum) {
		return clauseAfterSalesMapper.deleteByPrimaryKey(serialNum);
	}

	@Override
	public ClauseAfterSales selectById(String serialNum) {
		return clauseAfterSalesMapper.selectByPrimaryKey(serialNum);
	}

	@Override
	public ClauseAfterSales selectOne() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ClauseAfterSales> selectList() {
		return clauseAfterSalesMapper.selectByExample(null);
	}

	@Override
	public ClauseAfterSales selectByContractId(String id) {
		ClauseAfterSalesExample ex = new ClauseAfterSalesExample();
		Criteria criteria =  ex.createCriteria();
    	criteria.andContractSerialEqualTo(id);
		List<ClauseAfterSales> list = clauseAfterSalesMapper.selectByExample(ex);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}

}