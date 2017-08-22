package com.congmai.zhgj.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.congmai.zhgj.web.dao.ClauseAdvanceMapper;
import com.congmai.zhgj.web.model.ClauseAdvance;
import com.congmai.zhgj.web.model.ClauseAdvanceExample;
import com.congmai.zhgj.web.model.ClauseAdvanceExample.Criteria;
import com.congmai.zhgj.web.service.ClauseAdvanceService;

/**
 * 
 * @ClassName ClauseAdvanceServiceImpl
 * @Description 垫资条款接口实现类
 * @author qfzhao
 * @Date 2017年8月22日 下午4:07:04
 * @version 1.0.0
 */
@Service
public class ClauseAdvanceServiceImpl  implements
		ClauseAdvanceService {

	@Resource
	private ClauseAdvanceMapper clauseAdvanceMapper;

	@Override
	public int insert(ClauseAdvance model) {
		return clauseAdvanceMapper.insert(model);
	}

	@Override
	public int update(ClauseAdvance model) {
		return clauseAdvanceMapper.updateByPrimaryKeySelective(model);
	}

	
	@Override
	public int delete(String serialNum) {
		return clauseAdvanceMapper.deleteByPrimaryKey(serialNum);
	}

	@Override
	public ClauseAdvance selectById(String serialNum) {
		return clauseAdvanceMapper.selectByPrimaryKey(serialNum);
	}

	@Override
	public ClauseAdvance selectOne() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ClauseAdvance> selectList() {
		return clauseAdvanceMapper.selectByExample(null);
	}

	@Override
	public ClauseAdvance selectByContractId(String id) {
		ClauseAdvanceExample ex = new ClauseAdvanceExample();
		Criteria criteria =  ex.createCriteria();
    	criteria.andContractSerialEqualTo(id);
		List<ClauseAdvance> list = clauseAdvanceMapper.selectByExample(ex);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}

}