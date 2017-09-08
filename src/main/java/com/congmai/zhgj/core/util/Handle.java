package com.congmai.zhgj.core.util;

import java.math.BigDecimal;

public class Handle {
	
	public static double stringToDouble(String value){
		try {
			return new BigDecimal(value).doubleValue();
		} catch (Exception e) {
			System.out.println(e.getClass()+"----------->"+e.getMessage()+":"+e.getStackTrace());
		}
		
		return 0;
	}
}
