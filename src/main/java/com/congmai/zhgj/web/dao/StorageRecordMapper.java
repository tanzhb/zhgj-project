package com.congmai.zhgj.web.dao;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.web.model.StorageRecord;
import com.congmai.zhgj.web.model.StorageRecordExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface StorageRecordMapper extends GenericDao<StorageRecord,String> {
    int countByExample(StorageRecordExample example);

    int deleteByExample(StorageRecordExample example);

    int deleteByPrimaryKey(String serialNum);

    int insert(StorageRecord record);

    int insertSelective(StorageRecord record);

    List<StorageRecord> selectByExample(StorageRecordExample example);

    StorageRecord selectByPrimaryKey(String serialNum);

    int updateByExampleSelective(@Param("record") StorageRecord record, @Param("example") StorageRecordExample example);

    int updateByExample(@Param("record") StorageRecord record, @Param("example") StorageRecordExample example);

    int updateByPrimaryKeySelective(StorageRecord record);

    int updateByPrimaryKey(StorageRecord record);
}