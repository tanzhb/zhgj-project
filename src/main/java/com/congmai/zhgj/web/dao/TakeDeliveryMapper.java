package com.congmai.zhgj.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.web.model.Delivery;
import com.congmai.zhgj.web.model.TakeDelivery;
import com.congmai.zhgj.web.model.TakeDeliveryExample;
import com.congmai.zhgj.web.model.TakeDeliverySelectExample;

public interface TakeDeliveryMapper extends GenericDao<TakeDelivery, String>{
    int countByExample(TakeDeliverySelectExample example);

    int deleteByExample(TakeDeliveryExample example);

    int deleteByPrimaryKey(String serialNum);

    int insert(TakeDelivery record);

    int insertSelective(TakeDelivery record);

    List<Delivery> selectByExample(TakeDeliveryExample example);
    
    List<Delivery> selectListByExample(TakeDeliverySelectExample example);

    TakeDelivery selectByPrimaryKey(String serialNum);
    
    Delivery selectByTakeDeliveryPrimaryKey(String serialNum);

    int updateByExampleSelective(@Param("record") TakeDelivery record, @Param("example") TakeDeliveryExample example);

    int updateByExample(@Param("record") TakeDelivery record, @Param("example") TakeDeliveryExample example);

    int updateByPrimaryKeySelective(TakeDelivery record);

    int updateByPrimaryKey(TakeDelivery record);

	int countListByExample(TakeDeliverySelectExample example);

	Delivery selectByTakeDeliveryPrimaryKeyForOtherType(String serialNum);

	TakeDelivery selectTakeDeliveryByDeliveryId(String serialNum);
	
	Delivery selectByPrimaryDeliveryKey(String serialNum);
	public void updateOtherTakeDeliveryStatus(Map<String,Object> map);
}