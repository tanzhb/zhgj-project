package com.congmai.zhgj.web.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TakeDeliveryVO {
	//收货流水号
	private String serialNum;
	
	private String takeDeliveryVOSerialNum;
	
	//发货流水号
	private String deliverSerial;
	
	//收货仓库流水号
	private String takeDeliveryWarehouseSerial;
	
	//收货地址
	private String takeAddress;
	
	//收货日期
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date takeDeliverDate;
	
	//收货人
	private String takeDeliveryReceiver;
	
	//联系电话
	private String takeDeliveryContactNum;
	
	//收货编号
	private String takeDeliverNum;
	
	//收货信息备注
	private String takeDeliveryRemark;
	
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


	public String getTakeDeliveryWarehouseSerial() {
		return takeDeliveryWarehouseSerial;
	}

	public void setTakeDeliveryWarehouseSerial(String takeDeliveryWarehouseSerial) {
		this.takeDeliveryWarehouseSerial = takeDeliveryWarehouseSerial;
	}

	@JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd")
	public Date getTakeDeliverDate() {
		return takeDeliverDate;
	}

	public void setTakeDeliverDate(Date takeDeliverDate) {
		this.takeDeliverDate = takeDeliverDate;
	}

	public String getTakeDeliverNum() {
		return takeDeliverNum;
	}

	public void setTakeDeliverNum(String takeDeliverNum) {
		this.takeDeliverNum = takeDeliverNum;
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

	public String getTakeAddress() {
		return takeAddress;
	}

	public void setTakeAddress(String takeAddress) {
		this.takeAddress = takeAddress;
	}

	public String getTakeDeliveryVOSerialNum() {
		return takeDeliveryVOSerialNum;
	}

	public void setTakeDeliveryVOSerialNum(String takeDeliveryVOSerialNum) {
		this.takeDeliveryVOSerialNum = takeDeliveryVOSerialNum;
	}
	
}
