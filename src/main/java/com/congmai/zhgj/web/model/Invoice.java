package com.congmai.zhgj.web.model;

import java.util.Date;

/**
 * @ClassName Invoice
 * @Description TODO(发票)
 * @author zhaichao
 * @Date 2017年8月30日 下午1:47:08
 * @version 1.0.0
 */
public class Invoice extends BaseVO{
	
    /**
	 * @Field @serialVersionUID : TODO(这里用一句话描述这个类的作用)
	 */
	private static final long serialVersionUID = 6526485609642456608L;

	private String serialNum;

    private String invoiceNum;//发票编号

    private String supplyComId;

    private String buyComId;
    
    private String buyComName;//收票方
    
    private String supplyComName;//开票方

    private String orderSerial;

    private String paymentSerial;
    
    private String clauseSettlementSerial;//结算条款流水号

    private String paymentAmount;

    private String paymentStatus;//付款状态

    private String invoiceType;//发票类型

    private String invoiceAmount;//发票金额

    private Date billingDate;//开票日期

    private String invoiceNO;//发票号

    private String submitter;//申请人

    private Date submitDate;//申请日期
    
    private String submitDepartment;//申请部门

    private String billingRemark;

    private Date receiptDate;//申请开票日期

    private String operator;

    private String receiptRemark;
    
    private String secondRemark;
    
    private String thirdRemark;
    
    private String shouldPayOrReceiptMoney;//应付/应收金额
    
    private String prePayOrReceiptMoney;//预付/预收金额
    
    private String PayedOrReceiptedMoney;//已付/已收金额
    
    private String unPayOrReceiptMoney;//未付/未收金额
    
    private String billedOrReceiptedMoney;//>已开票/收票金额
    
    private String unBillOrReceiptMoney;//>	未开票/收票金额
    
    private String   companyName;//企业全称
    
    private String  account;//账号
    
    private String  tel;//联系电话
    
    private String  address;//地址
    
    private String    taxNum;//企业纳税号
    
    private String  bankName;//开户行名称

    private String invoiceVoucher;

    private String delFlg;

    private String creator;

    private Date createTime;

    private String updater;

    private Date updateTime;
    
    private  String relationBuyOrSaleNum;//关联销售/采购单号
    
    private  String relationReceiveOrPayNum;//关联收付款编号
    
    private String orderAmount;//订单金额/收票金额
    
    private  String billWay;//开票方式
    
    private  int invoiceCount;//发票数
    
    private  String comName;//收票方/开票方名称  
    
    private  String unBillAmount;//未开金额
    
    private  String approver;//收票人/开票人
    
    private  Date approvalDate;
    
    private String  billOrReceiptMoney;//当前收票金额/申请开票金额
    
    private String capitalMoney;//大写金额
    
    private String currentAmount;//当前发票金额
    
    private String  currency;//币种
    
    private ProcessBase processBase;//流程字段类
    

    public String getCapitalMoney() {
		return capitalMoney;
	}

	public String getCurrentAmount() {
		return currentAmount;
	}

	public void setCurrentAmount(String currentAmount) {
		this.currentAmount = currentAmount;
	}

	public void setCapitalMoney(String capitalMoney) {
		this.capitalMoney = capitalMoney;
	}

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

	public String getSubmitDepartment() {
		return submitDepartment;
	}

	public void setSubmitDepartment(String submitDepartment) {
		this.submitDepartment = submitDepartment;
	}

	public String getSecondRemark() {
		return secondRemark;
	}

	public void setSecondRemark(String secondRemark) {
		this.secondRemark = secondRemark;
	}

	public String getThirdRemark() {
		return thirdRemark;
	}

	public void setThirdRemark(String thirdRemark) {
		this.thirdRemark = thirdRemark;
	}

	public String getShouldPayOrReceiptMoney() {
		return shouldPayOrReceiptMoney;
	}

	public void setShouldPayOrReceiptMoney(String shouldPayOrReceiptMoney) {
		this.shouldPayOrReceiptMoney = shouldPayOrReceiptMoney;
	}

	public String getPrePayOrReceiptMoney() {
		return prePayOrReceiptMoney;
	}

	public void setPrePayOrReceiptMoney(String prePayOrReceiptMoney) {
		this.prePayOrReceiptMoney = prePayOrReceiptMoney;
	}

	public String getPayedOrReceiptedMoney() {
		return PayedOrReceiptedMoney;
	}

	public void setPayedOrReceiptedMoney(String payedOrReceiptedMoney) {
		PayedOrReceiptedMoney = payedOrReceiptedMoney;
	}

	public String getUnPayOrReceiptMoney() {
		return unPayOrReceiptMoney;
	}

	public void setUnPayOrReceiptMoney(String unPayOrReceiptMoney) {
		this.unPayOrReceiptMoney = unPayOrReceiptMoney;
	}

	public String getBilledOrReceiptedMoney() {
		return billedOrReceiptedMoney;
	}

	public void setBilledOrReceiptedMoney(String billedOrReceiptedMoney) {
		this.billedOrReceiptedMoney = billedOrReceiptedMoney;
	}

	public String getUnBillOrReceiptMoney() {
		return unBillOrReceiptMoney;
	}

	public void setUnBillOrReceiptMoney(String unBillOrReceiptMoney) {
		this.unBillOrReceiptMoney = unBillOrReceiptMoney;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTaxNum() {
		return taxNum;
	}

	public void setTaxNum(String taxNum) {
		this.taxNum = taxNum;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBillOrReceiptMoney() {
		return billOrReceiptMoney;
	}

	public void setBillOrReceiptMoney(String billOrReceiptMoney) {
		this.billOrReceiptMoney = billOrReceiptMoney;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public ProcessBase getProcessBase() {
		return processBase;
	}

	public void setProcessBase(ProcessBase processBase) {
		this.processBase = processBase;
	}

	public String getBuyComName() {
		return buyComName;
	}

	public String getSupplyComName() {
		return supplyComName;
	}

	public void setBuyComName(String buyComName) {
		this.buyComName = buyComName;
	}

	public void setSupplyComName(String supplyComName) {
		this.supplyComName = supplyComName;
	}
    
    
}