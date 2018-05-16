package com.congmai.zhgj.wms.model;


import java.util.Date;

public class DeliveryProduct {
    private String id;

    private String deliveryId;

    private String productId;

    private String amount;

    private String sendCount;

    private String deliveryRemark;

    private Integer delFlg;

    private Integer isForbit;

    private Integer isDefualt;

    private Date createTime;

    private String creator;

    private Date updateTime;

    private String updator;

    private String remark;

    private Product product;

    private String inventoryCount;

    private String lackCount;

    /**
     * 炉批号
     */
    private String batchFurnaceNum;

    /**
     * 机型机号
     */
    private String machineNum;

    /**
     * 项目编号
     */
    private String projectNum;
/**
     * 合同编号
     */
    private String contractNum;

    private String orderIndex;

    private String storageType;

    private String state;


    private boolean noMatch = false;



    public String getStorageType() {
        return storageType;
    }

    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId == null ? null : deliveryId.trim();
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount == null ? null : amount.trim();
    }

    public String getDeliveryRemark() {
        return deliveryRemark;
    }

    public void setDeliveryRemark(String deliveryRemark) {
        this.deliveryRemark = deliveryRemark == null ? null : deliveryRemark.trim();
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getInventoryCount() {
        return inventoryCount;
    }

    public void setInventoryCount(String inventoryCount) {
        this.inventoryCount = inventoryCount;
    }

    public String getLackCount() {
        return lackCount;
    }

    public void setLackCount(String lackCount) {
        this.lackCount = lackCount;
    }

    public String getSendCount() {
        return sendCount;
    }

    public void setSendCount(String sendCount) {
        this.sendCount = sendCount;
    }

    public String getBatchFurnaceNum() {
        return batchFurnaceNum;
    }

    public void setBatchFurnaceNum(String batchFurnaceNum) {
        this.batchFurnaceNum = batchFurnaceNum;
    }

    public String getMachineNum() {
        return machineNum;
    }

    public void setMachineNum(String machineNum) {
        this.machineNum = machineNum;
    }

    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }

    public String getContractNum() {
        return contractNum;
    }

    public void setContractNum(String contractNum) {
        this.contractNum = contractNum;
    }

    public String getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(String orderIndex) {
        this.orderIndex = orderIndex;
    }
    public boolean isNoMatch() {
        return noMatch;
    }

    public void setNoMatch(boolean noMatch) {
        this.noMatch = noMatch;
    }
}
