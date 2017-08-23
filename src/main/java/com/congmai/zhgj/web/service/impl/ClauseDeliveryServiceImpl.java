package com.congmai.zhgj.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.congmai.zhgj.web.dao.ClauseDeliveryMapper;
import com.congmai.zhgj.web.model.ClauseDelivery;
import com.congmai.zhgj.web.model.ClauseDeliveryExample.Criteria;
import com.congmai.zhgj.web.model.ClauseDeliveryExample;
import com.congmai.zhgj.web.service.ClauseDeliveryService;

/**
 * 
 * @ClassName ClauseDeliveryServiceImpl
 * @Description 交付条款接口实现类
 * @author qfzhao
 * @Date 2017年8月22日 下午4:07:04
 * @version 1.0.0
 */
@Service
public class ClauseDeliveryServiceImpl  implements
		ClauseDeliveryService {

	@Resource
	private ClauseDeliveryMapper clauseDeliveryMapper;

	@Override
	public int insert(ClauseDelivery model) {
		return clauseDeliveryMapper.insert(model);
	}

	@Override
	public int update(ClauseDelivery model) {
		return clauseDeliveryMapper.updateByPrimaryKeySelective(model);
	}

	
	@Override
	public int delete(String serialNum) {
		return clauseDeliveryMapper.deleteByPrimaryKey(serialNum);
	}

	@Override
	public ClauseDelivery selectById(String serialNum) {
		return clauseDeliveryMapper.selectByPrimaryKey(serialNum);
	}

	@Override
	public ClauseDelivery selectOne() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ClauseDelivery> selectList() {
		return clauseDeliveryMapper.selectByExample(null);
	}

	@Override
	public ClauseDelivery selectByContractId(String id) {
		ClauseDeliveryExample ex = new ClauseDeliveryExample();
		Criteria criteria =  ex.createCriteria();
    	criteria.andContractSerialEqualTo(id);
		List<ClauseDelivery> list = clauseDeliveryMapper.selectByExample(ex);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}

}