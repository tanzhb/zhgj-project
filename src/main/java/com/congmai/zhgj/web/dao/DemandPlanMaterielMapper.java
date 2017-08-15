package com.congmai.zhgj.web.dao;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.web.model.DemandPlanMateriel;
import com.congmai.zhgj.web.model.DemandPlanMaterielExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface DemandPlanMaterielMapper extends GenericDao<DemandPlanMateriel, String>{
    int countByExample(DemandPlanMaterielExample example);

    int deleteByExample(DemandPlanMaterielExample example);

    int deleteByPrimaryKey(String serialNum);

    int insert(DemandPlanMateriel record);

    int insertSelective(DemandPlanMateriel record);

    List<DemandPlanMateriel> selectByExample(DemandPlanMaterielExample example);

    DemandPlanMateriel selectByPrimaryKey(String serialNum);

    int updateByExampleSelective(@Param("record") DemandPlanMateriel record, @Param("example") DemandPlanMaterielExample example);

    int updateByExample(@Param("record") DemandPlanMateriel record, @Param("example") DemandPlanMaterielExample example);

    int updateByPrimaryKeySelective(DemandPlanMateriel record);

    int updateByPrimaryKey(DemandPlanMateriel record);
}