package com.congmai.zhgj.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.web.dao.UserCompanyMapper;
import com.congmai.zhgj.web.model.UserCompanyExample;
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


	@Override
	public List<String> getComIdsByUserId(String user_id) {
		List<String> list = null;
		UserCompanyExample example = new UserCompanyExample(); 
		example.createCriteria().andUser_idEqualTo(user_id);
		List<UserCompanyKey> companys = userCompanyMapper.selectByExample(example);
		if(CollectionUtils.isNotEmpty(companys)){
			list = new ArrayList<String>();
			for(UserCompanyKey companyKey : companys){
				list.add(companyKey.getCom_id());
			}
		}
		return list;
	}

}
