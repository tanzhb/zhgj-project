package com.congmai.zhgj.web.model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Materiel {
    private String serialNum;

    private String materielId;

    private String materielNum;

    private String materielName;

    private String specifications;

    private String unit;

    private String parentMaterielSerial;
    
    private Materiel parentMateriel;
    
    private List<SupplyMateriel> supplyMateriels;

    private String type;

    private String productionPlace;

    private String brand;

    private String category;

    private String mnemonicCode;

    private String isBOM;

    private String stockUnit;

    private String originCountry;

    private String width;

    private String height;

    private String length;

    private String currency;

    private String unitPrice;

    private String deliveryCycle;
    
    private String filingItemNo;

    private String volume;

    private String weight;

    private String palletSpecification;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date manufactureDate;

    private String remark;

    private String packageNum;

    private String packageSpecifications;

    private String packageUnit;

    private String packagewidth;

    private String packageheight;

    private String packagelength;

    private String packagevolume;

    private String packageUnitConversion;

    private String palletWeight;

    private String customsSupervisionConditions;
    
    private String customsCode;

    private String qualityDate;

    private String versionNO;

    private String isLatestVersion;

    private String status;

    private String delFlg;

    private String creator;

    private Date createTime;

    private String updater;

    private Date updateTime;
    
/*    private boolean isBOMcheck;*/
    
    private String  canBillAmount;//可开/可收数量
    
    private String  orderUnitPrice;//含税单价
    
    private String amount ;//订单数量
    
    private String  billAmount;//开票数量/收票数量
    
    private String  money;//当前开票金额
    
    private String  invoiceBillingRecordSerial;//发票开票/收票记录流水
    
    

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

    public String getMaterielNum() {
        return materielNum;
    }

    public void setMaterielNum(String materielNum) {
        this.materielNum = materielNum == null ? null : materielNum.trim();
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

    public String getParentMaterielSerial() {
        return parentMaterielSerial;
    }

    public void setParentMaterielSerial(String parentMaterielSerial) {
        this.parentMaterielSerial = parentMaterielSerial == null ? null : parentMaterielSerial.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getProductionPlace() {
        return productionPlace;
    }

    public void setProductionPlace(String productionPlace) {
        this.productionPlace = productionPlace == null ? null : productionPlace.trim();
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand == null ? null : brand.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getMnemonicCode() {
        return mnemonicCode;
    }

    public void setMnemonicCode(String mnemonicCode) {
        this.mnemonicCode = mnemonicCode == null ? null : mnemonicCode.trim();
    }

    public String getIsBOM() {
        return isBOM;
    }

    public void setIsBOM(String isBOM) {
        this.isBOM = isBOM == null ? null : isBOM.trim();
    }

    public String getStockUnit() {
        return stockUnit;
    }

    public void setStockUnit(String stockUnit) {
        this.stockUnit = stockUnit == null ? null : stockUnit.trim();
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry == null ? null : originCountry.trim();
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width == null ? null : width.trim();
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height == null ? null : height.trim();
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length == null ? null : length.trim();
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

    public String getFilingItemNo() {
        return filingItemNo;
    }

    public void setFilingItemNo(String filingItemNo) {
        this.filingItemNo = filingItemNo == null ? null : filingItemNo.trim();
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume == null ? null : volume.trim();
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight == null ? null : weight.trim();
    }

    public String getPalletSpecification() {
        return palletSpecification;
    }

    public void setPalletSpecification(String palletSpecification) {
        this.palletSpecification = palletSpecification == null ? null : palletSpecification.trim();
    }

    @JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd")
    public Date getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(Date manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

    public String getPalletWeight() {
        return palletWeight;
    }

    public void setPalletWeight(String palletWeight) {
        this.palletWeight = palletWeight == null ? null : palletWeight.trim();
    }

    public String getCustomsSupervisionConditions() {
        return customsSupervisionConditions;
    }

    public void setCustomsSupervisionConditions(String customsSupervisionConditions) {
        this.customsSupervisionConditions = customsSupervisionConditions == null ? null : customsSupervisionConditions.trim();
    }

    public String getQualityDate() {
        return qualityDate;
    }

    public void setQualityDate(String qualityDate) {
        this.qualityDate = qualityDate == null ? null : qualityDate.trim();
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

	public Materiel getParentMateriel() {
		return parentMateriel;
	}

	public void setParentMateriel(Materiel parentMateriel) {
		this.parentMateriel = parentMateriel;
	}

	public List<SupplyMateriel> getSupplyMateriels() {
		return supplyMateriels;
	}

	public void setSupplyMateriels(List<SupplyMateriel> supplyMateriels) {
		this.supplyMateriels = supplyMateriels;
	}

	public String getDeliveryCycle() {
		return deliveryCycle;
	}

	public void setDeliveryCycle(String deliveryCycle) {
		this.deliveryCycle = deliveryCycle;
	}


	public String getCanBillAmount() {
		return canBillAmount;
	}

	public void setCanBillAmount(String canBillAmount) {
		this.canBillAmount = canBillAmount;
	}

	public String getOrderUnitPrice() {
		return orderUnitPrice;
	}

	public void setOrderUnitPrice(String orderUnitPrice) {
		this.orderUnitPrice = orderUnitPrice;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(String billAmount) {
		this.billAmount = billAmount;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getCustomsCode() {
		return customsCode;
	}

	public void setCustomsCode(String customsCode) {
		this.customsCode = customsCode;
	}

	public String getInvoiceBillingRecordSerial() {
		return invoiceBillingRecordSerial;
	}

	public void setInvoiceBillingRecordSerial(String invoiceBillingRecordSerial) {
		this.invoiceBillingRecordSerial = invoiceBillingRecordSerial;
	}


	

    
}