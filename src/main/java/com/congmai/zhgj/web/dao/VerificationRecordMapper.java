package com.congmai.zhgj.web.dao;

import com.congmai.zhgj.web.model.VerificationRecord;
import com.congmai.zhgj.web.model.VerificationRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VerificationRecordMapper {
    int countByExample(VerificationRecordExample example);

    int deleteByExample(VerificationRecordExample example);

    int deleteByPrimaryKey(String serialNum);

    int insert(VerificationRecord record);

    int insertSelective(VerificationRecord record);

    List<VerificationRecord> selectByExample(VerificationRecordExample example);

    VerificationRecord selectByPrimaryKey(String serialNum);

    int updateByExampleSelective(@Param("record") VerificationRecord record, @Param("example") VerificationRecordExample example);

    int updateByExample(@Param("record") VerificationRecord record, @Param("example") VerificationRecordExample example);

    int updateByPrimaryKeySelective(VerificationRecord record);

    int updateByPrimaryKey(VerificationRecord record);
    
    List<VerificationRecord> selectByExampleForPaymentRecord(VerificationRecordExample example);
}