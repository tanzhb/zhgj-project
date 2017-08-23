package com.congmai.zhgj.web.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.congmai.zhgj.web.dao.OrderFileMapper;
import com.congmai.zhgj.web.model.OrderFile;
import com.congmai.zhgj.web.model.OrderFileExample;
import com.congmai.zhgj.web.model.OrderFileExample.Criteria;
import com.congmai.zhgj.web.service.OrderFileService;
import com.congmai.zhgj.web.service.OrderService;

/**
 * 
 * @ClassName OrderFileServiceImpl
 * @Description 订单附件ServiceImpl
 * @author qfzhao
 * @Date 2017年8月11日 下午4:52:15
 * @version 1.0.0
 */
@Service
public class OrderFileServiceImpl implements OrderFileService {
    @Resource
    private OrderFileMapper OrderFileMapper;
    
	@Override
	public int insert(OrderFile model) {
		return OrderFileMapper.insert(model);
	}

	@Override
	public int update(OrderFile model) {
		return OrderFileMapper.updateByPrimaryKeySelective(model);
	}

	
	@Override
	public int delete(String serialNum) {
		return OrderFileMapper.deleteByPrimaryKey(serialNum);
	}

	@Override
	public OrderFile selectById(String serialNum) {
		return OrderFileMapper.selectByPrimaryKey(serialNum);
	}

	@Override
	public OrderFile selectOne() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderFile> selectList() {
		return OrderFileMapper.selectByExample(null);
	}

	@Override
	public List<OrderFile> selectList(OrderFileExample m) {
		return OrderFileMapper.selectByExample(m);
	}

	@Override
	public void deleteOrderFiles(String ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void betchInsertOrderFiles(List<OrderFile> Files) {
		if(!CollectionUtils.isEmpty(Files)){
			//删除之前的附件
			OrderFile m = new OrderFile();
			m.setDelFlg("1");
			m.setUpdateTime(new Date());
			m.setUpdater(Files.get(0).getCreator());
			
			OrderFileExample ex =new OrderFileExample();
	    	Criteria criteria =  ex.createCriteria();
	    	criteria.andOrderSerialEqualTo(Files.get(0).getOrderSerial());
			
			OrderFileMapper.updateByExampleSelective(m, ex);
			
			for(OrderFile b:Files){
				OrderFileMapper.insert(b);
			}
		}
	}

}
