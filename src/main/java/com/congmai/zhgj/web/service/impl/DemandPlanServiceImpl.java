package com.congmai.zhgj.web.service.impl;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.congmai.zhgj.core.feature.orm.mybatis.Page;
import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.core.util.DateUtil;
import com.congmai.zhgj.web.dao.DemandPlanMapper;
import com.congmai.zhgj.web.dao.DemandPlanMaterielMapper;
import com.congmai.zhgj.web.dao.MaterielMapper;
import com.congmai.zhgj.web.model.DemandPlan;
import com.congmai.zhgj.web.model.DemandPlanExample;
import com.congmai.zhgj.web.model.DemandPlanMateriel;
import com.congmai.zhgj.web.model.DemandPlanMaterielExample;
import com.congmai.zhgj.web.model.Materiel;
import com.congmai.zhgj.web.model.MaterielExample;
import com.congmai.zhgj.web.service.DemandPlanService;

@Service
public class DemandPlanServiceImpl extends GenericServiceImpl<DemandPlan,String>
		implements DemandPlanService {

	@Resource
	private DemandPlanMapper demandPlanMapper;
	
	@Resource
	private MaterielMapper materielMapper;
	
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
		example.setStart((start-1)*limit);
		example.setLimit(limit);
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
		
		if(StringUtils.isNotEmpty(ids)){
			List<String> idList = Arrays.asList(ids.split(","));
			MaterielExample example = new MaterielExample();
			example.createCriteria().andSerialNumIn(idList).andDelFlgEqualTo("0");
			return materielMapper.selectByExample(example);
		}
		return null;
	}

	@Override
	public void deleteBatch(List<String> serialNumArray) {
		DemandPlanExample example = new DemandPlanExample();
		DemandPlan record = new DemandPlan();
		record.setDelFlg("1");
		example.createCriteria().andSerialNumIn(serialNumArray);
		demandPlanMapper.updateByExampleSelective(record, example);
		
	}

	@Override
	public void insertDemandPlanInfo(DemandPlan demandPlan,
			List<DemandPlanMateriel> demandPlanMateriels) {
			demandPlanMapper.insert(demandPlan);
			demandPlanMaterielMapper.insertBatch(demandPlanMateriels);
		
	}

}
