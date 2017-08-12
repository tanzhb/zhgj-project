package com.congmai.zhgj.web.dao;

import java.util.List;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.web.model.Company;

public interface CompanyMapper extends GenericDao<Company,String>{
	
	public List<Company> selectList(Company company);
	
	public Integer countList(Company company);
	
	public void deleteCompanyBatch(List<String> comIds);
	
	int insertSelectiveBatch(List<Company> list);
	
}