package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.LadderPrice;
import com.congmai.zhgj.web.model.Warehouse;
import com.congmai.zhgj.web.model.Warehouseposition;

/**
 * 
 * @ClassName WarehouseService
 * @Description  仓库库位 业务 接口
 * @author zhaichao
 * @Date 2017年7月28日 下午4:55:25
 * @version 1.0.0
 */
public interface WarehousepositionService extends GenericService<Warehouseposition, String> {
   /**
	 * 
	 * @Description (根据仓库流水查仓库库位信息)
	 * @param company
	 * @return
	 */
List<Warehouseposition> selectList(String warehouseSerial);

	
}
