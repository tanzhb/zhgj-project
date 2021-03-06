package com.congmai.zhgj.web.model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DemandPlanMateriel {
    private String serialNum;

    private String demandPlanSerial;
    
    private String orderSerial;

    private String materielSerial;

    private String supplyMaterielSerial;

    private String amount;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date deliveryDate;

    private String deliveryAddress;

    private String delFlg;

    private String creator;

    private Date createTime;

    private String updater;

    private Date updateTime;

    private String remark;
    
    private Materiel materiel;
    
    private String supplyName;
    
    //冗余字段
    private String materielNum;

    private String materielName;

    private String specifications;

    private String unit;
    
    private String remainTime;//距离交付时间
    
    private Date startTime;
    
    private Date endTime;
    
    private DemandPlan demandPlan;

    private String comName;
    
    private String daysBeforeDelivery;
    
    private List<SupplyMateriel> supplyMateriels;
    
    private SupplyMateriel supplyMateriel;
    
    private String  status;
    
    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum == null ? null : serialNum.trim();
    }

    public String getDemandPlanSerial() {
        return demandPlanSerial;
    }

    public void setDemandPlanSerial(String demandPlanSerial) {
        this.demandPlanSerial = demandPlanSerial == null ? null : demandPlanSerial.trim();
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

    @JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd")
    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress == null ? null : deliveryAddress.trim();
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

	public String getSupplyName() {
		return supplyName;
	}

	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}

	public Materiel getMateriel() {
		return materiel;
	}

	public void setMateriel(Materiel materiel) {
		this.materiel = materiel;
	}

	public String getMaterielNum() {
		return materielNum;
	}

	public void setMaterielNum(String materielNum) {
		this.materielNum = materielNum;
	}

	public String getMaterielName() {
		return materielName;
	}

	public void setMaterielName(String materielName) {
		this.materielName = materielName;
	}

	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getRemainTime() {
		return remainTime;
	}

	public void setRemainTime(String remainTime) {
		this.remainTime = remainTime;
	}

	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	public String getDaysBeforeDelivery() {
		return daysBeforeDelivery;
	}

	public void setDaysBeforeDelivery(String daysBeforeDelivery) {
		this.daysBeforeDelivery = daysBeforeDelivery;
	}

	public DemandPlan getDemandPlan() {
		return demandPlan;
	}

	public void setDemandPlan(DemandPlan demandPlan) {
		this.demandPlan = demandPlan;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public List<SupplyMateriel> getSupplyMateriels() {
		return supplyMateriels;
	}

	public void setSupplyMateriels(List<SupplyMateriel> supplyMateriels) {
		this.supplyMateriels = supplyMateriels;
	}

	public SupplyMateriel getSupplyMateriel() {
		return supplyMateriel;
	}

	public void setSupplyMateriel(SupplyMateriel supplyMateriel) {
		this.supplyMateriel = supplyMateriel;
	}

	public String getOrderSerial() {
		return orderSerial;
	}

	public void setOrderSerial(String orderSerial) {
		this.orderSerial = orderSerial;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	}