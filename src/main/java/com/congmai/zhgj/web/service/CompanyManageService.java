package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.CompanyManage;


public interface CompanyManageService extends GenericService<CompanyManage, String>{

	List<CompanyManage> selectListByComId(String comId);
	void deleteByComId(String comId);

}
