package com.congmai.zhgj.web.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.web.model.DemandMateriel;
import com.congmai.zhgj.web.model.DemandMaterielExample;
import com.congmai.zhgj.web.model.ProcurementPlanMateriel;
import com.congmai.zhgj.web.model.ProcurementPlanMaterielExample;
import com.congmai.zhgj.web.model.ProcurementPlanMaterielExample.Criteria;
import com.congmai.zhgj.web.service.ProcurementPlanMaterielService;
import com.congmai.zhgj.web.dao.ProcurementPlanMaterielMapper;
import com.congmai.zhgj.web.dao.DemandMaterielMapper;

/**
 * 
 * @ClassName MarerielServiceImpl
 * @Description 采购计划物料ServiceImpl
 * @author qfzhao
 * @Date 2017年7月28日 下午3:06:52
 * @version 1.0.0
 */
@Service
public class ProcurementPlanMaterielServiceImpl implements ProcurementPlanMaterielService {
    @Resource
    private ProcurementPlanMaterielMapper ProcurementPlanMaterielMapper;
    @Resource
    private DemandMaterielMapper DemandMaterielMapper;
    
	@Override
	public int insert(ProcurementPlanMateriel model) {
		return ProcurementPlanMaterielMapper.insert(model);
	}

	@Override
	public int update(ProcurementPlanMateriel model) {
		return ProcurementPlanMaterielMapper.updateByPrimaryKeySelective(model);
	}

	
	@Override
	public int delete(String serialNum) {
		return ProcurementPlanMaterielMapper.deleteByPrimaryKey(serialNum);
	}

	@Override
	public ProcurementPlanMateriel selectById(String serialNum) {
		return ProcurementPlanMaterielMapper.selectByPrimaryKey(serialNum);
	}

	@Override
	public ProcurementPlanMateriel selectOne() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProcurementPlanMateriel> selectList() {
		return ProcurementPlanMaterielMapper.selectByExample(null);
	}

	@Override
	public List<ProcurementPlanMateriel> selectList(ProcurementPlanMaterielExample m) {
		return ProcurementPlanMaterielMapper.selectByExample(m);
	}

	@Override
	public void deleteProcurementPlanMateriels(String ids) {
		List<String> idList = ApplicationUtils.getIdList(ids);
		for(String id : idList){
			ProcurementPlanMateriel m = new ProcurementPlanMateriel();
			m.setSerialNum(id);
			m.setDelFlg("1");
			m.setUpdateTime(new Date());
			ProcurementPlanMaterielMapper.updateByPrimaryKeySelective(m);
		}

	}
	@Override
	public void deleteDemandMateriel(String ids) {
		List<String> idList = ApplicationUtils.getIdList(ids);
		for(String id : idList){
			DemandMateriel dm  = new DemandMateriel();
			dm.setSerialNum(id);
			dm.setDelFlg("1");
			dm.setUpdateTime(new Date());
			DemandMaterielMapper.updateByPrimaryKeySelective(dm);
		}
	}
	@Override
	public void betchInsertProcurementPlanMateriel(List<ProcurementPlanMateriel> procurementPlanMateriel) {
		if(!CollectionUtils.isEmpty(procurementPlanMateriel)){
			//删除之前的采购清单物料
			ProcurementPlanMateriel m = new ProcurementPlanMateriel();
			m.setDelFlg("1");
			m.setUpdateTime(new Date());
			m.setUpdater(procurementPlanMateriel.get(0).getCreator());
			
			ProcurementPlanMaterielExample ex =new ProcurementPlanMaterielExample();
	    	Criteria criteria =  ex.createCriteria();
	    	criteria.andProcurementPlanSerialEqualTo(procurementPlanMateriel.get(0).getProcurementPlanSerial());
			
	    	ProcurementPlanMaterielMapper.updateByExampleSelective(m, ex);
			int i = 0;
			for(ProcurementPlanMateriel b:procurementPlanMateriel){
				i++;
				b.setSort(i);
				ProcurementPlanMaterielMapper.insert(b);
			}
		}
		
	}

	@Override
	public void betchInsertDemandMateriel(List<DemandMateriel> demandMateriels) {
		if(!CollectionUtils.isEmpty(demandMateriels)){
			//删除之前的需求物料
			DemandMateriel m = new DemandMateriel();
			m.setDelFlg("1");
			m.setUpdateTime(new Date());
			m.setUpdater(demandMateriels.get(0).getCreator());
			
			DemandMaterielExample ex =new DemandMaterielExample();
			com.congmai.zhgj.web.model.DemandMaterielExample.Criteria criteria =  ex.createCriteria();
	    	criteria.andProcurementPlanSerialEqualTo(demandMateriels.get(0).getProcurementPlanSerial());
			
	    	DemandMaterielMapper.updateByExampleSelective(m, ex);
			int i = 0;
			for(DemandMateriel b:demandMateriels){
				i++;
				b.setSort(i);
				DemandMaterielMapper.insert(b);
			}
		}
		
	}

	@Override
	public void deleteDemandMateriels(String procurementPlanSerial) {
		DemandMaterielExample dme=new DemandMaterielExample();
		com.congmai.zhgj.web.model.DemandMaterielExample.Criteria  c=dme.createCriteria();
		c.andProcurementPlanSerialEqualTo(procurementPlanSerial);
		DemandMaterielMapper.deleteByExample(dme);		
	}

	@Override
	public void deleteAllProcurementPlanMateriels(String procurementPlanSerial) {
		ProcurementPlanMaterielExample ppme=new ProcurementPlanMaterielExample();
		com.congmai.zhgj.web.model.ProcurementPlanMaterielExample.Criteria  c=ppme.createCriteria();
		c.andProcurementPlanSerialEqualTo(procurementPlanSerial);
		ProcurementPlanMaterielMapper.deleteByExample(ppme);	
	}

	@Override
	public List<DemandMateriel> selecDemandtList(DemandMaterielExample m) {
		return DemandMaterielMapper.selectByExample(m);
	}

	@Override
	public void updateProcurementPlanMateriel(ProcurementPlanMateriel record) {
		ProcurementPlanMaterielMapper.updateByPrimaryKeySelective(record);
		
	}

	@Override
	public void updateDemandMateriel(DemandMateriel demandMateriel) {
		DemandMaterielMapper.updateByPrimaryKeySelective(demandMateriel);
	}

}
