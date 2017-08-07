package com.congmai.zhgj.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.web.dao.CompanyContactMapper;
import com.congmai.zhgj.web.model.CompanyContact;
import com.congmai.zhgj.web.service.CompanyContactService;
@Service
public class CompanyContactServiceImpl extends GenericServiceImpl<CompanyContact, String> implements
		CompanyContactService {

	@Resource
	private CompanyContactMapper companyContactMapper;
	
	@Override
	public GenericDao<CompanyContact, String> getDao() {
		
		return this.companyContactMapper;
	}


	@Override
	public CompanyContact selectOne(String id) {
		
		return null;
	}


	@Override
	public List<CompanyContact> selectListByComId(String comId) {
		CompanyContact contact = new CompanyContact();
		contact.setComId(comId);
		return this.companyContactMapper.selectListByCondition(contact);
	}



}
