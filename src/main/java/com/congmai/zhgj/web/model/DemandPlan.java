package com.congmai.zhgj.web.model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DemandPlan {
    private String serialNum;

    private String buyComId;

    private String demandPlanNum;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date releaseDate;

    private String remark;

    private String delFlg;

    private String creator;

    private Date createTime;

    private String updater;

    private Date updateTime;
    
    private List<DemandPlanMateriel> materiels;
    
    //冗余字段
    private String buyComName;
    
    private String searchKey;

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum == null ? null : serialNum.trim();
    }

    public String getBuyComId() {
        return buyComId;
    }

    public void setBuyComId(String buyComId) {
        this.buyComId = buyComId == null ? null : buyComId.trim();
    }

    public String getDemandPlanNum() {
        return demandPlanNum;
    }

    public void setDemandPlanNum(String demandPlanNum) {
        this.demandPlanNum = demandPlanNum == null ? null : demandPlanNum.trim();
    }

    @JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd")
    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
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

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	public List<DemandPlanMateriel> getMateriels() {
		return materiels;
	}

	public void setMateriels(List<DemandPlanMateriel> materiels) {
		this.materiels = materiels;
	}

	public String getBuyComName() {
		return buyComName;
	}

	public void setBuyComName(String buyComName) {
		this.buyComName = buyComName;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
}