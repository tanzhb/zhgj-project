package com.congmai.zhgj.web.dao;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.web.model.PriceList;
import com.congmai.zhgj.web.model.PriceListExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface PriceListMapper extends GenericDao<PriceList, String>{
    int countByExample(PriceListExample example);

    int deleteByExample(PriceListExample example);

    int deleteByPrimaryKey(String serialNum);

    int insert(PriceList record);

    int insertSelective(PriceList record);

    List<PriceList> selectByExample(PriceListExample example);

    PriceList selectByPrimaryKey(String serialNum);

    int updateByExampleSelective(@Param("record") PriceList record, @Param("example") PriceListExample example);

    int updateByExample(@Param("record") PriceList record, @Param("example") PriceListExample example);

    int updateByPrimaryKeySelective(PriceList record);

    int updateByPrimaryKey(PriceList record);
    int deletePriceList(List<String>serialNums);
    
}