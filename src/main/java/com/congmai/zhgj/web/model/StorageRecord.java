package com.congmai.zhgj.web.model;

import java.util.Date;

/**
 * @ClassName StorageRecord
 * @Description TODO(出入库记录)
 * @author zhaichao
 * @Date 2017年8月21日 下午5:32:40
 * @version 1.0.0
 */
public class StorageRecord {
    private String serialNum;

    private String stockSerial;//库存流水

    private String warehousePositionSerial;//库位流水

    private String deliveryMaterielSerial;//发货物料流水号

    private String inOrOut;//出入库标志（1出0入）

    private String delFlg;

    private String creator;

    private Date createTime;

    private String updater;

    private Date updateTime;

    private String remark;

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum == null ? null : serialNum.trim();
    }

    public String getStockSerial() {
        return stockSerial;
    }

    public void setStockSerial(String stockSerial) {
        this.stockSerial = stockSerial == null ? null : stockSerial.trim();
    }

    public String getWarehousePositionSerial() {
        return warehousePositionSerial;
    }

    public void setWarehousePositionSerial(String warehousePositionSerial) {
        this.warehousePositionSerial = warehousePositionSerial == null ? null : warehousePositionSerial.trim();
    }

    public String getDeliveryMaterielSerial() {
        return deliveryMaterielSerial;
    }

    public void setDeliveryMaterielSerial(String deliveryMaterielSerial) {
        this.deliveryMaterielSerial = deliveryMaterielSerial == null ? null : deliveryMaterielSerial.trim();
    }

    public String getInOrOut() {
        return inOrOut;
    }

    public void setInOrOut(String inOrOut) {
        this.inOrOut = inOrOut == null ? null : inOrOut.trim();
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}