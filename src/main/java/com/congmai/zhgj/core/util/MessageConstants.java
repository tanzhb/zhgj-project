package com.congmai.zhgj.core.util;

public class MessageConstants {
	
	/**
	 * 系统消息
	 */
	public static final String SYSTEM_MESSAGE= "系统消息";
	
	/**
	 * 业务提醒
	 */
	public static final String BUSSINESS_MESSAGE = "业务提醒";
	
	
	/**
	 * 平台
	 */
	public static final String PLATFORM_NAME = "中航能科（上海）能源科技有限公司";
	
	
	
	
	/*========================采购订单相关消息Start==============================*/
	/**
	 * 采购订单申请消息
	 */
	public static final String APPLY_BUY_ORDER = "applyBuyOrder";
	
	/**
	 * 采购订单被驳回
	 */
	public static final String REFUSE_BUY_ORDER = "refuseBuyOrder";
	
	/**
	 * 采购订单单个节点通过审核
	 */
	public static final String SINGLE_AGREE_BUY_ORDER = "singleAgreeBuyOrder";
	
	/**
	 * 采购订单通过审核
	 */
	public static final String AGREE_BUY_ORDER = "agreeBuyOrder";
	
	/**
	 * 采购订单待确认（发给供应商）
	 */
	public static final String CONFIRM_BUY_ORDER = "confirmBuyOrder";
	
	/**
	 * 采购订单被确认
	 */
	public static final String BE_CONFIRM_BUY_ORDER = "beConfirmBuyOrder";
	/**
	 * 平台代发货通知
	 */
	public static final String NOTICESUPPLY = "noticeSupply";
	
	/**
	 * 付款申请被确认(审批通过)
	 */
	public static final String BE_CONFIRM_PAY = "beConfirmPayMemoRecord";
	/**
	 * 提示平台销售组人员采购商发布采购订单(接收)
	 */
	public static final String BE_RECEIVE_SALE_ORDER = "beReceiveSaleOrder";
	/**
	 * 提示平台采购组人员委托销售订单分解成功提示发起采购
	 */
	public static final String BE_CONFIRM_APPLY_BUY_ORDER = "beConfirmApplyBuyOrder";
	/*========================销售订单相关消息End==============================*/
	/**
	 * 销售订单申请消息
	 */
	public static final String APPLY_SALE_ORDER = "applySaleOrder";
	
	/**
	 * 销售订单被驳回
	 */
	public static final String REFUSE_SALE_ORDER = "refuseSaleOrder";
	
	/**
	 * 销售订单单个节点通过审核
	 */
	public static final String SINGLE_AGREE_SALE_ORDER = "singleAgreeSaleOrder";
	
	/**
	 * 销售订单通过审核
	 */
	public static final String AGREE_SALE_ORDER = "agreeSaleOrder";
	/**
	 * 销售订单分解采购计划（发给采购发布人员）
	 */
	public static final String SALE_TO_PALN = "sale2paln";
	
	/**
	 * 采购计划发布（发给采购人员）
	 */
	public static final String PALN_TO_BUY = "paln2buy";
	
	/**
	 * 入库2仓储
	 */
	public static final String IN_TO_STOCK = "in2stock";
	/**
	 * 确认代发货生成入库检验单(采购/仓储)/清关单/入库通知单
	 */
	public static final String IN_TO_WAITCHECK = "in2WaitCheck";
	/**
	 * 确认入库to采购订单制单人/采购计划制单人/销售订单制单人
	 */
	public static final String IN_TO_INSTOCK = "in2Instock";
	/*========================销售订单相关消息Start==============================*/
	//1
	
	/*========================销售订单相关消息End==============================*/
	
	/*========================物料相关消息Start==============================*/
	//2
	/*========================物料相关消息End==============================*/
	
	/*========================企业相关消息Start==============================*/
	//3
	/*========================企业相关消息End==============================*/
	
	/*========================需求计划相关消息Start==============================*/
	//4
	/*========================需求计划相关消息End==============================*/
	
	/*========================价格目录相关消息Start==============================*/
	
	/*========================价格目录相关消息End==============================*/
	
	/*========================仓库管理相关消息Start==============================*/
	//6
	/*========================仓库管理相关消息End==============================*/
	
	
	/*========================收货相关消息Start==============================*/
	/**
	 * 逾期未到货
	 */
	public static final String NO_TAKE_DELIVERY = "noTakeDelivery";
	/**
	 * 收货
	 */
	public static final String TAKE_DELIVERY = "takeDelivery";
	/*========================收货相关消息End==============================*/
	
	
	
	/*========================发货相关消息Start==============================*/
	//8
	
	/**
	 * 逾期未发货
	 */
	public static final String NO_DELIVERY = "noDelivery";
	
	/**
	 * 发货
	 */
	public static final String DELIVERY = "delivery";
	
	/**
	 * 收款
	 */
	public static final String SHOUKUAN = "shoukuan";
	
	/**
	 * 付款
	 */
	public static final String FUKUAN = "fukuan";
	/*========================发货相关消息End==============================*/
	
	/*===========出入库相关消息Start====== ========*/
	//9
	/**
	 * 入库完成消息(发给采购)
	 */
	public static final String IN_TO_BUY = "inToBuy";
	/**
	 * 入库完成消息(发给供应)
	 */
	public static final String IN_TO_SALE = "inToSale";
	
	/**
	 * 出库完成消息(发给采购)
	 */
	public static final String OUT_TO_BUY = "outToBuy";
	/**
	 * 出库完成消息(发给供应)
	 */
	public static final String OUT_TO_SALE = "outToSale";
	/**
	 * 出库完成消息(发给销售组)
	 */
	public static final String OUT_TO_SALE_GROUP = "outToSaleGroup";
	
	
	/**
	 * 入库完成消息(入库完成的采购订单，通知关联的销售订单制单人)
	 */
	public static final String IN_TO_BUY_TO_SALE = "inToBuyToSale";
	/**
	 * 清报关提醒(确认发货/代发货产生清报关，通知关联的采购销售订单制单人)
	 */
	public static final String IN_TO_CUSTOMSFORM = "inToCustomsform";
	
	
	/*===========出入库相关消息End====================*/
	
	
	/*========出入库检验相关消息Start==========*/
	//10
	/**
	 * 出库已检验消息
	 */
	public static final String IN_CHECK_TO_BUY = "inCheckToBuy";
	/**
	 * 出库已检验消息
	 */
	public static final String IN_CHECK_TO_SALE = "inCheckToSale";
	/**
	 * 入库已检验消息
	 */
	public static final String OUT_CHECK_TO_BUY = "outCheckToBuy";
	/**
	 * 入库已检验消息
	 */
	public static final String OUT_CHECK_TO_SALE = "outCheckToSale";
	/*========================出入库检验相关消息End==============================*/
	
	

	
	
	
	
	
	
	/***********************************************跳转URL**********************************************/
	/**
	 * 采购订单申请消息URL
	 */
	public static final String URL_APPLY_BUY_ORDER = "buyOrder({tabHref:'1'})";
	
	/**
	 * 采购订单驳回消息URL
	 */
	public static final String URL_REFUSE_BUY_ORDER = "buyOrder({tabHref:'1'})";
	/**
	 * 采购订单单个节点审核通过消息URL
	 */
	public static final String URL_SINGLE_AGREE_BUY_ORDER = "buyOrder({tabHref:'1'})";
	/**
	 * 采购订单审核通过消息URL
	 */
	public static final String URL_AGREE_BUY_ORDER = "buyOrder({tabHref:'1'})";
	/**
	 * 采购订单等待确认消息URL
	 */
	public static final String URL_CONFIRM_BUY_ORDER = "supplyOrder";
	/**
	 * 采购订单被确认消息URL
	 */
	public static final String URL_BE_CONFIRM_BUY_ORDER = "buyOrder";
	
	/**
	 *付款申请通过URL
	 */
	public static final String URL_AGREE_PAYMENTRECORD = "paymentRecordC";
	
	/**
	 * 销售订单URL
	 */
	public static final String URL_SALE_ORDER = "saleOrder";//
	
	/**
	 * 销售订单URL
	 */
	public static final String URL_APPLY_SALE_ORDER = "saleOrder({tabHref:'1'})";
	
	/**
	 * 销售订单审核通过消息URL
	 */
	public static final String URL_AGREE_SALE_ORDER = "saleOrder({tabHref:'1'})";
	/**
	 * 销售订单审核通过消息URL
	 */
	public static final String URL_CONFIRM_SALE_ORDER= "saleOrder";
	/**
	 * 提示平台销售组人员采购商发布采购订单(接收)URL
	 */
	public static final String URL_BE_RECEIVE_SALE_ORDER= "saleOrder";
	
	/**
	 * 提示平台采购组人员委托销售订单分解成功提示发起采购URL
	 */
	public static final String URL_BE_CONFIRM_APPLY_BUY_ORDER = "buyOrder";
	
	
	
	/*========================销售订单相关消息Start==============================*/
	//1
	
	/*========================销售订单相关消息End==============================*/
	
	/*========================物料相关消息Start==============================*/
	//2
	/*========================物料相关消息End==============================*/
	
	/*========================企业相关消息Start==============================*/
	//3
	/*========================企业相关消息End==============================*/
	
	/*========================需求计划相关消息Start==============================*/
	//4
	/*========================需求计划相关消息End==============================*/
	
	/*========================价格目录相关消息Start==============================*/
	//5
	/*========================价格目录相关消息End==============================*/
	
	/*========================仓库管理相关消息Start==============================*/
	//6
	/*========================仓库管理相关消息End==============================*/
	
	/*========================收货相关消息Start==============================*/
	//7
	/**
	 * 逾期未到货
	 */
	public static final String URL_NO_TAKE_DELIVERY = "takeDelivery";
	/**
	 * 收货后返回给供应商消息
	 */
	public static final String URL_TAKE_DELIVERY = "delivery";
	
	/*========================收货相关消息End==============================*/
	
	/*========================发货相关消息Start==============================*/
	//8
	/**
	 * 发货后给客户信息
	 */
	public static final String URL_DELIVERY = "takeDelivery";
	
	/**
	 * 付款后给供应商信息
	 */
	public static final String URL_SHOUKUAN= "gatheringMoneyRecord";
	
	/**
	 * 收款后给采购商信息
	 */
	public static final String URL_FUKUAN= "paymentRecordC";
	/*========================发货相关消息End==============================*/
	
	/*========================出入库相关消息Start==============================*/
	//9
	/**
	 * 入库完成消息(发给采购)
	 */
	public static final String URL_IN_TO_BUY = "takeDelivery";
	/**
	 * 入库完成消息(发给供应)
	 */
	public static final String URL_IN_TO_SALE = "delivery";
	
	/**
	 * 出库完成消息(发给采购)
	 */
	public static final String URL_OUT_TO_BUY = "takeDelivery";
	/**
	 * 出库完成消息(发给供应)
	 */
	public static final String URL_OUT_TO_SALE = "delivery";
	/**
	 * 出库完成消息(发给销售组)
	 */
	public static final String URL_OUT_TO_SALE_GROUP = "delivery";
	/*========================出入库相关消息End==============================*/
	
	/*========================出入库检验相关消息Start==============================*/
	//10
	/**
	 * 出库已检验消息
	 */
	public static final String URL_IN_CHECK_TO_BUY = "takeDelivery";
	/**
	 * 出库已检验消息
	 */
	public static final String URL_IN_CHECK_TO_SALE = "takeDelivery";
	/**
	 * 入库已检验消息
	 */
	public static final String URL_OUT_CHECK_TO_BUY = "takeDelivery";
	/**
	 * 入库已检验消息
	 */
	public static final String URL_OUT_CHECK_TO_SALE = "takeDelivery";
	/**
	 * 入库已检验消息
	 */
	public static final String URL_IN_TO_CHECK = "stockInOutCheck";
	/**
	 * 清关单通知消息
	 */
	public static final String URL_IN_TO_CLEARANCE  = "clearance";
	/**
	 * 报关单通知消息
	 */
	public static final String URL_IN_TO_DECLARATION = "declaration ";
	
	
	/*========================出入库检验相关消息End==============================*/
	public static final String URL_TO_BUYPLAN = "procurementPlan";

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/***********************************************消息模板编号**********************************************/
	
	
	/*========================采购订单相关消息Start==============================*/
	/**
	 * 采购订单申请消息模板
	 */
	public static final String TEMP_APPLY_BUY_ORDER = "01"; 
	/**
	 * 采购订单驳回消息模板
	 */
	public static final String TEMP_REFUSE_BUY_ORDER = "02"; 
	/**
	 * 采购订单单个节点审核通过消息模板
	 */
	public static final String TEMP_SINGLE_AGREE_BUY_ORDER = "03"; 
	/**
	 * 采购订单审核通过消息模板
	 */
	public static final String TEMP_AGREE_BUY_ORDER = "04"; 
	/**
	 * 采购订单等待确认消息模板
	 */
	public static final String TEMP_CONFIRM_BUY_ORDER = "05"; 
	/**
	 * 采购订单被确认消息模板
	 */
	public static final String TEMP_BE_CONFIRM_BUY_ORDER = "06"; 
	/**
	 * 采购订单审批发送给销售订单制单人消息模板
	 */
	public static final String TEMP_AGREE_BUY_SALEORDER = "007"; 
	/**
	 * 付款申请通过通知付款单制单人消息模板
	 */
	public static final String TEMP_AGREE_PAYMENTRECORD= "008"; 
	/**
	 * 采购商发布采购订单通知平台销售组人员
	 */
	public static final String TEMP_BE_RECEIVE_SALE_ORDER= "009"; 
	/**
	 * 提示平台采购组人员委托销售订单分解成功提示发起采购
	 */
	public static final String TEMP_BE_CONFIRM_APPLY_BUY_ORDER = "010";
	
	/*========================采购订单相关消息End==============================*/
	
	/*========================销售订单相关消息Start==============================*/
	/**
	 * 销售订单申请消息模板
	 */
	public static final String TEMP_APPLY_SALE_ORDER = "07"; 
	/**
	 * 销售订单驳回消息模板
	 */
	public static final String TEMP_REFUSE_SALE_ORDER = "08"; 
	/**
	 * 销售订单单个节点审核通过消息模板
	 */
	public static final String TEMP_SINGLE_AGREE_SALE_ORDER = "09"; 
	/**
	 * 销售订单审核通过消息模板(通知销售订单制单人)
	 */
	public static final String TEMP_AGREE_SALE_ORDER = "10"; 
	
	/**
	 * 销售订单审核通过消息模板(通知除制单人外其他人)
	 */
	public static final String TEMP_AGREE_SALE_ORDER1 = "11"; 
	
	/*========================销售订单相关消息End==============================*/
	
	/*========================物料相关消息Start==============================*/
	//2
	/*========================物料相关消息End==============================*/
	
	/*========================企业相关消息Start==============================*/
	//3
	/*========================企业相关消息End==============================*/
	
	/*========================需求计划相关消息Start==============================*/
	//4
	/*========================需求计划相关消息End==============================*/
	
	/*========================价格目录相关消息Start==============================*/
	/**
	 * 采购价格申请消息
	 */
	public static final String APPLY_BUY_PRICE = "applyBuyPrice";
	/**
	 * 销售价格申请消息
	 */
	public static final String APPLY_SALE_PRICE = "applySalePrice";
	
	/*========================价格目录相关消息End==============================*/
	/*========================销项票相关消息Start==============================*/
	/**
	 * 销项票申请消息
	 */
	public static final String APPLY_OUT_INVOICE = "applyOutInvoice";
	
	/*========================销项票相关消息End==============================*/
	
	/*========================仓库管理相关消息Start==============================*/
	//6
	/*========================仓库管理相关消息End==============================*/
	
	/*========================收货相关消息Start==============================*/
	//7
	/**
	 * 逾期未到货
	 */
	public static final String TEMP_NO_TAKE_DELIVERY = "71";
	
	/**
	 * 收货
	 */
	public static final String TEMP_TAKE_DELIVERY = "72";
	/*========================收货相关消息End==============================*/
	
	/*========================发货相关消息Start==============================*/
	//8
	/**
	 * 已发货消息模板
	 */
	public static final String TEMP_DELIVERY = "81"; 
	/*========================发货相关消息End==============================*/

	
	/*========================出入库相关消息Start==============================*/
	//9
	/**
	 * 入库完成消息(发给采购)
	 */
	public static final String TEMP_IN_TO_BUY = "91";
	/**
	 * 入库完成消息(发给供应)
	 */
	public static final String TEMP_IN_TO_SALE = "92";
	
	/**
	 * 出库完成消息(发给采购)
	 */
	public static final String TEMP_OUT_TO_BUY = "93";
	/**
	 * 出库完成消息(发给供应)
	 */
	public static final String TEMP_OUT_TO_SALE = "94";
	
	/**
	 * 入库完成消息(发给采购通知关联的销售订单制单人)
	 */
	public static final String TEMP_IN_TO_BUY_TO_SALE = "95";
	
	/**
	 * 出库完成消息(发给销售组)
	 */
	public static final String  TEMP_OUT_TO_SALE_GROUP = "96"; 
	/**
	 * 入库完成消息(发给通知关联的采购计划制单人)
	 */
	public static final String TEMP_IN_TO_BUY_TO_PLAN = "97";
	/**
	 * 入库完成消息(发给通知关联的采购订单制单人)
	 */
	public static final String TEMP_IN_TO_BUY_TO_BUY = "98";
	
	/*===========出入库相关消息End===========*/
	
	
	/*=======出入库检验相关消息Start===================*/
	/**
	 * 入库已检验消息(发给采购)
	 */
	public static final String TEMP_IN_CHECK_TO_BUY = "103";
	/**
	 * 入库已检验消息(发给供应)
	 */
	public static final String TEMP_IN_CHECK_TO_SALE = "104";
	/**
	 * 收款消息(发给供应)
	 */
	public static final String TEMP_SHOUKUAN = "105";
	/**
	 * 付款消息(发给采购)
	 */
	public static final String TEMP_FUKUAN = "106";
	//10
	/**
	 *  出库已检验消息(发给采购)
	 */
	public static final String TEMP_OUT_CHECK_TO_BUY = "102";
	/** 
	 * 出库已检验消息(发给供应)
	 */
	public static final String TEMP_OUT_CHECK_TO_SALE = "101";

	/**
	 * 销售分解(发给采购计划发布人员)
	 */
	public static final String SALE2PLAN = "107";
	/**
	 * 销售分解(发给物料消息给采购订单制单人)
	 */
	public static final String SALE2BUYORDEROWNER = "19";
	
	/**
	 * 销售分解(发给销售订单制单人)
	 */
	public static final String SALE2PLANOWNER = "110";
	
	/**
	 * 采购计划发布(发给采购人员)
	 */
	public static final String PLAN2BUY = "108";
	
	/**
	 * 入库通知(发给仓储)
	 */
	public static final String IN2STOCK = "109";
	/**
	 * 入库待检验(发给采购/仓储)
	 */
	public static final String IN2SWAITCHECK = "12 ";
	/**
	 * 出库待检验(发给采购/仓储)
	 */
	public static final String OUT2SWAITCHECK = "15 ";
	/**
	 * 出库通知(发给仓储)
	 */
	public static final String OUT2STOCK = "16";
	/**
	 * 清关单通知(发给仓储)
	 */
	public static final String IN2CLEARANCE = "17";
	/**
	 * 清关单通知(发给仓储)
	 */
	public static final String IN2DECLARATION = "18";
	/**
	 * 平台代发货通知
	 */
	public static final String TEMP_NOTICESUPPLY= "20";
	
	
	
	/*===========出入库检验相关消息End=================*/
	
	
}
