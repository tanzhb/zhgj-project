package com.congmai.zhgj.web.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * @ClassName StockSupplyRecord
 * @Description TODO(供应商库存)
 * @author zhaichao
 * @Date 2018年4月24日 下午4:49:57
 * @version 1.0.0
 */
public class StockSupplyRecord {
	 private String serialNum;

	    private String materielSerial;

	    private String relationNum;
	    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	    private Date stockDate;//  

	    private String operator;
	    
	    private String  inOutNum;
	    
	    private String warehouseSerial;//入库仓库

	    private String contactNum;
	    
	    private Materiel  materiel;//物料
	    
	    private Warehouse  warehouse;//物料

	    private String status;

	    private String remark;

	    private String delFlg;

	    private String creator;

	    private Date createTime;

	    private String updater;

	    private Date updateTime;

	    private String inOutType;
	    
	    private String manageType;//out出in入

	    private String warehouseName;//入库仓库名称
	    
	    private String materielCount;//入库数量
	    
		private String dateStock;//日期
		
		private String materielName;
		
		private String specifications;

		private String materielNum;
		
		private String belongWarehouseNameSupply;//所在仓库名称拼接
		
		private String currentAmount;//当前库存
		
		private String firstInDateSupply;//最早入库日期
		private String lastOutDateSupply;//最后出库日期
		private String preSaleAmount;//预售
		private String onRoadAmount;//在途
		private String canSaleAmount;//可售
		private String lastUpdateDate;//更新日期
		public String getSerialNum() {
			return serialNum;
		}

		public String getMaterielSerial() {
			return materielSerial;
		}
		@JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd HH:mm ")
		public Date getStockDate() {
			return stockDate;
		}

		public String getOperator() {
			return operator;
		}

		public String getContactNum() {
			return contactNum;
		}

		public String getStatus() {
			return status;
		}

		public String getRemark() {
			return remark;
		}

		public String getDelFlg() {
			return delFlg;
		}

		public String getCreator() {
			return creator;
		}

		public Date getCreateTime() {
			return createTime;
		}

		public String getUpdater() {
			return updater;
		}

		public Date getUpdateTime() {
			return updateTime;
		}

		public String getInOutType() {
			return inOutType;
		}

		public String getWarehouseName() {
			return warehouseName;
		}

		public String getMaterielCount() {
			return materielCount;
		}

		public String getDateStock() {
			return dateStock;
		}

		public void setSerialNum(String serialNum) {
			this.serialNum = serialNum;
		}

		public void setMaterielSerial(String materielSerial) {
			this.materielSerial = materielSerial;
		}

	
		public String getInOutNum() {
			return inOutNum;
		}

		public void setInOutNum(String inOutNum) {
			this.inOutNum = inOutNum;
		}

		public String getRelationNum() {
			return relationNum;
		}

		public void setRelationNum(String relationNum) {
			this.relationNum = relationNum;
		}
		@JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd HH:mm ")
		public void setStockDate(Date stockDate) {
			this.stockDate = stockDate;
		}

		public void setOperator(String operator) {
			this.operator = operator;
		}

		public void setContactNum(String contactNum) {
			this.contactNum = contactNum;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}

		public void setDelFlg(String delFlg) {
			this.delFlg = delFlg;
		}

		public void setCreator(String creator) {
			this.creator = creator;
		}

		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}

		public void setUpdater(String updater) {
			this.updater = updater;
		}

		public void setUpdateTime(Date updateTime) {
			this.updateTime = updateTime;
		}

		public void setInOutType(String inOutType) {
			this.inOutType = inOutType;
		}

		public void setWarehouseName(String warehouseName) {
			this.warehouseName = warehouseName;
		}

		public void setMaterielCount(String materielCount) {
			this.materielCount = materielCount;
		}

		public void setDateStock(String dateStock) {
			this.dateStock = dateStock;
		}

		public String getWarehouseSerial() {
			return warehouseSerial;
		}

		public void setWarehouseSerial(String warehouseSerial) {
			this.warehouseSerial = warehouseSerial;
		}

		public String getMaterielName() {
			return materielName;
		}

		public String getSpecifications() {
			return specifications;
		}

		public String getMaterielNum() {
			return materielNum;
		}

		public void setMaterielName(String materielName) {
			this.materielName = materielName;
		}

		public void setSpecifications(String specifications) {
			this.specifications = specifications;
		}

		public void setMaterielNum(String materielNum) {
			this.materielNum = materielNum;
		}

		public String getManageType() {
			return manageType;
		}

		public void setManageType(String manageType) {
			this.manageType = manageType;
		}

		public String getBelongWarehouseNameSupply() {
			return belongWarehouseNameSupply;
		}

		public String getCurrentAmount() {
			return currentAmount;
		}

		public String getFirstInDateSupply() {
			return firstInDateSupply;
		}

		public String getLastOutDateSupply() {
			return lastOutDateSupply;
		}

		public String getPreSaleAmount() {
			return preSaleAmount;
		}

		public String getOnRoadAmount() {
			return onRoadAmount;
		}

		public String getCanSaleAmount() {
			return canSaleAmount;
		}

		public String getLastUpdateDate() {
			return lastUpdateDate;
		}

		public void setBelongWarehouseNameSupply(String belongWarehouseNameSupply) {
			this.belongWarehouseNameSupply = belongWarehouseNameSupply;
		}

		public void setCurrentAmount(String currentAmount) {
			this.currentAmount = currentAmount;
		}

		public void setFirstInDateSupply(String firstInDateSupply) {
			this.firstInDateSupply = firstInDateSupply;
		}

		public void setLastOutDateSupply(String lastOutDateSupply) {
			this.lastOutDateSupply = lastOutDateSupply;
		}

		public void setPreSaleAmount(String preSaleAmount) {
			this.preSaleAmount = preSaleAmount;
		}

		public void setOnRoadAmount(String onRoadAmount) {
			this.onRoadAmount = onRoadAmount;
		}

		public void setCanSaleAmount(String canSaleAmount) {
			this.canSaleAmount = canSaleAmount;
		}

		public void setLastUpdateDate(String lastUpdateDate) {
			this.lastUpdateDate = lastUpdateDate;
		}

		public Materiel getMateriel() {
			return materiel;
		}

		public void setMateriel(Materiel materiel) {
			this.materiel = materiel;
		}

		public Warehouse getWarehouse() {
			return warehouse;
		}

		public void setWarehouse(Warehouse warehouse) {
			this.warehouse = warehouse;
		}
		
	
}