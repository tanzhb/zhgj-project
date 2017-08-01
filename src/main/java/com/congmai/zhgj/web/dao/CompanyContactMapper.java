package com.congmai.zhgj.web.dao;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.web.model.CompanyContact;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface CompanyContactMapper extends GenericDao<CompanyContact, String>{
    //int countByExample(CompanyContactExample example);

    //int deleteByExample(CompanyContactExample example);

    int deleteByPrimaryKey(String serialNum);

    int insert(CompanyContact record);

    int insertSelective(CompanyContact record);

   // List<CompanyContact> selectByExample(CompanyContactExample example);

    CompanyContact selectByPrimaryKey(String serialNum);

    //int updateByExampleSelective(@Param("record") CompanyContact record, @Param("example") CompanyContactExample example);

    //int updateByExample(@Param("record") CompanyContact record, @Param("example") CompanyContactExample example);

    int updateByPrimaryKeySelective(CompanyContact record);

    int updateByPrimaryKey(CompanyContact record);
}