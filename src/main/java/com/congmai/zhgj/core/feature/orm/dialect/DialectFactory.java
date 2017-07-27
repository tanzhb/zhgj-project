package com.congmai.zhgj.core.feature.orm.dialect;

import org.apache.ibatis.session.Configuration;

/**
 * 
 * @ClassName DialectFactory
 * @Description 数据库方言工厂,产生方言对象
 * @author tanzb
 * @Date 2017年7月26日 下午2:28:52
 * @version 1.0.0
 */
public class DialectFactory {

    public static String dialectClass = null;

    public static Dialect buildDialect(Configuration configuration) {
        if (dialectClass == null) {
            synchronized (DialectFactory.class) {
                if (dialectClass == null) {
                    dialectClass = configuration.getVariables().getProperty("dialectClass");
                }
            }
        }
        Dialect dialect = null;
        try {
            dialect = (Dialect) Class.forName(dialectClass).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("请检查 mybatis-config.xml 中  dialectClass 是否配置正确?");
        }
        return dialect;
    }
}
