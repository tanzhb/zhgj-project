package com.congmai.zhgj.web.dao;

import java.util.List;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.web.model.UserTask;

/**
 * @author tanzb
 * @version 2017年8月9日 下午10:22:09
 */
public interface UserTaskMapper extends GenericDao<UserTask, Integer>{
	
	public List<UserTask> findByWhere(String procDefKey) throws Exception;
	
	public List<UserTask> getAll() throws Exception;
}
