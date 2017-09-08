package com.congmai.zhgj.web.model;

import java.util.Date;

public class PaymentPlanView {
    private String serialNum;

    private String paymentPlanNum;

    private String supplyComId;

    private String buyComId;

    private String paymentType;

    private String orderSerial;

    private String clauseSettlementSerial;

    private String paymentNode;

    private String paymentNO;

    private String paymentRate;

    private String paymentAmount;

    private String period;

    private String paymentStyle;

    private String billStyle;

    private String readyAmount;

    private String unreadyAmount;

    private Date playPaymentDate;

    private String approver;

    private Date applyDate;

    private String applicant;

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

    public String getPaymentPlanNum() {
        return paymentPlanNum;
    }

    public void setPaymentPlanNum(String paymentPlanNum) {
        this.paymentPlanNum = paymentPlanNum == null ? null : paymentPlanNum.trim();
    }

    public String getSupplyComId() {
        return supplyComId;
    }

    public void setSupplyComId(String supplyComId) {
        this.supplyComId = supplyComId == null ? null : supplyComId.trim();
    }

    public String getBuyComId() {
        return buyComId;
    }

    public void setBuyComId(String buyComId) {
        this.buyComId = buyComId == null ? null : buyComId.trim();
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType == null ? null : paymentType.trim();
    }

    public String getOrderSerial() {
        return orderSerial;
    }

    public void setOrderSerial(String orderSerial) {
        this.orderSerial = orderSerial == null ? null : orderSerial.trim();
    }

    public String getClauseSettlementSerial() {
        return clauseSettlementSerial;
    }

    public void setClauseSettlementSerial(String clauseSettlementSerial) {
        this.clauseSettlementSerial = clauseSettlementSerial == null ? null : clauseSettlementSerial.trim();
    }

    public String getPaymentNode() {
        return paymentNode;
    }

    public void setPaymentNode(String paymentNode) {
        this.paymentNode = paymentNode == null ? null : paymentNode.trim();
    }

    public String getPaymentNO() {
        return paymentNO;
    }

    public void setPaymentNO(String paymentNO) {
        this.paymentNO = paymentNO == null ? null : paymentNO.trim();
    }

    public String getPaymentRate() {
        return paymentRate;
    }

    public void setPaymentRate(String paymentRate) {
        this.paymentRate = paymentRate == null ? null : paymentRate.trim();
    }

    public String getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(String paymentAmount) {
        this.paymentAmount = paymentAmount == null ? null : paymentAmount.trim();
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period == null ? null : period.trim();
    }

    public String getPaymentStyle() {
        return paymentStyle;
    }

    public void setPaymentStyle(String paymentStyle) {
        this.paymentStyle = paymentStyle == null ? null : paymentStyle.trim();
    }

    public String getBillStyle() {
        return billStyle;
    }

    public void setBillStyle(String billStyle) {
        this.billStyle = billStyle == null ? null : billStyle.trim();
    }

    public String getReadyAmount() {
        return readyAmount;
    }

    public void setReadyAmount(String readyAmount) {
        this.readyAmount = readyAmount == null ? null : readyAmount.trim();
    }

    public String getUnreadyAmount() {
        return unreadyAmount;
    }

    public void setUnreadyAmount(String unreadyAmount) {
        this.unreadyAmount = unreadyAmount == null ? null : unreadyAmount.trim();
    }

    public Date getPlayPaymentDate() {
        return playPaymentDate;
    }

    public void setPlayPaymentDate(Date playPaymentDate) {
        this.playPaymentDate = playPaymentDate;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver == null ? null : approver.trim();
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant == null ? null : applicant.trim();
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