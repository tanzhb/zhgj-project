package com.congmai.zhgj.web.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.congmai.zhgj.web.dao.ClauseFrameworkMapper;
import com.congmai.zhgj.web.model.ClauseFramework;
import com.congmai.zhgj.web.model.ClauseFrameworkExample;
import com.congmai.zhgj.web.model.ClauseFrameworkExample.Criteria;
import com.congmai.zhgj.web.service.ClauseFrameworkService;

/**
 * 
 * @ClassName ClauseFrameworkServiceImpl
 * @Description 框架条款ServiceImpl
 * @author qfzhao
 * @Date 2017年7月28日 下午3:06:52
 * @version 1.0.0
 */
@Service
public class ClauseFrameworkServiceImpl implements ClauseFrameworkService {
    @Resource
    private ClauseFrameworkMapper ClauseFrameworkMapper;
    
	@Override
	public int insert(ClauseFramework model) {
		return ClauseFrameworkMapper.insert(model);
	}

	@Override
	public int update(ClauseFramework model) {
		return ClauseFrameworkMapper.updateByPrimaryKeySelective(model);
	}

	
	@Override
	public int delete(String serialNum) {
		return ClauseFrameworkMapper.deleteByPrimaryKey(serialNum);
	}

	@Override
	public ClauseFramework selectById(String serialNum) {
		return ClauseFrameworkMapper.selectByPrimaryKey(serialNum);
	}

	@Override
	public ClauseFramework selectOne() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ClauseFramework> selectList() {
		return ClauseFrameworkMapper.selectByExample(null);
	}

	@Override
	public List<ClauseFramework> selectList(ClauseFrameworkExample m) {
		return ClauseFrameworkMapper.selectByExample(m);
	}

	@Override
	public void deleteClauseFrameworks(String ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void betchInsertBOM(List<ClauseFramework> ClauseFrameworks) {
		if(!CollectionUtils.isEmpty(ClauseFrameworks)){
			//删除之前的框架合同
			ClauseFramework m = new ClauseFramework();
			m.setDelFlg("1");
			m.setUpdateTime(new Date());
			m.setUpdater(ClauseFrameworks.get(0).getCreator());
			
			ClauseFrameworkExample ex =new ClauseFrameworkExample();
	    	Criteria criteria =  ex.createCriteria();
	    	criteria.andContractSerialEqualTo(ClauseFrameworks.get(0).getContractSerial());
			
	    	ClauseFrameworkMapper.updateByExampleSelective(m, ex);
			
			for(ClauseFramework b:ClauseFrameworks){
				ClauseFrameworkMapper.insert(b);
			}
		}
		
	}

}
