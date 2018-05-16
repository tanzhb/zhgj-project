package com.congmai.zhgj.wms.model;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
 * @ClassName Product
 * @Description TODO(物料信息基础表)
 * @author bingx
 * @Date 2017年11月3日 下午2:24:34
 * @version 1.0.0
 */
public class Product {
    private String id;

    private String productnum;

    private String productchname;

    private String productenname;

    private String specification;

    private String unitid;
    
    private String unitname;

    private String typeid;
    
    private String typename;
    private String typeNum;

    private String manufacturer;

    private String address;

    private String brand;

    private BigDecimal costunitprice;
 
    @JSONField(format="yyyy-MM-dd")
    private Date prducedate;
 
    @JSONField(format="yyyy-MM-dd")
    private Date validitydate;

    private String versions;

    private Long lowerlimiting;

    private BigDecimal upperlimiting;

    private String state;
    
    private String stateName;

    private Integer delflag;

    private Integer isforbit;

    private Integer isdefualt;


    private Date createtime;

    private String creator;

    private Date updatetime;

    private String updator;

    private String remark;

    private String imageUrl;
    
    private String avatar; //图片名称
    
    /**
     * 最大库龄
     */
    private float libraryAge;
    
    /*************实时库存属性 start****************/
    private Float count; // '数量',
    private String storageBatchNum; // 入库批次号
    private String productCode;// '生产批次号',
    private String barCode; //'箱条码',
	private String storageWarehouse; // '仓库',
	private String storageWarehouseName;
    private String reservoirArea; //库区
    private String reservoirAreaName;
    private String storageLocation; //'库位',
    private String storageLocationName;  
    private String invenstoryState; //实时库存产品状态
    private String machineNum;  // 机型机号
    private String projectNum;  // 项目编号
    private String batchFurnaceNum;  // 炉批号
    private String inventoryId;
//    实时库存id集合
    private String inventoryIds;

    private String ids; //已经调整的产品id集合
    private String productNumArrays; //已经存在的产品物料编码集合
    private String isOvercharge;
    
    private String searchKey;
    /*************实时库存属性 end****************/

    /*************************组件***********/
    private String pId;
    private String cId;

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    /*************************组件***********/

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getProductnum() {
        return productnum;
    }

    public void setProductnum(String productnum) {
        this.productnum = productnum == null ? null : productnum.trim();
    }

    public String getProductchname() {
        return productchname;
    }

    public void setProductchname(String productchname) {
        this.productchname = productchname == null ? null : productchname.trim();
    }

    public String getProductenname() {
        return productenname;
    }

    public void setProductenname(String productenname) {
        this.productenname = productenname == null ? null : productenname.trim();
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification == null ? null : specification.trim();
    }

    public String getUnitid() {
        return unitid;
    }

    public void setUnitid(String unitid) {
        this.unitid = unitid == null ? null : unitid.trim();
    }

    public String getTypeid() {
        return typeid;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid == null ? null : typeid.trim();
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer == null ? null : manufacturer.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand == null ? null : brand.trim();
    }

    public BigDecimal getCostunitprice() {
        return costunitprice;
    }

    public void setCostunitprice(BigDecimal costunitprice) {
        this.costunitprice = costunitprice;
    }

    public Date getPrducedate() {
        return prducedate;
    }

    public void setPrducedate(Date prducedate) {
        this.prducedate = prducedate;
    }

    public Date getValiditydate() {
        return validitydate;
    }

    public void setValiditydate(Date validitydate) {
        this.validitydate = validitydate;
    }

    public String getVersions() {
        return versions;
    }

    public void setVersions(String versions) {
        this.versions = versions == null ? null : versions.trim();
    }

    public Long getLowerlimiting() {
        return lowerlimiting;
    }

    public void setLowerlimiting(Long lowerlimiting) {
        this.lowerlimiting = lowerlimiting;
    }

    public BigDecimal getUpperlimiting() {
        return upperlimiting;
    }

    public void setUpperlimiting(BigDecimal upperlimiting) {
        this.upperlimiting = upperlimiting;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getDelflag() {
        return delflag;
    }

    public void setDelflag(Integer delflag) {
        this.delflag = delflag;
    }

    public Integer getIsforbit() {
        return isforbit;
    }

    public void setIsforbit(Integer isforbit) {
        this.isforbit = isforbit;
    }

    public Integer getIsdefualt() {
        return isdefualt;
    }

    public void setIsdefualt(Integer isdefualt) {
        this.isdefualt = isdefualt;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator == null ? null : updator.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public String getUnitname() {
		return unitname;
	}

	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Float getCount() {
		return count;
	}

	public void setCount(Float count) {
		this.count = count;
	}

	public String getStorageBatchNum() {
		return storageBatchNum;
	}

	public void setStorageBatchNum(String storageBatchNum) {
		this.storageBatchNum = storageBatchNum;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getStorageWarehouse() {
		return storageWarehouse;
	}

	public void setStorageWarehouse(String storageWarehouse) {
		this.storageWarehouse = storageWarehouse;
	}

	public String getStorageWarehouseName() {
		return storageWarehouseName;
	}

	public void setStorageWarehouseName(String storageWarehouseName) {
		this.storageWarehouseName = storageWarehouseName;
	}

	public String getReservoirArea() {
		return reservoirArea;
	}

	public void setReservoirArea(String reservoirArea) {
		this.reservoirArea = reservoirArea;
	}

	public String getReservoirAreaName() {
		return reservoirAreaName;
	}

	public void setReservoirAreaName(String reservoirAreaName) {
		this.reservoirAreaName = reservoirAreaName;
	}

	public String getStorageLocation() {
		return storageLocation;
	}

	public void setStorageLocation(String storageLocation) {
		this.storageLocation = storageLocation;
	}

	public String getStorageLocationName() {
		return storageLocationName;
	}

	public void setStorageLocationName(String storageLocationName) {
		this.storageLocationName = storageLocationName;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getInvenstoryState() {
		return invenstoryState;
	}

	public void setInvenstoryState(String invenstoryState) {
		this.invenstoryState = invenstoryState;
	}

	public String getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(String inventoryId) {
		this.inventoryId = inventoryId;
	}

	public float getLibraryAge() {
		return libraryAge;
	}

	public void setLibraryAge(float libraryAge) {
		this.libraryAge = libraryAge;
	}

	public String getProductNumArrays() {
		return productNumArrays;
	}

	public void setProductNumArrays(String productNumArrays) {
		this.productNumArrays = productNumArrays;
	}


    public String getInventoryIds() {
        return inventoryIds;
    }

    public void setInventoryIds(String inventoryIds) {
        this.inventoryIds = inventoryIds;
    }

    public String getMachineNum() {
        return machineNum;
    }

    public void setMachineNum(String machineNum) {
        this.machineNum = machineNum;
    }

    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }

    public String getBatchFurnaceNum() {
        return batchFurnaceNum;
    }

    public void setBatchFurnaceNum(String batchFurnaceNum) {
        this.batchFurnaceNum = batchFurnaceNum;
    }

    public String getTypeNum() {
        return typeNum;
    }

    public void setTypeNum(String typeNum) {
        this.typeNum = typeNum;
    }

    public String getIsOvercharge() {
        return isOvercharge;
    }

    public void setIsOvercharge(String isOvercharge) {
        this.isOvercharge = isOvercharge;
    }
}