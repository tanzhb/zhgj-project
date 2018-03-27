package com.congmai.zhgj.web.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DemandMateriel {
    private String serialNum;

    private String procurementPlanSerial;

    private String materielSerial;

    private String supplyMaterielSerial;

    private String planCount;

    private String completeCount;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date deliveryDate;

    private String deliveryAddress;

    private String status;//(0待采购 1 已分解  2 部分入库  3 已入库)

    private Integer sort;

    private String delFlg;

    private String creator;

    private Date createTime;

    private String updater;

    private Date updateTime;

    private String remark;
    
    private Materiel materiel;
    
    private String materielNum;

    private String materielName;

    private String specifications;

    private String unit;  

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

    public String getCompleteCount() {
        return completeCount;
    }

    public void setCompleteCount(String completeCount) {
        this.completeCount = completeCount == null ? null : completeCount.trim();
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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
    
}