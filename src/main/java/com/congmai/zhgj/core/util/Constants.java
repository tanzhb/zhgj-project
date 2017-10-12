package com.congmai.zhgj.core.util;

import java.util.Date;

public class Constants {
	
	/***************** system *****************/
	public static final String DB_NAME = "mysql";
	public static final String MESSAGE = "message";
	
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
	
	/***************** 销售订单流程key *****************/
	public static final String SALEORDER = "com.congmai.zhgj.saleOrder";


	/***************** 销项票KEY *****************/
	public static final String OUTINVOICE_KEY = "com.congmai.zhgj.outInvoice";
	
	
	
}
