package com.congmai.zhgj.web.model;

import java.util.Date;
import java.util.List;

/**
 * @ClassName Stock
 * @Description TODO(库存)
 * @author zhaichao
 * @Date 2017年8月21日 下午5:30:06
 * @version 1.0.0
 */
public class Stock {
	private String serialNum;

	private String materielSerial;// 物料流水号

	private String stockNum;// 库存编号

	private String maxStock;// 最高库存

	private String minStock;// 最低库存

	private String manageType;// 管理类型

	private String materielOwner;// 物权方
	
	private String comId;// 用于查询该公司的物料库存

	private String serviceParty;// 服务方

	private String remark;

	private String delFlg;

	private String creator;

	private Date createTime;

	private String updater;

	private Date updateTime;

	private String currentAmount;// 当前库存数量

	private String belongWarehouseNumZijian;// 所在仓库数量自建库存

	private String belongWarehouseNumDaiguan;// 所在仓库数量自建库存

	private String averrageWhAge;// 平均库龄

	private String preSaleAmount;// 预售数量

	private String onRoadAmount;// 在途数量

	private String canSaleAmount;// 可售数量

	public String getBelongWarehouseNumZijian() {
		return belongWarehouseNumZijian;
	}

	public void setBelongWarehouseNumZijian(String belongWarehouseNumZijian) {
		this.belongWarehouseNumZijian = belongWarehouseNumZijian;
	}

	public String getBelongWarehouseNumDaiguan() {
		return belongWarehouseNumDaiguan;
	}

	public void setBelongWarehouseNumDaiguan(String belongWarehouseNumDaiguan) {
		this.belongWarehouseNumDaiguan = belongWarehouseNumDaiguan;
	}

	public String getCountInAmountZijian() {
		return countInAmountZijian;
	}

	public void setCountInAmountZijian(String countInAmountZijian) {
		this.countInAmountZijian = countInAmountZijian;
	}

	public String getCountInAmountDaiguan() {
		return countInAmountDaiguan;
	}

	public void setCountInAmountDaiguan(String countInAmountDaiguan) {
		this.countInAmountDaiguan = countInAmountDaiguan;
	}

	public String getCountOutAmountZijian() {
		return countOutAmountZijian;
	}

	public void setCountOutAmountZijian(String countOutAmountZijian) {
		this.countOutAmountZijian = countOutAmountZijian;
	}

	public String getCountOutAmountDaiguan() {
		return countOutAmountDaiguan;
	}

	public void setCountOutAmountDaiguan(String countOutAmountDaiguan) {
		this.countOutAmountDaiguan = countOutAmountDaiguan;
	}

	public String getLastInDateZijian() {
		return lastInDateZijian;
	}

	public void setLastInDateZijian(String lastInDateZijian) {
		this.lastInDateZijian = lastInDateZijian;
	}

	public String getLastInDateDaiguan() {
		return lastInDateDaiguan;
	}

	public void setLastInDateDaiguan(String lastInDateDaiguan) {
		this.lastInDateDaiguan = lastInDateDaiguan;
	}

	public String getLastOutDateZijian() {
		return lastOutDateZijian;
	}

	public void setLastOutDateZijian(String lastOutDateZijian) {
		this.lastOutDateZijian = lastOutDateZijian;
	}

	public String getLastOutDateDaiguan() {
		return lastOutDateDaiguan;
	}

	public void setLastOutDateDaiguan(String lastOutDateDaiguan) {
		this.lastOutDateDaiguan = lastOutDateDaiguan;
	}

	public String getRelationSaleNumZijian() {
		return relationSaleNumZijian;
	}

	public void setRelationSaleNumZijian(String relationSaleNumZijian) {
		this.relationSaleNumZijian = relationSaleNumZijian;
	}

	public String getRelationSaleNumDaiguan() {
		return relationSaleNumDaiguan;
	}

	public void setRelationSaleNumDaiguan(String relationSaleNumDaiguan) {
		this.relationSaleNumDaiguan = relationSaleNumDaiguan;
	}

	public String getRelationBuyNumZijian() {
		return relationBuyNumZijian;
	}

	public void setRelationBuyNumZijian(String relationBuyNumZijian) {
		this.relationBuyNumZijian = relationBuyNumZijian;
	}

	public String getRelationBuyNumDaiguan() {
		return relationBuyNumDaiguan;
	}

	public void setRelationBuyNumDaiguan(String relationBuyNumDaiguan) {
		this.relationBuyNumDaiguan = relationBuyNumDaiguan;
	}

	private String countInAmountZijian;// 累计入库数量自建库存

	private String countInAmountDaiguan;// 累计入库数量代管库存

	private String countOutAmountZijian;// 累计出库数量自建库存

	private String countOutAmountDaiguan;// 累计出库数量代管库存

	private String lastInDateZijian;// 最后入库日期自建库存

	private String lastInDateDaiguan;// 最后入库日期代管库存

	private String lastOutDateZijian;// 最后出库日期自建库存

	private String lastOutDateDaiguan;// 最后出库日期代管库存

	private String stockCost;// 库存成本

	private String relationSaleNumZijian;// 关联销售单号数量自建库存

	private String relationSaleNumDaiguan;// 关联销售单号数量代管库存

	private String relationBuyNumZijian;// 关联采购单号数量自建库存

	private String relationBuyNumDaiguan;// 关联采购单号数量代管库存

	private String riskGrade;// 风险等级

	private String status;// 状态

	private String materielName;

	private String specifications;

	private String materielNum;

	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum == null ? null : serialNum.trim();
	}

	public String getMaterielSerial() {
		return materielSerial;
	}

	public void setMaterielSerial(String materielSerial) {
		this.materielSerial = materielSerial == null ? null : materielSerial
				.trim();
	}

	public String getStockNum() {
		return stockNum;
	}

	public void setStockNum(String stockNum) {
		this.stockNum = stockNum == null ? null : stockNum.trim();
	}

	public String getMaxStock() {
		return maxStock;
	}

	public void setMaxStock(String maxStock) {
		this.maxStock = maxStock == null ? null : maxStock.trim();
	}

	public String getMinStock() {
		return minStock;
	}

	public void setMinStock(String minStock) {
		this.minStock = minStock == null ? null : minStock.trim();
	}

	public String getManageType() {
		return manageType;
	}

	public void setManageType(String manageType) {
		this.manageType = manageType == null ? null : manageType.trim();
	}

	public String getMaterielOwner() {
		return materielOwner;
	}

	public void setMaterielOwner(String materielOwner) {
		this.materielOwner = materielOwner == null ? null : materielOwner
				.trim();
	}

	public String getServiceParty() {
		return serviceParty;
	}

	public void setServiceParty(String serviceParty) {
		this.serviceParty = serviceParty == null ? null : serviceParty.trim();
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

	public String getCurrentAmount() {
		return currentAmount;
	}

	public void setCurrentAmount(String currentAmount) {
		this.currentAmount = currentAmount;
	}

	public String getAverrageWhAge() {
		return averrageWhAge;
	}

	public void setAverrageWhAge(String averrageWhAge) {
		this.averrageWhAge = averrageWhAge;
	}

	public String getPreSaleAmount() {
		return preSaleAmount;
	}

	public void setPreSaleAmount(String preSaleAmount) {
		this.preSaleAmount = preSaleAmount;
	}

	public String getOnRoadAmount() {
		return onRoadAmount;
	}

	public void setOnRoadAmount(String onRoadAmount) {
		this.onRoadAmount = onRoadAmount;
	}

	public String getCanSaleAmount() {
		return canSaleAmount;
	}

	public void setCanSaleAmount(String canSaleAmount) {
		this.canSaleAmount = canSaleAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getMaterielNum() {
		return materielNum;
	}

	public void setMaterielNum(String materielNum) {
		this.materielNum = materielNum;
	}

	public String getStockCost() {
		return stockCost;
	}

	public void setStockCost(String stockCost) {
		this.stockCost = stockCost;
	}

	public String getRiskGrade() {
		return riskGrade;
	}

	public void setRiskGrade(String riskGrade) {
		this.riskGrade = riskGrade;
	}

	public String getComId() {
		return comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}

}