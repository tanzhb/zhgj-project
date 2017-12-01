package com.congmai.zhgj.web.dao;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.web.model.LadderPrice;
import com.congmai.zhgj.web.model.PriceCom;
import com.congmai.zhgj.web.model.PriceComExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface PriceComMapper extends GenericDao<PriceCom, String>{
    int countByExample(PriceComExample example);

    int deleteByExample(PriceComExample example);

    int deleteByPrimaryKey(String serialNum);

    int insert(PriceCom record);

    int insertSelective(PriceCom record);

    List<PriceCom> selectByExample(PriceComExample example);

    PriceCom selectByPrimaryKey(String serialNum);

    int updateByExampleSelective(@Param("record") PriceCom record, @Param("example") PriceComExample example);

    int updateByExample(@Param("record") PriceCom record, @Param("example") PriceComExample example);

    int updateByPrimaryKeySelective(PriceCom record);

    int updateByPrimaryKey(PriceCom record);
    
    int insertPriceComList(List<PriceCom> list);
}