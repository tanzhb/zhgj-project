package com.congmai.zhgj.web.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DeliveryMateriel {
    private String serialNum;

    private String deliverSerial;

    private String orderMaterielSerial;
    
    private String stockInOutRecordSerial;
    
    private String supplyMaterielSerial;

    private String batchNum;

    private Date manufactureDate;

    private String deliverCount;

    private String deliverRemark;

    private String acceptCount;

    private String refuseCount;

    private String takeRemark;

    private String qualifiedCount;

    private String unqualifiedCount;

    private String checkRemark;

    private String warehouseSerial;

    private String positionSerial;

    private String stockCount;

    private String unstockCount;

    private String stockRemark;

    private String remark;

    private String delFlg;

    private String creator;

    private Date createTime;

    private String updater;

    private Date updateTime;
    
    private OrderMateriel orderMateriel;
    
    private Materiel materiel;
    
    private SupplyMateriel supplyMateriel;
    
    private Warehouse warehouse;
    
    private Warehouseposition position;
 private String  warehouseName;
    
    private String positionNum;
    private StockInOutRecord stockInOutRecord;
    
    private String  deliveredCount;//已发数量
    private Delivery delivery;
    private String buyComId;//物权方comId
    private  String stockInSerialNum;
    private  String stockInQualifiedCount;
    private  String stockInUnqualifiedCount;
    private  String stockInCheckRemark;
    private  String stockInWarehouseSerial;
    private  String stockInPositionSerial;
    private  String stockInCount;
    private  String unstockInCount;
    private  String  stockInRemark;
    private Warehouse stockInWarehouse;
    private Warehouseposition stockInPosition;
    private List<StockInBatch> stockInBatchs;//关联出库批次信息
    private List<StockOutBatch> stockOutMateriels;//关联出库批次信息
    private String  inOutNums;//关联出库批次号
    private String sumStockOutCount;//出库数量
    private String attachFile;
    private String currentStockAmount;//当前库存总数(基本物料)
	private List<RelationFile> files;
	private String outCount="0";//入库批次出库数量(针对出库批次弹框)
	
	private String deliveryAttachFile;
	private List<RelationFile> deliveryFiles;
	
    public List<StockOutBatch> getStockOutMateriels() {
		return stockOutMateriels;
	}

	public void setStockOutMateriels(List<StockOutBatch> stockOutMateriels) {
		this.stockOutMateriels = stockOutMateriels;
	}

	private int pageIndex;
    
    private int pageSize;

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

    public String getOrderMaterielSerial() {
        return orderMaterielSerial;
    }

    public void setOrderMaterielSerial(String orderMaterielSerial) {
        this.orderMaterielSerial = orderMaterielSerial == null ? null : orderMaterielSerial.trim();
    }

    public String getBatchNum() {
        return batchNum;
    }

    public void setBatchNum(String batchNum) {
        this.batchNum = batchNum == null ? null : batchNum.trim();
    }

    @JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd")
    public Date getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(Date manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public String getDeliverCount() {
        return deliverCount;
    }

    public void setDeliverCount(String deliverCount) {
        this.deliverCount = deliverCount == null ? null : deliverCount.trim();
    }

    public String getDeliverRemark() {
        return deliverRemark;
    }

    public void setDeliverRemark(String deliverRemark) {
        this.deliverRemark = deliverRemark == null ? null : deliverRemark.trim();
    }

    public String getAcceptCount() {
        return acceptCount;
    }

    public void setAcceptCount(String acceptCount) {
        this.acceptCount = acceptCount == null ? null : acceptCount.trim();
    }

    public String getRefuseCount() {
        return refuseCount;
    }

    public void setRefuseCount(String refuseCount) {
        this.refuseCount = refuseCount == null ? null : refuseCount.trim();
    }

    public String getTakeRemark() {
        return takeRemark;
    }

    public void setTakeRemark(String takeRemark) {
        this.takeRemark = takeRemark == null ? null : takeRemark.trim();
    }

    public String getQualifiedCount() {
        return qualifiedCount;
    }

    public void setQualifiedCount(String qualifiedCount) {
        this.qualifiedCount = qualifiedCount == null ? null : qualifiedCount.trim();
    }

    public String getUnqualifiedCount() {
        return unqualifiedCount;
    }

    public void setUnqualifiedCount(String unqualifiedCount) {
        this.unqualifiedCount = unqualifiedCount == null ? null : unqualifiedCount.trim();
    }

    public String getCheckRemark() {
        return checkRemark;
    }

    public void setCheckRemark(String checkRemark) {
        this.checkRemark = checkRemark == null ? null : checkRemark.trim();
    }

    public String getWarehouseSerial() {
        return warehouseSerial;
    }

    public void setWarehouseSerial(String warehouseSerial) {
        this.warehouseSerial = warehouseSerial == null ? null : warehouseSerial.trim();
    }

    public String getPositionSerial() {
        return positionSerial;
    }

    public void setPositionSerial(String positionSerial) {
        this.positionSerial = positionSerial == null ? null : positionSerial.trim();
    }

    public String getStockCount() {
        return stockCount;
    }

    public void setStockCount(String stockCount) {
        this.stockCount = stockCount == null ? null : stockCount.trim();
    }

    public String getUnstockCount() {
        return unstockCount;
    }

    public void setUnstockCount(String unstockCount) {
        this.unstockCount = unstockCount == null ? null : unstockCount.trim();
    }

    public String getStockRemark() {
        return stockRemark;
    }

    public void setStockRemark(String stockRemark) {
        this.stockRemark = stockRemark == null ? null : stockRemark.trim();
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

	public OrderMateriel getOrderMateriel() {
		return orderMateriel;
	}

	public void setOrderMateriel(OrderMateriel orderMateriel) {
		this.orderMateriel = orderMateriel;
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	public StockInOutRecord getStockInOutRecord() {
		return stockInOutRecord;
	}

	public void setStockInOutRecord(StockInOutRecord stockInOutRecord) {
		this.stockInOutRecord = stockInOutRecord;
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

	public Delivery getDelivery() {
		return delivery;
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}

	public Warehouseposition getPosition() {
		return position;
	}

	public void setPosition(Warehouseposition position) {
		this.position = position;
	}

	public String getStockInQualifiedCount() {
		return stockInQualifiedCount;
	}

	public void setStockInQualifiedCount(String stockInQualifiedCount) {
		this.stockInQualifiedCount = stockInQualifiedCount;
	}

	public String getStockInUnqualifiedCount() {
		return stockInUnqualifiedCount;
	}

	public void setStockInUnqualifiedCount(String stockInUnqualifiedCount) {
		this.stockInUnqualifiedCount = stockInUnqualifiedCount;
	}

	public String getStockInCheckRemark() {
		return stockInCheckRemark;
	}

	public void setStockInCheckRemark(String stockInCheckRemark) {
		this.stockInCheckRemark = stockInCheckRemark;
	}

	public String getStockInWarehouseSerial() {
		return stockInWarehouseSerial;
	}

	public void setStockInWarehouseSerial(String stockInWarehouseSerial) {
		this.stockInWarehouseSerial = stockInWarehouseSerial;
	}

	public String getStockInPositionSerial() {
		return stockInPositionSerial;
	}

	public void setStockInPositionSerial(String stockInPositionSerial) {
		this.stockInPositionSerial = stockInPositionSerial;
	}

	public String getStockInCount() {
		return stockInCount;
	}

	public void setStockInCount(String stockInCount) {
		this.stockInCount = stockInCount;
	}

	public String getUnstockInCount() {
		return unstockInCount;
	}

	public void setUnstockInCount(String unstockInCount) {
		this.unstockInCount = unstockInCount;
	}

	public String getStockInRemark() {
		return stockInRemark;
	}

	public void setStockInRemark(String stockInRemark) {
		this.stockInRemark = stockInRemark;
	}

	public String getStockInSerialNum() {
		return stockInSerialNum;
	}

	public void setStockInSerialNum(String stockInSerialNum) {
		this.stockInSerialNum = stockInSerialNum;
	}

	public Warehouse getStockInWarehouse() {
		return stockInWarehouse;
	}

	public void setStockInWarehouse(Warehouse stockInWarehouse) {
		this.stockInWarehouse = stockInWarehouse;
	}

	public Warehouseposition getStockInPosition() {
		return stockInPosition;
	}

	public void setStockInPosition(Warehouseposition stockInPosition) {
		this.stockInPosition = stockInPosition;
	}

	public String getStockInOutRecordSerial() {
		return stockInOutRecordSerial;
	}

	public void setStockInOutRecordSerial(String stockInOutRecordSerial) {
		this.stockInOutRecordSerial = stockInOutRecordSerial;
	}

	public String getSupplyMaterielSerial() {
		return supplyMaterielSerial;
	}

	public void setSupplyMaterielSerial(String supplyMaterielSerial) {
		this.supplyMaterielSerial = supplyMaterielSerial;
	}

	public SupplyMateriel getSupplyMateriel() {
		return supplyMateriel;
	}

	public void setSupplyMateriel(SupplyMateriel supplyMateriel) {
		this.supplyMateriel = supplyMateriel;
	}

	public String getBuyComId() {
		return buyComId;
	}

	public void setBuyComId(String buyComId) {
		this.buyComId = buyComId;
	}

	public String getInOutNums() {
		return inOutNums;
	}

	public void setInOutNums(String inOutNums) {
		this.inOutNums = inOutNums;
	}

	public String getSumStockOutCount() {
		return sumStockOutCount;
	}

	public void setSumStockOutCount(String sumStockOutCount) {
		this.sumStockOutCount = sumStockOutCount;
	}

	public String getAttachFile() {
		return attachFile;
	}

	public void setAttachFile(String attachFile) {
		this.attachFile = attachFile;
	}

	public List<RelationFile> getFiles() {
		return files;
	}

	public void setFiles(List<RelationFile> files) {
		this.files = files;
	}

	public String getCurrentStockAmount() {
		return currentStockAmount;
	}

	public void setCurrentStockAmount(String currentStockAmount) {
		this.currentStockAmount = currentStockAmount;
	}

	public List<RelationFile> getDeliveryFiles() {
		return deliveryFiles;
	}

	public void setDeliveryFiles(List<RelationFile> deliveryFiles) {
		this.deliveryFiles = deliveryFiles;
	}

	public String getDeliveryAttachFile() {
		return deliveryAttachFile;
	}

	public void setDeliveryAttachFile(String deliveryAttachFile) {
		this.deliveryAttachFile = deliveryAttachFile;
	}

	public List<StockInBatch> getStockInBatchs() {
		return stockInBatchs;
	}

	public void setStockInBatchs(List<StockInBatch> stockInBatchs) {
		this.stockInBatchs = stockInBatchs;
	}

	public Materiel getMateriel() {
		return materiel;
	}

	public void setMateriel(Materiel materiel) {
		this.materiel = materiel;
	}

	public String getOutCount() {
		return outCount;
	}

	public void setOutCount(String outCount) {
		this.outCount = outCount;
	}

	public String getDeliveredCount() {
		return deliveredCount;
	}

	public void setDeliveredCount(String deliveredCount) {
		this.deliveredCount = deliveredCount;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public String getPositionNum() {
		return positionNum;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public void setPositionNum(String positionNum) {
		this.positionNum = positionNum;
	}

	
	
    
    
}