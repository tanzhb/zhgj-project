package com.congmai.zhgj.web.model;

import java.util.Date;
import java.util.List;


public class CompanyManage {
    private String serialNum;

    private String comId;

    private String comShortName;

    private String userId;
    
    private String type;
    
    private String grade;

    private String creator;

    private Date createTime;

    private String updater;

    private Date updateTime;
    
	private List<User> users;//维护人员信息

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum == null ? null : serialNum.trim();
    }

    public String getComId() {
        return comId;
    }

    public void setComId(String comId) {
        this.comId = comId == null ? null : comId.trim();
    }

    public String getComShortName() {
        return comShortName;
    }

    public void setComShortName(String comShortName) {
        this.comShortName = comShortName == null ? null : comShortName.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
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

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getType() {
		return type;
	}

	public String getGrade() {
		return grade;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
    
}