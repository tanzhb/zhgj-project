package com.congmai.zhgj.web.dao;

import com.congmai.zhgj.web.model.ContractFile;
import com.congmai.zhgj.web.model.ContractFileExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ContractFileMapper {
    int countByExample(ContractFileExample example);

    int deleteByExample(ContractFileExample example);

    int deleteByPrimaryKey(String serialNum);

    int insert(ContractFile record);

    int insertSelective(ContractFile record);

    List<ContractFile> selectByExample(ContractFileExample example);

    ContractFile selectByPrimaryKey(String serialNum);

    int updateByExampleSelective(@Param("record") ContractFile record, @Param("example") ContractFileExample example);

    int updateByExample(@Param("record") ContractFile record, @Param("example") ContractFileExample example);

    int updateByPrimaryKeySelective(ContractFile record);

    int updateByPrimaryKey(ContractFile record);
}