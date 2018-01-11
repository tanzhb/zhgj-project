package com.congmai.zhgj.web.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class OrderMateriel {
    private String serialNum;

    private String orderSerial;

    private Materiel materiel;
    
    private String materielSerial;
    
    private SupplyMateriel supplyMateriel;

    private String supplyMaterielSerial;

    private String amount;

    private String orderUnitPrice;
    
    private String orderRateUnit;

    private String money;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date deliveryDate;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date lastDeliveryDate;

    private String deliveryAddress;

    private String customsRate;
    
    private String discountRate;

    private String serviceRate;
    
    private String redTicket;
    
    private Integer sort;
    
    private String delFlg;

    private String creator;

    private Date createTime;

    private String updater;

    private Date updateTime;
    
    private String demandPlanMaterielSerial;
    
    private String  deliveredCount;//已发数量

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum == null ? null : serialNum.trim();
    }

    public String getOrderSerial() {
        return orderSerial;
    }

    public void setOrderSerial(String orderSerial) {
        this.orderSerial = orderSerial == null ? null : orderSerial.trim();
    }

    public String getMaterielSerial() {
        return materielSerial;
    }

    public void setMaterielSerial(String materielSerial) {
        this.materielSerial = materielSerial == null ? null : materielSerial.trim();
    }

    public String getSupplyMaterielSerial() {
        return supplyMaterielSerial;
    }

    public void setSupplyMaterielSerial(String supplyMaterielSerial) {
        this.supplyMaterielSerial = supplyMaterielSerial == null ? null : supplyMaterielSerial.trim();
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount == null ? null : amount.trim();
    }

    public String getOrderUnitPrice() {
        return orderUnitPrice;
    }

    public void setOrderUnitPrice(String orderUnitPrice) {
        this.orderUnitPrice = orderUnitPrice == null ? null : orderUnitPrice.trim();
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money == null ? null : money.trim();
    }
    @JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd")
    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
    @JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd")
    public Date getLastDeliveryDate() {
        return lastDeliveryDate;
    }

    public void setLastDeliveryDate(Date lastDeliveryDate) {
        this.lastDeliveryDate = lastDeliveryDate;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress == null ? null : deliveryAddress.trim();
    }

    public String getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(String discountRate) {
        this.discountRate = discountRate == null ? null : discountRate.trim();
    }

    public String getServiceRate() {
        return serviceRate;
    }

    public void setServiceRate(String serviceRate) {
        this.serviceRate = serviceRate == null ? null : serviceRate.trim();
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

	public Materiel getMateriel() {
		return materiel;
	}

	public void setMateriel(Materiel materiel) {
		this.materiel = materiel;
	}

	public SupplyMateriel getSupplyMateriel() {
		return supplyMateriel;
	}

	public void setSupplyMateriel(SupplyMateriel supplyMateriel) {
		this.supplyMateriel = supplyMateriel;
	}

	public String getRedTicket() {
		return redTicket;
	}

	public void setRedTicket(String redTicket) {
		this.redTicket = redTicket;
	}

	public String getCustomsRate() {
		return customsRate;
	}

	public void setCustomsRate(String customsRate) {
		this.customsRate = customsRate;
	}

	public String getOrderRateUnit() {
		return orderRateUnit;
	}

	public void setOrderRateUnit(String orderRateUnit) {
		this.orderRateUnit = orderRateUnit;
	}

	public String getDemandPlanMaterielSerial() {
		return demandPlanMaterielSerial;
	}

	public void setDemandPlanMaterielSerial(String demandPlanMaterielSerial) {
		this.demandPlanMaterielSerial = demandPlanMaterielSerial;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getDeliveredCount() {
		return deliveredCount;
	}

	public void setDeliveredCount(String deliveredCount) {
		this.deliveredCount = deliveredCount;
	}

	

    
}