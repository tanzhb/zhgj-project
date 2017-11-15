package com.congmai.zhgj.web.model;

import java.util.Date;

/**
 * @ClassName StockOutBatch
 * @Description TODO(出库来源批次;)
 * @author zhaichao
 * @Date 2017年10月12日 下午5:29:08
 * @version 1.0.0
 */

public class StockOutBatch {
    private String serialNum;

    private String stockOutSerial;//出库单流水

    private String stockInSerial;

    private String stockOutMaterielSerial;//发货物料流水

    private String stockInMaterielSerial;
    
    private String stockInBatchSerial;

    private String outCount;

    private String remark;

    private String delFlg;

    private String creator;

    private Date createTime;

    private String updater;

    private Date updateTime;

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum == null ? null : serialNum.trim();
    }

    public String getStockOutSerial() {
        return stockOutSerial;
    }

    public void setStockOutSerial(String stockOutSerial) {
        this.stockOutSerial = stockOutSerial == null ? null : stockOutSerial.trim();
    }

    public String getStockInSerial() {
        return stockInSerial;
    }

    public void setStockInSerial(String stockInSerial) {
        this.stockInSerial = stockInSerial == null ? null : stockInSerial.trim();
    }

    public String getStockOutMaterielSerial() {
        return stockOutMaterielSerial;
    }

    public void setStockOutMaterielSerial(String stockOutMaterielSerial) {
        this.stockOutMaterielSerial = stockOutMaterielSerial == null ? null : stockOutMaterielSerial.trim();
    }

    public String getStockInMaterielSerial() {
        return stockInMaterielSerial;
    }

    public void setStockInMaterielSerial(String stockInMaterielSerial) {
        this.stockInMaterielSerial = stockInMaterielSerial == null ? null : stockInMaterielSerial.trim();
    }

    public String getOutCount() {
        return outCount;
    }

    public void setOutCount(String outCount) {
        this.outCount = outCount == null ? null : outCount.trim();
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

	public String getStockInBatchSerial() {
		return stockInBatchSerial;
	}

	public void setStockInBatchSerial(String stockInBatchSerial) {
		this.stockInBatchSerial = stockInBatchSerial;
	}
    
}