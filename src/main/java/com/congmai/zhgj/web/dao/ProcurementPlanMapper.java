package com.congmai.zhgj.web.dao;

import com.congmai.zhgj.web.model.ProcurementPlan;
import com.congmai.zhgj.web.model.ProcurementPlanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProcurementPlanMapper {
    int countByExample(ProcurementPlanExample example);

    int deleteByExample(ProcurementPlanExample example);

    int deleteByPrimaryKey(String serialNum);

    int insert(ProcurementPlan record);

    int insertSelective(ProcurementPlan record);

    List<ProcurementPlan> selectByExample(ProcurementPlanExample example);

    ProcurementPlan selectByPrimaryKey(String serialNum);

    int updateByExampleSelective(@Param("record") ProcurementPlan record, @Param("example") ProcurementPlanExample example);

    int updateByExample(@Param("record") ProcurementPlan record, @Param("example") ProcurementPlanExample example);

    int updateByPrimaryKeySelective(ProcurementPlan record);

    int updateByPrimaryKey(ProcurementPlan record);
}