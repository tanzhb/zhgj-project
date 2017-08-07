package com.congmai.zhgj.web.model;

import java.util.Date;

public class Warehouseposition {
    private String serialNum;

    private String warehouseSerial;

    private String positionNum;

    private String positionName;

    private String storageAttribute;

    private String maxRows;

    private String maxCols;

    private String maxLayers;

    private String storageType;

    private String storageMode;

    private String defaultLWH;

    private String defaultVolume;

    private String defaultBearing;

    private String delFlg;

    private String creator;

    private Date createTime;

    private String updater;

    private Date updateTime;

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum == null ? null : serialNum.trim();
    }

    public String getWarehouseSerial() {
        return warehouseSerial;
    }

    public void setWarehouseSerial(String warehouseSerial) {
        this.warehouseSerial = warehouseSerial == null ? null : warehouseSerial.trim();
    }

    public String getPositionNum() {
        return positionNum;
    }

    public void setPositionNum(String positionNum) {
        this.positionNum = positionNum == null ? null : positionNum.trim();
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName == null ? null : positionName.trim();
    }

    public String getStorageAttribute() {
        return storageAttribute;
    }

    public void setStorageAttribute(String storageAttribute) {
        this.storageAttribute = storageAttribute == null ? null : storageAttribute.trim();
    }

    public String getMaxRows() {
        return maxRows;
    }

    public void setMaxRows(String maxRows) {
        this.maxRows = maxRows == null ? null : maxRows.trim();
    }

    public String getMaxCols() {
        return maxCols;
    }

    public void setMaxCols(String maxCols) {
        this.maxCols = maxCols == null ? null : maxCols.trim();
    }

    public String getMaxLayers() {
        return maxLayers;
    }

    public void setMaxLayers(String maxLayers) {
        this.maxLayers = maxLayers == null ? null : maxLayers.trim();
    }

    public String getStorageType() {
        return storageType;
    }

    public void setStorageType(String storageType) {
        this.storageType = storageType == null ? null : storageType.trim();
    }

    public String getStorageMode() {
        return storageMode;
    }

    public void setStorageMode(String storageMode) {
        this.storageMode = storageMode == null ? null : storageMode.trim();
    }

    public String getDefaultLWH() {
        return defaultLWH;
    }

    public void setDefaultLWH(String defaultLWH) {
        this.defaultLWH = defaultLWH == null ? null : defaultLWH.trim();
    }

    public String getDefaultVolume() {
        return defaultVolume;
    }

    public void setDefaultVolume(String defaultVolume) {
        this.defaultVolume = defaultVolume == null ? null : defaultVolume.trim();
    }

    public String getDefaultBearing() {
        return defaultBearing;
    }

    public void setDefaultBearing(String defaultBearing) {
        this.defaultBearing = defaultBearing == null ? null : defaultBearing.trim();
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
}