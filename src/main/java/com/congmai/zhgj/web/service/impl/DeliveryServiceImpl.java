package com.congmai.zhgj.web.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.web.dao.ContractMapper;
import com.congmai.zhgj.web.dao.DeliveryMapper;
import com.congmai.zhgj.web.dao.UserMapper;
import com.congmai.zhgj.web.model.ContractVO;
import com.congmai.zhgj.web.model.DeliveryMaterielVO;
import com.congmai.zhgj.web.model.DeliveryTransportVO;
import com.congmai.zhgj.web.model.DeliveryVO;
import com.congmai.zhgj.web.model.OrderMaterielExample;
import com.congmai.zhgj.web.model.TakeDeliveryVO;
import com.congmai.zhgj.web.model.User;
import com.congmai.zhgj.web.model.UserExample;
import com.congmai.zhgj.web.model.Warehouse;
import com.congmai.zhgj.web.service.ContractService;
import com.congmai.zhgj.web.service.DeliveryService;
import com.congmai.zhgj.web.service.UserService;

/**
 * 
 * @ClassName ContractServiceImpl
 * @Description 合同Service实现类
 * @author czw
 */
@Service
public class DeliveryServiceImpl extends GenericServiceImpl<DeliveryMaterielVO, String> implements
		DeliveryService {

	//合同的dao
	@Resource
	private DeliveryMapper deliveryMapper;

	@Override
	public GenericDao<DeliveryMaterielVO, String> getDao() {
		return deliveryMapper;
	}

	
	/**
	 * 添加用户合同
	 * @param contractVO
	 */
	@Override
	public void insertDeliveryMateriel(DeliveryMaterielVO deliveryMaterielVO) {
		// TODO Auto-generated method stub
		deliveryMapper.insertDeliveryMateriel(deliveryMaterielVO);
	}
	
	
	 
	 @Override
	public void deleteOldDeliveryMateriel(List<String> idList){
		// TODO Auto-generated method stub
		 deliveryMapper.deleteOldDeliveryMateriel(idList);
	}


	/**
	  * 查询用户合同
	  * @param userId（用户id）
	  * @return
	  */
	/*public List<ContractVO> queryContractList(String userId) {
		// TODO Auto-generated method stub
		
		return deliveryMapper.queryContractList(userId);
	}*/

	
	


	@Override
	public void updateDeliveryMateriel(DeliveryMaterielVO deliveryMaterielVO) {
		// TODO Auto-generated method stub
		deliveryMapper.updateDeliveryMateriel(deliveryMaterielVO);
	}


	/**
     * 
     * @Description 批量删除 合同
     * @param ids
     * @return
     */
	@Override
	public void deleteDeliveryS(String ids) {
		List<String> idList = ApplicationUtils.getIdList(ids);
		deliveryMapper.deleteDeliveryS(idList);
	}
	

	/**
	 * 根据id查询合同对象
	 * @param id
	 * @return
	 */
	@Override
	public DeliveryMaterielVO selectDeliveryMaterielById(String id) {
		// TODO Auto-generated method stub
		
		return deliveryMapper.selectDeliveryMaterielById(id);
	}


	@Override
	public List<DeliveryMaterielVO> selectList(String serialNum) {
		// TODO Auto-generated method stub
		return deliveryMapper.selectByExample(serialNum);
	}


	@Override
	public Warehouse queryAddressById(String serialNum) {
		// TODO Auto-generated method stub
		return deliveryMapper.queryAddressById(serialNum);
	}


	@Override
	public void insertBasicInfo(DeliveryVO record) {
		// TODO Auto-generated method stub
		deliveryMapper.insertBasicInfo(record);
	}


	@Override
	public void insertBasicInfoPartII(DeliveryTransportVO deliveryTransport) {
		// TODO Auto-generated method stub
		deliveryMapper.insertBasicInfoPartII(deliveryTransport);
	}


	@Override
	public void insertBasicInfoPartIII(TakeDeliveryVO takeDelivery) {
		// TODO Auto-generated method stub
		deliveryMapper.insertBasicInfoPartIII(takeDelivery);
	}
	
	
	@Override
	public void updateBasicInfo(DeliveryVO record) {
		// TODO Auto-generated method stub
		deliveryMapper.updateBasicInfo(record);
	}


	@Override
	public void updateBasicInfoPartII(DeliveryTransportVO deliveryTransport) {
		// TODO Auto-generated method stub
		deliveryMapper.updateBasicInfoPartII(deliveryTransport);
	}


	@Override
	public void updateBasicInfoPartIII(TakeDeliveryVO takeDelivery) {
		// TODO Auto-generated method stub
		deliveryMapper.updateBasicInfoPartIII(takeDelivery);
	}


	@Override
	public DeliveryVO selectDetailById(String id) {
		// TODO Auto-generated method stub
		return deliveryMapper.selectDetailById(id);
	}


	@Override
	public List<DeliveryVO> findAllDeliveryList(String username) {
		// TODO Auto-generated method stub
		return deliveryMapper.findAllDeliveryList(username);
	}


	@Override
	public List<DeliveryMaterielVO> selectListForDetail(String serialNum) {
		// TODO Auto-generated method stub
		return deliveryMapper.selectListForDetail(serialNum);
	}


	@Override
	public String selectOrderId(String orderMaterielSerialNum) {
		// TODO Auto-generated method stub
		return deliveryMapper.selectOrderId(orderMaterielSerialNum);
	}


	@Override
	public List<String> queryDeliveryMaterielDelete(String deliverySerial,String orderSerial) {
		// TODO Auto-generated method stub
		Map<String,Object> map=new HashMap<String,Object>();
		 map.put("deliverySerial", deliverySerial);
		 map.put("orderSerial", orderSerial);
		 
		return deliveryMapper.queryDeliveryMaterielDelete(map);
	}


	@Override
	public void goDelivery(Map<String,Object> map) {
		// TODO Auto-generated method stub
		deliveryMapper.goDelivery(map);
	}
}