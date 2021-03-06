package com.congmai.zhgj.web.dao;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.web.model.PaymentRecord;
import com.congmai.zhgj.web.model.PaymentRecordExample;
import com.congmai.zhgj.web.model.StatementPaymentInfo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface PaymentRecordMapper extends GenericDao<PaymentRecord, String> {
    int countByExample(PaymentRecordExample example);

    int deleteByExample(PaymentRecordExample example);

    int deleteByPrimaryKey(String serialNum);

    int insert(PaymentRecord record);

    int insertSelective(PaymentRecord record);

    List<PaymentRecord> selectByExample(PaymentRecordExample example);

    PaymentRecord selectByPrimaryKey(String serialNum);

    int updateByExampleSelective(@Param("record") PaymentRecord record, @Param("example") PaymentRecordExample example);

    int updateByExample(@Param("record") PaymentRecord record, @Param("example") PaymentRecordExample example);

    int updateByPrimaryKey(PaymentRecord record);

	List<StatementPaymentInfo> getAlreadyPaymentList(
			Map<String, String> alreadyPaymentMap);
}