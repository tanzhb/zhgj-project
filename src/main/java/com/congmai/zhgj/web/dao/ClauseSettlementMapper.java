package com.congmai.zhgj.web.dao;

import com.congmai.zhgj.web.model.ClauseSettlement;
import com.congmai.zhgj.web.model.ClauseSettlementExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClauseSettlementMapper {
    int countByExample(ClauseSettlementExample example);

    int deleteByExample(ClauseSettlementExample example);

    int deleteByPrimaryKey(String serialNum);

    int insert(ClauseSettlement record);

    int insertSelective(ClauseSettlement record);

    List<ClauseSettlement> selectByExample(ClauseSettlementExample example);

    ClauseSettlement selectByPrimaryKey(String serialNum);

    int updateByExampleSelective(@Param("record") ClauseSettlement record, @Param("example") ClauseSettlementExample example);

    int updateByExample(@Param("record") ClauseSettlement record, @Param("example") ClauseSettlementExample example);

    int updateByPrimaryKeySelective(ClauseSettlement record);

    int updateByPrimaryKey(ClauseSettlement record);
}