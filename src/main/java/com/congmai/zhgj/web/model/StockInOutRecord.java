package com.congmai.zhgj.web.model;

import java.util.Date;

/**
 * @ClassName StockInOutRecord
 * @Description TODO(出入库记录)
 * @author zhaichao
 * @Date 2017年8月23日 上午10:03:55
 * @version 1.0.0
 */
public class StockInOutRecord {
    private String serialNum;

    private String deliverSerial;

    private String takeDeliverSerial;

    private String inOutNum;

    private Date stockDate;

    private String operator;

    private String contactNum;

    private String status;

    private String remark;

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

    public String getDeliverSerial() {
        return deliverSerial;
    }

    public void setDeliverSerial(String deliverSerial) {
        this.deliverSerial = deliverSerial == null ? null : deliverSerial.trim();
    }

    public String getTakeDeliverSerial() {
        return takeDeliverSerial;
    }

    public void setTakeDeliverSerial(String takeDeliverSerial) {
        this.takeDeliverSerial = takeDeliverSerial == null ? null : takeDeliverSerial.trim();
    }

    public String getInOutNum() {
        return inOutNum;
    }

    public void setInOutNum(String inOutNum) {
        this.inOutNum = inOutNum == null ? null : inOutNum.trim();
    }

    public Date getStockDate() {
        return stockDate;
    }

    public void setStockDate(Date stockDate) {
        this.stockDate = stockDate;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum == null ? null : contactNum.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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
}