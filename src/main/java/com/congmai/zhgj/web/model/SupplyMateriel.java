package com.congmai.zhgj.web.model;

import java.util.Date;

public class SupplyMateriel {
    private String serialNum;

    private String materielId;

    private String supplyComId;

    private String supplyMaterielNum;

    private String packageNum;

    private String packageSpecifications;

    private String packageUnit;

    private String packagewidth;

    private String packageheight;

    private String packagelength;

    private String packagevolume;

    private String packageUnitConversion;

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

    public String getMaterielId() {
        return materielId;
    }

    public void setMaterielId(String materielId) {
        this.materielId = materielId == null ? null : materielId.trim();
    }

    public String getSupplyComId() {
        return supplyComId;
    }

    public void setSupplyComId(String supplyComId) {
        this.supplyComId = supplyComId == null ? null : supplyComId.trim();
    }

    public String getSupplyMaterielNum() {
        return supplyMaterielNum;
    }

    public void setSupplyMaterielNum(String supplyMaterielNum) {
        this.supplyMaterielNum = supplyMaterielNum == null ? null : supplyMaterielNum.trim();
    }

    public String getPackageNum() {
        return packageNum;
    }

    public void setPackageNum(String packageNum) {
        this.packageNum = packageNum == null ? null : packageNum.trim();
    }

    public String getPackageSpecifications() {
        return packageSpecifications;
    }

    public void setPackageSpecifications(String packageSpecifications) {
        this.packageSpecifications = packageSpecifications == null ? null : packageSpecifications.trim();
    }

    public String getPackageUnit() {
        return packageUnit;
    }

    public void setPackageUnit(String packageUnit) {
        this.packageUnit = packageUnit == null ? null : packageUnit.trim();
    }

    public String getPackagewidth() {
        return packagewidth;
    }

    public void setPackagewidth(String packagewidth) {
        this.packagewidth = packagewidth == null ? null : packagewidth.trim();
    }

    public String getPackageheight() {
        return packageheight;
    }

    public void setPackageheight(String packageheight) {
        this.packageheight = packageheight == null ? null : packageheight.trim();
    }

    public String getPackagelength() {
        return packagelength;
    }

    public void setPackagelength(String packagelength) {
        this.packagelength = packagelength == null ? null : packagelength.trim();
    }

    public String getPackagevolume() {
        return packagevolume;
    }

    public void setPackagevolume(String packagevolume) {
        this.packagevolume = packagevolume == null ? null : packagevolume.trim();
    }

    public String getPackageUnitConversion() {
        return packageUnitConversion;
    }

    public void setPackageUnitConversion(String packageUnitConversion) {
        this.packageUnitConversion = packageUnitConversion == null ? null : packageUnitConversion.trim();
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