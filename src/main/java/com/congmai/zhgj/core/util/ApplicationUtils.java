package com.congmai.zhgj.core.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @ClassName ApplicationUtils
 * @Description ApplicationUtils : 程序工具类，提供大量的便捷方法
 * @author tanzb
 * @Date 2017年7月26日 下午2:40:45
 * @version 1.0.0
 */
public class ApplicationUtils {

    /**
     * 产生一个36个字符的UUID
     *
     * @return UUID
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString();
    }


    /**
     * sha1加密
     *
     * @param value 要加密的值
     * @return sha1加密后的值
     */
    public static String sha1Hex(String value) {
        return DigestUtils.sha1Hex(value);
    }


    public static List<String> getIdList(String ids) {
    	List<String> list = new ArrayList<String>();
		String[] str = ids.split(",");
		for (int i = 0; i < str.length; i++) {
			list.add(str[i]);
		}
		return list;
	}
    
    
    /**
     * 产生一个32个字符的UUID
     *
     * @return UUID
     */
    public static String random32UUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

	/**
	 * md5加密
	 *
	 * @param value
	 *            要加密的值
	 * @return md5加密后的值
	 */
	public static String md5Hex(String value) {
		return DigestUtils.md5Hex(value);
	}

	/**
	 * sha256加密
	 *
	 * @param value
	 *            要加密的值
	 * @return sha256加密后的值
	 */
	public static String sha256Hex(String value) {
		return DigestUtils.sha256Hex(value);
	}
	
	/**
	 * 
	 * @Description (TODO生成一个11位编号)
	 * @return
	 */
	public static String getFromNumber(){
		String date = DateUtil.format("yyyyMMdd", new Date());
		int random = 0;
		if(!Constants.NOW_DATE_STRING.equals(DateUtil.format("yyyyMMdd", new Date()))){
			Constants.TAKEDELIVERY_NUMBER_INDEX = 1;//系统重启或第二天索引从1开始
			Constants.NOW_DATE_STRING = DateUtil.format("yyyyMMdd", new Date());//更新时间
			random = (int)(Math.random()*10);//生成一个随机数
		}
		date = StringUtils.substring(date, 2);
		int index = Constants.TAKEDELIVERY_NUMBER_INDEX;
		String str = String.format("%4d", index).replace(" ", "0");  
		Constants.TAKEDELIVERY_NUMBER_INDEX++;
		return date+random+str;
	}
	
	public static void main(String args[]){
		String ids = "45";
		List<String> list = ApplicationUtils.getIdList(ids);
		System.out.print("++++++++  " + list);
	}

}
