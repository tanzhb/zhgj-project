package com.congmai.zhgj.web.dao;

import com.congmai.zhgj.web.model.SupplyMateriel;
import com.congmai.zhgj.web.model.SupplyMaterielExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SupplyMaterielMapper {
    int countByExample(SupplyMaterielExample example);

    int deleteByExample(SupplyMaterielExample example);

    int deleteByPrimaryKey(String serialNum);

    int insert(SupplyMateriel record);

    int insertSelective(SupplyMateriel record);

    List<SupplyMateriel> selectByExample(SupplyMaterielExample example);

    SupplyMateriel selectByPrimaryKey(String serialNum);

    int updateByExampleSelective(@Param("record") SupplyMateriel record, @Param("example") SupplyMaterielExample example);

    int updateByExample(@Param("record") SupplyMateriel record, @Param("example") SupplyMaterielExample example);

    int updateByPrimaryKeySelective(SupplyMateriel record);

    int updateByPrimaryKey(SupplyMateriel record);
}