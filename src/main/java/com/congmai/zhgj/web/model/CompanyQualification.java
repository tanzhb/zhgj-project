package com.congmai.zhgj.web.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CompanyQualification {
    private String serialNum;

    private String comId;

    private String qualificationNum;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date validityDate;

    private String qualificationName;

    private String qualificatioImage;

    private String delFlg;

    private String creator;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String updater;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

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

    public String getQualificationNum() {
        return qualificationNum;
    }

    public void setQualificationNum(String qualificationNum) {
        this.qualificationNum = qualificationNum == null ? null : qualificationNum.trim();
    }
    @JsonFormat(timezone="GTM+8",pattern="yyyy-MM-dd")
    public Date getValidityDate() {
        return validityDate;
    }

    public void setValidityDate(Date validityDate) {
        this.validityDate = validityDate;
    }

    public String getQualificationName() {
        return qualificationName;
    }

    public void setQualificationName(String qualificationName) {
        this.qualificationName = qualificationName == null ? null : qualificationName.trim();
    }

    public String getQualificatioImage() {
        return qualificatioImage;
    }

    public void setQualificatioImage(String qualificatioImage) {
        this.qualificatioImage = qualificatioImage == null ? null : qualificatioImage.trim();
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
    @JsonFormat(timezone="GTM+8",pattern="yyyy-MM-dd HH:mm:ss")
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
    @JsonFormat(timezone="GTM+8",pattern="yyyy-MM-dd HH:mm:ss")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}