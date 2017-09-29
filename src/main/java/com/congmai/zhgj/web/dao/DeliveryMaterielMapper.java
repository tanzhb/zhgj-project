package com.congmai.zhgj.web.dao;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.web.model.DeliveryMateriel;
import com.congmai.zhgj.web.model.DeliveryMaterielExample;
import com.congmai.zhgj.web.model.StockInOutRecordExample;
import com.congmai.zhgj.web.model.StockInOutRecordSelectExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface DeliveryMaterielMapper extends GenericDao<DeliveryMateriel, String>{
    int countByExample(DeliveryMaterielExample example);

    int deleteByExample(DeliveryMaterielExample example);

    int deleteByPrimaryKey(String serialNum);

    int insert(DeliveryMateriel record);

    int insertSelective(DeliveryMateriel record);

    List<DeliveryMateriel> selectByExample(DeliveryMaterielExample example);
    
    List<DeliveryMateriel> selectByExampleForStockIn(DeliveryMaterielExample example);

    DeliveryMateriel selectByPrimaryKey(String serialNum);

    int updateByExampleSelective(@Param("record") DeliveryMateriel record, @Param("example") DeliveryMaterielExample example);

    int updateByExample(@Param("record") DeliveryMateriel record, @Param("example") DeliveryMaterielExample example);

    int updateByPrimaryKeySelective(DeliveryMateriel record);

    int updateByPrimaryKey(DeliveryMateriel record);
  
    List<DeliveryMateriel> selectListByExample(StockInOutRecordSelectExample example);
    
    List<DeliveryMateriel> selectListByExampleForStockIn(StockInOutRecordSelectExample example);

	int countListByExample(StockInOutRecordSelectExample example);
	
	int countListByExampleForStockIn(StockInOutRecordSelectExample example);

int updateDeliveryMateriel(DeliveryMateriel record);

  List<DeliveryMateriel> getDetailByRelationDeliverSerialList(List<String>serialNums);//获取出库明细

   List<DeliveryMateriel> getDetailByRelationTakeDeliverSerialList(List<String>serialNums);//获取入库明细

}

