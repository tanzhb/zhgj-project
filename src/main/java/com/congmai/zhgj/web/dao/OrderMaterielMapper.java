package com.congmai.zhgj.web.dao;

import com.congmai.zhgj.web.model.OrderMateriel;
import com.congmai.zhgj.web.model.OrderMaterielExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderMaterielMapper {
    int countByExample(OrderMaterielExample example);

    int deleteByExample(OrderMaterielExample example);

    int deleteByPrimaryKey(String serialNum);

    int insert(OrderMateriel record);

    int insertSelective(OrderMateriel record);

    List<OrderMateriel> selectByExample(OrderMaterielExample example);

    OrderMateriel selectByPrimaryKey(String serialNum);

    int updateByExampleSelective(@Param("record") OrderMateriel record, @Param("example") OrderMaterielExample example);

    int updateByExample(@Param("record") OrderMateriel record, @Param("example") OrderMaterielExample example);

    int updateByPrimaryKeySelective(OrderMateriel record);

    int updateByPrimaryKey(OrderMateriel record);
}