package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.CompanyAddress;


public interface CompanyAddressService extends GenericService<CompanyAddress, String>{

	List<CompanyAddress> selectListByComId(String comId);

}
