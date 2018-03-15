package com.congmai.zhgj.web.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.web.model.ProcurementPlan;
import com.congmai.zhgj.web.model.ProcurementPlanExample;
import com.congmai.zhgj.web.service.ProcurementPlanService;
import com.congmai.zhgj.web.dao.ProcurementPlanMapper;

/**
 * 
 * @ClassName ServiceImpl
 * @Description 采购计划ServiceImpl
 * @author qfzhao
 * @Date 2017年7月28日 下午3:06:52
 * @version 1.0.0
 */
@Service
public class ProcurementPlanServiceImpl implements ProcurementPlanService {
    @Resource
    private ProcurementPlanMapper ProcurementPlanMapper;
    
	@Override
	public int insert(ProcurementPlan model) {
		return ProcurementPlanMapper.insert(model);
	}

	@Override
	public int update(ProcurementPlan model) {
		return ProcurementPlanMapper.updateByPrimaryKeySelective(model);
	}

	
	@Override
	public int delete(String serialNum) {
		return ProcurementPlanMapper.deleteByPrimaryKey(serialNum);
	}

	@Override
	public ProcurementPlan selectById(String serialNum) {
		return ProcurementPlanMapper.selectByPrimaryKey(serialNum);
	}

	@Override
	public ProcurementPlan selectOne() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProcurementPlan> selectList() {
		return ProcurementPlanMapper.selectByExample(null);
	}

	@Override
	public List<ProcurementPlan> selectList(ProcurementPlanExample m) {
		return ProcurementPlanMapper.selectByExample(m);
	}

	@Override
	public void deleteProcurementPlans(String ids) {
		List<String> idList = ApplicationUtils.getIdList(ids);
		for(String id : idList){
			ProcurementPlan m = new ProcurementPlan();
			m.setSerialNum(id);
			m.setDelFlg("1");
			m.setUpdateTime(new Date());
			ProcurementPlanMapper.updateByPrimaryKeySelective(m);
		}

	}

	@Override
	public void updateProcurementPlan(ProcurementPlan p) {
		// TODO Auto-generated method stub
		
	}


}
