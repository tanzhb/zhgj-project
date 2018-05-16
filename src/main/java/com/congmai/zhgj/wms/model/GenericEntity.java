package com.congmai.zhgj.wms.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * 
 * @ClassName GenericEntity
 * @Description TODO(公共实体类)
 * @author pzg
 * @Date 2017年11月28日 下午1:30:36
 * @version 1.0.0
 */
public class GenericEntity {

	/**
	 * 删除标识（1：删除，0：正常）
	 */
	private int delFlag;
	
	/**
	 * 是否禁用（1：禁用，0,：不禁用）
	 */
	private int isForbit;
	
	/**
	 * 是否默认(1:默认，0不默认）
	 */
	private int isDefualt;
	
	/**
	 * 创建人
	 */
	private String creator;
	
	/**
	 * 创建时间
	 */
	@JSONField(format = "yyyy-MM-dd")
	private Date createTime;
	
	/**
	 * 修改人
	 */
	private String updator;
	
	/**
	 * 修改时间
	 */
	@JSONField(format = "yyyy-MM-dd")
	private Date updateTime;
	
	/**
	 * 备注
	 */
	private String remark;

	public int getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(int delFlag) {
		this.delFlag = delFlag;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime == null ? new Date() : createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime == null ? new Date() : updateTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


	public int getIsForbit() {
		return isForbit;
	}

	public void setIsForbit(int isForbit) {
		this.isForbit = isForbit;
	}

	public int getIsDefualt() {
		return isDefualt;
	}

	public void setIsDefualt(int isDefualt) {
		this.isDefualt = isDefualt;
	}

	public String getUpdator() {
		return updator;
	}

	public void setUpdator(String updator) {
		this.updator = updator;
	}
}
