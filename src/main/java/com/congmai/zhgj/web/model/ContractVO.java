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
	//id
    private String id;

    //供应商
    private String comId;

    //合同编号
    private String contractNum;

    //合同类型
    private String contractType;

    //服务模式
    private String serviceModel;
    
    //结算条款
    private String settlementClause;
    
    //开始日期
    private Date startDate;
    
    //结束日期
    private Date endDate;
    
    //签订日期
    private Date signDate;
    
    //签订人
    private String signer;
    
    //签订地址
    private String signerAddress;
    
    //付款方式
    private String paymentMethod;
    
    //付款期限
    private String paymentPeriod;
    
    //备注
    private String remark;
    
    //总物料金额
    private String totalMaterialMoney;
    
    //服务费
    private String serviceMoney;
    
    //物流费
    private String logisticsMoney;
    
    //合计金额
    private String totalMoney;
    
    //电子合同(线上合同)
    private String electronicContract;
    
    //签字合同(线下合同)
    private String signContract;
    
    //版本号
    private String versionNO;
    
    //最新版本（1是0否）
    private String isLatestVersion;
    
    //物料状态
    private String status;
    
    //删除标志（1是0否）
    private String delFlg;
    
    //创建人
    private String creator;
    
    //创建时间
    private Date createTime;
    
    //更新人
    private String updater;
    
    //更新时间
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