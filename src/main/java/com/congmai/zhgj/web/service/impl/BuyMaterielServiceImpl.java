package com.congmai.zhgj.web.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.congmai.zhgj.web.dao.BuyMaterielMapper;
import com.congmai.zhgj.web.model.BuyMateriel;
import com.congmai.zhgj.web.model.BuyMaterielExample;
import com.congmai.zhgj.web.model.BuyMaterielExample.Criteria;
import com.congmai.zhgj.web.service.BuyMaterielService;
import com.congmai.zhgj.web.service.MaterielService;

/**
 * 
 * @ClassName BuyMaterielServiceImpl
 * @Description 采购商物料ServiceImpl
 * @author qfzhao
 * @Date 2017年8月11日 下午4:52:15
 * @version 1.0.0
 */
@Service
public class BuyMaterielServiceImpl implements BuyMaterielService {
    @Resource
    private BuyMaterielMapper BuyMaterielMapper;
    
    @Resource
    private MaterielService materielService;
    
	@Override
	public int insert(BuyMateriel model) {
		return BuyMaterielMapper.insert(model);
	}

	@Override
	public int update(BuyMateriel model) {
		return BuyMaterielMapper.updateByPrimaryKeySelective(model);
	}

	
	@Override
	public int delete(String serialNum) {
		return BuyMaterielMapper.deleteByPrimaryKey(serialNum);
	}

	@Override
	public BuyMateriel selectById(String serialNum) {
		return BuyMaterielMapper.selectByPrimaryKey(serialNum);
	}

	@Override
	public BuyMateriel selectOne() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BuyMateriel> selectList() {
		return BuyMaterielMapper.selectByExample(null);
	}

	@Override
	public List<BuyMateriel> selectList(BuyMaterielExample m) {
		return BuyMaterielMapper.selectByExample(m);
	}

	@Override
	public void deleteBuyMateriels(String ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void betchInsertBuyMateriels(List<BuyMateriel> Files) {
		//删除之前的供应商物料
		BuyMateriel m = new BuyMateriel();
		m.setDelFlg("1");
		m.setUpdateTime(new Date());
		m.setUpdater(Files.get(0).getCreator());
		
		BuyMaterielExample ex =new BuyMaterielExample();
    	Criteria criteria =  ex.createCriteria();
    	criteria.andMaterielIdEqualTo(Files.get(0).getMaterielId());
		
		BuyMaterielMapper.updateByExampleSelective(m, ex);
		
		for(BuyMateriel b:Files){
			BuyMaterielMapper.insert(b);
		}
		
	}

	@Override
	public List<BuyMateriel> chooseMateriel(String ids) {
		if(StringUtils.isNotEmpty(ids)){
			List<String> idList = Arrays.asList(ids.split(","));
			BuyMaterielExample example = new BuyMaterielExample();
			example.createCriteria().andSerialNumIn(idList).andDelFlgEqualTo("0");
			return BuyMaterielMapper.selectByExample(example);
		}
		return null;
	}

}
