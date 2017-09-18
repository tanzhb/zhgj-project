package com.congmai.zhgj.web.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.web.dao.DeliveryMapper;
import com.congmai.zhgj.web.model.DeliveryMaterielVO;
import com.congmai.zhgj.web.model.DeliveryTransportVO;
import com.congmai.zhgj.web.model.DeliveryVO;
import com.congmai.zhgj.web.model.TakeDeliveryVO;
import com.congmai.zhgj.web.model.Warehouse;
import com.congmai.zhgj.web.service.DeliveryService;

/**
 * 
 * @ClassName DeliveryServiceImpl
 * @Description 发货Service实现类
 * @author czw
 */
@Service
public class DeliveryServiceImpl extends GenericServiceImpl<DeliveryMaterielVO, String> implements
		DeliveryService {

	//发货的dao
	@Resource
	private DeliveryMapper deliveryMapper;

	@Override
	public GenericDao<DeliveryMaterielVO, String> getDao() {
		return deliveryMapper;
	}

	
	/**
	 * 保存发货物料
	 * @param contractVO
	 */
	@Override
	public void insertDeliveryMateriel(DeliveryMaterielVO deliveryMaterielVO) {
		// TODO Auto-generated method stub
		deliveryMapper.insertDeliveryMateriel(deliveryMaterielVO);
	}
	
	
	/**
     * 删除旧的发货物料
     * @param idList
     */
	 @Override
	public void deleteOldDeliveryMateriel(List<String> idList){
		// TODO Auto-generated method stub
		 deliveryMapper.deleteOldDeliveryMateriel(idList);
	}


	 /**
	 * 编辑发货物料
	 * @param contractVO
	 */
	@Override
	public void updateDeliveryMateriel(DeliveryMaterielVO deliveryMaterielVO) {
		// TODO Auto-generated method stub
		deliveryMapper.updateDeliveryMateriel(deliveryMaterielVO);
	}


	/**
     * 
     * @Description 批量删除 发货
     * @param ids
     * @return
     */
	@Override
	public void deleteDeliveryS(String ids) {
		List<String> idList = ApplicationUtils.getIdList(ids);
		deliveryMapper.deleteDeliveryS(idList);
	}
	

	/**
	 * 根据id查询发货对象
	 * @param id
	 * @return
	 */
	@Override
	public DeliveryMaterielVO selectDeliveryMaterielById(String id) {
		// TODO Auto-generated method stub
		
		return deliveryMapper.selectDeliveryMaterielById(id);
	}

	/**
	 * 查询发货物料列表
	 * @param serialNum
	 * @return
	 */
	@Override
	public List<DeliveryMaterielVO> selectList(String serialNum) {
		// TODO Auto-generated method stub
		return deliveryMapper.selectByExample(serialNum);
	}


	/**
	 * 查询仓库地址
	 * @param serialNum
	 * @return
	 */
	@Override
	public Warehouse queryAddressById(String serialNum) {
		// TODO Auto-generated method stub
		return deliveryMapper.queryAddressById(serialNum);
	}


	/**
	 * 添加基本信息
	 * @param record
	 */
	@Override
	public void insertBasicInfo(DeliveryVO record) {
		// TODO Auto-generated method stub
		deliveryMapper.insertBasicInfo(record);
	}


	/**
	 * 添加基本信息第二部分
	 * @param deliveryTransport
	 */
	@Override
	public void insertBasicInfoPartII(DeliveryTransportVO deliveryTransport) {
		// TODO Auto-generated method stub
		deliveryMapper.insertBasicInfoPartII(deliveryTransport);
	}


	/**
	 * 添加基本信息第三部分
	 * @param takeDelivery
	 */
	@Override
	public void insertBasicInfoPartIII(TakeDeliveryVO takeDelivery) {
		// TODO Auto-generated method stub
		deliveryMapper.insertBasicInfoPartIII(takeDelivery);
	}
	
	
	/**
	 * 编辑基本信息
	 * @param record
	 */
	@Override
	public void updateBasicInfo(DeliveryVO record) {
		// TODO Auto-generated method stub
		deliveryMapper.updateBasicInfo(record);
	}


	/**
	 * 编辑基本信息第二部分
	 * @param deliveryTransport
	 */
	@Override
	public void updateBasicInfoPartII(DeliveryTransportVO deliveryTransport) {
		// TODO Auto-generated method stub
		deliveryMapper.updateBasicInfoPartII(deliveryTransport);
	}


	/**
	 * 编辑基本信息第三部分
	 * @param takeDelivery
	 */
	@Override
	public void updateBasicInfoPartIII(TakeDeliveryVO takeDelivery) {
		// TODO Auto-generated method stub
		deliveryMapper.updateBasicInfoPartIII(takeDelivery);
	}


	/**
	 * 通过id查询详情
	 * @param id
	 * @return
	 */
	@Override
	public DeliveryVO selectDetailById(String id) {
		// TODO Auto-generated method stub
		return deliveryMapper.selectDetailById(id);
	}


	/**
	 * 查询发货列表
	 * @param username
	 * @return
	 */
	@Override
	public List<DeliveryVO> findAllDeliveryList(String username) {
		// TODO Auto-generated method stub
		return deliveryMapper.findAllDeliveryList(username);
	}
	

	/**
	 * 查询发货列表(加入数据权限)
	 */
	@Override
	public List<DeliveryVO> findAllDeliveryList(DeliveryVO deliveryVO) {
		
		return deliveryMapper.findAllDeliveryList(deliveryVO);
	}


	/**
	 * 查询发货详情的发货物料
	 * @param serialNum
	 * @return
	 */
	@Override
	public List<DeliveryMaterielVO> selectListForDetail(String serialNum) {
		// TODO Auto-generated method stub
		return deliveryMapper.selectListForDetail(serialNum);
	}


	/**
	 * 查询发货物料的归属订单id
	 * @param orderMaterielSerialNum
	 * @return
	 */
	@Override
	public String selectOrderId(String orderMaterielSerialNum) {
		// TODO Auto-generated method stub
		return deliveryMapper.selectOrderId(orderMaterielSerialNum);
	}


	/**
    * 查询需要删除的发货物料
    * @param deliverySerial
    * @param orderSerial
    * @return
    */
	@Override
	public List<String> queryDeliveryMaterielDelete(String deliverySerial,String orderSerial) {
		// TODO Auto-generated method stub
		Map<String,Object> map=new HashMap<String,Object>();
		 map.put("deliverySerial", deliverySerial);
		 map.put("orderSerial", orderSerial);
		 
		return deliveryMapper.queryDeliveryMaterielDelete(map);
	}


	/**
    * 确认发货
    * @param map
    */
	@Override
	public void goDelivery(Map<String,Object> map) {
		// TODO Auto-generated method stub
		deliveryMapper.goDelivery(map);
	}


	@Override
	public void updateOrderWhenDeliveryComlete(Map<String,Object> map) {
		// TODO Auto-generated method stub
		deliveryMapper.updateOrderWhenDeliveryComlete(map);
	}
}