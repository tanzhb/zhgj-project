package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.generic.GenericService;
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
public interface WarehousepositionService extends GenericService<Warehouseposition, Long> {
   /**
	 * 
	 * @Description (根据仓库流水查仓库库位信息)
	 * @param company
	 * @return
	 */
List<Warehouseposition> selectList(String warehouseSerial);

	/**
	 * 
	 * @Description (根据id查询仓库信息)
	 * @param id
	 * @return
	 */
Warehouseposition  selectOne(String serialNum);
	 /**
     * 
     * @Description 批量删除
     * @param ids
     * @return
     */
	int  deleteWarehouseposition(String serialNum);
}
