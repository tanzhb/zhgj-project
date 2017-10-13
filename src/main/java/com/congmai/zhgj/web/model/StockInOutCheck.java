package com.congmai.zhgj.web.model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @ClassName StockInOutCheck
 * @Description TODO(出入库检验)
 * @author zhaichao
 * @Date 2017年8月23日 下午3:45:51
 * @version 1.0.0
 */
public class StockInOutCheck {
    private String serialNum;

    private String deliverSerial;

    private String takeDeliverSerial;

    private String checkNum;

    private String checkParty;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date checkDate;

    private String checker;

    private String contactNum;

    private String status;

    private String remark;

    private String delFlg;

    private String creator;

    private Date createTime;

    private String updater;

    private Date updateTime;
    
private  String  deliverNum;//发货单号
    
    private  String  takeDeliverNum;//收货单号
    
    private  String  materialNum;//物料种类数量
    
    private  String  qualifiedCount;//合格数量
    
    private  String  unQualifiedCount;//不合格数量
    
    private  String  supplyName;//供应商名称
    
private String  relationSaleNum;//关联销售单号
    
    private String  relationBuyNum;//关联采购单号
    
    private  String  totalDeliverCount;//合计发货数量
    
    private  String totalQualifiedCount;//合计合格数量
    
    private  String totalUnQualifiedCount;//合计不合格数量
    
    
    
    private List<DeliveryMateriel>deliverMaterials;

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

    public String getTakeDeliverSerial() {
        return takeDeliverSerial;
    }

    public void setTakeDeliverSerial(String takeDeliverSerial) {
        this.takeDeliverSerial = takeDeliverSerial == null ? null : takeDeliverSerial.trim();
    }

    public String getCheckNum() {
        return checkNum;
    }

    public void setCheckNum(String checkNum) {
        this.checkNum = checkNum == null ? null : checkNum.trim();
    }

    public String getCheckParty() {
        return checkParty;
    }

    public void setCheckParty(String checkParty) {
        this.checkParty = checkParty == null ? null : checkParty.trim();
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public String getChecker() {
        return checker;
    }

    public void setChecker(String checker) {
        this.checker = checker == null ? null : checker.trim();
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum == null ? null : contactNum.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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

	public String getDeliverNum() {
		return deliverNum;
	}

	public void setDeliverNum(String deliverNum) {
		this.deliverNum = deliverNum;
	}

	public String getTakeDeliverNum() {
		return takeDeliverNum;
	}

	public void setTakeDeliverNum(String takeDeliverNum) {
		this.takeDeliverNum = takeDeliverNum;
	}

	public String getMaterialNum() {
		return materialNum;
	}

	public void setMaterialNum(String materialNum) {
		this.materialNum = materialNum;
	}

	public String getQualifiedCount() {
		return qualifiedCount;
	}

	public void setQualifiedCount(String qualifiedCount) {
		this.qualifiedCount = qualifiedCount;
	}

	public String getUnQualifiedCount() {
		return unQualifiedCount;
	}

	public void setUnQualifiedCount(String unQualifiedCount) {
		this.unQualifiedCount = unQualifiedCount;
	}

	public String getSupplyName() {
		return supplyName;
	}

	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
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

	public List<DeliveryMateriel> getDeliverMaterials() {
		return deliverMaterials;
	}

	public void setDeliverMaterials(List<DeliveryMateriel> deliverMaterials) {
		this.deliverMaterials = deliverMaterials;
	}

	public String getTotalDeliverCount() {
		return totalDeliverCount;
	}

	public void setTotalDeliverCount(String totalDeliverCount) {
		this.totalDeliverCount = totalDeliverCount;
	}

	public String getTotalQualifiedCount() {
		return totalQualifiedCount;
	}

	public void setTotalQualifiedCount(String totalQualifiedCount) {
		this.totalQualifiedCount = totalQualifiedCount;
	}

	public String getTotalUnQualifiedCount() {
		return totalUnQualifiedCount;
	}

	public void setTotalUnQualifiedCount(String totalUnQualifiedCount) {
		this.totalUnQualifiedCount = totalUnQualifiedCount;
	}
	
	
    
}