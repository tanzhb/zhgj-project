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
	/*========================销售订单相关消息End==============================*/
	
	
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
	/*========================发货相关消息End==============================*/
	
	/*========================出入库相关消息Start==============================*/
	//9
	/**
	 * 入库完成消息(发给采购)
	 */
	public static final String URL_IN_TO_BUY = "inCheck";
	/**
	 * 入库完成消息(发给供应)
	 */
	public static final String URL_IN_TO_SALE = "inCheck";
	
	/**
	 * 出库完成消息(发给采购)
	 */
	public static final String URL_OUT_TO_BUY = "outCheck";
	/**
	 * 出库完成消息(发给供应)
	 */
	public static final String URL_OUT_TO_SALE = "outCheck";
	/*========================出入库相关消息End==============================*/
	
	/*========================出入库检验相关消息Start==============================*/
	//10
	/**
	 * 出库已检验消息
	 */
	public static final String URL_IN_CHECK_TO_BUY = "inCheckToBuy";
	/**
	 * 出库已检验消息
	 */
	public static final String URL_IN_CHECK_TO_SALE = "inCheckToSale";
	/**
	 * 入库已检验消息
	 */
	public static final String URL_OUT_CHECK_TO_BUY = "outCheckToBuy";
	/**
	 * 入库已检验消息
	 */
	public static final String URL_OUT_CHECK_TO_SALE = "outCheckToSale";
	/*========================出入库检验相关消息End==============================*/
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
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
	
	/*========================采购订单相关消息End==============================*/
	
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
	//10
	/**
	 *  出库已检验消息(发给采购)
	 */
	public static final String TEMP_OUT_CHECK_TO_BUY = "102";
	/** 
	 * 出库已检验消息(发给供应)
	 */
	public static final String TEMP_OUT_CHECK_TO_SALE = "101";

	/*===========出入库检验相关消息End=================*/
	
	
}
