package com.congmai.zhgj.web.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PaymentRecord extends BaseVO implements Serializable{
	
	private static final long serialVersionUID = -1495795296316800235L;

	//先款后票
	public static final String XKHP = "先款后票";
	//先票后款
	public static final String XPHK = "先票后款";
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
    
    
    
    private String payType;
    
    
    private String applyPaymentAmount;
    
    
    private String applyCurrency;
    
    
    private String paiedMoney;
    
    
    private String billedMoney;
    
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date playPaymentDate;
    
    
	private String paymentNode;
    
    
    private String nodeNum;
    
    
    private String applyDept;
    
    
    private String payee;
    
    
    private String contact;
    
    
    private String contactNum;
    
    
    private String bank;
    
    
    private String accountName;
    
    
    private String orderAmount;
    
    
    private List<PaymentFile> fileList;
    
    
    private String accountNumber;
    
    private String billType;
    


	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

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

	public String getApplyPaymentAmount() {
		return applyPaymentAmount;
	}

	public void setApplyPaymentAmount(String applyPaymentAmount) {
		this.applyPaymentAmount = applyPaymentAmount;
	}

	public String getApplyCurrency() {
		return applyCurrency;
	}

	public void setApplyCurrency(String applyCurrency) {
		this.applyCurrency = applyCurrency;
	}

	
	public String getPaymentNode() {
		return paymentNode;
	}

	public void setPaymentNode(String paymentNode) {
		this.paymentNode = paymentNode;
	}

	public String getNodeNum() {
		return nodeNum;
	}

	public void setNodeNum(String nodeNum) {
		this.nodeNum = nodeNum;
	}

	public String getApplyDept() {
		return applyDept;
	}

	public void setApplyDept(String applyDept) {
		this.applyDept = applyDept;
	}

	public String getPayee() {
		return payee;
	}

	public void setPayee(String payee) {
		this.payee = payee;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getContactNum() {
		return contactNum;
	}

	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	} public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	@JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd")
	public Date getPlayPaymentDate() {
		return playPaymentDate;
	}

	public void setPlayPaymentDate(Date playPaymentDate) {
		this.playPaymentDate = playPaymentDate;
	}

	public String getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getPaiedMoney() {
		return paiedMoney;
	}

	public void setPaiedMoney(String paiedMoney) {
		this.paiedMoney = paiedMoney;
	}

	public String getBilledMoney() {
		return billedMoney;
	}

	public void setBilledMoney(String billedMoney) {
		this.billedMoney = billedMoney;
	}

	public List<PaymentFile> getFileList() {
		return fileList;
	}

	public void setFileList(List<PaymentFile> fileList) {
		this.fileList = fileList;
	}
}