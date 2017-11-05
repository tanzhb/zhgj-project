package com.congmai.zhgj.web.dao;

import com.congmai.zhgj.web.model.SupplyBuyVO;
import com.congmai.zhgj.web.model.SupplyBuyVOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SupplyBuyVOMapper {
    int countByExample(SupplyBuyVOExample example);

    int deleteByExample(SupplyBuyVOExample example);

    int deleteByPrimaryKey(String serial_num);

    int insert(SupplyBuyVO record);

    int insertSelective(SupplyBuyVO record);

    List<SupplyBuyVO> selectByExample(SupplyBuyVOExample example);

    SupplyBuyVO selectByPrimaryKey(String serial_num);

    int updateByExampleSelective(@Param("record") SupplyBuyVO record, @Param("example") SupplyBuyVOExample example);

    int updateByExample(@Param("record") SupplyBuyVO record, @Param("example") SupplyBuyVOExample example);

    int updateByPrimaryKeySelective(SupplyBuyVO record);

    int updateByPrimaryKey(SupplyBuyVO record);
}