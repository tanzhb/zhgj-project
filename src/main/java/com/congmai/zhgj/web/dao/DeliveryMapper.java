package com.congmai.zhgj.web.dao;

import java.util.List;
import java.util.Map;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.web.model.Company;
import com.congmai.zhgj.web.model.DeliveryMateriel;
import com.congmai.zhgj.web.model.DeliveryMaterielVO;
import com.congmai.zhgj.web.model.DeliveryTransportVO;
import com.congmai.zhgj.web.model.DeliveryVO;
import com.congmai.zhgj.web.model.Materiel;
import com.congmai.zhgj.web.model.RelationFile;
import com.congmai.zhgj.web.model.TakeDeliveryVO;
import com.congmai.zhgj.web.model.Warehouse;

/**
 * 
 * @ClassName 发货Dao接口
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author czw
 */
public interface DeliveryMapper extends GenericDao<DeliveryMaterielVO, String> {
    
	//添加发货物料
    int insertDeliveryMateriel(DeliveryMaterielVO record);
    
    //批量添加附件
    public void insertAttachFiles(List<RelationFile> list);
    
    //删除旧的发货物料
    public void deleteOldDeliveryMateriel(List<String> list);
    
    //更新发货物料
    public void updateDeliveryMateriel(DeliveryMaterielVO deliveryMaterielVO);

    //删除物料
    public void deleteDeliveryS(List<String> ids);
    
    
    public List<Materiel> batchGetMaterielInfo(List<String> ids);
    
    //查询发货物料对象
    public DeliveryMaterielVO selectDeliveryMaterielById(String id);
    
    //查询发货物料对象
    public DeliveryMaterielVO selectDeliveryMaterielById2(String id);
    
    //查询销售订单的物料集合
    List<DeliveryMaterielVO> selectByExample(String serialNum);
    
    //查询仓库的地址
    public Warehouse queryAddressById(String serialNum);
    
    //添加基本信息第一部分
    public void insertBasicInfo(DeliveryVO record);
    
    //添加基本信息第二部分
    public void insertBasicInfoPartII(DeliveryTransportVO deliveryTransport);
    
    //添加基本信息第三部分
    public void insertBasicInfoPartIII(TakeDeliveryVO takeDelivery);
    
    //更新基本信息
    public void updateBasicInfo(DeliveryVO record);
    
  //更新基本信息
    public void updateBasicInfo2(DeliveryVO record);
    
    //更新基本信息第二部分
    public void updateBasicInfoPartII(DeliveryTransportVO deliveryTransport);
    
    //更新基本信息第三部分
    public void updateBasicInfoPartIII(TakeDeliveryVO takeDelivery);
    
    //查询发货详情
    public DeliveryVO selectDetailById(String id);
    
    //查询发货详情
    public DeliveryVO selectDetailById2(String id);
    
    //查询发货列表
    public List<DeliveryVO> findAllDeliveryList(String username);
    
    //查询发货列表(加入数据权限)
    public List<DeliveryVO> findAllDeliveryList(DeliveryVO deliveryVO);
    
    //查询发货详情的发货物料
    public List<DeliveryMaterielVO> selectListForDetail(String serialNum);
    
    //查询发货详情的发货物料
    public List<DeliveryMaterielVO> selectListForDetail2(String serialNum);
    
    //查询发货物料的归属销售订单
    public String selectOrderId(String orderMaterielSerialNum);
    
    //查询需要删除的发火物料
    public List<String> queryDeliveryMaterielDelete(Map<String,Object> map);
    
    //确认发货
    public void goDelivery(Map<String,Object> map);
    
    
    public void updateOrderWhenDeliveryComlete(Map<String,Object> map);
 //查询发货详情的发货物料(出入库检验)
    public List<DeliveryMaterielVO> selectListForDetailForStockCheck(Map<String,String>map);


    public Company selectCompanyInfo(String comId);
    

    public void deleteOldDeliveryMateriel2(String deliverSerial);
    
    
    public List<RelationFile> getAttachFileInfo(String relationSerial);
    
    public String selectTotalCountByDeliverSerial(String deliverSerial);
    
    public void updateOtherDeliveryStatus(Map<String,Object> map);
}
