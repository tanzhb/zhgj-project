package com.congmai.zhgj.core.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

/**
 * 
 * @ClassName: BeanUtils
 * @Description:TODO(工具类，判断bean，数组，集合是否为空)
 * @author: zml
 * @date: 2014-4-18 上午10:20:26
 *
 */
public class BeanUtils {

	public static boolean isBlank(Object obj){
		if(obj == null){
			return true;
		}
		return false;
	}
	public static boolean isBlank(List list){
		if(list == null || list.size()<=0){
			return true;
		}
		return false;
	}
	public static boolean isBlank(Map map){
		if(map == null || map.size()<=0){
			return true;
		}
		return false;
	}
	public static boolean isBlank(Object []obj){
		if(obj == null || obj.length<=0){
			return true;
		}
		return false;
	}
	
	/**
     * 将SolrDocument转换成Bean
     * @param record
     * @param clazz
     * @return
     */
    public static Object toBean(SolrDocument record, SolrDocument clazz) throws InstantiationException, IllegalAccessException{
        Object obj = null;
        obj = clazz;
        java.lang.reflect.Field[] fields = clazz.getClass().getDeclaredFields();
        for(java.lang.reflect.Field field:fields){
            Object value = record.get(field.getName());
            try {
            	org.apache.commons.beanutils.BeanUtils.setProperty(obj, field.getName(), value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return obj;
    }
    
    /**
     * 将SolrDocumentList转换成BeanList
     * @param records
     * @param clazz
     * @return
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     */
    public static Object toBeanList(SolrDocumentList records, SolrDocument clazz) throws InstantiationException, IllegalAccessException{
        List list = new ArrayList();
        for(SolrDocument record : records){
            list.add(toBean(record,clazz));
        }
        return list;
    }
}
