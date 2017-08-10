package com.congmai.zhgj.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.web.dao.PriceListMapper;
import com.congmai.zhgj.web.model.PriceList;
import com.congmai.zhgj.web.model.PriceListExample;
import com.congmai.zhgj.web.service.PriceListService;

/**
 * 
 * @ClassName WarehouseServiceImpl
 * @Descripzhaichao
 * @Date 2017年7月28日 下午4:56:29
 * @version 1.0.0
 */
@Service
public class PriceListServiceImpl extends GenericServiceImpl<PriceList, String> implements PriceListService {
    @Resource
    private  PriceListMapper  priceListMapper;
	@Override
	public GenericDao<PriceList, String> getDao() {
		// TODO Auto-generated method stub
		return   priceListMapper;
	}
	@Override
	public void deletePriceList(String serialNumList) {
		List<String> idList = ApplicationUtils.getIdList(serialNumList);
		// TODO Auto-generated method stub
		priceListMapper.deletePriceList(idList);
	}
	@Override
	public List<PriceList> selectPriceList(PriceListExample ple) {
		// TODO Auto-generated method stub
		return priceListMapper.selectByExample(ple);
	}
	
	
	  
	    

   
}
