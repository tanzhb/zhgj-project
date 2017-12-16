package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.feature.orm.mybatis.Page;
import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.Delivery;
import com.congmai.zhgj.web.model.DeliveryMateriel;
import com.congmai.zhgj.web.model.DeliveryMaterielExample;
import com.congmai.zhgj.web.model.StockInOutRecord;
import com.congmai.zhgj.web.model.StockInOutRecordExample;

public interface DeliveryMaterielService extends GenericService<DeliveryMateriel, String>{
	 List<DeliveryMateriel> selectByExample(DeliveryMaterielExample example);
	 List<DeliveryMateriel> selectByExampleForStockIn(DeliveryMaterielExample example);
	 Page<DeliveryMateriel> selectListByExample(StockInOutRecord record,String type,String  status);//status是否已入库   1是 0否  空或null查所有
 int updateDeliveryMateriel(DeliveryMateriel record);
}
