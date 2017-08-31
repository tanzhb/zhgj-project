package com.congmai.zhgj.web.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.congmai.zhgj.core.feature.orm.mybatis.Page;
import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.core.util.DateUtil;
import com.congmai.zhgj.web.dao.DemandPlanMapper;
import com.congmai.zhgj.web.dao.DemandPlanMaterielMapper;
import com.congmai.zhgj.web.dao.MaterielMapper;
import com.congmai.zhgj.web.dao.SupplyMaterielMapper;
import com.congmai.zhgj.web.model.DemandPlan;
import com.congmai.zhgj.web.model.DemandPlanExample;
import com.congmai.zhgj.web.model.DemandPlanMateriel;
import com.congmai.zhgj.web.model.DemandPlanMaterielExample;
import com.congmai.zhgj.web.model.Materiel;
import com.congmai.zhgj.web.model.MaterielExample;
import com.congmai.zhgj.web.model.SupplyMateriel;
import com.congmai.zhgj.web.model.SupplyMaterielExample;
import com.congmai.zhgj.web.service.DemandPlanService;

@Service
public class DemandPlanServiceImpl extends GenericServiceImpl<DemandPlan,String>
		implements DemandPlanService {

	@Resource
	private DemandPlanMapper demandPlanMapper;
	
	@Resource
	private MaterielMapper materielMapper;
	
	@Resource
	private SupplyMaterielMapper supplyMaterielMapper;
	
	@Resource
	private DemandPlanMaterielMapper demandPlanMaterielMapper;
	
	@Override
	public GenericDao<DemandPlan, String> getDao() {
		return this.demandPlanMapper;
	}

	@Override
	public Page<DemandPlan> getListByCondition(DemandPlan demandPlan,int start,int limit) {
		DemandPlanExample example = new DemandPlanExample();
		example.setOrderByClause("updateTime desc");
		example.setStart((demandPlan.getPageIndex()-1)*demandPlan.getPageSize());
		example.setLimit(demandPlan.getPageSize());
		example.createCriteria().andDelFlgEqualTo("0");
		List<DemandPlan> list = demandPlanMapper.selectByExample(example);
		if(!CollectionUtils.isEmpty(list)){
			for(DemandPlan vo : list){
				DemandPlanMaterielExample example2 = new DemandPlanMaterielExample();
				example2.createCriteria().andDemandPlanSerialEqualTo(vo.getSerialNum()).andDelFlgEqualTo("0");
				example2.setOrderByClause("createTime desc");
				if(limit!=99999999){
					example2.setStart(0);
					example2.setLimit(5);
				}
				List<DemandPlanMateriel> mList = demandPlanMaterielMapper.selectByExample(example2);
				for(DemandPlanMateriel dm : mList){
					int remainTime = 0;
					try {
						remainTime = DateUtil.daysBetween(new Date(), dm.getDeliveryDate());
					} catch (Exception e) {
						System.out.println("deliveryDate-----"+e.getMessage());
					}
					dm.setRemainTime(String.valueOf(remainTime<0?0:remainTime));
				}
				vo.setMateriels(mList);
			}
		}
		int count = demandPlanMapper.countByExample(example);
		Page<DemandPlan> page = new Page<DemandPlan>(start, limit);
		page.setResult(list);
		page.setTotalCount(count);
		return page;
	}

	@Override
	public List<Materiel> chooseMateriel(String ids) {
		List<Materiel> materiels = new ArrayList<Materiel>();
		try {
			if(StringUtils.isNotEmpty(ids)){
				List<String> idList = Arrays.asList(ids.split(","));
				SupplyMaterielExample example2 = new SupplyMaterielExample();
				example2.createCriteria().andSerialNumIn(idList).andDelFlgEqualTo("0");
				List<SupplyMateriel> list = supplyMaterielMapper.selectByExample(example2);
				if(CollectionUtils.isNotEmpty(list)){
					for(SupplyMateriel supplyMateriel : list){
						MaterielExample example = new MaterielExample();
						example.createCriteria().andMaterielIdEqualTo(supplyMateriel.getMaterielId()).andIsLatestVersionEqualTo("1").andDelFlgEqualTo("0");
						List<Materiel> materielList = materielMapper.selectByExample(example);
						if(CollectionUtils.isNotEmpty(materielList)){
							materiels.add(materielList.get(0));
						}
					}
				}
			}
		} catch (Exception e) {
			System.out.println(this.getClass()+"============"+e.getMessage());
		}
		return materiels;
	}

	@Override
	public void deleteBatch(List<String> serialNumArray) {
		DemandPlanExample example = new DemandPlanExample();
		DemandPlan record = new DemandPlan();
		record.setDelFlg("1");
		example.createCriteria().andSerialNumIn(serialNumArray);
		demandPlanMapper.updateByExampleSelective(record, example);
		
		DemandPlanMateriel materiel = new DemandPlanMateriel();
		DemandPlanMaterielExample materielExample = new DemandPlanMaterielExample();
		materiel.setDelFlg("1");
		materielExample.createCriteria().andDemandPlanSerialIn(serialNumArray);
		demandPlanMaterielMapper.updateByExampleSelective(materiel, materielExample);
	}

	@Override
	public void insertDemandPlanInfo(DemandPlan demandPlan,
			List<DemandPlanMateriel> demandPlanMateriels) {
			demandPlanMapper.insert(demandPlan);
			demandPlanMaterielMapper.insertBatch(demandPlanMateriels);
		
	}

}
