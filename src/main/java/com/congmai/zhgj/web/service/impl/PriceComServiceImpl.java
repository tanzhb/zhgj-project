package com.congmai.zhgj.web.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.web.dao.CompanyMapper;
import com.congmai.zhgj.web.dao.PriceComMapper;
import com.congmai.zhgj.web.model.Company;
import com.congmai.zhgj.web.model.PriceCom;
import com.congmai.zhgj.web.model.PriceComExample;
import com.congmai.zhgj.web.service.PriceComService;
@Service
public class PriceComServiceImpl extends GenericServiceImpl<PriceCom, String> implements
PriceComService {
	@Resource
	private PriceComMapper priceComMapper;
	@Resource
	private CompanyMapper companyMapper;
	
	@Override
	public GenericDao<PriceCom, String> getDao() {
		return priceComMapper;
	}

	@Override
	public List<PriceCom> insertPriceComs(List<PriceCom> priceComs, String userName) {
		if (!CollectionUtils.isEmpty(priceComs)) {
			for (PriceCom priceCom : priceComs) {
				priceCom.setSerialNum(UUID.randomUUID().toString()
						.replace("-", ""));
				priceCom.setCreateTime(new Date());
				priceCom.setCreator(userName);
				priceCom.setUpdateTime(new Date());
				priceCom.setUpdater(userName);
			}
		}
		priceComMapper.insertPriceComList(priceComs);
		return  priceComs;
	}
		

	@Override
	public List<PriceCom> selectListByPriceSerial(String PriceSerial) {
		PriceComExample pe=new  PriceComExample();
		com.congmai.zhgj.web.model.PriceComExample.Criteria c=pe.createCriteria();
		c.andDelFlgEqualTo("0").andPriceSerialEqualTo(PriceSerial);
		List<PriceCom>priceComs=priceComMapper.selectByExample(pe);
		for(PriceCom priceCom:priceComs){
			Company com=companyMapper.selectByPrimaryKey(priceCom.getComSerial());
			if(com!=null){
				priceCom.setComName(com.getComName());	
				priceCom.setComNum(com.getComNum());	
			}
			
		}
		return  priceComs;
	}

	@Override
	public void deleteByPriceSerial(String PriceSerial) {
		PriceComExample pe=new  PriceComExample();
		com.congmai.zhgj.web.model.PriceComExample.Criteria c=pe.createCriteria();
		c.andDelFlgEqualTo("0").andPriceSerialEqualTo(PriceSerial);
		 priceComMapper.deleteByExample(pe);
		
	}
	
}
