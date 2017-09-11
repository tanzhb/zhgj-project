package com.congmai.zhgj.web.service;

import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.UserCompanyKey;

public interface UserCompanyService extends GenericService<UserCompanyKey, String>{
	
	/**
	 * 
	 * @Description (TODO根据user_id获取com_id)
	 * @param user
	 * @return
	 */
	public String getUserComId(String user);
}
