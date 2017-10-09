package com.congmai.zhgj.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.web.dao.CompanyAddressMapper;
import com.congmai.zhgj.web.model.CompanyAddress;
import com.congmai.zhgj.web.model.CompanyAddressExample;
import com.congmai.zhgj.web.service.CompanyAddressService;
@Service
public class CompanyAddressServiceImpl extends GenericServiceImpl<CompanyAddress, String>
		implements CompanyAddressService{

	@Autowired
	private CompanyAddressMapper companyAddressMapper;
	
	@Override
	public GenericDao<CompanyAddress, String> getDao() {
		
		return this.companyAddressMapper;
	}

	@Override
	public List<CompanyAddress> selectListByComId(String comId) {
		CompanyAddressExample example = new CompanyAddressExample();
		example.createCriteria().andComIdEqualTo(comId).andDelFlgEqualTo("0");
		
		return companyAddressMapper.selectByExample(example);
	}
}
