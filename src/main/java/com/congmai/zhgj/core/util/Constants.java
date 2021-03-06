package com.congmai.zhgj.core.util;

import java.util.Date;

public class Constants {
	
	/***************** system *****************/
	public static final String DB_NAME = "mysql";
	public static final String MESSAGE = "message";
	
	public static final String DEL_FLAG_DEFUALT = "0";	//未删表示
	public static final String DELETE = "1";	//已删标识
	public static final String READ_FLAG_DEFUALT = "0";	//未读标识
	public static final String READ = "1";	//已读标识
	
	/***************** session key *****************/
    public static final String CURRENT_USER = "user";
    public static final String SESSION_FORCE_LOGOUT_KEY = "session.force.logout";
    
	/***************** default password (123456) *****************/
	public static final String DEFAULT_PASSWORD = "14e1b600b1fd579f47433b88e8d85291";
	
	/***************** 请假流程key *****************/
	public static final String VACATION = "com.zml.oa.vacation";
	
	/***************** 应付款流程key *****************/
	public static final String ACCOUNTPAYABLE = "com.congmai.zhgj.accountPayable";
	
	
	/***************** 发货流程key *****************/
	public static final String ACCOUNTDELIVERYABLE = "com.congmai.zhgj.accountDelivery";
	
	public static long SYSY_INIT_TIME = new Date().getTime();
	
	/***************** 收货编号生成起始索引 *****************/
	public static int TAKEDELIVERY_NUMBER_INDEX = 1; 
	
	/***************** 编号生成时间记录 *****************/
	public static String NOW_DATE_STRING = "19700101";
	
	/***************** 收货流程KEY *****************/
	public static final String TAKEDELIVERY_KEY = "com.congmai.zhgj.takeDelivery";
	
	/***************** 单据类型-收货 *****************/
	public static final String TAKEDELIVERY = "takeDelivery";
	
	/***************** 公告流程KEY *****************/
	public static final String NOTICE_KEY = "com.congmai.zhgj.notice";
	
	/***************** 单据类型-公告 *****************/
	public static final String NOTICE = "myNotice";
	
	/***************** 采购价格KEY *****************/
	public static final String BUYPRICE_KEY = "com.congmai.zhgj.buyPrice";
	
	/***************** 销售价格KEY *****************/
	public static final String SALEPRICE_KEY = "com.congmai.zhgj.salePrice";

	/***************** 采购订单流程key *****************/
	public static final String BUYORDER = "com.congmai.zhgj.buyOrder";
	
	/***************** 委托采购订单内贸流程key *****************/
	public static final String WTBUYORDER = "com.congmai.zhgj.wtBuyOrder";
	/***************** 委托销售订单内贸流程key *****************/
	public static final String WTSALEORDER = "com.congmai.zhgj.wtSaleOrder";
	/***************** 自主销售订单内贸流程key *****************/
	public static final String ZZSALEORDERIN = "com.congmai.zhgj.zzSaleOrderIn";
	
	/***************** 外贸采购订单流程key *****************/
	public static final String FOREIGN_TRADE_ORDER = "com.congmai.zhgj.foreignTradeOrder";
	
	/***************** FT外贸采购订单流程key *****************/
	public static final String FT_BUY_ORDER = "com.congmai.zhgj.FTBuyOrder";
	
	/***************** 自主采购订单流程key *****************/
	public static final String ZZBUYORDER= "com.congmai.zhgj.zzBuyOrder";
	
	/***************** 采购框架流程key *****************/
	public static final String BUYFRAME = "com.congmai.zhgj.buyFrame";
	
	/***************** 销售框架流程key *****************/
	public static final String SALEFRAME = "com.congmai.zhgj.saleFrame";
	
	
	/***************** 销售订单流程key *****************/
	public static final String SALEORDER = "com.congmai.zhgj.saleOrder";
	

	/***************** 销项票KEY *****************/
	public static final String OUTINVOICE_KEY = "com.congmai.zhgj.outInvoice";
	
	public static final String DELIVERY_KEY = "com.congmai.zhgj.delivery";
	/***************** 售前无合同发货流程key  *****************/
	public static final String BEFOREWITHOUTCONTRACTDELIVERY = "com.congmai.zhgj.beforeWithOutContractDelivery";
	
	/***************** 售后无合同发货流程key *****************/
	public static final String AFTERWITHOUTCONTRACTDELIVERY= "com.congmai.zhgj.afterWithoutContractDelivery";
	
	/***************** 采购计划流程key *****************/
	public static final String PROCUREMENTPLAN = "com.congmai.zhgj.buyApply";
	
	
	/******************************=======用户组类型START=====**********************************/
	/**
	 * 财务
	 */
	public static final String FINANCIAL =  "financial";
	/**
	 * 仓储
	 */
	public static final String STORAGE =  "storage";
	/**
	 * 国内采购组
	 */
	public static final String PURCHASE =  "purchase";
	
	/**
	 * 国际采购组
	 */
	public static final String INTERNATIONALPURCHASE =  "internationalPurchase";
	/**
	 * 销售
	 */
	public static final String SALES =  "sales";
	
	/**
	 * 供应链管理组
	 */
	public static final String MANAGER =  "manager";
	/**
	 * 产品经理/总监组
	 */
	public static final String PRODUCT_MANAGER =  "productManager";
	/**
	 * 综管部负责人组
	 */
	public static final String FULL_DEPARTMENT_LEADER  =  "fullDepartmentLeader ";
	/**
	 * 财务部负责人组
	 */
	public static final String FINANCIAL_LEADER  =  "financialLeader";
	/******************************=======用户组类型END=====**********************************/
	
	
	
}
