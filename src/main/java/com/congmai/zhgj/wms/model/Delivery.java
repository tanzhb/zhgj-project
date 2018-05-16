package com.congmai.zhgj.wms.model;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Delivery {
    private String id;

    private String deliveryNum;

    private String projectNum;

    private String deliveryType;

    private String orderNum;

    private String shipper;//发货方
    private String shipperStorageId;//发货仓库
    private String shipperStorageName;//发货仓库

    private String receiver;//收货方
    private String receiverStorageId;//收货仓库
    private String receiverStorageName;//收货仓库

    private String takeDeliveryStorage;

    private String takeDeliveryAddress;

    @JSONField(format="yyyy-MM-dd")
    private Date arrivalDate;

    private String transportType;

    private String deliveryStorage;

    private String deliveryAddress;

    private String consignee;

    private String contactNum;

    private String publisher;

    @JSONField(format="yyyy-MM-dd")
    private Date publishDate;

    private String fromRemark;

    private String status;

    private Integer delFlg;

    private Integer isForbit;

    private Integer isDefualt;

    private Date createTime;

    private String creator;

    private String creatorName;

    private Date updateTime;

    private String updator;

    private String remark;

    private String searchKey;

    public static final String SAVE = "0"; //待发货

    public static final String CONFIRM = "1"; //待拣货

    public static final String RE_PICKING = "2"; //待复拣

    public static final String CHECK = "3"; //待检验

    public static final String STOCK_OUT = "4"; //待出库

    public static final String STOCK_OUT_COMPLETE = "5"; //已出库

    public static final String CLOSE_ORDER = "6"; //订单关闭






    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDeliveryNum() {
        return deliveryNum;
    }

    public void setDeliveryNum(String deliveryNum) {
        this.deliveryNum = deliveryNum == null ? null : deliveryNum.trim();
    }

    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum == null ? null : projectNum.trim();
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType == null ? null : deliveryType.trim();
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum == null ? null : orderNum.trim();
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

    public String getTakeDeliveryStorage() {
        return takeDeliveryStorage;
    }

    public void setTakeDeliveryStorage(String takeDeliveryStorage) {
        this.takeDeliveryStorage = takeDeliveryStorage == null ? null : takeDeliveryStorage.trim();
    }

    public String getTakeDeliveryAddress() {
        return takeDeliveryAddress;
    }

    public void setTakeDeliveryAddress(String takeDeliveryAddress) {
        this.takeDeliveryAddress = takeDeliveryAddress == null ? null : takeDeliveryAddress.trim();
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType == null ? null : transportType.trim();
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress == null ? null : deliveryAddress.trim();
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee == null ? null : consignee.trim();
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum == null ? null : contactNum.trim();
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher == null ? null : publisher.trim();
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getFromRemark() {
        return fromRemark;
    }

    public void setFromRemark(String fromRemark) {
        this.fromRemark = fromRemark == null ? null : fromRemark.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getDelFlg() {
        return delFlg;
    }

    public void setDelFlg(Integer delFlg) {
        this.delFlg = delFlg;
    }

    public Integer getIsForbit() {
        return isForbit;
    }

    public void setIsForbit(Integer isForbit) {
        this.isForbit = isForbit;
    }

    public Integer getIsDefualt() {
        return isDefualt;
    }

    public void setIsDefualt(Integer isDefualt) {
        this.isDefualt = isDefualt;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator == null ? null : updator.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public String getDeliveryStorage() {
        return deliveryStorage;
    }

    public void setDeliveryStorage(String deliveryStorage) {
        this.deliveryStorage = deliveryStorage;
    }

    public String getShipperStorageId() {
        return shipperStorageId;
    }

    public void setShipperStorageId(String shipperStorageId) {
        this.shipperStorageId = shipperStorageId;
    }

    public String getShipperStorageName() {
        return shipperStorageName;
    }

    public void setShipperStorageName(String shipperStorageName) {
        this.shipperStorageName = shipperStorageName;
    }

    public String getReceiverStorageId() {
        return receiverStorageId;
    }

    public void setReceiverStorageId(String receiverStorageId) {
        this.receiverStorageId = receiverStorageId;
    }

    public String getReceiverStorageName() {
        return receiverStorageName;
    }

    public void setReceiverStorageName(String receiverStorageName) {
        this.receiverStorageName = receiverStorageName;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }
}
