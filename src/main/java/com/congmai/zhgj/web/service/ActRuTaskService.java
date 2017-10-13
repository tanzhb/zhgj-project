package com.congmai.zhgj.web.service;

import org.springframework.stereotype.Service;

import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.ActRuTask;
import com.congmai.zhgj.web.model.User;

public interface ActRuTaskService extends GenericService<ActRuTask,String>{

	User getAuditUserByProcessInstanceId(String processInstanceId);

}
