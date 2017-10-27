package com.congmai.zhgj.web.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.congmai.zhgj.web.dao.ClauseSettlementDetailMapper;
import com.congmai.zhgj.web.model.ClauseSettlementDetail;
import com.congmai.zhgj.web.model.ClauseSettlementDetailExample;
import com.congmai.zhgj.web.model.ClauseSettlementDetailExample.Criteria;
import com.congmai.zhgj.web.service.ClauseSettlementDetailService;
import com.congmai.zhgj.web.service.OrderService;

/**
 * 
 * @ClassName ClauseSettlementDetailServiceImpl
 * @Description 结算条款明细ServiceImpl
 * @author qfzhao
 * @Date 2017年8月11日 下午4:52:15
 * @version 1.0.0
 */
@Service
public class ClauseSettlementDetailServiceImpl implements ClauseSettlementDetailService {
    @Resource
    private ClauseSettlementDetailMapper ClauseSettlementDetailMapper;
    
	@Override
	public int insert(ClauseSettlementDetail model) {
		return ClauseSettlementDetailMapper.insert(model);
	}

	@Override
	public int update(ClauseSettlementDetail model) {
		return ClauseSettlementDetailMapper.updateByPrimaryKeySelective(model);
	}

	
	@Override
	public int delete(String serialNum) {
		return ClauseSettlementDetailMapper.deleteByPrimaryKey(serialNum);
	}

	@Override
	public ClauseSettlementDetail selectById(String serialNum) {
		return ClauseSettlementDetailMapper.selectByPrimaryKey(serialNum);
	}

	@Override
	public ClauseSettlementDetail selectOne() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ClauseSettlementDetail> selectList() {
		return ClauseSettlementDetailMapper.selectByExample(null);
	}

	@Override
	public List<ClauseSettlementDetail> selectList(ClauseSettlementDetailExample m) {
		return ClauseSettlementDetailMapper.selectByExample(m);
	}

	@Override
	public void deleteClauseSettlementDetails(String ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void betchInsertClauseSettlementDetails(List<ClauseSettlementDetail> clauseSettlementDetails) {
		if(!CollectionUtils.isEmpty(clauseSettlementDetails)){
			//删除之前的附件
			ClauseSettlementDetail m = new ClauseSettlementDetail();
			m.setDelFlg("1");
			m.setUpdateTime(new Date());
			m.setUpdater(clauseSettlementDetails.get(0).getCreator());
			
			ClauseSettlementDetailExample ex =new ClauseSettlementDetailExample();
	    	Criteria criteria =  ex.createCriteria();
	    	criteria.andClauseSettlementSerialEqualTo(clauseSettlementDetails.get(0).getClauseSettlementSerial());
			
			ClauseSettlementDetailMapper.updateByExampleSelective(m, ex);
			
			int i=0;
			for(ClauseSettlementDetail b:clauseSettlementDetails){
				i++;
				b.setSort(i);
				ClauseSettlementDetailMapper.insert(b);
			}
		}
		
	}

}
