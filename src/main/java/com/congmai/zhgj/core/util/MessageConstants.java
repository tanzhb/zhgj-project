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
	 * 采购订单申请消息
	 */
	public static final String APPLY_BUY_ORDER = "applyBuyOrder";
	
	/**
	 * 采购订单被驳回
	 */
	public static final String REFUSE_BUY_ORDER = "refuseBuyOrder";
	
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
	
	
	
	/***********************************************消息模板编号**********************************************/
	
	/**
	 * 采购订单申请消息模板
	 */
	public static final String TEMP_APPLY_BUY_ORDER = "1"; 
	/**
	 * 采购订单驳回消息模板
	 */
	public static final String TEMP_REFUSE_BUY_ORDER = "2"; 
	/**
	 * 采购订单审核通过消息模板
	 */
	public static final String TEMP_AGREE_BUY_ORDER = "3"; 
	/**
	 * 采购订单等待确认消息模板
	 */
	public static final String TEMP_CONFIRM_BUY_ORDER = "4"; 
	/**
	 * 采购订单被确认消息模板
	 */
	public static final String TEMP_BE_CONFIRM_BUY_ORDER = "5"; 
}
