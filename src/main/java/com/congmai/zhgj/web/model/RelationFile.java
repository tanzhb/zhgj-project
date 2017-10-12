package com.congmai.zhgj.web.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class RelationFile {
	/**物料附件流水号*/
	private String serialNum;
	
	/**关联单据物料流水号*/
	private String relationSerial;
	
	/**是否可读（1是0否）*/
	private String isReadable;
	
	/**附件类型（发货，收货，检验，出库，入库*/
	private String fileType;
	
	/**描述*/
	private String fileDescribe;
	
	/**文件*/
	private String file;
	
	/**上传人*/
	private String uploader;
	
	/**上传时间*/
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date uploadDate;
	
	/**删除标志（1是0否）*/
	private String delFlg;
	
	/**创建人*/
	private String creator;
	
	/**创建时间*/
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createTime;
	
	/**更新人*/
	private String updater;
	
	/**更新时间*/
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updateTime;
	
	/**备注*/
	private String remark;


	public String getSerialNum() {
		return serialNum;
	}


	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}


	public String getRelationSerial() {
		return relationSerial;
	}


	public void setRelationSerial(String relationSerial) {
		this.relationSerial = relationSerial;
	}


	public String getIsReadable() {
		return isReadable;
	}


	public void setIsReadable(String isReadable) {
		this.isReadable = isReadable;
	}


	public String getFileType() {
		return fileType;
	}


	public void setFileType(String fileType) {
		this.fileType = fileType;
	}


	public String getFileDescribe() {
		return fileDescribe;
	}


	public void setFileDescribe(String fileDescribe) {
		this.fileDescribe = fileDescribe;
	}


	public String getFile() {
		return file;
	}


	public void setFile(String file) {
		this.file = file;
	}


	public String getUploader() {
		return uploader;
	}


	public void setUploader(String uploader) {
		this.uploader = uploader;
	}


	@JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd")
	public Date getUploadDate() {
		return uploadDate;
	}


	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}


	public String getDelFlg() {
		return delFlg;
	}


	public void setDelFlg(String delFlg) {
		this.delFlg = delFlg;
	}


	public String getCreator() {
		return creator;
	}


	public void setCreator(String creator) {
		this.creator = creator;
	}


	@JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd")
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
		this.updater = updater;
	}


	@JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd")
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
		this.remark = remark;
	}
}
