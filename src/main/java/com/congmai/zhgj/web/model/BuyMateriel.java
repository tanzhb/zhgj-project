package com.congmai.zhgj.web.model;

import java.util.Date;

public class BuyMateriel {
    private String serialNum;

    private String materielId;
    
    private Materiel materiel;
    
    private String buyComId;
    
    private Company buy;
    
    private String buyMaterielNum;

    private String packageNum;

    private String packageSpecifications;

    private String packageUnit;

    private String packagewidth;

    private String packageheight;

    private String packagelength;

    private String packagevolume;

    private String packageUnitConversion;

    private String delFlg;

    private String creator;

    private Date createTime;

    private String updater;

    private Date updateTime;

    private String remark;

    private String materielName;

    private String specifications;

    private String unit;

    private String type;

    private String category1;

    private String category2;

    private String category3;

    private String typeName;
    private String categoryName1;
    private String categoryName2;
    private String categoryName3;
    
    private String unitPriceGuide;

    private String purchaseQuota;

    private String moq;
    
    private Integer sort;

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum == null ? null : serialNum.trim();
    }

    public String getMaterielId() {
        return materielId;
    }

    public void setMaterielId(String materielId) {
        this.materielId = materielId == null ? null : materielId.trim();
    }

    public String getBuyComId() {
        return buyComId;
    }

    public void setBuyComId(String buyComId) {
        this.buyComId = buyComId == null ? null : buyComId.trim();
    }

    public String getBuyMaterielNum() {
        return buyMaterielNum;
    }

    public void setBuyMaterielNum(String buyMaterielNum) {
        this.buyMaterielNum = buyMaterielNum == null ? null : buyMaterielNum.trim();
    }

    public String getPackageNum() {
        return packageNum;
    }

    public void setPackageNum(String packageNum) {
        this.packageNum = packageNum == null ? null : packageNum.trim();
    }

    public String getPackageSpecifications() {
        return packageSpecifications;
    }

    public void setPackageSpecifications(String packageSpecifications) {
        this.packageSpecifications = packageSpecifications == null ? null : packageSpecifications.trim();
    }

    public String getPackageUnit() {
        return packageUnit;
    }

    public void setPackageUnit(String packageUnit) {
        this.packageUnit = packageUnit == null ? null : packageUnit.trim();
    }

    public String getPackagewidth() {
        return packagewidth;
    }

    public void setPackagewidth(String packagewidth) {
        this.packagewidth = packagewidth == null ? null : packagewidth.trim();
    }

    public String getPackageheight() {
        return packageheight;
    }

    public void setPackageheight(String packageheight) {
        this.packageheight = packageheight == null ? null : packageheight.trim();
    }

    public String getPackagelength() {
        return packagelength;
    }

    public void setPackagelength(String packagelength) {
        this.packagelength = packagelength == null ? null : packagelength.trim();
    }

    public String getPackagevolume() {
        return packagevolume;
    }

    public void setPackagevolume(String packagevolume) {
        this.packagevolume = packagevolume == null ? null : packagevolume.trim();
    }

    public String getPackageUnitConversion() {
        return packageUnitConversion;
    }

    public void setPackageUnitConversion(String packageUnitConversion) {
        this.packageUnitConversion = packageUnitConversion == null ? null : packageUnitConversion.trim();
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getMaterielName() {
        return materielName;
    }

    public void setMaterielName(String materielName) {
        this.materielName = materielName == null ? null : materielName.trim();
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications == null ? null : specifications.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getCategory1() {
        return category1;
    }

    public void setCategory1(String category1) {
        this.category1 = category1 == null ? null : category1.trim();
    }

    public String getCategory2() {
        return category2;
    }

    public void setCategory2(String category2) {
        this.category2 = category2 == null ? null : category2.trim();
    }

    public String getCategory3() {
        return category3;
    }

    public void setCategory3(String category3) {
        this.category3 = category3 == null ? null : category3.trim();
    }

    public String getUnitPriceGuide() {
        return unitPriceGuide;
    }

    public void setUnitPriceGuide(String unitPriceGuide) {
        this.unitPriceGuide = unitPriceGuide == null ? null : unitPriceGuide.trim();
    }

    public String getPurchaseQuota() {
        return purchaseQuota;
    }

    public void setPurchaseQuota(String purchaseQuota) {
        this.purchaseQuota = purchaseQuota == null ? null : purchaseQuota.trim();
    }

	public String getMoq() {
		return moq;
	}

	public void setMoq(String moq) {
		this.moq = moq;
	}

	public Materiel getMateriel() {
		return materiel;
	}

	public void setMateriel(Materiel materiel) {
		this.materiel = materiel;
	}

	public Company getBuy() {
		return buy;
	}

	public void setBuy(Company buy) {
		this.buy = buy;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getCategoryName1() {
		return categoryName1;
	}

	public void setCategoryName1(String categoryName1) {
		this.categoryName1 = categoryName1;
	}

	public String getCategoryName2() {
		return categoryName2;
	}

	public void setCategoryName2(String categoryName2) {
		this.categoryName2 = categoryName2;
	}

	public String getCategoryName3() {
		return categoryName3;
	}

	public void setCategoryName3(String categoryName3) {
		this.categoryName3 = categoryName3;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
    
}