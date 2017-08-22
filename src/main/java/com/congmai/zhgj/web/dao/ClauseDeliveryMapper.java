package com.congmai.zhgj.web.dao;

import com.congmai.zhgj.web.model.ClauseDelivery;
import com.congmai.zhgj.web.model.ClauseDeliveryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClauseDeliveryMapper {
    int countByExample(ClauseDeliveryExample example);

    int deleteByExample(ClauseDeliveryExample example);

    int deleteByPrimaryKey(String serialNum);

    int insert(ClauseDelivery record);

    int insertSelective(ClauseDelivery record);

    List<ClauseDelivery> selectByExample(ClauseDeliveryExample example);

    ClauseDelivery selectByPrimaryKey(String serialNum);

    int updateByExampleSelective(@Param("record") ClauseDelivery record, @Param("example") ClauseDeliveryExample example);

    int updateByExample(@Param("record") ClauseDelivery record, @Param("example") ClauseDeliveryExample example);

    int updateByPrimaryKeySelective(ClauseDelivery record);

    int updateByPrimaryKey(ClauseDelivery record);
}