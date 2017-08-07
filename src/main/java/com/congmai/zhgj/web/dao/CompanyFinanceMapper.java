package com.congmai.zhgj.web.dao;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.web.model.CompanyFinance;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface CompanyFinanceMapper extends GenericDao<CompanyFinance, String>{
   // int countByExample(CompanyFinanceExample example);

    //int deleteByExample(CompanyFinanceExample example);

    int deleteByPrimaryKey(String serialNum);

    int insert(CompanyFinance record);

    int insertSelective(CompanyFinance record);

    //List<CompanyFinance> selectByExample(CompanyFinanceExample example);

    CompanyFinance selectByPrimaryKey(String serialNum);

    //int updateByExampleSelective(@Param("record") CompanyFinance record, @Param("example") CompanyFinanceExample example);

    //int updateByExample(@Param("record") CompanyFinance record, @Param("example") CompanyFinanceExample example);

    int updateByPrimaryKeySelective(CompanyFinance record);

    int updateByPrimaryKey(CompanyFinance record);
    
    List<CompanyFinance> selectListByCondition(CompanyFinance companyFinance);
}