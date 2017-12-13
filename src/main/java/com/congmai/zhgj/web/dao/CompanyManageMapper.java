package com.congmai.zhgj.web.dao;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.web.model.CompanyFinance;
import com.congmai.zhgj.web.model.CompanyManage;
import com.congmai.zhgj.web.model.CompanyManageExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface CompanyManageMapper extends GenericDao<CompanyManage, String>{
    int countByExample(CompanyManageExample example);

    int deleteByExample(CompanyManageExample example);

    int deleteByPrimaryKey(String serialNum);

    int insert(CompanyManage record);

    int insertSelective(CompanyManage record);

    List<CompanyManage> selectByExample(CompanyManageExample example);

    CompanyManage selectByPrimaryKey(String serialNum);

    int updateByExampleSelective(@Param("record") CompanyManage record, @Param("example") CompanyManageExample example);

    int updateByExample(@Param("record") CompanyManage record, @Param("example") CompanyManageExample example);

    int updateByPrimaryKeySelective(CompanyManage record);

    int updateByPrimaryKey(CompanyManage record);
}