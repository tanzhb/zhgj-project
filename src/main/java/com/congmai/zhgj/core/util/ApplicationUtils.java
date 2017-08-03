package com.congmai.zhgj.core.util;

import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;

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
     * 产生一个32个字符的UUID
     *
     * @return UUID
     */
    public static String random32UUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * md5加密
     *
     * @param value 要加密的值
     * @return md5加密后的值
     */
    public static String md5Hex(String value) {
        return DigestUtils.md5Hex(value);
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

    /**
     * sha256加密
     *
     * @param value 要加密的值
     * @return sha256加密后的值
     */
    public static String sha256Hex(String value) {
        return DigestUtils.sha256Hex(value);
    }

}
