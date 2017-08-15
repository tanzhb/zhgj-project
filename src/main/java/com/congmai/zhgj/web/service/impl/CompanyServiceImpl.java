package com.congmai.zhgj.web.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.congmai.zhgj.core.feature.orm.mybatis.Page;
import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.web.dao.CompanyContactMapper;
import com.congmai.zhgj.web.dao.CompanyFinanceMapper;
import com.congmai.zhgj.web.dao.CompanyMapper;
import com.congmai.zhgj.web.dao.CompanyQualificationMapper;
import com.congmai.zhgj.web.model.Company;
import com.congmai.zhgj.web.service.CompanyService;

@Service
public class CompanyServiceImpl extends GenericServiceImpl<Company, String> implements CompanyService{

	@Resource
	private CompanyMapper companyMapper;
	@Resource
	private CompanyContactMapper companyContactMapper;
	@Resource
	private CompanyFinanceMapper companyFinanceMapper;
	@Resource
	private CompanyQualificationMapper companyQualificationMapper;
	
	@Override
	public GenericDao<Company, String> getDao() {
		
		return companyMapper;
	}


	@Override
	public Page<Company> selectByPage(Company company) {
		List<Company> list =  null;
		Integer count = 0;
		if(company != null){
			list = companyMapper.selectList(company);
			count = companyMapper.countList(company);
		}
		Page<Company>  page = new Page<Company>();
		page.setResult(list);
		page.setTotalCount(count);
		return page;
	}

	@Override
	public Company selectOne(String id) {
		
		return companyMapper.selectByPrimaryKey(id);
	}


	@Override
	public void deleteBatch(List<String> comIdList) {
		companyMapper.deleteCompanyBatch(comIdList);
	}

	@Override
	public void insertBatch(List<Company> companyList) {
		companyMapper.insertSelectiveBatch(companyList);
	}


	@Override
	public List<Company> selectCompanyByComType(String comType,String searchKey) {
		Map<String,Object> map =new HashMap<String, Object>();
		map.put("comType", comType);
		if(StringUtils.isNotBlank(searchKey)){
			map.put("searchKey", searchKey);
		}else{
			map.put("searchKey", null);
		}
		
		return companyMapper.selectCompanyByComType(map);
	}



	
}
