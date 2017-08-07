package com.congmai.zhgj.web.dao;

import com.congmai.zhgj.web.model.Warehouseposition;
import com.congmai.zhgj.web.model.WarehousepositionExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface WarehousepositionMapper {
    int countByExample(WarehousepositionExample example);

    int deleteByExample(WarehousepositionExample example);

    int deleteByPrimaryKey(String serialNum);

    int insert(Warehouseposition record);

    int insertSelective(Warehouseposition record);

    List<Warehouseposition> selectlistByWarehouseSerial(String warehouseSerial);

    Warehouseposition selectByPrimaryKey(String serialNum);

    int updateByExampleSelective(@Param("record") Warehouseposition record, @Param("example") WarehousepositionExample example);

    int updateByExample(@Param("record") Warehouseposition record, @Param("example") WarehousepositionExample example);

    int updateByPrimaryKeySelective(Warehouseposition record);

    int updateByPrimaryKey(Warehouseposition record);
    int deleteWarehouseposition(List<String>serialNums);
}