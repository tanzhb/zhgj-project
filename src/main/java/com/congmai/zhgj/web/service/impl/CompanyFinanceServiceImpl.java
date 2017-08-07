package com.congmai.zhgj.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.web.dao.CompanyFinanceMapper;
import com.congmai.zhgj.web.model.CompanyFinance;
import com.congmai.zhgj.web.service.CompanyFinanceService;
@Service
public class CompanyFinanceServiceImpl extends GenericServiceImpl<CompanyFinance, String> implements
		CompanyFinanceService {

	@Resource
	private CompanyFinanceMapper companyFinanceMapper;
	
	@Override
	public GenericDao<CompanyFinance, String> getDao() {
		
		return this.companyFinanceMapper;
	}


	@Override
	public CompanyFinance selectOne(String id) {
		
		return null;
	}


	@Override
	public List<CompanyFinance> selectListByComId(
			String comId) {
		
		CompanyFinance companyFinance =new CompanyFinance();
		companyFinance.setComId(comId);
		return this.companyFinanceMapper.selectListByCondition(companyFinance);
	}



}
