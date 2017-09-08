package com.congmai.zhgj.web.model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PaymentRecord {
	//流水
    private String serialNum;

    //支付单号
    private String paymentNum;
    
    //付款计划单号
    private String paymentPlanNum;

    //供应商
    private String supplyComId;

    //采购商
    private String buyComId;

    //发票流水
    private String invoiceSerial;
    
    //订单流水
    private String orderSerial;
    
    //订单编号
    private String orderNum;

    //支付类型
    private String paymentType;
    
    //付款金额
    private String paymentAmount;
    
    //付款类型
    private String paymentStyle;

    //付款计划流水
    private String paymentPlanSerial;
    
    //结算条款流水
    private String clauseSettlementSerial;
    
    //开票方式
    private String billStyle;

    //付款日期
    private Date paymentDate;

    //申请人
    private String approver;

    //审批日期
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date approvalDate;

    //申请日期
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date applyDate;

    //申请人
    private String applicant;

    //付款凭证֤
    private String paymentVoucher;

    //备注
    private String remark;

    //̬状态
    private String status;
    
    //是否开票
    private String isBill;
    
    //结算条款的支付类型
    private String clausePaymentType;
    
    //结算条款的结算节点
    private String deliveryNode;
    
    //结算条款的账期
    private String accountPeriod;
    
    //结算条款的支付比例
    private String deliveryRate;
    
    //结算条款的总金额
    private String deliveryAmount;
    
    //结算条款的支付方式
    private String paymentMethod;
    
    //结算条款的开票方式
    private String billingMethod;
    
    //结算条款的开票金额
    private String billingAmount;
    
    //结算条款的未开票金额
    private String unbilledAmount;
    
    //结算条款的备注
    private String clauseRemark;
    
    //结算条款的状态
    private String clauseStatus;
    
    //结算条款列表
    private List<ClauseSettlementDetail> clauseSettList;
    
    //结算明细流水
    private String clauseSettlementDetailSerialNum;

    //删除标示
    private String delFlg;

    //创建人
    private String creator;

    //创建时间
    private Date createTime;

    //更新时间
    private Date updateTime;

    //更新人
    private String updater;

    //供应商名称
    private String supplyComName;
    
    //采购商名称
    private String buyComName;
    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum == null ? null : serialNum.trim();
    }

    public String getPaymentNum() {
        return paymentNum;
    }

    public void setPaymentNum(String paymentNum) {
        this.paymentNum = paymentNum == null ? null : paymentNum.trim();
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

    public String getInvoiceSerial() {
        return invoiceSerial;
    }

    public void setInvoiceSerial(String invoiceSerial) {
        this.invoiceSerial = invoiceSerial == null ? null : invoiceSerial.trim();
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType == null ? null : paymentType.trim();
    }

    public String getPaymentPlanSerial() {
        return paymentPlanSerial;
    }

    public void setPaymentPlanSerial(String paymentPlanSerial) {
        this.paymentPlanSerial = paymentPlanSerial == null ? null : paymentPlanSerial.trim();
    }

    public String getBillStyle() {
        return billStyle;
    }

    public void setBillStyle(String billStyle) {
        this.billStyle = billStyle == null ? null : billStyle.trim();
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getIsBill() {
        return isBill;
    }

    public void setIsBill(String isBill) {
        this.isBill = isBill == null ? null : isBill.trim();
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver == null ? null : approver.trim();
    }

    @JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd")
    public Date getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }

    @JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd")
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

    public String getPaymentVoucher() {
        return paymentVoucher;
    }

    public void setPaymentVoucher(String paymentVoucher) {
        this.paymentVoucher = paymentVoucher == null ? null : paymentVoucher.trim();
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
        this.updater = updater == null ? null : updater.trim();
    }

	public String getPaymentPlanNum() {
		return paymentPlanNum;
	}

	public void setPaymentPlanNum(String paymentPlanNum) {
		this.paymentPlanNum = paymentPlanNum;
	}

	public String getClauseSettlementSerial() {
		return clauseSettlementSerial;
	}

	public void setClauseSettlementSerial(String clauseSettlementSerial) {
		this.clauseSettlementSerial = clauseSettlementSerial;
	}

	public String getOrderSerial() {
		return orderSerial;
	}

	public void setOrderSerial(String orderSerial) {
		this.orderSerial = orderSerial;
	}

	public String getPaymentStyle() {
		return paymentStyle;
	}

	public void setPaymentStyle(String paymentStyle) {
		this.paymentStyle = paymentStyle;
	}

	public String getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(String paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getClausePaymentType() {
		return clausePaymentType;
	}

	public void setClausePaymentType(String clausePaymentType) {
		this.clausePaymentType = clausePaymentType;
	}

	public String getDeliveryNode() {
		return deliveryNode;
	}

	public void setDeliveryNode(String deliveryNode) {
		this.deliveryNode = deliveryNode;
	}

	public String getAccountPeriod() {
		return accountPeriod;
	}

	public void setAccountPeriod(String accountPeriod) {
		this.accountPeriod = accountPeriod;
	}

	public String getDeliveryRate() {
		return deliveryRate;
	}

	public void setDeliveryRate(String deliveryRate) {
		this.deliveryRate = deliveryRate;
	}

	public String getDeliveryAmount() {
		return deliveryAmount;
	}

	public void setDeliveryAmount(String deliveryAmount) {
		this.deliveryAmount = deliveryAmount;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getBillingMethod() {
		return billingMethod;
	}

	public void setBillingMethod(String billingMethod) {
		this.billingMethod = billingMethod;
	}

	public String getBillingAmount() {
		return billingAmount;
	}

	public void setBillingAmount(String billingAmount) {
		this.billingAmount = billingAmount;
	}

	public String getUnbilledAmount() {
		return unbilledAmount;
	}

	public void setUnbilledAmount(String unbilledAmount) {
		this.unbilledAmount = unbilledAmount;
	}

	public String getClauseRemark() {
		return clauseRemark;
	}

	public void setClauseRemark(String clauseRemark) {
		this.clauseRemark = clauseRemark;
	}

	public String getClauseStatus() {
		return clauseStatus;
	}

	public void setClauseStatus(String clauseStatus) {
		this.clauseStatus = clauseStatus;
	}

	public List<ClauseSettlementDetail> getClauseSettList() {
		return clauseSettList;
	}

	public void setClauseSettList(List<ClauseSettlementDetail> clauseSettList) {
		this.clauseSettList = clauseSettList;
	}

	public String getClauseSettlementDetailSerialNum() {
		return clauseSettlementDetailSerialNum;
	}

	public void setClauseSettlementDetailSerialNum(
			String clauseSettlementDetailSerialNum) {
		this.clauseSettlementDetailSerialNum = clauseSettlementDetailSerialNum;
	}

	public String getSupplyComName() {
		return supplyComName;
	}

	public void setSupplyComName(String supplyComName) {
		this.supplyComName = supplyComName;
	}

	public String getBuyComName() {
		return buyComName;
	}

	public void setBuyComName(String buyComName) {
		this.buyComName = buyComName;
	}
}