package com.congmai.zhgj.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.congmai.zhgj.web.dao.ClauseSettlementMapper;
import com.congmai.zhgj.web.model.ClauseSettlement;
import com.congmai.zhgj.web.model.ClauseSettlementExample;
import com.congmai.zhgj.web.model.ClauseSettlementExample.Criteria;
import com.congmai.zhgj.web.service.ClauseSettlementService;

/**
 * 
 * @ClassName ClauseSettlementServiceImpl
 * @Description 结算条款接口实现类
 * @author qfzhao
 * @Date 2017年8月22日 下午4:07:04
 * @version 1.0.0
 */
@Service
public class ClauseSettlementServiceImpl  implements
		ClauseSettlementService {

	@Resource
	private ClauseSettlementMapper clauseSettlementMapper;

	@Override
	public int insert(ClauseSettlement model) {
		return clauseSettlementMapper.insert(model);
	}

	@Override
	public int update(ClauseSettlement model) {
		return clauseSettlementMapper.updateByPrimaryKeySelective(model);
	}

	
	@Override
	public int delete(String serialNum) {
		return clauseSettlementMapper.deleteByPrimaryKey(serialNum);
	}

	@Override
	public ClauseSettlement selectById(String serialNum) {
		return clauseSettlementMapper.selectByPrimaryKey(serialNum);
	}

	@Override
	public ClauseSettlement selectOne() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ClauseSettlement> selectList() {
		return clauseSettlementMapper.selectByExample(null);
	}

	@Override
	public ClauseSettlement selectByContractId(String id) {
		ClauseSettlementExample ex = new ClauseSettlementExample();
		Criteria criteria =  ex.createCriteria();
    	criteria.andContractSerialEqualTo(id);

		List<ClauseSettlement> list = clauseSettlementMapper.selectByExample(ex);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}

}