package com.congmai.zhgj.web.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.congmai.zhgj.web.dao.ProcessBaseMapper;
import com.congmai.zhgj.web.model.BaseVO;
import com.congmai.zhgj.web.model.HistoricTaskVO;
import com.congmai.zhgj.web.model.ProcessBase;
import com.congmai.zhgj.web.service.ProcessBaseService;

/**
 * 
 * @ClassName ProcessBaseServiceImpl
 * @Description TODO(流程字段相关)
 * @author qfzhao
 * @Date 2017年9月16日 下午2:35:13
 * @version 1.0.0
 */
@Service
public class ProcessBaseServiceImpl  implements
ProcessBaseService {

	@Resource
	private ProcessBaseMapper processBaseMapper;

	@Override
	public int insert(BaseVO model) {
		return processBaseMapper.insert(model);
	}

	@Override
	public int update(BaseVO model) {
		return processBaseMapper.updateByPrimaryKeySelective(model);
	}

	
	@Override
	public int delete(String serialNum) {
		return processBaseMapper.deleteByPrimaryKey(serialNum);
	}

	@Override
	public BaseVO selectById(String serialNum) {
		return null;
	}
	
	@Override
	public ProcessBase selectBaseById(String serialNum) {
		return processBaseMapper.selectByPrimaryKey(serialNum);
	}
	

	@Override
	public BaseVO selectOne() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BaseVO> selectList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HistoricTaskVO> findFinishedTaskInstancesDiy(Map map) {
		return processBaseMapper.findFinishedTaskInstancesDiy(map);
	}

	@Override
	public void insertHistoricTask(HistoricTaskVO historicTaskVO) {
		 processBaseMapper.insertHistoricTask(historicTaskVO);
	}

	@Override
	public void updateHistoricTask(HistoricTaskVO historicTaskVO) {
		 processBaseMapper.updateHistoricTask(historicTaskVO);
		
	}


}