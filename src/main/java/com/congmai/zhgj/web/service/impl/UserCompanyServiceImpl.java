package com.congmai.zhgj.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.web.dao.CompanyMapper;
import com.congmai.zhgj.web.dao.UserCompanyMapper;
import com.congmai.zhgj.web.model.Company;
import com.congmai.zhgj.web.model.User;
import com.congmai.zhgj.web.model.UserCompanyExample;
import com.congmai.zhgj.web.model.UserCompanyKey;
import com.congmai.zhgj.web.service.UserCompanyService;
import com.sun.org.apache.regexp.internal.recompile;
@Service
public class UserCompanyServiceImpl extends GenericServiceImpl<UserCompanyKey, String>
		implements UserCompanyService{
	
	@Autowired
	private UserCompanyMapper userCompanyMapper;
	
	@Autowired
	private CompanyMapper companyMapper;

	@Override
	public GenericDao<UserCompanyKey, String> getDao() {
		
		return this.userCompanyMapper;
	}
	
	
	@Override
	public String getUserComId(String user_id) {//这里加上特殊的平台com_id作为判断
		String comId=userCompanyMapper.selectComIdByUserId(user_id);
		if("daf3e5685cae4bf883f2ec8237a0c8ce".equals(comId)){
			comId=null;
		}
		return  comId;
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


	@Override
	public String getUserComType(String user_id) {
		try{
			String comId = getUserComId(user_id);
			return companyMapper.selectByPrimaryKey(comId).getComType();
		}catch(Exception e){
			
		}
	
		return null;
	}


	@Override
	public List<UserCompanyKey> getUsersByComId(String comId) {
		UserCompanyExample example = new UserCompanyExample();
		example.createCriteria().andCom_idEqualTo(comId);
		List<UserCompanyKey> list = userCompanyMapper.selectByExample(example);
		return list;
	}


	@Override
	public Company getCompany(String userId) {
		try{
			String comId = getUserComId(userId);
			return companyMapper.selectByPrimaryKey(comId);
		}catch(Exception e){
			
		}
		return null;
	}

}
