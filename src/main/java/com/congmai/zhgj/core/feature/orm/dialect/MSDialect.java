package com.congmai.zhgj.core.feature.orm.dialect;

/**
 * 
 * @ClassName MSDialect
 * @Description MSSQL 数据库方言
 * @author tanzb
 * @Date 2017年7月26日 下午2:29:16
 * @version 1.0.0
 */
public class MSDialect extends Dialect {

    protected static final String SQL_END_DELIMITER = ";";

    @Override
    public String getLimitString(String sql, int offset, int limit) {
        return MSPageHepler.getLimitString(sql, offset, limit);
    }

    @Override
    public String getCountString(String sql) {
        return MSPageHepler.getCountString(sql);
    }
}
