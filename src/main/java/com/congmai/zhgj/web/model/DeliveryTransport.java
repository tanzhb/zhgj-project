package com.congmai.zhgj.web.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DeliveryTransport {
    private String serialNum;
    

    private String deliverSerial;

    private String transportType;

    private String transport;

    private String port;

    private String shipNumber;

    private Date playArrivalDate;

    private Date playWarehouseDate;

    private String remark;

    private String delFlg;

    private String creator;

    private Date createTime;

    private String updater;

    private Date updateTime;

    private String contact;

    private String contactNum;
    

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum == null ? null : serialNum.trim();
    }

    public String getDeliverSerial() {
        return deliverSerial;
    }

    public void setDeliverSerial(String deliverSerial) {
        this.deliverSerial = deliverSerial == null ? null : deliverSerial.trim();
    }

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType == null ? null : transportType.trim();
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport == null ? null : transport.trim();
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port == null ? null : port.trim();
    }

    public String getShipNumber() {
        return shipNumber;
    }

    public void setShipNumber(String shipNumber) {
        this.shipNumber = shipNumber == null ? null : shipNumber.trim();
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum == null ? null : contactNum.trim();
    }



	
    
}