package com.congmai.zhgj.web.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DeliveryVO extends BaseVO implements Serializable{
	
	private static final long serialVersionUID = -1495795296316800235L;
	//发货流水号
	private String serialNum;

	//发货编号
	private String deliverNum;
	
	//发货类型
	private String deliverType;
	
	//订单流水号
	private String orderSerial;
	
	
	private String orderNum;
	
	//供应商
	private String supplyComId;
	
	//供应商
	private String buyComId;
	
	//发货方
	private String shipper;
	
	//收货方
	private String receiver;
	
	//制单日期
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date makeDate;
	
	//制单人
	private String maker;
	
	//基本信息备注
	private String remark;
	
/*	//订单状态
	private String status;
	*/
	//审批人
	private String approval;
	
	//审批日期
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date approvalDate;//暂时作为制单日期
	
	//发货仓库流水号
	private String warehouseSerial;
	
	
	//物料条目数量
	private String materielCount;
	
	//发货日期
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date deliverDate;
	
	//包装件数
	private String packageCount;
	
	//物料重量
	private String materielWeight;
	
	//包装类型
	private String packageType;
	
	//包装规格
	private String packageSpecifications;
	
	//服务费
	private String serviceMoney;
	
	//发货人
	private String deliverer;
	
	//联系电话
	private String contactNum;
	
	//发货信息备注
	private String deliverRemark;
	
	//删除标志（1是0否）
	private String delFlg;
	
	//创建人
	private String creator;
	
	//创建时间
	private Date createTime;
	
	//更新人
	private String updater;
	
	//更新时间
	private Date updateTime ;
	
	
	//运输方式
	private String transportType;
	
	//运输方
	private String transport;
	
	//港口
	private String port;
	
	//船号
	private String shipNumber;
	
	//预计到港日期
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date playArrivalDate;
	
	//预计到库日期
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date playWarehouseDate;
	
	//基本信息备注
	private String transportRemark;
	
	//联系人
	private String transportContact;
	
	//联系电话
	private String transportContactNum;
	
	
	private String takeWarehouseSerial;
	
	
	private String deliveryWarehouseName;
	
	
	private String takeWarehouseName;
	//收货方联系人
	private String takeDeliverer;
	
	//收货方联系电话
		private String takeContactNum;
		
	//收货方备注
		private String takeTransportRemark;
		
		private String saleOrderNum; // 关联销售订单号
		
		//发货方联系人
		private String deContact;
		
		//发货方联系电话
			private String deContactNum;
			
		//发货方备注
			private String deRemark;

		
	//发货地址
	private String deliveryAddress;
	
	
	//收货地址
	private String takeAddress;
	
	
	//收货日期
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date takeDeliverDate;
	
	//收货人
	private String takeDeliveryReceiver;
	
	//联系电话
	private String takeDeliveryContactNum;
	
	//收货信息备注
	private String takeDeliveryRemark;
	
	private String transportserialNum;
	private String takeDeliverSerialNum;
	
	private String  supplyName;//供应商名称
	
	private String orderAmount;//订单金额
	
	private List<String> supplyComIds;
	
	private String  buyName;//采购商名称
	
	 private String  type;//保存类型
	 
	//发货数量合计
		private String materielTotalCount;
		private String inOutSerial;// 出入库计划列表出入库计划流水
		private String inOutNum;// 出入库计划列表出入库计划单号
		private String inOutType;// 出入库计划列表出入库类型
		private String inOutRemark;// 出入库计划列表备注
		private String inOutPackageCount;// 出入库计划列表包装数量
		
		private String realCount;// 实际出入库数量
	  
	
	  //0：待发货；3：待收货；4：已收货；5：部分收货'
	    public static final String WAIT_CHECK = "1"; //待检验
	    

	    public static final String WAITRECORD = "2"; //待出库
	    

	    public static final String WAIT_OUT = "6"; //待清关
	    
	    public static final String DECLARATION = "7"; //待报关 
	    
	    public static final String COMPLETE = "8"; //完成发货
	    
	    public static final String WAIT_IN_RECORD = "9"; //发货单待入库
	    
	    public static final String WAIT_TAKEDELIVER_DELIVERY=  "10"; //待收货
	    
	    public static final String TAKEDELIVER_DELIVERY=  "4"; //已收货
	    
	    private  Boolean  isDel;
	    
	    private  Boolean  flag;
	    private  List<DeliveryMaterielVO> deliveryMateriels;
	    
	    private String orderApplyCount;//订单已申请数量
	    
	    
	    private   List<String> idList;
	
/*	// 业务类型
	private String businessType;
	
	//申请日期
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date applyDate;
    
    
    //原因
  	private String reason;
  	
    // 用户id
 	private Integer userId;
 	
 	private String processInstanceId;*/
	
	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public String getDeliverNum() {
		return deliverNum;
	}

	public void setDeliverNum(String deliverNum) {
		this.deliverNum = deliverNum;
	}

	public String getOrderSerial() {
		return orderSerial;
	}

	public void setOrderSerial(String orderSerial) {
		this.orderSerial = orderSerial;
	}

	public String getSupplyComId() {
		return supplyComId;
	}

	public void setSupplyComId(String supplyComId) {
		this.supplyComId = supplyComId;
	}

	public String getShipper() {
		return shipper;
	}

	public void setShipper(String shipper) {
		this.shipper = shipper;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	@JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd")
	public Date getMakeDate() {
		return makeDate;
	}

	public void setMakeDate(Date makeDate) {
		this.makeDate = makeDate;
	}

	public String getMaker() {
		return maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

/*	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
*/
	public String getApproval() {
		return approval;
	}

	public void setApproval(String approval) {
		this.approval = approval;
	}

	@JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd")
	public Date getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	public String getWarehouseSerial() {
		return warehouseSerial;
	}

	public void setWarehouseSerial(String warehouseSerial) {
		this.warehouseSerial = warehouseSerial;
	}

	public String getMaterielCount() {
		return materielCount;
	}

	public void setMaterielCount(String materielCount) {
		this.materielCount = materielCount;
	}

	@JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd")
	public Date getDeliverDate() {
		return deliverDate;
	}

	public void setDeliverDate(Date deliverDate) {
		this.deliverDate = deliverDate;
	}

	public String getPackageCount() {
		return packageCount;
	}

	public void setPackageCount(String packageCount) {
		this.packageCount = packageCount;
	}

	public String getMaterielWeight() {
		return materielWeight;
	}

	public void setMaterielWeight(String materielWeight) {
		this.materielWeight = materielWeight;
	}

	public String getPackageType() {
		return packageType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

	public String getPackageSpecifications() {
		return packageSpecifications;
	}

	public void setPackageSpecifications(String packageSpecifications) {
		this.packageSpecifications = packageSpecifications;
	}

	public String getServiceMoney() {
		return serviceMoney;
	}

	public void setServiceMoney(String serviceMoney) {
		this.serviceMoney = serviceMoney;
	}

	public String getDeliverer() {
		return deliverer;
	}

	public void setDeliverer(String deliverer) {
		this.deliverer = deliverer;
	}

	public String getContactNum() {
		return contactNum;
	}

	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}

	public String getDeliverRemark() {
		return deliverRemark;
	}

	public void setDeliverRemark(String deliverRemark) {
		this.deliverRemark = deliverRemark;
	}

	public String getDelFlg() {
		return delFlg;
	}

	public void setDelFlg(String delFlg) {
		this.delFlg = delFlg;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdater() {
		return updater;
	}

	public void setUpdater(String updater) {
		this.updater = updater;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getTransportType() {
		return transportType;
	}

	public void setTransportType(String transportType) {
		this.transportType = transportType;
	}

	public String getTransport() {
		return transport;
	}

	public void setTransport(String transport) {
		this.transport = transport;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getShipNumber() {
		return shipNumber;
	}

	public void setShipNumber(String shipNumber) {
		this.shipNumber = shipNumber;
	}

	@JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd")
	public Date getPlayArrivalDate() {
		return playArrivalDate;
	}

	
	public void setPlayArrivalDate(Date playArrivalDate) {
		this.playArrivalDate = playArrivalDate;
	}

	@JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd")
	public Date getPlayWarehouseDate() {
		return playWarehouseDate;
	}

	public void setPlayWarehouseDate(Date playWarehouseDate) {
		this.playWarehouseDate = playWarehouseDate;
	}

	public String getTransportRemark() {
		return transportRemark;
	}

	public void setTransportRemark(String transportRemark) {
		this.transportRemark = transportRemark;
	}

	public String getTransportContact() {
		return transportContact;
	}

	public void setTransportContact(String transportContact) {
		this.transportContact = transportContact;
	}

	public String getTransportContactNum() {
		return transportContactNum;
	}

	public void setTransportContactNum(String transportContactNum) {
		this.transportContactNum = transportContactNum;
	}

	public String getDeliveryWarehouseName() {
		return deliveryWarehouseName;
	}

	public void setDeliveryWarehouseName(String deliveryWarehouseName) {
		this.deliveryWarehouseName = deliveryWarehouseName;
	}

	public String getTakeWarehouseName() {
		return takeWarehouseName;
	}

	public void setTakeWarehouseName(String takeWarehouseName) {
		this.takeWarehouseName = takeWarehouseName;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getTakeAddress() {
		return takeAddress;
	}

	public void setTakeAddress(String takeAddress) {
		this.takeAddress = takeAddress;
	}

	@JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd")
	public Date getTakeDeliverDate() {
		return takeDeliverDate;
	}

	public void setTakeDeliverDate(Date takeDeliverDate) {
		this.takeDeliverDate = takeDeliverDate;
	}

	public String getTakeDeliveryReceiver() {
		return takeDeliveryReceiver;
	}

	public void setTakeDeliveryReceiver(String takeDeliveryReceiver) {
		this.takeDeliveryReceiver = takeDeliveryReceiver;
	}

	public String getTakeDeliveryContactNum() {
		return takeDeliveryContactNum;
	}

	public void setTakeDeliveryContactNum(String takeDeliveryContactNum) {
		this.takeDeliveryContactNum = takeDeliveryContactNum;
	}

	public String getTakeDeliveryRemark() {
		return takeDeliveryRemark;
	}

	public void setTakeDeliveryRemark(String takeDeliveryRemark) {
		this.takeDeliveryRemark = takeDeliveryRemark;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getTransportserialNum() {
		return transportserialNum;
	}

	public void setTransportserialNum(String transportserialNum) {
		this.transportserialNum = transportserialNum;
	}

	public String getTakeDeliverSerialNum() {
		return takeDeliverSerialNum;
	}

	public void setTakeDeliverSerialNum(String takeDeliverSerialNum) {
		this.takeDeliverSerialNum = takeDeliverSerialNum;
	}

	public String getSupplyName() {
		return supplyName;
	}

	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}

	public String getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}

	public List<String> getSupplyComIds() {
		return supplyComIds;
	}

	public void setSupplyComIds(List<String> supplyComIds) {
		this.supplyComIds = supplyComIds;
	}

	public String getDeliverType() {
		return deliverType;
	}

	public void setDeliverType(String deliverType) {
		this.deliverType = deliverType;
	}

	public String getTakeWarehouseSerial() {
		return takeWarehouseSerial;
	}

	public void setTakeWarehouseSerial(String takeWarehouseSerial) {
		this.takeWarehouseSerial = takeWarehouseSerial;
	}

	public String getBuyName() {
		return buyName;
	}

	public void setBuyName(String buyName) {
		this.buyName = buyName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBuyComId() {
		return buyComId;
	}

	public void setBuyComId(String buyComId) {
		this.buyComId = buyComId;
	}

	public String getMaterielTotalCount() {
		return materielTotalCount;
	}

	public void setMaterielTotalCount(String materielTotalCount) {
		this.materielTotalCount = materielTotalCount;
	}

	public String getInOutSerial() {
		return inOutSerial;
	}

	public void setInOutSerial(String inOutSerial) {
		this.inOutSerial = inOutSerial;
	}

	public String getInOutNum() {
		return inOutNum;
	}

	public void setInOutNum(String inOutNum) {
		this.inOutNum = inOutNum;
	}

	public String getInOutType() {
		return inOutType;
	}

	public void setInOutType(String inOutType) {
		this.inOutType = inOutType;
	}

	public String getInOutRemark() {
		return inOutRemark;
	}

	public void setInOutRemark(String inOutRemark) {
		this.inOutRemark = inOutRemark;
	}

	public String getInOutPackageCount() {
		return inOutPackageCount;
	}

	public void setInOutPackageCount(String inOutPackageCount) {
		this.inOutPackageCount = inOutPackageCount;
	}

	public Boolean getIsDel() {
		return isDel;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setIsDel(Boolean isDel) {
		this.isDel = isDel;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public List<DeliveryMaterielVO> getDeliveryMateriels() {
		return deliveryMateriels;
	}

	public void setDeliveryMateriels(List<DeliveryMaterielVO> deliveryMateriels) {
		this.deliveryMateriels = deliveryMateriels;
	}

	public String getOrderApplyCount() {
		return orderApplyCount;
	}

	public void setOrderApplyCount(String orderApplyCount) {
		this.orderApplyCount = orderApplyCount;
	}

	public String getRealCount() {
		return realCount;
	}

	public void setRealCount(String realCount) {
		this.realCount = realCount;
	}

	public String getTakeDeliverer() {
		return takeDeliverer;
	}

	public String getTakeContactNum() {
		return takeContactNum;
	}

	public String getTakeTransportRemark() {
		return takeTransportRemark;
	}

	public void setTakeDeliverer(String takeDeliverer) {
		this.takeDeliverer = takeDeliverer;
	}

	public void setTakeContactNum(String takeContactNum) {
		this.takeContactNum = takeContactNum;
	}

	public void setTakeTransportRemark(String takeTransportRemark) {
		this.takeTransportRemark = takeTransportRemark;
	}

	public String getSaleOrderNum() {
		return saleOrderNum;
	}

	public void setSaleOrderNum(String saleOrderNum) {
		this.saleOrderNum = saleOrderNum;
	}

	public String getDeContact() {
		return deContact;
	}

	public String getDeContactNum() {
		return deContactNum;
	}

	public String getDeRemark() {
		return deRemark;
	}

	public void setDeContact(String deContact) {
		this.deContact = deContact;
	}

	public void setDeContactNum(String deContactNum) {
		this.deContactNum = deContactNum;
	}

	public void setDeRemark(String deRemark) {
		this.deRemark = deRemark;
	}

	public List<String> getIdList() {
		return idList;
	}

	public void setIdList(List<String> idList) {
		this.idList = idList;
	}

/*	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	@JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd")
	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}*/	
}
