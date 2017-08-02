package com.congmai.zhgj.core.feature.orm.dialect;

/**
 * 
 * @ClassName MySql5Dialect
 * @Description MySQL数据库方言
 * @author tanzb
 * @Date 2017年7月26日 下午2:30:41
 * @version 1.0.0
 */
public class MySql5Dialect extends Dialect {

    protected static final String SQL_END_DELIMITER = ";";

    @Override
    public String getLimitString(String sql, int offset, int limit) {
        return MySql5PageHepler.getLimitString(sql, offset, limit);
    }

    @Override
    public String getCountString(String sql) {
        return MySql5PageHepler.getCountString(sql);
    }
}
