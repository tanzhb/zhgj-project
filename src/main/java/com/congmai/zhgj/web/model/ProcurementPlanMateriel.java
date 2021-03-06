package com.congmai.zhgj.web.model;

import java.util.Date;
import java.util.List;

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
    
    private String sets;//台套
    
    private String singleDose;//单套用量
    
    private String demandMaterielSerial;//需求物料流水
    
    private String supplyComId;//供应商id
    
    private String materielNum;

    private String materielName;

    private String specifications;

    private String unit;  
    
    private String status;  //(0 待采购 1已采购)
    
    private String stockCount;
    
    private String daysBeforeDelivery;//供应商/贸易商距交付
    
    private List<SupplyMateriel> supplyMateriels;
    

    public String getDaysBeforeDelivery() {
		return daysBeforeDelivery;
	}

	public void setDaysBeforeDelivery(String daysBeforeDelivery) {
		this.daysBeforeDelivery = daysBeforeDelivery;
	}

	public String getStockCount() {
		return stockCount;
	}

	public void setStockCount(String stockCount) {
		this.stockCount = stockCount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMaterielNum() {
		return materielNum;
	}

	public String getMaterielName() {
		return materielName;
	}

	public String getSpecifications() {
		return specifications;
	}

	public String getUnit() {
		return unit;
	}

	public void setMaterielNum(String materielNum) {
		this.materielNum = materielNum;
	}

	public void setMaterielName(String materielName) {
		this.materielName = materielName;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

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

	public String getSets() {
		return sets;
	}

	public String getSingleDose() {
		return singleDose;
	}

	public void setSets(String sets) {
		this.sets = sets;
	}

	public void setSingleDose(String singleDose) {
		this.singleDose = singleDose;
	}

	public String getDemandMaterielSerial() {
		return demandMaterielSerial;
	}

	public void setDemandMaterielSerial(String demandMaterielSerial) {
		this.demandMaterielSerial = demandMaterielSerial;
	}

	public String getSupplyComId() {
		return supplyComId;
	}

	public void setSupplyComId(String supplyComId) {
		this.supplyComId = supplyComId;
	}

	public List<SupplyMateriel> getSupplyMateriels() {
		return supplyMateriels;
	}

	public void setSupplyMateriels(List<SupplyMateriel> supplyMateriels) {
		this.supplyMateriels = supplyMateriels;
	}
    
}