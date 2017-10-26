package com.congmai.zhgj.web.dao;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.web.model.CustomsForm;
import com.congmai.zhgj.web.model.CustomsFormExample;
import com.congmai.zhgj.web.model.Stock;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface CustomsFormMapper  extends GenericDao<CustomsForm,String> {
    int countByExample(CustomsFormExample example);

    int deleteByExample(CustomsFormExample example);

    int deleteByPrimaryKey(String serialNum);

    int insert(CustomsForm record);

    int insertSelective(CustomsForm record);

    List<CustomsForm> selectByExample(CustomsFormExample example);

    CustomsForm selectByPrimaryKey(String serialNum);

    int updateByExampleSelective(@Param("record") CustomsForm record, @Param("example") CustomsFormExample example);

    int updateByExample(@Param("record") CustomsForm record, @Param("example") CustomsFormExample example);

    int updateByPrimaryKeySelective(CustomsForm record);

    int updateByPrimaryKey(CustomsForm record);
    
    int deleteCustomsForm(List<String>serialNums);
}