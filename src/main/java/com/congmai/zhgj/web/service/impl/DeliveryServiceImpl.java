package com.congmai.zhgj.web.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.core.util.MessageConstants;
import com.congmai.zhgj.core.util.StringUtil;
import com.congmai.zhgj.log.annotation.OperationLog;
import com.congmai.zhgj.web.dao.CustomsFormMapper;
import com.congmai.zhgj.web.dao.Delivery2Mapper;
import com.congmai.zhgj.web.dao.DeliveryMapper;
import com.congmai.zhgj.web.dao.DeliveryTransportMapper;
import com.congmai.zhgj.web.dao.MaterielMapper;
import com.congmai.zhgj.web.dao.OrderInfoMapper;
import com.congmai.zhgj.web.dao.StockInOutCheckMapper;
import com.congmai.zhgj.web.dao.StockInOutRecordMapper;
import com.congmai.zhgj.web.dao.TakeDeliveryMapper;
import com.congmai.zhgj.web.enums.StaticConst;
import com.congmai.zhgj.web.event.EventExample;
import com.congmai.zhgj.web.event.SendMessageEvent;
import com.congmai.zhgj.web.model.ClauseSettlementDetail;
import com.congmai.zhgj.web.model.Company;
import com.congmai.zhgj.web.model.CustomsForm;
import com.congmai.zhgj.web.model.Delivery;
import com.congmai.zhgj.web.model.DeliveryMaterielVO;
import com.congmai.zhgj.web.model.DeliveryTransport;
import com.congmai.zhgj.web.model.DeliveryTransportExample;
import com.congmai.zhgj.web.model.DeliveryTransportVO;
import com.congmai.zhgj.web.model.DeliveryVO;
import com.congmai.zhgj.web.model.Materiel;
import com.congmai.zhgj.web.model.OrderInfo;
import com.congmai.zhgj.web.model.RelationFile;
import com.congmai.zhgj.web.model.StockInOutCheck;
import com.congmai.zhgj.web.model.StockInOutRecord;
import com.congmai.zhgj.web.model.StockInOutRecordExample;
import com.congmai.zhgj.web.model.TakeDelivery;
import com.congmai.zhgj.web.model.TakeDeliveryVO;
import com.congmai.zhgj.web.model.Warehouse;
import com.congmai.zhgj.web.service.ContractService;
import com.congmai.zhgj.web.service.DeliveryService;
import com.congmai.zhgj.web.service.OrderService;

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
	
	//收货的dao
	@Resource
	private TakeDeliveryMapper takeDeliveryMapper;
	
	@Resource
	private CustomsFormMapper customsFormMapper;
	
	@Resource
	private DeliveryTransportMapper deliveryTransportMapper;

	@Resource
	private MaterielMapper materielMapper;
	
	@Resource
	private OrderInfoMapper orderInfoMapper;
	
	@Resource
	private StockInOutCheckMapper  stockInOutCheckMapper;
	
	@Resource
	private OrderService  orderService;
	@Resource 
	private ContractService contractService;
	
	@Resource
	private Delivery2Mapper delivery2Mapper;
	
	@Resource
	private StockInOutRecordMapper stockInOutRecordMapper;

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
     * 批量添加附件
     * @param list（附件集合）
     */
	@Override
	public void insertAttachFiles(List<RelationFile> list) {
		// TODO Auto-generated method stub
		deliveryMapper.insertAttachFiles(list);
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
	     * 删除旧的发货物料
	     * @param idList
	     */
		 @Override
		public void deleteOldDeliveryMateriel2(String deliverSerial){
			// TODO Auto-generated method stub
			 deliveryMapper.deleteOldDeliveryMateriel2(deliverSerial);
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
	 * 批量获取物料信息
	 */
	@Override
	public List<Materiel> batchGetMaterielInfo(String ids) {
		// TODO Auto-generated method stub
		List<String> idList = ApplicationUtils.getIdList(ids);
		return deliveryMapper.batchGetMaterielInfo(idList);
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
	 * 根据id查询发货对象
	 * @param id
	 * @return
	 */
	@Override
	public DeliveryMaterielVO selectDeliveryMaterielById2(String id) {
		// TODO Auto-generated method stub
		return deliveryMapper.selectDeliveryMaterielById2(id);
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
	 * 编辑基本信息
	 * @param record
	 */
	@Override
	public void updateBasicInfo2(DeliveryVO record) {
		// TODO Auto-generated method stub
		deliveryMapper.updateBasicInfo2(record);
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
	 * 通过id查询详情
	 * @param id
	 * @return
	 */
	@Override
	public DeliveryVO selectDetailById2(String id) {
		// TODO Auto-generated method stub
		return deliveryMapper.selectDetailById2(id);
	}


	/**
	 * 查询发货列表
	 * @param username
	 * @return
	 */
//	@Override
//	public List<DeliveryVO> findAllDeliveryList(String username) {
//		// TODO Auto-generated method stub
//		return deliveryMapper.findAllDeliveryList(username);
//	}
	

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
	 * 查询发货详情的发货物料
	 * @param serialNum
	 * @return
	 */
	@Override
	public List<DeliveryMaterielVO> selectListForDetail2(String serialNum) {
		// TODO Auto-generated method stub
		return deliveryMapper.selectListForDetail2(serialNum);
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
		OrderInfo o=(OrderInfo) map.get("orderInfo");
		String serialNum=(String) map.get("serialNum");//发货流水
		  String currenLoginName=  (String) map.get("updater");
		Boolean  createQG= (Boolean) map.get("createQG");
		OrderInfo orderInfo=new OrderInfo();
		orderInfo.setSerialNum(o.getSerialNum());
		orderInfo.setDeliveryCount(o.getDeliveryCount());
		List<DeliveryMaterielVO> deliveryMateriels= deliveryMapper.selectListForDetail(serialNum);
		if(deliveryMateriels!=null&&deliveryMateriels.size()>0){
			for(DeliveryMaterielVO deliveryMaterielVO:deliveryMateriels){
				orderInfo.setDeliveryCount(StringUtil.sum(orderInfo.getDeliveryCount(),deliveryMaterielVO.getDeliverCount()));
			}
		}
		if(createQG){//外贸 (供应商外贸发货)
			createCustomsClearanceForm(map);//自动生成清关单
			//更新订单状态待清关
			//设置订单发货数量，用于更新
			orderInfo.setDeliverStatus(orderInfo.CLEARANCE);
			
			//更新收货单状态
			TakeDelivery takeDelivery = new TakeDelivery();
			TakeDelivery temp = takeDeliveryMapper.selectTakeDeliveryByDeliveryId(serialNum);
			takeDelivery.setSerialNum(temp.getSerialNum());
			takeDelivery.setStatus(TakeDelivery.WAIT_Cearance);//收货单更新为待清关
			takeDeliveryMapper.updateByPrimaryKeySelective(takeDelivery);//更新收货单状态
			
			
			orderInfoMapper.updateByPrimaryKeySelective(orderInfo);
		}
		//发完订单数量将其它未发货的发货单更新为已失效
		if(new BigDecimal(o.getMaterielCount()).compareTo(new BigDecimal(orderInfo.getDeliveryCount()))==0){//发完订单数量
			takeDeliveryMapper.updateOtherTakeDeliveryStatus(map);
			deliveryMapper.updateOtherDeliveryStatus(map);
		}
		
	}
	//自动生成清关单
	public void createCustomsClearanceForm(Map<String,Object> map1){
		String deliverySerial=(String) map1.get("serialNum");
		String currenLoginName=(String) map1.get("currenLoginName");
		String orderSerial=(String) map1.get("orderSerial");
		Map<String,String>map=new HashMap<String,String>();
		map.put("orderSerial", orderSerial);
		map.put("invoiceSerial", null);
		map.put("deliverySerial", deliverySerial);
		map.put("isClearance", "1");//是否清关单
		List<Materiel> materiels = materielMapper.selectMaterielByOrderSerial(map);
		DeliveryTransportExample de=new DeliveryTransportExample();
		com.congmai.zhgj.web.model.DeliveryTransportExample.Criteria  c=de.createCriteria();
		c.andDelFlgEqualTo("0");
		c.andDeliverSerialEqualTo(deliverySerial);
		List<DeliveryTransport>ds =deliveryTransportMapper.selectByExample(de);
		DeliveryTransport deliveryTransport=null;
		if(CollectionUtils.isNotEmpty(ds)){
			deliveryTransport=ds.get(0);
		}
		BigDecimal deliverAmount=BigDecimal.ZERO;//deliverAmount发货金额
		BigDecimal addedTax=BigDecimal.ZERO;//addedTax增值税
		BigDecimal customsAmount=BigDecimal.ZERO;//customsAmount关税额
		for(Materiel materiel:materiels){
			materiel.setMoney(new BigDecimal(materiel.getOrderUnitPrice()).multiply(new BigDecimal(materiel.getBillAmount()).setScale(2,BigDecimal.ROUND_HALF_UP )).toString());
			if(!StringUtils.isEmpty(materiel.getRate())){//	税额
				materiel.setRateMoney(new BigDecimal(materiel.getRate()).multiply(new BigDecimal(materiel.getOrderUnitPrice())).multiply(new BigDecimal(materiel.getDeliverCount())).divide(new BigDecimal("100")).setScale(2,BigDecimal.ROUND_HALF_UP ).toString());
				addedTax=addedTax.add(new BigDecimal(materiel.getRateMoney()));
			}
			if(!StringUtils.isEmpty(materiel.getCustomsRate())){//关税额
				materiel.setCustomRateMoney(new BigDecimal(materiel.getCustomsRate()).multiply(new BigDecimal(materiel.getOrderUnitPrice())).multiply(new BigDecimal(materiel.getDeliverCount())).divide(new BigDecimal("100")).setScale(2,BigDecimal.ROUND_HALF_UP ).toString());
				customsAmount=customsAmount.add(new BigDecimal(materiel.getCustomRateMoney()));
			}
			materiel.setMaterielMoney(new BigDecimal(materiel.getOrderUnitPrice()).multiply(new BigDecimal(materiel.getDeliverCount())).setScale(2,BigDecimal.ROUND_HALF_UP ).toString());
			deliverAmount=deliverAmount.add(new BigDecimal(materiel.getMaterielMoney()));
		}
		CustomsForm customsForm=new  CustomsForm();
		customsForm.setStatus("0");
		customsForm.setDelFlg("0");
		customsForm.setCustomsFormNum(orderService.getNumCode("QG"));
		customsForm.setSerialNum(ApplicationUtils.random32UUID());
		customsForm.setCustomsFormType("clearance");
		customsForm.setCreator(currenLoginName);
		customsForm.setCreateTime(new Date());
		customsForm.setDeliverSerial(deliverySerial);
		customsForm.setOrderSerial(orderSerial);
		customsForm.setDeliverAmount(deliverAmount.toString());
		customsForm.setAddedTax(addedTax.toString());
		customsForm.setCustomsAmount(customsAmount.toString());
		customsForm.setShipNumber(deliveryTransport==null?"":deliveryTransport.getShipNumber());
		customsForm.setPlayArrivalDate(deliveryTransport==null?null:deliveryTransport.getPlayArrivalDate());
		customsForm.setPort(deliveryTransport==null?null:deliveryTransport.getPort());
		customsFormMapper.insert(customsForm);
		//清关消息通知采购订单制单人
    	EventExample.getEventPublisher().publicSendMessageEvent(new SendMessageEvent(customsForm,MessageConstants.IN_TO_CUSTOMSFORM));
		
		
	}
	@Override
	@OperationLog(operateType = "update" ,operationDesc = "发货" ,objectSerial= "{serialNum}")
	public void updateOrderWhenDeliveryComlete(Map<String,Object> map) {
		// TODO Auto-generated method stub
		deliveryMapper.updateOrderWhenDeliveryComlete(map);
		
		//按结算条款中的签订合同节点生成付款
		String orderString = (String) map.get("orderSerial");
		String nodeString = ClauseSettlementDetail.FHH;
		if(StringUtils.isNotEmpty(orderString)){
			contractService.findPaymentNode(orderString, nodeString);
		}
		
	}


	@Override
	public List<DeliveryMaterielVO> selectListForDetailForStockCheck(
			String serialNum, String judgeString) {
		Map<String,String> map=new HashMap<String,String>();
		if("in".equals(judgeString)){
			map.put("takeDeliverSerial", serialNum);//deliverSerial  takeDeliverSerial
			map.put("deliverSerial", null);
		}else{
			map.put("deliverSerial", serialNum);
			map.put("takeDeliverSerial", null);
		}
		map.put("serialNum", serialNum);
		 return deliveryMapper.selectListForDetailForStockCheck(map);
	}


	@Override
	public Company selectCompanyInfo(String comId) {
		// TODO Auto-generated method stub
		 return deliveryMapper.selectCompanyInfo(comId);
	}


	@Override
	public List<RelationFile> getAttachFileInfo(String relationSerial) {
		// TODO Auto-generated method stub
		 return deliveryMapper.getAttachFileInfo(relationSerial);
	}


	@Override
	public String getDeliveryTotalCount(String serialNum) {
		// TODO Auto-generated method stub
		return deliveryMapper.selectTotalCountByDeliverSerial(serialNum);
	}


	@Override
	public void updateDelivery(Delivery delivery) {
		delivery2Mapper.updateByPrimaryKeySelective(delivery);
		
	}


	@Override
	public StockInOutRecord selectStockInOutRecordByDeliveryId(String serialNum,String inOrOut ) {
		StockInOutRecordExample sre=new StockInOutRecordExample();
		com.congmai.zhgj.web.model.StockInOutRecordExample.Criteria c=sre.createCriteria();
		if("out".equals(inOrOut)){
			c.andDelFlgEqualTo("0").andDeliverSerialEqualTo(serialNum).andStatusNotEqualTo("0");
		}else{
			c.andDelFlgEqualTo("0").andTakeDeliverSerialEqualTo(serialNum).andStatusNotEqualTo("0");
		}
		List<StockInOutRecord>list=stockInOutRecordMapper.selectByExample(sre);
		if(org.springframework.util.CollectionUtils.isEmpty(list)){
			return null;
		}else{
			return list.get(0);
		}
		
		
	}


}
