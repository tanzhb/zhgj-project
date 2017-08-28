package com.congmai.zhgj.web.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DeliveryMateriel {
    private String serialNum;

    private String deliverSerial;

    private String orderMaterielSerial;

    private String batchNum;

    private Date manufactureDate;

    private String deliverCount;

    private String deliverRemark;

    private String acceptCount;

    private String refuseCount;

    private String takeRemark;

    private String qualifiedCount;

    private String unqualifiedCount;

    private String checkRemark;

    private String warehouseSerial;

    private String positionSerial;

    private String stockCount;

    private String unstockCount;

    private String stockRemark;

    private String remark;

    private String delFlg;

    private String creator;

    private Date createTime;

    private String updater;

    private Date updateTime;
    
    private OrderMateriel orderMateriel;
    
    private Warehouse warehouse;

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

    public String getOrderMaterielSerial() {
        return orderMaterielSerial;
    }

    public void setOrderMaterielSerial(String orderMaterielSerial) {
        this.orderMaterielSerial = orderMaterielSerial == null ? null : orderMaterielSerial.trim();
    }

    public String getBatchNum() {
        return batchNum;
    }

    public void setBatchNum(String batchNum) {
        this.batchNum = batchNum == null ? null : batchNum.trim();
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
        this.deliverCount = deliverCount == null ? null : deliverCount.trim();
    }

    public String getDeliverRemark() {
        return deliverRemark;
    }

    public void setDeliverRemark(String deliverRemark) {
        this.deliverRemark = deliverRemark == null ? null : deliverRemark.trim();
    }

    public String getAcceptCount() {
        return acceptCount;
    }

    public void setAcceptCount(String acceptCount) {
        this.acceptCount = acceptCount == null ? null : acceptCount.trim();
    }

    public String getRefuseCount() {
        return refuseCount;
    }

    public void setRefuseCount(String refuseCount) {
        this.refuseCount = refuseCount == null ? null : refuseCount.trim();
    }

    public String getTakeRemark() {
        return takeRemark;
    }

    public void setTakeRemark(String takeRemark) {
        this.takeRemark = takeRemark == null ? null : takeRemark.trim();
    }

    public String getQualifiedCount() {
        return qualifiedCount;
    }

    public void setQualifiedCount(String qualifiedCount) {
        this.qualifiedCount = qualifiedCount == null ? null : qualifiedCount.trim();
    }

    public String getUnqualifiedCount() {
        return unqualifiedCount;
    }

    public void setUnqualifiedCount(String unqualifiedCount) {
        this.unqualifiedCount = unqualifiedCount == null ? null : unqualifiedCount.trim();
    }

    public String getCheckRemark() {
        return checkRemark;
    }

    public void setCheckRemark(String checkRemark) {
        this.checkRemark = checkRemark == null ? null : checkRemark.trim();
    }

    public String getWarehouseSerial() {
        return warehouseSerial;
    }

    public void setWarehouseSerial(String warehouseSerial) {
        this.warehouseSerial = warehouseSerial == null ? null : warehouseSerial.trim();
    }

    public String getPositionSerial() {
        return positionSerial;
    }

    public void setPositionSerial(String positionSerial) {
        this.positionSerial = positionSerial == null ? null : positionSerial.trim();
    }

    public String getStockCount() {
        return stockCount;
    }

    public void setStockCount(String stockCount) {
        this.stockCount = stockCount == null ? null : stockCount.trim();
    }

    public String getUnstockCount() {
        return unstockCount;
    }

    public void setUnstockCount(String unstockCount) {
        this.unstockCount = unstockCount == null ? null : unstockCount.trim();
    }

    public String getStockRemark() {
        return stockRemark;
    }

    public void setStockRemark(String stockRemark) {
        this.stockRemark = stockRemark == null ? null : stockRemark.trim();
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

	public OrderMateriel getOrderMateriel() {
		return orderMateriel;
	}

	public void setOrderMateriel(OrderMateriel orderMateriel) {
		this.orderMateriel = orderMateriel;
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}
    
    
}