package com.congmai.zhgj.core.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

/**
 * 
 * @ClassName JsonDateSerializer
 * @Description 日期转换器
 * @author tanzb
 * @Date 2017年8月4日 下午11:41:26
 * @version 1.0.0
 */
public class JsonDateSerializer extends JsonSerializer<Date> {
	private SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	@Override
	public void serialize(Date date, JsonGenerator gen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
		String value = dateFormat.format(date);
		gen.writeString(value);
	}
}