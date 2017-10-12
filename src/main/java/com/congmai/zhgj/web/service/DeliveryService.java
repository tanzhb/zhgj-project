package com.congmai.zhgj.web.service;

import java.util.List;
import java.util.Map;

import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.Company;
import com.congmai.zhgj.web.model.DeliveryMaterielVO;
import com.congmai.zhgj.web.model.DeliveryTransportVO;
import com.congmai.zhgj.web.model.DeliveryVO;
import com.congmai.zhgj.web.model.Materiel;
import com.congmai.zhgj.web.model.RelationFile;
import com.congmai.zhgj.web.model.TakeDeliveryVO;
import com.congmai.zhgj.web.model.Warehouse;

/**
 * 
 * @ClassName DeliveryService
 * @Description  发货 业务 接口
 * @author czw
 */
public interface DeliveryService extends GenericService<DeliveryMaterielVO, String> {
	
	/**
	 * 保存发货物料
	 * @param contractVO
	 */
    public void insertDeliveryMateriel(DeliveryMaterielVO deliveryMaterielVO);
    
    
    
    /**
     * 批量添加附件
     * @param list（附件集合）
     */
    public void insertAttachFiles(List<RelationFile> list);
    
    
    /**
     * 删除旧的发货物料
     * @param idList
     */
    public void deleteOldDeliveryMateriel(List<String> idList);
    
    
    
    /**
     * 删除旧的发货物料
     * @param idList
     */
    public void deleteOldDeliveryMateriel2(String deliverSerial);
    
    /**
	 * 编辑发货物料
	 * @param contractVO
	 */
    public void updateDeliveryMateriel(DeliveryMaterielVO deliveryMaterielVO);
    
    /**
     * @Description 批量删除 发货
     * @param ids
     * @return
     */
	public void deleteDeliveryS(String ids);
	
	
	
	public List<Materiel> batchGetMaterielInfo(String ids);
	
	
	/**
	 * 根据id查询发货对象
	 * @param id
	 * @return
	 */
	public DeliveryMaterielVO selectDeliveryMaterielById(String id);
	
	
	/**
	 * 根据id查询发货对象
	 * @param id
	 * @return
	 */
	public DeliveryMaterielVO selectDeliveryMaterielById2(String id);
	
	
	/**
	 * 查询发货物料列表
	 * @param serialNum
	 * @return
	 */
	List<DeliveryMaterielVO> selectList(String serialNum);
	
	
	/**
	 * 查询仓库地址
	 * @param serialNum
	 * @return
	 */
	public Warehouse queryAddressById(String serialNum);
	
	
	/**
	 * 添加基本信息
	 * @param record
	 */
	public void insertBasicInfo(DeliveryVO record);
	
	
	/**
	 * 添加基本信息第二部分
	 * @param deliveryTransport
	 */
	public void insertBasicInfoPartII(DeliveryTransportVO deliveryTransport);
	
	
	/**
	 * 添加基本信息第三部分
	 * @param takeDelivery
	 */
	public void insertBasicInfoPartIII(TakeDeliveryVO takeDelivery);
	
	
	/**
	 * 编辑基本信息
	 * @param record
	 */
	public void updateBasicInfo(DeliveryVO record);
	
	
	/**
	 * 编辑基本信息
	 * @param record
	 */
	public void updateBasicInfo2(DeliveryVO record);
	
	
	/**
	 * 编辑基本信息第二部分
	 * @param deliveryTransport
	 */
	public void updateBasicInfoPartII(DeliveryTransportVO deliveryTransport);
	
	
	/**
	 * 编辑基本信息第三部分
	 * @param takeDelivery
	 */
	public void updateBasicInfoPartIII(TakeDeliveryVO takeDelivery);
	
	
	/**
	 * 通过id查询详情
	 * @param id
	 * @return
	 */
	public DeliveryVO selectDetailById(String id);
	
	/**
	 * 通过id查询详情
	 * @param id
	 * @return
	 */
	public DeliveryVO selectDetailById2(String id);
	
	/**
	 * 查询发货列表
	 * @param username
	 * @return
	 */
	public List<DeliveryVO> findAllDeliveryList(String username);
	
	/**
	 * 查询发货列表(加入数据权限)
	 * @param username
	 * @return
	 */
	public List<DeliveryVO> findAllDeliveryList(DeliveryVO deliveryVO);
	
	
	/**
	 * 查询发货详情的发货物料
	 * @param serialNum
	 * @return
	 */
	List<DeliveryMaterielVO> selectListForDetail(String serialNum);
	
	
	/**
	 * 查询发货详情的发货物料
	 * @param serialNum
	 * @return
	 */
	List<DeliveryMaterielVO> selectListForDetail2(String serialNum);
	
	
	/**
	 * 查询发货物料的归属订单id
	 * @param orderMaterielSerialNum
	 * @return
	 */
   public String selectOrderId(String orderMaterielSerialNum);
   
   
   /**
    * 查询需要删除的发货物料
    * @param deliverySerial
    * @param orderSerial
    * @return
    */
   public List<String> queryDeliveryMaterielDelete(String deliverySerial,String orderSerial);
	
  
   /**
    * 确认发货
    * @param map
    */
   public void goDelivery(Map<String,Object> map);
   
   
   
   public void updateOrderWhenDeliveryComlete(Map<String,Object> map);
  
   /**
	 * 查询发货详情的发货物料(出入库检验)
	 * @param serialNum
	 * @return
	 */
	List<DeliveryMaterielVO> selectListForDetailForStockCheck(String serialNum,String judgeString);
	
	
	
	public Company selectCompanyInfo(String comId);
}
