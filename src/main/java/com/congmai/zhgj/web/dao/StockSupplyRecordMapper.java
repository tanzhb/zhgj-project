package com.congmai.zhgj.web.dao;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.web.model.Stock;
import com.congmai.zhgj.web.model.StockSupplyRecord;
import com.congmai.zhgj.web.model.StockSupplyRecordExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface StockSupplyRecordMapper  extends GenericDao<StockSupplyRecord,String> {
    int countByExample(StockSupplyRecordExample example);

    int deleteByExample(StockSupplyRecordExample example);

    int deleteByPrimaryKey(String serialNum);

    int insert(StockSupplyRecord record);

    int insertSelective(StockSupplyRecord record);

    List<StockSupplyRecord> selectByExample(StockSupplyRecordExample example);

    StockSupplyRecord selectByPrimaryKey(String serialNum);

    int updateByExampleSelective(@Param("record") StockSupplyRecord record, @Param("example") StockSupplyRecordExample example);

    int updateByExample(@Param("record") StockSupplyRecord record, @Param("example") StockSupplyRecordExample example);

    int updateByPrimaryKeySelective(StockSupplyRecord record);

    int updateByPrimaryKey(StockSupplyRecord record);
    
    List<StockSupplyRecord> getStockSupplyRecordBySupplyComId(String comId);
}