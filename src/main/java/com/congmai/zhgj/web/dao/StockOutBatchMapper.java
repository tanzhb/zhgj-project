package com.congmai.zhgj.web.dao;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.web.model.Company;
import com.congmai.zhgj.web.model.StockOutBatch;
import com.congmai.zhgj.web.model.StockOutBatchExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface StockOutBatchMapper  extends GenericDao<StockOutBatch,String> {
    int countByExample(StockOutBatchExample example);

    int deleteByExample(StockOutBatchExample example);

    int deleteByPrimaryKey(String serialNum);

    int insert(StockOutBatch record);

    int insertSelective(StockOutBatch record);

    List<StockOutBatch> selectByExample(StockOutBatchExample example);

    StockOutBatch selectByPrimaryKey(String serialNum);

    int updateByExampleSelective(@Param("record") StockOutBatch record, @Param("example") StockOutBatchExample example);

    int updateByExample(@Param("record") StockOutBatch record, @Param("example") StockOutBatchExample example);

    int updateByPrimaryKeySelective(StockOutBatch record);

    int updateByPrimaryKey(StockOutBatch record);
}