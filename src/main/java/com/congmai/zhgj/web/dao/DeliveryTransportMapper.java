package com.congmai.zhgj.web.dao;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.web.model.DeliveryTransport;
import com.congmai.zhgj.web.model.DeliveryTransportExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface DeliveryTransportMapper extends GenericDao<DeliveryTransport,String>{
    int countByExample(DeliveryTransportExample example);

    int deleteByExample(DeliveryTransportExample example);

    int deleteByPrimaryKey(String serialNum);

    int insert(DeliveryTransport record);

    int insertSelective(DeliveryTransport record);

    List<DeliveryTransport> selectByExample(DeliveryTransportExample example);

    DeliveryTransport selectByPrimaryKey(String serialNum);

    int updateByExampleSelective(@Param("record") DeliveryTransport record, @Param("example") DeliveryTransportExample example);

    int updateByExample(@Param("record") DeliveryTransport record, @Param("example") DeliveryTransportExample example);

    int updateByPrimaryKeySelective(DeliveryTransport record);

    int updateByPrimaryKey(DeliveryTransport record);
}