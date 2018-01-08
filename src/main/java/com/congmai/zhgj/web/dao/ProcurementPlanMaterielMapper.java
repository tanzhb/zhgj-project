package com.congmai.zhgj.web.dao;

import com.congmai.zhgj.web.model.ProcurementPlanMateriel;
import com.congmai.zhgj.web.model.ProcurementPlanMaterielExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProcurementPlanMaterielMapper {
    int countByExample(ProcurementPlanMaterielExample example);

    int deleteByExample(ProcurementPlanMaterielExample example);

    int deleteByPrimaryKey(String serialNum);

    int insert(ProcurementPlanMateriel record);

    int insertSelective(ProcurementPlanMateriel record);

    List<ProcurementPlanMateriel> selectByExample(ProcurementPlanMaterielExample example);

    ProcurementPlanMateriel selectByPrimaryKey(String serialNum);

    int updateByExampleSelective(@Param("record") ProcurementPlanMateriel record, @Param("example") ProcurementPlanMaterielExample example);

    int updateByExample(@Param("record") ProcurementPlanMateriel record, @Param("example") ProcurementPlanMaterielExample example);

    int updateByPrimaryKeySelective(ProcurementPlanMateriel record);

    int updateByPrimaryKey(ProcurementPlanMateriel record);
}