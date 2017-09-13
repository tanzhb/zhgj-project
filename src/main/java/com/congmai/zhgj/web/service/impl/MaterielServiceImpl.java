package com.congmai.zhgj.web.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.web.dao.InvoiceBillingRecordMapper;
import com.congmai.zhgj.web.dao.MaterielMapper;
import com.congmai.zhgj.web.model.Materiel;
import com.congmai.zhgj.web.model.MaterielExample;
import com.congmai.zhgj.web.model.MaterielExample.Criteria;
import com.congmai.zhgj.web.model.MaterielSelectExample;
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
    @Resource
    private InvoiceBillingRecordMapper invoiceBillingRecordMapper;
    
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
	public List<Materiel> selectList(MaterielSelectExample m) {
		return MaterielMapper.selectBySelectExample(m);
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

	@Override
	public List<Materiel> chooseMateriel(String ids) {
		if(StringUtils.isNotEmpty(ids)){
			List<String> idList = Arrays.asList(ids.split(","));
			MaterielExample example = new MaterielExample();
			example.createCriteria().andSerialNumIn(idList).andDelFlgEqualTo("0");
			return MaterielMapper.selectByExample(example);
		}
		return null;
	}

	@Override
	public List<Materiel> selectMaterielByOrderSerial(String orderSerial,String orderSerial1) {
		List<Materiel>materiels=null;
		if(StringUtils.isNotEmpty(orderSerial)){
			Map<String,String> map=new HashMap<String,String>();
			map.put("orderSerial", orderSerial);
			map.put("invoiceSerial", (orderSerial1==null||orderSerial1.length()<66)?null:orderSerial1.substring(34, 66));
			materiels= MaterielMapper.selectMaterielByOrderSerial(map);
			if(materiels!=null&&materiels.size()>0){
				for(Materiel materiel:materiels){
					int billedCount=invoiceBillingRecordMapper.countBilledNum(materiel.getSerialNum());
					materiel.setCanBillAmount((Integer.parseInt(materiel.getAmount())-billedCount)+"");
				}
			}
		}
	
		return materiels;
	}

}
