package com.congmai.zhgj.web.dao;

import com.congmai.zhgj.web.model.CompanyCode;
import com.congmai.zhgj.web.model.CompanyCodeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CompanyCodeMapper {
    int countByExample(CompanyCodeExample example);

    int deleteByExample(CompanyCodeExample example);

    int insert(CompanyCode record);

    int insertSelective(CompanyCode record);

    List<CompanyCode> selectByExample(CompanyCodeExample example);

    int updateByExampleSelective(@Param("record") CompanyCode record, @Param("example") CompanyCodeExample example);

    int updateByExample(@Param("record") CompanyCode record, @Param("example") CompanyCodeExample example);
}