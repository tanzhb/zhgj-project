package com.congmai.zhgj.web.dao;

import java.util.List;

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
    
    
    public DeliveryVO selectDetailById(String id);
    
    
    public List<DeliveryVO> findAllDeliveryList(String username);
    
    //更新合同
   /* public void updateContract(ContractVO record);*/
}