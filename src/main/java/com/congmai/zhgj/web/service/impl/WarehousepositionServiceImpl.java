package com.congmai.zhgj.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.web.dao.WarehouseMapper;
import com.congmai.zhgj.web.dao.WarehousepositionMapper;
import com.congmai.zhgj.web.model.Warehouse;
import com.congmai.zhgj.web.model.WarehouseExample;
import com.congmai.zhgj.web.model.Warehouseposition;
import com.congmai.zhgj.web.service.WarehouseService;
import com.congmai.zhgj.web.service.WarehousepositionService;

/**
 * 
 * @ClassName WarehouseServiceImpl
 * @Descripzhaichao
 * @Date 2017年7月28日 下午4:56:29
 * @version 1.0.0
 */
@Service
public class WarehousepositionServiceImpl extends GenericServiceImpl<Warehouseposition, Long> implements  WarehousepositionService {
    @Resource
    private  WarehousepositionMapper  warehousepositionMapper;
	@Override
	public GenericDao<Warehouseposition, Long> getDao() {
		// TODO Auto-generated method stub
		return (GenericDao<Warehouseposition, Long>) warehousepositionMapper;
	}

	@Override
	public Warehouseposition selectOne(String id) {
		// TODO Auto-generated method stub
		return warehousepositionMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteWarehouseposition(String ids) {
		// TODO Auto-generated method stub
		List<String> idList = ApplicationUtils.getIdList(ids);
		warehousepositionMapper.deleteWarehouseposition(idList);
		return 1;
	}

	@Override
	public List<Warehouseposition> selectList(String warehouseSerial) {
		// TODO Auto-generated method stub
		return warehousepositionMapper.selectlistByWarehouseSerial(warehouseSerial);
	}

	
	
	
	  
	    

   
}
