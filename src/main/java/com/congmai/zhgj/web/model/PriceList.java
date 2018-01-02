package com.congmai.zhgj.web.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @ClassName PriceList
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author zhaichao
 * @Date 2017年8月8日 下午3:01:50
 * @version 1.0.0
 */
public class PriceList extends BaseVO{
    private String serialNum;

    private String priceNum;
    
    private String priceId;

    private String supplyComId;

    private String buyComId;

    private String priceDescribe;

    private String materielSerial;

    private String rate;

    private String isLadderPrice;

    private String ladderType;

    private String inclusivePrice;

    private String price;

    private String priceType;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date priceEffectiveDate;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date priceExpirationDate;

    private String remark;

    private String file;

    private String delFlg;

    private String creator;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String versionNO;

    private String isLatestVersion;

    private String updater;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private String currency;

    private String unitPrice;

    private String topPrice;

    private String floorPrice;
    
    private String materielName;
    
    private String specifications;
    
    private String unit;
    
    private String materielNum;
    
    private String supplyComName;
    
    private String buyComName;
    
    private   String number;//订单数量
    
    
    private String comNum;//企业编号
    
    private String comName;//企业名称
    
    private ProcessBase processBase;//流程字段类
    
    

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

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getMaterielNum() {
		return materielNum;
	}

	public void setMaterielNum(String materielNum) {
		this.materielNum = materielNum;
	}

	public String getSupplyComName() {
		return supplyComName;
	}

	public void setSupplyComName(String supplyComName) {
		this.supplyComName = supplyComName;
	}

	public String getBuyComName() {
		return buyComName;
	}

	public void setBuyComName(String buyComName) {
		this.buyComName = buyComName;
	}

	public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum == null ? null : serialNum.trim();
    }

    public String getPriceNum() {
        return priceNum;
    }

    public void setPriceNum(String priceNum) {
        this.priceNum = priceNum == null ? null : priceNum.trim();
    }

    public String getSupplyComId() {
        return supplyComId;
    }

    public void setSupplyComId(String supplyComId) {
        this.supplyComId = supplyComId == null ? null : supplyComId.trim();
    }

    public String getBuyComId() {
        return buyComId;
    }

    public void setBuyComId(String buyComId) {
        this.buyComId = buyComId == null ? null : buyComId.trim();
    }

    public String getPriceDescribe() {
        return priceDescribe;
    }

    public void setPriceDescribe(String priceDescribe) {
        this.priceDescribe = priceDescribe == null ? null : priceDescribe.trim();
    }

    public String getMaterielSerial() {
        return materielSerial;
    }

    public void setMaterielSerial(String materielSerial) {
        this.materielSerial = materielSerial == null ? null : materielSerial.trim();
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate == null ? null : rate.trim();
    }

    public String getIsLadderPrice() {
        return isLadderPrice;
    }

    public void setIsLadderPrice(String isLadderPrice) {
        this.isLadderPrice = isLadderPrice == null ? null : isLadderPrice.trim();
    }

    public String getLadderType() {
        return ladderType;
    }

    public void setLadderType(String ladderType) {
        this.ladderType = ladderType == null ? null : ladderType.trim();
    }

    public String getInclusivePrice() {
        return inclusivePrice;
    }

    public void setInclusivePrice(String inclusivePrice) {
        this.inclusivePrice = inclusivePrice == null ? null : inclusivePrice.trim();
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price == null ? null : price.trim();
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType == null ? null : priceType.trim();
    }

    public Date getPriceEffectiveDate() {
        return priceEffectiveDate;
    }

    public void setPriceEffectiveDate(Date priceEffectiveDate) {
        this.priceEffectiveDate = priceEffectiveDate;
    }

    public Date getPriceExpirationDate() {
        return priceExpirationDate;
    }

    public void setPriceExpirationDate(Date priceExpirationDate) {
        this.priceExpirationDate = priceExpirationDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file == null ? null : file.trim();
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

    public String getVersionNO() {
        return versionNO;
    }

    public void setVersionNO(String versionNO) {
        this.versionNO = versionNO == null ? null : versionNO.trim();
    }

    public String getIsLatestVersion() {
        return isLatestVersion;
    }

    public void setIsLatestVersion(String isLatestVersion) {
        this.isLatestVersion = isLatestVersion == null ? null : isLatestVersion.trim();
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice == null ? null : unitPrice.trim();
    }

    public String getTopPrice() {
        return topPrice;
    }

    public void setTopPrice(String topPrice) {
        this.topPrice = topPrice == null ? null : topPrice.trim();
    }

    public String getFloorPrice() {
        return floorPrice;
    }

    public void setFloorPrice(String floorPrice) {
        this.floorPrice = floorPrice == null ? null : floorPrice.trim();
    }

	public String getPriceId() {
		return priceId;
	}

	public void setPriceId(String priceId) {
		 this.priceId = priceId == null ? null : priceId.trim();
	}

	public String getComNum() {
		return comNum;
	}

	public void setComNum(String comNum) {
		this.comNum = comNum;
	}

	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	public ProcessBase getProcessBase() {
		return processBase;
	}

	public void setProcessBase(ProcessBase processBase) {
		this.processBase = processBase;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
}