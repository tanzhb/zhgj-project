package com.congmai.zhgj.web.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.congmai.zhgj.web.dao.MaterielFileMapper;
import com.congmai.zhgj.web.model.Materiel;
import com.congmai.zhgj.web.model.MaterielExample;
import com.congmai.zhgj.web.model.MaterielFile;
import com.congmai.zhgj.web.model.MaterielFileExample;
import com.congmai.zhgj.web.model.MaterielFileExample.Criteria;
import com.congmai.zhgj.web.service.MaterielFileService;
import com.congmai.zhgj.web.service.MaterielService;

/**
 * 
 * @ClassName MaterielFileServiceImpl
 * @Description 物料附件ServiceImpl
 * @author qfzhao
 * @Date 2017年8月11日 下午4:52:15
 * @version 1.0.0
 */
@Service
public class MaterielFileServiceImpl implements MaterielFileService {
    @Resource
    private MaterielFileMapper MaterielFileMapper;
    
    @Resource
    private MaterielService materielService;
    
	@Override
	public int insert(MaterielFile model) {
		return MaterielFileMapper.insert(model);
	}

	@Override
	public int update(MaterielFile model) {
		return MaterielFileMapper.updateByPrimaryKeySelective(model);
	}

	
	@Override
	public int delete(String serialNum) {
		return MaterielFileMapper.deleteByPrimaryKey(serialNum);
	}

	@Override
	public MaterielFile selectById(String serialNum) {
		return MaterielFileMapper.selectByPrimaryKey(serialNum);
	}

	@Override
	public MaterielFile selectOne() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MaterielFile> selectList() {
		return MaterielFileMapper.selectByExample(null);
	}

	@Override
	public List<MaterielFile> selectList(MaterielFileExample m) {
		return MaterielFileMapper.selectByExample(m);
	}

	@Override
	public void deleteMaterielFiles(String ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void betchInsertMaterielFiles(List<MaterielFile> Files) {
		//删除之前的附件
		MaterielFile m = new MaterielFile();
		m.setDelFlg("1");
		m.setUpdateTime(new Date());
		m.setUpdater(Files.get(0).getCreator());
		
		MaterielFileExample ex =new MaterielFileExample();
    	Criteria criteria =  ex.createCriteria();
    	criteria.andMaterielIdEqualTo(Files.get(0).getMaterielId());
		
		MaterielFileMapper.updateByExample(m, ex);
		
		for(MaterielFile b:Files){
			MaterielFileMapper.insert(b);
		}
		
	}

}
