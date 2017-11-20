package com.congmai.zhgj.web.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TakeDelivery extends BaseVO implements Serializable{
    /**
	 * @Field @serialVersionUID : TODO(这里用一句话描述这个类的作用)
	 */
	private static final long serialVersionUID = 1L;

	private String serialNum;

    private String deliverSerial;

    private String warehouseSerial;

    private Date takeDeliverDate;

    private String receiver;

    private String contactNum;

    private String takeDeliverNum;

    private String remark;

    private Date actualDate;

    private String taker;

    private String takeRemark;
    
    private String takeDeliverAddress;

    private String delFlg;

    private String creator;

    private Date createTime;

    private String updater;

    private Date updateTime;
    
    private Warehouse warehouse;
    
    private String supplyName;//供应商名称
    
    
    public static final String WAITING = "0"; //待收货
    public static final String APPLY_COMPLETE = "1"; //待检验
    public static final String CANCEL = "2"; //已取消
    public static final String CHECK_COMPLETE = "3"; //待入库/待出库
    public static final String COMPLETE = "4"; //已完成
    public static final String COMPLETE_Declaration = "5"; //已报关
    
/*    private String processInstanceId;
    
    private String status;
    
    private String reason;
    
    private int userId;
    
    private Date applyDate;*/

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum == null ? null : serialNum.trim();
    }

    public String getDeliverSerial() {
        return deliverSerial;
    }

    public void setDeliverSerial(String deliverSerial) {
        this.deliverSerial = deliverSerial == null ? null : deliverSerial.trim();
    }

    public String getWarehouseSerial() {
        return warehouseSerial;
    }

    public void setWarehouseSerial(String warehouseSerial) {
        this.warehouseSerial = warehouseSerial == null ? null : warehouseSerial.trim();
    }

    @JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd")
    public Date getTakeDeliverDate() {
        return takeDeliverDate;
    }

    public void setTakeDeliverDate(Date takeDeliverDate) {
        this.takeDeliverDate = takeDeliverDate;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver == null ? null : receiver.trim();
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum == null ? null : contactNum.trim();
    }

    public String getTakeDeliverNum() {
        return takeDeliverNum;
    }

    public void setTakeDeliverNum(String takeDeliverNum) {
        this.takeDeliverNum = takeDeliverNum == null ? null : takeDeliverNum.trim();
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

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	public String getSupplyName() {
		return supplyName;
	}

	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}

	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	public Date getActualDate() {
		return actualDate;
	}

	public void setActualDate(Date actualDate) {
		this.actualDate = actualDate;
	}

	public String getTaker() {
		return taker;
	}

	public void setTaker(String taker) {
		this.taker = taker;
	}

	public String getTakeRemark() {
		return takeRemark;
	}

	public void setTakeRemark(String takeRemark) {
		this.takeRemark = takeRemark;
	}

	public String getTakeDeliverAddress() {
		return takeDeliverAddress;
	}

	public void setTakeDeliverAddress(String takeDeliverAddress) {
		this.takeDeliverAddress = takeDeliverAddress;
	}


    
    
}