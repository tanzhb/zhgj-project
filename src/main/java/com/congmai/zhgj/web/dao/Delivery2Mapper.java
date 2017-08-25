package com.congmai.zhgj.web.dao;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.web.model.Delivery;
import com.congmai.zhgj.web.model.DeliveryExample;
import com.congmai.zhgj.web.model.DeliverySelectExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface Delivery2Mapper extends GenericDao<Delivery, String>{
    int countByExample(DeliveryExample example);

    int deleteByExample(DeliveryExample example);

    int deleteByPrimaryKey(String serialNum);

    int insert(Delivery record);

    int insertSelective(Delivery record);

    List<Delivery> selectByExample(DeliveryExample example);
    
    List<Delivery> selectListByExample(DeliverySelectExample example);

    Delivery selectByPrimaryKey(String serialNum);
    
    Delivery selectByTakeDeliveryPrimaryKey(String serialNum);

    int updateByExampleSelective(@Param("record") Delivery record, @Param("example") DeliveryExample example);

    int updateByExample(@Param("record") Delivery record, @Param("example") DeliveryExample example);

    int updateByPrimaryKeySelective(Delivery record);

    int updateByPrimaryKey(Delivery record);
}