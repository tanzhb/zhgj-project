package com.congmai.zhgj.web.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Delivery {
	private String serialNum;

	private String deliverNum;

	private String deliverType;

	private String orderSerial;

	private String docNum; // 单据号

	private String supplyComId;

	private String buyComId; // 采购商Id

	private String shipper;

	private String receiver;

	private Date makeDate;

	private String maker;

	private String remark;

	private String status;

	private String approval;

	private Date approvalDate;

	private String warehouseSerial;

	private String materielCount;

	private Date deliverDate;

	private String packageCount;

	private String materielWeight;

	private String packageType;

	private String packageSpecifications;

	private String serviceMoney;

	private String deliverer;

	private String contactNum;

	private String deliverRemark;

	private String processInstanceId;

	private String deliverAddress;

	private String reason;

	private Integer userId;

	private String delFlg;

	private String creator;

	private Date createTime;

	private String updater;

	private Date updateTime;

	private String takeDeliverSerial;

	private String warehouseName;// 发货仓库名称
	
	private boolean hasCheckData;// 是否有检验条款(订单)
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

	// 发货数量合计
	private String materielTotalCount;

	// 冗余字段

	private List<DeliveryMateriel> deliveryMateriels;

	private DeliveryTransport deliveryTransport;

	private TakeDelivery takeDelivery;

	private Warehouse warehouse;

	private int pageIndex;

	private int pageSize;

	private String supplyName;

	private String buyName;

	private String shipperName;

	private String receiverName;

	private String orderNum;

	private String orderAmount;// 订单金额

	private String inOutSerial;// 出入库计划列表出入库计划流水
	private String inOutNum;// 出入库计划列表出入库计划单号
	private String inOutType;// 出入库计划列表出入库类型
	private String inOutRemark;// 出入库计划列表备注
	private String inOutPackageCount;// 出入库计划列表包装数量
	private    StockInOutRecord  stockInOutRecord;//出入库记录
	private String takeDeliverAddress;//收货地址
	
	private String takeDeliverWarehouseName;// 收货仓库名称
	
	private String realCount;// 实际出入库数量
	
	private List<DeliverFile> deliverFile;

	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum == null ? null : serialNum.trim();
	}

	public String getDeliverNum() {
		return deliverNum;
	}

	public void setDeliverNum(String deliverNum) {
		this.deliverNum = deliverNum == null ? null : deliverNum.trim();
	}

	public String getOrderSerial() {
		return orderSerial;
	}

	public void setOrderSerial(String orderSerial) {
		this.orderSerial = orderSerial == null ? null : orderSerial.trim();
	}

	public String getSupplyComId() {
		return supplyComId;
	}

	public void setSupplyComId(String supplyComId) {
		this.supplyComId = supplyComId == null ? null : supplyComId.trim();
	}

	public String getShipper() {
		return shipper;
	}

	public void setShipper(String shipper) {
		this.shipper = shipper == null ? null : shipper.trim();
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver == null ? null : receiver.trim();
	}

	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
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
		this.maker = maker == null ? null : maker.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	public String getApproval() {
		return approval;
	}

	public void setApproval(String approval) {
		this.approval = approval == null ? null : approval.trim();
	}

	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
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
		this.warehouseSerial = warehouseSerial == null ? null : warehouseSerial
				.trim();
	}

	public String getMaterielCount() {
		return materielCount;
	}

	public void setMaterielCount(String materielCount) {
		this.materielCount = materielCount == null ? null : materielCount
				.trim();
	}

	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
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
		this.packageCount = packageCount == null ? null : packageCount.trim();
	}

	public String getMaterielWeight() {
		return materielWeight;
	}

	public void setMaterielWeight(String materielWeight) {
		this.materielWeight = materielWeight == null ? null : materielWeight
				.trim();
	}

	public String getPackageType() {
		return packageType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType == null ? null : packageType.trim();
	}

	public String getPackageSpecifications() {
		return packageSpecifications;
	}

	public void setPackageSpecifications(String packageSpecifications) {
		this.packageSpecifications = packageSpecifications == null ? null
				: packageSpecifications.trim();
	}

	public String getServiceMoney() {
		return serviceMoney;
	}

	public void setServiceMoney(String serviceMoney) {
		this.serviceMoney = serviceMoney == null ? null : serviceMoney.trim();
	}

	public String getDeliverer() {
		return deliverer;
	}

	public void setDeliverer(String deliverer) {
		this.deliverer = deliverer == null ? null : deliverer.trim();
	}

	public String getContactNum() {
		return contactNum;
	}

	public void setContactNum(String contactNum) {
		this.contactNum = contactNum == null ? null : contactNum.trim();
	}

	public String getDeliverRemark() {
		return deliverRemark;
	}

	public void setDeliverRemark(String deliverRemark) {
		this.deliverRemark = deliverRemark == null ? null : deliverRemark
				.trim();
	}

	public String getDelFlg() {
		return delFlg;
	}

	public void setDelFlg(String delFlg) {
		this.delFlg = delFlg == null ? null : delFlg.trim();
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator == null ? null : creator.trim();
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
		this.updater = updater == null ? null : updater.trim();
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<DeliveryMateriel> getDeliveryMateriels() {
		return deliveryMateriels;
	}

	public void setDeliveryMateriels(List<DeliveryMateriel> deliveryMateriels) {
		this.deliveryMateriels = deliveryMateriels;
	}

	public DeliveryTransport getDeliveryTransport() {
		return deliveryTransport;
	}

	public void setDeliveryTransport(DeliveryTransport deliveryTransport) {
		this.deliveryTransport = deliveryTransport;
	}

	public TakeDelivery getTakeDelivery() {
		return takeDelivery;
	}

	public void setTakeDelivery(TakeDelivery takeDelivery) {
		this.takeDelivery = takeDelivery;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getSupplyName() {
		return supplyName;
	}

	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	public String getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getTakeDeliverSerial() {
		return takeDeliverSerial;
	}

	public void setTakeDeliverSerial(String takeDeliverSerial) {
		this.takeDeliverSerial = takeDeliverSerial;
	}

	public String getDeliverType() {
		return deliverType;
	}

	public void setDeliverType(String deliverType) {
		this.deliverType = deliverType;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getShipperName() {
		return shipperName;
	}

	public void setShipperName(String shipperName) {
		this.shipperName = shipperName;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
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

	public String getDeliverAddress() {
		return deliverAddress;
	}

	public void setDeliverAddress(String deliverAddress) {
		this.deliverAddress = deliverAddress;
	}

	public String getDocNum() {
		return docNum;
	}

	public void setDocNum(String docNum) {
		this.docNum = docNum;
	}

	public String getBuyComId() {
		return buyComId;
	}

	public void setBuyComId(String buyComId) {
		this.buyComId = buyComId;
	}

	public String getBuyName() {
		return buyName;
	}

	public void setBuyName(String buyName) {
		this.buyName = buyName;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public String getMaterielTotalCount() {
		return materielTotalCount;
	}

	public void setMaterielTotalCount(String materielTotalCount) {
		this.materielTotalCount = materielTotalCount;
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

	public String getInOutSerial() {
		return inOutSerial;
	}

	public void setInOutSerial(String inOutSerial) {
		this.inOutSerial = inOutSerial;
	}

	public StockInOutRecord getStockInOutRecord() {
		return stockInOutRecord;
	}

	public void setStockInOutRecord(StockInOutRecord stockInOutRecord) {
		this.stockInOutRecord = stockInOutRecord;
	}

	public String getTakeDeliverAddress() {
		return takeDeliverAddress;
	}

	public void setTakeDeliverAddress(String takeDeliverAddress) {
		this.takeDeliverAddress = takeDeliverAddress;
	}

	public String getTakeDeliverWarehouseName() {
		return takeDeliverWarehouseName;
	}

	public void setTakeDeliverWarehouseName(String takeDeliverWarehouseName) {
		this.takeDeliverWarehouseName = takeDeliverWarehouseName;
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

	public boolean isHasCheckData() {
		return hasCheckData;
	}

	public void setHasCheckData(boolean hasCheckData) {
		this.hasCheckData = hasCheckData;
	}

	public List<DeliverFile> getDeliverFile() {
		return deliverFile;
	}

	public void setDeliverFile(List<DeliverFile> deliverFile) {
		this.deliverFile = deliverFile;
	}
	
}