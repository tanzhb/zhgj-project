package com.congmai.zhgj.web.model;

import java.util.Date;

/**
 * @ClassName PriceCom
 * @Description TODO(价格里面的采供应商信息)
 * @author zhaichao
 * @Date 2017年11月30日 下午6:15:20
 * @version 1.0.0
 */
public class PriceCom {
    private String serialNum;

    private String priceSerial;

    private String comSerial;

    private String remark;

    private String delFlg;

    private String creator;

    private Date createTime;

    private String updater;

    private Date updateTime;
    
    private String comNum;
    
    private String comName;

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

    public String getComSerial() {
        return comSerial;
    }

    public void setComSerial(String comSerial) {
        this.comSerial = comSerial == null ? null : comSerial.trim();
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

	public String getComNum() {
		return comNum;
	}

	public void setComNum(String comNum) {
		this.comNum = comNum;
	}

	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}
	
}