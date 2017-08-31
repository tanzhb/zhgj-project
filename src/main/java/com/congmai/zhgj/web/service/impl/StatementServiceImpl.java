package com.congmai.zhgj.web.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.web.dao.StatementMapper;
import com.congmai.zhgj.web.model.Statement;
import com.congmai.zhgj.web.model.StatementExample;
import com.congmai.zhgj.web.service.StatementService;

/**
 * 
 * @ClassName StatementServiceImpl
 * @Description 对账单ServiceImpl
 * @author qfzhao
 * @Date 2017年7月28日 下午3:06:52
 * @version 1.0.0
 */
@Service
public class StatementServiceImpl implements StatementService {
    @Resource
    private StatementMapper StatementMapper;
    
	@Override
	public int insert(Statement model) {
		return StatementMapper.insert(model);
	}

	@Override
	public int update(Statement model) {
		return StatementMapper.updateByPrimaryKeySelective(model);
	}

	
	@Override
	public int delete(String serialNum) {
		return StatementMapper.deleteByPrimaryKey(serialNum);
	}

	@Override
	public Statement selectById(String serialNum) {
		return StatementMapper.selectByPrimaryKey(serialNum);
	}

	@Override
	public Statement selectOne() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Statement> selectList() {
		return StatementMapper.selectByExample(null);
	}

	@Override
	public List<Statement> selectList(StatementExample m) {
		return StatementMapper.selectByExample(m);
	}

	@Override
	public void deleteStatements(String ids) {
		List<String> idList = ApplicationUtils.getIdList(ids);
		for(String id : idList){
			Statement m = new Statement();
			m.setSerialNum(id);
			m.setDelFlg("1");
			m.setUpdateTime(new Date());
			StatementMapper.updateByPrimaryKeySelective(m);
		}

	}

}
