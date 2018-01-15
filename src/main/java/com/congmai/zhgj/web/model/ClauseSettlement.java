package com.congmai.zhgj.web.model;

import java.util.Date;
import java.util.List;

public class ClauseSettlement {
    private String serialNum;

    private String contractSerial;
    
    private String orderSerial;
    
    private List<ClauseSettlementDetail> clauseSettlementDetails;

    private String payee;

    private String payer;

    private String materielAmount;
    private String rateAmount;
    private String rateAndAmount;
    private String otherAmount;
    private String orderAmount;

    private String remark;

    private String status;

    private String delFlg;

    private String creator;

    private Date createTime;

    private String updater;

    private Date updateTime;
    
    private String tradeWay;//贸易方式

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum == null ? null : serialNum.trim();
    }

    public String getContractSerial() {
        return contractSerial;
    }

    public void setContractSerial(String contractSerial) {
        this.contractSerial = contractSerial == null ? null : contractSerial.trim();
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee == null ? null : payee.trim();
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer == null ? null : payer.trim();
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount == null ? null : orderAmount.trim();
    }

    public String getOtherAmount() {
        return otherAmount;
    }

    public void setOtherAmount(String otherAmount) {
        this.otherAmount = otherAmount == null ? null : otherAmount.trim();
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

	public List<ClauseSettlementDetail> getClauseSettlementDetails() {
		return clauseSettlementDetails;
	}

	public void setClauseSettlementDetails(
			List<ClauseSettlementDetail> clauseSettlementDetails) {
		this.clauseSettlementDetails = clauseSettlementDetails;
	}

	public String getMaterielAmount() {
		return materielAmount;
	}

	public void setMaterielAmount(String materielAmount) {
		this.materielAmount = materielAmount;
	}

	public String getRateAmount() {
		return rateAmount;
	}

	public void setRateAmount(String rateAmount) {
		this.rateAmount = rateAmount;
	}

	public String getRateAndAmount() {
		return rateAndAmount;
	}

	public void setRateAndAmount(String rateAndAmount) {
		this.rateAndAmount = rateAndAmount;
	}

	public String getOrderSerial() {
		return orderSerial;
	}

	public void setOrderSerial(String orderSerial) {
		this.orderSerial = orderSerial;
	}

	public String getTradeWay() {
		return tradeWay;
	}

	public void setTradeWay(String tradeWay) {
		this.tradeWay = tradeWay;
	}
    
}