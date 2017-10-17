package com.congmai.zhgj.web.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.congmai.zhgj.core.feature.orm.mybatis.Page;
import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.core.util.DateUtil;
import com.congmai.zhgj.web.dao.CompanyMapper;
import com.congmai.zhgj.web.dao.DemandPlanMaterielMapper;
import com.congmai.zhgj.web.dao.MaterielMapper;
import com.congmai.zhgj.web.model.DemandPlan;
import com.congmai.zhgj.web.model.DemandPlanExample;
import com.congmai.zhgj.web.model.DemandPlanMateriel;
import com.congmai.zhgj.web.model.DemandPlanMaterielExample;
import com.congmai.zhgj.web.model.DemandPlanMaterielExample.Criteria;
import com.congmai.zhgj.web.model.DemandPlanMaterielSelectExample;
import com.congmai.zhgj.web.model.Materiel;
import com.congmai.zhgj.web.model.MaterielExample;
import com.congmai.zhgj.web.service.DemandPlanMaterielService;

@Service
public class DemandPlanMaterielServiceImpl extends GenericServiceImpl<DemandPlanMateriel,String>
		implements DemandPlanMaterielService {

	private Logger logger =  LoggerFactory.getLogger(DemandPlanMaterielServiceImpl.class);
	
	@Resource
	private DemandPlanMaterielMapper demandPlanMaterielMapper;
	
	@Resource
	private MaterielMapper materielMapper;
	
	@Resource
	private CompanyMapper companyMapper;

	@Override
	public GenericDao<DemandPlanMateriel, String> getDao() {
		
		return this.demandPlanMaterielMapper;
	}

	@Override
	public void deleteBatch(List<String> serialNumArray) {
		DemandPlanMateriel demandPlanMateriel = new DemandPlanMateriel();
		demandPlanMateriel.setDelFlg("1");
		DemandPlanMaterielExample example = new DemandPlanMaterielExample();
		example.createCriteria().andSerialNumIn(serialNumArray);
		demandPlanMaterielMapper.updateByExampleSelective(demandPlanMateriel, example);
	}

	@Override
	public List<DemandPlanMateriel> selectListByDemandPlanSerial(
			String serialNum) {
		DemandPlanMaterielExample example = new DemandPlanMaterielExample();
		example.createCriteria().andDemandPlanSerialEqualTo(serialNum).andDelFlgEqualTo("0");
		example.setOrderByClause("createTime desc");
		return this.listHandle(demandPlanMaterielMapper.selectByExample(example));
	}
	
	protected List<DemandPlanMateriel> listHandle(List<DemandPlanMateriel> list){
		if(CollectionUtils.isNotEmpty(list)){
			for(DemandPlanMateriel vo : list){
				int remainTime = 0;
				try {
					remainTime = DateUtil.daysBetween(new Date(), vo.getDeliveryDate());
				} catch (Exception e) {
					System.out.println("deliverDate-----"+e.getMessage()+e.getCause());
				}
				vo.setRemainTime(String.valueOf(remainTime<0?0:remainTime));
			}
		}
		return list;
	}

	@Override
	public String selectMaterielSerialByMaterielNum(String materielNum) {
		MaterielExample example = new MaterielExample();
		example.createCriteria().andMaterielNumEqualTo(materielNum);
		List<Materiel> list = materielMapper.selectByExample(example);
		if(CollectionUtils.isNotEmpty(list)){
			return list.get(0).getSerialNum();
		}
		return null;
	}

	@Override
	public Page<DemandPlanMateriel> getListByCondition(
			DemandPlanMateriel demandPlanMateriel, int start, int limit) {
		DemandPlanMaterielExample example = new DemandPlanMaterielExample();
		Criteria criteria= example.createCriteria();
		if(demandPlanMateriel!=null){
			if(demandPlanMateriel.getStartTime()!=null){
				criteria.andDeliveryDateGreaterThanOrEqualTo(demandPlanMateriel.getStartTime());
			}
			
			if(demandPlanMateriel.getEndTime()!=null){
				criteria.andDeliveryDateLessThanOrEqualTo(demandPlanMateriel.getEndTime());
			}
		}
		example.setOrderByClause("updateTime desc");
		example.setStart((start-1)*limit);
		example.setLimit(limit);
		criteria.andDelFlgEqualTo("0");
		List<DemandPlanMateriel> list = demandPlanMaterielMapper.selectByExample(example);
		if(!CollectionUtils.isEmpty(list)){
			for(DemandPlanMateriel vo : list){
					int remainTime = 0;
					try {
						remainTime = DateUtil.daysBetween(new Date(), vo.getDeliveryDate());
					} catch (Exception e) {
						System.out.println("demandPlanService---getListByCondition-----"+e.getMessage());
					}
					vo.setRemainTime(String.valueOf(remainTime<0?0:remainTime));
			}
		}
		int count = demandPlanMaterielMapper.countByExample(example);
		Page<DemandPlanMateriel> page = new Page<DemandPlanMateriel>(start, limit);
		page.setResult(list);
		page.setTotalCount(count);
		return page;
	}

	@Override
	public String selectSupplyName(String materielSerial) {
		
		return demandPlanMaterielMapper.selectSupplyName(materielSerial);
	}

	@Override
	public List<DemandPlanMateriel> searchDemandPlanMateriels(DemandPlan search) {
		//DemandPlanMaterielSelectExample example = new DemandPlanMaterielSelectExample();
		//example.createCriteria();
		List<DemandPlanMateriel> materiels = demandPlanMaterielMapper.searchMateriels(search);
		if(CollectionUtils.isNotEmpty(materiels)){
			try {
				for(DemandPlanMateriel materiel : materiels){
					materiel.setMaterielNum(materiel.getMateriel().getMaterielNum());
					materiel.setMaterielName(materiel.getMateriel().getMaterielName());
					materiel.setSpecifications(materiel.getMateriel().getSpecifications());
					materiel.setUnit(materiel.getMateriel().getUnit());
				}
			} catch (Exception e) {
				logger.warn(e.getMessage(), e);
			}
			
		}
	
		return listHandle(demandPlanMaterielMapper.searchMateriels(search));
	}
	


}
