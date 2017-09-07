package com.congmai.zhgj.web.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Statement {
    private String serialNum;

    private String statementNum;

    private Date statementDate;

    private String supplyComId;

    private String buyComId;

    private String totalAmount;

    private String deliveryAmount;

    private String paymentAmount;

    private String beginShouldPay;

    private String nowShouldPay;

    private String nowAlreadyPay;

    private String endShouldPay;

    private String overTimeAmout;

    private String serviceAmount;

    private Date reciveDate;

    private String nowOtherAmout;

    private String status;

    private String remark;

    private String delFlg;

    private String creator;

    private Date createTime;

    private String updater;

    private Date updateTime;

    private String approver;

    private Date approvalDate;

    private Date makeDate;

    private String maker;
    
    private String supplyName;
    
    private String buyName;

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum == null ? null : serialNum.trim();
    }

    public String getStatementNum() {
        return statementNum;
    }

    public void setStatementNum(String statementNum) {
        this.statementNum = statementNum == null ? null : statementNum.trim();
    }

    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    public Date getStatementDate() {
        return statementDate;
    }

    public void setStatementDate(Date statementDate) {
        this.statementDate = statementDate;
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

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount == null ? null : totalAmount.trim();
    }

    public String getDeliveryAmount() {
        return deliveryAmount;
    }

    public void setDeliveryAmount(String deliveryAmount) {
        this.deliveryAmount = deliveryAmount == null ? null : deliveryAmount.trim();
    }

    public String getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(String paymentAmount) {
        this.paymentAmount = paymentAmount == null ? null : paymentAmount.trim();
    }

    public String getBeginShouldPay() {
        return beginShouldPay;
    }

    public void setBeginShouldPay(String beginShouldPay) {
        this.beginShouldPay = beginShouldPay == null ? null : beginShouldPay.trim();
    }

    public String getNowShouldPay() {
        return nowShouldPay;
    }

    public void setNowShouldPay(String nowShouldPay) {
        this.nowShouldPay = nowShouldPay == null ? null : nowShouldPay.trim();
    }

    public String getNowAlreadyPay() {
        return nowAlreadyPay;
    }

    public void setNowAlreadyPay(String nowAlreadyPay) {
        this.nowAlreadyPay = nowAlreadyPay == null ? null : nowAlreadyPay.trim();
    }

    public String getEndShouldPay() {
        return endShouldPay;
    }

    public void setEndShouldPay(String endShouldPay) {
        this.endShouldPay = endShouldPay == null ? null : endShouldPay.trim();
    }

    public String getOverTimeAmout() {
        return overTimeAmout;
    }

    public void setOverTimeAmout(String overTimeAmout) {
        this.overTimeAmout = overTimeAmout == null ? null : overTimeAmout.trim();
    }

    public String getServiceAmount() {
        return serviceAmount;
    }

    public void setServiceAmount(String serviceAmount) {
        this.serviceAmount = serviceAmount == null ? null : serviceAmount.trim();
    }

    public Date getReciveDate() {
        return reciveDate;
    }

    public void setReciveDate(Date reciveDate) {
        this.reciveDate = reciveDate;
    }

    public String getNowOtherAmout() {
        return nowOtherAmout;
    }

    public void setNowOtherAmout(String nowOtherAmout) {
        this.nowOtherAmout = nowOtherAmout == null ? null : nowOtherAmout.trim();
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

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver == null ? null : approver.trim();
    }

    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    public Date getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }

    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    public Date getMakeDate() {
        return makeDate;
    }

    public void setMakeDate(Date makeDate) {
        this.makeDate = makeDate;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker == null ? null : maker.trim();
    }

	public String getSupplyName() {
		return supplyName;
	}

	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}

	public String getBuyName() {
		return buyName;
	}

	public void setBuyName(String buyName) {
		this.buyName = buyName;
	}
}