package com.congmai.zhgj.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.web.model.Warehouseposition;
import com.congmai.zhgj.web.model.WarehousepositionExample;

public interface WarehousepositionMapper  extends GenericDao<Warehouseposition, String>{
    int countByExample(WarehousepositionExample example);

    int deleteByExample(WarehousepositionExample example);

    int deleteByPrimaryKey(String serialNum);

    int insert(Warehouseposition record);

    int insertSelective(Warehouseposition record);

    List<Warehouseposition> selectlistByWarehouseSerial(Warehouseposition record);

    Warehouseposition selectByPrimaryKey(String serialNum);

    int updateByExampleSelective(@Param("record") Warehouseposition record, @Param("example") WarehousepositionExample example);

    int updateByExample(@Param("record") Warehouseposition record, @Param("example") WarehousepositionExample example);

    int updateByPrimaryKeySelective(Warehouseposition record);

    int updateByPrimaryKey(Warehouseposition record);
    int deleteWarehouseposition(List<String>serialNums);
    int insertWarehouseposition(List<Warehouseposition> list);
    int deleteByWarehouseSerial(Warehouseposition record);//根据仓库流水删除对应的库区信息
}