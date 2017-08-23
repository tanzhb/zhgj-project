package com.congmai.zhgj.web.model;

import java.util.Date;

/**
 * @ClassName Stock
 * @Description TODO(库存)
 * @author zhaichao
 * @Date 2017年8月21日 下午5:30:06
 * @version 1.0.0
 */
public class Stock {
    private String serialNum;

    private String materielSerial;//物料流水号

    private String stockNum;//库存编号

    private String maxStock;//最高库存

    private String minStock;//最低库存

    private String manageType;//管理类型

    private String materielOwner;//物权方

    private String serviceParty;//服务方

    private String remark;

    private String delFlg;

    private String creator;

    private Date createTime;

    private String updater;

    private Date updateTime;
    
    private  String  currentAmount;//当前库存数量
    
    private  String  belongWarehouseNum;//所在仓库数量
    
    private  String averrageWhAge;//平均库龄
    
    private String   preSaleAmount;//预售数量
    
    private String  onRoadAmount;//在途数量
    
    private String  canSaleAmount;//可售数量
    
    private String  countInAmount;//累计入库数量
    
    private String  countOutAmount;//累计出库数量
    
   private String  lastInDate;//最后入库日期
    
    private String  lastOutDate;//最后出库日期
    
    private String  stockCost;//库存成本
    
    private String  relationSaleNum;//关联销售单号
    
    private String  relationBuyNum;//关联采购单号
    
    private String  riskGrade;//风险等级
    
    private String status;//状态
    
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
        this.materielSerial = materielSerial == null ? null : materielSerial.trim();
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
        this.materielOwner = materielOwner == null ? null : materielOwner.trim();
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

	public String getBelongWarehouseNum() {
		return belongWarehouseNum;
	}

	public void setBelongWarehouseNum(String belongWarehouseNum) {
		this.belongWarehouseNum = belongWarehouseNum;
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

	public String getCountInAmount() {
		return countInAmount;
	}

	public void setCountInAmount(String countInAmount) {
		this.countInAmount = countInAmount;
	}

	public String getCountOutAmount() {
		return countOutAmount;
	}

	public void setCountOutAmount(String countOutAmount) {
		this.countOutAmount = countOutAmount;
	}

	public String getLastInDate() {
		return lastInDate;
	}

	public void setLastInDate(String lastInDate) {
		this.lastInDate = lastInDate;
	}

	public String getLastOutDate() {
		return lastOutDate;
	}

	public void setLastOutDate(String lastOutDate) {
		this.lastOutDate = lastOutDate;
	}

	public String getStockCost() {
		return stockCost;
	}

	public void setStockCost(String stockCost) {
		this.stockCost = stockCost;
	}

	public String getRelationSaleNum() {
		return relationSaleNum;
	}

	public void setRelationSaleNum(String relationSaleNum) {
		this.relationSaleNum = relationSaleNum;
	}

	public String getRelationBuyNum() {
		return relationBuyNum;
	}

	public void setRelationBuyNum(String relationBuyNum) {
		this.relationBuyNum = relationBuyNum;
	}

	public String getRiskGrade() {
		return riskGrade;
	}

	public void setRiskGrade(String riskGrade) {
		this.riskGrade = riskGrade;
	}
    
}