package com.congmai.zhgj.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.congmai.zhgj.web.dao.MaterielMapper;
import com.congmai.zhgj.web.model.Materiel;
import com.congmai.zhgj.web.model.MaterielExample;
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
	public Materiel selectById(Long id) {
		// TODO Auto-generated method stub
		return null;
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
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Materiel> selectList(MaterielExample m) {
		return MaterielMapper.selectByExample(m);
	}

	

}
