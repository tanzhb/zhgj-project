package com.congmai.zhgj.web.model;

import java.util.Date;

public class LadderPrice {
    private String serialNum;

    private String priceSerial;

    private String countStart;

    private String countEnd;

    private String inclusivePrice;

    private String price;

    private String delFlg;

    private String creator;

    private Date createTime;

    private String updater;

    private Date updateTime;

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum == null ? null : serialNum.trim();
    }

    public String getPriceSerial() {
        return priceSerial;
    }

    public void setPriceSerial(String priceSerial) {
        this.priceSerial = priceSerial == null ? null : priceSerial.trim();
    }

    public String getCountStart() {
        return countStart;
    }

    public void setCountStart(String countStart) {
        this.countStart = countStart == null ? null : countStart.trim();
    }

    public String getCountEnd() {
        return countEnd;
    }

    public void setCountEnd(String countEnd) {
        this.countEnd = countEnd == null ? null : countEnd.trim();
    }

    public String getInclusivePrice() {
        return inclusivePrice;
    }

    public void setInclusivePrice(String inclusivePrice) {
        this.inclusivePrice = inclusivePrice == null ? null : inclusivePrice.trim();
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price == null ? null : price.trim();
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
}