package com.congmai.zhgj.web.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.congmai.zhgj.web.dao.SupplyMaterielMapper;
import com.congmai.zhgj.web.model.Materiel;
import com.congmai.zhgj.web.model.MaterielExample;
import com.congmai.zhgj.web.model.SupplyMateriel;
import com.congmai.zhgj.web.model.SupplyMaterielExample;
import com.congmai.zhgj.web.model.SupplyMaterielExample.Criteria;
import com.congmai.zhgj.web.service.SupplyMaterielService;
import com.congmai.zhgj.web.service.MaterielService;

/**
 * 
 * @ClassName SupplyMaterielServiceImpl
 * @Description 供应商物料ServiceImpl
 * @author qfzhao
 * @Date 2017年8月11日 下午4:52:15
 * @version 1.0.0
 */
@Service
public class SupplyMaterielServiceImpl implements SupplyMaterielService {
    @Resource
    private SupplyMaterielMapper SupplyMaterielMapper;
    
    @Resource
    private MaterielService materielService;
    
	@Override
	public int insert(SupplyMateriel model) {
		return SupplyMaterielMapper.insert(model);
	}

	@Override
	public int update(SupplyMateriel model) {
		return SupplyMaterielMapper.updateByPrimaryKeySelective(model);
	}

	
	@Override
	public int delete(String serialNum) {
		return SupplyMaterielMapper.deleteByPrimaryKey(serialNum);
	}

	@Override
	public SupplyMateriel selectById(String serialNum) {
		return SupplyMaterielMapper.selectByPrimaryKey(serialNum);
	}

	@Override
	public SupplyMateriel selectOne() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SupplyMateriel> selectList() {
		return SupplyMaterielMapper.selectByExample(null);
	}

	@Override
	public List<SupplyMateriel> selectList(SupplyMaterielExample m) {
		return SupplyMaterielMapper.selectByExample(m);
	}

	@Override
	public void deleteSupplyMateriels(String ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void betchInsertSupplyMateriels(List<SupplyMateriel> Files) {
		//删除之前的供应商物料
		SupplyMateriel m = new SupplyMateriel();
		m.setDelFlg("1");
		m.setUpdateTime(new Date());
		m.setUpdater(Files.get(0).getCreator());
		
		SupplyMaterielExample ex =new SupplyMaterielExample();
    	Criteria criteria =  ex.createCriteria();
    	criteria.andMaterielIdEqualTo(Files.get(0).getMaterielId());
		
		SupplyMaterielMapper.updateByExampleSelective(m, ex);
		
		for(SupplyMateriel b:Files){
			SupplyMaterielMapper.insert(b);
		}
		
	}

}
