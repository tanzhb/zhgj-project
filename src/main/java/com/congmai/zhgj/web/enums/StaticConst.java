package com.congmai.zhgj.web.enums;


/**
 * @ClassName staticConst
 * @Description TODO(静态常量枚举类)
 * @author zhaichao
 * @Date 2017年8月16日 下午6:02:22
 * @version 1.0.0
 */
public enum StaticConst {
	/**
	 * @Field @LADDER_TYPE_ONE : TODO(单笔阶梯价格)
	 */
	LADDER_TYPE_ONE("oneStagePrice","单笔阶梯价格"),
	/**
	 * @Field @LADDER_TYPE_MORE : TODO(累计阶梯价格)
	 */
	LADDER_TYPE_MORE("moreStagePrice","累计阶梯价格"),
	/**
	 * @Field @PRICE_TYPE_SALE : TODO(框架合同)
	 */
	CONTRACT_TYPE_FRAM("framContract","框架合同"),
	
	CONTRACT_TYPE_BUY("buyContract","采购合同"),
	
	CONTRACT_TYPE_SALE("saleContract","销售合同"),
	
	CONTRACT_TYPE_BUYFRAME("buyFrame","采购框架"),
	
	CONTRACT_TYPE_BUYORDER("buyOrder","采购订单"),
	
	CONTRACT_TYPE_SALEFRAME("saleFrame","销售框架"),
	
	CONTRACT_TYPE_SALEORDER("saleOrder","销售订单"),
	/**
	 * @Field @PRICE_TYPE_BUY : TODO(采购价格)
	 */
	PRICE_TYPE_BUY("buyPrice","采购价格"),
	/**
	 * @Field @PRICE_TYPE_SALE : TODO(销售价格)
	 */
	PRICE_TYPE_SALE("salePrice","销售价格"),
	/**
	 * @Field @ORDER_TYPE : TODO(订单类型)
	 */
	ORDER_TYPE("orderTypeIn","标准采购(内贸)"),
	/**
	 * @Field @COMNAME : TODO(平台名称)
	 */
	COMNAME("comName","中航能科（上海）能源科技有限公司"),
	/**
	 * @Field @DAILIBUY : TODO(平台名称)
	 */
	DAILIBUY("dailiBuy","委托采购"),
	
	ZIZHUBUY("zizhuBuy","委托采购"),
	/**
	 * @Field @DAILISALE : TODO(平台名称)
	 */
	DAILISALE("dailiSale","委托销售"),
	/**
	 * @Field @DAILISALE : TODO(平台名称)
	 */
	WAIMAO("waimao","外贸"),
	
	NEIMAO("neimao","内贸"),
	
	CAIGOU("caigou","采购"),
	
	XIAOSHOU("xiaoshou","销售");
	
	
	
	private String value;
	private String info;
	 
    private StaticConst(String value,String info) {
        this.value = value;
        this.info = info;
    }
 
    public String getValue() {
        return value;
    } 
    
    public String getInfo() {
    	return info;
    }  
    
 
    public static String getInfo(String value) {
        for (StaticConst c : StaticConst.values()) {
            if (value.equals(c.getValue())) {
                return c.info;
            }
        }
        return null;
    }
    
    public static String getValueByInfo(String info) {
    	for (StaticConst c : StaticConst.values()) {
    		if (info.equals(c.getInfo())) {
    			return c.value;
    		}
    	}
    	return null;
    }
}
