package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.feature.orm.mybatis.Page;
import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.Company;
import com.congmai.zhgj.web.model.CompanyQualification;

public interface CompanyQualificationService extends GenericService<CompanyQualification, String>{


	/**
	 * 
	 * @Description (分页获取企业资质信息)
	 * @param company
	 * @return
	 */
	Page<Company> selectByPage(Company company);

	/**
	 * 
	 * @Description (根据id查询企业资质信息)
	 * @param id
	 * @return
	 */
	Company selectOne(String id);

	void insertBatch(List<CompanyQualification> insertList,String userId);

	void updateBatch(List<CompanyQualification> updateList);

	void insertOrUpdateBatch(List<CompanyQualification> companyQualifications);

	List<CompanyQualification> selectListByComId(String comId);

	void deleteByComId(String comId);
}
