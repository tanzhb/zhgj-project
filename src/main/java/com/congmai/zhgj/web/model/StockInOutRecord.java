package com.congmai.zhgj.web.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @ClassName StockInOutRecord
 * @Description TODO(出入库记录)
 * @author zhaichao
 * @Date 2017年8月23日 上午10:03:55
 * @version 1.0.0
 */
public class StockInOutRecord {
	 private String serialNum;

	    private String deliverSerial;

	    private String takeDeliverSerial;

	    private String docNum;

	    private String inOutNum;
	    @DateTimeFormat(pattern="yyyy-MM-dd")
	    private Date stockDate;//  @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")

	    private String operator;

	    private String contactNum;

	    private String status;

	    private String remark;

	    private String delFlg;

	    private String creator;

	    private Date createTime;

	    private String updater;

	    private Date updateTime;

	    private String inOutType;

	    private String shipperOrReceiver;

	    private String inOutFlag;

	    private String packageType;

	    private String packageSpecifications;

	    private String packageCount;
	    
	    private int pageSize;
	    
	    private int pageIndex; 
	    
	    private String shipperOrReceiverName;
	    
	    private Delivery delivery;
	    
	    private int warehouseCount;
	    
	    private int positionCount;
	    
	    private OrderInfo order;
	    
	    private String buyComId;
	    
	    private String supplyComId;
	    
	    
	    private String inCount;
	    
	    private String outCount;
	    
	    private String inWarehouseName;
	    
	    private String outWarehouseName;
	    
	    private String materielCount;//出入库数量
	    //发货计划运输信息挪至出库
	    private String transportType;//运输方式

	    private String transport;//运输方

	    private String shipNumber;//运单号
	    
	    private String  transportContact;//联系人
	    
	    private String transportContactNum;//联系电话
	    
	    private String transportRemark;//备注
	    
	    private String orderSerial;//订单流水
	    
	    


		public int getPageSize() {
			return pageSize;
		}

		public int getPageIndex() {
			return pageIndex;
		}

		public void setPageSize(int pageSize) {
			this.pageSize = pageSize;
		}

		public void setPageIndex(int pageIndex) {
			this.pageIndex = pageIndex;
		}

		public String getSerialNum() {
			return serialNum;
		}

		public String getDeliverSerial() {
			return deliverSerial;
		}

		public String getTakeDeliverSerial() {
			return takeDeliverSerial;
		}

		public String getDocNum() {
			return docNum;
		}

		public String getInOutNum() {
			return inOutNum;
		}
		 @JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd ")
		public Date getStockDate() {//@JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd HH:mm:ss ")
			return stockDate;
		}

		public String getOperator() {
			return operator;
		}

		public String getContactNum() {
			return contactNum;
		}

		public String getStatus() {
			return status;
		}

		public String getRemark() {
			return remark;
		}

		public String getDelFlg() {
			return delFlg;
		}

		public String getCreator() {
			return creator;
		}

		public Date getCreateTime() {
			return createTime;
		}

		public String getUpdater() {
			return updater;
		}

		public Date getUpdateTime() {
			return updateTime;
		}

		public String getInOutType() {
			return inOutType;
		}

		public String getShipperOrReceiver() {
			return shipperOrReceiver;
		}

		public String getInOutFlag() {
			return inOutFlag;
		}

		public String getPackageType() {
			return packageType;
		}

		public String getPackageSpecifications() {
			return packageSpecifications;
		}

		public String getPackageCount() {
			return packageCount;
		}

		public void setSerialNum(String serialNum) {
			this.serialNum = serialNum;
		}

		public void setDeliverSerial(String deliverSerial) {
			this.deliverSerial = deliverSerial;
		}

		public void setTakeDeliverSerial(String takeDeliverSerial) {
			this.takeDeliverSerial = takeDeliverSerial;
		}

		public void setDocNum(String docNum) {
			this.docNum = docNum;
		}

		public void setInOutNum(String inOutNum) {
			this.inOutNum = inOutNum;
		}
		 @JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd")
		public void setStockDate(Date stockDate) {// @JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd HH:mm:ss ")
			this.stockDate = stockDate;
		}

		public void setOperator(String operator) {
			this.operator = operator;
		}

		public void setContactNum(String contactNum) {
			this.contactNum = contactNum;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}

		public void setDelFlg(String delFlg) {
			this.delFlg = delFlg;
		}

		public void setCreator(String creator) {
			this.creator = creator;
		}

		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}

		public void setUpdater(String updater) {
			this.updater = updater;
		}

		public void setUpdateTime(Date updateTime) {
			this.updateTime = updateTime;
		}

		public void setInOutType(String inOutType) {
			this.inOutType = inOutType;
		}

		public void setShipperOrReceiver(String shipperOrReceiver) {
			this.shipperOrReceiver = shipperOrReceiver;
		}

		public void setInOutFlag(String inOutFlag) {
			this.inOutFlag = inOutFlag;
		}

		public void setPackageType(String packageType) {
			this.packageType = packageType;
		}

		public void setPackageSpecifications(String packageSpecifications) {
			this.packageSpecifications = packageSpecifications;
		}

		public void setPackageCount(String packageCount) {
			this.packageCount = packageCount;
		}

		public String getShipperOrReceiverName() {
			return shipperOrReceiverName;
		}

		public Delivery getDelivery() {
			return delivery;
		}

		public int getWarehouseCount() {
			return warehouseCount;
		}

		public int getPositionCount() {
			return positionCount;
		}

		public OrderInfo getOrder() {
			return order;
		}

		public String getBuyComId() {
			return buyComId;
		}

		public String getSupplyComId() {
			return supplyComId;
		}

		public String getInCount() {
			return inCount;
		}

		public String getOutCount() {
			return outCount;
		}

		public String getInWarehouseName() {
			return inWarehouseName;
		}

		public String getOutWarehouseName() {
			return outWarehouseName;
		}

		public void setShipperOrReceiverName(String shipperOrReceiverName) {
			this.shipperOrReceiverName = shipperOrReceiverName;
		}

		public void setDelivery(Delivery delivery) {
			this.delivery = delivery;
		}

		public void setWarehouseCount(int warehouseCount) {
			this.warehouseCount = warehouseCount;
		}

		public void setPositionCount(int positionCount) {
			this.positionCount = positionCount;
		}

		public void setOrder(OrderInfo order) {
			this.order = order;
		}

		public void setBuyComId(String buyComId) {
			this.buyComId = buyComId;
		}

		public void setSupplyComId(String supplyComId) {
			this.supplyComId = supplyComId;
		}

		public void setInCount(String inCount) {
			this.inCount = inCount;
		}

		public void setOutCount(String outCount) {
			this.outCount = outCount;
		}

		public void setInWarehouseName(String inWarehouseName) {
			this.inWarehouseName = inWarehouseName;
		}

		public void setOutWarehouseName(String outWarehouseName) {
			this.outWarehouseName = outWarehouseName;
		}

		public String getMaterielCount() {
			return materielCount;
		}

		public void setMaterielCount(String materielCount) {
			this.materielCount = materielCount;
		}

		public String getTransportType() {
			return transportType;
		}

		public String getTransport() {
			return transport;
		}

		public String getShipNumber() {
			return shipNumber;
		}

		public String getTransportContact() {
			return transportContact;
		}

		public String getTransportContactNum() {
			return transportContactNum;
		}

		public String getTransportRemark() {
			return transportRemark;
		}

		public void setTransportType(String transportType) {
			this.transportType = transportType;
		}

		public void setTransport(String transport) {
			this.transport = transport;
		}

		public void setShipNumber(String shipNumber) {
			this.shipNumber = shipNumber;
		}

		public void setTransportContact(String transportContact) {
			this.transportContact = transportContact;
		}

		public void setTransportContactNum(String transportContactNum) {
			this.transportContactNum = transportContactNum;
		}

		public void setTransportRemark(String transportRemark) {
			this.transportRemark = transportRemark;
		}

		public String getOrderSerial() {
			return orderSerial;
		}

		public void setOrderSerial(String orderSerial) {
			this.orderSerial = orderSerial;
		}

	
}