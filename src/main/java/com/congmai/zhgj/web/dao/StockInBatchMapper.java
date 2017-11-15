package com.congmai.zhgj.web.dao;

import com.congmai.zhgj.web.model.StockInBatch;
import com.congmai.zhgj.web.model.StockInBatchExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StockInBatchMapper {
    int countByExample(StockInBatchExample example);

    int deleteByExample(StockInBatchExample example);

    int deleteByPrimaryKey(String serialNum);

    int insert(StockInBatch record);

    int insertSelective(StockInBatch record);

    List<StockInBatch> selectByExample(StockInBatchExample example);

    StockInBatch selectByPrimaryKey(String serialNum);

    int updateByExampleSelective(@Param("record") StockInBatch record, @Param("example") StockInBatchExample example);

    int updateByExample(@Param("record") StockInBatch record, @Param("example") StockInBatchExample example);

    int updateByPrimaryKeySelective(StockInBatch record);

    int updateByPrimaryKey(StockInBatch record);
}