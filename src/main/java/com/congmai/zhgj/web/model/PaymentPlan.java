package com.congmai.zhgj.web.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PaymentPlan {
	//流水
    private String serialNum;

    //付款计划编号
    private String paymentPlanNum;
    
    //供应商
    private String supplyComId;

    //采购商
    private String buyComId;

    //发票流水
    private String invoiceSerial;
    
    //订单流水
    private String orderSerial;

    //收/付款类型
    private String paymentType;

    //结算条款流水
    private String clauseSettlementSerial;
    
    //支付节点
    private String paymentNode;

    //支付单据号
    private String paymentNO;

    //计划收/付款日期
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date playPaymentDate;

    //申请日期
    private Date applyDate;
    
    //支付比率
    private String paymentRate;

    //支付金额
    private String paymentAmount;

    //账期
    private String period;

    //支付方式
    private String paymentStyle;
    
    //billStyle
    private String billStyle;

    //已开金额
    private String readyAmount;
    
    //未开金额
    private String unreadyAmount;
    
    //审批人
    private String approver;
    
    //申请人
    private String applicant;
    
    //状态
    private String status;
    
    //备注
    private String remark;

    //创建人
    private String creator;

    //createTime
    private Date createTime;

    //updateTime
    private Date updateTime;

    //updater
    private String updater;

	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public String getPaymentPlanNum() {
		return paymentPlanNum;
	}

	public void setPaymentPlanNum(String paymentPlanNum) {
		this.paymentPlanNum = paymentPlanNum;
	}

	public String getSupplyComId() {
		return supplyComId;
	}

	public void setSupplyComId(String supplyComId) {
		this.supplyComId = supplyComId;
	}

	public String getBuyComId() {
		return buyComId;
	}

	public void setBuyComId(String buyComId) {
		this.buyComId = buyComId;
	}

	public String getInvoiceSerial() {
		return invoiceSerial;
	}

	public void setInvoiceSerial(String invoiceSerial) {
		this.invoiceSerial = invoiceSerial;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getClauseSettlementSerial() {
		return clauseSettlementSerial;
	}

	public void setClauseSettlementSerial(String clauseSettlementSerial) {
		this.clauseSettlementSerial = clauseSettlementSerial;
	}

	public String getPaymentNode() {
		return paymentNode;
	}

	public void setPaymentNode(String paymentNode) {
		this.paymentNode = paymentNode;
	}

	public String getPaymentNO() {
		return paymentNO;
	}

	public void setPaymentNO(String paymentNO) {
		this.paymentNO = paymentNO;
	}

	@JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd")
	public Date getPlayPaymentDate() {
		return playPaymentDate;
	}

	public void setPlayPaymentDate(Date playPaymentDate) {
		this.playPaymentDate = playPaymentDate;
	}

	@JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd")
	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public String getPaymentRate() {
		return paymentRate;
	}

	public void setPaymentRate(String paymentRate) {
		this.paymentRate = paymentRate;
	}

	public String getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(String paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getPaymentStyle() {
		return paymentStyle;
	}

	public void setPaymentStyle(String paymentStyle) {
		this.paymentStyle = paymentStyle;
	}

	public String getBillStyle() {
		return billStyle;
	}

	public void setBillStyle(String billStyle) {
		this.billStyle = billStyle;
	}

	public String getReadyAmount() {
		return readyAmount;
	}

	public void setReadyAmount(String readyAmount) {
		this.readyAmount = readyAmount;
	}

	public String getUnreadyAmount() {
		return unreadyAmount;
	}

	public void setUnreadyAmount(String unreadyAmount) {
		this.unreadyAmount = unreadyAmount;
	}

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdater() {
		return updater;
	}

	public void setUpdater(String updater) {
		this.updater = updater;
	}

	public String getOrderSerial() {
		return orderSerial;
	}

	public void setOrderSerial(String orderSerial) {
		this.orderSerial = orderSerial;
	}
}