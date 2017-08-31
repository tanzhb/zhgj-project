package com.congmai.zhgj.web.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.aop.support.AopUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.congmai.zhgj.core.feature.orm.mybatis.Page;
import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.core.util.DateUtil;
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
	public void insertBatch(List<CompanyQualification> insertList,String userId) {
	if(!CollectionUtils.isEmpty(insertList)){
		try {
			System.out.println("是否是代理调用，AopUtils.isAopProxy(this) : " + AopUtils.isAopProxy(this));  
	        System.out.println("是否是cglib类代理调用，AopUtils.isCglibProxy(this) : " + AopUtils.isCglibProxy(this));  
	        System.out.println("是否是jdk动态接口代理调用，AopUtils.isJdkDynamicProxy(this) : " + AopUtils.isJdkDynamicProxy(this));  
			companyQualificationMapper.deleteByComId(insertList.get(0).getComId());
			//String s=null;
			//s.split(",");
			for(CompanyQualification companyQualification:insertList){
	    			companyQualification.setSerialNum(UUID.randomUUID().toString().replace("-",""));
	    			companyQualification.setCreateTime(new Date());
	    			companyQualification.setCreator(userId);
	    			companyQualification.setUpdateTime(new Date());
	    			companyQualification.setUpdater(userId);
    		}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
			
		}
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
					insertList,"");
		}
		if(!CollectionUtils.isEmpty(updateList)){
			this.updateBatch(
					updateList);
		}
		
	}


	@Override
	public List<CompanyQualification> selectListByComId(String comId){
		CompanyQualification companyQualification = new CompanyQualification();
		companyQualification.setComId(comId);
		List<CompanyQualification> list = companyQualificationMapper.selectListByCondition(companyQualification);
		if(!CollectionUtils.isEmpty(list)){
			for(CompanyQualification qualification :list){
				if(qualification!=null){
					int count;
					try {
						count = DateUtil.daysBetween(new Date(), qualification.getValidityDate());
						if(count<30&&count>=0){
							qualification.setStatus("2");//即将过期
						}else if(count<0){
							qualification.setStatus("1");//已过期
						}else{
							qualification.setStatus("0");//正常
						}
					} catch (Exception e) {
						System.out.println(e.getMessage());
						qualification.setStatus("0");
					}
					
				}
			}
		}
		return companyQualificationMapper.selectListByCondition(companyQualification);
	}


	@Override
	public void deleteByComId(String comId) {
		if(!StringUtils.isEmpty(comId)){
			companyQualificationMapper.deleteByComId(comId);
		}
		
		
	}


}
