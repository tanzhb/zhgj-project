package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.CompanyFinance;

public interface CompanyFinanceService extends GenericService<CompanyFinance, String>{




	/**
	 * 
	 * @Description (根据id财务信息)
	 * @param id
	 * @return
	 */
	CompanyFinance selectOne(String id);
	
	List<CompanyFinance> selectListByComId(String comId);
}
