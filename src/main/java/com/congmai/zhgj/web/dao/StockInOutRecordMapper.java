package com.congmai.zhgj.web.dao;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.web.model.StockInOutRecord;
import com.congmai.zhgj.web.model.StockInOutRecordExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface StockInOutRecordMapper  extends GenericDao<StockInOutRecord,String> {
    int countByExample(StockInOutRecordExample example);

    int deleteByExample(StockInOutRecordExample example);

    int deleteByPrimaryKey(String serialNum);

    int insert(StockInOutRecord record);

    int insertSelective(StockInOutRecord record);

    List<StockInOutRecord> selectByExample(StockInOutRecordExample example);

    StockInOutRecord selectByPrimaryKey(String serialNum);

    int updateByExampleSelective(@Param("record") StockInOutRecord record, @Param("example") StockInOutRecordExample example);

    int updateByExample(@Param("record") StockInOutRecord record, @Param("example") StockInOutRecordExample example);

    int updateByPrimaryKeySelective(StockInOutRecord record);

    int updateByPrimaryKey(StockInOutRecord record);
    int deleteStockInOutRecord(List<String>serialNums);//批量删除出入库记录信息
}