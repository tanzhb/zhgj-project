package com.congmai.zhgj.web.dao;

import com.congmai.zhgj.web.model.ClauseSettlementDetail;
import com.congmai.zhgj.web.model.ClauseSettlementDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClauseSettlementDetailMapper {
    int countByExample(ClauseSettlementDetailExample example);

    int deleteByExample(ClauseSettlementDetailExample example);

    int deleteByPrimaryKey(String serialNum);

    int insert(ClauseSettlementDetail record);

    int insertSelective(ClauseSettlementDetail record);

    List<ClauseSettlementDetail> selectByExample(ClauseSettlementDetailExample example);

    ClauseSettlementDetail selectByPrimaryKey(String serialNum);

    int updateByExampleSelective(@Param("record") ClauseSettlementDetail record, @Param("example") ClauseSettlementDetailExample example);

    int updateByExample(@Param("record") ClauseSettlementDetail record, @Param("example") ClauseSettlementDetailExample example);

    int updateByPrimaryKeySelective(ClauseSettlementDetail record);

    int updateByPrimaryKey(ClauseSettlementDetail record);
}