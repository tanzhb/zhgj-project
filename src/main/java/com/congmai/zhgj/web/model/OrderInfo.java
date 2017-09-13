package com.congmai.zhgj.web.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class OrderInfo extends BaseVO {
    /**
	 * @Field @serialVersionUID : 订单
	 */
	private static final long serialVersionUID = 4164102013915166314L;

	private String serialNum;

    private String contractSerial;
    
    private ContractVO contract;

    private String supplyComId;

    private String buyComId;

    private String orderNum;

    private String orderType;

    private String seller;

    private String entrustParty;

    private String serviceModel;

    private String demandPlanSerial;

    private String saleApplySerial;

    private String settlementClause;

    private String orderSerial;

    private String deliveryMode;
      
    
    private String paiedMoney;
    
    
    private String billedMoney;

    private String transportMode;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date orderDate;

    private String maker;

    private String rate;

    private String currency;

    private String exchangeRate;
    
    private String materielCount;
    private String materielAmount;
    private String rateAmount;
    private String rateAndAmount;
    private String otherAmount;
    private String orderAmount;

    private String remark;
    
    private String orderRemark;

    private String status;

    private String delFlg;

    private String creator;

    private Date createTime;

    private String updater;

    private Date updateTime;
    
    private String unBillAmount;//未开金额合计

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

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum == null ? null : orderNum.trim();
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType == null ? null : orderType.trim();
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller == null ? null : seller.trim();
    }

    public String getEntrustParty() {
        return entrustParty;
    }

    public void setEntrustParty(String entrustParty) {
        this.entrustParty = entrustParty == null ? null : entrustParty.trim();
    }

    public String getServiceModel() {
        return serviceModel;
    }

    public void setServiceModel(String serviceModel) {
        this.serviceModel = serviceModel == null ? null : serviceModel.trim();
    }

    public String getDemandPlanSerial() {
        return demandPlanSerial;
    }

    public void setDemandPlanSerial(String demandPlanSerial) {
        this.demandPlanSerial = demandPlanSerial == null ? null : demandPlanSerial.trim();
    }

    public String getSaleApplySerial() {
        return saleApplySerial;
    }

    public void setSaleApplySerial(String saleApplySerial) {
        this.saleApplySerial = saleApplySerial == null ? null : saleApplySerial.trim();
    }

    public String getSettlementClause() {
        return settlementClause;
    }

    public void setSettlementClause(String settlementClause) {
        this.settlementClause = settlementClause == null ? null : settlementClause.trim();
    }

    public String getOrderSerial() {
        return orderSerial;
    }

    public void setOrderSerial(String orderSerial) {
        this.orderSerial = orderSerial == null ? null : orderSerial.trim();
    }

    public String getDeliveryMode() {
        return deliveryMode;
    }

    public void setDeliveryMode(String deliveryMode) {
        this.deliveryMode = deliveryMode == null ? null : deliveryMode.trim();
    }

    public String getTransportMode() {
        return transportMode;
    }

    public void setTransportMode(String transportMode) {
        this.transportMode = transportMode == null ? null : transportMode.trim();
    }
    @JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd")
    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker == null ? null : maker.trim();
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate == null ? null : rate.trim();
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public String getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(String exchangeRate) {
        this.exchangeRate = exchangeRate == null ? null : exchangeRate.trim();
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount == null ? null : orderAmount.trim();
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

	public String getUnBillAmount() {
		return unBillAmount;
	}

	public void setUnBillAmount(String unBillAmount) {
		this.unBillAmount = unBillAmount;
	}

	public String getMaterielAmount() {
		return materielAmount;
	}

	public void setMaterielAmount(String materielAmount) {
		this.materielAmount = materielAmount;
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
	}	public String getOrderRemark() {
		return orderRemark;
	}

	public void setOrderRemark(String orderRemark) {
		this.orderRemark = orderRemark;
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

	public String getOtherAmount() {
		return otherAmount;
	}

	public void setOtherAmount(String otherAmount) {
		this.otherAmount = otherAmount;
	}

	public String getMaterielCount() {
		return materielCount;
	}

	public void setMaterielCount(String materielCount) {
		this.materielCount = materielCount;
	}

	public ContractVO getContract() {
		return contract;
	}

	public void setContract(ContractVO contract) {
		this.contract = contract;
	}
	
}