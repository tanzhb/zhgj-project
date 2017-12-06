package com.congmai.zhgj.web.model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DeliveryMaterielVO  {

	//发货物料流水号
	private String serialNum;
	
	//订单物料流水号
	private String orderMaterielSerialNum;
	
	//收发货流水号
	private String deliverSerial;
	
	//订单物料流水号
	private String orderMaterielSerial;
	
	//批次号
	private String batchNum;
	
	//生产日期
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date manufactureDate;
	
	//发货数量
	private String deliverCount;
	
	//备注
	private String remark;
	
	//删除标志（1是0否）
	private String delFlg;
	
	//创建人
	private String creator;
	
	//创建时间
	private Date createTime;
	
	//更新人
	private String updater;
	
	//更新时间
	private Date updateTime;
	
	
	private String materielName;
	
	
	private String materielNum;
	
	
	private String specifications;
	
	
	private String unit;
	
	
	private String amount;
	
	
	private String deliverRemark;
	
	
	private String stockCount;
	
	
	private String unstockCount;
	
	
	private String warehouseName;
	
	
	private String positionName;
	
	
	private String stockRemark;
	
	
	private String qualifiedCount;
	
	
	private String unqualifiedCount;
	
	
	private String checkRemark;
	
	
	private String acceptCount;
	
	
	private String refuseCount;
	
	
	private String takeRemark;
	
	
	private String status;
	
	private String orderSerial;//订单流水
	
	
	private String supplyMaterielSerial;
	
	
	private String attachFile;
	
	
	private List<RelationFile> files;
	
	 private String  deliveredCount;//已发数量
	
	


	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public String getDeliverSerial() {
		return deliverSerial;
	}

	public void setDeliverSerial(String deliverSerial) {
		this.deliverSerial = deliverSerial;
	}

	public String getOrderMaterielSerial() {
		return orderMaterielSerial;
	}

	public void setOrderMaterielSerial(String orderMaterielSerial) {
		this.orderMaterielSerial = orderMaterielSerial;
	}

	public String getBatchNum() {
		return batchNum;
	}

	public void setBatchNum(String batchNum) {
		this.batchNum = batchNum;
	}

	@JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd")
	public Date getManufactureDate() {
		return manufactureDate;
	}

	public void setManufactureDate(Date manufactureDate) {
		this.manufactureDate = manufactureDate;
	}

	public String getDeliverCount() {
		return deliverCount;
	}

	public void setDeliverCount(String deliverCount) {
		this.deliverCount = deliverCount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getOrderMaterielSerialNum() {
		return orderMaterielSerialNum;
	}

	public void setOrderMaterielSerialNum(String orderMaterielSerialNum) {
		this.orderMaterielSerialNum = orderMaterielSerialNum;
	}

	public String getMaterielName() {
		return materielName;
	}

	public void setMaterielName(String materielName) {
		this.materielName = materielName;
	}

	public String getMaterielNum() {
		return materielNum;
	}

	public void setMaterielNum(String materielNum) {
		this.materielNum = materielNum;
	}

	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getDeliverRemark() {
		return deliverRemark;
	}

	public void setDeliverRemark(String deliverRemark) {
		this.deliverRemark = deliverRemark;
	}

	public String getStockCount() {
		return stockCount;
	}

	public void setStockCount(String stockCount) {
		this.stockCount = stockCount;
	}

	public String getUnstockCount() {
		return unstockCount;
	}

	public void setUnstockCount(String unstockCount) {
		this.unstockCount = unstockCount;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getStockRemark() {
		return stockRemark;
	}

	public void setStockRemark(String stockRemark) {
		this.stockRemark = stockRemark;
	}

	public String getQualifiedCount() {
		return qualifiedCount;
	}

	public void setQualifiedCount(String qualifiedCount) {
		this.qualifiedCount = qualifiedCount;
	}

	public String getUnqualifiedCount() {
		return unqualifiedCount;
	}

	public void setUnqualifiedCount(String unqualifiedCount) {
		this.unqualifiedCount = unqualifiedCount;
	}

	public String getCheckRemark() {
		return checkRemark;
	}

	public void setCheckRemark(String checkRemark) {
		this.checkRemark = checkRemark;
	}

	public String getAcceptCount() {
		return acceptCount;
	}

	public void setAcceptCount(String acceptCount) {
		this.acceptCount = acceptCount;
	}

	public String getRefuseCount() {
		return refuseCount;
	}

	public void setRefuseCount(String refuseCount) {
		this.refuseCount = refuseCount;
	}

	public String getTakeRemark() {
		return takeRemark;
	}

	public void setTakeRemark(String takeRemark) {
		this.takeRemark = takeRemark;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrderSerial() {
		return orderSerial;
	}

	public void setOrderSerial(String orderSerial) {
		this.orderSerial = orderSerial;
	}

	public String getSupplyMaterielSerial() {
		return supplyMaterielSerial;
	}

	public void setSupplyMaterielSerial(String supplyMaterielSerial) {
		this.supplyMaterielSerial = supplyMaterielSerial;
	}

	public String getAttachFile() {
		return attachFile;
	}

	public void setAttachFile(String attachFile) {
		this.attachFile = attachFile;
	}

	public List<RelationFile> getFiles() {
		return files;
	}

	public void setFiles(List<RelationFile> files) {
		this.files = files;
	}

	public String getDeliveredCount() {
		return deliveredCount;
	}

	public void setDeliveredCount(String deliveredCount) {
		this.deliveredCount = deliveredCount;
	}

}
