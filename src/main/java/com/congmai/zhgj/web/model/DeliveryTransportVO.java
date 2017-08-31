package com.congmai.zhgj.web.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DeliveryTransportVO {
	//发货运输流水号
	private String serialNum;
	
	//发货流水号
	private String deliverSerial;
	
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
	
	//联系人
	private String transportContact;
	
	//联系电话
	private String transportContactNum;

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

}
