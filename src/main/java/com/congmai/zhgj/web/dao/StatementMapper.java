package com.congmai.zhgj.web.dao;

import com.congmai.zhgj.web.model.Statement;
import com.congmai.zhgj.web.model.StatementExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StatementMapper {
    int countByExample(StatementExample example);

    int deleteByExample(StatementExample example);

    int deleteByPrimaryKey(String serialNum);

    int insert(Statement record);

    int insertSelective(Statement record);

    List<Statement> selectByExample(StatementExample example);

    Statement selectByPrimaryKey(String serialNum);

    int updateByExampleSelective(@Param("record") Statement record, @Param("example") StatementExample example);

    int updateByExample(@Param("record") Statement record, @Param("example") StatementExample example);

    int updateByPrimaryKeySelective(Statement record);

    int updateByPrimaryKey(Statement record);
}