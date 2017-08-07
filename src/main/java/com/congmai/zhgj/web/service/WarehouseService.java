package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.Materiel;
import com.congmai.zhgj.web.model.MaterielExample;
import com.congmai.zhgj.web.model.Warehouse;
import com.congmai.zhgj.web.model.WarehouseExample;

/**
 * 
 * @ClassName WarehouseService
 * @Description  仓库 业务 接口
 * @author zhaichao
 * @Date 2017年7月28日 下午4:55:25
 * @version 1.0.0
 */
public interface WarehouseService extends GenericService<Warehouse, Long> {

	/**
	 * 
	 * @Description (根据id查询仓库信息)
	 * @param id
	 * @return
	 */
	Warehouse  selectOne(String id);
	 /**
     * 
     * @Description 删除一条库位信息
     * @param ids
     * @return
     */
	int  deleteWarehouse(String ids);
	 List<Warehouse> selectWarehouseList(WarehouseExample we);
}
