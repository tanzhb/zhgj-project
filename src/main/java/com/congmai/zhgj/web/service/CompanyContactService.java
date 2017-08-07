package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.CompanyContact;

public interface CompanyContactService extends GenericService<CompanyContact, String>{




	/**
	 * 
	 * @Description (根据id查询企业资质信息)
	 * @param id
	 * @return
	 */
	CompanyContact selectOne(String id);

	List<CompanyContact> selectListByComId(String comId);
}
