package com.congmai.zhgj.web.dao;

import java.util.List;
import java.util.Map;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.web.model.ContractVO;
import com.congmai.zhgj.web.model.DeliveryMaterielVO;
import com.congmai.zhgj.web.model.DeliveryTransportVO;
import com.congmai.zhgj.web.model.DeliveryVO;
import com.congmai.zhgj.web.model.OrderMateriel;
import com.congmai.zhgj.web.model.OrderMaterielExample;
import com.congmai.zhgj.web.model.TakeDeliveryVO;
import com.congmai.zhgj.web.model.Warehouse;

/**
 * 
 * @ClassName 合同Dao接口
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author czw
 */
public interface DeliveryMapper extends GenericDao<DeliveryMaterielVO, String> {
    
	//添加合同
    int insertDeliveryMateriel(DeliveryMaterielVO record);
    
    
    public void deleteOldDeliveryMateriel(List<String> list);
    
    
    public void updateDeliveryMateriel(DeliveryMaterielVO deliveryMaterielVO);

    //查询合同列表
    /*List<ContractVO> queryContractList(String userId);*/
    
    //删除合同
    public void deleteDeliveryS(List<String> ids);
    
    //查询合同对象
    public DeliveryMaterielVO selectDeliveryMaterielById(String id);
    
    
    List<DeliveryMaterielVO> selectByExample(String serialNum);
    
    
    
    public Warehouse queryAddressById(String serialNum);
    
    
    public void insertBasicInfo(DeliveryVO record);
    
    
    public void insertBasicInfoPartII(DeliveryTransportVO deliveryTransport);
    
    
    public void insertBasicInfoPartIII(TakeDeliveryVO takeDelivery);
    
    
    public void updateBasicInfo(DeliveryVO record);
    
    
    public void updateBasicInfoPartII(DeliveryTransportVO deliveryTransport);
    
    
    public void updateBasicInfoPartIII(TakeDeliveryVO takeDelivery);
    
    
    public DeliveryVO selectDetailById(String id);
    
    
    public List<DeliveryVO> findAllDeliveryList(String username);
    
    
    public List<DeliveryMaterielVO> selectListForDetail(String serialNum);
    
    
    public String selectOrderId(String orderMaterielSerialNum);
    
    
    public List<String> queryDeliveryMaterielDelete(Map<String,Object> map);
    
    
    public void goDelivery(Map<String,Object> map);
}