package com.congmai.zhgj.web.dao;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.web.model.DemandPlan;
import com.congmai.zhgj.web.model.DemandPlanExample;
import com.congmai.zhgj.web.model.DemandPlanSelectExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface DemandPlanMapper extends GenericDao<DemandPlan, String>{
    int countByExample(DemandPlanSelectExample example);

    int deleteByExample(DemandPlanExample example);

    int deleteByPrimaryKey(String serialNum);

    int insert(DemandPlan record);

    int insertSelective(DemandPlan record);

    List<DemandPlan> selectByExample(DemandPlanSelectExample example);

    DemandPlan selectByPrimaryKey(String serialNum);

    int updateByExampleSelective(@Param("record") DemandPlan record, @Param("example") DemandPlanExample example);

    int updateByExample(@Param("record") DemandPlan record, @Param("example") DemandPlanExample example);

    int updateByPrimaryKeySelective(DemandPlan record);

    int updateByPrimaryKey(DemandPlan record);
}