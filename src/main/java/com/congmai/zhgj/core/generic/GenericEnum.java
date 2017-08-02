package com.congmai.zhgj.core.generic;

/**
 * 
 * @ClassName GenericEnum
 * @Description 所有自定义枚举类型实现该接口
 * @author tanzb
 * @Date 2017年7月26日 下午2:38:37
 * @version 1.0.0
 */
public interface GenericEnum {

    /**
     * value: 为保存在数据库中的值
     */
    public String getValue();

    /**
     * text : 为前端显示值
     */
    public String getText();

}
