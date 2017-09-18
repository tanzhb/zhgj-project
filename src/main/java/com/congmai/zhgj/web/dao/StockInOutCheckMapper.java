package com.congmai.zhgj.web.dao;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.web.model.DeliveryMateriel;
import com.congmai.zhgj.web.model.StockInOutCheck;
import com.congmai.zhgj.web.model.StockInOutCheckExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface StockInOutCheckMapper extends GenericDao<StockInOutCheck,String>{
    int countByExample(StockInOutCheckExample example);

    int deleteByExample(StockInOutCheckExample example);

    int deleteByPrimaryKey(String serialNum);

    int insert(StockInOutCheck record);

    int insertSelective(StockInOutCheck record);

    List<StockInOutCheck> selectByExample(StockInOutCheckExample example);

    StockInOutCheck selectByPrimaryKey(String serialNum);

    int updateByExampleSelective(@Param("record") StockInOutCheck record, @Param("example") StockInOutCheckExample example);

    int updateByExample(@Param("record") StockInOutCheck record, @Param("example") StockInOutCheckExample example);

    int updateByPrimaryKeySelective(StockInOutCheck record);

    int updateByPrimaryKey(StockInOutCheck record);
    
    int deleteStockInOutCheck(List<String>serialNums);//批量删除出入库检验记录信息
    
    int updateDeliverMateriel(List<DeliveryMateriel>deliveryMateriels);//批量更新发货明细
    
    int updateStockInOutCheckStatus(String serialNum);//更新出入库检验状态
}