package com.congmai.zhgj.web.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.web.dao.ActRuTaskMapper;
import com.congmai.zhgj.web.dao.UserMapper;
import com.congmai.zhgj.web.model.ActRuTask;
import com.congmai.zhgj.web.model.ActRuTaskExample;
import com.congmai.zhgj.web.model.User;
import com.congmai.zhgj.web.service.ActRuTaskService;
@Service
public class ActRuTaskServiceImpl extends GenericServiceImpl<ActRuTask, String>
		implements ActRuTaskService {

	@Autowired
	private ActRuTaskMapper actRuTaskMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public GenericDao<ActRuTask, String> getDao() {
		
		return this.actRuTaskMapper;
	}

	@Override
	public User getAuditUserByProcessInstanceId(String processInstanceId) {
		ActRuTaskExample example = new ActRuTaskExample();
		example.createCriteria().andProcInstIdEqualTo(processInstanceId);
		List<ActRuTask> actRuTaskList = actRuTaskMapper.selectByExample(example);
		if(CollectionUtils.isNotEmpty(actRuTaskList)&&actRuTaskList.size()==1){
			ActRuTask actRuTask = actRuTaskList.get(0);
			if(actRuTask != null&&actRuTask.getAssignee()!=null){
				return userMapper.selectByPrimaryKey(Integer.valueOf(actRuTask.getAssignee()));
			}
		}
		return null;
	}

}
