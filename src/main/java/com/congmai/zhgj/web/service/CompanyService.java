package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.feature.orm.mybatis.Page;
import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.Company;
import com.congmai.zhgj.web.model.CompanyExample;

public interface CompanyService extends GenericService<Company, String>{

	/**
	 * 
	 * @Description (分页获取企业信息)
	 * @param company
	 * @return
	 */
	Page<Company> selectByPage(Company company);

	/**
	 * 
	 * @Description (根据id查询企业信息)
	 * @param id
	 * @return
	 */
	Company selectOne(String id);

	/**
	 * 
	 * @Description (批量删除)
	 * @param comIdList
	 */
	void deleteBatch(List<String> comIdList);

	/**
	 * 批量插入
	 * @Description (TODO这里用一句话描述这个方法的作用)
	 * @param companyList
	 */
	void insertBatch(List<Company> companyList);

	/**
	 * 
	 * @Description (根据公司类型获取公司)
	 * @return
	 */
	List<Company> selectCompanyByComType(String comType,String searchKey);
	
	/**
	 * 
	 * @Description (根据公司名获取公司Id)
	 * @return
	 */
	String selectComIdByComName(String comName);
	
	
	int countCompanybySelective(CompanyExample example);
	
}
