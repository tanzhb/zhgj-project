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
	 * @Field @PRICE_TYPE_BUY : TODO(采购价格)
	 */
	PRICE_TYPE_BUY("buyPrice","采购价格"),
	/**
	 * @Field @PRICE_TYPE_SALE : TODO(销售价格)
	 */
	PRICE_TYPE_SALE("salePrice","销售价格");
	
	
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