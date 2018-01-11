package com.congmai.zhgj.web.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @ClassName MemoRecord
 * @Description TODO(收付款水单)
 * @author zhaichao
 * @Date 2017年12月21日 下午3:58:55
 * @version 1.0.0
 */
public class MemoRecord {

	private String serialNum;

    private String memoNum;

    private String supplyComId;

    private String buyComId;

    private String verificationMoneyAmount;
    
    private String remainMoneyAmount;//水单余额

    private String moneyAmount;

    private String currency;

    private String payee;

    private String bank;

    private String accountName;

    private String accountNumber;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date paymentDate;

    private String paymentStyle;

    private String paymentVoucher;

    private String remark;

    private String status;

    private String delFlg;

    private String creator;

    private Date createTime;

    private String updater;

    private Date updateTime;
    
    private String comName;

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum == null ? null : serialNum.trim();
    }

    public String getMemoNum() {
        return memoNum;
    }

    public void setMemoNum(String memoNum) {
        this.memoNum = memoNum == null ? null : memoNum.trim();
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

    public String getVerificationMoneyAmount() {
        return verificationMoneyAmount;
    }

    public void setVerificationMoneyAmount(String verificationMoneyAmount) {
        this.verificationMoneyAmount = verificationMoneyAmount == null ? null : verificationMoneyAmount.trim();
    }

    public String getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(String moneyAmount) {
        this.moneyAmount = moneyAmount == null ? null : moneyAmount.trim();
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee == null ? null : payee.trim();
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank == null ? null : bank.trim();
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber == null ? null : accountNumber.trim();
    }
    @JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd")
    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentStyle() {
        return paymentStyle;
    }

    public void setPaymentStyle(String paymentStyle) {
        this.paymentStyle = paymentStyle == null ? null : paymentStyle.trim();
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

	public String getRemainMoneyAmount() {
		return remainMoneyAmount;
	}

	public void setRemainMoneyAmount(String remainMoneyAmount) {
		this.remainMoneyAmount = remainMoneyAmount;
	}

	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}
    
}