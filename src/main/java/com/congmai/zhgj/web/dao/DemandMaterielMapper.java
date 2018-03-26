package com.congmai.zhgj.web.dao;

import com.congmai.zhgj.web.model.DemandMateriel;
import com.congmai.zhgj.web.model.DemandMaterielExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DemandMaterielMapper {
    int countByExample(DemandMaterielExample example);

    int deleteByExample(DemandMaterielExample example);

    int deleteByPrimaryKey(String serialNum);

    int insert(DemandMateriel record);

    int insertSelective(DemandMateriel record);

    List<DemandMateriel> selectByExample(DemandMaterielExample example);

    DemandMateriel selectByPrimaryKey(String serialNum);

    int updateByExampleSelective(@Param("record") DemandMateriel record, @Param("example") DemandMaterielExample example);

    int updateByExample(@Param("record") DemandMateriel record, @Param("example") DemandMaterielExample example);

    int updateByPrimaryKeySelective(DemandMateriel record);

    int updateByPrimaryKey(DemandMateriel record);
}