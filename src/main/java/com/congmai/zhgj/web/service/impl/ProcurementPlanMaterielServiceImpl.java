package com.congmai.zhgj.web.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.web.model.ProcurementPlanMateriel;
import com.congmai.zhgj.web.model.ProcurementPlanMaterielExample;
import com.congmai.zhgj.web.model.ProcurementPlanMaterielExample.Criteria;
import com.congmai.zhgj.web.service.ProcurementPlanMaterielService;
import com.congmai.zhgj.web.dao.ProcurementPlanMaterielMapper;

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
	public void betchInsertProcurementPlanMateriel(List<ProcurementPlanMateriel> procurementPlanMateriel) {
		if(!CollectionUtils.isEmpty(procurementPlanMateriel)){
			//删除之前的附件
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

}
