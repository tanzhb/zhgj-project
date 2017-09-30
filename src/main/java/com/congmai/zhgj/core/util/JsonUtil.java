package com.congmai.zhgj.core.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * 
 * @ClassName JsonUtil
 * @Description 处理Json工具类
 * @author tanzb
 * @Date 2017年9月26日 下午6:30:52
 * @version 1.0.0
 */
public class JsonUtil {

	/**
	 * 
	 * @Description 将Json数组转化为List数组
	 * @param jsonStr
	 * @return
	 */
	public static List<Map<String, Object>> parseJSON2List(String jsonStr) {
		JSONArray jsonArr = JSONArray.fromObject(jsonStr);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Iterator<JSONObject> it = jsonArr.iterator();
		while (it.hasNext()) {
			JSONObject json2 = it.next();
			list.add(parseJSON2Map(json2.toString()));
		}
		return list;
	}

	/**
	 * 将集合类型数据(如List,Map等)转换成json字符串
	 * 
	 * @param obj
	 * @return
	 */
	public static String disposeListJson(Object obj) {
		JsonConfig jsonConfig = registerJsonValueProcessor();
		return JSONArray.fromObject(obj, jsonConfig).toString(4);
	}
	
	/**
	 * 将集合类型数据(如List,Map等)转换成Object json字符串
	 * 
	 * @param obj
	 * @return
	 */
	public static String disposeObjectJson(Object obj) {
		JsonConfig jsonConfig = registerJsonValueProcessor();
		return JSONObject.fromObject(obj, jsonConfig).toString(4);
	}

	/**
	 * 
	 * @Description 将Json对象转化为Map
	 * @param jsonStr
	 * @return
	 */
	public static Map<String, Object> parseJSON2Map(String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 最外层解析
		JSONObject json = JSONObject.fromObject(jsonStr);
		for (Object k : json.keySet()) {
			Object v = json.get(k);
			// 如果内层还是数组的话，继续解析
			if (v instanceof JSONArray) {
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				Iterator<JSONObject> it = ((JSONArray) v).iterator();
				while (it.hasNext()) {
					JSONObject json2 = it.next();
					list.add(parseJSON2Map(json2.toString()));
				}
				map.put(k.toString(), list);
			} else {
				map.put(k.toString(), v);
			}
		}
		return map;
	}

	protected static JsonConfig registerJsonValueProcessor() {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class,
				new DateJsonValueProcessor());
		jsonConfig.registerJsonValueProcessor(java.sql.Date.class,
				new DateJsonValueProcessor());
		jsonConfig.registerJsonValueProcessor(java.sql.Timestamp.class,
				new DateJsonValueProcessor());
		jsonConfig.registerJsonValueProcessor(java.lang.Integer.class,
				new NumberJsonValueProcessor());
		jsonConfig.registerJsonValueProcessor(java.lang.Long.class,
				new NumberJsonValueProcessor());
		jsonConfig.registerJsonValueProcessor(java.lang.Float.class,
				new NumberJsonValueProcessor());
		jsonConfig.registerJsonValueProcessor(java.lang.Double.class,
				new NumberJsonValueProcessor());
		// jsonConfig.registerJsonBeanProcessor(org.hibernate.proxy.HibernateProxy.class,
		// new HibernateJsonBeanProcessor());
		// jsonConfig.setJsonBeanProcessorMatcher(new
		// HibernateJsonBeanProcessorMatcher());
		// jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		return jsonConfig;
	}

	/**
	 * 处理json中的日期对象
	 * 
	 * @author Administrator
	 * 
	 */
	private static class DateJsonValueProcessor implements JsonValueProcessor {

		public Object processArrayValue(Object object, JsonConfig jsconfig) {
			if (null != object) {
				return DateUtil.formatDateTimess((Date) object);
			} else {
				return "";
			}
		}

		public Object processObjectValue(String str, Object object,
				JsonConfig jsconfig) {
			if (null != object) {
				return DateUtil.formatDateTimess((Date) object);
			} else {
				return "";
			}
		}
	}

	/**
	 * 处理json中的数字对象
	 * 
	 * @author Administrator
	 * 
	 */
	private static class NumberJsonValueProcessor implements JsonValueProcessor {

		public Object processArrayValue(Object object, JsonConfig jsonConfig) {
			return process(object);
		}

		public Object processObjectValue(String str, Object object,
				JsonConfig jsonConfig) {
			return process(object);
		}

		private Object process(Object object) {
			if (null != object) {
				return object.toString();
			} else {
				return "";
			}
		}
	}

}
