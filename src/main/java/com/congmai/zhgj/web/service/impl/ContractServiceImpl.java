package com.congmai.zhgj.web.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.web.dao.ContractMapper;
import com.congmai.zhgj.web.dao.UserMapper;
import com.congmai.zhgj.web.model.ContractVO;
import com.congmai.zhgj.web.model.OrderInfo;
import com.congmai.zhgj.web.model.User;
import com.congmai.zhgj.web.model.UserExample;
import com.congmai.zhgj.web.service.ContractService;
import com.congmai.zhgj.web.service.UserService;

/**
 * 
 * @ClassName ContractServiceImpl
 * @Description 合同Service实现类
 * @author czw
 */
@Service
public class ContractServiceImpl extends GenericServiceImpl<ContractVO, String> implements
		ContractService {

	//合同的dao
	@Resource
	private ContractMapper contractMapper;

	@Override
	public GenericDao<ContractVO, String> getDao() {
		return contractMapper;
	}

	
	/**
	 * 添加用户合同
	 * @param contractVO
	 */
 @Override
	public void insertContract(ContractVO contractVO) {
		// TODO Auto-generated method stub
	 contractMapper.insertContract(contractVO);
	}
	 
	 /**
	  * 查询用户合同
	  * @param userId（用户id）
	  * @return
	  */
	public List<ContractVO> queryContractList(String userId) {
		// TODO Auto-generated method stub
		
		return contractMapper.queryContractList(userId);
	}

	
	
	@Override
	public List<ContractVO> querySaleContractList(String userId) {
		// TODO Auto-generated method stub
		return contractMapper.querySaleContractList(userId);
	}

	

	@Override
	public List<ContractVO> queryBuyContractList(String userId) {
		// TODO Auto-generated method stub
		return contractMapper.queryBuyContractList(userId);
	}


	@Override
	public List<OrderInfo> queryOrderInfo(String contractSerial) {
		// TODO Auto-generated method stub
		return contractMapper.queryOrderInfo(contractSerial);
	}


	/**
     * 
     * @Description 批量删除 合同
     * @param ids
     * @return
     */
	@Override
	public void deleteUserContractS(String ids) {
		List<String> idList = ApplicationUtils.getIdList(ids);
		contractMapper.deleteUserContractS(idList);
	}
	

	/**
	 * 根据id查询合同对象
	 * @param id
	 * @return
	 */
	@Override
	public ContractVO selectConbtractById(String id) {
		// TODO Auto-generated method stub
		
		return contractMapper.selectContractById(id);
	}

	
	/**
	 * 更新用户对象
	 * @param contractVO
	 */
	@Override
	public void updateContract(ContractVO contractVO) {
		// TODO Auto-generated method stub
		contractMapper.updateContract(contractVO);
	}
}