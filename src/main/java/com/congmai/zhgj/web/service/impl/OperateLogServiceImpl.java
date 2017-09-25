package com.congmai.zhgj.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.congmai.zhgj.web.dao.OperateLogMapper;
import com.congmai.zhgj.web.model.OperateLog;
import com.congmai.zhgj.web.service.OperateLogService;

/**
 * 
 * @ClassName OperateLogServiceImpl
 * @Description 操作日志接口实现类
 * @author qfzhao
 * @Date 2017年9月23日 下午2:28:06
 * @version 1.0.0
 */
@Service
public class OperateLogServiceImpl  implements
OperateLogService {

	@Resource
	private OperateLogMapper operateLogMapper;

	@Override
	public int insert(OperateLog model) {
		return operateLogMapper.insert(model);
	}

	@Override
	public int update(OperateLog model) {
		return operateLogMapper.updateByPrimaryKeySelective(model);
	}

	
	@Override
	public int delete(String serialNum) {
		return operateLogMapper.deleteByPrimaryKey(serialNum);
	}

	@Override
	public OperateLog selectById(String serialNum) {
		return operateLogMapper.selectByPrimaryKey(serialNum);
	}

	@Override
	public OperateLog selectOne() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OperateLog> selectList() {
		return operateLogMapper.selectByExample(null);
	}


}