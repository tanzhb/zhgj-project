package com.congmai.zhgj.web.dao;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.web.model.Delivery;
import com.congmai.zhgj.web.model.DeliveryMateriel;
import com.congmai.zhgj.web.model.Stock;
import com.congmai.zhgj.web.model.StockInOutRecord;
import com.congmai.zhgj.web.model.StockInOutRecordExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface StockInOutRecordMapper  extends GenericDao<StockInOutRecord,String> {
    int countByExample(StockInOutRecordExample example);

    int deleteByExample(StockInOutRecordExample example);

    int deleteByPrimaryKey(String serialNum);

    int insert(StockInOutRecord record);

    int insertSelective(StockInOutRecord record);

    List<StockInOutRecord> selectByExample(StockInOutRecordExample example);

    StockInOutRecord selectByPrimaryKey(String serialNum);

    int updateByExampleSelective(@Param("record") StockInOutRecord record, @Param("example") StockInOutRecordExample example);

    int updateByExample(@Param("record") StockInOutRecord record, @Param("example") StockInOutRecordExample example);

    int updateByPrimaryKeySelective(StockInOutRecord record);

    int updateByPrimaryKey(StockInOutRecord record);
    int deleteStockInOutRecord(List<String>serialNums);//批量删除出入库记录信息

	List<Delivery> selectListByExample(StockInOutRecordExample example);
	
	StockInOutRecord selectStockInInfoByPrimaryKey(String serialNum);
	
	StockInOutRecord selectStockOutInfoByPrimaryKey(String serialNum);
	
	 List<String>getRelationDeliverSerialForZijian(Stock record);//获取发货流水自建
	    
	  List<String> getRelationTakeDeliverSerialForZijian(Stock record);//获取收货流水自建
	  
	  List<String>getRelationDeliverSerialForDaiguan(Stock record);//获取发货流水代管
	    
	  List<String> getRelationTakeDeliverSerialForDaiguan(Stock record);//获取收货流水代管
	  
	  String  countInAmountForZijian(String serialNum);//自建库存根据基本物料流水查入库总数
		String  countOutAmountForZijian(String serialNum);//自建库存根据基本物料流水查出库总数
		String  countInAmountForDaiguan(String serialNum);//代管库存根据基本物料流水查入库总数
		String  countOutAmountForDaiguan(String serialNum);//代管库存根据基本物料流水查出库总数
		
		String  getMaterielZiJianStock(String serialNum);//获取自建库存物料数量
	    
	 
}