package com.congmai.zhgj.web.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.web.dao.CompanyContactMapper;
import com.congmai.zhgj.web.dao.CompanyMapper;
import com.congmai.zhgj.web.dao.PriceListMapper;
import com.congmai.zhgj.web.model.Materiel;
import com.congmai.zhgj.web.model.MaterielExample;
import com.congmai.zhgj.web.model.PriceList;
import com.congmai.zhgj.web.model.PriceListExample;
import com.congmai.zhgj.web.model.PriceListExample.Criteria;
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
    @Resource
	private CompanyMapper companyMapper;
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
	/*@Override
	public List<Materiel> selectList(MaterielExample m) {
		// TODO Auto-generated method stub
		return MaterielMapper.selectByExample(m);
	}*/
	@Override
	public PriceList getPriceListInfoByPriceId(String priceId) {
		PriceListExample p =new PriceListExample();
    	//and 条件1
    	Criteria criteria =  p.createCriteria();
    	criteria.andIsLatestVersionEqualTo("1");
    	criteria.andPriceIdEqualTo(priceId);
    	criteria.andDelFlgEqualTo("0");
    	//排序字段
    	p.setOrderByClause("updateTime DESC");
    	List<PriceList> priceListList = priceListMapper.selectByExample(p);
    	
    	if(priceListList==null||priceListList.size()<1){
    		return null;
    	}
		return priceListList.get(0);
	}
	@Override
	public void updateVersion(PriceList priceList) {
		//当前版本更新为老版本-start
		PriceList p = new PriceList();
				p.setIsLatestVersion("0");
				p.setUpdateTime(new Date());
				p.setUpdater(p.getCreator());
				p.setPriceId(priceList.getPriceId());;
				
				//更新条件为当前版本
				PriceListExample ex =new PriceListExample();
		    	//and 条件1
		    	Criteria criteria =  ex.createCriteria();
		    	criteria.andIsLatestVersionEqualTo("1");
		    	criteria.andPriceIdEqualTo(p.getPriceId());
		    	priceListMapper.updateByExampleSelective(p, ex);
				
	}
	@Override
	public List<PriceList> getAllPriceListInfoByPriceIdOrPriceType(String priceId,String priceType) {
		// TODO Auto-generated method stub
		List<PriceList>priceLists=null;
		PriceListExample example=new PriceListExample();
		Criteria criteria =  example.createCriteria();
		criteria.andPriceIdEqualTo(priceId);
		if(priceType==null){
			criteria.andPriceIdEqualTo(priceId);
			priceLists=priceListMapper.selectByExample(example);
		}else{
			Map<String,String>map=new HashMap<String,String>();
			map.put("priceType", priceType);
			map.put("priceId", priceId);
			priceLists=priceListMapper.selectByPriceType(map);
			
		}
		for(PriceList priceList:priceLists ){
			priceList.setVersionNO("V"+priceList.getVersionNO());
			priceList.setRate(priceList.getRate()+"%");
			if(	!StringUtils.isEmpty(priceList.getBuyComId())){
				priceList.setBuyComName(companyMapper.selectByPrimaryKey(priceList.getBuyComId()).getComName());
				priceList.setComNum(companyMapper.selectByPrimaryKey(priceList.getBuyComId()).getComNum());
			}
		}
		return  priceLists;
	}
	
	}
	
	
	  
	    

   

