package com.congmai.zhgj.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.web.dao.WarehouseMapper;
import com.congmai.zhgj.web.model.Warehouse;
import com.congmai.zhgj.web.model.WarehouseExample;
import com.congmai.zhgj.web.service.WarehouseService;

/**
 * 
 * @ClassName WarehouseServiceImpl
 * @Descripzhaichao
 * @Date 2017年7月28日 下午4:56:29
 * @version 1.0.0
 */
@Service
public class WarehouseServiceImpl extends GenericServiceImpl<Warehouse, Long> implements WarehouseService {
    @Resource
    private  WarehouseMapper  warehouseMapper;
	@Override
	public GenericDao<Warehouse, Long> getDao() {
		// TODO Auto-generated method stub
		return warehouseMapper;
	}
	@Override
	public List<Warehouse> selectList() {
		return warehouseMapper.selectByExample(new WarehouseExample());
	}
	@Override
	public Warehouse selectOne(String id) {
		// TODO Auto-generated method stub
		return warehouseMapper.selectByPrimaryKey(id);
	}
	@Override
	public int deleteWarehouse(String ids) {
		List<String> idList = ApplicationUtils.getIdList(ids);
		warehouseMapper.deleteWarehouse(idList);
		return 1;
	}
	@Override
	public List<Warehouse> selectWarehouseList(WarehouseExample we) {
		// TODO Auto-generated method stub
		return  warehouseMapper.selectByExample(we);
	}
	
	  
	    

   
}
