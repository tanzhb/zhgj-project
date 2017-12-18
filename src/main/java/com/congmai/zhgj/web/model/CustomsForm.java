package com.congmai.zhgj.web.model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CustomsForm {
    private String serialNum;

    private String customsFormNum;

    private String customsFormType;

    private String deliverSerial;

    private String orderSerial;

    private String deliverAmount;

    private String addedTax;

    private String customsAmount;

    private String shipNumber;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date playArrivalDate;

    private String port;

    private String remark;

    private String status;//当报关单状态为2时显示已付款状态 ;当报关单状态为1为已确认未付款

    private String delFlg;

    private String creator;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createTime;

    private String updater;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updateTime;
    
    private  String deliverNum;//发货单号
    
    private  String buyOrderNum;//采购单号
    
    private  String agentUnit;//代理报关/清关单位
    
    private  String fileCount;//附件数
    
    private List<RelationFile> files;

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum == null ? null : serialNum.trim();
    }

    public String getCustomsFormNum() {
        return customsFormNum;
    }

    public void setCustomsFormNum(String customsFormNum) {
        this.customsFormNum = customsFormNum == null ? null : customsFormNum.trim();
    }

    public String getCustomsFormType() {
        return customsFormType;
    }

    public void setCustomsFormType(String customsFormType) {
        this.customsFormType = customsFormType == null ? null : customsFormType.trim();
    }

    public String getDeliverSerial() {
        return deliverSerial;
    }

    public void setDeliverSerial(String deliverSerial) {
        this.deliverSerial = deliverSerial == null ? null : deliverSerial.trim();
    }

    public String getOrderSerial() {
        return orderSerial;
    }

    public void setOrderSerial(String orderSerial) {
        this.orderSerial = orderSerial == null ? null : orderSerial.trim();
    }

    public String getDeliverAmount() {
        return deliverAmount;
    }

    public void setDeliverAmount(String deliverAmount) {
        this.deliverAmount = deliverAmount == null ? null : deliverAmount.trim();
    }

    public String getAddedTax() {
        return addedTax;
    }

    public void setAddedTax(String addedTax) {
        this.addedTax = addedTax == null ? null : addedTax.trim();
    }

    public String getCustomsAmount() {
        return customsAmount;
    }

    public void setCustomsAmount(String customsAmount) {
        this.customsAmount = customsAmount == null ? null : customsAmount.trim();
    }

    public String getShipNumber() {
        return shipNumber;
    }

    public void setShipNumber(String shipNumber) {
        this.shipNumber = shipNumber == null ? null : shipNumber.trim();
    }
    @JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd")
    public Date getPlayArrivalDate() {
        return playArrivalDate;
    }

    public void setPlayArrivalDate(Date playArrivalDate) {
        this.playArrivalDate = playArrivalDate;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port == null ? null : port.trim();
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
    @JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	public String getDeliverNum() {
		return deliverNum;
	}

	public void setDeliverNum(String deliverNum) {
		this.deliverNum = deliverNum;
	}

	public String getBuyOrderNum() {
		return buyOrderNum;
	}

	public void setBuyOrderNum(String buyOrderNum) {
		this.buyOrderNum = buyOrderNum;
	}

	public String getAgentUnit() {
		return agentUnit;
	}

	public void setAgentUnit(String agentUnit) {
		this.agentUnit = agentUnit;
	}

	public String getFileCount() {
		return fileCount;
	}

	public void setFileCount(String fileCount) {
		this.fileCount = fileCount;
	}

	public List<RelationFile> getFiles() {
		return files;
	}

	public void setFiles(List<RelationFile> files) {
		this.files = files;
	}
    
}