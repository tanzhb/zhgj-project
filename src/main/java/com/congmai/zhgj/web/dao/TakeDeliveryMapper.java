package com.congmai.zhgj.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.web.model.TakeDelivery;
import com.congmai.zhgj.web.model.TakeDeliveryExample;

public interface TakeDeliveryMapper extends GenericDao<TakeDelivery, String>{
    int countByExample(TakeDeliveryExample example);

    int deleteByExample(TakeDeliveryExample example);

    int deleteByPrimaryKey(String serialNum);

    int insert(TakeDelivery record);

    int insertSelective(TakeDelivery record);

    List<TakeDelivery> selectByExample(TakeDeliveryExample example);

    TakeDelivery selectByPrimaryKey(String serialNum);

    int updateByExampleSelective(@Param("record") TakeDelivery record, @Param("example") TakeDeliveryExample example);

    int updateByExample(@Param("record") TakeDelivery record, @Param("example") TakeDeliveryExample example);

    int updateByPrimaryKeySelective(TakeDelivery record);

    int updateByPrimaryKey(TakeDelivery record);
}