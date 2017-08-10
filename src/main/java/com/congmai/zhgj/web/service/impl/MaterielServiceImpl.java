package com.congmai.zhgj.web.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.web.dao.MaterielMapper;
import com.congmai.zhgj.web.model.Materiel;
import com.congmai.zhgj.web.model.MaterielExample;
import com.congmai.zhgj.web.model.User;
import com.congmai.zhgj.web.model.MaterielExample.Criteria;
import com.congmai.zhgj.web.service.MaterielService;

/**
 * 
 * @ClassName MarerielServiceImpl
 * @Description 物料ServiceImpl
 * @author qfzhao
 * @Date 2017年7月28日 下午3:06:52
 * @version 1.0.0
 */
@Service
public class MaterielServiceImpl implements MaterielService {
    @Resource
    private MaterielMapper MaterielMapper;
    
	@Override
	public int insert(Materiel model) {
		return MaterielMapper.insert(model);
	}

	@Override
	public int update(Materiel model) {
		return MaterielMapper.updateByPrimaryKeySelective(model);
	}

	
	@Override
	public int delete(String serialNum) {
		return MaterielMapper.deleteByPrimaryKey(serialNum);
	}

	@Override
	public Materiel selectById(String serialNum) {
		return MaterielMapper.selectByPrimaryKey(serialNum);
	}

	@Override
	public Materiel selectOne() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Materiel> selectList() {
		return MaterielMapper.selectByExample(null);
	}

	@Override
	public List<Materiel> selectList(MaterielExample m) {
		return MaterielMapper.selectByExample(m);
	}

	@Override
	public void deleteMateriels(String ids) {
		List<String> idList = ApplicationUtils.getIdList(ids);
		for(String id : idList){
			Materiel m = new Materiel();
			m.setSerialNum(id);
			m.setDelFlg("1");
			m.setUpdateTime(new Date());
			MaterielMapper.updateByPrimaryKeySelective(m);
		}

	}

	@Override
	public void updateVersion(Materiel materiel) {
		//当前版本更新为老版本-start
		Materiel m = new Materiel();
		m.setIsLatestVersion("0");
		m.setUpdateTime(new Date());
		m.setUpdater(materiel.getCreator());
		
		//更新条件为当前版本
		MaterielExample ex =new MaterielExample();
    	//and 条件1
    	Criteria criteria =  ex.createCriteria();
    	criteria.andIsLatestVersionEqualTo("1");
    	criteria.andMaterielIdEqualTo(materiel.getMaterielId());

		MaterielMapper.updateByExampleSelective(m, ex);
		//当前版本更新为老版本-end
		//插入最新版本
		MaterielMapper.insert(materiel);
	}
	/**
	 * 
	 * @Description 根据materielId查询最新版本物料
	 * @param materielId
	 * @return
	 */
	@Override
	public Materiel getMaterielInfoByMaterielId(String materielId) {
		MaterielExample m =new MaterielExample();
    	//and 条件1
    	Criteria criteria =  m.createCriteria();
    	criteria.andIsLatestVersionEqualTo("1");
    	criteria.andMaterielIdEqualTo(materielId);
    	criteria.andDelFlgEqualTo("0");
    	//排序字段
    	m.setOrderByClause("updateTime DESC");
    	List<Materiel> materielList = MaterielMapper.selectByExample(m);
    	
    	if(materielList==null||materielList.size()<1){
    		return null;
    	}
		return materielList.get(0);
	}

}
