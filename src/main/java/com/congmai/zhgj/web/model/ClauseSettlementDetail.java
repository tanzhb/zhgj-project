package com.congmai.zhgj.web.model;

import java.util.Date;

public class ClauseSettlementDetail {
    private String serialNum;

    private String clauseSettlementSerial;

    private String paymentType;

    private String deliveryNode;

    private String accountPeriod;

    private String deliveryRate;

    private String deliveryAmount;

    private String paymentMethod;

    private String billingMethod;

    private String billingAmount;

    private String unbilledAmount;

    private String remark;

    private String status;

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

    public String getClauseSettlementSerial() {
        return clauseSettlementSerial;
    }

    public void setClauseSettlementSerial(String clauseSettlementSerial) {
        this.clauseSettlementSerial = clauseSettlementSerial == null ? null : clauseSettlementSerial.trim();
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType == null ? null : paymentType.trim();
    }

    public String getDeliveryNode() {
        return deliveryNode;
    }

    public void setDeliveryNode(String deliveryNode) {
        this.deliveryNode = deliveryNode == null ? null : deliveryNode.trim();
    }

    public String getAccountPeriod() {
        return accountPeriod;
    }

    public void setAccountPeriod(String accountPeriod) {
        this.accountPeriod = accountPeriod == null ? null : accountPeriod.trim();
    }

    public String getDeliveryRate() {
        return deliveryRate;
    }

    public void setDeliveryRate(String deliveryRate) {
        this.deliveryRate = deliveryRate == null ? null : deliveryRate.trim();
    }

    public String getDeliveryAmount() {
        return deliveryAmount;
    }

    public void setDeliveryAmount(String deliveryAmount) {
        this.deliveryAmount = deliveryAmount == null ? null : deliveryAmount.trim();
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod == null ? null : paymentMethod.trim();
    }

    public String getBillingMethod() {
        return billingMethod;
    }

    public void setBillingMethod(String billingMethod) {
        this.billingMethod = billingMethod == null ? null : billingMethod.trim();
    }

    public String getBillingAmount() {
        return billingAmount;
    }

    public void setBillingAmount(String billingAmount) {
        this.billingAmount = billingAmount == null ? null : billingAmount.trim();
    }

    public String getUnbilledAmount() {
        return unbilledAmount;
    }

    public void setUnbilledAmount(String unbilledAmount) {
        this.unbilledAmount = unbilledAmount == null ? null : unbilledAmount.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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