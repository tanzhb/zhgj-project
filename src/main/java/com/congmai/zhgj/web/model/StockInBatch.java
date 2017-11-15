package com.congmai.zhgj.web.model;

import java.util.Date;

public class StockInBatch {
    private String serialNum;

    private String stockInMaterielSerial;

    private String remark;

    private String delFlg;

    private String creator;

    private Date createTime;

    private String updater;

    private Date updateTime;

    private String warehouseSerial;

    private String positionSerial;

    private String stockInCount;

    private String batchNum;

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum == null ? null : serialNum.trim();
    }

    public String getStockInMaterielSerial() {
        return stockInMaterielSerial;
    }

    public void setStockInMaterielSerial(String stockInMaterielSerial) {
        this.stockInMaterielSerial = stockInMaterielSerial == null ? null : stockInMaterielSerial.trim();
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

    public String getStockInCount() {
        return stockInCount;
    }

    public void setStockInCount(String stockInCount) {
        this.stockInCount = stockInCount == null ? null : stockInCount.trim();
    }

    public String getBatchNum() {
        return batchNum;
    }

    public void setBatchNum(String batchNum) {
        this.batchNum = batchNum == null ? null : batchNum.trim();
    }
}