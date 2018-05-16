package com.congmai.zhgj.wms.model;

import java.util.Date;

public class Supplier {
    private String id;

    private String enterpriseNum;

    private String enterpriseName;

    private String enterpriseShortname;

    private String enterpriseType;
    
    private String enterpriseTypeName;

    private String managementType;
    
    private String managementTypeName;

    private String address;

    private String contacts;

    private String telphoneNum;

    private String email;

    private String enterpriseDesc;

    private Integer delFlg;

    private Integer isForbit;

    private Integer isDefualt;

    private String creator;

    private Date createTime;

    private String updator;

    private Date updateTime;

    private String remark;
    
    
    private String searchKey;
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getEnterpriseNum() {
        return enterpriseNum;
    }

    public void setEnterpriseNum(String enterpriseNum) {
        this.enterpriseNum = enterpriseNum == null ? null : enterpriseNum.trim();
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName == null ? null : enterpriseName.trim();
    }

    public String getManagementTypeName() {
		return managementTypeName;
	}

	public void setManagementTypeName(String managementTypeName) {
		this.managementTypeName = managementTypeName;
	}

	public String getEnterpriseShortname() {
        return enterpriseShortname;
    }

    public void setEnterpriseShortname(String enterpriseShortname) {
        this.enterpriseShortname = enterpriseShortname == null ? null : enterpriseShortname.trim();
    }

    public String getEnterpriseType() {
        return enterpriseType;
    }

    public void setEnterpriseType(String enterpriseType) {
        this.enterpriseType = enterpriseType == null ? null : enterpriseType.trim();
    }

    public String getManagementType() {
        return managementType;
    }

    public void setManagementType(String managementType) {
        this.managementType = managementType == null ? null : managementType.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts == null ? null : contacts.trim();
    }

    public String getTelphoneNum() {
        return telphoneNum;
    }

    public void setTelphoneNum(String telphoneNum) {
        this.telphoneNum = telphoneNum == null ? null : telphoneNum.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getEnterpriseDesc() {
        return enterpriseDesc;
    }

    public void setEnterpriseDesc(String enterpriseDesc) {
        this.enterpriseDesc = enterpriseDesc == null ? null : enterpriseDesc.trim();
    }

    public Integer getDelFlg() {
        return delFlg;
    }

    public void setDelFlg(Integer delFlg) {
        this.delFlg = delFlg;
    }

    public Integer getIsForbit() {
        return isForbit;
    }

    public void setIsForbit(Integer isForbit) {
        this.isForbit = isForbit;
    }

    public Integer getIsDefualt() {
        return isDefualt;
    }

    public void setIsDefualt(Integer isDefualt) {
        this.isDefualt = isDefualt;
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

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator == null ? null : updator.trim();
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

	public String getEnterpriseTypeName() {
		return enterpriseTypeName;
	}

	public void setEnterpriseTypeName(String enterpriseTypeName) {
		this.enterpriseTypeName = enterpriseTypeName;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
}