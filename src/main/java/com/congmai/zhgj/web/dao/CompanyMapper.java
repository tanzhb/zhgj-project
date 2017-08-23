package com.congmai.zhgj.web.dao;

import java.util.List;
import java.util.Map;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.web.model.Company;
import com.congmai.zhgj.web.model.CompanyExample;

public interface CompanyMapper extends GenericDao<Company,String>{
	
	public List<Company> selectList(Company company);
	
	public Integer countList(Company company);
	
	public void deleteCompanyBatch(List<String> comIds);
	
	int insertSelectiveBatch(List<Company> list);
	
	List<Company> selectCompanyByComType(Map<String,Object> map);
	
	List<Company> selectByExample(CompanyExample example);
	
	 int countByExample(CompanyExample example);
	
}