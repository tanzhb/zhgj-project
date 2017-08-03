package com.congmai.zhgj.web.dao;

import java.util.List;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.web.model.CompanyQualification;

import org.apache.ibatis.annotations.Param;

public interface CompanyQualificationMapper extends GenericDao<CompanyQualification,String>{
    //int countByExample(CompanyQualificationExample example);
	
    //int deleteByExample(CompanyQualificationExample example);

    int deleteByPrimaryKey(String serialNum);

    int insert(CompanyQualification record);

    int insertSelective(CompanyQualification record);

    //List<CompanyQualification> selectByExample(CompanyQualificationExample example);

    CompanyQualification selectByPrimaryKey(String serialNum);

    //int updateByExampleSelective(@Param("record") CompanyQualification record, @Param("example") CompanyQualificationExample example);

    //int updateByExample(@Param("record") CompanyQualification record, @Param("example") CompanyQualificationExample example);

    int updateByPrimaryKeySelective(CompanyQualification record);

    int updateByPrimaryKey(CompanyQualification record);
    
    int insertSelectiveBatch(List<CompanyQualification> list);
    int updateSelectiveBatch(List<CompanyQualification> list);
    
    List<CompanyQualification>  selectListByCondition(CompanyQualification companyQualification);
}