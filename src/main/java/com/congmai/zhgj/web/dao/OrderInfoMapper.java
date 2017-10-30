package com.congmai.zhgj.web.dao;

import com.congmai.zhgj.web.model.OrderInfo;
import com.congmai.zhgj.web.model.OrderInfoExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface OrderInfoMapper {
    int countByExample(OrderInfoExample example);

    int deleteByExample(OrderInfoExample example);

    int deleteByPrimaryKey(String serialNum);

    int insert(OrderInfo record);

    int insertSelective(OrderInfo record);

    List<OrderInfo> selectByExample(OrderInfoExample example);
    
    List<OrderInfo> selectFramByExample(OrderInfo example);
    
    List<OrderInfo> selectCommenByExample(OrderInfo example);

    OrderInfo selectByPrimaryKey(String serialNum);

    int updateByExampleSelective(@Param("record") OrderInfo record, @Param("example") OrderInfoExample example);

    int updateByExample(@Param("record") OrderInfo record, @Param("example") OrderInfoExample example);

    int updateByPrimaryKeySelective(OrderInfo record);

	String getNumCode(Map m);

	String checkNum(OrderInfo orderInfo);

    /*int updateByPrimaryKey(OrderInfo record);*/
}