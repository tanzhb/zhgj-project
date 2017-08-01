package com.congmai.zhgj.web.dao;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.web.model.Company;

import java.util.List;

public interface CompanyMapper extends GenericDao<Company,String>{
	
	public List<Company> selectList(Company company);
	
	public Integer countList(Company company);
	
}