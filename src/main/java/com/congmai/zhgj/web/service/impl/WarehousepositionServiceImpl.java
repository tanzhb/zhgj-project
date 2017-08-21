package com.congmai.zhgj.web.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.web.dao.WarehousepositionMapper;
import com.congmai.zhgj.web.model.Warehouseposition;
import com.congmai.zhgj.web.service.WarehousepositionService;

/**
 * 
 * @ClassName WarehouseServiceImpl
 * @Descripzhaichao
 * @Date 2017年7月28日 下午4:56:29
 * @version 1.0.0
 */
@Service
public class WarehousepositionServiceImpl extends GenericServiceImpl<Warehouseposition, String> implements  WarehousepositionService {
    @Resource
    private  WarehousepositionMapper  warehousepositionMapper;
	@Override
	public GenericDao<Warehouseposition, String> getDao() {
		// TODO Auto-generated method stub
		return  warehousepositionMapper;
	}

	@Override
	public List<Warehouseposition> selectList(String warehouseSerial) {
		// TODO Auto-generated method stub
		Warehouseposition record=new Warehouseposition();
		record.setWarehouseSerial(warehouseSerial);
		return warehousepositionMapper.selectlistByWarehouseSerial(record);
	}

}
