package com.congmai.zhgj.web.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.core.util.DateUtil;
import com.congmai.zhgj.web.dao.LadderPriceMapper;
import com.congmai.zhgj.web.model. LadderPrice;
import com.congmai.zhgj.web.model.LadderPriceExample;
import com.congmai.zhgj.web.model.PriceList;
import com.congmai.zhgj.web.service.LadderPriceService;
@Service
public class LadderPriceServiceImpl extends GenericServiceImpl<LadderPrice, String> implements
LadderPriceService {
	@Resource
	private LadderPriceMapper ladderPriceMapper;
	
	@Override
	public GenericDao<LadderPrice, String> getDao() {
		return ladderPriceMapper;
	}
	@Override
	public PriceList selectOne(String priceSerial) {
		return null;
	}

	@Override
	public void insertOrUpdateLadderPrices(List<LadderPrice> ladderPrices) {
		
	}

	@Override
	public List<LadderPrice> selectListByPriceSerial(LadderPrice record) {
		return ladderPriceMapper.selectByPriceSerial(record);
	}

	@Override
	public void deleteByPriceSerial(LadderPrice record) {
		
		ladderPriceMapper.deleteByPriceSerial(record);
	}

	@Override
	public void deleteBySerialNum(String serialNum) {
		ladderPriceMapper.deleteByPrimaryKey(serialNum);
		
	}
	@Override
	public LadderPrice selectLadderPrice(String serialNum) {
		
		return ladderPriceMapper.selectByPrimaryKey(serialNum);
	}
	@Override
	public void insertLadderPrices(List<LadderPrice> ladderPrices) {
		ladderPriceMapper.insertLadderPriceList(ladderPrices);
	}
	

}
