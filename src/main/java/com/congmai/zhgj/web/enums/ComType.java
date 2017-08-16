package com.congmai.zhgj.web.enums;

/**
 * @ClassName ComType
 * @Description TODO(企业类型枚举类)
 * @author likt
 * @Date 2017年8月16日 下午3:56:51
 * @version 1.0.0
 */
public enum ComType {
		
		/**
		 * @Field @BUYER : TODO(采购商)
		 */
		BUYER("1","采购商"),
		
		/**
		 * @Field @SUPPLIER : TODO(供应商)
		 */
		SUPPLIER("2","供应商"),
		/**
		 * @Field @SUPPLIER : TODO(承运人)
		 */
		CARRIER("3","承运人"),
		/**
		 * @Field @SUPPLIER : TODO(外协仓)
		 */
		SPARE_PARTS_WAREHOUSE("4","外协仓"),
		/**
		 * @Field @SUPPLIER : TODO(境外供应商)
		 */
		OVERSEAS_SUPPLIER("5","境外供应商"),
		/**
		 * @Field @SUPPLIER : TODO(装卸公司)
		 */
		STEVEDORING_COMPANY("6","装卸公司"),
		/**
		 * @Field @SUPPLIER : TODO(银行)
		 */
		BANK("7","银行"),
		/**
		 * @Field @SUPPLIER : TODO(保险公司)
		 */
		INSURANCE_COMPANY("8","保险公司");
		
		
		private String value;
		private String info;
		 
	    private ComType(String value,String info) {
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
            for (ComType c : ComType.values()) {
                if (value.equals(c.getValue())) {
                    return c.info;
                }
            }
            return null;
        }
        
        public static String getValueByInfo(String info) {
        	for (ComType c : ComType.values()) {
        		if (info.equals(c.getInfo())) {
        			return c.value;
        		}
        	}
        	return null;
        }
}
