package com.congmai.zhgj.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.congmai.zhgj.web.dao.ClauseCheckAcceptMapper;
import com.congmai.zhgj.web.model.ClauseCheckAccept;
import com.congmai.zhgj.web.model.ClauseCheckAcceptExample.Criteria;
import com.congmai.zhgj.web.model.ClauseCheckAcceptExample;
import com.congmai.zhgj.web.service.ClauseCheckAcceptService;

/**
 * 
 * @ClassName ClauseCheckAcceptServiceImpl
 * @Description 垫资条款接口实现类
 * @author qfzhao
 * @Date 2017年8月22日 下午4:07:04
 * @version 1.0.0
 */
@Service
public class ClauseCheckAcceptServiceImpl  implements
		ClauseCheckAcceptService {

	@Resource
	private ClauseCheckAcceptMapper clauseCheckAcceptMapper;

	@Override
	public int insert(ClauseCheckAccept model) {
		return clauseCheckAcceptMapper.insert(model);
	}

	@Override
	public int update(ClauseCheckAccept model) {
		return clauseCheckAcceptMapper.updateByPrimaryKeySelective(model);
	}

	
	@Override
	public int delete(String serialNum) {
		return clauseCheckAcceptMapper.deleteByPrimaryKey(serialNum);
	}

	@Override
	public ClauseCheckAccept selectById(String serialNum) {
		return clauseCheckAcceptMapper.selectByPrimaryKey(serialNum);
	}

	@Override
	public ClauseCheckAccept selectOne() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ClauseCheckAccept> selectList() {
		return clauseCheckAcceptMapper.selectByExample(null);
	}

	@Override
	public ClauseCheckAccept selectByContractId(String id) {
		ClauseCheckAcceptExample ex = new ClauseCheckAcceptExample();
		Criteria criteria =  ex.createCriteria();
    	criteria.andContractSerialEqualTo(id);
		List<ClauseCheckAccept> list = clauseCheckAcceptMapper.selectByExample(ex);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}

}