package com.congmai.zhgj.web.model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ProcurementPlan {
    private String serialNum;

    private String saleOrderSerial;
    
    private String procurementPlanNum;

    private String buyCount;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date buyDate;

    private String status;

    private String remark;

    private String delFlg;

    private String creator;

    private Date createTime;

    private String updater;

    private Date updateTime;
    
    private OrderInfo saleOrder;

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum == null ? null : serialNum.trim();
    }

    public String getSaleOrderSerial() {
        return saleOrderSerial;
    }

    public void setSaleOrderSerial(String saleOrderSerial) {
        this.saleOrderSerial = saleOrderSerial == null ? null : saleOrderSerial.trim();
    }

    public String getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(String buyCount) {
        this.buyCount = buyCount == null ? null : buyCount.trim();
    }
    @JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd")
    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

	public OrderInfo getSaleOrder() {
		return saleOrder;
	}

	public void setSaleOrder(OrderInfo saleOrder) {
		this.saleOrder = saleOrder;
	}

	public String getProcurementPlanNum() {
		return procurementPlanNum;
	}

	public void setProcurementPlanNum(String procurementPlanNum) {
		this.procurementPlanNum = procurementPlanNum;
	}
    
}