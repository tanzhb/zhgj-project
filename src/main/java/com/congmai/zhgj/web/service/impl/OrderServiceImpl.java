package com.congmai.zhgj.web.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.log.annotation.OperationLog;
import com.congmai.zhgj.web.dao.ContractMapper;
import com.congmai.zhgj.web.dao.OrderInfoMapper;
import com.congmai.zhgj.web.model.ContractVO;
import com.congmai.zhgj.web.model.OrderInfo;
import com.congmai.zhgj.web.model.OrderInfoExample;
import com.congmai.zhgj.web.model.OrderInfo;
import com.congmai.zhgj.web.model.User;
import com.congmai.zhgj.web.model.OrderInfoExample.Criteria;
import com.congmai.zhgj.web.service.OrderService;

/**
 * 
 * @ClassName MarerielServiceImpl
 * @Description 订单ServiceImpl
 * @author qfzhao
 * @Date 2017年7月28日 下午3:06:52
 * @version 1.0.0
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderInfoMapper OrderInfoMapper;
  //合同的dao
  	@Resource
  	private ContractMapper contractMapper;
    
	@Override
	@OperationLog(operateType = "add" ,operationDesc = "订单新增" ,objectSerial= "{serialNum}")
	public int insert(OrderInfo model) {
		return OrderInfoMapper.insert(model);
	}

	@Override
	public int update(OrderInfo model) {
		return OrderInfoMapper.updateByPrimaryKeySelective(model);
	}

	
	@Override
	public int delete(String serialNum) {
		return OrderInfoMapper.deleteByPrimaryKey(serialNum);
	}

	@Override
	public OrderInfo selectById(String serialNum) {
		return OrderInfoMapper.selectByPrimaryKey(serialNum);
	}

	@Override
	public OrderInfo selectOne() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderInfo> selectList() {
		return OrderInfoMapper.selectByExample(null);
	}

	@Override
	public List<OrderInfo> selectList(OrderInfoExample m) {
		return OrderInfoMapper.selectByExample(m);
	}
	
	@Override
	public List<OrderInfo> selectFramList(OrderInfo m) {
		return OrderInfoMapper.selectFramByExample(m);
	}
	
	@Override
	public List<OrderInfo> selectCommenList(OrderInfo m) {
		return OrderInfoMapper.selectCommenByExample(m);
	}

	@Override
	public void deleteOrderInfos(String ids) {
		List<String> idList = ApplicationUtils.getIdList(ids);
		for(String id : idList){
			OrderInfo m = new OrderInfo();
			m.setSerialNum(id);
			m.setDelFlg("1");
			m.setUpdateTime(new Date());
			OrderInfoMapper.updateByPrimaryKeySelective(m);
		}

	}

	@Override
	public void insertContract(ContractVO contract) {
		OrderInfo m = new OrderInfo();
		m.setSerialNum(contract.getOrderSerial());
		m.setContractSerial(contract.getId());
		m.setUpdateTime(new Date());
		OrderInfoMapper.updateByPrimaryKeySelective(m);
		
		contractMapper.insertContract(contract);
	}

	@Override
	public void updateContract(ContractVO contract) {
		contractMapper.updateContract4order(contract);
	}
	

}
