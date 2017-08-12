package com.congmai.zhgj.web.model;

import java.util.Date;

/**
 * 
 * @ClassName Contract
 * @Description 合同模型
 * @author chenziwei
 * @Date 2017年7月31日 下午2:52:03
 * @version 1.0.0
 */
public class ContractVO {
    private String id;

    private String comId;

    private String contractNum;

    private String contractType;

    private String serviceModel;
    
    private String settlementClause;
    
    private Date startDate;
    
    
    private Date endDate;
    
    private Date signDate;
    
    private String signer;
    
    private String signerAddress;
    
    
    private String paymentMethod;
    
    private String paymentPeriod;
    
    
    private String remark;
    
    private String totalMaterialMoney;
    
    private String serviceMoney;
    
    
    private String logisticsMoney;
    
    private String totalMoney;
    
    private String electronicContract;
    
    private String signContract;
    
    private String versionNO;
    
    
    private String isLatestVersion;
    
    
    private String status;
    
    
    private String delFlg;
    
    
    private String creator;
    
    
    private Date createTime;
    
    private String updater;
    
    private Date updateTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getComId() {
		return comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}

	public String getContractNum() {
		return contractNum;
	}

	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}

	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public String getServiceModel() {
		return serviceModel;
	}

	public void setServiceModel(String serviceModel) {
		this.serviceModel = serviceModel;
	}

	public String getSettlementClause() {
		return settlementClause;
	}

	public void setSettlementClause(String settlementClause) {
		this.settlementClause = settlementClause;
	}

	

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getSignDate() {
		return signDate;
	}

	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}

	public String getSigner() {
		return signer;
	}

	public void setSigner(String signer) {
		this.signer = signer;
	}

	public String getSignerAddress() {
		return signerAddress;
	}

	public void setSignerAddress(String signerAddress) {
		this.signerAddress = signerAddress;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getPaymentPeriod() {
		return paymentPeriod;
	}

	public void setPaymentPeriod(String paymentPeriod) {
		this.paymentPeriod = paymentPeriod;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTotalMaterialMoney() {
		return totalMaterialMoney;
	}

	public void setTotalMaterialMoney(String totalMaterialMoney) {
		this.totalMaterialMoney = totalMaterialMoney;
	}

	public String getServiceMoney() {
		return serviceMoney;
	}

	public void setServiceMoney(String serviceMoney) {
		this.serviceMoney = serviceMoney;
	}

	public String getLogisticsMoney() {
		return logisticsMoney;
	}

	public void setLogisticsMoney(String logisticsMoney) {
		this.logisticsMoney = logisticsMoney;
	}

	public String getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}

	public String getElectronicContract() {
		return electronicContract;
	}

	public void setElectronicContract(String electronicContract) {
		this.electronicContract = electronicContract;
	}

	public String getSignContract() {
		return signContract;
	}

	public void setSignContract(String signContract) {
		this.signContract = signContract;
	}

	public String getVersionNO() {
		return versionNO;
	}

	public void setVersionNO(String versionNO) {
		this.versionNO = versionNO;
	}

	public String getIsLatestVersion() {
		return isLatestVersion;
	}

	public void setIsLatestVersion(String isLatestVersion) {
		this.isLatestVersion = isLatestVersion;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
    
    

   /* public Contract(String username, String password) {
        this.username = username;
        this.password = password;
    }*/



    /*@Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", password=" + password + ", state=" + state + ", createTime=" + createTime + "]";
    }*/

}