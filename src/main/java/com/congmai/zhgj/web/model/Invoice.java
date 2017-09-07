package com.congmai.zhgj.web.model;

import java.util.Date;

/**
 * @ClassName Invoice
 * @Description TODO(发票)
 * @author zhaichao
 * @Date 2017年8月30日 下午1:47:08
 * @version 1.0.0
 */
public class Invoice {
    private String serialNum;

    private String invoiceNum;

    private String supplyComId;

    private String buyComId;

    private String orderSerial;

    private String paymentSerial;
    
    private String clauseSettlementSerial;//结算条款流水号

    private String paymentAmount;

    private String paymentStatus;

    private String invoiceType;

    private String invoiceAmount;

    private Date billingDate;

    private String invoiceNO;

    private String submitter;

    private Date submitDate;

    private String billingRemark;

    private Date receiptDate;

    private String operator;

    private String receiptRemark;

    private String invoiceVoucher;

    private String delFlg;

    private String creator;

    private Date createTime;

    private String updater;

    private Date updateTime;
    
    private  String status;
    
    private  String relationBuyOrSaleNum;//关联销售/采购单号
    
    private  String relationReceiveOrPayNum;//关联收付款编号
    
    private String orderAmount;//订单金额
    
    private  String billWay;//开票方式
    
    private  int invoiceCount;//发票数
    
    private  String comName;//收票方/开票方名称  
    
    private  String unBillAmount;//未开金额
    
    private  String approver;
    
    private  Date approvalDate;

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum == null ? null : serialNum.trim();
    }

    public String getInvoiceNum() {
        return invoiceNum;
    }

    public void setInvoiceNum(String invoiceNum) {
        this.invoiceNum = invoiceNum == null ? null : invoiceNum.trim();
    }
    public String getOrderSerial() {
        return orderSerial;
    }

    public void setOrderSerial(String orderSerial) {
        this.orderSerial = orderSerial == null ? null : orderSerial.trim();
    }

    public String getPaymentSerial() {
        return paymentSerial;
    }

    public void setPaymentSerial(String paymentSerial) {
        this.paymentSerial = paymentSerial == null ? null : paymentSerial.trim();
    }

    public String getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(String paymentAmount) {
        this.paymentAmount = paymentAmount == null ? null : paymentAmount.trim();
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus == null ? null : paymentStatus.trim();
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType == null ? null : invoiceType.trim();
    }

    public String getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(String invoiceAmount) {
        this.invoiceAmount = invoiceAmount == null ? null : invoiceAmount.trim();
    }

    public Date getBillingDate() {
        return billingDate;
    }

    public void setBillingDate(Date billingDate) {
        this.billingDate = billingDate;
    }

    public String getInvoiceNO() {
        return invoiceNO;
    }

    public void setInvoiceNO(String invoiceNO) {
        this.invoiceNO = invoiceNO == null ? null : invoiceNO.trim();
    }

    public String getSubmitter() {
        return submitter;
    }

    public void setSubmitter(String submitter) {
        this.submitter = submitter == null ? null : submitter.trim();
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public String getBillingRemark() {
        return billingRemark;
    }

    public void setBillingRemark(String billingRemark) {
        this.billingRemark = billingRemark == null ? null : billingRemark.trim();
    }

    public Date getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public String getReceiptRemark() {
        return receiptRemark;
    }

    public void setReceiptRemark(String receiptRemark) {
        this.receiptRemark = receiptRemark == null ? null : receiptRemark.trim();
    }

    public String getInvoiceVoucher() {
        return invoiceVoucher;
    }

    public void setInvoiceVoucher(String invoiceVoucher) {
        this.invoiceVoucher = invoiceVoucher == null ? null : invoiceVoucher.trim();
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getClauseSettlementSerial() {
		return clauseSettlementSerial;
	}

	public void setClauseSettlementSerial(String clauseSettlementSerial) {
		this.clauseSettlementSerial = clauseSettlementSerial;
	}

	public String getRelationBuyOrSaleNum() {
		return relationBuyOrSaleNum;
	}

	public void setRelationBuyOrSaleNum(String relationBuyOrSaleNum) {
		this.relationBuyOrSaleNum = relationBuyOrSaleNum;
	}

	public String getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getBillWay() {
		return billWay;
	}

	public void setBillWay(String billWay) {
		this.billWay = billWay;
	}

	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	public String getUnBillAmount() {
		return unBillAmount;
	}

	public void setUnBillAmount(String unBillAmount) {
		this.unBillAmount = unBillAmount;
	}

	public int getInvoiceCount() {
		return invoiceCount;
	}

	public void setInvoiceCount(int invoiceCount) {
		this.invoiceCount = invoiceCount;
	}

	public String getRelationReceiveOrPayNum() {
		return relationReceiveOrPayNum;
	}

	public void setRelationReceiveOrPayNum(String relationReceiveOrPayNum) {
		this.relationReceiveOrPayNum = relationReceiveOrPayNum;
	}

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	public Date getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}
    
    
}