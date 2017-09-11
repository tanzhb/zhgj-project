package com.congmai.zhgj.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.web.dao.UserCompanyMapper;
import com.congmai.zhgj.web.model.UserCompanyKey;
import com.congmai.zhgj.web.service.UserCompanyService;
@Service
public class UserCompanyServiceImpl extends GenericServiceImpl<UserCompanyKey, String>
		implements UserCompanyService{
	
	@Autowired
	private UserCompanyMapper userCompanyMapper;

	@Override
	public GenericDao<UserCompanyKey, String> getDao() {
		
		return this.userCompanyMapper;
	}
	
	
	@Override
	public String getUserComId(String user_id) {
		return userCompanyMapper.selectComIdByUserId(user_id);
	}

}
