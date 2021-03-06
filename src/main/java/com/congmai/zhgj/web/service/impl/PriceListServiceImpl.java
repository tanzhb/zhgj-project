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
	public List<PriceList> selectPriceList(String  buyOrSale,List<String> comIds) {
		PriceListExample ple=new PriceListExample();
		Criteria criteria= ple.createCriteria();
		criteria.andDelFlgEqualTo("0");
		if(buyOrSale.indexOf("buy")>-1){
			criteria.andPriceTypeEqualTo("buyPrice");
			if(comIds!=null){
			  criteria.andBuyComIdIn(comIds);
			}
		}else  if(buyOrSale.indexOf("sale")>-1){
			criteria.andPriceTypeEqualTo("salePrice");
			if(comIds!=null){
			  criteria.andSupplyComIdIn(comIds);
			}
		}
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
	@Override
	public PriceList getPriceListInfo(String comId, String materielSerial,String priceType) {
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("priceType", priceType);
		map.put("materielSerial", materielSerial);
		map.put("comId", comId);
		PriceList priceList=priceListMapper.getGuideUnitPrice(map);
		return priceList;
	}
	@Override
	public List<PriceList> selectSamePriceList(PriceList priceList) {
		PriceListExample ple=new PriceListExample();
		Criteria criteria= ple.createCriteria();
		criteria.andDelFlgEqualTo("0");
		if(!StringUtils.isEmpty(priceList.getBuyComId())){
			criteria.andPriceTypeEqualTo("salePrice");
			criteria.andBuyComIdEqualTo(priceList.getBuyComId());
		}else if(!StringUtils.isEmpty(priceList.getSupplyComId())){
			criteria.andPriceTypeEqualTo("buyPrice");
			criteria.andSupplyComIdEqualTo(priceList.getSupplyComId());
		}
		criteria.andMaterielSerialEqualTo(priceList.getMaterielSerial());
		
		criteria.andPriceEffectiveDateLessThanOrEqualTo(priceList.getPriceExpirationDate());
		criteria.andPriceExpirationDateGreaterThanOrEqualTo(priceList.getPriceEffectiveDate());
		
		return priceListMapper.selectByExample(ple);
	}
	@Override
	public List<PriceList> selectCurrentPriceList(Materiel materiel,
			String comId, String comType) {
			PriceListExample ple=new PriceListExample();
			Criteria criteria= ple.createCriteria();
			criteria.andDelFlgEqualTo("0");
			if("buyComId".equals(comType)){
				criteria.andBuyComIdEqualTo(comId);
			}else if("supplyComId".equals(comType)){
				criteria.andSupplyComIdEqualTo(comId);
			}
			criteria.andMaterielSerialEqualTo(materiel.getSerialNum());
			
			criteria.andPriceEffectiveDateLessThanOrEqualTo(new Date());
			criteria.andPriceExpirationDateGreaterThanOrEqualTo(new Date());
			criteria.andStatusEqualTo("1");
			
			return priceListMapper.selectByExample(ple);
	}
	
	
	}
	
	
	  
	    

   

