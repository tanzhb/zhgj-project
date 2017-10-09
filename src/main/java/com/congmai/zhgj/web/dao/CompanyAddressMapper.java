package com.congmai.zhgj.web.dao;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.web.model.CompanyAddress;
import com.congmai.zhgj.web.model.CompanyAddressExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface CompanyAddressMapper extends GenericDao<CompanyAddress, String>{
    int countByExample(CompanyAddressExample example);

    int deleteByExample(CompanyAddressExample example);

    int deleteByPrimaryKey(String serialNum);

    int insert(CompanyAddress record);

    int insertSelective(CompanyAddress record);

    List<CompanyAddress> selectByExample(CompanyAddressExample example);

    CompanyAddress selectByPrimaryKey(String serialNum);

    int updateByExampleSelective(@Param("record") CompanyAddress record, @Param("example") CompanyAddressExample example);

    int updateByExample(@Param("record") CompanyAddress record, @Param("example") CompanyAddressExample example);

    int updateByPrimaryKeySelective(CompanyAddress record);

    int updateByPrimaryKey(CompanyAddress record);
}