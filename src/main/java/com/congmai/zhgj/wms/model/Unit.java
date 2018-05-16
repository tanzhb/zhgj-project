package com.congmai.zhgj.wms.model;

import java.util.Date;

public class Unit {
    private String id;

    private String unitnum;

    private String unitname;

    private Integer delflag;

    private Integer isforbit;

    private Integer isdefualt;

    private Date createtime;

    private String creator;

    private Date updatetime;

    private String updator;

    private String remark;

    private String searchKey;
    
    
    public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUnitnum() {
        return unitnum;
    }

    public void setUnitnum(String unitnum) {
        this.unitnum = unitnum == null ? null : unitnum.trim();
    }

    public String getUnitname() {
        return unitname;
    }

    public void setUnitname(String unitname) {
        this.unitname = unitname == null ? null : unitname.trim();
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
}