package com.congmai.zhgj.log.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface OperationLog {
	
	/**
	 * @Description (表单流水号)
	 * @return
	 */
	String objectSerial() default "";
    /**
     * 操作内容描述(例如：新建订单/发货/收货)
     * @return
     */
    String operationDesc() default "";
    
    /**
     * 数据操作类型(add/edit/delete/update)
     * @return
     */
    String operateType() default "";

    /**
     * 模块编码（例如采购订单：buyOrder）
     * @return
     */
    String moudleCode() default "";

    /**
     * 模块名称（例如:采购订单）
     * @return
     */
    String moudleName() default "";

    /**
     * 业务类型（预留字段）
     * @return
     */
    String bussType() default "";
    /**
     * 业务类型描述（预留字段）
     * @return
     */
    String bussTypeDesc() default "";
}