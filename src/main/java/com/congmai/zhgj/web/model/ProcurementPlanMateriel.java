package com.congmai.zhgj.web.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ProcurementPlanMateriel {
    private String serialNum;

    private String procurementPlanSerial;

    private String materielSerial;

    private String supplyMaterielSerial;
    
    private Materiel materiel;
    
    private SupplyMateriel supplyMateriel;

    private String planCount;

    private String buyCount;

    private String orderUnitPrice;

    private String orderRateUnit;

    private String money;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date deliveryDate;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date lastDeliveryDate;

    private String deliveryAddress;

    private Integer sort;

    private String delFlg;

    private String creator;

    private Date createTime;

    private String updater;

    private Date updateTime;

    private String remark;
    
    private String supplyName;

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum == null ? null : serialNum.trim();
    }

    public String getProcurementPlanSerial() {
        return procurementPlanSerial;
    }

    public void setProcurementPlanSerial(String procurementPlanSerial) {
        this.procurementPlanSerial = procurementPlanSerial == null ? null : procurementPlanSerial.trim();
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

    public String getPlanCount() {
        return planCount;
    }

    public void setPlanCount(String planCount) {
        this.planCount = planCount == null ? null : planCount.trim();
    }

    public String getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(String buyCount) {
        this.buyCount = buyCount == null ? null : buyCount.trim();
    }

    public String getOrderUnitPrice() {
        return orderUnitPrice;
    }

    public void setOrderUnitPrice(String orderUnitPrice) {
        this.orderUnitPrice = orderUnitPrice == null ? null : orderUnitPrice.trim();
    }

    public String getOrderRateUnit() {
        return orderRateUnit;
    }

    public void setOrderRateUnit(String orderRateUnit) {
        this.orderRateUnit = orderRateUnit == null ? null : orderRateUnit.trim();
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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

	public String getSupplyName() {
		return supplyName;
	}

	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}
    
}