package com.congmai.zhgj.web.service;

import java.util.List;
import java.util.Map;

import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.ContractVO;
import com.congmai.zhgj.web.model.DeliveryMaterielVO;
import com.congmai.zhgj.web.model.DeliveryTransportVO;
import com.congmai.zhgj.web.model.DeliveryVO;
import com.congmai.zhgj.web.model.OrderMateriel;
import com.congmai.zhgj.web.model.OrderMaterielExample;
import com.congmai.zhgj.web.model.TakeDeliveryVO;
import com.congmai.zhgj.web.model.User;
import com.congmai.zhgj.web.model.Warehouse;

/**
 * 
 * @ClassName ContractService
 * @Description  合同 业务 接口
 * @author czw
 */
public interface DeliveryService extends GenericService<DeliveryMaterielVO, String> {
	
	/**
	 * 保存发货物料
	 * @param contractVO
	 */
    public void insertDeliveryMateriel(DeliveryMaterielVO deliveryMaterielVO);
    
    
    
    public void deleteOldDeliveryMateriel(List<String> idList);
    
    /**
	 * 保存发货物料
	 * @param contractVO
	 */
    public void updateDeliveryMateriel(DeliveryMaterielVO deliveryMaterielVO);
    
    /**
     * 查询用户合同
     * @param userId（用户id）
     * @return
     */
    /*public List<ContractVO> queryContractList(String userId);*/
    
    
    /**
     * 
     * @Description 批量删除 合同
     * @param ids
     * @return
     */
	public void deleteDeliveryS(String ids);
	
	
	/**
	 * 根据id查询合同对象
	 * @param id
	 * @return
	 */
	public DeliveryMaterielVO selectDeliveryMaterielById(String id);
	
	
	
	List<DeliveryMaterielVO> selectList(String serialNum);
	
	
	
	public Warehouse queryAddressById(String serialNum);
	
	
	
	public void insertBasicInfo(DeliveryVO record);
	
	
	public void insertBasicInfoPartII(DeliveryTransportVO deliveryTransport);
	
	
	public void insertBasicInfoPartIII(TakeDeliveryVO takeDelivery);
	
	
	public void updateBasicInfo(DeliveryVO record);
	
	
	public void updateBasicInfoPartII(DeliveryTransportVO deliveryTransport);
	
	
	public void updateBasicInfoPartIII(TakeDeliveryVO takeDelivery);
	
	
	public DeliveryVO selectDetailById(String id);
	
	
	
	public List<DeliveryVO> findAllDeliveryList(String username);
	
	
	List<DeliveryMaterielVO> selectListForDetail(String serialNum);
	
	
	
   public String selectOrderId(String orderMaterielSerialNum);
   
   
   
   public List<String> queryDeliveryMaterielDelete(String deliverySerial,String orderSerial);
	
  
   public void goDelivery(Map<String,Object> map);
}
