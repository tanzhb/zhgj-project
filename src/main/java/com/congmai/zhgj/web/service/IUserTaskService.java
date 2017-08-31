package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.UserTask;

public interface IUserTaskService extends GenericService<UserTask, Integer> {

	public int doAdd(UserTask userTask) throws Exception;
	
	public void doUpdate(UserTask userTask) throws Exception;
	
	public List<UserTask> findByWhere(String procDefKey) throws Exception;
	
	public UserTask findById(Integer id) throws Exception;
	
	public List<UserTask> getAll() throws Exception;
	
}
