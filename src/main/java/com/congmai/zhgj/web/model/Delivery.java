package com.congmai.zhgj.web.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Delivery {
    private String serialNum;

    private String deliverNum;

    private String orderSerial;

    private String supplyComId;

    private String shipper;

    private String receiver;

    private Date makeDate;

    private String maker;

    private String remark;

    private String status;

    private String approval;

    private Date approvalDate;

    private String warehouseSerial;

    private String materielCount;

    private Date deliverDate;

    private String packageCount;

    private String materielWeight;

    private String packageType;

    private String packageSpecifications;

    private String serviceMoney;

    private String deliverer;

    private String contactNum;

    private String deliverRemark;

    private String delFlg;

    private String creator;

    private Date createTime;

    private String updater;

    private Date updateTime;
    
    private String takeDeliverSerial;
    
    
    //冗余字段
    
    private List<DeliveryMateriel> deliveryMateriels;
    
    private DeliveryTransport deliveryTransport;
    
    private TakeDelivery takeDelivery;
    
    private Warehouse warehouse;
    
    private int pageIndex;
    
    private int pageSize;
    
    private String supplyName;
    
    private String orderNum;
    
    private String orderAmount;//订单金额

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum == null ? null : serialNum.trim();
    }

    public String getDeliverNum() {
        return deliverNum;
    }

    public void setDeliverNum(String deliverNum) {
        this.deliverNum = deliverNum == null ? null : deliverNum.trim();
    }

    public String getOrderSerial() {
        return orderSerial;
    }

    public void setOrderSerial(String orderSerial) {
        this.orderSerial = orderSerial == null ? null : orderSerial.trim();
    }

    public String getSupplyComId() {
        return supplyComId;
    }

    public void setSupplyComId(String supplyComId) {
        this.supplyComId = supplyComId == null ? null : supplyComId.trim();
    }

    public String getShipper() {
        return shipper;
    }

    public void setShipper(String shipper) {
        this.shipper = shipper == null ? null : shipper.trim();
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver == null ? null : receiver.trim();
    }

    @JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd")
    public Date getMakeDate() {
        return makeDate;
    }

    public void setMakeDate(Date makeDate) {
        this.makeDate = makeDate;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker == null ? null : maker.trim();
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

    public String getApproval() {
        return approval;
    }

    public void setApproval(String approval) {
        this.approval = approval == null ? null : approval.trim();
    }

    @JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd")
    public Date getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getWarehouseSerial() {
        return warehouseSerial;
    }

    public void setWarehouseSerial(String warehouseSerial) {
        this.warehouseSerial = warehouseSerial == null ? null : warehouseSerial.trim();
    }

    public String getMaterielCount() {
        return materielCount;
    }

    public void setMaterielCount(String materielCount) {
        this.materielCount = materielCount == null ? null : materielCount.trim();
    }

    @JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd")
    public Date getDeliverDate() {
        return deliverDate;
    }

    public void setDeliverDate(Date deliverDate) {
        this.deliverDate = deliverDate;
    }

    public String getPackageCount() {
        return packageCount;
    }

    public void setPackageCount(String packageCount) {
        this.packageCount = packageCount == null ? null : packageCount.trim();
    }

    public String getMaterielWeight() {
        return materielWeight;
    }

    public void setMaterielWeight(String materielWeight) {
        this.materielWeight = materielWeight == null ? null : materielWeight.trim();
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType == null ? null : packageType.trim();
    }

    public String getPackageSpecifications() {
        return packageSpecifications;
    }

    public void setPackageSpecifications(String packageSpecifications) {
        this.packageSpecifications = packageSpecifications == null ? null : packageSpecifications.trim();
    }

    public String getServiceMoney() {
        return serviceMoney;
    }

    public void setServiceMoney(String serviceMoney) {
        this.serviceMoney = serviceMoney == null ? null : serviceMoney.trim();
    }

    public String getDeliverer() {
        return deliverer;
    }

    public void setDeliverer(String deliverer) {
        this.deliverer = deliverer == null ? null : deliverer.trim();
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum == null ? null : contactNum.trim();
    }

    public String getDeliverRemark() {
        return deliverRemark;
    }

    public void setDeliverRemark(String deliverRemark) {
        this.deliverRemark = deliverRemark == null ? null : deliverRemark.trim();
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

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<DeliveryMateriel> getDeliveryMateriels() {
		return deliveryMateriels;
	}

	public void setDeliveryMateriels(List<DeliveryMateriel> deliveryMateriels) {
		this.deliveryMateriels = deliveryMateriels;
	}

	public DeliveryTransport getDeliveryTransport() {
		return deliveryTransport;
	}

	public void setDeliveryTransport(DeliveryTransport deliveryTransport) {
		this.deliveryTransport = deliveryTransport;
	}

	public TakeDelivery getTakeDelivery() {
		return takeDelivery;
	}

	public void setTakeDelivery(TakeDelivery takeDelivery) {
		this.takeDelivery = takeDelivery;
	}


	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getSupplyName() {
		return supplyName;
	}

	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	public String getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getTakeDeliverSerial() {
		return takeDeliverSerial;
	}

	public void setTakeDeliverSerial(String takeDeliverSerial) {
		this.takeDeliverSerial = takeDeliverSerial;
	}
    
    
}