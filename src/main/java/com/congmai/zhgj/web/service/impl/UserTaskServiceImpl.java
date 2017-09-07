package com.congmai.zhgj.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.web.dao.UserTaskMapper;
import com.congmai.zhgj.web.model.UserTask;
import com.congmai.zhgj.web.service.IUserTaskService;

@Service
public class UserTaskServiceImpl extends GenericServiceImpl<UserTask, Integer> implements IUserTaskService {

	@Resource
	private UserTaskMapper userTaskMapper;
	
	@Override
	public GenericDao<UserTask, Integer> getDao() {
		return userTaskMapper;
	}
	
	@Override
	public int doAdd(UserTask userTask) throws Exception {
		return userTaskMapper.insertSelective(userTask);
	}

	@Override
	public void doUpdate(UserTask userTask) throws Exception {
		userTaskMapper.updateByPrimaryKeySelective(userTask);
	}
	@Override
	public List<UserTask> findByWhere(String procDefKey) throws Exception {
		List<UserTask> list = userTaskMapper.findByWhere(procDefKey);
		return list;
	}

	@Override
	public UserTask findById(Integer id) throws Exception {
		return userTaskMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<UserTask> getAll() throws Exception {
		return userTaskMapper.getAll();
	}

}
