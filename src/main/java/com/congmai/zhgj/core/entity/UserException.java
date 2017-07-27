package com.congmai.zhgj.core.entity;

/**
 * 
 * @ClassName UserException
 * @Description 用户自定义异常
 * @author tanzb
 * @Date 2017年7月26日 下午2:17:26
 * @version 1.0.0
 */
public class UserException extends RuntimeException {

    /**
     * 异常发生时间
     */
    private long date = System.currentTimeMillis();

    public long getDate() {
        return date;
    }
}
