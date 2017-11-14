package com.congmai.zhgj.web.dao;

import com.congmai.zhgj.web.model.StockInbatch;
import com.congmai.zhgj.web.model.StockInbatchExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StockInbatchMapper {
    int countByExample(StockInbatchExample example);

    int deleteByExample(StockInbatchExample example);

    int deleteByPrimaryKey(String serialNum);

    int insert(StockInbatch record);

    int insertSelective(StockInbatch record);

    List<StockInbatch> selectByExample(StockInbatchExample example);

    StockInbatch selectByPrimaryKey(String serialNum);

    int updateByExampleSelective(@Param("record") StockInbatch record, @Param("example") StockInbatchExample example);

    int updateByExample(@Param("record") StockInbatch record, @Param("example") StockInbatchExample example);

    int updateByPrimaryKeySelective(StockInbatch record);

    int updateByPrimaryKey(StockInbatch record);
}