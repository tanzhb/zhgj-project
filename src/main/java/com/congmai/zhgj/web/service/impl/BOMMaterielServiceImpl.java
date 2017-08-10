package com.congmai.zhgj.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.congmai.zhgj.web.dao.BOMMaterielMapper;
import com.congmai.zhgj.web.model.BOMMateriel;
import com.congmai.zhgj.web.model.BOMMaterielExample;
import com.congmai.zhgj.web.model.Materiel;
import com.congmai.zhgj.web.service.BOMMaterielService;
import com.congmai.zhgj.web.service.MaterielService;

/**
 * 
 * @ClassName BOMMarerielServiceImpl
 * @Description BOM物料ServiceImpl
 * @author qfzhao
 * @Date 2017年7月28日 下午3:06:52
 * @version 1.0.0
 */
@Service
public class BOMMaterielServiceImpl implements BOMMaterielService {
    @Resource
    private BOMMaterielMapper BOMMaterielMapper;
    
    @Resource
    private MaterielService materielService;
    
	@Override
	public int insert(BOMMateriel model) {
		return BOMMaterielMapper.insert(model);
	}

	@Override
	public int update(BOMMateriel model) {
		return BOMMaterielMapper.updateByPrimaryKeySelective(model);
	}

	
	@Override
	public int delete(String serialNum) {
		return BOMMaterielMapper.deleteByPrimaryKey(serialNum);
	}

	@Override
	public BOMMateriel selectById(String serialNum) {
		return BOMMaterielMapper.selectByPrimaryKey(serialNum);
	}

	@Override
	public BOMMateriel selectOne() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BOMMateriel> selectList() {
		return BOMMaterielMapper.selectByExample(null);
	}

	@Override
	public List<BOMMateriel> selectList(BOMMaterielExample m) {
		return BOMMaterielMapper.selectByExample(m);
	}

	@Override
	public void deleteBOMMateriels(String ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void betchInsertBOM(Materiel materiel, List<BOMMateriel> BOM) {
		//新增bom，物料需升级版本
		materielService.updateVersion(materiel);
		
		for(BOMMateriel b:BOM){
			BOMMaterielMapper.insert(b);
		}
		
	}

}
