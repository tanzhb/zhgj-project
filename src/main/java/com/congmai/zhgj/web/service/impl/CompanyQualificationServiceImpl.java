package com.congmai.zhgj.web.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.congmai.zhgj.core.feature.orm.mybatis.Page;
import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.web.dao.CompanyQualificationMapper;
import com.congmai.zhgj.web.model.Company;
import com.congmai.zhgj.web.model.CompanyQualification;
import com.congmai.zhgj.web.service.CompanyQualificationService;
@Service
public class CompanyQualificationServiceImpl extends GenericServiceImpl<CompanyQualification, String> implements
		CompanyQualificationService {

	@Resource
	private CompanyQualificationMapper companyQualificationMapper;
	
	@Override
	public GenericDao<CompanyQualification, String> getDao() {
		
		return this.companyQualificationMapper;
	}

	
	@Override
	public Page<Company> selectByPage(Company company) {
		
		return null;
	}

	@Override
	public Company selectOne(String id) {
		
		return null;
	}


	@Override
	public void insertBatch(List<CompanyQualification> insertList) {
		companyQualificationMapper.insertSelectiveBatch(insertList);
	}


	@Override
	public void updateBatch(List<CompanyQualification> updateList) {
		companyQualificationMapper.updateSelectiveBatch(updateList);
	}


	@Override
	public void insertOrUpdateBatch(
			List<CompanyQualification> companyQualifications) {
		List<CompanyQualification> insertList = new ArrayList<CompanyQualification>();
		List<CompanyQualification> updateList = new ArrayList<CompanyQualification>();
		if(!CollectionUtils.isEmpty(companyQualifications)){
			
			for(CompanyQualification companyQualification:companyQualifications){
				if(StringUtils.isEmpty(companyQualification.getSerialNum())){
					insertList.add(companyQualification);
	    			companyQualification.setSerialNum(UUID.randomUUID().toString().replace("-",""));
	    			companyQualification.setCreateTime(new Date());
	    			companyQualification.setCreator("user");
	    			companyQualification.setUpdateTime(new Date());
	    			companyQualification.setUpdater("user");
	    		}else{
	    			companyQualification.setUpdateTime(new Date());
	    			companyQualification.setUpdater("user");
	    			updateList.add(companyQualification);
	    		}
    		}
		}
		if(!CollectionUtils.isEmpty(insertList)){
			this.insertBatch(
					insertList);
		}
		if(!CollectionUtils.isEmpty(updateList)){
			this.updateBatch(
					updateList);
		}
		
	}


	@Override
	public List<CompanyQualification> selectListByComId(String comId) {
		CompanyQualification companyQualification = new CompanyQualification();
		companyQualification.setComId(comId);
		return companyQualificationMapper.selectListByCondition(companyQualification);
	}


}
