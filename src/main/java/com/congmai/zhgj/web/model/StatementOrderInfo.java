package com.congmai.zhgj.web.model;

public class StatementOrderInfo {
	private String  orderNum;
	private String  contractNum;
	private String  orderDate;
	private String  orderStatus;
	private String  totalMoney; //订单金额
	private String  paymentMoney; //应付金额
	private String  totalPaymentAmount; //已付金额
	private String  totalUnPaymentMoney; //未付金额
	private String  totalServiceMoney;  //服务费
	private String  totalReadyAmount; //已开金额
	private String  totalUnReadyAmount; //未开金额
	private String  overDueMoney;//超期金额
	
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public String getContractNum() {
		return contractNum;
	}
	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}
	public String getTotalPaymentAmount() {
		return totalPaymentAmount;
	}
	public void setTotalPaymentAmount(String totalPaymentAmount) {
		this.totalPaymentAmount = totalPaymentAmount;
	}
	public String getTotalUnPaymentMoney() {
		return totalUnPaymentMoney;
	}
	public void setTotalUnPaymentMoney(String totalUnPaymentMoney) {
		this.totalUnPaymentMoney = totalUnPaymentMoney;
	}
	public String getTotalServiceMoney() {
		return totalServiceMoney;
	}
	public void setTotalServiceMoney(String totalServiceMoney) {
		this.totalServiceMoney = totalServiceMoney;
	}
	public String getTotalReadyAmount() {
		return totalReadyAmount;
	}
	public void setTotalReadyAmount(String totalReadyAmount) {
		this.totalReadyAmount = totalReadyAmount;
	}
	public String getTotalUnReadyAmount() {
		return totalUnReadyAmount;
	}
	public void setTotalUnReadyAmount(String totalUnReadyAmount) {
		this.totalUnReadyAmount = totalUnReadyAmount;
	}
	public String getOverDueMoney() {
		return overDueMoney;
	}
	public void setOverDueMoney(String overDueMoney) {
		this.overDueMoney = overDueMoney;
	}
	public String getPaymentMoney() {
		return paymentMoney;
	}
	public void setPaymentMoney(String paymentMoney) {
		this.paymentMoney = paymentMoney;
	}
	
	
}
