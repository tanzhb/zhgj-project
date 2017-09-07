package com.congmai.zhgj.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.web.model.Invoice;
import com.congmai.zhgj.web.model.InvoiceExample;

public interface InvoiceMapper  extends GenericDao<Invoice, String>{
    int countByExample(InvoiceExample example);

    int deleteByExample(InvoiceExample example);

    int deleteByPrimaryKey(String serialNum);

    int insert(Invoice record);

    int insertSelective(Invoice record);

    List<Invoice> selectByExample(InvoiceExample example);

    Invoice selectByPrimaryKey(String serialNum);

    int updateByExampleSelective(@Param("record") Invoice record, @Param("example") InvoiceExample example);

    int updateByExample(@Param("record") Invoice record, @Param("example") InvoiceExample example);

    int updateByPrimaryKeySelective(Invoice record);

    int updateByPrimaryKey(Invoice record);
    
    int deleteInvoice(List<String>serialNums);//批量删除发票信息
}