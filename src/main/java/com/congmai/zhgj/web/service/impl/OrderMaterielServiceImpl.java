package com.congmai.zhgj.web.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.web.model.OrderMateriel;
import com.congmai.zhgj.web.model.OrderMaterielExample;
import com.congmai.zhgj.web.model.OrderMaterielExample.Criteria;
import com.congmai.zhgj.web.service.OrderMaterielService;
import com.congmai.zhgj.web.dao.OrderMaterielMapper;

/**
 * 
 * @ClassName MarerielServiceImpl
 * @Description 订单物料ServiceImpl
 * @author qfzhao
 * @Date 2017年7月28日 下午3:06:52
 * @version 1.0.0
 */
@Service
public class OrderMaterielServiceImpl implements OrderMaterielService {
    @Resource
    private OrderMaterielMapper OrderMaterielMapper;
    
	@Override
	public int insert(OrderMateriel model) {
		return OrderMaterielMapper.insert(model);
	}

	@Override
	public int update(OrderMateriel model) {
		return OrderMaterielMapper.updateByPrimaryKeySelective(model);
	}

	
	@Override
	public int delete(String serialNum) {
		return OrderMaterielMapper.deleteByPrimaryKey(serialNum);
	}

	@Override
	public OrderMateriel selectById(String serialNum) {
		return OrderMaterielMapper.selectByPrimaryKey(serialNum);
	}

	@Override
	public OrderMateriel selectOne() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderMateriel> selectList() {
		return OrderMaterielMapper.selectByExample(null);
	}

	@Override
	public List<OrderMateriel> selectList(OrderMaterielExample m) {
		return OrderMaterielMapper.selectByExample(m);
	}

	@Override
	public void deleteOrderMateriels(String ids) {
		List<String> idList = ApplicationUtils.getIdList(ids);
		for(String id : idList){
			OrderMateriel m = new OrderMateriel();
			m.setSerialNum(id);
			m.setDelFlg("1");
			m.setUpdateTime(new Date());
			OrderMaterielMapper.updateByPrimaryKeySelective(m);
		}

	}

	@Override
	public void betchInsertOrderMateriel(List<OrderMateriel> orderMateriel) {
		if(!CollectionUtils.isEmpty(orderMateriel)){
			//删除之前的附件
			OrderMateriel m = new OrderMateriel();
			m.setDelFlg("1");
			m.setUpdateTime(new Date());
			m.setUpdater(orderMateriel.get(0).getCreator());
			
			OrderMaterielExample ex =new OrderMaterielExample();
	    	Criteria criteria =  ex.createCriteria();
	    	criteria.andOrderSerialEqualTo(orderMateriel.get(0).getOrderSerial());
			
	    	OrderMaterielMapper.updateByExampleSelective(m, ex);
			int i = 0;
			for(OrderMateriel b:orderMateriel){
				i++;
				b.setSort(i);
				OrderMaterielMapper.insert(b);
			}
		}
		
	}

}
