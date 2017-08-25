package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.DeliveryMateriel;
import com.congmai.zhgj.web.model.DeliveryMaterielExample;

public interface DeliveryMaterielService extends GenericService<DeliveryMateriel, String>{
	 List<DeliveryMateriel> selectByExample(DeliveryMaterielExample example);
}
