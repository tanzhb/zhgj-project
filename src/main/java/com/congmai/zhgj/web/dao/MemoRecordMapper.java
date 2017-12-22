package com.congmai.zhgj.web.dao;

import com.congmai.zhgj.web.model.MemoRecord;
import com.congmai.zhgj.web.model.MemoRecordExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface MemoRecordMapper {
    int countByExample(MemoRecordExample example);

    int deleteByExample(MemoRecordExample example);

    int deleteByPrimaryKey(String serialNum);

    int insert(MemoRecord record);

    int insertSelective(MemoRecord record);

    List<MemoRecord> selectByExample(MemoRecordExample example);

    MemoRecord selectByPrimaryKey(String serialNum);

    int updateByExampleSelective(@Param("record") MemoRecord record, @Param("example") MemoRecordExample example);

    int updateByExample(@Param("record") MemoRecord record, @Param("example") MemoRecordExample example);

    int updateByPrimaryKeySelective(MemoRecord record);

    int updateByPrimaryKey(MemoRecord record);
    
    
    /**
	 * 删除付/收款水单
	 * @param ids
	 */
    public void delMemoRecord(List<String> ids);
}