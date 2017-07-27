package com.congmai.zhgj.core.feature.orm.dialect;

/**
 * 
 * @ClassName Dialect
 * @Description 数据库方言抽象类
 * @author tanzb
 * @Date 2017年7月26日 下午2:28:24
 * @version 1.0.0
 */
public abstract class Dialect {

    /**
     * 得到分页sql
     * 
     * @param sql
     * @param offset
     * @param limit
     * @return
     */
    public abstract String getLimitString(String sql, int offset, int limit);

    /**
     * 得到总数量 sql
     * 
     * @param sql
     * @return
     */
    public abstract String getCountString(String sql);

}
