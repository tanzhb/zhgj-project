package com.congmai.zhgj.web.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @ClassName VerificationRecord
 * @Description TODO(核销记录)
 * @author zhaichao
 * @Date 2017年12月21日 下午3:59:36
 * @version 1.0.0
 */
public class VerificationRecord {
    private String serialNum;

    private String paymentRecordSerial;

    private String receiveMemoSerial;

    private String moneyAmount;

    private String delFlg;

    private String creator;

    private Date createTime;

    private String updater;

    private Date updateTime;
    
    private PaymentRecord  paymentRecord;
    
    private MemoRecord   memoRecord;

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum == null ? null : serialNum.trim();
    }

    public String getPaymentRecordSerial() {
        return paymentRecordSerial;
    }

    public void setPaymentRecordSerial(String paymentRecordSerial) {
        this.paymentRecordSerial = paymentRecordSerial == null ? null : paymentRecordSerial.trim();
    }

    public String getReceiveMemoSerial() {
        return receiveMemoSerial;
    }

    public void setReceiveMemoSerial(String receiveMemoSerial) {
        this.receiveMemoSerial = receiveMemoSerial == null ? null : receiveMemoSerial.trim();
    }

    public String getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(String moneyAmount) {
        this.moneyAmount = moneyAmount == null ? null : moneyAmount.trim();
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
    @JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd")
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

	public PaymentRecord getPaymentRecord() {
		return paymentRecord;
	}

	public void setPaymentRecord(PaymentRecord paymentRecord) {
		this.paymentRecord = paymentRecord;
	}

	public MemoRecord getMemoRecord() {
		return memoRecord;
	}

	public void setMemoRecord(MemoRecord memoRecord) {
		this.memoRecord = memoRecord;
	}
    
}