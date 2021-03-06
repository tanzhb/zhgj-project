package com.congmai.zhgj.web.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * @ClassName Contract
 * @Description 合同模型
 * @author chenziwei
 * @Date 2017年7月31日 下午2:52:03
 * @version 1.0.0
 */
public class ContractVO  extends BaseVO {
	/**
	 * @Field @serialVersionUID : TODO(这里用一句话描述这个类的作用)
	 */
	private static final long serialVersionUID = -9026226685732898427L;


	//id
    private String id;


    private String comId;
    private String comName;
    //合同编号
    private String contractNum;

    //合同名称
    private String contractName;
    
    //合同标的物
    private String subjectMatter;
    
    //合同类型
    private String contractType;

    //服务模式
    private String serviceModel;
    
    //结算条款
    private String settlementClause;
    
    //开始日期
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startDate;
    
    //结束日期
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endDate;
    
    //签订日期
    @DateTimeFormat(pattern="yyyy-MM-dd")
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
    
    private String contractNumber;
    
    private String versionNO;
    
    //最新版本（1是0否）
    private String isLatestVersion;
    
    //状态
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

    //订单流水
    private String orderSerial;
    
    //关联订单数量
    private String orderAmount;
    
    //甲方
    private String firstParty;
    
    //乙方
    private String secondParty;
    
    //甲方签订人
    private String firstPartySigner;
    
    //乙方签订人
    private String secondPartySigner;
    
    //对方合同号
    private String otherPartyContractNum;
    
    
    private String supplyComId;
    
    
    private String orderNum;
    
    //框架协议状态
    
	public static final String WAIT_SUPPLY_CONFIRMED= "66";//平台提交待供应商确认
	
	public static final String WAIT_PT_CONFIRMED= "77";//供应商确认后，待平台
	
	public static final String WAIT_SIGN= "3";//待签合同
	
	public static final String SIGN= "2";//已签合同
	
	public static final String INIT= "0";//初始状态
	
	public static final String APPING= "1";//已确认待审批、审批中
	
    //框架协议内容
    private String contractContent;
    
    private String seller;
    
    private String serialNum;
    
    private ProcessBase processBase;
    
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
	
	@JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd")
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

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public String getOrderSerial() {
		return orderSerial;
	}

	public void setOrderSerial(String orderSerial) {
		this.orderSerial = orderSerial;
	}

	public String getFirstParty() {
		return firstParty;
	}

	public void setFirstParty(String firstParty) {
		this.firstParty = firstParty;
	}

	public String getSecondParty() {
		return secondParty;
	}

	public void setSecondParty(String secondParty) {
		this.secondParty = secondParty;
	}

	public String getFirstPartySigner() {
		return firstPartySigner;
	}

	public void setFirstPartySigner(String firstPartySigner) {
		this.firstPartySigner = firstPartySigner;
	}

	public String getSecondPartySigner() {
		return secondPartySigner;
	}

	public void setSecondPartySigner(String secondPartySigner) {
		this.secondPartySigner = secondPartySigner;
	}

	public String getOtherPartyContractNum() {
		return otherPartyContractNum;
	}

	public void setOtherPartyContractNum(String otherPartyContractNum) {
		this.otherPartyContractNum = otherPartyContractNum;
	}

	public String getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getSupplyComId() {
		return supplyComId;
	}

	public void setSupplyComId(String supplyComId) {
		this.supplyComId = supplyComId;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getContractContent() {
		return contractContent;
	}

	public void setContractContent(String contractContent) {
		this.contractContent = contractContent;
	}

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public ProcessBase getProcessBase() {
		return processBase;
	}

	public void setProcessBase(ProcessBase processBase) {
		this.processBase = processBase;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getSubjectMatter() {
		return subjectMatter;
	}

	public void setSubjectMatter(String subjectMatter) {
		this.subjectMatter = subjectMatter;
	}

	
	
}