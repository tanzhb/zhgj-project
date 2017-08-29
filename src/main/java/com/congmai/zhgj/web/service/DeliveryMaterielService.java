package com.congmai.zhgj.web.service;

import org.apache.ibatis.annotations.Param;

import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.DeliveryMateriel;
import com.congmai.zhgj.web.model.DeliveryMaterielExample;

public interface DeliveryMaterielService extends GenericService<DeliveryMateriel, String>{
	 int updateDeliveryMateriel(DeliveryMateriel record);
}
