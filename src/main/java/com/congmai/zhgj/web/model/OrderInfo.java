package com.congmai.zhgj.web.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class OrderInfo extends BaseVO {
    /**
	 * @Field @serialVersionUID : 订单
	 */
	private static final long serialVersionUID = 4164102013915166314L;
	
	//已发货
	public static final String DELIVER = "1";
	
	public static final String WAIT_TAKEDELIVER= "88";//销售订单待收货
	//已收货
	public static final String TAKEDELIVER = "2";
	//已检验
	public static final String CHECK = "3";
	//已出库
	public static final String OUTRECORD = "4";
	//已入库
	public static final String INRECORD = "5";
	//付款中
	/*
	 * 付款中
	 */
	public static final String PAYING = "0";
	//已付款
	public static final String PAY = "1";
	//已收款
	public static final String RECIVE = "2";
	//开票中
	public static final String BILLING = "3";
	//已开票
	public static final String BILL = "4";
	//已收票
	public static final String RECIVEBILL = "5";
	
	//待确认
	public static final String TOBECONFIRMED = "1";
	//已确认
	public static final String CONFIRMED = "2";
	//待签合同
	public static final String SIGNCONTRACT = "3";
	
	public static final String CLEARANCE= "6";//待清关
	
	public static final String DECLARATION= "7";//待报关
	
	public static final String WAIT_IN_CHECK= "8";//待检验(入库)
	
	public static final String WAIT_OUT_CHECK= "9";//待检验(出库)
	
    public static final String WAIT_INRECORD= "11";//待入库
	
	public static final String WAIT_OUTRECORD= "12";//待出库
	
	public static final String COMPLETE= "13";//已报关/收货完成
	
	public static final String CUSTOMER= "44";//客户端初始状态
	
	public static final String CUSTOMER_SUBMIT= "55";//客户端提交，待接收状态
	
	
	public static final String WAIT_SUPPLY_CONFIRMED= "66";//平台提交待供应商确认
	
	public static final String WAIT_PT_CONFIRMED= "77";//供应商确认后，待平台
	
	private String serialNum;

    private String contractSerial;
    
    private ContractVO contract;
    
    private ContractVO frame;//框架协议

    private String supplyComId;

    private String buyComId;

    private String orderNum;

    private String orderType;
    
    private String tradeType;
    
    private String seller;

    private String entrustParty;

    private String serviceModel;

    private String demandPlanSerial;

    private String saleApplySerial;
    
    private String projectNum;

    private String settlementClause;

    private String orderSerial;
    
    private String frameSerial;

    private String deliveryMode;
    
    private String paiedMoney;
    
    
    private String billedMoney;

    private String transportMode;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date orderDate;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date makeDate;

    private String maker;

    private String rate;

    private String currency;

    private String exchangeRate;
    
    private String materielCount;
    private String materielAmount;
    private String rateAmount;
    private String rateAndAmount;
    private String otherAmount;
    private String orderAmount;

    private String remark;
    
    private String contractContent;    
    
    
    private String orderRemark;


    private String delFlg;

    private String creator;

    private Date createTime;

    private String updater;

    private Date updateTime;
    
    private String unBillAmount;//未开金额合计
    
    private String supplyName;
    
    private String buyName;
    
    private ProcessBase processBase;
    //发货状态
    private String deliverStatus;
    //付款状态
    private String payStatus;
    //开票状态
    private String billStatus;
    
    private String unPayOrReceiptMoney;//未付/未收金额(发票里面用);
    
    private  String unBillOrReceiptMoney;//未开票/收票金额(发票里面用);
    
    private String deliveryCount;//发货数量

    private String payAmount;//付款金额
    
    private String receiveCount;//实际入库数量
    
    private String applyCount;//已申请数量
    
    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum == null ? null : serialNum.trim();
    }

    public String getContractSerial() {
        return contractSerial;
    }

    public void setContractSerial(String contractSerial) {
        this.contractSerial = contractSerial == null ? null : contractSerial.trim();
    }

    public String getSupplyComId() {
        return supplyComId;
    }

    public void setSupplyComId(String supplyComId) {
        this.supplyComId = supplyComId == null ? null : supplyComId.trim();
    }

    public String getBuyComId() {
        return buyComId;
    }

    public void setBuyComId(String buyComId) {
        this.buyComId = buyComId == null ? null : buyComId.trim();
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum == null ? null : orderNum.trim();
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType == null ? null : orderType.trim();
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller == null ? null : seller.trim();
    }

    public String getEntrustParty() {
        return entrustParty;
    }

    public void setEntrustParty(String entrustParty) {
        this.entrustParty = entrustParty == null ? null : entrustParty.trim();
    }

    public String getServiceModel() {
        return serviceModel;
    }

    public void setServiceModel(String serviceModel) {
        this.serviceModel = serviceModel == null ? null : serviceModel.trim();
    }

    public String getDemandPlanSerial() {
        return demandPlanSerial;
    }

    public void setDemandPlanSerial(String demandPlanSerial) {
        this.demandPlanSerial = demandPlanSerial == null ? null : demandPlanSerial.trim();
    }

    public String getSaleApplySerial() {
        return saleApplySerial;
    }

    public void setSaleApplySerial(String saleApplySerial) {
        this.saleApplySerial = saleApplySerial == null ? null : saleApplySerial.trim();
    }

    public String getSettlementClause() {
        return settlementClause;
    }

    public void setSettlementClause(String settlementClause) {
        this.settlementClause = settlementClause == null ? null : settlementClause.trim();
    }

    public String getOrderSerial() {
        return orderSerial;
    }

    public void setOrderSerial(String orderSerial) {
        this.orderSerial = orderSerial == null ? null : orderSerial.trim();
    }

    public String getDeliveryMode() {
        return deliveryMode;
    }

    public void setDeliveryMode(String deliveryMode) {
        this.deliveryMode = deliveryMode == null ? null : deliveryMode.trim();
    }

    public String getTransportMode() {
        return transportMode;
    }

    public void setTransportMode(String transportMode) {
        this.transportMode = transportMode == null ? null : transportMode.trim();
    }
    @JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd")
    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker == null ? null : maker.trim();
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate == null ? null : rate.trim();
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public String getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(String exchangeRate) {
        this.exchangeRate = exchangeRate == null ? null : exchangeRate.trim();
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount == null ? null : orderAmount.trim();
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

	public String getUnBillAmount() {
		return unBillAmount;
	}

	public void setUnBillAmount(String unBillAmount) {
		this.unBillAmount = unBillAmount;
	}

	public String getMaterielAmount() {
		return materielAmount;
	}

	public void setMaterielAmount(String materielAmount) {
		this.materielAmount = materielAmount;
	}

	public String getPaiedMoney() {
		return paiedMoney;
	}

	public void setPaiedMoney(String paiedMoney) {
		this.paiedMoney = paiedMoney;
	}

	public String getBilledMoney() {
		return billedMoney;
	}

	public void setBilledMoney(String billedMoney) {
		this.billedMoney = billedMoney;
	}	public String getOrderRemark() {
		return orderRemark;
	}

	public void setOrderRemark(String orderRemark) {
		this.orderRemark = orderRemark;
	}

	public String getRateAmount() {
		return rateAmount;
	}

	public void setRateAmount(String rateAmount) {
		this.rateAmount = rateAmount;
	}

	public String getRateAndAmount() {
		return rateAndAmount;
	}

	public void setRateAndAmount(String rateAndAmount) {
		this.rateAndAmount = rateAndAmount;
	}

	public String getOtherAmount() {
		return otherAmount;
	}

	public void setOtherAmount(String otherAmount) {
		this.otherAmount = otherAmount;
	}

	public String getMaterielCount() {
		return materielCount;
	}

	public void setMaterielCount(String materielCount) {
		this.materielCount = materielCount;
	}

	public ContractVO getContract() {
		return contract;
	}

	public void setContract(ContractVO contract) {
		this.contract = contract;
	}

	public String getSupplyName() {
		return supplyName;
	}

	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}

	public String getBuyName() {
		return buyName;
	}

	public void setBuyName(String buyName) {
		this.buyName = buyName;
	}

	public ProcessBase getProcessBase() {
		return processBase;
	}

	public void setProcessBase(ProcessBase processBase) {
		this.processBase = processBase;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
    @JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd")
	public Date getMakeDate() {
		return makeDate;
	}

	public void setMakeDate(Date makeDate) {
		this.makeDate = makeDate;
	}

	public String getContractContent() {
		return contractContent;
	}

	public void setContractContent(String contractContent) {
		this.contractContent = contractContent;
	}

	public String getDeliverStatus() {
		return deliverStatus;
	}

	public void setDeliverStatus(String deliverStatus) {
		this.deliverStatus = deliverStatus;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getBillStatus() {
		return billStatus;
	}

	public void setBillStatus(String billStatus) {
		this.billStatus = billStatus;
	}

	public String getUnPayOrReceiptMoney() {
		return unPayOrReceiptMoney;
	}

	public void setUnPayOrReceiptMoney(String unPayOrReceiptMoney) {
		this.unPayOrReceiptMoney = unPayOrReceiptMoney;
	}

	public String getUnBillOrReceiptMoney() {
		return unBillOrReceiptMoney;
	}

	public void setUnBillOrReceiptMoney(String unBillOrReceiptMoney) {
		this.unBillOrReceiptMoney = unBillOrReceiptMoney;
	}

	public String getProjectNum() {
		return projectNum;
	}

	public void setProjectNum(String projectNum) {
		this.projectNum = projectNum;
	}

	public String getDeliveryCount() {
		return deliveryCount;
	}

	public void setDeliveryCount(String deliveryCount) {
		this.deliveryCount = deliveryCount;
	}

	public String getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}

	public String getFrameSerial() {
		return frameSerial;
	}

	public void setFrameSerial(String frameSerial) {
		this.frameSerial = frameSerial;
	}

	public ContractVO getFrame() {
		return frame;
	}

	public void setFrame(ContractVO frame) {
		this.frame = frame;
	}

	public String getReceiveCount() {
		return receiveCount;
	}

	public void setReceiveCount(String receiveCount) {
		this.receiveCount = receiveCount;
	}

	public String getApplyCount() {
		return applyCount;
	}

	public void setApplyCount(String applyCount) {
		this.applyCount = applyCount;
	}

	
}