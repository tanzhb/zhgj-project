package com.congmai.zhgj.web.dao;

import com.congmai.zhgj.web.model.InvoiceBillingRecord;
import com.congmai.zhgj.web.model.InvoiceBillingRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InvoiceBillingRecordMapper {
    int countByExample(InvoiceBillingRecordExample example);

    int deleteByExample(InvoiceBillingRecordExample example);

    int deleteByPrimaryKey(String serialNum);

    int insert(InvoiceBillingRecord record);

    int insertSelective(InvoiceBillingRecord record);

    List<InvoiceBillingRecord> selectByExample(InvoiceBillingRecordExample example);

    InvoiceBillingRecord selectByPrimaryKey(String serialNum);

    int updateByExampleSelective(@Param("record") InvoiceBillingRecord record, @Param("example") InvoiceBillingRecordExample example);

    int updateByExample(@Param("record") InvoiceBillingRecord record, @Param("example") InvoiceBillingRecordExample example);

    int updateByPrimaryKeySelective(InvoiceBillingRecord record);

    int updateByPrimaryKey(InvoiceBillingRecord record);
}