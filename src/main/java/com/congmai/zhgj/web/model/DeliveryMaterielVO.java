package com.congmai.zhgj.web.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DeliveryMaterielVO {
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
}
